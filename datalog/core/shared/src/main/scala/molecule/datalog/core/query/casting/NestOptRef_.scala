// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.datalog.core.query.DatomicQueryBase
import scala.annotation.tailrec


trait NestOptRef_[Tpl]
  extends CastOptRefBranch_
    with CastOptRefLeaf_
    with CastRow2Tpl_
    with DatomicQueryBase {

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

  private lazy val pullBranch1: jIterator[_] => Option[Any] = {
    if (levels == 1)
      pullOptRefLeaf(aritiess(1), pullCasts1)
    else
      pullOptRefBranch(
        aritiess(1),
        pullCasts1,
        pullSorts1,
        pullBranch2,
        refDepths(1)
      )
  }

  private lazy val pullBranch2: jIterator[_] => Option[Any] = {
    if (levels == 2)
      pullOptRefLeaf(aritiess(2), pullCasts2)
    else
      pullOptRefBranch(aritiess(2), pullCasts2, pullSorts2, pullBranch3, refDepths(2))
  }

  private lazy val pullBranch3: jIterator[_] => Option[Any] = {
    if (levels == 3)
      pullOptRefLeaf(aritiess(3), pullCasts3)
    else
      pullOptRefBranch(aritiess(3), pullCasts3, pullSorts3, pullBranch4, refDepths(3))
  }

  private lazy val pullBranch4: jIterator[_] => Option[Any] = {
    if (levels == 4)
      pullOptRefLeaf(aritiess(4), pullCasts4)
    else
      pullOptRefBranch(aritiess(4), pullCasts4, pullSorts4, pullBranch5, refDepths(4))
  }

  private lazy val pullBranch5: jIterator[_] => Option[Any] = {
    if (levels == 5)
      pullOptRefLeaf(aritiess(5), pullCasts5)
    else
      pullOptRefBranch(aritiess(5), pullCasts5, pullSorts5, pullBranch6, refDepths(5))
  }

  private lazy val pullBranch6: jIterator[_] => Option[Any] = {
    if (levels == 6)
      pullOptRefLeaf(aritiess(6), pullCasts6)
    else
      pullOptRefBranch(aritiess(6), pullCasts6, pullSorts6, pullBranch7, refDepths(6))
  }

  private lazy val pullBranch7: jIterator[_] => Option[Any] = {
    pullOptRefLeaf(aritiess(7), pullCasts7)
  }

  protected lazy val pullOptRefData: AnyRef => AnyRef = {
    (rowValue: AnyRef) => {

      println("=== " + rowValue)
      println("=== " + rowValue.asInstanceOf[jMap[_, _]].values)
      pullBranch1(rowValue.asInstanceOf[jMap[_, _]].values.iterator)
    }
  }

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[AnyRef => AnyRef],
    rowIndex: Int,
    rowIndexTx: Int,
    acc: List[Row => AnyRef]
  ): List[Row => AnyRef] = {
    arities match {
      case 0 :: as =>
        val cast = (row: Row) => casts.head(row.get(rowIndex))
        resolveArities(as, casts.tail, rowIndex + 1, rowIndexTx, acc :+ cast)

      // OptNested
      case -1 :: as =>
        val cast = (row: Row) => casts.head(row.get(rowIndex))
        resolveArities(as, casts.tail, rowIndexTx, rowIndexTx, acc :+ cast)

      case _ => acc
    }
  }

  final lazy val pullOptRefRow2tpl: Row => Tpl = {
    val arities    = aritiess.head
    val casts      = castss.head
    val rowIndexTx = arities.takeWhile(_ != -1).sum + 1
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
    println("C")
    (row: Row) =>{
      println("-----------")
      val v1 = c1(row)
      println("---  " + v1)
      val v2 = c2(row)
      println("---  " + v2)

      (
        v1, v2
      ).asInstanceOf[Tpl]
//      (
//        c1(row),
//        c2(row)
//      ).asInstanceOf[Tpl]
    }
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