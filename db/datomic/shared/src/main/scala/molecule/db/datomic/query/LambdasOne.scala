package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, Map => jMap, Set => jSet, List => jList}
import java.util.{ArrayList => jArrayList, List => jList, Map => jMap, Iterator => jIterator}

object LambdasOne extends ResolveBase {

  // Datomic Java to Scala
  protected lazy val j2sString    : AnyRef => AnyRef = identity
  protected lazy val j2sInt       : AnyRef => AnyRef = identity
  protected lazy val j2sLong      : AnyRef => AnyRef = identity
  protected lazy val j2sFloat     : AnyRef => AnyRef = identity
  protected lazy val j2sDouble    : AnyRef => AnyRef = identity
  protected lazy val j2sBoolean   : AnyRef => AnyRef = identity
  protected lazy val j2sBigInt    : AnyRef => AnyRef = {
    case v: jBigInt => BigInt(v)
    case v          => BigInt(v.toString)
  }
  protected lazy val j2sBigDecimal: AnyRef => AnyRef =
    (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal])
  protected lazy val j2sDate      : AnyRef => AnyRef = identity
  protected lazy val j2sUUID      : AnyRef => AnyRef = identity
  protected lazy val j2sURI       : AnyRef => AnyRef = identity
  protected lazy val j2sByte      : AnyRef => AnyRef = {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  protected lazy val j2sShort     : AnyRef => AnyRef = {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }
  protected lazy val j2sChar      : AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]


  // Single sample value extracted from clojure LazySeq
  protected lazy val firstString    : AnyRef => AnyRef = first
  protected lazy val firstInt       : AnyRef => AnyRef = first
  protected lazy val firstLong      : AnyRef => AnyRef = first
  protected lazy val firstFloat     : AnyRef => AnyRef = first
  protected lazy val firstDouble    : AnyRef => AnyRef = first
  protected lazy val firstBoolean   : AnyRef => AnyRef = first
  protected lazy val firstBigInt    : AnyRef => AnyRef = first((v: Any) => BigInt(v.asInstanceOf[jBigInt]))
  protected lazy val firstBigDecimal: AnyRef => AnyRef = first((v: Any) => BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val firstDate      : AnyRef => AnyRef = first
  protected lazy val firstUUID      : AnyRef => AnyRef = first
  protected lazy val firstURI       : AnyRef => AnyRef = first
  protected lazy val firstByte      : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[Integer].toByte)
  protected lazy val firstShort     : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[Integer].toShort)
  protected lazy val firstChar      : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[String].charAt(0))

  private def first: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]

  private def first(value: Any => Any): AnyRef => AnyRef =
    (v: AnyRef) => value(v.asInstanceOf[jList[_]].get(0)).asInstanceOf[AnyRef]


  protected lazy val set2setString    : AnyRef => AnyRef = set2set
  protected lazy val set2setInt       : AnyRef => AnyRef = set2set
  protected lazy val set2setLong      : AnyRef => AnyRef = set2set
  protected lazy val set2setFloat     : AnyRef => AnyRef = set2set
  protected lazy val set2setDouble    : AnyRef => AnyRef = set2set
  protected lazy val set2setBoolean   : AnyRef => AnyRef = set2set
  protected lazy val set2setBigInt    : AnyRef => AnyRef = set2set((v: AnyRef) => BigInt(v.toString))
  protected lazy val set2setBigDecimal: AnyRef => AnyRef = set2set((v: AnyRef) => BigDecimal(v.toString))
  protected lazy val set2setDate      : AnyRef => AnyRef = set2set
  protected lazy val set2setUUID      : AnyRef => AnyRef = set2set
  protected lazy val set2setURI       : AnyRef => AnyRef = set2set
  protected lazy val set2setByte      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  protected lazy val set2setShort     : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  protected lazy val set2setChar      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def set2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

  private def set2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  protected lazy val vector2setString    : AnyRef => AnyRef = vector2set
  protected lazy val vector2setInt       : AnyRef => AnyRef = vector2set
  protected lazy val vector2setLong      : AnyRef => AnyRef = vector2set
  protected lazy val vector2setFloat     : AnyRef => AnyRef = vector2set
  protected lazy val vector2setDouble    : AnyRef => AnyRef = vector2set
  protected lazy val vector2setBoolean   : AnyRef => AnyRef = vector2set
  protected lazy val vector2setBigInt    : AnyRef => AnyRef = vector2set((v: AnyRef) => BigInt(v.toString))
  protected lazy val vector2setBigDecimal: AnyRef => AnyRef = vector2set((v: AnyRef) => BigDecimal(v.toString))
  protected lazy val vector2setDate      : AnyRef => AnyRef = vector2set
  protected lazy val vector2setUUID      : AnyRef => AnyRef = vector2set
  protected lazy val vector2setURI       : AnyRef => AnyRef = vector2set
  protected lazy val vector2setByte      : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  protected lazy val vector2setShort     : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  protected lazy val vector2setChar      : AnyRef => AnyRef = vector2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))


  lazy val it2String    : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[String]
  lazy val it2Int       : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[Integer].toInt
  lazy val it2Long      : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[jLong].toLong
  lazy val it2Float     : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[jFloat].toFloat
  lazy val it2Double    : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[jDouble].toDouble
  lazy val it2Boolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[Boolean]
  lazy val it2BigInt    : jIterator[_] => Any = (it: jIterator[_]) => BigInt(it.next.asInstanceOf[jBigInt])
  lazy val it2BigDecimal: jIterator[_] => Any = (it: jIterator[_]) => BigDecimal(it.next.asInstanceOf[jBigDecimal])
  lazy val it2Date      : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[Date]
  lazy val it2UUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[UUID]
  lazy val it2URI       : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[URI]
  lazy val it2Byte      : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[Integer].toByte
  lazy val it2Short     : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[Integer].toShort
  lazy val it2Char      : jIterator[_] => Any = (it: jIterator[_]) => it.next.asInstanceOf[String].charAt(0)


  case class ResOne[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2set: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef
  )

  lazy val resString    : ResOne[String]     = ResOne("String", dString, s2jString, j2sString, firstString, set2setString, vector2setString)
  lazy val resInt       : ResOne[Int]        = ResOne("Int", dInt, s2jInt, j2sInt, firstInt, set2setInt, vector2setInt)
  lazy val resLong      : ResOne[Long]       = ResOne("Long", dLong, s2jLong, j2sLong, firstLong, set2setLong, vector2setLong)
  lazy val resFloat     : ResOne[Float]      = ResOne("Float", dFloat, s2jFloat, j2sFloat, firstFloat, set2setFloat, vector2setFloat)
  lazy val resDouble    : ResOne[Double]     = ResOne("Double", dDouble, s2jDouble, j2sDouble, firstDouble, set2setDouble, vector2setDouble)
  lazy val resBoolean   : ResOne[Boolean]    = ResOne("Boolean", dBoolean, s2jBoolean, j2sBoolean, firstBoolean, set2setBoolean, vector2setBoolean)
  lazy val resBigInt    : ResOne[BigInt]     = ResOne("BigInt", dBigInt, s2jBigInt, j2sBigInt, firstBigInt, set2setBigInt, vector2setBigInt)
  lazy val resBigDecimal: ResOne[BigDecimal] = ResOne("BigDecimal", dBigDecimal, s2jBigDecimal, j2sBigDecimal, firstBigDecimal, set2setBigDecimal, vector2setBigDecimal)
  lazy val resDate      : ResOne[Date]       = ResOne("Date", dDate, s2jDate, j2sDate, firstDate, set2setDate, vector2setDate)
  lazy val resUUID      : ResOne[UUID]       = ResOne("UUID", dUUID, s2jUUID, j2sUUID, firstUUID, set2setUUID, vector2setUUID)
  lazy val resURI       : ResOne[URI]        = ResOne("URI", dURI, s2jURI, j2sURI, firstURI, set2setURI, vector2setURI)
  lazy val resByte      : ResOne[Byte]       = ResOne("Byte", dByte, s2jByte, j2sByte, firstByte, set2setByte, vector2setByte)
  lazy val resShort     : ResOne[Short]      = ResOne("Short", dShort, s2jShort, j2sShort, firstShort, set2setShort, vector2setShort)
  lazy val resChar      : ResOne[Char]       = ResOne("Char", dChar, s2jChar, j2sChar, firstChar, set2setChar, vector2setChar)


  private lazy val j2sOptString     = (v: AnyRef) => v match {
    case null          => Option.empty[String]
    case v: String     => Some(v) // attr_?(<expr>))
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String]) // attr_?
  }
  private lazy val j2sOptInt        = (v: AnyRef) => v match {
    case null          => Option.empty[Int]
    case v: jInteger   => Some(v.toInt)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toInt)
  }
  private lazy val j2sOptLong       = (v: AnyRef) => v match {
    case null          => Option.empty[Long]
    case v: jLong      => Some(v)
    case v: jMap[_, _] => v.values.iterator.next match {
      case l: Long => Some(l)
      // ref
      case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[Long])
    }
  }
  private lazy val j2sOptFloat      = (v: AnyRef) => v match {
    case null          => Option.empty[Float]
    case v: jFloat     => Some(v.toFloat)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Float])
  }
  private lazy val j2sOptDouble     = (v: AnyRef) => v match {
    case null          => Option.empty[Double]
    case v: jDouble    => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Double])
  }
  private lazy val j2sOptBoolean    = (v: AnyRef) => v match {
    case null          => Option.empty[Boolean]
    case v: jBoolean   => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Boolean])
  }
  private lazy val j2sOptBigInt     = (v: AnyRef) => v match {
    case null          => Option.empty[BigInt]
    case v: jBigInt    => Some(BigInt(v))
    case v: jMap[_, _] => Some(BigInt(v.values.iterator.next.asInstanceOf[jBigInt]))
  }
  private lazy val j2sOptBigDecimal = (v: AnyRef) => v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v: jMap[_, _]  => Some(BigDecimal(v.values.iterator.next.asInstanceOf[jBigDecimal]))
  }
  private lazy val j2sOptDate       = (v: AnyRef) => v match {
    case null          => Option.empty[Date]
    case v: Date       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Date])
  }
  private lazy val j2sOptUUID       = (v: AnyRef) => v match {
    case null          => Option.empty[UUID]
    case v: UUID       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[UUID])
  }
  private lazy val j2sOptURI        = (v: AnyRef) => v match {
    case null          => Option.empty[URI]
    case v: URI        => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[URI])
  }
  private lazy val j2sOptByte       = (v: AnyRef) => v match {
    case null          => Option.empty[Byte]
    case v: jInteger   => Some(v.toByte)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toByte)
  }
  private lazy val j2sOptShort      = (v: AnyRef) => v match {
    case null          => Option.empty[Short]
    case v: jInteger   => Some(v.toShort)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toShort)
  }
  private lazy val j2sOptChar       = (v: AnyRef) => v match {
    case null          => Option.empty[Char]
    case v: String     => Some(v.head)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String].charAt(0))
  }


  lazy val it2OptString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.toString)
  }
  lazy val it2OptInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[Integer].toInt)
  }
  lazy val it2OptLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[jLong].toLong)
  }
  lazy val it2OptFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[jFloat].toFloat)
  }
  lazy val it2OptDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[jDouble].toDouble)
  }
  lazy val it2OptBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[Boolean])
  }
  lazy val it2OptBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(BigInt(v.asInstanceOf[jBigInt]))
  }
  lazy val it2OptBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(BigDecimal(v.asInstanceOf[jBigDecimal]))
  }
  lazy val it2OptDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[Date])
  }
  lazy val it2OptUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[UUID])
  }
  lazy val it2OptURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[URI])
  }
  lazy val it2OptByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[Integer].toByte)
  }
  lazy val it2OptShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[Integer].toShort)
  }
  lazy val it2OptChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case "__none__" => None
    case v          => Some(v.asInstanceOf[String].charAt(0))
  }


  case class ResOneOpt[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef
  )

  lazy val resOptString    : ResOneOpt[String]     = ResOneOpt("String", dString, s2jString, j2sOptString)
  lazy val resOptInt       : ResOneOpt[Int]        = ResOneOpt("Int", dInt, s2jInt, j2sOptInt)
  lazy val resOptLong      : ResOneOpt[Long]       = ResOneOpt("Long", dLong, s2jLong, j2sOptLong)
  lazy val resOptFloat     : ResOneOpt[Float]      = ResOneOpt("Float", dFloat, s2jFloat, j2sOptFloat)
  lazy val resOptDouble    : ResOneOpt[Double]     = ResOneOpt("Double", dDouble, s2jDouble, j2sOptDouble)
  lazy val resOptBoolean   : ResOneOpt[Boolean]    = ResOneOpt("Boolean", dBoolean, s2jBoolean, j2sOptBoolean)
  lazy val resOptBigInt    : ResOneOpt[BigInt]     = ResOneOpt("BigInt", dBigInt, s2jBigInt, j2sOptBigInt)
  lazy val resOptBigDecimal: ResOneOpt[BigDecimal] = ResOneOpt("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOptBigDecimal)
  lazy val resOptDate      : ResOneOpt[Date]       = ResOneOpt("Date", dDate, s2jDate, j2sOptDate)
  lazy val resOptUUID      : ResOneOpt[UUID]       = ResOneOpt("UUID", dUUID, s2jUUID, j2sOptUUID)
  lazy val resOptURI       : ResOneOpt[URI]        = ResOneOpt("URI", dURI, s2jURI, j2sOptURI)
  lazy val resOptByte      : ResOneOpt[Byte]       = ResOneOpt("Byte", dByte, s2jByte, j2sOptByte)
  lazy val resOptShort     : ResOneOpt[Short]      = ResOneOpt("Short", dShort, s2jShort, j2sOptShort)
  lazy val resOptChar      : ResOneOpt[Char]       = ResOneOpt("Char", dChar, s2jChar, j2sOptChar)
}