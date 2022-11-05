package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, Map => jMap}
import molecule.coreTests.dataModels.core.types.dsl.CardOne.One

object ResolveOne extends ResolveBase {

  // Datomic Java to Scala type converters
  protected lazy val toBigInt = (v: AnyRef) => v match {
    case v: jBigInt => BigInt(v).asInstanceOf[AnyRef]
    case v          => BigInt(v.toString).asInstanceOf[AnyRef]
  }
  protected lazy val toBigDec = (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  protected lazy val toChar   = (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]
  protected lazy val toByte   = (v: AnyRef) => v match {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  protected lazy val toShort  = (v: AnyRef) => v match {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }

  case class ResOne[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2list: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
  )

  lazy val resString    : ResOne[String]     = ResOne("String", dString, identity, identity, seq2String, set2listString, vector2listString)
  lazy val resInt       : ResOne[Int]        = ResOne("Int", dInt, identity, identity, seq2Int, set2listInt, vector2listInt)
  lazy val resLong      : ResOne[Long]       = ResOne("Long", dLong, identity, identity, seq2Long, set2listLong, vector2listLong)
  lazy val resFloat     : ResOne[Float]      = ResOne("Float", dFloat, identity, identity, seq2Float, set2listFloat, vector2listFloat)
  lazy val resDouble    : ResOne[Double]     = ResOne("Double", dDouble, identity, identity, seq2Double, set2listDouble, vector2listDouble)
  lazy val resBoolean   : ResOne[Boolean]    = ResOne("Boolean", dBoolean, identity, identity, seq2Boolean, set2listBoolean, vector2listBoolean)
  lazy val resBigInt    : ResOne[BigInt]     = ResOne("BigInt", dBigInt, fromBigInt, toBigInt, seq2BigInt, set2listBigInt, vector2listBigInt)
  lazy val resBigDecimal: ResOne[BigDecimal] = ResOne("BigDecimal", dBigDec, fromBigDec, toBigDec, seq2BigDecimal, set2listBigDecimal, vector2listBigDecimal)
  lazy val resDate      : ResOne[Date]       = ResOne("Date", dDate, identity, identity, seq2Date, set2listDate, vector2listDate)
  lazy val resUUID      : ResOne[UUID]       = ResOne("UUID", dUUID, identity, identity, seq2UUID, set2listUUID, vector2listUUID)
  lazy val resURI       : ResOne[URI]        = ResOne("URI", dURI, identity, identity, seq2URI, set2listURI, vector2listURI)
  lazy val resByte      : ResOne[Byte]       = ResOne("Byte", dByte, fromByte, toByte, seq2Byte, set2listByte, vector2listByte)
  lazy val resShort     : ResOne[Short]      = ResOne("Short", dShort, fromShort, toShort, seq2Short, set2listShort, vector2listShort)
  lazy val resChar      : ResOne[Char]       = ResOne("Char", dChar, fromChar, toChar, seq2Char, set2listChar, vector2listChar)


  private lazy val toOptString = (v: AnyRef) => (v match {
    case null          => Option.empty[String]
    case v: String     => Some(v) // attr_?(<expr>))
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String]) // attr_?
  }).asInstanceOf[AnyRef]

