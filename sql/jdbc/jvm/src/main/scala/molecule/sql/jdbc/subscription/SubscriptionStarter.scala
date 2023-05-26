package molecule.sql.jdbc.subscription

import molecule.sql.jdbc.facade.JdbcConn_jvm


trait SubscriptionStarter {

  private var txReportWatcher: Option[TxReportWatcher] = None

  def getWatcher(conn: JdbcConn_jvm) = {
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
