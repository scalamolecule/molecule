package molecule.core.dataModel

import java.net.URI
import java.time.*
import java.util.{Date, UUID}


sealed trait Value {
  def _marker: String
  def _tpe: String
}


case object OneValue extends OneValue
trait OneValue extends Value {
  override def _marker = "One"
  override def _tpe = ""
}

case class OneString(v: String) extends OneValue
case class OneInt(v: Int) extends OneValue
case class OneLong(v: Long) extends OneValue
case class OneFloat(v: Float) extends OneValue
case class OneDouble(v: Double) extends OneValue
case class OneBoolean(v: Boolean) extends OneValue
case class OneBigInt(v: BigInt) extends OneValue
case class OneBigDecimal(v: BigDecimal) extends OneValue
case class OneDate(v: Date) extends OneValue
case class OneDuration(v: Duration) extends OneValue
case class OneInstant(v: Instant) extends OneValue
case class OneLocalDate(v: LocalDate) extends OneValue
case class OneLocalTime(v: LocalTime) extends OneValue
case class OneLocalDateTime(v: LocalDateTime) extends OneValue
case class OneOffsetTime(v: OffsetTime) extends OneValue
case class OneOffsetDateTime(v: OffsetDateTime) extends OneValue
case class OneZonedDateTime(v: ZonedDateTime) extends OneValue
case class OneUUID(v: UUID) extends OneValue
case class OneURI(v: URI) extends OneValue
case class OneByte(v: Byte) extends OneValue
case class OneShort(v: Short) extends OneValue
case class OneChar(v: Char) extends OneValue

case object SetValue extends SetValue
trait SetValue extends Value {
  override def _marker = "Set"
  override def _tpe = "Set"
}

case class SetString(v: Set[String]) extends SetValue
case class SetInt(v: Set[Int]) extends SetValue
case class SetLong(v: Set[Long]) extends SetValue
case class SetFloat(v: Set[Float]) extends SetValue
case class SetDouble(v: Set[Double]) extends SetValue
case class SetBoolean(v: Set[Boolean]) extends SetValue
case class SetBigInt(v: Set[BigInt]) extends SetValue
case class SetBigDecimal(v: Set[BigDecimal]) extends SetValue
case class SetDate(v: Set[Date]) extends SetValue
case class SetDuration(v: Set[Duration]) extends SetValue
case class SetInstant(v: Set[Instant]) extends SetValue
case class SetLocalDate(v: Set[LocalDate]) extends SetValue
case class SetLocalTime(v: Set[LocalTime]) extends SetValue
case class SetLocalDateTime(v: Set[LocalDateTime]) extends SetValue
case class SetOffsetTime(v: Set[OffsetTime]) extends SetValue
case class SetOffsetDateTime(v: Set[OffsetDateTime]) extends SetValue
case class SetZonedDateTime(v: Set[ZonedDateTime]) extends SetValue
case class SetUUID(v: Set[UUID]) extends SetValue
case class SetURI(v: Set[URI]) extends SetValue
case class SetByte(v: Set[Byte]) extends SetValue
case class SetShort(v: Set[Short]) extends SetValue
case class SetChar(v: Set[Char]) extends SetValue

case object SeqValue extends SeqValue
trait SeqValue extends Value {
  override def _marker = "Seq"
  override def _tpe = "Seq"
}

case class SeqString(v: Seq[String]) extends SeqValue
case class SeqInt(v: Seq[Int]) extends SeqValue
case class SeqLong(v: Seq[Long]) extends SeqValue
case class SeqFloat(v: Seq[Float]) extends SeqValue
case class SeqDouble(v: Seq[Double]) extends SeqValue
case class SeqBoolean(v: Seq[Boolean]) extends SeqValue
case class SeqBigInt(v: Seq[BigInt]) extends SeqValue
case class SeqBigDecimal(v: Seq[BigDecimal]) extends SeqValue
case class SeqDate(v: Seq[Date]) extends SeqValue
case class SeqDuration(v: Seq[Duration]) extends SeqValue
case class SeqInstant(v: Seq[Instant]) extends SeqValue
case class SeqLocalDate(v: Seq[LocalDate]) extends SeqValue
case class SeqLocalTime(v: Seq[LocalTime]) extends SeqValue
case class SeqLocalDateTime(v: Seq[LocalDateTime]) extends SeqValue
case class SeqOffsetTime(v: Seq[OffsetTime]) extends SeqValue
case class SeqOffsetDateTime(v: Seq[OffsetDateTime]) extends SeqValue
case class SeqZonedDateTime(v: Seq[ZonedDateTime]) extends SeqValue
case class SeqUUID(v: Seq[UUID]) extends SeqValue
case class SeqURI(v: Seq[URI]) extends SeqValue
case class ArrayByte(v: Array[Byte]) extends SeqValue // OBS: special, since Array[Byte] is a common transport type
case class SeqShort(v: Seq[Short]) extends SeqValue
case class SeqChar(v: Seq[Char]) extends SeqValue

case object MapValue extends MapValue
trait MapValue extends Value {
  override def _marker = "Map"
  override def _tpe = "Map"
}

case class MapString(v: Map[String, String]) extends MapValue
case class MapInt(v: Map[String, Int]) extends MapValue
case class MapLong(v: Map[String, Long]) extends MapValue
case class MapFloat(v: Map[String, Float]) extends MapValue
case class MapDouble(v: Map[String, Double]) extends MapValue
case class MapBoolean(v: Map[String, Boolean]) extends MapValue
case class MapBigInt(v: Map[String, BigInt]) extends MapValue
case class MapBigDecimal(v: Map[String, BigDecimal]) extends MapValue
case class MapDate(v: Map[String, Date]) extends MapValue
case class MapDuration(v: Map[String, Duration]) extends MapValue
case class MapInstant(v: Map[String, Instant]) extends MapValue
case class MapLocalDate(v: Map[String, LocalDate]) extends MapValue
case class MapLocalTime(v: Map[String, LocalTime]) extends MapValue
case class MapLocalDateTime(v: Map[String, LocalDateTime]) extends MapValue
case class MapOffsetTime(v: Map[String, OffsetTime]) extends MapValue
case class MapOffsetDateTime(v: Map[String, OffsetDateTime]) extends MapValue
case class MapZonedDateTime(v: Map[String, ZonedDateTime]) extends MapValue
case class MapUUID(v: Map[String, UUID]) extends MapValue
case class MapURI(v: Map[String, URI]) extends MapValue
case class MapByte(v: Map[String, Byte]) extends MapValue
case class MapShort(v: Map[String, Short]) extends MapValue
case class MapChar(v: Map[String, Char]) extends MapValue
