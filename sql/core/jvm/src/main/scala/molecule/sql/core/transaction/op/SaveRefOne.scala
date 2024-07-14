package molecule.sql.core.transaction.op

import java.sql.Connection
import molecule.sql.core.transaction.strategy.TxStrategy

case class SaveRefOne(
  parent: TxStrategy,
  sqlConn: Connection,
  ns: String, refAttr: String, refNs: String
) extends HandleInsert(sqlConn, refNs) {

  override def execute: List[Long] = {
    val refId = insertOne

    // Add ref id to refAttr of parent namespace
    val refAttrIndex = parent.paramIndex
    parent.add(refAttr, (ps: PS) => ps.setLong(refAttrIndex, refId))
    parent.execute
  }
}
