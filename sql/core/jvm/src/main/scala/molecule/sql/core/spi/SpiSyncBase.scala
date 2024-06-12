package molecule.sql.core.spi

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.marshalling.dbView.{AsOf, DbView, Since}
import molecule.core.spi._
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.sql.core.transaction.{SqlBase_JVM, SqlUpdateSetValidator, Table, UpdateUtils}
import scala.annotation.nowarn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


trait SpiSyncBase
  extends SpiSync
    with UpdateUtils
    with SqlBase_JVM
    with SpiHelpers
    with SqlUpdateSetValidator
    with Renderer
    with BaseHelpers {

  // Query --------------------------------------------------------

  def getModel2SqlQuery[Tpl](elements: List[Element]): Model2SqlQuery[Tpl] with SqlQueryBase

  override def query_get[Tpl](q0: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      query_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  private def query_getRaw[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  private def noTime(dbView: DbView): Unit = dbView match {
    case _: AsOf  => throw ModelError("Time function 'asOf' is only implemented for Datomic.")
    case _: Since => throw ModelError("Time function 'since' is only implemented for Datomic.")
  }

  override def query_subscribe[Tpl](q0: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .subscribe(conn, callback, (elements: List[Element]) => getModel2SqlQuery[Tpl](elements))
  }
  override def query_unsubscribe[Tpl](q0: Query[Tpl])(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .unsubscribe(conn)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements, q.optLimit, None, Some(conn.proxy))
  }


  override def queryOffset_get[Tpl](q0: QueryOffset[Tpl])(implicit conn0: Conn): (List[Tpl], Int, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      queryOffset_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset), m2q)
      .getListFromOffset_sync(conn)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements, q.optLimit, Some(q.offset), Some(conn.proxy))
  }

  override def queryCursor_get[Tpl](q0: QueryCursor[Tpl])(implicit conn0: Conn): (List[Tpl], String, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      queryCursor_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor), m2q)
      .getListFromCursor_sync(conn)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements, q.optLimit, None, Some(conn.proxy))
  }

  private def printInspectQuery(
    label: String,
    elements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): Unit = {
    tryInspect("query", elements) {
      val query = getModel2SqlQuery[Any](elements).getSqlQuery(Nil, optLimit, optOffset, optProxy)
      printRaw(label, elements, query)
    }
  }


  // Save --------------------------------------------------------

  override def save_transact(save0: Save)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val save = save0.copy(elements = noKeywords(save0.elements, Some(conn.proxy)))
    if (save.doInspect)
      save_inspect(save)
    val errors = save_validate(save0) // validate original elements against meta model
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(save_getData(save, conn))
      conn.callback(save.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("save", save.elements) {
      printInspectTx("SAVE", save.elements, save_getData(save, conn))
    }
  }

  // Implement for each sql database
  def save_getData(save: Save, conn: JdbcConn_JVM): Data

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    TxModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }



  // Insert --------------------------------------------------------

  override def insert_transact(insert0: Insert)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val insert = insert0.copy(elements = noKeywords(insert0.elements, Some(conn.proxy)))
    if (insert.doInspect)
      insert_inspect(insert)
    val errors = insert_validate(insert0) // validate original elements against meta model
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(insert_getData(insert, conn))
      conn.callback(insert.elements)
      txReport
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    tryInspect("insert", insert.elements) {
      val jdbcConn = conn.asInstanceOf[JdbcConn_JVM]
      printInspectTx("INSERT", insert.elements, insert_getData(insert, jdbcConn), insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update0: Update)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val update = update0.copy(elements = noKeywords(update0.elements, Some(conn.proxy)))
    if (update.doInspect)
      update_inspect(update)
    val errors = update_validate(update0) // validate original elements against meta model
    if (errors.isEmpty) {
      val data     = if (update.elements.exists {
        case _: Ref => true
        case _      => false
      }) {
        if (update0.isUpsert) refUpserts(update)(conn) else refUpdates(update)(conn)
      } else {
        update_getData(conn, update)
      }
      val txReport = conn.transact_sync(data)
      conn.callback(update.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }


  private def refUpserts(update: Update)(implicit conn: JdbcConn_JVM): Data = {
    val elements     = update.elements
    val refIdss      = mutable.Map.empty[List[String], List[Long]]
    var tableUpdates = List.empty[Table]

    println("\n=== UPSERT =========================================================================================")
    println("------ elements --------")
    elements.foreach(println)

    val stages = getUpsertStages(elements)
    println("\n------ stages ---------")
    stages.foreach(println)
    println("")

    stages.foreach {
      case FindAllIds(refPath, elements) =>
        // Get and cache current ids for each table
        val arity = refPath.sliding(1, 2).flatten.toList.length
        getIdLists(arity, elements) match {
          case Array() => return (Nil, Nil) // Abort if no filter matches no ids
          case idLists =>
            var i = 1
            idLists.foreach { idsList =>
              refIdss(refPath.take(i)) = idsList
              i += 2 // next table ref path
            }
            println("\n-- 1 ---- refIdss -------- " + refPath)
            refIdss.toList.sortBy(_._1.length).foreach(println)
        }


      case FindKnownIds(refPath, elements) =>
        val List(ns, refAttr, refNs) = refPath.takeRight(3)

        val refPathLength = refPath.length
        val refPaths      = (1 to(refPathLength, 2)).map(refPath.take).zipWithIndex
        val last          = (refPathLength - 1) / 2
        println("-- 2 ---- ref paths ------")
        refPaths.foreach(println)

        // Get current ids and optional ref ids
        val tuples = query_getRaw[AnyRef](Query(elements)).asInstanceOf[List[Product]]
        println("\n-- 2 ---- tuples --------- " + refPath)
        tuples.foreach(println)

        tuples.foreach { tuple =>
          refPaths.foreach {
            case (refPath, `last`) =>
              tuple.productElement(last).asInstanceOf[Option[String]] match {
                case Some(refId) =>
                  refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+ refId.toLong
                case None        =>
                  // Insert ref row
                  val insertRefRow = s"INSERT INTO $refNs DEFAULT VALUES"
                  val insert       = (ps: PS, _: IdsMap, _: RowIndex) => {
                    ps.addBatch()
                  }
                  val newRefTable  = Table(refPath, insertRefRow, insert, true)

                  // Add relationship to new ref row
                  val curId        = tuple.productElement(last - 1).asInstanceOf[String]
                  val updateCurRow = s"UPDATE $ns SET $refAttr = ? WHERE id = $curId"
                  val update       = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
                    // Use ref path to retrieve created ref id from previous insert
                    val refId = idsMap(refPath).head
                    //                    val refId = idsMap(refPath).last
                    println("  refId: " + refId)
                    ps.setLong(1, refId)
                    ps.addBatch()
                  }
                  val updateRef    = Table(refPath.dropRight(2), updateCurRow, update)

                  // Tables are resolved in reverse order in JdbcConn_JVM.populateStmts
                  tableUpdates = List(updateRef, newRefTable) ++ tableUpdates
              }

              if (!refIdss.contains(refPath))
                refIdss(refPath) = Nil

            case (refPath, i) =>
              refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+
                tuple.productElement(i).asInstanceOf[String].toLong
          }
        }
        println("-- 2 ---- refIdss -------- ")
        refIdss.toList.sortBy(_._1.length).foreach(println)


      case CompleteCurRef(refPath, idsResolver) =>
        val List(ns, refAttr, refNs) = refPath.takeRight(3)
        val insert                   = (_: PS, idsMap: IdsMap, _: RowIndex) => {
          val nsIds    = idsMap(refPath.dropRight(2))
          //          println("-- 3 ---- nsIds: " + nsIds)
          val elements = idsResolver(nsIds.map(_.toString))
          //          println("-- 3 ---- elements --------")
          //          elements.foreach(println)
          //          println("-- 3 ---- tuples --------")
          //          query_getRaw(Query[(String, Option[String])](elements)).foreach(println)
          val refIds   = ListBuffer.empty[Long]
          query_getRaw(Query[(String, Option[String])](elements)).flatMap {
            case (nsId, None)        =>
              val refId = getId(s"INSERT INTO $refNs DEFAULT VALUES")
              refIds += refId
              Some((nsId, refId))
            case (nsId, Some(refId)) =>
              refIds += refId.toLong
              None
          }.foreach {
            case (nsId, refId) => getId(s"UPDATE $ns SET $refAttr = $refId WHERE id = $nsId")
          }
          idsMap(refPath) = refIds.toList

          println("-- 3 ---- idsMap -------- " + refPath)
          println(idsMap)

          println("-- 3 ---- refIdss -------- ")
          refIdss.toList.sortBy(_._1.length).foreach(println)
        }
        // Update internally in this case since we complete the ref structure
        // needing queries during transaction buildup
        tableUpdates = Table(refPath, "select 42", insert, updateIdsMap = false) :: tableUpdates

      //        println("\n-- 3 ---- refIdss -------- " + refPath)
      //        val refIdLists = refIdss.toList.sortBy(_._1.length)
      //        refIdLists.foreach(println)
      //
      //        val nsIds    = refIdss(refPath.dropRight(2))
      //        val elements = idsResolver(nsIds.map(_.toString))
      //
      //        println("-- 3 ---- tuples --------")
      //        query_getRaw(Query[Option[String]](elements)).foreach(println)
      //
      //
      //        // Handle ids maybe pointing to ref
      //        query_getRaw(Query[Option[String]](elements)).foreach {
      //          case Some(refId) =>
      //            refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+ refId.toLong
      //          case None        =>
      //            println("+++++++++")
      //            makeRef(refPath, ns, refAttr, refNs, false, true, "  ", elements)
      //        }
      //
      //        // Handle missing ids of previous namespace
      //        val numberOfMissingIds = refIdLists.head._2.length - refIdLists.last._2.length
      //
      //        println(s"numberOfMissingIds: " + numberOfMissingIds)
      //        (0 until numberOfMissingIds).foreach { _ =>
      //          println(".........")
      //          makeRef(refPath, ns, refAttr, refNs, false, true, "            ", elements)
      //        }
      //          makeRef(refPath, ns, refAttr, refNs, false, true, "            ")


      //      case NewRef(refPath, idsResolver) =>
      //        val List(ns, refAttr, refNs) = refPath.takeRight(3)
      //
      //        println("\n-- 3 ---- refIdss -------- " + refPath)
      //        val refIdLists = refIdss.toList.sortBy(_._1.length)
      //        refIdLists.foreach(println)
      //
      //        val nsIds    = refIdss(refPath.dropRight(2))
      //        val elements = idsResolver(nsIds.map(_.toString))
      //
      //        println("-- 3 ---- tuples --------")
      //        query_getRaw(Query[Option[String]](elements)).foreach(println)
      //
      //
      //        // Handle ids maybe pointing to ref
      //        query_getRaw(Query[Option[String]](elements)).foreach {
      //          case Some(refId) =>
      //            refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+ refId.toLong
      //          case None        =>
      //            makeRef(refPath, ns, refAttr, refNs, false, true, "  ")
      //        }
      //
      //        // Handle missing ids of previous namespace
      //        val numberOfMissingIds = refIdLists.head._2.length - refIdLists.last._2.length
      //
      //        println(s"numberOfMissingIds: " + numberOfMissingIds)
      //        (0 until numberOfMissingIds).foreach { _ =>
      //          println(".........")
      //          makeRef(refPath, ns, refAttr, refNs, false, true, "            ")
      //        }
      ////          makeRef(refPath, ns, refAttr, refNs, false, true, "            ")

      case UpdateNsData(refPath, elements) =>
        println("\n-- 4 ---- refIdss -------- " + refPath)
        refIdss.toList.sortBy(_._1.length).foreach(println)

        println("-- 4 ---- elements --------")
        elements.foreach(println)

        val table = update_getData(conn, Update(elements, true))._1.head
        println("\n-- 4 ---- table -------- ")
        println(table)

        val tableUpdate = refIdss.get(refPath).fold {
          table.copy(
            refPath = refPath,
            accIds = true,
            useAccIds = true,
            updateIdsMap = false
          )

        }(ids =>
          table.copy(
            refPath = refPath,
            useAccIds = true,
            curIds = ids // add current ref ids
          )
        )
        tableUpdates = tableUpdate +: tableUpdates
    }

    (tableUpdates, Nil)
  }

  private def prepStmt(stmt: String)(implicit conn: JdbcConn_JVM) = {
    conn.sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }
  private def getId(stmt: String)(implicit conn: JdbcConn_JVM): Long = {
    val ps = conn.sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)

    ps.addBatch()
    ps.executeBatch()
    val resultSet = ps.getGeneratedKeys
    resultSet.next()
    val id = resultSet.getLong(1)
    println(stmt + "  -->  id " + id)
    id
  }

  private def refUpdates(update: Update)(implicit conn: JdbcConn_JVM): Data = {
    val elements = update.elements

    println("............ elements")
    elements.foreach(println)

    val (arity, idsModel) = getUpdateIdsModel(elements)

    println(s"------ updateIdsModel ------  $arity")
    idsModel.foreach(println)

    val idLists = getIdLists(arity, idsModel)

    println(s"------ idLists ------  $arity")
    idLists.foreach(println)

    if (idLists.isEmpty) {
      (Nil, Nil)
    } else {
      var i            = 0
      val tableUpdates = getUpdateResolvers(elements, update.isUpsert).flatMap {
        case (refPath, modelResolver) =>
          val updateModel = modelResolver(idLists(i).map(_.toString)) // we pass current ids in Table

          val n = updateModel.length
          println(s"------ X ------- $refPath  $n")
          updateModel.foreach(println)
          i += 1
          update_getData(conn, updateModel, update.isUpsert)._1
      }
      (tableUpdates, Nil)
    }
  }


  private def getIdLists(arity: Int, idsModel: List[Element])
                        (implicit conn: JdbcConn_JVM): Array[List[Long]] = {
    val idRows = query_getRaw(Query[AnyRef](idsModel))
    println("------ idRows --------")
    idRows.foreach(println)
    println("--------")

    if (idRows.isEmpty) {
      Array.empty[List[Long]]
    } else {

      val idLists = arity match {
        case 1 => Array(idRows.asInstanceOf[List[String]])
        case 2 =>
          val (aa, bb) = idRows.asInstanceOf[List[(String, String)]].unzip
          Array(aa, bb)

        case 3 =>
          val (aa, bb, cc) = idRows.asInstanceOf[List[(String, String, String)]].unzip3
          Array(aa, bb, cc)

        case idCount =>
          val result = Array.fill(idCount)(List.empty[String])
          idRows.asInstanceOf[List[Product]].foreach { tuple =>
            (0 until idCount).foreach { i =>
              result(i) = result(i) :+ tuple.productElement(i).asInstanceOf[String]
            }
          }
          result
      }
      println(idLists.mkString("Array(", ", ", ")"))
      idLists.map(_.map(_.toLong))
    }
  }

  def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.elements) {
      if (isRefUpdate(update.elements)) {
        val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
        val refIds                   =
          s"""REF IDS MODEL ----------------
             |${idsModel.mkString("\n")}
             |
             |${refIdsQuery(idsModel, conn.proxy)}
             |""".stripMargin
        val updates                  =
          updateModels
            .map(_(42L)) // dummy value
            .map { m =>
              val elements = m.mkString("\n")
              val tables   = update_getData(conn, m, update.isUpsert)._1
              tables.headOption.fold(elements)(table => elements + "\n" + table.stmt)
            }
            .mkString(action + "S ----------------------\n", "\n------------\n", "")

        printRaw(action, update.elements, refIds + "\n" + updates)
      } else {
        printInspectTx(action, update.elements, update_getData(conn, update))
      }
    }
  }

  // Implement for each sql database
  def update_getData(conn: JdbcConn_JVM, update: Update): Data
  def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data


  // Delete --------------------------------------------------------

  override def delete_transact(delete0: Delete)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val delete = delete0.copy(elements = noKeywords(delete0.elements, Some(conn.proxy)))
    if (delete.doInspect)
      delete_inspect(delete)
    val txReport = conn.transact_sync(delete_getData(conn, delete))
    conn.callback(delete.elements, true)
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("delete", delete.elements) {
      printInspectTx("DELETE", delete.elements, delete_getData(conn, delete))
    }
  }

  // Implement for each sql database
  def delete_getData(conn: JdbcConn_JVM, delete: Delete): Data


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, elements: List[Element])(body: => Unit): Unit = try {
    body
  } catch {
    case NonFatal(e) =>
      println(s"\n------------------ Error inspecting $action -----------------------")
      elements.foreach(println)
      throw e
  }

  private def printInspectTx(label: String, elements: List[Element], stmts: Data, tpls: Seq[Product] = Nil): Unit = {
    val (tables, joinTables) = stmts
    val tableStmts           = tables.reverse.map(_.stmt).mkString("\n--------\n")
    val joinTableStmts       = if (joinTables.isEmpty) {
      ""
    } else {
      "\n\n--------------\n\n" + joinTables.map(_.stmt).mkString("\n--------\n")
    }
    printRaw(label, elements, tableStmts + joinTableStmts, tpls.mkString("\n"))
  }


  // Util --------------------------------------


  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = false
  )(implicit conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(txData)

    val ps = conn.sqlConn.prepareStatement(txData, Statement.RETURN_GENERATED_KEYS)
    ps.execute()

    val resultSet = ps.getGeneratedKeys // is empty if no nested data
    var ids       = List.empty[String]
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1).toString
    }
    ps.close()

    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }
}