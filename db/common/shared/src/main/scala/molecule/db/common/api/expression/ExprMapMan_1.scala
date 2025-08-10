package molecule.db.common.api.expression

import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprMapMan_1[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends MapValue { self: Molecule  =>
  def apply (                                       ): Entity[Map[String, T]] = entity[Map[String, T]](addMap  (dataModel, NoValue, Map.empty[String, T] ))
  def apply (map  : Map[String, T]                  ): Entity[Map[String, T]] = entity[Map[String, T]](addMap  (dataModel, Eq     , map                  ))
  def apply (key  : String                          ): Entity[T             ] = entity[T             ](addMapKs(dataModel, Eq     , Seq(key)             ))
  def not   (key : String, keys: String*            ): Entity[T             ] = entity[T             ](addMapKs(dataModel, Neq    , Seq(key) ++ keys     ))
  def not   (keys: Seq[String]                      ): Entity[T             ] = entity[T             ](addMapKs(dataModel, Neq    , keys                 ))
  def has   (v : T, vs: T*                          ): Entity[Map[String, T]] = entity[Map[String, T]](addMapVs(dataModel, Has    , Seq(v) ++ vs         ))
  def has   (vs: Seq[T]                             ): Entity[Map[String, T]] = entity[Map[String, T]](addMapVs(dataModel, Has    , vs                   ))
  def hasNo (v : T, vs: T*                          ): Entity[Map[String, T]] = entity[Map[String, T]](addMapVs(dataModel, HasNo  , Seq(v) ++ vs         ))
  def hasNo (vs: Seq[T]                             ): Entity[Map[String, T]] = entity[Map[String, T]](addMapVs(dataModel, HasNo  , vs                   ))
  def add   (pair : (String, T), pairs: (String, T)*): Entity[Map[String, T]] = entity[Map[String, T]](addMap  (dataModel, Add    , (pair +: pairs).toMap))
  def add   (pairs: Seq[(String, T)]                ): Entity[Map[String, T]] = entity[Map[String, T]](addMap  (dataModel, Add    , pairs.toMap          ))
  def remove(key  : String, keys: String*           ): Entity[T             ] = entity[T             ](addMapKs(dataModel, Remove , Seq(key) ++ keys     ))
  def remove(keys : Seq[String]                     ): Entity[T             ] = entity[T             ](addMapKs(dataModel, Remove , keys                 ))
}