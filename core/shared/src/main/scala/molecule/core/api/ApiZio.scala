package molecule.core.api

import molecule.base.error._
import zio.ZIO

trait ApiZio extends PrintInspect {

  trait QueryApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, List[Tpl]]
    def subscribe(callback: List[Tpl] => Unit): ZIO[Connection, MoleculeError, Unit]
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)]
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)]
  }


  trait SaveTransaction extends Inspectable {
    def transact: ZIO[Connection, MoleculeError, TxReport]
    def validate: ZIO[Connection, MoleculeError, Map[String, Seq[String]]]
  }

  trait InsertTransaction extends Inspectable {
    def transact: ZIO[Connection, MoleculeError, TxReport]
    def validate: ZIO[Connection, MoleculeError, Seq[(Int, Seq[InsertError])]]
  }

  trait UpdateTransaction extends SaveTransaction

  trait DeleteTransaction extends Inspectable {
    def transact: ZIO[Connection, MoleculeError, TxReport]
  }

  trait Inspectable {
    def inspect: ZIO[Connection, MoleculeError, Unit]
  }
}
