// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api.Keywords.unify
import molecule.boilerplate.ast.MoleculeModel._


trait ExprOneTacOps_0[t, Ns[_]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[t]
}

trait ExprOneTac_0[t, Ns[_]]
  extends ExprOneTacOps_0[t, Ns] {
  def apply()                : Ns[t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_1[A, t, Ns[_, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, t]
}

trait ExprOneTac_1[A, t, Ns[_, _]]
  extends ExprOneTacOps_1[A, t, Ns] {
  def apply()                : Ns[A, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_2[A, B, t, Ns[_, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, t]
}

trait ExprOneTac_2[A, B, t, Ns[_, _, _]]
  extends ExprOneTacOps_2[A, B, t, Ns] {
  def apply()                : Ns[A, B, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_3[A, B, C, t, Ns[_, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, t]
}

trait ExprOneTac_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprOneTacOps_3[A, B, C, t, Ns] {
  def apply()                : Ns[A, B, C, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, t]
}

trait ExprOneTac_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprOneTacOps_4[A, B, C, D, t, Ns] {
  def apply()                : Ns[A, B, C, D, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, t]
}

trait ExprOneTac_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprOneTacOps_5[A, B, C, D, E, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, t]
}

trait ExprOneTac_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprOneTacOps_6[A, B, C, D, E, F, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, t]
}

trait ExprOneTac_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, t]
}

trait ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, t]
}

trait ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, t]
}

trait ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, t]
}

trait ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t]
}

trait ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t]
}

trait ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t]
}

trait ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t]
}

trait ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t]
}

trait ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t]
}

trait ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t]
}

trait ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t]
}

trait ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t]
}

trait ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t]
}

trait ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprTac(Ge, Seq(lower))
}


trait ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprTac(op: Op, vs: Seq[t]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t]
}

trait ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply()                : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(NoValue, Nil)
  def apply(unify: unify)    : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Unify, Nil)
  def apply(v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Eq, v +: vs)
  def apply(vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Eq, vs)
  def not  (v    : t, vs: t*): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Neq, v +: vs)
  def not  (vs   : Seq[t])   : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Neq, vs)
  def <    (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Lt, Seq(upper))
  def <=   (upper: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Le, Seq(upper))
  def >    (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Gt, Seq(lower))
  def >=   (lower: t)        : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprTac(Ge, Seq(lower))
}
