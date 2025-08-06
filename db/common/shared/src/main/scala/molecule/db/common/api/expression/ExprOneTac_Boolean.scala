package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneTac_Boolean[T, Entity](entity: DataModel => Entity) extends CardOne { self: Molecule =>
  def apply(             ): Entity = entity(addOne(dataModel, NoValue, Nil         ))
  def apply(v : T, vs: T*): Entity = entity(addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs: Seq[T]   ): Entity = entity(addOne(dataModel, Eq     , vs          ))
  def not  (v : T, vs: T*): Entity = entity(addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs: Seq[T]   ): Entity = entity(addOne(dataModel, Neq    , vs          ))

  def apply(v: qm): Entity = entity(addOne(dataModel, Eq , Nil, true))
  def not  (v: qm): Entity = entity(addOne(dataModel, Neq, Nil, true))

  def apply(a: Molecule_0 & CardOne): Entity = entity(filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne): Entity = entity(filterAttr(dataModel, Neq, a))

  def &&(bool: T): Entity = entity(addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity = entity(addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity = entity(addOne(dataModel, AttrOp.Not, Nil      ))
}