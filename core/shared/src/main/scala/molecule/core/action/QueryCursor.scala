package molecule.core.action


import molecule.boilerplate.ast.Model.Element

abstract class QueryCursor[Tpl](elements0: List[Element]) extends Action(elements0, Nil) {
  def limit(n: Int): QueryCursor[Tpl]
}
