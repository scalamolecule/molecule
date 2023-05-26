package molecule.core.api

import molecule.base.error.InsertError
import molecule.core.spi.{Conn, TxReport}
import scala.concurrent.{Future, ExecutionContext => EC}

trait ApiAsync {

  trait QueryApiAsync[Tpl] {
    def get(implicit conn: Conn, ec: EC): Future[List[Tpl]]
    def subscribe(callback: List[Tpl] => Unit)
                 (implicit conn: Conn, ec: EC): Future[Unit]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }
  trait QueryOffsetApiAsync[Tpl] {
    def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }
  trait QueryCursorApiAsync[Tpl] {
    def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }

  trait SaveApiAsync {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport]
    def validate(implicit conn: Conn): Map[String, Seq[String]]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }

  trait InsertApiAsync {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport]
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }

  trait UpdateApiAsync {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport]
    def validate(implicit conn: Conn): Map[String, Seq[String]]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }

  trait DeleteApiAsync {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport]
    def inspect(implicit conn: Conn, ec: EC): Future[Unit]
  }
}