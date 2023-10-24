// GENERATED CODE ********************************
package molecule.core.validation.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._

trait InsertValueResolvers_ {

  def tpl2valueResolver(a: Attr, curElements: List[Element]): Product => Seq[Value] = {
    val valueResolvers: Seq[Product => Value] = a.valueAttrs.flatMap { attr =>
      curElements.zipWithIndex.collectFirst {
        case (a1: Attr, i) if a1.attr == attr => (attr, a1, i)
      }
    }.sortBy(_._1).map { case (_, a, i) =>
      a match {
        case a: AttrOneMan => a match {
          case _: AttrOneManID             => (tpl: Product) => OneString(tpl.productElement(i).asInstanceOf[String])
          case _: AttrOneManString         => (tpl: Product) => OneString(tpl.productElement(i).asInstanceOf[String])
          case _: AttrOneManInt            => (tpl: Product) => OneInt(tpl.productElement(i).asInstanceOf[Int])
          case _: AttrOneManLong           => (tpl: Product) => OneLong(tpl.productElement(i).asInstanceOf[Long])
          case _: AttrOneManFloat          => (tpl: Product) => OneFloat(tpl.productElement(i).asInstanceOf[Float])
          case _: AttrOneManDouble         => (tpl: Product) => OneDouble(tpl.productElement(i).asInstanceOf[Double])
          case _: AttrOneManBoolean        => (tpl: Product) => OneBoolean(tpl.productElement(i).asInstanceOf[Boolean])
          case _: AttrOneManBigInt         => (tpl: Product) => OneBigInt(tpl.productElement(i).asInstanceOf[BigInt])
          case _: AttrOneManBigDecimal     => (tpl: Product) => OneBigDecimal(tpl.productElement(i).asInstanceOf[BigDecimal])
          case _: AttrOneManDate           => (tpl: Product) => OneDate(tpl.productElement(i).asInstanceOf[Date])
          case _: AttrOneManDuration       => (tpl: Product) => OneDuration(tpl.productElement(i).asInstanceOf[Duration])
          case _: AttrOneManInstant        => (tpl: Product) => OneInstant(tpl.productElement(i).asInstanceOf[Instant])
          case _: AttrOneManLocalDate      => (tpl: Product) => OneLocalDate(tpl.productElement(i).asInstanceOf[LocalDate])
          case _: AttrOneManLocalTime      => (tpl: Product) => OneLocalTime(tpl.productElement(i).asInstanceOf[LocalTime])
          case _: AttrOneManLocalDateTime  => (tpl: Product) => OneLocalDateTime(tpl.productElement(i).asInstanceOf[LocalDateTime])
          case _: AttrOneManOffsetTime     => (tpl: Product) => OneOffsetTime(tpl.productElement(i).asInstanceOf[OffsetTime])
          case _: AttrOneManOffsetDateTime => (tpl: Product) => OneOffsetDateTime(tpl.productElement(i).asInstanceOf[OffsetDateTime])
          case _: AttrOneManZonedDateTime  => (tpl: Product) => OneZonedDateTime(tpl.productElement(i).asInstanceOf[ZonedDateTime])
          case _: AttrOneManUUID           => (tpl: Product) => OneUUID(tpl.productElement(i).asInstanceOf[UUID])
          case _: AttrOneManURI            => (tpl: Product) => OneURI(tpl.productElement(i).asInstanceOf[URI])
          case _: AttrOneManByte           => (tpl: Product) => OneByte(tpl.productElement(i).asInstanceOf[Byte])
          case _: AttrOneManShort          => (tpl: Product) => OneShort(tpl.productElement(i).asInstanceOf[Short])
          case _: AttrOneManChar           => (tpl: Product) => OneChar(tpl.productElement(i).asInstanceOf[Char])
        }

        case a: AttrSetMan => a match {
          case _: AttrSetManID             => (tpl: Product) => SetString(tpl.productElement(i).asInstanceOf[Set[String]])
          case _: AttrSetManString         => (tpl: Product) => SetString(tpl.productElement(i).asInstanceOf[Set[String]])
          case _: AttrSetManInt            => (tpl: Product) => SetInt(tpl.productElement(i).asInstanceOf[Set[Int]])
          case _: AttrSetManLong           => (tpl: Product) => SetLong(tpl.productElement(i).asInstanceOf[Set[Long]])
          case _: AttrSetManFloat          => (tpl: Product) => SetFloat(tpl.productElement(i).asInstanceOf[Set[Float]])
          case _: AttrSetManDouble         => (tpl: Product) => SetDouble(tpl.productElement(i).asInstanceOf[Set[Double]])
          case _: AttrSetManBoolean        => (tpl: Product) => SetBoolean(tpl.productElement(i).asInstanceOf[Set[Boolean]])
          case _: AttrSetManBigInt         => (tpl: Product) => SetBigInt(tpl.productElement(i).asInstanceOf[Set[BigInt]])
          case _: AttrSetManBigDecimal     => (tpl: Product) => SetBigDecimal(tpl.productElement(i).asInstanceOf[Set[BigDecimal]])
          case _: AttrSetManDate           => (tpl: Product) => SetDate(tpl.productElement(i).asInstanceOf[Set[Date]])
          case _: AttrSetManDuration       => (tpl: Product) => SetDuration(tpl.productElement(i).asInstanceOf[Set[Duration]])
          case _: AttrSetManInstant        => (tpl: Product) => SetInstant(tpl.productElement(i).asInstanceOf[Set[Instant]])
          case _: AttrSetManLocalDate      => (tpl: Product) => SetLocalDate(tpl.productElement(i).asInstanceOf[Set[LocalDate]])
          case _: AttrSetManLocalTime      => (tpl: Product) => SetLocalTime(tpl.productElement(i).asInstanceOf[Set[LocalTime]])
          case _: AttrSetManLocalDateTime  => (tpl: Product) => SetLocalDateTime(tpl.productElement(i).asInstanceOf[Set[LocalDateTime]])
          case _: AttrSetManOffsetTime     => (tpl: Product) => SetOffsetTime(tpl.productElement(i).asInstanceOf[Set[OffsetTime]])
          case _: AttrSetManOffsetDateTime => (tpl: Product) => SetOffsetDateTime(tpl.productElement(i).asInstanceOf[Set[OffsetDateTime]])
          case _: AttrSetManZonedDateTime  => (tpl: Product) => SetZonedDateTime(tpl.productElement(i).asInstanceOf[Set[ZonedDateTime]])
          case _: AttrSetManUUID           => (tpl: Product) => SetUUID(tpl.productElement(i).asInstanceOf[Set[UUID]])
          case _: AttrSetManURI            => (tpl: Product) => SetURI(tpl.productElement(i).asInstanceOf[Set[URI]])
          case _: AttrSetManByte           => (tpl: Product) => SetByte(tpl.productElement(i).asInstanceOf[Set[Byte]])
          case _: AttrSetManShort          => (tpl: Product) => SetShort(tpl.productElement(i).asInstanceOf[Set[Short]])
          case _: AttrSetManChar           => (tpl: Product) => SetChar(tpl.productElement(i).asInstanceOf[Set[Char]])
        }

        case a => throw new Exception("Unsupported attribute type for insert validation value resolvers: " + a)
      }
    }

    valueResolvers.length match {
      case 1  => resolve1(valueResolvers)
      case 2  => resolve2(valueResolvers)
      case 3  => resolve3(valueResolvers)
      case 4  => resolve4(valueResolvers)
      case 5  => resolve5(valueResolvers)
      case 6  => resolve6(valueResolvers)
      case 7  => resolve7(valueResolvers)
      case 8  => resolve8(valueResolvers)
      case 9  => resolve9(valueResolvers)
      case 10 => resolve10(valueResolvers)
      case 11 => resolve11(valueResolvers)
      case 12 => resolve12(valueResolvers)
      case 13 => resolve13(valueResolvers)
      case 14 => resolve14(valueResolvers)
      case 15 => resolve15(valueResolvers)
      case 16 => resolve16(valueResolvers)
      case 17 => resolve17(valueResolvers)
      case 18 => resolve18(valueResolvers)
      case 19 => resolve19(valueResolvers)
      case 20 => resolve20(valueResolvers)
      case 21 => resolve21(valueResolvers)
      case 22 => resolve22(valueResolvers)
    }
  }

