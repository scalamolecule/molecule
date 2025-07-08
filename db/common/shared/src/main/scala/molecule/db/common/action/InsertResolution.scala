package molecule.db.common.action

import molecule.core.dataModel.DataModel
import scala.concurrent.{ExecutionContext, Future}

case class Insert_1[T](dataModel: DataModel) {
  final def apply(a: T, as: T*) = Insert(dataModel, (a +: as).map(a => Tuple1(a)))
  final def apply(tpls: Seq[T]) = Insert(dataModel, tpls.map(a => Tuple1(a)))
}


case class Insert_n[Tpl <: Tuple](dataModel: DataModel) {
  final def apply(a: Tpl, as: Tpl*) = Insert(dataModel, a +: as)
  final def apply(tpls: Seq[Tpl]) = Insert(dataModel, tpls)
}