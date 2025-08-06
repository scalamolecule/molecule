package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprSetMan[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply (                 ) = entity(addSet(dataModel, NoValue, Set.empty[T]   ))
  def apply (set : Set[T]     ) = entity(addSet(dataModel, Eq     , set            ))
  def has   (v   : T, vs: T*  ) = entity(addSet(dataModel, Has    , Set(v) ++ vs   ))
  def has   (vs  : Iterable[T]) = entity(addSet(dataModel, Has    , vs.toSet       ))
  def hasNo (v   : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , Set(v) ++ vs   ))
  def hasNo (vs  : Iterable[T]) = entity(addSet(dataModel, HasNo  , vs.toSet       ))
  def add   (v   : T, vs: T*  ) = entity(addSet(dataModel, Add    , Set(v) ++ vs   ))
  def add   (vs  : Iterable[T]) = entity(addSet(dataModel, Add    , vs.toSet       ))
  def remove(v   : T, vs: T*  ) = entity(addSet(dataModel, Remove , Set(v) ++ vs   ))
  def remove(vs  : Iterable[T]) = entity(addSet(dataModel, Remove , vs.toSet       ))

  def has  (a: Molecule_0 & CardOne) = entity(filterAttr(dataModel, Has  , a))
  def hasNo(a: Molecule_0 & CardOne) = entity(filterAttr(dataModel, HasNo, a))
}

trait ExprSetMan_Enum[T, Entity](entity: DataModel => Entity) extends CardSet { self: Molecule =>
  def apply (                 ) = entity(addSet(dataModel, NoValue, Set.empty[T]                                  ))
  def apply (set : Set[T]     ) = entity(addSet(dataModel, Eq     , set           .map(_.toString.asInstanceOf[T])))
  def has   (v   : T, vs: T*  ) = entity(addSet(dataModel, Has    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def has   (vs  : Iterable[T]) = entity(addSet(dataModel, Has    , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
  def hasNo (v   : T, vs: T*  ) = entity(addSet(dataModel, HasNo  , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def hasNo (vs  : Iterable[T]) = entity(addSet(dataModel, HasNo  , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
  def add   (v   : T, vs: T*  ) = entity(addSet(dataModel, Add    , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def add   (vs  : Iterable[T]) = entity(addSet(dataModel, Add    , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
  def remove(v   : T, vs: T*  ) = entity(addSet(dataModel, Remove , (Set(v) ++ vs).map(_.toString.asInstanceOf[T])))
  def remove(vs  : Iterable[T]) = entity(addSet(dataModel, Remove , (vs.toSet    ).map(_.toString.asInstanceOf[T])))
}
