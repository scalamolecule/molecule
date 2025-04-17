// GENERATED CODE ********************************
package molecule.core.api

import molecule.core.api.Keywords._


trait AggregatesOps_1[A, t, Entity[_, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[Int   , Int   ] & SortAttrs_1[Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[t     , t     ] & SortAttrs_1[t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[Double, Double] & SortAttrs_1[Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[Set[A], t     ]
}

trait Aggregates_1[A, t, Entity[_, _]] extends AggregatesOps_1[A, t, Entity] {
  def apply(kw: count)        : Entity[Int   , Int   ] & SortAttrs_1[Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[Int   , Int   ] & SortAttrs_1[Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[t     , t     ] & SortAttrs_1[t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[t     , t     ] & SortAttrs_1[t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[t     , t     ] & SortAttrs_1[t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[t     , t     ] & SortAttrs_1[t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[Double, Double] & SortAttrs_1[Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[Double, Double] & SortAttrs_1[Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[Double, Double] & SortAttrs_1[Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[Double, Double] & SortAttrs_1[Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[Set[t], t     ]                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[Set[t], t     ]                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[Set[t], t     ]                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[Set[A], t     ]                                       = _aggrDist(kw)
}


trait AggregatesOps_2[A, B, t, Entity[_, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, Int   , Int   ] & SortAttrs_2[A, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, t     , t     ] & SortAttrs_2[A, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, Double, Double] & SortAttrs_2[A, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, Set[B], t     ]
}

trait Aggregates_2[A, B, t, Entity[_, _, _]] extends AggregatesOps_2[A, B, t, Entity] {
  def apply(kw: count)        : Entity[A, Int   , Int   ] & SortAttrs_2[A, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, Int   , Int   ] & SortAttrs_2[A, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, t     , t     ] & SortAttrs_2[A, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, t     , t     ] & SortAttrs_2[A, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, t     , t     ] & SortAttrs_2[A, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, t     , t     ] & SortAttrs_2[A, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, Double, Double] & SortAttrs_2[A, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, Double, Double] & SortAttrs_2[A, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, Double, Double] & SortAttrs_2[A, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, Double, Double] & SortAttrs_2[A, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, Set[t], t     ]                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, Set[t], t     ]                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, Set[t], t     ]                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, Set[B], t     ]                                          = _aggrDist(kw)
}


trait AggregatesOps_3[A, B, C, t, Entity[_, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, Int   , Int   ] & SortAttrs_3[A, B, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, t     , t     ] & SortAttrs_3[A, B, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, Double, Double] & SortAttrs_3[A, B, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, Set[C], t     ]
}

trait Aggregates_3[A, B, C, t, Entity[_, _, _, _]] extends AggregatesOps_3[A, B, C, t, Entity] {
  def apply(kw: count)        : Entity[A, B, Int   , Int   ] & SortAttrs_3[A, B, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, Int   , Int   ] & SortAttrs_3[A, B, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, t     , t     ] & SortAttrs_3[A, B, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, t     , t     ] & SortAttrs_3[A, B, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, t     , t     ] & SortAttrs_3[A, B, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, t     , t     ] & SortAttrs_3[A, B, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, Double, Double] & SortAttrs_3[A, B, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, Double, Double] & SortAttrs_3[A, B, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, Double, Double] & SortAttrs_3[A, B, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, Double, Double] & SortAttrs_3[A, B, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, Set[t], t     ]                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, Set[t], t     ]                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, Set[t], t     ]                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, Set[C], t     ]                                             = _aggrDist(kw)
}


trait AggregatesOps_4[A, B, C, D, t, Entity[_, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, Int   , Int   ] & SortAttrs_4[A, B, C, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, t     , t     ] & SortAttrs_4[A, B, C, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, Double, Double] & SortAttrs_4[A, B, C, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, Set[D], t     ]
}

trait Aggregates_4[A, B, C, D, t, Entity[_, _, _, _, _]] extends AggregatesOps_4[A, B, C, D, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, Int   , Int   ] & SortAttrs_4[A, B, C, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, Int   , Int   ] & SortAttrs_4[A, B, C, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, t     , t     ] & SortAttrs_4[A, B, C, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, t     , t     ] & SortAttrs_4[A, B, C, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, t     , t     ] & SortAttrs_4[A, B, C, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, t     , t     ] & SortAttrs_4[A, B, C, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, Double, Double] & SortAttrs_4[A, B, C, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, Double, Double] & SortAttrs_4[A, B, C, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, Double, Double] & SortAttrs_4[A, B, C, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, Double, Double] & SortAttrs_4[A, B, C, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, Set[t], t     ]                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, Set[t], t     ]                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, Set[t], t     ]                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, Set[D], t     ]                                                = _aggrDist(kw)
}


trait AggregatesOps_5[A, B, C, D, E, t, Entity[_, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, Int   , Int   ] & SortAttrs_5[A, B, C, D, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, t     , t     ] & SortAttrs_5[A, B, C, D, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, Double, Double] & SortAttrs_5[A, B, C, D, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, Set[E], t     ]
}

trait Aggregates_5[A, B, C, D, E, t, Entity[_, _, _, _, _, _]] extends AggregatesOps_5[A, B, C, D, E, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, Int   , Int   ] & SortAttrs_5[A, B, C, D, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, Int   , Int   ] & SortAttrs_5[A, B, C, D, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, t     , t     ] & SortAttrs_5[A, B, C, D, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, t     , t     ] & SortAttrs_5[A, B, C, D, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, t     , t     ] & SortAttrs_5[A, B, C, D, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, t     , t     ] & SortAttrs_5[A, B, C, D, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, Double, Double] & SortAttrs_5[A, B, C, D, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, Double, Double] & SortAttrs_5[A, B, C, D, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, Double, Double] & SortAttrs_5[A, B, C, D, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, Double, Double] & SortAttrs_5[A, B, C, D, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, Set[t], t     ]                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, Set[t], t     ]                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, Set[t], t     ]                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, Set[E], t     ]                                                   = _aggrDist(kw)
}


