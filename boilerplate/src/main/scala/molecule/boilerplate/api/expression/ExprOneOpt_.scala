// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.MoleculeModel._


trait ExprOneOptOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, t] with SortAttrs_1[A, t, Ns] = ???
}

trait ExprOneOpt_1[A, t, Ns[_, _]]
  extends ExprOneOptOps_1[A, t, Ns]
    with SortAttrs_1[A, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = ???
}

trait ExprOneOpt_2[A, B, t, Ns[_, _, _]]
  extends ExprOneOptOps_2[A, B, t, Ns]
    with SortAttrs_2[A, B, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = ???
}

trait ExprOneOpt_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprOneOptOps_3[A, B, C, t, Ns]
    with SortAttrs_3[A, B, C, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = ???
}

trait ExprOneOpt_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprOneOptOps_4[A, B, C, D, t, Ns]
    with SortAttrs_4[A, B, C, D, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = ???
}

trait ExprOneOpt_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprOneOptOps_5[A, B, C, D, E, t, Ns]
    with SortAttrs_5[A, B, C, D, E, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = ???
}

trait ExprOneOpt_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprOneOptOps_6[A, B, C, D, E, F, t, Ns]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = ???
}

trait ExprOneOpt_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_7[A, B, C, D, E, F, G, t, Ns]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = ???
}

trait ExprOneOpt_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_8[A, B, C, D, E, F, G, H, t, Ns]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = ???
}

trait ExprOneOpt_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = ???
}

trait ExprOneOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = ???
}

trait ExprOneOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = ???
}

trait ExprOneOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = ???
}

trait ExprOneOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = ???
}

trait ExprOneOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = ???
}

trait ExprOneOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = ???
}

trait ExprOneOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = ???
}

trait ExprOneOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = ???
}

trait ExprOneOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = ???
}

trait ExprOneOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = ???
}

trait ExprOneOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = ???
}

trait ExprOneOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}


trait ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = ???
}

trait ExprOneOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply(v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
  def apply(vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Appl, vs               )
  def not  (v    : Option[t]     )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
  def not  (vs   : Option[Seq[t]])               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Not , vs               )
  def <    (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
  def <=   (upper: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
  def >    (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
  def >=   (lower: Option[t]     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
}
