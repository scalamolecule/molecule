package molecule.sql.core.transaction.op

import java.sql.Connection
import molecule.sql.core.transaction.strategy.TxStrategy

case class SaveRefMany(
  parent: TxStrategy,
  sqlConn: Connection,
  ns: String, refAttr: String, refNs: String
) extends HandleInsert(sqlConn, refNs) {

  override def execute: List[Long] = {
    val refId = insertOne

    // Save parent
    val List(parentId) = parent.execute

    // Add many-to-many join from parent to ref
    val (id1, id2) = if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
    val joinStmt   =
      s"""INSERT INTO ${ns}_${refAttr}_$refNs (
         |  $id1, $id2
         |) VALUES ($parentId, $refId)""".stripMargin
    val join       = sqlConn.prepareStatement(joinStmt)
    join.execute()

    // Parent id for TxReport
    List(parentId)
  }
}
