package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateNs(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  action: String,
) extends UpdateAction(parent, sqlConn, sqlOps, m2q, ns) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: UpdateAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    update()
  }

  override def curStmt: String = {
    if (cols.isEmpty) "..." else {
      val filterClauses = if (filters.isEmpty) Nil else
        m2q(filters).getWhereClauses
      sqlOps.updateStmt(ns, cols, clauses ++ filterClauses)
    }
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
