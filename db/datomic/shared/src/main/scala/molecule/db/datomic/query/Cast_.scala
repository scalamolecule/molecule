// GENERATED CODE ********************************
package molecule.db.datomic.query

import molecule.core.query.Model2Query


trait Cast_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  final override lazy protected val row2tpl: Row => Tpl = {
    castScala.length match {
      case 1 => resolve1
      case 2 => resolve2
      case 3 => resolve3
      case 4 => resolve4
      case 5 => resolve5
      case 6 => resolve6
      case 7 => resolve7
      case 8 => resolve8
      case 9 => resolve9
      case 10 => resolve10
      case 11 => resolve11
      case 12 => resolve12
      case 13 => resolve13
      case 14 => resolve14
      case 15 => resolve15
      case 16 => resolve16
      case 17 => resolve17
      case 18 => resolve18
      case 19 => resolve19
      case 20 => resolve20
      case 21 => resolve21
      case 22 => resolve22
    }
  }

  final private def resolve1: Row => Tpl = {
    val cast0 = castScala(0)
    (row: Row) =>
      (
        cast0(row.get(0))
        ).asInstanceOf[Tpl]
  }

  final private def resolve2: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1))
        ).asInstanceOf[Tpl]
  }

  final private def resolve3: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2))
        ).asInstanceOf[Tpl]
  }

  final private def resolve4: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3))
        ).asInstanceOf[Tpl]
  }

  final private def resolve5: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4))
        ).asInstanceOf[Tpl]
  }

  final private def resolve6: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5))
        ).asInstanceOf[Tpl]
  }

  final private def resolve7: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6))
        ).asInstanceOf[Tpl]
  }

  final private def resolve8: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7))
        ).asInstanceOf[Tpl]
  }

  final private def resolve9: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8))
        ).asInstanceOf[Tpl]
  }

  final private def resolve10: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9))
        ).asInstanceOf[Tpl]
  }

  final private def resolve11: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10))
        ).asInstanceOf[Tpl]
  }

  final private def resolve12: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11))
        ).asInstanceOf[Tpl]
  }

  final private def resolve13: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12))
        ).asInstanceOf[Tpl]
  }

  final private def resolve14: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13))
        ).asInstanceOf[Tpl]
  }

  final private def resolve15: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14))
        ).asInstanceOf[Tpl]
  }

  final private def resolve16: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15))
        ).asInstanceOf[Tpl]
  }

  final private def resolve17: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16))
        ).asInstanceOf[Tpl]
  }

  final private def resolve18: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    val cast17 = castScala(17)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16)),
        cast17(row.get(17))
        ).asInstanceOf[Tpl]
  }

  final private def resolve19: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    val cast17 = castScala(17)
    val cast18 = castScala(18)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16)),
        cast17(row.get(17)),
        cast18(row.get(18))
        ).asInstanceOf[Tpl]
  }

  final private def resolve20: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    val cast17 = castScala(17)
    val cast18 = castScala(18)
    val cast19 = castScala(19)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16)),
        cast17(row.get(17)),
        cast18(row.get(18)),
        cast19(row.get(19))
        ).asInstanceOf[Tpl]
  }

  final private def resolve21: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    val cast17 = castScala(17)
    val cast18 = castScala(18)
    val cast19 = castScala(19)
    val cast20 = castScala(20)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16)),
        cast17(row.get(17)),
        cast18(row.get(18)),
        cast19(row.get(19)),
        cast20(row.get(20))
        ).asInstanceOf[Tpl]
  }

  final private def resolve22: Row => Tpl = {
    val cast0 = castScala(0)
    val cast1 = castScala(1)
    val cast2 = castScala(2)
    val cast3 = castScala(3)
    val cast4 = castScala(4)
    val cast5 = castScala(5)
    val cast6 = castScala(6)
    val cast7 = castScala(7)
    val cast8 = castScala(8)
    val cast9 = castScala(9)
    val cast10 = castScala(10)
    val cast11 = castScala(11)
    val cast12 = castScala(12)
    val cast13 = castScala(13)
    val cast14 = castScala(14)
    val cast15 = castScala(15)
    val cast16 = castScala(16)
    val cast17 = castScala(17)
    val cast18 = castScala(18)
    val cast19 = castScala(19)
    val cast20 = castScala(20)
    val cast21 = castScala(21)
    (row: Row) =>
      (
        cast0(row.get(0)),
        cast1(row.get(1)),
        cast2(row.get(2)),
        cast3(row.get(3)),
        cast4(row.get(4)),
        cast5(row.get(5)),
        cast6(row.get(6)),
        cast7(row.get(7)),
        cast8(row.get(8)),
        cast9(row.get(9)),
        cast10(row.get(10)),
        cast11(row.get(11)),
        cast12(row.get(12)),
        cast13(row.get(13)),
        cast14(row.get(14)),
        cast15(row.get(15)),
        cast16(row.get(16)),
        cast17(row.get(17)),
        cast18(row.get(18)),
        cast19(row.get(19)),
        cast20(row.get(20)),
        cast21(row.get(21))
        ).asInstanceOf[Tpl]
  }
}