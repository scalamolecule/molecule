package molecule.db.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.db.datomic.action.DatomicQuery
import molecule.db.datomic.async._
import molecule.db.datomic.transaction._
import scala.concurrent.Future

object DatomicRpcJVM extends MoleculeRpc
  with DatomicTxBase_JVM
  with FutureUtils {

  // Data is typed when deserialized on client side
  override def query[Any](
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, List[Any]]] = either {
    for {
      conn <- getConn(proxy)
      rows <- new DatomicQuery[Any](elements).get(conn, global)
    } yield rows
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
      stmts = (new InsertExtraction with Insert_stmts).getStmts(tplElements, tplProducts)
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
        .getStmts(conn, elements)
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
