package molecule.db.mariadb.transaction

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.{ResolveUpdate, SqlUpdate}

trait Update_mariadb extends SqlUpdate { self: ResolveUpdate =>

  override def handleAppend(attr: String, cast: String) = s"CONCAT($attr, ?$cast)"
  override def handlePrepend(attr: String, cast: String) = s"CONCAT(?$cast, $attr)"

  override def updateSetEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, set, value2json)
  }

  override def updateSetAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableAdd(ent, attr, paramIndex, set, value2json)
  }

  override def updateSetRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    updateIterableRemove(ent, attr, paramIndex, set, exts, one2json)
  }

  override def updateSeqEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, seq, value2json)
  }

  override def updateSeqAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableAdd(ent, attr, paramIndex, seq, value2json)
  }

  override def updateSeqRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    updateIterableRemove(ent, attr, paramIndex, seq, exts, one2json)
  }

  override protected def updateMapAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): (String, PS => Unit) = {
    if (map.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val colInput  = s"$attr = JSON_MERGE_PATCH(IFNULL($attr, JSON_OBJECT()), ?)"
      val colSetter = (ps: PS) => ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      (colInput, colSetter)
    }
  }

  override protected def updateMapRemove(
    ent: String,
    attr: String,
    paramIndex: Int,
    keys: Seq[String],
    exts: List[String],
  ): (String, PS => Unit) = {
    if (keys.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val keys1    = keys.map(k => s"'$$.$k'").mkString(", ")
      val colInput =
        s"""$ent.$attr = CASE JSON_REMOVE(IFNULL($ent.$attr, NULL), $keys1)
           |    WHEN JSON_OBJECT() THEN NULL
           |    ELSE JSON_REMOVE($ent.$attr, $keys1)
           |  END""".stripMargin

      (colInput, (_: PS) => ())
    }
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    val colSetter = if (iterable.isEmpty)
      (ps: PS) => ps.setNull(paramIndex, java.sql.Types.NULL)
    else {
      val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
      (ps: PS) => ps.setString(paramIndex, json)
    }
    (s"$attr = ?", colSetter)
  }

  private def updateIterableAdd[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    if (iterable.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val colInput  = s"$attr = JSON_MERGE(IFNULL($attr, '[]'), ?)"
      val json      = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
      val colSetter = (ps: PS) => ps.setString(paramIndex, json)


      (colInput, colSetter)
    }
  }

  private def updateIterableRemove[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    exts: List[String],
    one2json: T => String
  ): (String, PS => Unit) = {
    if (iterable.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val valueTable    = "table_" + paramIndex // todo - does this work?
      val dbType        = exts(1)
      val retractValues = iterable.asInstanceOf[Iterable[T]].map(one2json).mkString(", ")
      val colInput      =
        s"""$attr = (
           |    SELECT JSON_ARRAYAGG($valueTable.v)
           |    FROM   JSON_TABLE($ent.$attr, '$$[*]' COLUMNS (v $dbType PATH '$$')) $valueTable
           |    WHERE  $valueTable.v NOT IN ($retractValues) AND $ent.id IS NOT NULL
           |  )""".stripMargin

      (colInput, (_: PS) => ())
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
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65, 38)", "")
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