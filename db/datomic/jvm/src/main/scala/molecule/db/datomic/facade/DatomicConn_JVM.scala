package molecule.db.datomic.facade

import java.io.StringReader
import java.util.{List => jList}
import java.{lang => jl, util => ju}
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, _}
import molecule.base.api.SchemaTransaction
import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.transaction.DatomicDataType_JVM
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal


class DatomicConn_JVM(
  override val schema: SchemaTransaction,
  val peerConn: DatomicConnection,
  val isFreeVersion: Boolean
) extends Connection(schema) with DatomicDataType_JVM {

  override def db: Database = peerConn.db()

  private var optimizeQueries = true
  def setOptimizeQuery(flag: Boolean): Unit = {
    optimizeQueries = flag
  }
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String)(implicit ec: ExecutionContext): Future[TxReport] =
    transact(readAll(new StringReader(edn)).get(0).asInstanceOf[Data])

  override def transact(javaStmts: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    bridgeDatomicFuture(peerConn.transactAsync(javaStmts)).map(DatomicTxReport)
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
              println(
                "---- ExecutionException: -------------\n" +
                  listenF +
                  javaStmts.fold("")(stmts => "\n---- javaStmts: ----\n" +
                    stmts.asScala.toList.mkString("\n"))
              )
              // White list of exceptions that can be pickled by BooPickle
              p.failure(
                e.getCause match {
                  //                  case e: TxFnException     => e
                  case e: MoleculeException => e
                  case e                    => MoleculeException(e.getMessage.trim)
                }
              )

            case NonFatal(e) =>
              println(
                "---- NonFatal exception: -------------\n" +
                  listenF +
                  javaStmts.fold("")(stmts => "\n---- javaStmts: ----\n" +
                    stmts.asScala.toList.mkString("\n"))
              )
              p.failure(MoleculeException(e.getMessage))
          }
        }
      },
      (arg0: Runnable) => ec.execute(arg0)
    )
    p.future
  }
}


object DatomicConn_JVM {
  def apply(
    schema: SchemaTransaction,
    uri: String,
    isFreeVersion: Boolean
  ): DatomicConn_JVM =
    new DatomicConn_JVM(schema, datomic.Peer.connect(uri), isFreeVersion)

  def apply(
    schema: SchemaTransaction,
    peerConn: DatomicConnection,
    isFreeVersion: Boolean
  ): DatomicConn_JVM =
    new DatomicConn_JVM(schema, peerConn, isFreeVersion)
}