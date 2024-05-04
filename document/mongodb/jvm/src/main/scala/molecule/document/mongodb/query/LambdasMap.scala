package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.util.JavaConversions
import org.bson._

trait LambdasMap extends LambdasBase with JavaConversions {

  case class ResMap[T](
    tpe: String,

    castMap: String => BsonDocument => Map[String, T],
    castValue: (String, String) => BsonDocument => T,
    v2bson: T => BsonValue,
    //    v2set: String => BsonDocument => Set[T],
  )

  lazy val resMapID            : ResMap[String]         = ResMap("String", mapID, valueID, v2bsonID) //, setSetID, v2setID)
  lazy val resMapString        : ResMap[String]         = ResMap("String", mapString, valueString, v2bsonString) //, setSetString, v2setString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", mapInt, valueInt, v2bsonInt) //, setSetInt, v2setInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", mapLong, valueLong, v2bsonLong) //, setSetLong, v2setLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", mapFloat, valueFloat, v2bsonFloat) //, setSetFloat, v2setFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", mapDouble, valueDouble, v2bsonDouble) //, setSetDouble, v2setDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", mapBoolean, valueBoolean, v2bsonBoolean) //, setSetBoolean, v2setBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", mapBigInt, valueBigInt, v2bsonBigInt) //, setSetBigInt, v2setBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", mapBigDecimal, valueBigDecimal, v2bsonBigDecimal) //, setSetBigDecimal, v2setBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", mapDate, valueDate, v2bsonDate) //, setSetDate, v2setDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", mapDuration, valueDuration, v2bsonDuration) //, setSetDuration, v2setDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", mapInstant, valueInstant, v2bsonInstant) //, setSetInstant, v2setInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", mapLocalDate, valueLocalDate, v2bsonLocalDate) //, setSetLocalDate, v2setLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", mapLocalTime, valueLocalTime, v2bsonLocalTime) //, setSetLocalTime, v2setLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", mapLocalDateTime, valueLocalDateTime, v2bsonLocalDateTime) //, setSetLocalDateTime, v2setLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", mapOffsetTime, valueOffsetTime, v2bsonOffsetTime) //, setSetOffsetTime, v2setOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", mapOffsetDateTime, valueOffsetDateTime, v2bsonOffsetDateTime) //, setSetOffsetDateTime, v2setOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", mapZonedDateTime, valueZonedDateTime, v2bsonZonedDateTime) //, setSetZonedDateTime, v2setZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", mapUUID, valueUUID, v2bsonUUID) //, setSetUUID, v2setUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", mapURI, valueURI, v2bsonURI) //, setSetURI, v2setURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", mapByte, valueByte, v2bsonByte) //, setSetByte, v2setByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", mapShort, valueShort, v2bsonShort) //, setSetShort, v2setShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", mapChar, valueChar, v2bsonChar) //, setSetChar, v2setChar)


