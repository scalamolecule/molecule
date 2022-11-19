// GENERATED CODE ********************************
package molecule.db.datomic.query

import molecule.core.query.Model2Query


trait CastNestedLeaf_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def castLeaf(casts: List[AnyRef => AnyRef], firstRowIndex: Int): Row => Any = {
    val n          = casts.length
    val rowIndexes = (firstRowIndex until (firstRowIndex + n)).toList
    n match {
      case 1 => cast1(casts, rowIndexes)
      case 2 => cast2(casts, rowIndexes)
      case 3 => cast3(casts, rowIndexes)
      case 4 => cast4(casts, rowIndexes)
      case 5 => cast5(casts, rowIndexes)
      case 6 => cast6(casts, rowIndexes)
      case 7 => cast7(casts, rowIndexes)
      case 8 => cast8(casts, rowIndexes)
      case 9 => cast9(casts, rowIndexes)
      case 10 => cast10(casts, rowIndexes)
      case 11 => cast11(casts, rowIndexes)
      case 12 => cast12(casts, rowIndexes)
      case 13 => cast13(casts, rowIndexes)
      case 14 => cast14(casts, rowIndexes)
      case 15 => cast15(casts, rowIndexes)
      case 16 => cast16(casts, rowIndexes)
      case 17 => cast17(casts, rowIndexes)
      case 18 => cast18(casts, rowIndexes)
      case 19 => cast19(casts, rowIndexes)
      case 20 => cast20(casts, rowIndexes)
      case 21 => cast21(casts, rowIndexes)
      case 22 => cast22(casts, rowIndexes)
    }
  }

  final private def cast1(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val List(i0) = rowIndexes
    (row: Row) =>
      (
        c0(row.get(i0))
        )
  }

  final private def cast2(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val List(i0, i1) = rowIndexes
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1))
        )
  }

  final private def cast3(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val List(i0, i1, i2) = rowIndexes
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2))
        )
  }

  final private def cast4(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val List(i0, i1, i2, i3) = rowIndexes
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3))
        )
  }

  final private def cast5(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val List(i0, i1, i2, i3, i4) = rowIndexes
    (row: Row) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4))
        )
  }

  final private def cast6(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val List(i0, i1, i2, i3, i4, i5) = rowIndexes
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

  final private def cast7(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val List(i0, i1, i2, i3, i4, i5, i6) = rowIndexes
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

  final private def cast8(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val List(i0, i1, i2, i3, i4, i5, i6, i7) = rowIndexes
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

  final private def cast9(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8) = rowIndexes
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

  final private def cast10(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9) = rowIndexes
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

  final private def cast11(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = rowIndexes
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

  final private def cast12(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = rowIndexes
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

  final private def cast13(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = rowIndexes
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

  final private def cast14(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = rowIndexes
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

  final private def cast15(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = rowIndexes
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

  final private def cast16(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = rowIndexes
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

  final private def cast17(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = rowIndexes
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

  final private def cast18(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val c17 = casts(17)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = rowIndexes
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

  final private def cast19(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val c17 = casts(17)
    val c18 = casts(18)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = rowIndexes
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

  final private def cast20(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val c17 = casts(17)
    val c18 = casts(18)
    val c19 = casts(19)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = rowIndexes
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

  final private def cast21(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val c17 = casts(17)
    val c18 = casts(18)
    val c19 = casts(19)
    val c20 = casts(20)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = rowIndexes
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

  final private def cast22(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    val c9 = casts(9)
    val c10 = casts(10)
    val c11 = casts(11)
    val c12 = casts(12)
    val c13 = casts(13)
    val c14 = casts(14)
    val c15 = casts(15)
    val c16 = casts(16)
    val c17 = casts(17)
    val c18 = casts(18)
    val c19 = casts(19)
    val c20 = casts(20)
    val c21 = casts(21)
    val List(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = rowIndexes
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