  private lazy val toOptInt = (v: AnyRef) => (v match {
    case null          => Option.empty[Int]
    case v: jInteger   => Some(v.toInt)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toInt)
  }).asInstanceOf[AnyRef]

  private lazy val toOptLong = (v: AnyRef) => (v match {
    case null          => Option.empty[Long]
    case v: jLong      => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Long])
  }).asInstanceOf[AnyRef]

  private lazy val toOptFloat = (v: AnyRef) => (v match {
    case null          => Option.empty[Float]
    case v: jFloat     => Some(v.toFloat)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Float])
  }).asInstanceOf[AnyRef]

  private lazy val toOptDouble = (v: AnyRef) => (v match {
    case null          => Option.empty[Double]
    case v: jDouble    => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Double])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBoolean = (v: AnyRef) => (v match {
    case null          => Option.empty[Boolean]
    case v: jBoolean   => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Boolean])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigInt = (v: AnyRef) => (v match {
    case null          => Option.empty[BigInt]
    case v: jBigInt    => Some(BigInt(v))
    case v: jMap[_, _] => Some(BigInt(v.values.iterator.next.asInstanceOf[jBigInt]))
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigDecimal = (v: AnyRef) => (v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v: jMap[_, _]  => Some(BigDecimal(v.values.iterator.next.asInstanceOf[jBigDecimal]))
  }).asInstanceOf[AnyRef]

  private lazy val toOptDate = (v: AnyRef) => (v match {
    case null          => Option.empty[Date]
    case v: Date       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Date])
  }).asInstanceOf[AnyRef]

  private lazy val toOptUUID = (v: AnyRef) => (v match {
    case null          => Option.empty[UUID]
    case v: UUID       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[UUID])
  }).asInstanceOf[AnyRef]

  private lazy val toOptURI = (v: AnyRef) => (v match {
    case null          => Option.empty[URI]
    case v: URI        => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[URI])
  }).asInstanceOf[AnyRef]

  private lazy val toOptByte = (v: AnyRef) => (v match {
    case null          => Option.empty[Byte]
    case v: jInteger   => Some(v.toByte)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toByte)
  }).asInstanceOf[AnyRef]

  private lazy val toOptShort = (v: AnyRef) => (v match {
    case null          => Option.empty[Short]
    case v: jInteger   => Some(v.toShort)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toShort)
  }).asInstanceOf[AnyRef]

  private lazy val toOptChar = (v: AnyRef) => (v match {
    case null          => Option.empty[Char]
    case v: String     => Some(v.head)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String].charAt(0))
  }).asInstanceOf[AnyRef]


  case class ResOneOpt[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
  )

  lazy val resOptString    : ResOneOpt[String]     = ResOneOpt("String", dString, identity, toOptString)
  lazy val resOptInt       : ResOneOpt[Int]        = ResOneOpt("Int", dInt, identity, toOptInt)
  lazy val resOptLong      : ResOneOpt[Long]       = ResOneOpt("Long", dLong, identity, toOptLong)
  lazy val resOptFloat     : ResOneOpt[Float]      = ResOneOpt("Float", dFloat, identity, toOptFloat)
  lazy val resOptDouble    : ResOneOpt[Double]     = ResOneOpt("Double", dDouble, identity, toOptDouble)
  lazy val resOptBoolean   : ResOneOpt[Boolean]    = ResOneOpt("Boolean", dBoolean, identity, toOptBoolean)
  lazy val resOptBigInt    : ResOneOpt[BigInt]     = ResOneOpt("BigInt", dBigInt, fromBigInt, toOptBigInt)
  lazy val resOptBigDecimal: ResOneOpt[BigDecimal] = ResOneOpt("BigDecimal", dBigDec, fromBigDec, toOptBigDecimal)
  lazy val resOptDate      : ResOneOpt[Date]       = ResOneOpt("Date", dDate, identity, toOptDate)
  lazy val resOptUUID      : ResOneOpt[UUID]       = ResOneOpt("UUID", dUUID, identity, toOptUUID)
  lazy val resOptURI       : ResOneOpt[URI]        = ResOneOpt("URI", dURI, identity, toOptURI)
  lazy val resOptByte      : ResOneOpt[Byte]       = ResOneOpt("Byte", dByte, fromByte, toOptByte)
  lazy val resOptShort     : ResOneOpt[Short]      = ResOneOpt("Short", dShort, fromShort, toOptShort)
  lazy val resOptChar      : ResOneOpt[Char]       = ResOneOpt("Char", dChar, fromChar, toOptChar)
}