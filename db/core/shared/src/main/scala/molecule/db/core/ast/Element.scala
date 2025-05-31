package molecule.db.core.ast

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.db.base.ast.*
import molecule.db.base.util.BaseHelpers.*


sealed trait Element {
  def render(i: Int = 0): String = "  " * i + this.toString
  def renders(es: List[Element], i: Int): String = es.map(_.render(i)).mkString(",\n")
}

sealed trait Attr extends Element {
  val ent       : String
  val attr      : String
  val op        : Op
  val filterAttr: Option[(Int, List[String], Attr)]
  val validator : Option[Validator]
  val valueAttrs: List[String]
  val errors    : Seq[String] // extracted from vs of type Seq[<type>]
  val ref       : Option[String]
  val sort      : Option[String]
  val coord     : List[Int]
  lazy val bind: Boolean = ???

  def name: String = ent + "." + attr

  // skip keyword collision prevention suffix underscore
  def cleanEnt: String = ent.replace("_", "")
  def cleanAttr: String = attr.replace("_", "")
  def cleanName: String = cleanEnt + "." + cleanAttr

  protected def errs: String = if (errors.isEmpty) "Nil" else errors.mkString("List(\"", "\", \"", "\")")
  protected def vats: String = if (valueAttrs.isEmpty) "Nil" else valueAttrs.mkString("List(\"", "\", \"", "\")")
  protected def coords: String = if (coord.isEmpty) "Nil" else coord.mkString("List(", ", ", ")")
}


sealed trait AttrOne extends Attr
sealed trait AttrSet extends Attr
sealed trait AttrSeq extends Attr
sealed trait AttrMap extends Attr {
  val keys: Seq[String] = Nil
  protected def ks: String = if (keys.isEmpty) "Nil" else keys.mkString("List(\"", "\", \"", "\")")
}


case class Ref(
  ent: String,
  refAttr: String,
  ref: String = "",
  card: Card = CardOne,
  owner: Boolean = false,
  coord: List[Int] = Nil
) extends Element {
  override def toString: String = {
    val coords = if (coord.isEmpty) "Nil" else coord.mkString("List(", ", ", ")")
    s"""Ref("$ent", "$refAttr", "$ref", $card, $owner, $coords)"""
  }
  def name = ent + "." + refAttr
}

case class BackRef(
  prev: String,
  cur: String,
  coord: List[Int] = Nil
) extends Element {
  override def toString: String = {
    val coords = if (coord.isEmpty) "Nil" else coord.mkString("List(", ", ", ")")
    s"""BackRef("$prev", "$cur", $coords)"""
  }
}

case class OptEntity(attrs: List[Attr]) extends Element {
  override def render(i: Int): String = {
    val indent = "  " * i
    s"""|${indent}OptEntity(List(
        |${renders(attrs, i + 1)}))""".stripMargin
  }
  override def toString: String = render(0)
}

