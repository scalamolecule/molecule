package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}


trait Validations {

//  sealed trait Validate

  abstract class ValidateString {
    def validate(v: String): Seq[String]
  }
  abstract class ValidateInt {
    def validate(v: Int): Seq[String]
  }
  abstract class ValidateLong {
    def validate(v: Long): Seq[String]
  }
  abstract class ValidateFloat {
    def validate(v: Float): Seq[String]
  }
  abstract class ValidateDouble {
    def validate(v: Double): Seq[String]
  }
  abstract class ValidateBoolean {
    def validate(v: Boolean): Seq[String]
  }
  abstract class ValidateBigInt {
    def validate(v: BigInt): Seq[String]
  }
  abstract class ValidateBigDecimal {
    def validate(v: BigDecimal): Seq[String]
  }
  abstract class ValidateDate {
    def validate(v: Date): Seq[String]
  }
  abstract class ValidateUUID {
    def validate(v: UUID): Seq[String]
  }
  abstract class ValidateURI {
    def validate(v: URI): Seq[String]
  }
  abstract class ValidateByte {
    def validate(v: Byte): Seq[String]
  }
  abstract class ValidateShort {
    def validate(v: Short): Seq[String]
  }
  abstract class ValidateChar {
    def validate(v: Char): Seq[String]
  }


  abstract class ValidateSetString {
    def validate(v: String): Seq[String]
  }
  abstract class ValidateSetInt {
    def validate(v: Int): Seq[String]
  }
  abstract class ValidateSetLong {
    def validate(v: Long): Seq[String]
  }
  abstract class ValidateSetFloat {
    def validate(v: Float): Seq[String]
  }
  abstract class ValidateSetDouble {
    def validate(v: Double): Seq[String]
  }
  abstract class ValidateSetBoolean {
    def validate(v: Boolean): Seq[String]
  }
  abstract class ValidateSetBigInt {
    def validate(v: BigInt): Seq[String]
  }
  abstract class ValidateSetBigDecimal {
    def validate(v: BigDecimal): Seq[String]
  }
  abstract class ValidateSetDate {
    def validate(v: Date): Seq[String]
  }
  abstract class ValidateSetUUID {
    def validate(v: UUID): Seq[String]
  }
  abstract class ValidateSetURI {
    def validate(v: URI): Seq[String]
  }
  abstract class ValidateSetByte {
    def validate(v: Byte): Seq[String]
  }
  abstract class ValidateSetShort {
    def validate(v: Short): Seq[String]
  }
  abstract class ValidateSetChar {
    def validate(v: Char): Seq[String]
  }
}
