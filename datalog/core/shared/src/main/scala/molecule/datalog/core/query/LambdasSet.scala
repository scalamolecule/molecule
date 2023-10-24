package molecule.datalog.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait LambdasSet extends ResolveBase with JavaConversions {

  protected lazy val j2sSetId            : AnyRef => AnyRef = (v: AnyRef) => Set(v.toString)
  protected lazy val j2sSetString        : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  // Datomic can return both Integer or Long
  protected lazy val j2sSetInt           : AnyRef => AnyRef = (v: AnyRef) => Set(v.toString.toInt)
  protected lazy val j2sSetLong          : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetFloat         : AnyRef => AnyRef = {
    case v: jFloat  => Set(v.asInstanceOf[AnyRef])
    case v: jDouble => Set(v.toFloat.asInstanceOf[AnyRef])
  }
  protected lazy val j2sSetDouble        : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBoolean       : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBigInt        : AnyRef => AnyRef = {
    case v: jBigInt => Set(BigInt(v))
    case v          => Set(BigInt(v.toString))
  }
  protected lazy val j2sSetBigDecimal    : AnyRef => AnyRef =
    (v: AnyRef) => Set(BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val j2sSetDate          : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetDuration      : AnyRef => AnyRef = (v: AnyRef) => Set(Duration.parse(v.toString))
  protected lazy val j2sSetInstant       : AnyRef => AnyRef = (v: AnyRef) => Set(Instant.parse(v.toString))
  protected lazy val j2sSetLocalDate     : AnyRef => AnyRef = (v: AnyRef) => Set(LocalDate.parse(v.toString))
  protected lazy val j2sSetLocalTime     : AnyRef => AnyRef = (v: AnyRef) => Set(LocalTime.parse(v.toString))
  protected lazy val j2sSetLocalDateTime : AnyRef => AnyRef = (v: AnyRef) => Set(LocalDateTime.parse(v.toString))
  protected lazy val j2sSetOffsetTime    : AnyRef => AnyRef = (v: AnyRef) => Set(OffsetTime.parse(v.toString))
  protected lazy val j2sSetOffsetDateTime: AnyRef => AnyRef = (v: AnyRef) => Set(OffsetDateTime.parse(v.toString))
  protected lazy val j2sSetZonedDateTime : AnyRef => AnyRef = (v: AnyRef) => Set(ZonedDateTime.parse(v.toString))
  protected lazy val j2sSetUUID          : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetURI           : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetByte          : AnyRef => AnyRef = {
    case v: Integer => Set(v.toByte)
    case v: jLong   => Set(v.toByte)
  }
  protected lazy val j2sSetShort         : AnyRef => AnyRef = {
    case v: Integer => Set(v.toShort)
    case v: jLong   => Set(v.toShort)
  }
  protected lazy val j2sSetChar          : AnyRef => AnyRef =
    (v: AnyRef) => Set(v.asInstanceOf[String].charAt(0))

  private lazy val set2setId            : AnyRef => AnyRef = jset2set((v: AnyRef) => v.toString)
  private lazy val set2setString        : AnyRef => AnyRef = jset2set
  private lazy val set2setInt           : AnyRef => AnyRef = jset2set((v: AnyRef) => v.toString.toInt)
  private lazy val set2setLong          : AnyRef => AnyRef = jset2set
  private lazy val set2setFloat         : AnyRef => AnyRef = jset2set
  private lazy val set2setDouble        : AnyRef => AnyRef = jset2set
  private lazy val set2setBoolean       : AnyRef => AnyRef = jset2set
  private lazy val set2setBigInt        : AnyRef => AnyRef = jset2set((v: AnyRef) => BigInt(v.toString))
  private lazy val set2setBigDecimal    : AnyRef => AnyRef = jset2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val set2setDate          : AnyRef => AnyRef = jset2set
  private lazy val set2setDuration      : AnyRef => AnyRef = jset2set((v: AnyRef) => Duration.parse(v.asInstanceOf[String]))
  private lazy val set2setInstant       : AnyRef => AnyRef = jset2set((v: AnyRef) => Instant.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalDate     : AnyRef => AnyRef = jset2set((v: AnyRef) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalTime     : AnyRef => AnyRef = jset2set((v: AnyRef) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalDateTime : AnyRef => AnyRef = jset2set((v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setOffsetTime    : AnyRef => AnyRef = jset2set((v: AnyRef) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val set2setOffsetDateTime: AnyRef => AnyRef = jset2set((v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setZonedDateTime : AnyRef => AnyRef = jset2set((v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setUUID          : AnyRef => AnyRef = jset2set
  private lazy val set2setURI           : AnyRef => AnyRef = jset2set
  private lazy val set2setByte          : AnyRef => AnyRef = jset2set({
    case v: Integer => v.toByte
    case v: jLong   => v.toByte
  })
  private lazy val set2setShort         : AnyRef => AnyRef = jset2set({
    case v: Integer => v.toByte
    case v: jLong   => v.toByte
  })
  private lazy val set2setChar          : AnyRef => AnyRef = jset2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def jset2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

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
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    set2sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
    j2sSet: AnyRef => AnyRef
  )

  lazy val resSetId            : ResSet[String]         = ResSet("String", dId, s2jId, set2setId, set2setsId, vector2setId, j2sSetId)
  lazy val resSetString        : ResSet[String]         = ResSet("String", dString, s2jString, set2setString, set2setsString, vector2setString, j2sSetString)
  lazy val resSetInt           : ResSet[Int]            = ResSet("Int", dInt, s2jInt, set2setInt, set2setsInt, vector2setInt, j2sSetInt)
  lazy val resSetLong          : ResSet[Long]           = ResSet("Long", dLong, s2jLong, set2setLong, set2setsLong, vector2setLong, j2sSetLong)
  lazy val resSetFloat         : ResSet[Float]          = ResSet("Float", dFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat, j2sSetFloat)
  lazy val resSetDouble        : ResSet[Double]         = ResSet("Double", dDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble, j2sSetDouble)
  lazy val resSetBoolean       : ResSet[Boolean]        = ResSet("Boolean", dBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean, j2sSetBoolean)
  lazy val resSetBigInt        : ResSet[BigInt]         = ResSet("BigInt", dBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt, j2sSetBigInt)
  lazy val resSetBigDecimal    : ResSet[BigDecimal]     = ResSet("BigDecimal", dBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal, j2sSetBigDecimal)
  lazy val resSetDate          : ResSet[Date]           = ResSet("Date", dDate, s2jDate, set2setDate, set2setsDate, vector2setDate, j2sSetDate)
  lazy val resSetDuration      : ResSet[Duration]       = ResSet("Duration", dDuration, s2jDuration, set2setDuration, set2setsDuration, vector2setDuration, j2sSetDuration)
  lazy val resSetInstant       : ResSet[Instant]        = ResSet("Instant", dInstant, s2jInstant, set2setInstant, set2setsInstant, vector2setInstant, j2sSetInstant)
  lazy val resSetLocalDate     : ResSet[LocalDate]      = ResSet("LocalDate", dLocalDate, s2jLocalDate, set2setLocalDate, set2setsLocalDate, vector2setLocalDate, j2sSetLocalDate)
  lazy val resSetLocalTime     : ResSet[LocalTime]      = ResSet("LocalTime", dLocalTime, s2jLocalTime, set2setLocalTime, set2setsLocalTime, vector2setLocalTime, j2sSetLocalTime)
  lazy val resSetLocalDateTime : ResSet[LocalDateTime]  = ResSet("LocalDateTime", dLocalDateTime, s2jLocalDateTime, set2setLocalDateTime, set2setsLocalDateTime, vector2setLocalDateTime, j2sSetLocalDateTime)
  lazy val resSetOffsetTime    : ResSet[OffsetTime]     = ResSet("OffsetTime", dOffsetTime, s2jOffsetTime, set2setOffsetTime, set2setsOffsetTime, vector2setOffsetTime, j2sSetOffsetTime)
  lazy val resSetOffsetDateTime: ResSet[OffsetDateTime] = ResSet("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, set2setOffsetDateTime, set2setsOffsetDateTime, vector2setOffsetDateTime, j2sSetOffsetDateTime)
  lazy val resSetZonedDateTime : ResSet[ZonedDateTime]  = ResSet("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, set2setZonedDateTime, set2setsZonedDateTime, vector2setZonedDateTime, j2sSetZonedDateTime)
  lazy val resSetUUID          : ResSet[UUID]           = ResSet("UUID", dUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID, j2sSetUUID)
  lazy val resSetURI           : ResSet[URI]            = ResSet("URI", dURI, s2jURI, set2setURI, set2setsURI, vector2setURI, j2sSetURI)
  lazy val resSetByte          : ResSet[Byte]           = ResSet("Byte", dByte, s2jByte, set2setByte, set2setsByte, vector2setByte, j2sSetByte)
  lazy val resSetShort         : ResSet[Short]          = ResSet("Short", dShort, s2jShort, set2setShort, set2setsShort, vector2setShort, j2sSetShort)
  lazy val resSetChar          : ResSet[Char]           = ResSet("Char", dChar, s2jChar, set2setChar, set2setsChar, vector2setChar, j2sSetChar)


  lazy val any2double: AnyRef => AnyRef = {
    ((v: AnyRef) => v.toString.toDouble).asInstanceOf[AnyRef => AnyRef]
  }

  private lazy val j2sOpSetId = (v: AnyRef) => v match {
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

  private lazy val j2sOpSetString = (v: AnyRef) => v match {
    case null            => Option.empty[Set[String]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String])) // attr_?(<expr>))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String]).toSet) // attr_?
  }

  private lazy val j2sOpSetInt = (v: AnyRef) => v match {
    case null => Option.empty[Set[Int]]
    // Datomic can return both Integer or Long
    case set: jSet[_]    => Some(set.asScala.map(_.toString.toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.toString.toInt).toSet)
  }

  private lazy val j2sOpSetLong = (v: AnyRef) => v match {
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

  private lazy val j2sOpSetFloat          = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Float]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Float]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Float]).toSet)
  }
  private lazy val j2sOpSetDouble         = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Double]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Double]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Double]).toSet)
  }
  private lazy val j2sOpSetBoolean        = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Boolean]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Boolean]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Boolean]).toSet)
  }
  private lazy val j2sOpSetBigInt         = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigInt]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }
  private lazy val j2sOpSetBigDecimal     = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigDecimal]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }
  private lazy val j2sOpSetDate           = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Date]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Date]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Date]).toSet)
  }
  private lazy val j2sOpSetDuration       = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Duration]]
    case set: jSet[_]    => Some(set.asScala.map(v => Duration.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => Duration.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetInstant        = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Instant]]
    case set: jSet[_]    => Some(set.asScala.map(v => Instant.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => Instant.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetLocalDate      = (v: AnyRef) => v match {
    case null            => Option.empty[Set[LocalDate]]
    case set: jSet[_]    => Some(set.asScala.map(v => LocalDate.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => LocalDate.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetLocalTime      = (v: AnyRef) => v match {
    case null            => Option.empty[Set[LocalTime]]
    case set: jSet[_]    => Some(set.asScala.map(v => LocalTime.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => LocalTime.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetLocalDateTime  = (v: AnyRef) => v match {
    case null            => Option.empty[Set[LocalDateTime]]
    case set: jSet[_]    => Some(set.asScala.map(v => LocalDateTime.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => LocalDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetOffsetTime     = (v: AnyRef) => v match {
    case null            => Option.empty[Set[OffsetTime]]
    case set: jSet[_]    => Some(set.asScala.map(v => OffsetTime.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => OffsetTime.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetOffsetDateTime = (v: AnyRef) => v match {
    case null            => Option.empty[Set[OffsetDateTime]]
    case set: jSet[_]    => Some(set.asScala.map(v => OffsetDateTime.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => OffsetDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetZonedDateTime  = (v: AnyRef) => v match {
    case null            => Option.empty[Set[ZonedDateTime]]
    case set: jSet[_]    => Some(set.asScala.map(v => ZonedDateTime.parse(v.asInstanceOf[String])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => ZonedDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  private lazy val j2sOpSetUUID           = (v: AnyRef) => v match {
    case null            => Option.empty[Set[UUID]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[UUID]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[UUID]).toSet)
  }
  private lazy val j2sOpSetURI            = (v: AnyRef) => v match {
    case null            => Option.empty[Set[URI]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[URI]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[URI]).toSet)
  }
  private lazy val j2sOpSetByte           = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Byte]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toByte))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toByte).toSet)
  }
  private lazy val j2sOpSetShort          = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Short]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toShort))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toShort).toSet)
  }
  private lazy val j2sOpSetChar           = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Char]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String].charAt(0)))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String].charAt(0)).toSet)
  }


  case class ResSetOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
  )

  lazy val resOptSetId            : ResSetOpt[String]         = ResSetOpt("String", dId, s2jId, j2sOpSetId)
  lazy val resOptSetString        : ResSetOpt[String]         = ResSetOpt("String", dString, s2jString, j2sOpSetString)
  lazy val resOptSetInt           : ResSetOpt[Int]            = ResSetOpt("Int", dInt, s2jInt, j2sOpSetInt)
  lazy val resOptSetLong          : ResSetOpt[Long]           = ResSetOpt("Long", dLong, s2jLong, j2sOpSetLong)
  lazy val resOptSetFloat         : ResSetOpt[Float]          = ResSetOpt("Float", dFloat, s2jFloat, j2sOpSetFloat)
  lazy val resOptSetDouble        : ResSetOpt[Double]         = ResSetOpt("Double", dDouble, s2jDouble, j2sOpSetDouble)
  lazy val resOptSetBoolean       : ResSetOpt[Boolean]        = ResSetOpt("Boolean", dBoolean, s2jBoolean, j2sOpSetBoolean)
  lazy val resOptSetBigInt        : ResSetOpt[BigInt]         = ResSetOpt("BigInt", dBigInt, s2jBigInt, j2sOpSetBigInt)
  lazy val resOptSetBigDecimal    : ResSetOpt[BigDecimal]     = ResSetOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOpSetBigDecimal)
  lazy val resOptSetDate          : ResSetOpt[Date]           = ResSetOpt("Date", dDate, s2jDate, j2sOpSetDate)
  lazy val resOptSetDuration      : ResSetOpt[Duration]       = ResSetOpt("Duration", dDuration, s2jDuration, j2sOpSetDuration)
  lazy val resOptSetInstant       : ResSetOpt[Instant]        = ResSetOpt("Instant", dInstant, s2jInstant, j2sOpSetInstant)
  lazy val resOptSetLocalDate     : ResSetOpt[LocalDate]      = ResSetOpt("LocalDate", dLocalDate, s2jLocalDate, j2sOpSetLocalDate)
  lazy val resOptSetLocalTime     : ResSetOpt[LocalTime]      = ResSetOpt("LocalTime", dLocalTime, s2jLocalTime, j2sOpSetLocalTime)
  lazy val resOptSetLocalDateTime : ResSetOpt[LocalDateTime]  = ResSetOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sOpSetLocalDateTime)
  lazy val resOptSetOffsetTime    : ResSetOpt[OffsetTime]     = ResSetOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOpSetOffsetTime)
  lazy val resOptSetOffsetDateTime: ResSetOpt[OffsetDateTime] = ResSetOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOpSetOffsetDateTime)
  lazy val resOptSetZonedDateTime : ResSetOpt[ZonedDateTime]  = ResSetOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sOpSetZonedDateTime)
  lazy val resOptSetUUID          : ResSetOpt[UUID]           = ResSetOpt("UUID", dUUID, s2jUUID, j2sOpSetUUID)
  lazy val resOptSetURI           : ResSetOpt[URI]            = ResSetOpt("URI", dURI, s2jURI, j2sOpSetURI)
  lazy val resOptSetByte          : ResSetOpt[Byte]           = ResSetOpt("Byte", dByte, s2jByte, j2sOpSetByte)
  lazy val resOptSetShort         : ResSetOpt[Short]          = ResSetOpt("Short", dShort, s2jShort, j2sOpSetShort)
  lazy val resOptSetChar          : ResSetOpt[Char]           = ResSetOpt("Char", dChar, s2jChar, j2sOpSetChar)


  // Nested opt ---------------------------------------------------------------------

  lazy val it2SetId            : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Long]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetString        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.toString).toSet
    case other        => unexpectedValue(other)
  }
  lazy val it2SetInt           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case vs: jList[_] => vs.asScala.map(v => v.toString.toInt).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLong          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Long]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetFloat         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Float]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDouble        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Double]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBoolean       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigInt        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigDecimal    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDate          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Date]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDuration      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => Duration.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetInstant       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => Instant.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLocalDate     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => LocalDate.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLocalTime     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => LocalTime.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLocalDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => LocalDateTime.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetOffsetTime    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => OffsetTime.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetOffsetDateTime: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => OffsetDateTime.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetZonedDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => ZonedDateTime.parse(v.asInstanceOf[String])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetUUID          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[UUID]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetURI           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[URI]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetByte          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetShort         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetChar          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet
    case other        => unexpectedValue(other)
  }


  lazy val it2OptSetId            : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Long]).toSet)
  }
  lazy val it2OptSetString        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String]).toSet)
  }
  lazy val it2OptSetInt           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    // Datomic can return both Integer or Long
    case vs: jList[_] => Some(vs.asScala.map(v => v.toString.toInt).toSet)
  }
  lazy val it2OptSetLong          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Long]).toSet)
  }
  lazy val it2OptSetFloat         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Float]).toSet)
  }
  lazy val it2OptSetDouble        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Double]).toSet)
  }
  lazy val it2OptSetBoolean       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet)
  }
  lazy val it2OptSetBigInt        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }
  lazy val it2OptSetBigDecimal    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }
  lazy val it2OptSetDate          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Date]).toSet)
  }
  lazy val it2OptSetDuration      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => Duration.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetInstant       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => Instant.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetLocalDate     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => LocalDate.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetLocalTime     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => LocalTime.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetLocalDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => LocalDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetOffsetTime    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => OffsetTime.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetOffsetDateTime: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => OffsetDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetZonedDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => ZonedDateTime.parse(v.asInstanceOf[String])).toSet)
  }
  lazy val it2OptSetUUID          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[UUID]).toSet)
  }
  lazy val it2OptSetURI           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[URI]).toSet)
  }
  lazy val it2OptSetByte          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet)
  }
  lazy val it2OptSetShort         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet)
  }
  lazy val it2OptSetChar          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet)
  }
}