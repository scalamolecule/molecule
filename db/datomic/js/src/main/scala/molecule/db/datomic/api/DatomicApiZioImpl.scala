package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.action.Insert
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.transaction.SaveExtraction
import molecule.db.datomic.action._
import zio._

trait DatomicApiZioImpl extends ApiZio {

  implicit class datomicQueryApiZio[Tpl](q: DatomicQueryImpl[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      //      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
      //        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
      ???
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryOffsetApiZio[Tpl](q: DatomicQueryImplOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      //      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
      //        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
      ???
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryCursorApiZio[Tpl](q: DatomicQueryImplCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      //      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
      //        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
      ???
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }


  implicit class datomicSaveApiZio[Tpl](save: DatomicSaveImpl) extends SaveApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = ???
//    override def transact: ZIO[Connection, MoleculeError, TxReport] = for {
//      stmts <- ZIO.succeed(new SaveExtraction() with Save_stmts).map(_.getStmts(save.elements))
//      conn <- ZIO.service[Connection]
//      txReport <- ZIO.fromFuture(_ => conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts))
//        .mapError {
//          case e: MoleculeError => e
//          case e: Throwable     => MoleculeError(e.toString, e)
//        }
//    } yield txReport
//    ???
  }

  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends InsertApi {
//    val insert = insert0.asInstanceOf[DatomicInsertImpl_JVM]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      //      val stmts = (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
      //      conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts)
      ???
    }
  }

  implicit class datomicUpdateApiZio[Tpl](update: DatomicUpdateImpl) extends UpdateApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      //      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      //      val stmts = (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
      //        .getStmts(conn, update.elements)
      //      conn.transact_async(stmts)
      ???
    }
  }

  implicit class datomicDeleteApiZio[Tpl](delete: DatomicDeleteImpl) extends DeleteApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      //      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      //      val stmts = (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
      //      conn.transact_async(stmts)
      ???
    }
  }
}
