// GENERATED CODE ********************************
package molecule.core.api.expression

import molecule.core.api.*
import molecule.core.ast.DataModel.*


trait ExprOneOptOps_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = ???
}

trait ExprOneOpt_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprOneOptOps_1[A, t, Entity1, Entity2]
    with SortAttrs_1[A, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = ???
}

trait ExprOneOpt_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprOneOptOps_2[A, B, t, Entity1, Entity2]
    with SortAttrs_2[A, B, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = ???
}

trait ExprOneOpt_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprOneOptOps_3[A, B, C, t, Entity1, Entity2]
    with SortAttrs_3[A, B, C, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = ???
}

trait ExprOneOpt_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprOneOptOps_4[A, B, C, D, t, Entity1, Entity2]
    with SortAttrs_4[A, B, C, D, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = ???
}

trait ExprOneOpt_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprOneOptOps_5[A, B, C, D, E, t, Entity1, Entity2]
    with SortAttrs_5[A, B, C, D, E, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = ???
}

trait ExprOneOpt_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2]
    with SortAttrs_6[A, B, C, D, E, F, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = ???
}

trait ExprOneOpt_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = ???
}

trait ExprOneOpt_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = ???
}

trait ExprOneOpt_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = ???
}

trait ExprOneOpt_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = ???
}

trait ExprOneOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = ???
}

trait ExprOneOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = ???
}

trait ExprOneOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = ???
}

trait ExprOneOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = ???
}

trait ExprOneOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = ???
}

trait ExprOneOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = ???
}

trait ExprOneOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = ???
}

trait ExprOneOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = ???
}

trait ExprOneOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = ???
}

trait ExprOneOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = ???
}

trait ExprOneOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _exprOneOpt(Eq, opt)
}


trait ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = ???
}

trait ExprOneOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] {
  def apply(opt: Option[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _exprOneOpt(Eq, opt)
}
