package molecule.db.sqlite.spi

import java.sql.{DriverManager, Statement, PreparedStatement as PS}
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import molecule.core.dataModel.Element
import molecule.db.common.crud.{Delete, Insert, Save, Update}
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.common.spi.{Conn, SpiBaseJVM_sync, TxReport}
import molecule.db.common.transaction.*
import molecule.db.common.util.Executor.*
import molecule.db.sqlite.facade.JdbcHandlerSQlite_JVM
import molecule.db.sqlite.query.Model2SqlQuery_sqlite
import molecule.db.sqlite.transaction.{Insert_sqlite, Save_sqlite, Update_sqlite}

object Spi_sqlite_sync extends Spi_sqlite_sync

trait Spi_sqlite_sync extends SpiBaseJVM_sync {

  override def getResolveSave(save: Save, conn: JdbcConn_JVM) =
    new ResolveSave with Save_sqlite {}

  override def getResolveInsert(insert: Insert, conn: JdbcConn_JVM) =
    new ResolveInsert with Insert_sqlite {}

  override def getResolveUpdate(update: Update, conn: JdbcConn_JVM) =
    new ResolveUpdate(update.isUpsert) with Update_sqlite {}


  // Util --------------------------------------

  override def getIdsAndClose(
    ps: PS, conn: JdbcConn_JVM, table: String,
  ): List[Long] = {
    val sqlConn = conn.sqlConn
    val getPrevId = sqlConn.prepareStatement(
      s"select max(id) from $table"
    ).executeQuery()
    getPrevId.next()
    val prevId = getPrevId.getLong(1)
    getPrevId.close()

    // Execute incoming batch of prepared statements
    ps.executeBatch()
    ps.close()

    val getNewIds = sqlConn.prepareStatement(
      s"select id from $table where id > $prevId order by id asc"
    ).executeQuery()

    val ids = ListBuffer.empty[Long]
    while (getNewIds.next()) {
      ids += getNewIds.getLong(1)
    }
    getNewIds.close()

    ids.toList
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    validateUpdateSet_sqlite(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_sqlite(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(
    proxy0: ConnProxy
  ): Future[JdbcConn_JVM] = Future {
    val proxy   = proxy0.asInstanceOf[JdbcProxy]
    val sqlConn = DriverManager.getConnection(proxy.url)
    JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
  }

  override def fallback_rawTransact(
    stmt: String,
    doPrint: Boolean = false
  )(using conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]

    // Check rawTransact access based on authenticated role
    checkRawTransactAccess(conn)

    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(stmt)

    val stmtReturningIds = if (stmt.trim.toLowerCase.endsWith("returning id"))
      stmt
    else
      stmt + " RETURNING id"

    val ps = conn.sqlConn.prepareStatement(
      stmtReturningIds, Statement.RETURN_GENERATED_KEYS
    )

    ps.addBatch()
    ps.execute()

    var ids       = List.empty[Long]
    val resultSet = ps.getResultSet
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1)
    }
    ps.close()
    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }
}