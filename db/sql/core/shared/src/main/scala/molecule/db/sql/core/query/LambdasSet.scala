package molecule.db.sql.core.query

import java.lang.Short as jShort
import java.math.BigDecimal as jBigDecimal
import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.db.core.util.JavaConversions

trait LambdasSet extends LambdasBase with JavaConversions { self: SqlQueryBase =>

  private lazy val sql2setId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueId)
  private lazy val sql2setString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
  private lazy val sql2setInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
  private lazy val sql2setLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
  private lazy val sql2setFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
  private lazy val sql2setDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
  private lazy val sql2setBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
  private lazy val sql2setBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
  private lazy val sql2setBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
  private lazy val sql2setDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
  private lazy val sql2setDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDuration)
  private lazy val sql2setInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInstant)
  private lazy val sql2setLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDate)
  private lazy val sql2setLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalTime)
  private lazy val sql2setLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDateTime)
  private lazy val sql2setOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetTime)
  private lazy val sql2setOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2setZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueZonedDateTime)
  private lazy val sql2setUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
  private lazy val sql2setURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
  private lazy val sql2setByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
  private lazy val sql2setShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
  private lazy val sql2setChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)

  case class ResSet[T](
    tpe: String,
    tpeDb: String,
    sql2set: (RS, ParamIndex) => Set[T],
    sql2setOrNull: (RS, ParamIndex) => Any, // Allow null in optional nested rows
    set2sqlArray: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
    array2set: (RS, ParamIndex) => Set[T],
    nestedArray2coalescedSet: (RS, ParamIndex) => Set[T],
    nestedArray2optCoalescedSet: (RS, ParamIndex) => Option[Set[T]],
    json2array: String => Array[T],
    json2optArray: String => Option[Array[T]],
    one2json: T => String,
    array2optSet: (RS, ParamIndex) => Option[Set[T]]
  )

  lazy val resSetId            : ResSet[Long]           = ResSet("Long", tpeDbId, sql2setId, null, set2sqlArrayId, set2sqlsId, one2sqlId, array2setId, nestedArray2coalescedSetId, nestedArray2optCoalescedSetId, json2arrayId, json2optArrayId, one2jsonId, array2optSetId)
  lazy val resSetString        : ResSet[String]         = ResSet("String", tpeDbString, sql2setString, null, set2sqlArrayString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2optCoalescedSetString, json2arrayString, json2optArrayString, one2jsonString, array2optSetString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", tpeDbInt, sql2setInt, null, set2sqlArrayInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2optCoalescedSetInt, json2arrayInt, json2optArrayInt, one2jsonInt, array2optSetInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", tpeDbLong, sql2setLong, null, set2sqlArrayLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2optCoalescedSetLong, json2arrayLong, json2optArrayLong, one2jsonLong, array2optSetLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", tpeDbFloat, sql2setFloat, null, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2optCoalescedSetFloat, json2arrayFloat, json2optArrayFloat, one2jsonFloat, array2optSetFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", tpeDbDouble, sql2setDouble, null, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2optCoalescedSetDouble, json2arrayDouble, json2optArrayDouble, one2jsonDouble, array2optSetDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", tpeDbBoolean, sql2setBoolean, null, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2optCoalescedSetBoolean, json2arrayBoolean, json2optArrayBoolean, one2jsonBoolean, array2optSetBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", tpeDbBigInt, sql2setBigInt, null, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2optCoalescedSetBigInt, json2arrayBigInt, json2optArrayBigInt, one2jsonBigInt, array2optSetBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", tpeDbBigDecimal, sql2setBigDecimal, null, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2optCoalescedSetBigDecimal, json2arrayBigDecimal, json2optArrayBigDecimal, one2jsonBigDecimal, array2optSetBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", tpeDbDate, sql2setDate, null, set2sqlArrayDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2optCoalescedSetDate, json2arrayDate, json2optArrayDate, one2jsonDate, array2optSetDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", tpeDbDuration, sql2setDuration, null, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, array2setDuration, nestedArray2coalescedSetDuration, nestedArray2optCoalescedSetDuration, json2arrayDuration, json2optArrayDuration, one2jsonDuration, array2optSetDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", tpeDbInstant, sql2setInstant, null, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, array2setInstant, nestedArray2coalescedSetInstant, nestedArray2optCoalescedSetInstant, json2arrayInstant, json2optArrayInstant, one2jsonInstant, array2optSetInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", tpeDbLocalDate, sql2setLocalDate, null, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, array2setLocalDate, nestedArray2coalescedSetLocalDate, nestedArray2optCoalescedSetLocalDate, json2arrayLocalDate, json2optArrayLocalDate, one2jsonLocalDate, array2optSetLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", tpeDbLocalTime, sql2setLocalTime, null, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, array2setLocalTime, nestedArray2coalescedSetLocalTime, nestedArray2optCoalescedSetLocalTime, json2arrayLocalTime, json2optArrayLocalTime, one2jsonLocalTime, array2optSetLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", tpeDbLocalDateTime, sql2setLocalDateTime, null, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, array2setLocalDateTime, nestedArray2coalescedSetLocalDateTime, nestedArray2optCoalescedSetLocalDateTime, json2arrayLocalDateTime, json2optArrayLocalDateTime, one2jsonLocalDateTime, array2optSetLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", tpeDbOffsetTime, sql2setOffsetTime, null, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, array2setOffsetTime, nestedArray2coalescedSetOffsetTime, nestedArray2optCoalescedSetOffsetTime, json2arrayOffsetTime, json2optArrayOffsetTime, one2jsonOffsetTime, array2optSetOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", tpeDbOffsetDateTime, sql2setOffsetDateTime, null, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, array2setOffsetDateTime, nestedArray2coalescedSetOffsetDateTime, nestedArray2optCoalescedSetOffsetDateTime, json2arrayOffsetDateTime, json2optArrayOffsetDateTime, one2jsonOffsetDateTime, array2optSetOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", tpeDbZonedDateTime, sql2setZonedDateTime, null, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, array2setZonedDateTime, nestedArray2coalescedSetZonedDateTime, nestedArray2optCoalescedSetZonedDateTime, json2arrayZonedDateTime, json2optArrayZonedDateTime, one2jsonZonedDateTime, array2optSetZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", tpeDbUUID, sql2setUUID, null, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2optCoalescedSetUUID, json2arrayUUID, json2optArrayUUID, one2jsonUUID, array2optSetUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", tpeDbURI, sql2setURI, null, set2sqlArrayURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2optCoalescedSetURI, json2arrayURI, json2optArrayURI, one2jsonURI, array2optSetURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", tpeDbByte, sql2setByte, null, set2sqlArrayByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2optCoalescedSetByte, json2arrayByte, json2optArrayByte, one2jsonByte, array2optSetByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", tpeDbShort, sql2setShort, null, set2sqlArrayShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2optCoalescedSetShort, json2arrayShort, json2optArrayShort, one2jsonShort, array2optSetShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", tpeDbChar, sql2setChar, null, set2sqlArrayChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2optCoalescedSetChar, json2arrayChar, json2optArrayChar, one2jsonChar, array2optSetChar)

  private lazy val set2sqlArrayId            : Set[Long] => String           = (set: Set[Long]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
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

  private lazy val set2sqlsId            : Set[Long] => Set[String]           = (set: Set[Long]) => set.map(_.toString)
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

  private def sqlNestedArrays2set[T](row: RS, paramIndex: Int, j2s: Any => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      var set                 = Set.empty[T]
      while (outerArrayResultSet.next()) {
        val array0 = outerArrayResultSet.getArray(2)
        // Account for empty Sets
        if (array0.getUnderlyingArray != null) {
          array0.getArray.asInstanceOf[Array[?]].foreach { value =>
            set += j2s(value)
          }
        }
      }
      set
    }
  }

  private lazy val nestedArray2coalescedSetId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Id)
  private lazy val nestedArray2coalescedSetString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String)
  private lazy val nestedArray2coalescedSetInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Int)
  private lazy val nestedArray2coalescedSetLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Long)
  private lazy val nestedArray2coalescedSetFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Float)
  private lazy val nestedArray2coalescedSetDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Double)
  private lazy val nestedArray2coalescedSetBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Boolean)
  private lazy val nestedArray2coalescedSetBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigInt)
  private lazy val nestedArray2coalescedSetBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2BigDecimal)
  private lazy val nestedArray2coalescedSetDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Date)
  private lazy val nestedArray2coalescedSetDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Duration)
  private lazy val nestedArray2coalescedSetInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Instant)
  private lazy val nestedArray2coalescedSetLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDate)
  private lazy val nestedArray2coalescedSetLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalTime)
  private lazy val nestedArray2coalescedSetLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2LocalDateTime)
  private lazy val nestedArray2coalescedSetOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetTime)
  private lazy val nestedArray2coalescedSetOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2OffsetDateTime)
  private lazy val nestedArray2coalescedSetZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2ZonedDateTime)
  private lazy val nestedArray2coalescedSetUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2UUID)
  private lazy val nestedArray2coalescedSetURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2String).map(v => new URI(v))
  private lazy val nestedArray2coalescedSetByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Byte)
  private lazy val nestedArray2coalescedSetShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Short)
  private lazy val nestedArray2coalescedSetChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => sqlNestedArrays2set(row, paramIndex, j2Char)

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


  case class ResSetOpt[T](
    sql2setOpt: (RS, ParamIndex) => Option[Set[T]],
    one2sql: T => String,
    one2json: T => String
  )

  lazy val resOptSetId            : ResSetOpt[Long]           = ResSetOpt(sql2setOptId, one2sqlId, one2jsonId)
  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt(sql2setOptString, one2sqlString, one2jsonString)
  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt(sql2setOptInt, one2sqlInt, one2jsonInt)
  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt(sql2setOptLong, one2sqlLong, one2jsonLong)
  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt(sql2setOptFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt(sql2setOptDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt(sql2setOptBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt(sql2setOptBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt(sql2setOptBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt(sql2setOptDate, one2sqlDate, one2jsonDate)
  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt(sql2setOptDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt(sql2setOptInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt(sql2setOptLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt(sql2setOptLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt(sql2setOptLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt(sql2setOptOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt(sql2setOptOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt(sql2setOptZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt(sql2setOptUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt(sql2setOptURI, one2sqlURI, one2jsonURI)
  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt(sql2setOptByte, one2sqlByte, one2jsonByte)
  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt(sql2setOptShort, one2sqlShort, one2jsonShort)
  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt(sql2setOptChar, one2sqlChar, one2jsonChar)


  private def sql2setOpt[T](row: RS, paramIndex: Int, getValue: RS => T): Option[Set[T]] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Option.empty[Set[T]]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        set += getValue(arrayResultSet)
      }
      if (set.isEmpty || set == Set(0L)) Option.empty[Set[T]] else Some(set)
    }
  }

  private lazy val sql2setOptId            : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueId)
  private lazy val sql2setOptString        : (RS, Int) => Option[Set[String]]         = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueString)
  private lazy val sql2setOptInt           : (RS, Int) => Option[Set[Int]]            = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInt)
  private lazy val sql2setOptLong          : (RS, Int) => Option[Set[Long]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLong)
  private lazy val sql2setOptFloat         : (RS, Int) => Option[Set[Float]]          = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueFloat)
  private lazy val sql2setOptDouble        : (RS, Int) => Option[Set[Double]]         = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDouble)
  private lazy val sql2setOptBoolean       : (RS, Int) => Option[Set[Boolean]]        = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBoolean)
  private lazy val sql2setOptBigInt        : (RS, Int) => Option[Set[BigInt]]         = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigInt)
  private lazy val sql2setOptBigDecimal    : (RS, Int) => Option[Set[BigDecimal]]     = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigDecimal)
  private lazy val sql2setOptDate          : (RS, Int) => Option[Set[Date]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDate)
  private lazy val sql2setOptDuration      : (RS, Int) => Option[Set[Duration]]       = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDuration)
  private lazy val sql2setOptInstant       : (RS, Int) => Option[Set[Instant]]        = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInstant)
  private lazy val sql2setOptLocalDate     : (RS, Int) => Option[Set[LocalDate]]      = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDate)
  private lazy val sql2setOptLocalTime     : (RS, Int) => Option[Set[LocalTime]]      = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalTime)
  private lazy val sql2setOptLocalDateTime : (RS, Int) => Option[Set[LocalDateTime]]  = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDateTime)
  private lazy val sql2setOptOffsetTime    : (RS, Int) => Option[Set[OffsetTime]]     = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetTime)
  private lazy val sql2setOptOffsetDateTime: (RS, Int) => Option[Set[OffsetDateTime]] = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetDateTime)
  private lazy val sql2setOptZonedDateTime : (RS, Int) => Option[Set[ZonedDateTime]]  = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueZonedDateTime)
  private lazy val sql2setOptUUID          : (RS, Int) => Option[Set[UUID]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueUUID)
  private lazy val sql2setOptURI           : (RS, Int) => Option[Set[URI]]            = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueURI)
  private lazy val sql2setOptByte          : (RS, Int) => Option[Set[Byte]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueByte)
  private lazy val sql2setOptShort         : (RS, Int) => Option[Set[Short]]          = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueShort)
  private lazy val sql2setOptChar          : (RS, Int) => Option[Set[Char]]           = (row: RS, paramIndex: Int) => sql2setOpt(row, paramIndex, valueChar)
}
