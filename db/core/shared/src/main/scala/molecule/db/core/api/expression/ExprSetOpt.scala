package molecule.db.core.api.expression

import molecule.base.metaModel.CardSet
import molecule.core.dataModel.*
import molecule.db.core.api.Molecule
import molecule.db.core.ops.ModelTransformations_.*


trait ExprSetOpt[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply(optSet: Option[Set[T]]) = entity(addSetOpt(dataModel, Eq, optSet))
}

trait ExprSetOpt_Enum[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply(optSet: Option[Set[T]]) = entity(addSetOpt(dataModel, Eq, optSet.map(_.map(_.toString.asInstanceOf[T]))))
}