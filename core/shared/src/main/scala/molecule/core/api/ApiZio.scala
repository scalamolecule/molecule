package molecule.core.api

import molecule.base.error._
import molecule.core.spi.{Conn, TxReport}
import zio.ZIO

trait ApiZio {

  trait QueryApiZio[Tpl] {
    def get: ZIO[Conn, MoleculeError, List[Tpl]]
    def subscribe(callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }
  trait QueryOffsetApiZio[Tpl] {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }
  trait QueryCursorApiZio[Tpl] {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }

  trait SaveApiZio {
    def transact: ZIO[Conn, MoleculeError, TxReport]
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }

  trait InsertApiZio {
    def transact: ZIO[Conn, MoleculeError, TxReport]
    def validate: ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }

  trait UpdateApiZio {
    def transact: ZIO[Conn, MoleculeError, TxReport]
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }

  trait DeleteApiZio {
    def transact: ZIO[Conn, MoleculeError, TxReport]
    def inspect: ZIO[Conn, MoleculeError, Unit]
  }
}