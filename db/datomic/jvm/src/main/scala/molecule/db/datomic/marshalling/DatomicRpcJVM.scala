package molecule.db.datomic.marshalling

import java.util.UUID
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.pack.Tpls2DTO
import molecule.core.marshalling.unpack.DTO2tpls
import molecule.core.transaction.{Delete, Insert, Save, Update}
import molecule.core.util.Executor._
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.db.datomic.api.ops.DatomicQueryOpsImpl
import molecule.db.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import molecule.db.datomic.query.Base
import molecule.db.datomic.transaction._
import scribe.Logging
import scala.collection.mutable
import scala.concurrent.Future

object DatomicRpcJVM extends MoleculeRpc
  with DatomicTxBase_JVM
  with JavaConversions
  with ModelUtils with Logging {

  // Api ---------------------------------------------

  override def query(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, DTO]] = either {
    for {
      conn <- getConn(proxy)
      rows <- new DatomicQueryOpsImpl[Any](elements).get(conn, global)
    } yield {
      val tpls = {
        if (countValueAttrs(elements) == 1) {
          rows.map(v => Tuple1(v))
        } else {
          rows
        }
      }
      Tpls2DTO(elements, tpls.asInstanceOf[Seq[Product]]).pack
    }
  }

  override def save(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new Save() with Save_stmts).getStmts(elements)
      txReport <- conn.transact(stmts)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    tplElements: Seq[Element],
    tplData: DTO,
    txElements: Seq[Element],
  ): Future[Either[MoleculeException, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tpls = if (countValueAttrs(tplElements) == 1) {
        DTO2tpls[Any](tplElements, tplData).unpack.map(v => Tuple1(v))
      } else {
        DTO2tpls[Product](tplElements, tplData).unpack
      }
      stmts = (new Insert with Insert_stmts).getStmts(tplElements, tpls)
      _ = if (txElements.nonEmpty) {
        val txStmts = (new Save() with Save_stmts)
          .getRawStmts(txElements, datomicTx, false)
        stmts.addAll(txStmts)
      }
      txReport <- conn.transact(stmts)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: Seq[Element],
    isUpsert: Boolean,
    isMultiple: Boolean,
  ): Future[Either[MoleculeException, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new Update(conn.proxy.uniqueAttrs, isUpsert, isMultiple) with Update_stmts)
        .getStmts(conn, elements)
      txReport <- conn.transact(stmts)
    } yield txReport
  }

  override def delete(
    proxy: ConnProxy,
    elements: Seq[Element],
    isMultiple: Boolean
  ): Future[Either[MoleculeException, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
      txReport <- conn.transact(stmts)
    } yield txReport
  }


  private def either[T](fut: Future[T]): Future[Either[MoleculeException, T]] = {
    // Transfer exceptions in Either to allow Boopickle to serialize
    fut
      .map(txR => Right(txR))
      .recover {
        case e: MoleculeException => Left(e)
        case e: Throwable         => Left(MoleculeException(e.getMessage, e))
      }
  }



  // Connection pool ---------------------------------------------

  // todo: real solution
  private val connectionPool = mutable.HashMap.empty[UUID, Future[DatomicConn_JVM]]

  override def clearConnPool: Future[Unit] = Future {
    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
    connectionPool.clear()
  }

  private def getConn(proxy: ConnProxy): Future[DatomicConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      //      conn.updateAdhocDbView(proxy.adhocDbView)
      //      conn.updateTestDbView(proxy.testDbView, proxy.testDbStatus)
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    // logger.debug("connectionPool.size: " + connectionPool.size)
    futConnTimeAdjusted
  }

  private def getFreshConn(proxy: ConnProxy): Future[DatomicConn_JVM] = {
    proxy match {
      case proxy@DatomicPeerProxy(protocol, dbIdentifier, _, _, _, _, _, _, _, _, isFreeVersion) =>
        protocol match {
          case "mem" =>
            DatomicPeer.recreateDbFromEdn(proxy, protocol, dbIdentifier, isFreeVersion)
              .recover(exc => throw MoleculeException(exc.getMessage))

          case "free" | "dev" | "pro" =>
            Future(DatomicPeer.connect(proxy, protocol, dbIdentifier))
              .recover(exc => throw MoleculeException(exc.getMessage))

          case other => Future.failed(MoleculeException(s"\nCan't serve Peer protocol `$other`."))
        }
    }
  }
}
