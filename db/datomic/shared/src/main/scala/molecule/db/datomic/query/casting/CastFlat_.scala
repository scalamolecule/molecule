// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastFlat_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  override lazy protected val row2tpl: Row => Tpl = {
    casts.length match {
      case 1 => cast1
      case 2 => cast2
      case 3 => cast3
      case 4 => cast4
      case 5 => cast5
      case 6 => cast6
      case 7 => cast7
      case 8 => cast8
      case 9 => cast9
      case 10 => cast10
      case 11 => cast11
      case 12 => cast12
      case 13 => cast13
      case 14 => cast14
      case 15 => cast15
      case 16 => cast16
      case 17 => cast17
      case 18 => cast18
      case 19 => cast19
      case 20 => cast20
      case 21 => cast21
      case 22 => cast22
    }
  }

  final private def cast1: Row => Tpl = {
    val c0 = casts(0)
    (row: Row) =>
      (
        c0(row.get(0))
        ).asInstanceOf[Tpl]
  }

  final private def cast2: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1))
        ).asInstanceOf[Tpl]
  }

  final private def cast3: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2))
        ).asInstanceOf[Tpl]
  }

  final private def cast4: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3))
        ).asInstanceOf[Tpl]
  }

  final private def cast5: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4))
        ).asInstanceOf[Tpl]
  }

  final private def cast6: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5))
        ).asInstanceOf[Tpl]
  }

  final private def cast7: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6))
        ).asInstanceOf[Tpl]
  }

  final private def cast8: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7))
        ).asInstanceOf[Tpl]
  }

  final private def cast9: Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    val c4 = casts(4)
    val c5 = casts(5)
    val c6 = casts(6)
    val c7 = casts(7)
    val c8 = casts(8)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8))
        ).asInstanceOf[Tpl]
  }

  final private def cast10: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9))
        ).asInstanceOf[Tpl]
  }

  final private def cast11: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10))
        ).asInstanceOf[Tpl]
  }

  final private def cast12: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11))
        ).asInstanceOf[Tpl]
  }

  final private def cast13: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12))
        ).asInstanceOf[Tpl]
  }

  final private def cast14: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13))
        ).asInstanceOf[Tpl]
  }

  final private def cast15: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14))
        ).asInstanceOf[Tpl]
  }

  final private def cast16: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15))
        ).asInstanceOf[Tpl]
  }

  final private def cast17: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16))
        ).asInstanceOf[Tpl]
  }

  final private def cast18: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16)),
        c17(row.get(17))
        ).asInstanceOf[Tpl]
  }

  final private def cast19: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16)),
        c17(row.get(17)),
        c18(row.get(18))
        ).asInstanceOf[Tpl]
  }

  final private def cast20: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16)),
        c17(row.get(17)),
        c18(row.get(18)),
        c19(row.get(19))
        ).asInstanceOf[Tpl]
  }

  final private def cast21: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16)),
        c17(row.get(17)),
        c18(row.get(18)),
        c19(row.get(19)),
        c20(row.get(20))
        ).asInstanceOf[Tpl]
  }

  final private def cast22: Row => Tpl = {
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
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        c4(row.get(4)),
        c5(row.get(5)),
        c6(row.get(6)),
        c7(row.get(7)),
        c8(row.get(8)),
        c9(row.get(9)),
        c10(row.get(10)),
        c11(row.get(11)),
        c12(row.get(12)),
        c13(row.get(13)),
        c14(row.get(14)),
        c15(row.get(15)),
        c16(row.get(16)),
        c17(row.get(17)),
        c18(row.get(18)),
        c19(row.get(19)),
        c20(row.get(20)),
        c21(row.get(21))
        ).asInstanceOf[Tpl]
  }
}