case class OptRef(ref: Ref, elements: List[Element]) extends Element {
  override def render(i: Int): String = {
    val indent = "  " * i
    s"""|${indent}OptRef(
        |${indent}  $ref,
        |${indent}  List(
        |${renders(elements, i + 2)}))""".stripMargin
  }
  override def toString: String = render(0)
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

case class OptNested(ref: Ref, elements: List[Element]) extends Element {
  override def render(i: Int): String = {
    val indent = "  " * i
    s"""|${indent}OptNested(
        |${indent}  $ref,
        |${indent}  List(
        |${renders(elements, i + 2)}))""".stripMargin
  }
  override def toString: String = render(0)
}

/**
 * Email regex for validators in boilerplate code
 * todo: make configurable
 * From section 5 in https://www.baeldung.com/java-email-validation-regex
 * Allowing unicode characters
 *
 * ```
 * lazy val jsEnvironment = {
 *   Seq(
 *     scalaJSLinkerConfig ~= {
 *       // Allow unicode characters in regex expressions (emailRegex)
 *       // https://www.scala-js.org/doc/regular-expressions.html
 *       _.withESFeatures(_.withESVersion(ESVersion.ES2018))
 *     },
 *   )
 * }
 * ```
 * Bootzooka has a simpler version not allowing unicode characters:
 * private val emailRegex =
 * """^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
 */
val emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$".r


// GENERATED from here and below (edit in _Element generator) ======================================

sealed trait AttrOneMan extends AttrOne with Mandatory

case class AttrOneManID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil,
//  override val bind: Boolean = false,
) extends AttrOneMan {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneManInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneManDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneManBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Byte] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneManChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneMan {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneManChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrOneOpt extends AttrOne with Optional

case class AttrOneOptID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[String]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Int]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Float]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Double]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Boolean]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[BigInt]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[BigDecimal]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Date]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Duration]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Instant]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalDate]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[OffsetTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[OffsetDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[ZonedDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[UUID]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[URI]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Byte]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Short]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneOptChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Char]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneOpt {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrOneOptChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrOneTac extends AttrOne with Tacit

case class AttrOneTacID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneTacInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneTacDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrOneTacBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Byte] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrOneTacChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrOneTac {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrOneTacChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSetMan extends AttrSet with Mandatory

case class AttrSetManID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Long] = Set.empty[Long],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[String] = Set.empty[String],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Int] = Set.empty[Int],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetManInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Long] = Set.empty[Long],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Float] = Set.empty[Float],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Double] = Set.empty[Double],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetManDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Boolean] = Set.empty[Boolean],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetManBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[BigInt] = Set.empty[BigInt],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[BigDecimal] = Set.empty[BigDecimal],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Date] = Set.empty[Date],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Duration] = Set.empty[Duration],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Instant] = Set.empty[Instant],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalDate] = Set.empty[LocalDate],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalTime] = Set.empty[LocalTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalDateTime] = Set.empty[LocalDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[OffsetTime] = Set.empty[OffsetTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[OffsetDateTime] = Set.empty[OffsetDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[ZonedDateTime] = Set.empty[ZonedDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[UUID] = Set.empty[UUID],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[URI] = Set.empty[URI],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Byte] = Set.empty[Byte],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Short] = Set.empty[Short],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetManChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Char] = Set.empty[Char],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetMan {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetManChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSetOpt extends AttrSet with Optional

case class AttrSetOptID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[String]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Int]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Float]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Double]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Boolean]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[BigInt]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[BigDecimal]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Date]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Duration]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Instant]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[LocalDate]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[LocalTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[LocalDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[OffsetTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[OffsetDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[ZonedDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[UUID]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[URI]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Byte]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Short]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetOptChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Set[Char]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetOpt {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
    s"""AttrSetOptChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSetTac extends AttrSet with Tacit

case class AttrSetTacID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Long] = Set.empty[Long],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[String] = Set.empty[String],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Int] = Set.empty[Int],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetTacInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Long] = Set.empty[Long],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Float] = Set.empty[Float],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Double] = Set.empty[Double],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetTacDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Boolean] = Set.empty[Boolean],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def vss: String = vs.mkString("Set(", ", ", ")")
    s"""AttrSetTacBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[BigInt] = Set.empty[BigInt],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[BigDecimal] = Set.empty[BigDecimal],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Date] = Set.empty[Date],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Duration] = Set.empty[Duration],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Instant] = Set.empty[Instant],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalDate] = Set.empty[LocalDate],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalTime] = Set.empty[LocalTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[LocalDateTime] = Set.empty[LocalDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[OffsetTime] = Set.empty[OffsetTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[OffsetDateTime] = Set.empty[OffsetDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[ZonedDateTime] = Set.empty[ZonedDateTime],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[UUID] = Set.empty[UUID],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[URI] = Set.empty[URI],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Byte] = Set.empty[Byte],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Short] = Set.empty[Short],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSetTacChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Set[Char] = Set.empty[Char],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSetTac {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Set(", ", ", ")")
    s"""AttrSetTacChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSeqMan extends AttrSeq with Mandatory

case class AttrSeqManID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqManInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqManDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqManBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Array[Byte] = Array.empty[Byte],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Array(", ", ", ")")
    s"""AttrSeqManByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqManChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqMan {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqManChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSeqOpt extends AttrSeq with Optional

case class AttrSeqOptID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[String]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Int]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Long]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Float]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Double]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Boolean]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[BigInt]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[BigDecimal]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Date]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Duration]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Instant]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalDate]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[LocalDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[OffsetTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[OffsetDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[ZonedDateTime]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[UUID]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[URI]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Array[Byte]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Array(", ", ", "))"))
    s"""AttrSeqOptByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Short]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqOptChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Option[Seq[Char]] = None,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqOpt {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
    s"""AttrSeqOptChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrSeqTac extends AttrSeq with Tacit

case class AttrSeqTacID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: String): String = "\"" + escStr(v) + "\""
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacString("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqTacInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Long): String = v.toString + "L"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacLong("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Float): String = v.toString + "f"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacFloat("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqTacDouble("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def vss: String = vs.mkString("Seq(", ", ", ")")
    s"""AttrSeqTacBoolean("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: BigInt): String = "BigInt(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacBigInt("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: BigDecimal): String = "BigDecimal(" + v + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacBigDecimal("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Date): String = "new Date(" + v.getTime + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Duration): String = "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacDuration("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Instant): String = "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacInstant("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: LocalDate): String = "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacLocalDate("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: LocalTime): String = "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacLocalTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: LocalDateTime): String = "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacLocalDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: OffsetTime): String = "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacOffsetTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: OffsetDateTime): String = "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacOffsetDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: ZonedDateTime): String = "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacZonedDateTime("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: UUID): String = "UUID.fromString(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacUUID("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: URI): String = "new URI(\"" + v.toString + "\")"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacURI("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Array[Byte] = Array.empty[Byte],
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Byte): String = s"$v.toByte"
    def vss: String = vs.map(format).mkString("Array(", ", ", ")")
    s"""AttrSeqTacByte("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Short): String = s"$v.toShort"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacShort("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrSeqTacChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  vs: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrSeqTac {
  override def toString: String = {
    def format(v: Char): String = s"'$v'"
    def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
    s"""AttrSeqTacChar("$ent", "$attr", $op, $vss, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrMapMan extends AttrMap with Mandatory

case class AttrMapManID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Long] = Map.empty[String, Long],
  override val keys: Seq[String] = Nil,
  values: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManID("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, String] = Map.empty[String, String],
  override val keys: Seq[String] = Nil,
  values: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: String): String = if (v == null) "null" else "\"" + escStr(v) + "\""
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManString("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Int] = Map.empty[String, Int],
  override val keys: Seq[String] = Nil,
  values: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManInt("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Long] = Map.empty[String, Long],
  override val keys: Seq[String] = Nil,
  values: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManLong("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Float] = Map.empty[String, Float],
  override val keys: Seq[String] = Nil,
  values: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Float): String = if (v.toString == "0") "null" else v.toString + "f"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManFloat("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Double] = Map.empty[String, Double],
  override val keys: Seq[String] = Nil,
  values: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManDouble("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Boolean] = Map.empty[String, Boolean],
  override val keys: Seq[String] = Nil,
  values: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManBoolean("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, BigInt] = Map.empty[String, BigInt],
  override val keys: Seq[String] = Nil,
  values: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: BigInt): String = if (v == null) "null" else "BigInt(" + v + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManBigInt("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, BigDecimal] = Map.empty[String, BigDecimal],
  override val keys: Seq[String] = Nil,
  values: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: BigDecimal): String = if (v == null) "null" else "BigDecimal(" + v + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManBigDecimal("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Date] = Map.empty[String, Date],
  override val keys: Seq[String] = Nil,
  values: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Date): String = if (v == null) "null" else "new Date(" + v.getTime + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManDate("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Duration] = Map.empty[String, Duration],
  override val keys: Seq[String] = Nil,
  values: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Duration): String = if (v == null) "null" else "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManDuration("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Instant] = Map.empty[String, Instant],
  override val keys: Seq[String] = Nil,
  values: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Instant): String = if (v == null) "null" else "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManInstant("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalDate] = Map.empty[String, LocalDate],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: LocalDate): String = if (v == null) "null" else "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManLocalDate("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalTime] = Map.empty[String, LocalTime],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: LocalTime): String = if (v == null) "null" else "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManLocalTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalDateTime] = Map.empty[String, LocalDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: LocalDateTime): String = if (v == null) "null" else "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManLocalDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, OffsetTime] = Map.empty[String, OffsetTime],
  override val keys: Seq[String] = Nil,
  values: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: OffsetTime): String = if (v == null) "null" else "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManOffsetTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, OffsetDateTime] = Map.empty[String, OffsetDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: OffsetDateTime): String = if (v == null) "null" else "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManOffsetDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, ZonedDateTime] = Map.empty[String, ZonedDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: ZonedDateTime): String = if (v == null) "null" else "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManZonedDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, UUID] = Map.empty[String, UUID],
  override val keys: Seq[String] = Nil,
  values: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: UUID): String = if (v == null) "null" else "UUID.fromString(\"" + v.toString + "\")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManUUID("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, URI] = Map.empty[String, URI],
  override val keys: Seq[String] = Nil,
  values: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: URI): String = if (v == null) "null" else "new URI(\"" + v.toString + "\")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManURI("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Byte] = Map.empty[String, Byte],
  override val keys: Seq[String] = Nil,
  values: Seq[Byte] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Byte): String = if (v.toString == "0") "null" else s"$v.toByte"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManByte("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Short] = Map.empty[String, Short],
  override val keys: Seq[String] = Nil,
  values: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Short): String = if (v.toString == "0") "null" else s"$v.toShort"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManShort("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapManChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Char] = Map.empty[String, Char],
  override val keys: Seq[String] = Nil,
  values: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapMan {
  override def toString: String = {
    def format(v: Char): String = if (v.toString == "0") "null" else s"'$v'"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapManChar("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrMapOpt extends AttrMap with Optional

case class AttrMapOptID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Long]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptID("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, String]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: String): String = if (v == null) "null" else "\"" + escStr(v) + "\""
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptString("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Int]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", $v)""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptInt("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Long]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptLong("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Float]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Float): String = if (v.toString == "0") "null" else v.toString + "f"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptFloat("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Double]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", $v)""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptDouble("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Boolean]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", $v)""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptBoolean("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, BigInt]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: BigInt): String = if (v == null) "null" else "BigInt(" + v + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptBigInt("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, BigDecimal]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: BigDecimal): String = if (v == null) "null" else "BigDecimal(" + v + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptBigDecimal("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Date]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Date): String = if (v == null) "null" else "new Date(" + v.getTime + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptDate("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Duration]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Duration): String = if (v == null) "null" else "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptDuration("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Instant]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Instant): String = if (v == null) "null" else "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptInstant("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, LocalDate]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: LocalDate): String = if (v == null) "null" else "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptLocalDate("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, LocalTime]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: LocalTime): String = if (v == null) "null" else "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptLocalTime("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, LocalDateTime]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: LocalDateTime): String = if (v == null) "null" else "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptLocalDateTime("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, OffsetTime]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: OffsetTime): String = if (v == null) "null" else "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptOffsetTime("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, OffsetDateTime]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: OffsetDateTime): String = if (v == null) "null" else "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptOffsetDateTime("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, ZonedDateTime]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: ZonedDateTime): String = if (v == null) "null" else "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptZonedDateTime("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, UUID]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: UUID): String = if (v == null) "null" else "UUID.fromString(\"" + v.toString + "\")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptUUID("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, URI]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: URI): String = if (v == null) "null" else "new URI(\"" + v.toString + "\")"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptURI("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Byte]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Byte): String = if (v.toString == "0") "null" else s"$v.toByte"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptByte("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Short]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Short): String = if (v.toString == "0") "null" else s"$v.toShort"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptShort("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapOptChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Option[Map[String, Char]] = None,
  override val keys: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapOpt {
  override def toString: String = {
    def format(v: Char): String = if (v.toString == "0") "null" else s"'$v'"
    def pairs: String = map.fold("None")(_.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Some(Map(", ", ", "))"))
    s"""AttrMapOptChar("$ent", "$attr", $op, $pairs, $ks, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}


sealed trait AttrMapTac extends AttrMap with Tacit

case class AttrMapTacID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Long] = Map.empty[String, Long],
  override val keys: Seq[String] = Nil,
  values: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacID("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacString(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, String] = Map.empty[String, String],
  override val keys: Seq[String] = Nil,
  values: Seq[String] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateString] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: String): String = if (v == null) "null" else "\"" + escStr(v) + "\""
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacString("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Int] = Map.empty[String, Int],
  override val keys: Seq[String] = Nil,
  values: Seq[Int] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacInt("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacLong(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Long] = Map.empty[String, Long],
  override val keys: Seq[String] = Nil,
  values: Seq[Long] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLong] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Long): String = if (v.toString == "0") "null" else v.toString + "L"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacLong("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacFloat(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Float] = Map.empty[String, Float],
  override val keys: Seq[String] = Nil,
  values: Seq[Float] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateFloat] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Float): String = if (v.toString == "0") "null" else v.toString + "f"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacFloat("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacDouble(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Double] = Map.empty[String, Double],
  override val keys: Seq[String] = Nil,
  values: Seq[Double] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDouble] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacDouble("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacBoolean(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Boolean] = Map.empty[String, Boolean],
  override val keys: Seq[String] = Nil,
  values: Seq[Boolean] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBoolean] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def pairs: String = map.map { case (k, v) => s"""("$k", $v)""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacBoolean("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacBigInt(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, BigInt] = Map.empty[String, BigInt],
  override val keys: Seq[String] = Nil,
  values: Seq[BigInt] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigInt] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: BigInt): String = if (v == null) "null" else "BigInt(" + v + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacBigInt("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacBigDecimal(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, BigDecimal] = Map.empty[String, BigDecimal],
  override val keys: Seq[String] = Nil,
  values: Seq[BigDecimal] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateBigDecimal] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: BigDecimal): String = if (v == null) "null" else "BigDecimal(" + v + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacBigDecimal("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Date] = Map.empty[String, Date],
  override val keys: Seq[String] = Nil,
  values: Seq[Date] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Date): String = if (v == null) "null" else "new Date(" + v.getTime + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacDate("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacDuration(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Duration] = Map.empty[String, Duration],
  override val keys: Seq[String] = Nil,
  values: Seq[Duration] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateDuration] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Duration): String = if (v == null) "null" else "Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacDuration("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacInstant(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Instant] = Map.empty[String, Instant],
  override val keys: Seq[String] = Nil,
  values: Seq[Instant] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateInstant] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Instant): String = if (v == null) "null" else "Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacInstant("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacLocalDate(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalDate] = Map.empty[String, LocalDate],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalDate] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDate] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: LocalDate): String = if (v == null) "null" else "LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacLocalDate("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacLocalTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalTime] = Map.empty[String, LocalTime],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: LocalTime): String = if (v == null) "null" else "LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacLocalTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacLocalDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, LocalDateTime] = Map.empty[String, LocalDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[LocalDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateLocalDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: LocalDateTime): String = if (v == null) "null" else "LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacLocalDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacOffsetTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, OffsetTime] = Map.empty[String, OffsetTime],
  override val keys: Seq[String] = Nil,
  values: Seq[OffsetTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: OffsetTime): String = if (v == null) "null" else "OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacOffsetTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacOffsetDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, OffsetDateTime] = Map.empty[String, OffsetDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[OffsetDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateOffsetDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: OffsetDateTime): String = if (v == null) "null" else "OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacOffsetDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacZonedDateTime(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, ZonedDateTime] = Map.empty[String, ZonedDateTime],
  override val keys: Seq[String] = Nil,
  values: Seq[ZonedDateTime] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateZonedDateTime] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: ZonedDateTime): String = if (v == null) "null" else "ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacZonedDateTime("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacUUID(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, UUID] = Map.empty[String, UUID],
  override val keys: Seq[String] = Nil,
  values: Seq[UUID] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateUUID] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: UUID): String = if (v == null) "null" else "UUID.fromString(\"" + v.toString + "\")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacUUID("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacURI(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, URI] = Map.empty[String, URI],
  override val keys: Seq[String] = Nil,
  values: Seq[URI] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateURI] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: URI): String = if (v == null) "null" else "new URI(\"" + v.toString + "\")"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacURI("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacByte(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Byte] = Map.empty[String, Byte],
  override val keys: Seq[String] = Nil,
  values: Seq[Byte] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateByte] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Byte): String = if (v.toString == "0") "null" else s"$v.toByte"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacByte("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacShort(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Short] = Map.empty[String, Short],
  override val keys: Seq[String] = Nil,
  values: Seq[Short] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateShort] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Short): String = if (v.toString == "0") "null" else s"$v.toShort"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacShort("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}

case class AttrMapTacChar(
  override val ent: String,
  override val attr: String,
  override val op: Op = V,
  map: Map[String, Char] = Map.empty[String, Char],
  override val keys: Seq[String] = Nil,
  values: Seq[Char] = Nil,
  override val filterAttr: Option[(Int, List[String], Attr)] = None,
  override val validator: Option[ValidateChar] = None,
  override val valueAttrs: List[String] = Nil,
  override val errors: Seq[String] = Nil,
  override val ref: Option[String] = None,
  override val sort: Option[String] = None,
  override val coord: List[Int] = Nil
) extends AttrMapTac {
  override def toString: String = {
    def format(v: Char): String = if (v.toString == "0") "null" else s"'$v'"
    def pairs: String = map.map { case (k, v) => s"""("$k", ${format(v)})""" }.mkString("Map(", ", ", ")")
    def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
    s"""AttrMapTacChar("$ent", "$attr", $op, $pairs, $ks, $vs, ${optFilterAttr(filterAttr)}, ${opt(validator)}, $errs, $vats, ${oStr(ref)}, ${oStr(sort)}, $coords)"""
  }
}
