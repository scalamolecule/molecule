package molecule.db.core.action

import java.util.Date
import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.dbView.*
import molecule.db.core.spi.TxReport

case class Query[Tpl](
  dataModel: DataModel,
  private[molecule] val optLimit: Option[Int] = None,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false
) extends Action {

  // Common api

  def limit(l: Int): Query[Tpl] = copy(optLimit = Some(l))
  def offset(o: Int): QueryOffset[Tpl] = QueryOffset(dataModel, optLimit, o, dbView, doInspect)
  def from(cursor: String): QueryCursor[Tpl] = QueryCursor(dataModel, optLimit, cursor, dbView, doInspect)


  // Time actions (might be available only for Datomic)

  def asOf(d: Date): Query[Tpl] = copy(dbView = Some(AsOf(TxDate(d))))
  def asOf(t: Long): Query[Tpl] = copy(dbView = Some(AsOf(TxLong(t))))
  def asOf(txReport: TxReport): Query[Tpl] = copy(dbView = Some(AsOf(TxLong(txReport.tx))))

  def since(d: Date): Query[Tpl] = copy(dbView = Some(Since(TxDate(d))))
  def since(t: Long): Query[Tpl] = copy(dbView = Some(Since(TxLong(t))))
  def since(txReport: TxReport): Query[Tpl] = copy(dbView = Some(Since(TxLong(txReport.tx))))

  // Inspect also
  def i: Query[Tpl] = copy(doInspect = true)



  def apply(input1: Int | String) = this
}
