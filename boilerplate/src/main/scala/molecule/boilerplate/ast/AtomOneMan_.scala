// GENERATED CODE ********************************
package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}

trait AtomOneMan_ { self: ModelBase =>

  sealed trait AtomOneMan extends AtomOne
  
  case class AtomOneManString(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation  : Option[ValidateString] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManChar(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation  : Option[ValidateChar] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManByte(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation  : Option[ValidateByte] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManShort(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation  : Option[ValidateShort] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation  : Option[ValidateInt] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManLong(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation  : Option[ValidateLong] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManFloat(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation  : Option[ValidateFloat] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManDouble(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation  : Option[ValidateDouble] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManBoolean(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation  : Option[ValidateBoolean] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManBigInt(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation  : Option[ValidateBigInt] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManBigDecimal(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation  : Option[ValidateBigDecimal] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManDate(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation  : Option[ValidateDate] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManUUID(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation  : Option[ValidateUUID] = None,
    sort        : String = ""
  ) extends AtomOneMan

  case class AtomOneManURI(
    ns          : String,
    attr        : String,
    op          : Op = V,
    vs          : Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation  : Option[ValidateURI] = None,
    sort        : String = ""
  ) extends AtomOneMan
}