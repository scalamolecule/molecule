package molecule.boilerplate.ast

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._


object Model extends Model
trait Model extends Validations {

  sealed trait Mode
  trait Mandatory extends Mode
  trait Optional extends Mode
  trait Tacit extends Mode


  sealed trait Element {
    def render(i: Int = 0): String = "  " * i + this.toString
    def renders(es: List[Element], i: Int): String = es.map(_.render(i)).mkString(",\n")
  }

  trait Attr extends Element {
    val ns    : String
    val attr  : String
    val op    : Op
    val errors: Seq[String]
    val status: Option[String]
    val sort  : Option[String]
    def unapply(a: Attr): (String, String, Op) = (a.ns, a.attr, a.op)
    def name = ns + "." + attr
    protected def errs: String = if (errors.isEmpty) "Nil" else errors.mkString("Seq(\"", "\", \"", "\")")
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
  ) extends Element {
    override def toString: String = {
      s"""Ref("$ns", "$refAttr", "$refNs", $card)"""
    }
  }

  case class BackRef(backRef: String) extends Element {
    override def toString: String = {
      s"""BackRef("$backRef")"""
    }
  }

  case class Composite(elements: List[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      s"""|${"  " * i}Composite(List(
          |${renders(elements, i + 1)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class Nested(ref: Ref, elements: List[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}Nested(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class NestedOpt(ref: Ref, elements: List[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}NestedOpt(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class TxMetaData(elements: List[Element]) extends Element with Mandatory {
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
  case class Fn(fn: String, n: Option[Int] = None) extends Op
  case object Unify extends Op
  case object Add extends Op
  case object Swap extends Op
  case object Remove extends Op

  // Email regex for validators in boilerplate code
  // todo: make configurable
  // From section 5 in https://www.baeldung.com/java-email-validation-regex
  // Allowing unicode characters
  val emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$".r


  // GENERATED from here and below (edit in _Model generator) ======================================

  sealed trait AttrOneMan extends AttrOne with Mandatory

  case class AttrOneManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrOneOpt extends AttrOne with Optional

  case class AttrOneOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[String]] = None,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Int]] = None,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Long]] = None,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Float]] = None,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Double]] = None,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Date]] = None,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[URI]] = None,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Byte]] = None,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Short]] = None,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Char]] = None,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrOneTac extends AttrOne with Tacit

  case class AttrOneTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetMan extends AttrSet with Mandatory

  case class AttrSetManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetOpt extends AttrSet with Optional

  case class AttrSetOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[String]]] = None,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Int]]] = None,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Long]]] = None,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Float]]] = None,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Double]]] = None,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Boolean]]] = None,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigInt]]] = None,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigDecimal]]] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Date]]] = None,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[UUID]]] = None,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[URI]]] = None,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Byte]]] = None,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Short]]] = None,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Char]]] = None,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetTac extends AttrSet with Tacit

  case class AttrSetTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    validation: Option[ValidateString] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacString("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    validation: Option[ValidateInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacInt("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    validation: Option[ValidateLong] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacLong("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    validation: Option[ValidateFloat] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacFloat("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    validation: Option[ValidateDouble] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacDouble("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    validation: Option[ValidateBoolean] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBoolean("$ns", "$attr", $op, $vss, ${opt(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    validation: Option[ValidateBigInt] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBigInt("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    validation: Option[ValidateBigDecimal] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBigDecimal("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    validation: Option[ValidateDate] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacDate("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    validation: Option[ValidateUUID] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacUUID("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    validation: Option[ValidateURI] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacURI("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    validation: Option[ValidateByte] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacByte("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    validation: Option[ValidateShort] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacShort("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    validation: Option[ValidateChar] = None,
    override val errors: Seq[String] = Nil,
    override val status: Option[String] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacChar("$ns", "$attr", $op, $vss, ${o(validation)}, $errs, ${oStr(status)}, ${oStr(sort)})"""
    }
  }
}