package molecule.datalog.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.transaction._
import scala.concurrent.Future

object Rpc_datomic
  extends MoleculeRpc
    with DatomicBase_JVM
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
      stmts = (new ResolveSave with Save_datomic).getStmts(elements)
      txReport <- conn.transact_async(stmts)
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
      stmts = (new ResolveInsert with Insert_datomic)
        .getStmts(proxy.nsMap, elements, tpls)
      txReport <- conn.transact_async(stmts)
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
      stmts = (new ResolveUpdate(conn.proxy, isUpsert) with Update_datomic)
        .getStmts(conn, elements, true)
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new ResolveDelete with Delete_datomic).getData(conn, elements)
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }
}
