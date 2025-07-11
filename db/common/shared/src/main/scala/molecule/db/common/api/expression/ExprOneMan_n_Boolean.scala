package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprOneMan_n_Boolean[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends CardOne { self: Molecule  =>
  def apply(             ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil         ))
  def apply(v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs          ))
  def not  (v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs          ))

  def apply(v: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))

  def apply(a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, a))

  def apply(kw: count)        : Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: countDistinct): Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: distinct)     : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw            ))

  def &&(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Not, Nil      ))
}

