package molecule.db.common.query

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions

trait LambdasMap extends LambdasBase with JavaConversions { self: SqlQueryBase =>


  case class ResMap[T](
    tpe: String,
    tpeDb: String,
    one2json: T => String,
    sqlJson2map: (RS, ParamIndex) => Map[String, T],
    json2tpe: String => T,
  )

  lazy val resMapId            : ResMap[Long]           = ResMap("Long", tpeDbId, one2jsonId, sqlJson2mapId, json2oneId)
  lazy val resMapString        : ResMap[String]         = ResMap("String", tpeDbString, one2jsonString, sqlJson2mapString, json2oneString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", tpeDbInt, one2jsonInt, sqlJson2mapInt, json2oneInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", tpeDbLong, one2jsonLong, sqlJson2mapLong, json2oneLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", tpeDbFloat, one2jsonFloat, sqlJson2mapFloat, json2oneFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", tpeDbDouble, one2jsonDouble, sqlJson2mapDouble, json2oneDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", tpeDbBoolean, one2jsonBoolean, sqlJson2mapBoolean, json2oneBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", tpeDbBigInt, one2jsonBigInt, sqlJson2mapBigInt, json2oneBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", tpeDbBigDecimal, one2jsonBigDecimal, sqlJson2mapBigDecimal, json2oneBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", tpeDbDate, one2jsonDate, sqlJson2mapDate, json2oneDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", tpeDbDuration, one2jsonDuration, sqlJson2mapDuration, json2oneDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", tpeDbInstant, one2jsonInstant, sqlJson2mapInstant, json2oneInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", tpeDbLocalDate, one2jsonLocalDate, sqlJson2mapLocalDate, json2oneLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", tpeDbLocalTime, one2jsonLocalTime, sqlJson2mapLocalTime, json2oneLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", tpeDbLocalDateTime, one2jsonLocalDateTime, sqlJson2mapLocalDateTime, json2oneLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", tpeDbOffsetTime, one2jsonOffsetTime, sqlJson2mapOffsetTime, json2oneOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", tpeDbOffsetDateTime, one2jsonOffsetDateTime, sqlJson2mapOffsetDateTime, json2oneOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", tpeDbZonedDateTime, one2jsonZonedDateTime, sqlJson2mapZonedDateTime, json2oneZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", tpeDbUUID, one2jsonUUID, sqlJson2mapUUID, json2oneUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", tpeDbURI, one2jsonURI, sqlJson2mapURI, json2oneURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", tpeDbByte, one2jsonByte, sqlJson2mapByte, json2oneByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", tpeDbShort, one2jsonShort, sqlJson2mapShort, json2oneShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", tpeDbChar, one2jsonChar, sqlJson2mapChar, json2oneChar)


  private lazy val json2mapID            : String => Map[String, Long]           = (json: String) => upickle.default.read[Map[String, Long]](json)
  private lazy val json2mapString        : String => Map[String, String]         = (json: String) => upickle.default.read[Map[String, String]](json)
  private lazy val json2mapInt           : String => Map[String, Int]            = (json: String) => upickle.default.read[Map[String, Int]](json)
  private lazy val json2mapLong          : String => Map[String, Long]           = (json: String) => upickle.default.read[Map[String, Long]](json)
  private lazy val json2mapFloat         : String => Map[String, Float]          = (json: String) => upickle.default.read[Map[String, Float]](json)
  private lazy val json2mapDouble        : String => Map[String, Double]         = (json: String) => upickle.default.read[Map[String, Double]](json)
  private lazy val json2mapBoolean       : String => Map[String, Boolean]        = (json: String) => upickle.default.read[Map[String, Int]](json).map { case (k, v) => k -> (if (v == 1) true else false) }
  private lazy val json2mapBigInt        : String => Map[String, BigInt]         = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigInt(v) }
  private lazy val json2mapBigDecimal    : String => Map[String, BigDecimal]     = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> BigDecimal(v) }
  private lazy val json2mapDate          : String => Map[String, Date]           = (json: String) => upickle.default.read[Map[String, Long]](json).map { case (k, v) => k -> new Date(v) }
  private lazy val json2mapDuration      : String => Map[String, Duration]       = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Duration.parse(v) }
  private lazy val json2mapInstant       : String => Map[String, Instant]        = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> Instant.parse(v) }
  private lazy val json2mapLocalDate     : String => Map[String, LocalDate]      = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDate.parse(v) }
  private lazy val json2mapLocalTime     : String => Map[String, LocalTime]      = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalTime.parse(v) }
  private lazy val json2mapLocalDateTime : String => Map[String, LocalDateTime]  = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> LocalDateTime.parse(v) }
  private lazy val json2mapOffsetTime    : String => Map[String, OffsetTime]     = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetTime.parse(v) }
  private lazy val json2mapOffsetDateTime: String => Map[String, OffsetDateTime] = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> OffsetDateTime.parse(v) }
  private lazy val json2mapZonedDateTime : String => Map[String, ZonedDateTime]  = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> ZonedDateTime.parse(v) }
  private lazy val json2mapUUID          : String => Map[String, UUID]           = (json: String) => upickle.default.read[Map[String, UUID]](json)
  private lazy val json2mapURI           : String => Map[String, URI]            = (json: String) => upickle.default.read[Map[String, String]](json).map { case (k, v) => k -> new URI(v) }
  private lazy val json2mapByte          : String => Map[String, Byte]           = (json: String) => upickle.default.read[Map[String, Byte]](json)
  private lazy val json2mapShort         : String => Map[String, Short]          = (json: String) => upickle.default.read[Map[String, Short]](json)
  private lazy val json2mapChar          : String => Map[String, Char]           = (json: String) => upickle.default.read[Map[String, Char]](json)

  private lazy val sqlJson2mapId            : (RS, Int) => Map[String, Long]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapID)
  private lazy val sqlJson2mapString        : (RS, Int) => Map[String, String]         = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapString)
  private lazy val sqlJson2mapInt           : (RS, Int) => Map[String, Int]            = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapInt)
  private lazy val sqlJson2mapLong          : (RS, Int) => Map[String, Long]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapLong)
  private lazy val sqlJson2mapFloat         : (RS, Int) => Map[String, Float]          = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapFloat)
  private lazy val sqlJson2mapDouble        : (RS, Int) => Map[String, Double]         = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapDouble)
  private lazy val sqlJson2mapBoolean       : (RS, Int) => Map[String, Boolean]        = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapBoolean)
  private lazy val sqlJson2mapBigInt        : (RS, Int) => Map[String, BigInt]         = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapBigInt)
  private lazy val sqlJson2mapBigDecimal    : (RS, Int) => Map[String, BigDecimal]     = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapBigDecimal)
  private lazy val sqlJson2mapDate          : (RS, Int) => Map[String, Date]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapDate)
  private lazy val sqlJson2mapDuration      : (RS, Int) => Map[String, Duration]       = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapDuration)
  private lazy val sqlJson2mapInstant       : (RS, Int) => Map[String, Instant]        = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapInstant)
  private lazy val sqlJson2mapLocalDate     : (RS, Int) => Map[String, LocalDate]      = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapLocalDate)
  private lazy val sqlJson2mapLocalTime     : (RS, Int) => Map[String, LocalTime]      = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapLocalTime)
  private lazy val sqlJson2mapLocalDateTime : (RS, Int) => Map[String, LocalDateTime]  = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapLocalDateTime)
  private lazy val sqlJson2mapOffsetTime    : (RS, Int) => Map[String, OffsetTime]     = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapOffsetTime)
  private lazy val sqlJson2mapOffsetDateTime: (RS, Int) => Map[String, OffsetDateTime] = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapOffsetDateTime)
  private lazy val sqlJson2mapZonedDateTime : (RS, Int) => Map[String, ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapZonedDateTime)
  private lazy val sqlJson2mapUUID          : (RS, Int) => Map[String, UUID]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapUUID)
  private lazy val sqlJson2mapURI           : (RS, Int) => Map[String, URI]            = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapURI)
  private lazy val sqlJson2mapByte          : (RS, Int) => Map[String, Byte]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapByte)
  private lazy val sqlJson2mapShort         : (RS, Int) => Map[String, Short]          = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapShort)
  private lazy val sqlJson2mapChar          : (RS, Int) => Map[String, Char]           = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapChar)


  case class ResMapOpt[T](
    tpe: String,
    sql2optMap: (RS, ParamIndex) => Option[Map[String, T]],
  )

  lazy val resOptMapId            : ResMapOpt[Long]           = ResMapOpt("Long", sql2optMapId)
  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", sql2optMapString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", sql2optMapInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", sql2optMapLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", sql2optMapFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", sql2optMapDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", sql2optMapBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", sql2optMapBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", sql2optMapBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", sql2optMapDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", sql2optMapDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", sql2optMapInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", sql2optMapLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", sql2optMapLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", sql2optMapLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", sql2optMapOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", sql2optMapOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", sql2optMapZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", sql2optMapUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", sql2optMapURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", sql2optMapByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", sql2optMapShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", sql2optMapChar)


  protected def sql2mapOpt[T](row: RS, paramIndex: Int, json2map: String => Map[String, T]): Option[Map[String, T]] = {
    val byteArray = row.getBytes(paramIndex)
    if (!row.wasNull() && byteArray.nonEmpty)
      Some(json2map(new String(byteArray)))
    else
      None
  }

  private lazy val sql2optMapId            : (RS, Int) => Option[Map[String, Long]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapID)
  private lazy val sql2optMapString        : (RS, Int) => Option[Map[String, String]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapString)
  private lazy val sql2optMapInt           : (RS, Int) => Option[Map[String, Int]]            = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapInt)
  private lazy val sql2optMapLong          : (RS, Int) => Option[Map[String, Long]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLong)
  private lazy val sql2optMapFloat         : (RS, Int) => Option[Map[String, Float]]          = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapFloat)
  private lazy val sql2optMapDouble        : (RS, Int) => Option[Map[String, Double]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDouble)
  private lazy val sql2optMapBoolean       : (RS, Int) => Option[Map[String, Boolean]]        = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBoolean)
  private lazy val sql2optMapBigInt        : (RS, Int) => Option[Map[String, BigInt]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBigInt)
  private lazy val sql2optMapBigDecimal    : (RS, Int) => Option[Map[String, BigDecimal]]     = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBigDecimal)
  private lazy val sql2optMapDate          : (RS, Int) => Option[Map[String, Date]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDate)
  private lazy val sql2optMapDuration      : (RS, Int) => Option[Map[String, Duration]]       = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDuration)
  private lazy val sql2optMapInstant       : (RS, Int) => Option[Map[String, Instant]]        = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapInstant)
  private lazy val sql2optMapLocalDate     : (RS, Int) => Option[Map[String, LocalDate]]      = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalDate)
  private lazy val sql2optMapLocalTime     : (RS, Int) => Option[Map[String, LocalTime]]      = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalTime)
  private lazy val sql2optMapLocalDateTime : (RS, Int) => Option[Map[String, LocalDateTime]]  = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalDateTime)
  private lazy val sql2optMapOffsetTime    : (RS, Int) => Option[Map[String, OffsetTime]]     = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapOffsetTime)
  private lazy val sql2optMapOffsetDateTime: (RS, Int) => Option[Map[String, OffsetDateTime]] = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapOffsetDateTime)
  private lazy val sql2optMapZonedDateTime : (RS, Int) => Option[Map[String, ZonedDateTime]]  = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapZonedDateTime)
  private lazy val sql2optMapUUID          : (RS, Int) => Option[Map[String, UUID]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapUUID)
  private lazy val sql2optMapURI           : (RS, Int) => Option[Map[String, URI]]            = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapURI)
  private lazy val sql2optMapByte          : (RS, Int) => Option[Map[String, Byte]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapByte)
  private lazy val sql2optMapShort         : (RS, Int) => Option[Map[String, Short]]          = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapShort)
  private lazy val sql2optMapChar          : (RS, Int) => Option[Map[String, Char]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapChar)
}
