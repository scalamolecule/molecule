package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

// Initial update action
case class UpdateNs(
  parent: UpdateAction,
  sqlConn: Connection,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
)(implicit sqlOps: SqlOps) extends UpdateAction(parent, sqlConn, sqlOps, m2q, ns) {

  // Start collecting rowSetters
  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: UpdateAction = this

  override def executeRoot: List[Long] = update

  override def toString: String = recurseRender(0, "UpdateWithIds")
}
