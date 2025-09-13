package molecule.db.postgresql.spi

import java.sql.DriverManager
import scala.concurrent.Future
import molecule.core.dataModel.Element
import molecule.db.common.action.{Delete, Insert, Save, Update}
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.common.spi.SpiBaseJVM_sync
import molecule.db.common.transaction.*
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.delete.DeleteAction
import molecule.db.common.transaction.strategy.insert.InsertAction
import molecule.db.common.transaction.strategy.save.SaveAction
import molecule.db.common.transaction.strategy.update.UpdateAction
import molecule.db.common.util.Executor.*
import molecule.db.postgresql.query.Model2SqlQuery_postgresql
import molecule.db.postgresql.transaction.*


object Spi_postgresql_sync extends Spi_postgresql_sync

trait Spi_postgresql_sync extends SpiBaseJVM_sync {

  override def getResolveSave(save: Save, conn: JdbcConn_JVM) =
    new ResolveSave with Save_postgresql {}

  override def getResolveInsert(insert: Insert, conn: JdbcConn_JVM) =
    new ResolveInsert with Insert_postgresql {}

  override def getResolveUpdate(update: Update, conn: JdbcConn_JVM) =
    new ResolveUpdate(update.isUpsert) with Update_postgresql {}


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