package molecule.db.datalog.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*
import molecule.db.core.marshalling.deserialize.UnpickleTpls
import molecule.db.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.db.core.spi.TxReport
import molecule.db.core.util.Executor.*
import molecule.db.core.util.FutureUtils
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
    dataModel: DataModel,
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[AnyTpl]]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Query[AnyTpl](dataModel, limit, proxy.dbView).get(using conn, global)
    } yield tpls
  }

  override def queryOffset[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[AnyTpl], Int, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryOffset[AnyTpl](dataModel, limit, offset, proxy.dbView).get(using conn, global)
    } yield tpls
  }

  override def queryCursor[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[AnyTpl], String, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryCursor[AnyTpl](dataModel, limit, cursor, proxy.dbView).get(using conn, global)
    } yield tpls
  }

  override def subscribe[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    callback: List[AnyTpl] => Unit
  ): Future[Unit] = {
    for {
      conn <- getConn(proxy)
      _ <- Query[AnyTpl](dataModel, limit).subscribe(callback)(using conn, global)
    } yield ()
  }

  override def unsubscribe(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, Unit]] = either {
    getConn(proxy).map { conn =>
      query_unsubscribe[Unit](Query(dataModel))(using conn, global)
    }
  }

  override def save(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      // Validation already done on JS side
      txReport <- save_transact(Save(dataModel, doValidate = false))(using conn, global)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    dataModel: DataModel,
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tpls = UnpickleTpls[Any](dataModel, tplsSerialized).unpickleSeqOfProduct

      // Validation already done on JS side
      txReport <- insert_transact(Insert(dataModel, tpls, doValidate = false))(using conn, global)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    dataModel: DataModel,
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- update_transact(Update(dataModel, isUpsert))(using conn, global)
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      txReport <- delete_transact(Delete(dataModel))(using conn, global)
    } yield txReport
  }
}
