package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryCursor

case class DatomicQueryImplCursor[Tpl](
  elements: List[Element],
  private val limit: Int = 0,
  private val cursor: String = ""
) extends QueryCursor[Tpl] {

  override def limit(l: Int): DatomicQueryImplCursor[Tpl] = copy(limit = l)
}
