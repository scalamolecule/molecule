package molecule.db.datomic.facade

import java.util.{Date, List => jList, Map => jMap}
import datomic.Connection.{DB_AFTER, DB_BEFORE, TEMPIDS, TX_DATA}
import datomic.db.{Datum => PeerDatom}
import datomic.{Datom => _, _}
import molecule.core.api.TxReport
import molecule.core.util.JavaConversions

import scala.collection.mutable.ListBuffer

/** Datomic TxReport facade for peer api.
 *
 * @param rawTxReport
 * @param scalaStmts
 */
case class TxReport_Peer(rawTxReport: jMap[_, _]) extends TxReport with JavaConversions {

  override def eids: List[Long] = {
    // Fast lookups with mutable Buffers
    // https://www.lihaoyi.com/post/BenchmarkingScalaCollections.html#lookup-performance

    val allIds           = ListBuffer.empty[Long]
    val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
    val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[_, _]].values().asScala.toBuffer
    val tx               = datoms.next().e().asInstanceOf[Long] // Initial txInstant datom
    var txMetaData       = false
    var datom: PeerDatom = null
    var e                = 0L
    // Filter out tx meta data assertions
    while (!txMetaData && datoms.hasNext) {
      datom = datoms.next
      e = datom.e().asInstanceOf[Long]
      if (e == tx)
        txMetaData = true
      if (
        !txMetaData
          && datom.added()
          && !allIds.contains(e)
      ) {
        if (tempIds.contains(e)) {
          allIds += e
        }
      }
    }
    allIds.toList
  }
}
