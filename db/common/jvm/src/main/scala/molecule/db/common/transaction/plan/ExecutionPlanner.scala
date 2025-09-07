//package molecule.db.common.transaction.plan
//
//import scala.collection.mutable
//
///**
// * Computes insertion order for a LogicalPlan by honoring simple foreign key dependencies only.
// *
// * Semantics:
// * - Many-to-one (EdgeM2O(from = child, to = parent, ...)) means the child depends on the parent.
// *   For insertion, parent must be inserted before child, so we add edge parent -> child in the prerequisite graph.
// * - One-to-many (EdgeO2M(from = parent, to = child, ...)) is the reverse navigation of the same FK,
// *   and likewise means parent -> child in the prerequisite graph.
// *
// * The resulting order inserts all prerequisites before their dependents.
// * Cycles are reported as an error since pure reordering can't satisfy mutual dependencies.
// */
//object ExecutionPlanner {
//
//  sealed trait PlanError extends Product with Serializable
//  final case class CycleDetected(cyclicNodes: Vector[PlanNode]) extends PlanError
//
//  /**
//   * Returns plan nodes in the correct insertion order (prerequisites first).
//   * If a cycle exists, returns Left(CycleDetected(...)).
//   */
//  def insertionOrder(plan: LogicalPlan): Either[PlanError, Vector[PlanNode]] = {
//    val nodeById: Map[NodeId, PlanNode] = plan.nodes.map(n => n.id -> n).toMap
//    val orderIndex: Map[NodeId, Int] = plan.nodes.zipWithIndex.map { case (n, i) => n.id -> i }.toMap
//
//    // Build adjacency list: prerequisite -> dependents
//    val adj: Map[NodeId, List[NodeId]] = buildPrereqGraph(plan)
//
//    // Initialize in-degree counts for Kahn's algorithm
//    val inDegree = mutable.Map.empty[NodeId, Int]
//    plan.nodes.foreach(n => inDegree.update(n.id, 0))
//    adj.foreach { case (_, outs) =>
//      outs.foreach { v =>
//        inDegree.update(v, inDegree(v) + 1)
//      }
//    }
//
//    // Deterministic queue of zero in-degree nodes (by original plan.nodes order)
//    val zero = mutable.ArrayBuffer.from(inDegree.iterator.collect { case (id, deg) if deg == 0 => id })
//    zero.sortInPlaceBy(orderIndex)
//    val queue = mutable.ArrayDeque[NodeId](zero*)
//    val resultIds = Vector.newBuilder[NodeId]
//
//    while (queue.nonEmpty) {
//      val u = queue.removeHead()
//      resultIds += u
//      adj.getOrElse(u, Nil).foreach { v =>
//        val d = inDegree(v) - 1
//        inDegree.update(v, d)
//        if (d == 0) {
//          queue.append(v)
//        }
//      }
//      // Keep deterministic processing order among newly freed nodes
//      val tmp = queue.toArray
//      scala.util.Sorting.stableSort(tmp, (a: NodeId, b: NodeId) => orderIndex(a) < orderIndex(b))
//      queue.clear()
//      queue.appendAll(tmp)
//    }
//
//    val resIds = resultIds.result()
//    if (resIds.length != plan.nodes.length) {
//      // Remaining nodes are part of one or more cycles
//      val resolved = resIds.toSet
//      val cyclic = plan.nodes.filter(n => !resolved.contains(n.id)).toVector
//      Left(CycleDetected(cyclic))
//    } else {
//      Right(resIds.flatMap(nodeById.get))
//    }
//  }
//
//  /**
//   * Returns a tupled sequence where each element is:
//   *   (node, Vector of (prerequisiteNodeId, foreignKeyAttrName))
//   *
//   * This is intended to be consumed by an Element-only rewriter:
//   * - Iterate in the returned order.
//   * - For each node, if the dependency vector is non-empty, add those FK attributes
//   *   to the current Element (binding them with IDs produced by earlier inserts).
//   */
//  def insertionOrderWithDeps(
//    plan: LogicalPlan
//  ): Either[PlanError, Vector[(PlanNode, Vector[(NodeId, String)])]] = {
//    insertionOrder(plan).map { orderedNodes =>
//      val orderIndex: Map[NodeId, Int] = plan.nodes.zipWithIndex.map { case (n, i) => n.id -> i }.toMap
//
//      // Collect FK requirements for each dependent node (normalized)
//      val fkDeps = mutable.Map.empty[NodeId, Vector[(NodeId, String)]].withDefaultValue(Vector.empty)
//
//      plan.edges.foreach {
//        case EdgeM2O(child, parent, fkAttr) =>
//          // Child depends on parent; child's FK column name is fkAttr
//          fkDeps.update(child, fkDeps(child) :+ (parent -> fkAttr))
//
//        case EdgeO2M(parent, child, reverseFkAttr) =>
//          // Child depends on parent; child's FK column name is reverseFkAttr
//          fkDeps.update(child, fkDeps(child) :+ (parent -> reverseFkAttr))
//      }
//
//      // Deterministic ordering of FK deps per node by original node order of the prerequisites
//      val ordered = orderedNodes.map { node =>
//        val deps = fkDeps(node.id)
//          .sortBy { case (prereqId, _) => orderIndex.getOrElse(prereqId, Int.MaxValue) }
//        node -> deps
//      }
//      ordered
//    }
//  }
//
//  // Build graph with edges in prerequisite -> dependent direction for simple FK relationships.
//  private def buildPrereqGraph(plan: LogicalPlan): Map[NodeId, List[NodeId]] = {
//    val adj = mutable.Map.empty[NodeId, List[NodeId]]
//
//    // Ensure all nodes are present
//    plan.nodes.foreach(n => if (!adj.contains(n.id)) adj.update(n.id, Nil))
//
//    // Normalize edges to prerequisite -> dependent
//    plan.edges.foreach {
//      case EdgeM2O(from, to, _) =>
//        // from = dependent (child), to = prerequisite (parent)
//        adj.update(to, from :: adj.getOrElse(to, Nil))
//        if (!adj.contains(from)) adj.update(from, Nil)
//
//      case EdgeO2M(from, to, _) =>
//        // from = prerequisite (parent), to = dependent (child)
//        adj.update(from, to :: adj.getOrElse(from, Nil))
//        if (!adj.contains(to)) adj.update(to, Nil)
//    }
//
//    adj.toMap
//  }
//}
