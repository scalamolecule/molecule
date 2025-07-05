// GENERATED CODE ********************************
package molecule.db.datalog.core.query.casting

import molecule.db.datalog.core.query.DatomicQueryBase


trait CastRow2AnyTpl_ { self: DatomicQueryBase =>

  final def castRow2AnyTpl(
    casts: List[AnyRef => AnyRef],
    attrIndex: AttrIndex
  ): Row => Any = {
    casts.length match {
      case 1  => cast1(casts, attrIndex)
      case 2  => cast2(casts, attrIndex)
      case 3  => cast3(casts, attrIndex)
      case 4  => cast4(casts, attrIndex)
      case 5  => cast5(casts, attrIndex)
      case 6  => cast6(casts, attrIndex)
      case 7  => cast7(casts, attrIndex)
      case 8  => cast8(casts, attrIndex)
      case 9  => cast9(casts, attrIndex)
      case 10 => cast10(casts, attrIndex)
      case 11 => cast11(casts, attrIndex)
      case 12 => cast12(casts, attrIndex)
      case 13 => cast13(casts, attrIndex)
      case 14 => cast14(casts, attrIndex)
      case 15 => cast15(casts, attrIndex)
      case 16 => cast16(casts, attrIndex)
      case 17 => cast17(casts, attrIndex)
      case 18 => cast18(casts, attrIndex)
      case 19 => cast19(casts, attrIndex)
      case 20 => cast20(casts, attrIndex)
      case 21 => cast21(casts, attrIndex)
      case 22 => cast22(casts, attrIndex)
      case n  =>
        (row: Row) =>
          var rowIndex   = n - 1
          var castIndex  = attrIndex + n - 1
          var tpl: Tuple = EmptyTuple
          while (rowIndex >= 0) {
            tpl = casts(rowIndex)(row.get(castIndex)) *: tpl
            rowIndex -= 1
            castIndex -= 1
          }
          tpl
    }
  }

  final private def cast1(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val cast = casts.head
    (row: Row) => cast(row.get(attrIndex))
  }

  final private def cast2(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2) = casts
    val List(i1, i2) = (attrIndex until attrIndex + 2).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2))
      )
  }

  final private def cast3(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3) = casts
    val List(i1, i2, i3) = (attrIndex until attrIndex + 3).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3))
      )
  }

  final private def cast4(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4) = casts
    val List(i1, i2, i3, i4) = (attrIndex until attrIndex + 4).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4))
      )
  }

  final private def cast5(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5) = casts
    val List(i1, i2, i3, i4, i5) = (attrIndex until attrIndex + 5).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5))
      )
  }

  final private def cast6(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casts
    val List(i1, i2, i3, i4, i5, i6) = (attrIndex until attrIndex + 6).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6))
      )
  }

  final private def cast7(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casts
    val List(i1, i2, i3, i4, i5, i6, i7) = (attrIndex until attrIndex + 7).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7))
      )
  }

  final private def cast8(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (attrIndex until attrIndex + 8).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8))
      )
  }

  final private def cast9(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (attrIndex until attrIndex + 9).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9))
      )
  }

  final private def cast10(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (attrIndex until attrIndex + 10).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10))
      )
  }

  final private def cast11(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (attrIndex until attrIndex + 11).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11))
      )
  }

  final private def cast12(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (attrIndex until attrIndex + 12).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12))
      )
  }

  final private def cast13(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (attrIndex until attrIndex + 13).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13))
      )
  }

  final private def cast14(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (attrIndex until attrIndex + 14).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14))
      )
  }

  final private def cast15(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (attrIndex until attrIndex + 15).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15))
      )
  }

  final private def cast16(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (attrIndex until attrIndex + 16).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16))
      )
  }

  final private def cast17(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (attrIndex until attrIndex + 17).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17))
      )
  }

  final private def cast18(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (attrIndex until attrIndex + 18).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17)),
        c18(row.get(i18))
      )
  }

  final private def cast19(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (attrIndex until attrIndex + 19).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17)),
        c18(row.get(i18)),
        c19(row.get(i19))
      )
  }

  final private def cast20(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (attrIndex until attrIndex + 20).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17)),
        c18(row.get(i18)),
        c19(row.get(i19)),
        c20(row.get(i20))
      )
  }

  final private def cast21(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (attrIndex until attrIndex + 21).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17)),
        c18(row.get(i18)),
        c19(row.get(i19)),
        c20(row.get(i20)),
        c21(row.get(i21))
      )
  }

  final private def cast22(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22) = (attrIndex until attrIndex + 22).toList
    (row: Row) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        c9(row.get(i9)),
        c10(row.get(i10)),
        c11(row.get(i11)),
        c12(row.get(i12)),
        c13(row.get(i13)),
        c14(row.get(i14)),
        c15(row.get(i15)),
        c16(row.get(i16)),
        c17(row.get(i17)),
        c18(row.get(i18)),
        c19(row.get(i19)),
        c20(row.get(i20)),
        c21(row.get(i21)),
        c22(row.get(i22))
      )
  }
}