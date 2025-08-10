package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.Molecule
import molecule.db.common.ops.ModelTransformations_.*


trait ExprSetOpt[T, Entity](entity: DataModel => Entity) extends SetValue { self: Molecule =>
  def apply(optSet: Option[Set[T]]) = entity(addSetOpt(dataModel, Eq, optSet))
}

trait ExprSetOpt_Enum[T, Entity](entity: DataModel => Entity) extends SetValue { self: Molecule =>
  def apply(optSet: Option[Set[T]]) = entity(addSetOpt(dataModel, Eq, optSet.map(_.map(_.toString.asInstanceOf[T]))))
}