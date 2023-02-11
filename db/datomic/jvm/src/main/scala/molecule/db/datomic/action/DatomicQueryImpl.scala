package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Query

case class DatomicQueryImpl[Tpl](
  elements: List[Element],
  limit: Option[Int] = None
) extends Query[Tpl] {

  // Universal api

  override def limit(l: Int): DatomicQueryImpl[Tpl] = copy(limit = Some(l))
  override def offset(offset: Int): DatomicQueryImplOffset[Tpl] = DatomicQueryImplOffset(elements, limit, offset)
  override def from(cursor: String): DatomicQueryImplCursor[Tpl] = DatomicQueryImplCursor(elements, limit, cursor)

  // Datomic special features

  // Time
  override def asOf(n: Int): Query[Tpl] = ???
  override def history: Query[Tpl] = ???
}
