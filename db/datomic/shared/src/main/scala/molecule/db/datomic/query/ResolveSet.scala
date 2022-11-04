package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList, Map => jMap, Set => jSet}
import molecule.base.util.BaseHelpers
import molecule.core.util.JavaConversions

object ResolveSet extends BaseHelpers with JavaConversions {

  private lazy val fromBigInt = (v: Any) => v.asInstanceOf[BigInt].bigInteger.asInstanceOf[Any]
  private lazy val fromBigDec = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[Any]
  private lazy val fromChar   = (v: Any) => v.asInstanceOf[Char].toString.asInstanceOf[Any]
  private lazy val fromByte   = (v: Any) => v.asInstanceOf[Byte].toInt.asInstanceOf[Any]
  private lazy val fromShort  = (v: Any) => v.asInstanceOf[Short].toInt.asInstanceOf[Any]

  // Datomic Java to Scala type converters
  private lazy val toBigInt = (v: AnyRef) => v match {
    case v: jBigInt => BigInt(v).asInstanceOf[AnyRef]
    case v          => BigInt(v.toString).asInstanceOf[AnyRef]
  }
  private lazy val toBigDec = (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  private lazy val toChar   = (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]
  private lazy val toByte   = (v: AnyRef) => v match {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  private lazy val toShort  = (v: AnyRef) => v match {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }

  // Used for aggregate count and countDistinct functions
  lazy val toInt: AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[Integer].toInt.asInstanceOf[AnyRef]


  private lazy val dString : String => String     = (v: String) => "\"" + escStr(v) + "\""
  private lazy val dInt    : Int => String        = (v: Int) => v.toString
  private lazy val dLong   : Long => String       = (v: Long) => v.toString
  private lazy val dFloat  : Float => String      = (v: Float) => "(float " + v.toString + ")"
  private lazy val dDouble : Double => String     = (v: Double) => v.toString
  private lazy val dBoolean: Boolean => String    = (v: Boolean) => v.toString
  private lazy val dBigInt : BigInt => String     = (v: BigInt) => v.toString + "N"
  private lazy val dBigDec : BigDecimal => String = (v: BigDecimal) => v.toString + "M"
  private lazy val dDate   : Date => String       = (v: Date) => "#inst \"" + date2datomicStr2(v) + "\""
  private lazy val dUUID   : UUID => String       = (v: UUID) => "#uuid \"" + v.toString + "\""
  private lazy val dURI    : URI => String        = (v: URI) => v.toString
  private lazy val dByte   : Byte => String       = (v: Byte) => v.toString
  private lazy val dShort  : Short => String      = (v: Short) => v.toString
  private lazy val dChar   : Char => String       = (v: Char) => "\"" + v.toString + "\""


  // Single sample value extracted from clojure LazySeq
  private lazy val seq2String     = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Int        = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Long       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Float      = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Double     = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Boolean    = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2BigInt     = (v: AnyRef) => BigInt(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  private lazy val seq2BigDecimal = (v: AnyRef) => BigDecimal(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  private lazy val seq2Date       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2UUID       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2URI        = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Byte       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toByte.asInstanceOf[AnyRef]
  private lazy val seq2Short      = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toShort.asInstanceOf[AnyRef]
  private lazy val seq2Char       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]


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

  private lazy val set2listString     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listInt        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listLong       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listFloat      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listDouble     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listBoolean    = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listBigInt     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(v => BigInt(v.toString)).asInstanceOf[AnyRef]
  private lazy val set2listBigDecimal = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(v => BigDecimal(v.toString)).asInstanceOf[AnyRef]
  private lazy val set2listDate       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listUUID       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listURI        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listByte       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[Integer].toByte).asInstanceOf[AnyRef]
  private lazy val set2listShort      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[Integer].toShort).asInstanceOf[AnyRef]
  private lazy val set2listChar       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[String].charAt(0)).asInstanceOf[AnyRef]


