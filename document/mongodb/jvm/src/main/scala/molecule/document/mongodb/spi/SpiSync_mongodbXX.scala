//package molecule.document.mongodb.spi
//
//import java.sql
//import java.sql.ResultSet
//import molecule.boilerplate.ast.Model._
//import molecule.core.action._
//import molecule.core.marshalling.ConnProxy
//import molecule.core.spi._
//import molecule.core.transaction._
//import molecule.document.mongodb.facade.MongoDBConn_JVM
//import molecule.document.mongodb.javaSql.ResultSetImpl
//import molecule.document.mongodb.spi.SpiSyncBase
//import molecule.document.mongodb.query.Model2SqlQuery_mongodb
//import molecule.document.mongodb.transaction._
//import scala.collection.mutable.ListBuffer
//
//
//object SpiSync_mongodb extends SpiSync_mongodb
//
//trait SpiSync_mongodb extends SpiSyncBase {
//
//  override def getModel2SqlQuery[Tpl](elements: List[Element]) = new Model2SqlQuery_mongodb[Tpl](elements)
//
////  override def save_getData(save: Save, conn: MongoDBConn_JVM): Data = {
////    new ResolveSave with Save_mongodb {
////      override lazy val sqlConn = conn.sqlConn
////    }.getData(save.elements)
////  }
////
////  override def insert_getData(insert: Insert, conn: MongoDBConn_JVM): Data = {
////    new ResolveInsert with Insert_mongodb {
////      override lazy val sqlConn: sql.Connection = conn.sqlConn
////    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
////  }
////
////  override def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
////    new Model2SqlQuery_mongodb(idsModel).getSqlQuery(Nil, None, None, Some(proxy))
////  }
////
////  override def update_getData(conn: MongoDBConn_JVM, update: Update): Data = {
////    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mongodb {
////      override lazy val sqlConn = conn.sqlConn
////    }.getData(update.elements)
////  }
////
////  override def update_getData(conn: MongoDBConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
////    new ResolveUpdate(conn.proxy, isUpsert) with Update_mongodb {
////      override lazy val sqlConn = conn.sqlConn
////    }.getData(elements)
////  }
////
////  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
////    val conn     = conn0.asInstanceOf[MongoDBConn_JVM]
////    val resolver = (query: String) => {
////      val ps        = conn.sqlConn.prepareStatement(
////        query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
////      )
////      val resultSet = ps.executeQuery()
////      new ResultSetImpl(resultSet)
////    }
////    validateUpdateSet(conn.proxy, update.elements, update.isUpsert, resolver)
////  }
////
////  override def delete_getData(conn: MongoDBConn_JVM, delete: Delete): Data = {
////    new ResolveDelete with Delete_mongodb {
////      override lazy val sqlConn = conn.sqlConn
////    }.getData(delete.elements, conn.proxy.nsMap)
////  }
//
////  override def fallback_rawQuery(
////    query: String,
////    debugFlag: Boolean = false,
////  )(implicit conn: Conn): List[List[Any]] = {
////    val c             = conn.asInstanceOf[MongoDBConn_JVM].sqlConn
////    val statement     = c.createStatement()
////    val resultSet     = statement.executeQuery(query)
////    val rsmd          = resultSet.getMetaData
////    val columnsNumber = rsmd.getColumnCount
////
////    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
////    debug("\n=============================================================================")
////    debug(query)
////
////    val rows = ListBuffer.empty[List[Any]]
////    val row  = ListBuffer.empty[Any]
////
////    def value[T](rawValue: T, baseTpe: String): String = {
////      if (resultSet.wasNull()) {
////        row += null
////      } else {
////        row += rawValue
////      }
////      baseTpe
////    }
////    def array(n: Int, baseTpe: String): String = {
////      val arr = resultSet.getArray(n)
////      if (resultSet.wasNull()) {
////        row += null
////      } else {
////        row += arr.getArray.asInstanceOf[Array[_]].toSet
////      }
////      s"Set[$baseTpe]"
////    }
////
////    def nestedArray(n: Int, baseTpe: String): String = {
////      val arr = resultSet.getArray(n).getResultSet
////      if (arr.wasNull()) {
////        row += null
////      } else {
////        arr.next()
////        row += arr.getArray(2).getArray.asInstanceOf[Array[_]].toSet
////      }
////      s"Set[$baseTpe]"
////    }
////
////    while (resultSet.next) {
////      debug("-----------------------------------------------")
////      var n = 1
////      row.clear()
////      while (n <= columnsNumber) {
////        val col         = rsmd.getColumnName(n)
////        val sqlType     = rsmd.getColumnTypeName(n)
////        val tpe         = sqlType match {
////          case "CHARACTER VARYING" => value(resultSet.getString(n), "String/URI")
////          case "INTEGER"           => value(resultSet.getInt(n), "Int")
////          case "BIGINT"            => value(resultSet.getLong(n), "Long")
////          case "REAL"              => value(resultSet.getFloat(n), "Float")
////          case "DOUBLE PRECISION"  => value(resultSet.getDouble(n), "Double")
////          case "BOOLEAN"           => value(resultSet.getBoolean(n), "Boolean")
////          case "DECIMAL"           => value(resultSet.getDouble(n), "BigInt/Decimal")
////          case "DATE"              => value(resultSet.getDate(n), "Date")
////          case "UUID"              => value(resultSet.getString(n), "UUID")
////          case "TINYINT"           => value(resultSet.getShort(n), "Byte")
////          case "SMALLINT"          => value(resultSet.getShort(n), "Short")
////          case "CHARACTER"         => value(resultSet.getString(n), "Char")
////
////          case "NUMERIC"  => value(resultSet.getString(n), "NUMERIC")
////          case "DECFLOAT" => value(resultSet.getString(n), "DECFLOAT")
////
////          case "CHARACTER VARYING ARRAY"  => array(n, "String/URI")
////          case "INTEGER ARRAY"            => array(n, "Int")
////          case "BIGINT ARRAY"             => array(n, "Long")
////          case "REAL ARRAY"               => array(n, "Float")
////          case "DOUBLE PRECISION ARRAY"   => array(n, "Double")
////          case "BOOLEAN ARRAY"            => array(n, "Boolean")
////          case "DECIMAL(100, 0) ARRAY"    => array(n, "BigInt")
////          case "DECIMAL(65535, 25) ARRAY" => array(n, "BigDecimal")
////          case "DATE ARRAY"               => array(n, "Date")
////          case "UUID ARRAY"               => array(n, "UUID")
////          case "TINYINT ARRAY"            => array(n, "Byte")
////          case "SMALLINT ARRAY"           => array(n, "Short")
////          case "CHARACTER ARRAY"          => array(n, "Char")
////
////          case "NULL"                           => nestedArray(n, "null")
////          case "CHARACTER VARYING ARRAY ARRAY"  => nestedArray(n, "String/URI")
////          case "INTEGER ARRAY ARRAY"            => nestedArray(n, "Int")
////          case "BIGINT ARRAY ARRAY"             => nestedArray(n, "Long")
////          case "REAL ARRAY ARRAY"               => nestedArray(n, "Float")
////          case "DOUBLE PRECISION ARRAY ARRAY"   => nestedArray(n, "Double")
////          case "BOOLEAN ARRAY ARRAY"            => nestedArray(n, "Boolean")
////          case "DECIMAL(100, 0) ARRAY ARRAY"    => nestedArray(n, "BigInt")
////          case "DECIMAL(65535, 25) ARRAY ARRAY" => nestedArray(n, "BigDecimal")
////          case "DATE ARRAY ARRAY"               => nestedArray(n, "Date")
////          case "UUID ARRAY ARRAY"               => nestedArray(n, "UUID")
////          case "TINYINT ARRAY ARRAY"            => nestedArray(n, "Byte")
////          case "SMALLINT ARRAY ARRAY"           => nestedArray(n, "Short")
////          case "CHARACTER ARRAY ARRAY"          => nestedArray(n, "Char")
////
////          case other => throw new Exception(
////            s"Unexpected sql result type from raw query: " + other
////          )
////        }
////        val columnValue = resultSet.getString(n)
////        if (resultSet.wasNull()) {
////          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  null")
////        } else if (!resultSet.wasNull()) {
////          debug(tpe + "   " + padS(20, tpe) + col + padS(20, col) + "  " + columnValue)
////        }
////        n += 1
////      }
////      rows += row.toList
////    }
////    rows.toList
////  }
//}