package molecule.db.h2.spi

import java.sql.DriverManager
import scala.concurrent.Future
import molecule.core.dataModel.Element
import molecule.db.common.crud.{Delete, Insert, Save, Update}
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.{ConnProxy, JdbcProxy}
import molecule.db.common.query.{Model2SqlQuery, SqlQueryBase}
import molecule.db.common.spi.{SpiBaseJVM_sync, TxReport}
import molecule.db.common.transaction.*
import molecule.db.common.util.Executor.*
import molecule.db.h2.facade.JdbcConn_JVM_h2
import molecule.db.h2.query.Model2SqlQuery_h2
import molecule.db.h2.query.SqlQueryBase_h2

object Spi_h2_sync extends Spi_h2_sync

trait Spi_h2_sync extends SpiBaseJVM_sync {

  override def getResolveSave(save: Save, conn: JdbcConn_JVM) =
    new ResolveSave with SqlSave {}

  override def getResolveInsert(insert: Insert, conn: JdbcConn_JVM) =
    new ResolveInsert with SqlInsert {}

  override def getResolveUpdate(update: Update, conn: JdbcConn_JVM) =
    new ResolveUpdate(update.isUpsert) with SqlUpdate {}


  // Util --------------------------------------

  override def validateUpdateSet(
    proxy: ConnProxy, elements: List[Element], query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    validateUpdateSet_array(proxy, elements, query2resultSet)
  }

  override def getModel2SqlQuery(elements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_h2(elements) with SqlQueryBase_h2

  // Creating connection from RPC proxy
  override protected def getJdbcConn(proxy0: ConnProxy): Future[JdbcConn_JVM] = Future {
    val proxy   = proxy0.asInstanceOf[JdbcProxy]
    val sqlConn = DriverManager.getConnection(proxy.url)
    sqlConn.setAutoCommit(false)
    val conn = JdbcConn_JVM_h2(proxy, sqlConn)
    val stmt = sqlConn.createStatement()
    stmt.executeUpdate(JdbcHandler_JVM.getSqlInit(proxy))
    sqlConn.commit()
    stmt.close()
    conn
  }
}