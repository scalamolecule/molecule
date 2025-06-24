// GENERATED CODE ********************************
package molecule.db.core.api

import molecule.base.error.ExecutionError


trait SortAttrsOps[Tpl <: Tuple, T, Entity[_ <: Tuple, _]] {
  protected def _sort(sort: String): Entity[Tpl, T]
  protected def _dynsort(i: Int): Entity[Tpl, T] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs[Tpl <: Tuple, T, Entity[_ <: Tuple, _]] extends SortAttrsOps[Tpl, T, Entity] {
  def a1: Entity[Tpl, T] = _sort("a1")
  def a2: Entity[Tpl, T] = _sort("a2")
  def a3: Entity[Tpl, T] = _sort("a3")
  def a4: Entity[Tpl, T] = _sort("a4")
  def a5: Entity[Tpl, T] = _sort("a5")
  def d1: Entity[Tpl, T] = _sort("d1")
  def d2: Entity[Tpl, T] = _sort("d2")
  def d3: Entity[Tpl, T] = _sort("d3")
  def d4: Entity[Tpl, T] = _sort("d4")
  def d5: Entity[Tpl, T] = _sort("d5")
  def sort(i: Int): Entity[Tpl, T] = _dynsort(i)
}
