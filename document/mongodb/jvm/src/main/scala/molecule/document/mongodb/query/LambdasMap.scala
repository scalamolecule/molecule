package molecule.document.mongodb.query

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import org.bson._

trait LambdasMap extends LambdasBase with JavaConversions {

  case class ResMap[T](
    tpe: String,

    castMap: String => BsonDocument => Map[String, T],
//    v2bson: T => BsonValue,
//    castSetSet: String => BsonDocument => Set[Set[T]],
//    v2set: String => BsonDocument => Set[T],
  )

  lazy val resMapID            : ResMap[String]         = ResMap("String", castMapID) //, v2bsonID, setSetID, v2setID)
  lazy val resMapString        : ResMap[String]         = ResMap("String", castMapString) //, v2bsonString, setSetString, v2setString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", castMapInt) //, v2bsonInt, setSetInt, v2setInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", castMapLong) //, v2bsonLong, setSetLong, v2setLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", castMapFloat) //, v2bsonFloat, setSetFloat, v2setFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", castMapDouble) //, v2bsonDouble, setSetDouble, v2setDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", castMapBoolean) //, v2bsonBoolean, setSetBoolean, v2setBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", castMapBigInt) //, v2bsonBigInt, setSetBigInt, v2setBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", castMapBigDecimal) //, v2bsonBigDecimal, setSetBigDecimal, v2setBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", castMapDate) //, v2bsonDate, setSetDate, v2setDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", castMapDuration) //, v2bsonDuration, setSetDuration, v2setDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", castMapInstant) //, v2bsonInstant, setSetInstant, v2setInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", castMapLocalDate) //, v2bsonLocalDate, setSetLocalDate, v2setLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", castMapLocalTime) //, v2bsonLocalTime, setSetLocalTime, v2setLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", castMapLocalDateTime) //, v2bsonLocalDateTime, setSetLocalDateTime, v2setLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", castMapOffsetTime) //, v2bsonOffsetTime, setSetOffsetTime, v2setOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", castMapOffsetDateTime) //, v2bsonOffsetDateTime, setSetOffsetDateTime, v2setOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", castMapZonedDateTime) //, v2bsonZonedDateTime, setSetZonedDateTime, v2setZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", castMapUUID) //, v2bsonUUID, setSetUUID, v2setUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", castMapURI) //, v2bsonURI, setSetURI, v2setURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", castMapByte) //, v2bsonByte, setSetByte, v2setByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", castMapShort) //, v2bsonShort, setSetShort, v2setShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", castMapChar) //, v2bsonChar, setSetChar, v2setChar)


