package molecule.datomic.subscription

import molecule.datomic.facade.DatomicConn_JVM


trait SubscriptionStarter {

  private var txReportWatcher: Option[TxReportWatcher] = None

  def getWatcher(conn: DatomicConn_JVM) = {
    if (conn.fresh) {
      txReportWatcher = None
      conn.fresh = false
    }
    //    println(s"############ STARTING THREAD... " + txReportWatcher.toString.take(60))
    txReportWatcher.getOrElse {
      val watcher = TxReportWatcher(conn)
      txReportWatcher = Some(watcher)
      new Thread(watcher).start()
      watcher
    }
  }
}
