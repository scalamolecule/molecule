package molecule.core.action

trait QueryCursor[Tpl] {
  def limit(n: Int): QueryCursor[Tpl]
}
