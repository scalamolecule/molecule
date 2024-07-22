package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefJoin(
  parent: UpdateAction,
  ref: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(parent, sqlConn, sqlOps, m2q, refNs) {

  override def execute(): Unit = {
    val ps = prepare(curStmt)
    ps.setLong(1, parent.ids.last)
    ps.setLong(2, ref.ids.last)
    ps.addBatch()
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ns, refAttr, refNs)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefJoin")
  }
}
