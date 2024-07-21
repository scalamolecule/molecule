package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRoot(
  sqlConn: Connection,
  ns: String,
)(implicit sqlOps: SqlOps) extends InsertAction(null, sqlConn, sqlOps, ns) {

  val insertNs: InsertNs = {
    val first = InsertNs(this, sqlConn, sqlOps, ns, "Ns")
    children += first
    first
  }

  override def rootAction: InsertAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Insert")
}
