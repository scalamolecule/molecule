package molecule.sql.core.transaction.op

import java.sql.Connection
import molecule.sql.core.transaction.strategy.TxStrategy

case class SaveRefMany(
  parent: TxStrategy,
  sqlConn: Connection,
  dbOps: DbOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends SaveBase(sqlConn, dbOps, refNs) {

  def fromTop: TxStrategy = parent.fromTop

  override def execute: List[Long] = {
    val List(refId) = insert

    // Add many-to-many join once we have a parent id
    val (id1, id2) = joinIdNames(ns, refNs)
    addPostSetter((parentIds: List[Long]) => {
      val joinStmt =
        s"""INSERT INTO ${ns}_${refAttr}_$refNs (
           |  $id1, $id2
           |) VALUES (${parentIds.head}, $refId)""".stripMargin
      sqlConn.prepareStatement(joinStmt).execute()
    })

    List(refId)
  }

  override def backRef: TxStrategy = parent

  override def toString: String = render(0)
  override def render(indent: Int): String = render(indent, "SaveRefMany")
}
