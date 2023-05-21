package molecule.core.api

import molecule.base.error.InsertError
import molecule.core.action.Insert
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


  trait SaveTransaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
    def validate(implicit conn: Connection): Map[String, Seq[String]]
  }

  //  abstract class InsertTransaction extends InsertTransaction
  trait InsertTransaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
    def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])]
  }
//  class InsertTx(insert: Insert) {
//    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???
//    def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = ???
//  }

  trait UpdateTransaction extends SaveTransaction

  trait DeleteTransaction extends Inspectable {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
  }

  trait Inspectable {
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
  }
}

//object ApiAsync extends ApiAsync

trait ApiAsync2  {

  def insert_transact(insert: Insert)
                     (implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

//  trait InsertTransactionProxy extends ApiAsync.InsertTransaction {
//  trait InsertTransactionProxy  { self: ApiAsync.InsertTransaction =>
//    def transact_(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
////      ApiAsync.InsertTransaction
//      transact
//    }
//  }
}
