package molecule.sql.core.api

import molecule.base.error.{InsertError, ModelError}
import molecule.core.action.Insert
import molecule.core.api._
import molecule.core.action._


trait SqlApiSync extends ApiSync {

  def noSyncOnJSplatform = throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )

  implicit class datomicQueryApiSync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = noSyncOnJSplatform
    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }


  implicit class datomicSaveApiSync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def validate(implicit conn: Connection): Map[String, Seq[String]] = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicInsertApiSync[Tpl](insert: Insert) extends InsertTransaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicUpdateApiSync[Tpl](update: Update) extends UpdateTransaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def validate(implicit conn: Connection): Map[String, Seq[String]] = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicDeleteApiSync[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }
}
