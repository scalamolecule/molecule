package molecule.db.core.action

import molecule.db.core.ast.Element
import molecule.db.core.marshalling.dbView.DbView

case class QueryCursor[Tpl](
  private[molecule] val elements: List[Element],
  private[molecule] val optLimit: Option[Int],
  private[molecule] val cursor: String,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  def limit(l: Int): QueryCursor[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryCursor[Tpl] = copy(doInspect = true)
}
