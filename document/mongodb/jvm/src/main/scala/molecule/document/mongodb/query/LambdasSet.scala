package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import org.bson._
import org.bson.types.Decimal128

trait LambdasSet extends LambdasBase with JavaConversions {

  case class ResSet[T](
    tpe: String,
    j2s: String => BsonDocument => T,
    castSet: String => BsonDocument => Set[T],
    castOptSet: String => BsonDocument => Option[Set[T]],
    v2bson: T => BsonValue,
    castSetSet: String => BsonDocument => Set[Set[T]],
    v2set: String => BsonDocument => Set[T],
  )

  lazy val resSetID            : ResSet[String]         = ResSet("String", castID, castSetID, castOptSetID, v2bsonID, setSetID, v2setID)
  lazy val resSetString        : ResSet[String]         = ResSet("String", castString, castSetString, castOptSetString, v2bsonString, setSetString, v2setString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", castInt, castSetInt, castOptSetInt, v2bsonInt, setSetInt, v2setInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", castLong, castSetLong, castOptSetLong, v2bsonLong, setSetLong, v2setLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", castFloat, castSetFloat, castOptSetFloat, v2bsonFloat, setSetFloat, v2setFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", castDouble, castSetDouble, castOptSetDouble, v2bsonDouble, setSetDouble, v2setDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", castBoolean, castSetBoolean, castOptSetBoolean, v2bsonBoolean, setSetBoolean, v2setBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", castBigInt, castSetBigInt, castOptSetBigInt, v2bsonBigInt, setSetBigInt, v2setBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", castBigDecimal, castSetBigDecimal, castOptSetBigDecimal, v2bsonBigDecimal, setSetBigDecimal, v2setBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", castDate, castSetDate, castOptSetDate, v2bsonDate, setSetDate, v2setDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", castDuration, castSetDuration, castOptSetDuration, v2bsonDuration, setSetDuration, v2setDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", castInstant, castSetInstant, castOptSetInstant, v2bsonInstant, setSetInstant, v2setInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", castLocalDate, castSetLocalDate, castOptSetLocalDate, v2bsonLocalDate, setSetLocalDate, v2setLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", castLocalTime, castSetLocalTime, castOptSetLocalTime, v2bsonLocalTime, setSetLocalTime, v2setLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", castLocalDateTime, castSetLocalDateTime, castOptSetLocalDateTime, v2bsonLocalDateTime, setSetLocalDateTime, v2setLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", castOffsetTime, castSetOffsetTime, castOptSetOffsetTime, v2bsonOffsetTime, setSetOffsetTime, v2setOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", castOffsetDateTime, castSetOffsetDateTime, castOptSetOffsetDateTime, v2bsonOffsetDateTime, setSetOffsetDateTime, v2setOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", castZonedDateTime, castSetZonedDateTime, castOptSetZonedDateTime, v2bsonZonedDateTime, setSetZonedDateTime, v2setZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", castUUID, castSetUUID, castOptSetUUID, v2bsonUUID, setSetUUID, v2setUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", castURI, castSetURI, castOptSetURI, v2bsonURI, setSetURI, v2setURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", castByte, castSetByte, castOptSetByte, v2bsonByte, setSetByte, v2setByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", castShort, castSetShort, castOptSetShort, v2bsonShort, setSetShort, v2setShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", castChar, castSetChar, castOptSetChar, v2bsonChar, setSetChar, v2setChar)


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


  private lazy val v2setID             = (field: String) => (doc: BsonDocument) => Set(bson2ID(doc.get(field)))
  private lazy val v2setString         = (field: String) => (doc: BsonDocument) => Set(bson2String(doc.get(field)))
  private lazy val v2setInt            = (field: String) => (doc: BsonDocument) => Set(bson2Int(doc.get(field)))
  private lazy val v2setLong           = (field: String) => (doc: BsonDocument) => Set(bson2Long(doc.get(field)))
  private lazy val v2setFloat          = (field: String) => (doc: BsonDocument) => Set(bson2Float(doc.get(field)))
  private lazy val v2setDouble         = (field: String) => (doc: BsonDocument) => Set(bson2Double(doc.get(field)))
  private lazy val v2setBoolean        = (field: String) => (doc: BsonDocument) => Set(bson2Boolean(doc.get(field)))
  private lazy val v2setBigInt         = (field: String) => (doc: BsonDocument) => Set(bson2BigInt(doc.get(field)))
  private lazy val v2setBigDecimal     = (field: String) => (doc: BsonDocument) => Set(bson2BigDecimal(doc.get(field)))
  private lazy val v2setDate           = (field: String) => (doc: BsonDocument) => Set(bson2Date(doc.get(field)))
  private lazy val v2setDuration       = (field: String) => (doc: BsonDocument) => Set(bson2Duration(doc.get(field)))
  private lazy val v2setInstant        = (field: String) => (doc: BsonDocument) => Set(bson2Instant(doc.get(field)))
  private lazy val v2setLocalDate      = (field: String) => (doc: BsonDocument) => Set(bson2LocalDate(doc.get(field)))
  private lazy val v2setLocalTime      = (field: String) => (doc: BsonDocument) => Set(bson2LocalTime(doc.get(field)))
  private lazy val v2setLocalDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2LocalDateTime(doc.get(field)))
  private lazy val v2setOffsetTime     = (field: String) => (doc: BsonDocument) => Set(bson2OffsetTime(doc.get(field)))
  private lazy val v2setOffsetDateTime = (field: String) => (doc: BsonDocument) => Set(bson2OffsetDateTime(doc.get(field)))
  private lazy val v2setZonedDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2ZonedDateTime(doc.get(field)))
  private lazy val v2setUUID           = (field: String) => (doc: BsonDocument) => Set(bson2UUID(doc.get(field)))
  private lazy val v2setURI            = (field: String) => (doc: BsonDocument) => Set(bson2URI(doc.get(field)))
  private lazy val v2setByte           = (field: String) => (doc: BsonDocument) => Set(bson2Byte(doc.get(field)))
  private lazy val v2setShort          = (field: String) => (doc: BsonDocument) => Set(bson2Short(doc.get(field)))
  private lazy val v2setChar           = (field: String) => (doc: BsonDocument) => Set(bson2Char(doc.get(field)))


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


  protected def castSetSet[T](doc: BsonDocument, field: String, value: BsonValue => T): Set[Set[T]] = {
    val raw = doc.get(field)
    if (raw == null) {
      Set.empty[Set[T]]
    } else {
      val outerArray = raw.asArray.iterator
      var sets       = Set.empty[Set[T]]
      while (outerArray.hasNext) {
        var set        = Set.empty[T]
        val innerArray = outerArray.next.asArray.iterator
        while (innerArray.hasNext) {
          set += value(innerArray.next)
        }
        sets += set
      }
      sets
    }
  }

  private lazy val setSetID             = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2ID)
  private lazy val setSetString         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2String)
  private lazy val setSetInt            = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Int)
  private lazy val setSetLong           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Long)
  private lazy val setSetFloat          = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Float)
  private lazy val setSetDouble         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Double)
  private lazy val setSetBoolean        = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Boolean)
  private lazy val setSetBigInt         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2BigInt)
  private lazy val setSetBigDecimal     = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2BigDecimal)
  private lazy val setSetDate           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Date)
  private lazy val setSetDuration       = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Duration)
  private lazy val setSetInstant        = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Instant)
  private lazy val setSetLocalDate      = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalDate)
  private lazy val setSetLocalTime      = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalTime)
  private lazy val setSetLocalDateTime  = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalDateTime)
  private lazy val setSetOffsetTime     = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2OffsetTime)
  private lazy val setSetOffsetDateTime = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2OffsetDateTime)
  private lazy val setSetZonedDateTime  = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2ZonedDateTime)
  private lazy val setSetUUID           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2UUID)
  private lazy val setSetURI            = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2URI)
  private lazy val setSetByte           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Byte)
  private lazy val setSetShort          = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Short)
  private lazy val setSetChar           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Char)


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
