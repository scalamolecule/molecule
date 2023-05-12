package molecule.datalog.datomic.api

import boopickle.Default._
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}


trait DatomicApiAsync extends DatomicAsyncApiBase with ApiAsync with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      val conn  = conn0.asInstanceOf[DatomicConn_JS]
      val proxy = conn.proxy.copy(dbView = q.dbView)
      conn.rpc.query[Tpl](proxy, q.elements, q.limit).future
    }

    override def subscribe(callback: List[Tpl] => Unit)(implicit conn0: Connection): Unit = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.subscribe[Tpl](conn.proxy, q.elements, q.limit, callback)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }


  implicit class datomicQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      val conn  = conn0.asInstanceOf[DatomicConn_JS]
      val proxy = conn.proxy.copy(dbView = q.dbView)
      conn.rpc.queryOffset[Tpl](proxy, q.elements, q.limit, q.offset).future
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }


  implicit class datomicQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      val conn  = conn0.asInstanceOf[DatomicConn_JS]
      val proxy = conn.proxy.copy(dbView = q.dbView)
      conn.rpc.queryCursor[Tpl](proxy, q.elements, q.limit, q.cursor).future
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiAsync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[DatomicConn_JS]
        conn.rpc.save(conn.proxy, save.elements).future
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("SAVE", save.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.schema.nsMap, proxy.schema.attrMap, "save").validate(save.elements)
    }
  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        val conn                      = conn0.asInstanceOf[DatomicConn_JS]
        val (tplElements, txElements) = separateTxElements(insert.elements)
        val tplsSerialized            = PickleTpls(tplElements, true).pickle(Right(insert.tpls))
        conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
      } else {
        Future.failed(InsertErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("INSERT", insert.elements)
    }

    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }


  }


  implicit class datomicUpdateApiAsync[Tpl](update: Update) extends UpdateTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[DatomicConn_JS]
        conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("UPDATE", update.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      // Only generic model validation on JS platform
      ModelValidation(proxy.schema.nsMap, proxy.schema.attrMap, "update").validate(update.elements)
    }
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.delete(conn.proxy, delete.elements).future
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("DELETE", delete.elements)
    }
  }


  private def printInspectTx(label: String, elements: List[Element])
                            (implicit ec: ExecutionContext): Future[Unit] = {
    Future(printInspect("RPC " + label, elements))
  }
}
