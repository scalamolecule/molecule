package molecule.base.metaModel

sealed trait Cardinality {
  def _marker: String
  def _tpe: String
}

trait CardOne extends Cardinality {
  override def _marker = "One"
  override def _tpe = ""
}
case object CardOne extends CardOne

trait CardSet extends Cardinality {
  override def _marker = "Set"
  override def _tpe = "Set"
}
case object CardSet extends CardSet

trait CardSeq extends Cardinality {
  override def _marker = "Seq"
  override def _tpe = "Seq"
}
case object CardSeq extends CardSeq

trait CardMap extends Cardinality {
  override def _marker = "Map"
  override def _tpe = "Map"
}
case object CardMap extends CardMap
