// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import molecule.core.query.Model2Query
import molecule.datalog.core.query.DatomicQueryBase
import scala.annotation.tailrec


trait CastRow2Tpl_ { self: Model2Query with DatomicQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[AnyRef => AnyRef],
    attrIndex: AttrIndex,
    acc: List[Row => Any],
    nested: Option[NestedTpls]
  ): List[Row => Any] = {
    arities match {
      case List(1) :: as =>
        val cast = (row: Row) => casts.head(row.get(attrIndex))
        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast, nested)

      // Nested
      case List(-1) :: Nil =>
        val cast = (_: Row) => nested.get
        resolveArities(Nil, casts, 0, acc :+ cast, None)

      case _ => acc
    }
  }

  final protected def castRow2AnyTpl(
    arities: List[List[Int]],
    casts: List[AnyRef => AnyRef],
    attrIndex: AttrIndex,
    nested: Option[NestedTpls]
  ): Row => Any = {
    val casters = resolveArities(arities, casts, attrIndex, Nil, nested)
    arities.length match {
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

  final private def cast1(casters: List[Row => Any]): Row => Any = {
    val List(c1) = casters
    (row: Row) =>
      (
        c1(row)
        )
  }

  final private def cast2(casters: List[Row => Any]): Row => Any = {
    val List(c1, c2) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row)
      )
  }

  final private def cast3(casters: List[Row => Any]): Row => Any = {
    val List(c1, c2, c3) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row)
      )
  }

  final private def cast4(casters: List[Row => Any]): Row => Any = {
    val List(c1, c2, c3, c4) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row)
      )
  }

  final private def cast5(casters: List[Row => Any]): Row => Any = {
    val List(c1, c2, c3, c4, c5) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row)
      )
  }

  final private def cast6(casters: List[Row => Any]): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    (row: Row) =>
      (
        c1(row),
        c2(row),
        c3(row),
        c4(row),
        c5(row),
        c6(row)
      )
  }

  final private def cast7(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast8(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast9(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast10(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast11(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast12(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast13(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast14(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast15(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast16(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast17(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast18(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast19(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast20(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast21(casters: List[Row => Any]): Row => Any = {
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
      )
  }

  final private def cast22(casters: List[Row => Any]): Row => Any = {
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
      )
  }
}