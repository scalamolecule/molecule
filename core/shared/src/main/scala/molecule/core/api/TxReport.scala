package molecule.core.api

trait TxReport {
  val tx  : Long
  val eids: List[Long]
}
