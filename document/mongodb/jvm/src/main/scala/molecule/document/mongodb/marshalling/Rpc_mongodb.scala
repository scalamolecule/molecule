package molecule.document.mongodb.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, QueryCursor, QueryOffset}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.document.mongodb.async._
import molecule.document.mongodb.transaction._
import scala.concurrent.Future


object Rpc_mongodb
  extends MoleculeRpc
    with Base_JVM_mongodb
    with FutureUtils {

  /**
   * Tuple type is not marshalled from client to server. So we signal this with
   * the 'Any' type parameter. Model elements are used to pickle the correct types
   * here on the server side. And once wired to the client side we can unpickle
   * the data again from the model and cast to type `Tpl`.
   */
  override def query[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Any]]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Query[Any](elements, limit, proxy.dbView).get(conn, global)
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
      tpls <- QueryOffset[Any](elements, limit, offset, proxy.dbView).get(conn, global)
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
      tpls <- QueryCursor[Any](elements, limit, cursor, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      data = new ResolveSave with Save_mongodb().getData(elements)
      txReport <- conn.transact_async(data)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tplsEither = UnpickleTpls[Any](elements, ByteBuffer.wrap(tplsSerialized)).unpickle
      tpls = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(elements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err // catched in outer either wrapper
      }
      data = new ResolveInsert with Insert_mongodb().getData(proxy.nsMap, elements, tpls)
      txReport <- conn.transact_async(data)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      data = new ResolveUpdate(conn.proxy, isUpsert) with Update_mongodb()
        .getData(elements, conn)
      txReport <- conn.transact_async(data)
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      data = new ResolveDelete with Delete_mongodb().getData(elements, conn)
      txReport <- conn.transact_async(data)
    } yield txReport
  }
}
