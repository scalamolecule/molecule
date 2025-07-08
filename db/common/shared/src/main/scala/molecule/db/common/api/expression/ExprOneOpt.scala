package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneOpt[T, Entity](entity: DataModel => Entity) extends CardOne { self: Molecule =>
  def apply(opt: Option[T]) = entity(addOneOpt(dataModel, Eq, opt))
}

trait ExprOneOpt_Enum[T, Entity](entity: DataModel => Entity) extends CardOne { self: Molecule =>
  def apply(opt: Option[T]) = entity(addOneOpt(dataModel, Eq, opt.map(_.toString.asInstanceOf[T])))
}