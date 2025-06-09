// GENERATED CODE ********************************
package molecule.db.core.ops

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.ast.*
import molecule.core.ast.Keywords.*
import molecule.db.core.api.Molecule
import scala.annotation.tailrec

trait ModelTransformations_ {

  private def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)

  protected def toInt(dataModel: DataModel, kw: Kw): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrOneMan => AttrOneManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      case a: AttrSetMan => a match {
        case _: AttrSetManBoolean =>
          if (kw.isInstanceOf[count] || kw.isInstanceOf[countDistinct]) {
            // Catch unsupported aggregation of Sets of boolean values
            AttrSetManInt(a.ent, a.attr, Fn(kw.toString, Some(-1)), ref = a.ref, coord = a.coord)
          } else {
            AttrSetManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
          }

        case _ => AttrSetManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      }
      case a: AttrSeqMan => AttrSeqManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, sort = a.sort, coord = a.coord)
      case a: AttrMapMan => AttrMapManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, sort = a.sort, coord = a.coord)
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def toDouble(dataModel: DataModel, kw: Kw): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrOneMan => AttrOneManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      case a: AttrSetMan => AttrSetManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      case a: AttrSeqMan => AttrSeqManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      case a: AttrMapMan => AttrMapManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def asIs(dataModel: DataModel, kw: Kw, n: Option[Int] = None): DataModel = {
    val es   = dataModel.elements
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
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addOne[T](dataModel: DataModel, op: Op, vs: Seq[T], binding: Boolean): DataModel = {
    val es    = dataModel.elements
    val last  = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManID =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Long]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManString =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[String]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManInt =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Int]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManLong =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Long]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManFloat =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Float]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManDouble =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Double]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManBoolean =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Boolean]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManBigInt =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[BigInt]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManBigDecimal =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[BigDecimal]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManDate =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Date]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManDuration =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Duration]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManInstant =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Instant]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManLocalDate =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalDate]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManLocalTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManLocalDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManOffsetTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[OffsetTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManOffsetDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[OffsetDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManZonedDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[ZonedDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManUUID =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[UUID]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManURI =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[URI]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManByte =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Byte]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManShort =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Short]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneManChar =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Char]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }
      }
      case a: AttrOneTac => a match {
        case a: AttrOneTacID =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Long]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacString =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[String]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacInt =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Int]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacLong =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Long]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacFloat =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Float]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacDouble =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Double]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacBoolean =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Boolean]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacBigInt =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[BigInt]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacBigDecimal =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[BigDecimal]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacDate =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Date]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacDuration =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Duration]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacInstant =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Instant]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacLocalDate =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalDate]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacLocalTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacLocalDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[LocalDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacOffsetTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[OffsetTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacOffsetDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[OffsetDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacZonedDateTime =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[ZonedDateTime]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacUUID =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[UUID]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacURI =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[URI]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacByte =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Byte]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacShort =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Short]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }

        case a: AttrOneTacChar =>
          if binding then a.copy(op = op, binding = true) else {
            val vs1     = vs.asInstanceOf[Seq[Char]]
            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
              val validator = a.validator.get
              vs1.flatMap(v => validator.validate(v))
            }
            a.copy(op = op, vs = vs1, errors = errors1)
          }
      }
      case a             => unexpected(a)
    }
    val binds = dataModel.binds + (if binding then 1 else 0)
    dataModel.copy(elements = es.init :+ last, binds = binds)
  }

  protected def addOneOpt[T](dataModel: DataModel, op: Op, v: Option[T]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrOneOpt => a match {
        case a: AttrOneOptID =>
          val v1      = v.asInstanceOf[Option[Long]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptString =>
          val v1      = v.asInstanceOf[Option[String]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptInt =>
          val v1      = v.asInstanceOf[Option[Int]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptLong =>
          val v1      = v.asInstanceOf[Option[Long]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptFloat =>
          val v1      = v.asInstanceOf[Option[Float]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptDouble =>
          val v1      = v.asInstanceOf[Option[Double]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptBoolean =>
          val v1      = v.asInstanceOf[Option[Boolean]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptBigInt =>
          val v1      = v.asInstanceOf[Option[BigInt]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptBigDecimal =>
          val v1      = v.asInstanceOf[Option[BigDecimal]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptDate =>
          val v1      = v.asInstanceOf[Option[Date]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptDuration =>
          val v1      = v.asInstanceOf[Option[Duration]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptInstant =>
          val v1      = v.asInstanceOf[Option[Instant]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptLocalDate =>
          val v1      = v.asInstanceOf[Option[LocalDate]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptLocalTime =>
          val v1      = v.asInstanceOf[Option[LocalTime]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptLocalDateTime =>
          val v1      = v.asInstanceOf[Option[LocalDateTime]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptOffsetTime =>
          val v1      = v.asInstanceOf[Option[OffsetTime]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptOffsetDateTime =>
          val v1      = v.asInstanceOf[Option[OffsetDateTime]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptZonedDateTime =>
          val v1      = v.asInstanceOf[Option[ZonedDateTime]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptUUID =>
          val v1      = v.asInstanceOf[Option[UUID]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptURI =>
          val v1      = v.asInstanceOf[Option[URI]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptByte =>
          val v1      = v.asInstanceOf[Option[Byte]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptShort =>
          val v1      = v.asInstanceOf[Option[Short]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)

        case a: AttrOneOptChar =>
          val v1      = v.asInstanceOf[Option[Char]]
          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            a.validator.get.validate(v1.get)
          }
          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addSet[T](dataModel: DataModel, op: Op, vs: Set[T]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrSetMan => a match {
        case a: AttrSetManID =>
          val set     = vs.asInstanceOf[Set[Long]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManString =>
          val set     = vs.asInstanceOf[Set[String]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManInt =>
          val set     = vs.asInstanceOf[Set[Int]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManLong =>
          val set     = vs.asInstanceOf[Set[Long]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManFloat =>
          val set     = vs.asInstanceOf[Set[Float]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManDouble =>
          val set     = vs.asInstanceOf[Set[Double]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManBoolean =>
          val set     = vs.asInstanceOf[Set[Boolean]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManBigInt =>
          val set     = vs.asInstanceOf[Set[BigInt]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManBigDecimal =>
          val set     = vs.asInstanceOf[Set[BigDecimal]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManDate =>
          val set     = vs.asInstanceOf[Set[Date]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManDuration =>
          val set     = vs.asInstanceOf[Set[Duration]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManInstant =>
          val set     = vs.asInstanceOf[Set[Instant]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManLocalDate =>
          val set     = vs.asInstanceOf[Set[LocalDate]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManLocalTime =>
          val set     = vs.asInstanceOf[Set[LocalTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManLocalDateTime =>
          val set     = vs.asInstanceOf[Set[LocalDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManOffsetTime =>
          val set     = vs.asInstanceOf[Set[OffsetTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManOffsetDateTime =>
          val set     = vs.asInstanceOf[Set[OffsetDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManZonedDateTime =>
          val set     = vs.asInstanceOf[Set[ZonedDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManUUID =>
          val set     = vs.asInstanceOf[Set[UUID]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManURI =>
          val set     = vs.asInstanceOf[Set[URI]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManByte =>
          val set     = vs.asInstanceOf[Set[Byte]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManShort =>
          val set     = vs.asInstanceOf[Set[Short]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetManChar =>
          val set     = vs.asInstanceOf[Set[Char]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)
      }
      case a: AttrSetTac => a match {
        case a: AttrSetTacID =>
          val set     = vs.asInstanceOf[Set[Long]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacString =>
          val set     = vs.asInstanceOf[Set[String]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacInt =>
          val set     = vs.asInstanceOf[Set[Int]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacLong =>
          val set     = vs.asInstanceOf[Set[Long]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacFloat =>
          val set     = vs.asInstanceOf[Set[Float]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacDouble =>
          val set     = vs.asInstanceOf[Set[Double]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacBoolean =>
          val set     = vs.asInstanceOf[Set[Boolean]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacBigInt =>
          val set     = vs.asInstanceOf[Set[BigInt]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacBigDecimal =>
          val set     = vs.asInstanceOf[Set[BigDecimal]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacDate =>
          val set     = vs.asInstanceOf[Set[Date]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacDuration =>
          val set     = vs.asInstanceOf[Set[Duration]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacInstant =>
          val set     = vs.asInstanceOf[Set[Instant]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacLocalDate =>
          val set     = vs.asInstanceOf[Set[LocalDate]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacLocalTime =>
          val set     = vs.asInstanceOf[Set[LocalTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacLocalDateTime =>
          val set     = vs.asInstanceOf[Set[LocalDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacOffsetTime =>
          val set     = vs.asInstanceOf[Set[OffsetTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacOffsetDateTime =>
          val set     = vs.asInstanceOf[Set[OffsetDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacZonedDateTime =>
          val set     = vs.asInstanceOf[Set[ZonedDateTime]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacUUID =>
          val set     = vs.asInstanceOf[Set[UUID]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacURI =>
          val set     = vs.asInstanceOf[Set[URI]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacByte =>
          val set     = vs.asInstanceOf[Set[Byte]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacShort =>
          val set     = vs.asInstanceOf[Set[Short]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetTacChar =>
          val set     = vs.asInstanceOf[Set[Char]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addSetOpt[T](dataModel: DataModel, op: Op, vs: Option[Set[T]]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrSetOpt => a match {
        case a: AttrSetOptID =>
          val set     = vs.asInstanceOf[Option[Set[Long]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptString =>
          val set     = vs.asInstanceOf[Option[Set[String]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptInt =>
          val set     = vs.asInstanceOf[Option[Set[Int]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptLong =>
          val set     = vs.asInstanceOf[Option[Set[Long]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptFloat =>
          val set     = vs.asInstanceOf[Option[Set[Float]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptDouble =>
          val set     = vs.asInstanceOf[Option[Set[Double]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptBoolean =>
          val set     = vs.asInstanceOf[Option[Set[Boolean]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptBigInt =>
          val set     = vs.asInstanceOf[Option[Set[BigInt]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptBigDecimal =>
          val set     = vs.asInstanceOf[Option[Set[BigDecimal]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptDate =>
          val set     = vs.asInstanceOf[Option[Set[Date]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptDuration =>
          val set     = vs.asInstanceOf[Option[Set[Duration]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptInstant =>
          val set     = vs.asInstanceOf[Option[Set[Instant]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptLocalDate =>
          val set     = vs.asInstanceOf[Option[Set[LocalDate]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptLocalTime =>
          val set     = vs.asInstanceOf[Option[Set[LocalTime]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptLocalDateTime =>
          val set     = vs.asInstanceOf[Option[Set[LocalDateTime]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptOffsetTime =>
          val set     = vs.asInstanceOf[Option[Set[OffsetTime]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptOffsetDateTime =>
          val set     = vs.asInstanceOf[Option[Set[OffsetDateTime]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptZonedDateTime =>
          val set     = vs.asInstanceOf[Option[Set[ZonedDateTime]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptUUID =>
          val set     = vs.asInstanceOf[Option[Set[UUID]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptURI =>
          val set     = vs.asInstanceOf[Option[Set[URI]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptByte =>
          val set     = vs.asInstanceOf[Option[Set[Byte]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptShort =>
          val set     = vs.asInstanceOf[Option[Set[Short]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)

        case a: AttrSetOptChar =>
          val set     = vs.asInstanceOf[Option[Set[Char]]]
          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            set.get.toSeq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = set, errors = errors1)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addSeq[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrSeqMan => a match {
        case a: AttrSeqManID =>
          val seq     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManString =>
          val seq     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManInt =>
          val seq     = vs.asInstanceOf[Seq[Int]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManLong =>
          val seq     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManFloat =>
          val seq     = vs.asInstanceOf[Seq[Float]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManDouble =>
          val seq     = vs.asInstanceOf[Seq[Double]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManBoolean =>
          val seq     = vs.asInstanceOf[Seq[Boolean]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManBigInt =>
          val seq     = vs.asInstanceOf[Seq[BigInt]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManBigDecimal =>
          val seq     = vs.asInstanceOf[Seq[BigDecimal]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManDate =>
          val seq     = vs.asInstanceOf[Seq[Date]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManDuration =>
          val seq     = vs.asInstanceOf[Seq[Duration]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManInstant =>
          val seq     = vs.asInstanceOf[Seq[Instant]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManLocalDate =>
          val seq     = vs.asInstanceOf[Seq[LocalDate]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManLocalTime =>
          val seq     = vs.asInstanceOf[Seq[LocalTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManLocalDateTime =>
          val seq     = vs.asInstanceOf[Seq[LocalDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManOffsetTime =>
          val seq     = vs.asInstanceOf[Seq[OffsetTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManOffsetDateTime =>
          val seq     = vs.asInstanceOf[Seq[OffsetDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManZonedDateTime =>
          val seq     = vs.asInstanceOf[Seq[ZonedDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManUUID =>
          val seq     = vs.asInstanceOf[Seq[UUID]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManURI =>
          val seq     = vs.asInstanceOf[Seq[URI]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManShort =>
          val seq     = vs.asInstanceOf[Seq[Short]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManChar =>
          val seq     = vs.asInstanceOf[Seq[Char]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqManByte => ???
      }
      case a: AttrSeqTac => a match {
        case a: AttrSeqTacID =>
          val seq     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacString =>
          val seq     = vs.asInstanceOf[Seq[String]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacInt =>
          val seq     = vs.asInstanceOf[Seq[Int]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacLong =>
          val seq     = vs.asInstanceOf[Seq[Long]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacFloat =>
          val seq     = vs.asInstanceOf[Seq[Float]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacDouble =>
          val seq     = vs.asInstanceOf[Seq[Double]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacBoolean =>
          val seq     = vs.asInstanceOf[Seq[Boolean]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacBigInt =>
          val seq     = vs.asInstanceOf[Seq[BigInt]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacBigDecimal =>
          val seq     = vs.asInstanceOf[Seq[BigDecimal]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacDate =>
          val seq     = vs.asInstanceOf[Seq[Date]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacDuration =>
          val seq     = vs.asInstanceOf[Seq[Duration]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacInstant =>
          val seq     = vs.asInstanceOf[Seq[Instant]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacLocalDate =>
          val seq     = vs.asInstanceOf[Seq[LocalDate]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacLocalTime =>
          val seq     = vs.asInstanceOf[Seq[LocalTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacLocalDateTime =>
          val seq     = vs.asInstanceOf[Seq[LocalDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacOffsetTime =>
          val seq     = vs.asInstanceOf[Seq[OffsetTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacOffsetDateTime =>
          val seq     = vs.asInstanceOf[Seq[OffsetDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacZonedDateTime =>
          val seq     = vs.asInstanceOf[Seq[ZonedDateTime]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacUUID =>
          val seq     = vs.asInstanceOf[Seq[UUID]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacURI =>
          val seq     = vs.asInstanceOf[Seq[URI]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacShort =>
          val seq     = vs.asInstanceOf[Seq[Short]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacChar =>
          val seq     = vs.asInstanceOf[Seq[Char]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqTacByte => ???
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addSeqOpt[T](dataModel: DataModel, op: Op, vs: Option[Seq[T]]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrSeqOpt => a match {
        case a: AttrSeqOptID =>
          val seq     = vs.asInstanceOf[Option[Seq[Long]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptString =>
          val seq     = vs.asInstanceOf[Option[Seq[String]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptInt =>
          val seq     = vs.asInstanceOf[Option[Seq[Int]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptLong =>
          val seq     = vs.asInstanceOf[Option[Seq[Long]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptFloat =>
          val seq     = vs.asInstanceOf[Option[Seq[Float]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptDouble =>
          val seq     = vs.asInstanceOf[Option[Seq[Double]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptBoolean =>
          val seq     = vs.asInstanceOf[Option[Seq[Boolean]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptBigInt =>
          val seq     = vs.asInstanceOf[Option[Seq[BigInt]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptBigDecimal =>
          val seq     = vs.asInstanceOf[Option[Seq[BigDecimal]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptDate =>
          val seq     = vs.asInstanceOf[Option[Seq[Date]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptDuration =>
          val seq     = vs.asInstanceOf[Option[Seq[Duration]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptInstant =>
          val seq     = vs.asInstanceOf[Option[Seq[Instant]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptLocalDate =>
          val seq     = vs.asInstanceOf[Option[Seq[LocalDate]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptLocalTime =>
          val seq     = vs.asInstanceOf[Option[Seq[LocalTime]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptLocalDateTime =>
          val seq     = vs.asInstanceOf[Option[Seq[LocalDateTime]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptOffsetTime =>
          val seq     = vs.asInstanceOf[Option[Seq[OffsetTime]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptOffsetDateTime =>
          val seq     = vs.asInstanceOf[Option[Seq[OffsetDateTime]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptZonedDateTime =>
          val seq     = vs.asInstanceOf[Option[Seq[ZonedDateTime]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptUUID =>
          val seq     = vs.asInstanceOf[Option[Seq[UUID]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptURI =>
          val seq     = vs.asInstanceOf[Option[Seq[URI]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptShort =>
          val seq     = vs.asInstanceOf[Option[Seq[Short]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptChar =>
          val seq     = vs.asInstanceOf[Option[Seq[Char]]]
          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            seq.get.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = seq, errors = errors1)

        case a: AttrSeqOptByte => ???
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addBAr[T](dataModel: DataModel, op: Op, ba: Array[T]): DataModel = {
    val es          = dataModel.elements
    val newElements = es.init :+ (es.last match {
      case a: AttrSeqManByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
      case a: AttrSeqTacByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
    })
    dataModel.copy(elements = newElements)
  }

  protected def addBArOpt[T](dataModel: DataModel, op: Op, optBA: Option[Array[T]]): DataModel = {
    val es          = dataModel.elements
    val newElements = es.init :+ (es.last match {
      case a: AttrSeqOptByte => a.copy(op = op, vs = optBA.asInstanceOf[Option[Array[Byte]]])
      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
    })
    dataModel.copy(elements = newElements)
  }

  protected def addMap[T](dataModel: DataModel, op: Op, vs: Map[String, T]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrMapMan => a match {
        case a: AttrMapManID =>
          val newMap  = vs.asInstanceOf[Map[String, Long]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManString =>
          val newMap  = vs.asInstanceOf[Map[String, String]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManInt =>
          val newMap  = vs.asInstanceOf[Map[String, Int]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManLong =>
          val newMap  = vs.asInstanceOf[Map[String, Long]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManFloat =>
          val newMap  = vs.asInstanceOf[Map[String, Float]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManDouble =>
          val newMap  = vs.asInstanceOf[Map[String, Double]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManBoolean =>
          val newMap  = vs.asInstanceOf[Map[String, Boolean]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManBigInt =>
          val newMap  = vs.asInstanceOf[Map[String, BigInt]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManBigDecimal =>
          val newMap  = vs.asInstanceOf[Map[String, BigDecimal]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManDate =>
          val newMap  = vs.asInstanceOf[Map[String, Date]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManDuration =>
          val newMap  = vs.asInstanceOf[Map[String, Duration]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManInstant =>
          val newMap  = vs.asInstanceOf[Map[String, Instant]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManLocalDate =>
          val newMap  = vs.asInstanceOf[Map[String, LocalDate]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManLocalTime =>
          val newMap  = vs.asInstanceOf[Map[String, LocalTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManLocalDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, LocalDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManOffsetTime =>
          val newMap  = vs.asInstanceOf[Map[String, OffsetTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManOffsetDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, OffsetDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManZonedDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, ZonedDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManUUID =>
          val newMap  = vs.asInstanceOf[Map[String, UUID]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManURI =>
          val newMap  = vs.asInstanceOf[Map[String, URI]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManByte =>
          val newMap  = vs.asInstanceOf[Map[String, Byte]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManShort =>
          val newMap  = vs.asInstanceOf[Map[String, Short]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapManChar =>
          val newMap  = vs.asInstanceOf[Map[String, Char]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)
      }
      case a: AttrMapTac => a match {
        case a: AttrMapTacID =>
          val newMap  = vs.asInstanceOf[Map[String, Long]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacString =>
          val newMap  = vs.asInstanceOf[Map[String, String]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacInt =>
          val newMap  = vs.asInstanceOf[Map[String, Int]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacLong =>
          val newMap  = vs.asInstanceOf[Map[String, Long]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacFloat =>
          val newMap  = vs.asInstanceOf[Map[String, Float]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacDouble =>
          val newMap  = vs.asInstanceOf[Map[String, Double]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacBoolean =>
          val newMap  = vs.asInstanceOf[Map[String, Boolean]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacBigInt =>
          val newMap  = vs.asInstanceOf[Map[String, BigInt]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacBigDecimal =>
          val newMap  = vs.asInstanceOf[Map[String, BigDecimal]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacDate =>
          val newMap  = vs.asInstanceOf[Map[String, Date]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacDuration =>
          val newMap  = vs.asInstanceOf[Map[String, Duration]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacInstant =>
          val newMap  = vs.asInstanceOf[Map[String, Instant]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacLocalDate =>
          val newMap  = vs.asInstanceOf[Map[String, LocalDate]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacLocalTime =>
          val newMap  = vs.asInstanceOf[Map[String, LocalTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacLocalDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, LocalDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacOffsetTime =>
          val newMap  = vs.asInstanceOf[Map[String, OffsetTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacOffsetDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, OffsetDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacZonedDateTime =>
          val newMap  = vs.asInstanceOf[Map[String, ZonedDateTime]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacUUID =>
          val newMap  = vs.asInstanceOf[Map[String, UUID]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacURI =>
          val newMap  = vs.asInstanceOf[Map[String, URI]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacByte =>
          val newMap  = vs.asInstanceOf[Map[String, Byte]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacShort =>
          val newMap  = vs.asInstanceOf[Map[String, Short]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapTacChar =>
          val newMap  = vs.asInstanceOf[Map[String, Char]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addMapKs(dataModel: DataModel, op: Op, ks: Seq[String]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrMapMan => a match {
        case a: AttrMapManID             => a.copy(op = op, keys = ks)
        case a: AttrMapManString         => a.copy(op = op, keys = ks)
        case a: AttrMapManInt            => a.copy(op = op, keys = ks)
        case a: AttrMapManLong           => a.copy(op = op, keys = ks)
        case a: AttrMapManFloat          => a.copy(op = op, keys = ks)
        case a: AttrMapManDouble         => a.copy(op = op, keys = ks)
        case a: AttrMapManBoolean        => a.copy(op = op, keys = ks)
        case a: AttrMapManBigInt         => a.copy(op = op, keys = ks)
        case a: AttrMapManBigDecimal     => a.copy(op = op, keys = ks)
        case a: AttrMapManDate           => a.copy(op = op, keys = ks)
        case a: AttrMapManDuration       => a.copy(op = op, keys = ks)
        case a: AttrMapManInstant        => a.copy(op = op, keys = ks)
        case a: AttrMapManLocalDate      => a.copy(op = op, keys = ks)
        case a: AttrMapManLocalTime      => a.copy(op = op, keys = ks)
        case a: AttrMapManLocalDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapManOffsetTime     => a.copy(op = op, keys = ks)
        case a: AttrMapManOffsetDateTime => a.copy(op = op, keys = ks)
        case a: AttrMapManZonedDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapManUUID           => a.copy(op = op, keys = ks)
        case a: AttrMapManURI            => a.copy(op = op, keys = ks)
        case a: AttrMapManByte           => a.copy(op = op, keys = ks)
        case a: AttrMapManShort          => a.copy(op = op, keys = ks)
        case a: AttrMapManChar           => a.copy(op = op, keys = ks)
      }
      case a: AttrMapOpt => a match {
        case a: AttrMapOptID             => a.copy(op = op, keys = ks)
        case a: AttrMapOptString         => a.copy(op = op, keys = ks)
        case a: AttrMapOptInt            => a.copy(op = op, keys = ks)
        case a: AttrMapOptLong           => a.copy(op = op, keys = ks)
        case a: AttrMapOptFloat          => a.copy(op = op, keys = ks)
        case a: AttrMapOptDouble         => a.copy(op = op, keys = ks)
        case a: AttrMapOptBoolean        => a.copy(op = op, keys = ks)
        case a: AttrMapOptBigInt         => a.copy(op = op, keys = ks)
        case a: AttrMapOptBigDecimal     => a.copy(op = op, keys = ks)
        case a: AttrMapOptDate           => a.copy(op = op, keys = ks)
        case a: AttrMapOptDuration       => a.copy(op = op, keys = ks)
        case a: AttrMapOptInstant        => a.copy(op = op, keys = ks)
        case a: AttrMapOptLocalDate      => a.copy(op = op, keys = ks)
        case a: AttrMapOptLocalTime      => a.copy(op = op, keys = ks)
        case a: AttrMapOptLocalDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapOptOffsetTime     => a.copy(op = op, keys = ks)
        case a: AttrMapOptOffsetDateTime => a.copy(op = op, keys = ks)
        case a: AttrMapOptZonedDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapOptUUID           => a.copy(op = op, keys = ks)
        case a: AttrMapOptURI            => a.copy(op = op, keys = ks)
        case a: AttrMapOptByte           => a.copy(op = op, keys = ks)
        case a: AttrMapOptShort          => a.copy(op = op, keys = ks)
        case a: AttrMapOptChar           => a.copy(op = op, keys = ks)
      }
      case a: AttrMapTac => a match {
        case a: AttrMapTacID             => a.copy(op = op, keys = ks)
        case a: AttrMapTacString         => a.copy(op = op, keys = ks)
        case a: AttrMapTacInt            => a.copy(op = op, keys = ks)
        case a: AttrMapTacLong           => a.copy(op = op, keys = ks)
        case a: AttrMapTacFloat          => a.copy(op = op, keys = ks)
        case a: AttrMapTacDouble         => a.copy(op = op, keys = ks)
        case a: AttrMapTacBoolean        => a.copy(op = op, keys = ks)
        case a: AttrMapTacBigInt         => a.copy(op = op, keys = ks)
        case a: AttrMapTacBigDecimal     => a.copy(op = op, keys = ks)
        case a: AttrMapTacDate           => a.copy(op = op, keys = ks)
        case a: AttrMapTacDuration       => a.copy(op = op, keys = ks)
        case a: AttrMapTacInstant        => a.copy(op = op, keys = ks)
        case a: AttrMapTacLocalDate      => a.copy(op = op, keys = ks)
        case a: AttrMapTacLocalTime      => a.copy(op = op, keys = ks)
        case a: AttrMapTacLocalDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapTacOffsetTime     => a.copy(op = op, keys = ks)
        case a: AttrMapTacOffsetDateTime => a.copy(op = op, keys = ks)
        case a: AttrMapTacZonedDateTime  => a.copy(op = op, keys = ks)
        case a: AttrMapTacUUID           => a.copy(op = op, keys = ks)
        case a: AttrMapTacURI            => a.copy(op = op, keys = ks)
        case a: AttrMapTacByte           => a.copy(op = op, keys = ks)
        case a: AttrMapTacShort          => a.copy(op = op, keys = ks)
        case a: AttrMapTacChar           => a.copy(op = op, keys = ks)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addMapVs[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrMapMan => a match {
        case a: AttrMapManID             => a.copy(op = op, values = vs.asInstanceOf[Seq[Long]])
        case a: AttrMapManString         => a.copy(op = op, values = vs.asInstanceOf[Seq[String]])
        case a: AttrMapManInt            => a.copy(op = op, values = vs.asInstanceOf[Seq[Int]])
        case a: AttrMapManLong           => a.copy(op = op, values = vs.asInstanceOf[Seq[Long]])
        case a: AttrMapManFloat          => a.copy(op = op, values = vs.asInstanceOf[Seq[Float]])
        case a: AttrMapManDouble         => a.copy(op = op, values = vs.asInstanceOf[Seq[Double]])
        case a: AttrMapManBoolean        => a.copy(op = op, values = vs.asInstanceOf[Seq[Boolean]])
        case a: AttrMapManBigInt         => a.copy(op = op, values = vs.asInstanceOf[Seq[BigInt]])
        case a: AttrMapManBigDecimal     => a.copy(op = op, values = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AttrMapManDate           => a.copy(op = op, values = vs.asInstanceOf[Seq[Date]])
        case a: AttrMapManDuration       => a.copy(op = op, values = vs.asInstanceOf[Seq[Duration]])
        case a: AttrMapManInstant        => a.copy(op = op, values = vs.asInstanceOf[Seq[Instant]])
        case a: AttrMapManLocalDate      => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalDate]])
        case a: AttrMapManLocalTime      => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalTime]])
        case a: AttrMapManLocalDateTime  => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalDateTime]])
        case a: AttrMapManOffsetTime     => a.copy(op = op, values = vs.asInstanceOf[Seq[OffsetTime]])
        case a: AttrMapManOffsetDateTime => a.copy(op = op, values = vs.asInstanceOf[Seq[OffsetDateTime]])
        case a: AttrMapManZonedDateTime  => a.copy(op = op, values = vs.asInstanceOf[Seq[ZonedDateTime]])
        case a: AttrMapManUUID           => a.copy(op = op, values = vs.asInstanceOf[Seq[UUID]])
        case a: AttrMapManURI            => a.copy(op = op, values = vs.asInstanceOf[Seq[URI]])
        case a: AttrMapManByte           => a.copy(op = op, values = vs.asInstanceOf[Seq[Byte]])
        case a: AttrMapManShort          => a.copy(op = op, values = vs.asInstanceOf[Seq[Short]])
        case a: AttrMapManChar           => a.copy(op = op, values = vs.asInstanceOf[Seq[Char]])
      }
      case a: AttrMapTac => a match {
        case a: AttrMapTacID             => a.copy(op = op, values = vs.asInstanceOf[Seq[Long]])
        case a: AttrMapTacString         => a.copy(op = op, values = vs.asInstanceOf[Seq[String]])
        case a: AttrMapTacInt            => a.copy(op = op, values = vs.asInstanceOf[Seq[Int]])
        case a: AttrMapTacLong           => a.copy(op = op, values = vs.asInstanceOf[Seq[Long]])
        case a: AttrMapTacFloat          => a.copy(op = op, values = vs.asInstanceOf[Seq[Float]])
        case a: AttrMapTacDouble         => a.copy(op = op, values = vs.asInstanceOf[Seq[Double]])
        case a: AttrMapTacBoolean        => a.copy(op = op, values = vs.asInstanceOf[Seq[Boolean]])
        case a: AttrMapTacBigInt         => a.copy(op = op, values = vs.asInstanceOf[Seq[BigInt]])
        case a: AttrMapTacBigDecimal     => a.copy(op = op, values = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AttrMapTacDate           => a.copy(op = op, values = vs.asInstanceOf[Seq[Date]])
        case a: AttrMapTacDuration       => a.copy(op = op, values = vs.asInstanceOf[Seq[Duration]])
        case a: AttrMapTacInstant        => a.copy(op = op, values = vs.asInstanceOf[Seq[Instant]])
        case a: AttrMapTacLocalDate      => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalDate]])
        case a: AttrMapTacLocalTime      => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalTime]])
        case a: AttrMapTacLocalDateTime  => a.copy(op = op, values = vs.asInstanceOf[Seq[LocalDateTime]])
        case a: AttrMapTacOffsetTime     => a.copy(op = op, values = vs.asInstanceOf[Seq[OffsetTime]])
        case a: AttrMapTacOffsetDateTime => a.copy(op = op, values = vs.asInstanceOf[Seq[OffsetDateTime]])
        case a: AttrMapTacZonedDateTime  => a.copy(op = op, values = vs.asInstanceOf[Seq[ZonedDateTime]])
        case a: AttrMapTacUUID           => a.copy(op = op, values = vs.asInstanceOf[Seq[UUID]])
        case a: AttrMapTacURI            => a.copy(op = op, values = vs.asInstanceOf[Seq[URI]])
        case a: AttrMapTacByte           => a.copy(op = op, values = vs.asInstanceOf[Seq[Byte]])
        case a: AttrMapTacShort          => a.copy(op = op, values = vs.asInstanceOf[Seq[Short]])
        case a: AttrMapTacChar           => a.copy(op = op, values = vs.asInstanceOf[Seq[Char]])
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addMapOpt[T](dataModel: DataModel, op: Op, vs: Option[Map[String, T]]): DataModel = {
    val es   = dataModel.elements
    val last = es.last match {
      case a: AttrMapOpt => a match {
        case a: AttrMapOptID =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Long]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptString =>
          val newMap  = vs.asInstanceOf[Option[Map[String, String]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptInt =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Int]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptLong =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Long]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptFloat =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Float]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptDouble =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Double]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptBoolean =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Boolean]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptBigInt =>
          val newMap  = vs.asInstanceOf[Option[Map[String, BigInt]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptBigDecimal =>
          val newMap  = vs.asInstanceOf[Option[Map[String, BigDecimal]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptDate =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Date]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptDuration =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Duration]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptInstant =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Instant]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptLocalDate =>
          val newMap  = vs.asInstanceOf[Option[Map[String, LocalDate]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptLocalTime =>
          val newMap  = vs.asInstanceOf[Option[Map[String, LocalTime]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptLocalDateTime =>
          val newMap  = vs.asInstanceOf[Option[Map[String, LocalDateTime]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptOffsetTime =>
          val newMap  = vs.asInstanceOf[Option[Map[String, OffsetTime]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptOffsetDateTime =>
          val newMap  = vs.asInstanceOf[Option[Map[String, OffsetDateTime]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptZonedDateTime =>
          val newMap  = vs.asInstanceOf[Option[Map[String, ZonedDateTime]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptUUID =>
          val newMap  = vs.asInstanceOf[Option[Map[String, UUID]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptURI =>
          val newMap  = vs.asInstanceOf[Option[Map[String, URI]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptByte =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Byte]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptShort =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Short]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)

        case a: AttrMapOptChar =>
          val newMap  = vs.asInstanceOf[Option[Map[String, Char]]]
          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
            val validator = a.validator.get
            newMap.get.values.toSeq.flatMap(validator.validate)
          }
          a.copy(op = op, map = newMap, errors = errors1)
      }
      case a             => unexpected(a)
    }
    dataModel.copy(elements = es.init :+ last)
  }

  protected def addSort(dataModel: DataModel, sort: String): DataModel = {
    val es             = dataModel.elements
    val sortedElements = es.size match {
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
    dataModel.copy(elements = sortedElements)
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

      case a: AttrSetMan => a match {
        case a: AttrSetManID             => a.copy(sort = Some(sort))
        case a: AttrSetManString         => a.copy(sort = Some(sort))
        case a: AttrSetManInt            => a.copy(sort = Some(sort))
        case a: AttrSetManLong           => a.copy(sort = Some(sort))
        case a: AttrSetManFloat          => a.copy(sort = Some(sort))
        case a: AttrSetManDouble         => a.copy(sort = Some(sort))
        case a: AttrSetManBoolean        => a.copy(sort = Some(sort))
        case a: AttrSetManBigInt         => a.copy(sort = Some(sort))
        case a: AttrSetManBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrSetManDate           => a.copy(sort = Some(sort))
        case a: AttrSetManDuration       => a.copy(sort = Some(sort))
        case a: AttrSetManInstant        => a.copy(sort = Some(sort))
        case a: AttrSetManLocalDate      => a.copy(sort = Some(sort))
        case a: AttrSetManLocalTime      => a.copy(sort = Some(sort))
        case a: AttrSetManLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrSetManOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrSetManOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrSetManZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrSetManUUID           => a.copy(sort = Some(sort))
        case a: AttrSetManURI            => a.copy(sort = Some(sort))
        case a: AttrSetManByte           => a.copy(sort = Some(sort))
        case a: AttrSetManShort          => a.copy(sort = Some(sort))
        case a: AttrSetManChar           => a.copy(sort = Some(sort))
      }
      case a: AttrSetOpt => a match {
        case a: AttrSetOptID             => a.copy(sort = Some(sort))
        case a: AttrSetOptString         => a.copy(sort = Some(sort))
        case a: AttrSetOptInt            => a.copy(sort = Some(sort))
        case a: AttrSetOptLong           => a.copy(sort = Some(sort))
        case a: AttrSetOptFloat          => a.copy(sort = Some(sort))
        case a: AttrSetOptDouble         => a.copy(sort = Some(sort))
        case a: AttrSetOptBoolean        => a.copy(sort = Some(sort))
        case a: AttrSetOptBigInt         => a.copy(sort = Some(sort))
        case a: AttrSetOptBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrSetOptDate           => a.copy(sort = Some(sort))
        case a: AttrSetOptDuration       => a.copy(sort = Some(sort))
        case a: AttrSetOptInstant        => a.copy(sort = Some(sort))
        case a: AttrSetOptLocalDate      => a.copy(sort = Some(sort))
        case a: AttrSetOptLocalTime      => a.copy(sort = Some(sort))
        case a: AttrSetOptLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrSetOptOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrSetOptOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrSetOptZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrSetOptUUID           => a.copy(sort = Some(sort))
        case a: AttrSetOptURI            => a.copy(sort = Some(sort))
        case a: AttrSetOptByte           => a.copy(sort = Some(sort))
        case a: AttrSetOptShort          => a.copy(sort = Some(sort))
        case a: AttrSetOptChar           => a.copy(sort = Some(sort))
      }

      case a: AttrSeqMan => a match {
        case a: AttrSeqManID             => a.copy(sort = Some(sort))
        case a: AttrSeqManString         => a.copy(sort = Some(sort))
        case a: AttrSeqManInt            => a.copy(sort = Some(sort))
        case a: AttrSeqManLong           => a.copy(sort = Some(sort))
        case a: AttrSeqManFloat          => a.copy(sort = Some(sort))
        case a: AttrSeqManDouble         => a.copy(sort = Some(sort))
        case a: AttrSeqManBoolean        => a.copy(sort = Some(sort))
        case a: AttrSeqManBigInt         => a.copy(sort = Some(sort))
        case a: AttrSeqManBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrSeqManDate           => a.copy(sort = Some(sort))
        case a: AttrSeqManDuration       => a.copy(sort = Some(sort))
        case a: AttrSeqManInstant        => a.copy(sort = Some(sort))
        case a: AttrSeqManLocalDate      => a.copy(sort = Some(sort))
        case a: AttrSeqManLocalTime      => a.copy(sort = Some(sort))
        case a: AttrSeqManLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrSeqManOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrSeqManOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrSeqManZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrSeqManUUID           => a.copy(sort = Some(sort))
        case a: AttrSeqManURI            => a.copy(sort = Some(sort))
        case a: AttrSeqManByte           => a.copy(sort = Some(sort))
        case a: AttrSeqManShort          => a.copy(sort = Some(sort))
        case a: AttrSeqManChar           => a.copy(sort = Some(sort))
      }
      case a: AttrSeqOpt => a match {
        case a: AttrSeqOptID             => a.copy(sort = Some(sort))
        case a: AttrSeqOptString         => a.copy(sort = Some(sort))
        case a: AttrSeqOptInt            => a.copy(sort = Some(sort))
        case a: AttrSeqOptLong           => a.copy(sort = Some(sort))
        case a: AttrSeqOptFloat          => a.copy(sort = Some(sort))
        case a: AttrSeqOptDouble         => a.copy(sort = Some(sort))
        case a: AttrSeqOptBoolean        => a.copy(sort = Some(sort))
        case a: AttrSeqOptBigInt         => a.copy(sort = Some(sort))
        case a: AttrSeqOptBigDecimal     => a.copy(sort = Some(sort))
        case a: AttrSeqOptDate           => a.copy(sort = Some(sort))
        case a: AttrSeqOptDuration       => a.copy(sort = Some(sort))
        case a: AttrSeqOptInstant        => a.copy(sort = Some(sort))
        case a: AttrSeqOptLocalDate      => a.copy(sort = Some(sort))
        case a: AttrSeqOptLocalTime      => a.copy(sort = Some(sort))
        case a: AttrSeqOptLocalDateTime  => a.copy(sort = Some(sort))
        case a: AttrSeqOptOffsetTime     => a.copy(sort = Some(sort))
        case a: AttrSeqOptOffsetDateTime => a.copy(sort = Some(sort))
        case a: AttrSeqOptZonedDateTime  => a.copy(sort = Some(sort))
        case a: AttrSeqOptUUID           => a.copy(sort = Some(sort))
        case a: AttrSeqOptURI            => a.copy(sort = Some(sort))
        case a: AttrSeqOptByte           => a.copy(sort = Some(sort))
        case a: AttrSeqOptShort          => a.copy(sort = Some(sort))
        case a: AttrSeqOptChar           => a.copy(sort = Some(sort))
      }

      case e => e
    }
  }

  @tailrec
  private def resolvePath(es: List[Element], path: List[String]): List[String] = {
    es match {
      case e :: tail => e match {
        case r: Ref    =>
          val p = if (path.isEmpty) List(r.ent, r.refAttr, r.ref) else List(r.refAttr, r.ref)
          resolvePath(tail, path ++ p)
        case r: OptRef =>
          ???
        case a: Attr   => resolvePath(tail, if (path.isEmpty) List(a.ent) else path)
        case other     => throw ModelError("Invalid element in filter attribute path: " + other)
      }
      case Nil       => path
    }
  }

  protected def filterAttr(dataModel: DataModel, op: Op, filterAttrMolecule: Molecule): DataModel = {
    val es          = dataModel.elements
    val filterAttr0 = filterAttrMolecule.dataModel.elements.last.asInstanceOf[Attr]
    val attrs       = es.last match {
      case a: Attr =>
        val (tacitFilterAttr, adjacent) = if (a.ent == filterAttr0.ent) {
          // Rudimentary checked for same current entity (it's the only information
          // we have now during molecule buildup). At least we can rule out if the
          // filter attribute is not adjacent to the caller attribute.
          // Could point to other branch - have to be checked later.
          // If pointing to other branch, the added filterAttr0 should be removed

          // Convert adjacent mandatory filter attribute to tacit attribute
          val tacitAttr = filterAttr0 match {
            case a: AttrOneMan => a match {
              case a: AttrOneManID             => AttrOneTacID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManString         => AttrOneTacString(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManInt            => AttrOneTacInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManLong           => AttrOneTacLong(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManFloat          => AttrOneTacFloat(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManDouble         => AttrOneTacDouble(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManBoolean        => AttrOneTacBoolean(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManBigInt         => AttrOneTacBigInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManBigDecimal     => AttrOneTacBigDecimal(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManDate           => AttrOneTacDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManDuration       => AttrOneTacDuration(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManInstant        => AttrOneTacInstant(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManLocalDate      => AttrOneTacLocalDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManLocalTime      => AttrOneTacLocalTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManLocalDateTime  => AttrOneTacLocalDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManOffsetTime     => AttrOneTacOffsetTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManOffsetDateTime => AttrOneTacOffsetDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManZonedDateTime  => AttrOneTacZonedDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManUUID           => AttrOneTacUUID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManURI            => AttrOneTacURI(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManByte           => AttrOneTacByte(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManShort          => AttrOneTacShort(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
              case a: AttrOneManChar           => AttrOneTacChar(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)
            }
            case a: AttrSetMan => a match {
              case a: AttrSetManID             => AttrSetTacID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManString         => AttrSetTacString(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManInt            => AttrSetTacInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManLong           => AttrSetTacLong(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManFloat          => AttrSetTacFloat(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManDouble         => AttrSetTacDouble(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManBoolean        => AttrSetTacBoolean(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManBigInt         => AttrSetTacBigInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManBigDecimal     => AttrSetTacBigDecimal(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManDate           => AttrSetTacDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManDuration       => AttrSetTacDuration(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManInstant        => AttrSetTacInstant(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManLocalDate      => AttrSetTacLocalDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManLocalTime      => AttrSetTacLocalTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManLocalDateTime  => AttrSetTacLocalDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManOffsetTime     => AttrSetTacOffsetTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManOffsetDateTime => AttrSetTacOffsetDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManZonedDateTime  => AttrSetTacZonedDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManUUID           => AttrSetTacUUID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManURI            => AttrSetTacURI(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManByte           => AttrSetTacByte(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManShort          => AttrSetTacShort(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSetManChar           => AttrSetTacChar(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
            }
            case a: AttrSeqMan => a match {
              case a: AttrSeqManID             => AttrSeqTacID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManString         => AttrSeqTacString(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManInt            => AttrSeqTacInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManLong           => AttrSeqTacLong(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManFloat          => AttrSeqTacFloat(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManDouble         => AttrSeqTacDouble(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManBoolean        => AttrSeqTacBoolean(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManBigInt         => AttrSeqTacBigInt(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManBigDecimal     => AttrSeqTacBigDecimal(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManDate           => AttrSeqTacDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManDuration       => AttrSeqTacDuration(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManInstant        => AttrSeqTacInstant(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManLocalDate      => AttrSeqTacLocalDate(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManLocalTime      => AttrSeqTacLocalTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManLocalDateTime  => AttrSeqTacLocalDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManOffsetTime     => AttrSeqTacOffsetTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManOffsetDateTime => AttrSeqTacOffsetDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManZonedDateTime  => AttrSeqTacZonedDateTime(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManUUID           => AttrSeqTacUUID(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManURI            => AttrSeqTacURI(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManByte           => AttrSeqTacByte(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManShort          => AttrSeqTacShort(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrSeqManChar           => AttrSeqTacChar(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
            }
            case a: AttrMapMan => a match {
              case a: AttrMapManID             => AttrMapTacID(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManString         => AttrMapTacString(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManInt            => AttrMapTacInt(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManLong           => AttrMapTacLong(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManFloat          => AttrMapTacFloat(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManDouble         => AttrMapTacDouble(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManBoolean        => AttrMapTacBoolean(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManBigInt         => AttrMapTacBigInt(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManBigDecimal     => AttrMapTacBigDecimal(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManDate           => AttrMapTacDate(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManDuration       => AttrMapTacDuration(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManInstant        => AttrMapTacInstant(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManLocalDate      => AttrMapTacLocalDate(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManLocalTime      => AttrMapTacLocalTime(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManLocalDateTime  => AttrMapTacLocalDateTime(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManOffsetTime     => AttrMapTacOffsetTime(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManOffsetDateTime => AttrMapTacOffsetDateTime(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManZonedDateTime  => AttrMapTacZonedDateTime(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManUUID           => AttrMapTacUUID(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManURI            => AttrMapTacURI(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManByte           => AttrMapTacByte(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManShort          => AttrMapTacShort(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
              case a: AttrMapManChar           => AttrMapTacChar(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)
            }
            case other         => other
          }
          (tacitAttr, List(filterAttr0))
        } else (filterAttr0, Nil)

        val filterPath         = resolvePath(filterAttrMolecule.dataModel.elements, Nil)
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
    dataModel.copy(elements = es.init ++ attrs)
  }

  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
    es.map {
      case attr: AttrOneMan => attr match {
        case a@AttrOneManID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)             => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManString(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManInt(_, _, _, _, _, _, _, _, _, Some(sort), _, _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLong(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManFloat(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDouble(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBoolean(_, _, _, _, _, _, _, _, _, Some(sort), _, _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigInt(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigDecimal(_, _, _, _, _, _, _, _, _, Some(sort), _, _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDate(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDuration(_, _, _, _, _, _, _, _, _, Some(sort), _, _)       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManInstant(_, _, _, _, _, _, _, _, _, Some(sort), _, _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalDate(_, _, _, _, _, _, _, _, _, Some(sort), _, _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLocalDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManOffsetTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManOffsetDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManZonedDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManUUID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManURI(_, _, _, _, _, _, _, _, _, Some(sort), _, _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManByte(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManShort(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManChar(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a                                                                       => a
      }
      case attr: AttrOneOpt => attr match {
        case a@AttrOneOptID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)             => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptString(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptInt(_, _, _, _, _, _, _, _, _, Some(sort), _, _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLong(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptFloat(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDouble(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBoolean(_, _, _, _, _, _, _, _, _, Some(sort), _, _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigInt(_, _, _, _, _, _, _, _, _, Some(sort), _, _)         => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigDecimal(_, _, _, _, _, _, _, _, _, Some(sort), _, _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDate(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDuration(_, _, _, _, _, _, _, _, _, Some(sort), _, _)       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptInstant(_, _, _, _, _, _, _, _, _, Some(sort), _, _)        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalDate(_, _, _, _, _, _, _, _, _, Some(sort), _, _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLocalDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptOffsetTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptOffsetDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptZonedDateTime(_, _, _, _, _, _, _, _, _, Some(sort), _, _)  => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptUUID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptURI(_, _, _, _, _, _, _, _, _, Some(sort), _, _)            => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptByte(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptShort(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptChar(_, _, _, _, _, _, _, _, _, Some(sort), _, _)           => a.copy(sort = Some(reverseSort(sort)))
        case a                                                                       => a
      }
      case other            => other
    }
  }

  private def reverseSort(sort: String): String = sort.head match {
    case 'a' => "d" + sort.last
    case 'd' => "a" + sort.last
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
        case _: OptRef        => topLevelAttrCount(tail, count)
        case _: OptEntity     => topLevelAttrCount(tail, count)
        case _: BackRef       => topLevelAttrCount(tail, count)
        case Nested(_, es)    => topLevelAttrCount(tail, count + countNested(es))
        case OptNested(_, es) => topLevelAttrCount(tail, count + countNested(es))
      }
    }
  }

  private def countNested(elements: List[Element]): Int = {
    topLevelAttrCount(elements, 0)
  }
}