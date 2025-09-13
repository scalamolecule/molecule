package molecule.db.postgresql.transaction

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.{ResolveSave, SqlSave}

trait Save_postgresql extends SqlSave { self: ResolveSave =>

  override protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optMap: Option[Map[String, T]],
    valueSetter: (PS, Int, T) => Unit,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = "::jsonb"
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: Product) => ps.setString(paramIndex, map2json(map, value2json))
      case _                                    =>
        (ps: PS, _: Product) => ps.setNull(paramIndex, 0)
    }
  }

  override protected lazy val extsID             = List("ID", "VARCHAR", "")
  override protected lazy val extsString         = List("String", "VARCHAR", "")
  override protected lazy val extsInt            = List("Int", "INTEGER", "")
  override protected lazy val extsLong           = List("Long", "BIGINT", "")
  override protected lazy val extsFloat          = List("Float", "DECIMAL", "")
  override protected lazy val extsDouble         = List("Double", "DECIMAL", "")
  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL", "")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL", "")
  override protected lazy val extsDate           = List("Date", "BIGINT", "")
  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "")
  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "")
  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "")
  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "")
  override protected lazy val extsUUID           = List("UUID", "UUID", "::uuid") // UUIDs require casting in postgres
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "SMALLINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "TEXT", "")
}