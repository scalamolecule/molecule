package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._


trait Validations {
  
  sealed trait Validator {
    def validateWith(attrs: Seq[Attr]): Validator
  }

  case class ValidateString(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateString = ???
    def validate(v: String): Seq[String] = Nil
  }

  case class ValidateInt(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateInt = ???
    def validate(v: Int): Seq[String] = Nil
  }

  case class ValidateLong(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateLong = ???
    def validate(v: Long): Seq[String] = Nil
  }

  case class ValidateFloat(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateFloat = ???
    def validate(v: Float): Seq[String] = Nil
  }

  case class ValidateDouble(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateDouble = ???
    def validate(v: Double): Seq[String] = Nil
  }

  case class ValidateBoolean(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateBoolean = ???
    def validate(v: Boolean): Seq[String] = Nil
  }

  case class ValidateBigInt(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateBigInt = ???
    def validate(v: BigInt): Seq[String] = Nil
  }

  case class ValidateBigDecimal(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateBigDecimal = ???
    def validate(v: BigDecimal): Seq[String] = Nil
  }

  case class ValidateDate(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateDate = ???
    def validate(v: Date): Seq[String] = Nil
  }

  case class ValidateUUID(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateUUID = ???
    def validate(v: UUID): Seq[String] = Nil
  }

  case class ValidateURI(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateURI = ???
    def validate(v: URI): Seq[String] = Nil
  }

  case class ValidateByte(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateByte = ???
    def validate(v: Byte): Seq[String] = Nil
  }

  case class ValidateShort(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateShort = ???
    def validate(v: Short): Seq[String] = Nil
  }

  case class ValidateChar(attrs: Seq[Attr] = Nil) extends Validator {
    override def validateWith(attrs: Seq[Attr]): ValidateChar = ???
    def validate(v: Char): Seq[String] = Nil
  }
}
