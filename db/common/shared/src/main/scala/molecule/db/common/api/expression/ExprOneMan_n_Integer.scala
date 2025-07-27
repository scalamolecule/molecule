package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprOneMan_n_Integer[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends CardOne { self: Molecule  =>
  def apply(                ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil         ))
  def apply(v    : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs   : Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs          ))
  def not  (v    : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs   : Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs          ))
  def <    (upper: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Lt     , Seq(upper)  ))
  def <=   (upper: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Le     , Seq(upper)  ))
  def >    (lower: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Gt     , Seq(lower)  ))
  def >=   (lower: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Ge     , Seq(lower)  ))

  def apply(v    : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Ge , Nil, true))

  def apply(a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Ge , a))

  def apply(kw: count)        : Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: countDistinct): Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: min)          : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: max)          : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: sample)       : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: mins)         : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: maxs)         : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: samples)      : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: distinct)     : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw            ))
  def apply(kw: sum)          : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs     (dataModel, kw))
  def apply(kw: median)       : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: avg)          : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: variance)     : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: stddev)       : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))

  def +(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Plus  , Seq(v)))
  def -(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Minus , Seq(v)))
  def *(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Times , Seq(v)))
  def /(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Divide, Seq(v)))
  def negate : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Negate, Nil   ))
  def abs    : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Abs   , Nil   ))
  def absNeg : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.AbsNeg, Nil   ))

  def even                       : Entity[Tpl] = entity[Tpl](addOne(dataModel, Even         , Nil                    ))
  def odd                        : Entity[Tpl] = entity[Tpl](addOne(dataModel, Odd          , Nil                    ))
  def %(divider: T, remainder: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, Remainder    , Seq(divider, remainder)))
}