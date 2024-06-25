package molecule.core.spi

case class TxReport(ids: List[Long], tx: Long = 0L) {

  // Convenience method for common need
  def id: Long = ids.head
}
