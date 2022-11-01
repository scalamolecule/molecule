// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.MoleculeModel._


trait ExprSetTacOps_0[t, Ns[_]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[t] = ???
}

trait ExprSetTac_0[t, Ns[_]]
  extends ExprSetTacOps_0[t, Ns] { //self: Ns[_] =>
  def apply  ()                                           : Ns[t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_1[A, t, Ns[_, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, t] = ???
}

trait ExprSetTac_1[A, t, Ns[_, _]]
  extends ExprSetTacOps_1[A, t, Ns] { //self: Ns[_, _] =>
  def apply  ()                                           : Ns[A, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_2[A, B, t, Ns[_, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, t] = ???
}

trait ExprSetTac_2[A, B, t, Ns[_, _, _]]
  extends ExprSetTacOps_2[A, B, t, Ns] { //self: Ns[_, _, _] =>
  def apply  ()                                           : Ns[A, B, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_3[A, B, C, t, Ns[_, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, t] = ???
}

trait ExprSetTac_3[A, B, C, t, Ns[_, _, _, _]]
  extends ExprSetTacOps_3[A, B, C, t, Ns] { //self: Ns[_, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, t] = ???
}

trait ExprSetTac_4[A, B, C, D, t, Ns[_, _, _, _, _]]
  extends ExprSetTacOps_4[A, B, C, D, t, Ns] { //self: Ns[_, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, t] = ???
}

trait ExprSetTac_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]]
  extends ExprSetTacOps_5[A, B, C, D, E, t, Ns] { //self: Ns[_, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, t] = ???
}

trait ExprSetTac_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]]
  extends ExprSetTacOps_6[A, B, C, D, E, F, t, Ns] { //self: Ns[_, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetTac_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_7[A, B, C, D, E, F, G, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetTac_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetTac_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Delete , Seq(vs.toSet))
}


trait ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] { //self: Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
  def apply  ()                                           : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(NoValue, Nil)
  def apply  (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , vs.map(v => Set(v)))
  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , set +: sets)
  def apply  (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , sets)
  def not    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not    (vs   : Seq[t]               )(implicit x: X): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , vs.map(v => Set(v)))
  def not    (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , set +: sets)
  def not    (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , sets)
  def ==     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , Seq(set))
  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , sets)
  def ==     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , sets)
  def !=     (set  : Set[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , Seq(set))
  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , set +: sets)
  def !=     (sets : Seq[Set[t]]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , sets)
  def <      (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Lt     , Seq(Set(upper)))
  def <=     (upper: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Le     , Seq(Set(upper)))
  def >      (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Gt     , Seq(Set(lower)))
  def >=     (lower: t                    )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Ge     , Seq(Set(lower)))
  def add    (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Add    , Seq((v +: vs).toSet) )
  def add    (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Add    , Seq(vs.toSet))
  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Replace, abs2sets(ab +: abs))
  def replace(abs  : Seq[(t, t)]          )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Replace, abs2sets(abs))
  def delete (v    : t, vs: t*            )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Delete , Seq((v +: vs).toSet))
  def delete (vs   : Seq[t]               )               : Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Delete , Seq(vs.toSet))
}
