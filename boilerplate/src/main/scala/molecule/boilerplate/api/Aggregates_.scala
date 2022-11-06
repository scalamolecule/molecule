// GENERATED CODE ********************************
package molecule.boilerplate.api

import molecule.boilerplate.api.Keywords._


trait AggregatesOps_1[A, t, Ns[_, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[Double, Double] with SortAttrs_1[Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns]
}

trait Aggregates_1[A, t, Ns[_, _]] extends AggregatesOps_1[A, t, Ns] {
  def apply(kw: count)        : Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[Int   , Int   ] with SortAttrs_1[Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A     , t     ] with SortAttrs_1[A     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[Set[A], t     ] with SortAttrs_1[Set[A], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[Double, Double] with SortAttrs_1[Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_2[A, B, t, Ns[_, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns]
}

trait Aggregates_2[A, B, t, Ns[_, _, _]] extends AggregatesOps_2[A, B, t, Ns] {
  def apply(kw: count)        : Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, Int   , Int   ] with SortAttrs_2[A, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B     , t     ] with SortAttrs_2[A, B     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, Set[B], t     ] with SortAttrs_2[A, Set[B], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, Double, Double] with SortAttrs_2[A, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_3[A, B, C, t, Ns[_, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns]
}

trait Aggregates_3[A, B, C, t, Ns[_, _, _, _]] extends AggregatesOps_3[A, B, C, t, Ns] {
  def apply(kw: count)        : Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, Int   , Int   ] with SortAttrs_3[A, B, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C     , t     ] with SortAttrs_3[A, B, C     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, Set[C], t     ] with SortAttrs_3[A, B, Set[C], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, Double, Double] with SortAttrs_3[A, B, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns]
}

trait Aggregates_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends AggregatesOps_4[A, B, C, D, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, Int   , Int   ] with SortAttrs_4[A, B, C, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D     , t     ] with SortAttrs_4[A, B, C, D     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, Set[D], t     ] with SortAttrs_4[A, B, C, Set[D], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, Double, Double] with SortAttrs_4[A, B, C, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns]
}

trait Aggregates_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends AggregatesOps_5[A, B, C, D, E, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, Int   , Int   ] with SortAttrs_5[A, B, C, D, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E     , t     ] with SortAttrs_5[A, B, C, D, E     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, Set[E], t     ] with SortAttrs_5[A, B, C, D, Set[E], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, Double, Double] with SortAttrs_5[A, B, C, D, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns]
}

trait Aggregates_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends AggregatesOps_6[A, B, C, D, E, F, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, Int   , Int   ] with SortAttrs_6[A, B, C, D, E, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F     , t     ] with SortAttrs_6[A, B, C, D, E, F     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, Set[F], t     ] with SortAttrs_6[A, B, C, D, E, Set[F], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, Double, Double] with SortAttrs_6[A, B, C, D, E, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns]
}

trait Aggregates_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends AggregatesOps_7[A, B, C, D, E, F, G, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, Int   , Int   ] with SortAttrs_7[A, B, C, D, E, F, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G     , t     ] with SortAttrs_7[A, B, C, D, E, F, G     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, Set[G], t     ] with SortAttrs_7[A, B, C, D, E, F, Set[G], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, Double, Double] with SortAttrs_7[A, B, C, D, E, F, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns]
}

trait Aggregates_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends AggregatesOps_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, Int   , Int   ] with SortAttrs_8[A, B, C, D, E, F, G, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H     , t     ] with SortAttrs_8[A, B, C, D, E, F, G, H     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, Set[H], t     ] with SortAttrs_8[A, B, C, D, E, F, G, Set[H], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, Double, Double] with SortAttrs_8[A, B, C, D, E, F, G, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns]
}

trait Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, Int   , Int   ] with SortAttrs_9[A, B, C, D, E, F, G, H, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I     , t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, I     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, Set[I], t     ] with SortAttrs_9[A, B, C, D, E, F, G, H, Set[I], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, Double, Double] with SortAttrs_9[A, B, C, D, E, F, G, H, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns]
}

trait Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, Int   , Int   ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J     , t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, Set[J], t     ] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Set[J], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, Double, Double] with SortAttrs_10[A, B, C, D, E, F, G, H, I, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns]
}

trait Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, Int   , Int   ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K     , t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, Set[K], t     ] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Set[K], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, Double, Double] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns]
}

trait Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L     , t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     ] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Set[L], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, Double, Double] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns]
}

trait Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     ] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Set[M], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns]
}

trait Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     ] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[N], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns]
}

trait Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     ] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[O], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns]
}

trait Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     ] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[P], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns]
}

trait Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     ] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[Q], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns]
}

trait Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     ] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[R], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns]
}

trait Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     ] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[S], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns]
}

trait Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     ] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[T], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns]
}

trait Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     ] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[U], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Double, Double, Ns] = _aggrDouble(kw)
}


trait AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _aggrInt   (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns]
  protected def _aggrDouble(kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns]
  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns]
  protected def _aggrT     (kw: Kw                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns]
}

trait Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends AggregatesOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply(kw: count)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: countDistinct): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Int   , Int   , Ns] = _aggrInt(kw)
  def apply(kw: min)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: max)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: rand)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: sample)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: sum)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: median)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V     , t     , Ns] = _aggrT(kw)
  def apply(kw: mins)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: maxs)         : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: rands)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: samples)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns] = _aggrSet(kw, Some(kw.n))
  def apply(kw: distinct)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     ] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[V], t     , Ns] = _aggrSet(kw, None)
  def apply(kw: avg)          : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: variance)     : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
  def apply(kw: stddev)       : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Double, Double, Ns] = _aggrDouble(kw)
}