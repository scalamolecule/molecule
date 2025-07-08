package molecule.db.h2.spi

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
import molecule.db.h2.query.Model2SqlQuery_h2
import scala.concurrent.Future


object Spi_h2_sync extends Spi_h2_sync

trait Spi_h2_sync extends SpiBaseJVM_sync {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_h2(conn) with ResolveSave with Spi_h2_sync with SqlSave {}
      .getSaveAction(save.dataModel.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_h2(conn) with ResolveInsert with SqlInsert {}
      .getInsertAction(insert.dataModel.elements, insert.tpls)
  }

  override def update_getAction(
    update: Update, conn0: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_h2(conn0) with ResolveUpdate with Spi_h2_sync with SqlUpdate {
      override val isUpsert: Boolean = update.isUpsert
    }.getUpdateAction(update.dataModel.elements)
  }

  override def delete_getAction(
    delete: Delete, conn: JdbcConn_JVM
  ): DeleteAction = {
    new SqlOps_h2(conn)
      with ResolveDelete with SqlDelete {}
      .getDeleteAction(delete.dataModel.elements, conn.proxy.metaDb)
  }


  // Util --------------------------------------

  case class SqlOps_h2(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn
    override val m2q     = (elements: List[Element]) =>
      new Model2SqlQuery_h2(elements)
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    validateUpdateSet_array(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_h2(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy0: ConnProxy): Future[JdbcConn_JVM] = Future {
    val proxy   = proxy0.asInstanceOf[JdbcProxy]
    val sqlConn = DriverManager.getConnection(proxy.url)
    JdbcHandler_JVM.recreateDb(proxy, sqlConn)
  }
}