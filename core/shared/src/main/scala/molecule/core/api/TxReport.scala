package molecule.core.api

trait TxReport {

  def tx: Long
  def eids: List[Long]
}
