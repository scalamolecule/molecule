package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneTac_Enum[T, Entity](entity: DataModel => Entity) extends OneValue { self: Molecule =>
  def apply(             ): Entity = entity(addOne(dataModel, NoValue, Nil                                      ))
  def apply(v : T, vs: T*): Entity = entity(addOne(dataModel, Eq     , (v +: vs).map(_.toString.asInstanceOf[T])))
  def apply(vs: Seq[T]   ): Entity = entity(addOne(dataModel, Eq     , vs       .map(_.toString.asInstanceOf[T])))
  def not  (v : T, vs: T*): Entity = entity(addOne(dataModel, Neq    , (v +: vs).map(_.toString.asInstanceOf[T])))
  def not  (vs: Seq[T]   ): Entity = entity(addOne(dataModel, Neq    , vs       .map(_.toString.asInstanceOf[T])))

  def apply(v : qm): Entity = entity(addOne(dataModel, Eq , Nil, true))
  def not  (v : qm): Entity = entity(addOne(dataModel, Neq, Nil, true))
}