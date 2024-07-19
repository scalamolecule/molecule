package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRefMany(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(sqlConn, sqlOps, refNs) {

  override def initialAction: UpdateAction = parent.initialAction
  override def backRef: UpdateAction = parent

  override def execute: List[Long] = {
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
    parent.postStmts += sqlOps.getJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "UpdateRefMany")
  }
}
