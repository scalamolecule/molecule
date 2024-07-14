package molecule.sql.core.transaction.op

import java.sql.Connection
import molecule.sql.core.transaction.strategy.TxStrategy

case class SaveNs(
  sqlConn: Connection,
  ns: String
) extends HandleInsert(sqlConn, ns) {

  override def execute: List[Long] = {
    List(insertOne)
  }

  override def refOne(ns: String, refAttr: String, refNs: String): TxStrategy = {
    SaveRefOne(this, sqlConn, ns, refAttr, refNs)
  }
  override def refMany(ns: String, refAttr: String, refNs: String): TxStrategy = {
    SaveRefMany(this, sqlConn, ns, refAttr, refNs)
  }
}
