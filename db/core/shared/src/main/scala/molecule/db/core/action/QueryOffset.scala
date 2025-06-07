package molecule.db.core.action

import molecule.db.base.error.ModelError
import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.dbView.DbView

case class QueryOffset[Tpl](
  private[molecule] val dataModel: DataModel,
  private[molecule] val optLimit: Option[Int],
  private[molecule] val offset: Int,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val bindValues: List[Any] = Nil
) extends Action with QueryBind[Tpl, QueryOffset] {

  def limit(l: Int): QueryOffset[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryOffset[Tpl] = copy(printInspect = true)

  protected override def bind(inputs: List[Any]): QueryOffset[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.binds
    if found != expected then
      throw ModelError(s"Expected $expected bind parameters but got $found.")

    copy(bindValues = inputs)
  }
}
