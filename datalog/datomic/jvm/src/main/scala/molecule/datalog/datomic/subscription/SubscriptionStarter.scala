package molecule.datalog.datomic.subscription

import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.subscription

trait SubscriptionStarter {

  private var txReportWatcher: Option[TxReportWatcher] = None

  def getWatcher(conn: DatomicConn_JVM): TxReportWatcher = {
    if (conn.fresh) {
      txReportWatcher = None
      conn.fresh = false
    }
    // println(s"############ STARTING THREAD... " + txReportWatcher.toString.take(60))
    txReportWatcher.getOrElse {
      val watcher = subscription.TxReportWatcher(conn)
      txReportWatcher = Some(watcher)
      new Thread(watcher).start()
      watcher
    }
  }
}
