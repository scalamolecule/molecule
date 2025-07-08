package molecule.db.common.action

import molecule.base.error.ModelError
import molecule.core.dataModel.*

case class Query[Tpl](
  dataModel: DataModel,
  private[molecule] val optLimit: Option[Int] = None,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val bindValues: List[Value] = Nil
) extends Action with QueryBind_[Tpl, Query] {

  // Common api

  def limit(l: Int): Query[Tpl] = copy(optLimit = Some(l))
  def offset(o: Int): QueryOffset[Tpl] = QueryOffset(dataModel, optLimit, o, printInspect, bindValues)
  def from(cursor: String): QueryCursor[Tpl] = QueryCursor(dataModel, optLimit, cursor, printInspect, bindValues)

  // Inspect also
  def i: Query[Tpl] = copy(printInspect = true)

  protected override def bind(inputs: List[Value]): Query[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.binds
    if found != expected then
      throw ModelError(s"Expected $expected bind parameters but got $found.")

    copy(bindValues = inputs)
  }
}
