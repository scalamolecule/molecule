package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Query

case class DatomicQuery[Tpl](
  elements: List[Element],
  limit: Option[Int] = None
) extends Query[Tpl] {

  // Universal api

  override def limit(l: Int): DatomicQuery[Tpl] = copy(limit = Some(l))
  override def offset(offset: Int): DatomicQueryOffset[Tpl] = DatomicQueryOffset(elements, limit, offset)
  override def from(cursor: String): DatomicQueryCursor[Tpl] = DatomicQueryCursor(elements, limit, cursor)

  // Datomic special features

  // Time
  def asOf(n: Int): Query[Tpl] = ???
  def since(n: Int): Query[Tpl] = ???
  def widh(n: Int): Query[Tpl] = ???
  def history: Query[Tpl] = ???
}
