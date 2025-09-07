package molecule.db.common.spi

case class TxReport(ids: List[Long]) {

  // Convenience method for common need
  def id: Long = ids.head
}
