package molecule.sql.postgres.spi

import java.sql
import java.sql.ResultSet
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.marshalling.dbView.{AsOf, DbView, Since}
import molecule.core.spi._
import molecule.core.transaction._
import molecule.core.util.ModelUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.ResultSetImpl
import molecule.sql.core.query.{SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.sql.core.spi.{SpiHelpers, SpiSyncBase}
import molecule.sql.core.transaction.{SqlBase_JVM, SqlUpdateSetValidator}
import molecule.sql.postgres.query._
import molecule.sql.postgres.transaction._
import scala.annotation.nowarn
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


object SpiSync_postgres extends SpiSync_postgres

trait SpiSync_postgres extends SpiSyncBase {

  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_postgres[Tpl](elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_postgres {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_postgres(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getData(update.elements)
  }

  override def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy, isUpsert) with Update_postgres {
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
    new ResolveDelete with Delete_postgres {
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

    def value[T](rawValue: T, baseTpe: String): String = {
      val isNull = resultSet.wasNull()
      if (withNulls && isNull) {
        row += null
      } else if (!isNull) {
        row += rawValue
      }
      baseTpe
    }
    def array(n: Int, baseTpe: String): String = {
      val arr    = resultSet.getArray(n)
      val isNull = resultSet.wasNull()
      if (withNulls && isNull) {
        row += null
      } else if (!isNull) {
        row += arr.getArray.asInstanceOf[Array[_]].toSet
      }
      s"Set[$baseTpe]"
    }

    while (resultSet.next) {
      debug("-----------------------------------------------")
      var n = 1
      row.clear()
      while (n <= columnsNumber) {
        val col     = rsmd.getColumnName(n)
        val sqlType = rsmd.getColumnTypeName(n)

        // todo: get types of sql dialect
        val tpe         = sqlType match {
          case "CHARACTER VARYING"            => value(resultSet.getString(n), "String/URI")
          case "INTEGER" | "int4"             => value(resultSet.getInt(n), "Int")
          case "BIGINT"                       => value(resultSet.getLong(n), "Long")
          case "REAL" | "float4"              => value(resultSet.getFloat(n), "Float")
          case "DOUBLE PRECISION" | "numeric" => value(resultSet.getDouble(n), "Double")
          case "BOOLEAN"                      => value(resultSet.getBoolean(n), "Boolean")
          case "DECIMAL"                      => value(resultSet.getDouble(n), "BigInt/Decimal")
          case "DATE"                         => value(resultSet.getDate(n), "Date")
          case "UUID"                         => value(resultSet.getString(n), "UUID")
          case "TINYINT"                      => value(resultSet.getShort(n), "Byte")
          case "SMALLINT"                     => value(resultSet.getShort(n), "Short")
          case "CHARACTER"                    => value(resultSet.getString(n), "Char")

          case "CHARACTER VARYING ARRAY"  => array(n, "String/URI")
          case "INTEGER ARRAY"            => array(n, "Int")
          case "BIGINT ARRAY"             => array(n, "Long")
          case "REAL ARRAY"               => array(n, "Float")
          case "DOUBLE PRECISION ARRAY"   => array(n, "Double")
          case "BOOLEAN ARRAY"            => array(n, "Boolean")
          case "DECIMAL(100, 0) ARRAY"    => array(n, "BigInt")
          case "DECIMAL(65535, 25) ARRAY" => array(n, "BigDecimal")
          case "DATE ARRAY"               => array(n, "Date")
          case "UUID ARRAY"               => array(n, "UUID")
          case "TINYINT ARRAY"            => array(n, "Byte")
          case "SMALLINT ARRAY"           => array(n, "Short")
          case "CHARACTER ARRAY"          => array(n, "Char")

          case "NULL"                          => row += "NULL"; "NULL"
          case "CHARACTER VARYING ARRAY ARRAY" => row += "CHARACTER VARYING ARRAY ARRAY"; "CHARACTER VARYING ARRAY ARRAY"
          case "INTEGER ARRAY ARRAY"           => row += "INTEGER ARRAY ARRAY"; "INTEGER ARRAY ARRAY"
          case "BIGINT ARRAY ARRAY"            => row += "BIGINT ARRAY ARRAY"; "BIGINT ARRAY ARRAY"
          case "REAL ARRAY ARRAY"              => row += "REAL ARRAY ARRAY"; "REAL ARRAY ARRAY"
          case "DOUBLE PRECISION ARRAY ARRAY"  => row += "DOUBLE PRECISION ARRAY ARRAY"; "DOUBLE PRECISION ARRAY ARRAY"
          case "BOOLEAN ARRAY ARRAY"           => row += "BOOLEAN ARRAY ARRAY"; "BOOLEAN ARRAY ARRAY"
          case "DECIMAL ARRAY ARRAY"           => row += "DECIMAL ARRAY ARRAY"; "DECIMAL ARRAY ARRAY"
          case "DATE ARRAY ARRAY"              => row += "DATE ARRAY ARRAY"; "DATE ARRAY ARRAY"
          case "UUID ARRAY ARRAY"              => row += "UUID ARRAY ARRAY"; "UUID ARRAY ARRAY"
          case "TINYINT ARRAY ARRAY"           => row += "TINYINT ARRAY ARRAY"; "TINYINT ARRAY ARRAY"
          case "SMALLINT ARRAY ARRAY"          => row += "SMALLINT ARRAY ARRAY"; "SMALLINT ARRAY ARRAY"
          case "CHARACTER ARRAY ARRAY"         => row += "CHARACTER ARRAY ARRAY"; "CHARACTER ARRAY ARRAY"

          // case "DOUBLE"      => row += resultSet.getDouble(n); "Double/Float x"
          // case "BIT"         => row += resultSet.getByte(n); "a"
          // case "FLOAT"       => row += resultSet.getFloat(n); "e"
          // case "REAL"        => row += resultSet.getDouble(n); "f"
          // case "NUMERIC"     => row += resultSet.getDouble(n); "g"
          // case "CHAR"        => row += resultSet.getString(n).charAt(0); "h"
          // case "VARCHAR"     => row += resultSet.getString(n).charAt(0); "j"
          // case "LONGVARCHAR" => row += resultSet.getString(n); "k"
          // case "BINARY"      => row += resultSet.getByte(n); "l"

          case "float8"    => value(resultSet.getDouble(n), "Double")
          case "text"      => value(resultSet.getString(n), "String/URI")
          case "bool"      => value(resultSet.getString(n), "Boolean")
          case "bigserial" => value(resultSet.getString(n), "BigInt/Decimal")
          case "bigint"    => value(resultSet.getString(n), "BigInt/Decimal")

          case "_int4"    => array(n, "Int")
          case "_int8"    => array(n, "Bigint")
          case "_text"    => array(n, "String/URI")
          case "_bool"    => array(n, "Boolean")
          case "_numeric" => array(n, "Bigint")

          case other => throw new Exception(
            s"Unexpected/not yet considered sql result type from raw query: " + other
          )
        }
        val columnValue = resultSet.getString(n)
        if (withNulls && resultSet.wasNull()) {
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