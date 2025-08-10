package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.Molecule
import molecule.db.common.ops.ModelTransformations_.*


trait ExprSeqOpt[T, Entity](entity: DataModel => Entity) extends SeqValue { self: Molecule =>
  def apply(optSeq: Option[Seq[T]]) = entity(addSeqOpt(dataModel, Eq, optSeq))
}

trait ExprSeqOpt_Enum[T, Entity](entity: DataModel => Entity) { self: Molecule =>
  def apply(optSeq: Option[Seq[T]]) = entity(addSeqOpt(dataModel, Eq, optSeq.map(_.map(_.toString.asInstanceOf[T]))))
}
