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

  private lazy val j2sListId            : AnyRef => List[Long]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Long]).apply(seq)
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

  private def jList2list[T](decode: Any => T): AnyRef => List[T] = (seq: AnyRef) => {
    seq.asInstanceOf[jList[_]].toArray.toList.map(decode)
  }

  case class ResSeq[T](
    tpe: String,
    j2sList: AnyRef => List[T],
    s2j: Any => Any,
  )

  lazy val resSeqId            : ResSeq[Long]           = ResSeq("Long", j2sListId, s2jId)
  lazy val resSeqString        : ResSeq[String]         = ResSeq("String", j2sListString, s2jString)
  lazy val resSeqInt           : ResSeq[Int]            = ResSeq("Int", j2sListInt, s2jInt)
  lazy val resSeqLong          : ResSeq[Long]           = ResSeq("Long", j2sListLong, s2jLong)
  lazy val resSeqFloat         : ResSeq[Float]          = ResSeq("Float", j2sListFloat, s2jFloat)
  lazy val resSeqDouble        : ResSeq[Double]         = ResSeq("Double", j2sListDouble, s2jDouble)
  lazy val resSeqBoolean       : ResSeq[Boolean]        = ResSeq("Boolean", j2sListBoolean, s2jBoolean)
  lazy val resSeqBigInt        : ResSeq[BigInt]         = ResSeq("BigInt", j2sListBigInt, s2jBigInt)
  lazy val resSeqBigDecimal    : ResSeq[BigDecimal]     = ResSeq("BigDecimal", j2sListBigDecimal, s2jBigDecimal)
  lazy val resSeqDate          : ResSeq[Date]           = ResSeq("Date", j2sListDate, s2jDate)
  lazy val resSeqDuration      : ResSeq[Duration]       = ResSeq("Duration", j2sListDuration, s2jDuration)
  lazy val resSeqInstant       : ResSeq[Instant]        = ResSeq("Instant", j2sListInstant, s2jInstant)
  lazy val resSeqLocalDate     : ResSeq[LocalDate]      = ResSeq("LocalDate", j2sListLocalDate, s2jLocalDate)
  lazy val resSeqLocalTime     : ResSeq[LocalTime]      = ResSeq("LocalTime", j2sListLocalTime, s2jLocalTime)
  lazy val resSeqLocalDateTime : ResSeq[LocalDateTime]  = ResSeq("LocalDateTime", j2sListLocalDateTime, s2jLocalDateTime)
  lazy val resSeqOffsetTime    : ResSeq[OffsetTime]     = ResSeq("OffsetTime", j2sListOffsetTime, s2jOffsetTime)
  lazy val resSeqOffsetDateTime: ResSeq[OffsetDateTime] = ResSeq("OffsetDateTime", j2sListOffsetDateTime, s2jOffsetDateTime)
  lazy val resSeqZonedDateTime : ResSeq[ZonedDateTime]  = ResSeq("ZonedDateTime", j2sListZonedDateTime, s2jZonedDateTime)
  lazy val resSeqUUID          : ResSeq[UUID]           = ResSeq("UUID", j2sListUUID, s2jUUID)
  lazy val resSeqURI           : ResSeq[URI]            = ResSeq("URI", j2sListURI, s2jURI)
  lazy val resSeqByte          : ResSeq[Byte]           = ResSeq("Byte", j2sListByte, s2jByte)
  lazy val resSeqShort         : ResSeq[Short]          = ResSeq("Short", j2sListShort, s2jShort)
  lazy val resSeqChar          : ResSeq[Char]           = ResSeq("Char", j2sListChar, s2jChar)


  private def optSeq2sID = (v: AnyRef) => {
    val set = v.asInstanceOf[Array[_]]
    if (set.iterator.next().asInstanceOf[jList[_]].isEmpty)
      Option.empty[Array[Long]]
    else
      Some(
        set.flatMap(
          _.asInstanceOf[jList[_]].asScala.map(
            _.asInstanceOf[jMap[_, _]].values.iterator.next
          )
        )
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
    j2sOptList: AnyRef => AnyRef,
  )

  lazy val resOptSeqId            : ResSeqOpt[Long]           = ResSeqOpt(j2sOptListId)
  lazy val resOptSeqString        : ResSeqOpt[String]         = ResSeqOpt(j2sOptListString)
  lazy val resOptSeqInt           : ResSeqOpt[Int]            = ResSeqOpt(j2sOptListInt)
  lazy val resOptSeqLong          : ResSeqOpt[Long]           = ResSeqOpt(j2sOptListLong)
  lazy val resOptSeqFloat         : ResSeqOpt[Float]          = ResSeqOpt(j2sOptListFloat)
  lazy val resOptSeqDouble        : ResSeqOpt[Double]         = ResSeqOpt(j2sOptListDouble)
  lazy val resOptSeqBoolean       : ResSeqOpt[Boolean]        = ResSeqOpt(j2sOptListBoolean)
  lazy val resOptSeqBigInt        : ResSeqOpt[BigInt]         = ResSeqOpt(j2sOptListBigInt)
  lazy val resOptSeqBigDecimal    : ResSeqOpt[BigDecimal]     = ResSeqOpt(j2sOptListBigDecimal)
  lazy val resOptSeqDate          : ResSeqOpt[Date]           = ResSeqOpt(j2sOptListDate)
  lazy val resOptSeqDuration      : ResSeqOpt[Duration]       = ResSeqOpt(j2sOptListDuration)
  lazy val resOptSeqInstant       : ResSeqOpt[Instant]        = ResSeqOpt(j2sOptListInstant)
  lazy val resOptSeqLocalDate     : ResSeqOpt[LocalDate]      = ResSeqOpt(j2sOptListLocalDate)
  lazy val resOptSeqLocalTime     : ResSeqOpt[LocalTime]      = ResSeqOpt(j2sOptListLocalTime)
  lazy val resOptSeqLocalDateTime : ResSeqOpt[LocalDateTime]  = ResSeqOpt(j2sOptListLocalDateTime)
  lazy val resOptSeqOffsetTime    : ResSeqOpt[OffsetTime]     = ResSeqOpt(j2sOptListOffsetTime)
  lazy val resOptSeqOffsetDateTime: ResSeqOpt[OffsetDateTime] = ResSeqOpt(j2sOptListOffsetDateTime)
  lazy val resOptSeqZonedDateTime : ResSeqOpt[ZonedDateTime]  = ResSeqOpt(j2sOptListZonedDateTime)
  lazy val resOptSeqUUID          : ResSeqOpt[UUID]           = ResSeqOpt(j2sOptListUUID)
  lazy val resOptSeqURI           : ResSeqOpt[URI]            = ResSeqOpt(j2sOptListURI)
  lazy val resOptSeqByte          : ResSeqOpt[Byte]           = ResSeqOpt(j2sOptListByte)
  lazy val resOptSeqShort         : ResSeqOpt[Short]          = ResSeqOpt(j2sOptListShort)
  lazy val resOptSeqChar          : ResSeqOpt[Char]           = ResSeqOpt(j2sOptListChar)


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