  final private def resolve1(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl)
      )
  }

  final private def resolve2(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl)
      )
  }

  final private def resolve3(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl)
      )
  }

  final private def resolve4(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl)
      )
  }

  final private def resolve5(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl)
      )
  }

  final private def resolve6(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl)
      )
  }

  final private def resolve7(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl)
      )
  }

  final private def resolve8(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl)
      )
  }

  final private def resolve9(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl)
      )
  }

  final private def resolve10(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl)
      )
  }

  final private def resolve11(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl)
      )
  }

  final private def resolve12(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl)
      )
  }

  final private def resolve13(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl)
      )
  }

  final private def resolve14(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl)
      )
  }

  final private def resolve15(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl)
      )
  }

  final private def resolve16(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl)
      )
  }

  final private def resolve17(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl)
      )
  }

  final private def resolve18(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl),
        r18(tpl)
      )
  }

  final private def resolve19(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl),
        r18(tpl),
        r19(tpl)
      )
  }

  final private def resolve20(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl),
        r18(tpl),
        r19(tpl),
        r20(tpl)
      )
  }

  final private def resolve21(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl),
        r18(tpl),
        r19(tpl),
        r20(tpl),
        r21(tpl)
      )
  }

  final private def resolve22(
    valueResolvers: Seq[Product => Value]
  ): Product => Seq[Value] = {
    val Seq(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22) = valueResolvers
    (tpl: Product) =>
      Seq(
        r1(tpl),
        r2(tpl),
        r3(tpl),
        r4(tpl),
        r5(tpl),
        r6(tpl),
        r7(tpl),
        r8(tpl),
        r9(tpl),
        r10(tpl),
        r11(tpl),
        r12(tpl),
        r13(tpl),
        r14(tpl),
        r15(tpl),
        r16(tpl),
        r17(tpl),
        r18(tpl),
        r19(tpl),
        r20(tpl),
        r21(tpl),
        r22(tpl)
      )
  }
}