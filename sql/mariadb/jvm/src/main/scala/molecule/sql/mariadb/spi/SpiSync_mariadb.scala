package molecule.sql.mariadb.spi

import java.sql
import java.sql.ResultSet
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.transaction._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.core.transaction.strategy.TxStrategy
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import molecule.sql.mariadb.transaction._


object SpiSync_mariadb extends SpiSync_mariadb

trait SpiSync_mariadb extends SpiSyncBase {

  override def getModel2SqlQuery(elements: List[Element]) =
    new Model2SqlQuery_mariadb(elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): TxStrategy = {
    new ResolveSave with Save_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getSaveStrategy(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_mariadb {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getInsertData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_mariadb(idsModel).getSqlQuery(Nil, None, None, None)
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getUpdateData(update.elements)
  }

  override def update_validate(
    update: Update)
    (implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn            = conn0.asInstanceOf[JdbcConn_JVM]
    val query2resultSet = (query: String) => {
      val ps = conn.sqlConn.prepareStatement(
        query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
      )
      conn.resultSet(ps.executeQuery())
    }
    validateUpdateSet_json(conn.proxy, update.elements, query2resultSet)
  }

  override def delete_getExecutioner(
    conn: JdbcConn_JVM, delete: Delete
  ): Option[() => List[Long]] = {
    new ResolveDelete with Delete_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteExecutioner(
      delete.elements, conn.proxy.nsMap, "SET FOREIGN_KEY_CHECKS", "0", "1"
    )
  }

  override def delete_getInspectionData(
    conn: JdbcConn_JVM, delete: Delete
  ): Data = {
    new ResolveDelete with Delete_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteDataForInspection(delete.elements, conn.proxy.nsMap)
  }
}