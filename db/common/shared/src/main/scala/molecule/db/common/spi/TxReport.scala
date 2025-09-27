package molecule.db.common.spi

case class TxReport(ids: List[Long]) {

  // Convenience method for common need
  lazy val id: Long = ids.head
}
