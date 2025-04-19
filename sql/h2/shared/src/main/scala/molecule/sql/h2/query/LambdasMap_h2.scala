package molecule.sql.h2.query

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.sql.core.query.{LambdasMap, SqlQueryBase}

trait LambdasMap_h2 extends LambdasMap { self: SqlQueryBase =>

  // Unquoting json string values from H2
  override protected lazy val json2oneId            : String => Long           = (v: String) => v.toLong
  override protected lazy val json2oneString        : String => String         = (v: String) => v.substring(1, v.length - 1)
  override protected lazy val json2oneInt           : String => Int            = (v: String) => v.toInt
  override protected lazy val json2oneLong          : String => Long           = (v: String) => v.toLong
  override protected lazy val json2oneFloat         : String => Float          = (v: String) => v.toFloat
  override protected lazy val json2oneDouble        : String => Double         = (v: String) => v.toDouble
  override protected lazy val json2oneBoolean       : String => Boolean        = (v: String) => v == "1"
  override protected lazy val json2oneBigInt        : String => BigInt         = (v: String) => BigInt(v.substring(1, v.length - 1))
  override protected lazy val json2oneBigDecimal    : String => BigDecimal     = (v: String) => BigDecimal(v.substring(1, v.length - 1))
  override protected lazy val json2oneDate          : String => Date           = (v: String) => new Date(v.toLong)
  override protected lazy val json2oneDuration      : String => Duration       = (v: String) => Duration.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneInstant       : String => Instant        = (v: String) => Instant.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneLocalDate     : String => LocalDate      = (v: String) => LocalDate.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneLocalTime     : String => LocalTime      = (v: String) => LocalTime.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneLocalDateTime : String => LocalDateTime  = (v: String) => LocalDateTime.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneOffsetTime    : String => OffsetTime     = (v: String) => OffsetTime.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneOffsetDateTime: String => OffsetDateTime = (v: String) => OffsetDateTime.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneZonedDateTime : String => ZonedDateTime  = (v: String) => ZonedDateTime.parse(v.substring(1, v.length - 1))
  override protected lazy val json2oneUUID          : String => UUID           = (v: String) => UUID.fromString(v.substring(1, v.length - 1))
  override protected lazy val json2oneURI           : String => URI            = (v: String) => new URI(v.substring(1, v.length - 1))
  override protected lazy val json2oneByte          : String => Byte           = (v: String) => v.toByte
  override protected lazy val json2oneShort         : String => Short          = (v: String) => v.toShort
  override protected lazy val json2oneChar          : String => Char           = (v: String) => v.charAt(1)
}
