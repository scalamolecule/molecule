// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[t] = ???
}

trait ExprSetTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprSetTacOps_0[t, Ns1, Ns2] {
  def apply (                            )               : Ns1[t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, t] = ???
}

trait ExprSetTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetTacOps_1[A, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, t] = ???
}

trait ExprSetTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetTacOps_2[A, B, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, t] = ???
}

trait ExprSetTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSetTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSetTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSetTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Appl, a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Not , a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Lt  , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Le  , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Gt  , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Ge  , a)

  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Appl, a)
  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Not , a)
  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Lt  , a)
  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Le  , a)
  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Gt  , a)
  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Ge  , a)
}


trait ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply (                            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(NoValue, Nil                       )
  def apply (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , set +: sets               )
  def apply (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Appl   , sets                      )
  def not   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , set +: sets               )
  def not   (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Not    , sets                      )
  def ==    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , set +: sets               )
  def ==    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Eq     , sets                      )
  def !=    (set  : Set[t]               )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , set +: sets               )
  def !=    (sets : Seq[Set[t]]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Neq    , sets                      )
  def <     (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Lt     , Seq(Set(upper))           )
  def <=    (upper: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Le     , Seq(Set(upper))           )
  def >     (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Gt     , Seq(Set(lower))           )
  def >=    (lower: t                    )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Ge     , Seq(Set(lower))           )
  def add   (v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Add    , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Swap   , abs2sets(abs)             )
  def remove(v    : t, vs: t*            )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]          )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetTac(Remove , Seq(vs.toSet)             )

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Appl, a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Not , a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt  , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le  , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt  , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge  , a)
}