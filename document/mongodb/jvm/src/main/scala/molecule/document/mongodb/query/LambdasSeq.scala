package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import org.bson._
import scala.collection.mutable.ListBuffer

trait LambdasSeq extends LambdasBase with JavaConversions {

  case class ResSeq[T](
    tpe: String,
    castSeq: String => BsonDocument => Seq[T],
    castOptSet: String => BsonDocument => Option[Seq[T]],
    v2bson: T => BsonValue,
    //    castSetSet: String => BsonDocument => Set[Set[T]],
    //    v2set: String => BsonDocument => Set[T],
  )

  lazy val resSeqID            : ResSeq[String]         = ResSeq("String", castSeqID, castOptSeqID, v2bsonID) //, setSetID, v2setID)
  lazy val resSeqString        : ResSeq[String]         = ResSeq("String", castSeqString, castOptSeqString, v2bsonString) //, setSetString, v2setString)
  lazy val resSeqInt           : ResSeq[Int]            = ResSeq("Int", castSeqInt, castOptSeqInt, v2bsonInt) //, setSetInt, v2setInt)
  lazy val resSeqLong          : ResSeq[Long]           = ResSeq("Long", castSeqLong, castOptSeqLong, v2bsonLong) //, setSetLong, v2setLong)
  lazy val resSeqFloat         : ResSeq[Float]          = ResSeq("Float", castSeqFloat, castOptSeqFloat, v2bsonFloat) //, setSetFloat, v2setFloat)
  lazy val resSeqDouble        : ResSeq[Double]         = ResSeq("Double", castSeqDouble, castOptSeqDouble, v2bsonDouble) //, setSetDouble, v2setDouble)
  lazy val resSeqBoolean       : ResSeq[Boolean]        = ResSeq("Boolean", castSeqBoolean, castOptSeqBoolean, v2bsonBoolean) //, setSetBoolean, v2setBoolean)
  lazy val resSeqBigInt        : ResSeq[BigInt]         = ResSeq("BigInt", castSeqBigInt, castOptSeqBigInt, v2bsonBigInt) //, setSetBigInt, v2setBigInt)
  lazy val resSeqBigDecimal    : ResSeq[BigDecimal]     = ResSeq("BigDecimal", castSeqBigDecimal, castOptSeqBigDecimal, v2bsonBigDecimal) //, setSetBigDecimal, v2setBigDecimal)
  lazy val resSeqDate          : ResSeq[Date]           = ResSeq("Date", castSeqDate, castOptSeqDate, v2bsonDate) //, setSetDate, v2setDate)
  lazy val resSeqDuration      : ResSeq[Duration]       = ResSeq("Duration", castSeqDuration, castOptSeqDuration, v2bsonDuration) //, setSetDuration, v2setDuration)
  lazy val resSeqInstant       : ResSeq[Instant]        = ResSeq("Instant", castSeqInstant, castOptSeqInstant, v2bsonInstant) //, setSetInstant, v2setInstant)
  lazy val resSeqLocalDate     : ResSeq[LocalDate]      = ResSeq("LocalDate", castSeqLocalDate, castOptSeqLocalDate, v2bsonLocalDate) //, setSetLocalDate, v2setLocalDate)
  lazy val resSeqLocalTime     : ResSeq[LocalTime]      = ResSeq("LocalTime", castSeqLocalTime, castOptSeqLocalTime, v2bsonLocalTime) //, setSetLocalTime, v2setLocalTime)
  lazy val resSeqLocalDateTime : ResSeq[LocalDateTime]  = ResSeq("LocalDateTime", castSeqLocalDateTime, castOptSeqLocalDateTime, v2bsonLocalDateTime) //, setSetLocalDateTime, v2setLocalDateTime)
  lazy val resSeqOffsetTime    : ResSeq[OffsetTime]     = ResSeq("OffsetTime", castSeqOffsetTime, castOptSeqOffsetTime, v2bsonOffsetTime) //, setSetOffsetTime, v2setOffsetTime)
  lazy val resSeqOffsetDateTime: ResSeq[OffsetDateTime] = ResSeq("OffsetDateTime", castSeqOffsetDateTime, castOptSeqOffsetDateTime, v2bsonOffsetDateTime) //, setSetOffsetDateTime, v2setOffsetDateTime)
  lazy val resSeqZonedDateTime : ResSeq[ZonedDateTime]  = ResSeq("ZonedDateTime", castSeqZonedDateTime, castOptSeqZonedDateTime, v2bsonZonedDateTime) //, setSetZonedDateTime, v2setZonedDateTime)
  lazy val resSeqUUID          : ResSeq[UUID]           = ResSeq("UUID", castSeqUUID, castOptSeqUUID, v2bsonUUID) //, setSetUUID, v2setUUID)
  lazy val resSeqURI           : ResSeq[URI]            = ResSeq("URI", castSeqURI, castOptSeqURI, v2bsonURI) //, setSetURI, v2setURI)
  lazy val resSeqByte          : ResSeq[Byte]           = ResSeq("Byte", castSeqByte, castOptSeqByte, v2bsonByte) //, setSetByte, v2setByte)
  lazy val resSeqShort         : ResSeq[Short]          = ResSeq("Short", castSeqShort, castOptSeqShort, v2bsonShort) //, setSetShort, v2setShort)
  lazy val resSeqChar          : ResSeq[Char]           = ResSeq("Char", castSeqChar, castOptSeqChar, v2bsonChar) //, setSetChar, v2setChar)


