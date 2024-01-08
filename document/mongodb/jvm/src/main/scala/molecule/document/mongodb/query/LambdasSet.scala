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
    castSet: String => BsonDocument => Set[T],
    castOptSet: String => BsonDocument => Option[Set[T]],
    v2bson: T => BsonValue,
    castSetSet: String => BsonDocument => Set[Set[T]],
    v2set: String => BsonDocument => Set[T],

  )

  lazy val resSetID            : ResSet[String]         = ResSet("String", castSetID, castOptSetID, v2bsonID, setSetID, v2setID)
  lazy val resSetString        : ResSet[String]         = ResSet("String", castSetString, castOptSetString, v2bsonString, setSetString, v2setString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", castSetInt, castOptSetInt, v2bsonInt, setSetInt, v2setInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", castSetLong, castOptSetLong, v2bsonLong, setSetLong, v2setLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", castSetFloat, castOptSetFloat, v2bsonFloat, setSetFloat, v2setFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", castSetDouble, castOptSetDouble, v2bsonDouble, setSetDouble, v2setDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", castSetBoolean, castOptSetBoolean, v2bsonBoolean, setSetBoolean, v2setBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", castSetBigInt, castOptSetBigInt, v2bsonBigInt, setSetBigInt, v2setBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", castSetBigDecimal, castOptSetBigDecimal, v2bsonBigDecimal, setSetBigDecimal, v2setBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", castSetDate, castOptSetDate, v2bsonDate, setSetDate, v2setDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", castSetDuration, castOptSetDuration, v2bsonDuration, setSetDuration, v2setDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", castSetInstant, castOptSetInstant, v2bsonInstant, setSetInstant, v2setInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", castSetLocalDate, castOptSetLocalDate, v2bsonLocalDate, setSetLocalDate, v2setLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", castSetLocalTime, castOptSetLocalTime, v2bsonLocalTime, setSetLocalTime, v2setLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", castSetLocalDateTime, castOptSetLocalDateTime, v2bsonLocalDateTime, setSetLocalDateTime, v2setLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", castSetOffsetTime, castOptSetOffsetTime, v2bsonOffsetTime, setSetOffsetTime, v2setOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", castSetOffsetDateTime, castOptSetOffsetDateTime, v2bsonOffsetDateTime, setSetOffsetDateTime, v2setOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", castSetZonedDateTime, castOptSetZonedDateTime, v2bsonZonedDateTime, setSetZonedDateTime, v2setZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", castSetUUID, castOptSetUUID, v2bsonUUID, setSetUUID, v2setUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", castSetURI, castOptSetURI, v2bsonURI, setSetURI, v2setURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", castSetByte, castOptSetByte, v2bsonByte, setSetByte, v2setByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", castSetShort, castOptSetShort, v2bsonShort, setSetShort, v2setShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", castSetChar, castOptSetChar, v2bsonChar, setSetChar, v2setChar)


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


  protected lazy val v2setID             = (field: String) => (doc: BsonDocument) => Set(bson2ID(doc.get(field)))
  protected lazy val v2setString         = (field: String) => (doc: BsonDocument) => Set(bson2String(doc.get(field)))
  protected lazy val v2setInt            = (field: String) => (doc: BsonDocument) => Set(bson2Int(doc.get(field)))
  protected lazy val v2setLong           = (field: String) => (doc: BsonDocument) => Set(bson2Long(doc.get(field)))
  protected lazy val v2setFloat          = (field: String) => (doc: BsonDocument) => Set(bson2Float(doc.get(field)))
  protected lazy val v2setDouble         = (field: String) => (doc: BsonDocument) => Set(bson2Double(doc.get(field)))
  protected lazy val v2setBoolean        = (field: String) => (doc: BsonDocument) => Set(bson2Boolean(doc.get(field)))
  protected lazy val v2setBigInt         = (field: String) => (doc: BsonDocument) => Set(bson2BigInt(doc.get(field)))
  protected lazy val v2setBigDecimal     = (field: String) => (doc: BsonDocument) => Set(bson2BigDecimal(doc.get(field)))
  protected lazy val v2setDate           = (field: String) => (doc: BsonDocument) => Set(bson2Date(doc.get(field)))
  protected lazy val v2setDuration       = (field: String) => (doc: BsonDocument) => Set(bson2Duration(doc.get(field)))
  protected lazy val v2setInstant        = (field: String) => (doc: BsonDocument) => Set(bson2Instant(doc.get(field)))
  protected lazy val v2setLocalDate      = (field: String) => (doc: BsonDocument) => Set(bson2LocalDate(doc.get(field)))
  protected lazy val v2setLocalTime      = (field: String) => (doc: BsonDocument) => Set(bson2LocalTime(doc.get(field)))
  protected lazy val v2setLocalDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2LocalDateTime(doc.get(field)))
  protected lazy val v2setOffsetTime     = (field: String) => (doc: BsonDocument) => Set(bson2OffsetTime(doc.get(field)))
  protected lazy val v2setOffsetDateTime = (field: String) => (doc: BsonDocument) => Set(bson2OffsetDateTime(doc.get(field)))
  protected lazy val v2setZonedDateTime  = (field: String) => (doc: BsonDocument) => Set(bson2ZonedDateTime(doc.get(field)))
  protected lazy val v2setUUID           = (field: String) => (doc: BsonDocument) => Set(bson2UUID(doc.get(field)))
  protected lazy val v2setURI            = (field: String) => (doc: BsonDocument) => Set(bson2URI(doc.get(field)))
  protected lazy val v2setByte           = (field: String) => (doc: BsonDocument) => Set(bson2Byte(doc.get(field)))
  protected lazy val v2setShort          = (field: String) => (doc: BsonDocument) => Set(bson2Short(doc.get(field)))
  protected lazy val v2setChar           = (field: String) => (doc: BsonDocument) => Set(bson2Char(doc.get(field)))

  //  protected lazy val v2bsonID            : String => BsonValue         = (v: String) => new BsonString(v)
  //  protected lazy val v2bsonString        : String => BsonValue         = (v: String) => new BsonString(v)
  //  protected lazy val v2bsonInt           : Int => BsonValue            = (v: Int) => new BsonInt32(v)
  //  protected lazy val v2bsonLong          : Long => BsonValue           = (v: Long) => new BsonInt64(v)
  //  protected lazy val v2bsonFloat         : Float => BsonValue          = (v: Float) => new BsonDouble(v)
  //  protected lazy val v2bsonDouble        : Double => BsonValue         = (v: Double) => new BsonDouble(v)
  //  protected lazy val v2bsonBoolean       : Boolean => BsonValue        = (v: Boolean) => new BsonBoolean(v)
  //  protected lazy val v2bsonBigInt        : BigInt => BsonValue         = (v: BigInt) => new BsonDecimal128(new Decimal128(BigDecimal(v).bigDecimal))
  //  protected lazy val v2bsonBigDecimal    : BigDecimal => BsonValue     = (v: BigDecimal) => new BsonDecimal128(new Decimal128(v.bigDecimal))
  //  protected lazy val v2bsonDate          : Date => BsonValue           = (v: Date) => new BsonDateTime(v.getTime)
  //  protected lazy val v2bsonDuration      : Duration => BsonValue       = (v: Duration) => new BsonString(v.toString)
  //  protected lazy val v2bsonInstant       : Instant => BsonValue        = (v: Instant) => new BsonString(v.toString)
  //  protected lazy val v2bsonLocalDate     : LocalDate => BsonValue      = (v: LocalDate) => new BsonString(v.toString)
  //  protected lazy val v2bsonLocalTime     : LocalTime => BsonValue      = (v: LocalTime) => new BsonString(v.toString)
  //  protected lazy val v2bsonLocalDateTime : LocalDateTime => BsonValue  = (v: LocalDateTime) => new BsonString(v.toString)
  //  protected lazy val v2bsonOffsetTime    : OffsetTime => BsonValue     = (v: OffsetTime) => new BsonString(v.toString)
  //  protected lazy val v2bsonOffsetDateTime: OffsetDateTime => BsonValue = (v: OffsetDateTime) => new BsonString(v.toString)
  //  protected lazy val v2bsonZonedDateTime : ZonedDateTime => BsonValue  = (v: ZonedDateTime) => new BsonString(v.toString)
  //  protected lazy val v2bsonUUID          : UUID => BsonValue           = (v: UUID) => new BsonString(v.toString)
  //  protected lazy val v2bsonURI           : URI => BsonValue            = (v: URI) => new BsonString(v.toString)
  //  protected lazy val v2bsonByte          : Byte => BsonValue           = (v: Byte) => new BsonInt32(v)
  //  protected lazy val v2bsonShort         : Short => BsonValue          = (v: Short) => new BsonInt32(v)
  //  protected lazy val v2bsonChar          : Char => BsonValue           = (v: Char) => new BsonString(v.toString)

  //  protected lazy val castString         = (field: String) => (doc: BsonDocument) => doc.get(field).asString.getValue
  //  protected lazy val castInt            = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue
  //  protected lazy val castLong           = (field: String) => (doc: BsonDocument) => doc.get(field).asInt64.getValue
  //  protected lazy val castFloat          = (field: String) => (doc: BsonDocument) => doc.get(field).asDouble.getValue.toFloat
  //  protected lazy val castDouble         = (field: String) => (doc: BsonDocument) => doc.get(field).asDouble.getValue
  //  protected lazy val castBoolean        = (field: String) => (doc: BsonDocument) => doc.get(field).asBoolean.getValue
  //  protected lazy val castBigInt         = (field: String) => (doc: BsonDocument) => BigInt(doc.get(field).asDecimal128.getValue.bigDecimalValue.toBigInteger)
  //  protected lazy val castBigDecimal     = (field: String) => (doc: BsonDocument) => BigDecimal(doc.get(field).asDecimal128.getValue.bigDecimalValue)
  //  protected lazy val castDate           = (field: String) => (doc: BsonDocument) => new Date(doc.get(field).asDateTime.getValue)
  //  protected lazy val castDuration       = (field: String) => (doc: BsonDocument) => Duration.parse(doc.get(field).asString.getValue)
  //  protected lazy val castInstant        = (field: String) => (doc: BsonDocument) => Instant.parse(doc.get(field).asString.getValue)
  //  protected lazy val castLocalDate      = (field: String) => (doc: BsonDocument) => LocalDate.parse(doc.get(field).asString.getValue)
  //  protected lazy val castLocalTime      = (field: String) => (doc: BsonDocument) => LocalTime.parse(doc.get(field).asString.getValue)
  //  protected lazy val castLocalDateTime  = (field: String) => (doc: BsonDocument) => LocalDateTime.parse(doc.get(field).asString.getValue)
  //  protected lazy val castOffsetTime     = (field: String) => (doc: BsonDocument) => OffsetTime.parse(doc.get(field).asString.getValue)
  //  protected lazy val castOffsetDateTime = (field: String) => (doc: BsonDocument) => OffsetDateTime.parse(doc.get(field).asString.getValue)
  //  protected lazy val castZonedDateTime  = (field: String) => (doc: BsonDocument) => ZonedDateTime.parse(doc.get(field).asString.getValue)
  //  protected lazy val castUUID           = (field: String) => (doc: BsonDocument) => UUID.fromString(doc.get(field).asString.getValue)
  //  protected lazy val castURI            = (field: String) => (doc: BsonDocument) => new URI(doc.get(field).asString.getValue)
  //  protected lazy val castByte           = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue.toByte
  //  protected lazy val castShort          = (field: String) => (doc: BsonDocument) => doc.get(field).asInt32.getValue.toShort
  //  protected lazy val castChar           = (field: String) => (doc: BsonDocument) => doc.get(field).asString.getValue.charAt(0)

  //  lazy val resSetString        : ResSet[String]         = ResSet("String", tpeDbString, sql2setString, null, set2sqlArrayString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2setSumString, json2oneString, json2arrayString, one2jsonString)
  //  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", tpeDbInt, sql2setInt, null, set2sqlArrayInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2setSumInt, json2oneInt, json2arrayInt, one2jsonInt)
  //  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", tpeDbLong, sql2setLong, null, set2sqlArrayLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2setSumLong, json2oneLong, json2arrayLong, one2jsonLong)
  //  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", tpeDbFloat, sql2setFloat, null, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2setSumFloat, json2oneFloat, json2arrayFloat, one2jsonFloat)
  //  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", tpeDbDouble, sql2setDouble, null, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2setSumDouble, json2oneDouble, json2arrayDouble, one2jsonDouble)
  //  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", tpeDbBoolean, sql2setBoolean, null, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2setSumBoolean, json2oneBoolean, json2arrayBoolean, one2jsonBoolean)
  //  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", tpeDbBigInt, sql2setBigInt, null, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2setSumBigInt, json2oneBigInt, json2arrayBigInt, one2jsonBigInt)
  //  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", tpeDbBigDecimal, sql2setBigDecimal, null, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2setSumBigDecimal, json2oneBigDecimal, json2arrayBigDecimal, one2jsonBigDecimal)
  //  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", tpeDbDate, sql2setDate, null, set2sqlArrayDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2setSumDate, json2oneDate, json2arrayDate, one2jsonDate)
  //  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", tpeDbDuration, sql2setDuration, null, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, array2setDuration, nestedArray2coalescedSetDuration, nestedArray2nestedSetDuration, array2setFirstDuration, array2setLastDuration, nestedArray2setAscDuration, nestedArray2setDescDuration, nestedArray2setSumDuration, json2oneDuration, json2arrayDuration, one2jsonDuration)
  //  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", tpeDbInstant, sql2setInstant, null, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, array2setInstant, nestedArray2coalescedSetInstant, nestedArray2nestedSetInstant, array2setFirstInstant, array2setLastInstant, nestedArray2setAscInstant, nestedArray2setDescInstant, nestedArray2setSumInstant, json2oneInstant, json2arrayInstant, one2jsonInstant)
  //  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", tpeDbLocalDate, sql2setLocalDate, null, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, array2setLocalDate, nestedArray2coalescedSetLocalDate, nestedArray2nestedSetLocalDate, array2setFirstLocalDate, array2setLastLocalDate, nestedArray2setAscLocalDate, nestedArray2setDescLocalDate, nestedArray2setSumLocalDate, json2oneLocalDate, json2arrayLocalDate, one2jsonLocalDate)
  //  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", tpeDbLocalTime, sql2setLocalTime, null, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, array2setLocalTime, nestedArray2coalescedSetLocalTime, nestedArray2nestedSetLocalTime, array2setFirstLocalTime, array2setLastLocalTime, nestedArray2setAscLocalTime, nestedArray2setDescLocalTime, nestedArray2setSumLocalTime, json2oneLocalTime, json2arrayLocalTime, one2jsonLocalTime)
  //  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", tpeDbLocalDateTime, sql2setLocalDateTime, null, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, array2setLocalDateTime, nestedArray2coalescedSetLocalDateTime, nestedArray2nestedSetLocalDateTime, array2setFirstLocalDateTime, array2setLastLocalDateTime, nestedArray2setAscLocalDateTime, nestedArray2setDescLocalDateTime, nestedArray2setSumLocalDateTime, json2oneLocalDateTime, json2arrayLocalDateTime, one2jsonLocalDateTime)
  //  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", tpeDbOffsetTime, sql2setOffsetTime, null, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, array2setOffsetTime, nestedArray2coalescedSetOffsetTime, nestedArray2nestedSetOffsetTime, array2setFirstOffsetTime, array2setLastOffsetTime, nestedArray2setAscOffsetTime, nestedArray2setDescOffsetTime, nestedArray2setSumOffsetTime, json2oneOffsetTime, json2arrayOffsetTime, one2jsonOffsetTime)
  //  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", tpeDbOffsetDateTime, sql2setOffsetDateTime, null, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, array2setOffsetDateTime, nestedArray2coalescedSetOffsetDateTime, nestedArray2nestedSetOffsetDateTime, array2setFirstOffsetDateTime, array2setLastOffsetDateTime, nestedArray2setAscOffsetDateTime, nestedArray2setDescOffsetDateTime, nestedArray2setSumOffsetDateTime, json2oneOffsetDateTime, json2arrayOffsetDateTime, one2jsonOffsetDateTime)
  //  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", tpeDbZonedDateTime, sql2setZonedDateTime, null, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, array2setZonedDateTime, nestedArray2coalescedSetZonedDateTime, nestedArray2nestedSetZonedDateTime, array2setFirstZonedDateTime, array2setLastZonedDateTime, nestedArray2setAscZonedDateTime, nestedArray2setDescZonedDateTime, nestedArray2setSumZonedDateTime, json2oneZonedDateTime, json2arrayZonedDateTime, one2jsonZonedDateTime)
  //  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", tpeDbUUID, sql2setUUID, null, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2setSumUUID, json2oneUUID, json2arrayUUID, one2jsonUUID)
  //  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", tpeDbURI, sql2setURI, null, set2sqlArrayURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2setSumURI, json2oneURI, json2arrayURI, one2jsonURI)
  //  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", tpeDbByte, sql2setByte, null, set2sqlArrayByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2setSumByte, json2oneByte, json2arrayByte, one2jsonByte)
  //  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", tpeDbShort, sql2setShort, null, set2sqlArrayShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2setSumShort, json2oneShort, json2arrayShort, one2jsonShort)
  //  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", tpeDbChar, sql2setChar, null, set2sqlArrayChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2setSumChar, json2oneChar, json2arrayChar, one2jsonChar)
  //
  //  protected lazy val set2sqlArrayString        : Set[String] => String         = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayInt           : Set[Int] => String            = (set: Set[Int]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayLong          : Set[Long] => String           = (set: Set[Long]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayFloat         : Set[Float] => String          = (set: Set[Float]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayDouble        : Set[Double] => String         = (set: Set[Double]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayBoolean       : Set[Boolean] => String        = (set: Set[Boolean]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayBigInt        : Set[BigInt] => String         = (set: Set[BigInt]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayBigDecimal    : Set[BigDecimal] => String     = (set: Set[BigDecimal]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayDate          : Set[Date] => String           = (set: Set[Date]) => set.map(date2str(_)).mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayDuration      : Set[Duration] => String       = (set: Set[Duration]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayInstant       : Set[Instant] => String        = (set: Set[Instant]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayLocalDate     : Set[LocalDate] => String      = (set: Set[LocalDate]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayLocalTime     : Set[LocalTime] => String      = (set: Set[LocalTime]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayLocalDateTime : Set[LocalDateTime] => String  = (set: Set[LocalDateTime]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayOffsetTime    : Set[OffsetTime] => String     = (set: Set[OffsetTime]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayOffsetDateTime: Set[OffsetDateTime] => String = (set: Set[OffsetDateTime]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayZonedDateTime : Set[ZonedDateTime] => String  = (set: Set[ZonedDateTime]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayUUID          : Set[UUID] => String           = (set: Set[UUID]) => set.mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayURI           : Set[URI] => String            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  //  protected lazy val set2sqlArrayByte          : Set[Byte] => String           = (set: Set[Byte]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayShort         : Set[Short] => String          = (set: Set[Short]) => set.mkString("ARRAY [", ", ", "]")
  //  protected lazy val set2sqlArrayChar          : Set[Char] => String           = (set: Set[Char]) => set.mkString("ARRAY ['", "', '", "']")
  //
  //  private lazy val set2sqlsString        : Set[String] => Set[String]         = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  //  private lazy val set2sqlsInt           : Set[Int] => Set[String]            = (set: Set[Int]) => set.map(_.toString)
  //  private lazy val set2sqlsLong          : Set[Long] => Set[String]           = (set: Set[Long]) => set.map(_.toString)
  //  private lazy val set2sqlsFloat         : Set[Float] => Set[String]          = (set: Set[Float]) => set.map(_.toString)
  //  private lazy val set2sqlsDouble        : Set[Double] => Set[String]         = (set: Set[Double]) => set.map(_.toString)
  //  private lazy val set2sqlsBoolean       : Set[Boolean] => Set[String]        = (set: Set[Boolean]) => set.map(_.toString)
  //  private lazy val set2sqlsBigInt        : Set[BigInt] => Set[String]         = (set: Set[BigInt]) => set.map(_.toString)
  //  private lazy val set2sqlsBigDecimal    : Set[BigDecimal] => Set[String]     = (set: Set[BigDecimal]) => set.map(_.toString)
  //  private lazy val set2sqlsDate          : Set[Date] => Set[String]           = (set: Set[Date]) => set.map(d => "'" + date2str(d) + "'")
  //  private lazy val set2sqlsDuration      : Set[Duration] => Set[String]       = (set: Set[Duration]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsInstant       : Set[Instant] => Set[String]        = (set: Set[Instant]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalDate     : Set[LocalDate] => Set[String]      = (set: Set[LocalDate]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalTime     : Set[LocalTime] => Set[String]      = (set: Set[LocalTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsLocalDateTime : Set[LocalDateTime] => Set[String]  = (set: Set[LocalDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsOffsetTime    : Set[OffsetTime] => Set[String]     = (set: Set[OffsetTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsOffsetDateTime: Set[OffsetDateTime] => Set[String] = (set: Set[OffsetDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsZonedDateTime : Set[ZonedDateTime] => Set[String]  = (set: Set[ZonedDateTime]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsUUID          : Set[UUID] => Set[String]           = (set: Set[UUID]) => set.map(v => s"'$v'")
  //  private lazy val set2sqlsURI           : Set[URI] => Set[String]            = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).map(v => s"'$v'")
  //  private lazy val set2sqlsByte          : Set[Byte] => Set[String]           = (set: Set[Byte]) => set.map(_.toString)
  //  private lazy val set2sqlsShort         : Set[Short] => Set[String]          = (set: Set[Short]) => set.map(_.toString)
  //  private lazy val set2sqlsChar          : Set[Char] => Set[String]           = (set: Set[Char]) => set.map(v => s"'$v'")
  //
  //
  //  protected def sqlNestedArrays2coalescedSet[T](row: Row, paramIndex: Int, j2s: Any => T): Set[T] = {
  //    //    val array = row.getArray(paramIndex)
  //    //    if (row.wasNull()) {
  //    //      Set.empty[T]
  //    //    } else {
  //    //      val outerArrayResultSet = array.getResultSet
  //    //      var set                 = Set.empty[T]
  //    //      while (outerArrayResultSet.next()) {
  //    //        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
  //    //          set += j2s(value)
  //    //        }
  //    //      }
  //    //      set
  //    //    }
  //    ???
  //  }
  //  private lazy val nestedArray2coalescedSetString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String)
  //  private lazy val nestedArray2coalescedSetInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Int)
  //  private lazy val nestedArray2coalescedSetLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Long)
  //  private lazy val nestedArray2coalescedSetFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Float)
  //  private lazy val nestedArray2coalescedSetDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Double)
  //  private lazy val nestedArray2coalescedSetBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Boolean)
  //  private lazy val nestedArray2coalescedSetBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigInt)
  //  private lazy val nestedArray2coalescedSetBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigDecimal)
  //  private lazy val nestedArray2coalescedSetDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Date)
  //  private lazy val nestedArray2coalescedSetDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Duration)
  //  private lazy val nestedArray2coalescedSetInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Instant)
  //  private lazy val nestedArray2coalescedSetLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDate)
  //  private lazy val nestedArray2coalescedSetLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalTime)
  //  private lazy val nestedArray2coalescedSetLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDateTime)
  //  private lazy val nestedArray2coalescedSetOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetTime)
  //  private lazy val nestedArray2coalescedSetOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetDateTime)
  //  private lazy val nestedArray2coalescedSetZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2ZonedDateTime)
  //  private lazy val nestedArray2coalescedSetUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2UUID)
  //  private lazy val nestedArray2coalescedSetURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String).map(v => new URI(v))
  //  private lazy val nestedArray2coalescedSetByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Byte)
  //  private lazy val nestedArray2coalescedSetShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Short)
  //  private lazy val nestedArray2coalescedSetChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Char)
  //
  //  private lazy val nestedArray2setAscString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Int).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Long).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Float).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Double).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Boolean).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigInt).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigDecimal).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Date).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Duration).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Instant).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDate).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2ZonedDateTime).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2UUID).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  //  private lazy val nestedArray2setAscByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Byte).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Short).toList.sorted.take(size).toSet
  //  private lazy val nestedArray2setAscChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Char).toList.sorted.take(size).toSet
  //
  //  private lazy val nestedArray2setDescString        : Int => (Row, Int) => Set[String]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescInt           : Int => (Row, Int) => Set[Int]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Int).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLong          : Int => (Row, Int) => Set[Long]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Long).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescFloat         : Int => (Row, Int) => Set[Float]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Float).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDouble        : Int => (Row, Int) => Set[Double]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Double).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBoolean       : Int => (Row, Int) => Set[Boolean]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Boolean).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBigInt        : Int => (Row, Int) => Set[BigInt]         = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigInt).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescBigDecimal    : Int => (Row, Int) => Set[BigDecimal]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2BigDecimal).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDate          : Int => (Row, Int) => Set[Date]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Date).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescDuration      : Int => (Row, Int) => Set[Duration]       = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Duration).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescInstant       : Int => (Row, Int) => Set[Instant]        = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Instant).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalDate     : Int => (Row, Int) => Set[LocalDate]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDate).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalTime     : Int => (Row, Int) => Set[LocalTime]      = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescLocalDateTime : Int => (Row, Int) => Set[LocalDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2LocalDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescOffsetTime    : Int => (Row, Int) => Set[OffsetTime]     = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescOffsetDateTime: Int => (Row, Int) => Set[OffsetDateTime] = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2OffsetDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescZonedDateTime : Int => (Row, Int) => Set[ZonedDateTime]  = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2ZonedDateTime).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescUUID          : Int => (Row, Int) => Set[UUID]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2UUID).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescURI           : Int => (Row, Int) => Set[URI]            = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  //  private lazy val nestedArray2setDescByte          : Int => (Row, Int) => Set[Byte]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Byte).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescShort         : Int => (Row, Int) => Set[Short]          = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Short).toList.sorted.takeRight(size).toSet
  //  private lazy val nestedArray2setDescChar          : Int => (Row, Int) => Set[Char]           = (size: Int) => (row: Row, paramIndex: Int) => sqlNestedArrays2coalescedSet(row, paramIndex, j2Char).toList.sorted.takeRight(size).toSet
  //
  //  protected def onlyNumbers = throw new Exception("Casting only for numbers.")
  //
  //  private lazy val nestedArray2setSumString        : (Row, Int) => Set[String]         = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumInt           : (Row, Int) => Set[Int]            = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Int](row, paramIndex, j2Int).sum)
  //  private lazy val nestedArray2setSumLong          : (Row, Int) => Set[Long]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Long](row, paramIndex, j2Long).sum)
  //  private lazy val nestedArray2setSumFloat         : (Row, Int) => Set[Float]          = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Float](row, paramIndex, j2Float).sum)
  //  private lazy val nestedArray2setSumDouble        : (Row, Int) => Set[Double]         = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Double](row, paramIndex, j2Double).sum)
  //  private lazy val nestedArray2setSumBoolean       : (Row, Int) => Set[Boolean]        = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumBigInt        : (Row, Int) => Set[BigInt]         = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[BigInt](row, paramIndex, j2BigInt).sum)
  //  private lazy val nestedArray2setSumBigDecimal    : (Row, Int) => Set[BigDecimal]     = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[BigDecimal](row, paramIndex, j2BigDecimal).sum)
  //  private lazy val nestedArray2setSumDate          : (Row, Int) => Set[Date]           = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumDuration      : (Row, Int) => Set[Duration]       = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumInstant       : (Row, Int) => Set[Instant]        = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumLocalDate     : (Row, Int) => Set[LocalDate]      = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumLocalTime     : (Row, Int) => Set[LocalTime]      = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumLocalDateTime : (Row, Int) => Set[LocalDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumOffsetTime    : (Row, Int) => Set[OffsetTime]     = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumUUID          : (Row, Int) => Set[UUID]           = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumURI           : (Row, Int) => Set[URI]            = (row: Row, paramIndex: Int) => onlyNumbers
  //  private lazy val nestedArray2setSumByte          : (Row, Int) => Set[Byte]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Byte](row, paramIndex, j2Byte).sum)
  //  private lazy val nestedArray2setSumShort         : (Row, Int) => Set[Short]          = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Short](row, paramIndex, j2Short).sum)
  //  private lazy val nestedArray2setSumChar          : (Row, Int) => Set[Char]           = (row: Row, paramIndex: Int) => Set(sqlNestedArrays2coalescedSet[Char](row, paramIndex, j2Char).sum)


