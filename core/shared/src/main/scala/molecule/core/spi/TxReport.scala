package molecule.core.spi

case class TxReport(tx: Long, ids: List[Long]) {

  // Convenience method for common need
  def id: Long = ids.head
}
