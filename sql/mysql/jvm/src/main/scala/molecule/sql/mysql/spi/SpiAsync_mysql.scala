package molecule.sql.mysql.spi

import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.action._
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.util.ModelUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiAsync_mysql extends SpiAsync with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = Future {
    SpiSync_mysql.query_get(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }
  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.query_subscribe(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))), callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.query_unsubscribe(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }
  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.query_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = Future {
    SpiSync_mysql.queryOffset_get(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.queryOffset_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = Future {
    SpiSync_mysql.queryCursor_get(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.queryCursor_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }


  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    val proxy  = conn.proxy
    val errors = TxModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
    if (errors.isEmpty) {
      SpiSync_mysql.save_transact(save.copy(elements = noKeywords(save.elements, Some(proxy))))
    } else {
      throw ValidationErrors(errors)
    }
  }
  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.save_inspect(save)
  }
  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiSync_mysql.save_validate(save)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    val errors = InsertValidation.validate(conn, insert.elements, insert.tpls)
    if (errors.isEmpty) {
      SpiSync_mysql.insert_transact(insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy))))
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.insert_inspect(insert)
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    SpiSync_mysql.insert_validate(insert)
  }


  override def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    val errors = update_validate(update)
    if (errors.isEmpty) {
      SpiSync_mysql.update_transact(update.copy(elements = noKeywords(update.elements, Some(conn.proxy))))
    } else {
      throw ValidationErrors(errors)
    }
  }
  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.update_inspect(update)
  }
  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiSync_mysql.update_validate(update)
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_mysql.delete_transact(delete.copy(elements = noKeywords(delete.elements, Some(conn.proxy))))
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_mysql.delete_inspect(delete)
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    SpiSync_mysql.fallback_rawQuery(query, withNulls, doPrint)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_mysql.fallback_rawTransact(txData, doPrint)
  }
}
