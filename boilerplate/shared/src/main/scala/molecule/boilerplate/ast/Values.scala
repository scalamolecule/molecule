package molecule.boilerplate.ast

import java.net.URI
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
  case class SetUUID(v: Set[UUID]) extends Value
  case class SetURI(v: Set[URI]) extends Value
  case class SetByte(v: Set[Byte]) extends Value
  case class SetShort(v: Set[Short]) extends Value
  case class SetChar(v: Set[Char]) extends Value
}