trait AggregatesOps_6[A, B, C, D, E, F, t, Entity[_, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, Int   , Int   ] & SortAttrs_6[A, B, C, D, E, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, t     , t     ] & SortAttrs_6[A, B, C, D, E, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, Double, Double] & SortAttrs_6[A, B, C, D, E, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, Set[F], t     ]
}

trait Aggregates_6[A, B, C, D, E, F, t, Entity[_, _, _, _, _, _, _]] extends AggregatesOps_6[A, B, C, D, E, F, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, Int   , Int   ] & SortAttrs_6[A, B, C, D, E, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, Int   , Int   ] & SortAttrs_6[A, B, C, D, E, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, t     , t     ] & SortAttrs_6[A, B, C, D, E, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, t     , t     ] & SortAttrs_6[A, B, C, D, E, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, t     , t     ] & SortAttrs_6[A, B, C, D, E, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, t     , t     ] & SortAttrs_6[A, B, C, D, E, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, Double, Double] & SortAttrs_6[A, B, C, D, E, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, Double, Double] & SortAttrs_6[A, B, C, D, E, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, Double, Double] & SortAttrs_6[A, B, C, D, E, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, Double, Double] & SortAttrs_6[A, B, C, D, E, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, Set[t], t     ]                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, Set[t], t     ]                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, Set[t], t     ]                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, Set[F], t     ]                                                      = _aggrDist(kw)
}


trait AggregatesOps_7[A, B, C, D, E, F, G, t, Entity[_, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, Int   , Int   ] & SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, t     , t     ] & SortAttrs_7[A, B, C, D, E, F, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, Double, Double] & SortAttrs_7[A, B, C, D, E, F, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, Set[G], t     ]
}

trait Aggregates_7[A, B, C, D, E, F, G, t, Entity[_, _, _, _, _, _, _, _]] extends AggregatesOps_7[A, B, C, D, E, F, G, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, Int   , Int   ] & SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, Int   , Int   ] & SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, t     , t     ] & SortAttrs_7[A, B, C, D, E, F, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, t     , t     ] & SortAttrs_7[A, B, C, D, E, F, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, t     , t     ] & SortAttrs_7[A, B, C, D, E, F, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, t     , t     ] & SortAttrs_7[A, B, C, D, E, F, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, Double, Double] & SortAttrs_7[A, B, C, D, E, F, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, Double, Double] & SortAttrs_7[A, B, C, D, E, F, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, Double, Double] & SortAttrs_7[A, B, C, D, E, F, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, Double, Double] & SortAttrs_7[A, B, C, D, E, F, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, Set[t], t     ]                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, Set[t], t     ]                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, Set[t], t     ]                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, Set[G], t     ]                                                         = _aggrDist(kw)
}


trait AggregatesOps_8[A, B, C, D, E, F, G, H, t, Entity[_, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, Int   , Int   ] & SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, t     , t     ] & SortAttrs_8[A, B, C, D, E, F, G, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, Double, Double] & SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, Set[H], t     ]
}

