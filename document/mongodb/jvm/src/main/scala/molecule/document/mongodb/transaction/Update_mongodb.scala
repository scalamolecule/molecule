package molecule.document.mongodb.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.time._
import java.util.Date
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.document.mongodb.query.Model2MongoQuery

trait Update_mongodb
  extends Base_JVM_mongodb
    with UpdateOps
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false
  protected var curParamIndex = 1

  def model2SqlQuery(elements: List[Element]): Model2MongoQuery[Any] =
    new Model2MongoQuery[Any](elements)

  def getData(elements: List[Element]): Data = {
    //    curRefPath = List(getInitialNs(elements))
    //    resolve(elements)
    //    addRowSetterToTables()
    //    (manualTableDatas ++ getTables, Nil)

    ???
  }

  override def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit = {
    //    updateCurRefPath(attr)
    //    placeHolders = placeHolders :+ s"$attr = ?"
    //    val colSetter: Setter = vs match {
    //      case Seq(v) =>
    //        if (!isUpsert) {
    //          addToUpdateCols(ns, attr)
    //        }
    //        (ps: PS, _: IdsMap, _: RowIndex) => {
    //          handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
    //          curParamIndex += 1
    //        }
    //      case Nil    =>
    //        (ps: PS, _: IdsMap, _: RowIndex) => {
    //          ps.setNull(curParamIndex, 0)
    //          curParamIndex += 1
    //        }
    //      case vs     =>
    //        val cleanAttr = attr.replace("_", "")
    //        throw ExecutionError(
    //          s"Can only $update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
    //        )
    //    }
    //    addColSetter(curRefPath, colSetter)
    ???
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


  override def handleIds(ids1: Seq[String]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = Some(ids1)
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (uniqueFilterElements.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    uniqueFilterElements = uniqueFilterElements :+ uniqueFilterAttr
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    filterElements = filterElements :+ filterAttr
  }

  override def handleRefNs(ref: Ref): Unit = ()

  override def handleBackRef(backRef: BackRef): Unit = ()


  //  // Ref id join handling --------------------------------------------------------------------
  //
  //  protected def deleteJoins(joinTable: String, ns_id: String, id: Long, refIds: String = ""): Table = {
  //    val deletePath  = List("deleteJoins")
  //    val deleteJoins = s"DELETE FROM $joinTable WHERE $ns_id = $id" + refIds
  //    val deletePS    = sqlConn.prepareStatement(deleteJoins, Statement.RETURN_GENERATED_KEYS)
  //    val delete      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
  //    Table(deletePath, deleteJoins, deletePS, delete)
  //  }
  //
  //  protected def addJoins(joinTable: String, ns_id: String, refNs_id: String, id: Long, refIds: Iterable[Long]): Table = {
  //    val addPath  = List("addJoins")
  //    val addJoins = s"INSERT INTO $joinTable($ns_id, $refNs_id) VALUES (?, ?)"
  //    val addPS    = sqlConn.prepareStatement(addJoins, Statement.RETURN_GENERATED_KEYS)
  //    val add      = (ps: PS, _: IdsMap, _: RowIndex) =>
  //      refIds.foreach { refId =>
  //        ps.setLong(1, id)
  //        ps.setLong(2, refId)
  //        ps.addBatch()
  //      }
  //    Table(addPath, addJoins, addPS, add)
  //  }
  //
  //  protected def getUpdateId: Long = {
  //    ids.toList match {
  //      case List(v) => v
  //      case other   => throw ModelError("Expected to update one entity. Found multiple ids: " + other)
  //    }
  //  }

}