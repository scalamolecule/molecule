package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryCursor
import molecule.core.marshalling.dbView.DbView

case class DatomicQueryCursor[Tpl](
  private val elements0: List[Element],
  limit: Option[Int],
  cursor: String,
  dbView: Option[DbView] = None
) extends QueryCursor[Tpl](elements0) {

  override def limit(l: Int): DatomicQueryCursor[Tpl] = copy(limit = Some(l))
}
