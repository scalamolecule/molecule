package molecule.datomic.facade

import java.io.StringReader
import java.util.{Date, List => jList}
import java.{lang => jl, util => ju}
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.DatomicPeerProxy
import molecule.datomic.transaction.DatomicDataType_JVM
import molecule.datomic.util.MakeDatomicTxReport
import scala.collection.mutable
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

case class DatomicConn_JVM(
  override val proxy: DatomicPeerProxy,
  peerConn: DatomicConnection,
  isFreeVersion: Boolean
) extends Connection(proxy)
  with DatomicDataType_JVM
  with MoleculeLogging {

  override def db: Database = peerConn.db()

  private var optimizeQueries = true
  def setOptimizeQuery(flag: Boolean): Unit = {
    optimizeQueries = flag
  }
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String)(implicit ec: ExecutionContext): Future[TxReport] =
    transact_async(readAll(new StringReader(edn)).get(0).asInstanceOf[Data])

  override def transact_async(javaStmts: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    bridgeDatomicFuture(peerConn.transactAsync(javaStmts)).map(MakeDatomicTxReport(_))
  }
  override def transact_sync(javaStmts: Data): TxReport = try {
    import molecule.core.util.Executor._
    Await.result(transact_async(javaStmts), 10.seconds)
  } catch {
    case t: Throwable => throw MoleculeError(t.toString)
  }

  private def bridgeDatomicFuture[T](
    listenF: ListenableFuture[T],
    javaStmts: Option[jList[_]] = None
  )(implicit ec: ExecutionContext): Future[T] = {
    val p = Promise[T]()
    listenF.addListener(
      new jl.Runnable {
        override def run: Unit = {
          try {
            p.success(listenF.get())
          } catch {
            case e: ju.concurrent.ExecutionException =>
              logger.error(
                "---- ExecutionException: -------------\n" +
                  listenF +
                  javaStmts.fold("")(stmts => "\n---- javaStmts: ----\n" +
                    stmts.asScala.toList.mkString("\n"))
              )
              // White list of exceptions that can be pickled by BooPickle
              p.failure(
                e.getCause match {
                  //                  case e: TxFnException     => e
                  case e: MoleculeError => e
                  case e                => MoleculeError(e.getMessage.trim)
                }
              )

            case NonFatal(e) =>
              logger.error(
                "---- NonFatal exception: -------------\n" +
                  listenF +
                  javaStmts.fold("")(stmts => "\n---- javaStmts: ----\n" +
                    stmts.asScala.toList.mkString("\n"))
              )
              p.failure(MoleculeError(e.getMessage))
          }
        }
      },
      (arg0: Runnable) => ec.execute(arg0)
    )
    p.future
  }


  //  final def sync: Conn = usingAdhocDbView(Sync(0))
  //  final def sync(t: Long): Conn = usingAdhocDbView(Sync(t))

  /**
   * Request that a background indexing job begin immediately.
   *
   * Background indexing will happen asynchronously. You can track indexing
   * completion with syncIndex(long).
   */
  final def requestIndex: Boolean =
    peerConn.requestIndex()
  //
  //  /** Synchronize Peer database to have been indexed through time t.
  //   *
  //   * (only implemented for Peer api)
  //   *
  //   * Sets a flag with a time t on the connection to do the synchronization
  //   * on the first subsequent query. Hereafter the flag is removed.
  //   *
  //   * The synchronization guarantees a database that has been indexed through time t.
  //   * The synchronization does not involve calling the transactor.
  //   *
  //   * A Future with the synchronized database is returned for the query to use. The future
  //   * can take arbitrarily long to complete. Waiting code should specify a timeout.
  //   *
  //   * Only use `syncIndex` when coordination of multiple peer/client processes is required.
  //   *
  //   * @param ec an implicit execution context.
  //   * @return Peer Connection with synchronization flag set
  //   */
  //  final def syncIndex(t: Long): DatomicConn_JVM =
  //    usingAdhocDbView(SyncIndex(t)).asInstanceOf[DatomicConn_JVM]
  //
  //  /** Synchronize Peer database to be aware of all schema changes up to time t.
  //   *
  //   * (only implemented for Peer api)
  //   *
  //   * Sets a flag with a time t on the connection to do the synchronization
  //   * on the first subsequent query. Hereafter the flag is removed.
  //   *
  //   * The synchronization guarantees a database aware of all schema changes up to
  //   * time t. The synchronization does not involve calling the transactor.
  //   *
  //   * A Future with the synchronized database is returned for the query to use. The future
  //   * can take arbitrarily long to complete. Waiting code should specify a timeout.
  //   *
  //   * Only use `syncSchema` when coordination of multiple peer/client processes is required.
  //   *
  //   * @param ec an implicit execution context.
  //   * @return Connection with synchronization flag set
  //   */
  //  final def syncSchema(t: Long): DatomicConn_JVM =
  //    usingAdhocDbView(SyncSchema(t)).asInstanceOf[DatomicConn_JVM]
  //
  //  /** Synchronize Peer database to be aware of all excisions up to time t.
  //   *
  //   * (only implemented for Peer api)
  //   *
  //   * Sets a flag with a time t on the connection to do the synchronization
  //   * on the first subsequent query. Hereafter the flag is removed.
  //   *
  //   * The synchronization guarantees a database aware of all excisions up to a time t.
  //   * The synchronization does not involve calling the transactor.
  //   *
  //   * A Future with the synchronized database is returned for the query to use. The future
  //   * can take arbitrarily long to complete. Waiting code should specify a timeout.
  //   *
  //   * Only use `syncSchema` when coordination of multiple peer/client processes is required.
  //   *
  //   * @param ec an implicit execution context.
  //   * @return Connection with synchronization flag set
  //   */
  //  final def syncExcise(t: Long): DatomicConn_JVM =
  //    usingAdhocDbView(SyncExcise(t)).asInstanceOf[DatomicConn_JVM]


  private[molecule] val attrIds = mutable.Map.empty[String, java.lang.Long]

  /**
   * Gets the single transaction report queue associated with this connection,
   * creating it if necessary.
   *
   * The transaction report queue receives reports from all transactions in the
   * system. Objects on the queue have the same keys as returned by
   * transact(java.util.List).
   *
   * The returned queue may be consumed from more than one thread.
   *
   * Note that the returned queue does not block producers, and will consume
   * memory until you consume the elements from it. Reports will be added to the
   * queue at some point after the db has been updated.
   *
   * If this connection originated the transaction, the transaction future will
   * be notified first, before a report is placed on the queue.
   *
   * @return TxReportQueue
   */
  final lazy val txReportQueue: DatomicTxReportQueue = {
    // Get attribute ids for masking tx report datoms that match query attributes
    Peer.q(
      """[:find  ?nsFull ?attr ?attrId
        | :where [_ :db.install/attribute ?attrId ?tx]
        |        [?attrId :db/ident ?attrIdent]
        |        [(namespace ?attrIdent) ?nsFull]
        |        [(.matches ^String ?nsFull "(db|db.alter|db.excise|db.install|db.part|db.sys|fressian|db.entity|db.attr|-.*)") ?sys]
        |        [(= ?sys false)]
        |        [(name ?attrIdent) ?attr]
        |]""".stripMargin,
      peerConn.db
    ).forEach { row =>
      attrIds += (row.get(0).toString + "." + row.get(1).toString -> row.get(2).asInstanceOf[java.lang.Long])
    }
    DatomicTxReportQueue(peerConn.txReportQueue())
  }

  /**
   * Removes the tx report queue associated with this connection.
   */
  final def removeTxReportQueue(): Unit =
    peerConn.removeTxReportQueue()

  /**
   * Reclaim storage garbage older than a certain age.
   *
   * As part of capacity planning for a Datomic system, you should schedule
   * regular (e.g daily, weekly) calls to gcStorage.
   *
   * @param olderThan
   */
  final def gcStorage(olderThan: Date): Unit =
    peerConn.gcStorage(olderThan)

  /**
   * Request the release of resources associated with this connection.
   *
   * Method returns immediately, resources will be released asynchronously.
   *
   * This method should only be called when the entire process is no longer
   * interested in the connection.
   *
   * Note that Datomic connections do not adhere to an acquire/use/release pattern.
   * They are thread-safe, cached, and long lived. Many processes (e.g. application
   * servers) will never call release.
   */
  final def release(): Unit =
    peerConn.release()
}


object DatomicConn_JVM {
  def apply(
    proxy: DatomicPeerProxy,
    uri: String,
    isFreeVersion: Boolean
  ): DatomicConn_JVM =
    DatomicConn_JVM(proxy, datomic.Peer.connect(uri), isFreeVersion)
}