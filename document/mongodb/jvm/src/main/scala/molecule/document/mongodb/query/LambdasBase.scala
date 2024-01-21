package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.base.util.BaseHelpers
import molecule.core.util.AggrUtils
import org.bson._
import org.bson.types.{Decimal128, ObjectId}

trait LambdasBase extends BaseHelpers with AggrUtils {

  protected lazy val v2bsonID            : String => BsonValue         = (v: String) => if (v == null) new BsonNull else new BsonObjectId(new ObjectId(v))
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

  protected lazy val castID             = (field: String) => (doc: BsonDocument) => {
    doc.get(field) match {
      case v: BsonObjectId => v.getValue.toString
      case v: BsonString   => v.getValue
      case v               => throw ModelError("Unexpected Bson value for id: " + v)
    }
  }
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

  protected lazy val hardCastDouble = (field: String) => (doc: BsonDocument) => doc.get(field) match {
    case v: BsonInt32      => v.asInt32.getValue.toDouble
    case v: BsonInt64      => v.asInt64.getValue.toDouble
    case v: BsonDouble     => v.asDouble.getValue
    case v: BsonDecimal128 => v.asDecimal128.getValue.toString.toDouble
  }
}