  protected def castMap[T](doc: BsonDocument, field: String, value: BsonValue => T): Map[String, T] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Map.empty[String, T]
    } else {
      val mapDoc = raw.asDocument()
      var map = Map.empty[String, T]
      mapDoc.forEach{
        case (k, v) => map = map + (k -> value(v))
      }
      map
    }
  }

  protected lazy val castMapID             = (field: String) => (doc: BsonDocument) => castMap[String](doc, field, bson2ID)
  protected lazy val castMapString         = (field: String) => (doc: BsonDocument) => castMap[String](doc, field, bson2String)
  protected lazy val castMapInt            = (field: String) => (doc: BsonDocument) => castMap[Int](doc, field, bson2Int)
  protected lazy val castMapLong           = (field: String) => (doc: BsonDocument) => castMap[Long](doc, field, bson2Long)
  protected lazy val castMapFloat          = (field: String) => (doc: BsonDocument) => castMap[Float](doc, field, bson2Float)
  protected lazy val castMapDouble         = (field: String) => (doc: BsonDocument) => castMap[Double](doc, field, bson2Double)
  protected lazy val castMapBoolean        = (field: String) => (doc: BsonDocument) => castMap[Boolean](doc, field, bson2Boolean)
  protected lazy val castMapBigInt         = (field: String) => (doc: BsonDocument) => castMap[BigInt](doc, field, bson2BigInt)
  protected lazy val castMapBigDecimal     = (field: String) => (doc: BsonDocument) => castMap[BigDecimal](doc, field, bson2BigDecimal)
  protected lazy val castMapDate           = (field: String) => (doc: BsonDocument) => castMap[Date](doc, field, bson2Date)
  protected lazy val castMapDuration       = (field: String) => (doc: BsonDocument) => castMap[Duration](doc, field, bson2Duration)
  protected lazy val castMapInstant        = (field: String) => (doc: BsonDocument) => castMap[Instant](doc, field, bson2Instant)
  protected lazy val castMapLocalDate      = (field: String) => (doc: BsonDocument) => castMap[LocalDate](doc, field, bson2LocalDate)
  protected lazy val castMapLocalTime      = (field: String) => (doc: BsonDocument) => castMap[LocalTime](doc, field, bson2LocalTime)
  protected lazy val castMapLocalDateTime  = (field: String) => (doc: BsonDocument) => castMap[LocalDateTime](doc, field, bson2LocalDateTime)
  protected lazy val castMapOffsetTime     = (field: String) => (doc: BsonDocument) => castMap[OffsetTime](doc, field, bson2OffsetTime)
  protected lazy val castMapOffsetDateTime = (field: String) => (doc: BsonDocument) => castMap[OffsetDateTime](doc, field, bson2OffsetDateTime)
  protected lazy val castMapZonedDateTime  = (field: String) => (doc: BsonDocument) => castMap[ZonedDateTime](doc, field, bson2ZonedDateTime)
  protected lazy val castMapUUID           = (field: String) => (doc: BsonDocument) => castMap[UUID](doc, field, bson2UUID)
  protected lazy val castMapURI            = (field: String) => (doc: BsonDocument) => castMap[URI](doc, field, bson2URI)
  protected lazy val castMapByte           = (field: String) => (doc: BsonDocument) => castMap[Byte](doc, field, bson2Byte)
  protected lazy val castMapShort          = (field: String) => (doc: BsonDocument) => castMap[Short](doc, field, bson2Short)
  protected lazy val castMapChar           = (field: String) => (doc: BsonDocument) => castMap[Char](doc, field, bson2Char)

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
  protected def castOptMap[T](doc: BsonDocument, field: String, value: BsonValue => T): Option[Map[String, T]] = {
    val raw = doc.get(field)
    if (raw == null || raw == BsonNull.VALUE) {
      Option.empty[Map[String, T]]
    } else {
      val mapDoc = raw.asDocument()
      var map = Map.empty[String, T]
      mapDoc.forEach{
        case (k, v) => map = map + (k -> value(v))
      }
      if (map.isEmpty) None else Some(map)
    }
  }

  private lazy val castOptMapID             = (field: String) => (doc: BsonDocument) => castOptMap[String](doc, field, (bv: BsonValue) => bv.asObjectId().getValue.toString)
  private lazy val castOptMapString         = (field: String) => (doc: BsonDocument) => castOptMap[String](doc, field, (bv: BsonValue) => bv.asString.getValue)
  private lazy val castOptMapInt            = (field: String) => (doc: BsonDocument) => castOptMap[Int](doc, field, (bv: BsonValue) => bv.asInt32.getValue)
  private lazy val castOptMapLong           = (field: String) => (doc: BsonDocument) => castOptMap[Long](doc, field, (bv: BsonValue) => bv.asInt64.getValue)
  private lazy val castOptMapFloat          = (field: String) => (doc: BsonDocument) => castOptMap[Float](doc, field, (bv: BsonValue) => bv.asDouble.getValue.toFloat)
  private lazy val castOptMapDouble         = (field: String) => (doc: BsonDocument) => castOptMap[Double](doc, field, (bv: BsonValue) => bv.asDouble.getValue)
  private lazy val castOptMapBoolean        = (field: String) => (doc: BsonDocument) => castOptMap[Boolean](doc, field, (bv: BsonValue) => bv.asBoolean.getValue)
  private lazy val castOptMapBigInt         = (field: String) => (doc: BsonDocument) => castOptMap[BigInt](doc, field, (bv: BsonValue) => BigInt(bv.asDecimal128.getValue.bigDecimalValue.toBigInteger))
  private lazy val castOptMapBigDecimal     = (field: String) => (doc: BsonDocument) => castOptMap[BigDecimal](doc, field, (bv: BsonValue) => BigDecimal(bv.asDecimal128.getValue.bigDecimalValue))
  private lazy val castOptMapDate           = (field: String) => (doc: BsonDocument) => castOptMap[Date](doc, field, (bv: BsonValue) => new Date(bv.asDateTime.getValue))
  private lazy val castOptMapDuration       = (field: String) => (doc: BsonDocument) => castOptMap[Duration](doc, field, (bv: BsonValue) => Duration.parse(bv.asString.getValue))
  private lazy val castOptMapInstant        = (field: String) => (doc: BsonDocument) => castOptMap[Instant](doc, field, (bv: BsonValue) => Instant.parse(bv.asString.getValue))
  private lazy val castOptMapLocalDate      = (field: String) => (doc: BsonDocument) => castOptMap[LocalDate](doc, field, (bv: BsonValue) => LocalDate.parse(bv.asString.getValue))
  private lazy val castOptMapLocalTime      = (field: String) => (doc: BsonDocument) => castOptMap[LocalTime](doc, field, (bv: BsonValue) => LocalTime.parse(bv.asString.getValue))
  private lazy val castOptMapLocalDateTime  = (field: String) => (doc: BsonDocument) => castOptMap[LocalDateTime](doc, field, (bv: BsonValue) => LocalDateTime.parse(bv.asString.getValue))
  private lazy val castOptMapOffsetTime     = (field: String) => (doc: BsonDocument) => castOptMap[OffsetTime](doc, field, (bv: BsonValue) => OffsetTime.parse(bv.asString.getValue))
  private lazy val castOptMapOffsetDateTime = (field: String) => (doc: BsonDocument) => castOptMap[OffsetDateTime](doc, field, (bv: BsonValue) => OffsetDateTime.parse(bv.asString.getValue))
  private lazy val castOptMapZonedDateTime  = (field: String) => (doc: BsonDocument) => castOptMap[ZonedDateTime](doc, field, (bv: BsonValue) => ZonedDateTime.parse(bv.asString.getValue))
  private lazy val castOptMapUUID           = (field: String) => (doc: BsonDocument) => castOptMap[UUID](doc, field, (bv: BsonValue) => UUID.fromString(bv.asString.getValue))
  private lazy val castOptMapURI            = (field: String) => (doc: BsonDocument) => castOptMap[URI](doc, field, (bv: BsonValue) => new URI(bv.asString.getValue))
  private lazy val castOptMapByte           = (field: String) => (doc: BsonDocument) => castOptMap[Byte](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toByte)
  private lazy val castOptMapShort          = (field: String) => (doc: BsonDocument) => castOptMap[Short](doc, field, (bv: BsonValue) => bv.asInt32.getValue.toShort)
  private lazy val castOptMapChar           = (field: String) => (doc: BsonDocument) => castOptMap[Char](doc, field, (bv: BsonValue) => bv.asString.getValue.charAt(0))

  case class ResMapOpt[T](
    tpe: String,
    castOptMap: String => BsonDocument => Option[Map[String, T]],


    //    set2sqlArray: Set[T] => String,
    //    set2sqls: Set[T] => Set[String],
    //    one2sql: T => String,
    //    one2json: T => String
  )

  lazy val resOptMapID            : ResMapOpt[String]         = ResMapOpt("String", castOptMapID) //, sql2optMapId) //, set2sqlArrayId, set2sqlsId, one2sqlId, one2jsonId)
  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", castOptMapString) //, sql2optMapString) //, set2sqlArrayString, set2sqlsString, one2sqlString, one2jsonString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", castOptMapInt) //, sql2optMapInt) //, set2sqlArrayInt, set2sqlsInt, one2sqlInt, one2jsonInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", castOptMapLong) //, sql2optMapLong) //, set2sqlArrayLong, set2sqlsLong, one2sqlLong, one2jsonLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", castOptMapFloat) //, sql2optMapFloat) //, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, one2jsonFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", castOptMapDouble) //, sql2optMapDouble) //, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, one2jsonDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", castOptMapBoolean) //, sql2optMapBoolean) //, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, one2jsonBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", castOptMapBigInt) //, sql2optMapBigInt) //, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, one2jsonBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", castOptMapBigDecimal) //, sql2optMapBigDecimal) //, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, one2jsonBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", castOptMapDate) //, sql2optMapDate) //, set2sqlArrayDate, set2sqlsDate, one2sqlDate, one2jsonDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", castOptMapDuration) //, sql2optMapDuration) //, set2sqlArrayDuration, set2sqlsDuration, one2sqlDuration, one2jsonDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", castOptMapInstant) //, sql2optMapInstant) //, set2sqlArrayInstant, set2sqlsInstant, one2sqlInstant, one2jsonInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", castOptMapLocalDate) //, sql2optMapLocalDate) //, set2sqlArrayLocalDate, set2sqlsLocalDate, one2sqlLocalDate, one2jsonLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", castOptMapLocalTime) //, sql2optMapLocalTime) //, set2sqlArrayLocalTime, set2sqlsLocalTime, one2sqlLocalTime, one2jsonLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", castOptMapLocalDateTime) //, sql2optMapLocalDateTime) //, set2sqlArrayLocalDateTime, set2sqlsLocalDateTime, one2sqlLocalDateTime, one2jsonLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", castOptMapOffsetTime) //, sql2optMapOffsetTime) //, set2sqlArrayOffsetTime, set2sqlsOffsetTime, one2sqlOffsetTime, one2jsonOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", castOptMapOffsetDateTime) //, sql2optMapOffsetDateTime) //, set2sqlArrayOffsetDateTime, set2sqlsOffsetDateTime, one2sqlOffsetDateTime, one2jsonOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", castOptMapZonedDateTime) //, sql2optMapZonedDateTime) //, set2sqlArrayZonedDateTime, set2sqlsZonedDateTime, one2sqlZonedDateTime, one2jsonZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", castOptMapUUID) //, sql2optMapUUID) //, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, one2jsonUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", castOptMapURI) //, sql2optMapURI) //, set2sqlArrayURI, set2sqlsURI, one2sqlURI, one2jsonURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", castOptMapByte) //, sql2optMapByte) //, set2sqlArrayByte, set2sqlsByte, one2sqlByte, one2jsonByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", castOptMapShort) //, sql2optMapShort) //, set2sqlArrayShort, set2sqlsShort, one2sqlShort, one2jsonShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", castOptMapChar) //, sql2optMapChar) //, set2sqlArrayChar, set2sqlsChar, one2sqlChar, one2jsonChar)


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
