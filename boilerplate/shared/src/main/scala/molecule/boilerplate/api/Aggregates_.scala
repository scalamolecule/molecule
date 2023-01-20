// GENERATED CODE ********************************
package molecule.boilerplate.api

import molecule.boilerplate.api.Keywords._


trait AggregatesOps_1[A, t, Ns[_, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[Double, Double] with SortAttrs_1[Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[Set[A], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A     , t     ]
}

trait Aggregates_1[A, t, Ns[_, _]] extends AggregatesOps_1[A, t, Ns] {
  def apply(kw: count)        : Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A     , t     ]                                      = _aggrT(kw)
  def apply(kw: sample)       : Ns[A     , t     ]                                      = _aggrT(kw)
  def apply(kw: distinct)     : Ns[Set[A], t     ]                                      = _aggrDist(kw)
  def apply(kw: mins)         : Ns[Set[t], t     ]                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[Set[t], t     ]                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[Set[t], t     ]                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[Set[t], t     ]                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_2[A, B, t, Ns[_, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, Set[B], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B     , t     ]
}

trait Aggregates_2[A, B, t, Ns[_, _, _]] extends AggregatesOps_2[A, B, t, Ns] {
  def apply(kw: count)        : Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B     , t     ]                                         = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B     , t     ]                                         = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, Set[B], t     ]                                         = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, Set[t], t     ]                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, Set[t], t     ]                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, Set[t], t     ]                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, Set[t], t     ]                                         = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_3[A, B, C, t, Ns[_, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, Set[C], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C     , t     ]
}

trait Aggregates_3[A, B, C, t, Ns[_, _, _, _]] extends AggregatesOps_3[A, B, C, t, Ns] {
  def apply(kw: count)        : Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C     , t     ]                                            = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C     , t     ]                                            = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, Set[C], t     ]                                            = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, Set[t], t     ]                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, Set[t], t     ]                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, Set[t], t     ]                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, Set[t], t     ]                                            = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, Set[D], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D     , t     ]
}

trait Aggregates_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends AggregatesOps_4[A, B, C, D, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D     , t     ]                                               = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D     , t     ]                                               = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, Set[D], t     ]                                               = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, Set[t], t     ]                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, Set[t], t     ]                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, Set[t], t     ]                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, Set[t], t     ]                                               = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, Set[E], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E     , t     ]
}

trait Aggregates_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends AggregatesOps_5[A, B, C, D, E, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E     , t     ]                                                  = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E     , t     ]                                                  = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, Set[E], t     ]                                                  = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, Set[t], t     ]                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, Set[t], t     ]                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, Set[t], t     ]                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, Set[t], t     ]                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, Set[F], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F     , t     ]
}

trait Aggregates_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends AggregatesOps_6[A, B, C, D, E, F, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F     , t     ]                                                     = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F     , t     ]                                                     = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, Set[F], t     ]                                                     = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, Set[t], t     ]                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, Set[t], t     ]                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, Set[t], t     ]                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, Set[t], t     ]                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, Set[G], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G     , t     ]
}

trait Aggregates_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends AggregatesOps_7[A, B, C, D, E, F, G, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G     , t     ]                                                        = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G     , t     ]                                                        = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, Set[G], t     ]                                                        = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, Set[t], t     ]                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, Set[t], t     ]                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, Set[t], t     ]                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, Set[t], t     ]                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, Set[H], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H     , t     ]
}

trait Aggregates_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends AggregatesOps_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H     , t     ]                                                           = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H     , t     ]                                                           = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, Set[H], t     ]                                                           = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, Set[t], t     ]                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, Set[t], t     ]                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, Set[t], t     ]                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, Set[t], t     ]                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, Set[I], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I     , t     ]
}

trait Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I     , t     ]                                                              = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I     , t     ]                                                              = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, Set[I], t     ]                                                              = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, Set[t], t     ]                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, Set[t], t     ]                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, Set[t], t     ]                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, Set[t], t     ]                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J     , t     ]
}

trait Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J     , t     ]                                                                  = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J     , t     ]                                                                  = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ]                                                                  = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, Set[t], t     ]                                                                  = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ]
}

trait Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ]                                                                     = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ]                                                                     = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ]                                                                     = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, Set[t], t     ]                                                                     = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ]
}

trait Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ]                                                                        = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ]                                                                        = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ]                                                                        = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[t], t     ]                                                                        = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ]
}

trait Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ]                                                                           = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ]                                                                           = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ]                                                                           = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t     ]                                                                           = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ]
}

trait Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ]                                                                              = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ]                                                                              = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ]                                                                              = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t     ]                                                                              = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ]
}

trait Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ]                                                                                 = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ]                                                                                 = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ]                                                                                 = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t     ]                                                                                 = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ]
}

trait Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ]                                                                                    = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ]                                                                                    = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ]                                                                                    = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t     ]                                                                                    = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ]
}

trait Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ]                                                                                       = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ]                                                                                       = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ]                                                                                       = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t     ]                                                                                       = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ]
}

trait Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ]                                                                                          = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ]                                                                                          = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ]                                                                                          = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t     ]                                                                                          = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ]
}

trait Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ]                                                                                             = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ]                                                                                             = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ]                                                                                             = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t     ]                                                                                             = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ]
}

trait Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ]                                                                                                = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ]                                                                                                = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ]                                                                                                = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t     ]                                                                                                = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ]
}

trait Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ]                                                                                                   = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ]                                                                                                   = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ]                                                                                                   = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t     ]                                                                                                   = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns]
  protected def _aggrDist  (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]
  protected def _aggrTsort (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ]
}

trait Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrTsort(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ]                                                                                                      = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ]                                                                                                      = _aggrT(kw)
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ]                                                                                                      = _aggrDist(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t     ]                                                                                                      = _aggrSet(kw, Some(kw.n))
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
}