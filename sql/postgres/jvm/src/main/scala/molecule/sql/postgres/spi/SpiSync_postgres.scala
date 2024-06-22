package molecule.sql.postgres.spi

import java.sql
import java.sql.ResultSet
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.transaction._
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.spi.SpiSyncBase
import molecule.sql.postgres.query._
import molecule.sql.postgres.transaction._
import org.postgresql.util.PSQLException
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


object SpiSync_postgres extends SpiSync_postgres

trait SpiSync_postgres extends SpiSyncBase {

  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_postgres[Tpl](elements)

  override def save_getData(save: Save, conn: JdbcConn_JVM): Data = {
    new ResolveSave with Save_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getSaveData(save.elements)
  }

  override def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data = {
    new ResolveInsert with Insert_postgres {
      override lazy val sqlConn: sql.Connection = conn.sqlConn
    }.getInsertData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
    new Model2SqlQuery_postgres(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
  }

  override def update_getData(conn: JdbcConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getUpdateData(update.elements)
  }

  override def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy, isUpsert) with Update_postgres {
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
    validateUpdateSet(conn.proxy, update.elements, query2resultSet)
  }

  override def delete_getInspectionData(conn: JdbcConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteDataForInspection(delete.elements, conn.proxy.nsMap)
  }

  override def delete_getExecutioner(conn: JdbcConn_JVM, delete: Delete): Option[() => List[Long]] = {
    new ResolveDelete with Delete_postgres {
      override lazy val sqlConn = conn.sqlConn
    }.getDeleteExecutioner(delete.elements, conn.proxy.nsMap, "SET session_replication_role", "replica", "default")
  }

  override def fallback_rawQuery(
    query: String,
    debugFlag: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
    val c             = conn.asInstanceOf[JdbcConn_JVM].sqlConn
    val statement     = c.createStatement()
    val rowsResultSet = statement.executeQuery(query)
    val rsmd          = rowsResultSet.getMetaData
    val columnsNumber = rsmd.getColumnCount

    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
    //    debug("\n=============================================================================")
    //    debug(query)

    val rows = ListBuffer.empty[List[Any]]
    val row  = ListBuffer.empty[Any]

    def value[T](rawValue: T, baseTpe: String): String = {
      if (rowsResultSet.wasNull()) {
        row += null
      } else {
        row += rawValue
      }
      baseTpe
    }

    def array(n: Int, baseTpe: String): String = {
      try {
        val arrayN = rowsResultSet.getArray(n)
        if (arrayN == null) {
          //          debug("  A  " + arrayN)
          row += null
        } else {
          val arrayRS = arrayN.getResultSet
          if (arrayRS.wasNull()) {
            //            debug("  B  " + arrayRS)
            row += null
          } else {
            //            debug("  C  " + arrayRS)
            arrayRS.next()
            val array2 = arrayRS.getArray(2)
            if (array2 == null) {
              //              debug("  C1  null")
              row += null
            } else {
              val set = arrayRS.getArray(2).getArray.asInstanceOf[Array[_]].toSet
              //              debug("  C2  " + set)
              row += set
            }
          }
        }
      } catch {
        case e: PSQLException =>
          if (e.getMessage.contains("No results were returned by the query")) {
            val arrayN = rowsResultSet.getArray(n)
            if (rowsResultSet.wasNull()) {
              //              debug("  D  null")
              row += null
            } else {
              val set = arrayN.getArray.asInstanceOf[Array[_]].toSet
              //              debug("  E  " + set)
              row += set
            }
          } else if (e.getMessage.contains("perhaps you need to call next")) {
            //            debug("  F  null")
            row += null
          } else {
            throw new Exception("Unexpected error from Postgres: " + e)
          }
        case NonFatal(e)      => throw e
      }
      s"Set[$baseTpe]"
    }

    while (rowsResultSet.next) {
      debug("--------------------------------------------------------------------------------------")
      var n = 1
      row.clear()
      while (n <= columnsNumber) {
        val col               = rsmd.getColumnName(n)
        val sqlType           = rsmd.getColumnTypeName(n)
        //        debug("TPE: " + sqlType)
        val tpe               = sqlType match {
          case "text"      => value(rowsResultSet.getString(n), "String/URI")
          case "int4"      => value(rowsResultSet.getInt(n), "Int")
          case "int8"      => value(rowsResultSet.getLong(n), "Long")
          case "numeric"   => value(rowsResultSet.getBigDecimal(n), "Float/BigInt/BigDecimal")
          case "float8"    => value(rowsResultSet.getDouble(n), "Double")
          case "bool"      => value(rowsResultSet.getBoolean(n), "Boolean")
          case "date"      => value(rowsResultSet.getDate(n), "Date")
          case "varchar"   => value(rowsResultSet.getString(n), "java.time text")
          case "uuid"      => value(rowsResultSet.getString(n), "UUID")
          case "int2"      => value(rowsResultSet.getInt(n), "Byte")
          case "bpchar"    => value(rowsResultSet.getString(n), "Char")
          case "bigserial" => value(rowsResultSet.getLong(n), "Long id")
          case "jsonb"     =>

            //            import org.postgresql.jdbc.PgArray

            //            println("1  " + rowsResultSet.)
            //            println("1  " + rowsResultSet.getArray(n))
            //            println("1  " + rowsResultSet.getArray(n).getArray)
            //            println("1  " + rowsResultSet.getArray(n).getClass)
            //            println("1  " + rowsResultSet.getString(n))
            //            println("2  " + rowsResultSet.getArray(n).getResultSet)
            //            println("3  " + rowsResultSet.getArray(n).getArray.asInstanceOf[Array[_]].toList)

            //            array(n, "Int")
            value(rowsResultSet.getString(n), "String/URI")

          case "_int4"    => array(n, "Int")
          case "_text"    => array(n, "String")
          case "_int8"    => array(n, "Long")
          case "_numeric" => array(n, "Float/BigInt/BigDecimal")
          case "_float8"  => array(n, "Double")
          case "_bool"    => array(n, "Boolean")
          case "_date"    => array(n, "Date")
          case "_varchar" => array(n, "java.time text/URI")
          case "_uuid"    => array(n, "UUID")
          case "_int2"    => array(n, "Byte/Short")
          case "_bpchar"  => array(n, "Char")
          case "_jsonb"   =>
            array(n, "String")

          case other =>
            throw new Exception(s"Unexpected sql result type from raw query: " + other)
        }
        val columnStringValue = rowsResultSet.getString(n)
        if (rowsResultSet.wasNull()) {
          debug(tpe + "   " + padS(20, tpe) + col + padS(30, col) + "  null")
        } else {
          debug(tpe + "   " + padS(20, tpe) + col + padS(30, col) + "  " + columnStringValue)
        }
        n += 1
      }
      rows += row.toList
    }
    rows.toList
  }
}