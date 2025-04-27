package molecule.db.datalog.datomic.util

import java.lang.Long as jLong
import java.util.{List as jList, Map as jMap}
import datomic.Connection.{DB_AFTER, TEMPIDS, TX_DATA}
import datomic.db.Datum as PeerDatom
import datomic.{Datom as _, *}
import molecule.core.spi.TxReport
import molecule.db.datalog
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.*

object MakeTxReport {

  def apply(rawTxReport: jMap[?, ?]): TxReport = {
    val dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]
    val t      : Long     = dbAfter.basisT
    val tx     : Long     = Peer.toTx(t).asInstanceOf[Long]

    val ids: List[Long] = {
      val allIds           = ListBuffer.empty[Long]
      val refs             = mutable.Set.empty[Long]
      val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
      val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[?, ?]].values().asScala.toBuffer
      var datom: PeerDatom = null
      var e                = 0L

      // Filter out tx meta data assertions
      while (datoms.hasNext) {
        datom = datoms.next
        e = datom.e().asInstanceOf[Long]
        if (datom.added() && tempIds.contains(e) && !refs.contains(e)) {
          allIds += e
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