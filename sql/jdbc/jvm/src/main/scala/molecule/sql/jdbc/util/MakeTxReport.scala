package molecule.sql.jdbc.util

import java.util.{List => jList, Map => jMap}
import datomic.Connection.{DB_AFTER, TEMPIDS, TX_DATA}
import datomic.db.{Datum => PeerDatom}
import datomic.{Datom => _, _}
import molecule.core.api.TxReport
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

object MakeTxReport {

  def apply(rawTxReport: jMap[_, _]): TxReport = {
    val dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]
    val t      : Long     = dbAfter.basisT
    val tx     : Long     = Peer.toTx(t).asInstanceOf[Long]

    val eids: List[Long] = {
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

    TxReport(tx, eids)
  }
}