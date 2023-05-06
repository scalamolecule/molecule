package molecule.sql.core.subscription

import molecule.sql.core.facade.SqlConn_JVM


trait SubscriptionStarter {

  private var txReportWatcher: Option[TxReportWatcher] = None

  def getWatcher(conn: SqlConn_JVM) = {
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
