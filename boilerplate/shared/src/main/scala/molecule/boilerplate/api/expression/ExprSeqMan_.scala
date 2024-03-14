// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSeqMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSeqTacOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                           ): Ns1[A, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSeqTacOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                           ): Ns1[A, B, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSeqTacOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSeqTacOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSeqTacOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Eq    , Nil                       )
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Eq    , seqs                      )
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Has   , Seq(seq)                  )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(HasNo , Seq(seq)                  )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Add   , Seq(v +: vs)              )
  def add   (vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Remove, Seq(v +: vs)              )
  def remove(vs  : Seq[t]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeq(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
