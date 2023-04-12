package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, Query}

case class DatomicQuery[Tpl](
  private val elements0: List[Element],
  limit: Option[Int] = None
) extends Action(elements0) with Query[Tpl] {

  override def limit(l: Int): DatomicQuery[Tpl] = copy(limit = Some(l))
  override def offset(o: Int): DatomicQueryOffset[Tpl] = DatomicQueryOffset(elements, limit, o)
  override def from(cursor: String): DatomicQueryCursor[Tpl] = DatomicQueryCursor(elements, limit, cursor)
}
