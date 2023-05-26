package molecule.core.spi

case class TxReport(tx: Long, eids: List[Long]) {

  // Convenience method for common need
  def eid: Long = eids.head
}
