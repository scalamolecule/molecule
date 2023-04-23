// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprOneOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = ???
}

trait ExprOneOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprOneOptOps_1[A, t, Ns1, Ns2]
    with SortAttrs_1[A, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = ???
}

trait ExprOneOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprOneOptOps_2[A, B, t, Ns1, Ns2]
    with SortAttrs_2[A, B, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = ???
}

trait ExprOneOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprOneOptOps_3[A, B, C, t, Ns1, Ns2]
    with SortAttrs_3[A, B, C, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = ???
}

trait ExprOneOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprOneOptOps_4[A, B, C, D, t, Ns1, Ns2]
    with SortAttrs_4[A, B, C, D, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = ???
}

trait ExprOneOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprOneOptOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with SortAttrs_5[A, B, C, D, E, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = ???
}

trait ExprOneOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = ???
}

trait ExprOneOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = ???
}

trait ExprOneOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = ???
}

trait ExprOneOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = ???
}

trait ExprOneOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = ???
}

trait ExprOneOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = ???
}

trait ExprOneOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = ???
}

trait ExprOneOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = ???
}

trait ExprOneOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = ???
}

trait ExprOneOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = ???
}

trait ExprOneOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = ???
}

trait ExprOneOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = ???
}

trait ExprOneOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = ???
}

trait ExprOneOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = ???
}

trait ExprOneOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = ???
}

trait ExprOneOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}


trait ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = ???
}

trait ExprOneOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply(v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Eq , vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Neq, vs               )
  def <    (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
}
