// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AttrSetTac_ { self: ModelBase =>

  sealed trait AttrSetTac extends AttrSet with Tacit
  
  case class AttrSetTacString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None,
    isRef       : Boolean = false
  ) extends AttrSetTac

  case class AttrSetTacFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AttrSetTac
}