package molecule.sql.jdbc.spi

import java.sql
import java.sql.Statement
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.spi._
import molecule.core.transaction._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction._
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

object JdbcSpiSync extends JdbcSpiSync

trait JdbcSpiSync
  extends SpiSync
    with JVMJdbcSpiBase
    with SubscriptionStarter
    with PrintInspect
    with BaseHelpers {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])._1
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
      .subscribe(jdbcConn, getWatcher(jdbcConn), callback)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    JdbcQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
      .getListFromCursor_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
    val errors = save_validate(save)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      conn.transact_sync(save_getData(save, conn))
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    printInspectTx("SAVE", save.elements, save_getData(save, conn.asInstanceOf[JdbcConn_jvm]))
  }

  private def save_getData(save: Save, conn: JdbcConn_jvm): Data = {
    new ResolveSave() with Data_Save {
      override protected val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn): TxReport = {
    val errors = insert_validate(insert)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      conn.transact_sync(insert_getData(insert, conn))
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    printInspectTx("INSERT", insert.elements, insert_getData(insert, jdbcConn))
  }

  private def insert_getData(insert: Insert, conn: JdbcConn_jvm): Data = {
    new ResolveInsert with Data_Insert {
      override protected val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn): TxReport = {
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      if (isRefUpdate(update.elements)) {
        // Atomic transaction with updates for each ref namespace
        conn.atomicTransaction(refUpdates(update)(conn))
      } else {
        conn.transact_sync(update_getData(conn, update))
      }
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    printInspectTx("UPDATE", update.elements, update_getData(conn0.asInstanceOf[JdbcConn_jvm], update))
  }

  private def update_getData(conn: JdbcConn_jvm, update: Update): Data = {
    new ResolveUpdate(conn.proxy.uniqueAttrs, update.isUpsert) with Data_Update {
      override protected val sqlConn = conn.sqlConn
    }.getData(update.elements)
  }

  private def update_getData(conn: JdbcConn_jvm, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy.uniqueAttrs, isUpsert) with Data_Update {
      override protected val sqlConn = conn.sqlConn
    }.getData(elements)
  }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_jvm]
    conn.transact_sync(delete_getData(conn, delete))
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    printInspectTx("DELETE", delete.elements, delete_getData(conn0.asInstanceOf[JdbcConn_jvm], delete))
  }

  private def delete_getData(conn: JdbcConn_jvm, delete: Delete): Data = {
    new ResolveDelete with Data_Delete {
      override protected val sqlConn = conn.sqlConn
    }.getData(delete.elements, conn.proxy.nsMap)
  }


  // Inspect --------------------------------------------------------

  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new SqlModel2Query(elements).getQuery(Nil) //._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data): Unit = {
    // Simply print the statement (with no data)
    printInspect(label, elements, data._1.head.stmt)
  }


  // Util --------------------------------------

  private def refUpdates(update: Update)(implicit conn: JdbcConn_jvm): () => Map[List[String], List[Long]] = {
    if (update.isUpsert)
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
    type L = Long
    val idQuery = updateModels.size match {
      case 1  => Query[L](idsModel)
      case 2  => Query[(L, L)](idsModel)
      case 3  => Query[(L, L, L)](idsModel)
      case 4  => Query[(L, L, L, L)](idsModel)
      case 5  => Query[(L, L, L, L, L)](idsModel)
      case 6  => Query[(L, L, L, L, L, L)](idsModel)
      case 7  => Query[(L, L, L, L, L, L, L)](idsModel)
      case 8  => Query[(L, L, L, L, L, L, L, L)](idsModel)
      case 9  => Query[(L, L, L, L, L, L, L, L, L)](idsModel)
      case 10 => Query[(L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 11 => Query[(L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 12 => Query[(L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 13 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 14 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 15 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 16 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 17 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 18 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 19 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 20 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 21 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 22 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
    }

    val ids: List[Long] = query_get(idQuery).head match {
      case a: L                                                                                                                                 => List(0L, a)
      case (a: L, b: L)                                                                                                                         => List(0L, a, b)
      case (a: L, b: L, c: L)                                                                                                                   => List(0L, a, b, c)
      case (a: L, b: L, c: L, d: L)                                                                                                             => List(0L, a, b, c, d)
      case (a: L, b: L, c: L, d: L, e: L)                                                                                                       => List(0L, a, b, c, d, e)
      case (a: L, b: L, c: L, d: L, e: L, f: L)                                                                                                 => List(0L, a, b, c, d, e, f)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L)                                                                                           => List(0L, a, b, c, d, e, f, g)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L)                                                                                     => List(0L, a, b, c, d, e, f, g, h)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L)                                                                               => List(0L, a, b, c, d, e, f, g, h, i)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L)                                                                         => List(0L, a, b, c, d, e, f, g, h, i, j)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L)                                                                   => List(0L, a, b, c, d, e, f, g, h, i, j, k)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L)                                                             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L)                                                       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L)                                                 => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L)                                           => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L)                                     => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L)                               => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L)                         => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L)                   => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L)             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L)       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L, v: L) => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    }

    () => {
      val idMaps = ids.zipWithIndex.map {
        case (id: Long, i) =>
          val updateModel = updateModels(i)(id)
          conn.populateStmts(update_getData(conn, updateModel, update.isUpsert))
      }
      // Return TxReport with initial update ids
      idMaps.head
    }
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn): List[List[Any]] = {
    val c             = conn.asInstanceOf[JdbcConn_jvm].sqlConn
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
          case "CHARACTER VARYING" => value(resultSet.getString(n), "String/URI")
          case "INTEGER"           => value(resultSet.getInt(n), "Int")
          case "BIGINT"            => value(resultSet.getLong(n), "Long")
          case "REAL"              => value(resultSet.getFloat(n), "Float")
          case "DOUBLE PRECISION"  => value(resultSet.getDouble(n), "Double")
          case "BOOLEAN"           => value(resultSet.getBoolean(n), "Boolean")
          case "DECIMAL"           => value(resultSet.getDouble(n), "BigInt/Decimal")
          case "DATE"              => value(resultSet.getDate(n), "Date")
          case "UUID"              => value(resultSet.getString(n), "UUID")
          case "TINYINT"           => value(resultSet.getShort(n), "Byte")
          case "SMALLINT"          => value(resultSet.getShort(n), "Short")
          case "CHARACTER"         => value(resultSet.getString(n), "Char")

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

          // case "NULL"                => row += "NULL"; "NULL"
          // case "INTEGER ARRAY ARRAY" => row += "INTEGER ARRAY ARRAY"; "INTEGER ARRAY ARRAY"
          // case "DOUBLE"      => row += resultSet.getDouble(n); "Double/Float x"
          // case "BIT"         => row += resultSet.getByte(n); "a"
          // case "FLOAT"       => row += resultSet.getFloat(n); "e"
          // case "REAL"        => row += resultSet.getDouble(n); "f"
          // case "NUMERIC"     => row += resultSet.getDouble(n); "g"
          // case "CHAR"        => row += resultSet.getString(n).charAt(0); "h"
          // case "VARCHAR"     => row += resultSet.getString(n).charAt(0); "j"
          // case "LONGVARCHAR" => row += resultSet.getString(n); "k"
          // case "BINARY"      => row += resultSet.getByte(n); "l"

          case other => throw new Exception(
            s"Unexpected/not yet considered sql result type from raw query: " + other
          )
        }
        val columnValue = resultSet.getString(n)
        if (withNulls && resultSet.wasNull()) {
          debug(tpe + "   " + padS(18, tpe) + col + padS(20, col) + "null")
        } else if (!resultSet.wasNull()) {
          debug(tpe + "   " + padS(18, tpe) + col + padS(20, col) + columnValue)
        }
        n += 1
      }
      rows += row.toList
    }
    rows.toList
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn): TxReport = {
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(txData)

    val ps = conn.asInstanceOf[JdbcConn_jvm].sqlConn.prepareStatement(txData, Statement.RETURN_GENERATED_KEYS)
    ps.execute()

    val resultSet = ps.getGeneratedKeys // is empty if no nested data
    var ids       = List.empty[Long]
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1)
    }
    ps.close()

    debug("---------------")
    debug("Ids: " + ids)
    TxReport(0, ids)
  }
}
