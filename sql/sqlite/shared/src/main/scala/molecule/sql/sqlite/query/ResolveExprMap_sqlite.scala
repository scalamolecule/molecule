package molecule.sql.sqlite.query

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.sql.core.query.{ResolveExprMap, SqlQueryBase}

trait ResolveExprMap_sqlite
  extends ResolveExprMap
    with LambdasMap_sqlite { self: SqlQueryBase =>


  // Unquoting json string values from Sqlite
  override protected lazy val json2oneId            : String => String         = (v: String) => v
  override protected lazy val json2oneString        : String => String         = (v: String) => v
  override protected lazy val json2oneInt           : String => Int            = (v: String) => v.toInt
  override protected lazy val json2oneLong          : String => Long           = (v: String) => v.toLong
  override protected lazy val json2oneFloat         : String => Float          = (v: String) => v.toFloat
  override protected lazy val json2oneDouble        : String => Double         = (v: String) => v.toDouble
  override protected lazy val json2oneBoolean       : String => Boolean        = (v: String) => v == "1"
  override protected lazy val json2oneBigInt        : String => BigInt         = (v: String) => BigInt(v)
  override protected lazy val json2oneBigDecimal    : String => BigDecimal     = (v: String) => BigDecimal(v)
  override protected lazy val json2oneDate          : String => Date           = (v: String) => new Date(v.toLong)
  override protected lazy val json2oneDuration      : String => Duration       = (v: String) => Duration.parse(v)
  override protected lazy val json2oneInstant       : String => Instant        = (v: String) => Instant.parse(v)
  override protected lazy val json2oneLocalDate     : String => LocalDate      = (v: String) => LocalDate.parse(v)
  override protected lazy val json2oneLocalTime     : String => LocalTime      = (v: String) => LocalTime.parse(v)
  override protected lazy val json2oneLocalDateTime : String => LocalDateTime  = (v: String) => LocalDateTime.parse(v)
  override protected lazy val json2oneOffsetTime    : String => OffsetTime     = (v: String) => OffsetTime.parse(v)
  override protected lazy val json2oneOffsetDateTime: String => OffsetDateTime = (v: String) => OffsetDateTime.parse(v)
  override protected lazy val json2oneZonedDateTime : String => ZonedDateTime  = (v: String) => ZonedDateTime.parse(v)
  override protected lazy val json2oneUUID          : String => UUID           = (v: String) => UUID.fromString(v)
  override protected lazy val json2oneURI           : String => URI            = (v: String) => new URI(v)
  override protected lazy val json2oneByte          : String => Byte           = (v: String) => v.toByte
  override protected lazy val json2oneShort         : String => Short          = (v: String) => v.toShort
  override protected lazy val json2oneChar          : String => Char           = (v: String) => v.charAt(0)


  override protected lazy val jsonArrayId            : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayString        : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayInt           : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayLong          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayFloat         : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayDouble        : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayBoolean       : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayBigInt        : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayBigDecimal    : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayDate          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayDuration      : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayInstant       : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayLocalDate     : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayLocalTime     : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayLocalDateTime : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayOffsetTime    : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayOffsetDateTime: String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayZonedDateTime : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayUUID          : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayURI           : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  override protected lazy val jsonArrayByte          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayShort         : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  override protected lazy val jsonArrayChar          : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
}