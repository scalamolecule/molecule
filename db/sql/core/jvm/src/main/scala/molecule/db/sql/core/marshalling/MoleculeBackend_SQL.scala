package molecule.db.sql.core.marshalling

import java.nio.ByteBuffer
import molecule.db.core.ast.Element
import molecule.db.core.marshalling.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.util.Executor.*
import molecule.db.base.error.MoleculeError
import molecule.db.core.action.{Delete, Insert, Query, QueryCursor, QueryOffset, Save, Update}
import molecule.db.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.db.core.marshalling.deserialize.UnpickleTpls
import molecule.db.core.spi.{Spi_sync, TxReport}
import molecule.db.core.util.FutureUtils
import molecule.db.sql.core.transaction.CachedConnection
import scala.concurrent.Future


trait MoleculeBackend_SQL
  extends MoleculeRpc
    with CachedConnection
    with FutureUtils { self: Spi_sync =>

  /**
   * Tuple type is not marshalled from client to server. So we signal this with
   * the dummy 'AnyTpl' type parameter. Model elements are used to pickle the correct
   * types here on the server side. And once the response is wired back to the
   * client side we can unpickle the data again using the model on the JS side
   * to cast to type `Tpl`.
   */
  override def query[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[AnyTpl]]] = either {
    getConn(proxy).map(conn =>
      query_get[AnyTpl](Query(elements, limit))(conn)
    )
  }

  override def queryOffset[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[AnyTpl], Int, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryOffset_get[AnyTpl](QueryOffset(elements, limit, offset))(conn)
    )
  }

  override def queryCursor[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[AnyTpl], String, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryCursor_get[AnyTpl](QueryCursor(elements, limit, cursor))(conn)
    )
  }


  override def subscribe[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[AnyTpl] => Unit
  ): Future[Unit] = {
    getConn(proxy).map { conn =>
      query_subscribe[AnyTpl](Query(elements, limit), callback)(conn)
    }
  }
  override def unsubscribe(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, Unit]] = either {
    getConn(proxy).map { conn =>
      query_unsubscribe[Unit](Query(elements))(conn)
    }
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map(conn =>
      // Validation already done on JS side
      save_transact(Save(elements, doValidate = false))(conn)
    )
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      val tpls = UnpickleTpls[Any](elements, tplsSerialized).unpickleSeqOfProduct

      // Validation already done on JS side
      insert_transact(Insert(elements, tpls, doValidate = false))(conn)
    }
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      update_transact(Update(elements, isUpsert))(conn)
    }
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      delete_transact(Delete(elements))(conn)
    }
  }
}
