// GENERATED CODE ********************************
package molecule.boilerplate.ops

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._

trait ModelTransformations_ {

  def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)

  protected def toInt(es: List[Element], kw: Kw): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => AttrOneManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      case a: AttrSetMan => a match {
        case _: AttrSetManBoolean =>
          if (kw.isInstanceOf[count] || kw.isInstanceOf[countDistinct]) {
            // Catch unsupported aggregation of Sets of boolean values
            AttrSetManInt(a.ns, a.attr, Fn(kw.toString, Some(-1)), refNs = a.refNs, coord = a.coord)
          } else {
            AttrSetManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
          }

        case _ => AttrSetManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      }

      case a => unexpected(a)
    }
    es.init :+ last
  }

  protected def toDouble(es: List[Element], kw: Kw): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => AttrOneManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      case a: AttrSetMan => AttrSetManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def asIs(es: List[Element], kw: Kw, n: Option[Int] = None): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString         => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManInt            => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManLong           => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManFloat          => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManDouble         => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBoolean        => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBigInt         => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBigDecimal     => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManDate           => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManDuration       => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManInstant        => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManLocalDate      => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManLocalTime      => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManLocalDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManOffsetTime     => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManOffsetDateTime => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManZonedDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManUUID           => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManURI            => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManByte           => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManShort          => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManChar           => a.copy(op = Fn(kw.toString, n))
      }
      case a: AttrSetMan => a match {
        case a: AttrSetManString         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManInt            => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManLong           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManFloat          => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManDouble         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBoolean        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBigInt         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBigDecimal     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManDate           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManDuration       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManInstant        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManLocalDate      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManLocalTime      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManLocalDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManOffsetTime     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManOffsetDateTime => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManZonedDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManUUID           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManURI            => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManByte           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManShort          => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManChar           => a.copy(op = Fn(kw.toString, n))
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOne[T](es: List[Element], op: Op, vs: Seq[T]): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString =>
          val vs1     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManInt =>
          val vs1     = vs.asInstanceOf[Seq[Int]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManLong =>
          val vs1     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManFloat =>
          val vs1     = vs.asInstanceOf[Seq[Float]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManDouble =>
          val vs1     = vs.asInstanceOf[Seq[Double]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManBoolean =>
          val vs1     = vs.asInstanceOf[Seq[Boolean]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManBigInt =>
          val vs1     = vs.asInstanceOf[Seq[BigInt]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManBigDecimal =>
          val vs1     = vs.asInstanceOf[Seq[BigDecimal]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManDate =>
          val vs1     = vs.asInstanceOf[Seq[Date]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManDuration =>
          val vs1     = vs.asInstanceOf[Seq[Duration]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManInstant =>
          val vs1     = vs.asInstanceOf[Seq[Instant]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManLocalDate =>
          val vs1     = vs.asInstanceOf[Seq[LocalDate]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManLocalTime =>
          val vs1     = vs.asInstanceOf[Seq[LocalTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManLocalDateTime =>
          val vs1     = vs.asInstanceOf[Seq[LocalDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManOffsetTime =>
          val vs1     = vs.asInstanceOf[Seq[OffsetTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManOffsetDateTime =>
          val vs1     = vs.asInstanceOf[Seq[OffsetDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManZonedDateTime =>
          val vs1     = vs.asInstanceOf[Seq[ZonedDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManUUID =>
          val vs1     = vs.asInstanceOf[Seq[UUID]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManURI =>
          val vs1     = vs.asInstanceOf[Seq[URI]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManByte =>
          val vs1     = vs.asInstanceOf[Seq[Byte]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManShort =>
          val vs1     = vs.asInstanceOf[Seq[Short]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneManChar =>
          val vs1     = vs.asInstanceOf[Seq[Char]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)
      }
      case a: AttrOneTac => a match {
        case a: AttrOneTacString =>
          val vs1     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacInt =>
          val vs1     = vs.asInstanceOf[Seq[Int]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacLong =>
          val vs1     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacFloat =>
          val vs1     = vs.asInstanceOf[Seq[Float]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacDouble =>
          val vs1     = vs.asInstanceOf[Seq[Double]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacBoolean =>
          val vs1     = vs.asInstanceOf[Seq[Boolean]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacBigInt =>
          val vs1     = vs.asInstanceOf[Seq[BigInt]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacBigDecimal =>
          val vs1     = vs.asInstanceOf[Seq[BigDecimal]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacDate =>
          val vs1     = vs.asInstanceOf[Seq[Date]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacDuration =>
          val vs1     = vs.asInstanceOf[Seq[Duration]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacInstant =>
          val vs1     = vs.asInstanceOf[Seq[Instant]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacLocalDate =>
          val vs1     = vs.asInstanceOf[Seq[LocalDate]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacLocalTime =>
          val vs1     = vs.asInstanceOf[Seq[LocalTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacLocalDateTime =>
          val vs1     = vs.asInstanceOf[Seq[LocalDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacOffsetTime =>
          val vs1     = vs.asInstanceOf[Seq[OffsetTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacOffsetDateTime =>
          val vs1     = vs.asInstanceOf[Seq[OffsetDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacZonedDateTime =>
          val vs1     = vs.asInstanceOf[Seq[ZonedDateTime]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacUUID =>
          val vs1     = vs.asInstanceOf[Seq[UUID]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacURI =>
          val vs1     = vs.asInstanceOf[Seq[URI]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacByte =>
          val vs1     = vs.asInstanceOf[Seq[Byte]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacShort =>
          val vs1     = vs.asInstanceOf[Seq[Short]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneTacChar =>
          val vs1     = vs.asInstanceOf[Seq[Char]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptOne[T](es: List[Element], op: Op, vs: Option[Seq[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrOneOpt => a match {
        case a: AttrOneOptString =>
          val vs1     = vs.asInstanceOf[Option[Seq[String]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptInt =>
          val vs1     = vs.asInstanceOf[Option[Seq[Int]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptLong =>
          val vs1     = vs.asInstanceOf[Option[Seq[Long]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptFloat =>
          val vs1     = vs.asInstanceOf[Option[Seq[Float]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptDouble =>
          val vs1     = vs.asInstanceOf[Option[Seq[Double]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptBoolean =>
          val vs1     = vs.asInstanceOf[Option[Seq[Boolean]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptBigInt =>
          val vs1     = vs.asInstanceOf[Option[Seq[BigInt]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptBigDecimal =>
          val vs1     = vs.asInstanceOf[Option[Seq[BigDecimal]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptDate =>
          val vs1     = vs.asInstanceOf[Option[Seq[Date]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptDuration =>
          val vs1     = vs.asInstanceOf[Option[Seq[Duration]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptInstant =>
          val vs1     = vs.asInstanceOf[Option[Seq[Instant]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptLocalDate =>
          val vs1     = vs.asInstanceOf[Option[Seq[LocalDate]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptLocalTime =>
          val vs1     = vs.asInstanceOf[Option[Seq[LocalTime]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptLocalDateTime =>
          val vs1     = vs.asInstanceOf[Option[Seq[LocalDateTime]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptOffsetTime =>
          val vs1     = vs.asInstanceOf[Option[Seq[OffsetTime]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptOffsetDateTime =>
          val vs1     = vs.asInstanceOf[Option[Seq[OffsetDateTime]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptZonedDateTime =>
          val vs1     = vs.asInstanceOf[Option[Seq[ZonedDateTime]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptUUID =>
          val vs1     = vs.asInstanceOf[Option[Seq[UUID]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptURI =>
          val vs1     = vs.asInstanceOf[Option[Seq[URI]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptByte =>
          val vs1     = vs.asInstanceOf[Option[Seq[Byte]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptShort =>
          val vs1     = vs.asInstanceOf[Option[Seq[Short]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

        case a: AttrOneOptChar =>
          val vs1     = vs.asInstanceOf[Option[Seq[Char]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addSet[T](es: List[Element], op: Op, vs: Seq[Set[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrSetMan => a match {
        case a: AttrSetManString =>
          val sets    = vs.asInstanceOf[Seq[Set[String]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManInt =>
          val sets    = vs.asInstanceOf[Seq[Set[Int]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManLong =>
          val sets    = vs.asInstanceOf[Seq[Set[Long]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManFloat =>
          val sets    = vs.asInstanceOf[Seq[Set[Float]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManDouble =>
          val sets    = vs.asInstanceOf[Seq[Set[Double]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManBoolean =>
          val sets    = vs.asInstanceOf[Seq[Set[Boolean]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManBigInt =>
          val sets    = vs.asInstanceOf[Seq[Set[BigInt]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManBigDecimal =>
          val sets    = vs.asInstanceOf[Seq[Set[BigDecimal]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManDate =>
          val sets    = vs.asInstanceOf[Seq[Set[Date]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManDuration =>
          val sets    = vs.asInstanceOf[Seq[Set[Duration]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManInstant =>
          val sets    = vs.asInstanceOf[Seq[Set[Instant]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManLocalDate =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalDate]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManLocalTime =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManLocalDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManOffsetTime =>
          val sets    = vs.asInstanceOf[Seq[Set[OffsetTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManOffsetDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[OffsetDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManZonedDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[ZonedDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManUUID =>
          val sets    = vs.asInstanceOf[Seq[Set[UUID]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManURI =>
          val sets    = vs.asInstanceOf[Seq[Set[URI]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManByte =>
          val sets    = vs.asInstanceOf[Seq[Set[Byte]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManShort =>
          val sets    = vs.asInstanceOf[Seq[Set[Short]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetManChar =>
          val sets    = vs.asInstanceOf[Seq[Set[Char]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)
      }
      case a: AttrSetTac => a match {
        case a: AttrSetTacString =>
          val sets    = vs.asInstanceOf[Seq[Set[String]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacInt =>
          val sets    = vs.asInstanceOf[Seq[Set[Int]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacLong =>
          val sets    = vs.asInstanceOf[Seq[Set[Long]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacFloat =>
          val sets    = vs.asInstanceOf[Seq[Set[Float]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacDouble =>
          val sets    = vs.asInstanceOf[Seq[Set[Double]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacBoolean =>
          val sets    = vs.asInstanceOf[Seq[Set[Boolean]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacBigInt =>
          val sets    = vs.asInstanceOf[Seq[Set[BigInt]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacBigDecimal =>
          val sets    = vs.asInstanceOf[Seq[Set[BigDecimal]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacDate =>
          val sets    = vs.asInstanceOf[Seq[Set[Date]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacDuration =>
          val sets    = vs.asInstanceOf[Seq[Set[Duration]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacInstant =>
          val sets    = vs.asInstanceOf[Seq[Set[Instant]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacLocalDate =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalDate]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacLocalTime =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacLocalDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[LocalDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacOffsetTime =>
          val sets    = vs.asInstanceOf[Seq[Set[OffsetTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacOffsetDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[OffsetDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacZonedDateTime =>
          val sets    = vs.asInstanceOf[Seq[Set[ZonedDateTime]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacUUID =>
          val sets    = vs.asInstanceOf[Seq[Set[UUID]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacURI =>
          val sets    = vs.asInstanceOf[Seq[Set[URI]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacByte =>
          val sets    = vs.asInstanceOf[Seq[Set[Byte]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacShort =>
          val sets    = vs.asInstanceOf[Seq[Set[Short]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetTacChar =>
          val sets    = vs.asInstanceOf[Seq[Set[Char]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptSet[T](es: List[Element], op: Op, vs: Option[Seq[Set[T]]]): List[Element] = {
    val last = es.last match {
      case a: AttrSetOpt => a match {
        case a: AttrSetOptString =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[String]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptInt =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Int]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptLong =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Long]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptFloat =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Float]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptDouble =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Double]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptBoolean =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Boolean]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptBigInt =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[BigInt]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptBigDecimal =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[BigDecimal]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptDate =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Date]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptDuration =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Duration]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptInstant =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Instant]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptLocalDate =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[LocalDate]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptLocalTime =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[LocalTime]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptLocalDateTime =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[LocalDateTime]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptOffsetTime =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[OffsetTime]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptOffsetDateTime =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[OffsetDateTime]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptZonedDateTime =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[ZonedDateTime]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptUUID =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[UUID]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptURI =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[URI]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptByte =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Byte]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptShort =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Short]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

        case a: AttrSetOptChar =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[Char]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addSort(es: List[Element], sort: String): List[Element] = {
    es.size match {
      case 1 =>
        List(setSort(es.last, sort))
      case 2 =>
        val (first, last) = (es.head, es.last)
        first match {
          case attr: Attr if attr.filterAttr.nonEmpty => List(setSort(first, sort), last)
          case _                                      => List(first, setSort(last, sort))
        }

      case _ =>
        val (prev, last) = (es.init.last, es.last)
        val sorted       = prev match {
          case attr: Attr if attr.filterAttr.nonEmpty => List(setSort(prev, sort), last)
          case _                                      => List(prev, setSort(last, sort))
        }
        es.dropRight(2) ++ sorted
    }
  }

  private def setSort(e: Element, sort: String): Element = {
    e match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString         => a.copy(sort = Some(sort))
        case a: AttrOneManInt            => a.copy(sort = Some(sort))
        case a: AttrOneManLong           => a.copy(sort = Some(sort))
        case a: AttrOneManFloat          => a.copy(sort = Some(sort))
        case a: AttrOneManDouble         => a.copy(sort = Some(sort))
        case a: AttrOneManBoolean        => a.copy(sort = Some(sort))
        case a: AttrOneManBigInt         => a.copy(sort = Some(sort))
        case a: AttrOneManBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrOneManDate           => a.copy(sort = Some(sort))
        case a: AttrOneManDuration       => a.copy(sort = Some(sort))
        case a: AttrOneManInstant        => a.copy(sort = Some(sort))
        case a: AttrOneManLocalDate      => a.copy(sort = Some(sort))
        case a: AttrOneManLocalTime      => a.copy(sort = Some(sort))
        case a: AttrOneManLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrOneManOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrOneManOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrOneManZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrOneManUUID           => a.copy(sort = Some(sort))
        case a: AttrOneManURI            => a.copy(sort = Some(sort))
        case a: AttrOneManByte           => a.copy(sort = Some(sort))
        case a: AttrOneManShort          => a.copy(sort = Some(sort))
        case a: AttrOneManChar           => a.copy(sort = Some(sort))
      }
      case a: AttrOneOpt => a match {
        case a: AttrOneOptString         => a.copy(sort = Some(sort))
        case a: AttrOneOptInt            => a.copy(sort = Some(sort))
        case a: AttrOneOptLong           => a.copy(sort = Some(sort))
        case a: AttrOneOptFloat          => a.copy(sort = Some(sort))
        case a: AttrOneOptDouble         => a.copy(sort = Some(sort))
        case a: AttrOneOptBoolean        => a.copy(sort = Some(sort))
        case a: AttrOneOptBigInt         => a.copy(sort = Some(sort))
        case a: AttrOneOptBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrOneOptDate           => a.copy(sort = Some(sort))
        case a: AttrOneOptDuration       => a.copy(sort = Some(sort))
        case a: AttrOneOptInstant        => a.copy(sort = Some(sort))
        case a: AttrOneOptLocalDate      => a.copy(sort = Some(sort))
        case a: AttrOneOptLocalTime      => a.copy(sort = Some(sort))
        case a: AttrOneOptLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrOneOptOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrOneOptOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrOneOptZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrOneOptUUID           => a.copy(sort = Some(sort))
        case a: AttrOneOptURI            => a.copy(sort = Some(sort))
        case a: AttrOneOptByte           => a.copy(sort = Some(sort))
        case a: AttrOneOptShort          => a.copy(sort = Some(sort))
        case a: AttrOneOptChar           => a.copy(sort = Some(sort))
      }

      case e => e
    }
  }

  protected def filterAttr(es: List[Element], op: Op, filterAttrMolecule: Molecule): List[Element] = {
    val filterAttr0 = filterAttrMolecule.elements.last.asInstanceOf[Attr]
    val attrs       = es.last match {
      case a: Attr =>
        val (filterAttr, adjacent) = if (a.ns == filterAttr0.ns) {
          // Convert mandatory filter attribute to tacit attribute
          filterAttr0 match {
            case a: AttrOneMan => a match {
              case a: AttrOneManString         => (AttrOneTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManInt            => (AttrOneTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManLong           => (AttrOneTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManFloat          => (AttrOneTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManDouble         => (AttrOneTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManBoolean        => (AttrOneTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManBigInt         => (AttrOneTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManBigDecimal     => (AttrOneTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManDate           => (AttrOneTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManDuration       => (AttrOneTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManInstant        => (AttrOneTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManLocalDate      => (AttrOneTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManLocalTime      => (AttrOneTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManLocalDateTime  => (AttrOneTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManOffsetTime     => (AttrOneTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManOffsetDateTime => (AttrOneTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManZonedDateTime  => (AttrOneTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManUUID           => (AttrOneTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManURI            => (AttrOneTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManByte           => (AttrOneTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManShort          => (AttrOneTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrOneManChar           => (AttrOneTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
            }
            case a: AttrSetMan => a match {
              case a: AttrSetManString         => (AttrSetTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManInt            => (AttrSetTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManLong           => (AttrSetTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManFloat          => (AttrSetTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManDouble         => (AttrSetTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManBoolean        => (AttrSetTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManBigInt         => (AttrSetTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManBigDecimal     => (AttrSetTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManDate           => (AttrSetTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManDuration       => (AttrSetTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManInstant        => (AttrSetTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManLocalDate      => (AttrSetTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManLocalTime      => (AttrSetTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManLocalDateTime  => (AttrSetTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManOffsetTime     => (AttrSetTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManOffsetDateTime => (AttrSetTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManZonedDateTime  => (AttrSetTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManUUID           => (AttrSetTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManURI            => (AttrSetTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManByte           => (AttrSetTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManShort          => (AttrSetTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
              case a: AttrSetManChar           => (AttrSetTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord), List(filterAttr0))
            }
            case other         => (other, List(filterAttr0))
          }
        } else (filterAttr0, Nil)

        a match {
          case a: AttrOne => a match {
            case a: AttrOneMan => a match {
              case a: AttrOneManString         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManInt            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManLong           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManFloat          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManDouble         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManBoolean        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManBigInt         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManBigDecimal     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManDate           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManDuration       => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManInstant        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManLocalDate      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManLocalTime      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManLocalDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManOffsetTime     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManOffsetDateTime => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManZonedDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManUUID           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManURI            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManByte           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManShort          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneManChar           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
            }
            case a: AttrOneTac => a match {
              case a: AttrOneTacString         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacInt            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacLong           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacFloat          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacDouble         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacBoolean        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacBigInt         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacBigDecimal     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacDate           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacDuration       => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacInstant        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacLocalDate      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacLocalTime      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacLocalDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacOffsetTime     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacOffsetDateTime => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacZonedDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacUUID           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacURI            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacByte           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacShort          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrOneTacChar           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
            }
            case a             => unexpected(a)
          }
          case a: AttrSet => a match {
            case a: AttrSetMan => a match {
              case a: AttrSetManString         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManInt            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManLong           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManFloat          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManDouble         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManBoolean        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManBigInt         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManBigDecimal     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManDate           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManDuration       => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManInstant        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManLocalDate      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManLocalTime      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManLocalDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManOffsetTime     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManOffsetDateTime => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManZonedDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManUUID           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManURI            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManByte           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManShort          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetManChar           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
            }
            case a: AttrSetTac => a match {
              case a: AttrSetTacString         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacInt            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacLong           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacFloat          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacDouble         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacBoolean        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacBigInt         => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacBigDecimal     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacDate           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacDuration       => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacInstant        => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacLocalDate      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacLocalTime      => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacLocalDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacOffsetTime     => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacOffsetDateTime => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacZonedDateTime  => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacUUID           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacURI            => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacByte           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacShort          => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
              case a: AttrSetTacChar           => a.copy(op = op, filterAttr = Some(filterAttr)) +: adjacent
            }
            case a             => unexpected(a)
          }
        }
      case e       => unexpected(e)
    }
    es.init ++ attrs
  }

  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
    es.map {
      case attr: AttrOneMan => attr match {
        case a@AttrOneManString(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManInt(_, _, _, _, _, _, _, _, _, Some(sort), _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLong(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManFloat(_, _, _, _, _, _, _, _, _, Some(sort), _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDouble(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBoolean(_, _, _, _, _, _, _, _, _, Some(sort), _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigInt(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigDecimal(_, _, _, _, _, _, _, _, _, Some(sort), _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDate(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDuration(_, _, _, _, _, _, _, _, _, Some(sort), _)       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManInstant(_, _, _, _, _, _, _, _, _, Some(sort), _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalDate(_, _, _, _, _, _, _, _, _, Some(sort), _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalTime(_, _, _, _, _, _, _, _, _, Some(sort), _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManOffsetTime(_, _, _, _, _, _, _, _, _, Some(sort), _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManOffsetDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManZonedDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManUUID(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManURI(_, _, _, _, _, _, _, _, _, Some(sort), _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManByte(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManShort(_, _, _, _, _, _, _, _, _, Some(sort), _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManChar(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a                                                                    => a
      }
      case attr: AttrOneOpt => attr match {
        case a@AttrOneOptString(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptInt(_, _, _, _, _, _, _, _, _, Some(sort), _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLong(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptFloat(_, _, _, _, _, _, _, _, _, Some(sort), _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDouble(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBoolean(_, _, _, _, _, _, _, _, _, Some(sort), _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigInt(_, _, _, _, _, _, _, _, _, Some(sort), _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigDecimal(_, _, _, _, _, _, _, _, _, Some(sort), _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDate(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDuration(_, _, _, _, _, _, _, _, _, Some(sort), _)       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptInstant(_, _, _, _, _, _, _, _, _, Some(sort), _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalDate(_, _, _, _, _, _, _, _, _, Some(sort), _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalTime(_, _, _, _, _, _, _, _, _, Some(sort), _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptOffsetTime(_, _, _, _, _, _, _, _, _, Some(sort), _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptOffsetDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptZonedDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptUUID(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptURI(_, _, _, _, _, _, _, _, _, Some(sort), _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptByte(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptShort(_, _, _, _, _, _, _, _, _, Some(sort), _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptChar(_, _, _, _, _, _, _, _, _, Some(sort), _)           => a.copy(sort = Some(reverseSort(sort)))
        case a                                                                    => a
      }
      case other            => other
    }
  }

  private def reverseSort(sort: String): String = sort.head match {
    case 'a' => "d" + sort.last
    case 'd' => "a" + sort.last
  }
}