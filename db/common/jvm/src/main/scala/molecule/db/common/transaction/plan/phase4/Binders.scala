package molecule.db.common.transaction.plan.phase4

import java.sql.PreparedStatement
import molecule.core.dataModel.*
import molecule.db.common.transaction.plan.*
import molecule.db.common.transaction.plan.phase3.RowShapeBuilder.RowShape
import molecule.db.common.transaction.plan.phase3.MappingBuilder.{ColBinding, PathStep}
import molecule.db.common.transaction.plan.phase4.binders.*

object Binders {
  type Binder = (PlanNode, Int, Int) => (PreparedStatement, Int) => Unit

  def build(
    plan: LogicalPlan,
    model: DataModel,
    tpls: Seq[Product],
    shape: RowShape
  ): Vector[Binder] = {

    val nodes = plan.nodes

    // Precompute for each (ent, attr) all matching (nodeIdx, localIdx) where localIdx points to a non-FK column
    val indexByEntAttr: Map[(String, String), Vector[(Int, Int)]] = {
      val buf = scala.collection.mutable.Map.empty[(String, String), scala.collection.mutable.ArrayBuffer[(Int, Int)]]
      nodes.zipWithIndex.foreach { case (node, nodeIdx) =>
        node.attrs.zipWithIndex.foreach { case (ac, localIdx) =>
          if (!ac.isFk) {
            val key = (node.ent, ac.attr)
            val vs  = buf.getOrElseUpdate(key, scala.collection.mutable.ArrayBuffer.empty[(Int, Int)])
            vs += ((nodeIdx, localIdx))
          }
        }
      }
      buf.view.mapValues(_.toVector).toMap
    }

    // Usage counter per (ent, attr) so the k-th occurrence maps to the k-th matching node
    val usedCount = scala.collection.mutable.Map.empty[(String, String), Int].withDefaultValue(0)

    // Accumulate per-node ColBindings keyed by colIdx
    val colsByNode: Map[Int, scala.collection.mutable.Map[Int, ColBinding]] =
      nodes.indices.map(_ -> scala.collection.mutable.HashMap.empty[Int, ColBinding]).toMap

    // Helper: emit a binding for an attribute
    def emitAttr(a: Attr, tplIdx: Int, path: Vector[PathStep]): Unit = {
      val key = (a.ent, a.attr)
      val vec = indexByEntAttr.getOrElse(key, Vector.empty)
      val ix  = usedCount(key)
      if (ix >= vec.length) {
        throw new IllegalStateException(s"Couldn't map attribute ${a.ent}.${a.attr} to planned nodes.")
      }
      val (nodeIdx, localIdx) = vec(ix)
      usedCount.update(key, ix + 1)
      val nodeCols = colsByNode(nodeIdx)
      nodeCols.update(localIdx, ColBinding(colIdx = localIdx, tplIdx = tplIdx, attr = a, path = path))
    }

    // Single recursive traversal of the DataModel AST using tplIndex parameter.
    // - Increment tplIndex for each attribute at the current level.
    // - Reset tplIndex to 0 when entering Nested/OptNested/Ref/OptRef.
    // - BackRef handling (derive from previous path) can be added when needed.
    def recurse(elems: List[Element], pathPrefix: Vector[PathStep], tplIndex: Int): Int = {
      var curTplIdx = tplIndex

      def onAttr(a: Attr): Unit = {
        val path = pathPrefix :+ PathStep.TuplePos(curTplIdx)
        emitAttr(a, curTplIdx, path)
        curTplIdx += 1
      }

      def onNested(childElems: List[Element]): Unit = {
        val childPrefix = pathPrefix :+ PathStep.TuplePos(curTplIdx) :+ PathStep.SeqIndexFromShape
        recurse(childElems, childPrefix, 0)
        curTplIdx += 1
      }

      def onOptNested(childElems: List[Element]): Unit = {
        val childPrefix = pathPrefix :+ PathStep.TuplePos(curTplIdx) :+ PathStep.SeqIndexFromShape
        recurse(childElems, childPrefix, 0)
        curTplIdx += 1
      }

      def onOptRef(childElems: List[Element]): Unit = {
        recurse(childElems, pathPrefix, 0)
      }

      def onRef(): Unit = {
        // Structural marker; tpl index resets when we recurse into ref scopes
        ()
      }

      def onBackRef(): Unit = {
        // Placeholder: compute tplIndex from previous path if needed
        ()
      }

      elems.foreach {
        case a: Attr               => onAttr(a)
        case Nested(_, es)         => onNested(es)
        case OptNested(_, es)      => onOptNested(es)
        case OptRef(_, es)         => onOptRef(es)
        case _: Ref                => onRef()
        case _: BackRef            => onBackRef()
        case OptEntity(attrs)      => attrs.foreach(onAttr)
        case _                     => ()
      }

      curTplIdx
    }

    // Build all per-node column bindings from the model
    recurse(model.elements, Vector.empty[PathStep], 0)

    // Build a binder per node; each binder handles only its own node's columns
    nodes.zipWithIndex.map { case (planNode, nodeIdx) =>
      val colsByIdx: Map[Int, ColBinding] = colsByNode(nodeIdx).toMap

      (node: PlanNode, colIdx: Int, localRowIdx: Int) => {
        if (node.id.id != nodeIdx) {
          null
        } else {
          colsByIdx.get(colIdx) match {
            case Some(colBinding) =>
              val attr     = colBinding.attr
              val path     = colBinding.path
              val topIdx   = shape.topIdxByNode(node.id.id)(localRowIdx)
              val innerIdx = shape.innerIdxByNode(node.id.id)(localRowIdx)

              // Deterministic extractor driven by DataModel-derived path
              def extractWithPath(row: Product, steps: Vector[PathStep]): Any = {
                steps.foldLeft(row.asInstanceOf[Any]) { (curr, step) =>
                  step match {
                    case PathStep.TuplePos(pos) =>
                      curr match {
                        case p: Product =>
                          val arity     = p.productArity
                          val safeIndex = if (arity == 0) 0 else math.max(0, math.min(pos, arity - 1))
                          p.productElement(safeIndex)
                        case s: Seq[_] =>
                          val safeIndex = math.max(0, math.min(pos, s.size - 1))
                          if (s.isEmpty) s else s(safeIndex)
                        case a: Array[_] =>
                          val safeIndex = math.max(0, math.min(pos, a.length - 1))
                          if (a.isEmpty) a else a(safeIndex)
                        case other => other
                      }
                    case PathStep.SeqIndexFromShape =>
                      curr match {
                        case s: Seq[_] =>
                          if (innerIdx >= 0 && innerIdx < s.size) s(innerIdx)
                          else if (innerIdx == 0 && s.nonEmpty) s.head
                          else curr
                        case a: Array[_] =>
                          if (innerIdx >= 0 && innerIdx < a.length) a(innerIdx)
                          else if (innerIdx == 0 && a.nonEmpty) a(0)
                          else curr
                        case other => other
                      }
                  }
                }
              }

              val topRow = tpls(topIdx)
              val raw: Any =
                if (path.nonEmpty) extractWithPath(topRow, path)
                else extractWithPath(topRow, Vector(PathStep.TuplePos(colBinding.tplIdx)))

              attr match {
                case oneMan: AttrOneMan => ScalarOneBinders.forOneMan(oneMan, raw)
                case oneOpt: AttrOneOpt => ScalarOneBinders.forOneOpt(oneOpt, raw)

                case setMan: AttrSetMan => SetBinders.forSetMan(setMan, raw)
                case setOpt: AttrSetOpt => SetBinders.forSetOpt(setOpt, raw)

                case seqMan: AttrSeqMan => SeqBinders.forSeqMan(seqMan, raw)
                case seqOpt: AttrSeqOpt => SeqBinders.forSeqOpt(seqOpt, raw)

                case mapMan: AttrMapMan => MapBinders.forMapMan(mapMan, raw)
                case mapOpt: AttrMapOpt => MapBinders.forMapOpt(mapOpt, raw)

                case other =>
                  throw new IllegalStateException(
                    s"Unsupported attribute type in PlannedInsert binder: ${other.getClass.getSimpleName}"
                  )
              }
            case None =>
              // Either FK column or not part of this tuple – executor will handle FKs
              null
          }
        }
      }
    }.toVector
  }
}
