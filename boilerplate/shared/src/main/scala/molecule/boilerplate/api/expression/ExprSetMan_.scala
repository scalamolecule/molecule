// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast.SchemaAST._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, t] = ???
}

trait ExprSetMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                             )               : Ns1[A, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, t] = ???
}

trait ExprSetMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                             )               : Ns1[A, B, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, t] = ???
}

trait ExprSetMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSetMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSetMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSetMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasGe, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(HasNo, a)
  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t] = _attrMan(HasLt, a)
  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t] = _attrMan(HasLe, a)
  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t] = _attrMan(HasGt, a)
  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t     , t] = _attrMan(HasGe, a)
}


trait ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq   , sets                      )
  def has   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Has   , set +: sets               )
  def has   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Has   , sets                      )
  def hasNo (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasNo , set +: sets               )
  def hasNo (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasNo , sets                      )
  def hasLt (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
  def hasLe (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
  def hasGt (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
  def hasGe (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(HasGe , Seq(Set(lower))           )

  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
  def hasLt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasLt, a)
  def hasLe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasLe, a)
  def hasGt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasGt, a)
  def hasGe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasGe, a)
}
