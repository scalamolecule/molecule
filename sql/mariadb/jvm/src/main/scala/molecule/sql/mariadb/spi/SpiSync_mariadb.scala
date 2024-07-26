package molecule.sql.mariadb.spi

import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.mariadb.marshalling.Connection_mariadb
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import molecule.sql.mariadb.transaction._
import scala.concurrent.Future

object SpiSync_mariadb extends SpiSync_mariadb

trait SpiSync_mariadb extends SpiSyncBase {

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
    update0: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_mariadb(conn) with ResolveUpdate with Update_mariadb {
      override val isUpsert: Boolean = update0.isUpsert
    }.getUpdateAction(update0.elements)
  }

  override def delete_getAction(
    conn: JdbcConn_JVM, delete: Delete
  ): DeleteAction = {
    new SqlOps_mariadb(conn)
      with ResolveDelete with SpiSync_mariadb with SqlDelete {}
      .getDeleteAction(
        delete.elements, conn.proxy.nsMap,
        "SET FOREIGN_KEY_CHECKS", "0", "1"
      )
  }


  // Util --------------------------------------

  case class SqlOps_mariadb(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn

    override val m2q =
      (elements: List[Element]) => new Model2SqlQuery_mariadb(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    validateUpdateSet_json(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_mariadb(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      Connection_mariadb.getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }
}