package molecule.db.datomic.facade

import java.io.StringReader
import java.util.{Date, List => jList, Map => jMap}
import java.{lang => jl, util => ju}
import datomic.Connection.{DB_AFTER, DB_BEFORE, TEMPIDS, TX_DATA}
import datomic.Util.readAll
import datomic.db.{Datum => PeerDatom}
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.{DatomicPeerProxy, MoleculeRpc}
import molecule.db.datomic.marshalling.DatomicRpcJVM
import molecule.db.datomic.transaction.DatomicDataType_JVM
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

case class DatomicConn_JVM(
  override val proxy: DatomicPeerProxy,
  peerConn: DatomicConnection,
  isFreeVersion: Boolean
) extends Connection(proxy) with DatomicDataType_JVM {

  override def db: Database = peerConn.db()

  private[molecule] final override lazy val rpc: MoleculeRpc = DatomicRpcJVM

  private var optimizeQueries = true
  def setOptimizeQuery(flag: Boolean): Unit = {
    optimizeQueries = flag
  }
  def optimizeQuery: Boolean = optimizeQueries

  final def transactEdn(edn: String)(implicit ec: ExecutionContext): Future[TxReport] =
    transact(readAll(new StringReader(edn)).get(0).asInstanceOf[Data])

  override def transact(javaStmts: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    bridgeDatomicFuture(peerConn.transactAsync(javaStmts)).map(txReport)
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


  private def txReport(rawTxReport: jMap[_, _]) = {
    def t: Long = dbAfter.basisT

    def tx: Long = Peer.toTx(t).asInstanceOf[Long]

    def txInstant: Date = {
      rawTxReport.get(DatomicConnection.TX_DATA).asInstanceOf[jList[PeerDatom]].get(0).v().asInstanceOf[Date]
    }

    def eids: List[Long] = {
      // Fast lookups with mutable Buffers
      // https://www.lihaoyi.com/post/BenchmarkingScalaCollections.html#lookup-performance

      val allIds           = ListBuffer.empty[Long]
      val datoms           = rawTxReport.get(TX_DATA).asInstanceOf[jList[PeerDatom]].iterator
      val tempIds          = rawTxReport.get(TEMPIDS).asInstanceOf[jMap[_, _]].values().asScala.toBuffer
      val tx               = datoms.next().e().asInstanceOf[Long] // Initial txInstant datom
      var txMetaData       = false
      var datom: PeerDatom = null
      var e                = 0L
      // Filter out tx meta data assertions
      while (!txMetaData && datoms.hasNext) {
        datom = datoms.next
        e = datom.e().asInstanceOf[Long]
        if (e == tx)
          txMetaData = true
        if (
          !txMetaData
            && datom.added()
            && !allIds.contains(e)
        ) {
          if (tempIds.contains(e)) {
            allIds += e
          }
        }
      }
      allIds.toList
    }

    /** Get database value before transaction. */
    def dbBefore: Database = rawTxReport.get(DB_BEFORE).asInstanceOf[Database]

    /** Get database value after transaction. */
    def dbAfter: Database = rawTxReport.get(DB_AFTER).asInstanceOf[Database]

    TxReport(tx, eids)
  }
}


object DatomicConn_JVM {
  def apply(
    proxy: DatomicPeerProxy,
    uri: String,
    isFreeVersion: Boolean
  ): DatomicConn_JVM =
    DatomicConn_JVM(proxy, datomic.Peer.connect(uri), isFreeVersion)
}