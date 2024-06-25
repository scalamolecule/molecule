package molecule.sql.sqlite.query

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.sql.core.query.{LambdasSet, SqlQueryBase}

trait LambdasSet_sqlite extends LambdasSet { self: SqlQueryBase =>


  override protected lazy val json2optArrayId            : String => Option[Array[Long]]           = (json: String) => if (json == null) Option.empty[Array[Long]] else jsonArray2optArray(jsonArrayId(json), json2oneId)
  override protected lazy val json2optArrayString        : String => Option[Array[String]]         = (json: String) => if (json == null) Option.empty[Array[String]] else jsonArray2optArray(jsonArrayString(json), json2oneString)
  override protected lazy val json2optArrayInt           : String => Option[Array[Int]]            = (json: String) => if (json == null) Option.empty[Array[Int]] else jsonArray2optArray(jsonArrayInt(json), json2oneInt)
  override protected lazy val json2optArrayLong          : String => Option[Array[Long]]           = (json: String) => if (json == null) Option.empty[Array[Long]] else jsonArray2optArray(jsonArrayLong(json), json2oneLong)
  override protected lazy val json2optArrayFloat         : String => Option[Array[Float]]          = (json: String) => if (json == null) Option.empty[Array[Float]] else jsonArray2optArray(jsonArrayFloat(json), json2oneFloat)
  override protected lazy val json2optArrayDouble        : String => Option[Array[Double]]         = (json: String) => if (json == null) Option.empty[Array[Double]] else jsonArray2optArray(jsonArrayDouble(json), json2oneDouble)
  override protected lazy val json2optArrayBoolean       : String => Option[Array[Boolean]]        = (json: String) => if (json == null) Option.empty[Array[Boolean]] else jsonArray2optArray(jsonArrayBoolean(json), json2oneBoolean)
  override protected lazy val json2optArrayBigInt        : String => Option[Array[BigInt]]         = (json: String) => if (json == null) Option.empty[Array[BigInt]] else jsonArray2optArray(jsonArrayBigInt(json), json2oneBigInt)
  override protected lazy val json2optArrayBigDecimal    : String => Option[Array[BigDecimal]]     = (json: String) => if (json == null) Option.empty[Array[BigDecimal]] else jsonArray2optArray(jsonArrayBigDecimal(json), json2oneBigDecimal)
  override protected lazy val json2optArrayDate          : String => Option[Array[Date]]           = (json: String) => if (json == null) Option.empty[Array[Date]] else jsonArray2optArray(jsonArrayDate(json), json2oneDate)
  override protected lazy val json2optArrayDuration      : String => Option[Array[Duration]]       = (json: String) => if (json == null) Option.empty[Array[Duration]] else jsonArray2optArray(jsonArrayDuration(json), json2oneDuration)
  override protected lazy val json2optArrayInstant       : String => Option[Array[Instant]]        = (json: String) => if (json == null) Option.empty[Array[Instant]] else jsonArray2optArray(jsonArrayInstant(json), json2oneInstant)
  override protected lazy val json2optArrayLocalDate     : String => Option[Array[LocalDate]]      = (json: String) => if (json == null) Option.empty[Array[LocalDate]] else jsonArray2optArray(jsonArrayLocalDate(json), json2oneLocalDate)
  override protected lazy val json2optArrayLocalTime     : String => Option[Array[LocalTime]]      = (json: String) => if (json == null) Option.empty[Array[LocalTime]] else jsonArray2optArray(jsonArrayLocalTime(json), json2oneLocalTime)
  override protected lazy val json2optArrayLocalDateTime : String => Option[Array[LocalDateTime]]  = (json: String) => if (json == null) Option.empty[Array[LocalDateTime]] else jsonArray2optArray(jsonArrayLocalDateTime(json), json2oneLocalDateTime)
  override protected lazy val json2optArrayOffsetTime    : String => Option[Array[OffsetTime]]     = (json: String) => if (json == null) Option.empty[Array[OffsetTime]] else jsonArray2optArray(jsonArrayOffsetTime(json), json2oneOffsetTime)
  override protected lazy val json2optArrayOffsetDateTime: String => Option[Array[OffsetDateTime]] = (json: String) => if (json == null) Option.empty[Array[OffsetDateTime]] else jsonArray2optArray(jsonArrayOffsetDateTime(json), json2oneOffsetDateTime)
  override protected lazy val json2optArrayZonedDateTime : String => Option[Array[ZonedDateTime]]  = (json: String) => if (json == null) Option.empty[Array[ZonedDateTime]] else jsonArray2optArray(jsonArrayZonedDateTime(json), json2oneZonedDateTime)
  override protected lazy val json2optArrayUUID          : String => Option[Array[UUID]]           = (json: String) => if (json == null) Option.empty[Array[UUID]] else jsonArray2optArray(jsonArrayUUID(json), json2oneUUID)
  override protected lazy val json2optArrayURI           : String => Option[Array[URI]]            = (json: String) => if (json == null) Option.empty[Array[URI]] else jsonArray2optArray(jsonArrayURI(json), json2oneURI)
  override protected lazy val json2optArrayByte          : String => Option[Array[Byte]]           = (json: String) => if (json == null) Option.empty[Array[Byte]] else jsonArray2optArray(jsonArrayByte(json), json2oneByte)
  override protected lazy val json2optArrayShort         : String => Option[Array[Short]]          = (json: String) => if (json == null) Option.empty[Array[Short]] else jsonArray2optArray(jsonArrayShort(json), json2oneShort)
  override protected lazy val json2optArrayChar          : String => Option[Array[Char]]           = (json: String) => if (json == null) Option.empty[Array[Char]] else jsonArray2optArray(jsonArrayChar(json), json2oneChar)
}
