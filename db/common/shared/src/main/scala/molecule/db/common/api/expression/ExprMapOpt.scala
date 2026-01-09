package molecule.db.common.api.expression

import scala.Tuple.{:*, Init}
import molecule.core.dataModel.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*

trait ExprMapOpt_1[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends MapValue { self: Molecule  =>
  def apply(map: Option[Map[String, T]]): Entity[Option[Map[String, T]]] = entity[Option[Map[String, T]]](addMapOpt(dataModel, Eq , map     ))
  def apply(key: String                ): Entity[Option[T             ]] = entity[Option[T             ]](addMapKs (dataModel, Has, Seq(key)))
}

trait ExprMapOpt_n[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends MapValue { self: Molecule  =>
  def apply(map: Option[Map[String, T]]): Entity[Tpl                   ] = entity[Tpl                   ](addMapOpt(dataModel, Eq , map     ))
  def apply(key: String                ): Entity[Init[Tpl] :* Option[T]] = entity[Init[Tpl] :* Option[T]](addMapKs (dataModel, Has, Seq(key)))
}