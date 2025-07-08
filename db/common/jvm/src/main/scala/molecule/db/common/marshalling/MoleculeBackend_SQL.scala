package molecule.db.common.marshalling

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.MoleculeError
import molecule.core.dataModel.{DataModel, Value}
import molecule.db.common.action.*
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.deserialize.UnpickleTpls
import molecule.db.common.marshalling.{ConnProxy, MoleculeRpc}
import molecule.db.common.spi.{Spi_sync, TxReport}
import molecule.db.common.util.Executor.*
import molecule.db.common.util.FutureUtils
import molecule.db.common.transaction.CachedConnection
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
    dataModel: DataModel,
    limit: Option[Int],
    bindValues: List[Value]
  ): Future[Either[MoleculeError, List[AnyTpl]]] = either {
    getConn(proxy).map(conn =>
      query_get[AnyTpl](Query(dataModel, limit, bindValues = bindValues))(using conn)
    )
  }

  override def queryOffset[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    offset: Int,
    bindValues: List[Value]
  ): Future[Either[MoleculeError, (List[AnyTpl], Int, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryOffset_get[AnyTpl](QueryOffset(dataModel, limit, offset, bindValues = bindValues))(using conn)
    )
  }

  override def queryCursor[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    cursor: String,
    bindValues: List[Value]
  ): Future[Either[MoleculeError, (List[AnyTpl], String, Boolean)]] = either {
    getConn(proxy).map(conn =>
      queryCursor_get[AnyTpl](QueryCursor(dataModel, limit, cursor, bindValues = bindValues))(using conn)
    )
  }


  override def subscribe[AnyTpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    callback: List[AnyTpl] => Unit
  ): Future[Unit] = {
    getConn(proxy).map { conn =>
      query_subscribe[AnyTpl](Query(dataModel, limit), callback)(using conn)
    }
  }
  override def unsubscribe(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, Unit]] = either {
    getConn(proxy).map { conn =>
      query_unsubscribe[Unit](Query(dataModel))(using conn)
    }
  }

  override def save(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map(conn =>
      // Validation already done on JS side
      save_transact(Save(dataModel, doValidate = false))(using conn)
    )
  }

  override def insert(
    proxy: ConnProxy,
    dataModel: DataModel,
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      val tpls = UnpickleTpls[Any](dataModel, tplsSerialized).unpickleSeqOfProduct

      // Validation already done on JS side
      insert_transact(Insert(dataModel, tpls, doValidate = false))(using conn)
    }
  }

  override def update(
    proxy: ConnProxy,
    dataModel: DataModel,
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      update_transact(Update(dataModel, isUpsert))(using conn)
    }
  }

  override def delete(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = either {
    getConn(proxy).map { conn =>
      delete_transact(Delete(dataModel))(using conn)
    }
  }
}
