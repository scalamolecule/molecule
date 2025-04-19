package molecule.sql.core.query

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import boopickle.Default.*
import molecule.base.util.BaseHelpers
import molecule.core.transformation.JsonBase
import molecule.core.util.AggrUtils
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag


trait LambdasBase extends BaseHelpers with AggrUtils with JsonBase { self: SqlQueryBase =>

  protected lazy val one2sqlId            : Long => String           = (v: Long) => s"$v"
  protected lazy val one2sqlString        : String => String         = (v: String) => s"'${v.replace("'", "''")}'"
  protected lazy val one2sqlInt           : Int => String            = (v: Int) => s"$v"
  protected lazy val one2sqlLong          : Long => String           = (v: Long) => s"$v"
  protected lazy val one2sqlFloat         : Float => String          = (v: Float) => s"$v"
  protected lazy val one2sqlDouble        : Double => String         = (v: Double) => s"$v"
  protected lazy val one2sqlBoolean       : Boolean => String        = (v: Boolean) => s"$v"
  protected lazy val one2sqlBigInt        : BigInt => String         = (v: BigInt) => s"'$v'"
  protected lazy val one2sqlBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"'$v'"
  protected lazy val one2sqlDate          : Date => String           = (v: Date) => s"${v.getTime}"
  protected lazy val one2sqlDuration      : Duration => String       = (v: Duration) => s"'${v.toString}'"
  protected lazy val one2sqlInstant       : Instant => String        = (v: Instant) => s"'${v.toString}'"
  protected lazy val one2sqlLocalDate     : LocalDate => String      = (v: LocalDate) => s"'${v.toString}'"
  protected lazy val one2sqlLocalTime     : LocalTime => String      = (v: LocalTime) => s"'${v.toString}'"
  protected lazy val one2sqlLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => s"'${v.toString}'"
  protected lazy val one2sqlOffsetTime    : OffsetTime => String     = (v: OffsetTime) => s"'${v.toString}'"
  protected lazy val one2sqlOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => s"'${v.toString}'"
  protected lazy val one2sqlZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => s"'${v.toString}'"
  protected lazy val one2sqlUUID          : UUID => String           = (v: UUID) => s"'${v.toString}'"
  protected lazy val one2sqlURI           : URI => String            = (v: URI) => s"'${v.toString.replace("'", "''")}'"
  protected lazy val one2sqlByte          : Byte => String           = (v: Byte) => s"$v"
  protected lazy val one2sqlShort         : Short => String          = (v: Short) => s"$v"
  protected lazy val one2sqlChar          : Char => String           = (v: Char) => s"'${v.toString}'"


  lazy val toInt: (RS, Int) => Int = (row: RS, paramIndex: Int) => row.getLong(paramIndex).toInt

