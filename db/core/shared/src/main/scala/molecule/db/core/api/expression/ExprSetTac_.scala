// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.base.ast.CardOne
import molecule.core.ast.*
import molecule.db.core.api.*


trait ExprSetTacOps_0[t, Entity1[_], Entity2[_, _]] extends ExprAttr_0[t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[t] = ???
}

trait ExprSetTac_0[t, Entity1[_], Entity2[_, _]]
  extends ExprSetTacOps_0[t, Entity1, Entity2] {
  def apply(                ): Entity1[t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, t] = ???
}

trait ExprSetTac_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprSetTacOps_1[A, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, t] = ???
}

trait ExprSetTac_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprSetTacOps_2[A, B, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, t] = ???
}

trait ExprSetTac_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprSetTacOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, t] = ???
}

trait ExprSetTac_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprSetTacOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, t] = ???
}

trait ExprSetTac_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprSetTacOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, t] = ???
}

trait ExprSetTac_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, t] = ???
}

trait ExprSetTac_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprSetTac_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprSetTac_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprSetTac_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprSetTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprSetTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprSetTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprSetTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprSetTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprSetTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprSetTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprSetTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprSetTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprSetTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprSetTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = _attrMan(HasNo, a)
}


trait ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprSet(op: Op, set: Set[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprSetTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprSetTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(NoValue, Set.empty[t]   )
  def apply(set: Set[t]     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Eq     , set            )
  def has  (v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Has    , Set(v) ++ vs   )
  def has  (vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(Has    , vs.toSet       )
  def hasNo(v  : t, vs: t*  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo(vs : Iterable[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprSet(HasNo  , vs.toSet       )
  
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
