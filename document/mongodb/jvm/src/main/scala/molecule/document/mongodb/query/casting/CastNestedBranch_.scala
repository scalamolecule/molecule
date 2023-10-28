// GENERATED CODE ********************************
package molecule.document.mongodb.query.casting

import molecule.core.query.Model2QueryBase
import molecule.document.mongodb.query.MongoQueryBase
import scala.annotation.tailrec


trait CastNestedBranch_ extends CastRow2Tpl_ { self: Model2QueryBase with MongoQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[(Row, ParamIndex) => Any],
    attrIndex: ParamIndex,
    attrIndexTx: ParamIndex,
    acc: List[(Row, ParamIndex, NestedTpls) => Any]
  ): List[(Row, ParamIndex, NestedTpls) => Any] = {
    arities match {
      case List(1) :: as =>
        val cast = (row: Row, attrIndex1: ParamIndex, _: NestedTpls) => casts.head(row, attrIndex1)
        resolveArities(as, casts.tail, attrIndex + 1, attrIndexTx, acc :+ cast)

      // Nested
      case List(-1) :: as =>
        val cast = (_: Row, _: ParamIndex, nested: NestedTpls) => nested
        resolveArities(as, casts, attrIndexTx, attrIndexTx, acc :+ cast)

      case _ => acc
    }
  }

  final protected def castBranch[T](
    arities: List[List[Int]],
    casts: List[(Row, ParamIndex) => Any],
    firstAttrIndex: ParamIndex,
    firstAttrIndexTx: ParamIndex
  ): (Row, NestedTpls) => T = {
    val casters = resolveArities(arities, casts, firstAttrIndex, firstAttrIndexTx, Nil)
    casters.length match {
      case 0  => cast0[T]
      case 1  => cast1[T](casters, firstAttrIndex)
      case 2  => cast2[T](casters, firstAttrIndex)
      case 3  => cast3[T](casters, firstAttrIndex)
      case 4  => cast4[T](casters, firstAttrIndex)
      case 5  => cast5[T](casters, firstAttrIndex)
      case 6  => cast6[T](casters, firstAttrIndex)
      case 7  => cast7[T](casters, firstAttrIndex)
      case 8  => cast8[T](casters, firstAttrIndex)
      case 9  => cast9[T](casters, firstAttrIndex)
      case 10 => cast10[T](casters, firstAttrIndex)
      case 11 => cast11[T](casters, firstAttrIndex)
      case 12 => cast12[T](casters, firstAttrIndex)
      case 13 => cast13[T](casters, firstAttrIndex)
      case 14 => cast14[T](casters, firstAttrIndex)
      case 15 => cast15[T](casters, firstAttrIndex)
      case 16 => cast16[T](casters, firstAttrIndex)
      case 17 => cast17[T](casters, firstAttrIndex)
      case 18 => cast18[T](casters, firstAttrIndex)
      case 19 => cast19[T](casters, firstAttrIndex)
      case 20 => cast20[T](casters, firstAttrIndex)
      case 21 => cast21[T](casters, firstAttrIndex)
    }
  }

  final private def cast0[T]: (Row, NestedTpls) => T = {
    (_: Row, nested: NestedTpls) =>
      nested.asInstanceOf[T]
  }

  final private def cast1[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, NestedTpls) => T = {
    val List(c1) = casters
    (row: Row, nested: NestedTpls) =>
      (
        c1(row, firstAttrIndex, nested)
        ).asInstanceOf[T]
  }

  final private def cast2[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2) = casters
    val List(a1, a2) = (firstAttrIndex until firstAttrIndex + 2).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested)
      ).asInstanceOf[T]
  }

  final private def cast3[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3) = casters
    val List(a1, a2, a3) = (firstAttrIndex until firstAttrIndex + 3).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested)
      ).asInstanceOf[T]
  }

  final private def cast4[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4) = casters
    val List(a1, a2, a3, a4) = (firstAttrIndex until firstAttrIndex + 4).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested)
      ).asInstanceOf[T]
  }

  final private def cast5[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5) = casters
    val List(a1, a2, a3, a4, a5) = (firstAttrIndex until firstAttrIndex + 5).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested)
      ).asInstanceOf[T]
  }

  final private def cast6[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    val List(a1, a2, a3, a4, a5, a6) = (firstAttrIndex until firstAttrIndex + 6).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested)
      ).asInstanceOf[T]
  }

  final private def cast7[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    val List(a1, a2, a3, a4, a5, a6, a7) = (firstAttrIndex until firstAttrIndex + 7).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested)
      ).asInstanceOf[T]
  }

  final private def cast8[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8) = (firstAttrIndex until firstAttrIndex + 8).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested)
      ).asInstanceOf[T]
  }

  final private def cast9[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9) = (firstAttrIndex until firstAttrIndex + 9).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested)
      ).asInstanceOf[T]
  }

  final private def cast10[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) = (firstAttrIndex until firstAttrIndex + 10).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested)
      ).asInstanceOf[T]
  }

  final private def cast11[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) = (firstAttrIndex until firstAttrIndex + 11).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested)
      ).asInstanceOf[T]
  }

  final private def cast12[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) = (firstAttrIndex until firstAttrIndex + 12).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested)
      ).asInstanceOf[T]
  }

  final private def cast13[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) = (firstAttrIndex until firstAttrIndex + 13).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested)
      ).asInstanceOf[T]
  }

  final private def cast14[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) = (firstAttrIndex until firstAttrIndex + 14).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested)
      ).asInstanceOf[T]
  }

  final private def cast15[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) = (firstAttrIndex until firstAttrIndex + 15).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested)
      ).asInstanceOf[T]
  }

  final private def cast16[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16) = (firstAttrIndex until firstAttrIndex + 16).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested)
      ).asInstanceOf[T]
  }

  final private def cast17[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17) = (firstAttrIndex until firstAttrIndex + 17).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested),
        c17(row, a17, nested)
      ).asInstanceOf[T]
  }

  final private def cast18[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18) = (firstAttrIndex until firstAttrIndex + 18).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested),
        c17(row, a17, nested),
        c18(row, a18, nested)
      ).asInstanceOf[T]
  }

  final private def cast19[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19) = (firstAttrIndex until firstAttrIndex + 19).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested),
        c17(row, a17, nested),
        c18(row, a18, nested),
        c19(row, a19, nested)
      ).asInstanceOf[T]
  }

  final private def cast20[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20) = (firstAttrIndex until firstAttrIndex + 20).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested),
        c17(row, a17, nested),
        c18(row, a18, nested),
        c19(row, a19, nested),
        c20(row, a20, nested)
      ).asInstanceOf[T]
  }

  final private def cast21[T](
    casters: List[(Row, ParamIndex, NestedTpls) => Any],
    firstAttrIndex: ParamIndex
  ): (Row, List[Any]) => T = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21) = (firstAttrIndex until firstAttrIndex + 21).toList
    (row: Row, nested: List[Any]) =>
      (
        c1(row, a1, nested),
        c2(row, a2, nested),
        c3(row, a3, nested),
        c4(row, a4, nested),
        c5(row, a5, nested),
        c6(row, a6, nested),
        c7(row, a7, nested),
        c8(row, a8, nested),
        c9(row, a9, nested),
        c10(row, a10, nested),
        c11(row, a11, nested),
        c12(row, a12, nested),
        c13(row, a13, nested),
        c14(row, a14, nested),
        c15(row, a15, nested),
        c16(row, a16, nested),
        c17(row, a17, nested),
        c18(row, a18, nested),
        c19(row, a19, nested),
        c20(row, a20, nested),
        c21(row, a21, nested)
      ).asInstanceOf[T]
  }
}