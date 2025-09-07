package molecule.db.common.transaction.plan.phase3

import molecule.core.dataModel.*
import molecule.db.common.transaction.plan.*

object MappingBuilder {

  // Path-aware step from the top-level input tuple down to a scalar value.
  // TuplePos: position within a Product at the current nesting level
  // SeqIndexFromShape: a dynamic index resolved from RowShape for the current node/row (nested sequences)
  sealed trait PathStep
  object PathStep {
    final case class TuplePos(pos: Int) extends PathStep
    case object SeqIndexFromShape extends PathStep
  }

  // Key that uniquely identifies a column binding within a planned node, including its extraction path
  // from the top-level tuple down to the scalar.
  final case class AttrKey(nodeId: Int, colIdx: Int, path: Vector[PathStep])

  // Column binding information grouped per planned node.
  final case class ColBinding(colIdx: Int, tplIdx: Int, attr: Attr, path: Vector[PathStep])

  // Node-level grouping of column bindings.
  final case class NodeBinding(nodeId: Int, ent: String, cols: Vector[ColBinding])

  // Recursive graph mirroring the recursive DataModel
  final case class MappingBranch(nodes: Vector[NodeBinding], children: Vector[MappingBranch])

  // Graph of path-aware bindings across all nodes in the plan.
  type MappingGraph = MappingBranch

  // Build a path-aware mapping graph from a recursive DataModel.
  // Each ColBinding carries:
  //  - tplIdx: index within the current level's product (top-level or nested)
  //  - colIdx: planned column index within the node (non-FK)
  //  - attr: the attribute metadata
  //  - path: deterministic extractor path from the top-level tuple to the scalar
  def buildGraph(plan: LogicalPlan, model: DataModel): MappingGraph = {
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

    // Helper: emit a binding for an attribute
    def emitAttr(a: Attr, tplIdx: Int, path: Vector[PathStep], colsByNode: scala.collection.mutable.Map[Int, scala.collection.mutable.ArrayBuffer[ColBinding]]): Unit = {
      val key = (a.ent, a.attr)
      val vec = indexByEntAttr.getOrElse(key, Vector.empty)
      val ix  = usedCount(key)
      if (ix >= vec.length) {
        throw new IllegalStateException(s"Couldn't map attribute ${a.ent}.${a.attr} to planned nodes.")
      }
      val (nodeIdx, localIdx) = vec(ix)
      usedCount.update(key, ix + 1)
      val buf = colsByNode.getOrElseUpdate(nodeIdx, scala.collection.mutable.ArrayBuffer.empty[ColBinding])
      buf += ColBinding(colIdx = localIdx, tplIdx = tplIdx, attr = a, path = path)
    }

    // Single recursive traversal for both top-level and nested elements.
    // tplIndex starts at 0 and increments by 1 for each attribute; when entering Nested/OptNested/OptRef,
    // recursion resets tplIndex to 0 for that inner level. BackRef handling (calculating from previous path)
    // can be added when needed.
    def buildBranch(elems: List[Element], pathPrefix: Vector[PathStep], tplIndex: Int): (Int, MappingBranch) = {
      var curTplIdx = tplIndex
      val colsByNode = scala.collection.mutable.Map.empty[Int, scala.collection.mutable.ArrayBuffer[ColBinding]]
      val childBranches = scala.collection.mutable.ArrayBuffer.empty[MappingBranch]

      def onAttr(a: Attr): Unit = {
        val path = pathPrefix :+ PathStep.TuplePos(curTplIdx)
        emitAttr(a, curTplIdx, path, colsByNode)
        curTplIdx += 1
      }
      def onNested(childElems: List[Element]): Unit = {
        val childPrefix = pathPrefix :+ PathStep.TuplePos(curTplIdx) :+ PathStep.SeqIndexFromShape
        val (_, child)  = buildBranch(childElems, childPrefix, 0)
        childBranches += child
        curTplIdx += 1
      }
      def onOptNested(childElems: List[Element]): Unit = {
        val childPrefix = pathPrefix :+ PathStep.TuplePos(curTplIdx) :+ PathStep.SeqIndexFromShape
        val (_, child)  = buildBranch(childElems, childPrefix, 0)
        childBranches += child
        curTplIdx += 1
      }
      def onOptRef(childElems: List[Element]): Unit = {
        // Optional references don't advance tpl index themselves; recurse into their elements with reset tplIndex
        val (_, child) = buildBranch(childElems, pathPrefix, 0)
        childBranches += child
      }
      def onBackRef(): Unit = {
        // Placeholder: BackRef could adjust indexing based on previous path if needed
        ()
      }

      elems.foreach {
        case a: Attr               => onAttr(a)
        case Nested(_, es)         => onNested(es)
        case OptNested(_, es)      => onOptNested(es)
        case OptRef(_, es)         => onOptRef(es)
        case _: BackRef            => onBackRef()
        case _: Ref                => () // structural marker; no direct values
        case OptEntity(attrs)      => attrs.foreach(onAttr)
        case _                     => ()
      }

      val nodesVec: Vector[NodeBinding] =
        nodes.zipWithIndex.flatMap { case (node, nodeIdx) =>
          colsByNode.get(nodeIdx).map { buf =>
            NodeBinding(nodeIdx, node.ent, buf.sortBy(_.colIdx).toVector)
          }
        }.toVector

      (curTplIdx, MappingBranch(nodes = nodesVec, children = childBranches.toVector))
    }

    buildBranch(model.elements, Vector.empty[PathStep], 0)._2
  }

