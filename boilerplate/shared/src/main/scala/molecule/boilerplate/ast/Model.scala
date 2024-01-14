package molecule.boilerplate.ast

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.base.util.BaseHelpers


object Model extends Model
trait Model extends Validations with Values with BaseHelpers {

  sealed trait Mode
  trait Mandatory extends Mode
  trait Optional extends Mode
  trait Tacit extends Mode


  sealed trait Element {
    def render(i: Int = 0): String = "  " * i + this.toString
    def renders(es: List[Element], i: Int): String = es.map(_.render(i)).mkString(",\n")
  }

  sealed trait Attr extends Element {
    val ns        : String
    val attr      : String
    val op        : Op
    val filterAttr: Option[(Int, List[String], Attr)]
    val validator : Option[Validator]
    val valueAttrs: Seq[String]
    val errors    : Seq[String]
    val refNs     : Option[String]
    val sort      : Option[String]
    val coord     : Seq[Int]

    // Used for id/ref attributes
    val owner: Boolean = false

    def unapply(a: Attr): (String, String, Op) = (a.ns, a.attr, a.op)
    def name: String = ns + "." + attr
    def cleanNs: String = ns.replace("_", "")
    def cleanAttr: String = attr.replace("_", "")
    def cleanName: String = cleanNs + "." + cleanAttr
    protected def errs: String = if (errors.isEmpty) "Nil" else errors.mkString("Seq(\"", "\", \"", "\")")
    protected def vats: String = if (valueAttrs.isEmpty) "Nil" else valueAttrs.mkString("Seq(\"", "\", \"", "\")")
    protected def coords: String = if (coord.isEmpty) "Nil" else coord.mkString("Seq(", ", ", ")")
  }


  sealed trait AttrOne extends Attr
  sealed trait AttrSet extends Attr
  //  trait AttrArray extends Attr
  //  trait AttrMap extends Attr


  case class Ref(
    ns: String,
    refAttr: String,
    refNs: String = "",
    card: Card = CardOne,
    owner: Boolean,
    coord: Seq[Int] = Nil
  ) extends Element {
    override def toString: String = {
      val coords = if (coord.isEmpty) "Nil" else coord.mkString("Seq(", ", ", ")")
      s"""Ref("$ns", "$refAttr", "$refNs", $card, $owner, $coords)"""
    }
    def name = ns + "." + refAttr
  }

  case class BackRef(
    prevNs: String,
    curNs: String,
    coord: Seq[Int] = Nil
  ) extends Element {
    override def toString: String = {
      val coords = if (coord.isEmpty) "Nil" else coord.mkString("Seq(", ", ", ")")
      s"""BackRef("$prevNs", "$curNs", $coords)"""
    }
  }

