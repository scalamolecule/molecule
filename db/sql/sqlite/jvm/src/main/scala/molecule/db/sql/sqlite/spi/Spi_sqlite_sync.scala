package molecule.db.sql.sqlite.spi

import java.sql.{DriverManager, Statement, PreparedStatement as PS}
import molecule.core.ast.Element
import molecule.db.core.action.{Delete, Insert, Save, Update}
import molecule.db.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.core.spi.{Conn, TxReport}
import molecule.db.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.db.core.util.Executor.*
import molecule.db.sql.core.facade.JdbcConn_JVM
import molecule.db.sql.core.javaSql.ResultSetInterface as RS
import molecule.db.sql.core.spi.SpiBaseJVM_sync
import molecule.db.sql.core.transaction.SqlDelete
import molecule.db.sql.core.transaction.strategy.SqlOps
import molecule.db.sql.core.transaction.strategy.delete.DeleteAction
import molecule.db.sql.core.transaction.strategy.insert.InsertAction
import molecule.db.sql.core.transaction.strategy.save.SaveAction
import molecule.db.sql.core.transaction.strategy.update.UpdateAction
import molecule.db.sql.sqlite.facade.JdbcHandlerSQlite_JVM
import molecule.db.sql.sqlite.query.Model2SqlQuery_sqlite
import molecule.db.sql.sqlite.transaction.{Insert_sqlite, Save_sqlite, Update_sqlite}
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

object Spi_sqlite_sync extends Spi_sqlite_sync

trait Spi_sqlite_sync extends SpiBaseJVM_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_sqlite(conn) with ResolveSave with Save_sqlite {}
      .getSaveAction(save.dataModel.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_sqlite(conn) with ResolveInsert with Insert_sqlite {}
      .getInsertAction(insert.dataModel.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn0: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_sqlite(conn0) with ResolveUpdate with Update_sqlite {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.dataModel.elements)
  }

  override def delete_getAction(
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_sqlite(conn)
      with ResolveDelete with SqlDelete {}
      .getDeleteAction(delete.dataModel.elements, conn.proxy.entityMap)
  }


  // Util --------------------------------------

  case class SqlOps_sqlite(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn       = conn.sqlConn
    override val defaultValues = "DEFAULT VALUES"
    override val m2q           = (elements: List[Element]) =>
      new Model2SqlQuery_sqlite(elements)

    // Since SQlite doesn't allow us to get ps.getGeneratedKeys after an
    // executeBatch(), we get the affected ids by brute force with a query instead.
    override def getIds(
      ps: PS,
      table: String
    ): List[Long] = {
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
  )(implicit conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
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