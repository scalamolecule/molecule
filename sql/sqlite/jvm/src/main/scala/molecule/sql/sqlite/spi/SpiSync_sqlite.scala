package molecule.sql.sqlite.spi

import java.sql
import java.sql.{Connection, ResultSet}
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.transaction._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.core.transaction.strategy.TxStrategy
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite
import molecule.sql.sqlite.transaction._
import scala.collection.mutable.ListBuffer


object SpiSync_sqlite extends SpiSync_sqlite

trait SpiSync_sqlite extends SpiSyncBase {

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_sqlite(elements)

  override protected lazy val defaultValues = "DEFAULT VALUES"


  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_sqlite {
      override lazy val sqlConn = conn.sqlConn
    }.getSaveData(save.elements)
  }
  override def save_getData2(save: Save, conn: JdbcConn_JVM): TxStrategy = {
    new ResolveSave with Save_sqlite {
      override lazy val sqlConn = conn.sqlConn
    }.getSaveStrategy(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_sqlite {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getInsertData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_sqlite(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_sqlite {
      override lazy val sqlConn = conn.sqlConn
    }.getUpdateData(update.elements)
  }

  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn            = conn0.asInstanceOf[JdbcConn_JVM]
    val query2resultSet = (query: String) => {
      val ps = conn.sqlConn.prepareStatement(
        query,
        ResultSet.TYPE_FORWARD_ONLY,
        ResultSet.CONCUR_READ_ONLY
      )
      conn.resultSet(ps.executeQuery())
    }
    validateUpdateSet_sqlite(conn.proxy, update.elements, query2resultSet)
  }


  override def delete_transact(delete0: Delete)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val delete = delete0.copy(elements = noKeywords(delete0.elements, Some(conn.proxy)))
    if (delete.doInspect) {
      delete_inspect(delete)
    }
    delete_getExecutioner(conn, delete).fold(TxReport(Nil)) { executions =>
      // Turn of foreign key constraints temporarily to allow multiple deletions
      setFkConstraint(conn.sqlConn, 0)

      // Commit deletions
      val txReport = conn.atomicTransaction(executions)

      // Turn on foreign key constraints again
      setFkConstraint(conn.sqlConn, 1)

      conn.callback(delete.elements, true)
      txReport
    }
  }

  protected def setFkConstraint(sqlConn: Connection, value: Int): Unit = {
    sqlConn.setAutoCommit(true)
    val a = sqlConn.prepareStatement(s"PRAGMA foreign_keys = $value")
    a.executeUpdate()
    a.close()
  }

  override def delete_getInspectionData(conn: JdbcConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_sqlite {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteDataForInspection(delete.elements, conn.proxy.nsMap)
  }


  override def delete_getExecutioner(
    conn: JdbcConn_JVM, delete: Delete
  ): Option[() => List[Long]] = {
    new ResolveDelete with Delete_sqlite {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteExecutioner(delete.elements, conn.proxy.nsMap, "", "", "")
  }


  // Util --------------------------------------

  override def fallback_rawTransact(
    stmt: String,
    doPrint: Boolean = false
  )(implicit conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(stmt)

    val stmtReturningIds = if (stmt.trim.toLowerCase.endsWith("returning id"))
      stmt
    else
      stmt + " RETURNING id"
    val ps               = conn.transactionStmt(stmtReturningIds)
    ps.addBatch()
    ps.execute()

    var ids       = List.empty[Long]
    val resultSet = ps.getResultSet
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1)
    }
    ps.close()
    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }
}