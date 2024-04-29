package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import org.bson
import org.bson._
import org.bson.conversions.Bson
import org.bson.types.{Decimal128, ObjectId}

trait LambdasOne extends LambdasSet {

  protected case class ResOne[T](
    cast: String => BsonDocument => T,
    castOpt: String => BsonDocument => Option[T],
    eq: (String, T) => Bson,
    neq: (String, T) => Bson,
    lt: (String, T) => Bson,
    gt: (String, T) => Bson,
    le: (String, T) => Bson,
    ge: (String, T) => Bson,
    castSet: String => BsonDocument => Set[T],
    v2bson: T => BsonValue
  )

  protected lazy val resID             = ResOne(castID, castOptID, eqID, neqID, ltID, gtID, leID, geID, castSetID, v2bsonID)
  protected lazy val resString         = ResOne(castString, castOptString, eqString, neqString, ltString, gtString, leString, geString, castSetString, v2bsonString)
  protected lazy val resInt            = ResOne(castInt, castOptInt, eqInt, neqInt, ltInt, gtInt, leInt, geInt, castSetInt, v2bsonInt)
  protected lazy val resLong           = ResOne(castLong, castOptLong, eqLong, neqLong, ltLong, gtLong, leLong, geLong, castSetLong, v2bsonLong)
  protected lazy val resFloat          = ResOne(castFloat, castOptFloat, eqFloat, neqFloat, ltFloat, gtFloat, leFloat, geFloat, castSetFloat, v2bsonFloat)
  protected lazy val resDouble         = ResOne(castDouble, castOptDouble, eqDouble, neqDouble, ltDouble, gtDouble, leDouble, geDouble, castSetDouble, v2bsonDouble)
  protected lazy val resBoolean        = ResOne(castBoolean, castOptBoolean, eqBoolean, neqBoolean, ltBoolean, gtBoolean, leBoolean, geBoolean, castSetBoolean, v2bsonBoolean)
  protected lazy val resBigInt         = ResOne(castBigInt, castOptBigInt, eqBigInt, neqBigInt, ltBigInt, gtBigInt, leBigInt, geBigInt, castSetBigInt, v2bsonBigInt)
  protected lazy val resBigDecimal     = ResOne(castBigDecimal, castOptBigDecimal, eqBigDecimal, neqBigDecimal, ltBigDecimal, gtBigDecimal, leBigDecimal, geBigDecimal, castSetBigDecimal, v2bsonBigDecimal)
  protected lazy val resDate           = ResOne(castDate, castOptDate, eqDate, neqDate, ltDate, gtDate, leDate, geDate, castSetDate, v2bsonDate)
  protected lazy val resDuration       = ResOne(castDuration, castOptDuration, eqDuration, neqDuration, ltDuration, gtDuration, leDuration, geDuration, castSetDuration, v2bsonDuration)
  protected lazy val resInstant        = ResOne(castInstant, castOptInstant, eqInstant, neqInstant, ltInstant, gtInstant, leInstant, geInstant, castSetInstant, v2bsonInstant)
  protected lazy val resLocalDate      = ResOne(castLocalDate, castOptLocalDate, eqLocalDate, neqLocalDate, ltLocalDate, gtLocalDate, leLocalDate, geLocalDate, castSetLocalDate, v2bsonLocalDate)
  protected lazy val resLocalTime      = ResOne(castLocalTime, castOptLocalTime, eqLocalTime, neqLocalTime, ltLocalTime, gtLocalTime, leLocalTime, geLocalTime, castSetLocalTime, v2bsonLocalTime)
  protected lazy val resLocalDateTime  = ResOne(castLocalDateTime, castOptLocalDateTime, eqLocalDateTime, neqLocalDateTime, ltLocalDateTime, gtLocalDateTime, leLocalDateTime, geLocalDateTime, castSetLocalDateTime, v2bsonLocalDateTime)
  protected lazy val resOffsetTime     = ResOne(castOffsetTime, castOptOffsetTime, eqOffsetTime, neqOffsetTime, ltOffsetTime, gtOffsetTime, leOffsetTime, geOffsetTime, castSetOffsetTime, v2bsonOffsetTime)
  protected lazy val resOffsetDateTime = ResOne(castOffsetDateTime, castOptOffsetDateTime, eqOffsetDateTime, neqOffsetDateTime, ltOffsetDateTime, gtOffsetDateTime, leOffsetDateTime, geOffsetDateTime, castSetOffsetDateTime, v2bsonOffsetDateTime)
  protected lazy val resZonedDateTime  = ResOne(castZonedDateTime, castOptZonedDateTime, eqZonedDateTime, neqZonedDateTime, ltZonedDateTime, gtZonedDateTime, leZonedDateTime, geZonedDateTime, castSetZonedDateTime, v2bsonZonedDateTime)
  protected lazy val resUUID           = ResOne(castUUID, castOptUUID, eqUUID, neqUUID, ltUUID, gtUUID, leUUID, geUUID, castSetUUID, v2bsonUUID)
  protected lazy val resURI            = ResOne(castURI, castOptURI, eqURI, neqURI, ltURI, gtURI, leURI, geURI, castSetURI, v2bsonURI)
  protected lazy val resByte           = ResOne(castByte, castOptByte, eqByte, neqByte, ltByte, gtByte, leByte, geByte, castSetByte, v2bsonByte)
  protected lazy val resShort          = ResOne(castShort, castOptShort, eqShort, neqShort, ltShort, gtShort, leShort, geShort, castSetShort, v2bsonShort)
  protected lazy val resChar           = ResOne(castChar, castOptChar, eqChar, neqChar, ltChar, gtChar, leChar, geChar, castSetChar, v2bsonChar)


