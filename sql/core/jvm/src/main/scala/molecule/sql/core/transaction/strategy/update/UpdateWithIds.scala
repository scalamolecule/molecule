package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.base.error.ModelError
import molecule.sql.core.transaction.strategy.SqlOps

// Initial update action with ids
case class UpdateWithIds(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  ids: List[Long]
) extends UpdateAction(sqlConn, sqlOps, ns) {

  // Set id clause
  clauses += (ids match {
    case Seq(id) => s"$ns.id = $id"
    case Nil     => throw ModelError("Missing ids to update")
    case ids     => s"$ns.id IN(${ids.mkString(", ")})"
  })

  override def initialAction: UpdateAction = this

  override def execute: List[Long] = {
    update(ids)
  }

  override def toString: String = recurseRender(0, "SaveNs")

  override def render(indent: Int): String = {
    recurseRender(indent, "UpdateWithIds")
  }
}
