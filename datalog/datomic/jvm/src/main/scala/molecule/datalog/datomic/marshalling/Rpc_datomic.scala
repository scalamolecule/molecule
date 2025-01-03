package molecule.datalog.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.DataModel._
import molecule.core.action._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
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
   * the 'AnyTpl' type parameter. Model elements are used to pickle the correct types
   * here on the server side. And once wired to the client side we can unpickle
   * the data again from the model and cast to type `Tpl`.
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
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tplsEither = UnpickleTpls[Any](elements, ByteBuffer.wrap(tplsSerialized)).unpickleEither
      tpls = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(elements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err // catch in outer either wrapper
      }
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
