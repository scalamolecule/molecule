package molecule.sql.core.facade

import java.util.Date

/**
 * Facade to Datomic transaction report with convenience methods to access tx meta data.
 *
 * @param basisTbefore Datomic time point t on before Db
 * @param t            Transaction time t. Same as basis t on after Db
 * @param tx           Transaction entity id (Long).
 * @param txDate    Transaction instant (Date).
 * @param ids         List of affected entity ids from transaction
 * @param datoms       Tx report data, a List of [[molecule.datomic.base.api.Datom]]
 */
case class SqlTxReport(
  ids: List[Long]
) {

  /** Convenience method to get single affected entity id from transaction.
   *
   * Often useful when you know only one entity was affected:
   * {{{
   * for {
   *   benId <- Person.name("Ben").map(_.id)
   * } yield ()
   * }}}
   *
   * @return
   */
  lazy val id: Long = ids.head

//  override def toString = {
//    s"""DatomicTxReport {
//       |  dbBefore.t: $basisTbefore
//       |  dbAfter.t : $t
//       |  txData    : ${datoms.mkString(",\n              ")}
//       |  ids       : $ids
//       |}""".stripMargin
//  }
}
