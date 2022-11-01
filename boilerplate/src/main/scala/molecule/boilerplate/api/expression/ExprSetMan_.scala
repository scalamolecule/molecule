// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.MoleculeModel._


trait ExprSetManOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, t] with SortAttrs_1[A, t, Ns] = ???
}

trait ExprSetMan_1[A, t, Ns[_, _]]
  extends ExprSetManOps_1[A, t, Ns]
    with Aggregates_1[A, t, Ns]
    with SortAttrs_1[A, t, Ns] { self: Ns[_, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = ???
}

trait ExprSetMan_2[A, B, t, Ns[_, _, _]]
  extends ExprSetManOps_2[A, B, t, Ns]
    with Aggregates_2[A, B, t, Ns]
    with SortAttrs_2[A, B, t, Ns] { self: Ns[_, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, t] with SortAttrs_2[A, B, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = ???
}

trait ExprSetMan_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprSetManOps_3[A, B, C, t, Ns]
    with Aggregates_3[A, B, C, t, Ns]
    with SortAttrs_3[A, B, C, t, Ns] { self: Ns[_, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = ???
}

trait ExprSetMan_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprSetManOps_4[A, B, C, D, t, Ns]
    with Aggregates_4[A, B, C, D, t, Ns]
    with SortAttrs_4[A, B, C, D, t, Ns] { self: Ns[_, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = ???
}

trait ExprSetMan_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprSetManOps_5[A, B, C, D, E, t, Ns]
    with Aggregates_5[A, B, C, D, E, t, Ns]
    with SortAttrs_5[A, B, C, D, E, t, Ns] { self: Ns[_, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = ???
}

trait ExprSetMan_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprSetManOps_6[A, B, C, D, E, F, t, Ns]
    with Aggregates_6[A, B, C, D, E, F, t, Ns]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns] { self: Ns[_, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = ???
}

trait ExprSetMan_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] { self: Ns[_, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = ???
}

trait ExprSetMan_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = ???
}

trait ExprSetMan_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = ???
}

trait ExprSetMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = ???
}

trait ExprSetMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = ???
}

trait ExprSetMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = ???
}

trait ExprSetMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = ???
}

trait ExprSetMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = ???
}

trait ExprSetMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = ???
}

trait ExprSetMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = ???
}

trait ExprSetMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = ???
}

trait ExprSetMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = ???
}

trait ExprSetMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = ???
}

trait ExprSetMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = ???
}

trait ExprSetMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = ???
}

trait ExprSetMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] { self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Eq     , sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
}
