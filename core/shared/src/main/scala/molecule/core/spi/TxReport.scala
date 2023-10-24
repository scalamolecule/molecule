package molecule.core.spi

case class TxReport(ids: List[String], tx: Long = 0L) {

  // Convenience method for common need
  def id: String = ids.head
}
