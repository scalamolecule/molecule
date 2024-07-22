package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefOne(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int
) extends UpdateAction(parent, sqlConn, sqlOps, m2q, refNs) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: UpdateAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    update()

//    parent.rowSetters.last += {
//      (ps: PS) => ps.setLong(refAttrIndex, ids.head)
//    }
  }


  override def curStmt: String = {
    if (cols.isEmpty) "..." else {
      val filterClauses = if (filters.isEmpty) Nil else
        m2q(filters).getWhereClauses
      sqlOps.updateStmt(ns, cols, clauses ++ filterClauses)
    }
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.paramIndex(refAttr)
    recurseRender(indent, "UpdateRefOne")
  }
}
