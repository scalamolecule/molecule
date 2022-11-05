package molecule.db.datomic.query

import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

object ResolveSet extends ResolveBase with JavaConversions {

  private lazy val set2setString     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setInt        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setLong       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setFloat      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setDouble     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setBoolean    = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setBigInt     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(v => BigInt(v.toString)).toSet.asInstanceOf[AnyRef]
  private lazy val set2setBigDecimal = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(v => BigDecimal(v.toString)).toSet.asInstanceOf[AnyRef]
  private lazy val set2setDate       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setUUID       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setURI        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet.asInstanceOf[AnyRef]
  private lazy val set2setByte       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(_.asInstanceOf[Integer].toByte).toSet.asInstanceOf[AnyRef]
  private lazy val set2setShort      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(_.asInstanceOf[Integer].toShort).toSet.asInstanceOf[AnyRef]
  private lazy val set2setChar       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(_.asInstanceOf[String].charAt(0)).toSet.asInstanceOf[AnyRef]

  case class ResSet[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2list: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
  )

  lazy val resSetString    : ResSet[String]     = ResSet("String", dString, identity, set2setString, seq2String, set2listString, vector2listString)
  lazy val resSetInt       : ResSet[Int]        = ResSet("Int", dInt, identity, set2setInt, seq2Int, set2listInt, vector2listInt)
  lazy val resSetLong      : ResSet[Long]       = ResSet("Long", dLong, identity, set2setLong, seq2Long, set2listLong, vector2listLong)
  lazy val resSetFloat     : ResSet[Float]      = ResSet("Float", dFloat, identity, set2setFloat, seq2Float, set2listFloat, vector2listFloat)
  lazy val resSetDouble    : ResSet[Double]     = ResSet("Double", dDouble, identity, set2setDouble, seq2Double, set2listDouble, vector2listDouble)
  lazy val resSetBoolean   : ResSet[Boolean]    = ResSet("Boolean", dBoolean, identity, set2setBoolean, seq2Boolean, set2listBoolean, vector2listBoolean)
  lazy val resSetBigInt    : ResSet[BigInt]     = ResSet("BigInt", dBigInt, fromBigInt, set2setBigInt, seq2BigInt, set2listBigInt, vector2listBigInt)
  lazy val resSetBigDecimal: ResSet[BigDecimal] = ResSet("BigDecimal", dBigDec, fromBigDec, set2setBigDecimal, seq2BigDecimal, set2listBigDecimal, vector2listBigDecimal)
  lazy val resSetDate      : ResSet[Date]       = ResSet("Date", dDate, identity, set2setDate, seq2Date, set2listDate, vector2listDate)
  lazy val resSetUUID      : ResSet[UUID]       = ResSet("UUID", dUUID, identity, set2setUUID, seq2UUID, set2listUUID, vector2listUUID)
  lazy val resSetURI       : ResSet[URI]        = ResSet("URI", dURI, identity, set2setURI, seq2URI, set2listURI, vector2listURI)
  lazy val resSetByte      : ResSet[Byte]       = ResSet("Byte", dByte, fromByte, set2setByte, seq2Byte, set2listByte, vector2listByte)
  lazy val resSetShort     : ResSet[Short]      = ResSet("Short", dShort, fromShort, set2setShort, seq2Short, set2listShort, vector2listShort)
  lazy val resSetChar      : ResSet[Char]       = ResSet("Char", dChar, fromChar, set2setChar, seq2Char, set2listChar, vector2listChar)


  private lazy val toOpSetString = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[String]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String])) // attr_?(<expr>))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String]).toSet) // attr_?
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetInt = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Int]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toInt).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetLong = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Long]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Long]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetFloat = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Float]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Float]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Float]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetDouble = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Double]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Double]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Double]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBoolean = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Boolean]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Boolean]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Boolean]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBigInt = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[BigInt]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBigDecimal = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[BigDecimal]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetDate = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Date]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Date]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Date]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetUUID = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[UUID]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[UUID]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[UUID]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetURI = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[URI]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[URI]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[URI]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetByte = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Byte]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toByte))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toByte).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetShort = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Short]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toShort))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toShort).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetChar = (v: AnyRef) => (v match {
    case null            => Option.empty[Set[Char]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String].charAt(0)))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String].charAt(0)).toSet)
  }).asInstanceOf[AnyRef]


  case class ResSetOpt[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
  )

  lazy val resOptSetString    : ResSetOpt[String]     = ResSetOpt("String", dString, identity, toOpSetString)
  lazy val resOptSetInt       : ResSetOpt[Int]        = ResSetOpt("Int", dInt, identity, toOpSetInt)
  lazy val resOptSetLong      : ResSetOpt[Long]       = ResSetOpt("Long", dLong, identity, toOpSetLong)
  lazy val resOptSetFloat     : ResSetOpt[Float]      = ResSetOpt("Float", dFloat, identity, toOpSetFloat)
  lazy val resOptSetDouble    : ResSetOpt[Double]     = ResSetOpt("Double", dDouble, identity, toOpSetDouble)
  lazy val resOptSetBoolean   : ResSetOpt[Boolean]    = ResSetOpt("Boolean", dBoolean, identity, toOpSetBoolean)
  lazy val resOptSetBigInt    : ResSetOpt[BigInt]     = ResSetOpt("BigInt", dBigInt, fromBigInt, toOpSetBigInt)
  lazy val resOptSetBigDecimal: ResSetOpt[BigDecimal] = ResSetOpt("BigDecimal", dBigDec, fromBigDec, toOpSetBigDecimal)
  lazy val resOptSetDate      : ResSetOpt[Date]       = ResSetOpt("Date", dDate, identity, toOpSetDate)
  lazy val resOptSetUUID      : ResSetOpt[UUID]       = ResSetOpt("UUID", dUUID, identity, toOpSetUUID)
  lazy val resOptSetURI       : ResSetOpt[URI]        = ResSetOpt("URI", dURI, identity, toOpSetURI)
  lazy val resOptSetByte      : ResSetOpt[Byte]       = ResSetOpt("Byte", dByte, fromByte, toOpSetByte)
  lazy val resOptSetShort     : ResSetOpt[Short]      = ResSetOpt("Short", dShort, fromShort, toOpSetShort)
  lazy val resOptSetChar      : ResSetOpt[Char]       = ResSetOpt("Char", dChar, fromChar, toOpSetChar)
}