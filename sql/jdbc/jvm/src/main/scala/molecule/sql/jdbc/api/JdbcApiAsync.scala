package molecule.sql.jdbc.api

import java.sql.Statement
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.facade.JdbcConn_JVM
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}


trait JdbcApiAsync
  extends JVMJdbcApiBase
    with SubscriptionStarter
    with JdbcAsyncApiBase
    with ApiAsync
    with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .getListFromOffset_async(conn.asInstanceOf[JdbcConn_JVM], ec).map(_._1)
    }

    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      val datomicConn = conn.asInstanceOf[JdbcConn_JVM]
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .subscribe(datomicConn, getWatcher(datomicConn), callback)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
        .getListFromOffset_async(conn.asInstanceOf[JdbcConn_JVM], ec)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }


  implicit class datomicQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      JdbcQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
        .getListFromCursor_async(conn.asInstanceOf[JdbcConn_JVM], ec)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiAsync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        //        conn.asInstanceOf[SqlConn_JVM].transact_async(getStmts)
        Future {
          val sqlConn = conn.asInstanceOf[JdbcConn_JVM].sqlConn

          val sqlIns =
            """insert into Ns(int)
                      values (?)"""
          val stmt   = sqlConn.prepareStatement(sqlIns, Statement.RETURN_GENERATED_KEYS)
          stmt.setInt(1, 3)
          val affectedRows = stmt.executeUpdate()
          if (affectedRows == 0) {
            throw ModelError("Couldn't save")
          }
          val eids          = ListBuffer.empty[Long]
          val generatedKeys = stmt.getGeneratedKeys
          while (generatedKeys.next()) {
            eids += generatedKeys.getLong(1)
          }
          stmt.close()

          //          println("eids: " + eids)

          TxReport(0, eids.toList)
        }
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("SAVE", save.elements, getStmts)
    }

    private def getStmts: Data = {
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
    }
  }

  //  public void create(User user) throws SQLException {
  //    try (
  //      Connection connection = dataSource.getConnection();
  //    PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
  //      Statement.RETURN_GENERATED_KEYS);
  //    )
  //    {
  //      statement.setString(1, user.getName());
  //      statement.setString(2, user.getPassword());
  //      statement.setString(3, user.getEmail());
  //      // ...
  //
  //      int affectedRows = statement.executeUpdate();
  //
  //      if (affectedRows == 0) {
  //        throw new SQLException("Creating user failed, no rows affected.");
  //      }
  //
  //      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
  //        if (generatedKeys.next()) {
  //          user.setId(generatedKeys.getLong(1));
  //        }
  //        else {
  //          throw new SQLException("Creating user failed, no ID obtained.");
  //        }
  //      }
  //    }
  //  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        conn.asInstanceOf[JdbcConn_JVM].transact_async(getStmts(conn.proxy))
      } else {
        Future.failed(InsertErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("INSERT", insert.elements, getStmts(conn.proxy))
    }

    private def getStmts(proxy: ConnProxy): Data = {
      (new InsertExtraction with Insert_stmts)
        .getStmts(proxy.nsMap, insert.elements, insert.tpls)
    }

    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiAsync[Tpl](update: Update) extends UpdateTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[JdbcConn_JVM]
        conn.transact_async(getStmts(conn))
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    }

    private def getStmts(conn: JdbcConn_JVM): Data = {
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      validateUpdate(conn, update.elements)
    }
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val conn = conn0.asInstanceOf[JdbcConn_JVM]
      conn.transact_async(getStmts(conn))
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    }

    private def getStmts(conn: JdbcConn_JVM): Data = {
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
    }
  }


  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: ExecutionContext): Future[Unit] = {
    //    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
    ???
  }
}
