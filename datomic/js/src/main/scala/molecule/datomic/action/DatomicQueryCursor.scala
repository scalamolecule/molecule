package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, QueryCursor}

case class DatomicQueryCursor[Tpl](
  private val elements0: List[Element],
  limit: Option[Int],
  cursor: String
) extends Action(elements0) with QueryCursor[Tpl] {

  override def limit(l: Int): DatomicQueryCursor[Tpl] = copy(limit = Some(l))
}
