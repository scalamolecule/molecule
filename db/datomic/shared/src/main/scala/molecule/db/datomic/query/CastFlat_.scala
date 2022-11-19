// GENERATED CODE ********************************
package molecule.db.datomic.query

import molecule.core.query.Model2Query


trait CastFlat_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  final override lazy protected val row2tpl: Row => Tpl = {
    castScala.length match {
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
    val c0 = castScala(0)
    (row: Row) =>
      (
        c0(row.get(0))
        ).asInstanceOf[Tpl]
  }

  final private def cast2: Row => Tpl = {
    val c0 = castScala(0)
    val c1 = castScala(1)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1))
        ).asInstanceOf[Tpl]
  }

  final private def cast3: Row => Tpl = {
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2))
        ).asInstanceOf[Tpl]
  }

  final private def cast4: Row => Tpl = {
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3))
        ).asInstanceOf[Tpl]
  }

  final private def cast5: Row => Tpl = {
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
    val c17 = castScala(17)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
    val c17 = castScala(17)
    val c18 = castScala(18)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
    val c17 = castScala(17)
    val c18 = castScala(18)
    val c19 = castScala(19)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
    val c17 = castScala(17)
    val c18 = castScala(18)
    val c19 = castScala(19)
    val c20 = castScala(20)
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
    val c0 = castScala(0)
    val c1 = castScala(1)
    val c2 = castScala(2)
    val c3 = castScala(3)
    val c4 = castScala(4)
    val c5 = castScala(5)
    val c6 = castScala(6)
    val c7 = castScala(7)
    val c8 = castScala(8)
    val c9 = castScala(9)
    val c10 = castScala(10)
    val c11 = castScala(11)
    val c12 = castScala(12)
    val c13 = castScala(13)
    val c14 = castScala(14)
    val c15 = castScala(15)
    val c16 = castScala(16)
    val c17 = castScala(17)
    val c18 = castScala(18)
    val c19 = castScala(19)
    val c20 = castScala(20)
    val c21 = castScala(21)
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