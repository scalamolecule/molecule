package molecule.datomic.facade

import java.util.Date
import molecule.core.api.TxReport

/**
 * Facade to Datomic transaction report with convenience methods to access tx data.
 *
 * @param basisTbefore Datomic time point t on before Db
 * @param t            Transaction time t. Same as basis t on after Db
 * @param tx           Transaction entity id (Long).
 * @param txInstant    Transaction instant (Date).
 * @param eids         List of affected entity ids from transaction
 * @param txData       Tx report data, a List of [[molecule.datomic.base.api.Datom]]
 * @param basisTafter  Datomic time point t on after Db
 * @param tempIds      Temporary ids generated
 */
case class DatomicTxReport(
  basisTbefore: Long,
  t: Long,
  override val tx: Long,
  txInstant: Date,
  override val eids: List[Long],
  txData: List[Datom],
//  tempIds: List[Long]
) extends TxReport {

  /** Convenience method to get single affected entity id from transaction.
   *
   * Often useful when you know only one entity was affected:
   * {{{
   * for {
   *   benId <- Person.name("Ben").map(_.eid)
   * } yield ()
   * }}}
   *
   * @return
   */
  val eid: Long = eids.head

  override def toString = {
    s"""TxReport {
       |  dbBefore.t: $basisTbefore
       |  dbAfter.t : $t
       |  txData    : ${txData.mkString(",\n              ")}
       |  eids      : $eids
       |}""".stripMargin
//       |  tempIds   : $tempIds
  }
}
