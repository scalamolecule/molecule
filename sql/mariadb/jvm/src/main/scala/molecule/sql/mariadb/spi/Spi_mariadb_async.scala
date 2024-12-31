package molecule.sql.mariadb.spi

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, Spi_async, TxReport}
import molecule.core.util.ModelUtils
import scala.concurrent.{Future, ExecutionContext => EC}

trait Spi_mariadb_async extends Spi_async with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = Future {
    //    // Check that rawQuery can handle all SPI queries
    //    val q1  = q.copy(elements = noKeywords(q.elements, Some(conn.proxy)))
    //    val m2q = getModel2SqlQuery(q1.elements)
    //    val qu  = m2q.getSqlQuery(q1.elements, None, None)
    //    SpiSync_mariadb.fallback_rawQuery(qu, true, true)

    Spi_mariadb_sync.query_get(q)
  }
  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.query_subscribe(q, callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.query_unsubscribe(q)
  }
  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.query_inspect(q)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = Future {
    Spi_mariadb_sync.queryOffset_get(q)
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.queryOffset_inspect(q)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = Future {
    Spi_mariadb_sync.queryCursor_get(q)
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.queryCursor_inspect(q)
  }


  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    Spi_mariadb_sync.save_transact(save)
  }
  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.save_inspect(save)
  }
  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    Spi_mariadb_sync.save_validate(save)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    Spi_mariadb_sync.insert_transact(insert)
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.insert_inspect(insert)
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = Future {
    Spi_mariadb_sync.insert_validate(insert)
  }


  override def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    Spi_mariadb_sync.update_transact(update)
  }
  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.update_inspect(update)
  }
  override def update_validate(update: Update)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = Future {
    Spi_mariadb_sync.update_validate(update)
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    Spi_mariadb_sync.delete_transact(delete)
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    Spi_mariadb_sync.delete_inspect(delete)
  }

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    Spi_mariadb_sync.fallback_rawQuery(query, debug)
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    Spi_mariadb_sync.fallback_rawTransact(txData, debug)
  }
}
