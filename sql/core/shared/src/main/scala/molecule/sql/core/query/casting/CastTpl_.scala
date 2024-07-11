// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase
import scala.annotation.tailrec


object CastTpl_ extends SqlQueryBase {

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[(RS, ParamIndex) => Any],
    attrIndex: ParamIndex,
    acc: List[(RS, ParamIndex) => Any],
  ): List[(RS, ParamIndex) => Any] = {
    arities match {
      case 0 :: as =>
        val cast = (row: RS, _: ParamIndex) => casts.head(row, attrIndex)
        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)

      // Nested
      case -1 :: Nil =>
        val cast = (_: RS, _: ParamIndex) => List.empty[Any]
        resolveArities(Nil, casts, 0, acc :+ cast)

      case _ => acc
    }
  }

  final def castTpl(
    arities: List[Int],
    casts: List[(RS, ParamIndex) => Any],
    attrIndex: ParamIndex,
  ): RS => Any = {
    val casters: List[(RS, ParamIndex) => Any] = resolveArities(arities, casts, attrIndex, Nil)
    arities.length match {
      case 1  => cast1(casters, attrIndex)
      case 2  => cast2(casters, attrIndex)
      case 3  => cast3(casters, attrIndex)
      case 4  => cast4(casters, attrIndex)
      case 5  => cast5(casters, attrIndex)
      case 6  => cast6(casters, attrIndex)
      case 7  => cast7(casters, attrIndex)
      case 8  => cast8(casters, attrIndex)
      case 9  => cast9(casters, attrIndex)
      case 10 => cast10(casters, attrIndex)
      case 11 => cast11(casters, attrIndex)
      case 12 => cast12(casters, attrIndex)
      case 13 => cast13(casters, attrIndex)
      case 14 => cast14(casters, attrIndex)
      case 15 => cast15(casters, attrIndex)
      case 16 => cast16(casters, attrIndex)
      case 17 => cast17(casters, attrIndex)
      case 18 => cast18(casters, attrIndex)
      case 19 => cast19(casters, attrIndex)
      case 20 => cast20(casters, attrIndex)
      case 21 => cast21(casters, attrIndex)
      case 22 => cast22(casters, attrIndex)
    }
  }

  final private def cast1(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1) = casters
    (row: RS) =>
      c1(row, attrIndex)
  }

  final private def cast2(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2) = casters
    val List(i1, i2) = (attrIndex until attrIndex + 2).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2)
      )
  }

  final private def cast3(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3) = casters
    val List(i1, i2, i3) = (attrIndex until attrIndex + 3).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3)
      )
  }

  final private def cast4(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4) = casters
    val List(i1, i2, i3, i4) = (attrIndex until attrIndex + 4).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4)
      )
  }

  final private def cast5(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5) = casters
    val List(i1, i2, i3, i4, i5) = (attrIndex until attrIndex + 5).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5)
      )
  }

  final private def cast6(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    val List(i1, i2, i3, i4, i5, i6) = (attrIndex until attrIndex + 6).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6)
      )
  }

  final private def cast7(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    val List(i1, i2, i3, i4, i5, i6, i7) = (attrIndex until attrIndex + 7).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7)
      )
  }

  final private def cast8(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (attrIndex until attrIndex + 8).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8)
      )
  }

  final private def cast9(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (attrIndex until attrIndex + 9).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9)
      )
  }

  final private def cast10(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (attrIndex until attrIndex + 10).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10)
      )
  }

  final private def cast11(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (attrIndex until attrIndex + 11).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11)
      )
  }

  final private def cast12(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (attrIndex until attrIndex + 12).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12)
      )
  }

  final private def cast13(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (attrIndex until attrIndex + 13).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13)
      )
  }

  final private def cast14(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (attrIndex until attrIndex + 14).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14)
      )
  }

  final private def cast15(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (attrIndex until attrIndex + 15).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15)
      )
  }

  final private def cast16(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (attrIndex until attrIndex + 16).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16)
      )
  }

  final private def cast17(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (attrIndex until attrIndex + 17).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17)
      )
  }

  final private def cast18(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (attrIndex until attrIndex + 18).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18)
      )
  }

  final private def cast19(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (attrIndex until attrIndex + 19).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19)
      )
  }

  final private def cast20(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (attrIndex until attrIndex + 20).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19),
        c20(row, i20)
      )
  }

  final private def cast21(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (attrIndex until attrIndex + 21).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19),
        c20(row, i20),
        c21(row, i21)
      )
  }

  final private def cast22(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22) = (attrIndex until attrIndex + 22).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19),
        c20(row, i20),
        c21(row, i21),
        c22(row, i22)
      )
  }
}