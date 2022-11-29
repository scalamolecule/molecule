// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastCompositeTpl_[Tpl] {
  self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def castSubTpl(n: Int, firstIndex: Int): Row => Any = {
    n match {
      case 1 => cast1(firstIndex)
      case 2 => cast2(firstIndex)
      case 3 => cast3(firstIndex)
      case 4 => cast4(firstIndex)
      case 5 => cast5(firstIndex)
      case 6 => cast6(firstIndex)
      case 7 => cast7(firstIndex)
      case 8 => cast8(firstIndex)
      case 9 => cast9(firstIndex)
      case 10 => cast10(firstIndex)
      case 11 => cast11(firstIndex)
      case 12 => cast12(firstIndex)
      case 13 => cast13(firstIndex)
      case 14 => cast14(firstIndex)
      case 15 => cast15(firstIndex)
      case 16 => cast16(firstIndex)
      case 17 => cast17(firstIndex)
      case 18 => cast18(firstIndex)
      case 19 => cast19(firstIndex)
      case 20 => cast20(firstIndex)
      case 21 => cast21(firstIndex)
      case 22 => cast22(firstIndex)
    }
  }

  final private def cast1(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val List(i0) = (firstIndex until firstIndex + 1).toList
    (row: Row) =>
      (
        c0(row.get(i0))
        )
  }

  final private def cast2(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val List(i0, i1) = (firstIndex until firstIndex + 2).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1))
        )
  }

  final private def cast3(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val List(i0, i1, i2) = (firstIndex until firstIndex + 3).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2))
        )
  }

  final private def cast4(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val List(i0, i1, i2, i3) = (firstIndex until firstIndex + 4).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3))
        )
  }

  final private def cast5(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val List(i0, i1, i2, i3, i4) = (firstIndex until firstIndex + 5).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4))
        )
  }

  final private def cast6(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val List(i0, i1, i2, i3, i4, i5) = (firstIndex until firstIndex + 6).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5))
        )
  }

  final private def cast7(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val List(i0, i1, i2, i3, i4, i5, i6) = (firstIndex until firstIndex + 7).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6))
        )
  }

  final private def cast8(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val List(i0, i1, i2, i3, i4, i5, i6, i7) = (firstIndex until firstIndex + 8).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7))
        )
  }

  final private def cast9(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8) = (firstIndex until firstIndex + 9).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast10(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstIndex until firstIndex + 10).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast11(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstIndex until firstIndex + 11).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast12(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstIndex until firstIndex + 12).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast13(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstIndex until firstIndex + 13).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast14(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstIndex until firstIndex + 14).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast15(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstIndex until firstIndex + 15).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast16(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstIndex until firstIndex + 16).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast17(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstIndex until firstIndex + 17).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast18(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val c17 = casts(firstIndex + 17)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstIndex until firstIndex + 18).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast19(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val c17 = casts(firstIndex + 17)
    val c18 = casts(firstIndex + 18)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstIndex until firstIndex + 19).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast20(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val c17 = casts(firstIndex + 17)
    val c18 = casts(firstIndex + 18)
    val c19 = casts(firstIndex + 19)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstIndex until firstIndex + 20).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast21(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val c17 = casts(firstIndex + 17)
    val c18 = casts(firstIndex + 18)
    val c19 = casts(firstIndex + 19)
    val c20 = casts(firstIndex + 20)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstIndex until firstIndex + 21).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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

  final private def cast22(firstIndex: Int): Row => Any = {
    val c0 = casts(firstIndex)
    val c1 = casts(firstIndex + 1)
    val c2 = casts(firstIndex + 2)
    val c3 = casts(firstIndex + 3)
    val c4 = casts(firstIndex + 4)
    val c5 = casts(firstIndex + 5)
    val c6 = casts(firstIndex + 6)
    val c7 = casts(firstIndex + 7)
    val c8 = casts(firstIndex + 8)
    val c9 = casts(firstIndex + 9)
    val c10 = casts(firstIndex + 10)
    val c11 = casts(firstIndex + 11)
    val c12 = casts(firstIndex + 12)
    val c13 = casts(firstIndex + 13)
    val c14 = casts(firstIndex + 14)
    val c15 = casts(firstIndex + 15)
    val c16 = casts(firstIndex + 16)
    val c17 = casts(firstIndex + 17)
    val c18 = casts(firstIndex + 18)
    val c19 = casts(firstIndex + 19)
    val c20 = casts(firstIndex + 20)
    val c21 = casts(firstIndex + 21)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstIndex until firstIndex + 22).toList
    (row: Row) =>
      (
        c0(row.get(i0)),
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
}