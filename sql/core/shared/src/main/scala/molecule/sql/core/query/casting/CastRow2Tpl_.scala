// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.core.query.Model2Query
import molecule.sql.core.query.SqlQueryBase
import scala.annotation.tailrec


trait CastRow2Tpl_ { self: Model2Query with SqlQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[(Row, AttrIndex) => Any],
    attrIndex: AttrIndex,
    acc: List[(Row, AttrIndex) => Any],
    nested: Option[NestedTpls]
  ): List[(Row, AttrIndex) => Any] = {
    arities match {
      case List(1) :: as =>
        val cast = (row: Row, _: AttrIndex) => casts.head(row, attrIndex)
        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast, nested)

      // Nested
      case List(-1) :: Nil =>
        val cast = (_: Row, _: AttrIndex) => nested.getOrElse(List.empty[Any])
        resolveArities(Nil, casts, 0, acc :+ cast, None)


      case _ => acc
    }
  }

  final  def castRow2AnyTpl(
    arities: List[List[Int]],
    casts: List[(Row, AttrIndex) => Any],
    attrIndex: AttrIndex,
    nested: Option[NestedTpls]
  ): Row => Any = {
    val casters: List[(Row, AttrIndex) => Any] = resolveArities(arities, casts, attrIndex, Nil, nested)
    arities.length match {
      case 1  => cast1(casters, attrIndex)
      case 2  => cast2(casters, attrIndex)
      case 3  => cast3(casters, attrIndex)
      case 4  => cast4(casters, attrIndex)
      case 5  => cast5(casters, attrIndex)
      case 6  => cast6(casters, attrIndex)
      case 7  => cast7(casters, attrIndex)
      case 8  => cast8(casters, attrIndex)
      case 9  => cast9(casters, attrIndex)
      case 10 => cast10(casters, attrIndex)
      case 11 => cast11(casters, attrIndex)
      case 12 => cast12(casters, attrIndex)
      case 13 => cast13(casters, attrIndex)
      case 14 => cast14(casters, attrIndex)
      case 15 => cast15(casters, attrIndex)
      case 16 => cast16(casters, attrIndex)
      case 17 => cast17(casters, attrIndex)
      case 18 => cast18(casters, attrIndex)
      case 19 => cast19(casters, attrIndex)
      case 20 => cast20(casters, attrIndex)
      case 21 => cast21(casters, attrIndex)
      case 22 => cast22(casters, attrIndex)
    }
  }

  final private def cast1(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1) = casters
    (row: Row) =>
      c1(row, attrIndex)
  }

  final private def cast2(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2) = casters
    val List(a1, a2) = (attrIndex until attrIndex + 2).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2)
      )
  }

  final private def cast3(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3) = casters
    val List(a1, a2, a3) = (attrIndex until attrIndex + 3).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3)
      )
  }

  final private def cast4(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4) = casters
    val List(a1, a2, a3, a4) = (attrIndex until attrIndex + 4).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4)
      )
  }

  final private def cast5(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5) = casters
    val List(a1, a2, a3, a4, a5) = (attrIndex until attrIndex + 5).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5)
      )
  }

  final private def cast6(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    val List(a1, a2, a3, a4, a5, a6) = (attrIndex until attrIndex + 6).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6)
      )
  }

  final private def cast7(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    val List(a1, a2, a3, a4, a5, a6, a7) = (attrIndex until attrIndex + 7).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7)
      )
  }

  final private def cast8(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8) = (attrIndex until attrIndex + 8).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8)
      )
  }

  final private def cast9(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9) = (attrIndex until attrIndex + 9).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9)
      )
  }

  final private def cast10(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) = (attrIndex until attrIndex + 10).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10)
      )
  }

  final private def cast11(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) = (attrIndex until attrIndex + 11).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11)
      )
  }

  final private def cast12(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) = (attrIndex until attrIndex + 12).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12)
      )
  }

  final private def cast13(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) = (attrIndex until attrIndex + 13).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13)
      )
  }

  final private def cast14(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) = (attrIndex until attrIndex + 14).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14)
      )
  }

  final private def cast15(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) = (attrIndex until attrIndex + 15).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15)
      )
  }

  final private def cast16(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16) = (attrIndex until attrIndex + 16).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16)
      )
  }

  final private def cast17(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17) = (attrIndex until attrIndex + 17).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17)
      )
  }

  final private def cast18(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18) = (attrIndex until attrIndex + 18).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17),
        c18(row, a18)
      )
  }

  final private def cast19(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19) = (attrIndex until attrIndex + 19).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17),
        c18(row, a18),
        c19(row, a19)
      )
  }

  final private def cast20(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20) = (attrIndex until attrIndex + 20).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17),
        c18(row, a18),
        c19(row, a19),
        c20(row, a20)
      )
  }

  final private def cast21(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21) = (attrIndex until attrIndex + 21).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17),
        c18(row, a18),
        c19(row, a19),
        c20(row, a20),
        c21(row, a21)
      )
  }

  final private def cast22(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22) = (attrIndex until attrIndex + 22).toList
    (row: Row) =>
      (
        c1(row, a1),
        c2(row, a2),
        c3(row, a3),
        c4(row, a4),
        c5(row, a5),
        c6(row, a6),
        c7(row, a7),
        c8(row, a8),
        c9(row, a9),
        c10(row, a10),
        c11(row, a11),
        c12(row, a12),
        c13(row, a13),
        c14(row, a14),
        c15(row, a15),
        c16(row, a16),
        c17(row, a17),
        c18(row, a18),
        c19(row, a19),
        c20(row, a20),
        c21(row, a21),
        c22(row, a22)
      )
  }
}