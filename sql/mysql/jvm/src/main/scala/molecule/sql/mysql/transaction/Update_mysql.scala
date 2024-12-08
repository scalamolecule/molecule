package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.core.transaction.strategy.SqlOps

trait Update_mysql extends SqlUpdate { self: ResolveUpdate with SqlOps =>

  override def handleAppend(attr: String, cast: String) = s"CONCAT($attr, ?$cast)"
  override def handlePrepend(attr: String, cast: String) = s"CONCAT(?$cast, $attr)"

  override def updateSetEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, set, value2json)
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(ns, attr, optRefNs, set, value2json)
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, set, exts, one2json)
  }

  override def updateSeqEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, seq, value2json)
  }

  override def updateSeqAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(ns, attr, optRefNs, seq, value2json)
  }

  override def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, seq, exts, one2json)
  }


  override def updateMapEq[T](
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
        ps.setString(paramIndex, map2json(map, value2json))
      )
    }
  }

  override def updateMapAdd[T](
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
      val setAttr    = s"$ns.$attr = JSON_MERGE_PATCH(IFNULL($ns.$attr, JSON_OBJECT()), ?)"
      val paramIndex = updateAction.setCol(setAttr)
      val json       = map2json(map, value2json)
      updateAction.addColSetter((ps: PS) =>
        ps.setString(paramIndex, json)
      )
    }
  }

  override def updateMapRemove(
    ns: String,
    attr: String,
    optRefNs: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      setAttrPresence(ns, attr)
      val keys1   = keys.map(k => s"'$$.$k'").mkString(", ")
      val setAttr =
        s"""$ns.$attr = CASE JSON_REMOVE(IFNULL($ns.$attr, NULL), $keys1)
           |    WHEN JSON_OBJECT() THEN NULL
           |    ELSE JSON_REMOVE($ns.$attr, $keys1)
           |  END""".stripMargin
      updateAction.setCol(setAttr)
      updateAction.addColSetter((_: PS) => ())
    }
  }

  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      val paramIndex = updateAction.setCol(s"$attr = ?")
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
        updateAction.addColSetter((ps: PS) => ps.setString(paramIndex, json))
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
    refNs: Option[String],
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val setAttr    = s"$attr = JSON_MERGE(IFNULL($attr, '[]'), ?)"
        val paramIndex = updateAction.setCol(setAttr)
        updateAction.addColSetter((ps: PS) => {
          val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
          ps.setString(paramIndex, json)
        })
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
    refNs: Option[String],
    iterable: M[T],
    exts: List[String],
    one2json: T => String
  ): Unit = {
    refNs.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        val valueTable    = "table_" + updateAction.colCount
        val cast          = exts(1)
        val retractValues = iterable.asInstanceOf[Iterable[T]].map(one2json).mkString(", ")

        updateAction.setCol(
          s"""$attr = (
             |    SELECT JSON_ARRAYAGG($valueTable.v)
             |    FROM   JSON_TABLE($ns.$attr, '$$[*]' COLUMNS (v $cast PATH '$$')) $valueTable
             |    WHERE  $valueTable.v NOT IN ($retractValues)
             |  )""".stripMargin
        )
        updateAction.addColSetter((_: PS) => ())
      }
    } { refNs =>
      if (iterable.nonEmpty) {
        val refIds = iterable.asInstanceOf[Set[Long]]
        updateAction.deleteRefIds(attr, refNs, getUpdateId, refIds)
      }
    }
  }

  override protected lazy val extsID             = List("ID", "LONGTEXT", "")
  override protected lazy val extsString         = List("String", "LONGTEXT", "")
  override protected lazy val extsInt            = List("Int", "INT", "")
  override protected lazy val extsLong           = List("Long", "BIGINT", "")
  override protected lazy val extsFloat          = List("Float", "REAL", "")
  override protected lazy val extsDouble         = List("Double", "DOUBLE", "")
  override protected lazy val extsBoolean        = List("Boolean", "TINYINT(1)", "")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL(65, 0)", "")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65, 30)", "")
  override protected lazy val extsDate           = List("Date", "BIGINT", "")
  override protected lazy val extsDuration       = List("Duration", "TINYTEXT", "")
  override protected lazy val extsInstant        = List("Instant", "TINYTEXT", "")
  override protected lazy val extsLocalDate      = List("LocalDate", "TINYTEXT", "")
  override protected lazy val extsLocalTime      = List("LocalTime", "TINYTEXT", "")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "TINYTEXT", "")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "TINYTEXT", "")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "TINYTEXT", "")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "TINYTEXT", "")
  override protected lazy val extsUUID           = List("UUID", "TINYTEXT", "")
  override protected lazy val extsURI            = List("URI", "TEXT", "")
  override protected lazy val extsByte           = List("Byte", "TINYINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "CHAR", "")
}