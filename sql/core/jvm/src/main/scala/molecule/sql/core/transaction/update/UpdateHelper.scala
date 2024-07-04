package molecule.sql.core.transaction.update

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.ast.{CardOne, CardSet}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, Update}
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.Conn
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.SqlQueryResolveOffset
import molecule.sql.core.spi.{SpiHelpers, SpiSyncBase}
import molecule.sql.core.transaction.{SqlBase_JVM, Table}
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


trait UpdateHelper extends SpiHelpers { self: SqlBase_JVM =>

  protected def query_getRaw[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  protected def refUpserts(elements: List[Element])(implicit conn: JdbcConn_JVM): Data = {
    val refIdss      = mutable.Map.empty[List[String], List[Long]]
    var tableUpdates = List.empty[Table]

    //    println("\n=== UPSERT =========================================================================================")
    //    println("------ elements --------")
    //    elements.foreach(println)

    val stages = getUpsertStages(elements)
    //    println("\n------ stages ---------")
    //    stages.foreach(println)
    //    println("")

    var moveOn = false
    stages.foreach {
      case _ if moveOn => ()

      case FindAllIds(refPath, elements) =>
        // Get and cache current ids for each table
        val arity = refPath.sliding(1, 2).flatten.toList.length
        getIdLists(arity, elements) match {
          case Array() =>
            tableUpdates = Nil
            moveOn = true // Abort if no filter matches no ids

          case idLists =>
            var i = 1
            idLists.foreach { idsList =>
              refIdss(refPath.take(i)) = idsList
              i += 2 // next table ref path
            }
          //            println("\n-- 1 ---- refIdss -------- " + refPath)
          //            refIdss.toList.sortBy(_._1.length).foreach(println)
        }


      case FindKnownIds(refPath, elements) =>
        val List(ns, refAttr, refNs) = refPath.takeRight(3)

        val refPathLength = refPath.length
        val refPaths      = (1 to(refPathLength, 2)).map(refPath.take).zipWithIndex
        val last          = (refPathLength - 1) / 2
        //        println("-- 2 ---- ref paths ------")
        //        refPaths.foreach(println)

        //        println("-- 2 ---- elements --------")
        //        elements.foreach(println)


        // Get current ids and optional ref ids
        val rowTuples = query_getRaw[AnyRef](Query(elements)).asInstanceOf[List[Product]]
        //        println("\n-- 2 ---- row tuples --------- " + refPath)
        //        rowTuples.foreach(println)

        rowTuples.foreach { rowTuple =>
          // Loop through namespaces of each row to complete ref structure
          refPaths.foreach {
            case (refPath, `last`) =>
              rowTuple.productElement(last) match {
                case nested: List[_] if nested.isEmpty =>
                  val insertRefRowStmt   = s"INSERT INTO $refNs $defaultValues"
                  val insertRefRowAction = (ps: PS, _: IdsMap, _: RowIndex) => {
                    ps.addBatch()
                  }
                  val insertRefRow       = Table(refPath, insertRefRowStmt, insertRefRowAction, true)

                  // Insert join row
                  val curId               = rowTuple.productElement(last - 1).asInstanceOf[Long]
                  val insertJoinRowStmt   =
                    s"INSERT INTO ${ns}_${refAttr}_$refNs (${ns}_id, ${refNs}_id) VALUES ($curId, ?)"
                  val insertJoinRowAction = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
                    // Use ref path to retrieve created ref id from previous insert
                    val refId = idsMap(refPath).head
                    ps.setLong(1, refId)
                    ps.addBatch()
                  }
                  val insertJoinRow       = Table(refPath, insertJoinRowStmt, insertJoinRowAction)

                  // Tables are resolved in reverse order in JdbcConn_JVM.populateStmts
                  tableUpdates = List(insertJoinRow, insertRefRow) ++ tableUpdates

                case nested: List[_] =>
                  //                  val refIds = nested.asInstanceOf[List[String]].map(_.toLong)
                  val refIds = nested.asInstanceOf[List[Long]]
                  refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) ++ refIds

                case Some(refId: Long) =>
                  refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+ refId

                case None =>
                  val insertRefRowStmt   = s"INSERT INTO $refNs $defaultValues"
                  val insertRefRowAction = (ps: PS, _: IdsMap, _: RowIndex) => {
                    ps.addBatch()
                  }
                  val insertRefRow       = Table(refPath, insertRefRowStmt, insertRefRowAction, true)

                  // Add relationship to new ref row
                  val curId              = rowTuple.productElement(last - 1).asInstanceOf[Long]
                  val updateCurRowStmt   = s"UPDATE $ns SET $refAttr = ? WHERE id = $curId"
                  val updateCurRowAction = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
                    // Use ref path to retrieve created ref id from previous insert
                    val refId = idsMap(refPath).head
                    //                    println("  refId: " + refId)
                    ps.setLong(1, refId)
                    ps.addBatch()
                  }
                  val updateCurRow       = Table(
                    refPath.dropRight(2),
                    updateCurRowStmt,
                    updateCurRowAction,
                    curIds = List(curId),
                  )

                  // Tables are resolved in reverse order in JdbcConn_JVM.populateStmts
                  tableUpdates = List(updateCurRow, insertRefRow) ++ tableUpdates
              }
              if (!refIdss.contains(refPath))
                refIdss(refPath) = Nil

            case (refPath, i) =>
              refIdss(refPath) = refIdss.getOrElse(refPath, List.empty[Long]) :+
                rowTuple.productElement(i).asInstanceOf[Long]
          }
        }
      //        println("-- 2 ---- refIdss -------- ")
      //        refIdss.toList.sortBy(_._1.length).foreach(println)


