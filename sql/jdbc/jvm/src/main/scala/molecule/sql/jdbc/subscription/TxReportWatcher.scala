package molecule.sql.jdbc.subscription

import java.lang.{Long => jLong}
import datomic.Connection.DB_AFTER
import datomic.Database
import molecule.base.error.ModelError
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.util.MakeJdbcTxReport
import scala.collection.mutable.ListBuffer


case class TxReportWatcher(conn: JdbcConn_jvm) extends Runnable {

//  private val blockingQueue = conn.txReportQueue.javaQueue
  private val dbCallbacks   = ListBuffer.empty[(List[jLong], Database => Unit)]

  def addSubscription(
    queryAttrIds: List[jLong],
    dbCallback: Database => Unit
  ): ListBuffer[(List[jLong], Database => Unit)] = {
    dbCallbacks += queryAttrIds -> dbCallback
  }

  override def run(): Unit = {

    ???

//    while (true) {
//      try {
//        //        println("Loop -----------------------------------------------------------------------")
//        // blocks until new data is transacted
//        val rawTxReport = blockingQueue.take
//        val dbAfter     = rawTxReport.get(DB_AFTER).asInstanceOf[datomic.Database]
//        val txReport    = MakeSqlTxReport(rawTxReport)
//        //        println(txReport)
//        val txAttrIds   = txReport.datoms.map(_.a)
//
//        dbCallbacks.foreach { case (queryAttrIds, dbCallback) =>
//          // todo: optimize by using bitwise comparison, one bit per attribute - if necessary?
//          if (txAttrIds.intersect(queryAttrIds).nonEmpty) {
//            //            println("--- Match --- " + dbAfter)
//            dbCallback(dbAfter)
//          }
//        }
//      } catch {
//        case e: InterruptedException =>
//          e.printStackTrace()
//          throw ModelError("Datomic transaction queue interrupted.")
//      }
//    }
  }
}