  protected def castSeq[T](doc: BsonDocument, field: String, value: BsonValue => T): Seq[T] = {
    val raw = doc.get(field)
    if (raw == null) {
      List.empty[T]
    } else {
      val array = raw.asArray.iterator
      val buf   = ListBuffer.empty[T]
      while (array.hasNext) {
        buf += value(array.next)
      }
      buf.toList
    }
  }

  private lazy val castSeqID             = (field: String) => (doc: BsonDocument) => castSeq[String](doc, field, bson2ID)
  private lazy val castSeqString         = (field: String) => (doc: BsonDocument) => castSeq[String](doc, field, bson2String)
  private lazy val castSeqInt            = (field: String) => (doc: BsonDocument) => castSeq[Int](doc, field, bson2Int)
  private lazy val castSeqLong           = (field: String) => (doc: BsonDocument) => castSeq[Long](doc, field, bson2Long)
  private lazy val castSeqFloat          = (field: String) => (doc: BsonDocument) => castSeq[Float](doc, field, bson2Float)
  private lazy val castSeqDouble         = (field: String) => (doc: BsonDocument) => castSeq[Double](doc, field, bson2Double)
  private lazy val castSeqBoolean        = (field: String) => (doc: BsonDocument) => castSeq[Boolean](doc, field, bson2Boolean)
  private lazy val castSeqBigInt         = (field: String) => (doc: BsonDocument) => castSeq[BigInt](doc, field, bson2BigInt)
  private lazy val castSeqBigDecimal     = (field: String) => (doc: BsonDocument) => castSeq[BigDecimal](doc, field, bson2BigDecimal)
  private lazy val castSeqDate           = (field: String) => (doc: BsonDocument) => castSeq[Date](doc, field, bson2Date)
  private lazy val castSeqDuration       = (field: String) => (doc: BsonDocument) => castSeq[Duration](doc, field, bson2Duration)
  private lazy val castSeqInstant        = (field: String) => (doc: BsonDocument) => castSeq[Instant](doc, field, bson2Instant)
  private lazy val castSeqLocalDate      = (field: String) => (doc: BsonDocument) => castSeq[LocalDate](doc, field, bson2LocalDate)
  private lazy val castSeqLocalTime      = (field: String) => (doc: BsonDocument) => castSeq[LocalTime](doc, field, bson2LocalTime)
  private lazy val castSeqLocalDateTime  = (field: String) => (doc: BsonDocument) => castSeq[LocalDateTime](doc, field, bson2LocalDateTime)
  private lazy val castSeqOffsetTime     = (field: String) => (doc: BsonDocument) => castSeq[OffsetTime](doc, field, bson2OffsetTime)
  private lazy val castSeqOffsetDateTime = (field: String) => (doc: BsonDocument) => castSeq[OffsetDateTime](doc, field, bson2OffsetDateTime)
  private lazy val castSeqZonedDateTime  = (field: String) => (doc: BsonDocument) => castSeq[ZonedDateTime](doc, field, bson2ZonedDateTime)
  private lazy val castSeqUUID           = (field: String) => (doc: BsonDocument) => castSeq[UUID](doc, field, bson2UUID)
  private lazy val castSeqURI            = (field: String) => (doc: BsonDocument) => castSeq[URI](doc, field, bson2URI)
  private lazy val castSeqByte           = (field: String) => (doc: BsonDocument) => castSeq[Byte](doc, field, bson2Byte)
  private lazy val castSeqShort          = (field: String) => (doc: BsonDocument) => castSeq[Short](doc, field, bson2Short)
  private lazy val castSeqChar           = (field: String) => (doc: BsonDocument) => castSeq[Char](doc, field, bson2Char)


