package molecule.core.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.marshalling.dbView.DbView

case class QueryCursor[Tpl](
  elements0: List[Element],
  limit: Option[Int],
  cursor: String,
  dbView: Option[DbView] = None
) extends Action(elements0) {

  def limit(l: Int): QueryCursor[Tpl] = copy(limit = Some(l))
}
