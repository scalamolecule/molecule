package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.core.util.AggrUtils
import org.bson._
import org.bson.types.Decimal128

trait LambdasBase extends BaseHelpers with AggrUtils with MongoQueryBase {

//  protected lazy val one2sqlString        : String => String         = (v: String) => s"'${v.replace("'", "''")}'"
//  protected lazy val one2sqlInt           : Int => String            = (v: Int) => s"$v"
//  protected lazy val one2sqlLong          : Long => String           = (v: Long) => s"$v"
//  protected lazy val one2sqlFloat         : Float => String          = (v: Float) => s"$v"
//  protected lazy val one2sqlDouble        : Double => String         = (v: Double) => s"$v"
//  protected lazy val one2sqlBoolean       : Boolean => String        = (v: Boolean) => s"$v"
//  protected lazy val one2sqlBigInt        : BigInt => String         = (v: BigInt) => s"$v"
//  protected lazy val one2sqlBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"$v"
//  protected lazy val one2sqlDate          : Date => String           = (v: Date) => s"'${date2str(v)}'"
//  protected lazy val one2sqlDuration      : Duration => String       = (v: Duration) => s"'${v.toString}'"
//  protected lazy val one2sqlInstant       : Instant => String        = (v: Instant) => s"'${v.toString}'"
//  protected lazy val one2sqlLocalDate     : LocalDate => String      = (v: LocalDate) => s"'${v.toString}'"
//  protected lazy val one2sqlLocalTime     : LocalTime => String      = (v: LocalTime) => s"'${v.toString}'"
//  protected lazy val one2sqlLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => s"'${v.toString}'"
//  protected lazy val one2sqlOffsetTime    : OffsetTime => String     = (v: OffsetTime) => s"'${v.toString}'"
//  protected lazy val one2sqlOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => s"'${v.toString}'"
//  protected lazy val one2sqlZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => s"'${v.toString}'"
//  protected lazy val one2sqlUUID          : UUID => String           = (v: UUID) => s"'${v.toString}'"
//  protected lazy val one2sqlURI           : URI => String            = (v: URI) => s"'${v.toString.replace("'", "''")}'"
//  protected lazy val one2sqlByte          : Byte => String           = (v: Byte) => s"$v"
//  protected lazy val one2sqlShort         : Short => String          = (v: Short) => s"$v"
//  protected lazy val one2sqlChar          : Char => String           = (v: Char) => s"'${v.toString}'"

