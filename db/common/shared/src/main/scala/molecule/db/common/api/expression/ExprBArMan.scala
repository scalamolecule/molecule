package molecule.db.common.api.expression

import molecule.base.metaModel.CardSeq
import molecule.core.dataModel.*
import molecule.db.common.api.{Molecule, Molecule_0}
import molecule.db.common.ops.ModelTransformations_.*
import scala.annotation.compileTimeOnly


trait ExprBArMan[T, Entity](entity: DataModel => Entity) extends CardSeq { self: Molecule =>
  def apply(                   ) = entity(addBAr(dataModel, NoValue, Array.empty[Byte].asInstanceOf[Array[T]]))
  def apply(byteArray: Array[T]) = entity(addBAr(dataModel, Eq     , byteArray                               ))
  def not  (byteArray: Array[T]) = entity(addBAr(dataModel, Neq    , byteArray                               ))

  // Avoid stack overflow from overload resolution
  @compileTimeOnly("Byte arrays not allowed as filter attributes.")
  def apply(a: Molecule_0 & CardSeq) = entity(filterAttr(dataModel, Eq, a))

  @compileTimeOnly("Byte arrays not allowed as filter attributes.")
  def not(a: Molecule_0 & CardSeq) = entity(filterAttr(dataModel, Neq, a))
}

