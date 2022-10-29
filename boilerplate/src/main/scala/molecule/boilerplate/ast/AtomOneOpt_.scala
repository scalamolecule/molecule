// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AtomOneOpt_ { self: ModelBase =>

  sealed trait AtomOneOpt extends Atom
  
  case class AtomOneOptString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt

  case class AtomOneOptURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AtomOneOpt
}