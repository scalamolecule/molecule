package molecule.db.datalog.datomic.facade

import java.io.StringReader
import java.util.List as jList
import java.{lang as jl, util as ju}
import cats.effect.IO
import datomic.Util.readAll
import datomic.{Connection as DatomicConnection, Datom as _, *}
import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.DatomicProxy
import molecule.db.core.spi.{Conn, TxReport}
import molecule.db.core.util.Executor
import molecule.db.datalog.datomic.transaction.DatomicDataType_JVM
import molecule.db.datalog.datomic.util.MakeTxReport
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters.*
import scala.util.control.NonFatal

case class DatomicConn_JVM(
  override val proxy: DatomicProxy,
  peerConn: DatomicConnection
) extends Conn(proxy)
  with DatomicDataType_JVM
  with MoleculeLogging {

  override def db: Database = peerConn.db()

  private var optimizeQueries = true
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String)(implicit ec: ExecutionContext): Future[TxReport] = {
    transact_async(readAll(new StringReader(edn)).get(0).asInstanceOf[Data])
  }
  final def transactEdnIO(edn: String): IO[TxReport] = {
    IO.fromFuture(
      IO(
        transact_async(
          readAll(new StringReader(edn)).get(0).asInstanceOf[Data]
        )(Executor.global)
      )
    )
  }

  override def transact_async(javaStmts: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    bridgeDatomicFuture(peerConn.transactAsync(javaStmts))
      .map(MakeTxReport(_))
      .recover {
        case e: Throwable => throw e
      }
  }

  override def transact_sync(javaStmts: Data): TxReport = try {
    import Executor.*
    Await.result(transact_async(javaStmts), 10.seconds)
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }

  private def bridgeDatomicFuture[T](
    listenF: ListenableFuture[T],
    javaStmts: Option[jList[?]] = None
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

                      case _ =>
                        // e.printStackTrace()
                        ExecutionError(e.getMessage.trim)
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
}