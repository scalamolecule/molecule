package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryOffset

case class DatomicQueryOffset[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Int
) extends QueryOffset[Tpl] {

  override def limit(l: Int): DatomicQueryOffset[Tpl] = copy(limit = Some(l))
}
