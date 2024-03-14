// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.Model._


trait ExprSeqOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, t] = ???
}

trait ExprSeqOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSeqOptOps_1[A, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, t] = ???
}

trait ExprSeqOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSeqOptOps_2[A, B, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, t] = ???
}

trait ExprSeqOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSeqOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSeqOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSeqOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSeqOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSeqOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSeqOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSeqOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSeqOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSeqOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSeqOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSeqOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSeqOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSeqOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSeqOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSeqOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSeqOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSeqOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSeqOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSeqOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSeqOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSeqOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}


trait ExprSeqOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSeqOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply(seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Eq   , seqs                      )
  def not  (seq : Option[Seq[t]]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Neq  , seqs                      )
  def has  (v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
  def hasNo(v   : Option[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
}
