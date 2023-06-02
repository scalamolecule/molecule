// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.core.query.Model2Query
import molecule.datalog.core.query.Base
import scala.annotation.tailrec


trait NestOpt_[Tpl]
  extends CastNestedOptBranch_
    with CastNestedOptLeaf_
    with CastRow2Tpl_
    with Base
    with Model2Query {

  private lazy val levels = pullCastss.length

  private lazy val pullCasts1 = pullCastss.head
  private lazy val pullCasts2 = pullCastss(1)
  private lazy val pullCasts3 = pullCastss(2)
  private lazy val pullCasts4 = pullCastss(3)
  private lazy val pullCasts5 = pullCastss(4)
  private lazy val pullCasts6 = pullCastss(5)
  private lazy val pullCasts7 = pullCastss(6)

  private lazy val pullSorts1 = pullSortss.head
  private lazy val pullSorts2 = pullSortss(1)
  private lazy val pullSorts3 = pullSortss(2)
  private lazy val pullSorts4 = pullSortss(3)
  private lazy val pullSorts5 = pullSortss(4)
  private lazy val pullSorts6 = pullSortss(5)
  private lazy val pullSorts7 = pullSortss(6)

  private lazy val pullBranch1: jIterator[_] => List[Any] = {
    if (levels == 1)
      pullLeaf(aritiess(1), pullCasts1, pullSorts1)
    else
      pullBranch(aritiess(1), pullCasts1, pullSorts1, pullBranch2, refDepths(1))
  }

  private lazy val pullBranch2: jIterator[_] => List[Any] = {
    if (levels == 2)
      pullLeaf(aritiess(2), pullCasts2, pullSorts2)
    else
      pullBranch(aritiess(2), pullCasts2, pullSorts2, pullBranch3, refDepths(2))
  }

  private lazy val pullBranch3: jIterator[_] => List[Any] = {
    if (levels == 3)
      pullLeaf(aritiess(3), pullCasts3, pullSorts3)
    else
      pullBranch(aritiess(3), pullCasts3, pullSorts3, pullBranch4, refDepths(3))
  }

  private lazy val pullBranch4: jIterator[_] => List[Any] = {
    if (levels == 4)
      pullLeaf(aritiess(4), pullCasts4, pullSorts4)
    else
      pullBranch(aritiess(4), pullCasts4, pullSorts4, pullBranch5, refDepths(4))
  }

  private lazy val pullBranch5: jIterator[_] => List[Any] = {
    if (levels == 5)
      pullLeaf(aritiess(5), pullCasts5, pullSorts5)
    else
      pullBranch(aritiess(5), pullCasts5, pullSorts5, pullBranch6, refDepths(5))
  }

  private lazy val pullBranch6: jIterator[_] => List[Any] = {
    if (levels == 6)
      pullLeaf(aritiess(6), pullCasts6, pullSorts6)
    else
      pullBranch(aritiess(6), pullCasts6, pullSorts6, pullBranch7, refDepths(6))
  }

  private lazy val pullBranch7: jIterator[_] => List[Any] = {
    pullLeaf(aritiess(7), pullCasts7, pullSorts7)
  }

  protected lazy val pullNestedData: AnyRef => AnyRef = {
    (rowValue: AnyRef) => pullBranch1(rowValue.asInstanceOf[jMap[_, _]].values.iterator)
  }

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[AnyRef => AnyRef],
    rowIndex: Int,
    rowIndexTx: Int,
    acc: List[Row => AnyRef]
  ): List[Row => AnyRef] = {
    arities match {
      case List(1) :: as =>
        val cast = (row: Row) => casts.head(row.get(rowIndex))
        resolveArities(as, casts.tail, rowIndex + 1, rowIndexTx, acc :+ cast)

      // NestedOpt
      case List(-1) :: as =>
        val cast = (row: Row) => casts.head(row.get(rowIndex))
        resolveArities(as, casts.tail, rowIndexTx, rowIndexTx, acc :+ cast)

      // Composite with only tacit attributes
      case ii :: as if ii.isEmpty =>
        resolveArities(as, casts, rowIndex, rowIndexTx, acc)

      // Composite branch
      case ii :: as if ii.last == -1 =>
        val n                      = ii.length - 1
        val (tplCasts, moreCasts0) = casts.splitAt(n)

        // Explicitly pull branch in composite tuple
        val nested = (row: Row) => pullBranch1(row.get(rowIndex + n).asInstanceOf[jMap[_, _]].values.iterator)
        val cast   = (row: Row) =>
          castRow2AnyTpl(ii.map(List(_)), tplCasts, rowIndex, Some(nested(row)))(row).asInstanceOf[AnyRef]

        // From here on it is tx data
        val moreCasts = moreCasts0.tail // ignore
        resolveArities(as, moreCasts, rowIndexTx, 0, acc :+ cast)

      // Top level composite (can be before nested and after in tx data)
      case ii :: as =>
        val n                     = ii.length
        val (tplCasts, moreCasts) = casts.splitAt(n)
        val tplCaster             = castRow2AnyTpl(ii.map(List(_)), tplCasts, rowIndex, None)
        val cast                  = (row: Row) => tplCaster(row).asInstanceOf[AnyRef]
        resolveArities(as, moreCasts, rowIndex + n, rowIndexTx, acc :+ cast)

      case Nil => acc
    }
  }

  final protected lazy val pullRow2tpl: Row => Tpl = {
    val arities    = aritiess.head
    val casts      = castss.head
    val rowIndexTx = arities.flatten.takeWhile(_ != -1).sum + 1
    val casters    = resolveArities(arities, casts, 0, rowIndexTx, Nil)
    casters.length match {
      case 1  => cast1(casters)
      case 2  => cast2(casters)
      case 3  => cast3(casters)
      case 4  => cast4(casters)
      case 5  => cast5(casters)
      case 6  => cast6(casters)
      case 7  => cast7(casters)
      case 8  => cast8(casters)
      case 9  => cast9(casters)
      case 10 => cast10(casters)
      case 11 => cast11(casters)
      case 12 => cast12(casters)
      case 13 => cast13(casters)
      case 14 => cast14(casters)
      case 15 => cast15(casters)
      case 16 => cast16(casters)
      case 17 => cast17(casters)
      case 18 => cast18(casters)
      case 19 => cast19(casters)
      case 20 => cast20(casters)
      case 21 => cast21(casters)
      case 22 => cast22(casters)
    }
  }

  final private def cast1(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1) = casters
    (row: Row) =>
      (
        c1(row)
        ).asInstanceOf[Tpl]
  }

  final private def cast2(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast3(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast4(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast5(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast6(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast7(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row),
        c7(row)
      ).asInstanceOf[Tpl]
  }

  final private def cast8(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    (row: Row) =>
      (
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

  final private def cast9(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    (row: Row) =>
      (
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

  final private def cast10(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    (row: Row) =>
      (
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

  final private def cast11(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    (row: Row) =>
      (
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

  final private def cast12(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    (row: Row) =>
      (
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

  final private def cast13(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    (row: Row) =>
      (
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

  final private def cast14(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    (row: Row) =>
      (
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

  final private def cast15(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    (row: Row) =>
      (
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

  final private def cast16(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    (row: Row) =>
      (
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

  final private def cast17(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    (row: Row) =>
      (
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

  final private def cast18(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    (row: Row) =>
      (
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

  final private def cast19(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    (row: Row) =>
      (
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

  final private def cast20(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    (row: Row) =>
      (
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

  final private def cast21(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    (row: Row) =>
      (
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

  final private def cast22(casters: List[Row => AnyRef]): Row => Tpl = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casters
    (row: Row) =>
      (
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
        c21(row),
        c22(row)
      ).asInstanceOf[Tpl]
  }
}