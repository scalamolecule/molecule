package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.core.api.*


trait ExprSetMan[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSetTacOps[Tpl, T, This, Next] {
  def apply (                 ): This[Tpl, T] = _exprSet(NoValue, Set.empty[T]   )
  def apply (set : Set[T]     ): This[Tpl, T] = _exprSet(Eq     , set            )
  def has   (v   : T, vs: T*  ): This[Tpl, T] = _exprSet(Has    , Set(v) ++ vs   )
  def has   (vs  : Iterable[T]): This[Tpl, T] = _exprSet(Has    , vs.toSet       )
  def hasNo (v   : T, vs: T*  ): This[Tpl, T] = _exprSet(HasNo  , Set(v) ++ vs   )
  def hasNo (vs  : Iterable[T]): This[Tpl, T] = _exprSet(HasNo  , vs.toSet       )
  def add   (v   : T, vs: T*  ): This[Tpl, T] = _exprSet(Add    , Set(v) ++ vs   )
  def add   (vs  : Iterable[T]): This[Tpl, T] = _exprSet(Add    , vs.toSet       )
  def remove(v   : T, vs: T*  ): This[Tpl, T] = _exprSet(Remove , Set(v) ++ vs   )
  def remove(vs  : Iterable[T]): This[Tpl, T] = _exprSet(Remove , vs.toSet       )
  
  def has  (a: Molecule_0 & CardOne)(implicit x: DummyImplicit) = _filterAttrTacTac(Has  , a)
  def hasNo(a: Molecule_0 & CardOne)(implicit x: DummyImplicit) = _filterAttrTacTac(HasNo, a)

  def has  [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Has  , a)
  def hasNo[ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(HasNo, a)
}

trait ExprSetMan_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSetTacOps[Tpl, T, This, Next] {
  def apply (                 ) = _exprSet(NoValue, Set.empty[T]                                  )
  def apply (set : Set[T]     ) = _exprSet(Eq     , set           .map(_.toString.asInstanceOf[T]))
  def has   (v   : T, vs: T*  ) = _exprSet(Has    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def has   (vs  : Iterable[T]) = _exprSet(Has    , (vs.toSet    ).map(_.toString.asInstanceOf[T]))
  def hasNo (v   : T, vs: T*  ) = _exprSet(HasNo  , (Set(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def hasNo (vs  : Iterable[T]) = _exprSet(HasNo  , (vs.toSet    ).map(_.toString.asInstanceOf[T]))
  def add   (v   : T, vs: T*  ) = _exprSet(Add    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def add   (vs  : Iterable[T]) = _exprSet(Add    , (vs.toSet    ).map(_.toString.asInstanceOf[T]))
  def remove(v   : T, vs: T*  ) = _exprSet(Remove , (Set(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def remove(vs  : Iterable[T]) = _exprSet(Remove , (vs.toSet    ).map(_.toString.asInstanceOf[T]))
}
