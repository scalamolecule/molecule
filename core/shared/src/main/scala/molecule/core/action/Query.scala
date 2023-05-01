package molecule.core.action

import java.util.Date
import molecule.boilerplate.ast.Model.Element
import molecule.core.api.TxReport
import molecule.core.marshalling.dbView._

case class Query[Tpl](
  elements0: List[Element],
  limit: Option[Int] = None,
  dbView: Option[DbView] = None
) extends Action(elements0) {

  // Common api

  def limit(l: Int): Query[Tpl] = copy(limit = Some(l))
  def offset(o: Int): QueryOffset[Tpl] = QueryOffset(elements, limit, o)
  def from(cursor: String): QueryCursor[Tpl] = QueryCursor(elements, limit, cursor)


  // Time actions (might be available only for Datomic)

  def asOf(d: Date): Query[Tpl] = copy(dbView = Some(AsOf(TxDate(d))))
  def asOf(t: Long): Query[Tpl] = copy(dbView = Some(AsOf(TxLong(t))))
  def asOf(txReport: TxReport): Query[Tpl] = copy(dbView = Some(AsOf(TxLong(txReport.tx))))

  def since(d: Date): Query[Tpl] = copy(dbView = Some(Since(TxDate(d))))
  def since(t: Long): Query[Tpl] = copy(dbView = Some(Since(TxLong(t))))
  def since(txReport: TxReport): Query[Tpl] = copy(dbView = Some(Since(TxLong(txReport.tx))))
}
