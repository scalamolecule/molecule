package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.api.Keywords.Kw


object Model extends Model
trait Model extends Validations {

  sealed trait Mode
  trait Mandatory extends Mode
  trait Optional extends Mode
  trait Tacit extends Mode


  sealed trait Element {
    def render(i: Int = 0): String = "  " * i + this.toString
    def renders(es: Seq[Element], i: Int): String = es.map(_.render(i)).mkString(",\n")
  }

  trait Attr extends Element {
    val ns  : String
    val attr: String
    val op  : Op
    val sort: Option[String]
    def unapply(a: Attr): (String, String, Op) = (a.ns, a.attr, a.op)
    def name = ns + "." + attr
  }

  trait AttrOne extends Attr
  trait AttrSet extends Attr
  trait AttrArray extends Attr
  trait AttrMap extends Attr

  case class Ref(
    ns: String,
    refAttr: String,
    refNs: String = "",
    card: Cardinality = CardOne
  ) extends Element

  case class BackRef(backRef: String) extends Element

  case class Composite(elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      s"""|${"  " * i}Composite(List(
          |${renders(elements, i + 1)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class Nested(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}Nested(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class NestedOpt(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}NestedOpt(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class TxMetaData(elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      s"""|${"  " * i}TxMetaData(List(
          |${renders(elements, i + 1)}))""".stripMargin
    }
    override def toString: String = render(0)
  }


  sealed trait Op
  case object V extends Op
  case object Appl extends Op
  case object Not extends Op
  case object Eq extends Op
  case object Neq extends Op
  case object Lt extends Op
  case object Le extends Op
  case object Gt extends Op
  case object Ge extends Op
  case object NoValue extends Op
  case class Fn(fn: Kw, n: Option[Int] = None) extends Op
  case object Unify extends Op
  case object Add extends Op
  case object Swap extends Op
  case object Remove extends Op


  // GENERATED (edit in _Model generator) ======================================

  sealed trait AttrOneMan extends AttrOne with Mandatory

  case class AttrOneManString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneMan

  case class AttrOneManFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrOneMan

  case class AttrOneManChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrOneMan


  sealed trait AttrOneOpt extends AttrOne with Optional

  case class AttrOneOptString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[String]] = None,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Int]] = None,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Long]] = None,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneOpt

  case class AttrOneOptFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Float]] = None,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Double]] = None,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Boolean]] = None,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[BigInt]] = None,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[BigDecimal]] = None,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Date]] = None,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[UUID]] = None,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[URI]] = None,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Byte]] = None,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Short]] = None,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt

  case class AttrOneOptChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Char]] = None,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrOneOpt


  sealed trait AttrOneTac extends AttrOne with Tacit

  case class AttrOneTacString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneTac

  case class AttrOneTacFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrOneTac

  case class AttrOneTacChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrOneTac


  sealed trait AttrSetMan extends AttrSet with Mandatory

  case class AttrSetManString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetMan

  case class AttrSetManFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrSetMan

  case class AttrSetManChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrSetMan


  sealed trait AttrSetOpt extends AttrSet with Optional

  case class AttrSetOptString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[String]]] = None,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Int]]] = None,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Long]]] = None,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetOpt

  case class AttrSetOptFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Float]]] = None,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Double]]] = None,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Boolean]]] = None,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[BigInt]]] = None,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[BigDecimal]]] = None,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Date]]] = None,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[UUID]]] = None,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[URI]]] = None,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Byte]]] = None,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Short]]] = None,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt

  case class AttrSetOptChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Set[Char]]] = None,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrSetOpt


  sealed trait AttrSetTac extends AttrSet with Tacit

  case class AttrSetTacString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacLong(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetTac

  case class AttrSetTacFloat(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBoolean(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBigInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacBigDecimal(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacDate(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacUUID(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacURI(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacByte(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacShort(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    sort: Option[String] = None
  ) extends AttrSetTac

  case class AttrSetTacChar(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    sort: Option[String] = None
  ) extends AttrSetTac
}