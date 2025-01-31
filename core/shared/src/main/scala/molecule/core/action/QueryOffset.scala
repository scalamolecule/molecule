package molecule.core.action

import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.dbView.DbView

case class QueryOffset[Tpl](
  private[molecule] val elements: List[Element],
  private[molecule] val optLimit: Option[Int],
  private[molecule] val offset: Int,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  def limit(l: Int): QueryOffset[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryOffset[Tpl] = copy(doInspect = true)
}
