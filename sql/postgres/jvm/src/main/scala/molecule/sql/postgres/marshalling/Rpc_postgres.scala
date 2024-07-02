package molecule.sql.postgres.marshalling

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
import molecule.sql.postgres.spi.SpiSync_postgres
import molecule.sql.postgres.transaction._
import scala.concurrent.Future


object Rpc_postgres extends SpiSync_postgres with Rpc_SQL {

  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      Connection_postgres.getNewConnection(proxy.asInstanceOf[JdbcProxy])
    )
  }

  override protected def getSaveData(conn: JdbcConn_JVM): ResolveSave with SqlSave = {
    new ResolveSave with Save_postgres {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }

  override protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert = {
    new ResolveInsert with Insert_postgres {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }
}
