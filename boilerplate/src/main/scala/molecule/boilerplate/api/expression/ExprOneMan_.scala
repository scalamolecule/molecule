// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.MoleculeModel._


trait ExprOneManOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, t] with SortAttrs_1[A, t, Ns] = ???
}

trait ExprOneMan_1[A, t, Ns[_, _]]
  extends ExprOneManOps_1[A, t, Ns]
    with Aggregates_1[A, t, Ns]
    with SortAttrs_1[A, t, Ns] {
  def apply(                ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = ???
}

trait ExprOneMan_2[A, B, t, Ns[_, _, _]]
  extends ExprOneManOps_2[A, B, t, Ns]
    with Aggregates_2[A, B, t, Ns]
    with SortAttrs_2[A, B, t, Ns] {
  def apply(                ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = ???
}

trait ExprOneMan_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprOneManOps_3[A, B, C, t, Ns]
    with Aggregates_3[A, B, C, t, Ns]
    with SortAttrs_3[A, B, C, t, Ns] {
  def apply(                ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = ???
}

trait ExprOneMan_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprOneManOps_4[A, B, C, D, t, Ns]
    with Aggregates_4[A, B, C, D, t, Ns]
    with SortAttrs_4[A, B, C, D, t, Ns] {
  def apply(                ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = ???
}

trait ExprOneMan_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprOneManOps_5[A, B, C, D, E, t, Ns]
    with Aggregates_5[A, B, C, D, E, t, Ns]
    with SortAttrs_5[A, B, C, D, E, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = ???
}

trait ExprOneMan_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprOneManOps_6[A, B, C, D, E, F, t, Ns]
    with Aggregates_6[A, B, C, D, E, F, t, Ns]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = ???
}

trait ExprOneMan_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = ???
}

trait ExprOneMan_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = ???
}

trait ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = ???
}

trait ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = ???
}

trait ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = ???
}

trait ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = ???
}

trait ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = ???
}

trait ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = ???
}

trait ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = ???
}

trait ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = ???
}

trait ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = ???
}

trait ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = ???
}

trait ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = ???
}

trait ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = ???
}

trait ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}


trait ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = ???
}

trait ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply(                ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Appl, Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Appl, v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Appl, vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Not , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Not , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Lt  , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Le  , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Gt  , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneMan(Ge  , Seq(lower))
}
