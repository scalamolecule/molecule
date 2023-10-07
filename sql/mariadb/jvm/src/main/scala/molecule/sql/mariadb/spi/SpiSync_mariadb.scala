package molecule.sql.mariadb.spi

import java.sql
import java.sql.ResultSet
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.transaction._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.ResultSetImpl
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import molecule.sql.mariadb.transaction._
import scala.collection.mutable.ListBuffer


object SpiSync_mariadb extends SpiSync_mariadb

trait SpiSync_mariadb extends SpiSyncBase {

  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_mariadb[Tpl](elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_mariadb {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_mariadb(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getData(update.elements)
  }

  override def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy, isUpsert) with Update_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getData(elements)
  }

  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn     = conn0.asInstanceOf[JdbcConn_JVM]
    val resolver = (query: String) => {
      val ps        = conn.sqlConn.prepareStatement(
        query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
      )
      val resultSet = ps.executeQuery()
      new ResultSetImpl(resultSet)
    }
    validateUpdateSet2(conn.proxy, update.elements, update.isUpsert, resolver)
  }

  override def delete_getData(conn: JdbcConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_mariadb {
      override lazy val sqlConn = conn.sqlConn
    }.getData(delete.elements, conn.proxy.nsMap)
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn): List[List[Any]] = {
    val c             = conn.asInstanceOf[JdbcConn_JVM].sqlConn
    val statement     = c.createStatement()
    val resultSet     = statement.executeQuery(query)
    val rsmd          = resultSet.getMetaData
    val columnsNumber = rsmd.getColumnCount

    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(query)

    val rows = ListBuffer.empty[List[Any]]
    val row  = ListBuffer.empty[Any]

    def value[T](rawValue: T, baseTpe: String, isStr: Boolean = false): (Any, String) = {
      val isNull = resultSet.wasNull()
      if (withNulls && isNull) {
        row += null
        ("null", baseTpe)
      } else if (!isNull) {
        val value = if (isStr) rawValue.toString.replace(29.toChar, ',') else rawValue
        row += value
        (value, baseTpe)
      } else {
        ("null", baseTpe)
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
          case "LONGTEXT" | "VARCHAR" => value(resultSet.getString(paramIndex), "String", true)
          case "INT"                  => value(resultSet.getInt(paramIndex), "Int")
          case "BIGINT"               => value(resultSet.getLong(paramIndex), "Long")
          case "REAL"                 => value(resultSet.getFloat(paramIndex), "Float")
          case "DOUBLE"               => value(resultSet.getDouble(paramIndex), "Double")
          case "TINYINT"              => value(resultSet.getBoolean(paramIndex), "Boolean/Byte")
          case "DECIMAL"              => value(resultSet.getDouble(paramIndex), "BigInt/Decimal")
          case "DATETIME"             => value(resultSet.getDate(paramIndex), "Date")
          case "TINYTEXT"             => value(resultSet.getString(paramIndex), "UUID")
          case "TEXT"                 => value(resultSet.getString(paramIndex), "URI", true)
          case "SMALLINT"             => value(resultSet.getShort(paramIndex), "Short")
          case "CHAR"                 => value(resultSet.getShort(paramIndex), "Char")
          case "JSON"                 =>

            value(resultSet.getString(paramIndex), "String", true)
          case other                  => throw new Exception(
            s"Unexpected/not yet considered sql result type from raw query: " + other
          )
        }
        if (withNulls && resultSet.wasNull()) {
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