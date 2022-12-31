package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import boopickle.Default._
import molecule.base.ast.SchemaAST._


trait Validations {

  sealed trait Validate

  abstract class ValidateString {
    def validate(v: String, test: => Boolean): Either[String, String]
  }
  abstract class ValidateInt extends Validate {
    def validate(v: Int, test: => Boolean): Either[String, Int]
  }
  abstract class ValidateLong {
    def validate(v: Long): Either[String, Long]
  }
  abstract class ValidateFloat {
    def validate(v: Float): Either[String, Float]
  }
  abstract class ValidateDouble {
    def validate(v: Double): Either[String, Double]
  }
  abstract class ValidateBoolean {
    def validate(v: Boolean): Either[String, Boolean]
  }
  abstract class ValidateBigInt {
    def validate(v: BigInt): Either[String, BigInt]
  }
  abstract class ValidateBigDecimal {
    def validate(v: BigDecimal): Either[String, BigDecimal]
  }
  abstract class ValidateDate {
    def validate(v: Date): Either[String, Date]
  }
  abstract class ValidateUUID {
    def validate(v: UUID): Either[String, UUID]
  }
  abstract class ValidateURI {
    def validate(v: URI): Either[String, URI]
  }
  abstract class ValidateByte {
    def validate(v: Byte): Either[String, Byte]
  }
  abstract class ValidateShort {
    def validate(v: Short): Either[String, Short]
  }
  abstract class ValidateChar {
    def validate(v: Char): Either[String, Char]
  }


  abstract class ValidateSetString {
    def validate(v: String): Either[String, String]
  }
  abstract class ValidateSetInt {
    def validate(v: Int): Either[String, Int]
  }
  abstract class ValidateSetLong {
    def validate(v: Long): Either[String, Long]
  }
  abstract class ValidateSetFloat {
    def validate(v: Float): Either[String, Float]
  }
  abstract class ValidateSetDouble {
    def validate(v: Double): Either[String, Double]
  }
  abstract class ValidateSetBoolean {
    def validate(v: Boolean): Either[String, Boolean]
  }
  abstract class ValidateSetBigInt {
    def validate(v: BigInt): Either[String, BigInt]
  }
  abstract class ValidateSetBigDecimal {
    def validate(v: BigDecimal): Either[String, BigDecimal]
  }
  abstract class ValidateSetDate {
    def validate(v: Date): Either[String, Date]
  }
  abstract class ValidateSetUUID {
    def validate(v: UUID): Either[String, UUID]
  }
  abstract class ValidateSetURI {
    def validate(v: URI): Either[String, URI]
  }
  abstract class ValidateSetByte {
    def validate(v: Byte): Either[String, Byte]
  }
  abstract class ValidateSetShort {
    def validate(v: Short): Either[String, Short]
  }
  abstract class ValidateSetChar {
    def validate(v: Char): Either[String, Char]
  }
}