  protected def sqlArray2set[T](row: RS, paramIndex: Int, getValue: RS => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        val value = getValue(arrayResultSet)
        if (!arrayResultSet.wasNull()) {
          set += value
        }
      }
      set
    }
  }

  protected def sqlArray2list[T](row: RS, paramIndex: Int, getValue: RS => T): List[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      List.empty[T]
    } else {
      val arrayResultSet = array.getResultSet
      val buf            = ListBuffer.empty[T]
      while (arrayResultSet.next()) {
        val value = getValue(arrayResultSet)
        if (!arrayResultSet.wasNull()) {
          buf += value
        }
      }
      buf.toList
    }
  }

  protected def sqlJson2map[T](row: RS, paramIndex: Int, json2map: String => Map[String, T]): Map[String, T] = {
    val byteArray = row.getBytes(paramIndex)
    if (row.wasNull()) {
      Map.empty[String, T]
    } else {
      json2map(new String(byteArray))
    }
  }

  protected lazy val valueId            : RS => Long           = (rs: RS) => rs.getLong(2)
  protected lazy val valueString        : RS => String         = (rs: RS) => rs.getString(2)
  protected lazy val valueInt           : RS => Int            = (rs: RS) => rs.getInt(2)
  protected lazy val valueLong          : RS => Long           = (rs: RS) => rs.getLong(2)
  protected lazy val valueFloat         : RS => Float          = (rs: RS) => rs.getFloat(2)
  protected lazy val valueDouble        : RS => Double         = (rs: RS) => rs.getDouble(2)
  protected lazy val valueBoolean       : RS => Boolean        = (rs: RS) => rs.getBoolean(2)
  protected lazy val valueBigInt        : RS => BigInt         = (rs: RS) => rs.getBigDecimal(2).toBigInteger
  protected lazy val valueBigDecimal    : RS => BigDecimal     = (rs: RS) => rs.getBigDecimal(2)
  protected lazy val valueDate          : RS => Date           = (rs: RS) => new Date(rs.getLong(2))
  protected lazy val valueDuration      : RS => Duration       = (rs: RS) => Duration.parse(rs.getString(2))
  protected lazy val valueInstant       : RS => Instant        = (rs: RS) => Instant.parse(rs.getString(2))
  protected lazy val valueLocalDate     : RS => LocalDate      = (rs: RS) => LocalDate.parse(rs.getString(2))
  protected lazy val valueLocalTime     : RS => LocalTime      = (rs: RS) => LocalTime.parse(rs.getString(2))
  protected lazy val valueLocalDateTime : RS => LocalDateTime  = (rs: RS) => LocalDateTime.parse(rs.getString(2))
  protected lazy val valueOffsetTime    : RS => OffsetTime     = (rs: RS) => OffsetTime.parse(rs.getString(2))
  protected lazy val valueOffsetDateTime: RS => OffsetDateTime = (rs: RS) => OffsetDateTime.parse(rs.getString(2))
  protected lazy val valueZonedDateTime : RS => ZonedDateTime  = (rs: RS) => ZonedDateTime.parse(rs.getString(2))
  protected lazy val valueUUID          : RS => UUID           = (rs: RS) => UUID.fromString(rs.getString(2))
  protected lazy val valueURI           : RS => URI            = (rs: RS) => new URI(rs.getString(2))
  protected lazy val valueByte          : RS => Byte           = (rs: RS) => rs.getByte(2)
  protected lazy val valueShort         : RS => Short          = (rs: RS) => rs.getShort(2)
  protected lazy val valueChar          : RS => Char           = (rs: RS) => rs.getString(2).charAt(0)


  protected lazy val array2setId            : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueId)
  protected lazy val array2setString        : (RS, Int) => Set[String]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
  protected lazy val array2setInt           : (RS, Int) => Set[Int]            = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
  protected lazy val array2setLong          : (RS, Int) => Set[Long]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
  protected lazy val array2setFloat         : (RS, Int) => Set[Float]          = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
  protected lazy val array2setDouble        : (RS, Int) => Set[Double]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
  protected lazy val array2setBoolean       : (RS, Int) => Set[Boolean]        = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
  protected lazy val array2setBigInt        : (RS, Int) => Set[BigInt]         = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
  protected lazy val array2setBigDecimal    : (RS, Int) => Set[BigDecimal]     = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
  protected lazy val array2setDate          : (RS, Int) => Set[Date]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
  protected lazy val array2setDuration      : (RS, Int) => Set[Duration]       = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDuration)
  protected lazy val array2setInstant       : (RS, Int) => Set[Instant]        = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInstant)
  protected lazy val array2setLocalDate     : (RS, Int) => Set[LocalDate]      = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDate)
  protected lazy val array2setLocalTime     : (RS, Int) => Set[LocalTime]      = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalTime)
  protected lazy val array2setLocalDateTime : (RS, Int) => Set[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDateTime)
  protected lazy val array2setOffsetTime    : (RS, Int) => Set[OffsetTime]     = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetTime)
  protected lazy val array2setOffsetDateTime: (RS, Int) => Set[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetDateTime)
  protected lazy val array2setZonedDateTime : (RS, Int) => Set[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueZonedDateTime)
  protected lazy val array2setUUID          : (RS, Int) => Set[UUID]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
  protected lazy val array2setURI           : (RS, Int) => Set[URI]            = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
  protected lazy val array2setByte          : (RS, Int) => Set[Byte]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
  protected lazy val array2setShort         : (RS, Int) => Set[Short]          = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
  protected lazy val array2setChar          : (RS, Int) => Set[Char]           = (row: RS, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)

  protected lazy val array2listId            : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueId)
  protected lazy val array2listString        : (RS, Int) => List[String]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueString)
  protected lazy val array2listInt           : (RS, Int) => List[Int]            = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueInt)
  protected lazy val array2listLong          : (RS, Int) => List[Long]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLong)
  protected lazy val array2listFloat         : (RS, Int) => List[Float]          = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueFloat)
  protected lazy val array2listDouble        : (RS, Int) => List[Double]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDouble)
  protected lazy val array2listBoolean       : (RS, Int) => List[Boolean]        = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBoolean)
  protected lazy val array2listBigInt        : (RS, Int) => List[BigInt]         = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBigInt)
  protected lazy val array2listBigDecimal    : (RS, Int) => List[BigDecimal]     = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueBigDecimal)
  protected lazy val array2listDate          : (RS, Int) => List[Date]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDate)
  protected lazy val array2listDuration      : (RS, Int) => List[Duration]       = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueDuration)
  protected lazy val array2listInstant       : (RS, Int) => List[Instant]        = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueInstant)
  protected lazy val array2listLocalDate     : (RS, Int) => List[LocalDate]      = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalDate)
  protected lazy val array2listLocalTime     : (RS, Int) => List[LocalTime]      = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalTime)
  protected lazy val array2listLocalDateTime : (RS, Int) => List[LocalDateTime]  = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueLocalDateTime)
  protected lazy val array2listOffsetTime    : (RS, Int) => List[OffsetTime]     = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueOffsetTime)
  protected lazy val array2listOffsetDateTime: (RS, Int) => List[OffsetDateTime] = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueOffsetDateTime)
  protected lazy val array2listZonedDateTime : (RS, Int) => List[ZonedDateTime]  = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueZonedDateTime)
  protected lazy val array2listUUID          : (RS, Int) => List[UUID]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueUUID)
  protected lazy val array2listURI           : (RS, Int) => List[URI]            = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueURI)
  protected lazy val array2listByte          : (RS, Int) => List[Byte]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueByte)
  protected lazy val array2listShort         : (RS, Int) => List[Short]          = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueShort)
  protected lazy val array2listChar          : (RS, Int) => List[Char]           = (row: RS, paramIndex: Int) => sqlArray2list(row, paramIndex, valueChar)


  protected lazy val json2oneId            : String => Long           = (v: String) => v.toLong
  protected lazy val json2oneString        : String => String         = (v: String) => v
  protected lazy val json2oneInt           : String => Int            = (v: String) => v.toInt
  protected lazy val json2oneLong          : String => Long           = (v: String) => v.toLong
  protected lazy val json2oneFloat         : String => Float          = (v: String) => v.toFloat
  protected lazy val json2oneDouble        : String => Double         = (v: String) => v.toDouble
  protected lazy val json2oneBoolean       : String => Boolean        = (v: String) => v == "1"
  protected lazy val json2oneBigInt        : String => BigInt         = (v: String) => BigInt(v)
  protected lazy val json2oneBigDecimal    : String => BigDecimal     = (v: String) => BigDecimal(v)
  protected lazy val json2oneDate          : String => Date           = (v: String) => new Date(v.toLong)
  protected lazy val json2oneDuration      : String => Duration       = (v: String) => Duration.parse(v)
  protected lazy val json2oneInstant       : String => Instant        = (v: String) => Instant.parse(v)
  protected lazy val json2oneLocalDate     : String => LocalDate      = (v: String) => LocalDate.parse(v)
  protected lazy val json2oneLocalTime     : String => LocalTime      = (v: String) => LocalTime.parse(v)
  protected lazy val json2oneLocalDateTime : String => LocalDateTime  = (v: String) => LocalDateTime.parse(v)
  protected lazy val json2oneOffsetTime    : String => OffsetTime     = (v: String) => OffsetTime.parse(v)
  protected lazy val json2oneOffsetDateTime: String => OffsetDateTime = (v: String) => OffsetDateTime.parse(v)
  protected lazy val json2oneZonedDateTime : String => ZonedDateTime  = (v: String) => ZonedDateTime.parse(v)
  protected lazy val json2oneUUID          : String => UUID           = (v: String) => UUID.fromString(v)
  protected lazy val json2oneURI           : String => URI            = (v: String) => new URI(v)
  protected lazy val json2oneByte          : String => Byte           = (v: String) => v.toByte
  protected lazy val json2oneShort         : String => Short          = (v: String) => v.toShort
  protected lazy val json2oneChar          : String => Char           = (v: String) => v.charAt(0)


  protected def jsonArray2optArray[T: ClassTag](array: Array[String], decode: String => T): Option[Array[T]] = {
    if (array.isEmpty) None else {
      val vs = array.flatMap {
        case "null" => None
        case v      => Some(decode(v))
      }
      if (vs.nonEmpty) Some(vs) else None
    }
  }

  protected lazy val json2optArrayId            : String => Option[Array[Long]]           = (json: String) => jsonArray2optArray(jsonArrayId(json), json2oneId)
  protected lazy val json2optArrayString        : String => Option[Array[String]]         = (json: String) => jsonArray2optArray(jsonArrayString(json), json2oneString)
  protected lazy val json2optArrayInt           : String => Option[Array[Int]]            = (json: String) => jsonArray2optArray(jsonArrayInt(json), json2oneInt)
  protected lazy val json2optArrayLong          : String => Option[Array[Long]]           = (json: String) => jsonArray2optArray(jsonArrayLong(json), json2oneLong)
  protected lazy val json2optArrayFloat         : String => Option[Array[Float]]          = (json: String) => jsonArray2optArray(jsonArrayFloat(json), json2oneFloat)
  protected lazy val json2optArrayDouble        : String => Option[Array[Double]]         = (json: String) => jsonArray2optArray(jsonArrayDouble(json), json2oneDouble)
  protected lazy val json2optArrayBoolean       : String => Option[Array[Boolean]]        = (json: String) => jsonArray2optArray(jsonArrayBoolean(json), json2oneBoolean)
  protected lazy val json2optArrayBigInt        : String => Option[Array[BigInt]]         = (json: String) => jsonArray2optArray(jsonArrayBigInt(json), json2oneBigInt)
  protected lazy val json2optArrayBigDecimal    : String => Option[Array[BigDecimal]]     = (json: String) => jsonArray2optArray(jsonArrayBigDecimal(json), json2oneBigDecimal)
  protected lazy val json2optArrayDate          : String => Option[Array[Date]]           = (json: String) => jsonArray2optArray(jsonArrayDate(json), json2oneDate)
  protected lazy val json2optArrayDuration      : String => Option[Array[Duration]]       = (json: String) => jsonArray2optArray(jsonArrayDuration(json), json2oneDuration)
  protected lazy val json2optArrayInstant       : String => Option[Array[Instant]]        = (json: String) => jsonArray2optArray(jsonArrayInstant(json), json2oneInstant)
  protected lazy val json2optArrayLocalDate     : String => Option[Array[LocalDate]]      = (json: String) => jsonArray2optArray(jsonArrayLocalDate(json), json2oneLocalDate)
  protected lazy val json2optArrayLocalTime     : String => Option[Array[LocalTime]]      = (json: String) => jsonArray2optArray(jsonArrayLocalTime(json), json2oneLocalTime)
  protected lazy val json2optArrayLocalDateTime : String => Option[Array[LocalDateTime]]  = (json: String) => jsonArray2optArray(jsonArrayLocalDateTime(json), json2oneLocalDateTime)
  protected lazy val json2optArrayOffsetTime    : String => Option[Array[OffsetTime]]     = (json: String) => jsonArray2optArray(jsonArrayOffsetTime(json), json2oneOffsetTime)
  protected lazy val json2optArrayOffsetDateTime: String => Option[Array[OffsetDateTime]] = (json: String) => jsonArray2optArray(jsonArrayOffsetDateTime(json), json2oneOffsetDateTime)
  protected lazy val json2optArrayZonedDateTime : String => Option[Array[ZonedDateTime]]  = (json: String) => jsonArray2optArray(jsonArrayZonedDateTime(json), json2oneZonedDateTime)
  protected lazy val json2optArrayUUID          : String => Option[Array[UUID]]           = (json: String) => jsonArray2optArray(jsonArrayUUID(json), json2oneUUID)
  protected lazy val json2optArrayURI           : String => Option[Array[URI]]            = (json: String) => jsonArray2optArray(jsonArrayURI(json), json2oneURI)
  protected lazy val json2optArrayByte          : String => Option[Array[Byte]]           = (json: String) => jsonArray2optArray(jsonArrayByte(json), json2oneByte)
  protected lazy val json2optArrayShort         : String => Option[Array[Short]]          = (json: String) => jsonArray2optArray(jsonArrayShort(json), json2oneShort)
  protected lazy val json2optArrayChar          : String => Option[Array[Char]]           = (json: String) => jsonArray2optArray(jsonArrayChar(json), json2oneChar)


  private def trimString(json: String): String = {
    val length = json.length
    (json(1), json(length - 2)) match {
      case ('"', '"') => json.substring(2, length - 2) // ["x", ..., "y"]
      case ('"', _)   => json.substring(2, length - 1) // ["x", ..., null]
      case (_, '"')   => json.substring(1, length - 2) // [null, ..., "y"]
      case (_, _)     => json.substring(1, length - 1) // [null, ..., null]
    }
  }
  protected def strings(json: String): Array[String] = trimString(json).split("\", ?\"")
  protected def elements(json: String): Array[String] = if (json == null)
    Array.empty[String] // Optional entity is empty for Seq types
  else
    json.substring(1, json.length - 1).split(", ?")

  protected lazy val jsonArrayId            : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayString        : String => Array[String] = (json: String) => strings(json) // todo: un-escape quotes in strings
  protected lazy val jsonArrayInt           : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayLong          : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayFloat         : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayDouble        : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayBoolean       : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayBigInt        : String => Array[String] = (json: String) => if (json(1) == '"') strings(json) else elements(json)
  protected lazy val jsonArrayBigDecimal    : String => Array[String] = (json: String) => if (json(1) == '"') strings(json) else elements(json)
  protected lazy val jsonArrayDate          : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayDuration      : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayInstant       : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayLocalDate     : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayLocalTime     : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayLocalDateTime : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayOffsetTime    : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayOffsetDateTime: String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayZonedDateTime : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayUUID          : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayURI           : String => Array[String] = (json: String) => strings(json)
  protected lazy val jsonArrayByte          : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayShort         : String => Array[String] = (json: String) => elements(json)
  protected lazy val jsonArrayChar          : String => Array[String] = (json: String) => strings(json)

  protected def jsonArray2array[T: ClassTag](array: Array[String], decode: String => T): Array[T] = {
    array.flatMap {
      case "null" => None
      case v      => Some(decode(v))
    }
  }

  protected lazy val json2arrayId            : String => Array[Long]           = (json: String) => if (json == "[null]") Array.empty[Long] else jsonArray2array(jsonArrayId(json), json2oneId)
  protected lazy val json2arrayString        : String => Array[String]         = (json: String) => if (json == "[null]") Array.empty[String] else jsonArray2array(jsonArrayString(json), json2oneString)
  protected lazy val json2arrayInt           : String => Array[Int]            = (json: String) => if (json == "[null]") Array.empty[Int] else jsonArray2array(jsonArrayInt(json), json2oneInt)
  protected lazy val json2arrayLong          : String => Array[Long]           = (json: String) => if (json == "[null]") Array.empty[Long] else jsonArray2array(jsonArrayLong(json), json2oneLong)
  protected lazy val json2arrayFloat         : String => Array[Float]          = (json: String) => if (json == "[null]") Array.empty[Float] else jsonArray2array(jsonArrayFloat(json), json2oneFloat)
  protected lazy val json2arrayDouble        : String => Array[Double]         = (json: String) => if (json == "[null]") Array.empty[Double] else jsonArray2array(jsonArrayDouble(json), json2oneDouble)
  protected lazy val json2arrayBoolean       : String => Array[Boolean]        = (json: String) => if (json == "[null]") Array.empty[Boolean] else jsonArray2array(jsonArrayBoolean(json), json2oneBoolean)
  protected lazy val json2arrayBigInt        : String => Array[BigInt]         = (json: String) => if (json == "[null]") Array.empty[BigInt] else jsonArray2array(jsonArrayBigInt(json), json2oneBigInt)
  protected lazy val json2arrayBigDecimal    : String => Array[BigDecimal]     = (json: String) => if (json == "[null]") Array.empty[BigDecimal] else jsonArray2array(jsonArrayBigDecimal(json), json2oneBigDecimal)
  protected lazy val json2arrayDate          : String => Array[Date]           = (json: String) => if (json == "[null]") Array.empty[Date] else jsonArray2array(jsonArrayDate(json), json2oneDate)
  protected lazy val json2arrayDuration      : String => Array[Duration]       = (json: String) => if (json == "[null]") Array.empty[Duration] else jsonArray2array(jsonArrayDuration(json), json2oneDuration)
  protected lazy val json2arrayInstant       : String => Array[Instant]        = (json: String) => if (json == "[null]") Array.empty[Instant] else jsonArray2array(jsonArrayInstant(json), json2oneInstant)
  protected lazy val json2arrayLocalDate     : String => Array[LocalDate]      = (json: String) => if (json == "[null]") Array.empty[LocalDate] else jsonArray2array(jsonArrayLocalDate(json), json2oneLocalDate)
  protected lazy val json2arrayLocalTime     : String => Array[LocalTime]      = (json: String) => if (json == "[null]") Array.empty[LocalTime] else jsonArray2array(jsonArrayLocalTime(json), json2oneLocalTime)
  protected lazy val json2arrayLocalDateTime : String => Array[LocalDateTime]  = (json: String) => if (json == "[null]") Array.empty[LocalDateTime] else jsonArray2array(jsonArrayLocalDateTime(json), json2oneLocalDateTime)
  protected lazy val json2arrayOffsetTime    : String => Array[OffsetTime]     = (json: String) => if (json == "[null]") Array.empty[OffsetTime] else jsonArray2array(jsonArrayOffsetTime(json), json2oneOffsetTime)
  protected lazy val json2arrayOffsetDateTime: String => Array[OffsetDateTime] = (json: String) => if (json == "[null]") Array.empty[OffsetDateTime] else jsonArray2array(jsonArrayOffsetDateTime(json), json2oneOffsetDateTime)
  protected lazy val json2arrayZonedDateTime : String => Array[ZonedDateTime]  = (json: String) => if (json == "[null]") Array.empty[ZonedDateTime] else jsonArray2array(jsonArrayZonedDateTime(json), json2oneZonedDateTime)
  protected lazy val json2arrayUUID          : String => Array[UUID]           = (json: String) => if (json == "[null]") Array.empty[UUID] else jsonArray2array(jsonArrayUUID(json), json2oneUUID)
  protected lazy val json2arrayURI           : String => Array[URI]            = (json: String) => if (json == "[null]") Array.empty[URI] else jsonArray2array(jsonArrayURI(json), json2oneURI)
  protected lazy val json2arrayByte          : String => Array[Byte]           = (json: String) => if (json == "[null]") Array.empty[Byte] else jsonArray2array(jsonArrayByte(json), json2oneByte)
  protected lazy val json2arrayShort         : String => Array[Short]          = (json: String) => if (json == "[null]") Array.empty[Short] else jsonArray2array(jsonArrayShort(json), json2oneShort)
  protected lazy val json2arrayChar          : String => Array[Char]           = (json: String) => if (json == "[null]") Array.empty[Char] else jsonArray2array(jsonArrayChar(json), json2oneChar)

  protected lazy val one2jsonId            : Long => String           = (v: Long) => s"$v"
  protected lazy val one2jsonString        : String => String         = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonInt           : Int => String            = (v: Int) => s"$v"
  protected lazy val one2jsonLong          : Long => String           = (v: Long) => s"$v"
  protected lazy val one2jsonFloat         : Float => String          = (v: Float) => s"$v"
  protected lazy val one2jsonDouble        : Double => String         = (v: Double) => s"$v"
  protected lazy val one2jsonBoolean       : Boolean => String        = (v: Boolean) => if (v) "1" else "0"
  protected lazy val one2jsonBigInt        : BigInt => String         = (v: BigInt) => "\"" + v + "\""
  protected lazy val one2jsonBigDecimal    : BigDecimal => String     = (v: BigDecimal) => "\"" + v + "\""
  protected lazy val one2jsonDate          : Date => String           = (v: Date) => v.getTime.toString
  protected lazy val one2jsonDuration      : Duration => String       = (v: Duration) => "\"" + v.toString + "\""
  protected lazy val one2jsonInstant       : Instant => String        = (v: Instant) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalDate     : LocalDate => String      = (v: LocalDate) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalTime     : LocalTime => String      = (v: LocalTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonOffsetTime    : OffsetTime => String     = (v: OffsetTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonUUID          : UUID => String           = (v: UUID) => "\"" + v.toString + "\""
  protected lazy val one2jsonURI           : URI => String            = (v: URI) => "\"" + v.toString.replace("'", "''") + "\""
  protected lazy val one2jsonByte          : Byte => String           = (v: Byte) => s"$v"
  protected lazy val one2jsonShort         : Short => String          = (v: Short) => s"$v"
  protected lazy val one2jsonChar          : Char => String           = (v: Char) => "\"" + v.toString + "\""

  protected lazy val tpeDbId            : String = ""
  protected lazy val tpeDbString        : String = ""
  protected lazy val tpeDbInt           : String = ""
  protected lazy val tpeDbLong          : String = ""
  protected lazy val tpeDbFloat         : String = ""
  protected lazy val tpeDbDouble        : String = ""
  protected lazy val tpeDbBoolean       : String = ""
  protected lazy val tpeDbBigInt        : String = ""
  protected lazy val tpeDbBigDecimal    : String = ""
  protected lazy val tpeDbDate          : String = ""
  protected lazy val tpeDbDuration      : String = ""
  protected lazy val tpeDbInstant       : String = ""
  protected lazy val tpeDbLocalDate     : String = ""
  protected lazy val tpeDbLocalTime     : String = ""
  protected lazy val tpeDbLocalDateTime : String = ""
  protected lazy val tpeDbOffsetTime    : String = ""
  protected lazy val tpeDbOffsetDateTime: String = ""
  protected lazy val tpeDbZonedDateTime : String = ""
  protected lazy val tpeDbUUID          : String = ""
  protected lazy val tpeDbURI           : String = ""
  protected lazy val tpeDbByte          : String = ""
  protected lazy val tpeDbShort         : String = ""
  protected lazy val tpeDbChar          : String = ""
}