// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, t] = ???
}

trait ExprSetOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetOptOps_1[A, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, t] = ???
}

trait ExprSetOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetOptOps_2[A, B, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, t] = ???
}

trait ExprSetOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSetOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSetOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSetOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasGe, a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Eq   , a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasNo, a)
  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasLt, a)
  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasLe, a)
  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasGt, a)
  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasGe, a)
}


trait ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq   , sets                       )
  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Neq  , sets                       )
  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Has  , sets                       )
  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasNo, sets                       )
  def hasLt(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
  def hasLe(upper: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
  def hasGt(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
  def hasGe(lower: Option[t]          )                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasGe, a)
}