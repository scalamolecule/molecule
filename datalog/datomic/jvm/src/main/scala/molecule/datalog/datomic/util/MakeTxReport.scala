package molecule.datalog.datomic.util

import java.lang.{Long => jLong}
import java.util.{List => jList, Map => jMap}
import datomic.Connection.{DB_AFTER, TEMPIDS, TX_DATA}
import datomic.db.{Datum => PeerDatom}
import datomic.{Datom => _, _}
import molecule.core.spi.TxReport
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

object MakeTxReport {

  def apply(rawTxReport: jMap[_, _]): TxReport = {
    val dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]
    val t      : Long     = dbAfter.basisT
    val tx     : Long     = Peer.toTx(t).asInstanceOf[Long]

    val ids: List[String] = {
      val allIds           = ListBuffer.empty[String]
      val refs             = mutable.Set.empty[Long]
      val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
      val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[_, _]].values().asScala.toBuffer
      var datom: PeerDatom = null
      var e                = 0L

      // Filter out tx meta data assertions
      while (datoms.hasNext) {
        datom = datoms.next
        e = datom.e().asInstanceOf[Long]
        val eStr = e.toString
        if (datom.added() && tempIds.contains(e) && !refs.contains(e)) {
          allIds += eStr
        }
        datom.v() match {
          case likelyRef: jLong =>
            if (likelyRef > 17592186000000L) {
              refs += likelyRef
            }
          case _                => ()
        }
      }
      allIds.toList.distinct
    }

    TxReport(ids.distinct, tx)
  }
}