package molecule.db.core.api.expression

import molecule.base.metaModel.CardSeq
import molecule.core.dataModel.*
import molecule.db.core.api.Molecule
import molecule.db.core.ops.ModelTransformations_.*


trait ExprSeqOpt[T, Entity](entity: DataModel => Entity) extends CardSeq { self: Molecule =>
  def apply(optSeq: Option[Seq[T]]) = entity(addSeqOpt(dataModel, Eq, optSeq))
}

trait ExprSeqOpt_Enum[T, Entity](entity: DataModel => Entity) { self: Molecule =>
  def apply(optSeq: Option[Seq[T]]) = entity(addSeqOpt(dataModel, Eq, optSeq.map(_.map(_.toString.asInstanceOf[T]))))
}
