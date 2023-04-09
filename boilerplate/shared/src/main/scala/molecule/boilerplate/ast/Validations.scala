package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._


trait Validations {
  
  sealed trait Validator {
    def withAttrs(_attrs: Seq[Attr]): Validator
    def withValues(_values: Seq[Value]): Validator
  }

  case class ValidateString(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateString = ???
    override def withValues(_values: Seq[Value]): ValidateString = ???
    def validate(v: String): Seq[String] = ???
  }

  case class ValidateInt(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateInt = ???
    override def withValues(_values: Seq[Value]): ValidateInt = ???
    def validate(v: Int): Seq[String] = ???
  }

  case class ValidateLong(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateLong = ???
    override def withValues(_values: Seq[Value]): ValidateLong = ???
    def validate(v: Long): Seq[String] = ???
  }

  case class ValidateFloat(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateFloat = ???
    override def withValues(_values: Seq[Value]): ValidateFloat = ???
    def validate(v: Float): Seq[String] = ???
  }

  case class ValidateDouble(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateDouble = ???
    override def withValues(_values: Seq[Value]): ValidateDouble = ???
    def validate(v: Double): Seq[String] = ???
  }

  case class ValidateBoolean(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateBoolean = ???
    override def withValues(_values: Seq[Value]): ValidateBoolean = ???
    def validate(v: Boolean): Seq[String] = ???
  }

  case class ValidateBigInt(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateBigInt = ???
    override def withValues(_values: Seq[Value]): ValidateBigInt = ???
    def validate(v: BigInt): Seq[String] = ???
  }

  case class ValidateBigDecimal(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateBigDecimal = ???
    override def withValues(_values: Seq[Value]): ValidateBigDecimal = ???
    def validate(v: BigDecimal): Seq[String] = ???
  }

  case class ValidateDate(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateDate = ???
    override def withValues(_values: Seq[Value]): ValidateDate = ???
    def validate(v: Date): Seq[String] = ???
  }

  case class ValidateUUID(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateUUID = ???
    override def withValues(_values: Seq[Value]): ValidateUUID = ???
    def validate(v: UUID): Seq[String] = ???
  }

  case class ValidateURI(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateURI = ???
    override def withValues(_values: Seq[Value]): ValidateURI = ???
    def validate(v: URI): Seq[String] = ???
  }

  case class ValidateByte(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateByte = ???
    override def withValues(_values: Seq[Value]): ValidateByte = ???
    def validate(v: Byte): Seq[String] = ???
  }

  case class ValidateShort(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateShort = ???
    override def withValues(_values: Seq[Value]): ValidateShort = ???
    def validate(v: Short): Seq[String] = ???
  }

  case class ValidateChar(_attrs: Seq[Attr] = Nil, _values: Seq[Value] = Nil) extends Validator {
    override def withAttrs(_attrs: Seq[Attr]): ValidateChar = ???
    override def withValues(_values: Seq[Value]): ValidateChar = ???
    def validate(v: Char): Seq[String] = ???
  }
}
