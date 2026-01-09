package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprSeqMan[T, Entity](entity: DataModel => Entity) extends SeqValue { self: Molecule =>
  def apply (                 ) = entity(addSeq(dataModel, NoValue, Seq.empty[T]))
  def apply (seq : Seq[T]     ) = entity(addSeq(dataModel, Eq     , seq         ))
  def has   (v   : T, vs: T*  ) = entity(addSeq(dataModel, Has    , Seq(v) ++ vs))
  def has   (vs  : Iterable[T]) = entity(addSeq(dataModel, Has    , Seq()  ++ vs))
  def hasNo (v   : T, vs: T*  ) = entity(addSeq(dataModel, HasNo  , Seq(v) ++ vs))
  def hasNo (vs  : Iterable[T]) = entity(addSeq(dataModel, HasNo  , Seq()  ++ vs))
  def add   (v   : T, vs: T*  ) = entity(addSeq(dataModel, Add    , Seq(v) ++ vs))
  def add   (vs  : Iterable[T]) = entity(addSeq(dataModel, Add    , Seq()  ++ vs))
  def remove(v   : T, vs: T*  ) = entity(addSeq(dataModel, Remove , Seq(v) ++ vs))
  def remove(vs  : Iterable[T]) = entity(addSeq(dataModel, Remove , Seq()  ++ vs))

  def has  (m: Molecule) = entity(filterAttr(dataModel, Has  , m))
  def hasNo(m: Molecule) = entity(filterAttr(dataModel, HasNo, m))
}

trait ExprSeqMan_Enum[T, Entity](entity: DataModel => Entity) { self: Molecule =>
  def apply (                 ) = entity(addSeq(dataModel, NoValue, Seq.empty[T]                                  ))
  def apply (seq : Seq[T]     ) = entity(addSeq(dataModel, Eq     , seq           .map(_.toString.asInstanceOf[T])))
  def has   (v   : T, vs: T*  ) = entity(addSeq(dataModel, Has    , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def has   (vs  : Iterable[T]) = entity(addSeq(dataModel, Has    , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo (v   : T, vs: T*  ) = entity(addSeq(dataModel, HasNo  , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo (vs  : Iterable[T]) = entity(addSeq(dataModel, HasNo  , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
  def add   (v   : T, vs: T*  ) = entity(addSeq(dataModel, Add    , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def add   (vs  : Iterable[T]) = entity(addSeq(dataModel, Add    , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
  def remove(v   : T, vs: T*  ) = entity(addSeq(dataModel, Remove , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def remove(vs  : Iterable[T]) = entity(addSeq(dataModel, Remove , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
}