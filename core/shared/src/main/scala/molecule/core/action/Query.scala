package molecule.core.action

import molecule.boilerplate.ast.Model.Element

abstract class Query[Tpl](elements0: List[Element]) extends Action(elements0, Nil) {
  def limit(n: Int): Query[Tpl]
  def offset(n: Int): QueryOffset[Tpl]
  def from(cursor: String): QueryCursor[Tpl]
}