  protected lazy val v2bsonID            : String => BsonValue         = (v: String) => if (v == null) new BsonNull else new BsonString(v)
  protected lazy val v2bsonString        : String => BsonValue         = (v: String) => if (v == null) new BsonNull else new BsonString(v)
  protected lazy val v2bsonInt           : Int => BsonValue            = (v: Int) => new BsonInt32(v)
  protected lazy val v2bsonLong          : Long => BsonValue           = (v: Long) => new BsonInt64(v)
  protected lazy val v2bsonFloat         : Float => BsonValue          = (v: Float) => new BsonDouble(v)
  protected lazy val v2bsonDouble        : Double => BsonValue         = (v: Double) => new BsonDouble(v)
  protected lazy val v2bsonBoolean       : Boolean => BsonValue        = (v: Boolean) => new BsonBoolean(v)
  protected lazy val v2bsonBigInt        : BigInt => BsonValue         = (v: BigInt) => if (v == null) new BsonNull else new BsonDecimal128(new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val v2bsonBigDecimal    : BigDecimal => BsonValue     = (v: BigDecimal) => if (v == null) new BsonNull else new BsonDecimal128(new Decimal128(v.bigDecimal))
  protected lazy val v2bsonDate          : Date => BsonValue           = (v: Date) => if (v == null) new BsonNull else new BsonDateTime(v.getTime)
  protected lazy val v2bsonDuration      : Duration => BsonValue       = (v: Duration) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonInstant       : Instant => BsonValue        = (v: Instant) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonLocalDate     : LocalDate => BsonValue      = (v: LocalDate) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonLocalTime     : LocalTime => BsonValue      = (v: LocalTime) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonLocalDateTime : LocalDateTime => BsonValue  = (v: LocalDateTime) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonOffsetTime    : OffsetTime => BsonValue     = (v: OffsetTime) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonOffsetDateTime: OffsetDateTime => BsonValue = (v: OffsetDateTime) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonZonedDateTime : ZonedDateTime => BsonValue  = (v: ZonedDateTime) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonUUID          : UUID => BsonValue           = (v: UUID) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonURI           : URI => BsonValue            = (v: URI) => if (v == null) new BsonNull else new BsonString(v.toString)
  protected lazy val v2bsonByte          : Byte => BsonValue           = (v: Byte) => if (v != 0 && v == null.asInstanceOf[Byte]) new BsonNull else new BsonInt32(v)
  protected lazy val v2bsonShort         : Short => BsonValue          = (v: Short) => if (v != 0 && v == null.asInstanceOf[Short]) new BsonNull else new BsonInt32(v)
  protected lazy val v2bsonChar          : Char => BsonValue           = (v: Char) => if (v == null.asInstanceOf[Char]) new BsonNull else new BsonString(v.toString)

  protected lazy val castID             = (field: String) => (doc: BsonDocument) => doc.get(field).asObjectId.getValue.toString
  protected lazy val castString         = (field: String) => (doc: BsonDocument) => doc.get(field).asString.getValue
  protected lazy val castInt            = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue
  protected lazy val castLong           = (field: String) => (doc: BsonDocument) => doc.get(field).asInt64.getValue
  protected lazy val castFloat          = (field: String) => (doc: BsonDocument) => doc.get(field).asDouble.getValue.toFloat
  protected lazy val castDouble         = (field: String) => (doc: BsonDocument) => doc.get(field).asDouble.getValue
  protected lazy val castBoolean        = (field: String) => (doc: BsonDocument) => doc.get(field).asBoolean.getValue
  protected lazy val castBigInt         = (field: String) => (doc: BsonDocument) => BigInt(doc.get(field).asDecimal128.getValue.bigDecimalValue.toBigInteger)
  protected lazy val castBigDecimal     = (field: String) => (doc: BsonDocument) => BigDecimal(doc.get(field).asDecimal128.getValue.bigDecimalValue)
  protected lazy val castDate           = (field: String) => (doc: BsonDocument) => new Date(doc.get(field).asDateTime.getValue)
  protected lazy val castDuration       = (field: String) => (doc: BsonDocument) => Duration.parse(doc.get(field).asString.getValue)
  protected lazy val castInstant        = (field: String) => (doc: BsonDocument) => Instant.parse(doc.get(field).asString.getValue)
  protected lazy val castLocalDate      = (field: String) => (doc: BsonDocument) => LocalDate.parse(doc.get(field).asString.getValue)
  protected lazy val castLocalTime      = (field: String) => (doc: BsonDocument) => LocalTime.parse(doc.get(field).asString.getValue)
  protected lazy val castLocalDateTime  = (field: String) => (doc: BsonDocument) => LocalDateTime.parse(doc.get(field).asString.getValue)
  protected lazy val castOffsetTime     = (field: String) => (doc: BsonDocument) => OffsetTime.parse(doc.get(field).asString.getValue)
  protected lazy val castOffsetDateTime = (field: String) => (doc: BsonDocument) => OffsetDateTime.parse(doc.get(field).asString.getValue)
  protected lazy val castZonedDateTime  = (field: String) => (doc: BsonDocument) => ZonedDateTime.parse(doc.get(field).asString.getValue)
  protected lazy val castUUID           = (field: String) => (doc: BsonDocument) => UUID.fromString(doc.get(field).asString.getValue)
  protected lazy val castURI            = (field: String) => (doc: BsonDocument) => new URI(doc.get(field).asString.getValue)
  protected lazy val castByte           = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue.toByte
  protected lazy val castShort          = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue.toShort
  protected lazy val castChar           = (field: String) => (doc: BsonDocument) => doc.get(field).asString.getValue.charAt(0)

  //  lazy val toInt: BsonDocument => Int = (doc: BsonDocument) =>

  protected lazy val hardCastDouble = (field: String) => (doc: BsonDocument) => doc.get(field) match {
    case v: BsonInt32      => v.asInt32.getValue.toDouble
    case v: BsonInt64      => v.asInt64.getValue.toDouble
    case v: BsonDouble     => v.asDouble.getValue
    case v: BsonDecimal128 => v.asDecimal128.getValue.toString.toDouble
  }

//  protected def sqlArray2set[T](row: Row, paramIndex: Int, getValue: Row => T): Set[T] = {
//    //    val array = row.getArray(paramIndex)
//    //    if (row.wasNull()) {
//    //      Set.empty[T]
//    //    } else {
//    //      val arrayResultSet = array.getResultSet
//    //      var set            = Set.empty[T]
//    //      while (arrayResultSet.next()) {
//    //        set += getValue(arrayResultSet)
//    //      }
//    //      set
//    //    }
//    ???
//  }
//
//  protected lazy val valueString        : Row => String         = (rs: Row) => ??? //rs.getString(2)
//  protected lazy val valueInt           : Row => Int            = (rs: Row) => ??? //rs.getInt(2)
//  protected lazy val valueLong          : Row => Long           = (rs: Row) => ??? //rs.getLong(2)
//  protected lazy val valueFloat         : Row => Float          = (rs: Row) => ??? //rs.getFloat(2)
//  protected lazy val valueDouble        : Row => Double         = (rs: Row) => ??? //rs.getDouble(2)
//  protected lazy val valueBoolean       : Row => Boolean        = (rs: Row) => ??? //rs.getBoolean(2)
//  protected lazy val valueBigInt        : Row => BigInt         = (rs: Row) => ??? //rs.getBigDecimal(2).toBigInteger
//  protected lazy val valueBigDecimal    : Row => BigDecimal     = (rs: Row) => ??? //rs.getBigDecimal(2)
//  protected lazy val valueDate          : Row => Date           = (rs: Row) => ??? //rs.getDate(2)
//  protected lazy val valueDuration      : Row => Duration       = (rs: Row) => ??? //Duration.parse(rs.getString(2))
//  protected lazy val valueInstant       : Row => Instant        = (rs: Row) => ??? //Instant.parse(rs.getString(2))
//  protected lazy val valueLocalDate     : Row => LocalDate      = (rs: Row) => ??? //LocalDate.parse(rs.getString(2))
//  protected lazy val valueLocalTime     : Row => LocalTime      = (rs: Row) => ??? //LocalTime.parse(rs.getString(2))
//  protected lazy val valueLocalDateTime : Row => LocalDateTime  = (rs: Row) => ??? //LocalDateTime.parse(rs.getString(2))
//  protected lazy val valueOffsetTime    : Row => OffsetTime     = (rs: Row) => ??? //OffsetTime.parse(rs.getString(2))
//  protected lazy val valueOffsetDateTime: Row => OffsetDateTime = (rs: Row) => ??? //OffsetDateTime.parse(rs.getString(2))
//  protected lazy val valueZonedDateTime : Row => ZonedDateTime  = (rs: Row) => ??? //ZonedDateTime.parse(rs.getString(2))
//  protected lazy val valueUUID          : Row => UUID           = (rs: Row) => ??? //UUID.fromString(rs.getString(2))
//  protected lazy val valueURI           : Row => URI            = (rs: Row) => ??? //new URI(rs.getString(2))
//  protected lazy val valueByte          : Row => Byte           = (rs: Row) => ??? //rs.getByte(2)
//  protected lazy val valueShort         : Row => Short          = (rs: Row) => ??? //rs.getShort(2)
//  protected lazy val valueChar          : Row => Char           = (rs: Row) => ??? //rs.getString(2).charAt(0)
//
//
//  protected lazy val array2setString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
//  protected lazy val array2setInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
//  protected lazy val array2setLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
//  protected lazy val array2setFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
//  protected lazy val array2setDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
//  protected lazy val array2setBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
//  protected lazy val array2setBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
//  protected lazy val array2setBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
//  protected lazy val array2setDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
//  protected lazy val array2setDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDuration)
//  protected lazy val array2setInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInstant)
//  protected lazy val array2setLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDate)
//  protected lazy val array2setLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalTime)
//  protected lazy val array2setLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLocalDateTime)
//  protected lazy val array2setOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetTime)
//  protected lazy val array2setOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueOffsetDateTime)
//  protected lazy val array2setZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueZonedDateTime)
//  protected lazy val array2setUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
//  protected lazy val array2setURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
//  protected lazy val array2setByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
//  protected lazy val array2setShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
//  protected lazy val array2setChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)
//
//
//  protected lazy val json2oneString        : String => String         = (v: String) => v
//  protected lazy val json2oneInt           : String => Int            = (v: String) => v.toInt
//  protected lazy val json2oneLong          : String => Long           = (v: String) => v.toLong
//  protected lazy val json2oneFloat         : String => Float          = (v: String) => v.toFloat
//  protected lazy val json2oneDouble        : String => Double         = (v: String) => v.toDouble
//  protected lazy val json2oneBoolean       : String => Boolean        = (v: String) => v == "1"
//  protected lazy val json2oneBigInt        : String => BigInt         = (v: String) => BigInt(v)
//  protected lazy val json2oneBigDecimal    : String => BigDecimal     = (v: String) => BigDecimal(v)
//  protected lazy val json2oneDate          : String => Date           = (v: String) => str2date(v)
//  protected lazy val json2oneDuration      : String => Duration       = (v: String) => Duration.parse(v)
//  protected lazy val json2oneInstant       : String => Instant        = (v: String) => Instant.parse(v)
//  protected lazy val json2oneLocalDate     : String => LocalDate      = (v: String) => LocalDate.parse(v)
//  protected lazy val json2oneLocalTime     : String => LocalTime      = (v: String) => LocalTime.parse(v)
//  protected lazy val json2oneLocalDateTime : String => LocalDateTime  = (v: String) => LocalDateTime.parse(v)
//  protected lazy val json2oneOffsetTime    : String => OffsetTime     = (v: String) => OffsetTime.parse(v)
//  protected lazy val json2oneOffsetDateTime: String => OffsetDateTime = (v: String) => OffsetDateTime.parse(v)
//  protected lazy val json2oneZonedDateTime : String => ZonedDateTime  = (v: String) => ZonedDateTime.parse(v)
//  protected lazy val json2oneUUID          : String => UUID           = (v: String) => UUID.fromString(v)
//  protected lazy val json2oneURI           : String => URI            = (v: String) => new URI(v)
//  protected lazy val json2oneByte          : String => Byte           = (v: String) => v.toByte
//  protected lazy val json2oneShort         : String => Short          = (v: String) => v.toShort
//  protected lazy val json2oneChar          : String => Char           = (v: String) => v.charAt(0)
//
//  protected lazy val json2arrayString        : String => Array[String]         = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneString)
//  protected lazy val json2arrayInt           : String => Array[Int]            = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneInt)
//  protected lazy val json2arrayLong          : String => Array[Long]           = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneLong)
//  protected lazy val json2arrayFloat         : String => Array[Float]          = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneFloat)
//  protected lazy val json2arrayDouble        : String => Array[Double]         = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneDouble)
//  protected lazy val json2arrayBoolean       : String => Array[Boolean]        = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBoolean)
//  protected lazy val json2arrayBigInt        : String => Array[BigInt]         = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBigInt)
//  protected lazy val json2arrayBigDecimal    : String => Array[BigDecimal]     = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBigDecimal)
//  protected lazy val json2arrayDate          : String => Array[Date]           = (json: String) => json.substring(2, json.length - 2).replace("000000", "000").split("\", ?\"").map(json2oneDate)
//  protected lazy val json2arrayDuration      : String => Array[Duration]       = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneDuration)
//  protected lazy val json2arrayInstant       : String => Array[Instant]        = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneInstant)
//  protected lazy val json2arrayLocalDate     : String => Array[LocalDate]      = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneLocalDate)
//  protected lazy val json2arrayLocalTime     : String => Array[LocalTime]      = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneLocalTime)
//  protected lazy val json2arrayLocalDateTime : String => Array[LocalDateTime]  = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneLocalDateTime)
//  protected lazy val json2arrayOffsetTime    : String => Array[OffsetTime]     = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneOffsetTime)
//  protected lazy val json2arrayOffsetDateTime: String => Array[OffsetDateTime] = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneOffsetDateTime)
//  protected lazy val json2arrayZonedDateTime : String => Array[ZonedDateTime]  = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneZonedDateTime)
//  protected lazy val json2arrayUUID          : String => Array[UUID]           = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneUUID)
//  protected lazy val json2arrayURI           : String => Array[URI]            = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneURI)
//  protected lazy val json2arrayByte          : String => Array[Byte]           = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneByte)
//  protected lazy val json2arrayShort         : String => Array[Short]          = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneShort)
//  protected lazy val json2arrayChar          : String => Array[Char]           = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneChar)
//
//
//  protected lazy val one2jsonString        : String => String         = (v: String) => "\"" + escStr(v) + "\""
//  protected lazy val one2jsonInt           : Int => String            = (v: Int) => s"$v"
//  protected lazy val one2jsonLong          : Long => String           = (v: Long) => s"$v"
//  protected lazy val one2jsonFloat         : Float => String          = (v: Float) => s"$v"
//  protected lazy val one2jsonDouble        : Double => String         = (v: Double) => s"$v"
//  protected lazy val one2jsonBoolean       : Boolean => String        = (v: Boolean) => if (v) "1" else "0"
//  protected lazy val one2jsonBigInt        : BigInt => String         = (v: BigInt) => s"$v"
//  protected lazy val one2jsonBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"$v"
//  protected lazy val one2jsonDate          : Date => String           = (v: Date) => "\"" + date2str(v) + "\""
//  protected lazy val one2jsonDuration      : Duration => String       = (v: Duration) => "\"" + v.toString + "\""
//  protected lazy val one2jsonInstant       : Instant => String        = (v: Instant) => "\"" + v.toString + "\""
//  protected lazy val one2jsonLocalDate     : LocalDate => String      = (v: LocalDate) => "\"" + v.toString + "\""
//  protected lazy val one2jsonLocalTime     : LocalTime => String      = (v: LocalTime) => "\"" + v.toString + "\""
//  protected lazy val one2jsonLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => "\"" + v.toString + "\""
//  protected lazy val one2jsonOffsetTime    : OffsetTime => String     = (v: OffsetTime) => "\"" + v.toString + "\""
//  protected lazy val one2jsonOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => "\"" + v.toString + "\""
//  protected lazy val one2jsonZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => "\"" + v.toString + "\""
//  protected lazy val one2jsonUUID          : UUID => String           = (v: UUID) => "\"" + v.toString + "\""
//  protected lazy val one2jsonURI           : URI => String            = (v: URI) => "\"" + v.toString.replace("'", "''") + "\""
//  protected lazy val one2jsonByte          : Byte => String           = (v: Byte) => s"$v"
//  protected lazy val one2jsonShort         : Short => String          = (v: Short) => s"$v"
//  protected lazy val one2jsonChar          : Char => String           = (v: Char) => "\"" + v.toString + "\""
//
//  protected lazy val tpeDbString        : String = ""
//  protected lazy val tpeDbInt           : String = ""
//  protected lazy val tpeDbLong          : String = ""
//  protected lazy val tpeDbFloat         : String = ""
//  protected lazy val tpeDbDouble        : String = ""
//  protected lazy val tpeDbBoolean       : String = ""
//  protected lazy val tpeDbBigInt        : String = ""
//  protected lazy val tpeDbBigDecimal    : String = ""
//  protected lazy val tpeDbDate          : String = ""
//  protected lazy val tpeDbDuration      : String = ""
//  protected lazy val tpeDbInstant       : String = ""
//  protected lazy val tpeDbLocalDate     : String = ""
//  protected lazy val tpeDbLocalTime     : String = ""
//  protected lazy val tpeDbLocalDateTime : String = ""
//  protected lazy val tpeDbOffsetTime    : String = ""
//  protected lazy val tpeDbOffsetDateTime: String = ""
//  protected lazy val tpeDbZonedDateTime : String = ""
//  protected lazy val tpeDbUUID          : String = ""
//  protected lazy val tpeDbURI           : String = ""
//  protected lazy val tpeDbByte          : String = ""
//  protected lazy val tpeDbShort         : String = ""
//  protected lazy val tpeDbChar          : String = ""
}
