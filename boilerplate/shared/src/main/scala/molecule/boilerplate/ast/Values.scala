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

  case class ArrString(v: Array[String]) extends Value
  case class ArrInt(v: Array[Int]) extends Value
  case class ArrLong(v: Array[Long]) extends Value
  case class ArrFloat(v: Array[Float]) extends Value
  case class ArrDouble(v: Array[Double]) extends Value
  case class ArrBoolean(v: Array[Boolean]) extends Value
  case class ArrBigInt(v: Array[BigInt]) extends Value
  case class ArrBigDecimal(v: Array[BigDecimal]) extends Value
  case class ArrDate(v: Array[Date]) extends Value
  case class ArrDuration(v: Array[Duration]) extends Value
  case class ArrInstant(v: Array[Instant]) extends Value
  case class ArrLocalDate(v: Array[LocalDate]) extends Value
  case class ArrLocalTime(v: Array[LocalTime]) extends Value
  case class ArrLocalDateTime(v: Array[LocalDateTime]) extends Value
  case class ArrOffsetTime(v: Array[OffsetTime]) extends Value
  case class ArrOffsetDateTime(v: Array[OffsetDateTime]) extends Value
  case class ArrZonedDateTime(v: Array[ZonedDateTime]) extends Value
  case class ArrUUID(v: Array[UUID]) extends Value
  case class ArrURI(v: Array[URI]) extends Value
  case class ArrByte(v: Array[Byte]) extends Value
  case class ArrShort(v: Array[Short]) extends Value
  case class ArrChar(v: Array[Char]) extends Value

  case class MapString(v: Map[String, String]) extends Value
  case class MapInt(v: Map[String, Int]) extends Value
  case class MapLong(v: Map[String, Long]) extends Value
  case class MapFloat(v: Map[String, Float]) extends Value
  case class MapDouble(v: Map[String, Double]) extends Value
  case class MapBoolean(v: Map[String, Boolean]) extends Value
  case class MapBigInt(v: Map[String, BigInt]) extends Value
  case class MapBigDecimal(v: Map[String, BigDecimal]) extends Value
  case class MapDate(v: Map[String, Date]) extends Value
  case class MapDuration(v: Map[String, Duration]) extends Value
  case class MapInstant(v: Map[String, Instant]) extends Value
  case class MapLocalDate(v: Map[String, LocalDate]) extends Value
  case class MapLocalTime(v: Map[String, LocalTime]) extends Value
  case class MapLocalDateTime(v: Map[String, LocalDateTime]) extends Value
  case class MapOffsetTime(v: Map[String, OffsetTime]) extends Value
  case class MapOffsetDateTime(v: Map[String, OffsetDateTime]) extends Value
  case class MapZonedDateTime(v: Map[String, ZonedDateTime]) extends Value
  case class MapUUID(v: Map[String, UUID]) extends Value
  case class MapURI(v: Map[String, URI]) extends Value
  case class MapByte(v: Map[String, Byte]) extends Value
  case class MapShort(v: Map[String, Short]) extends Value
  case class MapChar(v: Map[String, Char]) extends Value
}
