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
import molecule.sql.core.transaction.strategy.update.UpdateHelper
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
    getConn(proxy).map(conn =>
      query_get[Any](Query(elements, limit))(conn)
    )
  }

  override def queryOffset[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Any], Int, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryOffset_get[Any](QueryOffset(elements, limit, offset))(conn)
    )
  }

  override def queryCursor[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Any], String, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryCursor_get[Any](QueryCursor(elements, limit, cursor))(conn)
    )
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map(conn =>
      conn.transact_sync(
        getSaveData(conn).getSaveAction(elements)
      )
    )
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      val tplsEither = UnpickleTpls[Any](
        elements, ByteBuffer.wrap(tplsSerialized)
      ).unpickle
      val tpls       = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(elements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err
      }
      conn.transact_sync(
        getInsertData(conn).getInsertAction(elements, tpls)
      )
    }
  }

  override def update(
    proxy: ConnProxy,
    elementsRaw: List[Element],
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
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
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      delete_getExecutioner(conn, Delete(elements)).fold(
        TxReport(Nil)
      ) { executions =>
        conn.atomicTransaction(executions)
      }
    }
  }


  // Implement for each db ------------------------------------

  protected def getSaveData(conn: JdbcConn_JVM): ResolveSave with SqlSave
  protected def getInsertData(conn: JdbcConn_JVM): ResolveInsert with SqlInsert
}
