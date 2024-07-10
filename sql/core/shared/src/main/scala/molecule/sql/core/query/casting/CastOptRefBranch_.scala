// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase
import scala.annotation.tailrec


object CastOptRefBranch_ extends SqlQueryBase {

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[(RS, ParamIndex) => Any],
    attrIndex: ParamIndex,
    acc: List[(RS, ParamIndex, Option[Any]) => Any]
  ): List[(RS, ParamIndex, Option[Any]) => Any] = {
    arities match {
      case 1 :: as =>
        val cast = (row: RS, attrIndex1: ParamIndex, _: Option[Any]) => casts.head(row, attrIndex1)
        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)

      // Nested
      case -1 :: as =>
        val cast = (_: RS, _: ParamIndex, nested: Option[Any]) => nested
        resolveArities(as, casts, 0, acc :+ cast)

      case _ => acc
    }
  }

  final def cast(
    arities: List[Int],
    casts: List[(RS, ParamIndex) => Any],
    firstAttrIndex: ParamIndex,
  ): (RS, Option[Any]) => Option[Any] = {
    val casters = resolveArities(arities, casts, firstAttrIndex, Nil)
    casters.length match {
      case 2  => cast2(casters, firstAttrIndex)
      case 3  => cast3(casters, firstAttrIndex)
      case 4  => cast4(casters, firstAttrIndex)
      case 5  => cast5(casters, firstAttrIndex)
      case 6  => cast6(casters, firstAttrIndex)
      case 7  => cast7(casters, firstAttrIndex)
      case 8  => cast8(casters, firstAttrIndex)
      case 9  => cast9(casters, firstAttrIndex)
      case 10 => cast10(casters, firstAttrIndex)
      case 11 => cast11(casters, firstAttrIndex)
      case 12 => cast12(casters, firstAttrIndex)
      case 13 => cast13(casters, firstAttrIndex)
      case 14 => cast14(casters, firstAttrIndex)
      case 15 => cast15(casters, firstAttrIndex)
      case 16 => cast16(casters, firstAttrIndex)
      case 17 => cast17(casters, firstAttrIndex)
      case 18 => cast18(casters, firstAttrIndex)
      case 19 => cast19(casters, firstAttrIndex)
      case 20 => cast20(casters, firstAttrIndex)
      case 21 => cast21(casters, firstAttrIndex)
    }
  }

  final private def cast2(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2) = casters
    val List(i1, i2) = (firstAttrIndex until firstAttrIndex + 2).toList
    (row: RS, nestedOption: Option[Any]) => {
      val r1       = row.getObject(i1)
      val (v1, v2) = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption)
      )
      if (r1 == null && v1 != None)
        Option.empty[Any]
      else
        Some((v1, v2))
    }
  }

  final private def cast3(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3) = casters
    val List(i1, i2, i3) = (firstAttrIndex until firstAttrIndex + 3).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2) = List(i1, i2).map(row.getObject)
      val (v1, v2, v3)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3))
    }
  }

  final private def cast4(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4) = casters
    val List(i1, i2, i3, i4) = (firstAttrIndex until firstAttrIndex + 4).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3) = List(i1, i2, i3).map(row.getObject)
      val (v1, v2, v3, v4)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4))
    }
  }

  final private def cast5(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = casters
    val List(i1, i2, i3, i4, i5) = (firstAttrIndex until firstAttrIndex + 5).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4) = List(i1, i2, i3, i4).map(row.getObject)
      val (v1, v2, v3, v4, v5)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5))
    }
  }

  final private def cast6(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    val List(i1, i2, i3, i4, i5, i6) = (firstAttrIndex until firstAttrIndex + 6).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5) = List(i1, i2, i3, i4, i5).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6))
    }
  }

  final private def cast7(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    val List(i1, i2, i3, i4, i5, i6, i7) = (firstAttrIndex until firstAttrIndex + 7).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6) = List(i1, i2, i3, i4, i5, i6).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7))
    }
  }

  final private def cast8(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstAttrIndex until firstAttrIndex + 8).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7) = List(i1, i2, i3, i4, i5, i6, i7).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8))
    }
  }

  final private def cast9(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstAttrIndex until firstAttrIndex + 9).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8) = List(i1, i2, i3, i4, i5, i6, i7, i8).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9))
    }
  }

  final private def cast10(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstAttrIndex until firstAttrIndex + 10).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10))
    }
  }

  final private def cast11(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstAttrIndex until firstAttrIndex + 11).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11))
    }
  }

  final private def cast12(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstAttrIndex until firstAttrIndex + 12).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12))
    }
  }

  final private def cast13(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstAttrIndex until firstAttrIndex + 13).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13))
    }
  }

  final private def cast14(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstAttrIndex until firstAttrIndex + 14).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14))
    }
  }

  final private def cast15(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstAttrIndex until firstAttrIndex + 15).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15))
    }
  }

  final private def cast16(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstAttrIndex until firstAttrIndex + 16).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16))
    }
  }

  final private def cast17(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstAttrIndex until firstAttrIndex + 17).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption),
        c17(row, i17, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
        || r16 == null && v16 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17))
    }
  }

  final private def cast18(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstAttrIndex until firstAttrIndex + 18).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption),
        c17(row, i17, nestedOption),
        c18(row, i18, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
        || r16 == null && v16 != None
        || r17 == null && v17 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18))
    }
  }

  final private def cast19(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstAttrIndex until firstAttrIndex + 19).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption),
        c17(row, i17, nestedOption),
        c18(row, i18, nestedOption),
        c19(row, i19, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
        || r16 == null && v16 != None
        || r17 == null && v17 != None
        || r18 == null && v18 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19))
    }
  }

  final private def cast20(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstAttrIndex until firstAttrIndex + 20).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption),
        c17(row, i17, nestedOption),
        c18(row, i18, nestedOption),
        c19(row, i19, nestedOption),
        c20(row, i20, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
        || r16 == null && v16 != None
        || r17 == null && v17 != None
        || r18 == null && v18 != None
        || r19 == null && v19 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20))
    }
  }

  final private def cast21(
    casters: List[(RS, ParamIndex, Option[Any]) => Any],
    firstAttrIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstAttrIndex until firstAttrIndex + 21).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21)     = (
        c1(row, i1, nestedOption),
        c2(row, i2, nestedOption),
        c3(row, i3, nestedOption),
        c4(row, i4, nestedOption),
        c5(row, i5, nestedOption),
        c6(row, i6, nestedOption),
        c7(row, i7, nestedOption),
        c8(row, i8, nestedOption),
        c9(row, i9, nestedOption),
        c10(row, i10, nestedOption),
        c11(row, i11, nestedOption),
        c12(row, i12, nestedOption),
        c13(row, i13, nestedOption),
        c14(row, i14, nestedOption),
        c15(row, i15, nestedOption),
        c16(row, i16, nestedOption),
        c17(row, i17, nestedOption),
        c18(row, i18, nestedOption),
        c19(row, i19, nestedOption),
        c20(row, i20, nestedOption),
        c21(row, i21, nestedOption)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
        || r8 == null && v8 != None
        || r9 == null && v9 != None
        || r10 == null && v10 != None
        || r11 == null && v11 != None
        || r12 == null && v12 != None
        || r13 == null && v13 != None
        || r14 == null && v14 != None
        || r15 == null && v15 != None
        || r16 == null && v16 != None
        || r17 == null && v17 != None
        || r18 == null && v18 != None
        || r19 == null && v19 != None
        || r20 == null && v20 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21))
    }
  }
}