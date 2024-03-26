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


  private lazy val set2setString        : AnyRef => AnyRef = jset2set(j2sString)
  private lazy val set2setInt           : AnyRef => AnyRef = jset2set(j2sInt)
  private lazy val set2setLong          : AnyRef => AnyRef = jset2set(j2sLong)
  private lazy val set2setFloat         : AnyRef => AnyRef = jset2set(j2sFloat)
  private lazy val set2setDouble        : AnyRef => AnyRef = jset2set(j2sDouble)
  private lazy val set2setBoolean       : AnyRef => AnyRef = jset2set(j2sBoolean)
  private lazy val set2setBigInt        : AnyRef => AnyRef = jset2set(j2sBigInt)
  private lazy val set2setBigDecimal    : AnyRef => AnyRef = jset2set(j2sBigDecimal)
  private lazy val set2setDate          : AnyRef => AnyRef = jset2set(j2sDate)
  private lazy val set2setDuration      : AnyRef => AnyRef = jset2set(j2sDuration)
  private lazy val set2setInstant       : AnyRef => AnyRef = jset2set(j2sInstant)
  private lazy val set2setLocalDate     : AnyRef => AnyRef = jset2set(j2sLocalDate)
  private lazy val set2setLocalTime     : AnyRef => AnyRef = jset2set(j2sLocalTime)
  private lazy val set2setLocalDateTime : AnyRef => AnyRef = jset2set(j2sLocalDateTime)
  private lazy val set2setOffsetTime    : AnyRef => AnyRef = jset2set(j2sOffsetTime)
  private lazy val set2setOffsetDateTime: AnyRef => AnyRef = jset2set(j2sOffsetDateTime)
  private lazy val set2setZonedDateTime : AnyRef => AnyRef = jset2set(j2sZonedDateTime)
  private lazy val set2setUUID          : AnyRef => AnyRef = jset2set(j2sUUID)
  private lazy val set2setURI           : AnyRef => AnyRef = jset2set(j2sURI)
  private lazy val set2setByte          : AnyRef => AnyRef = jset2set(j2sByte)
  private lazy val set2setShort         : AnyRef => AnyRef = jset2set(j2sShort)
  private lazy val set2setChar          : AnyRef => AnyRef = jset2set(j2sChar)

  private def jset2set(decode: AnyRef => AnyRef): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[Set[AnyRef]].toArray.map(decode)


  private lazy val jSetOfLists2sString        : AnyRef => AnyRef = jSetOfLists2s[String](_.asInstanceOf[String])
  private lazy val jSetOfLists2sInt           : AnyRef => AnyRef = jSetOfLists2s[Int](_.toString.toInt)
  private lazy val jSetOfLists2sLong          : AnyRef => AnyRef = jSetOfLists2s[Long](_.asInstanceOf[Long])
  private lazy val jSetOfLists2sFloat         : AnyRef => AnyRef = jSetOfLists2s[Float](_.asInstanceOf[Float])
  private lazy val jSetOfLists2sDouble        : AnyRef => AnyRef = jSetOfLists2s[Double](_.asInstanceOf[Double])
  private lazy val jSetOfLists2sBoolean       : AnyRef => AnyRef = jSetOfLists2s[Boolean](_.asInstanceOf[Boolean])
  private lazy val jSetOfLists2sBigInt        : AnyRef => AnyRef = jSetOfLists2s[BigInt]((v: Any) => BigInt(v.toString))
  private lazy val jSetOfLists2sBigDecimal    : AnyRef => AnyRef = jSetOfLists2s[BigDecimal]((v: Any) => BigDecimal(v.toString))
  private lazy val jSetOfLists2sDate          : AnyRef => AnyRef = jSetOfLists2s[Date](_.asInstanceOf[Date])
  private lazy val jSetOfLists2sDuration      : AnyRef => AnyRef = jSetOfLists2s[Duration]((v: Any) => Duration.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sInstant       : AnyRef => AnyRef = jSetOfLists2s[Instant]((v: Any) => Instant.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sLocalDate     : AnyRef => AnyRef = jSetOfLists2s[LocalDate]((v: Any) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sLocalTime     : AnyRef => AnyRef = jSetOfLists2s[LocalTime]((v: Any) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sLocalDateTime : AnyRef => AnyRef = jSetOfLists2s[LocalDateTime]((v: Any) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sOffsetTime    : AnyRef => AnyRef = jSetOfLists2s[OffsetTime]((v: Any) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sOffsetDateTime: AnyRef => AnyRef = jSetOfLists2s[OffsetDateTime]((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sZonedDateTime : AnyRef => AnyRef = jSetOfLists2s[ZonedDateTime]((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val jSetOfLists2sUUID          : AnyRef => AnyRef = jSetOfLists2s[UUID](_.asInstanceOf[UUID])
  private lazy val jSetOfLists2sURI           : AnyRef => AnyRef = jSetOfLists2s[URI](_.asInstanceOf[URI])
  private lazy val jSetOfLists2sByte          : AnyRef => AnyRef = jSetOfLists2s[Byte](_.asInstanceOf[Integer].toByte)
  private lazy val jSetOfLists2sShort         : AnyRef => AnyRef = jSetOfLists2s[Short](_.asInstanceOf[Integer].toShort)
  private lazy val jSetOfLists2sChar          : AnyRef => AnyRef = jSetOfLists2s[Char](_.asInstanceOf[String].charAt(0))

  private def jSetOfLists2s[T](value: Any => T): AnyRef => AnyRef = (v: AnyRef) => {
    var setOfLists = Set.empty[List[T]]
    val listBuf    = ListBuffer.empty[T]
    v.asInstanceOf[jSet[_]].forEach { javaList =>
      listBuf.clear()
      javaList.asInstanceOf[jList[_]].forEach(v => listBuf += value(v))
      setOfLists += listBuf.toList
    }
    setOfLists.asInstanceOf[AnyRef]
  }


  private lazy val vector2listString        : AnyRef => AnyRef = jvector2list
  private lazy val vector2listInt           : AnyRef => AnyRef = jvector2list((v: AnyRef) => v.toString.toInt)
  private lazy val vector2listLong          : AnyRef => AnyRef = jvector2list
  private lazy val vector2listFloat         : AnyRef => AnyRef = jvector2list
  private lazy val vector2listDouble        : AnyRef => AnyRef = jvector2list
  private lazy val vector2listBoolean       : AnyRef => AnyRef = jvector2list
  private lazy val vector2listBigInt        : AnyRef => AnyRef = jvector2list((v: AnyRef) => BigInt(v.toString))
  private lazy val vector2listBigDecimal    : AnyRef => AnyRef = jvector2list((v: AnyRef) => BigDecimal(v.toString))
  private lazy val vector2listDate          : AnyRef => AnyRef = jvector2list
  private lazy val vector2listDuration      : AnyRef => AnyRef = jvector2list((v: AnyRef) => Duration.parse(v.asInstanceOf[String]))
  private lazy val vector2listInstant       : AnyRef => AnyRef = jvector2list((v: AnyRef) => Instant.parse(v.asInstanceOf[String]))
  private lazy val vector2listLocalDate     : AnyRef => AnyRef = jvector2list((v: AnyRef) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val vector2listLocalTime     : AnyRef => AnyRef = jvector2list((v: AnyRef) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val vector2listLocalDateTime : AnyRef => AnyRef = jvector2list((v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2listOffsetTime    : AnyRef => AnyRef = jvector2list((v: AnyRef) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val vector2listOffsetDateTime: AnyRef => AnyRef = jvector2list((v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2listZonedDateTime : AnyRef => AnyRef = jvector2list((v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2listUUID          : AnyRef => AnyRef = jvector2list
  private lazy val vector2listURI           : AnyRef => AnyRef = jvector2list
  private lazy val vector2listByte          : AnyRef => AnyRef = jvector2list((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val vector2listShort         : AnyRef => AnyRef = jvector2list((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val vector2listChar          : AnyRef => AnyRef = jvector2list((v: AnyRef) => v.asInstanceOf[String].charAt(0))

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

  private lazy val vector2setString        : AnyRef => AnyRef = jvector2set
  private lazy val vector2setInt           : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.toString.toInt)
  private lazy val vector2setLong          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setFloat         : AnyRef => AnyRef = jvector2set
  private lazy val vector2setDouble        : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBoolean       : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBigInt        : AnyRef => AnyRef = jvector2set((v: AnyRef) => BigInt(v.toString))
  private lazy val vector2setBigDecimal    : AnyRef => AnyRef = jvector2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val vector2setDate          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setDuration      : AnyRef => AnyRef = jvector2set((v: AnyRef) => Duration.parse(v.asInstanceOf[String]))
  private lazy val vector2setInstant       : AnyRef => AnyRef = jvector2set((v: AnyRef) => Instant.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalDate     : AnyRef => AnyRef = jvector2set((v: AnyRef) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalTime     : AnyRef => AnyRef = jvector2set((v: AnyRef) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalDateTime : AnyRef => AnyRef = jvector2set((v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setOffsetTime    : AnyRef => AnyRef = jvector2set((v: AnyRef) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setOffsetDateTime: AnyRef => AnyRef = jvector2set((v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setZonedDateTime : AnyRef => AnyRef = jvector2set((v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setUUID          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setURI           : AnyRef => AnyRef = jvector2set
  private lazy val vector2setByte          : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val vector2setShort         : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val vector2setChar          : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

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


    //    j2s: AnyRef => AnyRef,
    //    jSet2sSet: AnyRef => AnyRef,
    //    jSet2s: AnyRef => AnyRef,
    //    jSetOfLists2s: AnyRef => AnyRef,
    //    vector2list: AnyRef => AnyRef,
    //    vector2set: AnyRef => AnyRef,

    //    j2sArray: AnyRef => AnyRef
  )

  lazy val resMapString        : ResMap[String]         = ResMap("String", dString, j2sMapString, sPair2jVectorString, s2jString) //, j2sString, set2setString, jSet2sString, jSetOfLists2sString, vector2listString, vector2setString)
  lazy val resMapInt           : ResMap[Int]            = ResMap("Int", dInt, j2sMapInt, sPair2jVectorInt, s2jInt) //, j2sInt, set2setInt, jSet2sInt, jSetOfLists2sInt, vector2listInt, vector2setInt)
  lazy val resMapLong          : ResMap[Long]           = ResMap("Long", dLong, j2sMapLong, sPair2jVectorLong, s2jLong) //, j2sLong, set2setLong, jSet2sLong, jSetOfLists2sLong, vector2listLong, vector2setLong)
  lazy val resMapFloat         : ResMap[Float]          = ResMap("Float", dFloat, j2sMapFloat, sPair2jVectorFloat, s2jFloat) //, j2sFloat, set2setFloat, jSet2sFloat, jSetOfLists2sFloat, vector2listFloat, vector2setFloat)
  lazy val resMapDouble        : ResMap[Double]         = ResMap("Double", dDouble, j2sMapDouble, sPair2jVectorDouble, s2jDouble) //, j2sDouble, set2setDouble, jSet2sDouble, jSetOfLists2sDouble, vector2listDouble, vector2setDouble)
  lazy val resMapBoolean       : ResMap[Boolean]        = ResMap("Boolean", dBoolean, j2sMapBoolean, sPair2jVectorBoolean, s2jBoolean) //, j2sBoolean, set2setBoolean, jSet2sBoolean, jSetOfLists2sBoolean, vector2listBoolean, vector2setBoolean)
  lazy val resMapBigInt        : ResMap[BigInt]         = ResMap("BigInt", dBigInt, j2sMapBigInt, sPair2jVectorBigInt, s2jBigInt) //, j2sBigInt, set2setBigInt, jSet2sBigInt, jSetOfLists2sBigInt, vector2listBigInt, vector2setBigInt)
  lazy val resMapBigDecimal    : ResMap[BigDecimal]     = ResMap("BigDecimal", dBigDecimal, j2sMapBigDecimal, sPair2jVectorBigDecimal, s2jBigDecimal) //, j2sBigDecimal, set2setBigDecimal, jSet2sBigDecimal, jSetOfLists2sBigDecimal, vector2listBigDecimal, vector2setBigDecimal)
  lazy val resMapDate          : ResMap[Date]           = ResMap("Date", dDate, j2sMapDate, sPair2jVectorDate, s2jDate) //, j2sDate, set2setDate, jSet2sDate, jSetOfLists2sDate, vector2listDate, vector2setDate)
  lazy val resMapDuration      : ResMap[Duration]       = ResMap("Duration", dDuration, j2sMapDuration, sPair2jVectorDuration, s2jDuration) //, j2sDuration, set2setDuration, jSet2sDuration, jSetOfLists2sDuration, vector2listDuration, vector2setDuration)
  lazy val resMapInstant       : ResMap[Instant]        = ResMap("Instant", dInstant, j2sMapInstant, sPair2jVectorInstant, s2jInstant) //, j2sInstant, set2setInstant, jSet2sInstant, jSetOfLists2sInstant, vector2listInstant, vector2setInstant)
  lazy val resMapLocalDate     : ResMap[LocalDate]      = ResMap("LocalDate", dLocalDate, j2sMapLocalDate, sPair2jVectorLocalDate, s2jLocalDate) //, j2sLocalDate, set2setLocalDate, jSet2sLocalDate, jSetOfLists2sLocalDate, vector2listLocalDate, vector2setLocalDate)
  lazy val resMapLocalTime     : ResMap[LocalTime]      = ResMap("LocalTime", dLocalTime, j2sMapLocalTime, sPair2jVectorLocalTime, s2jLocalTime) //, j2sLocalTime, set2setLocalTime, jSet2sLocalTime, jSetOfLists2sLocalTime, vector2listLocalTime, vector2setLocalTime)
  lazy val resMapLocalDateTime : ResMap[LocalDateTime]  = ResMap("LocalDateTime", dLocalDateTime, j2sMapLocalDateTime, sPair2jVectorLocalDateTime, s2jLocalDateTime) //, j2sLocalDateTime, set2setLocalDateTime, jSet2sLocalDateTime, jSetOfLists2sLocalDateTime, vector2listLocalDateTime, vector2setLocalDateTime)
  lazy val resMapOffsetTime    : ResMap[OffsetTime]     = ResMap("OffsetTime", dOffsetTime, j2sMapOffsetTime, sPair2jVectorOffsetTime, s2jOffsetTime) //, j2sOffsetTime, set2setOffsetTime, jSet2sOffsetTime, jSetOfLists2sOffsetTime, vector2listOffsetTime, vector2setOffsetTime)
  lazy val resMapOffsetDateTime: ResMap[OffsetDateTime] = ResMap("OffsetDateTime", dOffsetDateTime, j2sMapOffsetDateTime, sPair2jVectorOffsetDateTime, s2jOffsetDateTime) //, j2sOffsetDateTime, set2setOffsetDateTime, jSet2sOffsetDateTime, jSetOfLists2sOffsetDateTime, vector2listOffsetDateTime, vector2setOffsetDateTime)
  lazy val resMapZonedDateTime : ResMap[ZonedDateTime]  = ResMap("ZonedDateTime", dZonedDateTime, j2sMapZonedDateTime, sPair2jVectorZonedDateTime, s2jZonedDateTime) //, j2sZonedDateTime, set2setZonedDateTime, jSet2sZonedDateTime, jSetOfLists2sZonedDateTime, vector2listZonedDateTime, vector2setZonedDateTime)
  lazy val resMapUUID          : ResMap[UUID]           = ResMap("UUID", dUUID, j2sMapUUID, sPair2jVectorUUID, s2jUUID) //, j2sUUID, set2setUUID, jSet2sUUID, jSetOfLists2sUUID, vector2listUUID, vector2setUUID)
  lazy val resMapURI           : ResMap[URI]            = ResMap("URI", dURI, j2sMapURI, sPair2jVectorURI, s2jURI) //, j2sURI, set2setURI, jSet2sURI, jSetOfLists2sURI, vector2listURI, vector2setURI)
  lazy val resMapByte          : ResMap[Byte]           = ResMap("Byte", dByte, j2sMapByte, sPair2jVectorByte, s2jByte) //, j2sByte, set2setByte, jSet2sByte, jSetOfLists2sByte, vector2listByte, vector2setByte)
  lazy val resMapShort         : ResMap[Short]          = ResMap("Short", dShort, j2sMapShort, sPair2jVectorShort, s2jShort) //, j2sShort, set2setShort, jSet2sShort, jSetOfLists2sShort, vector2listShort, vector2setShort)
  lazy val resMapChar          : ResMap[Char]           = ResMap("Char", dChar, j2sMapChar, sPair2jVectorChar, s2jChar) //, j2sChar, set2setChar, jSet2sChar, jSetOfLists2sChar, vector2listChar, vector2setChar)


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


  private lazy val j2sOptListIdOLD = (v: AnyRef) => v match {
    case null            => Option.empty[Array[String]]
    case set: Array[_]   => Some(set.map(_.toString))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: jLong => Some(list.map(_.toString).toArray)
        // Refs
        case _: jMap[_, _] =>
          /*
          // If a ref is owned (:db/isComponent true), Datomic returns all nested values in a pull for an optional
          // ref value. So, the id can hide anywhere in the map entries and we need to extract it.
          // We can't call get(<key>) on the map since it needs a clojure.lang.Keyword that we can use in a shared module
          {:ns/ownedRef {:ns/attr1 6, :ns/attr2 7, :db/id 17592186045419, :ns/attr3 8}}
          -------
          // If the ref is not owned, Datomic only returns the id
          {:ns/ref {:db/id 17592186045422}}
           */
          //          var ids = Array.empty[Long]
          var ids = Array.empty[String]
          list.foreach {
            case m: jMap[_, _] =>
              var continue = true
              val it       = m.entrySet().iterator()
              while (it.hasNext && continue) {
                val pair = it.next()
                if (pair.getKey.toString == ":db/id") {
                  continue = false
                  ids = ids :+ pair.getValue.toString
                }
              }
            case other         => throw new Exception(
              s"Unexpected set values of type ${other.getClass}: " + other
            )
          }
          Some(ids)
      }
  }

  private lazy val j2sOptListLongOLD = (v: AnyRef) => v match {
    case null            => Option.empty[Array[Long]]
    case set: Array[_]   => Some(set.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: jLong => Some(list.map(_.asInstanceOf[Long]).toArray)
        // Refs
        case _: jMap[_, _] =>
          /*
          // If a ref is owned (:db/isComponent true), Datomic returns all nested values in a pull for an optional
          // ref value. So, the id can hide anywhere in the map entries and we need to extract it.
          // We can't call get(<key>) on the map since it needs a clojure.lang.Keyword that we can use in a shared module
          {:ns/ownedRef {:ns/attr1 6, :ns/attr2 7, :db/id 17592186045419, :ns/attr3 8}}
          -------
          // If the ref is not owned, Datomic only returns the id
          {:ns/ref {:db/id 17592186045422}}
           */
          var ids = Array.empty[Long]
          list.foreach {
            case m: jMap[_, _] =>
              var continue = true
              val it       = m.entrySet().iterator()
              while (it.hasNext && continue) {
                val pair = it.next()
                if (pair.getKey.toString == ":db/id") {
                  continue = false
                  ids = ids :+ pair.getValue.asInstanceOf[Long]
                }
              }
            case other         => throw new Exception(
              s"Unexpected set values of type ${other.getClass}: " + other
            )
          }
          Some(ids)
      }
  }


  private def optSeq2sID = (v: AnyRef) => {
    val set = v.asInstanceOf[Array[_]]
    if (set.iterator.next().asInstanceOf[jList[_]].isEmpty)
      Option.empty[Array[String]]
    else
      Some(
        set.flatMap(
          _.asInstanceOf[jList[_]].asScala.map(
            _.asInstanceOf[jMap[_, _]].values.iterator.next.toString
          )
        ).toArray
      )
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

  private def optByteAttr2s = (v: AnyRef) => {
    if (v == null) {
      Option.empty[Array[Byte]]
    } else {
      //      val array = v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Array[Byte]]
      val array = v match {
        case map: jMap[_, _] => map.values.iterator.next.asInstanceOf[Array[Byte]]
        case arr: Array[_]   => arr.asInstanceOf[Array[Byte]]
      }
      if (array.isEmpty) Option.empty[Array[Byte]] else Some(array)
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


  case class ResMapOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2optMap: AnyRef => AnyRef,
  )

  lazy val resOptMapString        : ResMapOpt[String]         = ResMapOpt("String", dString, s2jString, j2optMapString)
  lazy val resOptMapInt           : ResMapOpt[Int]            = ResMapOpt("Int", dInt, s2jInt, j2optMapInt)
  lazy val resOptMapLong          : ResMapOpt[Long]           = ResMapOpt("Long", dLong, s2jLong, j2optMapLong)
  lazy val resOptMapFloat         : ResMapOpt[Float]          = ResMapOpt("Float", dFloat, s2jFloat, j2optMapFloat)
  lazy val resOptMapDouble        : ResMapOpt[Double]         = ResMapOpt("Double", dDouble, s2jDouble, j2optMapDouble)
  lazy val resOptMapBoolean       : ResMapOpt[Boolean]        = ResMapOpt("Boolean", dBoolean, s2jBoolean, j2optMapBoolean)
  lazy val resOptMapBigInt        : ResMapOpt[BigInt]         = ResMapOpt("BigInt", dBigInt, s2jBigInt, j2optMapBigInt)
  lazy val resOptMapBigDecimal    : ResMapOpt[BigDecimal]     = ResMapOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2optMapBigDecimal)
  lazy val resOptMapDate          : ResMapOpt[Date]           = ResMapOpt("Date", dDate, s2jDate, j2optMapDate)
  lazy val resOptMapDuration      : ResMapOpt[Duration]       = ResMapOpt("Duration", dDuration, s2jDuration, j2optMapDuration)
  lazy val resOptMapInstant       : ResMapOpt[Instant]        = ResMapOpt("Instant", dInstant, s2jInstant, j2optMapInstant)
  lazy val resOptMapLocalDate     : ResMapOpt[LocalDate]      = ResMapOpt("LocalDate", dLocalDate, s2jLocalDate, j2optMapLocalDate)
  lazy val resOptMapLocalTime     : ResMapOpt[LocalTime]      = ResMapOpt("LocalTime", dLocalTime, s2jLocalTime, j2optMapLocalTime)
  lazy val resOptMapLocalDateTime : ResMapOpt[LocalDateTime]  = ResMapOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2optMapLocalDateTime)
  lazy val resOptMapOffsetTime    : ResMapOpt[OffsetTime]     = ResMapOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2optMapOffsetTime)
  lazy val resOptMapOffsetDateTime: ResMapOpt[OffsetDateTime] = ResMapOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2optMapOffsetDateTime)
  lazy val resOptMapZonedDateTime : ResMapOpt[ZonedDateTime]  = ResMapOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2optMapZonedDateTime)
  lazy val resOptMapUUID          : ResMapOpt[UUID]           = ResMapOpt("UUID", dUUID, s2jUUID, j2optMapUUID)
  lazy val resOptMapURI           : ResMapOpt[URI]            = ResMapOpt("URI", dURI, s2jURI, j2optMapURI)
  lazy val resOptMapByte          : ResMapOpt[Byte]           = ResMapOpt("Byte", dByte, s2jByte, j2optMapByte)
  lazy val resOptMapShort         : ResMapOpt[Short]          = ResMapOpt("Short", dShort, s2jShort, j2optMapShort)
  lazy val resOptMapChar          : ResMapOpt[Char]           = ResMapOpt("Char", dChar, s2jChar, j2optMapChar)


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