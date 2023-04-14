// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprOneManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = ???
}

trait ExprOneMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprOneManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1]
    with SortAttrs_1[A, t, Ns1] {
  def apply(                ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = ???
}

trait ExprOneMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprOneManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1]
    with SortAttrs_2[A, B, t, Ns1] {
  def apply(                ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = ???
}

trait ExprOneMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprOneManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1]
    with SortAttrs_3[A, B, C, t, Ns1] {
  def apply(                ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = ???
}

trait ExprOneMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprOneManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1]
    with SortAttrs_4[A, B, C, D, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = ???
}

trait ExprOneMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprOneManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1]
    with SortAttrs_5[A, B, C, D, E, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = ???
}

trait ExprOneMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprOneManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = ???
}

trait ExprOneMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = ???
}

trait ExprOneMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = ???
}

trait ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = ???
}

trait ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = ???
}

trait ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = ???
}

trait ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = ???
}

trait ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = ???
}

trait ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = ???
}

trait ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = ???
}

trait ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = ???
}

trait ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = ???
}

trait ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = ???
}

trait ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = ???
}

trait ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = ???
}

trait ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Ge , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Eq , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Neq, a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Le , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = _attrSortMan(Ge , a)
}


trait ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = ???
}

trait ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Eq , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Eq , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Eq , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Neq, v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Neq, vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Lt , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Le , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Gt , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneMan(Ge , Seq(lower))

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Ge , a)
}