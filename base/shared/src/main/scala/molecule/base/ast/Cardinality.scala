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

//  trait CardArr extends Card {
//    override def marker = "Arr"
//    override def tpe = "Array"
//  }
//  case object CardArr extends CardMap
//
//  trait CardMap extends Card {
//    override def marker = "Map"
//    override def tpe = "Map"
//  }
//  case object CardMap extends CardMap
