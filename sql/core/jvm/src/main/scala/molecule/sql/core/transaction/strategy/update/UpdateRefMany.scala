package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefMany(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(parent, sqlConn, sqlOps, m2q, refNs) {

  override def rootAction: UpdateAction = parent.rootAction
  override def backRef: UpdateAction = parent

  override def execute(): Unit = {
//    val List(refId) = update
//
//    // Add many-to-many join once we have a parent id
//    val (id1, id2) = sqlOps.joinIdNames(ns, refNs)
//    addPostSetter((parentIds: List[Long]) => {
//      val joinStmt =
//        s"""INSERT INTO ${ns}_${refAttr}_$refNs (
//           |  $id1, $id2
//           |) VALUES (${parentIds.head}, $refId)""".stripMargin
//      sqlConn.prepareStatement(joinStmt).execute()
//    })
//
//    List(refId)

    ???
  }

  override def render(indent: Int): String = {
    // show join table after parent insert
    parent.postStmts += sqlOps.insertJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "UpdateRefMany")
  }
}
