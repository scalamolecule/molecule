package molecule.datomic.facade

import java.io.StringReader
import java.util.{Date, List => jList}
import java.{lang => jl, util => ju}
import com.google.common.util.concurrent.UncheckedExecutionException
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.base.error._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.marshalling.dbView.{AsOf, TxLong}
import molecule.datomic.transaction.DatomicDataType_JVM
import molecule.datomic.util.MakeTxReport
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

  private[molecule] var fresh = true

  override def db: Database = peerConn.db()

  private var optimizeQueries = true
  //  private var optimizeQueries = false
  def setOptimizeQuery(flag: Boolean): Unit = {
    optimizeQueries = flag
  }
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String)(implicit ec: ExecutionContext): Future[TxReport] = {
    transact_async(readAll(new StringReader(edn)).get(0).asInstanceOf[Data])
  }

  override def transact_async(javaStmts: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    bridgeDatomicFuture(peerConn.transactAsync(javaStmts))
      .map(MakeTxReport(_))
      .recover {
        case e: Throwable => throw e
      }
  }

  override def transact_sync(javaStmts: Data): TxReport = try {
    import molecule.core.util.Executor._
    Await.result(transact_async(javaStmts), 10.seconds)
  } catch {
    case t: Throwable => throw ModelError(t.toString)
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
              logger.debug(
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
                  case e                =>
                    e.getMessage.trim match {
                      case ":db.error/reset-tx-instant You can set :db/txInstant only on the current transaction." =>
                        ExecutionError("Can't delete transaction id.")

                      case _ => ExecutionError(e.getMessage.trim)
                    }
                }
              )

            case NonFatal(e) =>
              logger.error(
                "---- NonFatal exception: -------------\n" +
                  listenF +
                  javaStmts.fold("")(stmts => "\n---- javaStmts: ----\n" +
                    stmts.asScala.toList.mkString("\n"))
              )
              p.failure(ExecutionError(e.getMessage))
          }
        }
      },
      (arg0: Runnable) => ec.execute(arg0)
    )
    p.future
  }


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
   * Sometimes (!) sbt needs to be restarted if this exception is thrown:
   * java.lang.ClassCastException: datomic.extensions$eval19 cannot be cast to clojure.lang.IFn
   *
   * I'm quessing this is a concurrency issue:
   *
   * @see https://clojurians-log.clojureverse.org/luminus/2022-02-11
   * @return TxReportQueue
   */
  final lazy val txReportQueue: DatomicTxReportQueue = {
    // Get attribute ids for masking tx report datoms that match query attributes
    // todo: find attribute ids once and cache instead of querying on each subscription
    try {
      if (attrIds.isEmpty) {
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
      }
      DatomicTxReportQueue(peerConn.txReportQueue())
    } catch {
      case e: UncheckedExecutionException =>
        println(
          """-------------
            |When the http server is terminated with a keyboard stroke and
            |subscription molecule on client side is called, this error occurs.
            |Then please ctrl-c the sbt process and restart the server.
            |TODO: Either skip system.terminated() or find some setting with
            |Akka Http to avoid this.
            |-------------""".stripMargin
        )
        e.printStackTrace()
        throw e
      case e: Throwable                   =>
        e.printStackTrace()
        throw e
    }
  }
}