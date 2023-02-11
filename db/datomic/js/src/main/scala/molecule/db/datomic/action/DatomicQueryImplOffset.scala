package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.QueryOffset

case class DatomicQueryImplOffset[Tpl](
  elements: List[Element],
  private val limit: Int = 0,
  private val offset: Int = 0
) extends QueryOffset[Tpl] {

  override def limit(l: Int): DatomicQueryImplOffset[Tpl] = copy(limit = l)
}
