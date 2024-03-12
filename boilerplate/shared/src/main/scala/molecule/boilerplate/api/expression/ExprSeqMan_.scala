// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSeqManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, t] = ???
}

trait ExprSeqMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSeqManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                           ): Ns1[A, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, t] = ???
}

trait ExprSeqMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSeqManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                           ): Ns1[A, B, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, t] = ???
}

trait ExprSeqMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSeqManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSeqMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSeqManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSeqMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSeqManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSeqMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSeqMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSeqMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSeqMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSeqMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSeqMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSeqMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSeqMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSeqMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSeqMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSeqMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSeqMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSeqMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSeqMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSeqMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSeqMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Seq[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Seq[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X     , t] = _attrMan(HasNo, a)
}


trait ExprSeqManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSeqMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Eq    , Nil                       )
  def apply (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Eq    , seq +: seqs               )
  def apply (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Eq    , seqs                      )
  def not   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Neq   , seq +: seqs               )
  def not   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Neq   , seqs                      )
  def has   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Has   , seq +: seqs               )
  def has   (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Has   , seqs                      )
  def hasNo (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(HasNo , seq +: seqs               )
  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(HasNo , seqs                      )
  def add   (v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
  def add   (vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
  def remove(v   : t, vs: t*            ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
  def remove(vs  : Iterable[t]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
