// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AttrOneTac_ { self: ModelBase =>

  sealed trait AttrOneTac extends AttrOne with Tacit
  
  case class AttrOneTacString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AttrOneTac
}