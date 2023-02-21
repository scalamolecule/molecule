package molecule.core.api

import molecule.base.util.exceptions.MoleculeError
import zio.{Chunk, ZIO}

trait ApiZio {

  trait QueryApi[Tpl] {
    def get: ZIO[Connection, MoleculeError, List[Tpl]]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryOffsetApi[Tpl] {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryCursorApi[Tpl] {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }


  trait SaveApi {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }

  trait InsertApi {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }

  trait UpdateApi {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }

  trait DeleteApi {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }
}
