// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.api.Keywords.unify
import molecule.boilerplate.ast.Model._


trait ExprOneTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[t] = ???
}

trait ExprOneTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprOneTacOps_0[t, Ns1, Ns2] {
  def apply()                : Ns1[t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, t] = ???
}

trait ExprOneTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprOneTacOps_1[A, t, Ns1, Ns2] {
  def apply()                : Ns1[A, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, t] = ???
}

trait ExprOneTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprOneTacOps_2[A, B, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, t] = ???
}

trait ExprOneTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprOneTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, t] = ???
}

trait ExprOneTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprOneTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprOneTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprOneTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprOneTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprOneTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Ge  , a)
}


trait ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply()                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(NoValue, Nil       )
  def apply(unify: unify    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Unify  , Nil       )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Appl   , v +: vs   )
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Appl   , vs        )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Not    , v +: vs   )
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Not    , vs        )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Lt     , Seq(upper))
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Le     , Seq(upper))
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Gt     , Seq(lower))
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprOneTac(Ge     , Seq(lower))

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Appl, a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Not , a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt  , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le  , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt  , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge  , a)
}