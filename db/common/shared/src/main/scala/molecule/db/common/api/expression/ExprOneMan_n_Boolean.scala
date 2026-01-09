package molecule.db.common.api.expression

import scala.Tuple.{:*, Init}
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprOneMan_n_Boolean[T, Tpl <: Tuple, Entity[_ <: Tuple]](
  entity: [tpl <: Tuple] => DataModel => Entity[tpl]
) extends OneValue { self: Molecule  =>
  def apply(             ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil         ))
  def apply(v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs          ))
  def not  (v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs          ))

  def apply(v: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))

  def apply(fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , fa))
  def not  (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, fa))

  def apply(sub: Molecule_1[T] & OneValue): Entity[Tpl] = entity[Tpl](subQueryComparison(dataModel, Eq , sub))
  def not  (sub: Molecule_1[T] & OneValue): Entity[Tpl] = entity[Tpl](subQueryComparison(dataModel, Neq, sub))

  def apply(kw: distinct): Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw))

  def &&(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Not, Nil      ))
}


trait ExprOneMan_n_Boolean_Aggr[T, Tpl <: Tuple, Entity[_, _ <: Tuple]](
  entity: [t, tpl <: Tuple] => DataModel => Entity[t, tpl]
) extends OneValue { self: Molecule  =>
  def apply(kw: count)        : Entity[Int, Init[Tpl] :* Int] = entity[Int, Init[Tpl] :* Int](toInt(dataModel, kw))
  def apply(kw: countDistinct): Entity[Int, Init[Tpl] :* Int] = entity[Int, Init[Tpl] :* Int](toInt(dataModel, kw))
}


trait ExprOneMan_n_Boolean_AggrOps[T, Entity <: Molecule](
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
