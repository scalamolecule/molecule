package molecule.db.core.action

import molecule.db.base.error.ModelError
import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.dbView.DbView

case class QueryCursor[Tpl](
  private[molecule] val dataModel: DataModel,
  private[molecule] val optLimit: Option[Int],
  private[molecule] val cursor: String,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val bindValues: List[Any] = Nil
) extends Action with QueryBind[Tpl, QueryCursor] {

  def limit(l: Int): QueryCursor[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryCursor[Tpl] = copy(doInspect = true)


  protected override def bind(inputs: List[Any]): QueryCursor[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.binds
    if found != expected then
      throw ModelError(s"Expected $expected bind parameters but got $found.")

    copy(bindValues = inputs)
  }
}
