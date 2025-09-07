package molecule.db.common.transaction.plan

import java.sql.{PreparedStatement, Statement}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import molecule.db.common.transaction.strategy.SqlOps

// Executor: prepare -> fulfill -> flush (scaffold)

final class Executor(sqlOps: SqlOps) {

  private final case class Row(colSetters: mutable.ArrayBuffer[PreparedStatement => Unit])
  private final case class NodeState(
    node: PlanNode,
    cols: Vector[String],
    casts: Vector[String],
    rows: mutable.ArrayBuffer[Row],
    ids: mutable.ArrayBuffer[Long]
  )

  // Build node states with empty row buffers
  private def materializeNodes(plan: LogicalPlan): Vector[NodeState] = {
    plan.nodes.map { n =>
      val cols  = n.attrs.map(_.attr).toVector
      val casts = n.attrs.map(_.typeCast).toVector
      NodeState(n, cols, casts, mutable.ArrayBuffer.empty[Row], mutable.ArrayBuffer.empty[Long])
    }
  }

  // Allocate rows per node (supports nested O2M with variable counts)
  private def allocateRows(states: Vector[NodeState], nodeRowCounts: Vector[Int]): Unit = {
    states.indices.foreach { i =>
      val st   = states(i)
      val need = nodeRowCounts(i)
      while (st.rows.size < need)
        st.rows += Row(mutable.ArrayBuffer.empty)
    }
  }

  private def insertAll(st: NodeState): Unit = {
    if (st.cols.isEmpty) return
    val placeholders = st.casts.map(tc => "?" + (if (tc == null || tc.isEmpty) "" else tc))
    val ps = sqlOps.sqlConn.prepareStatement(
      sqlOps.insertStmt(
        st.node.ent,
        ListBuffer.from(st.cols),
        ListBuffer.from(placeholders)
      ),
      Statement.RETURN_GENERATED_KEYS
    )
    st.rows.foreach { row =>
      if (row.colSetters.nonEmpty) {
        row.colSetters.foreach(set => set(ps))
        ps.addBatch()
      } else {
        // Still add empty row to keep arities aligned with relationships
        ps.addBatch()
      }
    }
    val ids = sqlOps.getIds(ps, st.node.ent)
    st.ids.clear()
    st.ids ++= ids
  }

