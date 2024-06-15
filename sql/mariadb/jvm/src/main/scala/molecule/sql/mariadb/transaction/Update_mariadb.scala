package molecule.sql.mariadb.transaction

import java.sql.{PreparedStatement => PS}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb

trait Update_mariadb extends SqlUpdate { self: ResolveUpdate =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_mariadb[Any](elements)


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
      placeHolders = placeHolders :+
        s"$ns.$attr = JSON_MERGE_PATCH(IFNULL($ns.$attr, JSON_OBJECT()), ?)"
      val jsonBytes  = map2jsonByteArray(map, value2json)
      val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setBytes(curParamIndex, jsonBytes)
        curParamIndex += 1
      }
      addColSetter(curRefPath, colSetter)
    }
  }

  override protected def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    map: Map[String, T],
    exts: List[String],
  ): Unit = {
    if (map.nonEmpty) {
      cols += attr
      if (!isUpsert) {
        addToUpdateColsNotNull(attr)
      }
      val keys = map.keySet.map(k => s"'$$.$k'").mkString(", ")
      placeHolders = placeHolders :+
        s"""$ns.$attr = CASE JSON_REMOVE(IFNULL($ns.$attr, NULL), $keys)
           |    WHEN JSON_OBJECT() THEN NULL
           |    ELSE JSON_REMOVE($ns.$attr, $keys)
           |  END""".stripMargin
      addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => ())
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
      cols += attr
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = if (iterable.nonEmpty) {
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
          ps.setString(curParamIndex, json)
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
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      if (iterable.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        placeHolders = placeHolders :+ s"""$attr = JSON_MERGE(IFNULL($attr, '[]'), ?)"""
        val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setString(curParamIndex, json)
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
    one2json: T => String
  ): Unit = {
    refNs.fold {
      if (iterable.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        val valueTable    = "table_" + (placeHolders.size + 1)
        val dbType        = exts(1)
        val retractValues = iterable.asInstanceOf[Iterable[T]].map(one2json).mkString(", ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT JSON_ARRAYAGG($valueTable.v)
             |    FROM   JSON_TABLE($ns.$attr, '$$[*]' COLUMNS (v $dbType PATH '$$')) $valueTable
             |    WHERE  $valueTable.v NOT IN ($retractValues) AND $ns.id IS NOT NULL
             |  )""".stripMargin
        addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      }
    } { refNs =>
      joinRemove(ns, attr, refNs, iterable)
    }
  }


  override protected lazy val extsID             = List("ID", "BIGINT", "")
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