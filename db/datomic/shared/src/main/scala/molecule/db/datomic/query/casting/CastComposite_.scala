// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastComposite_[Tpl] extends CastCompositeTpl_[Tpl] {
  self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def compositeRow2tpl: Row => Tpl = {
    val tplCounts = compositeTplCounts.filterNot(_ == 0)
    tplCounts.length match {
      case 1 => cast1(tplCounts)
      case 2 => cast2(tplCounts)
      case 3 => cast3(tplCounts)
      case 4 => cast4(tplCounts)
      case 5 => cast5(tplCounts)
      case 6 => cast6(tplCounts)
      case 7 => cast7(tplCounts)
      case 8 => cast8(tplCounts)
      case 9 => cast9(tplCounts)
      case 10 => cast10(tplCounts)
      case 11 => cast11(tplCounts)
      case 12 => cast12(tplCounts)
      case 13 => cast13(tplCounts)
      case 14 => cast14(tplCounts)
      case 15 => cast15(tplCounts)
      case 16 => cast16(tplCounts)
      case 17 => cast17(tplCounts)
      case 18 => cast18(tplCounts)
      case 19 => cast19(tplCounts)
      case 20 => cast20(tplCounts)
      case 21 => cast21(tplCounts)
      case 22 => cast22(tplCounts)
    }
  }

  final private def cast1(tplCounts: List[Int]): Row => Tpl = {
    val List(n0) = tplCounts
    val i0 = 0
    val c0 = castSubTpl(n0, i0)
    (row: Row) =>
      (
        c0(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast2(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    (row: Row) =>
      (
        c0(row),
        c1(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast3(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast4(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast5(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast6(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast7(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast8(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast9(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast10(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast11(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast12(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast13(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast14(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast15(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast16(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast17(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast18(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val i17 = n16 + i16
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    val c17 = castSubTpl(n17, i17)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row),
        c17(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast19(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val i17 = n16 + i16
    val i18 = n17 + i17
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    val c17 = castSubTpl(n17, i17)
    val c18 = castSubTpl(n18, i18)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row),
        c17(row),
        c18(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast20(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val i17 = n16 + i16
    val i18 = n17 + i17
    val i19 = n18 + i18
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    val c17 = castSubTpl(n17, i17)
    val c18 = castSubTpl(n18, i18)
    val c19 = castSubTpl(n19, i19)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row),
        c17(row),
        c18(row),
        c19(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast21(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val i17 = n16 + i16
    val i18 = n17 + i17
    val i19 = n18 + i18
    val i20 = n19 + i19
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    val c17 = castSubTpl(n17, i17)
    val c18 = castSubTpl(n18, i18)
    val c19 = castSubTpl(n19, i19)
    val c20 = castSubTpl(n20, i20)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row),
        c17(row),
        c18(row),
        c19(row),
        c20(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast22(tplCounts: List[Int]): Row => Tpl = {
    val List(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21) = tplCounts
    val i0 = 0
    val i1 = n0 + i0
    val i2 = n1 + i1
    val i3 = n2 + i2
    val i4 = n3 + i3
    val i5 = n4 + i4
    val i6 = n5 + i5
    val i7 = n6 + i6
    val i8 = n7 + i7
    val i9 = n8 + i8
    val i10 = n9 + i9
    val i11 = n10 + i10
    val i12 = n11 + i11
    val i13 = n12 + i12
    val i14 = n13 + i13
    val i15 = n14 + i14
    val i16 = n15 + i15
    val i17 = n16 + i16
    val i18 = n17 + i17
    val i19 = n18 + i18
    val i20 = n19 + i19
    val i21 = n20 + i20
    val c0 = castSubTpl(n0, i0)
    val c1 = castSubTpl(n1, i1)
    val c2 = castSubTpl(n2, i2)
    val c3 = castSubTpl(n3, i3)
    val c4 = castSubTpl(n4, i4)
    val c5 = castSubTpl(n5, i5)
    val c6 = castSubTpl(n6, i6)
    val c7 = castSubTpl(n7, i7)
    val c8 = castSubTpl(n8, i8)
    val c9 = castSubTpl(n9, i9)
    val c10 = castSubTpl(n10, i10)
    val c11 = castSubTpl(n11, i11)
    val c12 = castSubTpl(n12, i12)
    val c13 = castSubTpl(n13, i13)
    val c14 = castSubTpl(n14, i14)
    val c15 = castSubTpl(n15, i15)
    val c16 = castSubTpl(n16, i16)
    val c17 = castSubTpl(n17, i17)
    val c18 = castSubTpl(n18, i18)
    val c19 = castSubTpl(n19, i19)
    val c20 = castSubTpl(n20, i20)
    val c21 = castSubTpl(n21, i21)
    (row: Row) =>
      (
        c0(row),
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row),
        c8(row),
        c9(row),
        c10(row),
        c11(row),
        c12(row),
        c13(row),
        c14(row),
        c15(row),
        c16(row),
        c17(row),
        c18(row),
        c19(row),
        c20(row),
        c21(row)
        ).asInstanceOf[Tpl]
  }
}