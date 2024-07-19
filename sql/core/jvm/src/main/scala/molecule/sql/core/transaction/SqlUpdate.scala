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
import molecule.sql.core.transaction.strategy.update.{UpdateAction, UpdateNs}
import scala.collection.mutable.ListBuffer

trait SqlUpdate
  extends SqlBase_JVM
    with UpdateOps
    with SqlBaseOps
    with SpiHelpers
    with MoleculeLogging { self: ResolveUpdate =>

  protected var update: UpdateAction = null

  def getUpdateAction(elements: List[Element]): UpdateAction = {
    update = UpdateNs(
      sqlConn,
      (elements: ListBuffer[Element]) => model2SqlQuery(elements.toList),
      getInitialNs(elements),
    )
    resolve(elements)
    update.initialAction
  }

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery
  def getUpdateData(elements: List[Element]): Data = ???

  protected def addToUpdateColsNotNull(attr: String) = ???


  override protected def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    val paramIndex = update.paramIndex(ns, attr, isUpsert, exts(2))
    vs match {
      case Seq(v) => update.addColSetter((ps: PS) =>
        transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex))
      case Nil    => update.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      case vs     => throw ExecutionError(
        s"Can only update one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }

  override def handleIds(ns: String, ids: Seq[Long]): Unit = {
    if (update.ids.nonEmpty) noIdsTwice()
    if (update.filters.nonEmpty) noMixIdsFilterAttrs()
    update.ids = ids.toList
    update.clauses += (ids match {
      case Seq(id) => s"$ns.id = $id"
      case Nil     => throw ModelError("Missing ids to update")
      case ids     => s"$ns.id IN(${ids.mkString(", ")})"
    })
  }

  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    if (update.ids.nonEmpty) noMixIdsFilterAttrs()
    filterAttr.asInstanceOf[Attr] match {
      case a: AttrSeqTac if a.op == Eq => noCollectionFilterEq(a.name)
      case a: AttrSetTac if a.op == Eq => noCollectionFilterEq(a.name)
      case _                           => update.filters += filterAttr
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
    val paramIndex = update.paramIndex(ns, attr, isUpsert)
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
      val scalaBaseType = exts.head
      val setAttr       = s"$attr = addPairs_$scalaBaseType($attr, ?)"
      val paramIndex    = update.paramIndex(ns, setAttr, isUpsert)
      update.addColSetter((ps: PS) =>
        ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      )
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
      val scalaBaseType = exts.head
      val setAttr       = s"$attr = removePairs_$scalaBaseType($attr, ?)"
      val paramIndex    = update.paramIndex(ns, setAttr, isUpsert)
      placeHolders = placeHolders :+ s"$attr = removePairs_$scalaBaseType($attr, ?)"
      update.addColSetter((ps: PS) =>
        ps.setArray(paramIndex, ps.getConnection.createArrayOf("String", keys.toArray))
      )
    }
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
    val paramIndex = update.paramIndex(ns, attr, isUpsert, dbBaseType)
    refNs.fold {
      if (iterable.nonEmpty) {
        addArray(paramIndex, dbBaseType, vs2array(iterable))
      } else {
        update.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
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
    refNs.fold {
      if (iterable.nonEmpty) {
        val dbBaseType = exts(1)
        val cast       = exts(2) match {
          case ""  => ""
          case tpe => tpe + "[]"
        }
        val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
        val paramIndex = update.paramIndex(ns, setAttr, isUpsert, dbBaseType)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))
      }
    } { refNs =>
      joinAdd(ns, attr, refNs, iterable)
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
        val scalaBaseType = exts.head
        val dbBaseType    = exts(1)
        val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
        val paramIndex    = update.paramIndex(ns, setAttr, isUpsert, dbBaseType)
        addArray(paramIndex, dbBaseType, iterable2array(iterable))

        //        placeHolders = placeHolders :+ s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
        //        val array = iterable2array(iterable)
        //        addColSetter(curRefPath, (ps: PS) => {
        //          val conn = ps.getConnection
        //          ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
        //          paramIndex += 1
        //        })
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
    val delete      = (ps: PS) => ps.addBatch()
    //    Table(deletePath, deleteJoins, delete)
    ???
  }

  private def addJoins(
    joinTable: String, ns_id: String, refNs_id: String, id: Long, refIds: Iterable[Long]
  ): Table = {
    val addPath  = List("addJoins")
    val addJoins = s"INSERT INTO $joinTable($ns_id, $refNs_id) VALUES (?, ?)"
    val add      = (ps: PS) =>
      refIds.foreach { refId =>
        ps.setLong(1, id)
        ps.setLong(2, refId)
        ps.addBatch()
      }
    //    Table(addPath, addJoins, add)
    ???
  }

  protected def updateMapEqJdbc[T](
    attr: String,
    cast: String,
    map: Map[String, T],
    map2jdbc: (PS, Int) => Unit
  ): Unit = {
    val paramIndex = update.paramIndex(attr)
    cols += attr
    placeHolders = placeHolders :+ s"$attr = ?$cast"
    if (!isUpsert) {
      addToUpdateColsNotNull(attr)
    }
    //    if (map.nonEmpty) {
    //      (ps: PS) =>
    //        map2jdbc(ps, paramIndex)
    //        paramIndex += 1
    //    } else {
    //      (ps: PS) => {
    //        ps.setNull(paramIndex, 0)
    //        paramIndex += 1
    //      }
    //    }
    ???
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