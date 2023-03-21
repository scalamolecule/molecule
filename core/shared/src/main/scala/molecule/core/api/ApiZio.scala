package molecule.core.api

import molecule.base.util.exceptions.ExecutionError
import zio.ZIO

trait ApiZio extends PrintInspect {

  trait QueryApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, ExecutionError, List[Tpl]]
    def subscribe(callback: List[Tpl] => Unit): ZIO[Connection, ExecutionError, Unit]
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, ExecutionError, (List[Tpl], Int, Boolean)]
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get: ZIO[Connection, ExecutionError, (List[Tpl], String, Boolean)]
  }


  trait Transaction extends Inspectable {
    def transact: ZIO[Connection, ExecutionError, TxReport]
  }

  trait Inspectable {
    def inspect: ZIO[Connection, ExecutionError, Unit]
  }
}
