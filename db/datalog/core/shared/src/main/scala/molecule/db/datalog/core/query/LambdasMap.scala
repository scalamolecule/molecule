package molecule.db.datalog.core.query

import java.net.URI
import java.time.*
import java.util.{Date, UUID, Iterator as jIterator, List as jList, Map as jMap, Set as jSet}
import molecule.base.error.ModelError
import molecule.core.util.JavaConversions
import scala.collection.mutable.ListBuffer

trait LambdasMap extends ResolveBase with JavaConversions {

  def noId = throw ModelError("Ids not supported as values in a Map.")

  private lazy val j2sMapString        : AnyRef => Map[String, String]         = (seq: AnyRef) => j2Map(_.asInstanceOf[String]).apply(seq)
  private lazy val j2sMapInt           : AnyRef => Map[String, Int]            = (seq: AnyRef) => j2Map(_.toString.toInt).apply(seq)
  private lazy val j2sMapLong          : AnyRef => Map[String, Long]           = (seq: AnyRef) => j2Map(_.asInstanceOf[Long]).apply(seq)
  private lazy val j2sMapFloat         : AnyRef => Map[String, Float]          = (seq: AnyRef) => j2Map(_.asInstanceOf[Float]).apply(seq)
  private lazy val j2sMapDouble        : AnyRef => Map[String, Double]         = (seq: AnyRef) => j2Map(_.asInstanceOf[Double]).apply(seq)
  private lazy val j2sMapBoolean       : AnyRef => Map[String, Boolean]        = (seq: AnyRef) => j2Map(_.asInstanceOf[Boolean]).apply(seq)
  private lazy val j2sMapBigInt        : AnyRef => Map[String, BigInt]         = (seq: AnyRef) => j2Map((v: Any) => BigInt(v.toString)).apply(seq)
  private lazy val j2sMapBigDecimal    : AnyRef => Map[String, BigDecimal]     = (seq: AnyRef) => j2Map((v: Any) => BigDecimal(v.toString)).apply(seq)
  private lazy val j2sMapDate          : AnyRef => Map[String, Date]           = (seq: AnyRef) => j2Map(_.asInstanceOf[Date]).apply(seq)
  private lazy val j2sMapDuration      : AnyRef => Map[String, Duration]       = (seq: AnyRef) => j2Map((v: Any) => Duration.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapInstant       : AnyRef => Map[String, Instant]        = (seq: AnyRef) => j2Map((v: Any) => Instant.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapLocalDate     : AnyRef => Map[String, LocalDate]      = (seq: AnyRef) => j2Map((v: Any) => LocalDate.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapLocalTime     : AnyRef => Map[String, LocalTime]      = (seq: AnyRef) => j2Map((v: Any) => LocalTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapLocalDateTime : AnyRef => Map[String, LocalDateTime]  = (seq: AnyRef) => j2Map((v: Any) => LocalDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapOffsetTime    : AnyRef => Map[String, OffsetTime]     = (seq: AnyRef) => j2Map((v: Any) => OffsetTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapOffsetDateTime: AnyRef => Map[String, OffsetDateTime] = (seq: AnyRef) => j2Map((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapZonedDateTime : AnyRef => Map[String, ZonedDateTime]  = (seq: AnyRef) => j2Map((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sMapUUID          : AnyRef => Map[String, UUID]           = (seq: AnyRef) => j2Map(_.asInstanceOf[UUID]).apply(seq)
  private lazy val j2sMapURI           : AnyRef => Map[String, URI]            = (seq: AnyRef) => j2Map(_.asInstanceOf[URI]).apply(seq)
  private lazy val j2sMapByte          : AnyRef => Map[String, Byte]           = (seq: AnyRef) => j2Map(_.asInstanceOf[Integer].toByte).apply(seq)
  private lazy val j2sMapShort         : AnyRef => Map[String, Short]          = (seq: AnyRef) => j2Map(_.asInstanceOf[Integer].toShort).apply(seq)
  private lazy val j2sMapChar          : AnyRef => Map[String, Char]           = (seq: AnyRef) => j2Map(_.asInstanceOf[String].charAt(0)).apply(seq)


  private def j2Map[T](decode: Any => T): AnyRef => Map[String, T] = (pairs: AnyRef) => {
    val buf = ListBuffer.empty[(String, T)]
    pairs.asInstanceOf[jSet[?]].forEach {
      case pair: jList[_] => buf += ((pair.get(0).asInstanceOf[String], decode(pair.get(1))))
      case `none`         => nullValue
      case other          => unexpectedValue(other)
    }
    buf.toMap
  }

  case class ResMap[T](
    j2sMap: AnyRef => Map[String, T],
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
  )

  lazy val resMapString        : ResMap[String]         = ResMap(j2sMapString, s2jString, j2sString)
  lazy val resMapInt           : ResMap[Int]            = ResMap(j2sMapInt, s2jInt, j2sInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap(j2sMapLong, s2jLong, j2sLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap(j2sMapFloat, s2jFloat, j2sFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap(j2sMapDouble, s2jDouble, j2sDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap(j2sMapBoolean, s2jBoolean, j2sBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap(j2sMapBigInt, s2jBigInt, j2sBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap(j2sMapBigDecimal, s2jBigDecimal, j2sBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap(j2sMapDate, s2jDate, j2sDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap(j2sMapDuration, s2jDuration, j2sDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap(j2sMapInstant, s2jInstant, j2sInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap(j2sMapLocalDate, s2jLocalDate, j2sLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap(j2sMapLocalTime, s2jLocalTime, j2sLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap(j2sMapLocalDateTime, s2jLocalDateTime, j2sLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap(j2sMapOffsetTime, s2jOffsetTime, j2sOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap(j2sMapOffsetDateTime, s2jOffsetDateTime, j2sOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap(j2sMapZonedDateTime, s2jZonedDateTime, j2sZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap(j2sMapUUID, s2jUUID, j2sUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap(j2sMapURI, s2jURI, j2sURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap(j2sMapByte, s2jByte, j2sByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap(j2sMapShort, s2jShort, j2sShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap(j2sMapChar, s2jChar, j2sChar)

  private def j2optMap[T](decode: Any => T): AnyRef => Option[Map[String, T]] = (v: AnyRef) => {
    val pairs = v.asInstanceOf[jList[?]]
    if (pairs.isEmpty) {
      Option.empty[Map[String, T]]
    } else {
      val buf = ListBuffer.empty[(String, T)]
      pairs.forEach {
        case map: jMap[_, _] =>
          val it = map.values.iterator
          buf += ((it.next.asInstanceOf[String], decode(it.next)))
      }
      Some(buf.toMap)
    }
  }

  private lazy val j2optMapString         = j2optMap(j2String)
  private lazy val j2optMapInt            = j2optMap(j2Int)
  private lazy val j2optMapLong           = j2optMap(j2Long)
  private lazy val j2optMapFloat          = j2optMap(j2Float)
  private lazy val j2optMapDouble         = j2optMap(j2Double)
  private lazy val j2optMapBoolean        = j2optMap(j2Boolean)
  private lazy val j2optMapBigInt         = j2optMap(j2BigInt)
  private lazy val j2optMapBigDecimal     = j2optMap(j2BigDecimal)
  private lazy val j2optMapDate           = j2optMap(j2Date)
  private lazy val j2optMapDuration       = j2optMap(j2Duration)
  private lazy val j2optMapInstant        = j2optMap(j2Instant)
  private lazy val j2optMapLocalDate      = j2optMap(j2LocalDate)
  private lazy val j2optMapLocalTime      = j2optMap(j2LocalTime)
  private lazy val j2optMapLocalDateTime  = j2optMap(j2LocalDateTime)
  private lazy val j2optMapOffsetTime     = j2optMap(j2OffsetTime)
  private lazy val j2optMapOffsetDateTime = j2optMap(j2OffsetDateTime)
  private lazy val j2optMapZonedDateTime  = j2optMap(j2ZonedDateTime)
  private lazy val j2optMapUUID           = j2optMap(j2UUID)
  private lazy val j2optMapURI            = j2optMap(j2URI)
  private lazy val j2optMapByte           = j2optMap(j2Byte)
  private lazy val j2optMapShort          = j2optMap(j2Short)
  private lazy val j2optMapChar           = j2optMap(j2Char)

  private def j2optValue[T](decode: Any => T): AnyRef => Option[T] = (v: AnyRef) => {
    val seq = v.asInstanceOf[jList[?]]
    if (seq.isEmpty) Option.empty[T] else Some(decode(seq.get(0)))
  }

  private lazy val j2optValueString         = j2optValue(j2String)
  private lazy val j2optValueInt            = j2optValue(j2Int)
  private lazy val j2optValueLong           = j2optValue(j2Long)
  private lazy val j2optValueFloat          = j2optValue(j2Float)
  private lazy val j2optValueDouble         = j2optValue(j2Double)
  private lazy val j2optValueBoolean        = j2optValue(j2Boolean)
  private lazy val j2optValueBigInt         = j2optValue(j2BigInt)
  private lazy val j2optValueBigDecimal     = j2optValue(j2BigDecimal)
  private lazy val j2optValueDate           = j2optValue(j2Date)
  private lazy val j2optValueDuration       = j2optValue(j2Duration)
  private lazy val j2optValueInstant        = j2optValue(j2Instant)
  private lazy val j2optValueLocalDate      = j2optValue(j2LocalDate)
  private lazy val j2optValueLocalTime      = j2optValue(j2LocalTime)
  private lazy val j2optValueLocalDateTime  = j2optValue(j2LocalDateTime)
  private lazy val j2optValueOffsetTime     = j2optValue(j2OffsetTime)
  private lazy val j2optValueOffsetDateTime = j2optValue(j2OffsetDateTime)
  private lazy val j2optValueZonedDateTime  = j2optValue(j2ZonedDateTime)
  private lazy val j2optValueUUID           = j2optValue(j2UUID)
  private lazy val j2optValueURI            = j2optValue(j2URI)
  private lazy val j2optValueByte           = j2optValue(j2Byte)
  private lazy val j2optValueShort          = j2optValue(j2Short)
  private lazy val j2optValueChar           = j2optValue(j2Char)


  case class ResMapOpt[T](
    j2optMap: AnyRef => Option[Map[String, T]],
    j2optValue: AnyRef => Option[T],
  )

  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt(j2optMapString, j2optValueString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt(j2optMapInt, j2optValueInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt(j2optMapLong, j2optValueLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt(j2optMapFloat, j2optValueFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt(j2optMapDouble, j2optValueDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt(j2optMapBoolean, j2optValueBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt(j2optMapBigInt, j2optValueBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt(j2optMapBigDecimal, j2optValueBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt(j2optMapDate, j2optValueDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt(j2optMapDuration, j2optValueDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt(j2optMapInstant, j2optValueInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt(j2optMapLocalDate, j2optValueLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt(j2optMapLocalTime, j2optValueLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt(j2optMapLocalDateTime, j2optValueLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt(j2optMapOffsetTime, j2optValueOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt(j2optMapOffsetDateTime, j2optValueOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt(j2optMapZonedDateTime, j2optValueZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt(j2optMapUUID, j2optValueUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt(j2optMapURI, j2optValueURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt(j2optMapByte, j2optValueByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt(j2optMapShort, j2optValueShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt(j2optMapChar, j2optValueChar)


  // Nested opt ---------------------------------------------------------------------

  private def it2Map[T](decode: Any => T): jIterator[?] => Any = (it: jIterator[?]) => it.next match {
    case maps: jList[_] =>
      val pairs = ListBuffer.empty[(String, T)]
      maps.forEach {
        case map: jMap[_, _] =>
          val vs = map.values().iterator()
          pairs += ((vs.next().asInstanceOf[String], decode(vs.next())))
      }
      pairs.toMap
    case `none`         => nullValue
    case other          => unexpectedValue(other)
  }

  lazy val it2MapString        : jIterator[?] => Any = it2Map(j2String)
  lazy val it2MapInt           : jIterator[?] => Any = it2Map(j2Int)
  lazy val it2MapLong          : jIterator[?] => Any = it2Map(j2Long)
  lazy val it2MapFloat         : jIterator[?] => Any = it2Map(j2Float)
  lazy val it2MapDouble        : jIterator[?] => Any = it2Map(j2Double)
  lazy val it2MapBoolean       : jIterator[?] => Any = it2Map(j2Boolean)
  lazy val it2MapBigInt        : jIterator[?] => Any = it2Map(j2BigInt)
  lazy val it2MapBigDecimal    : jIterator[?] => Any = it2Map(j2BigDecimal)
  lazy val it2MapDate          : jIterator[?] => Any = it2Map(j2Date)
  lazy val it2MapDuration      : jIterator[?] => Any = it2Map(j2Duration)
  lazy val it2MapInstant       : jIterator[?] => Any = it2Map(j2Instant)
  lazy val it2MapLocalDate     : jIterator[?] => Any = it2Map(j2LocalDate)
  lazy val it2MapLocalTime     : jIterator[?] => Any = it2Map(j2LocalTime)
  lazy val it2MapLocalDateTime : jIterator[?] => Any = it2Map(j2LocalDateTime)
  lazy val it2MapOffsetTime    : jIterator[?] => Any = it2Map(j2OffsetTime)
  lazy val it2MapOffsetDateTime: jIterator[?] => Any = it2Map(j2OffsetDateTime)
  lazy val it2MapZonedDateTime : jIterator[?] => Any = it2Map(j2ZonedDateTime)
  lazy val it2MapUUID          : jIterator[?] => Any = it2Map(j2UUID)
  lazy val it2MapURI           : jIterator[?] => Any = it2Map(j2URI)
  lazy val it2MapByte          : jIterator[?] => Any = it2Map(j2Byte)
  lazy val it2MapShort         : jIterator[?] => Any = it2Map(j2Short)
  lazy val it2MapChar          : jIterator[?] => Any = it2Map(j2Char)


  private def it2OptMap[T](decode: Any => T): jIterator[?] => Any = (it: jIterator[?]) => it.next match {
    case maps: jList[_] =>
      val pairs = ListBuffer.empty[(String, T)]
      maps.forEach {
        case map: jMap[_, _] =>
          val vs = map.values().iterator()
          pairs += ((vs.next().asInstanceOf[String], decode(vs.next())))
      }
      if (pairs.nonEmpty) Some(pairs.toMap) else None

    case `none` => None
    case other  => unexpectedValue(other)
  }

  lazy val it2OptMapString        : jIterator[?] => Any = it2OptMap(j2String)
  lazy val it2OptMapInt           : jIterator[?] => Any = it2OptMap(j2Int)
  lazy val it2OptMapLong          : jIterator[?] => Any = it2OptMap(j2Long)
  lazy val it2OptMapFloat         : jIterator[?] => Any = it2OptMap(j2Float)
  lazy val it2OptMapDouble        : jIterator[?] => Any = it2OptMap(j2Double)
  lazy val it2OptMapBoolean       : jIterator[?] => Any = it2OptMap(j2Boolean)
  lazy val it2OptMapBigInt        : jIterator[?] => Any = it2OptMap(j2BigInt)
  lazy val it2OptMapBigDecimal    : jIterator[?] => Any = it2OptMap(j2BigDecimal)
  lazy val it2OptMapDate          : jIterator[?] => Any = it2OptMap(j2Date)
  lazy val it2OptMapDuration      : jIterator[?] => Any = it2OptMap(j2Duration)
  lazy val it2OptMapInstant       : jIterator[?] => Any = it2OptMap(j2Instant)
  lazy val it2OptMapLocalDate     : jIterator[?] => Any = it2OptMap(j2LocalDate)
  lazy val it2OptMapLocalTime     : jIterator[?] => Any = it2OptMap(j2LocalTime)
  lazy val it2OptMapLocalDateTime : jIterator[?] => Any = it2OptMap(j2LocalDateTime)
  lazy val it2OptMapOffsetTime    : jIterator[?] => Any = it2OptMap(j2OffsetTime)
  lazy val it2OptMapOffsetDateTime: jIterator[?] => Any = it2OptMap(j2OffsetDateTime)
  lazy val it2OptMapZonedDateTime : jIterator[?] => Any = it2OptMap(j2ZonedDateTime)
  lazy val it2OptMapUUID          : jIterator[?] => Any = it2OptMap(j2UUID)
  lazy val it2OptMapURI           : jIterator[?] => Any = it2OptMap(j2URI)
  lazy val it2OptMapByte          : jIterator[?] => Any = it2OptMap(j2Byte)
  lazy val it2OptMapShort         : jIterator[?] => Any = it2OptMap(j2Short)
  lazy val it2OptMapChar          : jIterator[?] => Any = it2OptMap(j2Char)
}