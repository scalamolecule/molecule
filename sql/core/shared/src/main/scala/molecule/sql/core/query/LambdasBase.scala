package molecule.sql.core.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.core.util.AggrUtils
import scala.reflect.ClassTag


trait LambdasBase extends BaseHelpers with AggrUtils { self: SqlQueryBase =>

  protected lazy val one2sqlId            : String => String         = (v: String) => s"'${v.replace("'", "''")}'"
  protected lazy val one2sqlString        : String => String         = (v: String) => s"'${v.replace("'", "''")}'"
  protected lazy val one2sqlInt           : Int => String            = (v: Int) => s"$v"
  protected lazy val one2sqlLong          : Long => String           = (v: Long) => s"$v"
  protected lazy val one2sqlFloat         : Float => String          = (v: Float) => s"$v"
  protected lazy val one2sqlDouble        : Double => String         = (v: Double) => s"$v"
  protected lazy val one2sqlBoolean       : Boolean => String        = (v: Boolean) => s"$v"
  protected lazy val one2sqlBigInt        : BigInt => String         = (v: BigInt) => s"$v"
  protected lazy val one2sqlBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"$v"
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


  lazy val toInt: (Row, Int) => Int = (row: Row, paramIndex: Int) => row.getLong(paramIndex).toInt

