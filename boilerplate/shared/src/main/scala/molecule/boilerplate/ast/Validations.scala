package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}


trait Validations {

  case class ValidateString() {
    def validate(v: String): Seq[String] = Nil
  }
  case class ValidateInt() {
    def validate(v: Int): Seq[String] = Nil
  }
  case class ValidateLong() {
    def validate(v: Long): Seq[String] = Nil
  }
  case class ValidateFloat() {
    def validate(v: Float): Seq[String] = Nil
  }
  case class ValidateDouble() {
    def validate(v: Double): Seq[String] = Nil
  }
  case class ValidateBoolean() {
    def validate(v: Boolean): Seq[String] = Nil
  }
  case class ValidateBigInt() {
    def validate(v: BigInt): Seq[String] = Nil
  }
  case class ValidateBigDecimal() {
    def validate(v: BigDecimal): Seq[String] = Nil
  }
  case class ValidateDate() {
    def validate(v: Date): Seq[String] = Nil
  }
  case class ValidateUUID() {
    def validate(v: UUID): Seq[String] = Nil
  }
  case class ValidateURI() {
    def validate(v: URI): Seq[String] = Nil
  }
  case class ValidateByte() {
    def validate(v: Byte): Seq[String] = Nil
  }
  case class ValidateShort() {
    def validate(v: Short): Seq[String] = Nil
  }
  case class ValidateChar() {
    def validate(v: Char): Seq[String] = Nil
  }
}
