package molecule.core.ast

import java.net.URI
import java.time.*
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

  case class SeqString(v: Seq[String]) extends Value
  case class SeqInt(v: Seq[Int]) extends Value
  case class SeqLong(v: Seq[Long]) extends Value
  case class SeqFloat(v: Seq[Float]) extends Value
  case class SeqDouble(v: Seq[Double]) extends Value
  case class SeqBoolean(v: Seq[Boolean]) extends Value
  case class SeqBigInt(v: Seq[BigInt]) extends Value
  case class SeqBigDecimal(v: Seq[BigDecimal]) extends Value
  case class SeqDate(v: Seq[Date]) extends Value
  case class SeqDuration(v: Seq[Duration]) extends Value
  case class SeqInstant(v: Seq[Instant]) extends Value
  case class SeqLocalDate(v: Seq[LocalDate]) extends Value
  case class SeqLocalTime(v: Seq[LocalTime]) extends Value
  case class SeqLocalDateTime(v: Seq[LocalDateTime]) extends Value
  case class SeqOffsetTime(v: Seq[OffsetTime]) extends Value
  case class SeqOffsetDateTime(v: Seq[OffsetDateTime]) extends Value
  case class SeqZonedDateTime(v: Seq[ZonedDateTime]) extends Value
  case class SeqUUID(v: Seq[UUID]) extends Value
  case class SeqURI(v: Seq[URI]) extends Value
  case class ArrayByte(v: Array[Byte]) extends Value
  case class SeqShort(v: Seq[Short]) extends Value
  case class SeqChar(v: Seq[Char]) extends Value

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
