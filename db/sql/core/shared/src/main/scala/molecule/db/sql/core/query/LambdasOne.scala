package molecule.db.sql.core.query

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.db.base.error.ModelError
import molecule.db.sql.core.javaSql.PrepStmt
import scala.util.control.NonFatal

trait LambdasOne extends LambdasBase { self: SqlQueryBase =>

  case class ResOne[T](
    tpe: String,
    sql2one: (RS, ParamIndex) => T,
    sql2oneOrNull: (RS, ParamIndex) => Any, // Allow null in optional nested rows
    one2sql: T => String,
    array2set: (RS, Int) => AnyRef,
    json2tpe: String => T,
    json2array: String => Array[T],
    bind: (PrepStmt, Int, Int, Any) => Unit
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

  def typed[T](bindIndex: Int, rawValue: Any, tpe: String, correctType: Boolean): T = {
    if correctType then rawValue.asInstanceOf[T] else
      throw ModelError(s"Bind value `$rawValue` of type ${rawValue.getClass.getSimpleName} at index $bindIndex should be a value of type $tpe.")
  }
  private lazy val bindID             = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typed[Long](bindIndex, rawValue, "Long", rawValue.isInstanceOf[Long]))
  private lazy val bindString         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[String](bindIndex, rawValue, "String", rawValue.isInstanceOf[String]))
  private lazy val bindInt            = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setInt(paramIndex, typed[Int](bindIndex, rawValue, "Int", rawValue.isInstanceOf[Int]))
  private lazy val bindLong           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typed[Long](bindIndex, rawValue, "Long", rawValue.isInstanceOf[Long]))
  private lazy val bindFloat          = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setFloat(paramIndex, typed[Float](bindIndex, rawValue, "Float", rawValue.isInstanceOf[Float]))
  private lazy val bindDouble         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setDouble(paramIndex, typed[Double](bindIndex, rawValue, "Double", rawValue.isInstanceOf[Double]))
  private lazy val bindBoolean        = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBoolean(paramIndex, typed[Boolean](bindIndex, rawValue, "Boolean", rawValue.isInstanceOf[Boolean]))
  private lazy val bindBigInt         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBigDecimal(paramIndex, BigDecimal(typed[BigInt](bindIndex, rawValue, "BigInt", rawValue.isInstanceOf[BigInt])).bigDecimal)
  private lazy val bindBigDecimal     = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBigDecimal(paramIndex, typed[BigDecimal](bindIndex, rawValue, "BigDecimal", rawValue.isInstanceOf[BigDecimal]).bigDecimal)
  private lazy val bindDate           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typed[Date](bindIndex, rawValue, "Date", rawValue.isInstanceOf[Date]).getTime)
  private lazy val bindDuration       = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[Duration](bindIndex, rawValue, "Duration", rawValue.isInstanceOf[Duration]).toString)
  private lazy val bindInstant        = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[Instant](bindIndex, rawValue, "Instant", rawValue.isInstanceOf[Instant]).toString)
  private lazy val bindLocalDate      = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[LocalDate](bindIndex, rawValue, "LocalDate", rawValue.isInstanceOf[LocalDate]).toString)
  private lazy val bindLocalTime      = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[LocalTime](bindIndex, rawValue, "LocalTime", rawValue.isInstanceOf[LocalTime]).toString)
  private lazy val bindLocalDateTime  = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[LocalDateTime](bindIndex, rawValue, "LocalDateTime", rawValue.isInstanceOf[LocalDateTime]).toString)
  private lazy val bindOffsetTime     = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[OffsetTime](bindIndex, rawValue, "OffsetTime", rawValue.isInstanceOf[OffsetTime]).toString)
  private lazy val bindOffsetDateTime = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[OffsetDateTime](bindIndex, rawValue, "OffsetDateTime", rawValue.isInstanceOf[OffsetDateTime]).toString)
  private lazy val bindZonedDateTime  = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[ZonedDateTime](bindIndex, rawValue, "ZonedDateTime", rawValue.isInstanceOf[ZonedDateTime]).toString)
  private lazy val bindUUID           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[UUID](bindIndex, rawValue, "UUID", rawValue.isInstanceOf[UUID]).toString)
  private lazy val bindURI            = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[URI](bindIndex, rawValue, "URI", rawValue.isInstanceOf[URI]).toString)
  private lazy val bindByte           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setByte(paramIndex, typed[Byte](bindIndex, rawValue, "Byte", rawValue.isInstanceOf[Byte]))
  private lazy val bindShort          = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setShort(paramIndex, typed[Short](bindIndex, rawValue, "Short", rawValue.isInstanceOf[Short]))
  private lazy val bindChar           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typed[Char](bindIndex, rawValue, "Char", rawValue.isInstanceOf[Char]).toString)

  protected lazy val resId1            : ResOne[Long]           = ResOne("Long", sql2oneId, sql2oneIdOrNull, one2sqlId, array2setId, json2oneId, json2arrayId, bindID)
  protected lazy val resString1        : ResOne[String]         = ResOne("String", sql2oneString, sql2oneStringOrNull, one2sqlString, array2setString, json2oneString, json2arrayString, bindString)
  protected lazy val resInt1           : ResOne[Int]            = ResOne("Int", sql2oneInt, sql2oneIntOrNull, one2sqlInt, array2setInt, json2oneInt, json2arrayInt, bindInt)
  protected lazy val resLong1          : ResOne[Long]           = ResOne("Long", sql2oneLong, sql2oneLongOrNull, one2sqlLong, array2setLong, json2oneLong, json2arrayLong, bindLong)
  protected lazy val resFloat1         : ResOne[Float]          = ResOne("Float", sql2oneFloat, sql2oneFloatOrNull, one2sqlFloat, array2setFloat, json2oneFloat, json2arrayFloat, bindFloat)
  protected lazy val resDouble1        : ResOne[Double]         = ResOne("Double", sql2oneDouble, sql2oneDoubleOrNull, one2sqlDouble, array2setDouble, json2oneDouble, json2arrayDouble, bindDouble)
  protected lazy val resBoolean1       : ResOne[Boolean]        = ResOne("Boolean", sql2oneBoolean, sql2oneBooleanOrNull, one2sqlBoolean, array2setBoolean, json2oneBoolean, json2arrayBoolean, bindBoolean)
  protected lazy val resBigInt1        : ResOne[BigInt]         = ResOne("BigInt", sql2oneBigInt, sql2oneBigIntOrNull, one2sqlBigInt, array2setBigInt, json2oneBigInt, json2arrayBigInt, bindBigInt)
  protected lazy val resBigDecimal1    : ResOne[BigDecimal]     = ResOne("BigDecimal", sql2oneBigDecimal, sql2oneBigDecimalOrNull, one2sqlBigDecimal, array2setBigDecimal, json2oneBigDecimal, json2arrayBigDecimal, bindBigDecimal)
  protected lazy val resDate1          : ResOne[Date]           = ResOne("Date", sql2oneDate, sql2oneDateOrNull, one2sqlDate, array2setDate, json2oneDate, json2arrayDate, bindDate)
  protected lazy val resDuration1      : ResOne[Duration]       = ResOne("Duration", sql2oneDuration, sql2oneDurationOrNull, one2sqlDuration, array2setDuration, json2oneDuration, json2arrayDuration, bindDuration)
  protected lazy val resInstant1       : ResOne[Instant]        = ResOne("Instant", sql2oneInstant, sql2oneInstantOrNull, one2sqlInstant, array2setInstant, json2oneInstant, json2arrayInstant, bindInstant)
  protected lazy val resLocalDate1     : ResOne[LocalDate]      = ResOne("LocalDate", sql2oneLocalDate, sql2oneLocalDateOrNull, one2sqlLocalDate, array2setLocalDate, json2oneLocalDate, json2arrayLocalDate, bindLocalDate)
  protected lazy val resLocalTime1     : ResOne[LocalTime]      = ResOne("LocalTime", sql2oneLocalTime, sql2oneLocalTimeOrNull, one2sqlLocalTime, array2setLocalTime, json2oneLocalTime, json2arrayLocalTime, bindLocalTime)
  protected lazy val resLocalDateTime1 : ResOne[LocalDateTime]  = ResOne("LocalDateTime", sql2oneLocalDateTime, sql2oneLocalDateTimeOrNull, one2sqlLocalDateTime, array2setLocalDateTime, json2oneLocalDateTime, json2arrayLocalDateTime, bindLocalDateTime)
  protected lazy val resOffsetTime1    : ResOne[OffsetTime]     = ResOne("OffsetTime", sql2oneOffsetTime, sql2oneOffsetTimeOrNull, one2sqlOffsetTime, array2setOffsetTime, json2oneOffsetTime, json2arrayOffsetTime, bindOffsetTime)
  protected lazy val resOffsetDateTime1: ResOne[OffsetDateTime] = ResOne("OffsetDateTime", sql2oneOffsetDateTime, sql2oneOffsetDateTimeOrNull, one2sqlOffsetDateTime, array2setOffsetDateTime, json2oneOffsetDateTime, json2arrayOffsetDateTime, bindOffsetDateTime)
  protected lazy val resZonedDateTime1 : ResOne[ZonedDateTime]  = ResOne("ZonedDateTime", sql2oneZonedDateTime, sql2oneZonedDateTimeOrNull, one2sqlZonedDateTime, array2setZonedDateTime, json2oneZonedDateTime, json2arrayZonedDateTime, bindZonedDateTime)
  protected lazy val resUUID1          : ResOne[UUID]           = ResOne("UUID", sql2oneUUID, sql2oneUUIDOrNull, one2sqlUUID, array2setUUID, json2oneUUID, json2arrayUUID, bindUUID)
  protected lazy val resURI1           : ResOne[URI]            = ResOne("URI", sql2oneURI, sql2oneURIOrNull, one2sqlURI, array2setURI, json2oneURI, json2arrayURI, bindURI)
  protected lazy val resByte1          : ResOne[Byte]           = ResOne("Byte", sql2oneByte, sql2oneByteOrNull, one2sqlByte, array2setByte, json2oneByte, json2arrayByte, bindByte)
  protected lazy val resShort1         : ResOne[Short]          = ResOne("Short", sql2oneShort, sql2oneShortOrNull, one2sqlShort, array2setShort, json2oneShort, json2arrayShort, bindShort)
  protected lazy val resChar1          : ResOne[Char]           = ResOne("Char", sql2oneChar, sql2oneCharOrNull, one2sqlChar, array2setChar, json2oneChar, json2arrayChar, bindChar)

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
    one2sql: T => String,
    bind: (PrepStmt, Int, Int, Any) => Unit
  )

  def typedOpt[T](bindIndex: Int, rawValue: Any, tpe: String): T = {
    try {
      rawValue.asInstanceOf[T]
    } catch {
      case NonFatal(_) =>
        throw ModelError(s"Bind value `$rawValue` at index $bindIndex should be of type $tpe. Found value of unexpected type ${rawValue.getClass.getSimpleName}.")
    }
  }
  private lazy val bindOptID             = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typedOpt[Long](bindIndex, rawValue, "Long"))
  private lazy val bindOptString         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[String](bindIndex, rawValue, "String"))
  private lazy val bindOptInt            = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setInt(paramIndex, typedOpt[Int](bindIndex, rawValue, "Int"))
  private lazy val bindOptLong           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typedOpt[Long](bindIndex, rawValue, "Long"))
  private lazy val bindOptFloat          = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setFloat(paramIndex, typedOpt[Float](bindIndex, rawValue, "Float"))
  private lazy val bindOptDouble         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setDouble(paramIndex, typedOpt[Double](bindIndex, rawValue, "Double"))
  private lazy val bindOptBoolean        = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBoolean(paramIndex, typedOpt[Boolean](bindIndex, rawValue, "Boolean"))
  private lazy val bindOptBigInt         = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBigDecimal(paramIndex, BigDecimal(typedOpt[BigInt](bindIndex, rawValue, "BigInt")).bigDecimal)
  private lazy val bindOptBigDecimal     = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setBigDecimal(paramIndex, typedOpt[BigDecimal](bindIndex, rawValue, "BigDecimal").bigDecimal)
  private lazy val bindOptDate           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setLong(paramIndex, typedOpt[Date](bindIndex, rawValue, "Date").getTime)
  private lazy val bindOptDuration       = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[Duration](bindIndex, rawValue, "Duration").toString)
  private lazy val bindOptInstant        = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[Instant](bindIndex, rawValue, "Instant").toString)
  private lazy val bindOptLocalDate      = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[LocalDate](bindIndex, rawValue, "LocalDate").toString)
  private lazy val bindOptLocalTime      = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[LocalTime](bindIndex, rawValue, "LocalTime").toString)
  private lazy val bindOptLocalDateTime  = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[LocalDateTime](bindIndex, rawValue, "LocalDateTime").toString)
  private lazy val bindOptOffsetTime     = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[OffsetTime](bindIndex, rawValue, "OffsetTime").toString)
  private lazy val bindOptOffsetDateTime = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[OffsetDateTime](bindIndex, rawValue, "OffsetDateTime").toString)
  private lazy val bindOptZonedDateTime  = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[ZonedDateTime](bindIndex, rawValue, "ZonedDateTime").toString)
  private lazy val bindOptUUID           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[UUID](bindIndex, rawValue, "UUID").toString)
  private lazy val bindOptURI            = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[URI](bindIndex, rawValue, "URI").toString)
  private lazy val bindOptByte           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setByte(paramIndex, typedOpt[Byte](bindIndex, rawValue, "Byte"))
  private lazy val bindOptShort          = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setShort(paramIndex, typedOpt[Short](bindIndex, rawValue, "Short"))
  private lazy val bindOptChar           = (ps: PrepStmt, paramIndex: Int, bindIndex: Int, rawValue: Any) => ps.setString(paramIndex, typedOpt[Char](bindIndex, rawValue, "Char").toString)

  protected lazy val resOptId            : ResOneOpt[Long]           = ResOneOpt(sql2oneOptId, one2sqlId, bindOptID)
  protected lazy val resOptString        : ResOneOpt[String]         = ResOneOpt(sql2oneOptString, one2sqlString, bindOptString)
  protected lazy val resOptInt           : ResOneOpt[Int]            = ResOneOpt(sql2oneOptInt, one2sqlInt, bindOptInt)
  protected lazy val resOptLong          : ResOneOpt[Long]           = ResOneOpt(sql2oneOptLong, one2sqlLong, bindOptLong)
  protected lazy val resOptFloat         : ResOneOpt[Float]          = ResOneOpt(sql2oneOptFloat, one2sqlFloat, bindOptFloat)
  protected lazy val resOptDouble        : ResOneOpt[Double]         = ResOneOpt(sql2oneOptDouble, one2sqlDouble, bindOptDouble)
  protected lazy val resOptBoolean       : ResOneOpt[Boolean]        = ResOneOpt(sql2oneOptBoolean, one2sqlBoolean, bindOptBoolean)
  protected lazy val resOptBigInt        : ResOneOpt[BigInt]         = ResOneOpt(sql2oneOptBigInt, one2sqlBigInt, bindOptBigInt)
  protected lazy val resOptBigDecimal    : ResOneOpt[BigDecimal]     = ResOneOpt(sql2oneOptBigDecimal, one2sqlBigDecimal, bindOptBigDecimal)
  protected lazy val resOptDate          : ResOneOpt[Date]           = ResOneOpt(sql2oneOptDate, one2sqlDate, bindOptDate)
  protected lazy val resOptDuration      : ResOneOpt[Duration]       = ResOneOpt(sql2oneOptDuration, one2sqlDuration, bindOptDuration)
  protected lazy val resOptInstant       : ResOneOpt[Instant]        = ResOneOpt(sql2oneOptInstant, one2sqlInstant, bindOptInstant)
  protected lazy val resOptLocalDate     : ResOneOpt[LocalDate]      = ResOneOpt(sql2oneOptLocalDate, one2sqlLocalDate, bindOptLocalDate)
  protected lazy val resOptLocalTime     : ResOneOpt[LocalTime]      = ResOneOpt(sql2oneOptLocalTime, one2sqlLocalTime, bindOptLocalTime)
  protected lazy val resOptLocalDateTime : ResOneOpt[LocalDateTime]  = ResOneOpt(sql2oneOptLocalDateTime, one2sqlLocalDateTime, bindOptLocalDateTime)
  protected lazy val resOptOffsetTime    : ResOneOpt[OffsetTime]     = ResOneOpt(sql2oneOptOffsetTime, one2sqlOffsetTime, bindOptOffsetTime)
  protected lazy val resOptOffsetDateTime: ResOneOpt[OffsetDateTime] = ResOneOpt(sql2oneOptOffsetDateTime, one2sqlOffsetDateTime, bindOptOffsetDateTime)
  protected lazy val resOptZonedDateTime : ResOneOpt[ZonedDateTime]  = ResOneOpt(sql2oneOptZonedDateTime, one2sqlZonedDateTime, bindOptZonedDateTime)
  protected lazy val resOptUUID          : ResOneOpt[UUID]           = ResOneOpt(sql2oneOptUUID, one2sqlUUID, bindOptUUID)
  protected lazy val resOptURI           : ResOneOpt[URI]            = ResOneOpt(sql2oneOptURI, one2sqlURI, bindOptURI)
  protected lazy val resOptByte          : ResOneOpt[Byte]           = ResOneOpt(sql2oneOptByte, one2sqlByte, bindOptByte)
  protected lazy val resOptShort         : ResOneOpt[Short]          = ResOneOpt(sql2oneOptShort, one2sqlShort, bindOptShort)
  protected lazy val resOptChar          : ResOneOpt[Char]           = ResOneOpt(sql2oneOptChar, one2sqlChar, bindOptChar)


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