  protected lazy val eqID             = (field: String, v: String) => {
    def oid: BsonObjectId = if (v.length != 24) {
      throw ModelError("Object id string should be a hex string with 24 characters. Found: " + v)
    } else {
      new BsonObjectId(new ObjectId(v))
    }
    Filters.eq[BsonObjectId](field, if (v == null) null else oid)
  }
  protected lazy val eqString         = (field: String, v: String) => Filters.eq[String](field, v)
  protected lazy val eqInt            = (field: String, v: Int) => Filters.eq(field, v)
  protected lazy val eqLong           = (field: String, v: Long) => Filters.eq[Long](field, v)
  protected lazy val eqFloat          = (field: String, v: Float) => Filters.eq[Float](field, v)
  protected lazy val eqDouble         = (field: String, v: Double) => Filters.eq[Double](field, v)
  protected lazy val eqBoolean        = (field: String, v: Boolean) => Filters.eq[Boolean](field, v)
  protected lazy val eqBigInt         = (field: String, v: BigInt) => Filters.eq[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val eqBigDecimal     = (field: String, v: BigDecimal) => Filters.eq[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val eqDate           = (field: String, v: Date) => Filters.eq[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val eqDuration       = (field: String, v: Duration) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqInstant        = (field: String, v: Instant) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqLocalDate      = (field: String, v: LocalDate) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqLocalTime      = (field: String, v: LocalTime) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqLocalDateTime  = (field: String, v: LocalDateTime) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqOffsetTime     = (field: String, v: OffsetTime) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqUUID           = (field: String, v: UUID) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqURI            = (field: String, v: URI) => Filters.eq[String](field, if (v == null) null else v.toString)
  protected lazy val eqByte           = (field: String, v: Byte) => Filters.eq[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val eqShort          = (field: String, v: Short) => Filters.eq[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val eqChar           = (field: String, v: Char) => Filters.eq[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)

  protected lazy val neqID             = (field: String, v: String) => Filters.ne[BsonObjectId](field, new BsonObjectId(new ObjectId(v)))
  protected lazy val neqString         = (field: String, v: String) => Filters.ne[String](field, v)
  protected lazy val neqInt            = (field: String, v: Int) => Filters.ne[Int](field, v)
  protected lazy val neqLong           = (field: String, v: Long) => Filters.ne[Long](field, v)
  protected lazy val neqFloat          = (field: String, v: Float) => Filters.ne[Float](field, v)
  protected lazy val neqDouble         = (field: String, v: Double) => Filters.ne[Double](field, v)
  protected lazy val neqBoolean        = (field: String, v: Boolean) => Filters.ne[Boolean](field, v)
  protected lazy val neqBigInt         = (field: String, v: BigInt) => Filters.ne[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val neqBigDecimal     = (field: String, v: BigDecimal) => Filters.ne[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val neqDate           = (field: String, v: Date) => Filters.ne[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val neqDuration       = (field: String, v: Duration) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqInstant        = (field: String, v: Instant) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqLocalDate      = (field: String, v: LocalDate) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqLocalTime      = (field: String, v: LocalTime) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqLocalDateTime  = (field: String, v: LocalDateTime) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqOffsetTime     = (field: String, v: OffsetTime) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqUUID           = (field: String, v: UUID) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqURI            = (field: String, v: URI) => Filters.ne[String](field, if (v == null) null else v.toString)
  protected lazy val neqByte           = (field: String, v: Byte) => Filters.ne[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val neqShort          = (field: String, v: Short) => Filters.ne[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val neqChar           = (field: String, v: Char) => Filters.ne[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)

  protected lazy val ltID             = (field: String, v: String) => Filters.lt[BsonObjectId](field, new BsonObjectId(new ObjectId(v)))
  protected lazy val ltString         = (field: String, v: String) => Filters.lt[String](field, v)
  protected lazy val ltInt            = (field: String, v: Int) => Filters.lt[Int](field, v)
  protected lazy val ltLong           = (field: String, v: Long) => Filters.lt[Long](field, v)
  protected lazy val ltFloat          = (field: String, v: Float) => Filters.lt[Float](field, v)
  protected lazy val ltDouble         = (field: String, v: Double) => Filters.lt[Double](field, v)
  protected lazy val ltBoolean        = (field: String, v: Boolean) => Filters.lt[Boolean](field, v)
  protected lazy val ltBigInt         = (field: String, v: BigInt) => Filters.lt[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val ltBigDecimal     = (field: String, v: BigDecimal) => Filters.lt[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val ltDate           = (field: String, v: Date) => Filters.lt[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val ltDuration       = (field: String, v: Duration) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltInstant        = (field: String, v: Instant) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltLocalDate      = (field: String, v: LocalDate) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltLocalTime      = (field: String, v: LocalTime) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltLocalDateTime  = (field: String, v: LocalDateTime) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltOffsetTime     = (field: String, v: OffsetTime) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltUUID           = (field: String, v: UUID) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltURI            = (field: String, v: URI) => Filters.lt[String](field, if (v == null) null else v.toString)
  protected lazy val ltByte           = (field: String, v: Byte) => Filters.lt[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val ltShort          = (field: String, v: Short) => Filters.lt[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val ltChar           = (field: String, v: Char) => Filters.lt[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)

  protected lazy val gtID             = (field: String, v: String) => Filters.gt[BsonObjectId](field, new BsonObjectId(new ObjectId(v)))
  protected lazy val gtString         = (field: String, v: String) => Filters.gt[String](field, v)
  protected lazy val gtInt            = (field: String, v: Int) => Filters.gt[Int](field, v)
  protected lazy val gtLong           = (field: String, v: Long) => Filters.gt[Long](field, v)
  protected lazy val gtFloat          = (field: String, v: Float) => Filters.gt[Float](field, v)
  protected lazy val gtDouble         = (field: String, v: Double) => Filters.gt[Double](field, v)
  protected lazy val gtBoolean        = (field: String, v: Boolean) => Filters.gt[Boolean](field, v)
  protected lazy val gtBigInt         = (field: String, v: BigInt) => Filters.gt[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val gtBigDecimal     = (field: String, v: BigDecimal) => Filters.gt[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val gtDate           = (field: String, v: Date) => Filters.gt[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val gtDuration       = (field: String, v: Duration) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtInstant        = (field: String, v: Instant) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtLocalDate      = (field: String, v: LocalDate) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtLocalTime      = (field: String, v: LocalTime) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtLocalDateTime  = (field: String, v: LocalDateTime) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtOffsetTime     = (field: String, v: OffsetTime) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtUUID           = (field: String, v: UUID) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtURI            = (field: String, v: URI) => Filters.gt[String](field, if (v == null) null else v.toString)
  protected lazy val gtByte           = (field: String, v: Byte) => Filters.gt[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val gtShort          = (field: String, v: Short) => Filters.gt[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val gtChar           = (field: String, v: Char) => Filters.gt[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)

  protected lazy val leID             = (field: String, v: String) => Filters.lte[BsonObjectId](field, new BsonObjectId(new ObjectId(v)))
  protected lazy val leString         = (field: String, v: String) => Filters.lte[String](field, v)
  protected lazy val leInt            = (field: String, v: Int) => Filters.lte[Int](field, v)
  protected lazy val leLong           = (field: String, v: Long) => Filters.lte[Long](field, v)
  protected lazy val leFloat          = (field: String, v: Float) => Filters.lte[Float](field, v)
  protected lazy val leDouble         = (field: String, v: Double) => Filters.lte[Double](field, v)
  protected lazy val leBoolean        = (field: String, v: Boolean) => Filters.lte[Boolean](field, v)
  protected lazy val leBigInt         = (field: String, v: BigInt) => Filters.lte[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val leBigDecimal     = (field: String, v: BigDecimal) => Filters.lte[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val leDate           = (field: String, v: Date) => Filters.lte[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val leDuration       = (field: String, v: Duration) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leInstant        = (field: String, v: Instant) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leLocalDate      = (field: String, v: LocalDate) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leLocalTime      = (field: String, v: LocalTime) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leLocalDateTime  = (field: String, v: LocalDateTime) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leOffsetTime     = (field: String, v: OffsetTime) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leUUID           = (field: String, v: UUID) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leURI            = (field: String, v: URI) => Filters.lte[String](field, if (v == null) null else v.toString)
  protected lazy val leByte           = (field: String, v: Byte) => Filters.lte[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val leShort          = (field: String, v: Short) => Filters.lte[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val leChar           = (field: String, v: Char) => Filters.lte[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)

  protected lazy val geID             = (field: String, v: String) => Filters.gte[BsonObjectId](field, new BsonObjectId(new ObjectId(v)))
  protected lazy val geString         = (field: String, v: String) => Filters.gte[String](field, v)
  protected lazy val geInt            = (field: String, v: Int) => Filters.gte[Int](field, v)
  protected lazy val geLong           = (field: String, v: Long) => Filters.gte[Long](field, v)
  protected lazy val geFloat          = (field: String, v: Float) => Filters.gte[Float](field, v)
  protected lazy val geDouble         = (field: String, v: Double) => Filters.gte[Double](field, v)
  protected lazy val geBoolean        = (field: String, v: Boolean) => Filters.gte[Boolean](field, v)
  protected lazy val geBigInt         = (field: String, v: BigInt) => Filters.gte[Decimal128](field, if (v == null) null else new Decimal128(BigDecimal(v).bigDecimal))
  protected lazy val geBigDecimal     = (field: String, v: BigDecimal) => Filters.gte[Decimal128](field, if (v == null) null else new Decimal128(v.bigDecimal))
  protected lazy val geDate           = (field: String, v: Date) => Filters.gte[BsonDateTime](field, if (v == null) null else new BsonDateTime(v.getTime))
  protected lazy val geDuration       = (field: String, v: Duration) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geInstant        = (field: String, v: Instant) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geLocalDate      = (field: String, v: LocalDate) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geLocalTime      = (field: String, v: LocalTime) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geLocalDateTime  = (field: String, v: LocalDateTime) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geOffsetTime     = (field: String, v: OffsetTime) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geOffsetDateTime = (field: String, v: OffsetDateTime) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geZonedDateTime  = (field: String, v: ZonedDateTime) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geUUID           = (field: String, v: UUID) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geURI            = (field: String, v: URI) => Filters.gte[String](field, if (v == null) null else v.toString)
  protected lazy val geByte           = (field: String, v: Byte) => Filters.gte[Int](field, if (v == null.asInstanceOf[Byte]) null.asInstanceOf[Int] else v)
  protected lazy val geShort          = (field: String, v: Short) => Filters.gte[Int](field, if (v == null.asInstanceOf[Short]) null.asInstanceOf[Int] else v)
  protected lazy val geChar           = (field: String, v: Char) => Filters.gte[String](field, if (v == null.asInstanceOf[Char]) null else v.toString)


  protected lazy val castOptID: String => BsonDocument => Option[String] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[String]
      case v: BsonObjectId    => Some(v.getValue.toString)
      case other              => throw ModelError(
        "Can't query for non-existing ids of embedded documents in MongoDB. Found: " + other
      )
    }

  protected lazy val castOptString: String => BsonDocument => Option[String] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[String]
      case v                  => Some(v.asString.getValue)
    }

  protected lazy val castOptInt: String => BsonDocument => Option[Int] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Int]
      case v                  => Some(v.asInt32.getValue)
    }

  protected lazy val castOptLong: String => BsonDocument => Option[Long] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Long]
      case v                  => Some(v.asInt64.getValue)
    }

  protected lazy val castOptFloat: String => BsonDocument => Option[Float] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Float]
      case v                  => Some(v.asDouble.getValue.toFloat)
    }

  protected lazy val castOptDouble: String => BsonDocument => Option[Double] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Double]
      case v                  => Some(v.asDouble.getValue)
    }

  protected lazy val castOptBoolean: String => BsonDocument => Option[Boolean] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Boolean]
      case v                  => Some(v.asBoolean.getValue)
    }

  protected lazy val castOptBigInt: String => BsonDocument => Option[BigInt] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[BigInt]
      case v                  => Some(BigInt(v.asDecimal128.getValue.bigDecimalValue.toBigInteger))
    }

  protected lazy val castOptBigDecimal: String => BsonDocument => Option[BigDecimal] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[BigDecimal]
      case v                  => Some(BigDecimal(v.asDecimal128.getValue.bigDecimalValue))
    }

  protected lazy val castOptDate: String => BsonDocument => Option[Date] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Date]
      case v                  => Some(new Date(v.asDateTime.getValue))
    }

  protected lazy val castOptDuration: String => BsonDocument => Option[Duration] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Duration]
      case v                  => Some(Duration.parse(v.asString.getValue))
    }

  protected lazy val castOptInstant: String => BsonDocument => Option[Instant] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Instant]
      case v                  => Some(Instant.parse(v.asString.getValue))
    }

  protected lazy val castOptLocalDate: String => BsonDocument => Option[LocalDate] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[LocalDate]
      case v                  => Some(LocalDate.parse(v.asString.getValue))
    }

  protected lazy val castOptLocalTime: String => BsonDocument => Option[LocalTime] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[LocalTime]
      case v                  => Some(LocalTime.parse(v.asString.getValue))
    }

  protected lazy val castOptLocalDateTime: String => BsonDocument => Option[LocalDateTime] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[LocalDateTime]
      case v                  => Some(LocalDateTime.parse(v.asString.getValue))
    }

  protected lazy val castOptOffsetTime: String => BsonDocument => Option[OffsetTime] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[OffsetTime]
      case v                  => Some(OffsetTime.parse(v.asString.getValue))
    }

  protected lazy val castOptOffsetDateTime: String => BsonDocument => Option[OffsetDateTime] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[OffsetDateTime]
      case v                  => Some(OffsetDateTime.parse(v.asString.getValue))
    }

  protected lazy val castOptZonedDateTime: String => BsonDocument => Option[ZonedDateTime] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[ZonedDateTime]
      case v                  => Some(ZonedDateTime.parse(v.asString.getValue))
    }

  protected lazy val castOptUUID: String => BsonDocument => Option[UUID] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[UUID]
      case v                  => Some(UUID.fromString(v.asString.getValue))
    }

  protected lazy val castOptURI: String => BsonDocument => Option[URI] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[URI]
      case v                  => Some(new URI(v.asString.getValue))
    }

  protected lazy val castOptByte: String => BsonDocument => Option[Byte] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Byte]
      case v                  => Some(v.asInt32.getValue.toByte)
    }

  protected lazy val castOptShort: String => BsonDocument => Option[Short] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Short]
      case v                  => Some(v.asInt32.getValue.toShort)
    }

  protected lazy val castOptChar: String => BsonDocument => Option[Char] =
    (field: String) => (doc: BsonDocument) => doc.get(field) match {
      case _: BsonNull | null => Option.empty[Char]
      case v                  => Some(v.asString.getValue.charAt(0))
    }
}
