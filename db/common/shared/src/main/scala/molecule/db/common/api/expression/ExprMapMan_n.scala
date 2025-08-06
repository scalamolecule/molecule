package molecule.db.common.api.expression

import scala.Tuple.{:*, Init}
import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprMapMan_n[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends CardMap { self: Molecule  =>
  def apply (                                       ): Entity[Tpl           ] = entity[Tpl           ](addMap  (dataModel, NoValue, Map.empty[String, T] ))
  def apply (map  : Map[String, T]                  ): Entity[Tpl           ] = entity[Tpl           ](addMap  (dataModel, Eq     , map                  ))
  def apply (key  : String                          ): Entity[Init[Tpl] :* T] = entity[Init[Tpl] :* T](addMapKs(dataModel, Eq     , Seq(key)             ))
  def not   (key : String, keys: String*            ): Entity[Init[Tpl] :* T] = entity[Init[Tpl] :* T](addMapKs(dataModel, Neq    , Seq(key) ++ keys     ))
  def not   (keys: Seq[String]                      ): Entity[Init[Tpl] :* T] = entity[Init[Tpl] :* T](addMapKs(dataModel, Neq    , keys                 ))
  def has   (v : T, vs: T*                          ): Entity[Tpl           ] = entity[Tpl           ](addMapVs(dataModel, Has    , Seq(v) ++ vs         ))
  def has   (vs: Seq[T]                             ): Entity[Tpl           ] = entity[Tpl           ](addMapVs(dataModel, Has    , vs                   ))
  def hasNo (v : T, vs: T*                          ): Entity[Tpl           ] = entity[Tpl           ](addMapVs(dataModel, HasNo  , Seq(v) ++ vs         ))
  def hasNo (vs: Seq[T]                             ): Entity[Tpl           ] = entity[Tpl           ](addMapVs(dataModel, HasNo  , vs                   ))
  def add   (pair : (String, T), pairs: (String, T)*): Entity[Tpl           ] = entity[Tpl           ](addMap  (dataModel, Add    , (pair +: pairs).toMap))
  def add   (pairs: Seq[(String, T)]                ): Entity[Tpl           ] = entity[Tpl           ](addMap  (dataModel, Add    , pairs.toMap          ))
  def remove(key  : String, keys: String*           ): Entity[Init[Tpl] :* T] = entity[Init[Tpl] :* T](addMapKs(dataModel, Remove , Seq(key) ++ keys     ))
  def remove(keys : Seq[String]                     ): Entity[Init[Tpl] :* T] = entity[Init[Tpl] :* T](addMapKs(dataModel, Remove , keys                 ))
}