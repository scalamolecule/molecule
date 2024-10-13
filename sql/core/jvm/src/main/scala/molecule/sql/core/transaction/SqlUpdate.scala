package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast.CardOne
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.spi.SpiHelpers
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.update.{UpdateAction, UpdateRoot}
import scala.collection.mutable.ListBuffer

trait SqlUpdate
  extends UpdateOps
    with SqlBaseOps
    with SpiHelpers { self: ResolveUpdate with SqlOps =>

  protected var root        : UpdateRoot   = null
  protected var updateAction: UpdateAction = null

  private var isRefUpdate = false
  private var hasFilters  = false
  private var ns          = ""

  // Set after isUpsert has been set
  private lazy val join = if (isUpsert) "LEFT JOIN" else "INNER JOIN"

  // Build query for ids of each namespace involved in update
  private object query {
    var ids         = List.empty[Long]
    val idCols      = ListBuffer.empty[String]
    val joins       = ListBuffer.empty[String]
    val filterAttrs = ListBuffer.empty[Element]
  }

  def getUpdateAction(elements: List[Element]): UpdateAction = {
    ns = getInitialNs(elements)
    query.idCols += s"$ns.id"
    root = UpdateRoot(sqlOps, ns)
    updateAction = root.firstNs
    resolve(elements)
    initRoot()
    root
  }


  private def initRoot(): Unit = {
    if (hasFilters || isRefUpdate) {
      // Query for ids of each namespace
      val idsQuery = selectStmt(ns, query.idCols, query.joins,
        m2q(query.filterAttrs.toList).getWhereClauses
      )
      val nsCount  = query.idCols.length
      val refIds   = new Array[ListBuffer[Long]](nsCount)
        .map(_ => ListBuffer.empty[Long])

      val resultSet = sqlConn.prepareStatement(idsQuery).executeQuery()
      var nsIndex   = 0
      while (resultSet.next()) {
        nsIndex = 0
        while (nsIndex < nsCount) {
          refIds(nsIndex) += resultSet.getLong(nsIndex + 1)
          nsIndex += 1
        }
      }
      val refIdLists = refIds.map(_.toList)
      root.cols ++= query.idCols
      root.idsQuery = idsQuery
      root.refIds = refIdLists
      root.firstNs.completeIds(refIdLists)
    }
  }

  override def handleIds(ns: String, ids0: Seq[Long]): Unit = {
    if (query.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in update.")
    }
    query.filterAttrs += AttrOneManID(ns, "id", Eq, ids0)
    query.ids = ids0.toList

    // Set here if no need for distributing ids across multiple namespaces
    updateAction.ids = query.ids
  }

  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    filterAttr.asInstanceOf[Attr] match {
      case a: AttrSeqTac if a.op == Eq => noCollectionFilterEq(a.name)
      case a: AttrSetTac if a.op == Eq => noCollectionFilterEq(a.name)
      case _                           =>
        hasFilters = true
        query.filterAttrs += filterAttr
    }
  }

  override protected def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    val cast       = exts(2)
    val paramIndex = updateAction.setCol(s"$attr = ?$cast")
    vs match {
      case Seq(v) =>
        setAttrPresence(ns, attr)
        updateAction.addColSetter((ps: PS) =>
          transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
        )

      case Nil =>
        updateAction.addColSetter((ps: PS) =>
          ps.setNull(paramIndex, 0))

      case vs =>
        val cleanAttr = attr.replace("_", "")
        throw ModelError(
          s"Can only update one value for attribute `$ns.$cleanAttr`. " +
            s"Found: " + vs.mkString(", ")
        )
    }
  }


  override def handleRef(ref: Ref): Unit = {
    isRefUpdate = true
    val Ref(ns, refAttr, refNs, card, _, _) = ref
    updateAction = card match {
      case CardOne =>
        query.idCols += s"$refNs.id"
        query.joins += s"$join $refNs ON $ns.$refAttr = $refNs.id"
        // Switch strategy
        updateAction.refOne(ns, refAttr, refNs)

      case _ =>
        val joinTable = ss(ns, refAttr, refNs)
        val ns_id     = s"${ns}_id"
        val ref_id    = s"${refNs}_id"
        query.idCols += s"$refNs.id"
        query.joins ++= List(
          s"$join $joinTable ON $ns.id = $joinTable.$ns_id",
          s"$join $refNs ON $joinTable.$ref_id = $refNs.id",
        )
        // Switch strategy
        updateAction.refMany(ns, refAttr, refNs)
    }
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
    val paramIndex = updateAction.setCol(s"$attr = ?")
    if (byteArray.isEmpty) {
      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      updateAction.addColSetter((ps: PS) => ps.setBytes(paramIndex, byteArray))
    }
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
    val paramIndex = updateAction.setCol(s"$attr = ?")
    if (map.isEmpty) {
      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      setAttrPresence(ns, attr)
      updateAction.addColSetter((ps: PS) =>
        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      )
    }
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
      setAttrPresence(ns, attr)
      val scalaBaseType = exts.head
      val setAttr       = s"$attr = addPairs_$scalaBaseType($attr, ?)"
      val paramIndex    = updateAction.setCol(setAttr)
      updateAction.addColSetter((ps: PS) =>
        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      )
    }
  }


  override protected def updateMapRemove(
    ns: String,
    attr: String,
    optRefNs: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      setAttrPresence(ns, attr)
      val scalaBaseType = exts.head
      val setAttr       = s"$attr = removePairs_$scalaBaseType($attr, ?)"
      val paramIndex    = updateAction.setCol(setAttr)
      updateAction.addColSetter((ps: PS) =>
        ps.setArray(paramIndex,
          ps.getConnection.createArrayOf("String", keys.toArray))
      )
    }
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    // Switch strategy to previous action
    updateAction = updateAction.backRef
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    iterable: M[T],
    exts: List[String],
    vs2array: M[T] => Array[AnyRef],
  ): Unit = {
    optRefNs.fold {
      val dbBaseType = exts(1)
      val paramIndex = updateAction.setCol(s"$attr = ?")
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        addArray(paramIndex, dbBaseType, vs2array(iterable))
      } else {
        updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { refNs =>
      updateAction.deleteRefIds(attr, refNs, getUpdateId)
      val refIds = iterable.asInstanceOf[Set[Long]]
      if (refIds.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, refIds)
      }
    }
  }

  private def updateIterableAdd[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef],
  ): Unit = {
    optRefNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val dbBaseType = exts(1)
        val cast       = exts(2) match {
          case ""  => ""
          case tpe => tpe + "[]"
        }
        val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
        val paramIndex = updateAction.setCol(setAttr)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))
      }
    } { refNs =>
      if (iterable.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, iterable.asInstanceOf[Set[Long]])
      }
    }
  }

  private def updateIterableRemove[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef]
  ): Unit = {
    optRefNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val scalaBaseType = exts.head
        val dbBaseType    = exts(1)
        val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
        val paramIndex    = updateAction.setCol(setAttr)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))
      }
    } { refNs =>
      if (iterable.nonEmpty) {
        val refIds = iterable.asInstanceOf[Set[Long]]
        updateAction.deleteRefIds(attr, refNs, getUpdateId, refIds)
      }
    }
  }


  protected def setAttrPresence(ns: String, attr: String): Unit = {
    if (isUpsert) {
      // Allow finding where clauses for ids query. Not used otherwise
      query.filterAttrs += AttrOneOptByte(ns, attr)
    } else {
      // Attribute value present in updated data
      query.filterAttrs += AttrOneTacByte(ns, attr)

      // Used for single ns update with ids and no filters
      updateAction.mandatoryCols += s"$ns.$attr IS NOT NULL"
    }
  }

  private def addArray(
    paramIndex: Int, dbBaseType: String, array: Array[AnyRef]
  ): Unit = {
    updateAction.addColSetter((ps: PS) => {
      val conn = ps.getConnection
      ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
    })
  }


  protected def getUpdateId: Long = {
    updateAction.ids match {
      case List(v) => v
      case other   => throw ModelError(
        "Expected to update one entity. Found multiple ids: " + other
      )
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