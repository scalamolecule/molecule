package molecule.db.core.api.expression

import molecule.base.metaModel.{CardOne, CardSet}
import molecule.core.dataModel.*
import molecule.db.core.api.{Molecule_0, *}
import molecule.db.core.ops.ModelTransformations_.*


trait ExprSetTac[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply(                ) = entity(addSet(dataModel, NoValue, Set.empty[T]   ))
  def apply(set: Set[T]     ) = entity(addSet(dataModel, Eq     , set            ))
  def has  (v  : T, vs: T*  ) = entity(addSet(dataModel, Has    , Set(v) ++ vs   ))
  def has  (vs : Iterable[T]) = entity(addSet(dataModel, Has    , vs.toSet       ))
  def hasNo(v  : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , Set(v) ++ vs   ))
  def hasNo(vs : Iterable[T]) = entity(addSet(dataModel, HasNo  , vs.toSet       ))

  def has  (a: Molecule_0 & CardOne) = entity(filterAttr(dataModel, Has  , a))
  def hasNo(a: Molecule_0 & CardOne) = entity(filterAttr(dataModel, HasNo, a))
}

trait ExprSetTac_Enum[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply(                ) = entity(addSet(dataModel, NoValue, Set.empty[T]                                  ))
  def apply(set: Set[T]     ) = entity(addSet(dataModel, Eq     , set           .map(_.toString.asInstanceOf[T])))
  def has  (v  : T, vs: T*  ) = entity(addSet(dataModel, Has    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def has  (vs : Iterable[T]) = entity(addSet(dataModel, Has    , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
  def hasNo(v  : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo(vs : Iterable[T]) = entity(addSet(dataModel, HasNo  , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
}