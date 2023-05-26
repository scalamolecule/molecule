package molecule.datalog.datomic.util

import java.util.{Date, List => jList, Map => jMap}
import datomic.Connection.{DB_AFTER, DB_BEFORE, TEMPIDS, TX_DATA}
import datomic.db.{Datum => PeerDatom}
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.datalog.core.facade.{Datom, DatomicTxReport}
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

object MakeDatomicTxReport {

  def apply(rawTxReport: jMap[_, _]): DatomicTxReport = {
    /** Get database value before transaction. */
    val dbBefore: Database = rawTxReport.get(DB_BEFORE).asInstanceOf[Database]

    /** Get database value after transaction. */
    val dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]

    val basisTBefore: Long = dbBefore.basisT
    val t           : Long = dbAfter.basisT

    val tx: Long = Peer.toTx(t).asInstanceOf[Long]

    val txInstant: Date = {
      rawTxReport.get(DatomicConnection.TX_DATA).asInstanceOf[jList[PeerDatom]].get(0).v().asInstanceOf[Date]
    }

    lazy val txData: List[Datom] = {
      val list = List.newBuilder[Datom]
      rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].forEach { datom =>
        list += Datom(
          datom.e,
          datom.a.toString.toInt,
          datom.v.toString, // Only for inspection/debugging
          datom.tx.asInstanceOf[Long],
          datom.added,
        )
      }
      list.result()
    }

    val eids: List[Long] = {
      val allIds           = ListBuffer.empty[Long]
      val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
      val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[_, _]].values().asScala.toBuffer
      val tx               = datoms.next().e().asInstanceOf[Long] // Initial txInstant datom
      var txData       = false
      var datom: PeerDatom = null
      var e                = 0L
      // Filter out tx data assertions
      while (!txData && datoms.hasNext) {
        datom = datoms.next
        e = datom.e().asInstanceOf[Long]
        if (e == tx)
          txData = true
        if (
          !txData
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

    DatomicTxReport(basisTBefore, t, tx, txInstant, txData, eids)
  }
}