package molecule.graphql.client.action

import molecule.base.error.ModelError
import molecule.core.ast.DataModel

case class Query[Tpl](
  dataModel: DataModel,
  private[molecule] val optLimit: Option[Int] = None,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val bindValues: List[Any] = Nil
) extends QueryBind[Tpl, Query]{




  protected override def bind(inputs: List[Any]): Query[Tpl] = {
    val found    = inputs.length
    val expected = dataModel.binds
    if found != expected then
      throw ModelError(s"Expected $expected bind parameters but got $found.")

    copy(bindValues = inputs)
  }
}
