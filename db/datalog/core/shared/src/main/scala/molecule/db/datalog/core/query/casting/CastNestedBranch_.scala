// GENERATED CODE ********************************
package molecule.db.datalog.core.query.casting

import molecule.db.datalog.core.query.DatomicQueryBase


trait CastNestedBranch_ { self: DatomicQueryBase =>

  final protected def castBranch[T](
    casts: List[AnyRef => AnyRef],
    firstAttrIndex: AttrIndex,
  ): (Row, NestedTpls) => T = {
    casts.length match {
      case 0  => cast0[T]
      case 1  => cast1[T](casts, firstAttrIndex)
      case 2  => cast2[T](casts, firstAttrIndex)
      case 3  => cast3[T](casts, firstAttrIndex)
      case 4  => cast4[T](casts, firstAttrIndex)
      case 5  => cast5[T](casts, firstAttrIndex)
      case 6  => cast6[T](casts, firstAttrIndex)
      case 7  => cast7[T](casts, firstAttrIndex)
      case 8  => cast8[T](casts, firstAttrIndex)
      case 9  => cast9[T](casts, firstAttrIndex)
      case 10 => cast10[T](casts, firstAttrIndex)
      case 11 => cast11[T](casts, firstAttrIndex)
      case 12 => cast12[T](casts, firstAttrIndex)
      case 13 => cast13[T](casts, firstAttrIndex)
      case 14 => cast14[T](casts, firstAttrIndex)
      case 15 => cast15[T](casts, firstAttrIndex)
      case 16 => cast16[T](casts, firstAttrIndex)
      case 17 => cast17[T](casts, firstAttrIndex)
      case 18 => cast18[T](casts, firstAttrIndex)
      case 19 => cast19[T](casts, firstAttrIndex)
      case 20 => cast20[T](casts, firstAttrIndex)
      case 21 => cast21[T](casts, firstAttrIndex)
      case n  =>
        val last = n - 1
        (row: Row, nested: NestedTpls) =>
          var rowIndex   = firstAttrIndex + last
          var castIndex  = last
          var tpl: Tuple = Tuple1(nested) // adding nested tuples last
          while (castIndex >= 0) {
            tpl = casts(castIndex)(row.get(rowIndex)) *: tpl
            rowIndex -= 1
            castIndex -= 1
          }
          tpl.asInstanceOf[T]
    }
  }

  final private def cast0[T]: (Row, NestedTpls) => T = {
    (_: Row, nested: NestedTpls) => nested.asInstanceOf[T]
  }

  final private def cast1[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1) = casts
    val List(i1) = (firstIndex until firstIndex + 1).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast2[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2) = casts
    val List(i1, i2) = (firstIndex until firstIndex + 2).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast3[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3) = casts
    val List(i1, i2, i3) = (firstIndex until firstIndex + 3).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast4[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4) = casts
    val List(i1, i2, i3, i4) = (firstIndex until firstIndex + 4).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast5[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5) = casts
    val List(i1, i2, i3, i4, i5) = (firstIndex until firstIndex + 5).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast6[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6) = casts
    val List(i1, i2, i3, i4, i5, i6) = (firstIndex until firstIndex + 6).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast7[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casts
    val List(i1, i2, i3, i4, i5, i6, i7) = (firstIndex until firstIndex + 7).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast8[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstIndex until firstIndex + 8).toList
    (row: Row, nested: NestedTpls) =>
      (
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        c8(row.get(i8)),
        nested
      ).asInstanceOf[T]
  }

  final private def cast9[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstIndex until firstIndex + 9).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast10[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstIndex until firstIndex + 10).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast11[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstIndex until firstIndex + 11).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast12[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstIndex until firstIndex + 12).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast13[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstIndex until firstIndex + 13).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast14[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstIndex until firstIndex + 14).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast15[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstIndex until firstIndex + 15).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast16[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstIndex until firstIndex + 16).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast17[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstIndex until firstIndex + 17).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast18[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstIndex until firstIndex + 18).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast19[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstIndex until firstIndex + 19).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast20[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstIndex until firstIndex + 20).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast21[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstIndex until firstIndex + 21).toList
    (row: Row, nested: NestedTpls) =>
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
        nested
      ).asInstanceOf[T]
  }

  final private def cast22[T](
    casts: List[AnyRef => AnyRef],
    firstIndex: AttrIndex
  ): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22) = (firstIndex until firstIndex + 22).toList
    (row: Row, nested: NestedTpls) =>
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
        c22(row.get(i22)),
        nested
      ).asInstanceOf[T]
  }
}