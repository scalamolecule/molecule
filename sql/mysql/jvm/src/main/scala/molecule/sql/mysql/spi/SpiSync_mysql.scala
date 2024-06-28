package molecule.sql.mysql.spi

import java.sql
import java.sql.ResultSet
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.transaction._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import molecule.sql.mysql.transaction._
import scala.collection.mutable.ListBuffer


object SpiSync_mysql extends SpiSync_mysql

trait SpiSync_mysql extends SpiSyncBase {

  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_mysql[Tpl](elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_mysql {
      override lazy val sqlConn = conn.sqlConn
    }.getSaveData(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_mysql {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getInsertData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_mysql(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mysql {
      override lazy val sqlConn = conn.sqlConn
    }.getUpdateData(update.elements)
  }

  override def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy, isUpsert) with Update_mysql {
      override lazy val sqlConn = conn.sqlConn
    }.getUpdateData(elements)
  }

  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn            = conn0.asInstanceOf[JdbcConn_JVM]
    val query2resultSet = (query: String) => {
      val ps        = conn.sqlConn.prepareStatement(
        query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
      )
      conn.resultSet(ps.executeQuery())
    }
    validateUpdateSet_json(conn.proxy, update.elements, query2resultSet)
  }

  override def delete_getInspectionData(conn: JdbcConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_mysql {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteDataForInspection(delete.elements, conn.proxy.nsMap)
  }

  override def delete_getExecutioner(conn: JdbcConn_JVM, delete: Delete): Option[() => List[Long]] = {
    new ResolveDelete with Delete_mysql {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteExecutioner(delete.elements, conn.proxy.nsMap, "SET FOREIGN_KEY_CHECKS", "0", "1")
  }

  override def fallback_rawQuery(
    query: String,
    debugFlag: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
    val c             = conn.asInstanceOf[JdbcConn_JVM].sqlConn
    val statement     = c.createStatement()
    val resultSet     = statement.executeQuery(query)
    val rsmd          = resultSet.getMetaData
    val columnsNumber = rsmd.getColumnCount

    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(query)

    val rows = ListBuffer.empty[List[Any]]
    val row  = ListBuffer.empty[Any]

    def value[T](rawValue: T, baseTpe: String, isStr: Boolean = false): (Any, String) = {
      if (resultSet.wasNull()) {
        row += null
        ("null", baseTpe)
      } else {
        val value = if (isStr) rawValue.toString.replace(29.toChar, ',') else rawValue
        row += value
        (value, baseTpe)
      }
    }

    def json(value0: String, baseTpe: String): (Any, String) = {
      if (resultSet.wasNull()) {
        row += null
        ("null", baseTpe)
      } else {
        val value = if (value0(1) == '"') {
          value0.substring(2, value0.length - 2).split("\", ?\"").toSet
        } else {
          value0.substring(1, value0.length - 1).split(", ?").toSet
        }
        row += value
        (value, baseTpe)
      }
    }

    while (resultSet.next) {
      debug("-----------------------------------------------")
      var paramIndex = 1
      row.clear()
      while (paramIndex <= columnsNumber) {
        val col                = rsmd.getColumnName(paramIndex)
        val sqlType            = rsmd.getColumnTypeName(paramIndex)
        val (columnValue, tpe) = sqlType match {
          case "LONGTEXT"   => value(resultSet.getString(paramIndex), "String", true)
          case "INT"        => value(resultSet.getInt(paramIndex), "Int")
          case "BIGINT"     => value(resultSet.getLong(paramIndex), "Long")
          case "DOUBLE"     => value(resultSet.getDouble(paramIndex), "Float/Double")
          case "BIT"        => value(resultSet.getByte(paramIndex), "Boolean")
          case "DECIMAL"    => value(resultSet.getDouble(paramIndex), "BigInt/Decimal")
          case "TINYTEXT"   => value(resultSet.getString(paramIndex), "java.time text, UUID")
          case "TEXT"       => value(resultSet.getString(paramIndex), "URI", true)
          case "TINYINT"    => value(resultSet.getByte(paramIndex), "Byte")
          case "SMALLINT"   => value(resultSet.getShort(paramIndex), "Short")
          case "CHAR"       => value(resultSet.getString(paramIndex), "Char")
          case "MEDIUMTEXT" => value(resultSet.getString(paramIndex), "MEDIUMTEXT")
          case "VARCHAR"    => value(resultSet.getString(paramIndex), "VARCHAR")

          case "JSON" => json(resultSet.getString(paramIndex), "JSON")

          case other => throw new Exception(
            s"Unexpected sql result type from raw query: " + other
          )
        }
        if (resultSet.wasNull()) {
          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  " + columnValue)
        } else if (!resultSet.wasNull()) {
          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  " + columnValue)
        }
        paramIndex += 1
      }
      rows += row.toList
    }
    rows.toList
  }
}