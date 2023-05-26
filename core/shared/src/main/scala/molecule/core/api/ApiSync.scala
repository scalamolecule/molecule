package molecule.core.api

import molecule.base.error.InsertError
import molecule.core.spi.{Conn, TxReport}

trait ApiSync {

  trait QueryApiSync[Tpl] {
    def get(implicit conn: Conn): List[Tpl]
    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Conn): Unit
    def inspect(implicit conn: Conn): Unit
  }
  trait QueryOffsetApiSync[Tpl] {
    def get(implicit conn: Conn): (List[Tpl], Int, Boolean)
    def inspect(implicit conn: Conn): Unit
  }
  trait QueryCursorApiSync[Tpl] {
    def get(implicit conn: Conn): (List[Tpl], String, Boolean)
    def inspect(implicit conn: Conn): Unit
  }

  trait SaveApiSync {
    def transact(implicit conn: Conn): TxReport
    def validate(implicit conn: Conn): Map[String, Seq[String]]
    def inspect(implicit conn: Conn): Unit
  }

  trait InsertApiSync {
    def transact(implicit conn: Conn): TxReport
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])]
    def inspect(implicit conn: Conn): Unit
  }

  trait UpdateApiSync {
    def transact(implicit conn: Conn): TxReport
    def validate(implicit conn: Conn): Map[String, Seq[String]]
    def inspect(implicit conn: Conn): Unit
  }

  trait DeleteApiSync {
    def transact(implicit conn: Conn): TxReport
    def inspect(implicit conn: Conn): Unit
  }
}