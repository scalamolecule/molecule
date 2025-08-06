package molecule.db.postgresql.transaction

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.{ResolveUpdate, SqlUpdate}

trait Update_postgresql
  extends SqlUpdate { self: ResolveUpdate & SqlOps =>

  override def handleReplaceAll[T](attr: String, vs: Seq[T]) = s"REGEXP_REPLACE($attr, ?, '${vs(1)}', 'g')"

  override def updateSetRemove[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ent, attr, optRef, set, exts, set2array)
  }

  override def updateSeqRemove[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ent, attr, optRef, seq, exts, seq2array)
  }

  override def updateMapEq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    noValue: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex = updateAction.setCol(s"$attr = ?::jsonb")
    if (map.isEmpty) {
      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      setAttrPresence(ent, attr)
      updateAction.addColSetter((ps: PS) =>
        ps.setString(paramIndex, map2json(map, value2json))
      )
    }
  }

  override def updateMapAdd[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      setAttrPresence(ent, attr)
      val setAttr    =
        s"""$attr = CASE
           |    WHEN $attr IS NULL THEN '{}'::jsonb
           |    ELSE $attr::jsonb
           |  END || ?::jsonb""".stripMargin
      val json       = map2json(map, value2json)
      val paramIndex = updateAction.setCol(setAttr)
      updateAction.addColSetter((ps: PS) =>
        ps.setString(paramIndex, json)
      )
    }
  }

  override def updateMapRemove(
    ent: String,
    attr: String,
    optRef: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      setAttrPresence(ent, attr)
      val setAttr    =
        s"""$attr = CASE
           |    WHEN $attr::jsonb - ? = '{}' THEN NULL
           |    ELSE $attr::jsonb - ?
           |  END""".stripMargin
      val paramIndex = updateAction.setCol(setAttr)
      updateAction.addColSetter((ps: PS) => {
        val conn  = ps.getConnection
        val array = conn.createArrayOf("text", keys.toArray)
        ps.setArray(paramIndex, array)
        ps.setArray(paramIndex + 1, array)
      })
    }
  }

  // Helpers -------------------------------------------------------------------

  private def updateIterableRemove[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    optRef: Option[String],
    iterable: M[T],
    exts: List[String],
    set2array: M[T] => Array[AnyRef]
  ): Unit = {
    optRef.fold {
      if (iterable.nonEmpty) {
        setAttrPresence(ent, attr)
        val cast          = exts(2)
        val setAttr       =
          s"""$attr = (
             |    SELECT ARRAY_AGG(_v)
             |    FROM UNNEST($attr$cast[]) AS _v
             |    WHERE NOT _v = ANY(?$cast[])
             |  )""".stripMargin
        val negatives     = set2array(iterable)
        val arrayBaseType = exts(3)
        val paramIndex    = updateAction.setCol(setAttr)
        updateAction.addColSetter((ps: PS) => {
          val conn  = ps.getConnection
          val array = conn.createArrayOf(arrayBaseType, negatives)
          ps.setArray(paramIndex, array)
        })
      }
    } { ref =>
      if (iterable.nonEmpty) {
        val refIds = iterable.asInstanceOf[Set[Long]]
        updateAction.deleteRefIds(attr, ref, getUpdateId, refIds)
      }
    }
  }

  override protected lazy val extsID             = List("ID", "VARCHAR", "::bigint", "bigint")
  override protected lazy val extsString         = List("String", "VARCHAR", "::text", "text")
  override protected lazy val extsInt            = List("Int", "INTEGER", "::integer", "integer")
  override protected lazy val extsLong           = List("Long", "BIGINT", "::bigint", "bigint")
  override protected lazy val extsFloat          = List("Float", "DECIMAL", "::decimal", "decimal")
  override protected lazy val extsDouble         = List("Double", "DECIMAL", "::double precision", "double precision")
  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "::boolean", "boolean")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL", "::numeric", "numeric")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL", "::numeric", "numeric")
  override protected lazy val extsDate           = List("Date", "BIGINT", "::bigint", "bigint")
  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsUUID           = List("UUID", "UUID", "::uuid", "uuid")
  override protected lazy val extsURI            = List("URI", "VARCHAR", "::varchar", "varchar")
  override protected lazy val extsByte           = List("Byte", "SMALLINT", "::smallint", "smallint")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "::smallint", "smallint")
  override protected lazy val extsChar           = List("Char", "TEXT", "::char", "char")
}