//  protected lazy val bson2ID            : BsonValue => String         = (bv: BsonValue) => bv.asString.getValue
  protected lazy val bson2ID            : BsonValue => String         = (bv: BsonValue) => bv.asObjectId().getValue.toString
  protected lazy val bson2String        : BsonValue => String         = (bv: BsonValue) => bv.asString.getValue
  protected lazy val bson2Int           : BsonValue => Int            = (bv: BsonValue) => bv.asInt32.getValue
  protected lazy val bson2Long          : BsonValue => Long           = (bv: BsonValue) => bv.asInt64.getValue
  protected lazy val bson2Float         : BsonValue => Float          = (bv: BsonValue) => bv.asDouble.getValue.toFloat
  protected lazy val bson2Double        : BsonValue => Double         = (bv: BsonValue) => bv.asDouble.getValue
  protected lazy val bson2Boolean       : BsonValue => Boolean        = (bv: BsonValue) => bv.asBoolean.getValue
  protected lazy val bson2BigInt        : BsonValue => BigInt         = (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger)
  protected lazy val bson2BigDecimal    : BsonValue => BigDecimal     = (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue)
  protected lazy val bson2Date          : BsonValue => Date           = (bv: BsonValue) => new Date(bv.asDateTime.getValue)
  protected lazy val bson2Duration      : BsonValue => Duration       = (bv: BsonValue) => Duration.parse(bv.asString.getValue)
  protected lazy val bson2Instant       : BsonValue => Instant        = (bv: BsonValue) => Instant.parse(bv.asString.getValue)
  protected lazy val bson2LocalDate     : BsonValue => LocalDate      = (bv: BsonValue) => LocalDate.parse(bv.asString.getValue)
  protected lazy val bson2LocalTime     : BsonValue => LocalTime      = (bv: BsonValue) => LocalTime.parse(bv.asString.getValue)
  protected lazy val bson2LocalDateTime : BsonValue => LocalDateTime  = (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue)
  protected lazy val bson2OffsetTime    : BsonValue => OffsetTime     = (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue)
  protected lazy val bson2OffsetDateTime: BsonValue => OffsetDateTime = (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue)
  protected lazy val bson2ZonedDateTime : BsonValue => ZonedDateTime  = (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue)
  protected lazy val bson2UUID          : BsonValue => UUID           = (bv: BsonValue) => UUID.fromString(bv.asString.getValue)
  protected lazy val bson2URI           : BsonValue => URI            = (bv: BsonValue) => new URI(bv.asString.getValue)
  protected lazy val bson2Byte          : BsonValue => Byte           = (bv: BsonValue) => bv.asInt32.getValue.toByte
  protected lazy val bson2Short         : BsonValue => Short          = (bv: BsonValue) => bv.asInt32.getValue.toShort
  protected lazy val bson2Char          : BsonValue => Char           = (bv: BsonValue) => bv.asString.getValue.charAt(0)


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


  //  private lazy val array2setFirstString        : (Row, Int) => Set[String]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)
  //  private lazy val array2setFirstInt           : (Row, Int) => Set[Int]            = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).min)
  //  private lazy val array2setFirstLong          : (Row, Int) => Set[Long]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).min)
  //  private lazy val array2setFirstFloat         : (Row, Int) => Set[Float]          = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).min)
  //  private lazy val array2setFirstDouble        : (Row, Int) => Set[Double]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).min)
  //  private lazy val array2setFirstBoolean       : (Row, Int) => Set[Boolean]        = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).min)
  //  private lazy val array2setFirstBigInt        : (Row, Int) => Set[BigInt]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).min)
  //  private lazy val array2setFirstBigDecimal    : (Row, Int) => Set[BigDecimal]     = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).min)
  //  private lazy val array2setFirstDate          : (Row, Int) => Set[Date]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).min)
  //  private lazy val array2setFirstDuration      : (Row, Int) => Set[Duration]       = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).min)
  //  private lazy val array2setFirstInstant       : (Row, Int) => Set[Instant]        = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).min)
  //  private lazy val array2setFirstLocalDate     : (Row, Int) => Set[LocalDate]      = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).min)
  //  private lazy val array2setFirstLocalTime     : (Row, Int) => Set[LocalTime]      = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).min)
  //  private lazy val array2setFirstLocalDateTime : (Row, Int) => Set[LocalDateTime]  = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).min)
  //  private lazy val array2setFirstOffsetTime    : (Row, Int) => Set[OffsetTime]     = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).min)
  //  private lazy val array2setFirstOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).min)
  //  private lazy val array2setFirstZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).min)
  //  private lazy val array2setFirstUUID          : (Row, Int) => Set[UUID]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).min)
  //  private lazy val array2setFirstURI           : (Row, Int) => Set[URI]            = ??? //(row: Row, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).min)) // URI saved as String
  //  private lazy val array2setFirstByte          : (Row, Int) => Set[Byte]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).min)
  //  private lazy val array2setFirstShort         : (Row, Int) => Set[Short]          = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).min)
  //  private lazy val array2setFirstChar          : (Row, Int) => Set[Char]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).min)
  //
  //  private lazy val array2setLastString        : (Row, Int) => Set[String]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)
  //  private lazy val array2setLastInt           : (Row, Int) => Set[Int]            = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Int).max)
  //  private lazy val array2setLastLong          : (Row, Int) => Set[Long]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Long).max)
  //  private lazy val array2setLastFloat         : (Row, Int) => Set[Float]          = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Float).max)
  //  private lazy val array2setLastDouble        : (Row, Int) => Set[Double]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Double).max)
  //  private lazy val array2setLastBoolean       : (Row, Int) => Set[Boolean]        = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Boolean).max)
  //  private lazy val array2setLastBigInt        : (Row, Int) => Set[BigInt]         = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigInt).max)
  //  private lazy val array2setLastBigDecimal    : (Row, Int) => Set[BigDecimal]     = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).max)
  //  private lazy val array2setLastDate          : (Row, Int) => Set[Date]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Date).max)
  //  private lazy val array2setLastDuration      : (Row, Int) => Set[Duration]       = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Duration).max)
  //  private lazy val array2setLastInstant       : (Row, Int) => Set[Instant]        = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Instant).max)
  //  private lazy val array2setLastLocalDate     : (Row, Int) => Set[LocalDate]      = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDate).max)
  //  private lazy val array2setLastLocalTime     : (Row, Int) => Set[LocalTime]      = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalTime).max)
  //  private lazy val array2setLastLocalDateTime : (Row, Int) => Set[LocalDateTime]  = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2LocalDateTime).max)
  //  private lazy val array2setLastOffsetTime    : (Row, Int) => Set[OffsetTime]     = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetTime).max)
  //  private lazy val array2setLastOffsetDateTime: (Row, Int) => Set[OffsetDateTime] = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2OffsetDateTime).max)
  //  private lazy val array2setLastZonedDateTime : (Row, Int) => Set[ZonedDateTime]  = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2ZonedDateTime).max)
  //  private lazy val array2setLastUUID          : (Row, Int) => Set[UUID]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2UUID).max)
  //  private lazy val array2setLastURI           : (Row, Int) => Set[URI]            = ??? //(row: Row, paramIndex: Int) => Set(new URI(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2String).max)) // URI saved as String
  //  private lazy val array2setLastByte          : (Row, Int) => Set[Byte]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Byte).max)
  //  private lazy val array2setLastShort         : (Row, Int) => Set[Short]          = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Short).max)
  //  private lazy val array2setLastChar          : (Row, Int) => Set[Char]           = ??? //(row: Row, paramIndex: Int) => Set(row.getArray(paramIndex).getArray.asInstanceOf[Array[_]].map(j2Char).max)


  //  case class ResSetOpt[T](
  //    castOptSet: String => BsonDocument => Option[Set[T]],
  //    //    tpe: String,
  //    //    sql2setOpt: (Row, ParamIndex) => Option[Set[T]],
  //    //    set2sqlArray: Set[T] => String,
  //    //    set2sqls: Set[T] => Set[String],
  //    //    one2sql: T => String,
  //    //    one2json: T => String
  //  )
  //
  //  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt(castOptSetString)
  //  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt(castOptSetInt)
  //  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt(castOptSetLong)
  //  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt(castOptSetFloat)
  //  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt(castOptSetDouble)
  //  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt(castOptSetBoolean)
  //  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt(castOptSetBigInt)
  //  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt(castOptSetBigDecimal)
  //  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt(castOptSetDate)
  //  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt(castOptSetDuration)
  //  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt(castOptSetInstant)
  //  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt(castOptSetLocalDate)
  //  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt(castOptSetLocalTime)
  //  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt(castOptSetLocalDateTime)
  //  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt(castOptSetOffsetTime)
  //  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt(castOptSetOffsetDateTime)
  //  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt(castOptSetZonedDateTime)
  //  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt(castOptSetUUID)
  //  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt(castOptSetURI)
  //  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt(castOptSetByte)
  //  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt(castOptSetShort)
  //  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt(castOptSetChar)


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

  protected lazy val castOptSetID             = (field: String) => (doc: BsonDocument) => castOptSet[String](doc, field, (bv: BsonValue) => bv.asObjectId().getValue.toString)
  protected lazy val castOptSetString         = (field: String) => (doc: BsonDocument) => castOptSet[String](doc, field, (bv: BsonValue) => bv.asString.getValue)
  protected lazy val castOptSetInt            = (field: String) => (doc: BsonDocument) => castOptSet[Int](doc, field, (bv: BsonValue) => bv.asInt32.getValue)
  protected lazy val castOptSetLong           = (field: String) => (doc: BsonDocument) => castOptSet[Long](doc, field, (bv: BsonValue) => bv.asInt64.getValue)
  protected lazy val castOptSetFloat          = (field: String) => (doc: BsonDocument) => castOptSet[Float](doc, field, (bv: BsonValue) => bv.asDouble.getValue.toFloat)
  protected lazy val castOptSetDouble         = (field: String) => (doc: BsonDocument) => castOptSet[Double](doc, field, (bv: BsonValue) => bv.asDouble.getValue)
  protected lazy val castOptSetBoolean        = (field: String) => (doc: BsonDocument) => castOptSet[Boolean](doc, field, (bv: BsonValue) => bv.asBoolean.getValue)
  protected lazy val castOptSetBigInt         = (field: String) => (doc: BsonDocument) => castOptSet[BigInt](doc, field, (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger))
  protected lazy val castOptSetBigDecimal     = (field: String) => (doc: BsonDocument) => castOptSet[BigDecimal](doc, field, (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue))
  protected lazy val castOptSetDate           = (field: String) => (doc: BsonDocument) => castOptSet[Date](doc, field, (bv: BsonValue) => new Date(bv.asDateTime.getValue))
  protected lazy val castOptSetDuration       = (field: String) => (doc: BsonDocument) => castOptSet[Duration](doc, field, (bv: BsonValue) => Duration.parse(bv.asString.getValue))
  protected lazy val castOptSetInstant        = (field: String) => (doc: BsonDocument) => castOptSet[Instant](doc, field, (bv: BsonValue) => Instant.parse(bv.asString.getValue))
  protected lazy val castOptSetLocalDate      = (field: String) => (doc: BsonDocument) => castOptSet[LocalDate](doc, field, (bv: BsonValue) => LocalDate.parse(bv.asString.getValue))
  protected lazy val castOptSetLocalTime      = (field: String) => (doc: BsonDocument) => castOptSet[LocalTime](doc, field, (bv: BsonValue) => LocalTime.parse(bv.asString.getValue))
  protected lazy val castOptSetLocalDateTime  = (field: String) => (doc: BsonDocument) => castOptSet[LocalDateTime](doc, field, (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue))
  protected lazy val castOptSetOffsetTime     = (field: String) => (doc: BsonDocument) => castOptSet[OffsetTime](doc, field, (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue))
  protected lazy val castOptSetOffsetDateTime = (field: String) => (doc: BsonDocument) => castOptSet[OffsetDateTime](doc, field, (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue))
  protected lazy val castOptSetZonedDateTime  = (field: String) => (doc: BsonDocument) => castOptSet[ZonedDateTime](doc, field, (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue))
  protected lazy val castOptSetUUID           = (field: String) => (doc: BsonDocument) => castOptSet[UUID](doc, field, (bv: BsonValue) => UUID.fromString(bv.asString.getValue))
  protected lazy val castOptSetURI            = (field: String) => (doc: BsonDocument) => castOptSet[URI](doc, field, (bv: BsonValue) => new URI(bv.asString.getValue))
  protected lazy val castOptSetByte           = (field: String) => (doc: BsonDocument) => castOptSet[Byte](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toByte)
  protected lazy val castOptSetShort          = (field: String) => (doc: BsonDocument) => castOptSet[Short](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toShort)
  protected lazy val castOptSetChar           = (field: String) => (doc: BsonDocument) => castOptSet[Char](doc, field, (bv: BsonValue) => bv.asString.getValue.charAt(0))


  //  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt("String", sql2setOptString, set2sqlArrayString, set2sqlsString, one2sqlString, one2jsonString)
  //  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt("Int", sql2setOptInt, set2sqlArrayInt, set2sqlsInt, one2sqlInt, one2jsonInt)
  //  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt("Long", sql2setOptLong, set2sqlArrayLong, set2sqlsLong, one2sqlLong, one2jsonLong)
  //  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt("Float", sql2setOptFloat, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, one2jsonFloat)
  //  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt("Double", sql2setOptDouble, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, one2jsonDouble)
  //  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt("Boolean", sql2setOptBoolean, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  //  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt("BigInt", sql2setOptBigInt, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  //  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt("BigDecimal", sql2setOptBigDecimal, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  //  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt("Date", sql2setOptDate, set2sqlArrayDate, set2sqlsDate, one2sqlDate, one2jsonDate)
  //  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt("Duration", sql2setOptDuration, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, one2jsonDuration)
  //  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt("Instant", sql2setOptInstant, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, one2jsonInstant)
  //  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt("LocalDate", sql2setOptLocalDate, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  //  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt("LocalTime", sql2setOptLocalTime, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  //  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt("LocalDateTime", sql2setOptLocalDateTime, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  //  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt("OffsetTime", sql2setOptOffsetTime, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  //  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt("OffsetDateTime", sql2setOptOffsetDateTime, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  //  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt("ZonedDateTime", sql2setOptZonedDateTime, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  //  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt("UUID", sql2setOptUUID, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, one2jsonUUID)
  //  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt("URI", sql2setOptURI, set2sqlArrayURI, set2sqlsURI, one2sqlURI, one2jsonURI)
  //  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt("Byte", sql2setOptByte, set2sqlArrayByte, set2sqlsByte, one2sqlByte, one2jsonByte)
  //  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt("Short", sql2setOptShort, set2sqlArrayShort, set2sqlsShort, one2sqlShort, one2jsonShort)
  //  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt("Char", sql2setOptChar, set2sqlArrayChar, set2sqlsChar, one2sqlChar, one2jsonChar)
  //
  //
  //  protected def sql2setOpt[T](row: Row, paramIndex: Int, getValue: Row => T): Option[Set[T]] = {
  //    //    val array = row.getArray(paramIndex)
  //    //    if (row.wasNull()) {
  //    //      Option.empty[Set[T]]
  //    //    } else {
  //    //      val arrayResultSet = array.getResultSet
  //    //      var set            = Set.empty[T]
  //    //      while (arrayResultSet.next()) {
  //    //        set += getValue(arrayResultSet)
  //    //      }
  //    //      if (set.isEmpty || set == Set(0L)) Option.empty[Set[T]] else Some(set)
  //    //    }
  //    ???
  //  }
  //
  //  private lazy val sql2setOptString        : (Row, Int) => Option[Set[String]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueString)
  //  private lazy val sql2setOptInt           : (Row, Int) => Option[Set[Int]]            = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInt)
  //  private lazy val sql2setOptLong          : (Row, Int) => Option[Set[Long]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLong)
  //  private lazy val sql2setOptFloat         : (Row, Int) => Option[Set[Float]]          = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueFloat)
  //  private lazy val sql2setOptDouble        : (Row, Int) => Option[Set[Double]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDouble)
  //  private lazy val sql2setOptBoolean       : (Row, Int) => Option[Set[Boolean]]        = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBoolean)
  //  private lazy val sql2setOptBigInt        : (Row, Int) => Option[Set[BigInt]]         = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigInt)
  //  private lazy val sql2setOptBigDecimal    : (Row, Int) => Option[Set[BigDecimal]]     = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueBigDecimal)
  //  private lazy val sql2setOptDate          : (Row, Int) => Option[Set[Date]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDate)
  //  private lazy val sql2setOptDuration      : (Row, Int) => Option[Set[Duration]]       = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueDuration)
  //  private lazy val sql2setOptInstant       : (Row, Int) => Option[Set[Instant]]        = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueInstant)
  //  private lazy val sql2setOptLocalDate     : (Row, Int) => Option[Set[LocalDate]]      = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDate)
  //  private lazy val sql2setOptLocalTime     : (Row, Int) => Option[Set[LocalTime]]      = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalTime)
  //  private lazy val sql2setOptLocalDateTime : (Row, Int) => Option[Set[LocalDateTime]]  = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueLocalDateTime)
  //  private lazy val sql2setOptOffsetTime    : (Row, Int) => Option[Set[OffsetTime]]     = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetTime)
  //  private lazy val sql2setOptOffsetDateTime: (Row, Int) => Option[Set[OffsetDateTime]] = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueOffsetDateTime)
  //  private lazy val sql2setOptZonedDateTime : (Row, Int) => Option[Set[ZonedDateTime]]  = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueZonedDateTime)
  //  private lazy val sql2setOptUUID          : (Row, Int) => Option[Set[UUID]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueUUID)
  //  private lazy val sql2setOptURI           : (Row, Int) => Option[Set[URI]]            = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueURI)
  //  private lazy val sql2setOptByte          : (Row, Int) => Option[Set[Byte]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueByte)
  //  private lazy val sql2setOptShort         : (Row, Int) => Option[Set[Short]]          = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueShort)
  //  private lazy val sql2setOptChar          : (Row, Int) => Option[Set[Char]]           = (row: Row, paramIndex: Int) => sql2setOpt(row, paramIndex, valueChar)
}
