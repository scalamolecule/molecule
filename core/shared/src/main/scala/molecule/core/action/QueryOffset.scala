package molecule.core.action

trait QueryOffset[Tpl] {
  def limit(n: Int): QueryOffset[Tpl]
}
