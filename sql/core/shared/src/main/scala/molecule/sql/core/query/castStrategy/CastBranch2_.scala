// GENERATED CODE ********************************
package molecule.sql.core.query.castStrategy

import molecule.sql.core.query.SqlQueryBase
import scala.annotation.tailrec


object CastBranch2_ extends SqlQueryBase {

  final def branchCaster(
    casts: List[Cast],
    firstIndex: ParamIndex
  ): (RS, List[Any]) => Any = {
    casts.length match {
      case 0 => cast0
      case 1 => cast1(casts, firstIndex)
      case 2 => cast2(casts, firstIndex)
      //      case 3  => cast3[T](casters, firstAttrIndex)
      //      case 4  => cast4[T](casters, firstAttrIndex)
      //      case 5  => cast5[T](casters, firstAttrIndex)
      //      case 6  => cast6[T](casters, firstAttrIndex)
      //      case 7  => cast7[T](casters, firstAttrIndex)
      //      case 8  => cast8[T](casters, firstAttrIndex)
      //      case 9  => cast9[T](casters, firstAttrIndex)
      //      case 10 => cast10[T](casters, firstAttrIndex)
      //      case 11 => cast11[T](casters, firstAttrIndex)
      //      case 12 => cast12[T](casters, firstAttrIndex)
      //      case 13 => cast13[T](casters, firstAttrIndex)
      //      case 14 => cast14[T](casters, firstAttrIndex)
      //      case 15 => cast15[T](casters, firstAttrIndex)
      //      case 16 => cast16[T](casters, firstAttrIndex)
      //      case 17 => cast17[T](casters, firstAttrIndex)
      //      case 18 => cast18[T](casters, firstAttrIndex)
      //      case 19 => cast19[T](casters, firstAttrIndex)
      //      case 20 => cast20[T](casters, firstAttrIndex)
      //      case 21 => cast21[T](casters, firstAttrIndex)
    }
  }

  final private def cast0: (RS, List[Any]) => Any = {
    (_: RS, nestedTpls: List[Any]) =>{

      println(s"CastBranch2_   " + nestedTpls)
      nestedTpls
    }
  }

  final private def cast1(
    casters: List[(RS, ParamIndex) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, List[Any]) => Any = {
    val c1 = casters.head


    (row: RS, nestedTpls: List[Any]) => {
      println(s"CastBranch2_  $firstAttrIndex  " + c1(row, firstAttrIndex))
      (
        c1(row, firstAttrIndex),
        nestedTpls
      )
    }
  }

  final private def cast2(
    casters: List[(RS, ParamIndex) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, List[Any]) => Any = {
    val List(c1, c2) = casters
    val List(i1, i2) = (firstAttrIndex until firstAttrIndex + 2).toList
    (row: RS, nestedTpls: List[Any]) =>
      (
        c1(row, i1),
        c2(row, i2),
        nestedTpls
      )
  }

  //  final private def cast3[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3) = casters
  //    val List(i1, i2, i3) = (firstAttrIndex until firstAttrIndex + 3).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested)
  //      )
  //  }
  //
  //  final private def cast4[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4) = casters
  //    val List(i1, i2, i3, i4) = (firstAttrIndex until firstAttrIndex + 4).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested)
  //      )
  //  }
  //
  //  final private def cast5[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5) = casters
  //    val List(i1, i2, i3, i4, i5) = (firstAttrIndex until firstAttrIndex + 5).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested)
  //      )
  //  }
  //
  //  final private def cast6[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6) = casters
  //    val List(i1, i2, i3, i4, i5, i6) = (firstAttrIndex until firstAttrIndex + 6).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested)
  //      )
  //  }
  //
  //  final private def cast7[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7) = (firstAttrIndex until firstAttrIndex + 7).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested)
  //      )
  //  }
  //
  //  final private def cast8[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstAttrIndex until firstAttrIndex + 8).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested)
  //      )
  //  }
  //
  //  final private def cast9[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstAttrIndex until firstAttrIndex + 9).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested)
  //      )
  //  }
  //
  //  final private def cast10[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstAttrIndex until firstAttrIndex + 10).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested)
  //      )
  //  }
  //
  //  final private def cast11[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstAttrIndex until firstAttrIndex + 11).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested)
  //      )
  //  }
  //
  //  final private def cast12[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstAttrIndex until firstAttrIndex + 12).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested)
  //      )
  //  }
  //
  //  final private def cast13[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstAttrIndex until firstAttrIndex + 13).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested)
  //      )
  //  }
  //
  //  final private def cast14[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstAttrIndex until firstAttrIndex + 14).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested)
  //      )
  //  }
  //
  //  final private def cast15[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstAttrIndex until firstAttrIndex + 15).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested)
  //      )
  //  }
  //
  //  final private def cast16[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstAttrIndex until firstAttrIndex + 16).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested)
  //      )
  //  }
  //
  //  final private def cast17[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstAttrIndex until firstAttrIndex + 17).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested),
  //        c17(row, i17, nested)
  //      )
  //  }
  //
  //  final private def cast18[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstAttrIndex until firstAttrIndex + 18).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested),
  //        c17(row, i17, nested),
  //        c18(row, i18, nested)
  //      )
  //  }
  //
  //  final private def cast19[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstAttrIndex until firstAttrIndex + 19).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested),
  //        c17(row, i17, nested),
  //        c18(row, i18, nested),
  //        c19(row, i19, nested)
  //      )
  //  }
  //
  //  final private def cast20[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstAttrIndex until firstAttrIndex + 20).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested),
  //        c17(row, i17, nested),
  //        c18(row, i18, nested),
  //        c19(row, i19, nested),
  //        c20(row, i20, nested)
  //      )
  //  }
  //
  //  final private def cast21[T](
  //    casters: List[(RS, ParamIndex, List[Any]) => Any],
  //    firstAttrIndex: ParamIndex
  //  ): (RS, List[Any]) => T = {
  //    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
  //    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstAttrIndex until firstAttrIndex + 21).toList
  //    (row: RS, nested: List[Any]) =>
  //      (
  //        c1(row, i1, nested),
  //        c2(row, i2, nested),
  //        c3(row, i3, nested),
  //        c4(row, i4, nested),
  //        c5(row, i5, nested),
  //        c6(row, i6, nested),
  //        c7(row, i7, nested),
  //        c8(row, i8, nested),
  //        c9(row, i9, nested),
  //        c10(row, i10, nested),
  //        c11(row, i11, nested),
  //        c12(row, i12, nested),
  //        c13(row, i13, nested),
  //        c14(row, i14, nested),
  //        c15(row, i15, nested),
  //        c16(row, i16, nested),
  //        c17(row, i17, nested),
  //        c18(row, i18, nested),
  //        c19(row, i19, nested),
  //        c20(row, i20, nested),
  //        c21(row, i21, nested)
  //      )
  //  }
}