// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AtomSetMan_ { self: ModelBase =>

  sealed trait AtomSetMan extends Atom
  
  case class AtomSetManString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation  : Option[ValidateString] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation  : Option[ValidateChar] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation  : Option[ValidateByte] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation  : Option[ValidateShort] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation  : Option[ValidateInt] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation  : Option[ValidateLong] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation  : Option[ValidateDate] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan

  case class AtomSetManURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation  : Option[ValidateURI] = None,
    sort        : Option[String] = None
  ) extends AtomSetMan
}