package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

// Initial update action
case class UpdateNs(
  sqlConn: Connection,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
)(implicit sqlOps: SqlOps) extends UpdateAction(sqlConn, sqlOps, m2q, ns) {

  // Start collecting rowSetters
  rowSetters += ListBuffer.empty[PS => Unit]

  override def initialAction: UpdateAction = this

  override def execute: List[Long] = update

  override def toString: String = recurseRender(0, "UpdateWithIds")
}
