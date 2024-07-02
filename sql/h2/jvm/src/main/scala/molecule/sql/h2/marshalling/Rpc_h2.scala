package molecule.sql.h2.marshalling

import java.sql.Connection
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, QueryCursor, QueryOffset}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{ConnProxy, JdbcProxy}
import molecule.core.transaction._
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.core.transaction.{SqlInsert, SqlSave}
import molecule.sql.h2.spi.SpiSync_h2
import molecule.sql.h2.transaction._
import scala.concurrent.Future
import molecule.core.util.Executor._


object Rpc_h2 extends SpiSync_h2 with Rpc_SQL {

  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      JdbcHandler_JVM.recreateDb(proxy.asInstanceOf[JdbcProxy])
    )
  }

  override protected def getSaveData(conn: JdbcConn_JVM): ResolveSave with SqlSave = {
    new ResolveSave with Save_h2 {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }

  override protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert = {
    new ResolveInsert with Insert_h2 {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }
}
