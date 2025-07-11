package molecule.db.postgresql.spi

import java.sql.DriverManager
import molecule.core.dataModel.Element
import molecule.db.common.action.{Delete, Insert, Save, Update}
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.common.spi.SpiBaseJVM_sync
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.delete.DeleteAction
import molecule.db.common.transaction.strategy.insert.InsertAction
import molecule.db.common.transaction.strategy.save.SaveAction
import molecule.db.common.transaction.strategy.update.UpdateAction
import molecule.db.common.transaction.*
import molecule.db.common.util.Executor.*
import molecule.db.postgresql.query.Model2SqlQuery_postgresql
import molecule.db.postgresql.transaction.*
import scala.concurrent.Future


object Spi_postgresql_sync extends Spi_postgresql_sync

trait Spi_postgresql_sync extends SpiBaseJVM_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_postgres(conn) with ResolveSave with Save_postgresql {}
      .getSaveAction(save.dataModel.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_postgres(conn) with ResolveInsert with Insert_postgresql {}
      .getInsertAction(insert.dataModel.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_postgres(conn) with ResolveUpdate with Update_postgresql {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.dataModel.elements)
  }

  override def delete_getAction(
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_postgres(conn)
      with ResolveDelete with Spi_postgresql_sync with SqlDelete {}
      .getDeleteAction(delete.dataModel.elements, conn.proxy.metaDb)
  }


  // Util --------------------------------------

  case class SqlOps_postgres(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_postgresql(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    validateUpdateSet_array(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_postgresql(elements)

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