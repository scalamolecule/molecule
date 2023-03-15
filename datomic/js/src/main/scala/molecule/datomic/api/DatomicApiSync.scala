package molecule.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.action.Insert
import molecule.core.api.{ApiSync, Connection, TxReport}
import molecule.datomic.action._


trait DatomicApiSync extends ApiSync {

  def noSyncOnJSplatform = throw MoleculeError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )

  implicit class datomicQueryApiSync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = noSyncOnJSplatform
    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }


  implicit class datomicSaveApiSync[Tpl](save: DatomicSave) extends Transaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicInsertApiSync[Tpl](insert: Insert) extends Transaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicUpdateApiSync[Tpl](update: DatomicUpdate) extends Transaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }

  implicit class datomicDeleteApiSync[Tpl](delete: DatomicDelete) extends Transaction {
    override def transact(implicit conn: Connection): TxReport = noSyncOnJSplatform
    override def inspect(implicit conn: Connection): Unit = noSyncOnJSplatform
  }
}
