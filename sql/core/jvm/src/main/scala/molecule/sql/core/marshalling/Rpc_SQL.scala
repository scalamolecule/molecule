package molecule.sql.core.marshalling

import java.nio.ByteBuffer
import molecule.base.error.{MoleculeError, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.{SpiSync, TxReport}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.transaction.update.UpdateHelper
import molecule.sql.core.transaction.{SqlBase_JVM, SqlInsert, SqlSave}
import scala.concurrent.Future


trait Rpc_SQL
  extends MoleculeRpc
    with SqlBase_JVM
    with UpdateHelper
    with FutureUtils { self: SpiSync =>

  /**
   * Tuple type is not marshalled from client to server. So we signal this with
   * the dummy 'Any' type parameter. Model elements are used to pickle the correct
   * types here on the server side. And once wired to the client side we can unpickle
   * the data again using the model on the JS side to cast to type `Tpl`.
   */
  override def query[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Any]]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Future(getQuery[Any](conn, elements, limit))
    } yield tpls
  }

  override def queryOffset[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Any], Int, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Future(getQueryOffset[Any](conn, elements, limit, offset))
    } yield tpls
  }

  override def queryCursor[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Any], String, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Future(getQueryCursor[Any](conn, elements, limit, cursor))
    } yield tpls
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- Future {
        val data = getSaveData(conn).getSaveData(elements)
        conn.transact_sync(data)
      }

    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- Future {
        val tplsEither = UnpickleTpls[Any](elements, ByteBuffer.wrap(tplsSerialized)).unpickle
        val tpls       = tplsEither match {
          case Right(tpls) =>
            (if (countValueAttrs(elements) == 1) {
              tpls.map(Tuple1(_))
            } else tpls).asInstanceOf[Seq[Product]]
          case Left(err)   => throw err
        }
        val data = getInsertData(conn).getInsertData(proxy.nsMap, elements, tpls)
        conn.transact_sync(data)
      }
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elementsRaw: List[Element],
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- Future {
        val errors = update_validate(Update(elementsRaw, isUpsert))(conn)
        if (errors.nonEmpty)
          throw ValidationErrors(errors)

        val data = if (isRefUpdate(elements)) {
          if (isUpsert)
            refUpserts(elements)(conn)
          else
            refUpdates(elements, isUpsert)(conn)
        } else {
          update_getData(conn, Update(elements, isUpsert))
        }
        conn.transact_sync(data)
      }
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- Future(
        delete_getExecutioner(conn, Delete(elements)).fold(TxReport(Nil)) { executions =>
          conn.atomicTransaction(executions)
        }
      )
    } yield txReport
  }


  // Implement for each db ------------------------------------

  protected def getQuery[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int]
  ): List[Any]

  protected def getQueryOffset[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int],
    offset: Int
  ): (List[Any], RowIndex, Boolean)

  protected def getQueryCursor[Any](
    conn: JdbcConn_JVM,
    elements: List[Element],
    optLimit: Option[Int],
    cursor: String
  ): (List[Any], String, Boolean)

  protected def getSaveData(conn: JdbcConn_JVM): ResolveSave with SqlSave
  protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert
}
