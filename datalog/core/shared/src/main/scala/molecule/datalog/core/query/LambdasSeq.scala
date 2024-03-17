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

  protected lazy val j2sArrayId            : AnyRef => AnyRef = (v: AnyRef) => Array(v.toString)
  protected lazy val j2sArrayString        : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  // Datomic can return both Integer or Long
  protected lazy val j2sArrayInt           : AnyRef => AnyRef = (v: AnyRef) => Array(v.toString.toInt)
  protected lazy val j2sArrayLong          : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayFloat         : AnyRef => AnyRef = {
    case v: jFloat  => Array(v.asInstanceOf[AnyRef])
    case v: jDouble => Array(v.toFloat.asInstanceOf[AnyRef])
  }
  protected lazy val j2sArrayDouble        : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayBoolean       : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayBigInt        : AnyRef => AnyRef = {
    case v: jBigInt => Array(BigInt(v))
    case v          => Array(BigInt(v.toString))
  }
  protected lazy val j2sArrayBigDecimal    : AnyRef => AnyRef =
    (v: AnyRef) => Array(BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val j2sArrayDate          : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayDuration      : AnyRef => AnyRef = (v: AnyRef) => Array(Duration.parse(v.toString))
  protected lazy val j2sArrayInstant       : AnyRef => AnyRef = (v: AnyRef) => Array(Instant.parse(v.toString))
  protected lazy val j2sArrayLocalDate     : AnyRef => AnyRef = (v: AnyRef) => Array(LocalDate.parse(v.toString))
  protected lazy val j2sArrayLocalTime     : AnyRef => AnyRef = (v: AnyRef) => Array(LocalTime.parse(v.toString))
  protected lazy val j2sArrayLocalDateTime : AnyRef => AnyRef = (v: AnyRef) => Array(LocalDateTime.parse(v.toString))
  protected lazy val j2sArrayOffsetTime    : AnyRef => AnyRef = (v: AnyRef) => Array(OffsetTime.parse(v.toString))
  protected lazy val j2sArrayOffsetDateTime: AnyRef => AnyRef = (v: AnyRef) => Array(OffsetDateTime.parse(v.toString))
  protected lazy val j2sArrayZonedDateTime : AnyRef => AnyRef = (v: AnyRef) => Array(ZonedDateTime.parse(v.toString))
  protected lazy val j2sArrayUUID          : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayURI           : AnyRef => AnyRef = (v: AnyRef) => Array(v)
  protected lazy val j2sArrayByte          : AnyRef => AnyRef = {
    case v: Integer => Array(v.toByte)
    case v: jLong   => Array(v.toByte)
  }
  protected lazy val j2sArrayShort         : AnyRef => AnyRef = {
    case v: Integer => Array(v.toShort)
    case v: jLong   => Array(v.toShort)
  }
  protected lazy val j2sArrayChar          : AnyRef => AnyRef =
    (v: AnyRef) => Array(v.asInstanceOf[String].charAt(0))

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
    (v: AnyRef) => v.asInstanceOf[Array[_]].toArray

  private def jset2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[Array[_]].toArray //.map(value)


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

  private def jset2setsT[T: ClassTag](value: Any => T): AnyRef => AnyRef = (v: AnyRef) => {
    var sets = Array.empty[Array[T]]
    var set  = Array.empty[T]
    v.asInstanceOf[Array[_]].foreach { row =>
      set = Array.empty[T]
      row.asInstanceOf[Array[_]].foreach(v => set = set :+ value(v))
      //      sets += set
      ???
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

  private lazy val jList2listId            : AnyRef => List[String]         = (seq: AnyRef) => jList2list(_.asInstanceOf[String]).apply(seq)
  private lazy val jList2listString        : AnyRef => List[String]         = (seq: AnyRef) => jList2list(_.asInstanceOf[String]).apply(seq)
  private lazy val jList2listInt           : AnyRef => List[Int]            = (seq: AnyRef) => jList2list(_.toString.toInt).apply(seq)
  private lazy val jList2listLong          : AnyRef => List[Long]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Long]).apply(seq)
  private lazy val jList2listFloat         : AnyRef => List[Float]          = (seq: AnyRef) => jList2list(_.asInstanceOf[Float]).apply(seq)
  private lazy val jList2listDouble        : AnyRef => List[Double]         = (seq: AnyRef) => jList2list(_.asInstanceOf[Double]).apply(seq)
  private lazy val jList2listBoolean       : AnyRef => List[Boolean]        = (seq: AnyRef) => jList2list(_.asInstanceOf[Boolean]).apply(seq)
  private lazy val jList2listBigInt        : AnyRef => List[BigInt]         = (seq: AnyRef) => jList2list((v: Any) => BigInt(v.toString)).apply(seq)
  private lazy val jList2listBigDecimal    : AnyRef => List[BigDecimal]     = (seq: AnyRef) => jList2list((v: Any) => BigDecimal(v.toString)).apply(seq)
  private lazy val jList2listDate          : AnyRef => List[Date]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Date]).apply(seq)
  private lazy val jList2listDuration      : AnyRef => List[Duration]       = (seq: AnyRef) => jList2list((v: Any) => Duration.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listInstant       : AnyRef => List[Instant]        = (seq: AnyRef) => jList2list((v: Any) => Instant.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listLocalDate     : AnyRef => List[LocalDate]      = (seq: AnyRef) => jList2list((v: Any) => LocalDate.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listLocalTime     : AnyRef => List[LocalTime]      = (seq: AnyRef) => jList2list((v: Any) => LocalTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listLocalDateTime : AnyRef => List[LocalDateTime]  = (seq: AnyRef) => jList2list((v: Any) => LocalDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listOffsetTime    : AnyRef => List[OffsetTime]     = (seq: AnyRef) => jList2list((v: Any) => OffsetTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listOffsetDateTime: AnyRef => List[OffsetDateTime] = (seq: AnyRef) => jList2list((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listZonedDateTime : AnyRef => List[ZonedDateTime]  = (seq: AnyRef) => jList2list((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])).apply(seq)
  private lazy val jList2listUUID          : AnyRef => List[UUID]           = (seq: AnyRef) => jList2list(_.asInstanceOf[UUID]).apply(seq)
  private lazy val jList2listURI           : AnyRef => List[URI]            = (seq: AnyRef) => jList2list(_.asInstanceOf[URI]).apply(seq)
  private lazy val jList2listByte          : AnyRef => List[Byte]           = (seq: AnyRef) => jList2list(_.asInstanceOf[Integer].toByte).apply(seq)
  private lazy val jList2listShort         : AnyRef => List[Short]          = (seq: AnyRef) => jList2list(_.asInstanceOf[Integer].toShort).apply(seq)
  private lazy val jList2listChar          : AnyRef => List[Char]           = (seq: AnyRef) => jList2list(_.asInstanceOf[String].charAt(0)).apply(seq)

  //  private lazy val pairs2listId            : AnyRef => List[String]         = (pairs: AnyRef) => pairs2list(_.asInstanceOf[String]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listString        : AnyRef => List[String]         = (pairs: AnyRef) => pairs2list(_.asInstanceOf[String]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listInt           : AnyRef => List[Int]            = (pairs: AnyRef) => pairs2list(_.toString.toInt).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listLong          : AnyRef => List[Long]           = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Long]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listFloat         : AnyRef => List[Float]          = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Float]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listDouble        : AnyRef => List[Double]         = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Double]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listBoolean       : AnyRef => List[Boolean]        = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Boolean]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listBigInt        : AnyRef => List[BigInt]         = (pairs: AnyRef) => pairs2list((v: Any) => BigInt(v.toString)).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listBigDecimal    : AnyRef => List[BigDecimal]     = (pairs: AnyRef) => pairs2list((v: Any) => BigDecimal(v.toString)).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listDate          : AnyRef => List[Date]           = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Date]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listDuration      : AnyRef => List[Duration]       = (pairs: AnyRef) => pairs2list((v: Any) => Duration.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listInstant       : AnyRef => List[Instant]        = (pairs: AnyRef) => pairs2list((v: Any) => Instant.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listLocalDate     : AnyRef => List[LocalDate]      = (pairs: AnyRef) => pairs2list((v: Any) => LocalDate.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listLocalTime     : AnyRef => List[LocalTime]      = (pairs: AnyRef) => pairs2list((v: Any) => LocalTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listLocalDateTime : AnyRef => List[LocalDateTime]  = (pairs: AnyRef) => pairs2list((v: Any) => LocalDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listOffsetTime    : AnyRef => List[OffsetTime]     = (pairs: AnyRef) => pairs2list((v: Any) => OffsetTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listOffsetDateTime: AnyRef => List[OffsetDateTime] = (pairs: AnyRef) => pairs2list((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listZonedDateTime : AnyRef => List[ZonedDateTime]  = (pairs: AnyRef) => pairs2list((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listUUID          : AnyRef => List[UUID]           = (pairs: AnyRef) => pairs2list(_.asInstanceOf[UUID]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listURI           : AnyRef => List[URI]            = (pairs: AnyRef) => pairs2list(_.asInstanceOf[URI]).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listByte          : AnyRef => List[Byte]           = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Integer].toByte).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listShort         : AnyRef => List[Short]          = (pairs: AnyRef) => pairs2list(_.asInstanceOf[Integer].toShort).apply(pairs).sortBy(_._1).map(_._2)
  //  private lazy val pairs2listChar          : AnyRef => List[Char]           = (pairs: AnyRef) => pairs2list(_.asInstanceOf[String].charAt(0)).apply(pairs).sortBy(_._1).map(_._2)


  private def jList2list[T](decode: Any => T): AnyRef => List[T] = (seq: AnyRef) => {
    seq.asInstanceOf[jList[_]].toArray.toList.map(decode)
  }
  //  private def pairs2list[T](value: Any => T): AnyRef => List[(Int, T)] = (pairs: AnyRef) => {
  //    pairs.asInstanceOf[jSet[_]].toArray.toList.map {
  //      case pair: jList[_] => (pair.get(0).asInstanceOf[jInteger].toInt, value(pair.get(1)))
  //    }
  //  }

  case class ResSeq[T](
    jList2list: AnyRef => List[T],
    //    pairs2list: AnyRef => List[T],

    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    set2sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
    j2sArray: AnyRef => AnyRef
  )

  lazy val resSeqId            : ResSeq[String]         = ResSeq(jList2listId, "String", dId, s2jId, set2setId, set2setsId, vector2setId, j2sArrayId)
  lazy val resSeqString        : ResSeq[String]         = ResSeq(jList2listString, "String", dString, s2jString, set2setString, set2setsString, vector2setString, j2sArrayString)
  lazy val resSeqInt           : ResSeq[Int]            = ResSeq(jList2listInt, "Int", dInt, s2jInt, set2setInt, set2setsInt, vector2setInt, j2sArrayInt)
  lazy val resSeqLong          : ResSeq[Long]           = ResSeq(jList2listLong, "Long", dLong, s2jLong, set2setLong, set2setsLong, vector2setLong, j2sArrayLong)
  lazy val resSeqFloat         : ResSeq[Float]          = ResSeq(jList2listFloat, "Float", dFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat, j2sArrayFloat)
  lazy val resSeqDouble        : ResSeq[Double]         = ResSeq(jList2listDouble, "Double", dDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble, j2sArrayDouble)
  lazy val resSeqBoolean       : ResSeq[Boolean]        = ResSeq(jList2listBoolean, "Boolean", dBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean, j2sArrayBoolean)
  lazy val resSeqBigInt        : ResSeq[BigInt]         = ResSeq(jList2listBigInt, "BigInt", dBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt, j2sArrayBigInt)
  lazy val resSeqBigDecimal    : ResSeq[BigDecimal]     = ResSeq(jList2listBigDecimal, "BigDecimal", dBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal, j2sArrayBigDecimal)
  lazy val resSeqDate          : ResSeq[Date]           = ResSeq(jList2listDate, "Date", dDate, s2jDate, set2setDate, set2setsDate, vector2setDate, j2sArrayDate)
  lazy val resSeqDuration      : ResSeq[Duration]       = ResSeq(jList2listDuration, "Duration", dDuration, s2jDuration, set2setDuration, set2setsDuration, vector2setDuration, j2sArrayDuration)
  lazy val resSeqInstant       : ResSeq[Instant]        = ResSeq(jList2listInstant, "Instant", dInstant, s2jInstant, set2setInstant, set2setsInstant, vector2setInstant, j2sArrayInstant)
  lazy val resSeqLocalDate     : ResSeq[LocalDate]      = ResSeq(jList2listLocalDate, "LocalDate", dLocalDate, s2jLocalDate, set2setLocalDate, set2setsLocalDate, vector2setLocalDate, j2sArrayLocalDate)
  lazy val resSeqLocalTime     : ResSeq[LocalTime]      = ResSeq(jList2listLocalTime, "LocalTime", dLocalTime, s2jLocalTime, set2setLocalTime, set2setsLocalTime, vector2setLocalTime, j2sArrayLocalTime)
  lazy val resSeqLocalDateTime : ResSeq[LocalDateTime]  = ResSeq(jList2listLocalDateTime, "LocalDateTime", dLocalDateTime, s2jLocalDateTime, set2setLocalDateTime, set2setsLocalDateTime, vector2setLocalDateTime, j2sArrayLocalDateTime)
  lazy val resSeqOffsetTime    : ResSeq[OffsetTime]     = ResSeq(jList2listOffsetTime, "OffsetTime", dOffsetTime, s2jOffsetTime, set2setOffsetTime, set2setsOffsetTime, vector2setOffsetTime, j2sArrayOffsetTime)
  lazy val resSeqOffsetDateTime: ResSeq[OffsetDateTime] = ResSeq(jList2listOffsetDateTime, "OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, set2setOffsetDateTime, set2setsOffsetDateTime, vector2setOffsetDateTime, j2sArrayOffsetDateTime)
  lazy val resSeqZonedDateTime : ResSeq[ZonedDateTime]  = ResSeq(jList2listZonedDateTime, "ZonedDateTime", dZonedDateTime, s2jZonedDateTime, set2setZonedDateTime, set2setsZonedDateTime, vector2setZonedDateTime, j2sArrayZonedDateTime)
  lazy val resSeqUUID          : ResSeq[UUID]           = ResSeq(jList2listUUID, "UUID", dUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID, j2sArrayUUID)
  lazy val resSeqURI           : ResSeq[URI]            = ResSeq(jList2listURI, "URI", dURI, s2jURI, set2setURI, set2setsURI, vector2setURI, j2sArrayURI)
  lazy val resSeqByte          : ResSeq[Byte]           = ResSeq(jList2listByte, "Byte", dByte, s2jByte, set2setByte, set2setsByte, vector2setByte, j2sArrayByte)
  lazy val resSeqShort         : ResSeq[Short]          = ResSeq(jList2listShort, "Short", dShort, s2jShort, set2setShort, set2setsShort, vector2setShort, j2sArrayShort)
  lazy val resSeqChar          : ResSeq[Char]           = ResSeq(jList2listChar, "Char", dChar, s2jChar, set2setChar, set2setsChar, vector2setChar, j2sArrayChar)


  //  lazy val any2double: AnyRef => AnyRef = {
  //    ((v: AnyRef) => v.toString.toDouble).asInstanceOf[AnyRef => AnyRef]
  //  }

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

  private def it2Array[T: ClassTag](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(decode).toArray
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }

  lazy val it2ArrayId            : jIterator[_] => Any = it2Array(j2Id)
  lazy val it2ArrayString        : jIterator[_] => Any = it2Array(j2String)
  lazy val it2ArrayInt           : jIterator[_] => Any = it2Array(j2Int)
  lazy val it2ArrayLong          : jIterator[_] => Any = it2Array(j2Long)
  lazy val it2ArrayFloat         : jIterator[_] => Any = it2Array(j2Float)
  lazy val it2ArrayDouble        : jIterator[_] => Any = it2Array(j2Double)
  lazy val it2ArrayBoolean       : jIterator[_] => Any = it2Array(j2Boolean)
  lazy val it2ArrayBigInt        : jIterator[_] => Any = it2Array(j2BigInt)
  lazy val it2ArrayBigDecimal    : jIterator[_] => Any = it2Array(j2BigDecimal)
  lazy val it2ArrayDate          : jIterator[_] => Any = it2Array(j2Date)
  lazy val it2ArrayDuration      : jIterator[_] => Any = it2Array(j2Duration)
  lazy val it2ArrayInstant       : jIterator[_] => Any = it2Array(j2Instant)
  lazy val it2ArrayLocalDate     : jIterator[_] => Any = it2Array(j2LocalDate)
  lazy val it2ArrayLocalTime     : jIterator[_] => Any = it2Array(j2LocalTime)
  lazy val it2ArrayLocalDateTime : jIterator[_] => Any = it2Array(j2LocalDateTime)
  lazy val it2ArrayOffsetTime    : jIterator[_] => Any = it2Array(j2OffsetTime)
  lazy val it2ArrayOffsetDateTime: jIterator[_] => Any = it2Array(j2OffsetDateTime)
  lazy val it2ArrayZonedDateTime : jIterator[_] => Any = it2Array(j2ZonedDateTime)
  lazy val it2ArrayUUID          : jIterator[_] => Any = it2Array(j2UUID)
  lazy val it2ArrayURI           : jIterator[_] => Any = it2Array(j2URI)
  lazy val it2ArrayByte          : jIterator[_] => Any = it2Array(j2Byte)
  lazy val it2ArrayShort         : jIterator[_] => Any = it2Array(j2Short)
  lazy val it2ArrayChar          : jIterator[_] => Any = it2Array(j2Char)


  private def it2OptArray[T: ClassTag](decode: Any => T): jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(decode).toArray)
  }

  lazy val it2OptArrayId            : jIterator[_] => Any = it2OptArray(j2Id)
  lazy val it2OptArrayString        : jIterator[_] => Any = it2OptArray(j2String)
  lazy val it2OptArrayInt           : jIterator[_] => Any = it2OptArray(j2Int)
  lazy val it2OptArrayLong          : jIterator[_] => Any = it2OptArray(j2Long)
  lazy val it2OptArrayFloat         : jIterator[_] => Any = it2OptArray(j2Float)
  lazy val it2OptArrayDouble        : jIterator[_] => Any = it2OptArray(j2Double)
  lazy val it2OptArrayBoolean       : jIterator[_] => Any = it2OptArray(j2Boolean)
  lazy val it2OptArrayBigInt        : jIterator[_] => Any = it2OptArray(j2BigInt)
  lazy val it2OptArrayBigDecimal    : jIterator[_] => Any = it2OptArray(j2BigDecimal)
  lazy val it2OptArrayDate          : jIterator[_] => Any = it2OptArray(j2Date)
  lazy val it2OptArrayDuration      : jIterator[_] => Any = it2OptArray(j2Duration)
  lazy val it2OptArrayInstant       : jIterator[_] => Any = it2OptArray(j2Instant)
  lazy val it2OptArrayLocalDate     : jIterator[_] => Any = it2OptArray(j2LocalDate)
  lazy val it2OptArrayLocalTime     : jIterator[_] => Any = it2OptArray(j2LocalTime)
  lazy val it2OptArrayLocalDateTime : jIterator[_] => Any = it2OptArray(j2LocalDateTime)
  lazy val it2OptArrayOffsetTime    : jIterator[_] => Any = it2OptArray(j2OffsetTime)
  lazy val it2OptArrayOffsetDateTime: jIterator[_] => Any = it2OptArray(j2OffsetDateTime)
  lazy val it2OptArrayZonedDateTime : jIterator[_] => Any = it2OptArray(j2ZonedDateTime)
  lazy val it2OptArrayUUID          : jIterator[_] => Any = it2OptArray(j2UUID)
  lazy val it2OptArrayURI           : jIterator[_] => Any = it2OptArray(j2URI)
  lazy val it2OptArrayByte          : jIterator[_] => Any = it2OptArray(j2Byte)
  lazy val it2OptArrayShort         : jIterator[_] => Any = it2OptArray(j2Short)
  lazy val it2OptArrayChar          : jIterator[_] => Any = it2OptArray(j2Char)
}