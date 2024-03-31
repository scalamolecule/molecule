package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.Model2SqlQuery

trait SqlUpdate
  extends SqlBase_JVM
    with UpdateOps with SqlBaseOps
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false
  protected var curParamIndex = 1

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any]

  def getData(elements: List[Element]): Data = {
    curRefPath = List(getInitialNs(elements))
    resolve(elements)
    addRowSetterToTables()
    (manualTableDatas ++ getTables, Nil)
  }

  protected def addRowSetterToTables(): Unit = {
    updates.foreach {
      case (refPath, _) =>
        val table         = refPath.last
        val columnSetters = placeHolders.mkString(",\n  ")

        val clauses_ = if (ids.nonEmpty) {
          s"$table.id IN(${ids.mkString(", ")})"
        } else {
          if (uniqueFilterElements.nonEmpty) {
            // todo: if unique filter attributes exists, query to get ids...
            model2SqlQuery(uniqueFilterElements).getWhereClauses.map {
              case (attr, expr) => s"$attr $expr"
            }.mkString(" AND\n  ")
          } else {
            model2SqlQuery(filterElements).getWhereClauses.map {
              case (attr, expr) => s"$attr $expr"
            }.mkString(" AND\n  ")
          }
        }

        val updateCols2_ = if (updateCols.isEmpty) "" else {
          updateCols(refPath).map(c => s"$c IS NOT NULL").mkString(" AND\n  ", " AND\n  ", "")
        }

        val stmt =
          s"""UPDATE $table
             |SET
             |  $columnSetters
             |WHERE $clauses_$updateCols2_""".stripMargin

        debug(stmt)
        val colSetters = colSettersMap(refPath)
        debug(s"--- update -------------------  ${colSetters.length}  $refPath")

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)
        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach(colSetter =>
            colSetter(ps, idsMap, 0)
          )
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

  protected def addToUpdateCols(ns: String, attr: String) = {
    if (!updateCols.contains(curRefPath)) {
      updateCols += curRefPath -> Nil
    }
    updateCols(curRefPath) = updateCols(curRefPath) :+ s"$ns.$attr"
  }


  override def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    updateCurRefPath(attr)
    placeHolders = placeHolders :+ s"$attr = ?"
    val colSetter: Setter = vs match {
      case Seq(v) =>
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
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
          s"Can only $update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
    addColSetter(curRefPath, colSetter)
  }


  override def updateSetEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateVsEq(ns, attr, refNs, set, exts, set2array)
  }

  private def updateVsEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    vs: M[T],
    exts: List[String],
    vs2array: M[T] => Array[AnyRef],
  ): Unit = {
    refNs.fold {
      updateCurRefPath(attr)
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = if (vs.nonEmpty) {
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        val array = vs2array(vs)
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
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
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      val id        = getUpdateId
      if (vs.nonEmpty) {
        // Tables are reversed in JdbcConn_JVM and we want to delete first
        manualTableDatas = List(
          addJoins(joinTable, ns_id, refNs_id, id, vs.asInstanceOf[Iterable[T]].map {
            _.asInstanceOf[String].toLong
          }),
          deleteJoins(joinTable, ns_id, id)
        )
      } else {
        // Delete all joins when no ref ids are applied
        manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
      }
    }
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateVsAdd(ns, attr, refNs, set, exts, set2array)
  }

  private def updateVsAdd[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    vs: M[T],
    exts: List[String],
    set2array: M[T] => Array[AnyRef],
  ): Unit = {
    refNs.fold {
      if (vs.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        placeHolders = placeHolders :+ s"$attr = $attr || ?"
        val array = set2array(vs)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
          curParamIndex += 1
        })
      } else {

      }
    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      if (vs.nonEmpty) {
        manualTableDatas = List(
          addJoins(joinTable, ns_id, refNs_id, getUpdateId,
            vs.asInstanceOf[Iterable[T]].map(_.asInstanceOf[String].toLong))
        )
      }
    }
  }


  override def updateSetRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    updateVsRemove(ns, attr, refNs, set, transformValue, exts)
  }

  private def updateVsRemove[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    vs: M[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    val table  = ns
    val nsAttr = s"$ns.$attr"
    val dbType = exts(1)
    refNs.fold {
      if (vs.nonEmpty) {
        updateCurRefPath(nsAttr)
        val idClause = s"$table.id IN (${ids.mkString(", ")})"
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        val removeValuePlaceHolders = vs.toList.map(_ => "?").mkString(", ")
        placeHolders = placeHolders :+
          s"""$nsAttr =
             |  CASE
             |    WHEN
             |      ARRAY(
             |        SELECT v
             |        FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
             |        WHERE v NOT IN ($removeValuePlaceHolders)
             |      ) = array[]
             |    THEN
             |      NULL
             |  ELSE
             |    ARRAY(
             |      SELECT v
             |      FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
             |      WHERE v NOT IN ($removeValuePlaceHolders)
             |    )
             |  END""".stripMargin

        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          vs.asInstanceOf[Iterable[T]].foreach { v =>
            transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
          vs.asInstanceOf[Iterable[T]].foreach { v =>
            transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        })
      }
    } { refNs =>
      if (vs.nonEmpty) {
        // Separate update of ref ids in join table -----------------------------
        val refAttr    = attr
        val joinTable  = ss(ns, refAttr, refNs)
        val ns_id      = ss(ns, "id")
        val refNs_id   = ss(refNs, "id")
        val retractIds = vs.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
        )
      }
    }
  }


  override def updateSeqEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateVsEq(ns, attr, refNs, seq, exts, seq2array)
  }

  override def updateSeqAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateVsAdd(ns, attr, refNs, seq, exts, seq2array)
  }

  override def updateSeqRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    updateVsRemove(ns, attr, refNs, seq, transformValue, exts)
  }

  override def updateByteArray(
    ns: String,
    attr: String,
    byteArray: Array[Byte],
  ): Unit = {
    updateCurRefPath(attr)
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


  override def handleIds(ns: String, ids1: Seq[String]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids1.map(_.toLong)
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


  // Ref id join handling --------------------------------------------------------------------

  protected def deleteJoins(joinTable: String, ns_id: String, id: Long, refIds: String = ""): Table = {
    val deletePath  = List("deleteJoins")
    val deleteJoins = s"DELETE FROM $joinTable WHERE $ns_id = $id" + refIds
    val deletePS    = sqlConn.prepareStatement(deleteJoins, Statement.RETURN_GENERATED_KEYS)
    val delete      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
    Table(deletePath, deleteJoins, deletePS, delete)
  }

  protected def addJoins(joinTable: String, ns_id: String, refNs_id: String, id: Long, refIds: Iterable[Long]): Table = {
    val addPath  = List("addJoins")
    val addJoins = s"INSERT INTO $joinTable($ns_id, $refNs_id) VALUES (?, ?)"
    val addPS    = sqlConn.prepareStatement(addJoins, Statement.RETURN_GENERATED_KEYS)
    val add      = (ps: PS, _: IdsMap, _: RowIndex) =>
      refIds.foreach { refId =>
        ps.setLong(1, id)
        ps.setLong(2, refId)
        ps.addBatch()
      }
    Table(addPath, addJoins, addPS, add)
  }

  protected def getUpdateId: Long = {
    ids.toList match {
      case List(v) => v
      case other   => throw ModelError("Expected to update one entity. Found multiple ids: " + other)
    }
  }

  override protected lazy val extsID             = List("", "BIGINT")
  override protected lazy val extsString         = List("", "LONGVARCHAR")
  override protected lazy val extsInt            = List("", "INT")
  override protected lazy val extsLong           = List("", "BIGINT")
  override protected lazy val extsFloat          = List("", "REAL")
  override protected lazy val extsDouble         = List("", "DOUBLE")
  override protected lazy val extsBoolean        = List("", "BOOLEAN")
  override protected lazy val extsBigInt         = List("", "DECIMAL(100, 0)")
  override protected lazy val extsBigDecimal     = List("", "DECIMAL(65535, 25)")
  override protected lazy val extsDate           = List("", "BIGINT")
  override protected lazy val extsDuration       = List("", "VARCHAR")
  override protected lazy val extsInstant        = List("", "VARCHAR")
  override protected lazy val extsLocalDate      = List("", "VARCHAR")
  override protected lazy val extsLocalTime      = List("", "VARCHAR")
  override protected lazy val extsLocalDateTime  = List("", "VARCHAR")
  override protected lazy val extsOffsetTime     = List("", "VARCHAR")
  override protected lazy val extsOffsetDateTime = List("", "VARCHAR")
  override protected lazy val extsZonedDateTime  = List("", "VARCHAR")
  override protected lazy val extsUUID           = List("", "UUID")
  override protected lazy val extsURI            = List("", "VARCHAR")
  override protected lazy val extsByte           = List("", "TINYINT")
  override protected lazy val extsShort          = List("", "SMALLINT")
  override protected lazy val extsChar           = List("", "CHAR")
}