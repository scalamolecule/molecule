package molecule.sql.mariadb.spi

import java.sql.DriverManager
import molecule.core.action.*
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction.*
import molecule.core.util.Executor.*
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.core.javaSql.ResultSetInterface as RS
import molecule.sql.core.spi.SpiBaseJVM_sync
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import molecule.sql.mariadb.transaction.*
import scala.concurrent.Future


object Spi_mariadb_sync extends Spi_mariadb_sync

trait Spi_mariadb_sync extends SpiBaseJVM_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_mariadb(conn) with ResolveSave with Save_mariadb {}
      .getSaveAction(save.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_mariadb(conn) with ResolveInsert with Insert_mariadb {}
      .getInsertAction(insert.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_mariadb(conn) with ResolveUpdate with Update_mariadb {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.elements)
  }

  override def delete_getAction(
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_mariadb(conn)
      with ResolveDelete with Spi_mariadb_sync with SqlDelete {}
      .getDeleteAction(delete.elements, conn.proxy.entityMap)
  }


  // Util --------------------------------------

  case class SqlOps_mariadb(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_mariadb(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    validateUpdateSet_json(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_mariadb(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }

  private def getNewConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}