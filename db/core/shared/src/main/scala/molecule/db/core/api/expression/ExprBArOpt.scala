package molecule.db.core.api.expression

import molecule.base.metaModel.CardSeq
import molecule.core.dataModel.*
import molecule.db.core.api.{Molecule, Molecule_0}
import molecule.db.core.ops.ModelTransformations_.*
import scala.annotation.compileTimeOnly

trait ExprBArOpt[T, Entity](entity: DataModel => Entity) extends CardSeq { self: Molecule =>
  def apply(byteArray: Option[Array[T]]) = entity(addBArOpt(dataModel, Eq, byteArray))

  // Avoid stack overflow from overload resolution
  @compileTimeOnly("Optional attributes not allowed to compare with filter attribute.")
  def apply(a: Molecule_0 & CardSeq) = entity(filterAttr(dataModel, Eq, a))
}

