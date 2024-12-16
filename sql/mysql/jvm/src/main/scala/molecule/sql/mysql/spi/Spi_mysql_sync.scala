package molecule.sql.mysql.spi

import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiBase_sync
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.mysql.marshalling.Connection_mysql
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import molecule.sql.mysql.transaction._
import scala.concurrent.Future


object Spi_mysql_sync extends Spi_mysql_sync

trait Spi_mysql_sync extends SpiBase_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_mysql(conn) with ResolveSave with Save_mysql {}
      .getSaveAction(save.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_mysql(conn) with ResolveInsert with Insert_mysql {}
      .getInsertAction(insert.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_mysql(conn) with ResolveUpdate with Update_mysql {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.elements)
  }

  override def delete_getAction(
    conn: JdbcConn_JVM, delete: Delete, disableFKs: Boolean
  ): DeleteAction = {
    new SqlOps_mysql(conn)
      with ResolveDelete with Spi_mysql_sync with SqlDelete {}
      .getDeleteAction(
        delete.elements, conn.proxy.nsMap,
        "SET FOREIGN_KEY_CHECKS", "0", "1", disableFKs
      )
  }


  // Util --------------------------------------

  case class SqlOps_mysql(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_mysql(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    validateUpdateSet_json(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_mysql(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      Connection_mysql.getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }
}