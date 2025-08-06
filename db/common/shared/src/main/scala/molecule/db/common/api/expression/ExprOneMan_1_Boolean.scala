package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneMan_1_Boolean[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends CardOne { self: Molecule  =>
  def apply(             ): Entity[T] = entity[T](addOne(dataModel, NoValue, Nil         ))
  def apply(v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Eq     , vs          ))
  def not  (v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Neq    , vs          ))

  def apply(v: qm): Entity[T] = entity[T](addOne(dataModel, Eq , Nil, true))
  def not  (v: qm): Entity[T] = entity[T](addOne(dataModel, Neq, Nil, true))

  def apply(a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Neq, a))

  def apply(kw: distinct): Entity[Set[T]] = entity[Set[T]](asIs (dataModel, kw))

  def &&(bool: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity[T] = entity[T](addOne(dataModel, AttrOp.Not, Nil      ))
}


trait ExprOneMan_1_Boolean_Aggr[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends CardOne { self: Molecule  =>
  def apply(kw: count)        : Entity[Int] = entity[Int](toInt(dataModel, kw))
  def apply(kw: countDistinct): Entity[Int] = entity[Int](toInt(dataModel, kw))
}


trait ExprOneMan_1_Boolean_AggrOps[T, Entity <: Molecule](entity: DataModel => Entity) extends CardOne { self: Molecule  =>
  def apply(v    : T): Entity = entity(addAggrOp(dataModel, Eq , Some(v)    ))
  def not  (v    : T): Entity = entity(addAggrOp(dataModel, Neq, Some(v)    ))
  def <    (upper: T): Entity = entity(addAggrOp(dataModel, Lt , Some(upper)))
  def <=   (upper: T): Entity = entity(addAggrOp(dataModel, Le , Some(upper)))
  def >    (lower: T): Entity = entity(addAggrOp(dataModel, Gt , Some(lower)))
  def >=   (lower: T): Entity = entity(addAggrOp(dataModel, Ge , Some(lower)))

  def apply(v    : qm): Entity = entity(addAggrOp(dataModel, Eq , None))
  def not  (v    : qm): Entity = entity(addAggrOp(dataModel, Neq, None))
  def <    (upper: qm): Entity = entity(addAggrOp(dataModel, Lt , None))
  def <=   (upper: qm): Entity = entity(addAggrOp(dataModel, Le , None))
  def >    (lower: qm): Entity = entity(addAggrOp(dataModel, Gt , None))
  def >=   (lower: qm): Entity = entity(addAggrOp(dataModel, Ge , None))
}

