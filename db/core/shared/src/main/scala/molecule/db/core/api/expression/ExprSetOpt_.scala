// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprSetOptOps_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, t] = ???
}

trait ExprSetOpt_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprSetOptOps_1[A, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_1_Enum[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprSetOptOps_1[A, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, t] = ???
}

trait ExprSetOpt_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprSetOptOps_2[A, B, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_2_Enum[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprSetOptOps_2[A, B, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, t] = ???
}

trait ExprSetOpt_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprSetOptOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_3_Enum[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprSetOptOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, t] = ???
}

trait ExprSetOpt_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprSetOptOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_4_Enum[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprSetOptOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, t] = ???
}

trait ExprSetOpt_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprSetOptOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_5_Enum[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprSetOptOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, t] = ???
}

trait ExprSetOpt_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_6_Enum[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetOpt_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_7_Enum[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetOpt_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_8_Enum[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetOpt_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_9_Enum[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetOpt_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_10_Enum[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_11_Enum[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_12_Enum[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_13_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_14_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_15_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_16_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_17_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_18_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_19_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_20_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_21_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}


trait ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_22_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(optSet: Option[Set[t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[t])))
}
