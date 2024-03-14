// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetTacOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                             ): Ns1[A, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetTacOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                             ): Ns1[A, B, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetTacOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetTacOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetTacOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Set[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(HasNo, a)
}


trait ExprSetMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Eq    , Nil                       )
  def apply (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Eq    , set +: sets               )
  def apply (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Eq    , sets                      )
  def not   (set  : Set[t], sets: Set[t]* ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Neq   , set +: sets               )
  def not   (sets : Seq[Set[t]]           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Neq   , sets                      )
  def has   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Has   , (v +: vs).map(v => Set(v)))
  def has   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Has   , vs.map(v => Set(v))       )
  def hasNo (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(HasNo , (v +: vs).map(v => Set(v)))
  def hasNo (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(HasNo , vs.map(v => Set(v))       )
  def add   (v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Add   , Seq(vs.toSet)             )
  def remove(v    : t, vs: t*             ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Seq[t]                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Remove, Seq(vs.toSet)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