      case CompleteCurRef(refPath, idsResolver) =>
        val List(ns, refAttr, refNs) = refPath.takeRight(3)
        val insert                   = (_: PS, idsMap: IdsMap, _: RowIndex) => {
          val nsIds = idsMap(refPath.dropRight(2))
          //          println("-- 3 ---- nsIds: " + nsIds)

          val elements = idsResolver(nsIds)
          //          println("-- 3 ---- elements --------")
          //          elements.foreach(println)
          //          println("-- 3 ---- tuples --------")
          //          query_getRaw(Query[(String, Option[String])](elements)).foreach(println)

          val refIds = ListBuffer.empty[Long]
          query_getRaw(Query[(Long, Option[Long])](elements)).flatMap {
            case (nsId, None)        =>
              val refId = getId(s"INSERT INTO $refNs $defaultValues", refNs)
              refIds += refId
              Some((nsId, refId))
            case (nsId, Some(refId)) =>
              refIds += refId
              None
          }.foreach {
            case (nsId, refId) => getId(
              s"UPDATE $ns SET $refAttr = $refId WHERE id = $nsId", ns, Some(nsId)
            )
          }
          idsMap(refPath) = refIds.toList

          //          println("-- 3 ---- idsMap -------- " + refPath)
          //          println(idsMap)

          //          println("-- 3 ---- refIdss -------- ")
          //          refIdss.toList.sortBy(_._1.length).foreach(println)
        }
        // Update internally in this case since we complete the ref structure
        // needing queries during transaction buildup
        tableUpdates = Table(refPath, "select 42", insert, updateIdsMap = false) :: tableUpdates


      case UpdateNsData(refPath, elements) =>
        //        println("\n-- 4 ---- refIdss -------- " + refPath)
        //        refIdss.toList.sortBy(_._1.length).foreach(println)

        //        println("-- 4 ---- elements --------")
        //        elements.foreach(println)

        val table = update_getData(conn, Update(elements, true))._1.head
        //        val table = update_getData(conn, elements, true)._1.head
        //        println("\n-- 4 ---- table -------- ")
        //        println(table)

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

