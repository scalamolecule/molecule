package molecule.core.action

trait Query[Tpl] {

  def limit(n: Int): Query[Tpl]
  def offset(n: Int): QueryOffset[Tpl]
  def from(cursor: String): QueryCursor[Tpl]

  // Datomic time api
  def asOf(n: Int): Query[Tpl] = ???
  def history: Query[Tpl] = ???
}