  // Build a path-aware MappingGraph from a flat list of attributes (temporary adapter until full DataModel traversal).
  // Keeps tuple index assignment semantics identical to the previous implementation.
  def build(plan: LogicalPlan, attrElems: List[Attr]): MappingGraph = {
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

    // Accumulate ColBindings per planned node
    val colsByNode = scala.collection.mutable.Map.empty[Int, scala.collection.mutable.ArrayBuffer[ColBinding]]
    // Preserve attribute encounter order per tplIdx to determine tuple positions within nested products
    val orderByTplIdx = scala.collection.mutable.Map.empty[Int, scala.collection.mutable.ArrayBuffer[(Int, Int)]]

    var tplIdx = 0
    attrElems.foreach { a =>
      val key = (a.ent, a.attr)
      val vec = indexByEntAttr.getOrElse(key, Vector.empty)
      val ix  = usedCount(key)
      if (ix >= vec.length) {
        throw new IllegalStateException(s"Couldn't map attribute ${a.ent}.${a.attr} to planned nodes.")
      }
      val (nodeIdx, localIdx) = vec(ix)

      // Record encounter order for this tplIdx
      val seqForTpl = orderByTplIdx.getOrElseUpdate(tplIdx, scala.collection.mutable.ArrayBuffer.empty[(Int, Int)])
      seqForTpl += ((nodeIdx, localIdx))

      val buf = colsByNode.getOrElseUpdate(nodeIdx, scala.collection.mutable.ArrayBuffer.empty[ColBinding])
      // Path will be finalized after we know positions within each tplIdx product
      buf += ColBinding(
        colIdx = localIdx,
        tplIdx = tplIdx,
        attr = a,
        path = Vector.empty
      )
      usedCount.update(key, ix + 1)
      tplIdx += 1
    }

    // Build stable position lists per tplIdx in encounter order
    val keysByTplIdx: Map[Int, Vector[(Int, Int)]] =
      orderByTplIdx.view.mapValues(_.toVector).toMap

    // Finalize paths for each ColBinding:
    //   [TuplePos(tplIdx), SeqIndexFromShape, TuplePos(posWithinProductForTplIdx)]
    val nodesVec: Vector[NodeBinding] =
      nodes.zipWithIndex.flatMap { case (node, nodeIdx) =>
        colsByNode.get(nodeIdx).map { buf =>
          val finalizedCols = buf.toVector.map { cb =>
            val within = keysByTplIdx.get(cb.tplIdx).getOrElse(Vector.empty)
            val pos    = within.indexOf((nodeIdx, cb.colIdx)) match {
              case -1 => 0
              case p  => p
            }
            cb.copy(path = Vector(
              PathStep.TuplePos(cb.tplIdx),
              PathStep.SeqIndexFromShape,
              PathStep.TuplePos(pos)
            ))
          }.sortBy(_.colIdx).toVector
          NodeBinding(nodeIdx, node.ent, finalizedCols)
        }
      }.toVector

    MappingBranch(nodes = nodesVec, children = Vector.empty)
  }

  // Temporary adapter to the legacy flat mapping used by RowShapeBuilder.
  def flatten(graph: MappingGraph): Map[(Int, Int), (Int, Attr)] = {
    def loop(branch: MappingBranch): Vector[((Int, Int), (Int, Attr))] = {
      val here = branch.nodes.flatMap { nb =>
        nb.cols.map(cb => ((nb.nodeId, cb.colIdx) -> (cb.tplIdx, cb.attr)))
      }
      here ++ branch.children.flatMap(loop)
    }
    loop(graph).toMap
  }
}
