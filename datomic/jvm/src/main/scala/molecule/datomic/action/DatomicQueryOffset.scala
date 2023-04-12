package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, QueryOffset}

case class DatomicQueryOffset[Tpl](
  private val elements0: List[Element],
  limit: Option[Int],
  offset: Int
) extends Action(elements0) with QueryOffset[Tpl] {

  override def limit(l: Int): DatomicQueryOffset[Tpl] = copy(limit = Some(l))
}
