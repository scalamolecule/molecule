// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, t] = ???
}

trait ExprSetOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetOptOps_1[A, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, t] = ???
}

trait ExprSetOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetOptOps_2[A, B, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, t] = ???
}

trait ExprSetOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSetOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSetOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSetOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

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


trait ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Appl, sets                       )
  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Not , sets                       )
  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq  , sets                       )
  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Neq , sets                       )
  def <    (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
  def <=   (upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
  def >    (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
  def >=   (lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Appl, a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Not , a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt  , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le  , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt  , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge  , a)
}