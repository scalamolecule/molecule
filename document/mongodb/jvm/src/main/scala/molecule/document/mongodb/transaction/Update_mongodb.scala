package molecule.document.mongodb.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.action.Query
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.spi.SpiSync_mongodb
import org.bson.types.ObjectId
import org.bson._
import scala.collection.mutable.ListBuffer

trait Update_mongodb
  extends Base_JVM_mongodb
    with UpdateOps
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false

  case class RefData(
    var referee: Option[RefData] = None,
    var ns: String = "",
    doc: BsonDocument = new BsonDocument(),
    var ids: Seq[String] = Seq.empty[String],
    var filterElements: List[Element] = List.empty[Element],
    uniqueFilterElements: ListBuffer[Element] = ListBuffer.empty[Element],
  ) {
    override def toString: String = {
      val referee1              = if (referee.isEmpty) "None" else s"Some(${referee.get.ns})"
      val filterElements1       = if (filterElements.isEmpty) "Nil" else
        filterElements.mkString("\n    ", ",\n    ", "")
      val uniqueFilterElements1 = if (uniqueFilterElements.isEmpty) "Nil" else
        uniqueFilterElements.mkString("\n    ", ",\n    ", "")
      s"""RefData(
         |  referee             : $referee1
         |  ns                  : $ns
         |  doc                 : $doc
         |  ids                 : $ids
         |  filterElements      : $filterElements1
         |  uniqueFilterElements: $uniqueFilterElements1
         |)""".stripMargin
    }
  }

  // Initial namespace data container
  var d  = RefData()
  val dd = ListBuffer.empty[RefData].addOne(d)

  var conn: MongoConn_JVM = null

  def getData(elements: List[Element], conn0: MongoConn_JVM): Data = {
    conn = conn0
    d.ns = getInitialNs(elements)
    resolve(elements)
    val data = new BsonDocument("_action", new BsonString("update"))
    dd.foreach { case x@RefData(_, ns, doc, ids, filterElements, uniqueFilterElements) =>
      println(x)

      val ids1 = if (isUpdate) {
        if (ids.nonEmpty && filterElements.nonEmpty) {
          println("A")
          val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements.toList)
          println("A ------ filterIds: " + filterIds)
          val validIds = ids.intersect(filterIds)
          println("A ------ validIds : " + validIds)
          validIds

        } else if (ids.nonEmpty && uniqueFilterElements.nonEmpty) {
          println("B")
          val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
          println("B ------ uniqueFilterIds: " + filterIds)
          val validIds = ids.intersect(filterIds)
          println("B ------ validIds       : " + validIds)
          validIds

        } else if (uniqueFilterElements.nonEmpty) {
          println("C")
          val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
          println("C ------ filterIds: " + filterIds)
          filterIds

        } else if (filterElements.nonEmpty) {
          println("D")
          val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
          println("D ------ filterIds: " + filterIds)
          filterIds
        } else {
          println("E ------ ")
          Nil

        }
      } else {
        if (ids.nonEmpty) {
          println("F")
          //          throw ModelError("f")
          ids
        } else if (filterElements.nonEmpty) {
          //          throw ModelError("g")
          println("G")
          val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
          println("G ------ filterIds: " + filterIds)
          filterIds
        } else if (uniqueFilterElements.nonEmpty) {
          throw ModelError("h")
          println("H")
          val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
          println("H ------ uniqueFilterIds: " + filterIds)
          filterIds
        } else {
          throw ModelError("i")
          println("I")
          Nil
        }
      }

      //      val idsX = if (isUpdate) {
      //        if (ids.nonEmpty) {
      //          if (filterElements.nonEmpty) {
      //            println("A")
      //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements.toList)
      //            println("A ------ filterIds: " + filterIds)
      //            val validIds = ids.intersect(filterIds)
      //            println("A ------ validIds : " + validIds)
      //            validIds
      //          } else if (uniqueFilterElements.nonEmpty) {
      //            println("A")
      //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
      //            println("A ------ filterIds: " + filterIds)
      //            val validIds = ids.intersect(filterIds)
      //            println("A ------ validIds : " + validIds)
      //            validIds
      //          } else {
      //
      //          }
      //        } else {
      //
      //        }
      //        if (ids.nonEmpty && filterElements.nonEmpty) {
      //          println("A")
      //          val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements.toList)
      //          println("A ------ filterIds: " + filterIds)
      //          val validIds = ids.intersect(filterIds)
      //          println("A ------ validIds : " + validIds)
      //          validIds
      //        } else {
      //
      //          if (filterElements.nonEmpty) {
      //            println("B")
      //            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
      //            println("B ------ filterIds: " + filterIds)
      //            filterIds
      //          } else {
      //            println("C ------ ")
      //            Nil
      //          }
      //        }
      //      } else {
      //        if (ids.nonEmpty) {
      //          println("D")
      //          ids
      //        } else if (filterElements.nonEmpty) {
      //          //          throw ModelError("e")
      //          println("E")
      //          val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
      //          println("E ------ filterIds: " + filterIds)
      //          filterIds
      //        } else {
      //          throw ModelError("f")
      //          println("F")
      //          Nil
      //        }
      //      }

      if (ids1.nonEmpty) {
        val idArray = new BsonArray()
        ids1.foreach(id => idArray.add(new BsonObjectId(new ObjectId(id))))
        data.append(ns, new BsonDocument("ids", idArray).append("data", doc))
      }
    }
    data
  }

  private def query(elements: List[Element]): List[String] = {
    SpiSync_mongodb.query_get[String](Query(elements))(conn)
  }

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, owner, _) = ref
    if (owner) {
      // Embed document
      d.filterElements = d.filterElements :+ ref
      path = path :+ refAttr

    } else {
      // Referenced document

      if (d.filterElements.nonEmpty && d.filterElements.last.isInstanceOf[BackRef]) {
        // Remove BackRef orphan
        d.filterElements = d.filterElements.init
      }

      // Retrieve reference ids (could be done in one query for all refs...)
      val refIds = query(List(
        AttrOneTacID(ns, "id", Eq, d.ids),
        AttrOneManID(ns, refAttr, V),
      ))
      path = Nil

      // Process referenced documents
      val referee = Some(d)
      d = RefData(referee, refNs, ids = refIds)
      dd += d
    }
  }

  override def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    owner: Boolean,
    transformValue: T => Any,
    handleValue: T => Any,
  ): Unit = {
    val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)

    if (isUpdate) {
      if (owner) {
        throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
      }
      d.filterElements = d.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter
    }
    vs match {
      case Seq(v) =>
        d.doc.append(pathAttr, transformValue(v).asInstanceOf[BsonValue])
      case Nil    =>
        d.doc.append(pathAttr, new BsonNull())
      case vs     =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only $update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
  }

  override def handleIds(ns: String, ids0: Seq[String]): Unit = {
    if (d.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    d.ids = ids0
  }


  override def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      //      updateCurRefPath(attr)
      //      placeHolders = placeHolders :+ s"$attr = ?"
      //      val colSetter = sets match {
      //        case Seq(set) =>
      //          if (!isUpsert) {
      //            addToUpdateCols(ns, attr)
      //          }
      //          val array = set2array(set.asInstanceOf[Set[Any]])
      //          (ps: PS, _: IdsMap, _: RowIndex) => {
      //            val conn = ps.getConnection
      //            ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
      //            curParamIndex += 1
      //          }
      //        case Nil      =>
      //          (ps: PS, _: IdsMap, _: RowIndex) => {
      //            ps.setNull(curParamIndex, 0)
      //            curParamIndex += 1
      //          }
      //        case vs       => throw ExecutionError(
      //          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      //        )
      //      }
      //      addColSetter(curRefPath, colSetter)
      ???

    } { refNs =>
      //      // Separate update of ref ids in join table -----------------------------
      //      val refAttr   = attr
      //      val joinTable = ss(ns, refAttr, refNs)
      //      val ns_id     = ss(ns, "id")
      //      val refNs_id  = ss(refNs, "id")
      //      val id        = getUpdateId
      //      sets match {
      //        case Seq(set) =>
      //          // Tables are reversed in JdbcConn_JVM and we want to delete first
      //          manualTableDatas = List(
      //            addJoins(joinTable, ns_id, refNs_id, id, set.map(_.asInstanceOf[String].toLong)),
      //            deleteJoins(joinTable, ns_id, id)
      //          )
      //
      //        case Nil =>
      //          // Delete all joins when no ref ids are applied
      //          manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
      //
      //        case vs => throw ExecutionError(
      //          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      //        )
      //      }

      ???
    }
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      //      if (sets.nonEmpty && sets.head.nonEmpty) {
      //        updateCurRefPath(attr)
      //        if (!isUpsert) {
      //          addToUpdateCols(ns, attr)
      //        }
      //        placeHolders = placeHolders :+ s"$attr = $attr || ?"
      //        val array = set2array(sets.head.asInstanceOf[Set[Any]])
      //        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
      //          val conn = ps.getConnection
      //          ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
      //          curParamIndex += 1
      //        })
      //      }
      ???

    } { refNs =>
      //      // Separate update of ref ids in join table -----------------------------
      //      val refAttr   = attr
      //      val joinTable = ss(ns, refAttr, refNs)
      //      val ns_id     = ss(ns, "id")
      //      val refNs_id  = ss(refNs, "id")
      //      sets match {
      //        case Seq(set) => manualTableDatas = List(
      //          addJoins(joinTable, ns_id, refNs_id, getUpdateId, set.map(_.asInstanceOf[String].toLong))
      //        )
      //        case Nil      => () // Add no ref ids
      //        case vs       => throw ExecutionError(
      //          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      //        )
      //      }

      ???
    }
  }

  override def updateSetSwap[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    one2json: T => String
  ): Unit = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    val swaps              = retracts.zip(adds)
    if (retracts.isEmpty) {
      // Do nothing if no swap pairs
      return
    }
    if (retracts.length != retracts.distinct.length) {
      throw ExecutionError(s"Can't swap from duplicate retract values.")
    }
    if (adds.length != adds.distinct.length) {
      throw ExecutionError(s"Can't swap to duplicate replacement values.")
    }
    if (retracts.size != adds.size) {
      throw ExecutionError(
        s"""Can't swap duplicate keys/values:
           |  RETRACTS: $retracts
           |  ADDS    : $adds
           |""".stripMargin
      )
    }
    val table  = ns
    val nsAttr = s"$ns.$attr"
    val dbType = exts(1)
    refNs.fold {
      //      updateCurRefPath(nsAttr)
      //      def setterClauses(indents: Int) = swaps.map(_ => s"WHEN v = ? THEN ?").mkString("\n" + "  " * indents)
      //      val idClause  = s"$table.id IN (${ids.mkString(", ")})"
      //      val colSetter = if (isUpsert) {
      //        val guaranteedInsertValues = adds.map(_ => s"?::$dbType").mkString(", ")
      //        placeHolders = placeHolders :+
      //          s"""$nsAttr = ARRAY(
      //             |    SELECT v
      //             |    FROM TABLE_DISTINCT(
      //             |      v $dbType = ARRAY(
      //             |        SELECT CASE
      //             |          ${setterClauses(5)}
      //             |          ELSE v
      //             |        END
      //             |        FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
      //             |      ) || ARRAY[$guaranteedInsertValues]
      //             |    )
      //             |  )""".stripMargin
      //
      //        (ps: PS, _: IdsMap, _: RowIndex) => {
      //          swaps.foreach { case (retract, add) =>
      //            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
      //            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex + 1)
      //            curParamIndex += 2
      //          }
      //          adds.foreach { add =>
      //            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
      //          }
      //        }
      //      } else {
      //        placeHolders = placeHolders :+
      //          s"""$nsAttr = ARRAY(
      //             |    SELECT CASE
      //             |      ${setterClauses(3)}
      //             |      ELSE v
      //             |    END
      //             |    FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
      //             |  )""".stripMargin
      //
      //        (ps: PS, _: IdsMap, _: RowIndex) => {
      //          swaps.foreach { case (retract, add) =>
      //            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
      //            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex + 1)
      //            curParamIndex += 2
      //          }
      //        }
      //      }
      //      addColSetter(curRefPath, colSetter)

      ???
    } { refNs =>
      //      // Separate update of ref ids in join table -----------------------------
      //      val refAttr   = attr
      //      val joinTable = ss(ns, refAttr, refNs)
      //      val ns_id     = ss(ns, "id")
      //      val refNs_id  = ss(refNs, "id")
      //      val id        = getUpdateId
      //      if (isUpsert) {
      //        val retractIds = retracts.mkString(s" AND $refNs_id IN (", ", ", ")")
      //        manualTableDatas = List(
      //          // Add joins regardless if the old ref id was present
      //          addJoins(joinTable, ns_id, refNs_id, id, adds.asInstanceOf[Seq[Long]]),
      //          deleteJoins(joinTable, ns_id, id, retractIds)
      //        )
      //      } else {
      //        val matches   = swaps.map {
      //          case (oldId, newId) => s"WHEN $refNs_id = $oldId THEN $newId"
      //        }.mkString("\n      ")
      //        val swapJoins =
      //          s"""UPDATE $joinTable
      //             |SET
      //             |  $refNs_id =
      //             |    CASE
      //             |      $matches
      //             |      ELSE $refNs_id
      //             |    END
      //             |WHERE $ns_id = $id""".stripMargin
      //        val swapPS    = sqlConn.prepareStatement(swapJoins, Statement.RETURN_GENERATED_KEYS)
      //        val swap      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      //        val swapPath  = List("swapJoins")
      //
      //        // Do updates only (no new ref ids inserted if old ref id was not there)
      //        manualTableDatas = List(Table(swapPath, swapJoins, swapPS, swap))
      //      }
      ???
    }
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String],
    one2json: T => String
  ): Unit = {
    val table  = ns
    val nsAttr = s"$ns.$attr"
    val dbType = exts(1)
    refNs.fold {
      //      if (set.nonEmpty) {
      //        updateCurRefPath(nsAttr)
      //        val idClause = s"$table.id IN (${ids.mkString(", ")})"
      //        if (!isUpsert) {
      //          addToUpdateCols(ns, attr)
      //        }
      //        val removeValuePlaceHolders = set.toList.map(_ => "?").mkString(", ")
      //        placeHolders = placeHolders :+
      //          s"""$nsAttr =
      //             |  CASE
      //             |    WHEN
      //             |      ARRAY(
      //             |        SELECT v
      //             |        FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
      //             |        WHERE v NOT IN ($removeValuePlaceHolders)
      //             |      ) = array[]
      //             |    THEN
      //             |      NULL
      //             |  ELSE
      //             |    ARRAY(
      //             |      SELECT v
      //             |      FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
      //             |      WHERE v NOT IN ($removeValuePlaceHolders)
      //             |    )
      //             |  END""".stripMargin
      //
      //        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
      //          set.foreach { v =>
      //            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
      //            curParamIndex += 1
      //          }
      //          set.foreach { v =>
      //            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
      //            curParamIndex += 1
      //          }
      //        })
      //      }

      ???
    } { refNs =>
      //      if (set.nonEmpty) {
      //        // Separate update of ref ids in join table -----------------------------
      //        val refAttr    = attr
      //        val joinTable  = ss(ns, refAttr, refNs)
      //        val ns_id      = ss(ns, "id")
      //        val refNs_id   = ss(refNs, "id")
      //        val retractIds = set.mkString(s" AND $refNs_id IN (", ", ", ")")
      //        manualTableDatas = List(
      //          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
      //        )
      //      }

      ???
    }
  }


  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (d.uniqueFilterElements.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    d.uniqueFilterElements += uniqueFilterAttr
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    d.filterElements = d.filterElements :+ filterAttr
  }


  override def handleBackRef(backRef: BackRef): Unit = {
    d.referee.fold[Unit] {
      path = path.init
      d.filterElements = d.filterElements :+ backRef
    } { referee =>
      path = Nil
      d = referee
    }
  }
}