  private lazy val vector2listString     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listInt        = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listLong       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listFloat      = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listDouble     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listBoolean    = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listBigInt     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(v => BigInt(v.toString)).asInstanceOf[AnyRef]
  private lazy val vector2listBigDecimal = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(v => BigDecimal(v.toString)).asInstanceOf[AnyRef]
  private lazy val vector2listDate       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listUUID       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listURI        = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listByte       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[Integer].toByte).asInstanceOf[AnyRef]
  private lazy val vector2listShort      = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[Integer].toShort).asInstanceOf[AnyRef]
  private lazy val vector2listChar       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[String].charAt(0)).asInstanceOf[AnyRef]


  case class ResSet[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2list: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
  )

  lazy val resSetString     = ResSet("String", dString, identity, set2setString, seq2String, set2listString, vector2listString)
  lazy val resSetInt        = ResSet("Int", dInt, identity, set2setInt, seq2Int, set2listInt, vector2listInt)
  lazy val resSetLong       = ResSet("Long", dLong, identity, set2setLong, seq2Long, set2listLong, vector2listLong)
  lazy val resSetFloat      = ResSet("Float", dFloat, identity, set2setFloat, seq2Float, set2listFloat, vector2listFloat)
  lazy val resSetDouble     = ResSet("Double", dDouble, identity, set2setDouble, seq2Double, set2listDouble, vector2listDouble)
  lazy val resSetBoolean    = ResSet("Boolean", dBoolean, identity, set2setBoolean, seq2Boolean, set2listBoolean, vector2listBoolean)
  lazy val resSetBigInt     = ResSet("BigInt", dBigInt, fromBigInt, set2setBigInt, seq2BigInt, set2listBigInt, vector2listBigInt)
  lazy val resSetBigDecimal = ResSet("BigDecimal", dBigDec, fromBigDec, set2setBigDecimal, seq2BigDecimal, set2listBigDecimal, vector2listBigDecimal)
  lazy val resSetDate       = ResSet("Date", dDate, identity, set2setDate, seq2Date, set2listDate, vector2listDate)
  lazy val resSetUUID       = ResSet("UUID", dUUID, identity, set2setUUID, seq2UUID, set2listUUID, vector2listUUID)
  lazy val resSetURI        = ResSet("URI", dURI, identity, set2setURI, seq2URI, set2listURI, vector2listURI)
  lazy val resSetByte       = ResSet("Byte", dByte, fromByte, set2setByte, seq2Byte, set2listByte, vector2listByte)
  lazy val resSetShort      = ResSet("Short", dShort, fromShort, set2setShort, seq2Short, set2listShort, vector2listShort)
  lazy val resSetChar       = ResSet("Char", dChar, fromChar, set2setChar, seq2Char, set2listChar, vector2listChar)


  private lazy val toOpSetString = (v: AnyRef) => (v match {
    case null            => Option.empty[String]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String].toInt).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetInt = (v: AnyRef) => (v match {
    case null            => Option.empty[Int]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toInt).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetLong = (v: AnyRef) => (v match {
    case null            => Option.empty[Long]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Long]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetFloat = (v: AnyRef) => (v match {
    case null            => Option.empty[Float]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Float]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Float]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetDouble = (v: AnyRef) => (v match {
    case null            => Option.empty[Double]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Double]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Double]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBoolean = (v: AnyRef) => (v match {
    case null            => Option.empty[Boolean]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Boolean]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Boolean]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBigInt = (v: AnyRef) => (v match {
    case null            => Option.empty[BigInt]
    case set: jSet[_]    => Some(set.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetBigDecimal = (v: AnyRef) => (v match {
    case null            => Option.empty[BigDecimal]
    case set: jSet[_]    => Some(set.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetDate = (v: AnyRef) => (v match {
    case null            => Option.empty[Date]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Date]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Date]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetUUID = (v: AnyRef) => (v match {
    case null            => Option.empty[UUID]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[UUID]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[UUID]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetURI = (v: AnyRef) => (v match {
    case null            => Option.empty[URI]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[URI]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[URI]).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetByte = (v: AnyRef) => (v match {
    case null            => Option.empty[Byte]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toInt).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetShort = (v: AnyRef) => (v match {
    case null            => Option.empty[Short]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toShort))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toShort).toSet)
  }).asInstanceOf[AnyRef]

  private lazy val toOpSetChar = (v: AnyRef) => (v match {
    case null            => Option.empty[Char]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String].charAt(0)))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String].charAt(0)).toSet)
  }).asInstanceOf[AnyRef]


  case class ResSetOpt[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
  )

  lazy val resOptSetString     = ResSetOpt("String", dString, identity, toOpSetString)
  lazy val resOptSetInt        = ResSetOpt("Int", dInt, identity, toOpSetInt)
  lazy val resOptSetLong       = ResSetOpt("Long", dLong, identity, toOpSetLong)
  lazy val resOptSetFloat      = ResSetOpt("Float", dFloat, identity, toOpSetFloat)
  lazy val resOptSetDouble     = ResSetOpt("Double", dDouble, identity, toOpSetDouble)
  lazy val resOptSetBoolean    = ResSetOpt("Boolean", dBoolean, identity, toOpSetBoolean)
  lazy val resOptSetBigInt     = ResSetOpt("BigInt", dBigInt, fromBigInt, toOpSetBigInt)
  lazy val resOptSetBigDecimal = ResSetOpt("BigDecimal", dBigDec, fromBigDec, toOpSetBigDecimal)
  lazy val resOptSetDate       = ResSetOpt("Date", dDate, identity, toOpSetDate)
  lazy val resOptSetUUID       = ResSetOpt("UUID", dUUID, identity, toOpSetUUID)
  lazy val resOptSetURI        = ResSetOpt("URI", dURI, identity, toOpSetURI)
  lazy val resOptSetByte       = ResSetOpt("Byte", dByte, fromByte, toOpSetByte)
  lazy val resOptSetShort      = ResSetOpt("Short", dShort, fromShort, toOpSetShort)
  lazy val resOptSetChar       = ResSetOpt("Char", dChar, fromChar, toOpSetChar)
}