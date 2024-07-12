// GENERATED CODE ********************************
package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase


object CastOptRefBranch_ extends SqlQueryBase {

  final def cast(
    casts: List[Cast],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    casts.length match {
      case 0 => cast0
      case 1  => cast1(casts, firstIndex)
      case 2  => cast2(casts, firstIndex)
      case 3  => cast3(casts, firstIndex)
      case 4  => cast4(casts, firstIndex)
      case 5  => cast5(casts, firstIndex)
      case 6  => cast6(casts, firstIndex)
      case 7  => cast7(casts, firstIndex)
      case 8  => cast8(casts, firstIndex)
      case 9  => cast9(casts, firstIndex)
      case 10 => cast10(casts, firstIndex)
      case 11 => cast11(casts, firstIndex)
      case 12 => cast12(casts, firstIndex)
      case 13 => cast13(casts, firstIndex)
      case 14 => cast14(casts, firstIndex)
      case 15 => cast15(casts, firstIndex)
      case 16 => cast16(casts, firstIndex)
      case 17 => cast17(casts, firstIndex)
      case 18 => cast18(casts, firstIndex)
      case 19 => cast19(casts, firstIndex)
      case 20 => cast20(casts, firstIndex)
      case 21 => cast21(casts, firstIndex)
    }
  }

  final private def cast0: (RS, Option[Any]) => Option[Any] = {
    (_: RS, nestedOption: Option[Any]) =>
      nestedOption
  }

  final private def cast1(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1) = casts
    val List(i1) = (firstIndex until firstIndex + 2).toList
    (row: RS, nestedOption: Option[Any]) => {
      val r1 = row.getObject(i1)
      val v1 = c1(row, i1)
      if (r1 == null && v1 != None)
        Option.empty[Any]
      else
        Some((v1, nestedOption))
    }
  }

  final private def cast2(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2) = casts
    val List(i1, i2) = (firstIndex until firstIndex + 2).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2) = List(i1, i2).map(row.getObject)
      val (v1, v2)     = (
        c1(row, i1),
        c2(row, i2)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
      ) Option.empty[Any] else
        Some((v1, v2, nestedOption))
    }
  }

  final private def cast3(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3) = casts
    val List(i1, i2, i3) = (firstIndex until firstIndex + 3).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3) = List(i1, i2, i3).map(row.getObject)
      val (v1, v2, v3)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, nestedOption))
    }
  }

  final private def cast4(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4) = casts
    val List(i1, i2, i3, i4) = (firstIndex until firstIndex + 4).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4) = List(i1, i2, i3, i4).map(row.getObject)
      val (v1, v2, v3, v4)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, nestedOption))
    }
  }

  final private def cast5(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = casts
    val List(i1, i2, i3, i4, i5) = (firstIndex until firstIndex + 5).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5) = List(i1, i2, i3, i4, i5).map(row.getObject)
      val (v1, v2, v3, v4, v5)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, nestedOption))
    }
  }

  final private def cast6(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = casts
    val List(i1, i2, i3, i4, i5, i6) = (firstIndex until firstIndex + 6).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6) = List(i1, i2, i3, i4, i5, i6).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, nestedOption))
    }
  }

  final private def cast7(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casts
    val List(i1, i2, i3, i4, i5, i6, i7) = (firstIndex until firstIndex + 7).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7) = List(i1, i2, i3, i4, i5, i6, i7).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7)
      )
      if (r1 == null && v1 != None
        || r2 == null && v2 != None
        || r3 == null && v3 != None
        || r4 == null && v4 != None
        || r5 == null && v5 != None
        || r6 == null && v6 != None
        || r7 == null && v7 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, nestedOption))
    }
  }

  final private def cast8(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstIndex until firstIndex + 8).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8) = List(i1, i2, i3, i4, i5, i6, i7, i8).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, nestedOption))
    }
  }

  final private def cast9(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstIndex until firstIndex + 9).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, nestedOption))
    }
  }

  final private def cast10(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstIndex until firstIndex + 10).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, nestedOption))
    }
  }

  final private def cast11(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstIndex until firstIndex + 11).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, nestedOption))
    }
  }

  final private def cast12(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstIndex until firstIndex + 12).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, nestedOption))
    }
  }

  final private def cast13(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstIndex until firstIndex + 13).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, nestedOption))
    }
  }

  final private def cast14(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstIndex until firstIndex + 14).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, nestedOption))
    }
  }

  final private def cast15(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstIndex until firstIndex + 15).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, nestedOption))
    }
  }

  final private def cast16(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstIndex until firstIndex + 16).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, nestedOption))
    }
  }

  final private def cast17(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstIndex until firstIndex + 17).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, nestedOption))
    }
  }

  final private def cast18(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstIndex until firstIndex + 18).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, nestedOption))
    }
  }

  final private def cast19(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstIndex until firstIndex + 19).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, nestedOption))
    }
  }

  final private def cast20(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstIndex until firstIndex + 20).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19),
        c20(row, i20)
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
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, nestedOption))
    }
  }

  final private def cast21(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): (RS, Option[Any]) => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstIndex until firstIndex + 21).toList
    (row: RS, nestedOption: Option[Any]) => {
      val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21) = List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21).map(row.getObject)
      val (v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21)     = (
        c1(row, i1),
        c2(row, i2),
        c3(row, i3),
        c4(row, i4),
        c5(row, i5),
        c6(row, i6),
        c7(row, i7),
        c8(row, i8),
        c9(row, i9),
        c10(row, i10),
        c11(row, i11),
        c12(row, i12),
        c13(row, i13),
        c14(row, i14),
        c15(row, i15),
        c16(row, i16),
        c17(row, i17),
        c18(row, i18),
        c19(row, i19),
        c20(row, i20),
        c21(row, i21)
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
        || r21 == null && v21 != None
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, nestedOption))
    }
  }
}