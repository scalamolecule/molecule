package molecule.db.sql.postgres.spi

import java.sql.DriverManager
import molecule.core.dataModel.Element
import molecule.db.core.action.{Delete, Insert, Save, Update}
import molecule.db.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.db.core.util.Executor.*
import molecule.db.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.sql.core.javaSql.ResultSetInterface as RS
import molecule.db.sql.core.spi.SpiBaseJVM_sync
import molecule.db.sql.core.transaction.SqlDelete
import molecule.db.sql.core.transaction.strategy.SqlOps
import molecule.db.sql.core.transaction.strategy.delete.DeleteAction
import molecule.db.sql.core.transaction.strategy.insert.InsertAction
import molecule.db.sql.core.transaction.strategy.save.SaveAction
import molecule.db.sql.core.transaction.strategy.update.UpdateAction
import molecule.db.sql.postgres.query.*
import molecule.db.sql.postgres.transaction.*
import scala.concurrent.Future


object Spi_postgres_sync extends Spi_postgres_sync

trait Spi_postgres_sync extends SpiBaseJVM_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_postgres(conn) with ResolveSave with Save_postgres {}
      .getSaveAction(save.dataModel.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_postgres(conn) with ResolveInsert with Insert_postgres {}
      .getInsertAction(insert.dataModel.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_postgres(conn) with ResolveUpdate with Update_postgres {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.dataModel.elements)
  }

  override def delete_getAction(
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_postgres(conn)
      with ResolveDelete with Spi_postgres_sync with SqlDelete {}
      .getDeleteAction(delete.dataModel.elements, conn.proxy.metaDb)
  }


  // Util --------------------------------------

  case class SqlOps_postgres(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_postgres(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
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
    JdbcHandler_JVM.updateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}