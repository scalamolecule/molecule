package molecule.sql.postgres.spi

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
import molecule.sql.postgres.marshalling.Connection_postgres
import molecule.sql.postgres.query._
import molecule.sql.postgres.transaction._
import scala.concurrent.Future

object SpiSync_postgres extends SpiSync_postgres

trait SpiSync_postgres extends SpiSyncBase {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_postgres(conn) with ResolveSave with Save_postgres {}
      .getSaveAction(save.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_postgres(conn) with ResolveInsert with Insert_postgres {}
      .getInsertAction(insert.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_postgres(conn) with ResolveUpdate with Update_postgres {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.elements)
  }

  override def delete_getAction(
    conn: JdbcConn_JVM, delete: Delete
  ): DeleteAction = {
    new SqlOps_postgres(conn)
      with ResolveDelete with SpiSync_postgres with SqlDelete {}
      .getDeleteAction(
        delete.elements, conn.proxy.nsMap,
        "SET session_replication_role", "replica", "default"
      )
  }


  // Util --------------------------------------

  case class SqlOps_postgres(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_postgres(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    validateUpdateSet_array(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_postgres(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      Connection_postgres.getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }
}