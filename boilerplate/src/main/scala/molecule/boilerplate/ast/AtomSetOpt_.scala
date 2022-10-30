// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AtomSetOpt_ { self: ModelBase =>

  sealed trait AtomSetOpt extends AtomSet
  
  case class AtomSetOptString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[String]]] = None,
    defaultValue: Option[Set[String]] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Int]]] = None,
    defaultValue: Option[Set[Int]] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Long]]] = None,
    defaultValue: Option[Set[Long]] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Float]]] = None,
    defaultValue: Option[Set[Float]] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Double]]] = None,
    defaultValue: Option[Set[Double]] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Boolean]]] = None,
    defaultValue: Option[Set[Boolean]] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[BigInt]]] = None,
    defaultValue: Option[Set[BigInt]] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[BigDecimal]]] = None,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Date]]] = None,
    defaultValue: Option[Set[Date]] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[UUID]]] = None,
    defaultValue: Option[Set[UUID]] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[URI]]] = None,
    defaultValue: Option[Set[URI]] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Byte]]] = None,
    defaultValue: Option[Set[Byte]] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Short]]] = None,
    defaultValue: Option[Set[Short]] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt

  case class AtomSetOptChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Option[Seq[Set[Char]]] = None,
    defaultValue: Option[Set[Char]] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AtomSetOpt
}