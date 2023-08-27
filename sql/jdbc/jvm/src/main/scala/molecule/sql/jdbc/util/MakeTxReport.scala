package molecule.sql.jdbc.util

import java.util.{Map => jMap}
import molecule.core.spi.TxReport
import scala.collection.mutable.ListBuffer

object MakeTxReport {

  def apply(rawTxReport: jMap[_, _]): TxReport = {
//    val dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]
//    val t      : Long     = dbAfter.basisT
//    val tx     : Long     = Peer.toTx(t).asInstanceOf[Long]
    val tx     : Long     = 42L

    val ids: List[Long] = {
      val allIds           = ListBuffer.empty[Long]
//      val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
//      val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[_, _]].values().asScala.toBuffer
//      val tx               = datoms.next().e().asInstanceOf[Long] // Initial txInstant datom
//      var txData           = false
//      var datom: PeerDatom = null
//      var e                = 0L
//      // Filter out tx meta data assertions
//      while (!txData && datoms.hasNext) {
//        datom = datoms.next
//        e = datom.e().asInstanceOf[Long]
//        if (e == tx)
//          txData = true
//        if (
//          !txData
//            && datom.added()
//            && !allIds.contains(e)
//        ) {
//          if (tempIds.contains(e)) {
//            allIds += e
//          }
//        }
//      }
      allIds.toList
    }

    TxReport(tx, ids)
  }
}