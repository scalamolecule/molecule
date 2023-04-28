package molecule.core.action

import molecule.boilerplate.ast.Model.Element

abstract class QueryOffset[Tpl](elements0: List[Element]) extends Action(elements0, Nil) {
  def limit(n: Int): QueryOffset[Tpl]
}
