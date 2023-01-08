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


  // GENERATED (edit in _Model generator) ======================================
  
  sealed trait AttrOneMan extends AttrOne with Mandatory
  
  case class AttrOneManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneManInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrOneManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneManDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneManBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneManChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrOneOpt extends AttrOne with Optional
  
  case class AttrOneOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[String]] = None,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Int]] = None,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneOptInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Long]] = None,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrOneOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Float]] = None,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Double]] = None,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneOptDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Boolean]] = None,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneOptBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigInt]] = None,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigDecimal]] = None,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Date]] = None,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[UUID]] = None,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[URI]] = None,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Byte]] = None,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Short]] = None,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Char]] = None,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneOptChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrOneTac extends AttrOne with Tacit
  
  case class AttrOneTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    defaultValue: Option[String] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    defaultValue: Option[Int] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneTacInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    defaultValue: Option[Long] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrOneTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    defaultValue: Option[Float] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    defaultValue: Option[Double] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneTacDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    defaultValue: Option[Boolean] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some($v)")
      s"""AttrOneTacBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    defaultValue: Option[BigInt] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    defaultValue: Option[BigDecimal] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    defaultValue: Option[Date] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    defaultValue: Option[UUID] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    defaultValue: Option[URI] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    defaultValue: Option[Byte] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    defaultValue: Option[Short] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrOneTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    defaultValue: Option[Char] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(v => s"Some(${format(v)})")
      s"""AttrOneTacChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetMan extends AttrSet with Mandatory
  
  case class AttrSetManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetManInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrSetManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetManDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetManBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetManChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetOpt extends AttrSet with Optional
  
  case class AttrSetOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[String]]] = None,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Int]]] = None,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetOptInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Long]]] = None,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrSetOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Float]]] = None,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Double]]] = None,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetOptDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Boolean]]] = None,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetOptBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigInt]]] = None,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigDecimal]]] = None,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Date]]] = None,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[UUID]]] = None,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[URI]]] = None,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Byte]]] = None,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Short]]] = None,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Char]]] = None,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetOptChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }


  sealed trait AttrSetTac extends AttrSet with Tacit
  
  case class AttrSetTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    defaultValue: Option[Set[String]] = None,
    validation: Option[ValidateString] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacString("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    defaultValue: Option[Set[Int]] = None,
    validation: Option[ValidateInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetTacInt("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    defaultValue: Option[Set[Long]] = None,
    validation: Option[ValidateLong] = None,
    override val sort: Option[String] = None,
    isRef: Boolean = false
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacLong("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)}, $isRef)"""
    }
  }

  case class AttrSetTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    defaultValue: Option[Set[Float]] = None,
    validation: Option[ValidateFloat] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacFloat("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    defaultValue: Option[Set[Double]] = None,
    validation: Option[ValidateDouble] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetTacDouble("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    defaultValue: Option[Set[Boolean]] = None,
    validation: Option[ValidateBoolean] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.mkString("Set(", ", ", ")")})")
      s"""AttrSetTacBoolean("$ns", "$attr", $op, $vss, $defV, ${opt(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    defaultValue: Option[Set[BigInt]] = None,
    validation: Option[ValidateBigInt] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacBigInt("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    defaultValue: Option[Set[BigDecimal]] = None,
    validation: Option[ValidateBigDecimal] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacBigDecimal("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    defaultValue: Option[Set[Date]] = None,
    validation: Option[ValidateDate] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacDate("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    defaultValue: Option[Set[UUID]] = None,
    validation: Option[ValidateUUID] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacUUID("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    defaultValue: Option[Set[URI]] = None,
    validation: Option[ValidateURI] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacURI("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    defaultValue: Option[Set[Byte]] = None,
    validation: Option[ValidateByte] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Byte): String = "$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacByte("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    defaultValue: Option[Set[Short]] = None,
    validation: Option[ValidateShort] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Short): String = "$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacShort("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }

  case class AttrSetTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    defaultValue: Option[Set[Char]] = None,
    validation: Option[ValidateChar] = None,
    override val sort: Option[String] = None
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Char): String = "'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      def defV: String = defaultValue.fold("None")(set => s"Some(${set.map(format).mkString("Set(", ", ", ")")})")
      s"""AttrSetTacChar("$ns", "$attr", $op, $vss, $defV, ${o(validation)}, ${oStr(sort)})"""
    }
  }
}