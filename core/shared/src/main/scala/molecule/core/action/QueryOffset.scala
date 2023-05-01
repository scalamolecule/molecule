package molecule.core.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.marshalling.dbView.DbView

case class QueryOffset[Tpl](
  elements0: List[Element],
  limit: Option[Int],
  offset: Int,
  dbView: Option[DbView] = None
) extends Action(elements0) {

  def limit(l: Int): QueryOffset[Tpl] = copy(limit = Some(l))
}
