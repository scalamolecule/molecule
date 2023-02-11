package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryOffset

case class DatomicQueryImplOffset[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Int
) extends QueryOffset[Tpl] {

  override def limit(l: Int): DatomicQueryImplOffset[Tpl] = copy(limit = Some(l))
}
