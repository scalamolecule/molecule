package molecule.sql.core.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import java.math.{BigDecimal => jBigDecimal}
import java.lang.{Short => jShort}
import molecule.core.util.JavaConversions
import scala.collection.mutable.ListBuffer

trait LambdasSet extends LambdasBase with JavaConversions { self: SqlQueryBase =>

  private lazy val sql2setId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueId)
  private lazy val sql2setString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
  private lazy val sql2setInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
  private lazy val sql2setLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
  private lazy val sql2setFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
  private lazy val sql2setDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
  private lazy val sql2setBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
  private lazy val sql2setBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
  private lazy val sql2setBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
  private lazy val sql2setDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
  private lazy val sql2setDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDuration)
  private lazy val sql2setInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInstant)
  private lazy val sql2setLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDate)
  private lazy val sql2setLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalTime)
  private lazy val sql2setLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDateTime)
  private lazy val sql2setOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetTime)
  private lazy val sql2setOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2setZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueZonedDateTime)
  private lazy val sql2setUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
  private lazy val sql2setURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
  private lazy val sql2setByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
  private lazy val sql2setShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
  private lazy val sql2setChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)

  case class ResSet[T](
    tpe: String,
    tpeDb: String,
    sql2set: (Row, ParamIndex) => Set[T],
    sql2setOrNull: (Row, ParamIndex) => Any, // Allow null in optional nested rows
    set2sqlArray: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
    array2set: (Row, ParamIndex) => Set[T],
    nestedArray2coalescedSet: (Row, ParamIndex) => Set[T],
    nestedArray2optCoalescedSet: (Row, ParamIndex) => Option[Set[T]],
    nestedArray2nestedSet: (Row, ParamIndex) => Set[Set[T]],
    array2setFirst: (Row, ParamIndex) => Set[T],
    array2setLast: (Row, ParamIndex) => Set[T],
    nestedArray2setAsc: Int => (Row, ParamIndex) => Set[T],
    nestedArray2setDesc: Int => (Row, ParamIndex) => Set[T],
    nestedArray2sum: (Row, ParamIndex) => Set[T],
    json2tpe: String => T,
    json2array: String => Array[T],
    json2optArray: String => Option[Array[T]],
    one2json: T => String,
    stringArray2sum: Array[String] => T,
    row2tpe: Row => T,
    sqlArray2sum: (Row, ParamIndex) => Set[T],
    sqlArray2minN: Int => (Row, ParamIndex) => Set[T],
    sqlArray2maxN: Int => (Row, ParamIndex) => Set[T],
  )

  lazy val resSetId            : ResSet[String]         = ResSet("String", tpeDbId, sql2setId, null, set2sqlArrayId, set2sqlsId, one2sqlId, array2setId, nestedArray2coalescedSetId, nestedArray2optCoalescedSetId, nestedArray2nestedSetId, array2setFirstId, array2setLastId, nestedArray2setAscId, nestedArray2setDescId, nestedArray2sumId, json2oneId, json2arrayId, json2optArrayId, one2jsonId, stringArray2sumId, valueId, sqlArray2sumId, sqlArray2minNId, sqlArray2maxNId)
  lazy val resSetString        : ResSet[String]         = ResSet("String", tpeDbString, sql2setString, null, set2sqlArrayString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2optCoalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2sumString, json2oneString, json2arrayString, json2optArrayString, one2jsonString, stringArray2sumString, valueString, sqlArray2sumString, sqlArray2minNString, sqlArray2maxNString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", tpeDbInt, sql2setInt, null, set2sqlArrayInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2optCoalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2sumInt, json2oneInt, json2arrayInt, json2optArrayInt, one2jsonInt, stringArray2sumInt, valueInt, sqlArray2sumInt, sqlArray2minNInt, sqlArray2maxNInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", tpeDbLong, sql2setLong, null, set2sqlArrayLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2optCoalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2sumLong, json2oneLong, json2arrayLong, json2optArrayLong, one2jsonLong, stringArray2sumLong, valueLong, sqlArray2sumLong, sqlArray2minNLong, sqlArray2maxNLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", tpeDbFloat, sql2setFloat, null, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2optCoalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2sumFloat, json2oneFloat, json2arrayFloat, json2optArrayFloat, one2jsonFloat, stringArray2sumFloat, valueFloat, sqlArray2sumFloat, sqlArray2minNFloat, sqlArray2maxNFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", tpeDbDouble, sql2setDouble, null, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2optCoalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2sumDouble, json2oneDouble, json2arrayDouble, json2optArrayDouble, one2jsonDouble, stringArray2sumDouble, valueDouble, sqlArray2sumDouble, sqlArray2minNDouble, sqlArray2maxNDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", tpeDbBoolean, sql2setBoolean, null, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2optCoalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2sumBoolean, json2oneBoolean, json2arrayBoolean, json2optArrayBoolean, one2jsonBoolean, stringArray2sumBoolean, valueBoolean, sqlArray2sumBoolean, sqlArray2minNBoolean, sqlArray2maxNBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", tpeDbBigInt, sql2setBigInt, null, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2optCoalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2sumBigInt, json2oneBigInt, json2arrayBigInt, json2optArrayBigInt, one2jsonBigInt, stringArray2sumBigInt, valueBigInt, sqlArray2sumBigInt, sqlArray2minNBigInt, sqlArray2maxNBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", tpeDbBigDecimal, sql2setBigDecimal, null, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2optCoalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2sumBigDecimal, json2oneBigDecimal, json2arrayBigDecimal, json2optArrayBigDecimal, one2jsonBigDecimal, stringArray2sumBigDecimal, valueBigDecimal, sqlArray2sumBigDecimal, sqlArray2minNBigDecimal, sqlArray2maxNBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", tpeDbDate, sql2setDate, null, set2sqlArrayDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2optCoalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2sumDate, json2oneDate, json2arrayDate, json2optArrayDate, one2jsonDate, stringArray2sumDate, valueDate, sqlArray2sumDate, sqlArray2minNDate, sqlArray2maxNDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", tpeDbDuration, sql2setDuration, null, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, array2setDuration, nestedArray2coalescedSetDuration, nestedArray2optCoalescedSetDuration, nestedArray2nestedSetDuration, array2setFirstDuration, array2setLastDuration, nestedArray2setAscDuration, nestedArray2setDescDuration, nestedArray2sumDuration, json2oneDuration, json2arrayDuration, json2optArrayDuration, one2jsonDuration, stringArray2sumDuration, valueDuration, sqlArray2sumDuration, sqlArray2minNDuration, sqlArray2maxNDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", tpeDbInstant, sql2setInstant, null, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, array2setInstant, nestedArray2coalescedSetInstant, nestedArray2optCoalescedSetInstant, nestedArray2nestedSetInstant, array2setFirstInstant, array2setLastInstant, nestedArray2setAscInstant, nestedArray2setDescInstant, nestedArray2sumInstant, json2oneInstant, json2arrayInstant, json2optArrayInstant, one2jsonInstant, stringArray2sumInstant, valueInstant, sqlArray2sumInstant, sqlArray2minNInstant, sqlArray2maxNInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", tpeDbLocalDate, sql2setLocalDate, null, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, array2setLocalDate, nestedArray2coalescedSetLocalDate, nestedArray2optCoalescedSetLocalDate, nestedArray2nestedSetLocalDate, array2setFirstLocalDate, array2setLastLocalDate, nestedArray2setAscLocalDate, nestedArray2setDescLocalDate, nestedArray2sumLocalDate, json2oneLocalDate, json2arrayLocalDate, json2optArrayLocalDate, one2jsonLocalDate, stringArray2sumLocalDate, valueLocalDate, sqlArray2sumLocalDate, sqlArray2minNLocalDate, sqlArray2maxNLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", tpeDbLocalTime, sql2setLocalTime, null, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, array2setLocalTime, nestedArray2coalescedSetLocalTime, nestedArray2optCoalescedSetLocalTime, nestedArray2nestedSetLocalTime, array2setFirstLocalTime, array2setLastLocalTime, nestedArray2setAscLocalTime, nestedArray2setDescLocalTime, nestedArray2sumLocalTime, json2oneLocalTime, json2arrayLocalTime, json2optArrayLocalTime, one2jsonLocalTime, stringArray2sumLocalTime, valueLocalTime, sqlArray2sumLocalTime, sqlArray2minNLocalTime, sqlArray2maxNLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", tpeDbLocalDateTime, sql2setLocalDateTime, null, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, array2setLocalDateTime, nestedArray2coalescedSetLocalDateTime, nestedArray2optCoalescedSetLocalDateTime, nestedArray2nestedSetLocalDateTime, array2setFirstLocalDateTime, array2setLastLocalDateTime, nestedArray2setAscLocalDateTime, nestedArray2setDescLocalDateTime, nestedArray2sumLocalDateTime, json2oneLocalDateTime, json2arrayLocalDateTime, json2optArrayLocalDateTime, one2jsonLocalDateTime, stringArray2sumLocalDateTime, valueLocalDateTime, sqlArray2sumLocalDateTime, sqlArray2minNLocalDateTime, sqlArray2maxNLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", tpeDbOffsetTime, sql2setOffsetTime, null, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, array2setOffsetTime, nestedArray2coalescedSetOffsetTime, nestedArray2optCoalescedSetOffsetTime, nestedArray2nestedSetOffsetTime, array2setFirstOffsetTime, array2setLastOffsetTime, nestedArray2setAscOffsetTime, nestedArray2setDescOffsetTime, nestedArray2sumOffsetTime, json2oneOffsetTime, json2arrayOffsetTime, json2optArrayOffsetTime, one2jsonOffsetTime, stringArray2sumOffsetTime, valueOffsetTime, sqlArray2sumOffsetTime, sqlArray2minNOffsetTime, sqlArray2maxNOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", tpeDbOffsetDateTime, sql2setOffsetDateTime, null, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, array2setOffsetDateTime, nestedArray2coalescedSetOffsetDateTime, nestedArray2optCoalescedSetOffsetDateTime, nestedArray2nestedSetOffsetDateTime, array2setFirstOffsetDateTime, array2setLastOffsetDateTime, nestedArray2setAscOffsetDateTime, nestedArray2setDescOffsetDateTime, nestedArray2sumOffsetDateTime, json2oneOffsetDateTime, json2arrayOffsetDateTime, json2optArrayOffsetDateTime, one2jsonOffsetDateTime, stringArray2sumOffsetDateTime, valueOffsetDateTime, sqlArray2sumOffsetDateTime, sqlArray2minNOffsetDateTime, sqlArray2maxNOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", tpeDbZonedDateTime, sql2setZonedDateTime, null, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, array2setZonedDateTime, nestedArray2coalescedSetZonedDateTime, nestedArray2optCoalescedSetZonedDateTime, nestedArray2nestedSetZonedDateTime, array2setFirstZonedDateTime, array2setLastZonedDateTime, nestedArray2setAscZonedDateTime, nestedArray2setDescZonedDateTime, nestedArray2sumZonedDateTime, json2oneZonedDateTime, json2arrayZonedDateTime, json2optArrayZonedDateTime, one2jsonZonedDateTime, stringArray2sumZonedDateTime, valueZonedDateTime, sqlArray2sumZonedDateTime, sqlArray2minNZonedDateTime, sqlArray2maxNZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", tpeDbUUID, sql2setUUID, null, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2optCoalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2sumUUID, json2oneUUID, json2arrayUUID, json2optArrayUUID, one2jsonUUID, stringArray2sumUUID, valueUUID, sqlArray2sumUUID, sqlArray2minNUUID, sqlArray2maxNUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", tpeDbURI, sql2setURI, null, set2sqlArrayURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2optCoalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2sumURI, json2oneURI, json2arrayURI, json2optArrayURI, one2jsonURI, stringArray2sumURI, valueURI, sqlArray2sumURI, sqlArray2minNURI, sqlArray2maxNURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", tpeDbByte, sql2setByte, null, set2sqlArrayByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2optCoalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2sumByte, json2oneByte, json2arrayByte, json2optArrayByte, one2jsonByte, stringArray2sumByte, valueByte, sqlArray2sumByte, sqlArray2minNByte, sqlArray2maxNByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", tpeDbShort, sql2setShort, null, set2sqlArrayShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2optCoalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2sumShort, json2oneShort, json2arrayShort, json2optArrayShort, one2jsonShort, stringArray2sumShort, valueShort, sqlArray2sumShort, sqlArray2minNShort, sqlArray2maxNShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", tpeDbChar, sql2setChar, null, set2sqlArrayChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2optCoalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2sumChar, json2oneChar, json2arrayChar, json2optArrayChar, one2jsonChar, stringArray2sumChar, valueChar, sqlArray2sumChar, sqlArray2minNChar, sqlArray2maxNChar)

  private lazy val set2sqlArrayId            : Set[String] => String         = (set: Set[String]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val set2sqlArrayString        : Set[String] => String         = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY['", "', '", "']::text[]")
  private lazy val set2sqlArrayInt           : Set[Int] => String            = (set: Set[Int]) => set.mkString("ARRAY[", ", ", "]::integer[]")
  private lazy val set2sqlArrayLong          : Set[Long] => String           = (set: Set[Long]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val set2sqlArrayFloat         : Set[Float] => String          = (set: Set[Float]) => set.mkString("ARRAY[", ", ", "]::decimal[]")
  private lazy val set2sqlArrayDouble        : Set[Double] => String         = (set: Set[Double]) => set.mkString("ARRAY[", ", ", "]::double precision[]")
  private lazy val set2sqlArrayBoolean       : Set[Boolean] => String        = (set: Set[Boolean]) => set.mkString("ARRAY[", ", ", "]::boolean[]")
  private lazy val set2sqlArrayBigInt        : Set[BigInt] => String         = (set: Set[BigInt]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  private lazy val set2sqlArrayBigDecimal    : Set[BigDecimal] => String     = (set: Set[BigDecimal]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  private lazy val set2sqlArrayDate          : Set[Date] => String           = (set: Set[Date]) => set.map(_.getTime).mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val set2sqlArrayDuration      : Set[Duration] => String       = (set: Set[Duration]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayInstant       : Set[Instant] => String        = (set: Set[Instant]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayLocalDate     : Set[LocalDate] => String      = (set: Set[LocalDate]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayLocalTime     : Set[LocalTime] => String      = (set: Set[LocalTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayLocalDateTime : Set[LocalDateTime] => String  = (set: Set[LocalDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayOffsetTime    : Set[OffsetTime] => String     = (set: Set[OffsetTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayOffsetDateTime: Set[OffsetDateTime] => String = (set: Set[OffsetDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayZonedDateTime : Set[ZonedDateTime] => String  = (set: Set[ZonedDateTime]) => set.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayUUID          : Set[UUID] => String           = (set: Set[UUID]) => set.mkString("ARRAY['", "', '", "']::uuid[]")
  private lazy val set2sqlArrayURI           : Set[URI] => String            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val set2sqlArrayByte          : Set[Byte] => String           = (set: Set[Byte]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  private lazy val set2sqlArrayShort         : Set[Short] => String          = (set: Set[Short]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  private lazy val set2sqlArrayChar          : Set[Char] => String           = (set: Set[Char]) => set.mkString("ARRAY['", "', '", "']::char[]")

  private lazy val set2sqlsId            : Set[String] => Set[String]         = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  private lazy val set2sqlsString        : Set[String] => Set[String]         = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  private lazy val set2sqlsInt           : Set[Int] => Set[String]            = (set: Set[Int]) => set.map(_.toString)
  private lazy val set2sqlsLong          : Set[Long] => Set[String]           = (set: Set[Long]) => set.map(_.toString)
  private lazy val set2sqlsFloat         : Set[Float] => Set[String]          = (set: Set[Float]) => set.map(_.toString)
  private lazy val set2sqlsDouble        : Set[Double] => Set[String]         = (set: Set[Double]) => set.map(_.toString)
  private lazy val set2sqlsBoolean       : Set[Boolean] => Set[String]        = (set: Set[Boolean]) => set.map(_.toString)
  private lazy val set2sqlsBigInt        : Set[BigInt] => Set[String]         = (set: Set[BigInt]) => set.map(_.toString)
  private lazy val set2sqlsBigDecimal    : Set[BigDecimal] => Set[String]     = (set: Set[BigDecimal]) => set.map(_.toString)
  private lazy val set2sqlsDate          : Set[Date] => Set[String]           = (set: Set[Date]) => set.map(_.getTime.toString)
  private lazy val set2sqlsDuration      : Set[Duration] => Set[String]       = (set: Set[Duration]) => set.map(v => s"'$v'")
  private lazy val set2sqlsInstant       : Set[Instant] => Set[String]        = (set: Set[Instant]) => set.map(v => s"'$v'")
  private lazy val set2sqlsLocalDate     : Set[LocalDate] => Set[String]      = (set: Set[LocalDate]) => set.map(v => s"'$v'")
  private lazy val set2sqlsLocalTime     : Set[LocalTime] => Set[String]      = (set: Set[LocalTime]) => set.map(v => s"'$v'")
  private lazy val set2sqlsLocalDateTime : Set[LocalDateTime] => Set[String]  = (set: Set[LocalDateTime]) => set.map(v => s"'$v'")
  private lazy val set2sqlsOffsetTime    : Set[OffsetTime] => Set[String]     = (set: Set[OffsetTime]) => set.map(v => s"'$v'")
  private lazy val set2sqlsOffsetDateTime: Set[OffsetDateTime] => Set[String] = (set: Set[OffsetDateTime]) => set.map(v => s"'$v'")
  private lazy val set2sqlsZonedDateTime : Set[ZonedDateTime] => Set[String]  = (set: Set[ZonedDateTime]) => set.map(v => s"'$v'")
  private lazy val set2sqlsUUID          : Set[UUID] => Set[String]           = (set: Set[UUID]) => set.map(v => s"'$v'")
  private lazy val set2sqlsURI           : Set[URI] => Set[String]            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).map(v => s"'$v'")
  private lazy val set2sqlsByte          : Set[Byte] => Set[String]           = (set: Set[Byte]) => set.map(_.toString)
  private lazy val set2sqlsShort         : Set[Short] => Set[String]          = (set: Set[Short]) => set.map(_.toString)
  private lazy val set2sqlsChar          : Set[Char] => Set[String]           = (set: Set[Char]) => set.map(v => s"'$v'")


  protected def sqlNestedArrays2optCoalescedSet[T](row: Row, paramIndex: Int, j2s: Any => T): Option[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Option.empty[Set[T]]
    } else {
      val outerArrayResultSet = array.getResultSet
      var set                 = Set.empty[T]
      while (outerArrayResultSet.next()) {
        val outerArray = outerArrayResultSet.getArray(2)
        // Allow null/None
        if (outerArray.getUnderlyingArray != null) {
          outerArray.getArray.asInstanceOf[Array[_]].foreach { value =>
            set += j2s(value)
          }
        }
      }
      if (set.nonEmpty) Some(set) else Option.empty[Set[T]]
    }
  }

  private lazy val nestedArray2optCoalescedSetId            : (Row, Int) => Option[Set[String]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Id)
  private lazy val nestedArray2optCoalescedSetString        : (Row, Int) => Option[Set[String]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String)
  private lazy val nestedArray2optCoalescedSetInt           : (Row, Int) => Option[Set[Int]]            = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Int)
  private lazy val nestedArray2optCoalescedSetLong          : (Row, Int) => Option[Set[Long]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Long)
  private lazy val nestedArray2optCoalescedSetFloat         : (Row, Int) => Option[Set[Float]]          = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Float)
  private lazy val nestedArray2optCoalescedSetDouble        : (Row, Int) => Option[Set[Double]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Double)
  private lazy val nestedArray2optCoalescedSetBoolean       : (Row, Int) => Option[Set[Boolean]]        = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Boolean)
  private lazy val nestedArray2optCoalescedSetBigInt        : (Row, Int) => Option[Set[BigInt]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigInt)
  private lazy val nestedArray2optCoalescedSetBigDecimal    : (Row, Int) => Option[Set[BigDecimal]]     = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2optCoalescedSetDate          : (Row, Int) => Option[Set[Date]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Date)
  private lazy val nestedArray2optCoalescedSetDuration      : (Row, Int) => Option[Set[Duration]]       = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Duration)
  private lazy val nestedArray2optCoalescedSetInstant       : (Row, Int) => Option[Set[Instant]]        = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Instant)
  private lazy val nestedArray2optCoalescedSetLocalDate     : (Row, Int) => Option[Set[LocalDate]]      = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2optCoalescedSetLocalTime     : (Row, Int) => Option[Set[LocalTime]]      = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2optCoalescedSetLocalDateTime : (Row, Int) => Option[Set[LocalDateTime]]  = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2optCoalescedSetOffsetTime    : (Row, Int) => Option[Set[OffsetTime]]     = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2optCoalescedSetOffsetDateTime: (Row, Int) => Option[Set[OffsetDateTime]] = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2optCoalescedSetZonedDateTime : (Row, Int) => Option[Set[ZonedDateTime]]  = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2optCoalescedSetUUID          : (Row, Int) => Option[Set[UUID]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2UUID)
  private lazy val nestedArray2optCoalescedSetURI           : (Row, Int) => Option[Set[URI]]            = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String).map(_.map(v => new URI(v)))
  private lazy val nestedArray2optCoalescedSetByte          : (Row, Int) => Option[Set[Byte]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Byte)
  private lazy val nestedArray2optCoalescedSetShort         : (Row, Int) => Option[Set[Short]]          = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Short)
  private lazy val nestedArray2optCoalescedSetChar          : (Row, Int) => Option[Set[Char]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Char)

  protected def sqlNestedArrays2set[T](row: Row, paramIndex: Int, j2s: Any => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      var set                 = Set.empty[T]
      while (outerArrayResultSet.next()) {
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }

  private lazy val nestedArray2coalescedSetId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id)
  private lazy val nestedArray2coalescedSetString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String)
  private lazy val nestedArray2coalescedSetInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int)
  private lazy val nestedArray2coalescedSetLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long)
  private lazy val nestedArray2coalescedSetFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float)
  private lazy val nestedArray2coalescedSetDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double)
  private lazy val nestedArray2coalescedSetBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean)
  private lazy val nestedArray2coalescedSetBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt)
  private lazy val nestedArray2coalescedSetBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2coalescedSetDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date)
  private lazy val nestedArray2coalescedSetDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration)
  private lazy val nestedArray2coalescedSetInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant)
  private lazy val nestedArray2coalescedSetLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2coalescedSetLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2coalescedSetLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2coalescedSetOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2coalescedSetOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2coalescedSetZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2coalescedSetUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID)
  private lazy val nestedArray2coalescedSetURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).map(v => new URI(v))
  private lazy val nestedArray2coalescedSetByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte)
  private lazy val nestedArray2coalescedSetShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short)
  private lazy val nestedArray2coalescedSetChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char)


  private lazy val nestedArray2setAscId            : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  private lazy val nestedArray2setAscByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  private lazy val nestedArray2setAscChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char).toList.sorted.take(size).toSet

  private lazy val nestedArray2setDescId            : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  private lazy val nestedArray2setDescByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  private lazy val nestedArray2setDescChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet


  protected def sqlArrays2set[T](row: Row, paramIndex: Int, j2s: Any => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      //      val outerArrayResultSet = array.getResultSet
      var set = Set.empty[T]
      //      while (outerArrayResultSet.next()) {
      //        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
      //          set += j2s(value)
      //        }
      //      }
      array.getArray.asInstanceOf[Array[_]].foreach { value =>
        set += j2s(value)
      }
      set
    }
  }

  private lazy val sqlArray2minNId            : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  private lazy val sqlArray2minNByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.take(size).toSet

  private lazy val sqlArray2maxNId            : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  private lazy val sqlArray2maxNByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet

  protected def onlyNumbers = throw new Exception("Casting only for numbers.")

  protected def sqlNestedArrays2sum[T](row: Row, paramIndex: Int, j2s: Any => T): List[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      List.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      val list                = ListBuffer.empty[T]
      while (outerArrayResultSet.next()) {
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          list += j2s(value)
        }
      }
      list.toList
    }
  }
  private lazy val nestedArray2sumId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Int](row, paramIndex, j2Int).sum)
  private lazy val nestedArray2sumLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Long](row, paramIndex, j2Long).sum)
  private lazy val nestedArray2sumFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Float](row, paramIndex, j2Float).sum)
  private lazy val nestedArray2sumDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Double](row, paramIndex, j2Double).sum)
  private lazy val nestedArray2sumBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[BigInt](row, paramIndex, j2BigInt).sum)
  private lazy val nestedArray2sumBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[BigDecimal](row, paramIndex, j2BigDecimal).sum)
  private lazy val nestedArray2sumDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Byte](row, paramIndex, j2Byte).sum)
  private lazy val nestedArray2sumShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Short](row, paramIndex, j2Short).sum)
  private lazy val nestedArray2sumChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2sum[Char](row, paramIndex, j2Char).sum)

  protected def sqlArray2sum[T](row: Row, paramIndex: Int, value: Row => T): List[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      List.empty[T]
    } else {
      val resultSet = array.getResultSet
      val list      = ListBuffer.empty[T]
      while (resultSet.next()) {
        list += value(row)
      }
      list.toList
    }
  }
  private lazy val sqlArray2sumId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Int](row, paramIndex, valueInt).sum)
  private lazy val sqlArray2sumLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Long](row, paramIndex, valueLong).sum)
  private lazy val sqlArray2sumFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Float](row, paramIndex, valueFloat).sum)
  private lazy val sqlArray2sumDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Double](row, paramIndex, valueDouble).sum)
  private lazy val sqlArray2sumBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => Set(sqlArray2sum[BigInt](row, paramIndex, valueBigInt).sum)
  private lazy val sqlArray2sumBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => Set(sqlArray2sum[BigDecimal](row, paramIndex, valueBigDecimal).sum)
  private lazy val sqlArray2sumDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Byte](row, paramIndex, valueByte).sum)
  private lazy val sqlArray2sumShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Short](row, paramIndex, valueShort).sum)
  private lazy val sqlArray2sumChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => Set(sqlArray2sum[Char](row, paramIndex, valueChar).sum)

  private lazy val stringArray2sumId            : Array[String] => String         = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumString        : Array[String] => String         = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumInt           : Array[String] => Int            = (vs: Array[String]) => vs.map(_.toInt).sum
  private lazy val stringArray2sumLong          : Array[String] => Long           = (vs: Array[String]) => vs.map(_.toLong).sum
  private lazy val stringArray2sumFloat         : Array[String] => Float          = (vs: Array[String]) => vs.map(_.toFloat).sum
  private lazy val stringArray2sumDouble        : Array[String] => Double         = (vs: Array[String]) => vs.map(_.toDouble).sum
  private lazy val stringArray2sumBoolean       : Array[String] => Boolean        = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumBigInt        : Array[String] => BigInt         = (vs: Array[String]) => vs.map(BigInt(_)).sum
  private lazy val stringArray2sumBigDecimal    : Array[String] => BigDecimal     = (vs: Array[String]) => vs.map(BigDecimal(_)).sum
  private lazy val stringArray2sumDate          : Array[String] => Date           = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumDuration      : Array[String] => Duration       = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumInstant       : Array[String] => Instant        = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumLocalDate     : Array[String] => LocalDate      = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumLocalTime     : Array[String] => LocalTime      = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumLocalDateTime : Array[String] => LocalDateTime  = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumOffsetTime    : Array[String] => OffsetTime     = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumOffsetDateTime: Array[String] => OffsetDateTime = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumZonedDateTime : Array[String] => ZonedDateTime  = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumUUID          : Array[String] => UUID           = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumURI           : Array[String] => URI            = (vs: Array[String]) => onlyNumbers
  private lazy val stringArray2sumByte          : Array[String] => Byte           = (vs: Array[String]) => vs.map(_.toByte).sum
  private lazy val stringArray2sumShort         : Array[String] => Short          = (vs: Array[String]) => vs.map(_.toShort).sum
  private lazy val stringArray2sumChar          : Array[String] => Char           = (vs: Array[String]) => vs.map(_.charAt(0)).sum


  private lazy val j2Id            : Any => String         = (v: Any) => v.asInstanceOf[Long].toString
  private lazy val j2String        : Any => String         = (v: Any) => v.asInstanceOf[String]
  private lazy val j2Int           : Any => Int            = (v: Any) => v.toString.toInt
  private lazy val j2Long          : Any => Long           = (v: Any) => v.asInstanceOf[Long]
  private lazy val j2Float         : Any => Float          = {
    case v: Float       => v
    case v: jBigDecimal => v.toString.toFloat
  }
  private lazy val j2Double        : Any => Double         = (v: Any) => v.asInstanceOf[Double]
  private lazy val j2Boolean       : Any => Boolean        = (v: Any) => v.asInstanceOf[Boolean]
  private lazy val j2BigInt        : Any => BigInt         = (v: Any) => BigInt(v.toString)
  private lazy val j2BigDecimal    : Any => BigDecimal     = (v: Any) => BigDecimal(v.toString)
  private lazy val j2Date          : Any => Date           = (v: Any) => new Date(v.asInstanceOf[Long])
  private lazy val j2Duration      : Any => Duration       = (v: Any) => Duration.parse(v.asInstanceOf[String])
  private lazy val j2Instant       : Any => Instant        = (v: Any) => Instant.parse(v.asInstanceOf[String])
  private lazy val j2LocalDate     : Any => LocalDate      = (v: Any) => LocalDate.parse(v.asInstanceOf[String])
  private lazy val j2LocalTime     : Any => LocalTime      = (v: Any) => LocalTime.parse(v.asInstanceOf[String])
  private lazy val j2LocalDateTime : Any => LocalDateTime  = (v: Any) => LocalDateTime.parse(v.asInstanceOf[String])
  private lazy val j2OffsetTime    : Any => OffsetTime     = (v: Any) => OffsetTime.parse(v.asInstanceOf[String])
  private lazy val j2OffsetDateTime: Any => OffsetDateTime = (v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])
  private lazy val j2ZonedDateTime : Any => ZonedDateTime  = (v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])
  private lazy val j2UUID          : Any => UUID           = (v: Any) => v.asInstanceOf[UUID]
  private lazy val j2URI           : Any => URI            = (v: Any) => v.asInstanceOf[URI]
  private lazy val j2Byte          : Any => Byte           = {
    case v: jShort  => v.toByte
    case v: Integer => v.toByte
  }
  private lazy val j2Short         : Any => Short          = {
    case v: Integer => v.toShort
    case v: jShort  => v.toShort
  }
  private lazy val j2Char          : Any => Char           = (v: Any) => v.asInstanceOf[String].charAt(0)


  protected def sqlNestedArrays2nestedSet[T](row: Row, paramIndex: Int, getValue: Any => T): Set[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[Set[T]]
    } else {
      val outerArrayResultSet = array.getResultSet
      var sets                = Set.empty[Set[T]]
      while (outerArrayResultSet.next()) {
        var set = Set.empty[T]
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += getValue(value)
        }
        sets += set
      }
      sets
    }
  }

  private lazy val nestedArray2nestedSetId            : (Row, Int) => Set[Set[String]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Id)
  private lazy val nestedArray2nestedSetString        : (Row, Int) => Set[Set[String]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2String)
  private lazy val nestedArray2nestedSetInt           : (Row, Int) => Set[Set[Int]]            = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Int)
  private lazy val nestedArray2nestedSetLong          : (Row, Int) => Set[Set[Long]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Long)
  private lazy val nestedArray2nestedSetFloat         : (Row, Int) => Set[Set[Float]]          = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Float)
  private lazy val nestedArray2nestedSetDouble        : (Row, Int) => Set[Set[Double]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Double)
  private lazy val nestedArray2nestedSetBoolean       : (Row, Int) => Set[Set[Boolean]]        = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Boolean)
  private lazy val nestedArray2nestedSetBigInt        : (Row, Int) => Set[Set[BigInt]]         = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2BigInt)
  private lazy val nestedArray2nestedSetBigDecimal    : (Row, Int) => Set[Set[BigDecimal]]     = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2nestedSetDate          : (Row, Int) => Set[Set[Date]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Date)
  private lazy val nestedArray2nestedSetDuration      : (Row, Int) => Set[Set[Duration]]       = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Duration)
  private lazy val nestedArray2nestedSetInstant       : (Row, Int) => Set[Set[Instant]]        = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Instant)
  private lazy val nestedArray2nestedSetLocalDate     : (Row, Int) => Set[Set[LocalDate]]      = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2nestedSetLocalTime     : (Row, Int) => Set[Set[LocalTime]]      = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2nestedSetLocalDateTime : (Row, Int) => Set[Set[LocalDateTime]]  = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2nestedSetOffsetTime    : (Row, Int) => Set[Set[OffsetTime]]     = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2nestedSetOffsetDateTime: (Row, Int) => Set[Set[OffsetDateTime]] = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2nestedSetZonedDateTime : (Row, Int) => Set[Set[ZonedDateTime]]  = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2nestedSetUUID          : (Row, Int) => Set[Set[UUID]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2UUID)
  private lazy val nestedArray2nestedSetURI           : (Row, Int) => Set[Set[URI]]            = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, (v: Any) => new URI(j2String(v)))
  private lazy val nestedArray2nestedSetByte          : (Row, Int) => Set[Set[Byte]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Byte)
  private lazy val nestedArray2nestedSetShort         : (Row, Int) => Set[Set[Short]]          = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Short)
  private lazy val nestedArray2nestedSetChar          : (Row, Int) => Set[Set[Char]]           = (row: Row, paramIndex: Int) => sqlNestedArrays2nestedSet(row, paramIndex, j2Char)


  private lazy val array2setFirstId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Id).min)
  private lazy val array2setFirstString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)
  private lazy val array2setFirstInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).min)
  private lazy val array2setFirstLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).min)
  private lazy val array2setFirstFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).min)
  private lazy val array2setFirstDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).min)
  private lazy val array2setFirstBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).min)
  private lazy val array2setFirstBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).min)
  private lazy val array2setFirstBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).min)
  private lazy val array2setFirstDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).min)
  private lazy val array2setFirstDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).min)
  private lazy val array2setFirstInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).min)
  private lazy val array2setFirstLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).min)
  private lazy val array2setFirstLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).min)
  private lazy val array2setFirstLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).min)
  private lazy val array2setFirstOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).min)
  private lazy val array2setFirstOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).min)
  private lazy val array2setFirstZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).min)
  private lazy val array2setFirstUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).min)
  private lazy val array2setFirstURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)) // URI saved as String
  private lazy val array2setFirstByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).min)
  private lazy val array2setFirstShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).min)
  private lazy val array2setFirstChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).min)

  private lazy val array2setLastId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Id).max)
  private lazy val array2setLastString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)
  private lazy val array2setLastInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).max)
  private lazy val array2setLastLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).max)
  private lazy val array2setLastFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).max)
  private lazy val array2setLastDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).max)
  private lazy val array2setLastBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).max)
  private lazy val array2setLastBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).max)
  private lazy val array2setLastBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).max)
  private lazy val array2setLastDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).max)
  private lazy val array2setLastDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).max)
  private lazy val array2setLastInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).max)
  private lazy val array2setLastLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).max)
  private lazy val array2setLastLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).max)
  private lazy val array2setLastLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).max)
  private lazy val array2setLastOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).max)
  private lazy val array2setLastOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).max)
  private lazy val array2setLastZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).max)
  private lazy val array2setLastUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).max)
  private lazy val array2setLastURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)) // URI saved as String
  private lazy val array2setLastByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).max)
  private lazy val array2setLastShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).max)
  private lazy val array2setLastChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).max)


  case class ResSetOpt[T](
    tpe: String,
    sql2setOpt: (Row, ParamIndex) => Option[Set[T]],
    set2sqlArray: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
    one2json: T => String
  )

  lazy val resOptSetId            : ResSetOpt[String]         = ResSetOpt("String", sql2setOptId, set2sqlArrayId, set2sqlsId, one2sqlId, one2jsonId)
  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt("String", sql2setOptString, set2sqlArrayString, set2sqlsString, one2sqlString, one2jsonString)
  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt("Int", sql2setOptInt, set2sqlArrayInt, set2sqlsInt, one2sqlInt, one2jsonInt)
  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt("Long", sql2setOptLong, set2sqlArrayLong, set2sqlsLong, one2sqlLong, one2jsonLong)
  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt("Float", sql2setOptFloat, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt("Double", sql2setOptDouble, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt("Boolean", sql2setOptBoolean, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt("BigInt", sql2setOptBigInt, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt("BigDecimal", sql2setOptBigDecimal, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt("Date", sql2setOptDate, set2sqlArrayDate, set2sqlsDate, one2sqlDate, one2jsonDate)
  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt("Duration", sql2setOptDuration, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt("Instant", sql2setOptInstant, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt("LocalDate", sql2setOptLocalDate, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt("LocalTime", sql2setOptLocalTime, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt("LocalDateTime", sql2setOptLocalDateTime, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt("OffsetTime", sql2setOptOffsetTime, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt("OffsetDateTime", sql2setOptOffsetDateTime, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt("ZonedDateTime", sql2setOptZonedDateTime, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt("UUID", sql2setOptUUID, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt("URI", sql2setOptURI, set2sqlArrayURI, set2sqlsURI, one2sqlURI, one2jsonURI)
  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt("Byte", sql2setOptByte, set2sqlArrayByte, set2sqlsByte, one2sqlByte, one2jsonByte)
  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt("Short", sql2setOptShort, set2sqlArrayShort, set2sqlsShort, one2sqlShort, one2jsonShort)
  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt("Char", sql2setOptChar, set2sqlArrayChar, set2sqlsChar, one2sqlChar, one2jsonChar)


  protected def sql2setOpt[T](row: Row, paramIndex: Int, getValue: Row => T): Option[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Option.empty[Set[T]]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        set += getValue(arrayResultSet)
      }
      if (set.isEmpty || set == Set("0")) Option.empty[Set[T]] else Some(set)
    }
  }

  private lazy val sql2setOptId            : (Row, Int) => Option[Set[String]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueId)
  private lazy val sql2setOptString        : (Row, Int) => Option[Set[String]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueString)
  private lazy val sql2setOptInt           : (Row, Int) => Option[Set[Int]]            = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInt)
  private lazy val sql2setOptLong          : (Row, Int) => Option[Set[Long]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLong)
  private lazy val sql2setOptFloat         : (Row, Int) => Option[Set[Float]]          = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueFloat)
  private lazy val sql2setOptDouble        : (Row, Int) => Option[Set[Double]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDouble)
  private lazy val sql2setOptBoolean       : (Row, Int) => Option[Set[Boolean]]        = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBoolean)
  private lazy val sql2setOptBigInt        : (Row, Int) => Option[Set[BigInt]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigInt)
  private lazy val sql2setOptBigDecimal    : (Row, Int) => Option[Set[BigDecimal]]     = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigDecimal)
  private lazy val sql2setOptDate          : (Row, Int) => Option[Set[Date]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDate)
  private lazy val sql2setOptDuration      : (Row, Int) => Option[Set[Duration]]       = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDuration)
  private lazy val sql2setOptInstant       : (Row, Int) => Option[Set[Instant]]        = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInstant)
  private lazy val sql2setOptLocalDate     : (Row, Int) => Option[Set[LocalDate]]      = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDate)
  private lazy val sql2setOptLocalTime     : (Row, Int) => Option[Set[LocalTime]]      = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalTime)
  private lazy val sql2setOptLocalDateTime : (Row, Int) => Option[Set[LocalDateTime]]  = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDateTime)
  private lazy val sql2setOptOffsetTime    : (Row, Int) => Option[Set[OffsetTime]]     = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetTime)
  private lazy val sql2setOptOffsetDateTime: (Row, Int) => Option[Set[OffsetDateTime]] = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2setOptZonedDateTime : (Row, Int) => Option[Set[ZonedDateTime]]  = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueZonedDateTime)
  private lazy val sql2setOptUUID          : (Row, Int) => Option[Set[UUID]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueUUID)
  private lazy val sql2setOptURI           : (Row, Int) => Option[Set[URI]]            = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueURI)
  private lazy val sql2setOptByte          : (Row, Int) => Option[Set[Byte]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueByte)
  private lazy val sql2setOptShort         : (Row, Int) => Option[Set[Short]]          = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueShort)
  private lazy val sql2setOptChar          : (Row, Int) => Option[Set[Char]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueChar)
}
