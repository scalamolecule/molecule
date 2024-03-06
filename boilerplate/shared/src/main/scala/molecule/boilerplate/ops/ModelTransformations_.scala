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
      case a: AttrArrMan => AttrArrManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
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
      case a: AttrArrMan => a match {
        case a: AttrArrManID             => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManString         => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManInt            => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManLong           => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManFloat          => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManDouble         => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManBoolean        => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManBigInt         => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManBigDecimal     => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManDate           => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManDuration       => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManInstant        => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManLocalDate      => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManLocalTime      => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManLocalDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManOffsetTime     => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManOffsetDateTime => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManZonedDateTime  => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManUUID           => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManURI            => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManByte           => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManShort          => a.copy(op = Fn(kw.toString, n))
        case a: AttrArrManChar           => a.copy(op = Fn(kw.toString, n))
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

  protected def addArr[T](es: List[Element], op: Op, vs: Seq[Array[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrArrMan => a match {
        case a: AttrArrManID =>
          val arrays  = vs.asInstanceOf[Seq[Array[String]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManString =>
          val arrays  = vs.asInstanceOf[Seq[Array[String]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManInt =>
          val arrays  = vs.asInstanceOf[Seq[Array[Int]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManLong =>
          val arrays  = vs.asInstanceOf[Seq[Array[Long]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManFloat =>
          val arrays  = vs.asInstanceOf[Seq[Array[Float]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManDouble =>
          val arrays  = vs.asInstanceOf[Seq[Array[Double]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManBoolean =>
          val arrays  = vs.asInstanceOf[Seq[Array[Boolean]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManBigInt =>
          val arrays  = vs.asInstanceOf[Seq[Array[BigInt]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManBigDecimal =>
          val arrays  = vs.asInstanceOf[Seq[Array[BigDecimal]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManDate =>
          val arrays  = vs.asInstanceOf[Seq[Array[Date]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManDuration =>
          val arrays  = vs.asInstanceOf[Seq[Array[Duration]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManInstant =>
          val arrays  = vs.asInstanceOf[Seq[Array[Instant]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManLocalDate =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalDate]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManLocalTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManLocalDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManOffsetTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[OffsetTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManOffsetDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[OffsetDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManZonedDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[ZonedDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManUUID =>
          val arrays  = vs.asInstanceOf[Seq[Array[UUID]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManURI =>
          val arrays  = vs.asInstanceOf[Seq[Array[URI]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManByte =>
          val arrays  = vs.asInstanceOf[Seq[Array[Byte]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManShort =>
          val arrays  = vs.asInstanceOf[Seq[Array[Short]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrManChar =>
          val arrays  = vs.asInstanceOf[Seq[Array[Char]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)
      }
      case a: AttrArrTac => a match {
        case a: AttrArrTacID =>
          val arrays  = vs.asInstanceOf[Seq[Array[String]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacString =>
          val arrays  = vs.asInstanceOf[Seq[Array[String]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacInt =>
          val arrays  = vs.asInstanceOf[Seq[Array[Int]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacLong =>
          val arrays  = vs.asInstanceOf[Seq[Array[Long]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacFloat =>
          val arrays  = vs.asInstanceOf[Seq[Array[Float]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacDouble =>
          val arrays  = vs.asInstanceOf[Seq[Array[Double]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacBoolean =>
          val arrays  = vs.asInstanceOf[Seq[Array[Boolean]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacBigInt =>
          val arrays  = vs.asInstanceOf[Seq[Array[BigInt]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacBigDecimal =>
          val arrays  = vs.asInstanceOf[Seq[Array[BigDecimal]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacDate =>
          val arrays  = vs.asInstanceOf[Seq[Array[Date]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacDuration =>
          val arrays  = vs.asInstanceOf[Seq[Array[Duration]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacInstant =>
          val arrays  = vs.asInstanceOf[Seq[Array[Instant]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacLocalDate =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalDate]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacLocalTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacLocalDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[LocalDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacOffsetTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[OffsetTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacOffsetDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[OffsetDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacZonedDateTime =>
          val arrays  = vs.asInstanceOf[Seq[Array[ZonedDateTime]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacUUID =>
          val arrays  = vs.asInstanceOf[Seq[Array[UUID]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacURI =>
          val arrays  = vs.asInstanceOf[Seq[Array[URI]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacByte =>
          val arrays  = vs.asInstanceOf[Seq[Array[Byte]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacShort =>
          val arrays  = vs.asInstanceOf[Seq[Array[Short]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrTacChar =>
          val arrays  = vs.asInstanceOf[Seq[Array[Char]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptArr[T](es: List[Element], op: Op, vs: Option[Seq[Array[T]]]): List[Element] = {
    val last = es.last match {
      case a: AttrArrOpt => a match {
        case a: AttrArrOptID =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[String]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptString =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[String]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptInt =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Int]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptLong =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Long]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptFloat =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Float]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptDouble =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Double]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptBoolean =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Boolean]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptBigInt =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[BigInt]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptBigDecimal =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[BigDecimal]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptDate =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Date]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptDuration =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Duration]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptInstant =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Instant]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptLocalDate =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[LocalDate]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptLocalTime =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[LocalTime]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptLocalDateTime =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[LocalDateTime]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptOffsetTime =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[OffsetTime]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptOffsetDateTime =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[OffsetDateTime]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptZonedDateTime =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[ZonedDateTime]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptUUID =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[UUID]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptURI =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[URI]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptByte =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Byte]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptShort =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Short]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)

        case a: AttrArrOptChar =>
          val arrays    = vs.asInstanceOf[Option[Seq[Array[Char]]]]
          val errors1 = if (arrays.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            arrays.get.flatMap(array => array.flatMap(v => validator.validate(v)))
          }
          a.copy(op = op, vs = arrays, errors = errors1)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
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
        case r: Ref =>
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
            case a: AttrArrMan => a match {
              case a: AttrArrManID             => AttrArrTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
              case a: AttrArrManString         => AttrArrTacString(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManInt            => AttrArrTacInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManLong           => AttrArrTacLong(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManFloat          => AttrArrTacFloat(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManDouble         => AttrArrTacDouble(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManBoolean        => AttrArrTacBoolean(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManBigInt         => AttrArrTacBigInt(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManBigDecimal     => AttrArrTacBigDecimal(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManDate           => AttrArrTacDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManDuration       => AttrArrTacDuration(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManInstant        => AttrArrTacInstant(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManLocalDate      => AttrArrTacLocalDate(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManLocalTime      => AttrArrTacLocalTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManLocalDateTime  => AttrArrTacLocalDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManOffsetTime     => AttrArrTacOffsetTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManOffsetDateTime => AttrArrTacOffsetDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManZonedDateTime  => AttrArrTacZonedDateTime(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManUUID           => AttrArrTacUUID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManURI            => AttrArrTacURI(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManByte           => AttrArrTacByte(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManShort          => AttrArrTacShort(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
              case a: AttrArrManChar           => AttrArrTacChar(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
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

          case a: AttrArr => a match {
            case a: AttrArrMan => a match {
              case a: AttrArrManID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrManChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
            }
            case a: AttrArrTac => a match {
              case a: AttrArrTacID             => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacString         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacInt            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacLong           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacFloat          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacDouble         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacBoolean        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacBigInt         => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacBigDecimal     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacDate           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacDuration       => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacInstant        => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacLocalDate      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacLocalTime      => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacLocalDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacOffsetTime     => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacOffsetDateTime => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacZonedDateTime  => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacUUID           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacURI            => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacByte           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacShort          => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
              case a: AttrArrTacChar           => a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))
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

        case a: AttrArr => a match {
          case a: AttrArrTac => a
          case a: AttrArrMan => a match {
            case a: AttrArrManID             => a.copy(op = V)
            case a: AttrArrManString         => a.copy(op = V)
            case a: AttrArrManInt            => a.copy(op = V)
            case a: AttrArrManLong           => a.copy(op = V)
            case a: AttrArrManFloat          => a.copy(op = V)
            case a: AttrArrManDouble         => a.copy(op = V)
            case a: AttrArrManBoolean        => a.copy(op = V)
            case a: AttrArrManBigInt         => a.copy(op = V)
            case a: AttrArrManBigDecimal     => a.copy(op = V)
            case a: AttrArrManDate           => a.copy(op = V)
            case a: AttrArrManDuration       => a.copy(op = V)
            case a: AttrArrManInstant        => a.copy(op = V)
            case a: AttrArrManLocalDate      => a.copy(op = V)
            case a: AttrArrManLocalTime      => a.copy(op = V)
            case a: AttrArrManLocalDateTime  => a.copy(op = V)
            case a: AttrArrManOffsetTime     => a.copy(op = V)
            case a: AttrArrManOffsetDateTime => a.copy(op = V)
            case a: AttrArrManZonedDateTime  => a.copy(op = V)
            case a: AttrArrManUUID           => a.copy(op = V)
            case a: AttrArrManURI            => a.copy(op = V)
            case a: AttrArrManByte           => a.copy(op = V)
            case a: AttrArrManShort          => a.copy(op = V)
            case a: AttrArrManChar           => a.copy(op = V)
          }
          case a: AttrArrOpt => a match {
            case a: AttrArrOptID             => a.copy(op = V)
            case a: AttrArrOptString         => a.copy(op = V)
            case a: AttrArrOptInt            => a.copy(op = V)
            case a: AttrArrOptLong           => a.copy(op = V)
            case a: AttrArrOptFloat          => a.copy(op = V)
            case a: AttrArrOptDouble         => a.copy(op = V)
            case a: AttrArrOptBoolean        => a.copy(op = V)
            case a: AttrArrOptBigInt         => a.copy(op = V)
            case a: AttrArrOptBigDecimal     => a.copy(op = V)
            case a: AttrArrOptDate           => a.copy(op = V)
            case a: AttrArrOptDuration       => a.copy(op = V)
            case a: AttrArrOptInstant        => a.copy(op = V)
            case a: AttrArrOptLocalDate      => a.copy(op = V)
            case a: AttrArrOptLocalTime      => a.copy(op = V)
            case a: AttrArrOptLocalDateTime  => a.copy(op = V)
            case a: AttrArrOptOffsetTime     => a.copy(op = V)
            case a: AttrArrOptOffsetDateTime => a.copy(op = V)
            case a: AttrArrOptZonedDateTime  => a.copy(op = V)
            case a: AttrArrOptUUID           => a.copy(op = V)
            case a: AttrArrOptURI            => a.copy(op = V)
            case a: AttrArrOptByte           => a.copy(op = V)
            case a: AttrArrOptShort          => a.copy(op = V)
            case a: AttrArrOptChar           => a.copy(op = V)
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
        case a: AttrArr       => a match {
          case _: AttrArrMan => topLevelAttrCount(tail, count + 1)
          case _: AttrArrOpt => topLevelAttrCount(tail, count + 1)
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