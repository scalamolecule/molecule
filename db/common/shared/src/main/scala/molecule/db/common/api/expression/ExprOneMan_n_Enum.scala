package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprOneMan_n_Enum[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends CardOne { self: Molecule  =>
  def apply(             ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil                                      ))
  def apply(v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , (v +: vs).map(_.toString.asInstanceOf[T])))
  def apply(vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs       .map(_.toString.asInstanceOf[T])))
  def not  (v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , (v +: vs).map(_.toString.asInstanceOf[T])))
  def not  (vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs       .map(_.toString.asInstanceOf[T])))

  def apply(v : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))
}