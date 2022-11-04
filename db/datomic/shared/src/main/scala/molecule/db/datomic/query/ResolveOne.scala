package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList, Map => jMap, Set => jSet}
import molecule.base.util.BaseHelpers

object ResolveOne extends BaseHelpers {

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


  case class ResOne[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2list: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
  )

  lazy val resString     = ResOne("String", dString, identity, identity, seq2String, set2listString, vector2listString)
  lazy val resInt        = ResOne("Int", dInt, identity, identity, seq2Int, set2listInt, vector2listInt)
  lazy val resLong       = ResOne("Long", dLong, identity, identity, seq2Long, set2listLong, vector2listLong)
  lazy val resFloat      = ResOne("Float", dFloat, identity, identity, seq2Float, set2listFloat, vector2listFloat)
  lazy val resDouble     = ResOne("Double", dDouble, identity, identity, seq2Double, set2listDouble, vector2listDouble)
  lazy val resBoolean    = ResOne("Boolean", dBoolean, identity, identity, seq2Boolean, set2listBoolean, vector2listBoolean)
  lazy val resBigInt     = ResOne("BigInt", dBigInt, fromBigInt, toBigInt, seq2BigInt, set2listBigInt, vector2listBigInt)
  lazy val resBigDecimal = ResOne("BigDecimal", dBigDec, fromBigDec, toBigDec, seq2BigDecimal, set2listBigDecimal, vector2listBigDecimal)
  lazy val resDate       = ResOne("Date", dDate, identity, identity, seq2Date, set2listDate, vector2listDate)
  lazy val resUUID       = ResOne("UUID", dUUID, identity, identity, seq2UUID, set2listUUID, vector2listUUID)
  lazy val resURI        = ResOne("URI", dURI, identity, identity, seq2URI, set2listURI, vector2listURI)
  lazy val resByte       = ResOne("Byte", dByte, fromByte, toByte, seq2Byte, set2listByte, vector2listByte)
  lazy val resShort      = ResOne("Short", dShort, fromShort, toShort, seq2Short, set2listShort, vector2listShort)
  lazy val resChar       = ResOne("Char", dChar, fromChar, toChar, seq2Char, set2listChar, vector2listChar)


  private lazy val toOptString = (v: AnyRef) => (v match {
    case null      => Option.empty[String]
    case v: String => Some(v)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[String])
  }).asInstanceOf[AnyRef]

  private lazy val toOptInt = (v: AnyRef) => (v match {
    case null        => Option.empty[Int]
    case v: jInteger => Some(v.toInt)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toInt)
  }).asInstanceOf[AnyRef]

  private lazy val toOptLong = (v: AnyRef) => (v match {
    case null     => Option.empty[Long]
    case v: jLong => Some(v)
    case v        => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Long])
  }).asInstanceOf[AnyRef]

  private lazy val toOptFloat = (v: AnyRef) => (v match {
    case null      => Option.empty[Float]
    case v: jFloat => Some(v.toFloat)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Float])
  }).asInstanceOf[AnyRef]

  private lazy val toOptDouble = (v: AnyRef) => (v match {
    case null       => Option.empty[Double]
    case v: jDouble => Some(v)
    case v          => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Double])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBoolean = (v: AnyRef) => (v match {
    case null        => Option.empty[Boolean]
    case v: jBoolean => Some(v)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Boolean])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigInt = (v: AnyRef) => (v match {
    case null       => Option.empty[BigInt]
    case v: jBigInt => Some(BigInt(v))
    case v          => Some(
      BigInt(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[jBigInt])
    )
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigDecimal = (v: AnyRef) => (v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v              => Some(
      BigDecimal(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[jBigDecimal])
    )
  }).asInstanceOf[AnyRef]

  private lazy val toOptDate = (v: AnyRef) => (v match {
    case null    => Option.empty[Date]
    case v: Date => Some(v)
    case v       => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Date])
  }).asInstanceOf[AnyRef]

  private lazy val toOptUUID = (v: AnyRef) => (v match {
    case null    => Option.empty[UUID]
    case v: UUID => Some(v)
    case v       => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[UUID])
  }).asInstanceOf[AnyRef]

  private lazy val toOptURI = (v: AnyRef) => (v match {
    case null   => Option.empty[URI]
    case v: URI => Some(v)
    case v      => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[URI])
  }).asInstanceOf[AnyRef]

  private lazy val toOptByte = (v: AnyRef) => (v match {
    case null        => Option.empty[Byte]
    case v: jInteger => Some(v.toByte)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toByte)
  }).asInstanceOf[AnyRef]

  private lazy val toOptShort = (v: AnyRef) => (v match {
    case null        => Option.empty[Short]
    case v: jInteger => Some(v.toShort)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toShort)
  }).asInstanceOf[AnyRef]

  private lazy val toOptChar = (v: AnyRef) => (v match {
    case null      => Option.empty[Char]
    case v: String => Some(v.head)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[String].charAt(0))
  }).asInstanceOf[AnyRef]


  case class ResOneOpt[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
  )

  lazy val optString     = ResOneOpt("String", dString, identity, toOptString)
  lazy val optInt        = ResOneOpt("Int", dInt, identity, toOptInt)
  lazy val optLong       = ResOneOpt("Long", dLong, identity, toOptLong)
  lazy val optFloat      = ResOneOpt("Float", dFloat, identity, toOptFloat)
  lazy val optDouble     = ResOneOpt("Double", dDouble, identity, toOptDouble)
  lazy val optBoolean    = ResOneOpt("Boolean", dBoolean, identity, toOptBoolean)
  lazy val optBigInt     = ResOneOpt("BigInt", dBigInt, fromBigInt, toOptBigInt)
  lazy val optBigDecimal = ResOneOpt("BigDecimal", dBigDec, fromBigDec, toOptBigDecimal)
  lazy val optDate       = ResOneOpt("Date", dDate, identity, toOptDate)
  lazy val optUUID       = ResOneOpt("UUID", dUUID, identity, toOptUUID)
  lazy val optURI        = ResOneOpt("URI", dURI, identity, toOptURI)
  lazy val optByte       = ResOneOpt("Byte", dByte, fromByte, toOptByte)
  lazy val optShort      = ResOneOpt("Short", dShort, fromShort, toOptShort)
  lazy val optChar       = ResOneOpt("Char", dChar, fromChar, toOptChar)
}