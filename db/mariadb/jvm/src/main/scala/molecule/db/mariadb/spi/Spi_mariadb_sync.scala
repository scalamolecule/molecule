package molecule.db.mariadb.spi

import java.sql.DriverManager
import scala.concurrent.Future
import molecule.core.dataModel.Element
import molecule.db.common.crud.{Delete, Insert, Save, Update}
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.common.spi.SpiBaseJVM_sync
import molecule.db.common.transaction.*
import molecule.db.common.util.Executor.*
import molecule.db.mariadb.query.Model2SqlQuery_mariadb
import molecule.db.mariadb.transaction.{Insert_mariadb, Save_mariadb, Update_mariadb}


object Spi_mariadb_sync extends Spi_mariadb_sync

trait Spi_mariadb_sync extends SpiBaseJVM_sync {

  override def getResolveSave(save: Save, conn: JdbcConn_JVM) =
    new ResolveSave with Save_mariadb {}

  override def getResolveInsert(insert: Insert, conn: JdbcConn_JVM) =
    new ResolveInsert with Insert_mariadb {}

  override def getResolveUpdate(update: Update, conn: JdbcConn_JVM) =
    new ResolveUpdate(update.isUpsert) with Update_mariadb {}


  // Util --------------------------------------

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
    JdbcHandler_JVM.updateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}