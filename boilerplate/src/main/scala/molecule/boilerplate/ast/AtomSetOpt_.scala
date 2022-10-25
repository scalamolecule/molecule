// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AtomSetOpt_ { self: ModelBase =>

  sealed trait AtomSetOpt extends Atom
  
  case class AtomSetOptString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation  : Option[ValidateString] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation  : Option[ValidateChar] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation  : Option[ValidateByte] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation  : Option[ValidateShort] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation  : Option[ValidateInt] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation  : Option[ValidateLong] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation  : Option[ValidateDate] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : String = ""
  ) extends AtomSetOpt

  case class AtomSetOptURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation  : Option[ValidateURI] = None,
    sort        : String = ""
  ) extends AtomSetOpt
}