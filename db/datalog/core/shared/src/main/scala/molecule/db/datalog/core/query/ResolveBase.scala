package molecule.db.datalog.core.query

import java.lang.{Double as jDouble, Float as jFloat, Long as jLong}
import java.math.{BigDecimal as jBigDecimal, BigInteger as jBigInt}
import java.net.URI
import java.time.*
import java.util.{Date, UUID, List as jList}
import molecule.base.util.BaseHelpers
import molecule.db.datalog.core.query.casting.NullValueException

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

  // Datomic Java to Scala
  protected lazy val j2sId            : AnyRef => AnyRef = identity
  protected lazy val j2sString        : AnyRef => AnyRef = identity
  // Datomic can return both Integer or Long
  protected lazy val j2sInt           : AnyRef => AnyRef = (v: AnyRef) => v.toString.toInt.asInstanceOf[AnyRef]
  protected lazy val j2sLong          : AnyRef => AnyRef = identity
  protected lazy val j2sFloat         : AnyRef => AnyRef = {
    case v: jFloat  => v.asInstanceOf[AnyRef]
    case v: jDouble => v.toFloat.asInstanceOf[AnyRef]
  }
  protected lazy val j2sDouble        : AnyRef => AnyRef = identity
  protected lazy val j2sBoolean       : AnyRef => AnyRef = identity
  protected lazy val j2sBigInt        : AnyRef => AnyRef = {
    case v: jBigInt => BigInt(v)
    case v          => BigInt(v.toString)
  }
  protected lazy val j2sBigDecimal    : AnyRef => AnyRef = {
    case v: jBigDecimal => BigDecimal(v)
    case v              => BigDecimal(v.toString)
  }
  protected lazy val j2sDate          : AnyRef => AnyRef = identity
  protected lazy val j2sDuration      : AnyRef => AnyRef = (v: AnyRef) => Duration.parse(v.asInstanceOf[String])
  protected lazy val j2sInstant       : AnyRef => AnyRef = (v: AnyRef) => Instant.parse(v.asInstanceOf[String])
  protected lazy val j2sLocalDate     : AnyRef => AnyRef = (v: AnyRef) => LocalDate.parse(v.asInstanceOf[String])
  protected lazy val j2sLocalTime     : AnyRef => AnyRef = (v: AnyRef) => LocalTime.parse(v.asInstanceOf[String])
  protected lazy val j2sLocalDateTime : AnyRef => AnyRef = (v: AnyRef) => LocalDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2sOffsetTime    : AnyRef => AnyRef = (v: AnyRef) => OffsetTime.parse(v.asInstanceOf[String])
  protected lazy val j2sOffsetDateTime: AnyRef => AnyRef = (v: AnyRef) => OffsetDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2sZonedDateTime : AnyRef => AnyRef = (v: AnyRef) => ZonedDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2sUUID          : AnyRef => AnyRef = identity
  protected lazy val j2sURI           : AnyRef => AnyRef = identity
  protected lazy val j2sByte          : AnyRef => AnyRef = {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  protected lazy val j2sShort         : AnyRef => AnyRef = {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }
  protected lazy val j2sChar          : AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]


  // Scala to Java
  protected lazy val s2jId            : Any => Any = identity
  protected lazy val s2jString        : Any => Any = identity
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

  // Java to Scala
  protected lazy val j2Id            : Any => Long           = (v: Any) => v.asInstanceOf[Long]
  protected lazy val j2String        : Any => String         = (v: Any) => v.asInstanceOf[String]
  protected lazy val j2Int           : Any => Int            = (v: Any) => v.toString.toInt
  protected lazy val j2Long          : Any => Long           = (v: Any) => v.asInstanceOf[Long]
  protected lazy val j2Float         : Any => Float          = (v: Any) => v.asInstanceOf[Float]
  protected lazy val j2Double        : Any => Double         = (v: Any) => v.asInstanceOf[Double]
  protected lazy val j2Boolean       : Any => Boolean        = (v: Any) => v.asInstanceOf[Boolean]
  protected lazy val j2BigInt        : Any => BigInt         = (v: Any) => BigInt(v.asInstanceOf[jBigInt])
  protected lazy val j2BigDecimal    : Any => BigDecimal     = (v: Any) => BigDecimal(v.asInstanceOf[jBigDecimal])
  protected lazy val j2Date          : Any => Date           = (v: Any) => v.asInstanceOf[Date]
  protected lazy val j2Duration      : Any => Duration       = (v: Any) => Duration.parse(v.asInstanceOf[String])
  protected lazy val j2Instant       : Any => Instant        = (v: Any) => Instant.parse(v.asInstanceOf[String])
  protected lazy val j2LocalDate     : Any => LocalDate      = (v: Any) => LocalDate.parse(v.asInstanceOf[String])
  protected lazy val j2LocalTime     : Any => LocalTime      = (v: Any) => LocalTime.parse(v.asInstanceOf[String])
  protected lazy val j2LocalDateTime : Any => LocalDateTime  = (v: Any) => LocalDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2OffsetTime    : Any => OffsetTime     = (v: Any) => OffsetTime.parse(v.asInstanceOf[String])
  protected lazy val j2OffsetDateTime: Any => OffsetDateTime = (v: Any) => OffsetDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2ZonedDateTime : Any => ZonedDateTime  = (v: Any) => ZonedDateTime.parse(v.asInstanceOf[String])
  protected lazy val j2UUID          : Any => UUID           = (v: Any) => v.asInstanceOf[UUID]
  protected lazy val j2URI           : Any => URI            = (v: Any) => v.asInstanceOf[URI]
  protected lazy val j2Byte          : Any => Byte           = (v: Any) => v.asInstanceOf[Integer].toByte
  protected lazy val j2Short         : Any => Short          = (v: Any) => v.asInstanceOf[Integer].toShort
  protected lazy val j2Char          : Any => Char           = (v: Any) => v.asInstanceOf[String].charAt(0)


  // Used for aggregate count and countDistinct functions
  lazy val toInt: AnyRef => AnyRef = (v: AnyRef) => v.asInstanceOf[Integer].toInt.asInstanceOf[AnyRef]


  protected lazy val dId            : Long => String           = (v: Long) => v.toString
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
    (v: AnyRef) => v.asInstanceOf[jList[?]].toArray.toSet

  protected def jvector2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[?]].toArray.toSet.map(value)
}