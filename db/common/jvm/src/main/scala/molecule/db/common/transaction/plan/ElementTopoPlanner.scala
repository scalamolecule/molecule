package molecule.db.common.transaction.plan

import scala.collection.mutable

/**
 * Element-level topological planner.
 *
 * This helper avoids introducing any alternative AST. It treats each entity occurrence purely as an Element `E`,
 * and models only the foreign-key dependencies as simple indices with attribute names. The result lets callers
 * construct a new DataModel (Elements only) in correct insertion order by adding the listed FK attributes to
 * each dependent entity occurrence.
 *
 * Usage outline (domain code stays Element-only):
 * 1) Identify each entity occurrence in your DataModel as an Element `E` and collect them in original order.
 * 2) From the same Elements, extract FK dependencies:
 *    - For forward ref (A -> B where A has FK to B): child = A, parent = B, fkAttr = A's FK column name.
 *    - For reverse ref (B <- A where A has FK to B): child = A, parent = B, fkAttr = A's FK column name.
 * 3) Call topoWithFkDeps(entities, deps) to get ordered occurrences and, for each, the prerequisite entities + FK names.
 * 4) Build a new Element DataModel by iterating in returned order and injecting the FK attribute Elements produced
 *    from earlier inserts.
 *
 * Note on BackRefs:
 * - As long as BackRefs are only used to start a new branch from a previous entity (not to continue adding attributes
 *   to that entity), the dependency graph remains acyclic and this topo approach is sufficient.
 */
object ElementTopoPlanner {

  /** Simple FK dependency: child occurrence depends on parent occurrence via child's FK attribute name. */
  final case class FkDep(childIdx: Int, parentIdx: Int, fkAttr: String)

  sealed trait ElemPlanError extends Product with Serializable
  final case class CycleDetected(remainingOccIndices: Vector[Int]) extends ElemPlanError

  /**
   * Compute topological order for entity occurrences and list which FK attrs must be injected
   * for each occurrence based on earlier inserts.
   *
   * @param entities Vector of entity occurrences (Elements), in their original order.
   * @param deps     Vector of FK dependencies (childIdx depends on parentIdx via fkAttr).
   * @tparam E       Element type.
   * @return Either a cycle error or a Vector where each item is (entity, Vector of (parentIdx, fkAttr)).
   */
  def topoWithFkDeps[E](
    entities: Vector[E],
    deps: Vector[FkDep]
  ): Either[ElemPlanError, Vector[(E, Vector[(Int, String)])]] = {
    val n = entities.length
    val inDegree = Array.fill(n)(0)
    val adj = Array.fill(n)(List.empty[(Int, String)]) // parent -> List(childIdx, fkAttr)

    // Build parent -> child adjacency and in-degrees
    deps.foreach { case FkDep(child, parent, fk) =>
      require(parent >= 0 && parent < n, s"parentIdx $parent out of bounds 0..${n - 1}")
      require(child >= 0 && child < n, s"childIdx $child out of bounds 0..${n - 1}")
      adj(parent) = (child -> fk) :: adj(parent)
      inDegree(child) += 1
    }

    // Deterministic zero-in-degree queue seeded by original order
    val queue = mutable.ArrayDeque[Int]()
    (0 until n).foreach { i => if (inDegree(i) == 0) queue.append(i) }

    val orderedIdx = Vector.newBuilder[Int]
    // Kahn's algorithm
    while (queue.nonEmpty) {
      // Maintain deterministic order by processing current slice in original index order
      val slice = queue.toVector.sorted
      queue.clear()
      slice.foreach { u =>
        orderedIdx += u
        adj(u).foreach { case (v, _) =>
          inDegree(v) -= 1
          if (inDegree(v) == 0) {
            queue.append(v)
          }
        }
      }
    }

    val order = orderedIdx.result()
    if (order.length != n) {
      // Remaining nodes form one or more cycles
      val inOrder = order.toSet
      val remaining = (0 until n).filterNot(inOrder).toVector
      Left(CycleDetected(remaining))
    } else {
      // For each child occurrence, gather its parent dependencies (parentIdx, fkAttr)
      val depsByChild: Map[Int, Vector[(Int, String)]] =
        deps.groupMap(_.childIdx)(d => (d.parentIdx, d.fkAttr)).view.mapValues(_.toVector).toMap

      val result =
        order.map { idx =>
          val parents = depsByChild.getOrElse(idx, Vector.empty)
            // Stable sort parents by original index (deterministic FK injection order)
            .sortBy(_._1)
          (entities(idx), parents)
        }.toVector

      Right(result)
    }
  }
}
