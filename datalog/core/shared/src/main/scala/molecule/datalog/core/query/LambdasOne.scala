package molecule.datalog.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}

trait LambdasOne extends ResolveBase {

  // Single sample value extracted from clojure LazySeq
  private lazy val firstIdx           : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstString        : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstInt           : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).toString.toInt.asInstanceOf[AnyRef]
  private lazy val firstLong          : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstFloat         : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstDouble        : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstBoolean       : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstBigInt        : AnyRef => AnyRef = (v: AnyRef) => BigInt(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  private lazy val firstBigDecimal    : AnyRef => AnyRef = (v: AnyRef) => BigDecimal(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  private lazy val firstDate          : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstDuration      : AnyRef => AnyRef = (v: AnyRef) => Duration.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstInstant       : AnyRef => AnyRef = (v: AnyRef) => Instant.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstLocalDate     : AnyRef => AnyRef = (v: AnyRef) => LocalDate.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstLocalTime     : AnyRef => AnyRef = (v: AnyRef) => LocalTime.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstLocalDateTime : AnyRef => AnyRef = (v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstOffsetTime    : AnyRef => AnyRef = (v: AnyRef) => OffsetTime.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstOffsetDateTime: AnyRef => AnyRef = (v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstZonedDateTime : AnyRef => AnyRef = (v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[jList[_]].get(0).asInstanceOf[String]).asInstanceOf[AnyRef]
  private lazy val firstUUID          : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstURI           : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val firstByte          : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toByte.asInstanceOf[AnyRef]
  private lazy val firstShort         : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toShort.asInstanceOf[AnyRef]
  private lazy val firstChar          : AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]

  private lazy val set2setId            : AnyRef => AnyRef = set2set
  private lazy val set2setString        : AnyRef => AnyRef = set2set
  private lazy val set2setInt           : AnyRef => AnyRef = set2set((v: AnyRef) => v.toString.toInt)
  private lazy val set2setLong          : AnyRef => AnyRef = set2set
  private lazy val set2setFloat         : AnyRef => AnyRef = set2set
  private lazy val set2setDouble        : AnyRef => AnyRef = set2set
  private lazy val set2setBoolean       : AnyRef => AnyRef = set2set
  private lazy val set2setBigInt        : AnyRef => AnyRef = set2set((v: AnyRef) => BigInt(v.toString))
  private lazy val set2setBigDecimal    : AnyRef => AnyRef = set2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val set2setDate          : AnyRef => AnyRef = set2set
  private lazy val set2setDuration      : AnyRef => AnyRef = set2set((v: AnyRef) => Duration.parse(v.asInstanceOf[String]))
  private lazy val set2setInstant       : AnyRef => AnyRef = set2set((v: AnyRef) => Instant.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalDate     : AnyRef => AnyRef = set2set((v: AnyRef) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalTime     : AnyRef => AnyRef = set2set((v: AnyRef) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val set2setLocalDateTime : AnyRef => AnyRef = set2set((v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setOffsetTime    : AnyRef => AnyRef = set2set((v: AnyRef) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val set2setOffsetDateTime: AnyRef => AnyRef = set2set((v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setZonedDateTime : AnyRef => AnyRef = set2set((v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val set2setUUID          : AnyRef => AnyRef = set2set
  private lazy val set2setURI           : AnyRef => AnyRef = set2set
  private lazy val set2setByte          : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val set2setShort         : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val set2setChar          : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def set2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

  private def set2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet

  private lazy val vector2setId            : AnyRef => AnyRef = jvector2set
  private lazy val vector2setString        : AnyRef => AnyRef = jvector2set
  private lazy val vector2setInt           : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.toString.toInt)
  private lazy val vector2setLong          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setFloat         : AnyRef => AnyRef = jvector2set
  private lazy val vector2setDouble        : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBoolean       : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBigInt        : AnyRef => AnyRef = jvector2set((v: AnyRef) => BigInt(v.toString))
  private lazy val vector2setBigDecimal    : AnyRef => AnyRef = jvector2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val vector2setDate          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setDuration      : AnyRef => AnyRef = jvector2set((v: Any) => Duration.parse(v.asInstanceOf[String]))
  private lazy val vector2setInstant       : AnyRef => AnyRef = jvector2set((v: Any) => Instant.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalDate     : AnyRef => AnyRef = jvector2set((v: Any) => LocalDate.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalTime     : AnyRef => AnyRef = jvector2set((v: Any) => LocalTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setLocalDateTime : AnyRef => AnyRef = jvector2set((v: Any) => LocalDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setOffsetTime    : AnyRef => AnyRef = jvector2set((v: Any) => OffsetTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setOffsetDateTime: AnyRef => AnyRef = jvector2set((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setZonedDateTime : AnyRef => AnyRef = jvector2set((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String]))
  private lazy val vector2setUUID          : AnyRef => AnyRef = jvector2set
  private lazy val vector2setURI           : AnyRef => AnyRef = jvector2set
  private lazy val vector2setByte          : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val vector2setShort         : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val vector2setChar          : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  case class ResOne[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2set: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
  )

  lazy val resId            : ResOne[Long]           = ResOne("Long", dId, s2jId, j2sId, firstIdx, set2setId, vector2setId)
  lazy val resString        : ResOne[String]         = ResOne("String", dString, s2jString, j2sString, firstString, set2setString, vector2setString)
  lazy val resInt           : ResOne[Int]            = ResOne("Int", dInt, s2jInt, j2sInt, firstInt, set2setInt, vector2setInt)
  lazy val resLong          : ResOne[Long]           = ResOne("Long", dLong, s2jLong, j2sLong, firstLong, set2setLong, vector2setLong)
  lazy val resFloat         : ResOne[Float]          = ResOne("Float", dFloat, s2jFloat, j2sFloat, firstFloat, set2setFloat, vector2setFloat)
  lazy val resDouble        : ResOne[Double]         = ResOne("Double", dDouble, s2jDouble, j2sDouble, firstDouble, set2setDouble, vector2setDouble)
  lazy val resBoolean       : ResOne[Boolean]        = ResOne("Boolean", dBoolean, s2jBoolean, j2sBoolean, firstBoolean, set2setBoolean, vector2setBoolean)
  lazy val resBigInt        : ResOne[BigInt]         = ResOne("BigInt", dBigInt, s2jBigInt, j2sBigInt, firstBigInt, set2setBigInt, vector2setBigInt)
  lazy val resBigDecimal    : ResOne[BigDecimal]     = ResOne("BigDecimal", dBigDecimal, s2jBigDecimal, j2sBigDecimal, firstBigDecimal, set2setBigDecimal, vector2setBigDecimal)
  lazy val resDate          : ResOne[Date]           = ResOne("Date", dDate, s2jDate, j2sDate, firstDate, set2setDate, vector2setDate)
  lazy val resDuration      : ResOne[Duration]       = ResOne("Duration", dDuration, s2jDuration, j2sDuration, firstDuration, set2setDuration, vector2setDuration)
  lazy val resInstant       : ResOne[Instant]        = ResOne("Instant", dInstant, s2jInstant, j2sInstant, firstInstant, set2setInstant, vector2setInstant)
  lazy val resLocalDate     : ResOne[LocalDate]      = ResOne("LocalDate", dLocalDate, s2jLocalDate, j2sLocalDate, firstLocalDate, set2setLocalDate, vector2setLocalDate)
  lazy val resLocalTime     : ResOne[LocalTime]      = ResOne("LocalTime", dLocalTime, s2jLocalTime, j2sLocalTime, firstLocalTime, set2setLocalTime, vector2setLocalTime)
  lazy val resLocalDateTime : ResOne[LocalDateTime]  = ResOne("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sLocalDateTime, firstLocalDateTime, set2setLocalDateTime, vector2setLocalDateTime)
  lazy val resOffsetTime    : ResOne[OffsetTime]     = ResOne("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOffsetTime, firstOffsetTime, set2setOffsetTime, vector2setOffsetTime)
  lazy val resOffsetDateTime: ResOne[OffsetDateTime] = ResOne("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOffsetDateTime, firstOffsetDateTime, set2setOffsetDateTime, vector2setOffsetDateTime)
  lazy val resZonedDateTime : ResOne[ZonedDateTime]  = ResOne("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sZonedDateTime, firstZonedDateTime, set2setZonedDateTime, vector2setZonedDateTime)
  lazy val resUUID          : ResOne[UUID]           = ResOne("UUID", dUUID, s2jUUID, j2sUUID, firstUUID, set2setUUID, vector2setUUID)
  lazy val resURI           : ResOne[URI]            = ResOne("URI", dURI, s2jURI, j2sURI, firstURI, set2setURI, vector2setURI)
  lazy val resByte          : ResOne[Byte]           = ResOne("Byte", dByte, s2jByte, j2sByte, firstByte, set2setByte, vector2setByte)
  lazy val resShort         : ResOne[Short]          = ResOne("Short", dShort, s2jShort, j2sShort, firstShort, set2setShort, vector2setShort)
  lazy val resChar          : ResOne[Char]           = ResOne("Char", dChar, s2jChar, j2sChar, firstChar, set2setChar, vector2setChar)


  private lazy val j2sOptId             = (v: AnyRef) => v match {
    case null          => Option.empty[Long]
    case v: jLong      => Some(v)
    case v: jMap[_, _] =>
      v.values.iterator.next match {
        case l: Long => Some(l)
        // ref
        case map: jMap[_, _] =>
          /*
          // If a ref is owned (:db/isComponent true), Datomic returns all nested values in a pull for an optional
          // ref value. So, the id can hide anywhere in the map entries and we need to extract it.
          // We can't call get(<key>) on the map since it needs a clojure.lang.Keyword that we can't use in a shared module
          {:ns/ownedRef {:ns/attr1 6, :ns/attr2 7, :db/id 17592186045419, :ns/attr3 8}}
          -------
          // If the ref is not owned, Datomic only returns the id
          {:ns/ref {:db/id 17592186045422}}
           */
          var continue = true
          var id       = 0L
          val it       = map.entrySet().iterator()
          while (it.hasNext && continue) {
            val pair = it.next()
            if (pair.getKey.toString == ":db/id") {
              continue = false
              id = pair.getValue.toString.toLong
            }
          }
          Some(id)
      }
  }
  private lazy val j2sOptString         = (v: AnyRef) => v match {
    case null          => Option.empty[String]
    case v: String     => Some(v) // attr_?(<expr>))
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String]) // attr_?
  }
  private lazy val j2sOptInt            = (v: AnyRef) => v match {
    case null => Option.empty[Int]
    // Datomic can return both Integer or Long
    case v: jLong      => Some(v.toInt)
    case v: jInteger   => Some(v.toInt)
    case v: jMap[_, _] => Some(v.values.iterator.next.toString.toInt)
  }
  private lazy val j2sOptLong           = (v: AnyRef) => v match {
    case null          => Option.empty[Long]
    case v: jLong      => Some(v)
    case v: jMap[_, _] =>
      v.values.iterator.next match {
        case l: Long => Some(l)
        // ref
        case map: jMap[_, _] =>
          /*
          // If a ref is owned (:db/isComponent true), Datomic returns all nested values in a pull for an optional
          // ref value. So, the id can hide anywhere in the map entries and we need to extract it.
          // We can't call get(<key>) on the map since it needs a clojure.lang.Keyword that we can't use in a shared module
          {:ns/ownedRef {:ns/attr1 6, :ns/attr2 7, :db/id 17592186045419, :ns/attr3 8}}
          -------
          // If the ref is not owned, Datomic only returns the id
          {:ns/ref {:db/id 17592186045422}}
           */
          var continue = true
          var id       = 0L
          val it       = map.entrySet().iterator()
          while (it.hasNext && continue) {
            val pair = it.next()
            if (pair.getKey.toString == ":db/id") {
              continue = false
              id = pair.getValue.asInstanceOf[Long]
            }
          }
          Some(id)
      }
  }
  private lazy val j2sOptFloat          = (v: AnyRef) => v match {
    case null          => Option.empty[Float]
    case v: jFloat     => Some(v.toFloat)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Float])
  }
  private lazy val j2sOptDouble         = (v: AnyRef) => v match {
    case null          => Option.empty[Double]
    case v: jDouble    => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Double])
  }
  private lazy val j2sOptBoolean        = (v: AnyRef) => v match {
    case null          => Option.empty[Boolean]
    case v: jBoolean   => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Boolean])
  }
  private lazy val j2sOptBigInt         = (v: AnyRef) => v match {
    case null          => Option.empty[BigInt]
    case v: jBigInt    => Some(BigInt(v))
    case v: jMap[_, _] => Some(BigInt(v.values.iterator.next.asInstanceOf[jBigInt]))
  }
  private lazy val j2sOptBigDecimal     = (v: AnyRef) => v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v: jMap[_, _]  => Some(BigDecimal(v.values.iterator.next.asInstanceOf[jBigDecimal]))
  }
  private lazy val j2sOptDate           = (v: AnyRef) => v match {
    case null          => Option.empty[Date]
    case v: Date       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Date])
  }
  private lazy val j2sOptDuration       = (v: AnyRef) => v match {
    case null          => Option.empty[Duration]
    case v: String     => Some(Duration.parse(v))
    case v: jMap[_, _] => Some(Duration.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptInstant        = (v: AnyRef) => v match {
    case null          => Option.empty[Instant]
    case v: String     => Some(Instant.parse(v))
    case v: jMap[_, _] => Some(Instant.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptLocalDate      = (v: AnyRef) => v match {
    case null          => Option.empty[LocalDate]
    case v: String     => Some(LocalDate.parse(v))
    case v: jMap[_, _] => Some(LocalDate.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptLocalTime      = (v: AnyRef) => v match {
    case null          => Option.empty[LocalTime]
    case v: String     => Some(LocalTime.parse(v))
    case v: jMap[_, _] => Some(LocalTime.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptLocalDateTime  = (v: AnyRef) => v match {
    case null          => Option.empty[LocalDateTime]
    case v: String     => Some(LocalDateTime.parse(v))
    case v: jMap[_, _] => Some(LocalDateTime.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptOffsetTime     = (v: AnyRef) => v match {
    case null          => Option.empty[OffsetTime]
    case v: String     => Some(OffsetTime.parse(v))
    case v: jMap[_, _] => Some(OffsetTime.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptOffsetDateTime = (v: AnyRef) => v match {
    case null          => Option.empty[OffsetDateTime]
    case v: String     => Some(OffsetDateTime.parse(v))
    case v: jMap[_, _] => Some(OffsetDateTime.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptZonedDateTime  = (v: AnyRef) => v match {
    case null          => Option.empty[ZonedDateTime]
    case v: String     => Some(ZonedDateTime.parse(v))
    case v: jMap[_, _] => Some(ZonedDateTime.parse(v.values.iterator.next.asInstanceOf[String]))
  }
  private lazy val j2sOptUUID           = (v: AnyRef) => v match {
    case null          => Option.empty[UUID]
    case v: UUID       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[UUID])
  }
  private lazy val j2sOptURI            = (v: AnyRef) => v match {
    case null          => Option.empty[URI]
    case v: URI        => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[URI])
  }
  private lazy val j2sOptByte           = (v: AnyRef) => v match {
    case null          => Option.empty[Byte]
    case v: jInteger   => Some(v.toByte)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toByte)
  }
  private lazy val j2sOptShort          = (v: AnyRef) => v match {
    case null          => Option.empty[Short]
    case v: jInteger   => Some(v.toShort)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toShort)
  }
  private lazy val j2sOptChar           = (v: AnyRef) => v match {
    case null          => Option.empty[Char]
    case v: String     => Some(v.head)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String].charAt(0))
  }


  case class ResOneOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef
  )

  lazy val resOptId            : ResOneOpt[Long]           = ResOneOpt("Long", dId, s2jId, j2sOptId)
  lazy val resOptString        : ResOneOpt[String]         = ResOneOpt("String", dString, s2jString, j2sOptString)
  lazy val resOptInt           : ResOneOpt[Int]            = ResOneOpt("Int", dInt, s2jInt, j2sOptInt)
  lazy val resOptLong          : ResOneOpt[Long]           = ResOneOpt("Long", dLong, s2jLong, j2sOptLong)
  lazy val resOptFloat         : ResOneOpt[Float]          = ResOneOpt("Float", dFloat, s2jFloat, j2sOptFloat)
  lazy val resOptDouble        : ResOneOpt[Double]         = ResOneOpt("Double", dDouble, s2jDouble, j2sOptDouble)
  lazy val resOptBoolean       : ResOneOpt[Boolean]        = ResOneOpt("Boolean", dBoolean, s2jBoolean, j2sOptBoolean)
  lazy val resOptBigInt        : ResOneOpt[BigInt]         = ResOneOpt("BigInt", dBigInt, s2jBigInt, j2sOptBigInt)
  lazy val resOptBigDecimal    : ResOneOpt[BigDecimal]     = ResOneOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOptBigDecimal)
  lazy val resOptDate          : ResOneOpt[Date]           = ResOneOpt("Date", dDate, s2jDate, j2sOptDate)
  lazy val resOptDuration      : ResOneOpt[Duration]       = ResOneOpt("Duration", dDuration, s2jDuration, j2sOptDuration)
  lazy val resOptInstant       : ResOneOpt[Instant]        = ResOneOpt("Instant", dInstant, s2jInstant, j2sOptInstant)
  lazy val resOptLocalDate     : ResOneOpt[LocalDate]      = ResOneOpt("LocalDate", dLocalDate, s2jLocalDate, j2sOptLocalDate)
  lazy val resOptLocalTime     : ResOneOpt[LocalTime]      = ResOneOpt("LocalTime", dLocalTime, s2jLocalTime, j2sOptLocalTime)
  lazy val resOptLocalDateTime : ResOneOpt[LocalDateTime]  = ResOneOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sOptLocalDateTime)
  lazy val resOptOffsetTime    : ResOneOpt[OffsetTime]     = ResOneOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOptOffsetTime)
  lazy val resOptOffsetDateTime: ResOneOpt[OffsetDateTime] = ResOneOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOptOffsetDateTime)
  lazy val resOptZonedDateTime : ResOneOpt[ZonedDateTime]  = ResOneOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sOptZonedDateTime)
  lazy val resOptUUID          : ResOneOpt[UUID]           = ResOneOpt("UUID", dUUID, s2jUUID, j2sOptUUID)
  lazy val resOptURI           : ResOneOpt[URI]            = ResOneOpt("URI", dURI, s2jURI, j2sOptURI)
  lazy val resOptByte          : ResOneOpt[Byte]           = ResOneOpt("Byte", dByte, s2jByte, j2sOptByte)
  lazy val resOptShort         : ResOneOpt[Short]          = ResOneOpt("Short", dShort, s2jShort, j2sOptShort)
  lazy val resOptChar          : ResOneOpt[Char]           = ResOneOpt("Char", dChar, s2jChar, j2sOptChar)


  // Nested opt ---------------------------------------------------------------------

  lazy val it2Id2    : AnyRef => AnyRef = {
    case v: jLong => v.asInstanceOf[AnyRef]
    case `none`   => nullValue
    case other    => unexpectedValue(other)
  }
  lazy val it2String2: AnyRef => AnyRef = {
    case `none`    => nullValue
    case v: String => v.asInstanceOf[AnyRef]
    case other     => unexpectedValue(other)
  }
  lazy val it2Int2   : AnyRef => AnyRef = {
    // Datomic can return both Integer or Long
    case v: jLong   => v.toInt.asInstanceOf[AnyRef]
    case v: Integer => v.toInt.asInstanceOf[AnyRef]
    case `none`     => nullValue
    //    case null => null
    case other => unexpectedValue(other)
  }


  lazy val it2Id            : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`   => nullValue
    case v: jLong => v
    case other    => unexpectedValue(other)
  }
  lazy val it2String        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => v
    case other     => unexpectedValue(other)
  }
  lazy val it2Int           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case `none`     => nullValue
    case v: jLong   => v.toInt
    case v: Integer => v.toInt
    case other      => unexpectedValue(other)
  }
  lazy val it2Long          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`   => nullValue
    case v: jLong => v.toLong
    case other    => unexpectedValue(other)
  }
  lazy val it2Float         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: jFloat => v.toFloat
    case other     => unexpectedValue(other)
  }
  lazy val it2Double        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`     => nullValue
    case v: jDouble => v.toDouble
    case other      => unexpectedValue(other)
  }
  lazy val it2Boolean       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`      => nullValue
    case v: jBoolean => v
    case other       => unexpectedValue(other)
  }
  lazy val it2BigInt        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`     => nullValue
    case v: jBigInt => BigInt(v)
    case other      => unexpectedValue(other)
  }
  lazy val it2BigDecimal    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`         => nullValue
    case v: jBigDecimal => BigDecimal(v)
    case other          => unexpectedValue(other)
  }
  lazy val it2Date          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`  => nullValue
    case v: Date => v
    case other   => unexpectedValue(other)
  }
  lazy val it2Duration      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => Duration.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2Instant       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => Instant.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2LocalDate     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => LocalDate.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2LocalTime     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => LocalTime.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2LocalDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => LocalDateTime.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2OffsetTime    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => OffsetTime.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2OffsetDateTime: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => OffsetDateTime.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2ZonedDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => ZonedDateTime.parse(v)
    case other     => unexpectedValue(other)
  }
  lazy val it2UUID          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`  => nullValue
    case v: UUID => v
    case other   => unexpectedValue(other)
  }
  lazy val it2URI           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => nullValue
    case v: URI => v
    case other  => unexpectedValue(other)
  }
  lazy val it2Byte          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`     => nullValue
    case v: Integer => v.toByte
    case other      => unexpectedValue(other)
  }
  lazy val it2Short         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`     => nullValue
    case v: Integer => v.toShort
    case other      => unexpectedValue(other)
  }
  lazy val it2Char          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => v.charAt(0)
    case other     => unexpectedValue(other)
  }


  lazy val it2OptId            : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v)
  }
  lazy val it2OptString        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.toString)
  }
  lazy val it2OptInt           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case `none`     => None
    case v: Integer => Some(v.toInt)
    case v: jLong   => Some(v.toInt)
  }
  lazy val it2OptLong          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jLong].toLong)
  }
  lazy val it2OptFloat         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jFloat].toFloat)
  }
  lazy val it2OptDouble        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jDouble].toDouble)
  }
  lazy val it2OptBoolean       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Boolean])
  }
  lazy val it2OptBigInt        : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(BigInt(v.asInstanceOf[jBigInt]))
  }
  lazy val it2OptBigDecimal    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(BigDecimal(v.asInstanceOf[jBigDecimal]))
  }
  lazy val it2OptDate          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Date])
  }
  lazy val it2OptDuration      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(Duration.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptInstant       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(Instant.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptLocalDate     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(LocalDate.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptLocalTime     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(LocalTime.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptLocalDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(LocalDateTime.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptOffsetTime    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(OffsetTime.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptOffsetDateTime: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(OffsetDateTime.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptZonedDateTime : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => None
    case v: String => Some(ZonedDateTime.parse(v))
    case other     => unexpectedValue(other)
  }
  lazy val it2OptUUID          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[UUID])
  }
  lazy val it2OptURI           : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[URI])
  }
  lazy val it2OptByte          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Integer].toByte)
  }
  lazy val it2OptShort         : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Integer].toShort)
  }
  lazy val it2OptChar          : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[String].charAt(0))
  }
}