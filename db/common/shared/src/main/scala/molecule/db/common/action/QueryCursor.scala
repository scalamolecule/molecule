package molecule.db.common.action

import molecule.base.error.ModelError
import molecule.core.dataModel.{DataModel, Value}

case class QueryCursor[Tpl](
  private[molecule] val dataModel: DataModel,
  private[molecule] val optLimit: Option[Int],
  private[molecule] val cursor: String,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val bindValues: List[Value] = Nil
) extends Action with QueryBind_[Tpl, QueryCursor] {

  def limit(l: Int): QueryCursor[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryCursor[Tpl] = copy(printInspect = true)

  protected override def bind(inputs: List[Value]): QueryCursor[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.binds
    if found != expected then
      throw ModelError(s"Expected $expected bind parameters but got $found.")

    copy(bindValues = inputs)
  }
}
