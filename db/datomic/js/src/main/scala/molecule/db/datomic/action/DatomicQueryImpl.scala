package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Query

case class DatomicQueryImpl[Tpl](
  elements: List[Element],
  private val limit: Int = 0
) extends Query[Tpl] {

  override def limit(l: Int): DatomicQueryImpl[Tpl] = copy(limit = l)
  override def offset(o: Int): DatomicQueryImplOffset[Tpl] = DatomicQueryImplOffset(elements, limit, o)
  override def from(cursor: String): DatomicQueryImplCursor[Tpl] = DatomicQueryImplCursor(elements, limit, cursor)
}
