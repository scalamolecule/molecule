package molecule.db.common.crud

import molecule.core.dataModel.*
import molecule.core.error.ModelError

case class Query[Tpl](
  dataModel: DataModel,
  private[molecule] val optLimit: Option[Int] = None,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val bindValues: List[Value] = Nil
) extends QueryBind_[Tpl, Query] {

  // Common api

  def limit(l: Int): Query[Tpl] = if (l < 1)
    throw ModelError("Limit must be positive.")
  else
    copy(optLimit = Some(l))

  def offset(o: Int): QueryOffset[Tpl] = if (o < 1)
    throw ModelError("Offset must be positive.")
  else
    QueryOffset(dataModel, optLimit, o, printInspect, bindValues)

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
