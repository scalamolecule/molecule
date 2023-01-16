package molecule.db.datomic.marshalling

import java.nio.ByteBuffer
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.api.action.ApiOps
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.transaction.{Delete, Insert, Save, Update}
import molecule.core.util.Executor._
import molecule.db.datomic.api.action.DatomicQueryApiImpl
import molecule.db.datomic.transaction._
import scala.concurrent.Future

object DatomicRpcJVM extends MoleculeRpc
  with DatomicTxBase_JVM
  with ApiOps {

  // Api ---------------------------------------------

  // Data is typed when un-serialized on client side
  override def query[Any](
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, List[Any]]] = either {
    for {
      conn <- getConn(proxy)
      rows <- new DatomicQueryApiImpl[Any](elements).get(conn, global)
    } yield rows
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new Save() with Save_stmts).getStmts(elements)
      txReport <- conn.transact(stmts)
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
      //      _ = logger.trace("insert...")

      tplsEither = UnpickleTpls[Any](tplElements, ByteBuffer.wrap(tplsSerialized)).unpickle
      //      _ = logger.trace(tplsEither.toString)

      tplProducts = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(tplElements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]

        case Left(err) => throw err // catched in outer either wrapper
      }
      stmts = (new Insert with Insert_stmts).getStmts(tplElements, tplProducts)
      //      _ = logger.trace(stmts.toString)
      _ = if (txElements.nonEmpty) {
        val txStmts = (new Save() with Save_stmts).getRawStmts(txElements, datomicTx, false)
        //        logger.trace(txStmts.toString)
        stmts.addAll(txStmts)
      }

      txReport <- conn.transact(stmts)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false,
    isMultiple: Boolean = false,
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new Update(conn.proxy.uniqueAttrs, isUpsert, isMultiple) with Update_stmts)
        .getStmts(conn, elements)
      txReport <- conn.transact(stmts)
    } yield txReport

  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element],
    isMultiple: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = {

        (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
      }
      //      stmts = try {
      //        (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
      //      } catch {
      //        case e: Throwable =>
      //          logger.error(e.toString)
      //          logger.error(e.getStackTrace.toList.mkString("\n"))
      //          throw e
      //      }
      txReport <- conn.transact(stmts)
    } yield txReport
  }
}