  protected def castMap[T](doc: BsonDocument, field: String, value: BsonValue => T): Map[String, T] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Map.empty[String, T]
    } else {
      val mapDoc = raw.asDocument()
      var map    = Map.empty[String, T]
      mapDoc.forEach {
        case (k, v) => map = map + (k -> value(v))
      }
      map
    }
  }

  private lazy val mapID             = (field: String) => (doc: BsonDocument) => castMap[String](doc, field, bson2ID)
  private lazy val mapString         = (field: String) => (doc: BsonDocument) => castMap[String](doc, field, bson2String)
  private lazy val mapInt            = (field: String) => (doc: BsonDocument) => castMap[Int](doc, field, bson2Int)
  private lazy val mapLong           = (field: String) => (doc: BsonDocument) => castMap[Long](doc, field, bson2Long)
  private lazy val mapFloat          = (field: String) => (doc: BsonDocument) => castMap[Float](doc, field, bson2Float)
  private lazy val mapDouble         = (field: String) => (doc: BsonDocument) => castMap[Double](doc, field, bson2Double)
  private lazy val mapBoolean        = (field: String) => (doc: BsonDocument) => castMap[Boolean](doc, field, bson2Boolean)
  private lazy val mapBigInt         = (field: String) => (doc: BsonDocument) => castMap[BigInt](doc, field, bson2BigInt)
  private lazy val mapBigDecimal     = (field: String) => (doc: BsonDocument) => castMap[BigDecimal](doc, field, bson2BigDecimal)
  private lazy val mapDate           = (field: String) => (doc: BsonDocument) => castMap[Date](doc, field, bson2Date)
  private lazy val mapDuration       = (field: String) => (doc: BsonDocument) => castMap[Duration](doc, field, bson2Duration)
  private lazy val mapInstant        = (field: String) => (doc: BsonDocument) => castMap[Instant](doc, field, bson2Instant)
  private lazy val mapLocalDate      = (field: String) => (doc: BsonDocument) => castMap[LocalDate](doc, field, bson2LocalDate)
  private lazy val mapLocalTime      = (field: String) => (doc: BsonDocument) => castMap[LocalTime](doc, field, bson2LocalTime)
  private lazy val mapLocalDateTime  = (field: String) => (doc: BsonDocument) => castMap[LocalDateTime](doc, field, bson2LocalDateTime)
  private lazy val mapOffsetTime     = (field: String) => (doc: BsonDocument) => castMap[OffsetTime](doc, field, bson2OffsetTime)
  private lazy val mapOffsetDateTime = (field: String) => (doc: BsonDocument) => castMap[OffsetDateTime](doc, field, bson2OffsetDateTime)
  private lazy val mapZonedDateTime  = (field: String) => (doc: BsonDocument) => castMap[ZonedDateTime](doc, field, bson2ZonedDateTime)
  private lazy val mapUUID           = (field: String) => (doc: BsonDocument) => castMap[UUID](doc, field, bson2UUID)
  private lazy val mapURI            = (field: String) => (doc: BsonDocument) => castMap[URI](doc, field, bson2URI)
  private lazy val mapByte           = (field: String) => (doc: BsonDocument) => castMap[Byte](doc, field, bson2Byte)
  private lazy val mapShort          = (field: String) => (doc: BsonDocument) => castMap[Short](doc, field, bson2Short)
  private lazy val mapChar           = (field: String) => (doc: BsonDocument) => castMap[Char](doc, field, bson2Char)


  protected lazy val valueID             = (field: String, key: String) => (doc: BsonDocument) => {
    doc.getDocument(field).get(key) match {
      case v: BsonObjectId => v.getValue.toString
      case v: BsonString   => v.getValue
      case v               => throw ModelError("Unexpected Bson value for id: " + v)
    }
  }
  protected lazy val valueString         = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asString.getValue
  protected lazy val valueInt            = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asInt32.getValue
  protected lazy val valueLong           = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asInt64.getValue
  protected lazy val valueFloat          = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asDouble.getValue.toFloat
  protected lazy val valueDouble         = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asDouble.getValue
  protected lazy val valueBoolean        = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asBoolean.getValue
  protected lazy val valueBigInt         = (field: String, key: String) => (doc: BsonDocument) => BigInt(doc.getDocument(field).get(key).asDecimal128.getValue.bigDecimalValue.toBigInteger)
  protected lazy val valueBigDecimal     = (field: String, key: String) => (doc: BsonDocument) => BigDecimal(doc.getDocument(field).get(key).asDecimal128.getValue.bigDecimalValue)
  protected lazy val valueDate           = (field: String, key: String) => (doc: BsonDocument) => new Date(doc.getDocument(field).get(key).asDateTime.getValue)
  protected lazy val valueDuration       = (field: String, key: String) => (doc: BsonDocument) => Duration.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueInstant        = (field: String, key: String) => (doc: BsonDocument) => Instant.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueLocalDate      = (field: String, key: String) => (doc: BsonDocument) => LocalDate.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueLocalTime      = (field: String, key: String) => (doc: BsonDocument) => LocalTime.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueLocalDateTime  = (field: String, key: String) => (doc: BsonDocument) => LocalDateTime.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueOffsetTime     = (field: String, key: String) => (doc: BsonDocument) => OffsetTime.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueOffsetDateTime = (field: String, key: String) => (doc: BsonDocument) => OffsetDateTime.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueZonedDateTime  = (field: String, key: String) => (doc: BsonDocument) => ZonedDateTime.parse(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueUUID           = (field: String, key: String) => (doc: BsonDocument) => UUID.fromString(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueURI            = (field: String, key: String) => (doc: BsonDocument) => new URI(doc.getDocument(field).get(key).asString.getValue)
  protected lazy val valueByte           = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asInt32.getValue.toByte
  protected lazy val valueShort          = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asInt32.getValue.toShort
  protected lazy val valueChar           = (field: String, key: String) => (doc: BsonDocument) => doc.getDocument(field).get(key).asString.getValue.charAt(0)

  //
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
  //  private lazy val bson2ID            : BsonValue => String         = (bv: BsonValue) => {
  //    if (bv.isObjectId) bv.asObjectId().getValue.toString else bv.asString.getValue
  //  }
  //  //  private lazy val bson2ID            : BsonValue => String         = (bv: BsonValue) => bv.asObjectId().getValue.toString
  //  private lazy val bson2String        : BsonValue => String         = (bv: BsonValue) => bv.asString.getValue
  //  protected lazy val bson2Int           : BsonValue => Int            = (bv: BsonValue) => bv.asInt32.getValue
  //  private lazy val bson2Long          : BsonValue => Long           = (bv: BsonValue) => bv.asInt64.getValue
  //  private lazy val bson2Float         : BsonValue => Float          = (bv: BsonValue) => bv.asDouble.getValue.toFloat
  //  private lazy val bson2Double        : BsonValue => Double         = (bv: BsonValue) => bv.asDouble.getValue
  //  private lazy val bson2Boolean       : BsonValue => Boolean        = (bv: BsonValue) => bv.asBoolean.getValue
  //  private lazy val bson2BigInt        : BsonValue => BigInt         = (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger)
  //  private lazy val bson2BigDecimal    : BsonValue => BigDecimal     = (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue)
  //  private lazy val bson2Date          : BsonValue => Date           = (bv: BsonValue) => new Date(bv.asDateTime.getValue)
  //  private lazy val bson2Duration      : BsonValue => Duration       = (bv: BsonValue) => Duration.parse(bv.asString.getValue)
  //  private lazy val bson2Instant       : BsonValue => Instant        = (bv: BsonValue) => Instant.parse(bv.asString.getValue)
  //  private lazy val bson2LocalDate     : BsonValue => LocalDate      = (bv: BsonValue) => LocalDate.parse(bv.asString.getValue)
  //  private lazy val bson2LocalTime     : BsonValue => LocalTime      = (bv: BsonValue) => LocalTime.parse(bv.asString.getValue)
  //  private lazy val bson2LocalDateTime : BsonValue => LocalDateTime  = (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue)
  //  private lazy val bson2OffsetTime    : BsonValue => OffsetTime     = (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue)
  //  private lazy val bson2OffsetDateTime: BsonValue => OffsetDateTime = (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue)
  //  private lazy val bson2ZonedDateTime : BsonValue => ZonedDateTime  = (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue)
  //  private lazy val bson2UUID          : BsonValue => UUID           = (bv: BsonValue) => UUID.fromString(bv.asString.getValue)
  //  private lazy val bson2URI           : BsonValue => URI            = (bv: BsonValue) => new URI(bv.asString.getValue)
  //  private lazy val bson2Byte          : BsonValue => Byte           = (bv: BsonValue) => bv.asInt32.getValue.toByte
  //  private lazy val bson2Short         : BsonValue => Short          = (bv: BsonValue) => bv.asInt32.getValue.toShort
  //  private lazy val bson2Char          : BsonValue => Char           = (bv: BsonValue) => bv.asString.getValue.charAt(0)
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


  case class ResMapOpt[T](
    tpe: String,
    castOptMap: String => BsonDocument => Option[Map[String, T]],
    castOptValue: (String, String) => BsonDocument => Option[T],
    //    set2sqlArray: Set[T] => String,
    //    set2sqls: Set[T] => Set[String],
    //    one2sql: T => String,
    //    one2json: T => String
  )

  lazy val resOptMapID            : ResMapOpt[String]         = ResMapOpt("String", castOptMapID, valueOptMapID) //, sql2optMapId) //, set2sqlArrayId, set2sqlsId, one2sqlId, one2jsonId)
  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", castOptMapString, valueOptMapString) //, sql2optMapString) //, set2sqlArrayString, set2sqlsString, one2sqlString, one2jsonString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", castOptMapInt, valueOptMapInt) //, sql2optMapInt) //, set2sqlArrayInt, set2sqlsInt, one2sqlInt, one2jsonInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", castOptMapLong, valueOptMapLong) //, sql2optMapLong) //, set2sqlArrayLong, set2sqlsLong, one2sqlLong, one2jsonLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", castOptMapFloat, valueOptMapFloat) //, sql2optMapFloat) //, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", castOptMapDouble, valueOptMapDouble) //, sql2optMapDouble) //, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", castOptMapBoolean, valueOptMapBoolean) //, sql2optMapBoolean) //, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", castOptMapBigInt, valueOptMapBigInt) //, sql2optMapBigInt) //, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", castOptMapBigDecimal, valueOptMapBigDecimal) //, sql2optMapBigDecimal) //, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", castOptMapDate, valueOptMapDate) //, sql2optMapDate) //, set2sqlArrayDate, set2sqlsDate, one2sqlDate, one2jsonDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", castOptMapDuration, valueOptMapDuration) //, sql2optMapDuration) //, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", castOptMapInstant, valueOptMapInstant) //, sql2optMapInstant) //, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", castOptMapLocalDate, valueOptMapLocalDate) //, sql2optMapLocalDate) //, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", castOptMapLocalTime, valueOptMapLocalTime) //, sql2optMapLocalTime) //, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", castOptMapLocalDateTime, valueOptMapLocalDateTime) //, sql2optMapLocalDateTime) //, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", castOptMapOffsetTime, valueOptMapOffsetTime) //, sql2optMapOffsetTime) //, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", castOptMapOffsetDateTime, valueOptMapOffsetDateTime) //, sql2optMapOffsetDateTime) //, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", castOptMapZonedDateTime, valueOptMapZonedDateTime) //, sql2optMapZonedDateTime) //, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", castOptMapUUID, valueOptMapUUID) //, sql2optMapUUID) //, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", castOptMapURI, valueOptMapURI) //, sql2optMapURI) //, set2sqlArrayURI, set2sqlsURI, one2sqlURI, one2jsonURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", castOptMapByte, valueOptMapByte) //, sql2optMapByte) //, set2sqlArrayByte, set2sqlsByte, one2sqlByte, one2jsonByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", castOptMapShort, valueOptMapShort) //, sql2optMapShort) //, set2sqlArrayShort, set2sqlsShort, one2sqlShort, one2jsonShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", castOptMapChar, valueOptMapChar) //, sql2optMapChar) //, set2sqlArrayChar, set2sqlsChar, one2sqlChar, one2jsonChar)


  protected def castOptMap[T](doc: BsonDocument, field: String, value: BsonValue => T): Option[Map[String, T]] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Option.empty[Map[String, T]]
    } else {
      val mapDoc = raw.asDocument()
      var map    = Map.empty[String, T]
      mapDoc.forEach {
        case (k, v) => map = map + (k -> value(v))
      }
      if (map.isEmpty) None else Some(map)
    }
  }

  private lazy val castOptMapID             = (field: String) => (doc: BsonDocument) => castOptMap[String](doc, field, bson2ID)
  private lazy val castOptMapString         = (field: String) => (doc: BsonDocument) => castOptMap[String](doc, field, bson2String)
  private lazy val castOptMapInt            = (field: String) => (doc: BsonDocument) => castOptMap[Int](doc, field, bson2Int)
  private lazy val castOptMapLong           = (field: String) => (doc: BsonDocument) => castOptMap[Long](doc, field, bson2Long)
  private lazy val castOptMapFloat          = (field: String) => (doc: BsonDocument) => castOptMap[Float](doc, field, bson2Float)
  private lazy val castOptMapDouble         = (field: String) => (doc: BsonDocument) => castOptMap[Double](doc, field, bson2Double)
  private lazy val castOptMapBoolean        = (field: String) => (doc: BsonDocument) => castOptMap[Boolean](doc, field, bson2Boolean)
  private lazy val castOptMapBigInt         = (field: String) => (doc: BsonDocument) => castOptMap[BigInt](doc, field, bson2BigInt)
  private lazy val castOptMapBigDecimal     = (field: String) => (doc: BsonDocument) => castOptMap[BigDecimal](doc, field, bson2BigDecimal)
  private lazy val castOptMapDate           = (field: String) => (doc: BsonDocument) => castOptMap[Date](doc, field, bson2Date)
  private lazy val castOptMapDuration       = (field: String) => (doc: BsonDocument) => castOptMap[Duration](doc, field, bson2Duration)
  private lazy val castOptMapInstant        = (field: String) => (doc: BsonDocument) => castOptMap[Instant](doc, field, bson2Instant)
  private lazy val castOptMapLocalDate      = (field: String) => (doc: BsonDocument) => castOptMap[LocalDate](doc, field, bson2LocalDate)
  private lazy val castOptMapLocalTime      = (field: String) => (doc: BsonDocument) => castOptMap[LocalTime](doc, field, bson2LocalTime)
  private lazy val castOptMapLocalDateTime  = (field: String) => (doc: BsonDocument) => castOptMap[LocalDateTime](doc, field, bson2LocalDateTime)
  private lazy val castOptMapOffsetTime     = (field: String) => (doc: BsonDocument) => castOptMap[OffsetTime](doc, field, bson2OffsetTime)
  private lazy val castOptMapOffsetDateTime = (field: String) => (doc: BsonDocument) => castOptMap[OffsetDateTime](doc, field, bson2OffsetDateTime)
  private lazy val castOptMapZonedDateTime  = (field: String) => (doc: BsonDocument) => castOptMap[ZonedDateTime](doc, field, bson2ZonedDateTime)
  private lazy val castOptMapUUID           = (field: String) => (doc: BsonDocument) => castOptMap[UUID](doc, field, bson2UUID)
  private lazy val castOptMapURI            = (field: String) => (doc: BsonDocument) => castOptMap[URI](doc, field, bson2URI)
  private lazy val castOptMapByte           = (field: String) => (doc: BsonDocument) => castOptMap[Byte](doc, field, bson2Byte)
  private lazy val castOptMapShort          = (field: String) => (doc: BsonDocument) => castOptMap[Short](doc, field, bson2Short)
  private lazy val castOptMapChar           = (field: String) => (doc: BsonDocument) => castOptMap[Char](doc, field, bson2Char)


  protected def castOptValue[T](doc: BsonDocument, field: String, key: String, value: BsonValue => T): Option[T] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Option.empty[T]
    } else {
      val mapDoc    = raw.asDocument()
      val bsonValue = mapDoc.get(key)
      if (bsonValue == null || bsonValue == BsonNull.VALUE) {
        Option.empty[T]
      } else {
        Some(value(bsonValue))
      }
    }
  }

  private lazy val valueOptMapID             = (field: String, key: String) => (doc: BsonDocument) => castOptValue[String](doc, field, key, bson2ID)
  private lazy val valueOptMapString         = (field: String, key: String) => (doc: BsonDocument) => castOptValue[String](doc, field, key, bson2String)
  private lazy val valueOptMapInt            = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Int](doc, field, key, bson2Int)
  private lazy val valueOptMapLong           = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Long](doc, field, key, bson2Long)
  private lazy val valueOptMapFloat          = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Float](doc, field, key, bson2Float)
  private lazy val valueOptMapDouble         = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Double](doc, field, key, bson2Double)
  private lazy val valueOptMapBoolean        = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Boolean](doc, field, key, bson2Boolean)
  private lazy val valueOptMapBigInt         = (field: String, key: String) => (doc: BsonDocument) => castOptValue[BigInt](doc, field, key, bson2BigInt)
  private lazy val valueOptMapBigDecimal     = (field: String, key: String) => (doc: BsonDocument) => castOptValue[BigDecimal](doc, field, key, bson2BigDecimal)
  private lazy val valueOptMapDate           = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Date](doc, field, key, bson2Date)
  private lazy val valueOptMapDuration       = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Duration](doc, field, key, bson2Duration)
  private lazy val valueOptMapInstant        = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Instant](doc, field, key, bson2Instant)
  private lazy val valueOptMapLocalDate      = (field: String, key: String) => (doc: BsonDocument) => castOptValue[LocalDate](doc, field, key, bson2LocalDate)
  private lazy val valueOptMapLocalTime      = (field: String, key: String) => (doc: BsonDocument) => castOptValue[LocalTime](doc, field, key, bson2LocalTime)
  private lazy val valueOptMapLocalDateTime  = (field: String, key: String) => (doc: BsonDocument) => castOptValue[LocalDateTime](doc, field, key, bson2LocalDateTime)
  private lazy val valueOptMapOffsetTime     = (field: String, key: String) => (doc: BsonDocument) => castOptValue[OffsetTime](doc, field, key, bson2OffsetTime)
  private lazy val valueOptMapOffsetDateTime = (field: String, key: String) => (doc: BsonDocument) => castOptValue[OffsetDateTime](doc, field, key, bson2OffsetDateTime)
  private lazy val valueOptMapZonedDateTime  = (field: String, key: String) => (doc: BsonDocument) => castOptValue[ZonedDateTime](doc, field, key, bson2ZonedDateTime)
  private lazy val valueOptMapUUID           = (field: String, key: String) => (doc: BsonDocument) => castOptValue[UUID](doc, field, key, bson2UUID)
  private lazy val valueOptMapURI            = (field: String, key: String) => (doc: BsonDocument) => castOptValue[URI](doc, field, key, bson2URI)
  private lazy val valueOptMapByte           = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Byte](doc, field, key, bson2Byte)
  private lazy val valueOptMapShort          = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Short](doc, field, key, bson2Short)
  private lazy val valueOptMapChar           = (field: String, key: String) => (doc: BsonDocument) => castOptValue[Char](doc, field, key, bson2Char)

  //  protected def sql2mapOpt[T](row: RS, paramIndex: Int, json2map: String => Map[String, T]): Option[Map[String, T]] = {
  //    val byteArray = row.getBytes(paramIndex)
  //    if (!row.wasNull() && byteArray.nonEmpty)
  //      Some(json2map(new String(byteArray)))
  //    else
  //      None
  //  }
  //
  //  private lazy val sql2optMapId            : (RS, Int) => Option[Map[String, String]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapID)
  //  private lazy val sql2optMapString        : (RS, Int) => Option[Map[String, String]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapString)
  //  private lazy val sql2optMapInt           : (RS, Int) => Option[Map[String, Int]]            = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapInt)
  //  private lazy val sql2optMapLong          : (RS, Int) => Option[Map[String, Long]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLong)
  //  private lazy val sql2optMapFloat         : (RS, Int) => Option[Map[String, Float]]          = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapFloat)
  //  private lazy val sql2optMapDouble        : (RS, Int) => Option[Map[String, Double]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDouble)
  //  private lazy val sql2optMapBoolean       : (RS, Int) => Option[Map[String, Boolean]]        = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBoolean)
  //  private lazy val sql2optMapBigInt        : (RS, Int) => Option[Map[String, BigInt]]         = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBigInt)
  //  private lazy val sql2optMapBigDecimal    : (RS, Int) => Option[Map[String, BigDecimal]]     = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapBigDecimal)
  //  private lazy val sql2optMapDate          : (RS, Int) => Option[Map[String, Date]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDate)
  //  private lazy val sql2optMapDuration      : (RS, Int) => Option[Map[String, Duration]]       = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapDuration)
  //  private lazy val sql2optMapInstant       : (RS, Int) => Option[Map[String, Instant]]        = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapInstant)
  //  private lazy val sql2optMapLocalDate     : (RS, Int) => Option[Map[String, LocalDate]]      = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalDate)
  //  private lazy val sql2optMapLocalTime     : (RS, Int) => Option[Map[String, LocalTime]]      = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalTime)
  //  private lazy val sql2optMapLocalDateTime : (RS, Int) => Option[Map[String, LocalDateTime]]  = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapLocalDateTime)
  //  private lazy val sql2optMapOffsetTime    : (RS, Int) => Option[Map[String, OffsetTime]]     = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapOffsetTime)
  //  private lazy val sql2optMapOffsetDateTime: (RS, Int) => Option[Map[String, OffsetDateTime]] = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapOffsetDateTime)
  //  private lazy val sql2optMapZonedDateTime : (RS, Int) => Option[Map[String, ZonedDateTime]]  = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapZonedDateTime)
  //  private lazy val sql2optMapUUID          : (RS, Int) => Option[Map[String, UUID]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapUUID)
  //  private lazy val sql2optMapURI           : (RS, Int) => Option[Map[String, URI]]            = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapURI)
  //  private lazy val sql2optMapByte          : (RS, Int) => Option[Map[String, Byte]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapByte)
  //  private lazy val sql2optMapShort         : (RS, Int) => Option[Map[String, Short]]          = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapShort)
  //  private lazy val sql2optMapChar          : (RS, Int) => Option[Map[String, Char]]           = (row: RS, paramIndex: Int) => sql2mapOpt(row, paramIndex, json2mapChar)

}
