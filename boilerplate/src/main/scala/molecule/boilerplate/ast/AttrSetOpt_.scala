// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AttrSetOpt_ { self: ModelBase =>

  sealed trait AttrSetOpt extends AttrSet with Optional
  
  case class AttrSetOptString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[String]]] = None,
    defaultValue: Option[Set[String]] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Int]]] = None,
    defaultValue: Option[Set[Int]] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Long]]] = None,
    defaultValue: Option[Set[Long]] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None,
    isRef       : Boolean = false
  ) extends AttrSetOpt

  case class AttrSetOptFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Float]]] = None,
    defaultValue: Option[Set[Float]] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Double]]] = None,
    defaultValue: Option[Set[Double]] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Boolean]]] = None,
    defaultValue: Option[Set[Boolean]] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[BigInt]]] = None,
    defaultValue: Option[Set[BigInt]] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[BigDecimal]]] = None,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Date]]] = None,
    defaultValue: Option[Set[Date]] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[UUID]]] = None,
    defaultValue: Option[Set[UUID]] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[URI]]] = None,
    defaultValue: Option[Set[URI]] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Byte]]] = None,
    defaultValue: Option[Set[Byte]] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Short]]] = None,
    defaultValue: Option[Set[Short]] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Char]]] = None,
    defaultValue: Option[Set[Char]] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AttrSetOpt
}