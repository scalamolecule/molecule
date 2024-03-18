package molecule.datalog.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait LambdasSet extends ResolveBase with JavaConversions {

  private lazy val set2setId            : AnyRef => AnyRef = jset2set(j2sId)
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

  private def jset2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  private lazy val set2setsId            : AnyRef => AnyRef = jset2setsT[String](_.asInstanceOf[String])
  private lazy val set2setsString        : AnyRef => AnyRef = jset2setsT[String](_.asInstanceOf[String])
  private lazy val set2setsInt           : AnyRef => AnyRef = jset2setsT[Int](_.toString.toInt)
  private lazy val set2setsLong          : AnyRef => AnyRef = jset2setsT[Long](_.asInstanceOf[Long])
  private lazy val set2setsFloat         : AnyRef => AnyRef = jset2setsT[Float](_.asInstanceOf[Float])
  private lazy val set2setsDouble        : AnyRef => AnyRef = jset2setsT[Double](_.asInstanceOf[Double])
  private lazy val set2setsBoolean       : AnyRef => AnyRef = jset2setsT[Boolean](_.asInstanceOf[Boolean])
  private lazy val set2setsBigInt        : AnyRef => AnyRef = jset2setsT[BigInt]((v: Any) => BigInt(v.toString))
  private lazy val set2setsBigDecimal    : AnyRef => AnyRef = jset2setsT[BigDecimal]((v: Any) => BigDecimal(v.toString))
  private lazy val set2setsDate          : AnyRef => AnyRef = jset2setsT[Date](_.asInstanceOf[Date])
  private lazy val set2setsDuration      : AnyRef => AnyRef = jset2setsT[Duration]((v: Any) => Duration.parse(v.asInstanceOf[String]))
  private lazy val set2setsInstant       : AnyRef => AnyRef = jset2setsT[Instant]((v: Any) => Instant.parse(v.asInstanceOf[String]))
  private lazy val set2setsLocalDate     : AnyRef => AnyRef = jset2setsT[LocalDate]((v: Any) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val set2setsLocalTime     : AnyRef => AnyRef = jset2setsT[LocalTime]((v: Any) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val set2setsLocalDateTime : AnyRef => AnyRef = jset2setsT[LocalDateTime]((v: Any) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setsOffsetTime    : AnyRef => AnyRef = jset2setsT[OffsetTime]((v: Any) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val set2setsOffsetDateTime: AnyRef => AnyRef = jset2setsT[OffsetDateTime]((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setsZonedDateTime : AnyRef => AnyRef = jset2setsT[ZonedDateTime]((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setsUUID          : AnyRef => AnyRef = jset2setsT[UUID](_.asInstanceOf[UUID])
  private lazy val set2setsURI           : AnyRef => AnyRef = jset2setsT[URI](_.asInstanceOf[URI])
  private lazy val set2setsByte          : AnyRef => AnyRef = jset2setsT[Byte](_.asInstanceOf[Integer].toByte)
  private lazy val set2setsShort         : AnyRef => AnyRef = jset2setsT[Short](_.asInstanceOf[Integer].toShort)
  private lazy val set2setsChar          : AnyRef => AnyRef = jset2setsT[Char](_.asInstanceOf[String].charAt(0))

  private def jset2setsT[T](value: Any => T): AnyRef => AnyRef = (v: AnyRef) => {
    var sets = Set.empty[Set[T]]
    var set  = Set.empty[T]
    v.asInstanceOf[jSet[_]].asScala.foreach { row =>
      set = Set.empty[T]
      row.asInstanceOf[jSet[_]].asScala.foreach(v => set = set + value(v))
      sets += set
    }
    sets.asInstanceOf[AnyRef]
  }


  private lazy val vector2setId            : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.toString)
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


  case class ResSet[T](
    tpe: String,
    toDatalog: T => String,
    j2s: AnyRef => AnyRef,
    jSet2s: AnyRef => AnyRef,
    s2j: Any => Any,
    j2sSet: AnyRef => AnyRef,
    set2sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
//    j2sSetOne: AnyRef => AnyRef
  )

  lazy val resSetId            : ResSet[String]         = ResSet("String", dId, j2sId, jSet2sId, s2jId, set2setId, set2setsId, vector2setId)
  lazy val resSetString        : ResSet[String]         = ResSet("String", dString, j2sString, jSet2sString, s2jString, set2setString, set2setsString, vector2setString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", dInt, j2sInt, jSet2sInt, s2jInt, set2setInt, set2setsInt, vector2setInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", dLong, j2sLong, jSet2sLong, s2jLong, set2setLong, set2setsLong, vector2setLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", dFloat, j2sFloat, jSet2sFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", dDouble, j2sDouble, jSet2sDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", dBoolean, j2sBoolean, jSet2sBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", dBigInt, j2sBigInt, jSet2sBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", dBigDecimal, j2sBigDecimal, jSet2sBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", dDate, j2sDate, jSet2sDate, s2jDate, set2setDate, set2setsDate, vector2setDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", dDuration, j2sDuration, jSet2sDuration, s2jDuration, set2setDuration, set2setsDuration, vector2setDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", dInstant, j2sInstant, jSet2sInstant, s2jInstant, set2setInstant, set2setsInstant, vector2setInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", dLocalDate, j2sLocalDate, jSet2sLocalDate, s2jLocalDate, set2setLocalDate, set2setsLocalDate, vector2setLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", dLocalTime, j2sLocalTime, jSet2sLocalTime, s2jLocalTime, set2setLocalTime, set2setsLocalTime, vector2setLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", dLocalDateTime, j2sLocalDateTime, jSet2sLocalDateTime, s2jLocalDateTime, set2setLocalDateTime, set2setsLocalDateTime, vector2setLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", dOffsetTime, j2sOffsetTime, jSet2sOffsetTime, s2jOffsetTime, set2setOffsetTime, set2setsOffsetTime, vector2setOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", dOffsetDateTime, j2sOffsetDateTime, jSet2sOffsetDateTime, s2jOffsetDateTime, set2setOffsetDateTime, set2setsOffsetDateTime, vector2setOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", dZonedDateTime, j2sZonedDateTime, jSet2sZonedDateTime, s2jZonedDateTime, set2setZonedDateTime, set2setsZonedDateTime, vector2setZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", dUUID, j2sUUID, jSet2sUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", dURI, j2sURI, jSet2sURI, s2jURI, set2setURI, set2setsURI, vector2setURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", dByte, j2sByte, jSet2sByte, s2jByte, set2setByte, set2setsByte, vector2setByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", dShort, j2sShort, jSet2sShort, s2jShort, set2setShort, set2setsShort, vector2setShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", dChar, j2sChar, jSet2sChar, s2jChar, set2setChar, set2setsChar, vector2setChar)


  lazy val any2double: AnyRef => AnyRef = {
    ((v: AnyRef) => v.toString.toDouble).asInstanceOf[AnyRef => AnyRef]
  }

  private lazy val j2sOpSet_Id = (v: AnyRef) => v match {
    case null            => Option.empty[Set[String]]
    case set: jSet[_]    => Some(set.asScala.map(_.toString))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: jLong => Some(list.map(_.toString).toSet)
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
          //          var ids = Set.empty[Long]
          var ids = Set.empty[String]
          list.foreach {
            case m: jMap[_, _] =>
              var continue = true
              val it       = m.entrySet().iterator()
              while (it.hasNext && continue) {
                val pair = it.next()
                if (pair.getKey.toString == ":db/id") {
                  continue = false
                  ids = ids + pair.getValue.toString
                }
              }
            case other         => throw new Exception(
              s"Unexpected set values of type ${other.getClass}: " + other
            )
          }
          Some(ids)
      }
  }

  private lazy val j2sOpSet_Long = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Long]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: jLong => Some(list.map(_.asInstanceOf[Long]).toSet)
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
          var ids = Set.empty[Long]
          list.foreach {
            case m: jMap[_, _] =>
              var continue = true
              val it       = m.entrySet().iterator()
              while (it.hasNext && continue) {
                val pair = it.next()
                if (pair.getKey.toString == ":db/id") {
                  continue = false
                  ids = ids + pair.getValue.asInstanceOf[Long]
                }
              }
            case other         => throw new Exception(
              s"Unexpected set values of type ${other.getClass}: " + other
            )
          }
          Some(ids)
      }
  }


  private def j2sOpSet[T](decode: Any => T) = (v: AnyRef) => {
    v match {
      case null            => Option.empty[Set[String]]
      case set: jSet[_]    => Some(set.asScala.map(decode)) // attr_?(<expr>))
      case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(decode).toSet) // attr_?
    }
  }

  private lazy val j2sOpSetId             = j2sOpSet_Id
  private lazy val j2sOpSetString         = j2sOpSet(j2String)
  private lazy val j2sOpSetInt            = j2sOpSet(j2Int)
  private lazy val j2sOpSetLong           = j2sOpSet_Long
  private lazy val j2sOpSetFloat          = j2sOpSet(j2Float)
  private lazy val j2sOpSetDouble         = j2sOpSet(j2Double)
  private lazy val j2sOpSetBoolean        = j2sOpSet(j2Boolean)
  private lazy val j2sOpSetBigInt         = j2sOpSet(j2BigInt)
  private lazy val j2sOpSetBigDecimal     = j2sOpSet(j2BigDecimal)
  private lazy val j2sOpSetDate           = j2sOpSet(j2Date)
  private lazy val j2sOpSetDuration       = j2sOpSet(j2Duration)
  private lazy val j2sOpSetInstant        = j2sOpSet(j2Instant)
  private lazy val j2sOpSetLocalDate      = j2sOpSet(j2LocalDate)
  private lazy val j2sOpSetLocalTime      = j2sOpSet(j2LocalTime)
  private lazy val j2sOpSetLocalDateTime  = j2sOpSet(j2LocalDateTime)
  private lazy val j2sOpSetOffsetTime     = j2sOpSet(j2OffsetTime)
  private lazy val j2sOpSetOffsetDateTime = j2sOpSet(j2OffsetDateTime)
  private lazy val j2sOpSetZonedDateTime  = j2sOpSet(j2ZonedDateTime)
  private lazy val j2sOpSetUUID           = j2sOpSet(j2UUID)
  private lazy val j2sOpSetURI            = j2sOpSet(j2URI)
  private lazy val j2sOpSetByte           = j2sOpSet(j2Byte)
  private lazy val j2sOpSetShort          = j2sOpSet(j2Short)
  private lazy val j2sOpSetChar           = j2sOpSet(j2Char)


  private def optAttr2sOptSetID = (v: AnyRef) => {
    val set = v.asInstanceOf[jSet[_]]
    if (set.iterator.next.asInstanceOf[jList[_]].isEmpty)
      Option.empty[Set[String]]
    else
      Some(
        set.asScala.flatMap(
          _.asInstanceOf[jList[_]].asScala.map(
            _.asInstanceOf[jMap[_, _]].values.iterator.next.toString
          )
        ).toSet
      )
  }

  private def optAttr2sOptSet[T](decode: Any => T) = (v: AnyRef) => {
    val set = v.asInstanceOf[jSet[_]].asScala.flatMap(_.asInstanceOf[jList[_]].asScala.map(decode)).toSet
    if (set.isEmpty) Option.empty[Set[T]] else Some(set)
  }

  private lazy val jOptSetAttr2sOptSetId             = optAttr2sOptSetID
  private lazy val jOptSetAttr2sOptSetString         = optAttr2sOptSet(j2String)
  private lazy val jOptSetAttr2sOptSetInt            = optAttr2sOptSet(j2Int)
  private lazy val jOptSetAttr2sOptSetLong           = optAttr2sOptSet(j2Long)
  private lazy val jOptSetAttr2sOptSetFloat          = optAttr2sOptSet(j2Float)
  private lazy val jOptSetAttr2sOptSetDouble         = optAttr2sOptSet(j2Double)
  private lazy val jOptSetAttr2sOptSetBoolean        = optAttr2sOptSet(j2Boolean)
  private lazy val jOptSetAttr2sOptSetBigInt         = optAttr2sOptSet(j2BigInt)
  private lazy val jOptSetAttr2sOptSetBigDecimal     = optAttr2sOptSet(j2BigDecimal)
  private lazy val jOptSetAttr2sOptSetDate           = optAttr2sOptSet(j2Date)
  private lazy val jOptSetAttr2sOptSetDuration       = optAttr2sOptSet(j2Duration)
  private lazy val jOptSetAttr2sOptSetInstant        = optAttr2sOptSet(j2Instant)
  private lazy val jOptSetAttr2sOptSetLocalDate      = optAttr2sOptSet(j2LocalDate)
  private lazy val jOptSetAttr2sOptSetLocalTime      = optAttr2sOptSet(j2LocalTime)
  private lazy val jOptSetAttr2sOptSetLocalDateTime  = optAttr2sOptSet(j2LocalDateTime)
  private lazy val jOptSetAttr2sOptSetOffsetTime     = optAttr2sOptSet(j2OffsetTime)
  private lazy val jOptSetAttr2sOptSetOffsetDateTime = optAttr2sOptSet(j2OffsetDateTime)
  private lazy val jOptSetAttr2sOptSetZonedDateTime  = optAttr2sOptSet(j2ZonedDateTime)
  private lazy val jOptSetAttr2sOptSetUUID           = optAttr2sOptSet(j2UUID)
  private lazy val jOptSetAttr2sOptSetURI            = optAttr2sOptSet(j2URI)
  private lazy val jOptSetAttr2sOptSetByte           = optAttr2sOptSet(j2Byte)
  private lazy val jOptSetAttr2sOptSetShort          = optAttr2sOptSet(j2Short)
  private lazy val jOptSetAttr2sOptSetChar           = optAttr2sOptSet(j2Char)


  case class ResSetOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    optAttr2s: AnyRef => AnyRef,
  )

  lazy val resOptSetId            : ResSetOpt[String]         = ResSetOpt("String", dId, s2jId, j2sOpSetId, jOptSetAttr2sOptSetId)
  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt("String", dString, s2jString, j2sOpSetString, jOptSetAttr2sOptSetString)
  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt("Int", dInt, s2jInt, j2sOpSetInt, jOptSetAttr2sOptSetInt)
  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt("Long", dLong, s2jLong, j2sOpSetLong, jOptSetAttr2sOptSetLong)
  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt("Float", dFloat, s2jFloat, j2sOpSetFloat, jOptSetAttr2sOptSetFloat)
  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt("Double", dDouble, s2jDouble, j2sOpSetDouble, jOptSetAttr2sOptSetDouble)
  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt("Boolean", dBoolean, s2jBoolean, j2sOpSetBoolean, jOptSetAttr2sOptSetBoolean)
  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt("BigInt", dBigInt, s2jBigInt, j2sOpSetBigInt, jOptSetAttr2sOptSetBigInt)
  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOpSetBigDecimal, jOptSetAttr2sOptSetBigDecimal)
  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt("Date", dDate, s2jDate, j2sOpSetDate, jOptSetAttr2sOptSetDate)
  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt("Duration", dDuration, s2jDuration, j2sOpSetDuration, jOptSetAttr2sOptSetDuration)
  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt("Instant", dInstant, s2jInstant, j2sOpSetInstant, jOptSetAttr2sOptSetInstant)
  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt("LocalDate", dLocalDate, s2jLocalDate, j2sOpSetLocalDate, jOptSetAttr2sOptSetLocalDate)
  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt("LocalTime", dLocalTime, s2jLocalTime, j2sOpSetLocalTime, jOptSetAttr2sOptSetLocalTime)
  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sOpSetLocalDateTime, jOptSetAttr2sOptSetLocalDateTime)
  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOpSetOffsetTime, jOptSetAttr2sOptSetOffsetTime)
  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOpSetOffsetDateTime, jOptSetAttr2sOptSetOffsetDateTime)
  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sOpSetZonedDateTime, jOptSetAttr2sOptSetZonedDateTime)
  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt("UUID", dUUID, s2jUUID, j2sOpSetUUID, jOptSetAttr2sOptSetUUID)
  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt("URI", dURI, s2jURI, j2sOpSetURI, jOptSetAttr2sOptSetURI)
  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt("Byte", dByte, s2jByte, j2sOpSetByte, jOptSetAttr2sOptSetByte)
  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt("Short", dShort, s2jShort, j2sOpSetShort, jOptSetAttr2sOptSetShort)
  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt("Char", dChar, s2jChar, j2sOpSetChar, jOptSetAttr2sOptSetChar)


  // Nested opt ---------------------------------------------------------------------

  private def it2Set[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(decode).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }

  lazy val it2SetId            : jIterator[_] => Any = it2Set(j2Id)
  lazy val it2SetString        : jIterator[_] => Any = it2Set(j2String)
  lazy val it2SetInt           : jIterator[_] => Any = it2Set(j2Int)
  lazy val it2SetLong          : jIterator[_] => Any = it2Set(j2Long)
  lazy val it2SetFloat         : jIterator[_] => Any = it2Set(j2Float)
  lazy val it2SetDouble        : jIterator[_] => Any = it2Set(j2Double)
  lazy val it2SetBoolean       : jIterator[_] => Any = it2Set(j2Boolean)
  lazy val it2SetBigInt        : jIterator[_] => Any = it2Set(j2BigInt)
  lazy val it2SetBigDecimal    : jIterator[_] => Any = it2Set(j2BigDecimal)
  lazy val it2SetDate          : jIterator[_] => Any = it2Set(j2Date)
  lazy val it2SetDuration      : jIterator[_] => Any = it2Set(j2Duration)
  lazy val it2SetInstant       : jIterator[_] => Any = it2Set(j2Instant)
  lazy val it2SetLocalDate     : jIterator[_] => Any = it2Set(j2LocalDate)
  lazy val it2SetLocalTime     : jIterator[_] => Any = it2Set(j2LocalTime)
  lazy val it2SetLocalDateTime : jIterator[_] => Any = it2Set(j2LocalDateTime)
  lazy val it2SetOffsetTime    : jIterator[_] => Any = it2Set(j2OffsetTime)
  lazy val it2SetOffsetDateTime: jIterator[_] => Any = it2Set(j2OffsetDateTime)
  lazy val it2SetZonedDateTime : jIterator[_] => Any = it2Set(j2ZonedDateTime)
  lazy val it2SetUUID          : jIterator[_] => Any = it2Set(j2UUID)
  lazy val it2SetURI           : jIterator[_] => Any = it2Set(j2URI)
  lazy val it2SetByte          : jIterator[_] => Any = it2Set(j2Byte)
  lazy val it2SetShort         : jIterator[_] => Any = it2Set(j2Short)
  lazy val it2SetChar          : jIterator[_] => Any = it2Set(j2Char)


  private def it2OptSet[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(decode).toSet)
  }

  lazy val it2OptSetId            : jIterator[_] => Any = it2OptSet(j2Id)
  lazy val it2OptSetString        : jIterator[_] => Any = it2OptSet(j2String)
  lazy val it2OptSetInt           : jIterator[_] => Any = it2OptSet(j2Int)
  lazy val it2OptSetLong          : jIterator[_] => Any = it2OptSet(j2Long)
  lazy val it2OptSetFloat         : jIterator[_] => Any = it2OptSet(j2Float)
  lazy val it2OptSetDouble        : jIterator[_] => Any = it2OptSet(j2Double)
  lazy val it2OptSetBoolean       : jIterator[_] => Any = it2OptSet(j2Boolean)
  lazy val it2OptSetBigInt        : jIterator[_] => Any = it2OptSet(j2BigInt)
  lazy val it2OptSetBigDecimal    : jIterator[_] => Any = it2OptSet(j2BigDecimal)
  lazy val it2OptSetDate          : jIterator[_] => Any = it2OptSet(j2Date)
  lazy val it2OptSetDuration      : jIterator[_] => Any = it2OptSet(j2Duration)
  lazy val it2OptSetInstant       : jIterator[_] => Any = it2OptSet(j2Instant)
  lazy val it2OptSetLocalDate     : jIterator[_] => Any = it2OptSet(j2LocalDate)
  lazy val it2OptSetLocalTime     : jIterator[_] => Any = it2OptSet(j2LocalTime)
  lazy val it2OptSetLocalDateTime : jIterator[_] => Any = it2OptSet(j2LocalDateTime)
  lazy val it2OptSetOffsetTime    : jIterator[_] => Any = it2OptSet(j2OffsetTime)
  lazy val it2OptSetOffsetDateTime: jIterator[_] => Any = it2OptSet(j2OffsetDateTime)
  lazy val it2OptSetZonedDateTime : jIterator[_] => Any = it2OptSet(j2ZonedDateTime)
  lazy val it2OptSetUUID          : jIterator[_] => Any = it2OptSet(j2UUID)
  lazy val it2OptSetURI           : jIterator[_] => Any = it2OptSet(j2URI)
  lazy val it2OptSetByte          : jIterator[_] => Any = it2OptSet(j2Byte)
  lazy val it2OptSetShort         : jIterator[_] => Any = it2OptSet(j2Short)
  lazy val it2OptSetChar          : jIterator[_] => Any = it2OptSet(j2Char)
}