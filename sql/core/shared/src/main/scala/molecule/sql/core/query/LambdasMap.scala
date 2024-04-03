package molecule.sql.core.query

import java.net.URI
import java.nio.ByteBuffer
import java.time._
import java.util.{Date, UUID}
import boopickle.Default._
import molecule.core.util.JavaConversions

trait LambdasMap extends LambdasBase with JavaConversions { self: SqlQueryBase =>


  case class ResMap[T](
    tpe: String,
    tpeDb: String,
    one2json: T => String,
    sqlJson2map: (RS, ParamIndex) => Map[String, T],
    json2tpe: String => T,

    //    sql2setOrNull: (RS, ParamIndex) => Any, // Allow null in optional nested rows
    //    set2sqlArray: Set[T] => String,
    //    set2sqls: Set[T] => Set[String],
    //    one2sql: T => String,
    //    array2set: (RS, ParamIndex) => Set[T],
    //    nestedArray2coalescedSet: (RS, ParamIndex) => Set[T],
    //    nestedArray2optCoalescedSet: (RS, ParamIndex) => Option[Set[T]],
    //    nestedArray2nestedSet: (RS, ParamIndex) => Set[Set[T]],
    //    array2setFirst: (RS, ParamIndex) => Set[T],
    //    array2setLast: (RS, ParamIndex) => Set[T],
    //    nestedArray2setAsc: Int => (RS, ParamIndex) => Set[T],
    //    nestedArray2setDesc: Int => (RS, ParamIndex) => Set[T],
    //    nestedArray2sum: (RS, ParamIndex) => Set[T],
    //    json2array: String => Array[T],
    //    json2optArray: String => Option[Array[T]],
    //    stringArray2sum: Array[String] => T,
    //    row2tpe: RS => T,
    //    sqlArray2sum: (RS, ParamIndex) => Set[T],
    //    sqlArray2minN: Int => (RS, ParamIndex) => Set[T],
    //    sqlArray2maxN: Int => (RS, ParamIndex) => Set[T],
    //    array2optSet: (RS, ParamIndex) => Option[Set[T]],
  )

