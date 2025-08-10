package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneTac_String[T, Entity](entity: DataModel => Entity) extends OneValue { self: Molecule =>
  def apply(                ): Entity = entity(addOne(dataModel, NoValue, Nil         ))
  def apply(v    : T, vs: T*): Entity = entity(addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs   : Seq[T]   ): Entity = entity(addOne(dataModel, Eq     , vs          ))
  def not  (v    : T, vs: T*): Entity = entity(addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs   : Seq[T]   ): Entity = entity(addOne(dataModel, Neq    , vs          ))
  def <    (upper: T        ): Entity = entity(addOne(dataModel, Lt     , Seq(upper)  ))
  def <=   (upper: T        ): Entity = entity(addOne(dataModel, Le     , Seq(upper)  ))
  def >    (lower: T        ): Entity = entity(addOne(dataModel, Gt     , Seq(lower)  ))
  def >=   (lower: T        ): Entity = entity(addOne(dataModel, Ge     , Seq(lower)  ))

  def apply(v    : qm): Entity = entity(addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity = entity(addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity = entity(addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity = entity(addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity = entity(addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity = entity(addOne(dataModel, Ge , Nil, true))

  def apply(a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & OneValue): Entity = entity(filterAttr(dataModel, Ge , a))

  def startsWith(prefix: T): Entity = entity(addOne(dataModel, StartsWith, Seq(prefix)))
  def endsWith  (suffix: T): Entity = entity(addOne(dataModel, EndsWith  , Seq(suffix)))
  def contains  (needle: T): Entity = entity(addOne(dataModel, Contains  , Seq(needle)))
  def matches   (regex : T): Entity = entity(addOne(dataModel, Matches   , Seq(regex) ))

  def startsWith(prefix: qm): Entity = entity(addOne(dataModel, StartsWith, Nil, true))
  def endsWith  (suffix: qm): Entity = entity(addOne(dataModel, EndsWith  , Nil, true))
  def contains  (needle: qm): Entity = entity(addOne(dataModel, Contains  , Nil, true))
  def matches   (regex : qm): Entity = entity(addOne(dataModel, Matches   , Nil, true))
}