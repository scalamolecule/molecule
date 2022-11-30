// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastNestedBranch_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def castBranch[T](
    casts: List[AnyRef => AnyRef],
    firstRowIndex: Int
  ): (Row, List[Any]) => T = {
    val n          = casts.length
    val rowIndexes = (firstRowIndex until (firstRowIndex + n)).toList
    n match {
      case 0 => cast0[T]
      case 1 => cast1[T](casts, rowIndexes)
      case 2 => cast2[T](casts, rowIndexes)
      case 3 => cast3[T](casts, rowIndexes)
      case 4 => cast4[T](casts, rowIndexes)
      case 5 => cast5[T](casts, rowIndexes)
      case 6 => cast6[T](casts, rowIndexes)
      case 7 => cast7[T](casts, rowIndexes)
      case 8 => cast8[T](casts, rowIndexes)
      case 9 => cast9[T](casts, rowIndexes)
      case 10 => cast10[T](casts, rowIndexes)
      case 11 => cast11[T](casts, rowIndexes)
      case 12 => cast12[T](casts, rowIndexes)
      case 13 => cast13[T](casts, rowIndexes)
      case 14 => cast14[T](casts, rowIndexes)
      case 15 => cast15[T](casts, rowIndexes)
      case 16 => cast16[T](casts, rowIndexes)
      case 17 => cast17[T](casts, rowIndexes)
      case 18 => cast18[T](casts, rowIndexes)
      case 19 => cast19[T](casts, rowIndexes)
      case 20 => cast20[T](casts, rowIndexes)
      case 21 => cast21[T](casts, rowIndexes)
    }
  }

  final private def cast0[T]: (Row, List[Any]) => T = {
    (row: Row, leaf: List[Any]) => leaf.asInstanceOf[T]
  }

  final private def cast1[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val List(i0) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast2[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val List(i0, i1) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast3[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val List(i0, i1, i2) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast4[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val List(i0, i1, i2, i3) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast5[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val List(i0, i1, i2, i3, i4) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast6[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val List(i0, i1, i2, i3, i4, i5) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast7[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val List(i0, i1, i2, i3, i4, i5, i6) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast8[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val List(i0, i1, i2, i3, i4, i5, i6, i7) = rowIndexes
    (row: Row, leaf: List[Any]) =>
      (
        c0(row.get(i0)),
        c1(row.get(i1)),
        c2(row.get(i2)),
        c3(row.get(i3)),
        c4(row.get(i4)),
        c5(row.get(i5)),
        c6(row.get(i6)),
        c7(row.get(i7)),
        leaf
        ).asInstanceOf[T]
  }

  final private def cast9[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast10[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast11[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast12[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast13[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast14[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast15[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast16[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast17[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast18[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast19[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast20[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }

  final private def cast21[T](
    casts: List[AnyRef => AnyRef],
    rowIndexes: List[Int]
  ): (Row, List[Any]) => T = {
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
    (row: Row, leaf: List[Any]) =>
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
        leaf
        ).asInstanceOf[T]
  }
}