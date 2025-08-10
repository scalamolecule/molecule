package molecule.db.common.api.expression

import scala.Tuple.{:*, Init}
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneMan_n_Decimal[T, Tpl <: Tuple, Entity[_ <: Tuple]](
  entity: [tpl <: Tuple] => DataModel => Entity[tpl]
) extends OneValue { self: Molecule  =>
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

  def apply(a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Ge , a))

  def apply(kw: mins)    : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs(dataModel, kw, Some(kw.n)))
  def apply(kw: maxs)    : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs(dataModel, kw, Some(kw.n)))
  def apply(kw: samples) : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs(dataModel, kw, Some(kw.n)))
  def apply(kw: distinct): Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs(dataModel, kw            ))

  def +(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Plus  , Seq(v)))
  def -(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Minus , Seq(v)))
  def *(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Times , Seq(v)))
  def /(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Divide, Seq(v)))
  def negate : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Negate, Nil   ))
  def abs    : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Abs   , Nil   ))
  def absNeg : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.AbsNeg, Nil   ))

  def ceil : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Ceil , Nil))
  def floor: Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Floor, Nil))
}


trait ExprOneMan_n_Decimal_Aggr[T, Tpl <: Tuple, Entity[_, _ <: Tuple]](
  entity: [t, tpl <: Tuple] => DataModel => Entity[t, tpl]
) extends OneValue { self: Molecule  =>
  def apply(kw: count)        : Entity[Int   , Init[Tpl] :* Int   ] = entity[Int   , Init[Tpl] :* Int   ](toInt   (dataModel, kw))
  def apply(kw: countDistinct): Entity[Int   , Init[Tpl] :* Int   ] = entity[Int   , Init[Tpl] :* Int   ](toInt   (dataModel, kw))
  def apply(kw: min)          : Entity[T     , Init[Tpl] :* T     ] = entity[T     , Init[Tpl] :* T     ](asIs    (dataModel, kw))
  def apply(kw: max)          : Entity[T     , Init[Tpl] :* T     ] = entity[T     , Init[Tpl] :* T     ](asIs    (dataModel, kw))
  def apply(kw: sample)       : Entity[T     , Init[Tpl] :* T     ] = entity[T     , Init[Tpl] :* T     ](asIs    (dataModel, kw))
  def apply(kw: sum)          : Entity[T     , Init[Tpl] :* T     ] = entity[T     , Init[Tpl] :* T     ](asIs    (dataModel, kw))
  def apply(kw: median)       : Entity[Double, Init[Tpl] :* Double] = entity[Double, Init[Tpl] :* Double](toDouble(dataModel, kw))
  def apply(kw: avg)          : Entity[Double, Init[Tpl] :* Double] = entity[Double, Init[Tpl] :* Double](toDouble(dataModel, kw))
  def apply(kw: variance)     : Entity[Double, Init[Tpl] :* Double] = entity[Double, Init[Tpl] :* Double](toDouble(dataModel, kw))
  def apply(kw: stddev)       : Entity[Double, Init[Tpl] :* Double] = entity[Double, Init[Tpl] :* Double](toDouble(dataModel, kw))
}


trait ExprOneMan_n_Decimal_AggrOps[T, Entity <: Molecule](
  entity: DataModel => Entity
) extends OneValue { self: Molecule  =>
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
