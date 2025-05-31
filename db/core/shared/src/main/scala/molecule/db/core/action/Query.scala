package molecule.db.core.action

import java.util.Date
import molecule.db.base.error.ModelError
import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.dbView.*
import molecule.db.core.spi.TxReport

case class Query[Tpl](
  dataModel: DataModel,
  private[molecule] val optLimit: Option[Int] = None,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val bindValues: List[Any] = Nil
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


  private def bind(inputs: List[Any]): Query[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.elements.length // todo. input params instead
    if found != expected then
      throw ModelError(s"Expected $expected input parameters but got $found.")

    copy(bindValues = inputs)
  }
  def apply(a: Any): Query[Tpl] = bind(List(a))
  def apply(a: Any, b: Any): Query[Tpl] = bind(List(a, b))
  def apply(a: Any, b: Any, c: Any): Query[Tpl] = bind(List(a, b, c))
}