  lazy val resMapId            : ResMap[String]         = ResMap("String", tpeDbId, one2jsonId, sqlJson2mapId, json2oneId) //, null, set2sqlArrayId, set2sqlsId, one2sqlId, array2setId, nestedArray2coalescedSetId, nestedArray2optCoalescedSetId, nestedArray2nestedSetId, array2setFirstId, array2setLastId, nestedArray2setAscId, nestedArray2setDescId, nestedArray2sumId, json2arrayId, json2optArrayId, stringArray2sumId, valueId, sqlArray2sumId, sqlArray2minNId, sqlArray2maxNId, array2optSetId)
  lazy val resMapString        : ResMap[String]         = ResMap("String", tpeDbString, one2jsonString, sqlJson2mapString, json2oneString) //, null, set2sqlArrayString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2optCoalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2sumString, json2arrayString, json2optArrayString, stringArray2sumString, valueString, sqlArray2sumString, sqlArray2minNString, sqlArray2maxNString, array2optSetString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", tpeDbInt, one2jsonInt, sqlJson2mapInt, json2oneInt) //, null, set2sqlArrayInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2optCoalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2sumInt, json2arrayInt, json2optArrayInt, stringArray2sumInt, valueInt, sqlArray2sumInt, sqlArray2minNInt, sqlArray2maxNInt, array2optSetInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", tpeDbLong, one2jsonLong, sqlJson2mapLong, json2oneLong) //, null, set2sqlArrayLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2optCoalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2sumLong, json2arrayLong, json2optArrayLong, stringArray2sumLong, valueLong, sqlArray2sumLong, sqlArray2minNLong, sqlArray2maxNLong, array2optSetLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", tpeDbFloat, one2jsonFloat, sqlJson2mapFloat, json2oneFloat) //, null, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2optCoalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2sumFloat, json2arrayFloat, json2optArrayFloat, stringArray2sumFloat, valueFloat, sqlArray2sumFloat, sqlArray2minNFloat, sqlArray2maxNFloat, array2optSetFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", tpeDbDouble, one2jsonDouble, sqlJson2mapDouble, json2oneDouble) //, null, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2optCoalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2sumDouble, json2arrayDouble, json2optArrayDouble, stringArray2sumDouble, valueDouble, sqlArray2sumDouble, sqlArray2minNDouble, sqlArray2maxNDouble, array2optSetDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", tpeDbBoolean, one2jsonBoolean, sqlJson2mapBoolean, json2oneBoolean) //, null, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2optCoalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2sumBoolean, json2arrayBoolean, json2optArrayBoolean, stringArray2sumBoolean, valueBoolean, sqlArray2sumBoolean, sqlArray2minNBoolean, sqlArray2maxNBoolean, array2optSetBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", tpeDbBigInt, one2jsonBigInt, sqlJson2mapBigInt, json2oneBigInt) //, null, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2optCoalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2sumBigInt, json2arrayBigInt, json2optArrayBigInt, stringArray2sumBigInt, valueBigInt, sqlArray2sumBigInt, sqlArray2minNBigInt, sqlArray2maxNBigInt, array2optSetBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", tpeDbBigDecimal, one2jsonBigDecimal, sqlJson2mapBigDecimal, json2oneBigDecimal) //, null, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2optCoalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2sumBigDecimal, json2arrayBigDecimal, json2optArrayBigDecimal, stringArray2sumBigDecimal, valueBigDecimal, sqlArray2sumBigDecimal, sqlArray2minNBigDecimal, sqlArray2maxNBigDecimal, array2optSetBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", tpeDbDate, one2jsonDate, sqlJson2mapDate, json2oneDate) //, null, set2sqlArrayDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2optCoalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2sumDate, json2arrayDate, json2optArrayDate, stringArray2sumDate, valueDate, sqlArray2sumDate, sqlArray2minNDate, sqlArray2maxNDate, array2optSetDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", tpeDbDuration, one2jsonDuration, sqlJson2mapDuration, json2oneDuration) //, null, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, array2setDuration, nestedArray2coalescedSetDuration, nestedArray2optCoalescedSetDuration, nestedArray2nestedSetDuration, array2setFirstDuration, array2setLastDuration, nestedArray2setAscDuration, nestedArray2setDescDuration, nestedArray2sumDuration, json2arrayDuration, json2optArrayDuration, stringArray2sumDuration, valueDuration, sqlArray2sumDuration, sqlArray2minNDuration, sqlArray2maxNDuration, array2optSetDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", tpeDbInstant, one2jsonInstant, sqlJson2mapInstant, json2oneInstant) //, null, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, array2setInstant, nestedArray2coalescedSetInstant, nestedArray2optCoalescedSetInstant, nestedArray2nestedSetInstant, array2setFirstInstant, array2setLastInstant, nestedArray2setAscInstant, nestedArray2setDescInstant, nestedArray2sumInstant, json2arrayInstant, json2optArrayInstant, stringArray2sumInstant, valueInstant, sqlArray2sumInstant, sqlArray2minNInstant, sqlArray2maxNInstant, array2optSetInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", tpeDbLocalDate, one2jsonLocalDate, sqlJson2mapLocalDate, json2oneLocalDate) //, null, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, array2setLocalDate, nestedArray2coalescedSetLocalDate, nestedArray2optCoalescedSetLocalDate, nestedArray2nestedSetLocalDate, array2setFirstLocalDate, array2setLastLocalDate, nestedArray2setAscLocalDate, nestedArray2setDescLocalDate, nestedArray2sumLocalDate, json2arrayLocalDate, json2optArrayLocalDate, stringArray2sumLocalDate, valueLocalDate, sqlArray2sumLocalDate, sqlArray2minNLocalDate, sqlArray2maxNLocalDate, array2optSetLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", tpeDbLocalTime, one2jsonLocalTime, sqlJson2mapLocalTime, json2oneLocalTime) //, null, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, array2setLocalTime, nestedArray2coalescedSetLocalTime, nestedArray2optCoalescedSetLocalTime, nestedArray2nestedSetLocalTime, array2setFirstLocalTime, array2setLastLocalTime, nestedArray2setAscLocalTime, nestedArray2setDescLocalTime, nestedArray2sumLocalTime, json2arrayLocalTime, json2optArrayLocalTime, stringArray2sumLocalTime, valueLocalTime, sqlArray2sumLocalTime, sqlArray2minNLocalTime, sqlArray2maxNLocalTime, array2optSetLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", tpeDbLocalDateTime, one2jsonLocalDateTime, sqlJson2mapLocalDateTime, json2oneLocalDateTime) //, null, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, array2setLocalDateTime, nestedArray2coalescedSetLocalDateTime, nestedArray2optCoalescedSetLocalDateTime, nestedArray2nestedSetLocalDateTime, array2setFirstLocalDateTime, array2setLastLocalDateTime, nestedArray2setAscLocalDateTime, nestedArray2setDescLocalDateTime, nestedArray2sumLocalDateTime, json2arrayLocalDateTime, json2optArrayLocalDateTime, stringArray2sumLocalDateTime, valueLocalDateTime, sqlArray2sumLocalDateTime, sqlArray2minNLocalDateTime, sqlArray2maxNLocalDateTime, array2optSetLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", tpeDbOffsetTime, one2jsonOffsetTime, sqlJson2mapOffsetTime, json2oneOffsetTime) //, null, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, array2setOffsetTime, nestedArray2coalescedSetOffsetTime, nestedArray2optCoalescedSetOffsetTime, nestedArray2nestedSetOffsetTime, array2setFirstOffsetTime, array2setLastOffsetTime, nestedArray2setAscOffsetTime, nestedArray2setDescOffsetTime, nestedArray2sumOffsetTime, json2arrayOffsetTime, json2optArrayOffsetTime, stringArray2sumOffsetTime, valueOffsetTime, sqlArray2sumOffsetTime, sqlArray2minNOffsetTime, sqlArray2maxNOffsetTime, array2optSetOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", tpeDbOffsetDateTime, one2jsonOffsetDateTime, sqlJson2mapOffsetDateTime, json2oneOffsetDateTime) //, null, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, array2setOffsetDateTime, nestedArray2coalescedSetOffsetDateTime, nestedArray2optCoalescedSetOffsetDateTime, nestedArray2nestedSetOffsetDateTime, array2setFirstOffsetDateTime, array2setLastOffsetDateTime, nestedArray2setAscOffsetDateTime, nestedArray2setDescOffsetDateTime, nestedArray2sumOffsetDateTime, json2arrayOffsetDateTime, json2optArrayOffsetDateTime, stringArray2sumOffsetDateTime, valueOffsetDateTime, sqlArray2sumOffsetDateTime, sqlArray2minNOffsetDateTime, sqlArray2maxNOffsetDateTime, array2optSetOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", tpeDbZonedDateTime, one2jsonZonedDateTime, sqlJson2mapZonedDateTime, json2oneZonedDateTime) //, null, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, array2setZonedDateTime, nestedArray2coalescedSetZonedDateTime, nestedArray2optCoalescedSetZonedDateTime, nestedArray2nestedSetZonedDateTime, array2setFirstZonedDateTime, array2setLastZonedDateTime, nestedArray2setAscZonedDateTime, nestedArray2setDescZonedDateTime, nestedArray2sumZonedDateTime, json2arrayZonedDateTime, json2optArrayZonedDateTime, stringArray2sumZonedDateTime, valueZonedDateTime, sqlArray2sumZonedDateTime, sqlArray2minNZonedDateTime, sqlArray2maxNZonedDateTime, array2optSetZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", tpeDbUUID, one2jsonUUID, sqlJson2mapUUID, json2oneUUID) //, null, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2optCoalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2sumUUID, json2arrayUUID, json2optArrayUUID, stringArray2sumUUID, valueUUID, sqlArray2sumUUID, sqlArray2minNUUID, sqlArray2maxNUUID, array2optSetUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", tpeDbURI, one2jsonURI, sqlJson2mapURI, json2oneURI) //, null, set2sqlArrayURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2optCoalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2sumURI, json2arrayURI, json2optArrayURI, stringArray2sumURI, valueURI, sqlArray2sumURI, sqlArray2minNURI, sqlArray2maxNURI, array2optSetURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", tpeDbByte, one2jsonByte, sqlJson2mapByte, json2oneByte) //, null, set2sqlArrayByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2optCoalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2sumByte, json2arrayByte, json2optArrayByte, stringArray2sumByte, valueByte, sqlArray2sumByte, sqlArray2minNByte, sqlArray2maxNByte, array2optSetByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", tpeDbShort, one2jsonShort, sqlJson2mapShort, json2oneShort) //, null, set2sqlArrayShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2optCoalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2sumShort, json2arrayShort, json2optArrayShort, stringArray2sumShort, valueShort, sqlArray2sumShort, sqlArray2minNShort, sqlArray2maxNShort, array2optSetShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", tpeDbChar, one2jsonChar, sqlJson2mapChar, json2oneChar) //, null, set2sqlArrayChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2optCoalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2sumChar, json2arrayChar, json2optArrayChar, stringArray2sumChar, valueChar, sqlArray2sumChar, sqlArray2minNChar, sqlArray2maxNChar, array2optSetChar)


