package molecule.core.api

import scala.concurrent.{ExecutionContext, Future}

trait ApiAsync {

  trait QueryApi[Tpl] {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }
  trait QueryOffsetApi[Tpl] {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)]
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }
  trait QueryCursorApi[Tpl] {
    def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }


  trait SaveApi {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }

  trait InsertApi {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }

  trait UpdateApi {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }

  trait DeleteApi {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }
}
