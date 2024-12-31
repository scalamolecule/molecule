package molecule.sql.core.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}

trait LambdasOne extends LambdasBase { self: SqlQueryBase =>

  case class ResOne[T](
    tpe: String,
    sql2one: (RS, ParamIndex) => T,
    sql2oneOrNull: (RS, ParamIndex) => Any, // Allow null in optional nested rows
    one2sql: T => String,
    array2set: (RS, Int) => AnyRef,
    json2tpe: String => T,
    json2array: String => Array[T],
  )

  protected lazy val sql2oneId            : (RS, Int) => Long           = (row: RS, paramIndex: Int) => row.getLong(paramIndex)
  protected lazy val sql2oneString        : (RS, Int) => String         = (row: RS, paramIndex: Int) => row.getString(paramIndex)
  protected lazy val sql2oneInt           : (RS, Int) => Int            = (row: RS, paramIndex: Int) => row.getInt(paramIndex)
  protected lazy val sql2oneLong          : (RS, Int) => Long           = (row: RS, paramIndex: Int) => row.getLong(paramIndex)
  protected lazy val sql2oneFloat         : (RS, Int) => Float          = (row: RS, paramIndex: Int) => row.getFloat(paramIndex)
  protected lazy val sql2oneDouble        : (RS, Int) => Double         = (row: RS, paramIndex: Int) => row.getDouble(paramIndex)
  protected lazy val sql2oneBoolean       : (RS, Int) => Boolean        = (row: RS, paramIndex: Int) => row.getBoolean(paramIndex)
  protected lazy val sql2oneBigInt        : (RS, Int) => BigInt         = (row: RS, paramIndex: Int) => row.getBigDecimal(paramIndex).toBigInteger
  protected lazy val sql2oneBigDecimal    : (RS, Int) => BigDecimal     = (row: RS, paramIndex: Int) => row.getBigDecimal(paramIndex)
  protected lazy val sql2oneDate          : (RS, Int) => Date           = (row: RS, paramIndex: Int) => new Date(row.getLong(paramIndex))
  protected lazy val sql2oneDuration      : (RS, Int) => Duration       = (row: RS, paramIndex: Int) => Duration.parse(row.getString(paramIndex))
  protected lazy val sql2oneInstant       : (RS, Int) => Instant        = (row: RS, paramIndex: Int) => Instant.parse(row.getString(paramIndex))
  protected lazy val sql2oneLocalDate     : (RS, Int) => LocalDate      = (row: RS, paramIndex: Int) => LocalDate.parse(row.getString(paramIndex))
  protected lazy val sql2oneLocalTime     : (RS, Int) => LocalTime      = (row: RS, paramIndex: Int) => LocalTime.parse(row.getString(paramIndex))
  protected lazy val sql2oneLocalDateTime : (RS, Int) => LocalDateTime  = (row: RS, paramIndex: Int) => LocalDateTime.parse(row.getString(paramIndex))
  protected lazy val sql2oneOffsetTime    : (RS, Int) => OffsetTime     = (row: RS, paramIndex: Int) => OffsetTime.parse(row.getString(paramIndex))
  protected lazy val sql2oneOffsetDateTime: (RS, Int) => OffsetDateTime = (row: RS, paramIndex: Int) => OffsetDateTime.parse(row.getString(paramIndex))
  protected lazy val sql2oneZonedDateTime : (RS, Int) => ZonedDateTime  = (row: RS, paramIndex: Int) => ZonedDateTime.parse(row.getString(paramIndex))
  protected lazy val sql2oneUUID          : (RS, Int) => UUID           = (row: RS, paramIndex: Int) => UUID.fromString(row.getString(paramIndex))
  protected lazy val sql2oneURI           : (RS, Int) => URI            = (row: RS, paramIndex: Int) => new URI(row.getString(paramIndex))
  protected lazy val sql2oneByte          : (RS, Int) => Byte           = (row: RS, paramIndex: Int) => row.getByte(paramIndex)
  protected lazy val sql2oneShort         : (RS, Int) => Short          = (row: RS, paramIndex: Int) => row.getShort(paramIndex)
  protected lazy val sql2oneChar          : (RS, Int) => Char           = (row: RS, paramIndex: Int) => row.getString(paramIndex).charAt(0)