  private lazy val json2mapID            : String => Map[String, String]         = (json: String) => upickle.default.read[Map[String, String]](json)
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

  private lazy val sqlJson2mapId            : (RS, Int) => Map[String, String]         = (row: RS, paramIndex: Int) => sqlJson2map(row, paramIndex, json2mapID)
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

  //  private lazy val set2sqlArrayId            : Set[String] => String         = (set: Set[String]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
  //  private lazy val set2sqlArrayString        : Set[String] => String         = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY['", "', '", "']::text[]")
  //  private lazy val set2sqlArrayInt           : Set[Int] => String            = (set: Set[Int]) => set.mkString("ARRAY[", ", ", "]::integer[]")
  //  private lazy val set2sqlArrayLong          : Set[Long] => String           = (set: Set[Long]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
  //  private lazy val set2sqlArrayFloat         : Set[Float] => String          = (set: Set[Float]) => set.mkString("ARRAY[", ", ", "]::decimal[]")
  //  private lazy val set2sqlArrayDouble        : Set[Double] => String         = (set: Set[Double]) => set.mkString("ARRAY[", ", ", "]::double precision[]")
  //  private lazy val set2sqlArrayBoolean       : Set[Boolean] => String        = (set: Set[Boolean]) => set.mkString("ARRAY[", ", ", "]::boolean[]")
  //  private lazy val set2sqlArrayBigInt        : Set[BigInt] => String         = (set: Set[BigInt]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  //  private lazy val set2sqlArrayBigDecimal    : Set[BigDecimal] => String     = (set: Set[BigDecimal]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  //  private lazy val set2sqlArrayDate          : Set[Date] => String           = (set: Set[Date]) => set.map(_.getTime).mkString("ARRAY[", ", ", "]::bigint[]")
  //  private lazy val set2sqlArrayDuration      : Set[Duration] => String       = (set: Set[Duration]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayInstant       : Set[Instant] => String        = (set: Set[Instant]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayLocalDate     : Set[LocalDate] => String      = (set: Set[LocalDate]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayLocalTime     : Set[LocalTime] => String      = (set: Set[LocalTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayLocalDateTime : Set[LocalDateTime] => String  = (set: Set[LocalDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayOffsetTime    : Set[OffsetTime] => String     = (set: Set[OffsetTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayOffsetDateTime: Set[OffsetDateTime] => String = (set: Set[OffsetDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayZonedDateTime : Set[ZonedDateTime] => String  = (set: Set[ZonedDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayUUID          : Set[UUID] => String           = (set: Set[UUID]) => set.mkString("ARRAY['", "', '", "']::uuid[]")
  //  private lazy val set2sqlArrayURI           : Set[URI] => String            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY['", "', '", "']::varchar[]")
  //  private lazy val set2sqlArrayByte          : Set[Byte] => String           = (set: Set[Byte]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  //  private lazy val set2sqlArrayShort         : Set[Short] => String          = (set: Set[Short]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  //  private lazy val set2sqlArrayChar          : Set[Char] => String           = (set: Set[Char]) => set.mkString("ARRAY['", "', '", "']::char[]")
  //
  //  private lazy val set2sqlsId            : Set[String] => Set[String]         = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  //  private lazy val set2sqlsString        : Set[String] => Set[String]         = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  //  private lazy val set2sqlsInt           : Set[Int] => Set[String]            = (set: Set[Int]) => set.map(_.toString)
  //  private lazy val set2sqlsLong          : Set[Long] => Set[String]           = (set: Set[Long]) => set.map(_.toString)
  //  private lazy val set2sqlsFloat         : Set[Float] => Set[String]          = (set: Set[Float]) => set.map(_.toString)
  //  private lazy val set2sqlsDouble        : Set[Double] => Set[String]         = (set: Set[Double]) => set.map(_.toString)
  //  private lazy val set2sqlsBoolean       : Set[Boolean] => Set[String]        = (set: Set[Boolean]) => set.map(_.toString)
  //  private lazy val set2sqlsBigInt        : Set[BigInt] => Set[String]         = (set: Set[BigInt]) => set.map(_.toString)
  //  private lazy val set2sqlsBigDecimal    : Set[BigDecimal] => Set[String]     = (set: Set[BigDecimal]) => set.map(_.toString)
  //  private lazy val set2sqlsDate          : Set[Date] => Set[String]           = (set: Set[Date]) => set.map(_.getTime.toString)
  //  private lazy val set2sqlsDuration      : Set[Duration] => Set[String]       = (set: Set[Duration]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsInstant       : Set[Instant] => Set[String]        = (set: Set[Instant]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalDate     : Set[LocalDate] => Set[String]      = (set: Set[LocalDate]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalTime     : Set[LocalTime] => Set[String]      = (set: Set[LocalTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalDateTime : Set[LocalDateTime] => Set[String]  = (set: Set[LocalDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsOffsetTime    : Set[OffsetTime] => Set[String]     = (set: Set[OffsetTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsOffsetDateTime: Set[OffsetDateTime] => Set[String] = (set: Set[OffsetDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsZonedDateTime : Set[ZonedDateTime] => Set[String]  = (set: Set[ZonedDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsUUID          : Set[UUID] => Set[String]           = (set: Set[UUID]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsURI           : Set[URI] => Set[String]            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).map(v => s"'$v'")
  //  private lazy val set2sqlsByte          : Set[Byte] => Set[String]           = (set: Set[Byte]) => set.map(_.toString)
  //  private lazy val set2sqlsShort         : Set[Short] => Set[String]          = (set: Set[Short]) => set.map(_.toString)
  //  private lazy val set2sqlsChar          : Set[Char] => Set[String]           = (set: Set[Char]) => set.map(v => s"'$v'")
  //
  //
  //  private def sqlNestedArrays2optCoalescedSet[T](row: RS, paramIndex: Int, j2s: Any => T): Option[Set[T]] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      Option.empty[Set[T]]
  //    } else {
  //      val outerArrayResultSet = array.getResultSet
  //      var set                 = Set.empty[T]
  //      while (outerArrayResultSet.next()) {
  //        val outerArray = outerArrayResultSet.getArray(2)
  //        // Allow null/None
  //        if (outerArray.getUnderlyingArray != null) {
  //          outerArray.getArray.asInstanceOf[Array[_]].foreach { value =>
  //            set += j2s(value)
  //          }
  //        }
  //      }
  //      if (set.nonEmpty) Some(set) else Option.empty[Set[T]]
  //    }
  //  }
  //
  //  private lazy val nestedArray2optCoalescedSetId            : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Id)
  //  private lazy val nestedArray2optCoalescedSetString        : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String)
  //  private lazy val nestedArray2optCoalescedSetInt           : (RS, Int) => Option[Set[Int]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Int)
  //  private lazy val nestedArray2optCoalescedSetLong          : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Long)
  //  private lazy val nestedArray2optCoalescedSetFloat         : (RS, Int) => Option[Set[Float]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Float)
  //  private lazy val nestedArray2optCoalescedSetDouble        : (RS, Int) => Option[Set[Double]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Double)
  //  private lazy val nestedArray2optCoalescedSetBoolean       : (RS, Int) => Option[Set[Boolean]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Boolean)
  //  private lazy val nestedArray2optCoalescedSetBigInt        : (RS, Int) => Option[Set[BigInt]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigInt)
  //  private lazy val nestedArray2optCoalescedSetBigDecimal    : (RS, Int) => Option[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigDecimal)
  //  private lazy val nestedArray2optCoalescedSetDate          : (RS, Int) => Option[Set[Date]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Date)
  //  private lazy val nestedArray2optCoalescedSetDuration      : (RS, Int) => Option[Set[Duration]]       = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Duration)
  //  private lazy val nestedArray2optCoalescedSetInstant       : (RS, Int) => Option[Set[Instant]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Instant)
  //  private lazy val nestedArray2optCoalescedSetLocalDate     : (RS, Int) => Option[Set[LocalDate]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDate)
  //  private lazy val nestedArray2optCoalescedSetLocalTime     : (RS, Int) => Option[Set[LocalTime]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalTime)
  //  private lazy val nestedArray2optCoalescedSetLocalDateTime : (RS, Int) => Option[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDateTime)
  //  private lazy val nestedArray2optCoalescedSetOffsetTime    : (RS, Int) => Option[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetTime)
  //  private lazy val nestedArray2optCoalescedSetOffsetDateTime: (RS, Int) => Option[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetDateTime)
  //  private lazy val nestedArray2optCoalescedSetZonedDateTime : (RS, Int) => Option[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2ZonedDateTime)
  //  private lazy val nestedArray2optCoalescedSetUUID          : (RS, Int) => Option[Set[UUID]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2UUID)
  //  private lazy val nestedArray2optCoalescedSetURI           : (RS, Int) => Option[Set[URI]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String).map(_.map(v => new URI(v)))
  //  private lazy val nestedArray2optCoalescedSetByte          : (RS, Int) => Option[Set[Byte]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Byte)
  //  private lazy val nestedArray2optCoalescedSetShort         : (RS, Int) => Option[Set[Short]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Short)
  //  private lazy val nestedArray2optCoalescedSetChar          : (RS, Int) => Option[Set[Char]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Char)
  //
  //
  //  private def array2optSet[T](row: RS, paramIndex: Int, j2s: Any => T): Option[Set[T]] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      Option.empty[Set[T]]
  //    } else {
  //      var set = Set.empty[T]
  //      array.getArray.asInstanceOf[Array[_]].foreach { value =>
  //        set += j2s(value)
  //      }
  //      if (set.nonEmpty) Some(set) else Option.empty[Set[T]]
  //    }
  //  }
  //
  //  private lazy val array2optSetId            : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Id)
  //  private lazy val array2optSetString        : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2String)
  //  private lazy val array2optSetInt           : (RS, Int) => Option[Set[Int]]            = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Int)
  //  private lazy val array2optSetLong          : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Long)
  //  private lazy val array2optSetFloat         : (RS, Int) => Option[Set[Float]]          = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Float)
  //  private lazy val array2optSetDouble        : (RS, Int) => Option[Set[Double]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Double)
  //  private lazy val array2optSetBoolean       : (RS, Int) => Option[Set[Boolean]]        = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Boolean)
  //  private lazy val array2optSetBigInt        : (RS, Int) => Option[Set[BigInt]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2BigInt)
  //  private lazy val array2optSetBigDecimal    : (RS, Int) => Option[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2BigDecimal)
  //  private lazy val array2optSetDate          : (RS, Int) => Option[Set[Date]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Date)
  //  private lazy val array2optSetDuration      : (RS, Int) => Option[Set[Duration]]       = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Duration)
  //  private lazy val array2optSetInstant       : (RS, Int) => Option[Set[Instant]]        = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Instant)
  //  private lazy val array2optSetLocalDate     : (RS, Int) => Option[Set[LocalDate]]      = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalDate)
  //  private lazy val array2optSetLocalTime     : (RS, Int) => Option[Set[LocalTime]]      = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalTime)
  //  private lazy val array2optSetLocalDateTime : (RS, Int) => Option[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalDateTime)
  //  private lazy val array2optSetOffsetTime    : (RS, Int) => Option[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2OffsetTime)
  //  private lazy val array2optSetOffsetDateTime: (RS, Int) => Option[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2OffsetDateTime)
  //  private lazy val array2optSetZonedDateTime : (RS, Int) => Option[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2ZonedDateTime)
  //  private lazy val array2optSetUUID          : (RS, Int) => Option[Set[UUID]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2UUID)
  //  private lazy val array2optSetURI           : (RS, Int) => Option[Set[URI]]            = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2String).map(_.map(v => new URI(v)))
  //  private lazy val array2optSetByte          : (RS, Int) => Option[Set[Byte]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Byte)
  //  private lazy val array2optSetShort         : (RS, Int) => Option[Set[Short]]          = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Short)
  //  private lazy val array2optSetChar          : (RS, Int) => Option[Set[Char]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Char)
  //
  //  private def sqlNestedArrays2set[T](row: RS, paramIndex: Int, j2s: Any => T): Set[T] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      Set.empty[T]
  //    } else {
  //      val outerArrayResultSet = array.getResultSet
  //      var set                 = Set.empty[T]
  //      while (outerArrayResultSet.next()) {
  //        val array0 = outerArrayResultSet.getArray(2)
  //        // Account for empty Sets
  //        if (array0.getUnderlyingArray != null) {
  //          array0.getArray.asInstanceOf[Array[_]].foreach { value =>
  //            set += j2s(value)
  //          }
  //        }
  //      }
  //      set
  //    }
  //  }
  //
  //  private lazy val nestedArray2coalescedSetId            : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id)
  //  private lazy val nestedArray2coalescedSetString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String)
  //  private lazy val nestedArray2coalescedSetInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int)
  //  private lazy val nestedArray2coalescedSetLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long)
  //  private lazy val nestedArray2coalescedSetFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float)
  //  private lazy val nestedArray2coalescedSetDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double)
  //  private lazy val nestedArray2coalescedSetBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean)
  //  private lazy val nestedArray2coalescedSetBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt)
  //  private lazy val nestedArray2coalescedSetBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal)
  //  private lazy val nestedArray2coalescedSetDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date)
  //  private lazy val nestedArray2coalescedSetDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration)
  //  private lazy val nestedArray2coalescedSetInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant)
  //  private lazy val nestedArray2coalescedSetLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate)
  //  private lazy val nestedArray2coalescedSetLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime)
  //  private lazy val nestedArray2coalescedSetLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime)
  //  private lazy val nestedArray2coalescedSetOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime)
  //  private lazy val nestedArray2coalescedSetOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime)
  //  private lazy val nestedArray2coalescedSetZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime)
  //  private lazy val nestedArray2coalescedSetUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID)
  //  private lazy val nestedArray2coalescedSetURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).map(v => new URI(v))
  //  private lazy val nestedArray2coalescedSetByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte)
  //  private lazy val nestedArray2coalescedSetShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short)
  //  private lazy val nestedArray2coalescedSetChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char)
  //
  //
  //  private lazy val nestedArray2setAscId            : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  //  private lazy val nestedArray2setAscByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char).toList.sorted.take(size).toSet
  //
  //  private lazy val nestedArray2setDescId            : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  //  private lazy val nestedArray2setDescByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet
  //
  //
  //  private def sqlArrays2set[T](row: RS, paramIndex: Int, j2s: Any => T): Set[T] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      Set.empty[T]
  //    } else {
  //      var set = Set.empty[T]
  //      array.getArray.asInstanceOf[Array[_]].foreach { value =>
  //        set += j2s(value)
  //      }
  //      set
  //    }
  //  }
  //
  //  private lazy val sqlArray2minNId            : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  //  private lazy val sqlArray2minNByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  //  private lazy val sqlArray2minNChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.take(size).toSet
  //
  //  private lazy val sqlArray2maxNId            : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  //  private lazy val sqlArray2maxNByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  //  private lazy val sqlArray2maxNChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet
  //
  //  private def onlyNumbers = throw new Exception("Casting only for numbers.")
  //
  //  private def sqlNestedArrays2sum[T](row: RS, paramIndex: Int, j2s: Any => T): List[T] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      List.empty[T]
  //    } else {
  //      val outerArrayResultSet = array.getResultSet
  //      val list                = ListBuffer.empty[T]
  //      while (outerArrayResultSet.next()) {
  //        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
  //          list += j2s(value)
  //        }
  //      }
  //      list.toList
  //    }
  //  }
  //  private lazy val nestedArray2sumId            : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Int](row, paramIndex, j2Int).sum)
  //  private lazy val nestedArray2sumLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Long](row, paramIndex, j2Long).sum)
  //  private lazy val nestedArray2sumFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Float](row, paramIndex, j2Float).sum)
  //  private lazy val nestedArray2sumDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Double](row, paramIndex, j2Double).sum)
  //  private lazy val nestedArray2sumBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[BigInt](row, paramIndex, j2BigInt).sum)
  //  private lazy val nestedArray2sumBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[BigDecimal](row, paramIndex, j2BigDecimal).sum)
  //  private lazy val nestedArray2sumDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2sumByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Byte](row, paramIndex, j2Byte).sum)
  //  private lazy val nestedArray2sumShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Short](row, paramIndex, j2Short).sum)
  //  private lazy val nestedArray2sumChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Char](row, paramIndex, j2Char).sum)
  //
  //  private def sqlArray2sum[T](row: RS, paramIndex: Int, value: RS => T): List[T] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      List.empty[T]
  //    } else {
  //      val resultSet = array.getResultSet
  //      val list      = ListBuffer.empty[T]
  //      while (resultSet.next()) {
  //        list += value(row)
  //      }
  //      list.toList
  //    }
  //  }
  //  private lazy val sqlArray2sumId            : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Int](row, paramIndex, valueInt).sum)
  //  private lazy val sqlArray2sumLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Long](row, paramIndex, valueLong).sum)
  //  private lazy val sqlArray2sumFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Float](row, paramIndex, valueFloat).sum)
  //  private lazy val sqlArray2sumDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Double](row, paramIndex, valueDouble).sum)
  //  private lazy val sqlArray2sumBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(sqlArray2sum[BigInt](row, paramIndex, valueBigInt).sum)
  //  private lazy val sqlArray2sumBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(sqlArray2sum[BigDecimal](row, paramIndex, valueBigDecimal).sum)
  //  private lazy val sqlArray2sumDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => onlyNumbers
  //  private lazy val sqlArray2sumByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Byte](row, paramIndex, valueByte).sum)
  //  private lazy val sqlArray2sumShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Short](row, paramIndex, valueShort).sum)
  //  private lazy val sqlArray2sumChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Char](row, paramIndex, valueChar).sum)
  //
  //  private lazy val stringArray2sumId            : Array[String] => String         = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumString        : Array[String] => String         = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumInt           : Array[String] => Int            = (vs: Array[String]) => vs.map(_.toInt).sum
  //  private lazy val stringArray2sumLong          : Array[String] => Long           = (vs: Array[String]) => vs.map(_.toLong).sum
  //  private lazy val stringArray2sumFloat         : Array[String] => Float          = (vs: Array[String]) => vs.map(_.toFloat).sum
  //  private lazy val stringArray2sumDouble        : Array[String] => Double         = (vs: Array[String]) => vs.map(_.toDouble).sum
  //  private lazy val stringArray2sumBoolean       : Array[String] => Boolean        = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumBigInt        : Array[String] => BigInt         = (vs: Array[String]) => vs.map(BigInt(_)).sum
  //  private lazy val stringArray2sumBigDecimal    : Array[String] => BigDecimal     = (vs: Array[String]) => vs.map(BigDecimal(_)).sum
  //  private lazy val stringArray2sumDate          : Array[String] => Date           = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumDuration      : Array[String] => Duration       = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumInstant       : Array[String] => Instant        = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumLocalDate     : Array[String] => LocalDate      = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumLocalTime     : Array[String] => LocalTime      = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumLocalDateTime : Array[String] => LocalDateTime  = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumOffsetTime    : Array[String] => OffsetTime     = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumOffsetDateTime: Array[String] => OffsetDateTime = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumZonedDateTime : Array[String] => ZonedDateTime  = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumUUID          : Array[String] => UUID           = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumURI           : Array[String] => URI            = (vs: Array[String]) => onlyNumbers
  //  private lazy val stringArray2sumByte          : Array[String] => Byte           = (vs: Array[String]) => vs.map(_.toByte).sum
  //  private lazy val stringArray2sumShort         : Array[String] => Short          = (vs: Array[String]) => vs.map(_.toShort).sum
  //  private lazy val stringArray2sumChar          : Array[String] => Char           = (vs: Array[String]) => vs.map(_.charAt(0)).sum
  //
  //
  //  private lazy val j2Id            : Any => String         = (v: Any) => v.asInstanceOf[Long].toString
  //  private lazy val j2String        : Any => String         = (v: Any) => v.asInstanceOf[String]
  //  private lazy val j2Int           : Any => Int            = (v: Any) => v.toString.toInt
  //  private lazy val j2Long          : Any => Long           = (v: Any) => v.asInstanceOf[Long]
  //  private lazy val j2Float         : Any => Float          = {
  //    case v: Float       => v
  //    case v: jBigDecimal => v.toString.toFloat
  //  }
  //  private lazy val j2Double        : Any => Double         = (v: Any) => v.asInstanceOf[Double]
  //  private lazy val j2Boolean       : Any => Boolean        = (v: Any) => v.asInstanceOf[Boolean]
  //  private lazy val j2BigInt        : Any => BigInt         = (v: Any) => BigInt(v.toString)
  //  private lazy val j2BigDecimal    : Any => BigDecimal     = (v: Any) => BigDecimal(v.toString)
  //  private lazy val j2Date          : Any => Date           = (v: Any) => new Date(v.asInstanceOf[Long])
  //  private lazy val j2Duration      : Any => Duration       = (v: Any) => Duration.parse(v.asInstanceOf[String])
  //  private lazy val j2Instant       : Any => Instant        = (v: Any) => Instant.parse(v.asInstanceOf[String])
  //  private lazy val j2LocalDate     : Any => LocalDate      = (v: Any) => LocalDate.parse(v.asInstanceOf[String])
  //  private lazy val j2LocalTime     : Any => LocalTime      = (v: Any) => LocalTime.parse(v.asInstanceOf[String])
  //  private lazy val j2LocalDateTime : Any => LocalDateTime  = (v: Any) => LocalDateTime.parse(v.asInstanceOf[String])
  //  private lazy val j2OffsetTime    : Any => OffsetTime     = (v: Any) => OffsetTime.parse(v.asInstanceOf[String])
  //  private lazy val j2OffsetDateTime: Any => OffsetDateTime = (v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])
  //  private lazy val j2ZonedDateTime : Any => ZonedDateTime  = (v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])
  //  private lazy val j2UUID          : Any => UUID           = (v: Any) => v.asInstanceOf[UUID]
  //  private lazy val j2URI           : Any => URI            = (v: Any) => v.asInstanceOf[URI]
  //  private lazy val j2Byte          : Any => Byte           = {
  //    case v: jShort  => v.toByte
  //    case v: Integer => v.toByte
  //  }
  //  private lazy val j2Short         : Any => Short          = {
  //    case v: Integer => v.toShort
  //    case v: jShort  => v.toShort
  //  }
  //  private lazy val j2Char          : Any => Char           = (v: Any) => v.asInstanceOf[String].charAt(0)
  //
  //
  //  private def sqlNestedArrays2nestedSet[T](row: RS, paramIndex: Int, getValue: Any => T): Set[Set[T]] = {
  //    val array = row.getArray(paramIndex)
  //    if (row.wasNull()) {
  //      Set.empty[Set[T]]
  //    } else {
  //      val outerArrayResultSet = array.getResultSet
  //      var sets                = Set.empty[Set[T]]
  //      while (outerArrayResultSet.next()) {
  //        var set = Set.empty[T]
  //        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
  //          set += getValue(value)
  //        }
  //        sets += set
  //      }
  //      sets
  //    }
  //  }
  //
  //  private lazy val nestedArray2nestedSetId            : (RS, Int) => Set[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Id)
  //  private lazy val nestedArray2nestedSetString        : (RS, Int) => Set[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2String)
  //  private lazy val nestedArray2nestedSetInt           : (RS, Int) => Set[Set[Int]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Int)
  //  private lazy val nestedArray2nestedSetLong          : (RS, Int) => Set[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Long)
  //  private lazy val nestedArray2nestedSetFloat         : (RS, Int) => Set[Set[Float]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Float)
  //  private lazy val nestedArray2nestedSetDouble        : (RS, Int) => Set[Set[Double]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Double)
  //  private lazy val nestedArray2nestedSetBoolean       : (RS, Int) => Set[Set[Boolean]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Boolean)
  //  private lazy val nestedArray2nestedSetBigInt        : (RS, Int) => Set[Set[BigInt]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2BigInt)
  //  private lazy val nestedArray2nestedSetBigDecimal    : (RS, Int) => Set[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2BigDecimal)
  //  private lazy val nestedArray2nestedSetDate          : (RS, Int) => Set[Set[Date]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Date)
  //  private lazy val nestedArray2nestedSetDuration      : (RS, Int) => Set[Set[Duration]]       = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Duration)
  //  private lazy val nestedArray2nestedSetInstant       : (RS, Int) => Set[Set[Instant]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Instant)
  //  private lazy val nestedArray2nestedSetLocalDate     : (RS, Int) => Set[Set[LocalDate]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalDate)
  //  private lazy val nestedArray2nestedSetLocalTime     : (RS, Int) => Set[Set[LocalTime]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalTime)
  //  private lazy val nestedArray2nestedSetLocalDateTime : (RS, Int) => Set[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalDateTime)
  //  private lazy val nestedArray2nestedSetOffsetTime    : (RS, Int) => Set[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2OffsetTime)
  //  private lazy val nestedArray2nestedSetOffsetDateTime: (RS, Int) => Set[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2OffsetDateTime)
  //  private lazy val nestedArray2nestedSetZonedDateTime : (RS, Int) => Set[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2ZonedDateTime)
  //  private lazy val nestedArray2nestedSetUUID          : (RS, Int) => Set[Set[UUID]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2UUID)
  //  private lazy val nestedArray2nestedSetURI           : (RS, Int) => Set[Set[URI]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, (v: Any) => new URI(j2String(v)))
  //  private lazy val nestedArray2nestedSetByte          : (RS, Int) => Set[Set[Byte]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Byte)
  //  private lazy val nestedArray2nestedSetShort         : (RS, Int) => Set[Set[Short]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Short)
  //  private lazy val nestedArray2nestedSetChar          : (RS, Int) => Set[Set[Char]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Char)
  //
  //
  //  private lazy val array2setFirstId            : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Id).min)
  //  private lazy val array2setFirstString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)
  //  private lazy val array2setFirstInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).min)
  //  private lazy val array2setFirstLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).min)
  //  private lazy val array2setFirstFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).min)
  //  private lazy val array2setFirstDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).min)
  //  private lazy val array2setFirstBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).min)
  //  private lazy val array2setFirstBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).min)
  //  private lazy val array2setFirstBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).min)
  //  private lazy val array2setFirstDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).min)
  //  private lazy val array2setFirstDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).min)
  //  private lazy val array2setFirstInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).min)
  //  private lazy val array2setFirstLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).min)
  //  private lazy val array2setFirstLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).min)
  //  private lazy val array2setFirstLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).min)
  //  private lazy val array2setFirstOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).min)
  //  private lazy val array2setFirstOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).min)
  //  private lazy val array2setFirstZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).min)
  //  private lazy val array2setFirstUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).min)
  //  private lazy val array2setFirstURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)) // URI saved as String
  //  private lazy val array2setFirstByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).min)
  //  private lazy val array2setFirstShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).min)
  //  private lazy val array2setFirstChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).min)
  //
  //  private lazy val array2setLastId            : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Id).max)
  //  private lazy val array2setLastString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)
  //  private lazy val array2setLastInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).max)
  //  private lazy val array2setLastLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).max)
  //  private lazy val array2setLastFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).max)
  //  private lazy val array2setLastDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).max)
  //  private lazy val array2setLastBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).max)
  //  private lazy val array2setLastBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).max)
  //  private lazy val array2setLastBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).max)
  //  private lazy val array2setLastDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).max)
  //  private lazy val array2setLastDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).max)
  //  private lazy val array2setLastInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).max)
  //  private lazy val array2setLastLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).max)
  //  private lazy val array2setLastLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).max)
  //  private lazy val array2setLastLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).max)
  //  private lazy val array2setLastOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).max)
  //  private lazy val array2setLastOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).max)
  //  private lazy val array2setLastZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).max)
  //  private lazy val array2setLastUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).max)
  //  private lazy val array2setLastURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)) // URI saved as String
  //  private lazy val array2setLastByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).max)
  //  private lazy val array2setLastShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).max)
  //  private lazy val array2setLastChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).max)


