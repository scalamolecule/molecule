package molecule.datomic.api


import datomic.Peer
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.subscription.SubscriptionStarter
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}


trait DatomicApiAsync
  extends SubscriptionStarter
    with DatomicAsyncApiBase
    with ApiAsync
    with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
    }
    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .subscribe(datomicConn, getWatcher(datomicConn), callback)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }

  implicit class datomicQueryCursorApiAsync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiAsync[Tpl](save: DatomicSave) extends Transaction {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      Future(validate) // Future catches exceptions thrown
        .flatMap {
          case errorMap if errorMap.nonEmpty => throw ValidationErrors(errorMap)
          case _                             =>
            conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts(conn.proxy))
        }
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("SAVE", save.elements, getStmts(conn.proxy))
    }
    private def getStmts(proxy: ConnProxy): Data = {
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
    }
    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.nsMap, proxy.attrMap).check(save.elements)
    }
  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
      tryFuture {
        Future(validate) // Future catches exceptions thrown
          .flatMap {
            case insertErrors if insertErrors.nonEmpty => throw InsertErrors(insertErrors)
            case _                                     =>
              conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts(conn.proxy))
          }
      }
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("INSERT", insert.elements, getStmts(conn.proxy))
    }
    private def getStmts(proxy: ConnProxy): Data = {
      (new InsertExtraction with Insert_stmts)
        .getStmts(proxy.nsMap, proxy.attrMap, insert.elements, insert.tpls)
    }
    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiAsync[Tpl](update: DatomicUpdate) extends Transaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      Future(validate) // Future catches exceptions thrown
        .flatMap {
          case errorMap if errorMap.nonEmpty => throw ValidationErrors(errorMap)
          case _                             =>
            val conn = conn0.asInstanceOf[DatomicConn_JVM]
            conn.transact_async(getStmts(conn))
        }
    }
    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }
    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
    }
    override def validate(implicit conn0: Connection): Map[String, Seq[String]] = {
      val conn                              = conn0.asInstanceOf[DatomicConn_JVM]
      val proxy                             = conn.proxy
      val db                                = conn.peerConn.db()
      val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
        val a = s":${attr.ns}/${attr.attr}"
        try {
          val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
          if (curValues.isEmpty) {
            throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
              s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
          }
          val vs = ListBuffer.empty[Any]
          curValues.forEach(row => vs.addOne(row.get(0)))
          vs.toSet
        } catch {
          case e: MoleculeError => throw e
          case t: Throwable     => throw ExecutionError(
            s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
        }
      }
      ModelValidation(
        proxy.nsMap,
        proxy.attrMap,
        false,
        Some(getCurSetValues)
      ).check(update.elements)
    }
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: DatomicDelete) extends Transaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(getStmts(conn))
    }
    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }
    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
    }
  }


  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: ExecutionContext): Future[Unit] = {
    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
  }
}
