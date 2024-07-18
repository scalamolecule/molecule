package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRefMany(
  parent: SaveAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends SaveAction(sqlConn, sqlOps, refNs) {

  override def initialAction: SaveAction = parent.initialAction
  override def backRef: SaveAction = parent

  override def execute: List[Long] = {
    val List(refId) = insert

    // Add many-to-many join once we have a parent id
    val (id1, id2) = sqlOps.joinIdNames(ns, refNs)
    addPostSetter((parentIds: List[Long]) => {
      val joinStmt =
        s"""INSERT INTO ${ns}_${refAttr}_$refNs (
           |  $id1, $id2
           |) VALUES (${parentIds.head}, $refId)""".stripMargin
      sqlConn.prepareStatement(joinStmt).execute()
    })

    List(refId)
  }

  override def render(indent: Int): String = {
    // show join table after parent insert
    parent.postStmts += sqlOps.getJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "SaveRefMany")
  }
}
