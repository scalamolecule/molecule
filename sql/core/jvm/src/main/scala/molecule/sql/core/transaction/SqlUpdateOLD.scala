package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.spi.SpiHelpers

trait SqlUpdateOLD
  extends SqlBase_JVM
    with UpdateOps
    with SqlBaseOps
    with SpiHelpers
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false
  //  doPrint = true

  protected var curParamIndex = 1

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery

  def getUpdateData(elements: List[Element]): Data = {
    curRefPath = List(getInitialNs(elements))
    // Resolve update model
    resolve(elements)
    (manualTableDatas ++ getTableData, Nil)
  }


  private def getTableData: List[Table] = {
    if (cols.isEmpty) {
      Nil
    } else {
      addRowSetterToTable()
      val rowSetters = rowSettersMap(curRefPath)
      val populatePS = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
        )
      }
      // Add update resolver
      List(tableDatas(curRefPath).copy(populatePS = populatePS))
    }
  }

  private def addRowSetterToTable(): Unit = {
    val columnSetters      = placeHolders.mkString(",\n  ")
    val refPath            = curRefPath
    val table              = refPath.last
    val (stmt, upsertStmt) = if (isUpsert) {
      val upsertStmt = (ids1: List[Long]) => {
        val ids2    = if (ids1.nonEmpty) {
          ids1 // ids of existing and new entities (when ref structure establishment)
        } else {
          idsOLD // ids of existing entities (collected in handleIds)
        }
        //        println(s"  ............................ ids2: " + ids2)
        val clauses = if (ids2.nonEmpty) {
          s"$table.id IN(${ids2.mkString(", ")})"
        } else {
          model2SqlQuery(filterElements).getWhereClauses.mkString(" AND\n  ")
        }
        s"""UPDATE $table
           |SET
           |  $columnSetters
           |WHERE
           |  $clauses""".stripMargin
      }
      ("", Some(upsertStmt))

    } else {
      val updateCols2_ = if (updateCols.isEmpty) Nil else {
        updateCols(refPath).map(c => s"$c IS NOT NULL")
      }
      val clauses_     = if (idsOLD.nonEmpty) {
        List(s"$table.id IN(${idsOLD.mkString(", ")})")
      } else {
        model2SqlQuery(filterElements).getWhereClauses
      }
      val clauses      = (updateCols2_ ++ clauses_).mkString(" AND\n  ")

      val stmt =
        s"""UPDATE $table
           |SET
           |  $columnSetters
           |WHERE
           |  $clauses""".stripMargin
      (stmt, None)
    }

    //    println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    //    println("------ stmt --------")
    //    println(stmt)
    //    println("------ upsertStmt --------")
    //    println(upsertStmt.map(_(List(42))))

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

  protected def addToUpdateColsNotNull(attr: String) = {
    if (!updateCols.contains(curRefPath)) {
      updateCols += curRefPath -> Nil
    }
    updateCols(curRefPath) = updateCols(curRefPath) :+ attr
  }


  override protected def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    cols += attr
    val cast = exts(2)
    placeHolders = placeHolders :+ s"$attr = ?$cast"
    val colSetter: Setter = vs match {
      case Seq(v) =>
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
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
    cols += attr
    placeHolders = placeHolders :+ s"$attr = ?"
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
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateMapEqJdbc(attr, "", map,
      (ps: PS, paramIndex: Int) => {
        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      }
    )
  }

  override protected def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      cols += attr
      if (!isUpsert) {
        addToUpdateColsNotNull(attr)
      }
      val scalaBaseType = exts.head
      placeHolders = placeHolders :+ s"$attr = addPairs_$scalaBaseType($attr, ?)"
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
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      cols += attr
      if (!isUpsert) {
        addToUpdateColsNotNull(attr)
      }
      val scalaBaseType = exts.head
      placeHolders = placeHolders :+ s"$attr = removePairs_$scalaBaseType($attr, ?)"
      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
        val conn = ps.getConnection
        ps.setArray(curParamIndex, conn.createArrayOf("String", keys.toArray))
        curParamIndex += 1
      }
      addColSetter(curRefPath, colSetter)
    }
  }


  override def handleIds(ns: String, ids1: Seq[Long]): Unit = {
    if (idsOLD.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in update.")
    }
    if (filterElements.nonEmpty) {
      throw ModelError("Can't mix using ids and filter elements")
    }
    idsOLD = ids1
  }

  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    filterAttr.asInstanceOf[Attr] match {
      case a: AttrSeqTac if a.op == Eq => noCollectionFilterEq(a.name)
      case a: AttrSetTac if a.op == Eq => noCollectionFilterEq(a.name)
      case _                           => ()
    }
    if (idsOLD.nonEmpty) {
      throw ModelError("Can't mix using ids and filter elements")
    }
    filterElements = filterElements :+ filterAttr
  }

  override def handleRef(ref: Ref): Unit = {
    throw ModelError(s"Can't apply entity ids twice in update.")
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    throw ModelError(s"Can't apply entity ids twice in update.")
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
    val dbBaseType = exts(1)
    refNs.fold {
      cols += attr
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = if (iterable.nonEmpty) {
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        val array = vs2array(iterable)
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(dbBaseType, array))
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
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        val cast = exts(2) match {
          case ""  => ""
          case tpe => tpe + "[]"
        }
        placeHolders = placeHolders :+ s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
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
    val scalaBaseType = exts.head
    val dbBaseType    = exts(1)
    refNs.fold {
      if (iterable.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        placeHolders = placeHolders :+ s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
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


  protected def joinEq[T, M[_] <: Iterable[_]](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: M[T]
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
          vs.asInstanceOf[Iterable[T]].map(_.asInstanceOf[Long])
        ),
        deleteJoins(joinTable, ns_id, id)
      )
    } else {
      // Delete all joins when no ref ids are applied
      manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
    }
  }

  protected def joinAdd[T, M[_] <: Iterable[_]](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: M[T]
  ): Unit = {
    if (vs.nonEmpty) {
      // Separate update of ref ids in join table -----------------------------
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      manualTableDatas = List(
        addJoins(joinTable, ns_id, refNs_id, getUpdateId,
          vs.asInstanceOf[Iterable[T]].map(_.asInstanceOf[Long])
        )
      )
    }
  }

  protected def joinRemove[T, M[_] <: Iterable[_]](
    ns: String,
    refAttr: String,
    refNs: String,
    vs: M[T]
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

  private def deleteJoins(
    joinTable: String, ns_id: String, id: Long, refIds: String = ""
  ): Table = {
    val deletePath  = List("deleteJoins")
    val deleteJoins = s"DELETE FROM $joinTable WHERE $ns_id = $id" + refIds
    val delete      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
    Table(deletePath, deleteJoins, delete)
  }

  private def addJoins(
    joinTable: String, ns_id: String, refNs_id: String, id: Long, refIds: Iterable[Long]
  ): Table = {
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
    attr: String,
    cast: String,
    map: Map[String, T],
    map2jdbc: (PS, Int) => Unit
  ): Unit = {
    cols += attr
    placeHolders = placeHolders :+ s"$attr = ?$cast"
    if (!isUpsert) {
      addToUpdateColsNotNull(attr)
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

  protected def getUpdateId: Long = {
    idsOLD.toList match {
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
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65535, 38)", "")
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