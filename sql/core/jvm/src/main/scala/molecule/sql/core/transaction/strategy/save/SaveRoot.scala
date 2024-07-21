package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

case class SaveRoot(
  sqlConn: Connection,
  ns: String,
)(implicit sqlOps: SqlOps) extends SaveAction(null, sqlConn, sqlOps, ns) {

  val saveNs: SaveNs = {
    val first = SaveNs(this, sqlConn, sqlOps, ns, "Ns")
    children += first
    first
  }

  override def rootAction: SaveAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Save")
}
