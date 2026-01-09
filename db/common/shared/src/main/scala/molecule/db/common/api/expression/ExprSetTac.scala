package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprSetTac[T, Entity](entity: DataModel => Entity) extends SetValue { self: Molecule =>
  def apply(                ) = entity(addSet(dataModel, NoValue, Set.empty[T]   ))
  def apply(set: Set[T]     ) = entity(addSet(dataModel, Eq     , set            ))
  def has  (v  : T, vs: T*  ) = entity(addSet(dataModel, Has    , Set(v) ++ vs   ))
  def has  (vs : Iterable[T]) = entity(addSet(dataModel, Has    , vs.toSet       ))
  def hasNo(v  : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , Set(v) ++ vs   ))
  def hasNo(vs : Iterable[T]) = entity(addSet(dataModel, HasNo  , vs.toSet       ))

  def has  (m: Molecule) = entity(filterAttr(dataModel, Has  , m))
  def hasNo(m: Molecule) = entity(filterAttr(dataModel, HasNo, m))
}

trait ExprSetTac_Enum[T, Entity](entity: DataModel => Entity) extends SetValue { self: Molecule =>
  def apply(                ) = entity(addSet(dataModel, NoValue, Set.empty[T]                                  ))
  def apply(set: Set[T]     ) = entity(addSet(dataModel, Eq     , set           .map(_.toString.asInstanceOf[T])))
  def has  (v  : T, vs: T*  ) = entity(addSet(dataModel, Has    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def has  (vs : Iterable[T]) = entity(addSet(dataModel, Has    , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
  def hasNo(v  : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo(vs : Iterable[T]) = entity(addSet(dataModel, HasNo  , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
}