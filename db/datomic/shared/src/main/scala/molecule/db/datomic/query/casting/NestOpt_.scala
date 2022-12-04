// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base
import scala.collection.mutable.ArrayBuffer


trait NestOpt_[Tpl] { self: Model2Query[Tpl] with Base[Tpl]
  with CastNestedOptBranch_[Tpl] with CastNestedOptLeaf_[Tpl] =>

  private lazy val levels = pullCastss.length

  private lazy val pullCasts1 = pullCastss.head
  private lazy val pullCasts2 = pullCastss(1)
  private lazy val pullCasts3 = pullCastss(2)
  private lazy val pullCasts4 = pullCastss(3)
  private lazy val pullCasts5 = pullCastss(4)
  private lazy val pullCasts6 = pullCastss(5)
  private lazy val pullCasts7 = pullCastss(6)

  private lazy val pullBranch1: jIterator[_] => List[Any] = {
    if (levels == 1) pullLeaf(pullCasts1) else pullBranch(pullCasts1, pullBranch2)
  }
  private lazy val pullBranch2: jIterator[_] => List[Any] = {
    if (levels == 2) pullLeaf(pullCasts2) else pullBranch(pullCasts2, pullBranch3)
  }
  private lazy val pullBranch3: jIterator[_] => List[Any] = {
    if (levels == 3) pullLeaf(pullCasts3) else pullBranch(pullCasts3, pullBranch4)
  }
  private lazy val pullBranch4: jIterator[_] => List[Any] = {
    if (levels == 4) pullLeaf(pullCasts4) else pullBranch(pullCasts4, pullBranch5)
  }
  private lazy val pullBranch5: jIterator[_] => List[Any] = {
    if (levels == 5) pullLeaf(pullCasts5) else pullBranch(pullCasts5, pullBranch6)
  }
  private lazy val pullBranch6: jIterator[_] => List[Any] = {
    if (levels == 6) pullLeaf(pullCasts6) else pullBranch(pullCasts6, pullBranch7)
  }
  private lazy val pullBranch7: jIterator[_] => List[Any] = {
    pullLeaf(pullCasts7)
  }

  final protected lazy val pullRow2tpl: Row => Tpl = {
    val casters = castss.last
    casters.length match {
      case 0 => pullBranch0_0
      case 1 => pullBranch0_1(casters)
      case 2 => pullBranch0_2(casters)
      case 3 => pullBranch0_3(casters)
      case 4 => pullBranch0_4(casters)
      case 5 => pullBranch0_5(casters)
      case 6 => pullBranch0_6(casters)
      case 7 => pullBranch0_7(casters)
      case 8 => pullBranch0_8(casters)
      case 9 => pullBranch0_9(casters)
      case 10 => pullBranch0_10(casters)
      case 11 => pullBranch0_11(casters)
      case 12 => pullBranch0_12(casters)
      case 13 => pullBranch0_13(casters)
      case 14 => pullBranch0_14(casters)
      case 15 => pullBranch0_15(casters)
      case 16 => pullBranch0_16(casters)
      case 17 => pullBranch0_17(casters)
      case 18 => pullBranch0_18(casters)
      case 19 => pullBranch0_19(casters)
      case 20 => pullBranch0_20(casters)
      case 21 => pullBranch0_21(casters)
    }
  }

  final private def pullBranch0_0: Row => Tpl = {
    (row: Row) => pullBranch1(row.get(0).asInstanceOf[jMap[_, _]].values.iterator).asInstanceOf[Tpl]
  }

  final private def pullBranch0_1(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        pullBranch1(row.get(1).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_2(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        pullBranch1(row.get(2).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_3(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        pullBranch1(row.get(3).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_4(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        pullBranch1(row.get(4).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_5(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        pullBranch1(row.get(5).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_6(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        pullBranch1(row.get(6).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_7(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        pullBranch1(row.get(7).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_8(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        pullBranch1(row.get(8).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_9(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        pullBranch1(row.get(9).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_10(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        pullBranch1(row.get(10).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_11(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        pullBranch1(row.get(11).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_12(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        pullBranch1(row.get(12).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_13(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        pullBranch1(row.get(13).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_14(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        pullBranch1(row.get(14).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_15(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        pullBranch1(row.get(15).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_16(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        pullBranch1(row.get(16).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_17(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        c17(row.get(16)),
        pullBranch1(row.get(17).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_18(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        c17(row.get(16)),
        c18(row.get(17)),
        pullBranch1(row.get(18).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_19(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        c17(row.get(16)),
        c18(row.get(17)),
        c19(row.get(18)),
        pullBranch1(row.get(19).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_20(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        c17(row.get(16)),
        c18(row.get(17)),
        c19(row.get(18)),
        c20(row.get(19)),
        pullBranch1(row.get(20).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_21(casters: List[AnyRef => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    (row: Row) =>
      (
        c1(row.get(0)),
        c2(row.get(1)),
        c3(row.get(2)),
        c4(row.get(3)),
        c5(row.get(4)),
        c6(row.get(5)),
        c7(row.get(6)),
        c8(row.get(7)),
        c9(row.get(8)),
        c10(row.get(9)),
        c11(row.get(10)),
        c12(row.get(11)),
        c13(row.get(12)),
        c14(row.get(13)),
        c15(row.get(14)),
        c16(row.get(15)),
        c17(row.get(16)),
        c18(row.get(17)),
        c19(row.get(18)),
        c20(row.get(19)),
        c21(row.get(20)),
        pullBranch1(row.get(21).asInstanceOf[jMap[_, _]].values.iterator)
      ).asInstanceOf[Tpl]
  }
}