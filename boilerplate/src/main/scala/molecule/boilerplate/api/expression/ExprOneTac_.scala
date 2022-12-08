// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api.Keywords.unify
import molecule.boilerplate.ast.MoleculeModel._


trait ExprOneTacOps_0[t, Ns[_]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[t] = ???
}

trait ExprOneTac_0[t, Ns[_]]
  extends ExprOneTacOps_0[t, Ns]{
  def apply()                : Ns[t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_1[A, t, Ns[_, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, t] = ???
}

trait ExprOneTac_1[A, t, Ns[_, _]]
  extends ExprOneTacOps_1[A, t, Ns]{
  def apply()                : Ns[A, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_2[A, B, t, Ns[_, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, t] = ???
}

trait ExprOneTac_2[A, B, t, Ns[_, _, _]]
  extends ExprOneTacOps_2[A, B, t, Ns]{
  def apply()                : Ns[A, B, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_3[A, B, C, t, Ns[_, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, t] = ???
}

trait ExprOneTac_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprOneTacOps_3[A, B, C, t, Ns]{
  def apply()                : Ns[A, B, C, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, t] = ???
}

trait ExprOneTac_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprOneTacOps_4[A, B, C, D, t, Ns]{
  def apply()                : Ns[A, B, C, D, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, t] = ???
}

trait ExprOneTac_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprOneTacOps_5[A, B, C, D, E, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, t] = ???
}

trait ExprOneTac_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprOneTacOps_6[A, B, C, D, E, F, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, t] = ???
}

trait ExprOneTac_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Ge     , Seq(lower))
}


trait ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]{
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Ge     , Seq(lower))
}