  private def getUpsertStages(elements: List[Element]): List[UpsertStage] = {
    // Resolve model backwards to locate edge of known ids in ref structure
    @tailrec
    def rec(
      reversedElements: List[Element],
      stages: List[UpsertStage],
      refPath: List[String],
      idsModel: List[Element],
      updateModel: List[Element],
      isCardOne: Boolean,
      nsHasFilter: Boolean,
      nextNsHasFilter: Boolean,
      makeIdsOptRef: Boolean,
    ): List[UpsertStage] = {
      reversedElements match {
        case lastElement :: es => lastElement match {
          case a: Attr =>
            a match {
              case a: AttrOne => a match {
                case a: AttrOneMan => rec(
                  es, stages, refPath, idsModel, a :: updateModel,
                  isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef
                )
                case a: AttrOneTac => rec(
                  es, stages, refPath, a :: idsModel, updateModel,
                  isCardOne, true, nextNsHasFilter, makeIdsOptRef
                )
                case a: AttrOneOpt => noOptional(a)
              }

              case a: AttrSet => a match {
                case a: AttrSetMan => rec(
                  es, stages, refPath, idsModel, a :: updateModel,
                  isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef
                )
                case a: AttrSetTac =>
                  if (a.op == Eq) {
                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
                  }
                  rec(
                    es, stages, refPath, a :: idsModel, updateModel,
                    isCardOne, true, nextNsHasFilter, makeIdsOptRef
                  )
                case _: AttrSetOpt => noOptional(a)
              }

              case a: AttrSeq => a match {
                case a: AttrSeqMan => rec(
                  es, stages, refPath, idsModel, a :: updateModel,
                  isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef
                )
                case a: AttrSeqTac =>
                  if (a.op == Eq) {
                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
                  }
                  rec(
                    es, stages, refPath, a :: idsModel, updateModel,
                    isCardOne, true, nextNsHasFilter, makeIdsOptRef
                  )
                case _: AttrSeqOpt => noOptional(a)
              }

              case a: AttrMap => a match {
                case a: AttrMapMan => rec(
                  es, stages, refPath, idsModel, a :: updateModel,
                  isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef
                )
                case a: AttrMapTac => rec(
                  es, stages, refPath, a :: idsModel, updateModel,
                  isCardOne, true, nextNsHasFilter, makeIdsOptRef
                )
                case _: AttrMapOpt => noOptional(a)
              }
            }

          case ref@Ref(ns, refAttr, refNs, _, _, _) if nsHasFilter || nextNsHasFilter =>
            val curRefPath   = if (refPath.isEmpty) {
              // Start ref path
              List(ns, refAttr, refNs)
            } else {
              // Extend ref path from following namespaces
              List(ns, refAttr) ++ refPath
            }
            val updateNsData = if (updateModel.isEmpty) {
              Nil
            } else if (refPath.isEmpty) {
              List(UpdateNsData(curRefPath, updateModel))
            } else {
              List(UpdateNsData(List(ns, refAttr, refNs), updateModel))
            }
            if (stages.nonEmpty && stages.head.isInstanceOf[CompleteCurRef]) {
              val idsResolver    = stages.head.asInstanceOf[CompleteCurRef].idsResolver
              val refAttrElement = idsResolver(Nil).last
              val idsModel1      = ref :: AttrOneManID(refNs, "id") :: (idsModel :+ refAttrElement)

              // Exclude CompleteCurRef stage since we'll find an optional ref id
              // Set current ref path
              val stagesWithoutCompleteCurRef = stages.tail.map {
                case UpdateNsData(_, elements)   => UpdateNsData(curRefPath, elements)
                case CompleteCurRef(_, elements) => CompleteCurRef(curRefPath, elements)
                case other                       => other
              }
              val stages1                     = updateNsData ::: stagesWithoutCompleteCurRef
              rec(es, stages1, curRefPath, idsModel1, Nil, isCardOne, false, true, true)
            } else {
              val idsModel1 = ref :: AttrOneManID(refNs, "id") :: idsModel
              val stages1   = updateNsData ::: stages
              rec(es, stages1, curRefPath, idsModel1, Nil, isCardOne, false, true, makeIdsOptRef)
            }

          case Ref(ns, refAttr, refNs, card, _, _) =>
            // use ref path for this namespace only
            val refPath1   = List(ns, refAttr, refNs)
            val isCardOne1 = card.isInstanceOf[CardOne]

            val newRef        = if (isCardOne1) {
              CompleteCurRef(
                refPath1,
                (ids: List[Long]) => List(
                  AttrOneManID(ns, "id", Eq, ids),
                  AttrOneOptID(ns, refAttr, refNs = Some(refNs))
                )
              )
            } else {
              CompleteCurRef(
                refPath1,
                (ids: List[Long]) => List(
                  AttrOneManID(ns, "id", Eq, ids),
                  AttrOneOptID(ns, refAttr, refNs = Some(refNs))
                )
              )
            }
            val updatedStages = if (stages.isEmpty) {
              Nil
            } else {
              // Prepend current ref path
              val curRefPath = List(ns, refAttr) ++ refPath
              stages.map {
                case UpdateNsData(_, elements)   => UpdateNsData(curRefPath, elements)
                case CompleteCurRef(_, elements) => CompleteCurRef(curRefPath, elements)
                case other                       => other
              }
            }
            val stages1       = if (updateModel.isEmpty) {
              newRef :: updatedStages
            } else {
              newRef :: UpdateNsData(refPath1, updateModel) :: updatedStages
            }
            rec(es, stages1, refPath1, Nil, Nil, isCardOne1, false, nextNsHasFilter, makeIdsOptRef)

          case _: BackRef => throw ModelError("Back refs not allowed in upserts")
          case _          => noNested
        }

        case Nil if makeIdsOptRef =>
          //          println(s"\n... 1 ......  $nsHasFilter  $nextNsHasFilter  $refPath  ${stages.length}")
          val refPath1     = refPath
          val ns           = refPath1.head
          val findKnownIds = FindKnownIds(refPath, AttrOneManID(ns, "id") +: idsModel)
          if (updateModel.isEmpty) {
            findKnownIds :: stages
          } else {
            findKnownIds :: UpdateNsData(List(ns), updateModel) :: stages
          }

        case Nil if nextNsHasFilter =>
          //          println(s"\n... 2 ......  $nsHasFilter  $nextNsHasFilter  $refPath  ${stages.length}")
          val ns         = refPath.head
          val findAllIds = FindAllIds(refPath, AttrOneManID(ns, "id") +: idsModel)
          if (updateModel.isEmpty) {
            findAllIds :: stages
          } else {
            findAllIds :: UpdateNsData(List(ns), updateModel) :: stages
          }

        case Nil =>
          //          println(s"\n... 3 ......  $nsHasFilter  $nextNsHasFilter  $refPath")
          val List(ns, refAttr, refNs) = refPath.take(3)
          val refId                    = if (isCardOne)
            AttrOneOptID(ns, refAttr, refNs = Some(refNs))
          else
            NestedOpt(
              Ref(ns, refAttr, refNs, CardSet, false),
              List(
                AttrOneManID(refNs, "id")))
          val idsModel1                = AttrOneManID(ns, "id") +: idsModel :+ refId
          val findKnownIds             = FindKnownIds(refPath, idsModel1)
          val stages1                  = if (stages.isEmpty) Nil else stages.tail
          if (updateModel.isEmpty) {
            findKnownIds :: stages1
          } else {
            findKnownIds :: UpdateNsData(refPath, updateModel) :: stages1
          }
      }
    }

    rec(elements.reverse, Nil, Nil, Nil, Nil, true, false, false, false)
  }


