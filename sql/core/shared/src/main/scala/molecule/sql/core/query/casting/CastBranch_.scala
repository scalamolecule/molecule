// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase


class CastBranch_[Nested] extends SqlQueryBase {

  final def cast(
    casts: List[Cast],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    casts.length match {
      case 0  => cast0
      case 1  => cast1(casts, firstIndex)
      case 2  => cast2(casts, firstIndex)
      case 3  => cast3(casts, firstIndex)
      case 4  => cast4(casts, firstIndex)
      case 5  => cast5(casts, firstIndex)
      case 6  => cast6(casts, firstIndex)
      case 7  => cast7(casts, firstIndex)
      case 8  => cast8(casts, firstIndex)
      case 9  => cast9(casts, firstIndex)
      case 10 => cast10(casts, firstIndex)
      case 11 => cast11(casts, firstIndex)
      case 12 => cast12(casts, firstIndex)
      case 13 => cast13(casts, firstIndex)
      case 14 => cast14(casts, firstIndex)
      case 15 => cast15(casts, firstIndex)
      case 16 => cast16(casts, firstIndex)
      case 17 => cast17(casts, firstIndex)
      case 18 => cast18(casts, firstIndex)
      case 19 => cast19(casts, firstIndex)
      case 20 => cast20(casts, firstIndex)
      case 21 => cast21(casts, firstIndex)
    }
  }

  final private def cast0: (RS, Nested) => Any = {
    (_: RS, nested: Nested) =>
      nested
  }

  final private def cast1(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val c1 = casts.head
    (row: RS, nested: Nested) => {
      (
        c1(row, firstIndex),
        nested
      )
    }
  }

  final private def cast2(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2) = casts
    val List(i1, i2) = (firstIndex until firstIndex + 2).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        nested
      )
  }

  final private def cast3(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3) = casts
    val List(i1, i2, i3) = (firstIndex until firstIndex + 3).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        nested
      )
  }

  final private def cast4(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4) = casts
    val List(i1, i2, i3, i4) = (firstIndex until firstIndex + 4).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        nested
      )
  }

  final private def cast5(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5) = casts
    val List(i1, i2, i3, i4, i5) = (firstIndex until firstIndex + 5).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        nested
      )
  }

  final private def cast6(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casts
    val List(i1, i2, i3, i4, i5, i6) = (firstIndex until firstIndex + 6).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        nested
      )
  }

  final private def cast7(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casts
    val List(i1, i2, i3, i4, i5, i6, i7) = (firstIndex until firstIndex + 7).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        nested
      )
  }

  final private def cast8(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstIndex until firstIndex + 8).toList
    (row: RS, nested: Nested) =>
      (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        nested
      )
  }

  final private def cast9(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstIndex until firstIndex + 9).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast10(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstIndex until firstIndex + 10).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast11(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstIndex until firstIndex + 11).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast12(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstIndex until firstIndex + 12).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast13(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstIndex until firstIndex + 13).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast14(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstIndex until firstIndex + 14).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast15(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstIndex until firstIndex + 15).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast16(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstIndex until firstIndex + 16).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast17(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstIndex until firstIndex + 17).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast18(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstIndex until firstIndex + 18).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast19(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstIndex until firstIndex + 19).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast20(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstIndex until firstIndex + 20).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }

  final private def cast21(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Nested) => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstIndex until firstIndex + 21).toList
    (row: RS, nested: Nested) =>
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
        nested
      )
  }
}