  protected def sqlArray2set[T](row: Row, paramIndex: Int, getValue: Row => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        set += getValue(arrayResultSet)
      }
      set
    }
  }

  protected lazy val valueId            : Row => String         = (rs: Row) => rs.getLong(2).toString
  protected lazy val valueString        : Row => String         = (rs: Row) => rs.getString(2)
  protected lazy val valueInt           : Row => Int            = (rs: Row) => rs.getInt(2)
  protected lazy val valueLong          : Row => Long           = (rs: Row) => rs.getLong(2)
  protected lazy val valueFloat         : Row => Float          = (rs: Row) => rs.getFloat(2)
  protected lazy val valueDouble        : Row => Double         = (rs: Row) => rs.getDouble(2)
  protected lazy val valueBoolean       : Row => Boolean        = (rs: Row) => rs.getBoolean(2)
  protected lazy val valueBigInt        : Row => BigInt         = (rs: Row) => rs.getBigDecimal(2).toBigInteger
  protected lazy val valueBigDecimal    : Row => BigDecimal     = (rs: Row) => rs.getBigDecimal(2)
  protected lazy val valueDate          : Row => Date           = (rs: Row) => new Date(rs.getLong(2))
  protected lazy val valueDuration      : Row => Duration       = (rs: Row) => Duration.parse(rs.getString(2))
  protected lazy val valueInstant       : Row => Instant        = (rs: Row) => Instant.parse(rs.getString(2))
  protected lazy val valueLocalDate     : Row => LocalDate      = (rs: Row) => LocalDate.parse(rs.getString(2))
  protected lazy val valueLocalTime     : Row => LocalTime      = (rs: Row) => LocalTime.parse(rs.getString(2))
  protected lazy val valueLocalDateTime : Row => LocalDateTime  = (rs: Row) => LocalDateTime.parse(rs.getString(2))
  protected lazy val valueOffsetTime    : Row => OffsetTime     = (rs: Row) => OffsetTime.parse(rs.getString(2))
  protected lazy val valueOffsetDateTime: Row => OffsetDateTime = (rs: Row) => OffsetDateTime.parse(rs.getString(2))
  protected lazy val valueZonedDateTime : Row => ZonedDateTime  = (rs: Row) => ZonedDateTime.parse(rs.getString(2))
  protected lazy val valueUUID          : Row => UUID           = (rs: Row) => UUID.fromString(rs.getString(2))
  protected lazy val valueURI           : Row => URI            = (rs: Row) => new URI(rs.getString(2))
  protected lazy val valueByte          : Row => Byte           = (rs: Row) => rs.getByte(2)
  protected lazy val valueShort         : Row => Short          = (rs: Row) => rs.getShort(2)
  protected lazy val valueChar          : Row => Char           = (rs: Row) => rs.getString(2).charAt(0)


  protected lazy val array2setId            : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueId)
  protected lazy val array2setString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
  protected lazy val array2setInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
  protected lazy val array2setLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
  protected lazy val array2setFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
  protected lazy val array2setDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
  protected lazy val array2setBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
  protected lazy val array2setBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
  protected lazy val array2setBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
  protected lazy val array2setDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
  protected lazy val array2setDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDuration)
  protected lazy val array2setInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInstant)
  protected lazy val array2setLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDate)
  protected lazy val array2setLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalTime)
  protected lazy val array2setLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDateTime)
  protected lazy val array2setOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetTime)
  protected lazy val array2setOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetDateTime)
  protected lazy val array2setZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueZonedDateTime)
  protected lazy val array2setUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
  protected lazy val array2setURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
  protected lazy val array2setByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
  protected lazy val array2setShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
  protected lazy val array2setChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)


  protected lazy val json2oneId            : String => String         = (v: String) => v
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

  //  private def json2optArray[T](decode: String => Array[T]): String => Option[Array[T]] = (json: String) => {
  //    if (json == "[null]") {
  //      Option.empty[Array[T]]
  //    } else {
  //      val array = decode(json)
  //      if (array.nonEmpty) Some(array) else Option.empty[Array[T]]
  //    }
  //  }
  //
  //  protected lazy val json2optArrayId            : String => Option[Array[String]]         = json2optArray(json2arrayId)
  //  protected lazy val json2optArrayString        : String => Option[Array[String]]         = json2optArray(json2arrayString)
  //  protected lazy val json2optArrayInt           : String => Option[Array[Int]]            = json2optArray(json2arrayInt)
  //  protected lazy val json2optArrayLong          : String => Option[Array[Long]]           = json2optArray(json2arrayLong)
  //  protected lazy val json2optArrayFloat         : String => Option[Array[Float]]          = json2optArray(json2arrayFloat)
  //  protected lazy val json2optArrayDouble        : String => Option[Array[Double]]         = json2optArray(json2arrayDouble)
  //  protected lazy val json2optArrayBoolean       : String => Option[Array[Boolean]]        = json2optArray(json2arrayBoolean)
  //  protected lazy val json2optArrayBigInt        : String => Option[Array[BigInt]]         = json2optArray(json2arrayBigInt)
  //  protected lazy val json2optArrayBigDecimal    : String => Option[Array[BigDecimal]]     = json2optArray(json2arrayBigDecimal)
  //  protected lazy val json2optArrayDate          : String => Option[Array[Date]]           = json2optArray(json2arrayDate)
  //  protected lazy val json2optArrayDuration      : String => Option[Array[Duration]]       = json2optArray(json2arrayDuration)
  //  protected lazy val json2optArrayInstant       : String => Option[Array[Instant]]        = json2optArray(json2arrayInstant)
  //  protected lazy val json2optArrayLocalDate     : String => Option[Array[LocalDate]]      = json2optArray(json2arrayLocalDate)
  //  protected lazy val json2optArrayLocalTime     : String => Option[Array[LocalTime]]      = json2optArray(json2arrayLocalTime)
  //  protected lazy val json2optArrayLocalDateTime : String => Option[Array[LocalDateTime]]  = json2optArray(json2arrayLocalDateTime)
  //  protected lazy val json2optArrayOffsetTime    : String => Option[Array[OffsetTime]]     = json2optArray(json2arrayOffsetTime)
  //  protected lazy val json2optArrayOffsetDateTime: String => Option[Array[OffsetDateTime]] = json2optArray(json2arrayOffsetDateTime)
  //  protected lazy val json2optArrayZonedDateTime : String => Option[Array[ZonedDateTime]]  = json2optArray(json2arrayZonedDateTime)
  //  protected lazy val json2optArrayUUID          : String => Option[Array[UUID]]           = json2optArray(json2arrayUUID)
  //  protected lazy val json2optArrayURI           : String => Option[Array[URI]]            = json2optArray(json2arrayURI)
  //  protected lazy val json2optArrayByte          : String => Option[Array[Byte]]           = json2optArray(json2arrayByte)
  //  protected lazy val json2optArrayShort         : String => Option[Array[Short]]          = json2optArray(json2arrayShort)
  //  protected lazy val json2optArrayChar          : String => Option[Array[Char]]           = json2optArray(json2arrayChar)

  private def jsonArray2optArray[T: ClassTag](array: Array[String], decode: String => T): Option[Array[T]] = {
    val vs = array.flatMap {
      case "null" => None
      case v      => Some(decode(v))
    }
    if (vs.nonEmpty) Some(vs) else None
  }

  protected lazy val json2optArrayId            : String => Option[Array[String]]         = (json: String) => jsonArray2optArray(jsonArrayId(json), json2oneId)
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

  private lazy val jsonArrayId            : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayString        : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayInt           : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayLong          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayFloat         : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayDouble        : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayBoolean       : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayBigInt        : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayBigDecimal    : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayDate          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayDuration      : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayInstant       : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayLocalDate     : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayLocalTime     : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayLocalDateTime : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayOffsetTime    : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayOffsetDateTime: String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayZonedDateTime : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayUUID          : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayURI           : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")
  private lazy val jsonArrayByte          : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayShort         : String => Array[String] = (json: String) => json.substring(1, json.length - 1).split(", ?")
  private lazy val jsonArrayChar          : String => Array[String] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"")

  protected lazy val json2arrayId            : String => Array[String]         = (json: String) => jsonArrayId(json).map(json2oneId)
  protected lazy val json2arrayString        : String => Array[String]         = (json: String) => jsonArrayString(json).map(json2oneString)
  protected lazy val json2arrayInt           : String => Array[Int]            = (json: String) => jsonArrayInt(json).map(json2oneInt)
  protected lazy val json2arrayLong          : String => Array[Long]           = (json: String) => jsonArrayLong(json).map(json2oneLong)
  protected lazy val json2arrayFloat         : String => Array[Float]          = (json: String) => jsonArrayFloat(json).map(json2oneFloat)
  protected lazy val json2arrayDouble        : String => Array[Double]         = (json: String) => jsonArrayDouble(json).map(json2oneDouble)
  protected lazy val json2arrayBoolean       : String => Array[Boolean]        = (json: String) => jsonArrayBoolean(json).map(json2oneBoolean)
  protected lazy val json2arrayBigInt        : String => Array[BigInt]         = (json: String) => jsonArrayBigInt(json).map(json2oneBigInt)
  protected lazy val json2arrayBigDecimal    : String => Array[BigDecimal]     = (json: String) => jsonArrayBigDecimal(json).map(json2oneBigDecimal)
  protected lazy val json2arrayDate          : String => Array[Date]           = (json: String) => jsonArrayDate(json).map(json2oneDate)
  protected lazy val json2arrayDuration      : String => Array[Duration]       = (json: String) => jsonArrayDuration(json).map(json2oneDuration)
  protected lazy val json2arrayInstant       : String => Array[Instant]        = (json: String) => jsonArrayInstant(json).map(json2oneInstant)
  protected lazy val json2arrayLocalDate     : String => Array[LocalDate]      = (json: String) => jsonArrayLocalDate(json).map(json2oneLocalDate)
  protected lazy val json2arrayLocalTime     : String => Array[LocalTime]      = (json: String) => jsonArrayLocalTime(json).map(json2oneLocalTime)
  protected lazy val json2arrayLocalDateTime : String => Array[LocalDateTime]  = (json: String) => jsonArrayLocalDateTime(json).map(json2oneLocalDateTime)
  protected lazy val json2arrayOffsetTime    : String => Array[OffsetTime]     = (json: String) => jsonArrayOffsetTime(json).map(json2oneOffsetTime)
  protected lazy val json2arrayOffsetDateTime: String => Array[OffsetDateTime] = (json: String) => jsonArrayOffsetDateTime(json).map(json2oneOffsetDateTime)
  protected lazy val json2arrayZonedDateTime : String => Array[ZonedDateTime]  = (json: String) => jsonArrayZonedDateTime(json).map(json2oneZonedDateTime)
  protected lazy val json2arrayUUID          : String => Array[UUID]           = (json: String) => jsonArrayUUID(json).map(json2oneUUID)
  protected lazy val json2arrayURI           : String => Array[URI]            = (json: String) => jsonArrayURI(json).map(json2oneURI)
  protected lazy val json2arrayByte          : String => Array[Byte]           = (json: String) => jsonArrayByte(json).map(json2oneByte)
  protected lazy val json2arrayShort         : String => Array[Short]          = (json: String) => jsonArrayShort(json).map(json2oneShort)
  protected lazy val json2arrayChar          : String => Array[Char]           = (json: String) => jsonArrayChar(json).map(json2oneChar)


  protected lazy val one2jsonId            : String => String         = (v: String) => v
  protected lazy val one2jsonString        : String => String         = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonInt           : Int => String            = (v: Int) => s"$v"
  protected lazy val one2jsonLong          : Long => String           = (v: Long) => s"$v"
  protected lazy val one2jsonFloat         : Float => String          = (v: Float) => s"$v"
  protected lazy val one2jsonDouble        : Double => String         = (v: Double) => s"$v"
  protected lazy val one2jsonBoolean       : Boolean => String        = (v: Boolean) => if (v) "1" else "0"
  protected lazy val one2jsonBigInt        : BigInt => String         = (v: BigInt) => s"$v"
  protected lazy val one2jsonBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"$v"
  protected lazy val one2jsonDate          : Date => String           = (v: Date) => s"$v"
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