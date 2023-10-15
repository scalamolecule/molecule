package molecule.boilerplate.ast

import java.net.URI
import java.time._
import java.util.{Date, UUID}


trait Values {
  
  sealed trait Value

  case class OneString(v: String) extends Value
  case class OneInt(v: Int) extends Value
  case class OneLong(v: Long) extends Value
  case class OneFloat(v: Float) extends Value
  case class OneDouble(v: Double) extends Value
  case class OneBoolean(v: Boolean) extends Value
  case class OneBigInt(v: BigInt) extends Value
  case class OneBigDecimal(v: BigDecimal) extends Value
  case class OneDate(v: Date) extends Value
  case class OneDuration(v: Duration) extends Value
  case class OneInstant(v: Instant) extends Value
  case class OneLocalDate(v: LocalDate) extends Value
  case class OneLocalTime(v: LocalTime) extends Value
  case class OneLocalDateTime(v: LocalDateTime) extends Value
  case class OneOffsetTime(v: OffsetTime) extends Value
  case class OneOffsetDateTime(v: OffsetDateTime) extends Value
  case class OneZonedDateTime(v: ZonedDateTime) extends Value
  case class OneUUID(v: UUID) extends Value
  case class OneURI(v: URI) extends Value
  case class OneByte(v: Byte) extends Value
  case class OneShort(v: Short) extends Value
  case class OneChar(v: Char) extends Value

  case class SetString(v: Set[String]) extends Value
  case class SetInt(v: Set[Int]) extends Value
  case class SetLong(v: Set[Long]) extends Value
  case class SetFloat(v: Set[Float]) extends Value
  case class SetDouble(v: Set[Double]) extends Value
  case class SetBoolean(v: Set[Boolean]) extends Value
  case class SetBigInt(v: Set[BigInt]) extends Value
  case class SetBigDecimal(v: Set[BigDecimal]) extends Value
  case class SetDate(v: Set[Date]) extends Value
  case class SetDuration(v: Set[Duration]) extends Value
  case class SetInstant(v: Set[Instant]) extends Value
  case class SetLocalDate(v: Set[LocalDate]) extends Value
  case class SetLocalTime(v: Set[LocalTime]) extends Value
  case class SetLocalDateTime(v: Set[LocalDateTime]) extends Value
  case class SetOffsetTime(v: Set[OffsetTime]) extends Value
  case class SetOffsetDateTime(v: Set[OffsetDateTime]) extends Value
  case class SetZonedDateTime(v: Set[ZonedDateTime]) extends Value
  case class SetUUID(v: Set[UUID]) extends Value
  case class SetURI(v: Set[URI]) extends Value
  case class SetByte(v: Set[Byte]) extends Value
  case class SetShort(v: Set[Short]) extends Value
  case class SetChar(v: Set[Char]) extends Value
}
