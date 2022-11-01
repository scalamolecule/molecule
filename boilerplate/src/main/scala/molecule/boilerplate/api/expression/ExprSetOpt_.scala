// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.MoleculeModel._


trait ExprSetOptOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, t] with SortAttrs_1[A, t, Ns] = ???
}

trait ExprSetOpt_1[A, t, Ns[_, _]]
  extends ExprSetOptOps_1[A, t, Ns]
    with SortAttrs_1[A, t, Ns] { //self: Ns[_, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = ???
}

trait ExprSetOpt_2[A, B, t, Ns[_, _, _]]
  extends ExprSetOptOps_2[A, B, t, Ns]
    with SortAttrs_2[A, B, t, Ns] { //self: Ns[_, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = ???
}

trait ExprSetOpt_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprSetOptOps_3[A, B, C, t, Ns]
    with SortAttrs_3[A, B, C, t, Ns] { //self: Ns[_, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = ???
}

trait ExprSetOpt_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprSetOptOps_4[A, B, C, D, t, Ns]
    with SortAttrs_4[A, B, C, D, t, Ns] { //self: Ns[_, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = ???
}

trait ExprSetOpt_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprSetOptOps_5[A, B, C, D, E, t, Ns]
    with SortAttrs_5[A, B, C, D, E, t, Ns] { //self: Ns[_, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = ???
}

trait ExprSetOpt_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprSetOptOps_6[A, B, C, D, E, F, t, Ns]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns] { //self: Ns[_, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = ???
}

trait ExprSetOpt_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = ???
}

trait ExprSetOpt_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = ???
}

trait ExprSetOpt_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = ???
}

trait ExprSetOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = ???
}

trait ExprSetOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = ???
}

trait ExprSetOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = ???
}

trait ExprSetOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = ???
}

trait ExprSetOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = ???
}

trait ExprSetOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = ???
}

trait ExprSetOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = ???
}

trait ExprSetOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = ???
}

trait ExprSetOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = ???
}

trait ExprSetOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = ???
}

trait ExprSetOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = ???
}

trait ExprSetOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}


trait ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = ???
}

trait ExprSetOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply(v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
  def apply(sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Appl, sets)
  def not  (v    : Option[t]          )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
  def not  (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Not , sets)
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Eq  , sets)
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Neq , sets)
  def <    (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
}
