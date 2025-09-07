package molecule.db.common.transaction.plan.phase3

import molecule.core.dataModel.Attr
import molecule.db.common.transaction.plan.*

object RowShapeBuilder {
  final case class RowShape(
    nodeRowCounts: Vector[Int],
    topIdxByNode: Vector[Array[Int]],
    innerIdxByNode: Vector[Array[Int]] // -1 for one-side (no inner index)
  )

  def build(
    plan: LogicalPlan,
    mapping: Map[(Int, Int), (Int, Attr)],
    tpls: Seq[Product]
  ): RowShape = {
    val nodeCount      = plan.nodes.length
    val rowCounts      = Array.fill(nodeCount)(0)
    val topIdxByNode   = Array.fill(nodeCount)(Array.emptyIntArray)
    val innerIdxByNode = Array.fill(nodeCount)(Array.emptyIntArray)

    // Helper: find any mapped tplIdx for a given node (first non-FK column)
    val tplIdxByNode: Map[Int, Int] = mapping.collect {
      case ((nodeIdx, _), (tplIdx, _)) => nodeIdx -> tplIdx
    }.groupBy(_._1).view.mapValues(_.head._2).toMap

    // Identify O2M many-side nodes
    val manySide: Set[Int] = plan.edges.collect { case EdgeO2M(_, to, _) => to.id }.toSet

    // For each node, build per-top-tuple counts, then flatten
    (0 until nodeCount).foreach { n =>
      if (manySide.contains(n)) {
        val tplIdx = tplIdxByNode.getOrElse(n,
          throw new IllegalStateException(s"No attribute mapping found for many-side node ${plan.nodes(n).ent}")
        )
        val perTopCounts = tpls.indices.map { topIdx =>
          tpls(topIdx).productElement(tplIdx) match {
            case s: Seq[_]   => s.size
            case a: Array[_] => a.length
            case _           => 1 // Treat scalar as a single nested value
          }
        }
        val total = perTopCounts.sum
        rowCounts(n) = total
        val tops   = new Array[Int](total)
        val inners = new Array[Int](total)
        var cur    = 0
        tpls.indices.foreach { topIdx =>
          val cnt = perTopCounts(topIdx)
          var i   = 0
          while (i < cnt) {
            tops(cur) = topIdx
            inners(cur) = i
            cur += 1
            i += 1
          }
        }
        topIdxByNode(n)   = tops
        innerIdxByNode(n) = inners
      } else {
        // One-side or standalone node: one row per top tuple
        rowCounts(n) = tpls.length
        val tops = new Array[Int](tpls.length)
        var i    = 0
        while (i < tpls.length) { tops(i) = i; i += 1 }
        topIdxByNode(n)   = tops
        innerIdxByNode(n) = Array.fill(tpls.length)(-1)
      }
    }

    RowShape(rowCounts.toVector, topIdxByNode.toVector, innerIdxByNode.toVector)
  }
}
