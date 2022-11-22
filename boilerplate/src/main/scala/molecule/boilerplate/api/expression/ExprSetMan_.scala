// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.MoleculeModel._


trait ExprSetManOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, t] = ???
}

trait ExprSetMan_1[A, t, Ns[_, _]]
  extends ExprSetManOps_1[A, t, Ns]
    with Aggregates_1[A, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, t] = ???
}

trait ExprSetMan_2[A, B, t, Ns[_, _, _]]
  extends ExprSetManOps_2[A, B, t, Ns]
    with Aggregates_2[A, B, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, t] = ???
}

trait ExprSetMan_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprSetManOps_3[A, B, C, t, Ns]
    with Aggregates_3[A, B, C, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, t] = ???
}

trait ExprSetMan_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprSetManOps_4[A, B, C, D, t, Ns]
    with Aggregates_4[A, B, C, D, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, t] = ???
}

trait ExprSetMan_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprSetManOps_5[A, B, C, D, E, t, Ns]
    with Aggregates_5[A, B, C, D, E, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, t] = ???
}

trait ExprSetMan_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprSetManOps_6[A, B, C, D, E, F, t, Ns]
    with Aggregates_6[A, B, C, D, E, F, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetMan_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetMan_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetMan_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Delete , Seq(vs.toSet))
}


trait ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def apply  (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl   , sets)
  def not    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]                )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not    , sets)
  def ==     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq     , set +: sets)
  def ==     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq     , sets)
  def !=     (set  : Set[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq    , sets)
  def <      (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Lt     , Seq(Set(upper)))
  def <=     (upper: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Le     , Seq(Set(upper)))
  def >      (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Gt     , Seq(Set(lower)))
  def >=     (lower: t                     )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]           )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*             )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]                )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Delete , Seq(vs.toSet))
}
