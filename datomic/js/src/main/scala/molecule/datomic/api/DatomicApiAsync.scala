package molecule.datomic.api

import boopickle.Default._
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}


trait DatomicApiAsync extends DatomicAsyncApiBase with ApiAsync with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.query[Tpl](conn.proxy, q.elements, q.limit).future
    }
    override def subscribe(callback: List[Tpl] => Unit)(implicit conn0: Connection): Unit = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.subscribe[Tpl](conn.proxy, q.elements, q.limit, callback)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.limit, q.offset).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }

  implicit class datomicQueryCursorApiAsync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.limit, q.cursor).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiAsync[Tpl](save: DatomicSave) extends SaveTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      Future(validate) // Future catches exceptions thrown
        .flatMap {
          case errorMap if errorMap.nonEmpty => throw ValidationErrors(errorMap)
          case _                             => conn.rpc.save(conn.proxy, save.elements).future
        }
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("SAVE", save.elements)
    }
    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.nsMap, proxy.attrMap, "save").check(save.elements)
    }
  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[DatomicInsert_JS]
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      Future(validate) // Future catches exceptions thrown
        .flatMap {
          case insertErrors if insertErrors.nonEmpty => throw InsertErrors(insertErrors)
          case _                                     =>
            val conn                      = conn0.asInstanceOf[DatomicConn_JS]
            val (tplElements, txElements) = splitElements(insert.elements)
            val tplsSerialized            = PickleTpls(tplElements, true).pickle(Right(insert.tpls))
            conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
        }
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("INSERT", insert.elements)
    }
    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiAsync[Tpl](update: DatomicUpdate) extends UpdateTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      Future(validate) // Future catches exceptions thrown
        .flatMap {
          case errorMap if errorMap.nonEmpty => throw ValidationErrors(errorMap)
          case _                             =>
            val conn = conn0.asInstanceOf[DatomicConn_JS]
            conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
        }
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("UPDATE", update.elements)
    }
    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      // Only generic model validation on JS platform
      ModelValidation(proxy.nsMap, proxy.attrMap, "update").check(update.elements)
    }
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: DatomicDelete) extends DeleteTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.delete(conn.proxy, delete.elements).future
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
