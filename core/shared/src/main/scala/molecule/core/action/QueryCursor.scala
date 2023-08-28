package molecule.core.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.marshalling.dbView.DbView

case class QueryCursor[Tpl](
  private[molecule] val elements: List[Element],
  private[molecule] val limit: Option[Int],
  private[molecule] val cursor: String,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  def limit(l: Int): QueryCursor[Tpl] = copy(limit = Some(l))

  // Inspect Query
  def i: QueryCursor[Tpl] = copy(doInspect = true)
}
