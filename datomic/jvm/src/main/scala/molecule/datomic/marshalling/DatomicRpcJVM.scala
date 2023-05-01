package molecule.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.datomic.async._
import molecule.datomic.transaction._
import scala.concurrent.Future

object DatomicRpcJVM extends MoleculeRpc
  with DatomicTxBase_JVM
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

  override def subscribe[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[Any] => Unit
  ): Unit = {
    getConn(proxy).map(conn =>
      Query[Any](elements, limit).subscribe(callback)(conn)
    )
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new SaveExtraction() with Save_stmts).getStmts(elements)
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte],
    txElements: List[Element],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tplsEither = UnpickleTpls[Any](tplElements, ByteBuffer.wrap(tplsSerialized)).unpickle
      tplProducts = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(tplElements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err // catched in outer either wrapper
      }
      stmts = (new InsertExtraction with Insert_stmts).getStmts(proxy.nsMap, tplElements, tplProducts)
      _ = if (txElements.nonEmpty) {
        val txStmts = (new SaveExtraction() with Save_stmts).getRawStmts(txElements, datomicTx, false)
        stmts.addAll(txStmts)
      }
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new UpdateExtraction(conn.proxy.uniqueAttrs, isUpsert) with Update_stmts)
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
      stmts = (new DeleteExtraction with Delete_stmts).getStmtsData(conn, elements)
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }
}
