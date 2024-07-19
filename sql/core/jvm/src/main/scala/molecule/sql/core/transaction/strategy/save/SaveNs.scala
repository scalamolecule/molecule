package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

// Initial save action
case class SaveNs(
  sqlConn: Connection,
  ns: String,
)(implicit sqlOps: SqlOps) extends SaveAction(sqlConn, sqlOps, ns) {

  // Start collecting rowSetters
  rowSetters += ListBuffer.empty[PS => Unit]

  override def initialAction: SaveAction = this

  override def execute: List[Long] = insert

  override def toString: String = recurseRender(0, "SaveNs")
}
