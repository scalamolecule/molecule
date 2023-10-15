package molecule.datalog.core.query

import java.net.URI
import java.time._
import java.util.{Date, UUID, List => jList}
import molecule.base.util.BaseHelpers
import molecule.datalog.core.query.casting.NullValueException

trait ResolveBase extends BaseHelpers {

  // dummy value for null
  val none = "__none__"

  def nullValue = throw new NullValueException

  def unexpectedValue(other: Any) = other match {
    case null  => throw new Exception(s"Unexpected null value".stripMargin)
    case other =>
      throw new Exception(
        s"""Unexpected:
           |  $other
           |  ${other.getClass}""".stripMargin
      )
  }

  // Scala to Java
  protected lazy val s2jString        : Any => Any = identity
  // Save Int as Long in Datomic
  protected lazy val s2jInt           : Any => Any = (v: Any) => v.asInstanceOf[Int].toLong.asInstanceOf[Any]
  protected lazy val s2jLong          : Any => Any = identity
  protected lazy val s2jFloat         : Any => Any = identity
  protected lazy val s2jDouble        : Any => Any = identity
  protected lazy val s2jBoolean       : Any => Any = identity
  protected lazy val s2jBigInt        : Any => Any = (v: Any) => v.asInstanceOf[BigInt].bigInteger.asInstanceOf[Any]
  protected lazy val s2jBigDecimal    : Any => Any = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[Any]
  protected lazy val s2jDate          : Any => Any = identity
  protected lazy val s2jDuration      : Any => Any = (v: Any) => v.asInstanceOf[Duration].toString
  protected lazy val s2jInstant       : Any => Any = (v: Any) => v.asInstanceOf[Instant].toString
  protected lazy val s2jLocalDate     : Any => Any = (v: Any) => v.asInstanceOf[LocalDate].toString
  protected lazy val s2jLocalTime     : Any => Any = (v: Any) => v.asInstanceOf[LocalTime].toString
  protected lazy val s2jLocalDateTime : Any => Any = (v: Any) => v.asInstanceOf[LocalDateTime].toString
  protected lazy val s2jOffsetTime    : Any => Any = (v: Any) => v.asInstanceOf[OffsetTime].toString
  protected lazy val s2jOffsetDateTime: Any => Any = (v: Any) => v.asInstanceOf[OffsetDateTime].toString
  protected lazy val s2jZonedDateTime : Any => Any = (v: Any) => v.asInstanceOf[ZonedDateTime].toString
  protected lazy val s2jUUID          : Any => Any = identity
  protected lazy val s2jURI           : Any => Any = identity
  protected lazy val s2jByte          : Any => Any = (v: Any) => v.asInstanceOf[Byte].toInt.asInstanceOf[Any]
  protected lazy val s2jShort         : Any => Any = (v: Any) => v.asInstanceOf[Short].toInt.asInstanceOf[Any]
  protected lazy val s2jChar          : Any => Any = (v: Any) => v.asInstanceOf[Char].toString.asInstanceOf[Any]


  // Used for aggregate count and countDistinct functions
  lazy val toInt: AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[Integer].toInt.asInstanceOf[AnyRef]


  protected lazy val dString        : String => String         = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val dInt           : Int => String            = (v: Int) => v.toString
  protected lazy val dLong          : Long => String           = (v: Long) => v.toString
  protected lazy val dFloat         : Float => String          = (v: Float) => "(float " + v.toString + ")"
  protected lazy val dDouble        : Double => String         = (v: Double) => v.toString
  protected lazy val dBoolean       : Boolean => String        = (v: Boolean) => v.toString
  protected lazy val dBigInt        : BigInt => String         = (v: BigInt) => v.toString + "N"
  protected lazy val dBigDecimal    : BigDecimal => String     = (v: BigDecimal) => v.toString + "M"
  protected lazy val dDate          : Date => String           = (v: Date) => "#inst \"" + date2datomic(v) + "\""
  protected lazy val dDuration      : Duration => String       = (v: Duration) => "\"" + v.toString + "\""
  protected lazy val dInstant       : Instant => String        = (v: Instant) => "\"" + v.toString + "\""
  protected lazy val dLocalDate     : LocalDate => String      = (v: LocalDate) => "\"" + v.toString + "\""
  protected lazy val dLocalTime     : LocalTime => String      = (v: LocalTime) => "\"" + v.toString + "\""
  protected lazy val dLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => "\"" + v.toString + "\""
  protected lazy val dOffsetTime    : OffsetTime => String     = (v: OffsetTime) => "\"" + v.toString + "\""
  protected lazy val dOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => "\"" + v.toString + "\""
  protected lazy val dZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => "\"" + v.toString + "\""
  protected lazy val dUUID          : UUID => String           = (v: UUID) => "#uuid \"" + v.toString + "\""
  protected lazy val dURI           : URI => String            = (v: URI) => v.toString
  protected lazy val dByte          : Byte => String           = (v: Byte) => v.toString
  protected lazy val dShort         : Short => String          = (v: Short) => v.toString
  protected lazy val dChar          : Char => String           = (v: Char) => "\"" + v.toString + "\""


  protected def jvector2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toSet

  protected def jvector2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toSet.map(value)
}