  case class Nested(ref: Ref, elements: List[Element]) extends Element {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}Nested(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class NestedOpt(ref: Ref, elements: List[Element]) extends Element {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}NestedOpt(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }


  sealed trait Op
  case object NoValue extends Op
  case object V extends Op
  case object Eq extends Op
  case object Neq extends Op
  case object Lt extends Op
  case object Le extends Op
  case object Gt extends Op
  case object Ge extends Op
  case object StartsWith extends Op
  case object EndsWith extends Op
  case object Contains extends Op
  case object Matches extends Op
  case object Remainder extends Op
  case object Even extends Op
  case object Odd extends Op

  // Cardinality Set
  case object Has extends Op
  case object HasNo extends Op
  case object Add extends Op
  case object Swap extends Op
  case object Remove extends Op

  case object Unify extends Op
  case class Fn(fn: String, n: Option[Int] = None) extends Op

  // Email regex for validators in boilerplate code
  // todo: make configurable
  // From section 5 in https://www.baeldung.com/java-email-validation-regex
  // Allowing unicode characters
  val emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$".r


  // GENERATED from here and below (edit in _Model generator) ======================================
  
  sealed trait AttrOneMan extends AttrOne with Mandatory
  
  case class AttrOneManID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneManBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Duration] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Instant] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalDate] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[OffsetTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[OffsetDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[ZonedDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneMan {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneManChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }


  sealed trait AttrOneOpt extends AttrOne with Optional
  
  case class AttrOneOptID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[String]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[String]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Int]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Long]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Float]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Double]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Boolean]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigInt]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[BigDecimal]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Date]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Duration]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Instant]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[LocalDate]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[LocalTime]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[LocalDateTime]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[OffsetTime]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[OffsetDateTime]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[ZonedDateTime]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[UUID]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[URI]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Byte]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Short]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Char]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneOpt {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
      s"""AttrOneOptChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }


  sealed trait AttrOneTac extends AttrOne with Tacit
  
  case class AttrOneTacID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[String] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Int] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Long] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Float] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Double] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Boolean] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def vss: String = vs.mkString("Seq(", ", ", ")")
      s"""AttrOneTacBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigInt] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[BigDecimal] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Date] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Duration] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Instant] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalDate] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[LocalDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[OffsetTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[OffsetDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[ZonedDateTime] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[UUID] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[URI] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Byte] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Short] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrOneTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Char] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrOneTac {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
      s"""AttrOneTacChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }


  sealed trait AttrSetMan extends AttrSet with Mandatory
  
  case class AttrSetManID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Duration]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Instant]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalDate]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[OffsetTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[OffsetDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[ZonedDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetManChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetMan {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetManChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }


  sealed trait AttrSetOpt extends AttrSet with Optional
  
  case class AttrSetOptID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[String]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[String]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Int]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Long]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Float]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Double]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Boolean]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigInt]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[BigDecimal]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Date]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Duration]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Instant]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[LocalDate]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[LocalTime]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[LocalDateTime]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[OffsetTime]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[OffsetDateTime]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[ZonedDateTime]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[UUID]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[URI]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Byte]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Short]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetOptChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Option[Seq[Set[Char]]] = None,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetOpt {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
      s"""AttrSetOptChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }


  sealed trait AttrSetTac extends AttrSet with Tacit
  
  case class AttrSetTacID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil,
    override val owner: Boolean = false
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacString(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[String]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateString] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: String): String = "\"" + escStr(v) + "\""
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacString("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Int]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacLong(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Long]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLong] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Long): String = v.toString + "L"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacLong("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacFloat(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Float]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateFloat] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Float): String = v.toString + "f"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacFloat("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacDouble(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Double]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDouble] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacDouble("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacBoolean(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Boolean]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBoolean] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBoolean("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacBigInt(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigInt]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigInt] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigInt): String = "BigInt(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBigInt("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacBigDecimal(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[BigDecimal]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateBigDecimal] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacBigDecimal("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Date]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Date): String = "new Date(" + v.getTime + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacDuration(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Duration]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateDuration] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacDuration("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacInstant(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Instant]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateInstant] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacInstant("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacLocalDate(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalDate]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDate] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacLocalDate("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacLocalTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacLocalTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacLocalDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[LocalDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateLocalDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacLocalDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacOffsetTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[OffsetTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacOffsetTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacOffsetDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[OffsetDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateOffsetDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacOffsetDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacZonedDateTime(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[ZonedDateTime]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateZonedDateTime] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacZonedDateTime("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacUUID(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[UUID]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateUUID] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacUUID("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacURI(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[URI]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateURI] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: URI): String = "new URI(\"" + v.toString + "\")"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacURI("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacByte(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Byte]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateByte] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Byte): String = s"$v.toByte"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacByte("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacShort(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Short]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateShort] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Short): String = s"$v.toShort"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacShort("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }

  case class AttrSetTacChar(
    override val ns: String,
    override val attr: String,
    override val op: Op = V,
    vs: Seq[Set[Char]] = Nil,
    override val filterAttr: Option[(Int, List[String], Attr)] = None,
    override val validator: Option[ValidateChar] = None,
    override val valueAttrs: Seq[String] = Nil,
    override val errors: Seq[String] = Nil,
    override val refNs: Option[String] = None,
    override val sort: Option[String] = None,
    override val coord: Seq[Int] = Nil
  ) extends AttrSetTac {
    override def toString: String = {
      def format(v: Char): String = s"'$v'"
      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
      s"""AttrSetTacChar("$ns", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(refNs)}, ${oStr(sort)}, $coords)"""
    }
  }
}