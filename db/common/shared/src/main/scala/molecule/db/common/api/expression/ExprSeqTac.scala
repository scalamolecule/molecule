package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprSeqTac[T, Entity](entity: DataModel => Entity) extends SeqValue { self: Molecule =>
  def apply(                ) = entity(addSeq(dataModel, NoValue, Seq.empty[T]))
  def apply(seq: Seq[T]     ) = entity(addSeq(dataModel, Eq     , seq         ))
  def has  (v  : T, vs: T*  ) = entity(addSeq(dataModel, Has    , Seq(v) ++ vs))
  def has  (vs : Iterable[T]) = entity(addSeq(dataModel, Has    , Seq()  ++ vs))
  def hasNo(v  : T, vs: T*  ) = entity(addSeq(dataModel, HasNo  , Seq(v) ++ vs))
  def hasNo(vs : Iterable[T]) = entity(addSeq(dataModel, HasNo  , Seq()  ++ vs))

  def has  (a: Molecule) = entity(filterAttr(dataModel, Has  , a))
  def hasNo(a: Molecule) = entity(filterAttr(dataModel, HasNo, a))
}

trait ExprSeqTac_Enum[T, Entity](entity: DataModel => Entity) extends SeqValue { self: Molecule =>
  def apply(                ) = entity(addSeq(dataModel, NoValue, Seq.empty[T]                                  ))
  def apply(seq: Seq[T]     ) = entity(addSeq(dataModel, Eq     , seq           .map(_.toString.asInstanceOf[T])))
  def has  (v  : T, vs: T*  ) = entity(addSeq(dataModel, Has    , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def has  (vs : Iterable[T]) = entity(addSeq(dataModel, Has    , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo(v  : T, vs: T*  ) = entity(addSeq(dataModel, HasNo  , (Seq(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo(vs : Iterable[T]) = entity(addSeq(dataModel, HasNo  , (Seq()  ++ vs).map(_.toString.asInstanceOf[T])))
}
