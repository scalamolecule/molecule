package molecule.datalog.core.query

import java.lang.{Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.reflect.ClassTag

trait LambdasSeq extends ResolveBase with JavaConversions {

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

  private def jset2set(decode: AnyRef => AnyRef): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[Set[AnyRef]].toArray.map(decode)


  private lazy val jSetOfLists2sId            : AnyRef => AnyRef = jSetOfLists2s[String](_.asInstanceOf[String])
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


  private lazy val vector2listId            : AnyRef => AnyRef = jvector2list((v: AnyRef) => v.toString)
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

  private lazy val j2sListId            : AnyRef => List[String]         = (seq: AnyRef) => jList2list(_.asInstanceOf[String]).apply(seq)
  private lazy val j2sListString        : AnyRef => List[String]         = (seq: AnyRef) => jList2list(_.asInstanceOf[String]).apply(seq)
  private lazy val j2sListInt           : AnyRef => List[Int]            = (seq: AnyRef) => jList2list(_.toString.toInt).apply(seq)
  private lazy val j2sListLong          : AnyRef => List[Long]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Long]).apply(seq)
  private lazy val j2sListFloat         : AnyRef => List[Float]          = (seq: AnyRef) => jList2list(_.asInstanceOf[Float]).apply(seq)
  private lazy val j2sListDouble        : AnyRef => List[Double]         = (seq: AnyRef) => jList2list(_.asInstanceOf[Double]).apply(seq)
  private lazy val j2sListBoolean       : AnyRef => List[Boolean]        = (seq: AnyRef) => jList2list(_.asInstanceOf[Boolean]).apply(seq)
  private lazy val j2sListBigInt        : AnyRef => List[BigInt]         = (seq: AnyRef) => jList2list((v: Any) => BigInt(v.toString)).apply(seq)
  private lazy val j2sListBigDecimal    : AnyRef => List[BigDecimal]     = (seq: AnyRef) => jList2list((v: Any) => BigDecimal(v.toString)).apply(seq)
  private lazy val j2sListDate          : AnyRef => List[Date]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Date]).apply(seq)
  private lazy val j2sListDuration      : AnyRef => List[Duration]       = (seq: AnyRef) => jList2list((v: Any) => Duration.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListInstant       : AnyRef => List[Instant]        = (seq: AnyRef) => jList2list((v: Any) => Instant.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListLocalDate     : AnyRef => List[LocalDate]      = (seq: AnyRef) => jList2list((v: Any) => LocalDate.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListLocalTime     : AnyRef => List[LocalTime]      = (seq: AnyRef) => jList2list((v: Any) => LocalTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListLocalDateTime : AnyRef => List[LocalDateTime]  = (seq: AnyRef) => jList2list((v: Any) => LocalDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListOffsetTime    : AnyRef => List[OffsetTime]     = (seq: AnyRef) => jList2list((v: Any) => OffsetTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListOffsetDateTime: AnyRef => List[OffsetDateTime] = (seq: AnyRef) => jList2list((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListZonedDateTime : AnyRef => List[ZonedDateTime]  = (seq: AnyRef) => jList2list((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val j2sListUUID          : AnyRef => List[UUID]           = (seq: AnyRef) => jList2list(_.asInstanceOf[UUID]).apply(seq)
  private lazy val j2sListURI           : AnyRef => List[URI]            = (seq: AnyRef) => jList2list(_.asInstanceOf[URI]).apply(seq)
  private lazy val j2sListByte          : AnyRef => List[Byte]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Integer].toByte).apply(seq)
  private lazy val j2sListShort         : AnyRef => List[Short]          = (seq: AnyRef) => jList2list(_.asInstanceOf[Integer].toShort).apply(seq)
  private lazy val j2sListChar          : AnyRef => List[Char]           = (seq: AnyRef) => jList2list(_.asInstanceOf[String].charAt(0)).apply(seq)

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

  private def jList2list[T](decode: Any => T): AnyRef => List[T] = (seq: AnyRef) => {
    seq.asInstanceOf[jList[_]].toArray.toList.map(decode)
  }

  case class ResSeq[T](
    j2sList: AnyRef => List[T],
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    jSet2sSet: AnyRef => AnyRef,
    jSet2s: AnyRef => AnyRef,
    jSetOfLists2s: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,

    //    j2sArray: AnyRef => AnyRef
  )

  lazy val resSeqId            : ResSeq[String]         = ResSeq(j2sListId, "String", dId, s2jId, j2sId, set2setId, jSet2sId, jSetOfLists2sId, vector2listId, vector2setId)
  lazy val resSeqString        : ResSeq[String]         = ResSeq(j2sListString, "String", dString, s2jString, j2sString, set2setString, jSet2sString, jSetOfLists2sString, vector2listString, vector2setString)
  lazy val resSeqInt           : ResSeq[Int]            = ResSeq(j2sListInt, "Int", dInt, s2jInt, j2sInt, set2setInt, jSet2sInt, jSetOfLists2sInt, vector2listInt, vector2setInt)
  lazy val resSeqLong          : ResSeq[Long]           = ResSeq(j2sListLong, "Long", dLong, s2jLong, j2sLong, set2setLong, jSet2sLong, jSetOfLists2sLong, vector2listLong, vector2setLong)
  lazy val resSeqFloat         : ResSeq[Float]          = ResSeq(j2sListFloat, "Float", dFloat, s2jFloat, j2sFloat, set2setFloat, jSet2sFloat, jSetOfLists2sFloat, vector2listFloat, vector2setFloat)
  lazy val resSeqDouble        : ResSeq[Double]         = ResSeq(j2sListDouble, "Double", dDouble, s2jDouble, j2sDouble, set2setDouble, jSet2sDouble, jSetOfLists2sDouble, vector2listDouble, vector2setDouble)
  lazy val resSeqBoolean       : ResSeq[Boolean]        = ResSeq(j2sListBoolean, "Boolean", dBoolean, s2jBoolean, j2sBoolean, set2setBoolean, jSet2sBoolean, jSetOfLists2sBoolean, vector2listBoolean, vector2setBoolean)
  lazy val resSeqBigInt        : ResSeq[BigInt]         = ResSeq(j2sListBigInt, "BigInt", dBigInt, s2jBigInt, j2sBigInt, set2setBigInt, jSet2sBigInt, jSetOfLists2sBigInt, vector2listBigInt, vector2setBigInt)
  lazy val resSeqBigDecimal    : ResSeq[BigDecimal]     = ResSeq(j2sListBigDecimal, "BigDecimal", dBigDecimal, s2jBigDecimal, j2sBigDecimal, set2setBigDecimal, jSet2sBigDecimal, jSetOfLists2sBigDecimal, vector2listBigDecimal, vector2setBigDecimal)
  lazy val resSeqDate          : ResSeq[Date]           = ResSeq(j2sListDate, "Date", dDate, s2jDate, j2sDate, set2setDate, jSet2sDate, jSetOfLists2sDate, vector2listDate, vector2setDate)
  lazy val resSeqDuration      : ResSeq[Duration]       = ResSeq(j2sListDuration, "Duration", dDuration, s2jDuration, j2sDuration, set2setDuration, jSet2sDuration, jSetOfLists2sDuration, vector2listDuration, vector2setDuration)
  lazy val resSeqInstant       : ResSeq[Instant]        = ResSeq(j2sListInstant, "Instant", dInstant, s2jInstant, j2sInstant, set2setInstant, jSet2sInstant, jSetOfLists2sInstant, vector2listInstant, vector2setInstant)
  lazy val resSeqLocalDate     : ResSeq[LocalDate]      = ResSeq(j2sListLocalDate, "LocalDate", dLocalDate, s2jLocalDate, j2sLocalDate, set2setLocalDate, jSet2sLocalDate, jSetOfLists2sLocalDate, vector2listLocalDate, vector2setLocalDate)
  lazy val resSeqLocalTime     : ResSeq[LocalTime]      = ResSeq(j2sListLocalTime, "LocalTime", dLocalTime, s2jLocalTime, j2sLocalTime, set2setLocalTime, jSet2sLocalTime, jSetOfLists2sLocalTime, vector2listLocalTime, vector2setLocalTime)
  lazy val resSeqLocalDateTime : ResSeq[LocalDateTime]  = ResSeq(j2sListLocalDateTime, "LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sLocalDateTime, set2setLocalDateTime, jSet2sLocalDateTime, jSetOfLists2sLocalDateTime, vector2listLocalDateTime, vector2setLocalDateTime)
  lazy val resSeqOffsetTime    : ResSeq[OffsetTime]     = ResSeq(j2sListOffsetTime, "OffsetTime", dOffsetTime, s2jOffsetTime, j2sOffsetTime, set2setOffsetTime, jSet2sOffsetTime, jSetOfLists2sOffsetTime, vector2listOffsetTime, vector2setOffsetTime)
  lazy val resSeqOffsetDateTime: ResSeq[OffsetDateTime] = ResSeq(j2sListOffsetDateTime, "OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOffsetDateTime, set2setOffsetDateTime, jSet2sOffsetDateTime, jSetOfLists2sOffsetDateTime, vector2listOffsetDateTime, vector2setOffsetDateTime)
  lazy val resSeqZonedDateTime : ResSeq[ZonedDateTime]  = ResSeq(j2sListZonedDateTime, "ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sZonedDateTime, set2setZonedDateTime, jSet2sZonedDateTime, jSetOfLists2sZonedDateTime, vector2listZonedDateTime, vector2setZonedDateTime)
  lazy val resSeqUUID          : ResSeq[UUID]           = ResSeq(j2sListUUID, "UUID", dUUID, s2jUUID, j2sUUID, set2setUUID, jSet2sUUID, jSetOfLists2sUUID, vector2listUUID, vector2setUUID)
  lazy val resSeqURI           : ResSeq[URI]            = ResSeq(j2sListURI, "URI", dURI, s2jURI, j2sURI, set2setURI, jSet2sURI, jSetOfLists2sURI, vector2listURI, vector2setURI)
  lazy val resSeqByte          : ResSeq[Byte]           = ResSeq(j2sListByte, "Byte", dByte, s2jByte, j2sByte, set2setByte, jSet2sByte, jSetOfLists2sByte, vector2listByte, vector2setByte)
  lazy val resSeqShort         : ResSeq[Short]          = ResSeq(j2sListShort, "Short", dShort, s2jShort, j2sShort, set2setShort, jSet2sShort, jSetOfLists2sShort, vector2listShort, vector2setShort)
  lazy val resSeqChar          : ResSeq[Char]           = ResSeq(j2sListChar, "Char", dChar, s2jChar, j2sChar, set2setChar, jSet2sChar, jSetOfLists2sChar, vector2listChar, vector2setChar)


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


  private def optSeq2s[T](decode: Any => T): AnyRef => Option[List[T]] = (v: AnyRef) => {
    if (v == null) {
      Option.empty[List[T]]
    } else {
      val it   = v match {
        case v: jList[_] => v.iterator()
        case v: jSet[_]  => v.iterator()
      }
      val list = ListBuffer.empty[T]
      while (it.hasNext) {
        list.addOne(decode(it.next))
      }
      if (list.isEmpty) Option.empty[List[T]] else Some(list.toList)
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

  private lazy val j2sOptListId             = optSeq2sID
  private lazy val j2sOptListString         = optSeq2s(j2String)
  private lazy val j2sOptListInt            = optSeq2s(j2Int)
  private lazy val j2sOptListLong           = optSeq2s(j2Long)
  private lazy val j2sOptListFloat          = optSeq2s(j2Float)
  private lazy val j2sOptListDouble         = optSeq2s(j2Double)
  private lazy val j2sOptListBoolean        = optSeq2s(j2Boolean)
  private lazy val j2sOptListBigInt         = optSeq2s(j2BigInt)
  private lazy val j2sOptListBigDecimal     = optSeq2s(j2BigDecimal)
  private lazy val j2sOptListDate           = optSeq2s(j2Date)
  private lazy val j2sOptListDuration       = optSeq2s(j2Duration)
  private lazy val j2sOptListInstant        = optSeq2s(j2Instant)
  private lazy val j2sOptListLocalDate      = optSeq2s(j2LocalDate)
  private lazy val j2sOptListLocalTime      = optSeq2s(j2LocalTime)
  private lazy val j2sOptListLocalDateTime  = optSeq2s(j2LocalDateTime)
  private lazy val j2sOptListOffsetTime     = optSeq2s(j2OffsetTime)
  private lazy val j2sOptListOffsetDateTime = optSeq2s(j2OffsetDateTime)
  private lazy val j2sOptListZonedDateTime  = optSeq2s(j2ZonedDateTime)
  private lazy val j2sOptListUUID           = optSeq2s(j2UUID)
  private lazy val j2sOptListURI            = optSeq2s(j2URI)
  private lazy val j2sOptListByte           = optByteAttr2s
  private lazy val j2sOptListShort          = optSeq2s(j2Short)
  private lazy val j2sOptListChar           = optSeq2s(j2Char)


  case class ResSeqOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2sOptList: AnyRef => AnyRef,
  )

  lazy val resOptSeqId            : ResSeqOpt[String]         = ResSeqOpt("String", dId, s2jId, j2sOptListId)
  lazy val resOptSeqString        : ResSeqOpt[String]         = ResSeqOpt("String", dString, s2jString, j2sOptListString)
  lazy val resOptSeqInt           : ResSeqOpt[Int]            = ResSeqOpt("Int", dInt, s2jInt, j2sOptListInt)
  lazy val resOptSeqLong          : ResSeqOpt[Long]           = ResSeqOpt("Long", dLong, s2jLong, j2sOptListLong)
  lazy val resOptSeqFloat         : ResSeqOpt[Float]          = ResSeqOpt("Float", dFloat, s2jFloat, j2sOptListFloat)
  lazy val resOptSeqDouble        : ResSeqOpt[Double]         = ResSeqOpt("Double", dDouble, s2jDouble, j2sOptListDouble)
  lazy val resOptSeqBoolean       : ResSeqOpt[Boolean]        = ResSeqOpt("Boolean", dBoolean, s2jBoolean, j2sOptListBoolean)
  lazy val resOptSeqBigInt        : ResSeqOpt[BigInt]         = ResSeqOpt("BigInt", dBigInt, s2jBigInt, j2sOptListBigInt)
  lazy val resOptSeqBigDecimal    : ResSeqOpt[BigDecimal]     = ResSeqOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOptListBigDecimal)
  lazy val resOptSeqDate          : ResSeqOpt[Date]           = ResSeqOpt("Date", dDate, s2jDate, j2sOptListDate)
  lazy val resOptSeqDuration      : ResSeqOpt[Duration]       = ResSeqOpt("Duration", dDuration, s2jDuration, j2sOptListDuration)
  lazy val resOptSeqInstant       : ResSeqOpt[Instant]        = ResSeqOpt("Instant", dInstant, s2jInstant, j2sOptListInstant)
  lazy val resOptSeqLocalDate     : ResSeqOpt[LocalDate]      = ResSeqOpt("LocalDate", dLocalDate, s2jLocalDate, j2sOptListLocalDate)
  lazy val resOptSeqLocalTime     : ResSeqOpt[LocalTime]      = ResSeqOpt("LocalTime", dLocalTime, s2jLocalTime, j2sOptListLocalTime)
  lazy val resOptSeqLocalDateTime : ResSeqOpt[LocalDateTime]  = ResSeqOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sOptListLocalDateTime)
  lazy val resOptSeqOffsetTime    : ResSeqOpt[OffsetTime]     = ResSeqOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOptListOffsetTime)
  lazy val resOptSeqOffsetDateTime: ResSeqOpt[OffsetDateTime] = ResSeqOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOptListOffsetDateTime)
  lazy val resOptSeqZonedDateTime : ResSeqOpt[ZonedDateTime]  = ResSeqOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sOptListZonedDateTime)
  lazy val resOptSeqUUID          : ResSeqOpt[UUID]           = ResSeqOpt("UUID", dUUID, s2jUUID, j2sOptListUUID)
  lazy val resOptSeqURI           : ResSeqOpt[URI]            = ResSeqOpt("URI", dURI, s2jURI, j2sOptListURI)
  lazy val resOptSeqByte          : ResSeqOpt[Byte]           = ResSeqOpt("Byte", dByte, s2jByte, j2sOptListByte)
  lazy val resOptSeqShort         : ResSeqOpt[Short]          = ResSeqOpt("Short", dShort, s2jShort, j2sOptListShort)
  lazy val resOptSeqChar          : ResSeqOpt[Char]           = ResSeqOpt("Char", dChar, s2jChar, j2sOptListChar)


  // Nested opt ---------------------------------------------------------------------

  private def it2List[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case maps: jList[_]     =>
      val pairs = ListBuffer.empty[(Int, T)]
      maps.forEach {
        case map: jMap[_, _] =>
          val vs = map.values().iterator()
          pairs += ((vs.next().asInstanceOf[jInteger].toInt, decode(vs.next())))
      }
      pairs.sortBy(_._1).map(_._2).toList
    case `none`             => nullValue
    case bytes: Array[Byte] => bytes
    case other              => unexpectedValue(other)
  }

  lazy val it2ListId            : jIterator[_] => Any = it2List(j2Id)
  lazy val it2ListString        : jIterator[_] => Any = it2List(j2String)
  lazy val it2ListInt           : jIterator[_] => Any = it2List(j2Int)
  lazy val it2ListLong          : jIterator[_] => Any = it2List(j2Long)
  lazy val it2ListFloat         : jIterator[_] => Any = it2List(j2Float)
  lazy val it2ListDouble        : jIterator[_] => Any = it2List(j2Double)
  lazy val it2ListBoolean       : jIterator[_] => Any = it2List(j2Boolean)
  lazy val it2ListBigInt        : jIterator[_] => Any = it2List(j2BigInt)
  lazy val it2ListBigDecimal    : jIterator[_] => Any = it2List(j2BigDecimal)
  lazy val it2ListDate          : jIterator[_] => Any = it2List(j2Date)
  lazy val it2ListDuration      : jIterator[_] => Any = it2List(j2Duration)
  lazy val it2ListInstant       : jIterator[_] => Any = it2List(j2Instant)
  lazy val it2ListLocalDate     : jIterator[_] => Any = it2List(j2LocalDate)
  lazy val it2ListLocalTime     : jIterator[_] => Any = it2List(j2LocalTime)
  lazy val it2ListLocalDateTime : jIterator[_] => Any = it2List(j2LocalDateTime)
  lazy val it2ListOffsetTime    : jIterator[_] => Any = it2List(j2OffsetTime)
  lazy val it2ListOffsetDateTime: jIterator[_] => Any = it2List(j2OffsetDateTime)
  lazy val it2ListZonedDateTime : jIterator[_] => Any = it2List(j2ZonedDateTime)
  lazy val it2ListUUID          : jIterator[_] => Any = it2List(j2UUID)
  lazy val it2ListURI           : jIterator[_] => Any = it2List(j2URI)
  lazy val it2ListByte          : jIterator[_] => Any = it2List(j2Byte)
  lazy val it2ListShort         : jIterator[_] => Any = it2List(j2Short)
  lazy val it2ListChar          : jIterator[_] => Any = it2List(j2Char)


  private def it2OptList[T](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case maps: jList[_] =>
      val pairs = ListBuffer.empty[(Int, T)]
      maps.forEach {
        case map: jMap[_, _] =>
          val vs = map.values().iterator()
          pairs += ((vs.next().asInstanceOf[jInteger].toInt, decode(vs.next())))
      }
      if (pairs.nonEmpty) Some(pairs.sortBy(_._1).map(_._2).toList) else None

    case `none`             => None
    case bytes: Array[Byte] => if (bytes.nonEmpty) Some(bytes) else None
    case other              => unexpectedValue(other)
  }

  lazy val it2OptListId            : jIterator[_] => Any = it2OptList(j2Id)
  lazy val it2OptListString        : jIterator[_] => Any = it2OptList(j2String)
  lazy val it2OptListInt           : jIterator[_] => Any = it2OptList(j2Int)
  lazy val it2OptListLong          : jIterator[_] => Any = it2OptList(j2Long)
  lazy val it2OptListFloat         : jIterator[_] => Any = it2OptList(j2Float)
  lazy val it2OptListDouble        : jIterator[_] => Any = it2OptList(j2Double)
  lazy val it2OptListBoolean       : jIterator[_] => Any = it2OptList(j2Boolean)
  lazy val it2OptListBigInt        : jIterator[_] => Any = it2OptList(j2BigInt)
  lazy val it2OptListBigDecimal    : jIterator[_] => Any = it2OptList(j2BigDecimal)
  lazy val it2OptListDate          : jIterator[_] => Any = it2OptList(j2Date)
  lazy val it2OptListDuration      : jIterator[_] => Any = it2OptList(j2Duration)
  lazy val it2OptListInstant       : jIterator[_] => Any = it2OptList(j2Instant)
  lazy val it2OptListLocalDate     : jIterator[_] => Any = it2OptList(j2LocalDate)
  lazy val it2OptListLocalTime     : jIterator[_] => Any = it2OptList(j2LocalTime)
  lazy val it2OptListLocalDateTime : jIterator[_] => Any = it2OptList(j2LocalDateTime)
  lazy val it2OptListOffsetTime    : jIterator[_] => Any = it2OptList(j2OffsetTime)
  lazy val it2OptListOffsetDateTime: jIterator[_] => Any = it2OptList(j2OffsetDateTime)
  lazy val it2OptListZonedDateTime : jIterator[_] => Any = it2OptList(j2ZonedDateTime)
  lazy val it2OptListUUID          : jIterator[_] => Any = it2OptList(j2UUID)
  lazy val it2OptListURI           : jIterator[_] => Any = it2OptList(j2URI)
  lazy val it2OptListByte          : jIterator[_] => Any = it2OptList(j2Byte)
  lazy val it2OptListShort         : jIterator[_] => Any = it2OptList(j2Short)
  lazy val it2OptListChar          : jIterator[_] => Any = it2OptList(j2Char)
}