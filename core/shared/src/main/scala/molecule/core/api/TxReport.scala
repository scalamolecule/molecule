package molecule.core.api

case class TxReport(tx: Long, eids: List[Long]) {

  // Convenience method for common need
  def eid: Long = eids.head
}
