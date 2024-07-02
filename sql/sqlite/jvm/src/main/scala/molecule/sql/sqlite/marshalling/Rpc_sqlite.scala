package molecule.sql.sqlite.marshalling

import java.sql.Connection
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Delete, Query, QueryCursor, QueryOffset}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.spi.TxReport
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.core.transaction.{SqlInsert, SqlSave}
import molecule.sql.sqlite.facade.JdbcHandlerSQlite_JVM
import molecule.sql.sqlite.spi.SpiSync_sqlite
import molecule.sql.sqlite.transaction._
import scala.concurrent.Future


object Rpc_sqlite extends SpiSync_sqlite with Rpc_SQL {

  override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    Future(
      JdbcHandlerSQlite_JVM.recreateDb(proxy.asInstanceOf[JdbcProxy])
    )
  }


  // Disable foreign key constraints for possibly multiple deletions
  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- Future(
        delete_getExecutioner(conn, Delete(elements)).fold(TxReport(Nil)) { executions =>
          // Turn of foreign key constraints temporarily to allow multiple deletions
          setFkConstraint(conn.sqlConn, 0)

          // Commit deletions
          val txReport = conn.atomicTransaction(executions)

          // Turn on foreign key constraints again
          setFkConstraint(conn.sqlConn, 1)

          txReport
        }
      )
    } yield txReport
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
    new ResolveSave with Save_sqlite {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }

  override protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert = {
    new ResolveInsert with Insert_sqlite {
      override lazy val sqlConn: Connection = conn.sqlConn
    }
  }
}
