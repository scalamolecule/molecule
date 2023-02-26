package molecule.core.api

import molecule.base.util.exceptions.MoleculeError
import zio.ZIO

trait ApiZio extends PrintInspect {

  trait QueryApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, List[Tpl]]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)]
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }


  trait Transaction extends Inspectable {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }

  trait Inspectable {
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
}
