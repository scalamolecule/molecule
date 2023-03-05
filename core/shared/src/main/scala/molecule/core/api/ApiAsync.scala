package molecule.core.api

import scala.concurrent.{ExecutionContext, Future}

trait ApiAsync {

  trait QueryApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]
//    def subscribe(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)]
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
  }
//  trait QuerySubscriptionApi[Tpl] {
//    def subscribe(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
//  }


  trait Transaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }

  trait Inspectable {
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }
}