  // Execute planned insert in 3 phases: prepare -> resolve FKs (topological M2O, then O2M) -> flush.
  // Public entry point (binders will be supplied by the insert resolver in wiring step)
  def run(
    plan: LogicalPlan,
    nodeRowCounts: Vector[Int],
    topIdxByNode: Vector[Array[Int]],
    binders: Vector[(PlanNode, Int, Int) => (PreparedStatement, Int) => Unit] // (node, colIdx, localRowIdx) -> setter
  ): List[Long] = {
    val states = materializeNodes(plan)
    allocateRows(states, nodeRowCounts)

    // 1) Prepare: bind scalar values using provided binders (per node, per local row)
    binders.foreach { binder =>
      plan.nodes.foreach { node =>
        val st = states(node.id.id)
        node.attrs.indices.foreach { colIdx =>
          st.rows.indices.foreach { localRowIdx =>
            val setter = binder(node, colIdx, localRowIdx)
            if (setter != null) {
              val jdbcColIndex = colIdx + 1
              st.rows(localRowIdx).colSetters += ((ps: PreparedStatement) => setter(ps, jdbcColIndex))
            }
          }
        }
      }
    }

    // 2) Handle M2O dependencies with topological ordering (to -> from).
    // Ensures chained ManyToOne refs (A->B->C) insert in dependency order (C, then B, then A),
    // and inject FKs before inserting dependents.
    val (m2oEdges, o2mEdges) = plan.edges.partition(_.card == ManyToOne)

    // Build DAG for M2O: to -> from (from depends on to)
    val m2oNodes = mutable.Set.empty[Int]
    val indeg    = mutable.Map.empty[Int, Int].withDefaultValue(0)
    val outEdges = mutable.Map.empty[Int, List[EdgeM2O]].withDefaultValue(Nil)

    m2oEdges.foreach {
      case e @ EdgeM2O(from, to, _) =>
        m2oNodes += from.id; m2oNodes += to.id
        indeg.update(from.id, indeg(from.id) + 1)
        outEdges.update(to.id, e :: outEdges(to.id))
      case _ => ()
    }

    // Nodes that are many-side of O2M (defer their actual insert to the O2M phase)
    val o2mManySide = o2mEdges.collect { case EdgeO2M(_, to, _) => to.id }.toSet

    // Kahn's algorithm for topo order of M2O subgraph
    val queue = mutable.Queue.empty[Int]
    m2oNodes.foreach { n => if (indeg(n) == 0) queue.enqueue(n) }

    while (queue.nonEmpty) {
      val n  = queue.dequeue()
      val st = states(n)

      // Insert this node now unless it's deferred as O2M many-side
      if (!o2mManySide.contains(n) && st.ids.isEmpty) insertAll(st)

      // Inject this node's ids into M2O dependents (from-side FK columns)
      outEdges(n).foreach {
        case EdgeM2O(from, to, fkAttr) if to.id == n =>
          val fromSt = states(from.id)
          val toSt   = states(to.id)
          val fkIdx1 = fromSt.cols.indexOf(fkAttr) match {
            case -1 => throw new IllegalStateException(s"FK attr `$fkAttr` not found on ${fromSt.node.ent}")
            case i  => i + 1
          }
          // Map each from-row to a top tuple index and set fk accordingly
          val topIdxs = topIdxByNode(from.id)
          fromSt.rows.indices.foreach { localRowIdx =>
            val topIdx = topIdxs(localRowIdx)
            val idOpt  = if (topIdx >= 0 && topIdx < toSt.ids.length) Some(toSt.ids(topIdx)) else None
            idOpt match {
              case Some(id) => fromSt.rows(localRowIdx).colSetters += ((ps: PreparedStatement) => ps.setLong(fkIdx1, id))
              case None     => fromSt.rows(localRowIdx).colSetters += ((ps: PreparedStatement) => ps.setNull(fkIdx1, 0))
            }
          }

          // Decrease indegree and enqueue when satisfied
          indeg.update(from.id, indeg(from.id) - 1)
          if (indeg(from.id) == 0) queue.enqueue(from.id)

        case _ => ()
      }
    }

    // 3) Fulfill O2M reverse FKs by injecting one-side ids into many-side rows
    o2mEdges.foreach {
      case EdgeO2M(from, to, reverseFkAttr) =>
        val fromSt = states(from.id)
        val toSt   = states(to.id)

        // Ensure one-side has ids ready (skip if already inserted)
        if (fromSt.ids.isEmpty) insertAll(fromSt)

        val fkIdx1 = toSt.cols.indexOf(reverseFkAttr) match {
          case -1 => throw new IllegalStateException(s"Reverse FK attr `$reverseFkAttr` not found on ${toSt.node.ent}")
          case i  => i + 1
        }

        // Inject id corresponding to each many-side local row's top tuple index
        val toTopIdxs = topIdxByNode(to.id)
        toSt.rows.indices.foreach { localRowIdx =>
          val topIdx = toTopIdxs(localRowIdx)
          val idOpt  = if (topIdx >= 0 && topIdx < fromSt.ids.length) Some(fromSt.ids(topIdx)) else None
          idOpt match {
            case Some(id) => toSt.rows(localRowIdx).colSetters += ((ps: PreparedStatement) => ps.setLong(fkIdx1, id))
            case None     => toSt.rows(localRowIdx).colSetters += ((ps: PreparedStatement) => ps.setNull(fkIdx1, 0))
          }
        }

      case _ => ()
    }

    // 4) Insert any remaining nodes (those not yet inserted)
    plan.nodes.indices.foreach { i =>
      val st = states(i)
      if (st.ids.isEmpty) insertAll(st)
    }

    // Return ids of initial entity (node 0) as TxReport semantics
    states.headOption.map(_.ids.toList).getOrElse(Nil)
  }
}
