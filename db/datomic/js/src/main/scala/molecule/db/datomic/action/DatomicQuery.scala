package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Query

case class DatomicQuery[Tpl](
  elements: List[Element],
  private val limit: Int = 0
) extends Query[Tpl] {

  override def limit(l: Int): DatomicQuery[Tpl] = copy(limit = l)
  override def offset(o: Int): DatomicQueryOffset[Tpl] = DatomicQueryOffset(elements, limit, o)
  override def from(cursor: String): DatomicQueryCursor[Tpl] = DatomicQueryCursor(elements, limit, cursor)
}
