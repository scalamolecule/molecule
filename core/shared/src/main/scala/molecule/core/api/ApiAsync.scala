package molecule.core.api

import molecule.base.error.{InsertError, MoleculeError}
import scala.concurrent.{ExecutionContext, Future}

trait ApiAsync {

  trait QueryApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]
    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)]
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
  }


  trait Transaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
    def validate(implicit conn: Connection): Map[String, Seq[String]] = ???
  }

  trait InsertTransaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
    def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = ???
  }

  trait Inspectable {
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }
}
