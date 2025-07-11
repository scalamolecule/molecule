package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneMan_1_Enum[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends CardOne { self: Molecule  =>
  def apply(             ): Entity[T] = entity[T](addOne(dataModel, NoValue, Nil                                      ))
  def apply(v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Eq     , (v +: vs).map(_.toString.asInstanceOf[T])))
  def apply(vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Eq     , vs       .map(_.toString.asInstanceOf[T])))
  def not  (v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Neq    , (v +: vs).map(_.toString.asInstanceOf[T])))
  def not  (vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Neq    , vs       .map(_.toString.asInstanceOf[T])))

  def apply(v : qm): Entity[T] = entity[T](addOne(dataModel, Eq , Nil, true))
  def not  (v : qm): Entity[T] = entity[T](addOne(dataModel, Neq, Nil, true))
}