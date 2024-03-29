package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import org.bson._
import org.bson.types.Decimal128

trait LambdasSet extends LambdasBase with JavaConversions {

  case class ResSet[T](
    castSet: String => BsonDocument => Set[T],
    castOptSet: String => BsonDocument => Option[Set[T]],
    v2bson: T => BsonValue,
  )

  lazy val resSetID            : ResSet[String]         = ResSet(castSetID, castOptSetID, v2bsonID)
  lazy val resSetString        : ResSet[String]         = ResSet(castSetString, castOptSetString, v2bsonString)
  lazy val resSetInt           : ResSet[Int]            = ResSet(castSetInt, castOptSetInt, v2bsonInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet(castSetLong, castOptSetLong, v2bsonLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet(castSetFloat, castOptSetFloat, v2bsonFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet(castSetDouble, castOptSetDouble, v2bsonDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet(castSetBoolean, castOptSetBoolean, v2bsonBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet(castSetBigInt, castOptSetBigInt, v2bsonBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet(castSetBigDecimal, castOptSetBigDecimal, v2bsonBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet(castSetDate, castOptSetDate, v2bsonDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet(castSetDuration, castOptSetDuration, v2bsonDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet(castSetInstant, castOptSetInstant, v2bsonInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet(castSetLocalDate, castOptSetLocalDate, v2bsonLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet(castSetLocalTime, castOptSetLocalTime, v2bsonLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet(castSetLocalDateTime, castOptSetLocalDateTime, v2bsonLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet(castSetOffsetTime, castOptSetOffsetTime, v2bsonOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet(castSetOffsetDateTime, castOptSetOffsetDateTime, v2bsonOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet(castSetZonedDateTime, castOptSetZonedDateTime, v2bsonZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet(castSetUUID, castOptSetUUID, v2bsonUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet(castSetURI, castOptSetURI, v2bsonURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet(castSetByte, castOptSetByte, v2bsonByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet(castSetShort, castOptSetShort, v2bsonShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet(castSetChar, castOptSetChar, v2bsonChar)


  protected def castSet[T](doc: BsonDocument, field: String, value: BsonValue => T): Set[T] = {
    val raw = doc.get(field)
    if (raw == null) {
      Set.empty[T]
    } else {
      val array = raw.asArray.iterator
      var set   = Set.empty[T]
      while (array.hasNext) {
        set += value(array.next)
      }
      set
    }
  }

  protected lazy val castSetID             = (field: String) => (doc: BsonDocument) => castSet[String](doc, field, bson2ID)
  protected lazy val castSetString         = (field: String) => (doc: BsonDocument) => castSet[String](doc, field, bson2String)
  protected lazy val castSetInt            = (field: String) => (doc: BsonDocument) => castSet[Int](doc, field, bson2Int)
  protected lazy val castSetLong           = (field: String) => (doc: BsonDocument) => castSet[Long](doc, field, bson2Long)
  protected lazy val castSetFloat          = (field: String) => (doc: BsonDocument) => castSet[Float](doc, field, bson2Float)
  protected lazy val castSetDouble         = (field: String) => (doc: BsonDocument) => castSet[Double](doc, field, bson2Double)
  protected lazy val castSetBoolean        = (field: String) => (doc: BsonDocument) => castSet[Boolean](doc, field, bson2Boolean)
  protected lazy val castSetBigInt         = (field: String) => (doc: BsonDocument) => castSet[BigInt](doc, field, bson2BigInt)
  protected lazy val castSetBigDecimal     = (field: String) => (doc: BsonDocument) => castSet[BigDecimal](doc, field, bson2BigDecimal)
  protected lazy val castSetDate           = (field: String) => (doc: BsonDocument) => castSet[Date](doc, field, bson2Date)
  protected lazy val castSetDuration       = (field: String) => (doc: BsonDocument) => castSet[Duration](doc, field, bson2Duration)
  protected lazy val castSetInstant        = (field: String) => (doc: BsonDocument) => castSet[Instant](doc, field, bson2Instant)
  protected lazy val castSetLocalDate      = (field: String) => (doc: BsonDocument) => castSet[LocalDate](doc, field, bson2LocalDate)
  protected lazy val castSetLocalTime      = (field: String) => (doc: BsonDocument) => castSet[LocalTime](doc, field, bson2LocalTime)
  protected lazy val castSetLocalDateTime  = (field: String) => (doc: BsonDocument) => castSet[LocalDateTime](doc, field, bson2LocalDateTime)
  protected lazy val castSetOffsetTime     = (field: String) => (doc: BsonDocument) => castSet[OffsetTime](doc, field, bson2OffsetTime)
  protected lazy val castSetOffsetDateTime = (field: String) => (doc: BsonDocument) => castSet[OffsetDateTime](doc, field, bson2OffsetDateTime)
  protected lazy val castSetZonedDateTime  = (field: String) => (doc: BsonDocument) => castSet[ZonedDateTime](doc, field, bson2ZonedDateTime)
  protected lazy val castSetUUID           = (field: String) => (doc: BsonDocument) => castSet[UUID](doc, field, bson2UUID)
  protected lazy val castSetURI            = (field: String) => (doc: BsonDocument) => castSet[URI](doc, field, bson2URI)
  protected lazy val castSetByte           = (field: String) => (doc: BsonDocument) => castSet[Byte](doc, field, bson2Byte)
  protected lazy val castSetShort          = (field: String) => (doc: BsonDocument) => castSet[Short](doc, field, bson2Short)
  protected lazy val castSetChar           = (field: String) => (doc: BsonDocument) => castSet[Char](doc, field, bson2Char)


  private lazy val bson2ID            : BsonValue => String         = (bv: BsonValue) => {
    if (bv.isObjectId) bv.asObjectId().getValue.toString else bv.asString.getValue
  }
  private lazy val bson2String        : BsonValue => String         = (bv: BsonValue) => bv.asString.getValue
  private lazy val bson2Int           : BsonValue => Int            = (bv: BsonValue) => bv.asInt32.getValue
  private lazy val bson2Long          : BsonValue => Long           = (bv: BsonValue) => bv.asInt64.getValue
  private lazy val bson2Float         : BsonValue => Float          = (bv: BsonValue) => bv.asDouble.getValue.toFloat
  private lazy val bson2Double        : BsonValue => Double         = (bv: BsonValue) => bv.asDouble.getValue
  private lazy val bson2Boolean       : BsonValue => Boolean        = (bv: BsonValue) => bv.asBoolean.getValue
  private lazy val bson2BigInt        : BsonValue => BigInt         = (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger)
  private lazy val bson2BigDecimal    : BsonValue => BigDecimal     = (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue)
  private lazy val bson2Date          : BsonValue => Date           = (bv: BsonValue) => new Date(bv.asDateTime.getValue)
  private lazy val bson2Duration      : BsonValue => Duration       = (bv: BsonValue) => Duration.parse(bv.asString.getValue)
  private lazy val bson2Instant       : BsonValue => Instant        = (bv: BsonValue) => Instant.parse(bv.asString.getValue)
  private lazy val bson2LocalDate     : BsonValue => LocalDate      = (bv: BsonValue) => LocalDate.parse(bv.asString.getValue)
  private lazy val bson2LocalTime     : BsonValue => LocalTime      = (bv: BsonValue) => LocalTime.parse(bv.asString.getValue)
  private lazy val bson2LocalDateTime : BsonValue => LocalDateTime  = (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue)
  private lazy val bson2OffsetTime    : BsonValue => OffsetTime     = (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue)
  private lazy val bson2OffsetDateTime: BsonValue => OffsetDateTime = (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue)
  private lazy val bson2ZonedDateTime : BsonValue => ZonedDateTime  = (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue)
  private lazy val bson2UUID          : BsonValue => UUID           = (bv: BsonValue) => UUID.fromString(bv.asString.getValue)
  private lazy val bson2URI           : BsonValue => URI            = (bv: BsonValue) => new URI(bv.asString.getValue)
  private lazy val bson2Byte          : BsonValue => Byte           = (bv: BsonValue) => bv.asInt32.getValue.toByte
  private lazy val bson2Short         : BsonValue => Short          = (bv: BsonValue) => bv.asInt32.getValue.toShort
  private lazy val bson2Char          : BsonValue => Char           = (bv: BsonValue) => bv.asString.getValue.charAt(0)


  protected def castOptSet[T](doc: BsonDocument, field: String, value: BsonValue => T): Option[Set[T]] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Option.empty[Set[T]]
    } else {
      val array = raw.asArray.iterator
      var set   = Set.empty[T]
      while (array.hasNext) {
        set += value(array.next)
      }
      if (set.isEmpty) None else Some(set)
    }
  }

  private lazy val castOptSetID             = (field: String) => (doc: BsonDocument) => castOptSet[String](doc, field, (bv: BsonValue) => bv.asObjectId().getValue.toString)
  private lazy val castOptSetString         = (field: String) => (doc: BsonDocument) => castOptSet[String](doc, field, (bv: BsonValue) => bv.asString.getValue)
  private lazy val castOptSetInt            = (field: String) => (doc: BsonDocument) => castOptSet[Int](doc, field, (bv: BsonValue) => bv.asInt32.getValue)
  private lazy val castOptSetLong           = (field: String) => (doc: BsonDocument) => castOptSet[Long](doc, field, (bv: BsonValue) => bv.asInt64.getValue)
  private lazy val castOptSetFloat          = (field: String) => (doc: BsonDocument) => castOptSet[Float](doc, field, (bv: BsonValue) => bv.asDouble.getValue.toFloat)
  private lazy val castOptSetDouble         = (field: String) => (doc: BsonDocument) => castOptSet[Double](doc, field, (bv: BsonValue) => bv.asDouble.getValue)
  private lazy val castOptSetBoolean        = (field: String) => (doc: BsonDocument) => castOptSet[Boolean](doc, field, (bv: BsonValue) => bv.asBoolean.getValue)
  private lazy val castOptSetBigInt         = (field: String) => (doc: BsonDocument) => castOptSet[BigInt](doc, field, (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger))
  private lazy val castOptSetBigDecimal     = (field: String) => (doc: BsonDocument) => castOptSet[BigDecimal](doc, field, (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue))
  private lazy val castOptSetDate           = (field: String) => (doc: BsonDocument) => castOptSet[Date](doc, field, (bv: BsonValue) => new Date(bv.asDateTime.getValue))
  private lazy val castOptSetDuration       = (field: String) => (doc: BsonDocument) => castOptSet[Duration](doc, field, (bv: BsonValue) => Duration.parse(bv.asString.getValue))
  private lazy val castOptSetInstant        = (field: String) => (doc: BsonDocument) => castOptSet[Instant](doc, field, (bv: BsonValue) => Instant.parse(bv.asString.getValue))
  private lazy val castOptSetLocalDate      = (field: String) => (doc: BsonDocument) => castOptSet[LocalDate](doc, field, (bv: BsonValue) => LocalDate.parse(bv.asString.getValue))
  private lazy val castOptSetLocalTime      = (field: String) => (doc: BsonDocument) => castOptSet[LocalTime](doc, field, (bv: BsonValue) => LocalTime.parse(bv.asString.getValue))
  private lazy val castOptSetLocalDateTime  = (field: String) => (doc: BsonDocument) => castOptSet[LocalDateTime](doc, field, (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue))
  private lazy val castOptSetOffsetTime     = (field: String) => (doc: BsonDocument) => castOptSet[OffsetTime](doc, field, (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue))
  private lazy val castOptSetOffsetDateTime = (field: String) => (doc: BsonDocument) => castOptSet[OffsetDateTime](doc, field, (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue))
  private lazy val castOptSetZonedDateTime  = (field: String) => (doc: BsonDocument) => castOptSet[ZonedDateTime](doc, field, (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue))
  private lazy val castOptSetUUID           = (field: String) => (doc: BsonDocument) => castOptSet[UUID](doc, field, (bv: BsonValue) => UUID.fromString(bv.asString.getValue))
  private lazy val castOptSetURI            = (field: String) => (doc: BsonDocument) => castOptSet[URI](doc, field, (bv: BsonValue) => new URI(bv.asString.getValue))
  private lazy val castOptSetByte           = (field: String) => (doc: BsonDocument) => castOptSet[Byte](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toByte)
  private lazy val castOptSetShort          = (field: String) => (doc: BsonDocument) => castOptSet[Short](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toShort)
  private lazy val castOptSetChar           = (field: String) => (doc: BsonDocument) => castOptSet[Char](doc, field, (bv: BsonValue) => bv.asString.getValue.charAt(0))
}
