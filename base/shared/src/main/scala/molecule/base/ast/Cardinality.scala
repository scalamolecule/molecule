package molecule.base.ast


sealed trait Card {
  def _marker: String
  def _tpe: String
}

trait CardOne extends Card {
  override def _marker = "One"
  override def _tpe = ""
}
case object CardOne extends CardOne

trait CardSet extends Card {
  override def _marker = "Set"
  override def _tpe = "Set"
}
case object CardSet extends CardSet

trait CardSeq extends Card {
  override def _marker = "Seq"
  override def _tpe = "Seq"
}
case object CardSeq extends CardSeq

trait CardMap extends Card {
  override def _marker = "Map"
  override def _tpe = "Map"
}
case object CardMap extends CardMap
