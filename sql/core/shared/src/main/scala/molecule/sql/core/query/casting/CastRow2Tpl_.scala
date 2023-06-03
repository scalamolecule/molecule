// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.core.query.Model2Query
import molecule.sql.core.query.Base
import scala.annotation.tailrec


trait CastRow2Tpl_ { self: Model2Query with Base =>

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[(Row, Int) => AnyRef],
    attrIndex: Int,
    acc: List[(Row, Int) => Any],
    nested: Option[List[Any]]
  ): List[(Row, Int) => Any] = {
    arities match {
      case List(1) :: as =>
        resolveArities(as, casts.tail, attrIndex + 1, acc :+ casts.head, nested)

      // Nested
      case List(-1) :: Nil =>
        val cast = (_: Row, _: Int) => nested.get
        resolveArities(Nil, casts, 0, acc :+ cast, None)

      // Composite
      case ii :: as =>
        val n                          = ii.length
        val (tplCasts, moreCasts)      = casts.splitAt(n)
        //        val cast: (Row, Int) => AnyRef = castRow2AnyTpl(ii.map(List(_)), tplCasts, attrIndex, nested)
        val cast: (Row, Int) => AnyRef = ???
        resolveArities(as, moreCasts, attrIndex + n, acc :+ cast, nested)

      case Nil => acc
      //      case _ => acc
    }
  }

  final protected def castRow2AnyTpl(
    arities: List[List[Int]],
    casts: List[(Row, Int) => AnyRef],
    attrIndex: Int,
    nested: Option[List[Any]]
  ): Row => Any = {
    val casters: List[(Row, Int) => Any] = resolveArities(arities, casts, attrIndex, Nil, nested)
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
    (row: Row) => c1(row, attrIndex)
  }

  final private def cast2(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2) = casters
    val List(n1, n2) = (attrIndex until attrIndex + 2).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2)
      )
  }

  final private def cast3(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3) = casters
    val List(n1, n2, n3) = (attrIndex until attrIndex + 3).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3)
      )
  }

  final private def cast4(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4) = casters
    val List(n1, n2, n3, n4) = (attrIndex until attrIndex + 4).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4)
      )
  }

  final private def cast5(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5) = casters
    val List(n1, n2, n3, n4, n5) = (attrIndex until attrIndex + 5).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5)
      )
  }

  final private def cast6(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    val List(n1, n2, n3, n4, n5, n6) = (attrIndex until attrIndex + 6).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6)
      )
  }

  final private def cast7(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    val List(n1, n2, n3, n4, n5, n6, n7) = (attrIndex until attrIndex + 7).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7)
      )
  }

  final private def cast8(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8) = (attrIndex until attrIndex + 8).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8)
      )
  }

  final private def cast9(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9) = (attrIndex until attrIndex + 9).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9)
      )
  }

  final private def cast10(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10) = (attrIndex until attrIndex + 10).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10)
      )
  }

  final private def cast11(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11) = (attrIndex until attrIndex + 11).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11)
      )
  }

  final private def cast12(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12) = (attrIndex until attrIndex + 12).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12)
      )
  }

  final private def cast13(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13) = (attrIndex until attrIndex + 13).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13)
      )
  }

  final private def cast14(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14) = (attrIndex until attrIndex + 14).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14)
      )
  }

  final private def cast15(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15) = (attrIndex until attrIndex + 15).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15)
      )
  }

  final private def cast16(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16) = (attrIndex until attrIndex + 16).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16)
      )
  }

  final private def cast17(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17) = (attrIndex until attrIndex + 17).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17)
      )
  }

  final private def cast18(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18) = (attrIndex until attrIndex + 18).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17),
        c18(row, n18)
      )
  }

  final private def cast19(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19) = (attrIndex until attrIndex + 19).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17),
        c18(row, n18),
        c19(row, n19)
      )
  }

  final private def cast20(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20) = (attrIndex until attrIndex + 20).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17),
        c18(row, n18),
        c19(row, n19),
        c20(row, n20)
      )
  }

  final private def cast21(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21) = (attrIndex until attrIndex + 21).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17),
        c18(row, n18),
        c19(row, n19),
        c20(row, n20),
        c21(row, n21)
      )
  }

  final private def cast22(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casters
    val List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22) = (attrIndex until attrIndex + 22).toList
    (row: Row) =>
      (
        c1(row, n1),
        c2(row, n2),
        c3(row, n3),
        c4(row, n4),
        c5(row, n5),
        c6(row, n6),
        c7(row, n7),
        c8(row, n8),
        c9(row, n9),
        c10(row, n10),
        c11(row, n11),
        c12(row, n12),
        c13(row, n13),
        c14(row, n14),
        c15(row, n15),
        c16(row, n16),
        c17(row, n17),
        c18(row, n18),
        c19(row, n19),
        c20(row, n20),
        c21(row, n21),
        c22(row, n22)
      )
  }
}