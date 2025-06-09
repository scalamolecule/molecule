package molecule.db.sql.core.query

import java.lang.Short as jShort
import java.math.BigDecimal as jBigDecimal
import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import scala.collection.mutable.ListBuffer

trait LambdasSeq extends LambdasBase with JavaConversions { self: SqlQueryBase =>

  private lazy val sql2listId            : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueId)
  private lazy val sql2listString        : (RS, Int) => List[String]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueString)
  private lazy val sql2listInt           : (RS, Int) => List[Int]            = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueInt)
  private lazy val sql2listLong          : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLong)
  private lazy val sql2listFloat         : (RS, Int) => List[Float]          = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueFloat)
  private lazy val sql2listDouble        : (RS, Int) => List[Double]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDouble)
  private lazy val sql2listBoolean       : (RS, Int) => List[Boolean]        = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBoolean)
  private lazy val sql2listBigInt        : (RS, Int) => List[BigInt]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBigInt)
  private lazy val sql2listBigDecimal    : (RS, Int) => List[BigDecimal]     = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBigDecimal)
  private lazy val sql2listDate          : (RS, Int) => List[Date]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDate)
  private lazy val sql2listDuration      : (RS, Int) => List[Duration]       = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDuration)
  private lazy val sql2listInstant       : (RS, Int) => List[Instant]        = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueInstant)
  private lazy val sql2listLocalDate     : (RS, Int) => List[LocalDate]      = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalDate)
  private lazy val sql2listLocalTime     : (RS, Int) => List[LocalTime]      = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalTime)
  private lazy val sql2listLocalDateTime : (RS, Int) => List[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalDateTime)
  private lazy val sql2listOffsetTime    : (RS, Int) => List[OffsetTime]     = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueOffsetTime)
  private lazy val sql2listOffsetDateTime: (RS, Int) => List[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2listZonedDateTime : (RS, Int) => List[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueZonedDateTime)
  private lazy val sql2listUUID          : (RS, Int) => List[UUID]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueUUID)
  private lazy val sql2listURI           : (RS, Int) => List[URI]            = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueURI)
  private lazy val sql2listByte          : (RS, Int) => List[Byte]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueByte)
  private lazy val sql2listShort         : (RS, Int) => List[Short]          = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueShort)
  private lazy val sql2listChar          : (RS, Int) => List[Char]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueChar)

  case class ResSeq[T](
    tpe: String,
    tpeDb: String,

    sql2list: (RS, ParamIndex) => List[T],
    sql2listOrNull: (RS, ParamIndex) => Any, // Allow null in optional nested rows
    seq2sqlArray: Seq[T] => String,
    set2sqls: Seq[T] => Seq[String],
    one2sql: T => String,
    array2list: (RS, ParamIndex) => List[T],

    nestedArray2coalescedList: (RS, ParamIndex) => List[T],
    nestedArray2optCoalescedSet: (RS, ParamIndex) => Option[Set[T]],
    nestedArray2nestedSet: (RS, ParamIndex) => Set[Set[T]],
    array2setFirst: (RS, ParamIndex) => Set[T],
    array2setLast: (RS, ParamIndex) => Set[T],
    nestedArray2setAsc: Int => (RS, ParamIndex) => List[T],
    nestedArray2setDesc: Int => (RS, ParamIndex) => List[T],
    nestedArray2sum: (RS, ParamIndex) => Set[T],
    json2tpe: String => T,
    json2array: String => Array[T],
    json2optArray: String => Option[Array[T]],
    one2json: T => String,
    stringArray2sum: Array[String] => T,
    row2tpe: RS => T,
    sqlArray2sum: (RS, ParamIndex) => Set[T],
    sqlArray2minN: Int => (RS, ParamIndex) => Set[T],
    sqlArray2maxN: Int => (RS, ParamIndex) => Set[T],
    array2optSet: (RS, ParamIndex) => Option[Set[T]],
  )

  lazy val resSeqId            : ResSeq[Long]           = ResSeq("Long", tpeDbId, sql2listId, null, seq2sqlArrayId, seq2sqlsId, one2sqlId, array2listId, nestedArray2coalescedSetId, nestedArray2optCoalescedSetId, nestedArray2nestedSetId, array2setFirstId, array2setLastId, nestedArray2setAscId, nestedArray2setDescId, nestedArray2sumId, json2oneId, json2arrayId, json2optArrayId, one2jsonId, stringArray2sumId, valueId, sqlArray2sumId, sqlArray2minNId, sqlArray2maxNId, array2optSetId)
  lazy val resSeqString        : ResSeq[String]         = ResSeq("String", tpeDbString, sql2listString, null, seq2sqlArrayString, seq2sqlsString, one2sqlString, array2listString, nestedArray2coalescedSetString, nestedArray2optCoalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2sumString, json2oneString, json2arrayString, json2optArrayString, one2jsonString, stringArray2sumString, valueString, sqlArray2sumString, sqlArray2minNString, sqlArray2maxNString, array2optSetString)
  lazy val resSeqInt           : ResSeq[Int]            = ResSeq("Int", tpeDbInt, sql2listInt, null, seq2sqlArrayInt, seq2sqlsInt, one2sqlInt, array2listInt, nestedArray2coalescedSetInt, nestedArray2optCoalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2sumInt, json2oneInt, json2arrayInt, json2optArrayInt, one2jsonInt, stringArray2sumInt, valueInt, sqlArray2sumInt, sqlArray2minNInt, sqlArray2maxNInt, array2optSetInt)
  lazy val resSeqLong          : ResSeq[Long]           = ResSeq("Long", tpeDbLong, sql2listLong, null, seq2sqlArrayLong, seq2sqlsLong, one2sqlLong, array2listLong, nestedArray2coalescedSetLong, nestedArray2optCoalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2sumLong, json2oneLong, json2arrayLong, json2optArrayLong, one2jsonLong, stringArray2sumLong, valueLong, sqlArray2sumLong, sqlArray2minNLong, sqlArray2maxNLong, array2optSetLong)
  lazy val resSeqFloat         : ResSeq[Float]          = ResSeq("Float", tpeDbFloat, sql2listFloat, null, seq2sqlArrayFloat, seq2sqlsFloat, one2sqlFloat, array2listFloat, nestedArray2coalescedSetFloat, nestedArray2optCoalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2sumFloat, json2oneFloat, json2arrayFloat, json2optArrayFloat, one2jsonFloat, stringArray2sumFloat, valueFloat, sqlArray2sumFloat, sqlArray2minNFloat, sqlArray2maxNFloat, array2optSetFloat)
  lazy val resSeqDouble        : ResSeq[Double]         = ResSeq("Double", tpeDbDouble, sql2listDouble, null, seq2sqlArrayDouble, seq2sqlsDouble, one2sqlDouble, array2listDouble, nestedArray2coalescedSetDouble, nestedArray2optCoalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2sumDouble, json2oneDouble, json2arrayDouble, json2optArrayDouble, one2jsonDouble, stringArray2sumDouble, valueDouble, sqlArray2sumDouble, sqlArray2minNDouble, sqlArray2maxNDouble, array2optSetDouble)
  lazy val resSeqBoolean       : ResSeq[Boolean]        = ResSeq("Boolean", tpeDbBoolean, sql2listBoolean, null, seq2sqlArrayBoolean, seq2sqlsBoolean, one2sqlBoolean, array2listBoolean, nestedArray2coalescedSetBoolean, nestedArray2optCoalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2sumBoolean, json2oneBoolean, json2arrayBoolean, json2optArrayBoolean, one2jsonBoolean, stringArray2sumBoolean, valueBoolean, sqlArray2sumBoolean, sqlArray2minNBoolean, sqlArray2maxNBoolean, array2optSetBoolean)
  lazy val resSeqBigInt        : ResSeq[BigInt]         = ResSeq("BigInt", tpeDbBigInt, sql2listBigInt, null, seq2sqlArrayBigInt, seq2sqlsBigInt, one2sqlBigInt, array2listBigInt, nestedArray2coalescedSetBigInt, nestedArray2optCoalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2sumBigInt, json2oneBigInt, json2arrayBigInt, json2optArrayBigInt, one2jsonBigInt, stringArray2sumBigInt, valueBigInt, sqlArray2sumBigInt, sqlArray2minNBigInt, sqlArray2maxNBigInt, array2optSetBigInt)
  lazy val resSeqBigDecimal    : ResSeq[BigDecimal]     = ResSeq("BigDecimal", tpeDbBigDecimal, sql2listBigDecimal, null, seq2sqlArrayBigDecimal, seq2sqlsBigDecimal, one2sqlBigDecimal, array2listBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2optCoalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2sumBigDecimal, json2oneBigDecimal, json2arrayBigDecimal, json2optArrayBigDecimal, one2jsonBigDecimal, stringArray2sumBigDecimal, valueBigDecimal, sqlArray2sumBigDecimal, sqlArray2minNBigDecimal, sqlArray2maxNBigDecimal, array2optSetBigDecimal)
  lazy val resSeqDate          : ResSeq[Date]           = ResSeq("Date", tpeDbDate, sql2listDate, null, seq2sqlArrayDate, seq2sqlsDate, one2sqlDate, array2listDate, nestedArray2coalescedSetDate, nestedArray2optCoalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2sumDate, json2oneDate, json2arrayDate, json2optArrayDate, one2jsonDate, stringArray2sumDate, valueDate, sqlArray2sumDate, sqlArray2minNDate, sqlArray2maxNDate, array2optSetDate)
  lazy val resSeqDuration      : ResSeq[Duration]       = ResSeq("Duration", tpeDbDuration, sql2listDuration, null, seq2sqlArrayDuration, seq2sqlsDuration, one2sqlDuration, array2listDuration, nestedArray2coalescedSetDuration, nestedArray2optCoalescedSetDuration, nestedArray2nestedSetDuration, array2setFirstDuration, array2setLastDuration, nestedArray2setAscDuration, nestedArray2setDescDuration, nestedArray2sumDuration, json2oneDuration, json2arrayDuration, json2optArrayDuration, one2jsonDuration, stringArray2sumDuration, valueDuration, sqlArray2sumDuration, sqlArray2minNDuration, sqlArray2maxNDuration, array2optSetDuration)
  lazy val resSeqInstant       : ResSeq[Instant]        = ResSeq("Instant", tpeDbInstant, sql2listInstant, null, seq2sqlArrayInstant, seq2sqlsInstant, one2sqlInstant, array2listInstant, nestedArray2coalescedSetInstant, nestedArray2optCoalescedSetInstant, nestedArray2nestedSetInstant, array2setFirstInstant, array2setLastInstant, nestedArray2setAscInstant, nestedArray2setDescInstant, nestedArray2sumInstant, json2oneInstant, json2arrayInstant, json2optArrayInstant, one2jsonInstant, stringArray2sumInstant, valueInstant, sqlArray2sumInstant, sqlArray2minNInstant, sqlArray2maxNInstant, array2optSetInstant)
  lazy val resSeqLocalDate     : ResSeq[LocalDate]      = ResSeq("LocalDate", tpeDbLocalDate, sql2listLocalDate, null, seq2sqlArrayLocalDate, seq2sqlsLocalDate, one2sqlLocalDate, array2listLocalDate, nestedArray2coalescedSetLocalDate, nestedArray2optCoalescedSetLocalDate, nestedArray2nestedSetLocalDate, array2setFirstLocalDate, array2setLastLocalDate, nestedArray2setAscLocalDate, nestedArray2setDescLocalDate, nestedArray2sumLocalDate, json2oneLocalDate, json2arrayLocalDate, json2optArrayLocalDate, one2jsonLocalDate, stringArray2sumLocalDate, valueLocalDate, sqlArray2sumLocalDate, sqlArray2minNLocalDate, sqlArray2maxNLocalDate, array2optSetLocalDate)
  lazy val resSeqLocalTime     : ResSeq[LocalTime]      = ResSeq("LocalTime", tpeDbLocalTime, sql2listLocalTime, null, seq2sqlArrayLocalTime, seq2sqlsLocalTime, one2sqlLocalTime, array2listLocalTime, nestedArray2coalescedSetLocalTime, nestedArray2optCoalescedSetLocalTime, nestedArray2nestedSetLocalTime, array2setFirstLocalTime, array2setLastLocalTime, nestedArray2setAscLocalTime, nestedArray2setDescLocalTime, nestedArray2sumLocalTime, json2oneLocalTime, json2arrayLocalTime, json2optArrayLocalTime, one2jsonLocalTime, stringArray2sumLocalTime, valueLocalTime, sqlArray2sumLocalTime, sqlArray2minNLocalTime, sqlArray2maxNLocalTime, array2optSetLocalTime)
  lazy val resSeqLocalDateTime : ResSeq[LocalDateTime]  = ResSeq("LocalDateTime", tpeDbLocalDateTime, sql2listLocalDateTime, null, seq2sqlArrayLocalDateTime, seq2sqlsLocalDateTime, one2sqlLocalDateTime, array2listLocalDateTime, nestedArray2coalescedSetLocalDateTime, nestedArray2optCoalescedSetLocalDateTime, nestedArray2nestedSetLocalDateTime, array2setFirstLocalDateTime, array2setLastLocalDateTime, nestedArray2setAscLocalDateTime, nestedArray2setDescLocalDateTime, nestedArray2sumLocalDateTime, json2oneLocalDateTime, json2arrayLocalDateTime, json2optArrayLocalDateTime, one2jsonLocalDateTime, stringArray2sumLocalDateTime, valueLocalDateTime, sqlArray2sumLocalDateTime, sqlArray2minNLocalDateTime, sqlArray2maxNLocalDateTime, array2optSetLocalDateTime)
  lazy val resSeqOffsetTime    : ResSeq[OffsetTime]     = ResSeq("OffsetTime", tpeDbOffsetTime, sql2listOffsetTime, null, seq2sqlArrayOffsetTime, seq2sqlsOffsetTime, one2sqlOffsetTime, array2listOffsetTime, nestedArray2coalescedSetOffsetTime, nestedArray2optCoalescedSetOffsetTime, nestedArray2nestedSetOffsetTime, array2setFirstOffsetTime, array2setLastOffsetTime, nestedArray2setAscOffsetTime, nestedArray2setDescOffsetTime, nestedArray2sumOffsetTime, json2oneOffsetTime, json2arrayOffsetTime, json2optArrayOffsetTime, one2jsonOffsetTime, stringArray2sumOffsetTime, valueOffsetTime, sqlArray2sumOffsetTime, sqlArray2minNOffsetTime, sqlArray2maxNOffsetTime, array2optSetOffsetTime)
  lazy val resSeqOffsetDateTime: ResSeq[OffsetDateTime] = ResSeq("OffsetDateTime", tpeDbOffsetDateTime, sql2listOffsetDateTime, null, seq2sqlArrayOffsetDateTime, seq2sqlsOffsetDateTime, one2sqlOffsetDateTime, array2listOffsetDateTime, nestedArray2coalescedSetOffsetDateTime, nestedArray2optCoalescedSetOffsetDateTime, nestedArray2nestedSetOffsetDateTime, array2setFirstOffsetDateTime, array2setLastOffsetDateTime, nestedArray2setAscOffsetDateTime, nestedArray2setDescOffsetDateTime, nestedArray2sumOffsetDateTime, json2oneOffsetDateTime, json2arrayOffsetDateTime, json2optArrayOffsetDateTime, one2jsonOffsetDateTime, stringArray2sumOffsetDateTime, valueOffsetDateTime, sqlArray2sumOffsetDateTime, sqlArray2minNOffsetDateTime, sqlArray2maxNOffsetDateTime, array2optSetOffsetDateTime)
  lazy val resSeqZonedDateTime : ResSeq[ZonedDateTime]  = ResSeq("ZonedDateTime", tpeDbZonedDateTime, sql2listZonedDateTime, null, seq2sqlArrayZonedDateTime, seq2sqlsZonedDateTime, one2sqlZonedDateTime, array2listZonedDateTime, nestedArray2coalescedSetZonedDateTime, nestedArray2optCoalescedSetZonedDateTime, nestedArray2nestedSetZonedDateTime, array2setFirstZonedDateTime, array2setLastZonedDateTime, nestedArray2setAscZonedDateTime, nestedArray2setDescZonedDateTime, nestedArray2sumZonedDateTime, json2oneZonedDateTime, json2arrayZonedDateTime, json2optArrayZonedDateTime, one2jsonZonedDateTime, stringArray2sumZonedDateTime, valueZonedDateTime, sqlArray2sumZonedDateTime, sqlArray2minNZonedDateTime, sqlArray2maxNZonedDateTime, array2optSetZonedDateTime)
  lazy val resSeqUUID          : ResSeq[UUID]           = ResSeq("UUID", tpeDbUUID, sql2listUUID, null, seq2sqlArrayUUID, seq2sqlsUUID, one2sqlUUID, array2listUUID, nestedArray2coalescedSetUUID, nestedArray2optCoalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2sumUUID, json2oneUUID, json2arrayUUID, json2optArrayUUID, one2jsonUUID, stringArray2sumUUID, valueUUID, sqlArray2sumUUID, sqlArray2minNUUID, sqlArray2maxNUUID, array2optSetUUID)
  lazy val resSeqURI           : ResSeq[URI]            = ResSeq("URI", tpeDbURI, sql2listURI, null, seq2sqlArrayURI, seq2sqlsURI, one2sqlURI, array2listURI, nestedArray2coalescedSetURI, nestedArray2optCoalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2sumURI, json2oneURI, json2arrayURI, json2optArrayURI, one2jsonURI, stringArray2sumURI, valueURI, sqlArray2sumURI, sqlArray2minNURI, sqlArray2maxNURI, array2optSetURI)
  lazy val resSeqByte          : ResSeq[Byte]           = ResSeq("Byte", tpeDbByte, sql2listByte, null, seq2sqlArrayByte, seq2sqlsByte, one2sqlByte, array2listByte, nestedArray2coalescedSetByte, nestedArray2optCoalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2sumByte, json2oneByte, json2arrayByte, json2optArrayByte, one2jsonByte, stringArray2sumByte, valueByte, sqlArray2sumByte, sqlArray2minNByte, sqlArray2maxNByte, array2optSetByte)
  lazy val resSeqShort         : ResSeq[Short]          = ResSeq("Short", tpeDbShort, sql2listShort, null, seq2sqlArrayShort, seq2sqlsShort, one2sqlShort, array2listShort, nestedArray2coalescedSetShort, nestedArray2optCoalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2sumShort, json2oneShort, json2arrayShort, json2optArrayShort, one2jsonShort, stringArray2sumShort, valueShort, sqlArray2sumShort, sqlArray2minNShort, sqlArray2maxNShort, array2optSetShort)
  lazy val resSeqChar          : ResSeq[Char]           = ResSeq("Char", tpeDbChar, sql2listChar, null, seq2sqlArrayChar, seq2sqlsChar, one2sqlChar, array2listChar, nestedArray2coalescedSetChar, nestedArray2optCoalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2sumChar, json2oneChar, json2arrayChar, json2optArrayChar, one2jsonChar, stringArray2sumChar, valueChar, sqlArray2sumChar, sqlArray2minNChar, sqlArray2maxNChar, array2optSetChar)

  private lazy val seq2sqlArrayId            : Seq[Long] => String           = (seq: Seq[Long]) => seq.mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val seq2sqlArrayString        : Seq[String] => String         = (seq: Seq[String]) => seq.map(_.replace("'", "''")).mkString("ARRAY['", "', '", "']::text[]")
  private lazy val seq2sqlArrayInt           : Seq[Int] => String            = (seq: Seq[Int]) => seq.mkString("ARRAY[", ", ", "]::integer[]")
  private lazy val seq2sqlArrayLong          : Seq[Long] => String           = (seq: Seq[Long]) => seq.mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val seq2sqlArrayFloat         : Seq[Float] => String          = (seq: Seq[Float]) => seq.mkString("ARRAY[", ", ", "]::decimal[]")
  private lazy val seq2sqlArrayDouble        : Seq[Double] => String         = (seq: Seq[Double]) => seq.mkString("ARRAY[", ", ", "]::double precision[]")
  private lazy val seq2sqlArrayBoolean       : Seq[Boolean] => String        = (seq: Seq[Boolean]) => seq.mkString("ARRAY[", ", ", "]::boolean[]")
  private lazy val seq2sqlArrayBigInt        : Seq[BigInt] => String         = (seq: Seq[BigInt]) => seq.mkString("ARRAY[", ", ", "]::numeric[]")
  private lazy val seq2sqlArrayBigDecimal    : Seq[BigDecimal] => String     = (seq: Seq[BigDecimal]) => seq.mkString("ARRAY[", ", ", "]::numeric[]")
  private lazy val seq2sqlArrayDate          : Seq[Date] => String           = (seq: Seq[Date]) => seq.map(_.getTime).mkString("ARRAY[", ", ", "]::bigint[]")
  private lazy val seq2sqlArrayDuration      : Seq[Duration] => String       = (seq: Seq[Duration]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayInstant       : Seq[Instant] => String        = (seq: Seq[Instant]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayLocalDate     : Seq[LocalDate] => String      = (seq: Seq[LocalDate]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayLocalTime     : Seq[LocalTime] => String      = (seq: Seq[LocalTime]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayLocalDateTime : Seq[LocalDateTime] => String  = (seq: Seq[LocalDateTime]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayOffsetTime    : Seq[OffsetTime] => String     = (seq: Seq[OffsetTime]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayOffsetDateTime: Seq[OffsetDateTime] => String = (seq: Seq[OffsetDateTime]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayZonedDateTime : Seq[ZonedDateTime] => String  = (seq: Seq[ZonedDateTime]) => seq.mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayUUID          : Seq[UUID] => String           = (seq: Seq[UUID]) => seq.mkString("ARRAY['", "', '", "']::uuid[]")
  private lazy val seq2sqlArrayURI           : Seq[URI] => String            = (seq: Seq[URI]) => seq.map(_.toString.replace("'", "''")).mkString("ARRAY['", "', '", "']::varchar[]")
  private lazy val seq2sqlArrayByte          : Seq[Byte] => String           = (seq: Seq[Byte]) => seq.mkString("ARRAY[", ", ", "]::smallint[]")
  private lazy val seq2sqlArrayShort         : Seq[Short] => String          = (seq: Seq[Short]) => seq.mkString("ARRAY[", ", ", "]::smallint[]")
  private lazy val seq2sqlArrayChar          : Seq[Char] => String           = (seq: Seq[Char]) => seq.mkString("ARRAY['", "', '", "']::char[]")

  private lazy val seq2sqlsId            : Seq[Long] => Seq[String]           = (seq: Seq[Long]) => seq.map(_.toString)
  private lazy val seq2sqlsString        : Seq[String] => Seq[String]         = (seq: Seq[String]) => seq.map(_.replace("'", "''")).map(v => s"'$v'")
  private lazy val seq2sqlsInt           : Seq[Int] => Seq[String]            = (seq: Seq[Int]) => seq.map(_.toString)
  private lazy val seq2sqlsLong          : Seq[Long] => Seq[String]           = (seq: Seq[Long]) => seq.map(_.toString)
  private lazy val seq2sqlsFloat         : Seq[Float] => Seq[String]          = (seq: Seq[Float]) => seq.map(_.toString)
  private lazy val seq2sqlsDouble        : Seq[Double] => Seq[String]         = (seq: Seq[Double]) => seq.map(_.toString)
  private lazy val seq2sqlsBoolean       : Seq[Boolean] => Seq[String]        = (seq: Seq[Boolean]) => seq.map(_.toString)
  private lazy val seq2sqlsBigInt        : Seq[BigInt] => Seq[String]         = (seq: Seq[BigInt]) => seq.map(_.toString)
  private lazy val seq2sqlsBigDecimal    : Seq[BigDecimal] => Seq[String]     = (seq: Seq[BigDecimal]) => seq.map(_.toString)
  private lazy val seq2sqlsDate          : Seq[Date] => Seq[String]           = (seq: Seq[Date]) => seq.map(_.getTime.toString)
  private lazy val seq2sqlsDuration      : Seq[Duration] => Seq[String]       = (seq: Seq[Duration]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsInstant       : Seq[Instant] => Seq[String]        = (seq: Seq[Instant]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsLocalDate     : Seq[LocalDate] => Seq[String]      = (seq: Seq[LocalDate]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsLocalTime     : Seq[LocalTime] => Seq[String]      = (seq: Seq[LocalTime]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsLocalDateTime : Seq[LocalDateTime] => Seq[String]  = (seq: Seq[LocalDateTime]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsOffsetTime    : Seq[OffsetTime] => Seq[String]     = (seq: Seq[OffsetTime]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsOffsetDateTime: Seq[OffsetDateTime] => Seq[String] = (seq: Seq[OffsetDateTime]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsZonedDateTime : Seq[ZonedDateTime] => Seq[String]  = (seq: Seq[ZonedDateTime]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsUUID          : Seq[UUID] => Seq[String]           = (seq: Seq[UUID]) => seq.map(v => s"'$v'")
  private lazy val seq2sqlsURI           : Seq[URI] => Seq[String]            = (seq: Seq[URI]) => seq.map(_.toString.replace("'", "''")).map(v => s"'$v'")
  private lazy val seq2sqlsByte          : Seq[Byte] => Seq[String]           = (seq: Seq[Byte]) => seq.map(_.toString)
  private lazy val seq2sqlsShort         : Seq[Short] => Seq[String]          = (seq: Seq[Short]) => seq.map(_.toString)
  private lazy val seq2sqlsChar          : Seq[Char] => Seq[String]           = (seq: Seq[Char]) => seq.map(v => s"'$v'")


  private def sqlNestedArrays2optCoalescedSet[T](row: RS, paramIndex: Int, j2s: Any => T): Option[Set[T]] = {
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
          outerArray.getArray.asInstanceOf[Array[?]].foreach { value =>
            set += j2s(value)
          }
        }
      }
      if (set.nonEmpty) Some(set) else Option.empty[Set[T]]
    }
  }

  private lazy val nestedArray2optCoalescedSetId            : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Id)
  private lazy val nestedArray2optCoalescedSetString        : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String)
  private lazy val nestedArray2optCoalescedSetInt           : (RS, Int) => Option[Set[Int]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Int)
  private lazy val nestedArray2optCoalescedSetLong          : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Long)
  private lazy val nestedArray2optCoalescedSetFloat         : (RS, Int) => Option[Set[Float]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Float)
  private lazy val nestedArray2optCoalescedSetDouble        : (RS, Int) => Option[Set[Double]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Double)
  private lazy val nestedArray2optCoalescedSetBoolean       : (RS, Int) => Option[Set[Boolean]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Boolean)
  private lazy val nestedArray2optCoalescedSetBigInt        : (RS, Int) => Option[Set[BigInt]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigInt)
  private lazy val nestedArray2optCoalescedSetBigDecimal    : (RS, Int) => Option[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2optCoalescedSetDate          : (RS, Int) => Option[Set[Date]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Date)
  private lazy val nestedArray2optCoalescedSetDuration      : (RS, Int) => Option[Set[Duration]]       = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Duration)
  private lazy val nestedArray2optCoalescedSetInstant       : (RS, Int) => Option[Set[Instant]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Instant)
  private lazy val nestedArray2optCoalescedSetLocalDate     : (RS, Int) => Option[Set[LocalDate]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2optCoalescedSetLocalTime     : (RS, Int) => Option[Set[LocalTime]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2optCoalescedSetLocalDateTime : (RS, Int) => Option[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2optCoalescedSetOffsetTime    : (RS, Int) => Option[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2optCoalescedSetOffsetDateTime: (RS, Int) => Option[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2optCoalescedSetZonedDateTime : (RS, Int) => Option[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2optCoalescedSetUUID          : (RS, Int) => Option[Set[UUID]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2UUID)
  private lazy val nestedArray2optCoalescedSetURI           : (RS, Int) => Option[Set[URI]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2String).map(_.map(v => new URI(v)))
  private lazy val nestedArray2optCoalescedSetByte          : (RS, Int) => Option[Set[Byte]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Byte)
  private lazy val nestedArray2optCoalescedSetShort         : (RS, Int) => Option[Set[Short]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Short)
  private lazy val nestedArray2optCoalescedSetChar          : (RS, Int) => Option[Set[Char]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2optCoalescedSet(row, paramIndex, j2Char)


  private def array2optSet[T](row: RS, paramIndex: Int, j2s: Any => T): Option[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Option.empty[Set[T]]
    } else {
      var set = Set.empty[T]
      array.getArray.asInstanceOf[Array[?]].foreach { value =>
        set += j2s(value)
      }
      if (set.nonEmpty) Some(set) else Option.empty[Set[T]]
    }
  }

  private lazy val array2optSetId            : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Id)
  private lazy val array2optSetString        : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2String)
  private lazy val array2optSetInt           : (RS, Int) => Option[Set[Int]]            = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Int)
  private lazy val array2optSetLong          : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Long)
  private lazy val array2optSetFloat         : (RS, Int) => Option[Set[Float]]          = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Float)
  private lazy val array2optSetDouble        : (RS, Int) => Option[Set[Double]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Double)
  private lazy val array2optSetBoolean       : (RS, Int) => Option[Set[Boolean]]        = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Boolean)
  private lazy val array2optSetBigInt        : (RS, Int) => Option[Set[BigInt]]         = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2BigInt)
  private lazy val array2optSetBigDecimal    : (RS, Int) => Option[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2BigDecimal)
  private lazy val array2optSetDate          : (RS, Int) => Option[Set[Date]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Date)
  private lazy val array2optSetDuration      : (RS, Int) => Option[Set[Duration]]       = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Duration)
  private lazy val array2optSetInstant       : (RS, Int) => Option[Set[Instant]]        = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Instant)
  private lazy val array2optSetLocalDate     : (RS, Int) => Option[Set[LocalDate]]      = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalDate)
  private lazy val array2optSetLocalTime     : (RS, Int) => Option[Set[LocalTime]]      = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalTime)
  private lazy val array2optSetLocalDateTime : (RS, Int) => Option[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2LocalDateTime)
  private lazy val array2optSetOffsetTime    : (RS, Int) => Option[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2OffsetTime)
  private lazy val array2optSetOffsetDateTime: (RS, Int) => Option[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2OffsetDateTime)
  private lazy val array2optSetZonedDateTime : (RS, Int) => Option[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2ZonedDateTime)
  private lazy val array2optSetUUID          : (RS, Int) => Option[Set[UUID]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2UUID)
  private lazy val array2optSetURI           : (RS, Int) => Option[Set[URI]]            = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2String).map(_.map(v => new URI(v)))
  private lazy val array2optSetByte          : (RS, Int) => Option[Set[Byte]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Byte)
  private lazy val array2optSetShort         : (RS, Int) => Option[Set[Short]]          = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Short)
  private lazy val array2optSetChar          : (RS, Int) => Option[Set[Char]]           = (row: RS, paramIndex: Int) => array2optSet(row, paramIndex, j2Char)

  private def sqlNestedArrays2list[T](row: RS, paramIndex: Int, j2s: Any => T): List[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      List.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      val buf                 = ListBuffer.empty[T]
      while (outerArrayResultSet.next()) {
        val array0 = outerArrayResultSet.getArray(2)
        // Account for empty Sets
        if (array0.getUnderlyingArray != null) {
          array0.getArray.asInstanceOf[Array[?]].foreach { value =>
            buf += j2s(value)
          }
        }
      }
      buf.toList
    }
  }

  private lazy val nestedArray2coalescedSetId            : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Id)
  private lazy val nestedArray2coalescedSetString        : (RS, Int) => List[String]         = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String)
  private lazy val nestedArray2coalescedSetInt           : (RS, Int) => List[Int]            = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Int)
  private lazy val nestedArray2coalescedSetLong          : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Long)
  private lazy val nestedArray2coalescedSetFloat         : (RS, Int) => List[Float]          = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Float)
  private lazy val nestedArray2coalescedSetDouble        : (RS, Int) => List[Double]         = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Double)
  private lazy val nestedArray2coalescedSetBoolean       : (RS, Int) => List[Boolean]        = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Boolean)
  private lazy val nestedArray2coalescedSetBigInt        : (RS, Int) => List[BigInt]         = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigInt)
  private lazy val nestedArray2coalescedSetBigDecimal    : (RS, Int) => List[BigDecimal]     = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2coalescedSetDate          : (RS, Int) => List[Date]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Date)
  private lazy val nestedArray2coalescedSetDuration      : (RS, Int) => List[Duration]       = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Duration)
  private lazy val nestedArray2coalescedSetInstant       : (RS, Int) => List[Instant]        = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Instant)
  private lazy val nestedArray2coalescedSetLocalDate     : (RS, Int) => List[LocalDate]      = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2coalescedSetLocalTime     : (RS, Int) => List[LocalTime]      = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2coalescedSetLocalDateTime : (RS, Int) => List[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2coalescedSetOffsetTime    : (RS, Int) => List[OffsetTime]     = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2coalescedSetOffsetDateTime: (RS, Int) => List[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2coalescedSetZonedDateTime : (RS, Int) => List[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2coalescedSetUUID          : (RS, Int) => List[UUID]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2UUID)
  private lazy val nestedArray2coalescedSetURI           : (RS, Int) => List[URI]            = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String).map(v => new URI(v))
  private lazy val nestedArray2coalescedSetByte          : (RS, Int) => List[Byte]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Byte)
  private lazy val nestedArray2coalescedSetShort         : (RS, Int) => List[Short]          = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Short)
  private lazy val nestedArray2coalescedSetChar          : (RS, Int) => List[Char]           = (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Char)


  private lazy val nestedArray2setAscId            : Int => (RS, Int) => List[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Id).sorted.take(size)
  private lazy val nestedArray2setAscString        : Int => (RS, Int) => List[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String).sorted.take(size)
  private lazy val nestedArray2setAscInt           : Int => (RS, Int) => List[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Int).sorted.take(size)
  private lazy val nestedArray2setAscLong          : Int => (RS, Int) => List[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Long).sorted.take(size)
  private lazy val nestedArray2setAscFloat         : Int => (RS, Int) => List[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Float).sorted.take(size)
  private lazy val nestedArray2setAscDouble        : Int => (RS, Int) => List[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Double).sorted.take(size)
  private lazy val nestedArray2setAscBoolean       : Int => (RS, Int) => List[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Boolean).sorted.take(size)
  private lazy val nestedArray2setAscBigInt        : Int => (RS, Int) => List[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigInt).sorted.take(size)
  private lazy val nestedArray2setAscBigDecimal    : Int => (RS, Int) => List[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigDecimal).sorted.take(size)
  private lazy val nestedArray2setAscDate          : Int => (RS, Int) => List[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Date).sorted.take(size)
  private lazy val nestedArray2setAscDuration      : Int => (RS, Int) => List[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Duration).sorted.take(size)
  private lazy val nestedArray2setAscInstant       : Int => (RS, Int) => List[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Instant).sorted.take(size)
  private lazy val nestedArray2setAscLocalDate     : Int => (RS, Int) => List[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDate).sorted.take(size)
  private lazy val nestedArray2setAscLocalTime     : Int => (RS, Int) => List[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalTime).sorted.take(size)
  private lazy val nestedArray2setAscLocalDateTime : Int => (RS, Int) => List[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDateTime).sorted.take(size)
  private lazy val nestedArray2setAscOffsetTime    : Int => (RS, Int) => List[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetTime).sorted.take(size)
  private lazy val nestedArray2setAscOffsetDateTime: Int => (RS, Int) => List[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetDateTime).sorted.take(size)
  private lazy val nestedArray2setAscZonedDateTime : Int => (RS, Int) => List[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2ZonedDateTime).sorted.take(size)
  private lazy val nestedArray2setAscUUID          : Int => (RS, Int) => List[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2UUID).sorted.take(size)
  private lazy val nestedArray2setAscURI           : Int => (RS, Int) => List[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String).sorted.take(size).map(s => new URI(s))
  private lazy val nestedArray2setAscByte          : Int => (RS, Int) => List[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Byte).sorted.take(size)
  private lazy val nestedArray2setAscShort         : Int => (RS, Int) => List[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Short).sorted.take(size)
  private lazy val nestedArray2setAscChar          : Int => (RS, Int) => List[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Char).sorted.take(size)

  private lazy val nestedArray2setDescId            : Int => (RS, Int) => List[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Id).sorted.takeRight(size)
  private lazy val nestedArray2setDescString        : Int => (RS, Int) => List[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String).sorted.takeRight(size)
  private lazy val nestedArray2setDescInt           : Int => (RS, Int) => List[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Int).sorted.takeRight(size)
  private lazy val nestedArray2setDescLong          : Int => (RS, Int) => List[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Long).sorted.takeRight(size)
  private lazy val nestedArray2setDescFloat         : Int => (RS, Int) => List[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Float).sorted.takeRight(size)
  private lazy val nestedArray2setDescDouble        : Int => (RS, Int) => List[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Double).sorted.takeRight(size)
  private lazy val nestedArray2setDescBoolean       : Int => (RS, Int) => List[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Boolean).sorted.takeRight(size)
  private lazy val nestedArray2setDescBigInt        : Int => (RS, Int) => List[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigInt).sorted.takeRight(size)
  private lazy val nestedArray2setDescBigDecimal    : Int => (RS, Int) => List[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2BigDecimal).sorted.takeRight(size)
  private lazy val nestedArray2setDescDate          : Int => (RS, Int) => List[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Date).sorted.takeRight(size)
  private lazy val nestedArray2setDescDuration      : Int => (RS, Int) => List[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Duration).sorted.takeRight(size)
  private lazy val nestedArray2setDescInstant       : Int => (RS, Int) => List[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Instant).sorted.takeRight(size)
  private lazy val nestedArray2setDescLocalDate     : Int => (RS, Int) => List[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDate).sorted.takeRight(size)
  private lazy val nestedArray2setDescLocalTime     : Int => (RS, Int) => List[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalTime).sorted.takeRight(size)
  private lazy val nestedArray2setDescLocalDateTime : Int => (RS, Int) => List[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2LocalDateTime).sorted.takeRight(size)
  private lazy val nestedArray2setDescOffsetTime    : Int => (RS, Int) => List[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetTime).sorted.takeRight(size)
  private lazy val nestedArray2setDescOffsetDateTime: Int => (RS, Int) => List[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2OffsetDateTime).sorted.takeRight(size)
  private lazy val nestedArray2setDescZonedDateTime : Int => (RS, Int) => List[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2ZonedDateTime).sorted.takeRight(size)
  private lazy val nestedArray2setDescUUID          : Int => (RS, Int) => List[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2UUID).sorted.takeRight(size)
  private lazy val nestedArray2setDescURI           : Int => (RS, Int) => List[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2String).sorted.takeRight(size).map(s => new URI(s))
  private lazy val nestedArray2setDescByte          : Int => (RS, Int) => List[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Byte).sorted.takeRight(size)
  private lazy val nestedArray2setDescShort         : Int => (RS, Int) => List[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Short).sorted.takeRight(size)
  private lazy val nestedArray2setDescChar          : Int => (RS, Int) => List[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlNestedArrays2list(row, paramIndex, j2Char).sorted.takeRight(size)


  private def sqlArrays2set[T](row: RS, paramIndex: Int, j2s: Any => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      var set = Set.empty[T]
      array.getArray.asInstanceOf[Array[?]].foreach { value =>
        set += j2s(value)
      }
      set
    }
  }

  private lazy val sqlArray2minNId            : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  private lazy val sqlArray2minNByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  private lazy val sqlArray2minNChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.take(size).toSet

  private lazy val sqlArray2maxNId            : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Id).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNString        : Int => (RS, Int) => Set[String]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNInt           : Int => (RS, Int) => Set[Int]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLong          : Int => (RS, Int) => Set[Long]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNFloat         : Int => (RS, Int) => Set[Float]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDouble        : Int => (RS, Int) => Set[Double]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBoolean       : Int => (RS, Int) => Set[Boolean]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBigInt        : Int => (RS, Int) => Set[BigInt]         = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNBigDecimal    : Int => (RS, Int) => Set[BigDecimal]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDate          : Int => (RS, Int) => Set[Date]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNDuration      : Int => (RS, Int) => Set[Duration]       = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNInstant       : Int => (RS, Int) => Set[Instant]        = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalDate     : Int => (RS, Int) => Set[LocalDate]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalTime     : Int => (RS, Int) => Set[LocalTime]      = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNLocalDateTime : Int => (RS, Int) => Set[LocalDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNOffsetTime    : Int => (RS, Int) => Set[OffsetTime]     = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNOffsetDateTime: Int => (RS, Int) => Set[OffsetDateTime] = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNZonedDateTime : Int => (RS, Int) => Set[ZonedDateTime]  = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNUUID          : Int => (RS, Int) => Set[UUID]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNURI           : Int => (RS, Int) => Set[URI]            = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  private lazy val sqlArray2maxNByte          : Int => (RS, Int) => Set[Byte]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNShort         : Int => (RS, Int) => Set[Short]          = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  private lazy val sqlArray2maxNChar          : Int => (RS, Int) => Set[Char]           = (size: Int) => (row: RS, paramIndex: Int) => sqlArrays2set(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet

  private def onlyNumbers = throw new Exception("Casting only for numbers.")

  private def sqlNestedArrays2sum[T](row: RS, paramIndex: Int, j2s: Any => T): List[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      List.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      val list                = ListBuffer.empty[T]
      while (outerArrayResultSet.next()) {
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[?]].foreach { value =>
          list += j2s(value)
        }
      }
      list.toList
    }
  }
  private lazy val nestedArray2sumId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Int](row, paramIndex, j2Int).sum)
  private lazy val nestedArray2sumLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Long](row, paramIndex, j2Long).sum)
  private lazy val nestedArray2sumFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Float](row, paramIndex, j2Float).sum)
  private lazy val nestedArray2sumDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Double](row, paramIndex, j2Double).sum)
  private lazy val nestedArray2sumBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[BigInt](row, paramIndex, j2BigInt).sum)
  private lazy val nestedArray2sumBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[BigDecimal](row, paramIndex, j2BigDecimal).sum)
  private lazy val nestedArray2sumDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val nestedArray2sumByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Byte](row, paramIndex, j2Byte).sum)
  private lazy val nestedArray2sumShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Short](row, paramIndex, j2Short).sum)
  private lazy val nestedArray2sumChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(sqlNestedArrays2sum[Char](row, paramIndex, j2Char).sum)

  private def sqlArray2sum[T](row: RS, paramIndex: Int, value: RS => T): List[T] = {
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
  private lazy val sqlArray2sumId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Int](row, paramIndex, valueInt).sum)
  private lazy val sqlArray2sumLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Long](row, paramIndex, valueLong).sum)
  private lazy val sqlArray2sumFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Float](row, paramIndex, valueFloat).sum)
  private lazy val sqlArray2sumDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Double](row, paramIndex, valueDouble).sum)
  private lazy val sqlArray2sumBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(sqlArray2sum[BigInt](row, paramIndex, valueBigInt).sum)
  private lazy val sqlArray2sumBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(sqlArray2sum[BigDecimal](row, paramIndex, valueBigDecimal).sum)
  private lazy val sqlArray2sumDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => onlyNumbers
  private lazy val sqlArray2sumByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Byte](row, paramIndex, valueByte).sum)
  private lazy val sqlArray2sumShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Short](row, paramIndex, valueShort).sum)
  private lazy val sqlArray2sumChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(sqlArray2sum[Char](row, paramIndex, valueChar).sum)

  private lazy val stringArray2sumId            : Array[String] => Long           = (vs: Array[String]) => onlyNumbers
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


  private lazy val j2Id            : Any => Long           = (v: Any) => v.asInstanceOf[Long]
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


  private def sqlNestedArrays2nestedSeq[T](row: RS, paramIndex: Int, getValue: Any => T): Set[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[Set[T]]
    } else {
      val outerArrayResultSet = array.getResultSet
      var sets                = Set.empty[Set[T]]
      while (outerArrayResultSet.next()) {
        var set = Set.empty[T]
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[?]].foreach { value =>
          set += getValue(value)
        }
        sets += set
      }
      sets
    }
  }

  private lazy val nestedArray2nestedSetId            : (RS, Int) => Set[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Id)
  private lazy val nestedArray2nestedSetString        : (RS, Int) => Set[Set[String]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2String)
  private lazy val nestedArray2nestedSetInt           : (RS, Int) => Set[Set[Int]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Int)
  private lazy val nestedArray2nestedSetLong          : (RS, Int) => Set[Set[Long]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Long)
  private lazy val nestedArray2nestedSetFloat         : (RS, Int) => Set[Set[Float]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Float)
  private lazy val nestedArray2nestedSetDouble        : (RS, Int) => Set[Set[Double]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Double)
  private lazy val nestedArray2nestedSetBoolean       : (RS, Int) => Set[Set[Boolean]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Boolean)
  private lazy val nestedArray2nestedSetBigInt        : (RS, Int) => Set[Set[BigInt]]         = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2BigInt)
  private lazy val nestedArray2nestedSetBigDecimal    : (RS, Int) => Set[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2nestedSetDate          : (RS, Int) => Set[Set[Date]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Date)
  private lazy val nestedArray2nestedSetDuration      : (RS, Int) => Set[Set[Duration]]       = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Duration)
  private lazy val nestedArray2nestedSetInstant       : (RS, Int) => Set[Set[Instant]]        = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Instant)
  private lazy val nestedArray2nestedSetLocalDate     : (RS, Int) => Set[Set[LocalDate]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2nestedSetLocalTime     : (RS, Int) => Set[Set[LocalTime]]      = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2nestedSetLocalDateTime : (RS, Int) => Set[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2nestedSetOffsetTime    : (RS, Int) => Set[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2nestedSetOffsetDateTime: (RS, Int) => Set[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2nestedSetZonedDateTime : (RS, Int) => Set[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2nestedSetUUID          : (RS, Int) => Set[Set[UUID]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2UUID)
  private lazy val nestedArray2nestedSetURI           : (RS, Int) => Set[Set[URI]]            = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, (v: Any) => new URI(j2String(v)))
  private lazy val nestedArray2nestedSetByte          : (RS, Int) => Set[Set[Byte]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Byte)
  private lazy val nestedArray2nestedSetShort         : (RS, Int) => Set[Set[Short]]          = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Short)
  private lazy val nestedArray2nestedSetChar          : (RS, Int) => Set[Set[Char]]           = (row: RS, paramIndex: Int) => sqlNestedArrays2nestedSeq(row, paramIndex, j2Char)


  private lazy val array2setFirstId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Id).min)
  private lazy val array2setFirstString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2String).min)
  private lazy val array2setFirstInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Int).min)
  private lazy val array2setFirstLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Long).min)
  private lazy val array2setFirstFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Float).min)
  private lazy val array2setFirstDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Double).min)
  private lazy val array2setFirstBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Boolean).min)
  private lazy val array2setFirstBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2BigInt).min)
  private lazy val array2setFirstBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2BigDecimal).min)
  private lazy val array2setFirstDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Date).min)
  private lazy val array2setFirstDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Duration).min)
  private lazy val array2setFirstInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Instant).min)
  private lazy val array2setFirstLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalDate).min)
  private lazy val array2setFirstLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalTime).min)
  private lazy val array2setFirstLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalDateTime).min)
  private lazy val array2setFirstOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2OffsetTime).min)
  private lazy val array2setFirstOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2OffsetDateTime).min)
  private lazy val array2setFirstZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2ZonedDateTime).min)
  private lazy val array2setFirstUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2UUID).min)
  private lazy val array2setFirstURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2String).min)) // URI saved as String
  private lazy val array2setFirstByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Byte).min)
  private lazy val array2setFirstShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Short).min)
  private lazy val array2setFirstChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Char).min)

  private lazy val array2setLastId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Id).max)
  private lazy val array2setLastString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2String).max)
  private lazy val array2setLastInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Int).max)
  private lazy val array2setLastLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Long).max)
  private lazy val array2setLastFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Float).max)
  private lazy val array2setLastDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Double).max)
  private lazy val array2setLastBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Boolean).max)
  private lazy val array2setLastBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2BigInt).max)
  private lazy val array2setLastBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2BigDecimal).max)
  private lazy val array2setLastDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Date).max)
  private lazy val array2setLastDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Duration).max)
  private lazy val array2setLastInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Instant).max)
  private lazy val array2setLastLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalDate).max)
  private lazy val array2setLastLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalTime).max)
  private lazy val array2setLastLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2LocalDateTime).max)
  private lazy val array2setLastOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2OffsetTime).max)
  private lazy val array2setLastOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2OffsetDateTime).max)
  private lazy val array2setLastZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2ZonedDateTime).max)
  private lazy val array2setLastUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2UUID).max)
  private lazy val array2setLastURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2String).max)) // URI saved as String
  private lazy val array2setLastByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Byte).max)
  private lazy val array2setLastShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Short).max)
  private lazy val array2setLastChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[?]].map(j2Char).max)


  case class ResSeqOpt[T](
    tpe: String,

    sql2listOpt: (RS, ParamIndex) => Option[List[T]],
    seq2sqlArray: Seq[T] => String,
    seq2sqls: Seq[T] => Seq[String],
    one2sql: T => String,
    one2json: T => String
  )

  lazy val resOptSeqId            : ResSeqOpt[Long]           = ResSeqOpt("Long", sql2listOptId, seq2sqlArrayId, seq2sqlsId, one2sqlId, one2jsonId)
  lazy val resOptSeqString        : ResSeqOpt[String]         = ResSeqOpt("String", sql2listOptString, seq2sqlArrayString, seq2sqlsString, one2sqlString, one2jsonString)
  lazy val resOptSeqInt           : ResSeqOpt[Int]            = ResSeqOpt("Int", sql2listOptInt, seq2sqlArrayInt, seq2sqlsInt, one2sqlInt, one2jsonInt)
  lazy val resOptSeqLong          : ResSeqOpt[Long]           = ResSeqOpt("Long", sql2listOptLong, seq2sqlArrayLong, seq2sqlsLong, one2sqlLong, one2jsonLong)
  lazy val resOptSeqFloat         : ResSeqOpt[Float]          = ResSeqOpt("Float", sql2listOptFloat, seq2sqlArrayFloat, seq2sqlsFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptSeqDouble        : ResSeqOpt[Double]         = ResSeqOpt("Double", sql2listOptDouble, seq2sqlArrayDouble, seq2sqlsDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptSeqBoolean       : ResSeqOpt[Boolean]        = ResSeqOpt("Boolean", sql2listOptBoolean, seq2sqlArrayBoolean, seq2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptSeqBigInt        : ResSeqOpt[BigInt]         = ResSeqOpt("BigInt", sql2listOptBigInt, seq2sqlArrayBigInt, seq2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptSeqBigDecimal    : ResSeqOpt[BigDecimal]     = ResSeqOpt("BigDecimal", sql2listOptBigDecimal, seq2sqlArrayBigDecimal, seq2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptSeqDate          : ResSeqOpt[Date]           = ResSeqOpt("Date", sql2listOptDate, seq2sqlArrayDate, seq2sqlsDate, one2sqlDate, one2jsonDate)
  lazy val resOptSeqDuration      : ResSeqOpt[Duration]       = ResSeqOpt("Duration", sql2listOptDuration, seq2sqlArrayDuration, seq2sqlsDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptSeqInstant       : ResSeqOpt[Instant]        = ResSeqOpt("Instant", sql2listOptInstant, seq2sqlArrayInstant, seq2sqlsInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptSeqLocalDate     : ResSeqOpt[LocalDate]      = ResSeqOpt("LocalDate", sql2listOptLocalDate, seq2sqlArrayLocalDate, seq2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptSeqLocalTime     : ResSeqOpt[LocalTime]      = ResSeqOpt("LocalTime", sql2listOptLocalTime, seq2sqlArrayLocalTime, seq2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptSeqLocalDateTime : ResSeqOpt[LocalDateTime]  = ResSeqOpt("LocalDateTime", sql2listOptLocalDateTime, seq2sqlArrayLocalDateTime, seq2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptSeqOffsetTime    : ResSeqOpt[OffsetTime]     = ResSeqOpt("OffsetTime", sql2listOptOffsetTime, seq2sqlArrayOffsetTime, seq2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptSeqOffsetDateTime: ResSeqOpt[OffsetDateTime] = ResSeqOpt("OffsetDateTime", sql2listOptOffsetDateTime, seq2sqlArrayOffsetDateTime, seq2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptSeqZonedDateTime : ResSeqOpt[ZonedDateTime]  = ResSeqOpt("ZonedDateTime", sql2listOptZonedDateTime, seq2sqlArrayZonedDateTime, seq2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptSeqUUID          : ResSeqOpt[UUID]           = ResSeqOpt("UUID", sql2listOptUUID, seq2sqlArrayUUID, seq2sqlsUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptSeqURI           : ResSeqOpt[URI]            = ResSeqOpt("URI", sql2listOptURI, seq2sqlArrayURI, seq2sqlsURI, one2sqlURI, one2jsonURI)
  lazy val resOptSeqByte          : ResSeqOpt[Byte]           = ResSeqOpt("Byte", sql2listOptByte, seq2sqlArrayByte, seq2sqlsByte, one2sqlByte, one2jsonByte)
  lazy val resOptSeqShort         : ResSeqOpt[Short]          = ResSeqOpt("Short", sql2listOptShort, seq2sqlArrayShort, seq2sqlsShort, one2sqlShort, one2jsonShort)
  lazy val resOptSeqChar          : ResSeqOpt[Char]           = ResSeqOpt("Char", sql2listOptChar, seq2sqlArrayChar, seq2sqlsChar, one2sqlChar, one2jsonChar)


  private def sql2listOpt[T](row: RS, paramIndex: Int, getValue: RS => T): Option[List[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Option.empty[List[T]]
    } else {
      val arrayResultSet = array.getResultSet
      val buf            = ListBuffer.empty[T]
      while (arrayResultSet.next()) {
        buf += getValue(arrayResultSet)
      }
      if (buf.isEmpty) Option.empty[List[T]] else Some(buf.toList)
    }
  }

  private lazy val sql2listOptId            : (RS, Int) => Option[List[Long]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueId)
  private lazy val sql2listOptString        : (RS, Int) => Option[List[String]]         = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueString)
  private lazy val sql2listOptInt           : (RS, Int) => Option[List[Int]]            = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueInt)
  private lazy val sql2listOptLong          : (RS, Int) => Option[List[Long]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueLong)
  private lazy val sql2listOptFloat         : (RS, Int) => Option[List[Float]]          = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueFloat)
  private lazy val sql2listOptDouble        : (RS, Int) => Option[List[Double]]         = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueDouble)
  private lazy val sql2listOptBoolean       : (RS, Int) => Option[List[Boolean]]        = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueBoolean)
  private lazy val sql2listOptBigInt        : (RS, Int) => Option[List[BigInt]]         = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueBigInt)
  private lazy val sql2listOptBigDecimal    : (RS, Int) => Option[List[BigDecimal]]     = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueBigDecimal)
  private lazy val sql2listOptDate          : (RS, Int) => Option[List[Date]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueDate)
  private lazy val sql2listOptDuration      : (RS, Int) => Option[List[Duration]]       = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueDuration)
  private lazy val sql2listOptInstant       : (RS, Int) => Option[List[Instant]]        = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueInstant)
  private lazy val sql2listOptLocalDate     : (RS, Int) => Option[List[LocalDate]]      = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueLocalDate)
  private lazy val sql2listOptLocalTime     : (RS, Int) => Option[List[LocalTime]]      = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueLocalTime)
  private lazy val sql2listOptLocalDateTime : (RS, Int) => Option[List[LocalDateTime]]  = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueLocalDateTime)
  private lazy val sql2listOptOffsetTime    : (RS, Int) => Option[List[OffsetTime]]     = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueOffsetTime)
  private lazy val sql2listOptOffsetDateTime: (RS, Int) => Option[List[OffsetDateTime]] = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2listOptZonedDateTime : (RS, Int) => Option[List[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueZonedDateTime)
  private lazy val sql2listOptUUID          : (RS, Int) => Option[List[UUID]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueUUID)
  private lazy val sql2listOptURI           : (RS, Int) => Option[List[URI]]            = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueURI)
  private lazy val sql2listOptByte          : (RS, Int) => Option[List[Byte]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueByte)
  private lazy val sql2listOptShort         : (RS, Int) => Option[List[Short]]          = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueShort)
  private lazy val sql2listOptChar          : (RS, Int) => Option[List[Char]]           = (row: RS, paramIndex: Int) => sql2listOpt(row, paramIndex, valueChar)
}
