package molecule.sql.core.transaction

import java.net.URI
import java.sql.{PreparedStatement => PS}
import java.time._
import java.util.{Date, UUID}
import boopickle.Default._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.Model2SqlQuery

trait SqlUpdate
  extends SqlBase_JVM
    with UpdateOps
    with SqlBaseOps
    with UpdateUtils
    with MoleculeLogging { self: ResolveUpdate =>

  //  doPrint = false
  doPrint = true
  protected var curParamIndex = 1

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any]

  def getUpdateData(elements: List[Element]): Data = {
    curRefPath = List(getInitialNs(elements))
    // Resolve update model
    resolve(elements)
    addRowSetterToTables()
    (manualTableDatas ++ getTables, Nil)
  }


  protected def addRowSetterToTables(): Unit = {
    updates.foreach {
      case (refPath, _) =>
        val table              = refPath.last
        val (stmt, upsertStmt) = if (isUpsert) {
          var inputs   = ""
          var colList  = ""
          var setCols  = ""
          var colValus = ""
          var first    = true
          placeHolders2.foreach {
            case (col, placeHolder, expr) if first =>
              inputs = placeHolder
              colList = col
              setCols = s"$table.$col = $expr"
              colValus = s"_v.$col"
              first = false
            case (col, placeHolder, expr)          =>
              inputs = s"$inputs, $placeHolder"
              colList = s"$colList,\n    $col"
              setCols = s"$setCols,\n    $table.$col = $expr"
              colValus = s"$colValus,\n    _v.$col"
          }
          val upsertStmt = (ids1: List[Long]) => {
            val ids2 = if (ids1.nonEmpty)
              ids1 // ids of existing and new entities (when ref structure establishment)
            else if (ids.nonEmpty)
              ids // ids of existing entities (collected in handleIds)
            else
              List(-1) // no existing entities

            s"""MERGE INTO $table
               |USING VALUES (
               |  $inputs
               |) _v($colList)
               |ON $table.id IN(${ids2.mkString(", ")})
               |WHEN MATCHED THEN
               |  UPDATE SET
               |    $setCols
               |WHEN NOT MATCHED THEN
               |  INSERT ($colList) VALUES (
               |    $colValus
               |  )""".stripMargin
          }
          ("", Some(upsertStmt))

        } else {
          val updateCols2_ = if (updateCols.isEmpty) Nil else
            updateCols(refPath).map(c => s"$c IS NOT NULL")

          val clauses_ = if (ids.nonEmpty) {
            List(s"$table.id IN(${ids.mkString(", ")})")
          } else {
            model2SqlQuery(filterElements).getWhereClauses
          }
          val clauses  = (updateCols2_ ++ clauses_).mkString(" AND\n  ")

          val columnSetters = placeHolders.mkString(",\n  ")
          val stmt          =
            s"""UPDATE $table
               |SET
               |  $columnSetters
               |WHERE
               |  $clauses""".stripMargin
          (stmt, None)
        }

        println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("------ stmt --------")
        println(stmt)
        println("------ upsertStmt --------")
        println(upsertStmt.map(_(List(1))))

        debug(stmt)
        val colSetters = colSettersMap(refPath)
        debug(s"--- update -------------------  ${colSetters.length}  $refPath")

        tableDatas(refPath) = Table(refPath, stmt,
          useAccIds = isUpsert,
          upsertStmt = upsertStmt,
          updateIdsMap = isUpsert
        )

        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach { colSetter =>
            //            debug("\nps  Z   : " + ps)
            //            debug("idsMap    : " + idsMap)

            colSetter(ps, idsMap, 0)
          }
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }


  protected def getTables: List[Table] = {
    // Add insert resolver to each table insert
    updates.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
        )
      }
      tableDatas(refPath).copy(populatePS = populatePS)
    }
  }

  protected def updateCurRefPath(attr: String): (List[String], Int) = {
    if (updates.exists(_._1 == curRefPath)) {
      updates = updates.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, cols :+ attr)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      updates = updates :+ (curRefPath, List(attr))
    }
    (curRefPath, paramIndexes(curRefPath, attr))
  }

  protected def addToUpdateColsNotNull(ns: String, attr: String) = {
    if (!updateCols.contains(curRefPath)) {
      updateCols += curRefPath -> Nil
    }
    updateCols(curRefPath) = updateCols(curRefPath) :+ s"$ns.$attr"
  }


  override protected def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    updateCurRefPath(attr)
    placeHolders = placeHolders :+ s"$attr = ?"
    placeHolders2 += ((attr, "?", s"_v.$attr"))
    val colSetter: Setter = vs match {
      case Seq(v) =>
        if (!isUpsert) {
          addToUpdateColsNotNull(ns, attr)
        }
        (ps: PS, _: IdsMap, _: RowIndex) => {
          transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
          curParamIndex += 1
        }
      case Nil    =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(curParamIndex, 0)
          curParamIndex += 1
        }
      case vs     =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
    addColSetter(curRefPath, colSetter)
  }

  override protected def updateSetEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, set, exts, set2array)
  }

  override protected def updateSetAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(ns, attr, optRefNs, set, exts, set2array)
  }

  override protected def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, set, exts, set2array)
  }

  override protected def updateSeqEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, seq, exts, seq2array)
  }

  override protected def updateSeqAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(ns, attr, optRefNs, seq, exts, seq2array)
  }

  override protected def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, seq, exts, seq2array)
  }

  override protected def updateByteArray(
    ns: String,
    attr: String,
    byteArray: Array[Byte],
  ): Unit = {
    updateCurRefPath(attr)
    placeHolders = placeHolders :+ s"$attr = ?"
    placeHolders2 += ((attr, "?", s"_v.$attr"))
    val colSetter: Setter = if (byteArray.isEmpty) {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(curParamIndex, 0)
        curParamIndex += 1
      }
    } else {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setBytes(curParamIndex, byteArray)
        curParamIndex += 1
      }
    }
    addColSetter(curRefPath, colSetter)
  }

  override protected def updateMapEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateMapEqJdbc(ns, attr, "", map,
      (ps: PS, paramIndex: Int) => {
        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      }
    )
  }

  override protected def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      updateCurRefPath(attr)
      if (!isUpsert) {
        addToUpdateColsNotNull(ns, attr)
      }
      val nsAttr        = s"$ns.$attr"
      val scalaBaseType = exts.head
      placeHolders = placeHolders :+ s"$nsAttr = addPairs_$scalaBaseType($ns.$attr, ?)"
      placeHolders2 += ((
        attr,
        s"CAST(? AS JSON)",
        s"addPairs_$scalaBaseType($ns.$attr, _v.$attr)"
      ))

      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setBytes(curParamIndex, map2jsonByteArray(map, value2json))
        curParamIndex += 1
      }
      addColSetter(curRefPath, colSetter)
    }
  }


  override protected def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      updateCurRefPath(attr)
      if (!isUpsert) {
        addToUpdateColsNotNull(ns, attr)
      }
      val nsAttr        = s"$ns.$attr"
      val scalaBaseType = exts.head
      placeHolders = placeHolders :+ s"$nsAttr = removePairs_$scalaBaseType($ns.$attr, ?)"
      placeHolders2 += ((
        attr,
        s"CAST(? AS JSON)",
        s"removePairs_$scalaBaseType($ns.$attr, _v.$attr)"
      ))

      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setBytes(curParamIndex, map2jsonByteArray(map, value2json))
        curParamIndex += 1
      }
      addColSetter(curRefPath, colSetter)
    }
  }


  override def handleIds(ns: String, ids1: Seq[String]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in update.")
    }
    ids = ids1.map(_.toLong)
  }

  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    filterElements = filterElements :+ filterAttr
  }

  override def handleRefNs(ref: Ref): Unit = {
    curRefPath ++= List(ref.refAttr, ref.refNs)
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    curRefPath = curRefPath.dropRight(2)
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    iterable: M[T],
    exts: List[String],
    vs2array: M[T] => Array[AnyRef],
  ): Unit = {
    val baseType = exts(1)
    refNs.fold {
      updateCurRefPath(attr)
      placeHolders = placeHolders :+ s"$attr = ?"
      placeHolders2 += ((attr, s"CAST(? AS $baseType ARRAY)", s"_v.$attr"))
      val colSetter = if (iterable.nonEmpty) {
        if (!isUpsert) {
          addToUpdateColsNotNull(ns, attr)
        }
        val array = vs2array(iterable)
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(baseType, array))
          curParamIndex += 1
        }
      } else {
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(curParamIndex, 0)
          curParamIndex += 1
        }
      }
      addColSetter(curRefPath, colSetter)
    } { refNs =>
      joinEq(ns, attr, refNs, iterable)
    }
  }

  private def updateIterableAdd[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef],
  ): Unit = {
    val dbBaseType = exts(1)
    refNs.fold {
      if (iterable.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateColsNotNull(ns, attr)
        }
        placeHolders = placeHolders :+ s"$attr = $attr || ?"
        placeHolders2 += ((
          attr,
          s"CAST(? AS $dbBaseType ARRAY)",
          s"IFNULL($ns.$attr, ARRAY[]) || _v.$attr"
        ))
        val array = iterable2array(iterable)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(dbBaseType, array))
          curParamIndex += 1
        })
      }
    } { refNs =>
      joinAdd(ns, attr, refNs, iterable)
    }
  }

  private def updateIterableRemove[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef]
  ): Unit = {
    val nsAttr        = s"$ns.$attr"
    val scalaBaseType = exts.head
    val dbBaseType    = exts(1)
    refNs.fold {
      if (iterable.nonEmpty) {
        updateCurRefPath(nsAttr)
        if (!isUpsert) {
          addToUpdateColsNotNull(ns, attr)
        }
        placeHolders = placeHolders :+ s"$nsAttr = removeFromArray_$scalaBaseType($ns.$attr, ?)"
        placeHolders2 += ((
          attr,
          s"CAST(? AS $dbBaseType ARRAY)",
          s"removeFromArray_$scalaBaseType($ns.$attr, _v.$attr)"
        ))

        val array = iterable2array(iterable)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(dbBaseType, array))
          curParamIndex += 1
        })
      }
    } { refNs =>
      joinRemove(ns, attr, refNs, iterable)
    }
  }


  protected def joinEq[T](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: Iterable[T]
  ): Unit = {
    // Separate update of ref ids in join table -----------------------------
    val joinTable = ss(ns, refAttr, refNs)
    val ns_id     = ss(ns, "id")
    val refNs_id  = ss(refNs, "id")
    val id        = getUpdateId
    if (vs.nonEmpty) {
      // Tables are reversed in JdbcConn_JVM and we want to delete first
      manualTableDatas = List(
        addJoins(joinTable, ns_id, refNs_id, id,
          vs.map(_.asInstanceOf[String].toLong)
        ),
        deleteJoins(joinTable, ns_id, id)
      )
    } else {
      // Delete all joins when no ref ids are applied
      manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
    }
  }

  protected def joinAdd[T](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: Iterable[T]
  ): Unit = {
    if (vs.nonEmpty) {
      // Separate update of ref ids in join table -----------------------------
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      manualTableDatas = List(
        addJoins(joinTable, ns_id, refNs_id, getUpdateId,
          vs.map(_.asInstanceOf[String].toLong)
        )
      )
    }
  }

  protected def joinRemove[T](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: Iterable[T]
  ): Unit = {
    if (vs.nonEmpty) {
      // Separate update of ref ids in join table -----------------------------
      val joinTable  = ss(ns, refAttr, refNs)
      val ns_id      = ss(ns, "id")
      val refNs_id   = ss(refNs, "id")
      val retractIds = vs.mkString(s" AND $refNs_id IN (", ", ", ")")
      manualTableDatas = List(
        deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
      )
    }
  }

  private def deleteJoins(joinTable: String, ns_id: String, id: Long, refIds: String = ""): Table = {
    val deletePath  = List("deleteJoins")
    val deleteJoins = s"DELETE FROM $joinTable WHERE $ns_id = $id" + refIds
    val delete      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
    Table(deletePath, deleteJoins, delete)
  }

  private def addJoins(joinTable: String, ns_id: String, refNs_id: String, id: Long, refIds: Iterable[Long]): Table = {
    val addPath  = List("addJoins")
    val addJoins = s"INSERT INTO $joinTable($ns_id, $refNs_id) VALUES (?, ?)"
    val add      = (ps: PS, _: IdsMap, _: RowIndex) =>
      refIds.foreach { refId =>
        ps.setLong(1, id)
        ps.setLong(2, refId)
        ps.addBatch()
      }
    Table(addPath, addJoins, add)
  }

  protected def updateMapEqJdbc[T](
    ns: String,
    attr: String,
    cast: String,
    map: Map[String, T],
    map2jdbc: (PS, Int) => Unit
  ): Unit = {
    updateCurRefPath(attr)
    placeHolders = placeHolders :+ s"$attr = ?$cast"
    placeHolders2 += ((attr, s"cast(? as JSON)", s"_v.$attr"))

    if (!isUpsert) {
      addToUpdateColsNotNull(ns, attr)
    }
    val colSetter: Setter = if (map.nonEmpty) {
      (ps: PS, _: IdsMap, _: RowIndex) =>
        map2jdbc(ps, curParamIndex)
        curParamIndex += 1
    } else {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(curParamIndex, 0)
        curParamIndex += 1
      }
    }
    addColSetter(curRefPath, colSetter)
  }


  protected def updateMapAddJdbc[T](
    ns: String,
    attr: String,
    cast: String,
    map: Map[String, T],
    exts: List[String],
    map2jdbc: (PS, Int, Map[String, T]) => Unit
  ): Unit = {
    //    if (map.nonEmpty) {
    //      updateCurRefPath(attr)
    //      if (!isUpsert) {
    //        addToUpdateColsNotNull(ns, attr)
    //      }
    //
    //      val nsAttr        = s"$ns.$attr"
    //      val scalaBaseType = exts.head
    //      val dbBaseType    = exts(1)
    //
    //      //      placeHolders = placeHolders :+ s"$attr = ?$cast"
    //      //      placeHolders2 += ((attr, s"cast(? as JSON)", s"_v.$attr"))
    //
    //      placeHolders = placeHolders :+ s"$nsAttr = removeFromArray_$scalaBaseType($ns.$attr, ?)"
    //      placeHolders2 += ((
    //        attr,
    //        s"CAST(? AS JSON)",
    //        s"concatMaps_$scalaBaseType($ns.$attr, _v.$attr)"
    //      ))
    //
    //      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
    //        val updatedMap = {
    //          val query = s"SELECT $attr FROM $ns WHERE id IN(${ids.mkString(", ")})"
    //          //          val query = s"SELECT $attr FROM $ns"
    //          val rs    = ps.getConnection.prepareStatement(query).executeQuery()
    //          //          rs.next()
    //          rs.next()
    //          val byteArray = rs.getBytes(1)
    //          if (rs.wasNull()) map else {
    //            val json = new String(byteArray)
    //
    //
    //            println("    " + json)
    //
    //
    //            // Add/replace pairs in current map
    //            exts.head match {
    //              case "ID"             => upickle.default.read[Map[String, String]](json) ++ map.asInstanceOf[Map[String, String]]
    //              case "String"         => upickle.default.read[Map[String, String]](json) ++ map.asInstanceOf[Map[String, String]]
    //              case "Int"            =>
    //                val a = upickle.default.read[Map[String, Int]](json)
    //                val b = map.asInstanceOf[Map[String, Int]]
    //                val c = a ++ b
    //
    //                println("  a  " + a)
    //                println("  b  " + b)
    //                println("  c  " + c)
    //
    //                upickle.default.read[Map[String, Int]](json) ++ map.asInstanceOf[Map[String, Int]]
    //              case "Long"           => upickle.default.read[Map[String, Long]](json) ++ map.asInstanceOf[Map[String, Long]]
    //              case "Float"          => upickle.default.read[Map[String, Float]](json) ++ map.asInstanceOf[Map[String, Float]]
    //              case "Double"         => upickle.default.read[Map[String, Double]](json) ++ map.asInstanceOf[Map[String, Double]]
    //              case "Boolean"        => upickle.default.read[Map[String, Int]](json).map { case (k, v) => k -> (if (v == 1) true else false) } ++ map.asInstanceOf[Map[String, Boolean]]
    //              case "BigInt"         => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigInt(v) } ++ map.asInstanceOf[Map[String, BigInt]]
    //              case "BigDecimal"     => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigDecimal(v) } ++ map.asInstanceOf[Map[String, BigDecimal]]
    //              case "Date"           => upickle.default.read[Map[String, Long]](json).map { case (k, v) => k -> new Date(v) } ++ map.asInstanceOf[Map[String, Date]]
    //              case "Duration"       => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Duration.parse(v) } ++ map.asInstanceOf[Map[String, Duration]]
    //              case "Instant"        => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Instant.parse(v) } ++ map.asInstanceOf[Map[String, Instant]]
    //              case "LocalDate"      => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDate.parse(v) } ++ map.asInstanceOf[Map[String, LocalDate]]
    //              case "LocalTime"      => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalTime.parse(v) } ++ map.asInstanceOf[Map[String, LocalTime]]
    //              case "LocalDateTime"  => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDateTime.parse(v) } ++ map.asInstanceOf[Map[String, LocalDateTime]]
    //              case "OffsetTime"     => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetTime.parse(v) } ++ map.asInstanceOf[Map[String, OffsetTime]]
    //              case "OffsetDateTime" => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetDateTime.parse(v) } ++ map.asInstanceOf[Map[String, OffsetDateTime]]
    //              case "ZonedDateTime"  => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> ZonedDateTime.parse(v) } ++ map.asInstanceOf[Map[String, ZonedDateTime]]
    //              case "UUID"           => upickle.default.read[Map[String, UUID]](json) ++ map.asInstanceOf[Map[String, UUID]]
    //              case "URI"            => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> new URI(v) } ++ map.asInstanceOf[Map[String, URI]]
    //              case "Byte"           => upickle.default.read[Map[String, Byte]](json) ++ map.asInstanceOf[Map[String, Byte]]
    //              case "Short"          => upickle.default.read[Map[String, Short]](json) ++ map.asInstanceOf[Map[String, Short]]
    //              case "Char"           => upickle.default.read[Map[String, Char]](json) ++ map.asInstanceOf[Map[String, Char]]
    //            }
    //          }
    //        }
    //        map2jdbc(ps, curParamIndex, updatedMap.asInstanceOf[Map[String, T]])
    //        curParamIndex += 1
    //      }
    //      addColSetter(curRefPath, colSetter)
    //    }

    ???
  }

  protected def updateMapRemoveJdbc[T](
    ns: String,
    attr: String,
    cast: String,
    map: Map[String, T],
    exts: List[String],
    map2jdbc: (PS, Int, Map[String, T]) => Unit
  ): Unit = {
    //    if (map.nonEmpty) {
    //      updateCurRefPath(attr)
    //      if (!isUpsert) {
    //        addToUpdateColsNotNull(ns, attr)
    //      }
    //      placeHolders = placeHolders :+ s"$attr = ?$cast"
    //      placeHolders2 += ((attr, s"cast(? as JSON)", s"_v.$attr"))
    //
    //      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
    //        val query = s"select $attr from $ns where id IN(${ids.mkString(", ")})"
    //        val rs    = ps.getConnection.prepareStatement(query).executeQuery()
    //        rs.next()
    //        val byteArray = rs.getBytes(1)
    //        if (rs.wasNull()) {
    //          // No current map attribute asserted - do nothing
    //          ps.setNull(curParamIndex, 0)
    //        } else {
    //          val json = new String(byteArray)
    //
    //
    //          // Remove pairs from current map
    //          val removeKeys = map.keys.toSet // avoid duplicates
    //
    //
    //          val updatedMap = exts.head match {
    //            case "ID"             => upickle.default.read[Map[String, String]](json) -- removeKeys
    //            case "String"         => upickle.default.read[Map[String, String]](json) -- removeKeys
    //            case "Int"            => upickle.default.read[Map[String, Int]](json) -- removeKeys
    //            case "Long"           => upickle.default.read[Map[String, Long]](json) -- removeKeys
    //            case "Float"          => upickle.default.read[Map[String, Float]](json) -- removeKeys
    //            case "Double"         => upickle.default.read[Map[String, Double]](json) -- removeKeys
    //            case "Boolean"        => upickle.default.read[Map[String, Int]](json).map { case (k, v) => k -> (if (v == 1) true else false) } -- removeKeys
    //            case "BigInt"         => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigInt(v) } -- removeKeys
    //            case "BigDecimal"     => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigDecimal(v) } -- removeKeys
    //            case "Date"           => upickle.default.read[Map[String, Long]](json).map { case (k, v) => k -> new Date(v) } -- removeKeys
    //            case "Duration"       => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Duration.parse(v) } -- removeKeys
    //            case "Instant"        => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Instant.parse(v) } -- removeKeys
    //            case "LocalDate"      => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDate.parse(v) } -- removeKeys
    //            case "LocalTime"      => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalTime.parse(v) } -- removeKeys
    //            case "LocalDateTime"  => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDateTime.parse(v) } -- removeKeys
    //            case "OffsetTime"     => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetTime.parse(v) } -- removeKeys
    //            case "OffsetDateTime" => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetDateTime.parse(v) } -- removeKeys
    //            case "ZonedDateTime"  => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> ZonedDateTime.parse(v) } -- removeKeys
    //            case "UUID"           => upickle.default.read[Map[String, UUID]](json) -- removeKeys
    //            case "URI"            => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> new URI(v) } -- removeKeys
    //            case "Byte"           => upickle.default.read[Map[String, Byte]](json) -- removeKeys
    //            case "Short"          => upickle.default.read[Map[String, Short]](json) -- removeKeys
    //            case "Char"           => upickle.default.read[Map[String, Char]](json) -- removeKeys
    //          }
    //          if (updatedMap.nonEmpty) {
    //            map2jdbc(ps, curParamIndex, updatedMap.asInstanceOf[Map[String, T]])
    //          } else {
    //            // No pairs left - delete map attribute
    //            ps.setNull(curParamIndex, 0)
    //          }
    //        }
    //        curParamIndex += 1
    //      }
    //      addColSetter(curRefPath, colSetter)
    //    }
    ???
  }

  protected def getUpdateId: Long = {
    ids.toList match {
      case List(v) => v
      case other   => throw ModelError("Expected to update one entity. Found multiple ids: " + other)
    }
  }

  override protected lazy val extsID             = List("ID", "BIGINT", "")
  override protected lazy val extsString         = List("String", "LONGVARCHAR", "")
  override protected lazy val extsInt            = List("Int", "INT", "")
  override protected lazy val extsLong           = List("Long", "BIGINT", "")
  override protected lazy val extsFloat          = List("Float", "REAL", "")
  override protected lazy val extsDouble         = List("Double", "DOUBLE", "")
  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL(100, 0)", "")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65535, 25)", "")
  override protected lazy val extsDate           = List("Date", "BIGINT", "")
  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "")
  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "")
  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "")
  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "")
  override protected lazy val extsUUID           = List("UUID", "UUID", "")
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "TINYINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "CHAR", "")
}