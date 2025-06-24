package molecule.db.core.api

import molecule.core.dataModel.Keywords.*
import molecule.db.core.api.Tail
//import scala.Tuple.Tail // requires NonEmptyTuple in scala 3.3.6, not in 3.7

trait AggregatesOps[Tpl <: Tuple, T, Entity[_ <: Tuple, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[Int *: Tail[Tpl]   , Int   ] & SortAttrs[Int *: Tail[Tpl]   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[Tpl                , T     ] & SortAttrs[Tpl                , T     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[Double *: Tail[Tpl], Double] & SortAttrs[Double *: Tail[Tpl], Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[Set[T] *: Tail[Tpl], T]
  protected def _aggrDist  (kw: Kw                ): Entity[Set[T] *: Tail[Tpl], T]
}

trait Aggregates[Tpl <: Tuple, T, Entity[_ <: Tuple, _]] extends AggregatesOps[Tpl, T, Entity] {
  def apply(kw: count)         = _aggrInt(kw)
  def apply(kw: countDistinct) = _aggrInt(kw)
  def apply(kw: sum)           = _aggrT(kw)
  def apply(kw: min)           = _aggrT(kw)
  def apply(kw: max)           = _aggrT(kw)
  def apply(kw: sample)        = _aggrT(kw)
  def apply(kw: median)        = _aggrDouble(kw)
  def apply(kw: avg)           = _aggrDouble(kw)
  def apply(kw: variance)      = _aggrDouble(kw)
  def apply(kw: stddev)        = _aggrDouble(kw)
  def apply(kw: mins)          = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)          = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)       = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)      = _aggrDist(kw)
}

