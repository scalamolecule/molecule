package molecule.sql.mysql.marshalling

import java.sql.{Connection, DriverManager}
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, QueryCursor, QueryOffset}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.core.transaction.{SqlInsert, SqlSave}
import molecule.sql.mysql.spi.SpiSync_mysql
import molecule.sql.mysql.transaction._
import scala.concurrent.Future


object Rpc_mysql extends SpiSync_mysql with Rpc_SQL {

  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      Connection_mysql.getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }

  override protected def getSaveData(conn: JdbcConn_JVM): ResolveSave with SqlSave = {
    new ResolveSave with Save_mysql {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }

  override protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert = {
    new ResolveInsert with Insert_mysql {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }
}
