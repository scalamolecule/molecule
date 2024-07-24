package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast.CardOne
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.spi.SpiHelpers
import molecule.sql.core.transaction.strategy.update.{UpdateAction, UpdateRoot}
import scala.collection.mutable.ListBuffer

trait SqlUpdate
  extends SqlBase_JVM
    with UpdateOps
    with SqlBaseOps
    with SpiHelpers
    with MoleculeLogging { self: ResolveUpdate =>

  protected var root  : UpdateRoot   = null
  protected var update: UpdateAction = null

  private var isRefUpdate = false
  private var usesFilters = false
  private var ns          = ""
  private val join        = if (isUpsert) "LEFT JOIN" else "INNER JOIN"

  // Build query for ids of each namespace involved in update
  private object query {
    var ids            = List.empty[Long]
    val idCols         = ListBuffer.empty[String]
    val joins          = ListBuffer.empty[String]
    val filterElements = ListBuffer.empty[Element]
  }

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery


  def getUpdateAction(elements: List[Element]): UpdateAction = {
    val m2q = (elements: ListBuffer[Element]) => model2SqlQuery(elements.toList)
    ns = getInitialNs(elements)
    query.idCols += s"$ns.id"
    root = UpdateRoot(sqlConn, m2q, ns, isUpsert)
    update = root.firstNs
    resolve(elements)
    initRoot()
    root
  }


  private def initRoot(): Unit = {
    if (usesFilters || isRefUpdate) {
      // Query for ids of each namespace
      val idsQuery = sqlOps.selectStmt(ns, query.idCols, query.joins,
        model2SqlQuery(query.filterElements.toList).getWhereClauses2
      )
      val nsCount = query.idCols.length
      val refIds  = new Array[ListBuffer[Long]](nsCount)
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

  override protected def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    val cast       = exts(2)
    val paramIndex = update.setCol(s"$attr = ?$cast")
    vs match {
      case Seq(v) =>
        setAttrPresence(ns, attr)
        update.addColSetter((ps: PS) =>
          transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
        )

      case Nil =>
        update.addColSetter((ps: PS) =>
          ps.setNull(paramIndex, 0))

      case vs => throw ExecutionError(
        s"Can only update one value for attribute `$ns.$attr`. " +
          s"Found: " + vs.mkString(", ")
      )
    }
  }

  override def handleIds(ns: String, ids0: Seq[Long]): Unit = {
    if (query.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in update.")
    }
    query.filterElements += AttrOneManID(ns, "id", Eq, ids0)
    query.ids = ids0.toList

    // Set here if no need for distributing ids across multiple namespaces
    update.ids = query.ids
  }

  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    filterAttr.asInstanceOf[Attr] match {
      case a: AttrSeqTac if a.op == Eq => noCollectionFilterEq(a.name)
      case a: AttrSetTac if a.op == Eq => noCollectionFilterEq(a.name)
      case _                           =>
        usesFilters = true
        query.filterElements += filterAttr
    }
  }

  override def handleRef(ref: Ref): Unit = {
    isRefUpdate = true
    val Ref(ns, refAttr, refNs, card, _, _) = ref
    query.idCols += s"$refNs.id"
    query.joins += s"$join $refNs ON $ns.$refAttr = $refNs.id"
    update = card match {
      case CardOne => update.refOne(ns, refAttr, refNs)
      case _       => update.refMany(ns, refAttr, refNs)
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
    val paramIndex = update.setCol(s"$attr = ?")
    if (byteArray.isEmpty) {
      update.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      update.addColSetter((ps: PS) => ps.setBytes(paramIndex, byteArray))
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
    val paramIndex = update.setCol(s"$attr = ?")
    if (map.isEmpty) {
      update.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      setAttrPresence(ns, attr)
      update.addColSetter((ps: PS) =>
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
      val paramIndex    = update.setCol(setAttr)
      update.addColSetter((ps: PS) =>
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
      val paramIndex    = update.setCol(setAttr)
      update.addColSetter((ps: PS) =>
        ps.setArray(paramIndex,
          ps.getConnection.createArrayOf("String", keys.toArray))
      )
    }
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    update = update.backRef
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
    refNs.fold {
      val dbBaseType = exts(1)
      val paramIndex = update.setCol(s"$attr = ?")
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        addArray(paramIndex, dbBaseType, vs2array(iterable))
      } else {
        update.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { refNs =>
      update.deleteRefIds(attr, refNs, getUpdateId)
      val refIds = iterable.asInstanceOf[Set[Long]]
      if (refIds.nonEmpty) {
        update.insertRefIds(attr, refNs, refIds)
      }
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
    refNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val dbBaseType = exts(1)
        val cast       = exts(2) match {
          case ""  => ""
          case tpe => tpe + "[]"
        }
        val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
        val paramIndex = update.setCol(setAttr)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))
      }
    } { refNs =>
      if (iterable.nonEmpty) {
        update.insertRefIds(attr, refNs, iterable.asInstanceOf[Set[Long]])
      }
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
    refNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val scalaBaseType = exts.head
        val dbBaseType    = exts(1)
        val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
        val paramIndex    = update.setCol(setAttr)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))
      }
    } { refNs =>
      if (iterable.nonEmpty) {
        val refIds = iterable.asInstanceOf[Set[Long]]
        update.deleteRefIds(attr, refNs, getUpdateId, refIds)
      }
    }
  }


  private def setAttrPresence(ns: String, attr: String): Unit = {
    if (isUpsert) {
      // Allow finding where clauses for ids query. Not used otherwise
      query.filterElements += AttrOneOptByte(ns, attr)
    } else {
      // Attribute value present in updated data
      query.filterElements += AttrOneTacByte(ns, attr)

      // Used for single ns update with ids and no filters
      update.mandatoryCols += s"$ns.$attr IS NOT NULL"
    }
  }

  private def addArray(
    paramIndex: Int, dbBaseType: String, array: Array[AnyRef]
  ): Unit = {
    update.addColSetter((ps: PS) => {
      val conn = ps.getConnection
      ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
    })
  }


  protected def getUpdateId: Long = {
    update.ids match {
      case List(v) => v
      case other   => throw ModelError(
        "Expected to update one entity. Found multiple ids: " + other
      )
    }
  }

  // todo: remove
  def getUpdateData(elements: List[Element]): Data = ???
  protected def addToUpdateColsNotNull(attr: String) = ???

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