  protected lazy val resId1            : ResOne[Long]           = ResOne("String", sql2oneId, sql2oneIdOrNull, one2sqlId, array2setId, json2oneId, json2arrayId)
  protected lazy val resString1        : ResOne[String]         = ResOne("String", sql2oneString, sql2oneStringOrNull, one2sqlString, array2setString, json2oneString, json2arrayString)
  protected lazy val resInt1           : ResOne[Int]            = ResOne("Int", sql2oneInt, sql2oneIntOrNull, one2sqlInt, array2setInt, json2oneInt, json2arrayInt)
  protected lazy val resLong1          : ResOne[Long]           = ResOne("Long", sql2oneLong, sql2oneLongOrNull, one2sqlLong, array2setLong, json2oneLong, json2arrayLong)
  protected lazy val resFloat1         : ResOne[Float]          = ResOne("Float", sql2oneFloat, sql2oneFloatOrNull, one2sqlFloat, array2setFloat, json2oneFloat, json2arrayFloat)
  protected lazy val resDouble1        : ResOne[Double]         = ResOne("Double", sql2oneDouble, sql2oneDoubleOrNull, one2sqlDouble, array2setDouble, json2oneDouble, json2arrayDouble)
  protected lazy val resBoolean1       : ResOne[Boolean]        = ResOne("Boolean", sql2oneBoolean, sql2oneBooleanOrNull, one2sqlBoolean, array2setBoolean, json2oneBoolean, json2arrayBoolean)
  protected lazy val resBigInt1        : ResOne[BigInt]         = ResOne("BigInt", sql2oneBigInt, sql2oneBigIntOrNull, one2sqlBigInt, array2setBigInt, json2oneBigInt, json2arrayBigInt)
  protected lazy val resBigDecimal1    : ResOne[BigDecimal]     = ResOne("BigDecimal", sql2oneBigDecimal, sql2oneBigDecimalOrNull, one2sqlBigDecimal, array2setBigDecimal, json2oneBigDecimal, json2arrayBigDecimal)
  protected lazy val resDate1          : ResOne[Date]           = ResOne("Date", sql2oneDate, sql2oneDateOrNull, one2sqlDate, array2setDate, json2oneDate, json2arrayDate)
  protected lazy val resDuration1      : ResOne[Duration]       = ResOne("Duration", sql2oneDuration, sql2oneDurationOrNull, one2sqlDuration, array2setDuration, json2oneDuration, json2arrayDuration)
  protected lazy val resInstant1       : ResOne[Instant]        = ResOne("Instant", sql2oneInstant, sql2oneInstantOrNull, one2sqlInstant, array2setInstant, json2oneInstant, json2arrayInstant)
  protected lazy val resLocalDate1     : ResOne[LocalDate]      = ResOne("LocalDate", sql2oneLocalDate, sql2oneLocalDateOrNull, one2sqlLocalDate, array2setLocalDate, json2oneLocalDate, json2arrayLocalDate)
  protected lazy val resLocalTime1     : ResOne[LocalTime]      = ResOne("LocalTime", sql2oneLocalTime, sql2oneLocalTimeOrNull, one2sqlLocalTime, array2setLocalTime, json2oneLocalTime, json2arrayLocalTime)
  protected lazy val resLocalDateTime1 : ResOne[LocalDateTime]  = ResOne("LocalDateTime", sql2oneLocalDateTime, sql2oneLocalDateTimeOrNull, one2sqlLocalDateTime, array2setLocalDateTime, json2oneLocalDateTime, json2arrayLocalDateTime)
  protected lazy val resOffsetTime1    : ResOne[OffsetTime]     = ResOne("OffsetTime", sql2oneOffsetTime, sql2oneOffsetTimeOrNull, one2sqlOffsetTime, array2setOffsetTime, json2oneOffsetTime, json2arrayOffsetTime)
  protected lazy val resOffsetDateTime1: ResOne[OffsetDateTime] = ResOne("OffsetDateTime", sql2oneOffsetDateTime, sql2oneOffsetDateTimeOrNull, one2sqlOffsetDateTime, array2setOffsetDateTime, json2oneOffsetDateTime, json2arrayOffsetDateTime)
  protected lazy val resZonedDateTime1 : ResOne[ZonedDateTime]  = ResOne("ZonedDateTime", sql2oneZonedDateTime, sql2oneZonedDateTimeOrNull, one2sqlZonedDateTime, array2setZonedDateTime, json2oneZonedDateTime, json2arrayZonedDateTime)
  protected lazy val resUUID1          : ResOne[UUID]           = ResOne("UUID", sql2oneUUID, sql2oneUUIDOrNull, one2sqlUUID, array2setUUID, json2oneUUID, json2arrayUUID)
  protected lazy val resURI1           : ResOne[URI]            = ResOne("URI", sql2oneURI, sql2oneURIOrNull, one2sqlURI, array2setURI, json2oneURI, json2arrayURI)
  protected lazy val resByte1          : ResOne[Byte]           = ResOne("Byte", sql2oneByte, sql2oneByteOrNull, one2sqlByte, array2setByte, json2oneByte, json2arrayByte)
  protected lazy val resShort1         : ResOne[Short]          = ResOne("Short", sql2oneShort, sql2oneShortOrNull, one2sqlShort, array2setShort, json2oneShort, json2arrayShort)
  protected lazy val resChar1          : ResOne[Char]           = ResOne("Char", sql2oneChar, sql2oneCharOrNull, one2sqlChar, array2setChar, json2oneChar, json2arrayChar)

