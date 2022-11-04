// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AttrOneOpt_ { self: ModelBase =>

  sealed trait AttrOneOpt extends AttrOne
  
  case class AttrOneOptString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[String]] = None,
    defaultValue: Option[String] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Int]] = None,
    defaultValue: Option[Int] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Long]] = None,
    defaultValue: Option[Long] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Float]] = None,
    defaultValue: Option[Float] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Double]] = None,
    defaultValue: Option[Double] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Boolean]] = None,
    defaultValue: Option[Boolean] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[BigInt]] = None,
    defaultValue: Option[BigInt] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[BigDecimal]] = None,
    defaultValue: Option[BigDecimal] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Date]] = None,
    defaultValue: Option[Date] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[UUID]] = None,
    defaultValue: Option[UUID] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[URI]] = None,
    defaultValue: Option[URI] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Byte]] = None,
    defaultValue: Option[Byte] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Short]] = None,
    defaultValue: Option[Short] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Char]] = None,
    defaultValue: Option[Char] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AttrOneOpt
}