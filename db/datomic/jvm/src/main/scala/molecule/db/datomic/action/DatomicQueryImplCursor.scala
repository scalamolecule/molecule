package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryCursor

case class DatomicQueryImplCursor[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends QueryCursor[Tpl] {

  override def limit(l: Int): DatomicQueryImplCursor[Tpl] = copy(limit = Some(l))
}
