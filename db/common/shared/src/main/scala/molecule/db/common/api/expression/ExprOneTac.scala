package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprOneTac[T, Entity](
  entity: DataModel => Entity
) extends OneValue { self: Molecule =>
  def apply(                ): Entity = entity(addOne(dataModel, NoValue, Nil         ))
  def apply(v    : T, vs: T*): Entity = entity(addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs   : Seq[T]   ): Entity = entity(addOne(dataModel, Eq     , vs          ))
  def not  (v    : T, vs: T*): Entity = entity(addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs   : Seq[T]   ): Entity = entity(addOne(dataModel, Neq    , vs          ))
  def <    (upper: T        ): Entity = entity(addOne(dataModel, Lt     , Seq(upper)  ))
  def <=   (upper: T        ): Entity = entity(addOne(dataModel, Le     , Seq(upper)  ))
  def >    (lower: T        ): Entity = entity(addOne(dataModel, Gt     , Seq(lower)  ))
  def >=   (lower: T        ): Entity = entity(addOne(dataModel, Ge     , Seq(lower)  ))

  def apply(v    : qm): Entity = entity(addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity = entity(addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity = entity(addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity = entity(addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity = entity(addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity = entity(addOne(dataModel, Ge , Nil, true))

  def apply(fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Eq , fa))
  def not  (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Neq, fa))
  def <    (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Lt , fa))
  def <=   (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Le , fa))
  def >    (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Gt , fa))
  def >=   (fa: Molecule_0 & OneValue)(using ec: DummyImplicit): Entity = entity(filterAttr(dataModel, Ge , fa))

  def apply(sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Eq , sub))
  def not  (sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Neq, sub))
  def <    (sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Lt , sub))
  def <=   (sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Le , sub))
  def >    (sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Gt , sub))
  def >=   (sub: Molecule_1[T] & OneValue): Entity = entity(subQueryComparison(dataModel, Ge , sub))

  // Base type not used for tacit aggregates in subqueries
  def apply(kw: count)        : Entity = entity(addOne(dataModel, AggrFn("<unused>", "count"        ), Nil))
  def apply(kw: countDistinct): Entity = entity(addOne(dataModel, AggrFn("<unused>", "countDistinct"), Nil))
  def apply(kw: min)          : Entity = entity(addOne(dataModel, AggrFn("<unused>", "min"          ), Nil))
  def apply(kw: max)          : Entity = entity(addOne(dataModel, AggrFn("<unused>", "max"          ), Nil))
  def apply(kw: sample)       : Entity = entity(addOne(dataModel, AggrFn("<unused>", "sample"       ), Nil))
}