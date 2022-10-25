package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._


trait Validations {


  abstract class ValidateString {
    def validate(v: String): Either[String, Boolean]
  }
  abstract class ValidateChar {
    def validate(v: Char): Either[String, Boolean]
  }
  abstract class ValidateByte {
    def validate(v: Byte): Either[String, Boolean]
  }
  abstract class ValidateShort {
    def validate(v: Short): Either[String, Boolean]
  }
  abstract class ValidateInt {
    def validate(v: Int): Either[String, Boolean]
  }
  abstract class ValidateLong {
    def validate(v: Long): Either[String, Boolean]
  }
  abstract class ValidateFloat {
    def validate(v: Float): Either[String, Boolean]
  }
  abstract class ValidateDouble {
    def validate(v: Double): Either[String, Boolean]
  }
  abstract class ValidateBoolean {
    def validate(v: Boolean): Either[String, Boolean]
  }
  abstract class ValidateBigInt {
    def validate(v: BigInt): Either[String, Boolean]
  }
  abstract class ValidateBigDecimal {
    def validate(v: BigDecimal): Either[String, Boolean]
  }
  abstract class ValidateDate {
    def validate(v: Date): Either[String, Boolean]
  }
  abstract class ValidateUUID {
    def validate(v: UUID): Either[String, Boolean]
  }
  abstract class ValidateURI {
    def validate(v: URI): Either[String, Boolean]
  }


  abstract class ValidateSetString {
    def validate(v: String): Either[String, Boolean]
  }
  abstract class ValidateSetChar {
    def validate(v: Char): Either[String, Boolean]
  }
  abstract class ValidateSetByte {
    def validate(v: Byte): Either[String, Boolean]
  }
  abstract class ValidateSetShort {
    def validate(v: Short): Either[String, Boolean]
  }
  abstract class ValidateSetInt {
    def validate(v: Int): Either[String, Boolean]
  }
  abstract class ValidateSetLong {
    def validate(v: Long): Either[String, Boolean]
  }
  abstract class ValidateSetFloat {
    def validate(v: Float): Either[String, Boolean]
  }
  abstract class ValidateSetDouble {
    def validate(v: Double): Either[String, Boolean]
  }
  abstract class ValidateSetBoolean {
    def validate(v: Boolean): Either[String, Boolean]
  }
  abstract class ValidateSetBigInt {
    def validate(v: BigInt): Either[String, Boolean]
  }
  abstract class ValidateSetBigDecimal {
    def validate(v: BigDecimal): Either[String, Boolean]
  }
  abstract class ValidateSetDate {
    def validate(v: Date): Either[String, Boolean]
  }
  abstract class ValidateSetUUID {
    def validate(v: UUID): Either[String, Boolean]
  }
  abstract class ValidateSetURI {
    def validate(v: URI): Either[String, Boolean]
  }

}
