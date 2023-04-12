package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, Query}

case class DatomicQuery[Tpl](
  private val elements0: List[Element],
  limit: Option[Int] = None
) extends Action(elements0) with Query[Tpl] {

  // Universal api

  override def limit(l: Int): DatomicQuery[Tpl] = copy(limit = Some(l))
  override def offset(offset: Int): DatomicQueryOffset[Tpl] = DatomicQueryOffset(elements, limit, offset)
  override def from(cursor: String): DatomicQueryCursor[Tpl] = DatomicQueryCursor(elements, limit, cursor)

  // Datomic special features

  // Time
  def asOf(n: Int): DatomicQuery[Tpl] = ???
  def since(n: Int): DatomicQuery[Tpl] = ???
  def widh(n: Int): DatomicQuery[Tpl] = ???
  def history: DatomicQuery[Tpl] = ???
}