  //  private lazy val v2setID             = (field: String) => (doc: BsonDocument) => Set(bson2ID(doc.get(field)))
  //  private lazy val v2setString         = (field: String) => (doc: BsonDocument) => Set(bson2String(doc.get(field)))
  //  private lazy val v2setInt            = (field: String) => (doc: BsonDocument) => Set(bson2Int(doc.get(field)))
  //  private lazy val v2setLong           = (field: String) => (doc: BsonDocument) => Set(bson2Long(doc.get(field)))
  //  private lazy val v2setFloat          = (field: String) => (doc: BsonDocument) => Set(bson2Float(doc.get(field)))
  //  private lazy val v2setDouble         = (field: String) => (doc: BsonDocument) => Set(bson2Double(doc.get(field)))
  //  private lazy val v2setBoolean        = (field: String) => (doc: BsonDocument) => Set(bson2Boolean(doc.get(field)))
  //  private lazy val v2setBigInt         = (field: String) => (doc: BsonDocument) => Set(bson2BigInt(doc.get(field)))
  //  private lazy val v2setBigDecimal     = (field: String) => (doc: BsonDocument) => Set(bson2BigDecimal(doc.get(field)))
  //  private lazy val v2setDate           = (field: String) => (doc: BsonDocument) => Set(bson2Date(doc.get(field)))
  //  private lazy val v2setDuration       = (field: String) => (doc: BsonDocument) => Set(bson2Duration(doc.get(field)))
  //  private lazy val v2setInstant        = (field: String) => (doc: BsonDocument) => Set(bson2Instant(doc.get(field)))
  //  private lazy val v2setLocalDate      = (field: String) => (doc: BsonDocument) => Set(bson2LocalDate(doc.get(field)))
  //  private lazy val v2setLocalTime      = (field: String) => (doc: BsonDocument) => Set(bson2LocalTime(doc.get(field)))
  //  private lazy val v2setLocalDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2LocalDateTime(doc.get(field)))
  //  private lazy val v2setOffsetTime     = (field: String) => (doc: BsonDocument) => Set(bson2OffsetTime(doc.get(field)))
  //  private lazy val v2setOffsetDateTime = (field: String) => (doc: BsonDocument) => Set(bson2OffsetDateTime(doc.get(field)))
  //  private lazy val v2setZonedDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2ZonedDateTime(doc.get(field)))
  //  private lazy val v2setUUID           = (field: String) => (doc: BsonDocument) => Set(bson2UUID(doc.get(field)))
  //  private lazy val v2setURI            = (field: String) => (doc: BsonDocument) => Set(bson2URI(doc.get(field)))
  //  private lazy val v2setByte           = (field: String) => (doc: BsonDocument) => Set(bson2Byte(doc.get(field)))
  //  private lazy val v2setShort          = (field: String) => (doc: BsonDocument) => Set(bson2Short(doc.get(field)))
  //  private lazy val v2setChar           = (field: String) => (doc: BsonDocument) => Set(bson2Char(doc.get(field)))
  //
  //
  private lazy   val bson2ID            : BsonValue => String         = (bv: BsonValue) => {
    if (bv.isObjectId) bv.asObjectId().getValue.toString else bv.asString.getValue
  }
  private lazy   val bson2String        : BsonValue => String         = (bv: BsonValue) => bv.asString.getValue
  protected lazy val bson2Int           : BsonValue => Int            = (bv: BsonValue) => bv.asInt32.getValue
  private lazy   val bson2Long          : BsonValue => Long           = (bv: BsonValue) => bv.asInt64.getValue
  private lazy   val bson2Float         : BsonValue => Float          = (bv: BsonValue) => bv.asDouble.getValue.toFloat
  private lazy   val bson2Double        : BsonValue => Double         = (bv: BsonValue) => bv.asDouble.getValue
  private lazy   val bson2Boolean       : BsonValue => Boolean        = (bv: BsonValue) => bv.asBoolean.getValue
  private lazy   val bson2BigInt        : BsonValue => BigInt         = (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger)
  private lazy   val bson2BigDecimal    : BsonValue => BigDecimal     = (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue)
  private lazy   val bson2Date          : BsonValue => Date           = (bv: BsonValue) => new Date(bv.asDateTime.getValue)
  private lazy   val bson2Duration      : BsonValue => Duration       = (bv: BsonValue) => Duration.parse(bv.asString.getValue)
  private lazy   val bson2Instant       : BsonValue => Instant        = (bv: BsonValue) => Instant.parse(bv.asString.getValue)
  private lazy   val bson2LocalDate     : BsonValue => LocalDate      = (bv: BsonValue) => LocalDate.parse(bv.asString.getValue)
  private lazy   val bson2LocalTime     : BsonValue => LocalTime      = (bv: BsonValue) => LocalTime.parse(bv.asString.getValue)
  private lazy   val bson2LocalDateTime : BsonValue => LocalDateTime  = (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue)
  private lazy   val bson2OffsetTime    : BsonValue => OffsetTime     = (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue)
  private lazy   val bson2OffsetDateTime: BsonValue => OffsetDateTime = (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue)
  private lazy   val bson2ZonedDateTime : BsonValue => ZonedDateTime  = (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue)
  private lazy   val bson2UUID          : BsonValue => UUID           = (bv: BsonValue) => UUID.fromString(bv.asString.getValue)
  private lazy   val bson2URI           : BsonValue => URI            = (bv: BsonValue) => new URI(bv.asString.getValue)
  private lazy   val bson2Byte          : BsonValue => Byte           = (bv: BsonValue) => bv.asInt32.getValue.toByte
  private lazy   val bson2Short         : BsonValue => Short          = (bv: BsonValue) => bv.asInt32.getValue.toShort
  private lazy   val bson2Char          : BsonValue => Char           = (bv: BsonValue) => bv.asString.getValue.charAt(0)
  //
  //
  //  protected def castSetSet[T](doc: BsonDocument, field: String, value: BsonValue => T): Set[Set[T]] = {
  //    val raw = doc.get(field)
  //    if (raw == null) {
  //      Set.empty[Set[T]]
  //    } else {
  //      val outerArray = raw.asArray.iterator
  //      var sets       = Set.empty[Set[T]]
  //      while (outerArray.hasNext) {
  //        var set        = Set.empty[T]
  //        val innerArray = outerArray.next.asArray.iterator
  //        while (innerArray.hasNext) {
  //          set += value(innerArray.next)
  //        }
  //        sets += set
  //      }
  //      sets
  //    }
  //  }
  //
  //  private lazy val setSetID             = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2ID)
  //  private lazy val setSetString         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2String)
  //  private lazy val setSetInt            = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Int)
  //  private lazy val setSetLong           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Long)
  //  private lazy val setSetFloat          = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Float)
  //  private lazy val setSetDouble         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Double)
  //  private lazy val setSetBoolean        = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Boolean)
  //  private lazy val setSetBigInt         = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2BigInt)
  //  private lazy val setSetBigDecimal     = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2BigDecimal)
  //  private lazy val setSetDate           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Date)
  //  private lazy val setSetDuration       = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Duration)
  //  private lazy val setSetInstant        = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Instant)
  //  private lazy val setSetLocalDate      = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalDate)
  //  private lazy val setSetLocalTime      = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalTime)
  //  private lazy val setSetLocalDateTime  = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2LocalDateTime)
  //  private lazy val setSetOffsetTime     = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2OffsetTime)
  //  private lazy val setSetOffsetDateTime = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2OffsetDateTime)
  //  private lazy val setSetZonedDateTime  = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2ZonedDateTime)
  //  private lazy val setSetUUID           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2UUID)
  //  private lazy val setSetURI            = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2URI)
  //  private lazy val setSetByte           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Byte)
  //  private lazy val setSetShort          = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Short)
  //  private lazy val setSetChar           = (field: String) => (doc: BsonDocument) => castSetSet(doc, field, bson2Char)
  //
  //
  protected def castOptSeq[T](doc: BsonDocument, field: String, value: BsonValue => T): Option[Seq[T]] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Option.empty[List[T]]
    } else {
      val array = raw.asArray.iterator
      val buf   = ListBuffer.empty[T]
      while (array.hasNext) {
        buf += value(array.next)
      }
      if (buf.isEmpty) None else Some(buf.toList)
    }
  }

  private lazy val castOptSeqID             = (field: String) => (doc: BsonDocument) => castOptSeq[String](doc, field, (bv: BsonValue) => bv.asObjectId().getValue.toString)
  private lazy val castOptSeqString         = (field: String) => (doc: BsonDocument) => castOptSeq[String](doc, field, (bv: BsonValue) => bv.asString.getValue)
  private lazy val castOptSeqInt            = (field: String) => (doc: BsonDocument) => castOptSeq[Int](doc, field, (bv: BsonValue) => bv.asInt32.getValue)
  private lazy val castOptSeqLong           = (field: String) => (doc: BsonDocument) => castOptSeq[Long](doc, field, (bv: BsonValue) => bv.asInt64.getValue)
  private lazy val castOptSeqFloat          = (field: String) => (doc: BsonDocument) => castOptSeq[Float](doc, field, (bv: BsonValue) => bv.asDouble.getValue.toFloat)
  private lazy val castOptSeqDouble         = (field: String) => (doc: BsonDocument) => castOptSeq[Double](doc, field, (bv: BsonValue) => bv.asDouble.getValue)
  private lazy val castOptSeqBoolean        = (field: String) => (doc: BsonDocument) => castOptSeq[Boolean](doc, field, (bv: BsonValue) => bv.asBoolean.getValue)
  private lazy val castOptSeqBigInt         = (field: String) => (doc: BsonDocument) => castOptSeq[BigInt](doc, field, (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger))
  private lazy val castOptSeqBigDecimal     = (field: String) => (doc: BsonDocument) => castOptSeq[BigDecimal](doc, field, (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue))
  private lazy val castOptSeqDate           = (field: String) => (doc: BsonDocument) => castOptSeq[Date](doc, field, (bv: BsonValue) => new Date(bv.asDateTime.getValue))
  private lazy val castOptSeqDuration       = (field: String) => (doc: BsonDocument) => castOptSeq[Duration](doc, field, (bv: BsonValue) => Duration.parse(bv.asString.getValue))
  private lazy val castOptSeqInstant        = (field: String) => (doc: BsonDocument) => castOptSeq[Instant](doc, field, (bv: BsonValue) => Instant.parse(bv.asString.getValue))
  private lazy val castOptSeqLocalDate      = (field: String) => (doc: BsonDocument) => castOptSeq[LocalDate](doc, field, (bv: BsonValue) => LocalDate.parse(bv.asString.getValue))
  private lazy val castOptSeqLocalTime      = (field: String) => (doc: BsonDocument) => castOptSeq[LocalTime](doc, field, (bv: BsonValue) => LocalTime.parse(bv.asString.getValue))
  private lazy val castOptSeqLocalDateTime  = (field: String) => (doc: BsonDocument) => castOptSeq[LocalDateTime](doc, field, (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue))
  private lazy val castOptSeqOffsetTime     = (field: String) => (doc: BsonDocument) => castOptSeq[OffsetTime](doc, field, (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue))
  private lazy val castOptSeqOffsetDateTime = (field: String) => (doc: BsonDocument) => castOptSeq[OffsetDateTime](doc, field, (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue))
  private lazy val castOptSeqZonedDateTime  = (field: String) => (doc: BsonDocument) => castOptSeq[ZonedDateTime](doc, field, (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue))
  private lazy val castOptSeqUUID           = (field: String) => (doc: BsonDocument) => castOptSeq[UUID](doc, field, (bv: BsonValue) => UUID.fromString(bv.asString.getValue))
  private lazy val castOptSeqURI            = (field: String) => (doc: BsonDocument) => castOptSeq[URI](doc, field, (bv: BsonValue) => new URI(bv.asString.getValue))
  private lazy val castOptSeqByte           = (field: String) => (doc: BsonDocument) => castOptSeq[Byte](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toByte)
  private lazy val castOptSeqShort          = (field: String) => (doc: BsonDocument) => castOptSeq[Short](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toShort)
  private lazy val castOptSeqChar           = (field: String) => (doc: BsonDocument) => castOptSeq[Char](doc, field, (bv: BsonValue) => bv.asString.getValue.charAt(0))
}
