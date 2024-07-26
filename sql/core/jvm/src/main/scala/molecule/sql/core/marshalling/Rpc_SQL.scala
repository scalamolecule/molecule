package molecule.sql.core.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.{SpiSync, TxReport}
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.sql.core.transaction.ConnectionPool
import scala.concurrent.Future


trait Rpc_SQL
  extends MoleculeRpc
    with ConnectionPool
    with FutureUtils { self: SpiSync =>

  /**
   * Tuple type is not marshalled from client to server. So we signal this with
   * the dummy 'AnyTpl' type parameter. Model elements are used to pickle the correct
   * types here on the server side. And once wired to the client side we can unpickle
   * the data again using the model on the JS side to cast to type `Tpl`.
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

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map(conn =>
      save_transact(Save(elements))(conn)
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
          if (countValueAttrs(elements) == 1) {
            tpls.map(Tuple1(_))
          } else {
            tpls.asInstanceOf[Seq[Product]]
          }

        case Left(err) => throw err // catch in `either`
      }
      insert_transact(Insert(elements, tpls))(conn)
    }
  }

  override def update(
    proxy: ConnProxy,
    elementsRaw: List[Element],
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      update_transact(Update(elementsRaw, isUpsert))(conn)
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
