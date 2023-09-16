package molecule.sql.postgres.spi

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiAsync_postgres extends SpiAsync {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = Future {
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
    SpiSync_postgres.query_inspect(q)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = Future {
    SpiSync_postgres.queryOffset_get(q)
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.queryOffset_inspect(q)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = Future {
    SpiSync_postgres.queryCursor_get(q)
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.queryCursor_inspect(q)
  }


  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.save_transact(save)
  }
  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.save_inspect(save)
  }
  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiSync_postgres.save_validate(save)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.insert_transact(insert)
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.insert_inspect(insert)
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    SpiSync_postgres.insert_validate(insert)
  }


  override def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.update_transact(update)
  }
  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.update_inspect(update)
  }
  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiSync_postgres.update_validate(update)
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.delete_transact(delete)
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = Future {
    SpiSync_postgres.delete_inspect(delete)
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    SpiSync_postgres.fallback_rawQuery(query, withNulls, doPrint)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn, ec: EC): Future[TxReport] = Future {
    SpiSync_postgres.fallback_rawTransact(txData, doPrint)
  }
}
