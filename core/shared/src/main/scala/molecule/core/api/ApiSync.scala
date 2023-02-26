package molecule.core.api

trait ApiSync extends PrintInspect {

  trait QueryApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection): List[Tpl]
  }
  trait QueryOffsetApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection): (List[Tpl], Int, Boolean)
  }
  trait QueryCursorApi[Tpl] extends Inspectable {
    def get(implicit conn: Connection): (List[Tpl], String, Boolean)
  }


  trait Transaction extends Inspectable {
    def transact(implicit conn: Connection): TxReport
  }

  trait Inspectable {
    def inspect(implicit conn: Connection): Unit
  }
}
