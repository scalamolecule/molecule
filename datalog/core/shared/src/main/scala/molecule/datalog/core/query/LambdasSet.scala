package molecule.datalog.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait LambdasSet extends ResolveBase with JavaConversions {

  private lazy val j2sSetId            : AnyRef => AnyRef = jset2set(j2sId)
  private lazy val j2sSetString        : AnyRef => AnyRef = jset2set(j2sString)
  private lazy val j2sSetInt           : AnyRef => AnyRef = jset2set(j2sInt)
  private lazy val j2sSetLong          : AnyRef => AnyRef = jset2set(j2sLong)
  private lazy val j2sSetFloat         : AnyRef => AnyRef = jset2set(j2sFloat)
  private lazy val j2sSetDouble        : AnyRef => AnyRef = jset2set(j2sDouble)
  private lazy val j2sSetBoolean       : AnyRef => AnyRef = jset2set(j2sBoolean)
  private lazy val j2sSetBigInt        : AnyRef => AnyRef = jset2set(j2sBigInt)
  private lazy val j2sSetBigDecimal    : AnyRef => AnyRef = jset2set(j2sBigDecimal)
  private lazy val j2sSetDate          : AnyRef => AnyRef = jset2set(j2sDate)
  private lazy val j2sSetDuration      : AnyRef => AnyRef = jset2set(j2sDuration)
  private lazy val j2sSetInstant       : AnyRef => AnyRef = jset2set(j2sInstant)
  private lazy val j2sSetLocalDate     : AnyRef => AnyRef = jset2set(j2sLocalDate)
  private lazy val j2sSetLocalTime     : AnyRef => AnyRef = jset2set(j2sLocalTime)
  private lazy val j2sSetLocalDateTime : AnyRef => AnyRef = jset2set(j2sLocalDateTime)
  private lazy val j2sSetOffsetTime    : AnyRef => AnyRef = jset2set(j2sOffsetTime)
  private lazy val j2sSetOffsetDateTime: AnyRef => AnyRef = jset2set(j2sOffsetDateTime)
  private lazy val j2sSetZonedDateTime : AnyRef => AnyRef = jset2set(j2sZonedDateTime)
  private lazy val j2sSetUUID          : AnyRef => AnyRef = jset2set(j2sUUID)
  private lazy val j2sSetURI           : AnyRef => AnyRef = jset2set(j2sURI)
  private lazy val j2sSetByte          : AnyRef => AnyRef = jset2set(j2sByte)
  private lazy val j2sSetShort         : AnyRef => AnyRef = jset2set(j2sShort)
  private lazy val j2sSetChar          : AnyRef => AnyRef = jset2set(j2sChar)

  private def jset2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  case class ResSet[T](
    tpe: String,
    toDatalog: T => String,
    j2sSet: AnyRef => AnyRef,
  )

  lazy val resSetId            : ResSet[String]         = ResSet("String", dId, j2sSetId)
  lazy val resSetString        : ResSet[String]         = ResSet("String", dString, j2sSetString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", dInt, j2sSetInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", dLong, j2sSetLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", dFloat, j2sSetFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", dDouble, j2sSetDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", dBoolean, j2sSetBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", dBigInt, j2sSetBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", dBigDecimal, j2sSetBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", dDate, j2sSetDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", dDuration, j2sSetDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", dInstant, j2sSetInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", dLocalDate, j2sSetLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", dLocalTime, j2sSetLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", dLocalDateTime, j2sSetLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", dOffsetTime, j2sSetOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", dOffsetDateTime, j2sSetOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", dZonedDateTime, j2sSetZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", dUUID, j2sSetUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", dURI, j2sSetURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", dByte, j2sSetByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", dShort, j2sSetShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", dChar, j2sSetChar)


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

  private lazy val j2sOptSetId             = j2sOpSet_Id
  private lazy val j2sOptSetString         = j2sOpSet(j2String)
  private lazy val j2sOptSetInt            = j2sOpSet(j2Int)
  private lazy val j2sOptSetLong           = j2sOpSet_Long
  private lazy val j2sOptSetFloat          = j2sOpSet(j2Float)
  private lazy val j2sOptSetDouble         = j2sOpSet(j2Double)
  private lazy val j2sOptSetBoolean        = j2sOpSet(j2Boolean)
  private lazy val j2sOptSetBigInt         = j2sOpSet(j2BigInt)
  private lazy val j2sOptSetBigDecimal     = j2sOpSet(j2BigDecimal)
  private lazy val j2sOptSetDate           = j2sOpSet(j2Date)
  private lazy val j2sOptSetDuration       = j2sOpSet(j2Duration)
  private lazy val j2sOptSetInstant        = j2sOpSet(j2Instant)
  private lazy val j2sOptSetLocalDate      = j2sOpSet(j2LocalDate)
  private lazy val j2sOptSetLocalTime      = j2sOpSet(j2LocalTime)
  private lazy val j2sOptSetLocalDateTime  = j2sOpSet(j2LocalDateTime)
  private lazy val j2sOptSetOffsetTime     = j2sOpSet(j2OffsetTime)
  private lazy val j2sOptSetOffsetDateTime = j2sOpSet(j2OffsetDateTime)
  private lazy val j2sOptSetZonedDateTime  = j2sOpSet(j2ZonedDateTime)
  private lazy val j2sOptSetUUID           = j2sOpSet(j2UUID)
  private lazy val j2sOptSetURI            = j2sOpSet(j2URI)
  private lazy val j2sOptSetByte           = j2sOpSet(j2Byte)
  private lazy val j2sOptSetShort          = j2sOpSet(j2Short)
  private lazy val j2sOptSetChar           = j2sOpSet(j2Char)


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
    j2sOptSet: AnyRef => AnyRef,
    optAttr2s: AnyRef => AnyRef,
  )

  lazy val resOptSetId            : ResSetOpt[String]         = ResSetOpt("String", dId, j2sOptSetId, jOptSetAttr2sOptSetId)
  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt("String", dString, j2sOptSetString, jOptSetAttr2sOptSetString)
  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt("Int", dInt, j2sOptSetInt, jOptSetAttr2sOptSetInt)
  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt("Long", dLong, j2sOptSetLong, jOptSetAttr2sOptSetLong)
  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt("Float", dFloat, j2sOptSetFloat, jOptSetAttr2sOptSetFloat)
  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt("Double", dDouble, j2sOptSetDouble, jOptSetAttr2sOptSetDouble)
  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt("Boolean", dBoolean, j2sOptSetBoolean, jOptSetAttr2sOptSetBoolean)
  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt("BigInt", dBigInt, j2sOptSetBigInt, jOptSetAttr2sOptSetBigInt)
  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt("BigDecimal", dBigDecimal, j2sOptSetBigDecimal, jOptSetAttr2sOptSetBigDecimal)
  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt("Date", dDate, j2sOptSetDate, jOptSetAttr2sOptSetDate)
  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt("Duration", dDuration, j2sOptSetDuration, jOptSetAttr2sOptSetDuration)
  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt("Instant", dInstant, j2sOptSetInstant, jOptSetAttr2sOptSetInstant)
  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt("LocalDate", dLocalDate, j2sOptSetLocalDate, jOptSetAttr2sOptSetLocalDate)
  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt("LocalTime", dLocalTime, j2sOptSetLocalTime, jOptSetAttr2sOptSetLocalTime)
  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt("LocalDateTime", dLocalDateTime, j2sOptSetLocalDateTime, jOptSetAttr2sOptSetLocalDateTime)
  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt("OffsetTime", dOffsetTime, j2sOptSetOffsetTime, jOptSetAttr2sOptSetOffsetTime)
  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt("OffsetDateTime", dOffsetDateTime, j2sOptSetOffsetDateTime, jOptSetAttr2sOptSetOffsetDateTime)
  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt("ZonedDateTime", dZonedDateTime, j2sOptSetZonedDateTime, jOptSetAttr2sOptSetZonedDateTime)
  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt("UUID", dUUID, j2sOptSetUUID, jOptSetAttr2sOptSetUUID)
  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt("URI", dURI, j2sOptSetURI, jOptSetAttr2sOptSetURI)
  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt("Byte", dByte, j2sOptSetByte, jOptSetAttr2sOptSetByte)
  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt("Short", dShort, j2sOptSetShort, jOptSetAttr2sOptSetShort)
  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt("Char", dChar, j2sOptSetChar, jOptSetAttr2sOptSetChar)


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