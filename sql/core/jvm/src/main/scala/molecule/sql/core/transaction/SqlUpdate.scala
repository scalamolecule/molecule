package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.Date
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.Model2SqlQuery

trait SqlUpdate
  extends SqlBase_JVM
    with UpdateOps
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false
  protected var curParamIndex = 1

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any]

  def getData(elements0: List[Element], proxy: ConnProxy): Data = {
    val elements = resolveReservedKeywords(elements0, Some(proxy))
    curRefPath = List(getInitialNs(elements))
    resolve(elements)
    addRowSetterToTables()
    (manualTableDatas ++ getTables, Nil)
  }

  protected def addRowSetterToTables(): Unit = {
    debug("updates      : " + updates)
    debug("colSettersMap: " + colSettersMap.keys.toList)
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
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit = {
    updateCurRefPath(attr)
    placeHolders = placeHolders :+ s"$attr = ?"
    val colSetter: Setter = vs match {
      case Seq(v) =>
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        (ps: PS, _: IdsMap, _: RowIndex) => {
          handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
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
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      updateCurRefPath(attr)
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = sets match {
        case Seq(set) =>
          if (!isUpsert) {
            addToUpdateCols(ns, attr)
          }
          val array = set2array(set.asInstanceOf[Set[Any]])
          (ps: PS, _: IdsMap, _: RowIndex) => {
            val conn = ps.getConnection
            ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
            curParamIndex += 1
          }
        case Nil      =>
          (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.setNull(curParamIndex, 0)
            curParamIndex += 1
          }
        case vs       => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
      addColSetter(curRefPath, colSetter)

    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val ns_id     = ns + "_id"
      val refNs_id  = refNs + "_id"
      val id        = getUpdateId
      sets match {
        case Seq(set) =>
          // Tables are reversed in JdbcConn_JVM and we want to delete first
          manualTableDatas = List(
            addJoins(joinTable, ns_id, refNs_id, id, set.asInstanceOf[Set[Long]]),
            deleteJoins(joinTable, ns_id, id)
          )

        case Nil =>
          // Delete all joins when no ref ids are applied
          manualTableDatas = List(deleteJoins(joinTable, ns_id, id))

        case vs => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
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
      if (sets.nonEmpty && sets.head.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        placeHolders = placeHolders :+ s"$attr = $attr || ?"
        val array = set2array(sets.head.asInstanceOf[Set[Any]])
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          ps.setArray(curParamIndex, conn.createArrayOf(exts(1), array))
          curParamIndex += 1
        })
      }
    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val ns_id     = ns + "_id"
      val refNs_id  = refNs + "_id"
      sets match {
        case Seq(set) => manualTableDatas = List(
          addJoins(joinTable, ns_id, refNs_id, getUpdateId, set.asInstanceOf[Set[Long]])
        )
        case Nil      => () // Add no ref ids
        case vs       => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
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
      updateCurRefPath(nsAttr)
      def setterClauses(indents: Int) = swaps.map(_ => s"WHEN v = ? THEN ?").mkString("\n" + "  " * indents)
      val idClause  = s"$table.id IN (${ids.mkString(", ")})"
      val colSetter = if (isUpsert) {
        val guaranteedInsertValues = adds.map(_ => s"?::$dbType").mkString(", ")
        placeHolders = placeHolders :+
          s"""$nsAttr = ARRAY(
             |    SELECT v
             |    FROM TABLE_DISTINCT(
             |      v $dbType = ARRAY(
             |        SELECT CASE
             |          ${setterClauses(5)}
             |          ELSE v
             |        END
             |        FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
             |      ) || ARRAY[$guaranteedInsertValues]
             |    )
             |  )""".stripMargin

        (ps: PS, _: IdsMap, _: RowIndex) => {
          swaps.foreach { case (retract, add) =>
            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex + 1)
            curParamIndex += 2
          }
          adds.foreach { add =>
            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
          }
        }
      } else {
        placeHolders = placeHolders :+
          s"""$nsAttr = ARRAY(
             |    SELECT CASE
             |      ${setterClauses(3)}
             |      ELSE v
             |    END
             |    FROM TABLE(v $dbType = (SELECT $nsAttr FROM $table WHERE $idClause))
             |  )""".stripMargin

        (ps: PS, _: IdsMap, _: RowIndex) => {
          swaps.foreach { case (retract, add) =>
            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex + 1)
            curParamIndex += 2
          }
        }
      }
      addColSetter(curRefPath, colSetter)
    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val ns_id     = ns + "_id"
      val refNs_id  = refNs + "_id"
      val id        = getUpdateId
      if (isUpsert) {
        val retractIds = retracts.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          // Add joins regardless if the old ref id was present
          addJoins(joinTable, ns_id, refNs_id, id, adds.asInstanceOf[Seq[Long]]),
          deleteJoins(joinTable, ns_id, id, retractIds)
        )
      } else {
        val matches   = swaps.map {
          case (oldId, newId) => s"WHEN $refNs_id = $oldId THEN $newId"
        }.mkString("\n      ")
        val swapJoins =
          s"""UPDATE $joinTable
             |SET
             |  $refNs_id =
             |    CASE
             |      $matches
             |      ELSE $refNs_id
             |    END
             |WHERE $ns_id = $id""".stripMargin
        val swapPS    = sqlConn.prepareStatement(swapJoins, Statement.RETURN_GENERATED_KEYS)
        val swap      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        val swapPath  = List("swapJoins")

        // Do updates only (no new ref ids inserted if old ref id was not there)
        manualTableDatas = List(Table(swapPath, swapJoins, swapPS, swap))
      }
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
      if (set.nonEmpty) {
        updateCurRefPath(nsAttr)
        val idClause = s"$table.id IN (${ids.mkString(", ")})"
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        val removeValuePlaceHolders = set.toList.map(_ => "?").mkString(", ")
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
          set.foreach { v =>
            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
          set.foreach { v =>
            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        })
      }
    } { refNs =>
      if (set.nonEmpty) {
        // Separate update of ref ids in join table -----------------------------
        val refAttr    = attr
        val joinTable  = s"${ns}_${refAttr}_$refNs"
        val ns_id      = ns + "_id"
        val refNs_id   = refNs + "_id"
        val retractIds = set.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
        )
      }
    }
  }


  override def handleIds(ids1: Seq[Long]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids1
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

  override protected lazy val handleString     = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[String])
  override protected lazy val handleInt        = (v: Any) => (ps: PS, n: Int) => ps.setInt(n, v.asInstanceOf[Int])
  override protected lazy val handleLong       = (v: Any) => (ps: PS, n: Int) => ps.setLong(n, v.asInstanceOf[Long])
  override protected lazy val handleFloat      = (v: Any) => (ps: PS, n: Int) => ps.setFloat(n, v.asInstanceOf[Float])
  override protected lazy val handleDouble     = (v: Any) => (ps: PS, n: Int) => ps.setDouble(n, v.asInstanceOf[Double])
  override protected lazy val handleBoolean    = (v: Any) => (ps: PS, n: Int) => ps.setBoolean(n, v.asInstanceOf[Boolean])
  override protected lazy val handleBigInt     = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v.asInstanceOf[BigInt]).bigDecimal)
  override protected lazy val handleBigDecimal = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.asInstanceOf[BigDecimal].bigDecimal)
  override protected lazy val handleDate       = (v: Any) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.asInstanceOf[Date].getTime))
  override protected lazy val handleUUID       = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleURI        = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleByte       = (v: Any) => (ps: PS, n: Int) => ps.setByte(n, v.asInstanceOf[Byte])
  override protected lazy val handleShort      = (v: Any) => (ps: PS, n: Int) => ps.setShort(n, v.asInstanceOf[Short])
  override protected lazy val handleChar       = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)

  override protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigInt]].map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigDecimal]].map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray

  override protected lazy val extsString     = List("", "LONGVARCHAR")
  override protected lazy val extsInt        = List("", "INT")
  override protected lazy val extsLong       = List("", "BIGINT")
  override protected lazy val extsFloat      = List("", "REAL")
  override protected lazy val extsDouble     = List("", "DOUBLE")
  override protected lazy val extsBoolean    = List("", "BOOLEAN")
  override protected lazy val extsBigInt     = List("", "DECIMAL(100, 0)")
  override protected lazy val extsBigDecimal = List("", "DECIMAL(65535, 25)")
  override protected lazy val extsDate       = List("", "DATE")
  override protected lazy val extsUUID       = List("", "UUID")
  override protected lazy val extsURI        = List("", "VARCHAR")
  override protected lazy val extsByte       = List("", "TINYINT")
  override protected lazy val extsShort      = List("", "SMALLINT")
  override protected lazy val extsChar       = List("", "CHAR")
}