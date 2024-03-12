// GENERATED CODE ********************************
package molecule.boilerplate.ops

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

trait ModelTransformations_ {

  private def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)

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
      case a: AttrSeqMan => AttrSeqManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      case a: AttrMapMan => AttrMapManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def asIs(es: List[Element], kw: Kw, n: Option[Int] = None): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManID             => a.copy(op = Fn(kw.toString, n))
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
        case a: AttrSetManID             => a.copy(op = Fn(kw.toString, n))
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
      case a: AttrSeqMan => a match {
        case a: AttrSeqManID             => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManString         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManInt            => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManLong           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManFloat          => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManDouble         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManBoolean        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManBigInt         => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManBigDecimal     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManDate           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManDuration       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManInstant        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManLocalDate      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManLocalTime      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManLocalDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManOffsetTime     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManOffsetDateTime => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManZonedDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManUUID           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManURI            => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManByte           => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManShort          => a.copy(op = Fn(kw.toString, n))
        case a: AttrSeqManChar           => a.copy(op = Fn(kw.toString, n))
      }
      case a: AttrMapMan => a match {
        case a: AttrMapManID             => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManString         => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManInt            => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManLong           => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManFloat          => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManDouble         => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManBoolean        => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManBigInt         => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManBigDecimal     => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManDate           => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManDuration       => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManInstant        => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManLocalDate      => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManLocalTime      => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManLocalDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManOffsetTime     => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManOffsetDateTime => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManZonedDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManUUID           => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManURI            => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManByte           => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManShort          => a.copy(op = Fn(kw.toString, n))
        case a: AttrMapManChar           => a.copy(op = Fn(kw.toString, n))
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOne[T](es: List[Element], op: Op, vs: Seq[T]): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManID =>
          val vs1     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

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
        case a: AttrOneTacID =>
          val vs1     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

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
        case a: AttrOneOptID =>
          val vs1     = vs.asInstanceOf[Option[Seq[String]]]
          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            vs1.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors1)

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
        case a: AttrSetManID =>
          val sets    = vs.asInstanceOf[Seq[Set[String]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

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
        case a: AttrSetTacID =>
          val sets    = vs.asInstanceOf[Seq[Set[String]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

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
        case a: AttrSetOptID =>
          val sets    = vs.asInstanceOf[Option[Seq[Set[String]]]]
          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = sets, errors = errors1)

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

  protected def addSeq[T](es: List[Element], op: Op, vs: Seq[Seq[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrSeqMan => a match {
        case a: AttrSeqManID =>
          val seqs    = vs.asInstanceOf[Seq[Seq[String]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManString =>
          val seqs    = vs.asInstanceOf[Seq[Seq[String]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManInt =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Int]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManLong =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Long]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManFloat =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Float]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManDouble =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Double]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManBoolean =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Boolean]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManBigInt =>
          val seqs    = vs.asInstanceOf[Seq[Seq[BigInt]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManBigDecimal =>
          val seqs    = vs.asInstanceOf[Seq[Seq[BigDecimal]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManDate =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Date]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManDuration =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Duration]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManInstant =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Instant]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManLocalDate =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalDate]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManLocalTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManLocalDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManOffsetTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[OffsetTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManOffsetDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[OffsetDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManZonedDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[ZonedDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManUUID =>
          val seqs    = vs.asInstanceOf[Seq[Seq[UUID]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManURI =>
          val seqs    = vs.asInstanceOf[Seq[Seq[URI]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManShort =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Short]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManChar =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Char]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqManByte => ???
      }
      case a: AttrSeqTac => a match {
        case a: AttrSeqTacID =>
          val seqs    = vs.asInstanceOf[Seq[Seq[String]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacString =>
          val seqs    = vs.asInstanceOf[Seq[Seq[String]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacInt =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Int]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacLong =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Long]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacFloat =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Float]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacDouble =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Double]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacBoolean =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Boolean]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacBigInt =>
          val seqs    = vs.asInstanceOf[Seq[Seq[BigInt]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacBigDecimal =>
          val seqs    = vs.asInstanceOf[Seq[Seq[BigDecimal]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacDate =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Date]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacDuration =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Duration]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacInstant =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Instant]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacLocalDate =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalDate]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacLocalTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacLocalDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[LocalDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacOffsetTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[OffsetTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacOffsetDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[OffsetDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacZonedDateTime =>
          val seqs    = vs.asInstanceOf[Seq[Seq[ZonedDateTime]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacUUID =>
          val seqs    = vs.asInstanceOf[Seq[Seq[UUID]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacURI =>
          val seqs    = vs.asInstanceOf[Seq[Seq[URI]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacShort =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Short]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacChar =>
          val seqs    = vs.asInstanceOf[Seq[Seq[Char]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqTacByte => ???
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptSeq[T](es: List[Element], op: Op, vs: Option[Seq[Seq[T]]]): List[Element] = {
    val last = es.last match {
      case a: AttrSeqOpt => a match {
        case a: AttrSeqOptID =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[String]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptString =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[String]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptInt =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Int]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptLong =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Long]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptFloat =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Float]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptDouble =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Double]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptBoolean =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Boolean]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptBigInt =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[BigInt]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptBigDecimal =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[BigDecimal]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptDate =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Date]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptDuration =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Duration]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptInstant =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Instant]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptLocalDate =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[LocalDate]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptLocalTime =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[LocalTime]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptLocalDateTime =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[LocalDateTime]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptOffsetTime =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[OffsetTime]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptOffsetDateTime =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[OffsetDateTime]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptZonedDateTime =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[ZonedDateTime]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptUUID =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[UUID]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptURI =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[URI]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptShort =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Short]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptChar =>
          val seqs    = vs.asInstanceOf[Option[Seq[Seq[Char]]]]
          val errors1 = if (seqs.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seqs.get.flatMap(seq => seq.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = seqs, errors = errors1)

        case a: AttrSeqOptByte => ???
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addBAr[T](es: List[Element], op: Op, ba: Seq[Array[T]]): List[Element] = {
    es.init :+ (es.last match {
      case a: AttrSeqManByte => a.copy(op = op, vs = ba.asInstanceOf[Seq[Array[Byte]]])
      case a: AttrSeqTacByte => a.copy(op = op, vs = ba.asInstanceOf[Seq[Array[Byte]]])
      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
    })
  }

  protected def addOptBAr[T](es: List[Element], op: Op, optBA: Option[Array[T]]): List[Element] = {
    es.init :+ (es.last match {
      case a: AttrSeqOptByte =>
        a.copy(op = op, vs = optBA.asInstanceOf[Option[Array[Byte]]].map(array => Seq(array)))
      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
    })
  }

  protected def addMap[T](es: List[Element], op: Op, vs: Seq[Map[String, T]]): List[Element] = {
    val last = es.last match {
      case a: AttrMapMan => a match {
        case a: AttrMapManID =>
          val maps    = vs.asInstanceOf[Seq[Map[String, String]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManString =>
          val maps    = vs.asInstanceOf[Seq[Map[String, String]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManInt =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Int]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManLong =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Long]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManFloat =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Float]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManDouble =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Double]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManBoolean =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Boolean]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManBigInt =>
          val maps    = vs.asInstanceOf[Seq[Map[String, BigInt]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManBigDecimal =>
          val maps    = vs.asInstanceOf[Seq[Map[String, BigDecimal]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManDate =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Date]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManDuration =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Duration]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManInstant =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Instant]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManLocalDate =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalDate]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManLocalTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManLocalDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManOffsetTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, OffsetTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManOffsetDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, OffsetDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManZonedDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, ZonedDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManUUID =>
          val maps    = vs.asInstanceOf[Seq[Map[String, UUID]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManURI =>
          val maps    = vs.asInstanceOf[Seq[Map[String, URI]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManByte =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Byte]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManShort =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Short]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapManChar =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Char]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)
      }
      case a: AttrMapTac => a match {
        case a: AttrMapTacID =>
          val maps    = vs.asInstanceOf[Seq[Map[String, String]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacString =>
          val maps    = vs.asInstanceOf[Seq[Map[String, String]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacInt =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Int]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacLong =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Long]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacFloat =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Float]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacDouble =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Double]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacBoolean =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Boolean]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacBigInt =>
          val maps    = vs.asInstanceOf[Seq[Map[String, BigInt]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacBigDecimal =>
          val maps    = vs.asInstanceOf[Seq[Map[String, BigDecimal]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacDate =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Date]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacDuration =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Duration]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacInstant =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Instant]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacLocalDate =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalDate]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacLocalTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacLocalDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, LocalDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacOffsetTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, OffsetTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacOffsetDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, OffsetDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacZonedDateTime =>
          val maps    = vs.asInstanceOf[Seq[Map[String, ZonedDateTime]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacUUID =>
          val maps    = vs.asInstanceOf[Seq[Map[String, UUID]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacURI =>
          val maps    = vs.asInstanceOf[Seq[Map[String, URI]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacByte =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Byte]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacShort =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Short]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)

        case a: AttrMapTacChar =>
          val maps    = vs.asInstanceOf[Seq[Map[String, Char]]]
          val errors1 = if (maps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            maps.flatMap(map => map.flatMap { case (k, v) => validator.validate(v) })
          }
          a.copy(op = op, vs = maps, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptMap[T](es: List[Element], op: Op, vs: Option[Seq[Map[String, T]]]): List[Element] = {
    val last = es.last match {
      case a: AttrMapOpt => a match {
        case a: AttrMapOptID =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, String]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptString =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, String]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptInt =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Int]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptLong =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Long]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptFloat =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Float]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptDouble =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Double]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptBoolean =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Boolean]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptBigInt =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, BigInt]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptBigDecimal =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, BigDecimal]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptDate =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Date]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptDuration =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Duration]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptInstant =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Instant]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptLocalDate =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, LocalDate]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptLocalTime =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, LocalTime]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptLocalDateTime =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, LocalDateTime]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptOffsetTime =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, OffsetTime]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptOffsetDateTime =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, OffsetDateTime]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptZonedDateTime =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, ZonedDateTime]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptUUID =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, UUID]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptURI =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, URI]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptByte =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Byte]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptShort =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Short]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)

        case a: AttrMapOptChar =>
          val optMaps = vs.asInstanceOf[Option[Seq[Map[String, Char]]]]
          val errors1 = if (optMaps.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            optMaps.fold(Seq.empty[String]) { maps =>
              maps.flatMap { map =>
                map.values.toSeq.flatMap(validator.validate)
              }
            }
          }
          a.copy(op = op, vs = optMaps, errors = errors1)
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
        case a: AttrOneManID             => a.copy(sort = Some(sort))
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
        case a: AttrOneOptID             => a.copy(sort = Some(sort))
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

  @tailrec
  private def resolvePath(es: List[Element], path: List[String]): List[String] = {
    es match {
      case e :: tail => e match {
        case r: Ref  =>
          val p = if (path.isEmpty) List(r.ns, r.refAttr, r.refNs) else List(r.refAttr, r.refNs)
          resolvePath(tail, path ++ p)
        case a: Attr => resolvePath(tail, if (path.isEmpty) List(a.ns) else path)
        case other   => throw ModelError("Invalid element in filter attribute path: " + other)
      }
      case Nil       => path
    }
  }

  protected def filterAttr(es: List[Element], op: Op, filterAttrMolecule: Molecule): List[Element] = {
    val filterAttr0 = filterAttrMolecule.elements.last.asInstanceOf[Attr]
    val attrs       = es.last match {
      case a: Attr =>
        val (tacitFilterAttr, adjacent) = if (a.ns == filterAttr0.ns) {
          // Rudimentary checked for same current namespace (it's the only information
          // we have now during molecule buildup). At least we can rule out if the
          // filter attribute is not adjacent to the caller attribute.
          // Could point to other branch - have to be checked later.
          // If pointing to other branch, the added filterAttr0 should be removed

          // Convert adjacent mandatory filter attribute to tacit attribute
          val tacitAttr = filterAttr0 match {
            case a: AttrOneMan => a match {
              case a: AttrOneManID             => AttrOneTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
              case a: AttrOneManString         => AttrOneTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManInt            => AttrOneTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManLong           => AttrOneTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManFloat          => AttrOneTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManDouble         => AttrOneTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManBoolean        => AttrOneTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManBigInt         => AttrOneTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManBigDecimal     => AttrOneTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManDate           => AttrOneTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManDuration       => AttrOneTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManInstant        => AttrOneTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManLocalDate      => AttrOneTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManLocalTime      => AttrOneTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManLocalDateTime  => AttrOneTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManOffsetTime     => AttrOneTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManOffsetDateTime => AttrOneTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManZonedDateTime  => AttrOneTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManUUID           => AttrOneTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManURI            => AttrOneTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManByte           => AttrOneTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManShort          => AttrOneTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrOneManChar           => AttrOneTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
            }
            case a: AttrSetMan => a match {
              case a: AttrSetManID             => AttrSetTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
              case a: AttrSetManString         => AttrSetTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManInt            => AttrSetTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManLong           => AttrSetTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManFloat          => AttrSetTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManDouble         => AttrSetTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManBoolean        => AttrSetTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManBigInt         => AttrSetTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManBigDecimal     => AttrSetTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManDate           => AttrSetTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManDuration       => AttrSetTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManInstant        => AttrSetTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManLocalDate      => AttrSetTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManLocalTime      => AttrSetTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManLocalDateTime  => AttrSetTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManOffsetTime     => AttrSetTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManOffsetDateTime => AttrSetTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManZonedDateTime  => AttrSetTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManUUID           => AttrSetTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManURI            => AttrSetTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManByte           => AttrSetTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManShort          => AttrSetTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSetManChar           => AttrSetTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
            }
            case a: AttrSeqMan => a match {
              case a: AttrSeqManID             => AttrSeqTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
              case a: AttrSeqManString         => AttrSeqTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManInt            => AttrSeqTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManLong           => AttrSeqTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManFloat          => AttrSeqTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManDouble         => AttrSeqTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManBoolean        => AttrSeqTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManBigInt         => AttrSeqTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManBigDecimal     => AttrSeqTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManDate           => AttrSeqTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManDuration       => AttrSeqTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManInstant        => AttrSeqTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManLocalDate      => AttrSeqTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManLocalTime      => AttrSeqTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManLocalDateTime  => AttrSeqTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManOffsetTime     => AttrSeqTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManOffsetDateTime => AttrSeqTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManZonedDateTime  => AttrSeqTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManUUID           => AttrSeqTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManURI            => AttrSeqTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManByte           => AttrSeqTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManShort          => AttrSeqTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrSeqManChar           => AttrSeqTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
            }
            case a: AttrMapMan => a match {
              case a: AttrMapManID             => AttrMapTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
              case a: AttrMapManString         => AttrMapTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManInt            => AttrMapTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManLong           => AttrMapTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManFloat          => AttrMapTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManDouble         => AttrMapTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManBoolean        => AttrMapTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManBigInt         => AttrMapTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManBigDecimal     => AttrMapTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManDate           => AttrMapTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManDuration       => AttrMapTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManInstant        => AttrMapTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManLocalDate      => AttrMapTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManLocalTime      => AttrMapTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManLocalDateTime  => AttrMapTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManOffsetTime     => AttrMapTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManOffsetDateTime => AttrMapTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManZonedDateTime  => AttrMapTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManUUID           => AttrMapTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManURI            => AttrMapTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManByte           => AttrMapTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManShort          => AttrMapTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrMapManChar           => AttrMapTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
            }
            case other         => other
          }
          (tacitAttr, List(filterAttr0))
        } else (filterAttr0, Nil)

        val filterPath         = resolvePath(filterAttrMolecule.elements, Nil)
        val attrWithFilterAttr = a match {
          case a: AttrOne => a match {
            case a: AttrOneMan => a match {
              // -2 is just a dummy value until we can resolve the direction to either -1, 0 or 1
              case a: AttrOneManID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneManChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a: AttrOneTac => a match {
              case a: AttrOneTacID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrOneTacChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a             => unexpected(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a match {
              case a: AttrSetManID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetManChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a: AttrSetTac => a match {
              case a: AttrSetTacID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSetTacChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a             => unexpected(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan => a match {
              case a: AttrSeqManID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqManChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a: AttrSeqTac => a match {
              case a: AttrSeqTacID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrSeqTacChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a             => unexpected(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan => a match {
              case a: AttrMapManID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapManChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a: AttrMapTac => a match {
              case a: AttrMapTacID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrMapTacChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a             => unexpected(a)
          }
        }
        attrWithFilterAttr +: adjacent
      case e       => unexpected(e)
    }
    es.init ++ attrs
  }

  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
    es.map {
      case attr: AttrOneMan => attr match {
        case a@AttrOneManID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
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
        case a@AttrOneOptID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
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

  protected def cleanUpdateElements(elements: List[Element]): List[Element] = {
    elements.map {
      case a: Attr => a match {
        case a: AttrOne => a match {
          case a: AttrOneTac => a
          case a: AttrOneMan => a match {
            case a: AttrOneManID             => a.copy(op = V)
            case a: AttrOneManString         => a.copy(op = V)
            case a: AttrOneManInt            => a.copy(op = V)
            case a: AttrOneManLong           => a.copy(op = V)
            case a: AttrOneManFloat          => a.copy(op = V)
            case a: AttrOneManDouble         => a.copy(op = V)
            case a: AttrOneManBoolean        => a.copy(op = V)
            case a: AttrOneManBigInt         => a.copy(op = V)
            case a: AttrOneManBigDecimal     => a.copy(op = V)
            case a: AttrOneManDate           => a.copy(op = V)
            case a: AttrOneManDuration       => a.copy(op = V)
            case a: AttrOneManInstant        => a.copy(op = V)
            case a: AttrOneManLocalDate      => a.copy(op = V)
            case a: AttrOneManLocalTime      => a.copy(op = V)
            case a: AttrOneManLocalDateTime  => a.copy(op = V)
            case a: AttrOneManOffsetTime     => a.copy(op = V)
            case a: AttrOneManOffsetDateTime => a.copy(op = V)
            case a: AttrOneManZonedDateTime  => a.copy(op = V)
            case a: AttrOneManUUID           => a.copy(op = V)
            case a: AttrOneManURI            => a.copy(op = V)
            case a: AttrOneManByte           => a.copy(op = V)
            case a: AttrOneManShort          => a.copy(op = V)
            case a: AttrOneManChar           => a.copy(op = V)
          }
          case a: AttrOneOpt => a match {
            case a: AttrOneOptID             => a.copy(op = V)
            case a: AttrOneOptString         => a.copy(op = V)
            case a: AttrOneOptInt            => a.copy(op = V)
            case a: AttrOneOptLong           => a.copy(op = V)
            case a: AttrOneOptFloat          => a.copy(op = V)
            case a: AttrOneOptDouble         => a.copy(op = V)
            case a: AttrOneOptBoolean        => a.copy(op = V)
            case a: AttrOneOptBigInt         => a.copy(op = V)
            case a: AttrOneOptBigDecimal     => a.copy(op = V)
            case a: AttrOneOptDate           => a.copy(op = V)
            case a: AttrOneOptDuration       => a.copy(op = V)
            case a: AttrOneOptInstant        => a.copy(op = V)
            case a: AttrOneOptLocalDate      => a.copy(op = V)
            case a: AttrOneOptLocalTime      => a.copy(op = V)
            case a: AttrOneOptLocalDateTime  => a.copy(op = V)
            case a: AttrOneOptOffsetTime     => a.copy(op = V)
            case a: AttrOneOptOffsetDateTime => a.copy(op = V)
            case a: AttrOneOptZonedDateTime  => a.copy(op = V)
            case a: AttrOneOptUUID           => a.copy(op = V)
            case a: AttrOneOptURI            => a.copy(op = V)
            case a: AttrOneOptByte           => a.copy(op = V)
            case a: AttrOneOptShort          => a.copy(op = V)
            case a: AttrOneOptChar           => a.copy(op = V)
          }
        }

        case a: AttrSet => a match {
          case a: AttrSetTac => a
          case a: AttrSetMan => a match {
            case a: AttrSetManID             => a.copy(op = V)
            case a: AttrSetManString         => a.copy(op = V)
            case a: AttrSetManInt            => a.copy(op = V)
            case a: AttrSetManLong           => a.copy(op = V)
            case a: AttrSetManFloat          => a.copy(op = V)
            case a: AttrSetManDouble         => a.copy(op = V)
            case a: AttrSetManBoolean        => a.copy(op = V)
            case a: AttrSetManBigInt         => a.copy(op = V)
            case a: AttrSetManBigDecimal     => a.copy(op = V)
            case a: AttrSetManDate           => a.copy(op = V)
            case a: AttrSetManDuration       => a.copy(op = V)
            case a: AttrSetManInstant        => a.copy(op = V)
            case a: AttrSetManLocalDate      => a.copy(op = V)
            case a: AttrSetManLocalTime      => a.copy(op = V)
            case a: AttrSetManLocalDateTime  => a.copy(op = V)
            case a: AttrSetManOffsetTime     => a.copy(op = V)
            case a: AttrSetManOffsetDateTime => a.copy(op = V)
            case a: AttrSetManZonedDateTime  => a.copy(op = V)
            case a: AttrSetManUUID           => a.copy(op = V)
            case a: AttrSetManURI            => a.copy(op = V)
            case a: AttrSetManByte           => a.copy(op = V)
            case a: AttrSetManShort          => a.copy(op = V)
            case a: AttrSetManChar           => a.copy(op = V)
          }
          case a: AttrSetOpt => a match {
            case a: AttrSetOptID             => a.copy(op = V)
            case a: AttrSetOptString         => a.copy(op = V)
            case a: AttrSetOptInt            => a.copy(op = V)
            case a: AttrSetOptLong           => a.copy(op = V)
            case a: AttrSetOptFloat          => a.copy(op = V)
            case a: AttrSetOptDouble         => a.copy(op = V)
            case a: AttrSetOptBoolean        => a.copy(op = V)
            case a: AttrSetOptBigInt         => a.copy(op = V)
            case a: AttrSetOptBigDecimal     => a.copy(op = V)
            case a: AttrSetOptDate           => a.copy(op = V)
            case a: AttrSetOptDuration       => a.copy(op = V)
            case a: AttrSetOptInstant        => a.copy(op = V)
            case a: AttrSetOptLocalDate      => a.copy(op = V)
            case a: AttrSetOptLocalTime      => a.copy(op = V)
            case a: AttrSetOptLocalDateTime  => a.copy(op = V)
            case a: AttrSetOptOffsetTime     => a.copy(op = V)
            case a: AttrSetOptOffsetDateTime => a.copy(op = V)
            case a: AttrSetOptZonedDateTime  => a.copy(op = V)
            case a: AttrSetOptUUID           => a.copy(op = V)
            case a: AttrSetOptURI            => a.copy(op = V)
            case a: AttrSetOptByte           => a.copy(op = V)
            case a: AttrSetOptShort          => a.copy(op = V)
            case a: AttrSetOptChar           => a.copy(op = V)
          }
        }

        case a: AttrSeq => a match {
          case a: AttrSeqTac => a
          case a: AttrSeqMan => a match {
            case a: AttrSeqManID             => a.copy(op = V)
            case a: AttrSeqManString         => a.copy(op = V)
            case a: AttrSeqManInt            => a.copy(op = V)
            case a: AttrSeqManLong           => a.copy(op = V)
            case a: AttrSeqManFloat          => a.copy(op = V)
            case a: AttrSeqManDouble         => a.copy(op = V)
            case a: AttrSeqManBoolean        => a.copy(op = V)
            case a: AttrSeqManBigInt         => a.copy(op = V)
            case a: AttrSeqManBigDecimal     => a.copy(op = V)
            case a: AttrSeqManDate           => a.copy(op = V)
            case a: AttrSeqManDuration       => a.copy(op = V)
            case a: AttrSeqManInstant        => a.copy(op = V)
            case a: AttrSeqManLocalDate      => a.copy(op = V)
            case a: AttrSeqManLocalTime      => a.copy(op = V)
            case a: AttrSeqManLocalDateTime  => a.copy(op = V)
            case a: AttrSeqManOffsetTime     => a.copy(op = V)
            case a: AttrSeqManOffsetDateTime => a.copy(op = V)
            case a: AttrSeqManZonedDateTime  => a.copy(op = V)
            case a: AttrSeqManUUID           => a.copy(op = V)
            case a: AttrSeqManURI            => a.copy(op = V)
            case a: AttrSeqManByte           => a.copy(op = V)
            case a: AttrSeqManShort          => a.copy(op = V)
            case a: AttrSeqManChar           => a.copy(op = V)
          }
          case a: AttrSeqOpt => a match {
            case a: AttrSeqOptID             => a.copy(op = V)
            case a: AttrSeqOptString         => a.copy(op = V)
            case a: AttrSeqOptInt            => a.copy(op = V)
            case a: AttrSeqOptLong           => a.copy(op = V)
            case a: AttrSeqOptFloat          => a.copy(op = V)
            case a: AttrSeqOptDouble         => a.copy(op = V)
            case a: AttrSeqOptBoolean        => a.copy(op = V)
            case a: AttrSeqOptBigInt         => a.copy(op = V)
            case a: AttrSeqOptBigDecimal     => a.copy(op = V)
            case a: AttrSeqOptDate           => a.copy(op = V)
            case a: AttrSeqOptDuration       => a.copy(op = V)
            case a: AttrSeqOptInstant        => a.copy(op = V)
            case a: AttrSeqOptLocalDate      => a.copy(op = V)
            case a: AttrSeqOptLocalTime      => a.copy(op = V)
            case a: AttrSeqOptLocalDateTime  => a.copy(op = V)
            case a: AttrSeqOptOffsetTime     => a.copy(op = V)
            case a: AttrSeqOptOffsetDateTime => a.copy(op = V)
            case a: AttrSeqOptZonedDateTime  => a.copy(op = V)
            case a: AttrSeqOptUUID           => a.copy(op = V)
            case a: AttrSeqOptURI            => a.copy(op = V)
            case a: AttrSeqOptByte           => a.copy(op = V)
            case a: AttrSeqOptShort          => a.copy(op = V)
            case a: AttrSeqOptChar           => a.copy(op = V)
          }
        }

        case a: AttrMap => a match {
          case a: AttrMapTac => a
          case a: AttrMapMan => a match {
            case a: AttrMapManID             => a.copy(op = V)
            case a: AttrMapManString         => a.copy(op = V)
            case a: AttrMapManInt            => a.copy(op = V)
            case a: AttrMapManLong           => a.copy(op = V)
            case a: AttrMapManFloat          => a.copy(op = V)
            case a: AttrMapManDouble         => a.copy(op = V)
            case a: AttrMapManBoolean        => a.copy(op = V)
            case a: AttrMapManBigInt         => a.copy(op = V)
            case a: AttrMapManBigDecimal     => a.copy(op = V)
            case a: AttrMapManDate           => a.copy(op = V)
            case a: AttrMapManDuration       => a.copy(op = V)
            case a: AttrMapManInstant        => a.copy(op = V)
            case a: AttrMapManLocalDate      => a.copy(op = V)
            case a: AttrMapManLocalTime      => a.copy(op = V)
            case a: AttrMapManLocalDateTime  => a.copy(op = V)
            case a: AttrMapManOffsetTime     => a.copy(op = V)
            case a: AttrMapManOffsetDateTime => a.copy(op = V)
            case a: AttrMapManZonedDateTime  => a.copy(op = V)
            case a: AttrMapManUUID           => a.copy(op = V)
            case a: AttrMapManURI            => a.copy(op = V)
            case a: AttrMapManByte           => a.copy(op = V)
            case a: AttrMapManShort          => a.copy(op = V)
            case a: AttrMapManChar           => a.copy(op = V)
          }
          case a: AttrMapOpt => a match {
            case a: AttrMapOptID             => a.copy(op = V)
            case a: AttrMapOptString         => a.copy(op = V)
            case a: AttrMapOptInt            => a.copy(op = V)
            case a: AttrMapOptLong           => a.copy(op = V)
            case a: AttrMapOptFloat          => a.copy(op = V)
            case a: AttrMapOptDouble         => a.copy(op = V)
            case a: AttrMapOptBoolean        => a.copy(op = V)
            case a: AttrMapOptBigInt         => a.copy(op = V)
            case a: AttrMapOptBigDecimal     => a.copy(op = V)
            case a: AttrMapOptDate           => a.copy(op = V)
            case a: AttrMapOptDuration       => a.copy(op = V)
            case a: AttrMapOptInstant        => a.copy(op = V)
            case a: AttrMapOptLocalDate      => a.copy(op = V)
            case a: AttrMapOptLocalTime      => a.copy(op = V)
            case a: AttrMapOptLocalDateTime  => a.copy(op = V)
            case a: AttrMapOptOffsetTime     => a.copy(op = V)
            case a: AttrMapOptOffsetDateTime => a.copy(op = V)
            case a: AttrMapOptZonedDateTime  => a.copy(op = V)
            case a: AttrMapOptUUID           => a.copy(op = V)
            case a: AttrMapOptURI            => a.copy(op = V)
            case a: AttrMapOptByte           => a.copy(op = V)
            case a: AttrMapOptShort          => a.copy(op = V)
            case a: AttrMapOptChar           => a.copy(op = V)
          }
        }
      }
      case other   => other
    }
  }

  protected def topLevelAttrCount(elements: List[Element], count: Int = 0): Int = {
    elements match {
      case Nil       => count
      case e :: tail => e match {
        case a: AttrOne       => a match {
          case _: AttrOneMan => topLevelAttrCount(tail, count + 1)
          case _: AttrOneOpt => topLevelAttrCount(tail, count + 1)
          case _             => topLevelAttrCount(tail, count)
        }
        case a: AttrSet       => a match {
          case _: AttrSetMan => topLevelAttrCount(tail, count + 1)
          case _: AttrSetOpt => topLevelAttrCount(tail, count + 1)
          case _             => topLevelAttrCount(tail, count)
        }
        case a: AttrSeq       => a match {
          case _: AttrSeqMan => topLevelAttrCount(tail, count + 1)
          case _: AttrSeqOpt => topLevelAttrCount(tail, count + 1)
          case _             => topLevelAttrCount(tail, count)
        }
        case a: AttrMap       => a match {
          case _: AttrMapMan => topLevelAttrCount(tail, count + 1)
          case _: AttrMapOpt => topLevelAttrCount(tail, count + 1)
          case _             => topLevelAttrCount(tail, count)
        }
        case _: Ref           => topLevelAttrCount(tail, count)
        case _: BackRef       => topLevelAttrCount(tail, count)
        case Nested(_, es)    => topLevelAttrCount(tail, count + countNested(es))
        case NestedOpt(_, es) => topLevelAttrCount(tail, count + countNested(es))
      }
    }
  }

  private def countNested(elements: List[Element]): Int = {
    topLevelAttrCount(elements, 0)
  }
}