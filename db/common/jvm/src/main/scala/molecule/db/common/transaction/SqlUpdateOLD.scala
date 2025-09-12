//package molecule.db.common.transaction
//
//import java.sql.PreparedStatement as PS
//import scala.collection.mutable.ListBuffer
//import boopickle.Default.*
//import molecule.core.dataModel.*
//import molecule.core.error.ModelError
//import molecule.db.common.spi.SpiHelpers
//import molecule.db.common.transaction.strategy.SqlOps
//import molecule.db.common.transaction.strategy.update.{UpdateAction, UpdateRoot}
//
//trait SqlUpdateOLD extends ValueTransformers with SpiHelpers { self: ResolveUpdate & SqlOps =>
//
//  protected var root        : UpdateRoot   = null
//  protected var updateAction: UpdateAction = null
//
//  private var isRefUpdate = false
//  private var hasFilters  = false
//  private var ent         = ""
//
//  // Set after isUpsert has been set
//  private lazy val join = if (isUpsert) "LEFT JOIN" else "INNER JOIN"
//
//  // Build query for ids of each entity involved in update
//  private object query {
//    var ids         = List.empty[Long]
//    val idCols      = ListBuffer.empty[String]
//    val joins       = ListBuffer.empty[String]
//    val filterAttrs = ListBuffer.empty[Element]
//  }
//
//  def getUpdateAction(elements: List[Element]): UpdateAction = {
//    ent = getInitialEntity(elements)
//    query.idCols += s"$ent.id"
//    root = UpdateRoot(sqlOps, ent)
//    updateAction = root.firstEnt
////    resolve(elements)
//    initRoot()
//    root
//  }
//
//
//  private def initRoot(): Unit = {
//    if (hasFilters || isRefUpdate) {
//      // Query for ids of each entity
//      val idsQuery = selectStmt(ent, query.idCols, query.joins,
//        m2q(query.filterAttrs.toList).getWhereClauses
//      )
//      val entCount = query.idCols.length
//      val refIds   = new Array[ListBuffer[Long]](entCount)
//        .map(_ => ListBuffer.empty[Long])
//
//      val resultSet = sqlConn.prepareStatement(idsQuery).executeQuery()
//      var entIndex  = 0
//      while (resultSet.next()) {
//        entIndex = 0
//        while (entIndex < entCount) {
//          refIds(entIndex) += resultSet.getLong(entIndex + 1)
//          entIndex += 1
//        }
//      }
//      val refIdLists = refIds.map(_.toList)
//      root.cols ++= query.idCols
//      root.idsQuery = idsQuery
//      root.refIds = refIdLists
//      root.firstEnt.completeIds(refIdLists)
//    }
//  }
//
//  def handleIds(ent: String, ids0: Seq[Long]): Unit = {
//    if (query.ids.nonEmpty) {
//      throw ModelError(s"Can't apply entity ids twice in update.")
//    }
//    query.filterAttrs += AttrOneManID(ent, "id", Eq, ids0)
//    query.ids = ids0.toList
//
//    // Set here if no need for distributing ids across multiple entities
//    updateAction.ids = query.ids
//  }
//
//  def handleFilterAttr[T <: Attr & Tacit](filterAttr: T): Unit = {
//    filterAttr.asInstanceOf[Attr] match {
//      case a: AttrSeqTac if a.op == Eq => noCollectionFilterEq(a.name)
//      case a: AttrSetTac if a.op == Eq => noCollectionFilterEq(a.name)
//      case _                           =>
//        hasFilters = true
//        query.filterAttrs += filterAttr
//    }
//  }
//
//
//  def handleAppend(attr: String, cast: String): String = s"($attr || ?$cast)"
//  def handlePrepend(attr: String, cast: String): String = s"(?$cast || $attr)"
//  def handleReplaceAll[T](attr: String, vs: Seq[T]): String = s"REGEXP_REPLACE($attr, ?, '${vs(1)}')"
//
//  def substring(attr: String, start: Int, end: Int): String = {
//    if (start < 0)
//      throw ModelError("Start index should be 0 or more")
//
//    if (start >= end)
//      throw ModelError("Start index should be smaller than end index")
//
//    val length = end - start
//    val s      = start + 1
//    s"SUBSTRING($attr, $s, $length)"
//  }
//
//  protected def updateOne[T](
//    ent: String,
//    attr: String,
//    op: Op,
//    vs: Seq[T],
//    transformValue: T => Any,
//    exts: List[String],
//  ): Unit = {
//    lazy val cleanAttr = attr.replace("_", "")
//    val cast: String = exts(2)
//    op match {
//      case Eq | NoValue =>
//        val paramIndex = updateAction.setCol(s"$attr = ?$cast")
//        vs match {
//          case Seq(v) =>
//            setAttrPresence(ent, attr)
//            updateAction.addColSetter((ps: PS) =>
//              transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
//            )
//
//          case Nil =>
//            updateAction.addColSetter((ps: PS) =>
//              ps.setNull(paramIndex, 0))
//
//          case vs =>
//            throw ModelError(
//              s"Can only update one value for attribute `$ent.$cleanAttr`. " +
//                s"Found: " + vs.mkString(", ")
//            )
//        }
//
//      case op: AttrOp =>
//        setAttrPresence(ent, attr)
//        val cast: String = exts(2)
//        def handle(computedValue: String): Unit = {
//          val paramIndex = updateAction.setCol(s"$attr = $computedValue")
//          val colSetter  = if (vs.isEmpty)
//            (_: PS) => ()
//          else
//            (ps: PS) => transformValue(vs.head).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
//          updateAction.addColSetter(colSetter)
//        }
//
//        op match {
//          // String
//          case AttrOp.Append                   => handle(handleAppend(attr, cast))
//          case AttrOp.Prepend                  => handle(handlePrepend(attr, cast))
//          case AttrOp.SubString(start, length) => handle(substring(attr, start, length))
//          case AttrOp.ReplaceAll               => handle(handleReplaceAll(attr, vs))
//          case AttrOp.ToLower                  => handle(s"LOWER($attr)")
//          case AttrOp.ToUpper                  => handle(s"UPPER($attr)")
//
//          // Number
//          case AttrOp.Plus   => handle(s"$attr + ?$cast")
//          case AttrOp.Minus  => handle(s"$attr - ?$cast")
//          case AttrOp.Times  => handle(s"$attr * ?$cast")
//          case AttrOp.Divide => handle(s"$attr / ?$cast")
//          case AttrOp.Negate => handle(s"-$attr")
//          case AttrOp.Abs    => handle(s"ABS($attr)")
//          case AttrOp.AbsNeg => handle(s"-ABS($attr)")
//          case AttrOp.Ceil   => handle(s"CEIL($attr)")
//          case AttrOp.Floor  => handle(s"FLOOR($attr)")
//
//          // Boolean
//          case AttrOp.And => handle(s"$attr AND ?$cast")
//          case AttrOp.Or  => handle(s"$attr OR ?$cast")
//          case AttrOp.Not => handle(s"NOT($attr)")
//        }
//
//      case Even | Odd => throw ModelError(
//        s"$ent.$cleanAttr.even and $ent.$cleanAttr.odd can't be used with updates."
//      )
//
//      case Remainder => throw ModelError(
//        s"Modulo operations like $ent.$cleanAttr.%(${vs.head}) can't be used with updates."
//      )
//
//      case _ => throw ModelError(
//        s"Can't update attribute $ent.$cleanAttr without an applied or computed value."
//      )
//    }
//  }
//
//
//  def handleRef(r: Ref): Unit = {
//    isRefUpdate = true
//    val Ref(ent, refAttr, ref, value, _, _, reverseRefAttr) = r
//    updateAction = value match {
//      case ManyToOne =>
//        query.idCols += s"$ref.id"
//        query.joins += s"$join $ref ON $ent.$refAttr = $ref.id"
//        // Switch strategy
//        updateAction.refOne(ent, refAttr, ref)
//
//      case _ =>
//        val joinTable = ss(ent, refAttr, ref)
//        val eid       = s"${ent}_id"
//        val rid       = s"${ref}_id"
//        query.idCols += s"$ref.id"
//        query.joins ++= List(
//          s"$join $joinTable ON $ent.id = $joinTable.$eid",
//          s"$join $ref ON $joinTable.$rid = $ref.id",
//        )
//
////        query.idCols += s"$ref.id"
////        query.joins += s"$join $ref ON $ent.$refAttr = $ref.id"
//
//
//        // Switch strategy
//        updateAction.refMany(ent, refAttr, ref)
//    }
//  }
//
//  protected def updateSetEq[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    set: Set[T],
//    transformValue: T => Any,
//    exts: List[String],
//    set2array: Set[T] => Array[AnyRef],
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    updateIterableEq(ent, attr, optRef, set, exts, set2array)
//  }
//
//  protected def updateSetAdd[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    set: Set[T],
//    transformValue: T => Any,
//    exts: List[String],
//    set2array: Set[T] => Array[AnyRef],
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    updateIterableAdd(ent, attr, optRef, set, exts, set2array)
//  }
//
//  protected def updateSetRemove[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    set: Set[T],
//    transformValue: T => Any,
//    exts: List[String],
//    one2json: T => String,
//    set2array: Set[T] => Array[AnyRef]
//  ): Unit = {
//    updateIterableRemove(ent, attr, optRef, set, exts, set2array)
//  }
//
//  protected def updateSeqEq[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    seq: Seq[T],
//    transformValue: T => Any,
//    exts: List[String],
//    seq2array: Seq[T] => Array[AnyRef],
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    updateIterableEq(ent, attr, optRef, seq, exts, seq2array)
//  }
//
//  protected def updateSeqAdd[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    seq: Seq[T],
//    transformValue: T => Any,
//    exts: List[String],
//    seq2array: Seq[T] => Array[AnyRef],
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    updateIterableAdd(ent, attr, optRef, seq, exts, seq2array)
//  }
//
//  protected def updateSeqRemove[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    seq: Seq[T],
//    transformValue: T => Any,
//    exts: List[String],
//    one2json: T => String,
//    seq2array: Seq[T] => Array[AnyRef]
//  ): Unit = {
//    updateIterableRemove(ent, attr, optRef, seq, exts, seq2array)
//  }
//
//  protected def updateByteArray(
//    ent: String,
//    attr: String,
//    byteArray: Array[Byte],
//  ): Unit = {
//    val paramIndex = updateAction.setCol(s"$attr = ?")
//    if (byteArray.isEmpty) {
//      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
//    } else {
//      updateAction.addColSetter((ps: PS) => ps.setBytes(paramIndex, byteArray))
//    }
//  }
//
//  protected def updateMapEq[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    noValue: Boolean,
//    map: Map[String, T],
//    transformValue: T => Any,
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    val paramIndex = updateAction.setCol(s"$attr = ?")
//    if (map.isEmpty) {
//      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
//    } else {
//      setAttrPresence(ent, attr)
//      updateAction.addColSetter((ps: PS) =>
//        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
//      )
//    }
//  }
//
//  protected def updateMapAdd[T](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    map: Map[String, T],
//    transformValue: T => Any,
//    exts: List[String],
//    value2json: (StringBuffer, T) => StringBuffer,
//  ): Unit = {
//    if (map.nonEmpty) {
//      setAttrPresence(ent, attr)
//      val scalaBaseType = exts.head
//      val setAttr       = s"$attr = addPairs_$scalaBaseType($attr, ?)"
//      val paramIndex    = updateAction.setCol(setAttr)
//      updateAction.addColSetter((ps: PS) =>
//        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
//      )
//    }
//  }
//
//
//  protected def updateMapRemove(
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    keys: Seq[String],
//    exts: List[String],
//  ): Unit = {
//    if (keys.nonEmpty) {
//      setAttrPresence(ent, attr)
//      val scalaBaseType = exts.head
//      val setAttr       = s"$attr = removePairs_$scalaBaseType($attr, ?)"
//      val paramIndex    = updateAction.setCol(setAttr)
//      updateAction.addColSetter((ps: PS) =>
//        ps.setArray(paramIndex,
//          ps.getConnection.createArrayOf("String", keys.toArray))
//      )
//    }
//  }
//
//  def handleBackRef(backRef: BackRef): Unit = {
//    // Switch strategy to previous action
//    updateAction = updateAction.backRef
//  }
//
//
//  // Helpers -------------------------------------------------------------------
//
//  private def updateIterableEq[T, M[_] <: Iterable[?]](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    iterable: M[T],
//    exts: List[String],
//    vs2array: M[T] => Array[AnyRef],
//  ): Unit = {
//    optRef.fold {
//      val dbBaseType = exts(1)
//      val paramIndex = updateAction.setCol(s"$attr = ?")
//      if (iterable.nonEmpty) {
//        setAttrPresence(ent, attr)
//        addArray(paramIndex, dbBaseType, vs2array(iterable))
//      } else {
//        updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
//      }
//    } { ref =>
//      updateAction.deleteRefIds(attr, ref, getUpdateId)
//      val refIds = iterable.asInstanceOf[Set[Long]]
//      if (refIds.nonEmpty) {
//        updateAction.insertRefIds(attr, ref, refIds)
//      }
//    }
//  }
//
//  private def updateIterableAdd[T, M[_] <: Iterable[?]](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    iterable: M[T],
//    exts: List[String],
//    iterable2array: M[T] => Array[AnyRef],
//  ): Unit = {
//    optRef.fold {
//      if (iterable.nonEmpty) {
//        setAttrPresence(ent, attr)
//        val dbBaseType = exts(1)
//        val cast       = exts(2) match {
//          case ""  => ""
//          case tpe => tpe + "[]"
//        }
//        val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
//        val paramIndex = updateAction.setCol(setAttr)
//        addArray(paramIndex, dbBaseType, iterable2array(iterable))
//      }
//    } { ref =>
//      if (iterable.nonEmpty) {
//        updateAction.insertRefIds(attr, ref, iterable.asInstanceOf[Set[Long]])
//      }
//    }
//  }
//
//  private def updateIterableRemove[T, M[_] <: Iterable[?]](
//    ent: String,
//    attr: String,
//    optRef: Option[String],
//    iterable: M[T],
//    exts: List[String],
//    iterable2array: M[T] => Array[AnyRef]
//  ): Unit = {
//    optRef.fold {
//      if (iterable.nonEmpty) {
//        setAttrPresence(ent, attr)
//        val scalaBaseType = exts.head
//        val dbBaseType    = exts(1)
//        val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
//        val paramIndex    = updateAction.setCol(setAttr)
//        addArray(paramIndex, dbBaseType, iterable2array(iterable))
//      }
//    } { ref =>
//      if (iterable.nonEmpty) {
//        val refIds = iterable.asInstanceOf[Set[Long]]
//        updateAction.deleteRefIds(attr, ref, getUpdateId, refIds)
//      }
//    }
//  }
//
//
//  protected def setAttrPresence(ent: String, attr: String): Unit = {
//    if (isUpsert) {
//      // Allow finding where clauses for ids query. Not used otherwise
//      query.filterAttrs += AttrOneOptByte(ent, attr)
//    } else {
//      // Attribute value present in updated data
//      query.filterAttrs += AttrOneTacByte(ent, attr)
//
//      // Used for single entity update with ids and no filters
//      updateAction.mandatoryCols += s"$ent.$attr IS NOT NULL"
//    }
//  }
//
//  private def addArray(
//    paramIndex: Int, dbBaseType: String, array: Array[AnyRef]
//  ): Unit = {
//    updateAction.addColSetter((ps: PS) => {
//      val conn = ps.getConnection
//      ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
//    })
//  }
//
//
//  protected def getUpdateId: Long = {
//    updateAction.ids match {
//      case List(v) => v
//      case other   => throw ModelError(
//        "Expected to update one entity. Found multiple ids: " + other
//      )
//    }
//  }
//
//  override protected lazy val extsID             = List("ID", "BIGINT", "")
//  override protected lazy val extsString         = List("String", "LONGVARCHAR", "")
//  override protected lazy val extsInt            = List("Int", "INT", "")
//  override protected lazy val extsLong           = List("Long", "BIGINT", "")
//  override protected lazy val extsFloat          = List("Float", "REAL", "")
//  override protected lazy val extsDouble         = List("Double", "DOUBLE", "")
//  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "")
//  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL(100, 0)", "")
//  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65535, 38)", "")
//  override protected lazy val extsDate           = List("Date", "BIGINT", "")
//  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "")
//  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "")
//  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "")
//  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "")
//  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "")
//  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "")
//  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "")
//  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "")
//  override protected lazy val extsUUID           = List("UUID", "UUID", "")
//  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
//  override protected lazy val extsByte           = List("Byte", "TINYINT", "")
//  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
//  override protected lazy val extsChar           = List("Char", "CHAR", "")
//}