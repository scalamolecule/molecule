// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprSeqOptOps_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, t] = ???
}

trait ExprSeqOpt_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprSeqOptOps_1[A, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_1_Enum[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprSeqOptOps_1[A, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, t] = ???
}

trait ExprSeqOpt_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprSeqOptOps_2[A, B, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_2_Enum[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprSeqOptOps_2[A, B, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, t] = ???
}

trait ExprSeqOpt_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprSeqOptOps_3[A, B, C, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_3_Enum[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprSeqOptOps_3[A, B, C, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, t] = ???
}

trait ExprSeqOpt_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprSeqOptOps_4[A, B, C, D, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_4_Enum[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprSeqOptOps_4[A, B, C, D, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, t] = ???
}

trait ExprSeqOpt_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprSeqOptOps_5[A, B, C, D, E, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_5_Enum[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprSeqOptOps_5[A, B, C, D, E, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, t] = ???
}

trait ExprSeqOpt_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_6_Enum[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSeqOpt_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_7_Enum[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSeqOpt_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_8_Enum[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSeqOpt_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_9_Enum[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSeqOpt_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_10_Enum[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSeqOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_11_Enum[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSeqOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_12_Enum[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSeqOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_13_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSeqOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_14_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSeqOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_15_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSeqOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_16_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSeqOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_17_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSeqOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_18_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSeqOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_19_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSeqOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_20_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSeqOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_21_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSeqOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSeqOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_22_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSeqOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2]{
  def apply(optSeq: Option[Seq[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[t])))
}
