// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprSetManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, t] = ???
}

trait ExprSetMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprSetManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                             )               : Ns1[A, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, t] = ???
}

trait ExprSetMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprSetManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                             )               : Ns1[A, B, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, t] = ???
}

trait ExprSetMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprSetManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprSetMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprSetManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprSetMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprSetManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprSetMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprSetManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

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


trait ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl  , Nil                       )
  def apply (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl  , set +: sets               )
  def apply (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Appl  , sets                      )
  def not   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not   , set +: sets               )
  def not   (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Not   , sets                      )
  def ==    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , Seq(set)                  )
  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , set +: sets               )
  def ==    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Eq    , sets                      )
  def !=    (set  : Set[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq   , Seq(set)                  )
  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq   , set +: sets               )
  def !=    (sets : Seq[Set[t]]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Neq   , sets                      )
  def <     (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
  def <=    (upper: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Le    , Seq(Set(upper))           )
  def >     (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
  def >=    (lower: t                     )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
  def add   (v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
  def add   (vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
  def swap  (abs  : Seq[(t, t)]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Swap  , abs2sets(abs)             )
  def remove(v    : t, vs: t*             )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
  def remove(vs   : Iterable[t]           )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSetMan(Remove, Seq(vs.toSet)             )

  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Appl, a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Not , a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt  , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le  , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt  , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge  , a)
}