// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import molecule.core.query.Model2Query
import molecule.datalog.core.query.DatomicQueryBase
import scala.annotation.tailrec


trait CastNestedBranch_
  extends CastRow2AnyTpl_ { self: Model2Query with DatomicQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[AnyRef => AnyRef],
    attrIndex: AttrIndex,
    attrIndexTx: AttrIndex,
    acc: List[(Row, NestedTpls) => Any]
  ): List[(Row, NestedTpls) => Any] = {
    arities match {
      case 0 :: as =>
        val cast = (row: Row, _: NestedTpls) => casts.head(row.get(attrIndex))
        resolveArities(as, casts.tail, attrIndex + 1, attrIndexTx, acc :+ cast)

      // Nested
      case -1 :: as =>
        val cast = (_: Row, nested: NestedTpls) => nested
        resolveArities(as, casts, attrIndexTx, attrIndexTx, acc :+ cast)

      case _ => acc
    }
  }

  final protected def castBranch[T](
    arities: List[Int],
    casts: List[AnyRef => AnyRef],
    firstAttrIndex: AttrIndex,
    firstAttrIndexTx: AttrIndex
  ): (Row, NestedTpls) => T = {
    val casters = resolveArities(arities, casts, firstAttrIndex, firstAttrIndexTx, Nil)
    casters.length match {
      case 0  => cast0[T]
      case 1  => cast1[T](casters)
      case 2  => cast2[T](casters)
      case 3  => cast3[T](casters)
      case 4  => cast4[T](casters)
      case 5  => cast5[T](casters)
      case 6  => cast6[T](casters)
      case 7  => cast7[T](casters)
      case 8  => cast8[T](casters)
      case 9  => cast9[T](casters)
      case 10 => cast10[T](casters)
      case 11 => cast11[T](casters)
      case 12 => cast12[T](casters)
      case 13 => cast13[T](casters)
      case 14 => cast14[T](casters)
      case 15 => cast15[T](casters)
      case 16 => cast16[T](casters)
      case 17 => cast17[T](casters)
      case 18 => cast18[T](casters)
      case 19 => cast19[T](casters)
      case 20 => cast20[T](casters)
      case 21 => cast21[T](casters)
    }
  }

  final private def cast0[T]: (Row, NestedTpls) => T = {
    (_: Row, nested: NestedTpls) => nested.asInstanceOf[T]
  }

  final private def cast1[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested)
        ).asInstanceOf[T]
  }

  final private def cast2[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast3[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast4[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast5[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast6[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast7[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast8[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast9[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast10[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast11[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast12[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast13[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast14[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast15[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast16[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast17[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested),
        c17(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast18[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested),
        c17(row, nested),
        c18(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast19[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested),
        c17(row, nested),
        c18(row, nested),
        c19(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast20[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested),
        c17(row, nested),
        c18(row, nested),
        c19(row, nested),
        c20(row, nested)
      ).asInstanceOf[T]
  }

  final private def cast21[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, nested),
        c2(row, nested),
        c3(row, nested),
        c4(row, nested),
        c5(row, nested),
        c6(row, nested),
        c7(row, nested),
        c8(row, nested),
        c9(row, nested),
        c10(row, nested),
        c11(row, nested),
        c12(row, nested),
        c13(row, nested),
        c14(row, nested),
        c15(row, nested),
        c16(row, nested),
        c17(row, nested),
        c18(row, nested),
        c19(row, nested),
        c20(row, nested),
        c21(row, nested)
      ).asInstanceOf[T]
  }
}