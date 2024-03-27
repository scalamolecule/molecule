package molecule.datalog.core.query

import java.lang.{Integer => jInteger, Long => jLong}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
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
    pairs.asInstanceOf[jSet[_]].forEach {
      case pair: jList[_] => buf += ((pair.get(0).asInstanceOf[String], decode(pair.get(1))))
      case `none`         => nullValue
      case other          => unexpectedValue(other)
    }
    buf.toMap
  }

  case class ResMap[T](
    tpe: String,
    toDatalog: T => String,
    j2sMap: AnyRef => Map[String, T],
    sPair2jVector: ((String, T)) => Array[AnyRef],
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
  )

  lazy val resMapString        : ResMap[String]         = ResMap("String", dString, j2sMapString, sPair2jVectorString, s2jString, j2sString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", dInt, j2sMapInt, sPair2jVectorInt, s2jInt, j2sInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", dLong, j2sMapLong, sPair2jVectorLong, s2jLong, j2sLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", dFloat, j2sMapFloat, sPair2jVectorFloat, s2jFloat, j2sFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", dDouble, j2sMapDouble, sPair2jVectorDouble, s2jDouble, j2sDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", dBoolean, j2sMapBoolean, sPair2jVectorBoolean, s2jBoolean, j2sBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", dBigInt, j2sMapBigInt, sPair2jVectorBigInt, s2jBigInt, j2sBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", dBigDecimal, j2sMapBigDecimal, sPair2jVectorBigDecimal, s2jBigDecimal, j2sBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", dDate, j2sMapDate, sPair2jVectorDate, s2jDate, j2sDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", dDuration, j2sMapDuration, sPair2jVectorDuration, s2jDuration, j2sDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", dInstant, j2sMapInstant, sPair2jVectorInstant, s2jInstant, j2sInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", dLocalDate, j2sMapLocalDate, sPair2jVectorLocalDate, s2jLocalDate, j2sLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", dLocalTime, j2sMapLocalTime, sPair2jVectorLocalTime, s2jLocalTime, j2sLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", dLocalDateTime, j2sMapLocalDateTime, sPair2jVectorLocalDateTime, s2jLocalDateTime, j2sLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", dOffsetTime, j2sMapOffsetTime, sPair2jVectorOffsetTime, s2jOffsetTime, j2sOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", dOffsetDateTime, j2sMapOffsetDateTime, sPair2jVectorOffsetDateTime, s2jOffsetDateTime, j2sOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", dZonedDateTime, j2sMapZonedDateTime, sPair2jVectorZonedDateTime, s2jZonedDateTime, j2sZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", dUUID, j2sMapUUID, sPair2jVectorUUID, s2jUUID, j2sUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", dURI, j2sMapURI, sPair2jVectorURI, s2jURI, j2sURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", dByte, j2sMapByte, sPair2jVectorByte, s2jByte, j2sByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", dShort, j2sMapShort, sPair2jVectorShort, s2jShort, j2sShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", dChar, j2sMapChar, sPair2jVectorChar, s2jChar, j2sChar)


  // Scala to Java
  private lazy val s2jAnyRefString        : String => AnyRef         = (v: String) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefInt           : Int => AnyRef            = (v: Int) => v.toLong.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefLong          : Long => AnyRef           = (v: Long) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefFloat         : Float => AnyRef          = (v: Float) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefDouble        : Double => AnyRef         = (v: Double) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefBoolean       : Boolean => AnyRef        = (v: Boolean) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefBigInt        : BigInt => AnyRef         = (v: BigInt) => v.bigInteger.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefBigDecimal    : BigDecimal => AnyRef     = (v: BigDecimal) => v.bigDecimal.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefDate          : Date => AnyRef           = (v: Date) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefDuration      : Duration => AnyRef       = (v: Duration) => v.toString
  private lazy val s2jAnyRefInstant       : Instant => AnyRef        = (v: Instant) => v.toString
  private lazy val s2jAnyRefLocalDate     : LocalDate => AnyRef      = (v: LocalDate) => v.toString
  private lazy val s2jAnyRefLocalTime     : LocalTime => AnyRef      = (v: LocalTime) => v.toString
  private lazy val s2jAnyRefLocalDateTime : LocalDateTime => AnyRef  = (v: LocalDateTime) => v.toString
  private lazy val s2jAnyRefOffsetTime    : OffsetTime => AnyRef     = (v: OffsetTime) => v.toString
  private lazy val s2jAnyRefOffsetDateTime: OffsetDateTime => AnyRef = (v: OffsetDateTime) => v.toString
  private lazy val s2jAnyRefZonedDateTime : ZonedDateTime => AnyRef  = (v: ZonedDateTime) => v.toString
  private lazy val s2jAnyRefUUID          : UUID => AnyRef           = (v: UUID) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefURI           : URI => AnyRef            = (v: URI) => v.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefByte          : Byte => AnyRef           = (v: Byte) => v.toInt.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefShort         : Short => AnyRef          = (v: Short) => v.toInt.asInstanceOf[AnyRef]
  private lazy val s2jAnyRefChar          : Char => AnyRef           = (v: Char) => v.toString.asInstanceOf[AnyRef]

  private lazy val sPair2jVectorString        : ((String, String)) => Array[AnyRef]         = sPair2jVector(s2jAnyRefString)
  private lazy val sPair2jVectorInt           : ((String, Int)) => Array[AnyRef]            = sPair2jVector(s2jAnyRefInt)
  private lazy val sPair2jVectorLong          : ((String, Long)) => Array[AnyRef]           = sPair2jVector(s2jAnyRefLong)
  private lazy val sPair2jVectorFloat         : ((String, Float)) => Array[AnyRef]          = sPair2jVector(s2jAnyRefFloat)
  private lazy val sPair2jVectorDouble        : ((String, Double)) => Array[AnyRef]         = sPair2jVector(s2jAnyRefDouble)
  private lazy val sPair2jVectorBoolean       : ((String, Boolean)) => Array[AnyRef]        = sPair2jVector(s2jAnyRefBoolean)
  private lazy val sPair2jVectorBigInt        : ((String, BigInt)) => Array[AnyRef]         = sPair2jVector(s2jAnyRefBigInt)
  private lazy val sPair2jVectorBigDecimal    : ((String, BigDecimal)) => Array[AnyRef]     = sPair2jVector(s2jAnyRefBigDecimal)
  private lazy val sPair2jVectorDate          : ((String, Date)) => Array[AnyRef]           = sPair2jVector(s2jAnyRefDate)
  private lazy val sPair2jVectorDuration      : ((String, Duration)) => Array[AnyRef]       = sPair2jVector(s2jAnyRefDuration)
  private lazy val sPair2jVectorInstant       : ((String, Instant)) => Array[AnyRef]        = sPair2jVector(s2jAnyRefInstant)
  private lazy val sPair2jVectorLocalDate     : ((String, LocalDate)) => Array[AnyRef]      = sPair2jVector(s2jAnyRefLocalDate)
  private lazy val sPair2jVectorLocalTime     : ((String, LocalTime)) => Array[AnyRef]      = sPair2jVector(s2jAnyRefLocalTime)
  private lazy val sPair2jVectorLocalDateTime : ((String, LocalDateTime)) => Array[AnyRef]  = sPair2jVector(s2jAnyRefLocalDateTime)
  private lazy val sPair2jVectorOffsetTime    : ((String, OffsetTime)) => Array[AnyRef]     = sPair2jVector(s2jAnyRefOffsetTime)
  private lazy val sPair2jVectorOffsetDateTime: ((String, OffsetDateTime)) => Array[AnyRef] = sPair2jVector(s2jAnyRefOffsetDateTime)
  private lazy val sPair2jVectorZonedDateTime : ((String, ZonedDateTime)) => Array[AnyRef]  = sPair2jVector(s2jAnyRefZonedDateTime)
  private lazy val sPair2jVectorUUID          : ((String, UUID)) => Array[AnyRef]           = sPair2jVector(s2jAnyRefUUID)
  private lazy val sPair2jVectorURI           : ((String, URI)) => Array[AnyRef]            = sPair2jVector(s2jAnyRefURI)
  private lazy val sPair2jVectorByte          : ((String, Byte)) => Array[AnyRef]           = sPair2jVector(s2jAnyRefByte)
  private lazy val sPair2jVectorShort         : ((String, Short)) => Array[AnyRef]          = sPair2jVector(s2jAnyRefShort)
  private lazy val sPair2jVectorChar          : ((String, Char)) => Array[AnyRef]           = sPair2jVector(s2jAnyRefChar)

  private def sPair2jVector[T](encode: T => AnyRef): ((String, T)) => Array[AnyRef] = {
    (pair: (String, T)) => Array(pair._1, encode(pair._2))
  }

  private def j2optMap[T](decode: Any => T): AnyRef => Option[Map[String, T]] = (v: AnyRef) => {
    val pairs = v.asInstanceOf[jList[_]]
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
    val seq = v.asInstanceOf[jList[_]]
    if(seq.isEmpty) Option.empty[T] else Some(decode(seq.get(0)))
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
    tpe: String,
    toDatalog: T => String,
    j2optMap: AnyRef => Option[Map[String, T]],
    j2optValue: AnyRef => Option[T],
  )

  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", dString, j2optMapString, j2optValueString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", dInt, j2optMapInt, j2optValueInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", dLong, j2optMapLong, j2optValueLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", dFloat, j2optMapFloat, j2optValueFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", dDouble, j2optMapDouble, j2optValueDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", dBoolean, j2optMapBoolean, j2optValueBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", dBigInt, j2optMapBigInt, j2optValueBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", dBigDecimal, j2optMapBigDecimal, j2optValueBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", dDate, j2optMapDate, j2optValueDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", dDuration, j2optMapDuration, j2optValueDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", dInstant, j2optMapInstant, j2optValueInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", dLocalDate, j2optMapLocalDate, j2optValueLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", dLocalTime, j2optMapLocalTime, j2optValueLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", dLocalDateTime, j2optMapLocalDateTime, j2optValueLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", dOffsetTime, j2optMapOffsetTime, j2optValueOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", dOffsetDateTime, j2optMapOffsetDateTime, j2optValueOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", dZonedDateTime, j2optMapZonedDateTime, j2optValueZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", dUUID, j2optMapUUID, j2optValueUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", dURI, j2optMapURI, j2optValueURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", dByte, j2optMapByte, j2optValueByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", dShort, j2optMapShort, j2optValueShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", dChar, j2optMapChar, j2optValueChar)


  // Nested opt ---------------------------------------------------------------------

  private def it2Map[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
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

  lazy val it2MapString        : jIterator[_] => Any = it2Map(j2String)
  lazy val it2MapInt           : jIterator[_] => Any = it2Map(j2Int)
  lazy val it2MapLong          : jIterator[_] => Any = it2Map(j2Long)
  lazy val it2MapFloat         : jIterator[_] => Any = it2Map(j2Float)
  lazy val it2MapDouble        : jIterator[_] => Any = it2Map(j2Double)
  lazy val it2MapBoolean       : jIterator[_] => Any = it2Map(j2Boolean)
  lazy val it2MapBigInt        : jIterator[_] => Any = it2Map(j2BigInt)
  lazy val it2MapBigDecimal    : jIterator[_] => Any = it2Map(j2BigDecimal)
  lazy val it2MapDate          : jIterator[_] => Any = it2Map(j2Date)
  lazy val it2MapDuration      : jIterator[_] => Any = it2Map(j2Duration)
  lazy val it2MapInstant       : jIterator[_] => Any = it2Map(j2Instant)
  lazy val it2MapLocalDate     : jIterator[_] => Any = it2Map(j2LocalDate)
  lazy val it2MapLocalTime     : jIterator[_] => Any = it2Map(j2LocalTime)
  lazy val it2MapLocalDateTime : jIterator[_] => Any = it2Map(j2LocalDateTime)
  lazy val it2MapOffsetTime    : jIterator[_] => Any = it2Map(j2OffsetTime)
  lazy val it2MapOffsetDateTime: jIterator[_] => Any = it2Map(j2OffsetDateTime)
  lazy val it2MapZonedDateTime : jIterator[_] => Any = it2Map(j2ZonedDateTime)
  lazy val it2MapUUID          : jIterator[_] => Any = it2Map(j2UUID)
  lazy val it2MapURI           : jIterator[_] => Any = it2Map(j2URI)
  lazy val it2MapByte          : jIterator[_] => Any = it2Map(j2Byte)
  lazy val it2MapShort         : jIterator[_] => Any = it2Map(j2Short)
  lazy val it2MapChar          : jIterator[_] => Any = it2Map(j2Char)


  private def it2OptMap[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
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

  lazy val it2OptMapString        : jIterator[_] => Any = it2OptMap(j2String)
  lazy val it2OptMapInt           : jIterator[_] => Any = it2OptMap(j2Int)
  lazy val it2OptMapLong          : jIterator[_] => Any = it2OptMap(j2Long)
  lazy val it2OptMapFloat         : jIterator[_] => Any = it2OptMap(j2Float)
  lazy val it2OptMapDouble        : jIterator[_] => Any = it2OptMap(j2Double)
  lazy val it2OptMapBoolean       : jIterator[_] => Any = it2OptMap(j2Boolean)
  lazy val it2OptMapBigInt        : jIterator[_] => Any = it2OptMap(j2BigInt)
  lazy val it2OptMapBigDecimal    : jIterator[_] => Any = it2OptMap(j2BigDecimal)
  lazy val it2OptMapDate          : jIterator[_] => Any = it2OptMap(j2Date)
  lazy val it2OptMapDuration      : jIterator[_] => Any = it2OptMap(j2Duration)
  lazy val it2OptMapInstant       : jIterator[_] => Any = it2OptMap(j2Instant)
  lazy val it2OptMapLocalDate     : jIterator[_] => Any = it2OptMap(j2LocalDate)
  lazy val it2OptMapLocalTime     : jIterator[_] => Any = it2OptMap(j2LocalTime)
  lazy val it2OptMapLocalDateTime : jIterator[_] => Any = it2OptMap(j2LocalDateTime)
  lazy val it2OptMapOffsetTime    : jIterator[_] => Any = it2OptMap(j2OffsetTime)
  lazy val it2OptMapOffsetDateTime: jIterator[_] => Any = it2OptMap(j2OffsetDateTime)
  lazy val it2OptMapZonedDateTime : jIterator[_] => Any = it2OptMap(j2ZonedDateTime)
  lazy val it2OptMapUUID          : jIterator[_] => Any = it2OptMap(j2UUID)
  lazy val it2OptMapURI           : jIterator[_] => Any = it2OptMap(j2URI)
  lazy val it2OptMapByte          : jIterator[_] => Any = it2OptMap(j2Byte)
  lazy val it2OptMapShort         : jIterator[_] => Any = it2OptMap(j2Short)
  lazy val it2OptMapChar          : jIterator[_] => Any = it2OptMap(j2Char)
}