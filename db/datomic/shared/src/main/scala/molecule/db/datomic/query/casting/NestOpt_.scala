// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base
import scala.collection.mutable.ArrayBuffer


trait NestOpt_[Tpl] { self: Model2Query[Tpl] with Base[Tpl]
  with CastNestedOptBranch_[Tpl]
  with CastNestedOptLeaf_[Tpl]
  with CastNestedOptLeafFlatten_[Tpl] =>

  lazy val levels = pullCastss.length

  lazy val pullCasts1 = pullCastss.head
  lazy val pullCasts2 = pullCastss(1)
  lazy val pullCasts3 = pullCastss(2)
  lazy val pullCasts4 = pullCastss(3)
  lazy val pullCasts5 = pullCastss(4)
  lazy val pullCasts6 = pullCastss(5)
  lazy val pullCasts7 = pullCastss(6)

  lazy val pullBranch0: jIterator[_] => List[Any] = {
    (levels, flatten) match {
      case (1, true) => pullLeafFlatten(pullCasts1)
      case (1, _)    => pullLeaf(pullCasts1)
      case (_, true) => pullBranch(pullCasts1, pullBranch2)
      case (_, _)    => pullBranch(pullCasts1, pullBranch2)
    }
  }

  lazy val pullBranch1: jIterator[_] => List[Any] = {
    if (levels == 1)
      if (flatten) pullLeafFlatten(pullCasts1) else pullLeaf(pullCasts1)
    else
      if (flatten) pullBranch(pullCasts1, pullBranch2) else pullBranch(pullCasts1, pullBranch2)
  }

  lazy val pullBranch2: jIterator[_] => List[Any] = {
    if (levels == 2)
      pullLeaf(pullCasts2)
    else
      pullBranch(pullCasts2, pullBranch3)
  }
  lazy val pullBranch3: jIterator[_] => List[Any] = {
    if (levels == 3)
      pullLeaf(pullCasts3)
    else
      pullBranch(pullCasts3, pullBranch4)
  }
  lazy val pullBranch4: jIterator[_] => List[Any] = {
    if (levels == 4) pullLeaf(pullCasts4) else pullBranch(pullCasts4, pullBranch5)
  }
  lazy val pullBranch5: jIterator[_] => List[Any] = {
    if (levels == 5) pullLeaf(pullCasts5) else pullBranch(pullCasts5, pullBranch6)
  }
  lazy val pullBranch6: jIterator[_] => List[Any] = {
    if (levels == 6) pullLeaf(pullCasts6) else pullBranch(pullCasts6, pullBranch7)
  }
  lazy val pullBranch7: jIterator[_] => List[Any] = {
    pullLeaf(pullCasts7)
  }

  final protected lazy val pullRow2tpl: Row => Tpl = {
    casts.length match {
      case 0  => pullBranch0_0
      case 1  => pullBranch0_1(casts)
      case 2  => pullBranch0_2(casts)
      case 3  => pullBranch0_3(casts)
      case 4  => pullBranch0_4(casts)
      case 5  => pullBranch0_5(casts)
      case 6  => pullBranch0_6(casts)
      case 7  => pullBranch0_7(casts)
      case 8  => pullBranch0_8(casts)
      case 9  => pullBranch0_9(casts)
      case 10 => pullBranch0_10(casts)
      case 11 => pullBranch0_11(casts)
      case 12 => pullBranch0_12(casts)
      case 13 => pullBranch0_13(casts)
      case 14 => pullBranch0_14(casts)
      case 15 => pullBranch0_15(casts)
      case 16 => pullBranch0_16(casts)
      case 17 => pullBranch0_17(casts)
      case 18 => pullBranch0_18(casts)
      case 19 => pullBranch0_19(casts)
      case 20 => pullBranch0_20(casts)
      case 21 => pullBranch0_21(casts)
    }
  }

  final private def pullBranch0_0: Row => Tpl = {
    (row: Row) => pullBranch1(row.get(0).asInstanceOf[jMap[_, _]].values.iterator).asInstanceOf[Tpl]
  }

  final private def pullBranch0_1(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0 = casts(0)
    (row: Row) =>
      (
        c0(row.get(0)),
        pullBranch1(row.get(1).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_2(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        pullBranch1(row.get(2).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_3(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        pullBranch1(row.get(3).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_4(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0 = casts(0)
    val c1 = casts(1)
    val c2 = casts(2)
    val c3 = casts(3)
    (row: Row) =>
      (
        c0(row.get(0)),
        c1(row.get(1)),
        c2(row.get(2)),
        c3(row.get(3)),
        pullBranch1(row.get(4).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_5(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c4(row.get(4)),
        pullBranch1(row.get(5).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_6(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c5(row.get(5)),
        pullBranch1(row.get(6).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_7(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c6(row.get(6)),
        pullBranch1(row.get(7).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_8(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c7(row.get(7)),
        pullBranch1(row.get(8).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_9(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c8(row.get(8)),
        pullBranch1(row.get(9).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_10(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
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
        c9(row.get(9)),
        pullBranch1(row.get(10).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_11(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c10(row.get(10)),
        pullBranch1(row.get(11).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_12(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c11(row.get(11)),
        pullBranch1(row.get(12).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_13(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c12(row.get(12)),
        pullBranch1(row.get(13).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_14(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c13(row.get(13)),
        pullBranch1(row.get(14).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_15(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c14(row.get(14)),
        pullBranch1(row.get(15).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_16(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c15(row.get(15)),
        pullBranch1(row.get(16).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_17(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c16(row.get(16)),
        pullBranch1(row.get(17).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_18(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c17(row.get(17)),
        pullBranch1(row.get(18).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_19(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c18(row.get(18)),
        pullBranch1(row.get(19).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_20(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c19(row.get(19)),
        pullBranch1(row.get(20).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }

  final private def pullBranch0_21(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
    val c0  = casts(0)
    val c1  = casts(1)
    val c2  = casts(2)
    val c3  = casts(3)
    val c4  = casts(4)
    val c5  = casts(5)
    val c6  = casts(6)
    val c7  = casts(7)
    val c8  = casts(8)
    val c9  = casts(9)
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
        c20(row.get(20)),
        pullBranch1(row.get(21).asInstanceOf[jMap[_, _]].values.iterator)
        ).asInstanceOf[Tpl]
  }
}