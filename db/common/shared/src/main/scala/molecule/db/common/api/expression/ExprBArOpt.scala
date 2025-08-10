package molecule.db.common.api.expression

import scala.annotation.compileTimeOnly
import molecule.core.dataModel.*
import molecule.db.common.api.{Molecule, Molecule_0}
import molecule.db.common.ops.ModelTransformations_.*

trait ExprBArOpt[T, Entity](entity: DataModel => Entity) extends SeqValue { self: Molecule =>
  def apply(byteArray: Option[Array[T]]) = entity(addBArOpt(dataModel, Eq, byteArray))

  // Avoid stack overflow from overload resolution
  @compileTimeOnly("Optional attributes not allowed to compare with filter attribute.")
  def apply(a: Molecule_0 & SeqValue) = entity(filterAttr(dataModel, Eq, a))
}

