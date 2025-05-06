package molecule.db.datalog.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.core.action.*
import molecule.core.ast.DataModel.*
import molecule.core.marshalling.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor.*
import molecule.core.util.FutureUtils
import molecule.db.datalog
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.transaction.*
import scala.concurrent.Future

trait MoleculeBackend_datomic
  extends MoleculeRpc
    with DatomicBase_JVM
    with FutureUtils {

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
    for {
      conn <- getConn(proxy)
      tpls <- Query[AnyTpl](elements, limit, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def queryOffset[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[AnyTpl], Int, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryOffset[AnyTpl](elements, limit, offset, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def queryCursor[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[AnyTpl], String, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryCursor[AnyTpl](elements, limit, cursor, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def subscribe[AnyTpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[AnyTpl] => Unit
  ): Future[Unit] = {
    for {
      conn <- getConn(proxy)
      _ <- Query[AnyTpl](elements, limit).subscribe(callback)(conn, global)
    } yield ()
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      // Validation already done on JS side
      txReport <- save_transact(Save(elements, doValidate = false))(conn, global)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tpls = UnpickleTpls[Any](elements, tplsSerialized).unpickleSeqOfProduct

      // Validation already done on JS side
      txReport <- insert_transact(Insert(elements, tpls, doValidate = false))(conn, global)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- update_transact(Update(elements, isUpsert))(conn, global)
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- delete_transact(Delete(elements))(conn, global)
    } yield txReport
  }
}
