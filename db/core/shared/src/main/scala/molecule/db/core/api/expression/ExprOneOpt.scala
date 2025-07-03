package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.core.api.*
import molecule.db.core.ops.ModelTransformations_.*


trait ExprOneOpt[T, Entity](entity: DataModel => Entity) extends CardOne { self: Molecule =>
  def apply(opt: Option[T]) = entity(addOneOpt(dataModel, Eq, opt))
}

trait ExprOneOpt_Enum[T, Entity](entity: DataModel => Entity) extends CardOne { self: Molecule =>
  def apply(opt: Option[T]) = entity(addOneOpt(dataModel, Eq, opt.map(_.toString.asInstanceOf[T])))
}