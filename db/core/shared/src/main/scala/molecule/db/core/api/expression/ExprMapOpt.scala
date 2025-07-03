package molecule.db.core.api.expression

import molecule.base.metaModel.CardMap
import molecule.core.dataModel.*
import molecule.db.core.api.*
import molecule.db.core.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprMapOpt_1[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends CardMap { self: Molecule  =>
  def apply(map: Option[Map[String, T]]): Entity[Map[String, T]] = entity[Map[String, T]](addMapOpt(dataModel, Eq , map     ))
  def apply(key: String                ): Entity[Option[T]     ] = entity[Option[T]     ](addMapKs (dataModel, Has, Seq(key)))
}

trait ExprMapOpt_n[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) extends CardMap { self: Molecule  =>
  def apply(map: Option[Map[String, T]]): Entity[Tpl                   ] = entity[Tpl                   ](addMapOpt(dataModel, Eq , map     ))
  def apply(key: String                ): Entity[Init[Tpl] :* Option[T]] = entity[Init[Tpl] :* Option[T]](addMapKs (dataModel, Has, Seq(key)))
}