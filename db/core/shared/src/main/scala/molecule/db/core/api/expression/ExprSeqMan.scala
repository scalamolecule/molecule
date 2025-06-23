package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.core.api.*


trait ExprSeqMan[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSeqTacOps[Tpl, T, This, Next] {

  def apply (                 ): This[Tpl, T] = _exprSeq(NoValue, Seq.empty[T])
  def apply (seq : Seq[T]     ): This[Tpl, T] = _exprSeq(Eq     , seq         )
  def has   (v   : T, vs: T*  ): This[Tpl, T] = _exprSeq(Has    , Seq(v) ++ vs)
  def has   (vs  : Iterable[T]): This[Tpl, T] = _exprSeq(Has    , Seq()  ++ vs)
  def hasNo (v   : T, vs: T*  ): This[Tpl, T] = _exprSeq(HasNo  , Seq(v) ++ vs)
  def hasNo (vs  : Iterable[T]): This[Tpl, T] = _exprSeq(HasNo  , Seq()  ++ vs)
  def add   (v   : T, vs: T*  ): This[Tpl, T] = _exprSeq(Add    , Seq(v) ++ vs)
  def add   (vs  : Iterable[T]): This[Tpl, T] = _exprSeq(Add    , Seq()  ++ vs)
  def remove(v   : T, vs: T*  ): This[Tpl, T] = _exprSeq(Remove , Seq(v) ++ vs)
  def remove(vs  : Iterable[T]): This[Tpl, T] = _exprSeq(Remove , Seq()  ++ vs)
  
  def has  (a: Molecule_0 & CardOne)(implicit x: DummyImplicit) = _filterAttrTacTac(Has  , a)
  def hasNo(a: Molecule_0 & CardOne)(implicit x: DummyImplicit) = _filterAttrTacTac(HasNo, a)

  def has  [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Has  , a)
  def hasNo[ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(HasNo, a)
}

trait ExprSeqMan_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSeqTacOps[Tpl, T, This, Next] {
  def apply (                 ) = _exprSeq(NoValue, Seq.empty[T]                                  )
  def apply (seq : Seq[T]     ) = _exprSeq(Eq     , seq           .map(_.toString.asInstanceOf[T]))
  def has   (v   : T, vs: T*  ) = _exprSeq(Has    , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def has   (vs  : Iterable[T]) = _exprSeq(Has    , (Seq()  ++ vs).map(_.toString.asInstanceOf[T]))
  def hasNo (v   : T, vs: T*  ) = _exprSeq(HasNo  , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def hasNo (vs  : Iterable[T]) = _exprSeq(HasNo  , (Seq()  ++ vs).map(_.toString.asInstanceOf[T]))
  def add   (v   : T, vs: T*  ) = _exprSeq(Add    , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def add   (vs  : Iterable[T]) = _exprSeq(Add    , (Seq()  ++ vs).map(_.toString.asInstanceOf[T]))
  def remove(v   : T, vs: T*  ) = _exprSeq(Remove , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T]))
  def remove(vs  : Iterable[T]) = _exprSeq(Remove , (Seq()  ++ vs).map(_.toString.asInstanceOf[T]))
}