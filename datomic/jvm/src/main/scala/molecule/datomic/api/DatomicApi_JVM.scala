package molecule.datomic.api

import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.subscription.TxReportWatcher


trait DatomicApi_JVM {

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
