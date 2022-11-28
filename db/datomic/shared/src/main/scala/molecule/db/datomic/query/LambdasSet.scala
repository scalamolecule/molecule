package molecule.db.datomic.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions


object LambdasSet extends LambdasSet
trait LambdasSet extends ResolveBase with JavaConversions {

  protected lazy val j2sSetString    : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetInt       : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetLong      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetFloat     : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetDouble    : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBoolean   : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBigInt    : AnyRef => AnyRef = {
    case v: jBigInt => Set(BigInt(v))
    case v          => Set(BigInt(v.toString))
  }
  protected lazy val j2sSetBigDecimal: AnyRef => AnyRef =
    (v: AnyRef) => Set(BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val j2sSetDate      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetUUID      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetURI       : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetByte      : AnyRef => AnyRef = {
    case v: Integer => Set(v.toByte)
    case v: jLong   => Set(v.toByte)
  }
  protected lazy val j2sSetShort     : AnyRef => AnyRef = {
    case v: Integer => Set(v.toShort)
    case v: jLong   => Set(v.toShort)
  }
  protected lazy val j2sSetChar      : AnyRef => AnyRef =
    (v: AnyRef) => Set(v.asInstanceOf[String].charAt(0))

  private lazy val set2setString    : AnyRef => AnyRef = set2set
  private lazy val set2setInt       : AnyRef => AnyRef = set2set
  private lazy val set2setLong      : AnyRef => AnyRef = set2set
  private lazy val set2setFloat     : AnyRef => AnyRef = set2set
  private lazy val set2setDouble    : AnyRef => AnyRef = set2set
  private lazy val set2setBoolean   : AnyRef => AnyRef = set2set
  private lazy val set2setBigInt    : AnyRef => AnyRef = set2set((v: AnyRef) => BigInt(v.toString))
  private lazy val set2setBigDecimal: AnyRef => AnyRef = set2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val set2setDate      : AnyRef => AnyRef = set2set
  private lazy val set2setUUID      : AnyRef => AnyRef = set2set
  private lazy val set2setURI       : AnyRef => AnyRef = set2set
  private lazy val set2setByte      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val set2setShort     : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val set2setChar      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def set2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

  private def set2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  private lazy val set2setsString    : AnyRef => AnyRef = set2setsT[String]
  private lazy val set2setsInt       : AnyRef => AnyRef = set2setsT[Int]
  private lazy val set2setsLong      : AnyRef => AnyRef = set2setsT[Long]
  private lazy val set2setsFloat     : AnyRef => AnyRef = set2setsT[Float]
  private lazy val set2setsDouble    : AnyRef => AnyRef = set2setsT[Double]
  private lazy val set2setsBoolean   : AnyRef => AnyRef = set2setsT[Boolean]
  private lazy val set2setsBigInt    : AnyRef => AnyRef = set2setsT[BigInt]((v: Any) => BigInt(v.toString))
  private lazy val set2setsBigDecimal: AnyRef => AnyRef = set2setsT[BigDecimal]((v: Any) => BigDecimal(v.toString))
  private lazy val set2setsDate      : AnyRef => AnyRef = set2setsT[Date]
  private lazy val set2setsUUID      : AnyRef => AnyRef = set2setsT[UUID]
  private lazy val set2setsURI       : AnyRef => AnyRef = set2setsT[URI]
  private lazy val set2setsByte      : AnyRef => AnyRef = set2setsT[Byte]((v: Any) => v.asInstanceOf[Integer].toByte)
  private lazy val set2setsShort     : AnyRef => AnyRef = set2setsT[Short]((v: Any) => v.asInstanceOf[Integer].toShort)
  private lazy val set2setsChar      : AnyRef => AnyRef = set2setsT[Char]((v: Any) => v.asInstanceOf[String].charAt(0))

  private def set2setsT[T]: AnyRef => AnyRef = (v: AnyRef) => {
    var sets = Set.empty[Set[T]]
    var set  = Set.empty[T]
    v.asInstanceOf[jSet[_]].forEach { row =>
      set = Set.empty[T]
      row.asInstanceOf[jSet[_]].forEach(v => set = set + v.asInstanceOf[T])
      sets += set
    }
    sets
  }
  private def set2setsT[T](value: Any => T): AnyRef => AnyRef = (v: AnyRef) => {
    var sets = Set.empty[Set[T]]
    var set  = Set.empty[T]
    v.asInstanceOf[jSet[_]].forEach { row =>
      set = Set.empty[T]
      row.asInstanceOf[jSet[_]].forEach(v => set = set + value(v))
      sets += set
    }
    sets
  }


  private lazy val vector2setString    : AnyRef => AnyRef = vector2set
  private lazy val vector2setInt       : AnyRef => AnyRef = vector2set
  private lazy val vector2setLong      : AnyRef => AnyRef = vector2set
  private lazy val vector2setFloat     : AnyRef => AnyRef = vector2set
  private lazy val vector2setDouble    : AnyRef => AnyRef = vector2set
  private lazy val vector2setBoolean   : AnyRef => AnyRef = vector2set
  private lazy val vector2setBigInt    : AnyRef => AnyRef = vector2set((v: AnyRef) => BigInt(v.toString))
  private lazy val vector2setBigDecimal: AnyRef => AnyRef = vector2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val vector2setDate      : AnyRef => AnyRef = vector2set
  private lazy val vector2setUUID      : AnyRef => AnyRef = vector2set
  private lazy val vector2setURI       : AnyRef => AnyRef = vector2set
  private lazy val vector2setByte      : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val vector2setShort     : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val vector2setChar      : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))


  case class ResSet[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
    j2sSet: AnyRef => AnyRef
  )

  lazy val resSetString    : ResSet[String]     = ResSet("String", dString, s2jString, set2setString, set2setsString, vector2setString, j2sSetString)
  lazy val resSetInt       : ResSet[Int]        = ResSet("Int", dInt, s2jInt, set2setInt, set2setsInt, vector2setInt, j2sSetInt)
  lazy val resSetLong      : ResSet[Long]       = ResSet("Long", dLong, s2jLong, set2setLong, set2setsLong, vector2setLong, j2sSetLong)
  lazy val resSetFloat     : ResSet[Float]      = ResSet("Float", dFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat, j2sSetFloat)
  lazy val resSetDouble    : ResSet[Double]     = ResSet("Double", dDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble, j2sSetDouble)
  lazy val resSetBoolean   : ResSet[Boolean]    = ResSet("Boolean", dBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean, j2sSetBoolean)
  lazy val resSetBigInt    : ResSet[BigInt]     = ResSet("BigInt", dBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt, j2sSetBigInt)
  lazy val resSetBigDecimal: ResSet[BigDecimal] = ResSet("BigDecimal", dBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal, j2sSetBigDecimal)
  lazy val resSetDate      : ResSet[Date]       = ResSet("Date", dDate, s2jDate, set2setDate, set2setsDate, vector2setDate, j2sSetDate)
  lazy val resSetUUID      : ResSet[UUID]       = ResSet("UUID", dUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID, j2sSetUUID)
  lazy val resSetURI       : ResSet[URI]        = ResSet("URI", dURI, s2jURI, set2setURI, set2setsURI, vector2setURI, j2sSetURI)
  lazy val resSetByte      : ResSet[Byte]       = ResSet("Byte", dByte, s2jByte, set2setByte, set2setsByte, vector2setByte, j2sSetByte)
  lazy val resSetShort     : ResSet[Short]      = ResSet("Short", dShort, s2jShort, set2setShort, set2setsShort, vector2setShort, j2sSetShort)
  lazy val resSetChar      : ResSet[Char]       = ResSet("Char", dChar, s2jChar, set2setChar, set2setsChar, vector2setChar, j2sSetChar)


  private lazy val j2sOpSetString = (v: AnyRef) => v match {
    case null            => Option.empty[Set[String]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String])) // attr_?(<expr>))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String]).toSet) // attr_?
  }

  private lazy val j2sOpSetInt = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Int]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toInt).toSet)
  }

  private lazy val j2sOpSetLong = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Long]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: Long => Some(list.map(_.asInstanceOf[Long]).toSet)
        // Refs
        case _: jMap[_, _] => Some(list.map(_.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Long]).toSet)
      }

  }

  private lazy val j2sOpSetFloat = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Float]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Float]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Float]).toSet)
  }

  private lazy val j2sOpSetDouble = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Double]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Double]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Double]).toSet)
  }

  private lazy val j2sOpSetBoolean = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Boolean]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Boolean]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Boolean]).toSet)
  }

  private lazy val j2sOpSetBigInt = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigInt]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }

  private lazy val j2sOpSetBigDecimal = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigDecimal]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }

  private lazy val j2sOpSetDate = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Date]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Date]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Date]).toSet)
  }

  private lazy val j2sOpSetUUID = (v: AnyRef) => v match {
    case null            => Option.empty[Set[UUID]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[UUID]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[UUID]).toSet)
  }

  private lazy val j2sOpSetURI = (v: AnyRef) => v match {
    case null            => Option.empty[Set[URI]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[URI]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[URI]).toSet)
  }

  private lazy val j2sOpSetByte = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Byte]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toByte))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toByte).toSet)
  }

  private lazy val j2sOpSetShort = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Short]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toShort))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toShort).toSet)
  }

  private lazy val j2sOpSetChar = (v: AnyRef) => v match {
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

  lazy val resOptSetString    : ResSetOpt[String]     = ResSetOpt("String", dString, s2jString, j2sOpSetString)
  lazy val resOptSetInt       : ResSetOpt[Int]        = ResSetOpt("Int", dInt, s2jInt, j2sOpSetInt)
  lazy val resOptSetLong      : ResSetOpt[Long]       = ResSetOpt("Long", dLong, s2jLong, j2sOpSetLong)
  lazy val resOptSetFloat     : ResSetOpt[Float]      = ResSetOpt("Float", dFloat, s2jFloat, j2sOpSetFloat)
  lazy val resOptSetDouble    : ResSetOpt[Double]     = ResSetOpt("Double", dDouble, s2jDouble, j2sOpSetDouble)
  lazy val resOptSetBoolean   : ResSetOpt[Boolean]    = ResSetOpt("Boolean", dBoolean, s2jBoolean, j2sOpSetBoolean)
  lazy val resOptSetBigInt    : ResSetOpt[BigInt]     = ResSetOpt("BigInt", dBigInt, s2jBigInt, j2sOpSetBigInt)
  lazy val resOptSetBigDecimal: ResSetOpt[BigDecimal] = ResSetOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOpSetBigDecimal)
  lazy val resOptSetDate      : ResSetOpt[Date]       = ResSetOpt("Date", dDate, s2jDate, j2sOpSetDate)
  lazy val resOptSetUUID      : ResSetOpt[UUID]       = ResSetOpt("UUID", dUUID, s2jUUID, j2sOpSetUUID)
  lazy val resOptSetURI       : ResSetOpt[URI]        = ResSetOpt("URI", dURI, s2jURI, j2sOpSetURI)
  lazy val resOptSetByte      : ResSetOpt[Byte]       = ResSetOpt("Byte", dByte, s2jByte, j2sOpSetByte)
  lazy val resOptSetShort     : ResSetOpt[Short]      = ResSetOpt("Short", dShort, s2jShort, j2sOpSetShort)
  lazy val resOptSetChar      : ResSetOpt[Char]       = ResSetOpt("Char", dChar, s2jChar, j2sOpSetChar)



  // Nested opt ---------------------------------------------------------------------

  lazy val it2SetString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.toString).toSet
    case other        => unexpectedValue(other)
  }
  lazy val it2SetInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.toString.toInt).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Long]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Float]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Double]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Date]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[UUID]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[URI]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet
    case other        => unexpectedValue(other)
  }


  lazy val it2OptSetString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String]).toSet)
  }
  lazy val it2OptSetInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.toString.toInt).toSet)
  }
  lazy val it2OptSetLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Long]).toSet)
  }
  lazy val it2OptSetFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Float]).toSet)
  }
  lazy val it2OptSetDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Double]).toSet)
  }
  lazy val it2OptSetBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet)
  }
  lazy val it2OptSetBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }
  lazy val it2OptSetBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }
  lazy val it2OptSetDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Date]).toSet)
  }
  lazy val it2OptSetUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[UUID]).toSet)
  }
  lazy val it2OptSetURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[URI]).toSet)
  }
  lazy val it2OptSetByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet)
  }
  lazy val it2OptSetShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet)
  }
  lazy val it2OptSetChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet)
  }
}