  protected def refUpdates(elements: List[Element], isUpsert: Boolean)(implicit conn: JdbcConn_JVM): Data = {
    //    val elements = update.elements

    //    println("............ elements")
    //    elements.foreach(println)

    val (arity, idsModel) = getUpdateIdsModel(elements)

    //    println(s"------ updateIdsModel ------  $arity")
    //    idsModel.foreach(println)

    val idLists = getIdLists(arity, idsModel)

    //    println(s"------ idLists ------  $arity")
    //    idLists.foreach(println)

    if (idLists.isEmpty) {
      (Nil, Nil)
    } else {
      var i            = 0
      val tableUpdates = getUpdateResolvers(elements).flatMap {
        case (refPath, modelResolver) =>
          val updateModel = modelResolver(idLists(i)) // we pass current ids in Table

          val n = updateModel.length
          //          println(s"------ X ------- $refPath  $n")
          //          updateModel.foreach(println)
          i += 1
          //          update_getData(conn, updateModel, isUpsert)._1
          update_getData(conn, Update(updateModel, isUpsert))._1
      }
      (tableUpdates, Nil)
    }
  }

  private def getId(
    stmt: String,
    table: String,
    updateId: Option[Long] = None,
  )(implicit conn: JdbcConn_JVM): Long = {
    val ps = conn.sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
    ps.addBatch()
    ps.executeBatch()
    val resultSet = ps.getGeneratedKeys
    val id        = if (resultSet.next()) {
      resultSet.getLong(1)
    } else if (updateId.nonEmpty) {
      //      println("getId stmt:\n" + stmt)
      // MariaDB doesn't return id from updates, so we use supplied id in update stmt
      updateId.get
    } else {
      val getPrevId = conn.sqlConn.prepareStatement(
        s"select max(id) from $table"
      ).executeQuery()
      getPrevId.next()
      getPrevId.getLong(1)
    }
    //    println(stmt + "  -->  id " + id)
    id
  }

  protected def getIdLists(arity: Int, idsModel: List[Element])
                          (implicit conn: JdbcConn_JVM): Array[List[Long]] = {
    val idRows = query_getRaw(Query[AnyRef](idsModel))
    //    println("------ idRows --------")
    //    idRows.foreach(println)
    //    println("--------")

    if (idRows.isEmpty) {
      Array.empty[List[Long]]
    } else {

      val idLists = arity match {
        case 1 => Array(idRows.asInstanceOf[List[Long]])
        case 2 =>
          val (aa, bb) = idRows.asInstanceOf[List[(Long, Long)]].unzip
          Array(aa, bb)

        case 3 =>
          val (aa, bb, cc) = idRows.asInstanceOf[List[(Long, Long, Long)]].unzip3
          Array(aa, bb, cc)

        case idCount =>
          val result = Array.fill(idCount)(List.empty[Long])
          idRows.asInstanceOf[List[Product]].foreach { tuple =>
            (0 until idCount).foreach { i =>
              result(i) = result(i) :+ tuple.productElement(i).asInstanceOf[Long]
            }
          }
          result
      }
      //      println(idLists.mkString("Array(", ", ", ")"))
      idLists
    }
  }

  // Implement for each sql database
  def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String
}
