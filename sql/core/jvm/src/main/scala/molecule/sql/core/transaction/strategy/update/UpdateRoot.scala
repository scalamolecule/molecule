package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRoot(
  sqlConn: Connection,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
)(implicit sqlOps: SqlOps) extends UpdateAction(null, sqlConn, sqlOps, m2q, ns) {

  val updateNs: UpdateNs = {
    val first = UpdateNs(this, sqlConn, sqlOps, m2q, ns, "Ns")
    children += first
    first
  }

  override def rootAction: UpdateAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Update")
}
