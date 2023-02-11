package molecule.core.api

import molecule.base.util.exceptions.MoleculeError
import zio.{Chunk, ZIO}

trait ApiTypeLevel {

  trait DeleteApi {
    def transact: ZIO[Connection, MoleculeError, TxReport] = ???
  }

  trait InsertApi {
    def transact: ZIO[Connection, MoleculeError, TxReport] = ???
  }

  trait QueryMethods[Tpl] {
    def get: ZIO[Connection, MoleculeError, Chunk[Tpl]]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryOffsetMethods[Tpl] {
    def get: ZIO[Connection, MoleculeError, (Chunk[Tpl], Int, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryCursorMethods[Tpl] {
    def get: ZIO[Connection, MoleculeError, (Chunk[Tpl], String, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }


  trait SaveApi {
    def transact: ZIO[Connection, MoleculeError, TxReport] = ???
  }

  trait UpdateApi {
    def transact: ZIO[Connection, MoleculeError, TxReport] = ???
  }
}
