package molecule.sql.postgres.spi

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.util.ModelUtils
import molecule.sql.postgres.sync.getModel2SqlQuery
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiAsync_postgres extends SpiAsync with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = Future {
    //    // Check that rawQuery can handle all SPI queries
    //    val q1  = q.copy(elements = noKeywords(q.elements, Some(conn.proxy)))
    //    val m2q = getModel2SqlQuery[Tpl](q1.elements)
    //    val qu  = m2q.getSqlQuery(q1.elements, None, None)
    //    SpiSync_postgres.fallback_rawQuery(qu, true)

    SpiSync_postgres.query_get(q)
  }
  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.query_subscribe(q, callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.query_unsubscribe(q)
  }
  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.query_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = Future {
    SpiSync_postgres.queryOffset_get(q)
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.queryOffset_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = Future {
    SpiSync_postgres.queryCursor_get(q)
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.queryCursor_inspect(q.copy(elements = noKeywords(q.elements, Some(conn.proxy))))
  }


  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.save_transact(save)
  }
  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.save_inspect(save.copy(elements = noKeywords(save.elements, Some(conn.proxy))))
  }
  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    SpiSync_postgres.save_validate(save.copy(elements = noKeywords(save.elements, Some(conn.proxy))))
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.insert_transact(insert)
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.insert_inspect(insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy))))
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = Future {
    SpiSync_postgres.insert_validate(insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy))))
  }


  override def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.update_transact(update)
  }
  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.update_inspect(update.copy(elements = noKeywords(update.elements, Some(conn.proxy))))
  }
  override def update_validate(update: Update)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    SpiSync_postgres.update_validate(update.copy(elements = noKeywords(update.elements, Some(conn.proxy))))
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.delete_transact(delete)
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.delete_inspect(delete.copy(elements = noKeywords(delete.elements, Some(conn.proxy))))
  }

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    SpiSync_postgres.fallback_rawQuery(query, debug)
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.fallback_rawTransact(txData, debug)
  }
}
