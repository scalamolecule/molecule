package molecule.datalog.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong, Integer => jInteger}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.time._
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions
import scala.reflect.ClassTag

trait LambdasArr extends ResolveBase with JavaConversions {

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

  private lazy val pairs2arrayId            : AnyRef => Array[String]         = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[String]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayString        : AnyRef => Array[String]         = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[String]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayInt           : AnyRef => Array[Int]            = (pairs: AnyRef) => pairs2scala(_.toString.toInt).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayLong          : AnyRef => Array[Long]           = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Long]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayFloat         : AnyRef => Array[Float]          = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Float]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayDouble        : AnyRef => Array[Double]         = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Double]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayBoolean       : AnyRef => Array[Boolean]        = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Boolean]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayBigInt        : AnyRef => Array[BigInt]         = (pairs: AnyRef) => pairs2scala((v: Any) => BigInt(v.toString)).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayBigDecimal    : AnyRef => Array[BigDecimal]     = (pairs: AnyRef) => pairs2scala((v: Any) => BigDecimal(v.toString)).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayDate          : AnyRef => Array[Date]           = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Date]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayDuration      : AnyRef => Array[Duration]       = (pairs: AnyRef) => pairs2scala((v: Any) => Duration.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayInstant       : AnyRef => Array[Instant]        = (pairs: AnyRef) => pairs2scala((v: Any) => Instant.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayLocalDate     : AnyRef => Array[LocalDate]      = (pairs: AnyRef) => pairs2scala((v: Any) => LocalDate.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayLocalTime     : AnyRef => Array[LocalTime]      = (pairs: AnyRef) => pairs2scala((v: Any) => LocalTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayLocalDateTime : AnyRef => Array[LocalDateTime]  = (pairs: AnyRef) => pairs2scala((v: Any) => LocalDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayOffsetTime    : AnyRef => Array[OffsetTime]     = (pairs: AnyRef) => pairs2scala((v: Any) => OffsetTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayOffsetDateTime: AnyRef => Array[OffsetDateTime] = (pairs: AnyRef) => pairs2scala((v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayZonedDateTime : AnyRef => Array[ZonedDateTime]  = (pairs: AnyRef) => pairs2scala((v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayUUID          : AnyRef => Array[UUID]           = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[UUID]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayURI           : AnyRef => Array[URI]            = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[URI]).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayByte          : AnyRef => Array[Byte]           = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Integer].toByte).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayShort         : AnyRef => Array[Short]          = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[Integer].toShort).apply(pairs).sortBy(_._1).map(_._2)
  private lazy val pairs2arrayChar          : AnyRef => Array[Char]           = (pairs: AnyRef) => pairs2scala(_.asInstanceOf[String].charAt(0)).apply(pairs).sortBy(_._1).map(_._2)


  private def pairs2scala[T: ClassTag](value: Any => T): AnyRef => Array[(Int, T)] = (pairs: AnyRef) => {
    pairs.asInstanceOf[jSet[_]].toArray.map {
      case pair: jList[_] => (pair.get(0).asInstanceOf[jInteger].toInt, value(pair.get(1)))
    }
  }

  case class ResArr[T](
    pairs2array: AnyRef => Array[T],

    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    set2sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
    j2sArray: AnyRef => AnyRef
  )

  lazy val resArrId            : ResArr[String]         = ResArr(pairs2arrayId, "String", dId, s2jId, set2setId, set2setsId, vector2setId, j2sArrayId)
  lazy val resArrString        : ResArr[String]         = ResArr(pairs2arrayString, "String", dString, s2jString, set2setString, set2setsString, vector2setString, j2sArrayString)
  lazy val resArrInt           : ResArr[Int]            = ResArr(pairs2arrayInt, "Int", dInt, s2jInt, set2setInt, set2setsInt, vector2setInt, j2sArrayInt)
  lazy val resArrLong          : ResArr[Long]           = ResArr(pairs2arrayLong, "Long", dLong, s2jLong, set2setLong, set2setsLong, vector2setLong, j2sArrayLong)
  lazy val resArrFloat         : ResArr[Float]          = ResArr(pairs2arrayFloat, "Float", dFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat, j2sArrayFloat)
  lazy val resArrDouble        : ResArr[Double]         = ResArr(pairs2arrayDouble, "Double", dDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble, j2sArrayDouble)
  lazy val resArrBoolean       : ResArr[Boolean]        = ResArr(pairs2arrayBoolean, "Boolean", dBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean, j2sArrayBoolean)
  lazy val resArrBigInt        : ResArr[BigInt]         = ResArr(pairs2arrayBigInt, "BigInt", dBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt, j2sArrayBigInt)
  lazy val resArrBigDecimal    : ResArr[BigDecimal]     = ResArr(pairs2arrayBigDecimal, "BigDecimal", dBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal, j2sArrayBigDecimal)
  lazy val resArrDate          : ResArr[Date]           = ResArr(pairs2arrayDate, "Date", dDate, s2jDate, set2setDate, set2setsDate, vector2setDate, j2sArrayDate)
  lazy val resArrDuration      : ResArr[Duration]       = ResArr(pairs2arrayDuration, "Duration", dDuration, s2jDuration, set2setDuration, set2setsDuration, vector2setDuration, j2sArrayDuration)
  lazy val resArrInstant       : ResArr[Instant]        = ResArr(pairs2arrayInstant, "Instant", dInstant, s2jInstant, set2setInstant, set2setsInstant, vector2setInstant, j2sArrayInstant)
  lazy val resArrLocalDate     : ResArr[LocalDate]      = ResArr(pairs2arrayLocalDate, "LocalDate", dLocalDate, s2jLocalDate, set2setLocalDate, set2setsLocalDate, vector2setLocalDate, j2sArrayLocalDate)
  lazy val resArrLocalTime     : ResArr[LocalTime]      = ResArr(pairs2arrayLocalTime, "LocalTime", dLocalTime, s2jLocalTime, set2setLocalTime, set2setsLocalTime, vector2setLocalTime, j2sArrayLocalTime)
  lazy val resArrLocalDateTime : ResArr[LocalDateTime]  = ResArr(pairs2arrayLocalDateTime, "LocalDateTime", dLocalDateTime, s2jLocalDateTime, set2setLocalDateTime, set2setsLocalDateTime, vector2setLocalDateTime, j2sArrayLocalDateTime)
  lazy val resArrOffsetTime    : ResArr[OffsetTime]     = ResArr(pairs2arrayOffsetTime, "OffsetTime", dOffsetTime, s2jOffsetTime, set2setOffsetTime, set2setsOffsetTime, vector2setOffsetTime, j2sArrayOffsetTime)
  lazy val resArrOffsetDateTime: ResArr[OffsetDateTime] = ResArr(pairs2arrayOffsetDateTime, "OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, set2setOffsetDateTime, set2setsOffsetDateTime, vector2setOffsetDateTime, j2sArrayOffsetDateTime)
  lazy val resArrZonedDateTime : ResArr[ZonedDateTime]  = ResArr(pairs2arrayZonedDateTime, "ZonedDateTime", dZonedDateTime, s2jZonedDateTime, set2setZonedDateTime, set2setsZonedDateTime, vector2setZonedDateTime, j2sArrayZonedDateTime)
  lazy val resArrUUID          : ResArr[UUID]           = ResArr(pairs2arrayUUID, "UUID", dUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID, j2sArrayUUID)
  lazy val resArrURI           : ResArr[URI]            = ResArr(pairs2arrayURI, "URI", dURI, s2jURI, set2setURI, set2setsURI, vector2setURI, j2sArrayURI)
  lazy val resArrByte          : ResArr[Byte]           = ResArr(pairs2arrayByte, "Byte", dByte, s2jByte, set2setByte, set2setsByte, vector2setByte, j2sArrayByte)
  lazy val resArrShort         : ResArr[Short]          = ResArr(pairs2arrayShort, "Short", dShort, s2jShort, set2setShort, set2setsShort, vector2setShort, j2sArrayShort)
  lazy val resArrChar          : ResArr[Char]           = ResArr(pairs2arrayChar, "Char", dChar, s2jChar, set2setChar, set2setsChar, vector2setChar, j2sArrayChar)


  //  lazy val any2double: AnyRef => AnyRef = {
  //    ((v: AnyRef) => v.toString.toDouble).asInstanceOf[AnyRef => AnyRef]
  //  }

  private lazy val j2sOpArrayId = (v: AnyRef) => v match {
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

  private lazy val j2sOpArrayLong = (v: AnyRef) => v match {
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


  private def j2sOpArray[T: ClassTag](decode: Any => T) = (v: AnyRef) => {
    v match {
      case null            => Option.empty[Array[String]]
      case set: Array[_]   => Some(set.map(decode)) // attr_?(<expr>))
      case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(decode).toArray) // attr_?
    }
  }

  //  private lazy val j2sOpArrayId             = j2sOpArray(j2Id)
  private lazy val j2sOpArrayString         = j2sOpArray(j2String)
  private lazy val j2sOpArrayInt            = j2sOpArray(j2Int)
  //  private lazy val j2sOpArrayLong           = j2sOpArray(j2Long)
  private lazy val j2sOpArrayFloat          = j2sOpArray(j2Float)
  private lazy val j2sOpArrayDouble         = j2sOpArray(j2Double)
  private lazy val j2sOpArrayBoolean        = j2sOpArray(j2Boolean)
  private lazy val j2sOpArrayBigInt         = j2sOpArray(j2BigInt)
  private lazy val j2sOpArrayBigDecimal     = j2sOpArray(j2BigDecimal)
  private lazy val j2sOpArrayDate           = j2sOpArray(j2Date)
  private lazy val j2sOpArrayDuration       = j2sOpArray(j2Duration)
  private lazy val j2sOpArrayInstant        = j2sOpArray(j2Instant)
  private lazy val j2sOpArrayLocalDate      = j2sOpArray(j2LocalDate)
  private lazy val j2sOpArrayLocalTime      = j2sOpArray(j2LocalTime)
  private lazy val j2sOpArrayLocalDateTime  = j2sOpArray(j2LocalDateTime)
  private lazy val j2sOpArrayOffsetTime     = j2sOpArray(j2OffsetTime)
  private lazy val j2sOpArrayOffsetDateTime = j2sOpArray(j2OffsetDateTime)
  private lazy val j2sOpArrayZonedDateTime  = j2sOpArray(j2ZonedDateTime)
  private lazy val j2sOpArrayUUID           = j2sOpArray(j2UUID)
  private lazy val j2sOpArrayURI            = j2sOpArray(j2URI)
  private lazy val j2sOpArrayByte           = j2sOpArray(j2Byte)
  private lazy val j2sOpArrayShort          = j2sOpArray(j2Short)
  private lazy val j2sOpArrayChar           = j2sOpArray(j2Char)


  private def optAttr2sOptArrayID = (v: AnyRef) => {
    val set = v.asInstanceOf[Array[_]]
    if (set.iterator.next.asInstanceOf[jList[_]].isEmpty)
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

  private def optAttr2sOptArray[T: ClassTag](decode: Any => T) = (v: AnyRef) => {
    //    val set = v.asInstanceOf[Array[_]]

    //    println(" B " + set)

    val set = v.asInstanceOf[Array[_]].flatMap(_.asInstanceOf[jList[_]].asScala.map(decode))

    if (set.isEmpty) Option.empty[Array[T]] else Some(set)

    //    println(" C " + coalescedArray)
    //    if (set.iterator.next.asInstanceOf[jList[_]].isEmpty)
    //      Option.empty[Array[T]]
    //    else
    //      Some(set.asScala.flatMap(_.asInstanceOf[jList[_]].asScala.map(decode)).toArray)
    //
    //    set.asScala.flatMap(_.asInstanceOf[jList[_]].asScala.map(decode)).toArray match{
    //      case set if set.isEmpty => Option.empty[Array[T]]
    //      case set
    //    }
  }

  private lazy val jOptArrayAttr2sOptArrayId             = optAttr2sOptArrayID
  private lazy val jOptArrayAttr2sOptArrayString         = optAttr2sOptArray(j2String)
  private lazy val jOptArrayAttr2sOptArrayInt            = optAttr2sOptArray(j2Int)
  private lazy val jOptArrayAttr2sOptArrayLong           = optAttr2sOptArray(j2Long)
  private lazy val jOptArrayAttr2sOptArrayFloat          = optAttr2sOptArray(j2Float)
  private lazy val jOptArrayAttr2sOptArrayDouble         = optAttr2sOptArray(j2Double)
  private lazy val jOptArrayAttr2sOptArrayBoolean        = optAttr2sOptArray(j2Boolean)
  private lazy val jOptArrayAttr2sOptArrayBigInt         = optAttr2sOptArray(j2BigInt)
  private lazy val jOptArrayAttr2sOptArrayBigDecimal     = optAttr2sOptArray(j2BigDecimal)
  private lazy val jOptArrayAttr2sOptArrayDate           = optAttr2sOptArray(j2Date)
  private lazy val jOptArrayAttr2sOptArrayDuration       = optAttr2sOptArray(j2Duration)
  private lazy val jOptArrayAttr2sOptArrayInstant        = optAttr2sOptArray(j2Instant)
  private lazy val jOptArrayAttr2sOptArrayLocalDate      = optAttr2sOptArray(j2LocalDate)
  private lazy val jOptArrayAttr2sOptArrayLocalTime      = optAttr2sOptArray(j2LocalTime)
  private lazy val jOptArrayAttr2sOptArrayLocalDateTime  = optAttr2sOptArray(j2LocalDateTime)
  private lazy val jOptArrayAttr2sOptArrayOffsetTime     = optAttr2sOptArray(j2OffsetTime)
  private lazy val jOptArrayAttr2sOptArrayOffsetDateTime = optAttr2sOptArray(j2OffsetDateTime)
  private lazy val jOptArrayAttr2sOptArrayZonedDateTime  = optAttr2sOptArray(j2ZonedDateTime)
  private lazy val jOptArrayAttr2sOptArrayUUID           = optAttr2sOptArray(j2UUID)
  private lazy val jOptArrayAttr2sOptArrayURI            = optAttr2sOptArray(j2URI)
  private lazy val jOptArrayAttr2sOptArrayByte           = optAttr2sOptArray(j2Byte)
  private lazy val jOptArrayAttr2sOptArrayShort          = optAttr2sOptArray(j2Short)
  private lazy val jOptArrayAttr2sOptArrayChar           = optAttr2sOptArray(j2Char)


  case class ResArrOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    optAttr2s: AnyRef => AnyRef,
  )

  lazy val resOptArrId            : ResArrOpt[String]         = ResArrOpt("String", dId, s2jId, j2sOpArrayId, jOptArrayAttr2sOptArrayId)
  lazy val resOptArrString        : ResArrOpt[String]         = ResArrOpt("String", dString, s2jString, j2sOpArrayString, jOptArrayAttr2sOptArrayString)
  lazy val resOptArrInt           : ResArrOpt[Int]            = ResArrOpt("Int", dInt, s2jInt, j2sOpArrayInt, jOptArrayAttr2sOptArrayInt)
  lazy val resOptArrLong          : ResArrOpt[Long]           = ResArrOpt("Long", dLong, s2jLong, j2sOpArrayLong, jOptArrayAttr2sOptArrayLong)
  lazy val resOptArrFloat         : ResArrOpt[Float]          = ResArrOpt("Float", dFloat, s2jFloat, j2sOpArrayFloat, jOptArrayAttr2sOptArrayFloat)
  lazy val resOptArrDouble        : ResArrOpt[Double]         = ResArrOpt("Double", dDouble, s2jDouble, j2sOpArrayDouble, jOptArrayAttr2sOptArrayDouble)
  lazy val resOptArrBoolean       : ResArrOpt[Boolean]        = ResArrOpt("Boolean", dBoolean, s2jBoolean, j2sOpArrayBoolean, jOptArrayAttr2sOptArrayBoolean)
  lazy val resOptArrBigInt        : ResArrOpt[BigInt]         = ResArrOpt("BigInt", dBigInt, s2jBigInt, j2sOpArrayBigInt, jOptArrayAttr2sOptArrayBigInt)
  lazy val resOptArrBigDecimal    : ResArrOpt[BigDecimal]     = ResArrOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOpArrayBigDecimal, jOptArrayAttr2sOptArrayBigDecimal)
  lazy val resOptArrDate          : ResArrOpt[Date]           = ResArrOpt("Date", dDate, s2jDate, j2sOpArrayDate, jOptArrayAttr2sOptArrayDate)
  lazy val resOptArrDuration      : ResArrOpt[Duration]       = ResArrOpt("Duration", dDuration, s2jDuration, j2sOpArrayDuration, jOptArrayAttr2sOptArrayDuration)
  lazy val resOptArrInstant       : ResArrOpt[Instant]        = ResArrOpt("Instant", dInstant, s2jInstant, j2sOpArrayInstant, jOptArrayAttr2sOptArrayInstant)
  lazy val resOptArrLocalDate     : ResArrOpt[LocalDate]      = ResArrOpt("LocalDate", dLocalDate, s2jLocalDate, j2sOpArrayLocalDate, jOptArrayAttr2sOptArrayLocalDate)
  lazy val resOptArrLocalTime     : ResArrOpt[LocalTime]      = ResArrOpt("LocalTime", dLocalTime, s2jLocalTime, j2sOpArrayLocalTime, jOptArrayAttr2sOptArrayLocalTime)
  lazy val resOptArrLocalDateTime : ResArrOpt[LocalDateTime]  = ResArrOpt("LocalDateTime", dLocalDateTime, s2jLocalDateTime, j2sOpArrayLocalDateTime, jOptArrayAttr2sOptArrayLocalDateTime)
  lazy val resOptArrOffsetTime    : ResArrOpt[OffsetTime]     = ResArrOpt("OffsetTime", dOffsetTime, s2jOffsetTime, j2sOpArrayOffsetTime, jOptArrayAttr2sOptArrayOffsetTime)
  lazy val resOptArrOffsetDateTime: ResArrOpt[OffsetDateTime] = ResArrOpt("OffsetDateTime", dOffsetDateTime, s2jOffsetDateTime, j2sOpArrayOffsetDateTime, jOptArrayAttr2sOptArrayOffsetDateTime)
  lazy val resOptArrZonedDateTime : ResArrOpt[ZonedDateTime]  = ResArrOpt("ZonedDateTime", dZonedDateTime, s2jZonedDateTime, j2sOpArrayZonedDateTime, jOptArrayAttr2sOptArrayZonedDateTime)
  lazy val resOptArrUUID          : ResArrOpt[UUID]           = ResArrOpt("UUID", dUUID, s2jUUID, j2sOpArrayUUID, jOptArrayAttr2sOptArrayUUID)
  lazy val resOptArrURI           : ResArrOpt[URI]            = ResArrOpt("URI", dURI, s2jURI, j2sOpArrayURI, jOptArrayAttr2sOptArrayURI)
  lazy val resOptArrByte          : ResArrOpt[Byte]           = ResArrOpt("Byte", dByte, s2jByte, j2sOpArrayByte, jOptArrayAttr2sOptArrayByte)
  lazy val resOptArrShort         : ResArrOpt[Short]          = ResArrOpt("Short", dShort, s2jShort, j2sOpArrayShort, jOptArrayAttr2sOptArrayShort)
  lazy val resOptArrChar          : ResArrOpt[Char]           = ResArrOpt("Char", dChar, s2jChar, j2sOpArrayChar, jOptArrayAttr2sOptArrayChar)


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