trait Aggregates_8[A, B, C, D, E, F, G, H, t, Entity[_, _, _, _, _, _, _, _, _]] extends AggregatesOps_8[A, B, C, D, E, F, G, H, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, Int   , Int   ] & SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, Int   , Int   ] & SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, t     , t     ] & SortAttrs_8[A, B, C, D, E, F, G, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, t     , t     ] & SortAttrs_8[A, B, C, D, E, F, G, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, t     , t     ] & SortAttrs_8[A, B, C, D, E, F, G, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, t     , t     ] & SortAttrs_8[A, B, C, D, E, F, G, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, Double, Double] & SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, Double, Double] & SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, Double, Double] & SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, Double, Double] & SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, Set[t], t     ]                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, Set[t], t     ]                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, Set[t], t     ]                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, Set[H], t     ]                                                            = _aggrDist(kw)
}


trait AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Entity[_, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, Int   , Int   ] & SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, t     , t     ] & SortAttrs_9[A, B, C, D, E, F, G, H, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, Double, Double] & SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, Set[I], t     ]
}

trait Aggregates_9[A, B, C, D, E, F, G, H, I, t, Entity[_, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, Int   , Int   ] & SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, Int   , Int   ] & SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, t     , t     ] & SortAttrs_9[A, B, C, D, E, F, G, H, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, t     , t     ] & SortAttrs_9[A, B, C, D, E, F, G, H, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, t     , t     ] & SortAttrs_9[A, B, C, D, E, F, G, H, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, t     , t     ] & SortAttrs_9[A, B, C, D, E, F, G, H, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, Double, Double] & SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, Double, Double] & SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, Double, Double] & SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, Double, Double] & SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, Set[t], t     ]                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, Set[t], t     ]                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, Set[t], t     ]                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, Set[I], t     ]                                                               = _aggrDist(kw)
}


trait AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, Int   , Int   ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, t     , t     ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, Double, Double] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, Set[J], t     ]
}

trait Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Entity[_, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, Int   , Int   ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, Int   , Int   ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, t     , t     ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, t     , t     ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, t     , t     ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, t     , t     ] & SortAttrs_10[A, B, C, D, E, F, G, H, I, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, Double, Double] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, Double, Double] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, Double, Double] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, Double, Double] & SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, Set[J], t     ]                                                                   = _aggrDist(kw)
}


trait AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, t     , t     ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, Double, Double] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, Set[K], t     ]
}

trait Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, t     , t     ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, t     , t     ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, t     , t     ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, t     , t     ] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, Double, Double] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, Double, Double] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, Double, Double] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, Double, Double] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, Set[K], t     ]                                                                      = _aggrDist(kw)
}


trait AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, t     , t     ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, Double, Double] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ]
}

trait Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, t     , t     ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, t     , t     ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, t     , t     ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, t     , t     ] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, Double, Double] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, Double, Double] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, Double, Double] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, Double, Double] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ]                                                                         = _aggrDist(kw)
}


trait AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ]
}

trait Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     ] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ]                                                                            = _aggrDist(kw)
}


trait AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ]
}

trait Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     ] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ]                                                                               = _aggrDist(kw)
}


trait AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ]
}

trait Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     ] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ]                                                                                  = _aggrDist(kw)
}


trait AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ]
}

trait Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     ] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ]                                                                                     = _aggrDist(kw)
}


trait AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ]
}

trait Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     ] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ]                                                                                        = _aggrDist(kw)
}


trait AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ]
}

trait Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     ] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ]                                                                                           = _aggrDist(kw)
}


trait AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ]
}

trait Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     ] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ]                                                                                              = _aggrDist(kw)
}


trait AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ]
}

trait Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     ] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ]                                                                                                 = _aggrDist(kw)
}


trait AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ]
}

trait Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     ] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ]                                                                                                    = _aggrDist(kw)
}


trait AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Entity]
  protected def _aggrT     (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     , Entity]
  protected def _aggrDouble(kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Entity]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]
  protected def _aggrDist  (kw: Kw                ): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ]
}

trait Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity] {
  def apply(kw: count)        : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: countDistinct): Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Entity] = _aggrInt(kw)
  def apply(kw: sum)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: min)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: max)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: sample)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     ] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t     , Entity] = _aggrT(kw)
  def apply(kw: median)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: avg)          : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: variance)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: stddev)       : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Entity] = _aggrDouble(kw)
  def apply(kw: mins)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Entity[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ]                                                                                                       = _aggrDist(kw)
}