  private lazy val sql2oneIdOrNull            : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getLong(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneStringOrNull        : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneIntOrNull           : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getInt(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneLongOrNull          : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getLong(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneFloatOrNull         : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getFloat(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneDoubleOrNull        : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getDouble(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneBooleanOrNull       : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getBoolean(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneBigIntOrNull        : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getBigDecimal(paramIndex); if (row.wasNull()) null else BigInt(v.toBigInteger) }
  private lazy val sql2oneBigDecimalOrNull    : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getBigDecimal(paramIndex); if (row.wasNull()) null else BigDecimal(v) }
  private lazy val sql2oneDateOrNull          : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getLong(paramIndex); if (row.wasNull()) null else new Date(v) }
  private lazy val sql2oneDurationOrNull      : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else Duration.parse(v) }
  private lazy val sql2oneInstantOrNull       : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else Instant.parse(v) }
  private lazy val sql2oneLocalDateOrNull     : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else LocalDate.parse(v) }
  private lazy val sql2oneLocalTimeOrNull     : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else LocalTime.parse(v) }
  private lazy val sql2oneLocalDateTimeOrNull : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else LocalDateTime.parse(v) }
  private lazy val sql2oneOffsetTimeOrNull    : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else OffsetTime.parse(v) }
  private lazy val sql2oneOffsetDateTimeOrNull: (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else OffsetDateTime.parse(v) }
  private lazy val sql2oneZonedDateTimeOrNull : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else ZonedDateTime.parse(v) }
  private lazy val sql2oneUUIDOrNull          : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else UUID.fromString(v) }
  private lazy val sql2oneURIOrNull           : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else new URI(v) }
  private lazy val sql2oneByteOrNull          : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getByte(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneShortOrNull         : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getShort(paramIndex); if (row.wasNull()) null else v }
  private lazy val sql2oneCharOrNull          : (RS, Int) => Any = { (row: RS, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else v.charAt(0) }

  case class ResOneOpt[T](
    sql2oneOpt: (RS, ParamIndex) => Option[T],
    one2sql: T => String
  )

  protected lazy val resOptId            : ResOneOpt[Long]           = ResOneOpt(sql2oneOptId, one2sqlId)
  protected lazy val resOptString        : ResOneOpt[String]         = ResOneOpt(sql2oneOptString, one2sqlString)
  protected lazy val resOptInt           : ResOneOpt[Int]            = ResOneOpt(sql2oneOptInt, one2sqlInt)
  protected lazy val resOptLong          : ResOneOpt[Long]           = ResOneOpt(sql2oneOptLong, one2sqlLong)
  protected lazy val resOptFloat         : ResOneOpt[Float]          = ResOneOpt(sql2oneOptFloat, one2sqlFloat)
  protected lazy val resOptDouble        : ResOneOpt[Double]         = ResOneOpt(sql2oneOptDouble, one2sqlDouble)
  protected lazy val resOptBoolean       : ResOneOpt[Boolean]        = ResOneOpt(sql2oneOptBoolean, one2sqlBoolean)
  protected lazy val resOptBigInt        : ResOneOpt[BigInt]         = ResOneOpt(sql2oneOptBigInt, one2sqlBigInt)
  protected lazy val resOptBigDecimal    : ResOneOpt[BigDecimal]     = ResOneOpt(sql2oneOptBigDecimal, one2sqlBigDecimal)
  protected lazy val resOptDate          : ResOneOpt[Date]           = ResOneOpt(sql2oneOptDate, one2sqlDate)
  protected lazy val resOptDuration      : ResOneOpt[Duration]       = ResOneOpt(sql2oneOptDuration, one2sqlDuration)
  protected lazy val resOptInstant       : ResOneOpt[Instant]        = ResOneOpt(sql2oneOptInstant, one2sqlInstant)
  protected lazy val resOptLocalDate     : ResOneOpt[LocalDate]      = ResOneOpt(sql2oneOptLocalDate, one2sqlLocalDate)
  protected lazy val resOptLocalTime     : ResOneOpt[LocalTime]      = ResOneOpt(sql2oneOptLocalTime, one2sqlLocalTime)
  protected lazy val resOptLocalDateTime : ResOneOpt[LocalDateTime]  = ResOneOpt(sql2oneOptLocalDateTime, one2sqlLocalDateTime)
  protected lazy val resOptOffsetTime    : ResOneOpt[OffsetTime]     = ResOneOpt(sql2oneOptOffsetTime, one2sqlOffsetTime)
  protected lazy val resOptOffsetDateTime: ResOneOpt[OffsetDateTime] = ResOneOpt(sql2oneOptOffsetDateTime, one2sqlOffsetDateTime)
  protected lazy val resOptZonedDateTime : ResOneOpt[ZonedDateTime]  = ResOneOpt(sql2oneOptZonedDateTime, one2sqlZonedDateTime)
  protected lazy val resOptUUID          : ResOneOpt[UUID]           = ResOneOpt(sql2oneOptUUID, one2sqlUUID)
  protected lazy val resOptURI           : ResOneOpt[URI]            = ResOneOpt(sql2oneOptURI, one2sqlURI)
  protected lazy val resOptByte          : ResOneOpt[Byte]           = ResOneOpt(sql2oneOptByte, one2sqlByte)
  protected lazy val resOptShort         : ResOneOpt[Short]          = ResOneOpt(sql2oneOptShort, one2sqlShort)
  protected lazy val resOptChar          : ResOneOpt[Char]           = ResOneOpt(sql2oneOptChar, one2sqlChar)


  private lazy val sql2oneOptId            : (RS, Int) => Option[Long]           = (row: RS, paramIndex: Int) => {
    val v = row.getLong(paramIndex)
    if (row.wasNull()) Option.empty[Long] else Some(v)
  }
  private lazy val sql2oneOptString        : (RS, Int) => Option[String]         = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[String] else Some(v)
  }
  private lazy val sql2oneOptInt           : (RS, Int) => Option[Int]            = (row: RS, paramIndex: Int) => {
    val v = row.getInt(paramIndex)
    if (row.wasNull()) Option.empty[Int] else Some(v)
  }
  private lazy val sql2oneOptLong          : (RS, Int) => Option[Long]           = (row: RS, paramIndex: Int) => {
    val v = row.getLong(paramIndex)
    if (row.wasNull()) Option.empty[Long] else Some(v)
  }
  private lazy val sql2oneOptFloat         : (RS, Int) => Option[Float]          = (row: RS, paramIndex: Int) => {
    val v = row.getFloat(paramIndex)
    if (row.wasNull()) Option.empty[Float] else Some(v)
  }
  private lazy val sql2oneOptDouble        : (RS, Int) => Option[Double]         = (row: RS, paramIndex: Int) => {
    val v = row.getDouble(paramIndex)
    if (row.wasNull()) Option.empty[Double] else Some(v)
  }
  private lazy val sql2oneOptBoolean       : (RS, Int) => Option[Boolean]        = (row: RS, paramIndex: Int) => {
    val v = row.getBoolean(paramIndex)
    if (row.wasNull()) Option.empty[Boolean] else Some(v)
  }
  private lazy val sql2oneOptBigInt        : (RS, Int) => Option[BigInt]         = (row: RS, paramIndex: Int) => {
    val v = row.getBigDecimal(paramIndex)
    if (row.wasNull()) Option.empty[BigInt] else Some(v.toBigInteger)
  }
  private lazy val sql2oneOptBigDecimal    : (RS, Int) => Option[BigDecimal]     = (row: RS, paramIndex: Int) => {
    val v = row.getBigDecimal(paramIndex)
    if (row.wasNull()) Option.empty[BigDecimal] else Some(v)
  }
  private lazy val sql2oneOptDate          : (RS, Int) => Option[Date]           = (row: RS, paramIndex: Int) => {
    val v = row.getLong(paramIndex)
    if (row.wasNull()) Option.empty[Date] else Some(new Date(v))
  }
  private lazy val sql2oneOptDuration      : (RS, Int) => Option[Duration]       = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[Duration] else Some(Duration.parse(v))
  }
  private lazy val sql2oneOptInstant       : (RS, Int) => Option[Instant]        = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[Instant] else Some(Instant.parse(v))
  }
  private lazy val sql2oneOptLocalDate     : (RS, Int) => Option[LocalDate]      = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[LocalDate] else Some(LocalDate.parse(v))
  }
  private lazy val sql2oneOptLocalTime     : (RS, Int) => Option[LocalTime]      = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[LocalTime] else Some(LocalTime.parse(v))
  }
  private lazy val sql2oneOptLocalDateTime : (RS, Int) => Option[LocalDateTime]  = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[LocalDateTime] else Some(LocalDateTime.parse(v))
  }
  private lazy val sql2oneOptOffsetTime    : (RS, Int) => Option[OffsetTime]     = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[OffsetTime] else Some(OffsetTime.parse(v))
  }
  private lazy val sql2oneOptOffsetDateTime: (RS, Int) => Option[OffsetDateTime] = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[OffsetDateTime] else Some(OffsetDateTime.parse(v))
  }
  private lazy val sql2oneOptZonedDateTime : (RS, Int) => Option[ZonedDateTime]  = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[ZonedDateTime] else Some(ZonedDateTime.parse(v))
  }
  private lazy val sql2oneOptUUID          : (RS, Int) => Option[UUID]           = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[UUID] else Some(UUID.fromString(v))
  }
  private lazy val sql2oneOptURI           : (RS, Int) => Option[URI]            = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[URI] else Some(new URI(v))
  }
  private lazy val sql2oneOptByte          : (RS, Int) => Option[Byte]           = (row: RS, paramIndex: Int) => {
    val v = row.getByte(paramIndex)
    if (row.wasNull()) Option.empty[Byte] else Some(v)
  }
  private lazy val sql2oneOptShort         : (RS, Int) => Option[Short]          = (row: RS, paramIndex: Int) => {
    val v = row.getShort(paramIndex)
    if (row.wasNull()) Option.empty[Short] else Some(v)
  }
  private lazy val sql2oneOptChar          : (RS, Int) => Option[Char]           = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[Char] else Some(v.charAt(0))
  }
}