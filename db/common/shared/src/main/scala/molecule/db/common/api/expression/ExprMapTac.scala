package molecule.db.common.api.expression

import molecule.base.metaModel.CardMap
import molecule.core.dataModel.*
import molecule.db.common.api.Molecule
import molecule.db.common.ops.ModelTransformations_.*


trait ExprMapTac[T, Entity](entity: DataModel => Entity) extends CardMap { self: Molecule  =>
  def apply(                           ) = entity(addMap  (dataModel, NoValue, Map.empty[String, T]))
  def apply(key : String, keys: String*) = entity(addMapKs(dataModel, Eq     , Seq(key) ++ keys    ))
  def apply(keys: Seq[String]          ) = entity(addMapKs(dataModel, Eq     , keys                ))
  def not  (key : String, keys: String*) = entity(addMapKs(dataModel, Neq    , Seq(key) ++ keys    ))
  def not  (keys: Seq[String]          ) = entity(addMapKs(dataModel, Neq    , keys                ))
  def has  (v : T, vs: T*              ) = entity(addMapVs(dataModel, Has    , Seq(v) ++ vs        ))
  def has  (vs: Seq[T]                 ) = entity(addMapVs(dataModel, Has    , vs                  ))
  def hasNo(v : T, vs: T*              ) = entity(addMapVs(dataModel, HasNo  , Seq(v) ++ vs        ))
  def hasNo(vs: Seq[T]                 ) = entity(addMapVs(dataModel, HasNo  , vs                  ))
}