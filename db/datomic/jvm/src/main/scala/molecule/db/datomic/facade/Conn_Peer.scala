package molecule.db.datomic.facade

import java.io.StringReader
import java.util.{List => jList}
import java.{lang => jl, util => ju}
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, _}
import molecule.base.api.SchemaTransaction
import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, TxReport}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal


class Conn_Peer(
  override val schema: SchemaTransaction,
  val peerConn: DatomicConnection,
  val isFreeVersion: Boolean
) extends Connection(schema) {

  type Data = jList[_]

  override def db: Database = peerConn.db()

  private var optimizeQueries = true
  def setOptimizeQuery(flag: Boolean): Unit = {
    optimizeQueries = flag
  }
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String): TxReport =
    transact(readAll(new StringReader(edn)).get(0).asInstanceOf[jList[_]])

  override def transact(javaStmts: Data): TxReport = {
    Await.result(
      bridgeDatomicFuture(peerConn.transactAsync(javaStmts)).map(TxReport_Peer),
      5.seconds
    )
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

  //  override def transact[Data](data: Data): TxReport = ???
}


object Conn_Peer {
  def apply(
    schema: SchemaTransaction,
    uri: String,
    isFreeVersion: Boolean
  ): Conn_Peer =
    new Conn_Peer(schema, datomic.Peer.connect(uri), isFreeVersion)

  def apply(
    schema: SchemaTransaction,
    peerConn: DatomicConnection,
    isFreeVersion: Boolean
  ): Conn_Peer =
    new Conn_Peer(schema, peerConn, isFreeVersion)
}