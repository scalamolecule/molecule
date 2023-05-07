package molecule.sql.jdbc.facade

import java.io.StringReader
import java.util.{List => jList}
import java.{sql, lang => jl, util => ju}
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.base.error._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.{DatomicPeerProxy, SqlProxy}
import molecule.sql.jdbc.transaction.JdbcDataType_JVM
import molecule.sql.jdbc.util.MakeTxReport
import scala.collection.mutable
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

case class JdbcConn_JVM(
  override val proxy: SqlProxy,
  sqlConn: sql.Connection
) extends Connection(proxy)
  with JdbcDataType_JVM
  with MoleculeLogging {

  private[molecule] var fresh = true

  override def db: Database = ??? //sqlConn.db()

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
    //    bridgeDatomicFuture(sqlConn.transactAsync(javaStmts))
    //      .map(MakeTxReport(_))
    //      .recover {
    //        case e: Throwable => throw e
    //      }
    ???
  }

  override def transact_sync(stmt: Data): TxReport = try {

    //    stmt.
    ???

  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }

  def close: Unit = sqlConn.close()


}