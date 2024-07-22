package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateQueryIds(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  refAttr: String,
  refNs: String,
  parentIds: Set[Long]
) extends UpdateAction(parent, sqlConn, sqlOps, m2q, refNs) {

  override def execute(): Unit = {
//    val ps     = prepare(curStmt)
//    val curNs  = parent.children.head
//    val nsId   = curNs.ids.head // Single card-one id
//    val refIds = refIds0.iterator
//    while (refIds.hasNext) {
//      ps.setLong(1, nsId)
//      ps.setLong(2, refIds.next())
//      ps.addBatch()
//    }
//    ps.executeBatch()
//    ps.close()


  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ns, refAttr, refNs)

//    m2q.
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "InsertRefIds")
  }
}
