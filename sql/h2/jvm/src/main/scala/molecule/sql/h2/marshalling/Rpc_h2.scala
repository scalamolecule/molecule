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

  override protected def getQuery[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int]
  ): List[Any] = {
    query_get[Any](Query(elements, optLimit))(conn)
  }

  override protected def getQueryOffset[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int],
    offset: Int
  ): (List[Any], RowIndex, Boolean) = {
    queryOffset_get[Any](QueryOffset(elements, optLimit, offset))(conn)
  }

  override protected def getQueryCursor[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int],
    cursor: String
  ): (List[Any], String, Boolean) = {
    queryCursor_get[Any](QueryCursor(elements, optLimit, cursor))(conn)
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
