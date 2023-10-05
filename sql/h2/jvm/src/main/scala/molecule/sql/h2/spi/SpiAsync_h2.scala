package molecule.sql.h2.spi

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.util.ModelUtils
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiAsync_h2 extends SpiAsync with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = Future {
    SpiSync_h2.query_get(q)
  }
  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.query_subscribe(q, callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.query_unsubscribe(q)
  }
  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.query_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = Future {
    SpiSync_h2.queryOffset_get(q)
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.queryOffset_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = Future {
    SpiSync_h2.queryCursor_get(q)
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.queryCursor_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }


  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_h2.save_transact(save)
  }
  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.save_inspect(save.copy(elements = noKeywords(save.elements, Some(conn.proxy))))
  }
  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    SpiSync_h2.save_validate(save.copy(elements = noKeywords(save.elements, Some(conn.proxy))))
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_h2.insert_transact(insert)
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.insert_inspect(insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy))))
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = Future {
    SpiSync_h2.insert_validate(insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy))))
  }


  override def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_h2.update_transact(update)
  }
  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.update_inspect(update.copy(elements = noKeywords(update.elements, Some(conn.proxy))))
  }
  override def update_validate(update: Update)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    SpiSync_h2.update_validate(update.copy(elements = noKeywords(update.elements, Some(conn.proxy))))
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_h2.delete_transact(delete)
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_h2.delete_inspect(delete.copy(elements = noKeywords(delete.elements, Some(conn.proxy))))
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    SpiSync_h2.fallback_rawQuery(query, withNulls, doPrint)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_h2.fallback_rawTransact(txData, doPrint)
  }
}