  case class ResMapOpt[T](
    tpe: String,
    sql2optMap: (RS, ParamIndex) => Option[Map[String, T]],

    //    set2sqlArray: Set[T] => String,
    //    set2sqls: Set[T] => Set[String],
    //    one2sql: T => String,
    //    one2json: T => String
  )

  lazy val resOptMapId            : ResMapOpt[String]         = ResMapOpt("String", sql2optMapId) //, set2sqlArrayId, set2sqlsId, one2sqlId, one2jsonId)
  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", sql2optMapString) //, set2sqlArrayString, set2sqlsString, one2sqlString, one2jsonString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", sql2optMapInt) //, set2sqlArrayInt, set2sqlsInt, one2sqlInt, one2jsonInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", sql2optMapLong) //, set2sqlArrayLong, set2sqlsLong, one2sqlLong, one2jsonLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", sql2optMapFloat) //, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", sql2optMapDouble) //, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", sql2optMapBoolean) //, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", sql2optMapBigInt) //, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", sql2optMapBigDecimal) //, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", sql2optMapDate) //, set2sqlArrayDate, set2sqlsDate, one2sqlDate, one2jsonDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", sql2optMapDuration) //, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", sql2optMapInstant) //, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", sql2optMapLocalDate) //, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", sql2optMapLocalTime) //, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", sql2optMapLocalDateTime) //, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", sql2optMapOffsetTime) //, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", sql2optMapOffsetDateTime) //, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", sql2optMapZonedDateTime) //, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", sql2optMapUUID) //, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", sql2optMapURI) //, set2sqlArrayURI, set2sqlsURI, one2sqlURI, one2jsonURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", sql2optMapByte) //, set2sqlArrayByte, set2sqlsByte, one2sqlByte, one2jsonByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", sql2optMapShort) //, set2sqlArrayShort, set2sqlsShort, one2sqlShort, one2jsonShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", sql2optMapChar) //, set2sqlArrayChar, set2sqlsChar, one2sqlChar, one2jsonChar)


  protected def sql2mapOpt[T](row: RS, paramIndex: Int, json2map: String => Map[String, T]): Option[Map[String, T]] = {
    val byteArray = row.getBytes(paramIndex)
    if (!row.wasNull() && byteArray.nonEmpty)
      Some(json2map(new String(byteArray)))
    else 
      None
  }

  private lazy val sql2optMapId            : (RS, Int) => Option[Map[String, String]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapID)
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
