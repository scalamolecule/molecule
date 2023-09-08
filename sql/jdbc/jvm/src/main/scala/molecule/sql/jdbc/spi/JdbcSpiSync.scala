package molecule.sql.jdbc.spi

import java.sql
import java.sql.Statement
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.dbView.{AsOf, DbView, Since}
import molecule.core.spi._
import molecule.core.transaction._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction._
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal

object JdbcSpiSync extends JdbcSpiSync

trait JdbcSpiSync
  extends SpiSync
    with JVMJdbcSpiBase
    with SubscriptionStarter
    with PrintInspect
    with BaseHelpers {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    if (q.doInspect) {
      query_inspect(q)
    }
    q.dbView.foreach(noTime)
    JdbcQueryResolveOffset[Tpl](q.elements, q.optLimit, None)
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])._1
  }
  private def noTime(dbView: DbView): Unit = dbView match {
    case _: AsOf  => throw ModelError("Time function 'asOf' is only implemented for Datomic.")
    case _: Since => throw ModelError("Time function 'since' is only implemented for Datomic.")
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    JdbcQueryResolveOffset[Tpl](q.elements, q.optLimit, None)
      .subscribe(jdbcConn, callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    JdbcQueryResolveOffset[Tpl](q.elements, q.optLimit, None)
      .unsubscribe(jdbcConn)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements, q.optLimit, None)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    if (q.doInspect) queryOffset_inspect(q)
    q.dbView.foreach(noTime)
    JdbcQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset))
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements, q.optLimit, Some(q.offset))
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    if (q.doInspect) queryCursor_inspect(q)
    q.dbView.foreach(noTime)
    JdbcQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor))
      .getListFromCursor_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements, q.optLimit, None)
  }

  private def printInspectQuery(
    label: String,
    elements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    tryInspect("query", elements) {
      val query = new Model2SqlQuery(elements).getSqlQuery(Nil, optLimit, optOffset)
      printInspect(label, elements, query)
    }
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
    if (save.doInspect) save_inspect(save)
    val errors = save_validate(save)
    if (errors.isEmpty) {
      val conn     = conn0.asInstanceOf[JdbcConn_jvm]
      val txReport = conn.transact_sync(save_getData(save, conn))
      conn.callback(save.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }


  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    tryInspect("save", save.elements) {
      printInspectTx("SAVE", save.elements, save_getData(save, conn.asInstanceOf[JdbcConn_jvm]))
    }
  }

  private def save_getData(save: Save, conn: JdbcConn_jvm): Data = {
    new ResolveSave with Data_Save {
      override protected val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn): TxReport = {
    if (insert.doInspect) insert_inspect(insert)
    val errors = insert_validate(insert)
    if (errors.isEmpty) {
      val conn     = conn0.asInstanceOf[JdbcConn_jvm]
      val txReport = conn.transact_sync(insert_getData(insert, conn))
      conn.callback(insert.elements)
      txReport
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    tryInspect("insert", insert.elements) {
      val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
      printInspectTx("INSERT", insert.elements, insert_getData(insert, jdbcConn))
    }
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
    if (update.doInspect) update_inspect(update)
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn     = conn0.asInstanceOf[JdbcConn_jvm]
      val txReport = if (isRefUpdate(update.elements)) {
        // Atomic transaction with updates for each ref namespace
        conn.atomicTransaction(refUpdates(update)(conn))
      } else {
        conn.transact_sync(update_getData(conn, update))
      }
      conn.callback(update.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.elements) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      if (isRefUpdate(update.elements)) {
        val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
        val refIds                   =
          s"""REF IDS MODEL ----------------
             |${idsModel.mkString("\n")}
             |
             |${new Model2SqlQuery(idsModel).getSqlQuery(Nil, None, None)}
             |""".stripMargin
        val updates                  =
          updateModels
            .map(_(42))
            .map { m =>
              val elements = m.mkString("\n")
              val tables   = update_getData(conn, m, update.isUpsert)._1
              tables.headOption.fold(elements)(table => elements + "\n" + table.stmt)
            }
            .mkString(action + "S ----------------------\n", "\n------------\n", "")

        printInspect(action, update.elements, refIds + "\n" + updates)
      } else {
        printInspectTx(action, update.elements, update_getData(conn, update))
      }
    }
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
    if (delete.doInspect) delete_inspect(delete)
    val conn     = conn0.asInstanceOf[JdbcConn_jvm]
    val txReport = conn.transact_sync(delete_getData(conn, delete))
    conn.callback(delete.elements, true)
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    tryInspect("delete", delete.elements) {
      printInspectTx("DELETE", delete.elements, delete_getData(conn0.asInstanceOf[JdbcConn_jvm], delete))
    }
  }

  private def delete_getData(conn: JdbcConn_jvm, delete: Delete): Data = {
    new ResolveDelete with Data_Delete {
      override protected val sqlConn = conn.sqlConn
    }.getData(delete.elements, conn.proxy.nsMap)
  }


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, elements: List[Element])(body: => Unit): Unit = try {
    body
  } catch {
    case NonFatal(e) =>
      println(s"\n------------------ Error inspecting $action -----------------------")
      elements.foreach(println)
      throw e
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data): Unit = {
    val (tables, joinTables) = data
    val tableStmts           = tables.reverse.map(_.stmt).mkString("\n--------\n")
    val joinTableStmts       = if (joinTables.isEmpty) {
      ""
    } else {
      "\n\n--------------\n\n" + joinTables.map(_.stmt).mkString("\n--------\n")
    }
    printInspect(label, elements, tableStmts + joinTableStmts)
  }


  // Util --------------------------------------

  private def refUpdates(update: Update)(implicit conn: JdbcConn_jvm): () => Map[List[String], List[Long]] = {
    if (update.isUpsert)
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    val (refIdsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
    type L = Long
    val idQuery = updateModels.size match {
      case 1  => Query[L](refIdsModel)
      case 2  => Query[(L, L)](refIdsModel)
      case 3  => Query[(L, L, L)](refIdsModel)
      case 4  => Query[(L, L, L, L)](refIdsModel)
      case 5  => Query[(L, L, L, L, L)](refIdsModel)
      case 6  => Query[(L, L, L, L, L, L)](refIdsModel)
      case 7  => Query[(L, L, L, L, L, L, L)](refIdsModel)
      case 8  => Query[(L, L, L, L, L, L, L, L)](refIdsModel)
      case 9  => Query[(L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 10 => Query[(L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 11 => Query[(L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 12 => Query[(L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 13 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 14 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 15 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 16 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 17 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 18 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 19 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 20 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 21 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 22 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
    }

    val refIds: List[Long] = query_get(idQuery).headOption.fold(
      throw ExecutionError(
        s"""Couldn't find any ref ids for update:
           |
           |${idQuery.elements.mkString("\n")}
           |
           |${new Model2SqlQuery(idQuery.elements).getSqlQuery(Nil, None, None)}
           |""".stripMargin
      )
    ) {
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
      val refIdMaps = refIds.zipWithIndex.map {
        case (refId: Long, i) =>
          val updateModel = updateModels(i)(refId)
          conn.populateStmts(update_getData(conn, updateModel, update.isUpsert))
      }
      // Return TxReport with initial update ids
      refIdMaps.head
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
