package molecule.sql.postgres.spi

import java.sql.DriverManager
import molecule.core.action._
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiBaseJVM_sync
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.postgres.query._
import molecule.sql.postgres.transaction._
import scala.concurrent.Future


object Spi_postgres_sync extends Spi_postgres_sync

trait Spi_postgres_sync extends SpiBaseJVM_sync {

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
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_postgres(conn)
      with ResolveDelete with Spi_postgres_sync with SqlDelete {}
      .getDeleteAction(delete.elements, conn.proxy.entityMap)
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
      getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }

  private def getNewConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}