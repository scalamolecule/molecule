package molecule.sql.h2.spi

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
import molecule.sql.h2.query.Model2SqlQuery_h2
import molecule.sql.h2.transaction._
import scala.collection.mutable.ListBuffer


object SpiSync_h2 extends SpiSync_h2

trait SpiSync_h2 extends SpiSyncBase {

  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_h2[Tpl](elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_h2 {
      override lazy val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_h2 {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_h2(idsModel).getSqlQuery(Nil, None, None)
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_h2 {
      override lazy val sqlConn = conn.sqlConn
    }.getData(update.elements)
  }

  override def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy, isUpsert) with Update_h2 {
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
    validateUpdateSet(conn.proxy, update.elements, update.isUpsert, resolver)
  }

  override def delete_getData(conn: JdbcConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_h2 {
      override lazy val sqlConn = conn.sqlConn
    }.getData(delete.elements, conn.proxy.nsMap)
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

    def value[T](rawValue: T, baseTpe: String): String = {
      if (resultSet.wasNull()) {
        row += null
      } else {
        row += rawValue
      }
      baseTpe
    }
    def array(n: Int, baseTpe: String): String = {
      val arr = resultSet.getArray(n)
      if (resultSet.wasNull()) {
        row += null
      } else {
        row += arr.getArray.asInstanceOf[Array[_]].toSet
      }
      s"Set[$baseTpe]"
    }

    def nestedArray(n: Int, baseTpe: String): String = {
      val arr = resultSet.getArray(n).getResultSet
      if (arr.wasNull()) {
        row += null
      } else {
        arr.next()
        row += arr.getArray(2).getArray.asInstanceOf[Array[_]].toSet
      }
      s"Set[$baseTpe]"
    }

    while (resultSet.next) {
      debug("-----------------------------------------------")
      var n = 1
      row.clear()
      while (n <= columnsNumber) {
        val col         = rsmd.getColumnName(n)
        val sqlType     = rsmd.getColumnTypeName(n)

        val tpe         = sqlType match {
          case "CHARACTER VARYING" => value(resultSet.getString(n), "String/URI")
          case "INTEGER"           => value(resultSet.getInt(n), "Int")
          case "BIGINT"            => value(resultSet.getLong(n), "Long")
          case "REAL"              => value(resultSet.getFloat(n), "Float")
          case "DOUBLE PRECISION"  => value(resultSet.getDouble(n), "Double")
          case "BOOLEAN"           => value(resultSet.getBoolean(n), "Boolean")
          case "DECIMAL"           => value(resultSet.getDouble(n), "BigInt/Decimal")
          case "DATE"              => value(resultSet.getLong(n), "Long")
          case "UUID"              => value(resultSet.getString(n), "UUID")
          case "TINYINT"           => value(resultSet.getShort(n), "Byte")
          case "SMALLINT"          => value(resultSet.getShort(n), "Short")
          case "CHARACTER"         => value(resultSet.getString(n), "Char")

          case "NUMERIC"  => value(resultSet.getString(n), "NUMERIC")
          case "DECFLOAT" => value(resultSet.getString(n), "DECFLOAT")

          case "CHARACTER VARYING ARRAY"  => array(n, "String/URI")
          case "INTEGER ARRAY"            => array(n, "Int")
          case "BIGINT ARRAY"             => array(n, "Long")
          case "REAL ARRAY"               => array(n, "Float")
          case "DOUBLE PRECISION ARRAY"   => array(n, "Double")
          case "BOOLEAN ARRAY"            => array(n, "Boolean")
          case "DECIMAL(100, 0) ARRAY"    => array(n, "BigInt")
          case "DECIMAL(65535, 25) ARRAY" => array(n, "BigDecimal")
          case "DATE ARRAY"               => array(n, "Long")
          case "UUID ARRAY"               => array(n, "UUID")
          case "TINYINT ARRAY"            => array(n, "Byte")
          case "SMALLINT ARRAY"           => array(n, "Short")
          case "CHARACTER ARRAY"          => array(n, "Char")

          case "NULL"                           => nestedArray(n, "null")
          case "CHARACTER VARYING ARRAY ARRAY"  => nestedArray(n, "String/URI")
          case "INTEGER ARRAY ARRAY"            => nestedArray(n, "Int")
          case "BIGINT ARRAY ARRAY"             => nestedArray(n, "Long")
          case "REAL ARRAY ARRAY"               => nestedArray(n, "Float")
          case "DOUBLE PRECISION ARRAY ARRAY"   => nestedArray(n, "Double")
          case "BOOLEAN ARRAY ARRAY"            => nestedArray(n, "Boolean")
          case "DECIMAL(100, 0) ARRAY ARRAY"    => nestedArray(n, "BigInt")
          case "DECIMAL(65535, 25) ARRAY ARRAY" => nestedArray(n, "BigDecimal")
          case "DATE ARRAY ARRAY"               => nestedArray(n, "Long")
          case "UUID ARRAY ARRAY"               => nestedArray(n, "UUID")
          case "TINYINT ARRAY ARRAY"            => nestedArray(n, "Byte")
          case "SMALLINT ARRAY ARRAY"           => nestedArray(n, "Short")
          case "CHARACTER ARRAY ARRAY"          => nestedArray(n, "Char")

          case other => throw new Exception(
            s"Unexpected sql result type from raw query: " + other
          )
        }
        val columnValue = resultSet.getString(n)
        if (resultSet.wasNull()) {
          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  null")
        } else if (!resultSet.wasNull()) {
          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  " + columnValue)
        }
        n += 1
      }
      rows += row.toList
    }
    rows.toList
  }
}