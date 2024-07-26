package molecule.sql.h2.spi

import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.core.transaction.{SqlDelete, SqlInsert, SqlSave, SqlUpdate}
import molecule.sql.h2.query.Model2SqlQuery_h2
import scala.concurrent.Future


object SpiSync_h2 extends SpiSync_h2

trait SpiSync_h2 extends SpiSyncBase {

  override def save_getAction(
    save: Save, conn: JdbcConn_JVM
  ): SaveAction = {
    new SqlOps_h2(conn) with ResolveSave with SpiSync_h2 with SqlSave {}
      .getSaveAction(save.elements)
  }

  override def insert_getAction(
    insert: Insert, conn: JdbcConn_JVM
  ): InsertAction = {
    new SqlOps_h2(conn) with ResolveInsert with SqlInsert {}
      .getInsertAction(insert.elements, insert.tpls)
  }

  override def update_getAction(
    update0: Update, conn0: JdbcConn_JVM
  ): UpdateAction = {
    new SqlOps_h2(conn0) with ResolveUpdate with SpiSync_h2 with SqlUpdate {
      override val isUpsert: Boolean = update0.isUpsert
    }.getUpdateAction(update0.elements)
  }

  override def delete_getAction(
    conn: JdbcConn_JVM, delete: Delete
  ): DeleteAction = {
    new SqlOps_h2(conn) with ResolveDelete with SqlDelete {}
      .getDeleteAction(
        delete.elements, conn.proxy.nsMap,
        "SET REFERENTIAL_INTEGRITY", "FALSE", "TRUE"
      )
  }


  // Util --------------------------------------

  case class SqlOps_h2(conn: JdbcConn_JVM) extends SqlOps {
    override val sqlConn = conn.sqlConn

    override val m2q =
      (elements: List[Element]) => new Model2SqlQuery_h2(elements)

    override val defaultValues = "(id) VALUES (DEFAULT)"
  }

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    validateUpdateSet_array(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_h2(elements)

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      JdbcHandler_JVM.recreateDb(proxy.asInstanceOf[JdbcProxy])
    )
  }
}