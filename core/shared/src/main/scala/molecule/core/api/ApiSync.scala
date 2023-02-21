package molecule.core.api

trait ApiSync {

  trait QueryApi[Tpl] {
    def get(implicit conn: Connection): List[Tpl]
    def inspect(implicit conn: Connection): Unit
  }
  trait QueryOffsetApi[Tpl] {
    def get(implicit conn: Connection): (List[Tpl], Int, Boolean)
    def inspect(implicit conn: Connection): Unit
  }
  trait QueryCursorApi[Tpl] {
    def get(implicit conn: Connection): (List[Tpl], String, Boolean)
    def inspect(implicit conn: Connection): Unit
  }


  trait SaveApi {
    def transact(implicit conn: Connection): TxReport
  }

  trait InsertApi {
    def transact(implicit conn: Connection): TxReport
  }

  trait UpdateApi {
    def transact(implicit conn: Connection): TxReport
  }

  trait DeleteApi {
    def transact(implicit conn: Connection): TxReport
  }
}
