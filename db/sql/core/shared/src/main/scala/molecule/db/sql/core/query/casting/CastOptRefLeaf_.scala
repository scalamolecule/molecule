// GENERATED CODE ********************************
package molecule.db.sql.core.query.casting

import molecule.db.sql.core.query.SqlQueryBase


object CastOptRefLeaf_ extends SqlQueryBase {

  final def cast(
    casts: List[Cast],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    casts.length match {
      case 0  => (_: RS) => Option.empty[Any]
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
      case 22 => cast22(casts, firstIndex)
      case n  =>
        val last = n - 1
        (row: RS) =>
          var rowIndex   = firstIndex + last
          var castIndex  = last
          var hasEmpty   = false
          var tpl: Tuple = EmptyTuple
          while (castIndex >= 0 && !hasEmpty) {
            val cast = casts(castIndex)
            val v    = cast(row, rowIndex)
            hasEmpty = hasEmptyValue(row, rowIndex, v)
            if (!hasEmpty)
              tpl = v *: tpl
            rowIndex -= 1
            castIndex -= 1
          }
          if (hasEmpty) Option.empty[Any] else Some(tpl)
    }
  }

  final private def cast1(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val cast = casts.head
    (row: RS) =>
      val v1 = cast(row, firstIndex)
      if (hasEmptyValue(row, firstIndex, v1)) Option.empty[Any] else Some(v1)
  }

  final private def cast2(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2) = casts
    val List(i1, i2) = (firstIndex until firstIndex + 2).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2)
      ) Option.empty[Any] else
        Some((v1, v2))
    }
  }

  final private def cast3(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3) = casts
    val List(i1, i2, i3) = (firstIndex until firstIndex + 3).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3)
      ) Option.empty[Any] else
        Some((v1, v2, v3))
    }
  }

  final private def cast4(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4) = casts
    val List(i1, i2, i3, i4) = (firstIndex until firstIndex + 4).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4))
    }
  }

  final private def cast5(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = casts
    val List(i1, i2, i3, i4, i5) = (firstIndex until firstIndex + 5).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      val v5 = c5(row, i5)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5))
    }
  }

  final private def cast6(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = casts
    val List(i1, i2, i3, i4, i5, i6) = (firstIndex until firstIndex + 6).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      val v5 = c5(row, i5)
      val v6 = c6(row, i6)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6))
    }
  }

  final private def cast7(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casts
    val List(i1, i2, i3, i4, i5, i6, i7) = (firstIndex until firstIndex + 7).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      val v5 = c5(row, i5)
      val v6 = c6(row, i6)
      val v7 = c7(row, i7)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7))
    }
  }

  final private def cast8(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8) = (firstIndex until firstIndex + 8).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      val v5 = c5(row, i5)
      val v6 = c6(row, i6)
      val v7 = c7(row, i7)
      val v8 = c8(row, i8)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8))
    }
  }

  final private def cast9(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9) = (firstIndex until firstIndex + 9).toList
    (row: RS) => {
      val v1 = c1(row, i1)
      val v2 = c2(row, i2)
      val v3 = c3(row, i3)
      val v4 = c4(row, i4)
      val v5 = c5(row, i5)
      val v6 = c6(row, i6)
      val v7 = c7(row, i7)
      val v8 = c8(row, i8)
      val v9 = c9(row, i9)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9))
    }
  }

  final private def cast10(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) = (firstIndex until firstIndex + 10).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10))
    }
  }

  final private def cast11(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11) = (firstIndex until firstIndex + 11).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11))
    }
  }

  final private def cast12(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12) = (firstIndex until firstIndex + 12).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12))
    }
  }

  final private def cast13(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13) = (firstIndex until firstIndex + 13).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13))
    }
  }

  final private def cast14(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14) = (firstIndex until firstIndex + 14).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14))
    }
  }

  final private def cast15(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15) = (firstIndex until firstIndex + 15).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15))
    }
  }

  final private def cast16(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16) = (firstIndex until firstIndex + 16).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16))
    }
  }

  final private def cast17(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17) = (firstIndex until firstIndex + 17).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17))
    }
  }

  final private def cast18(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18) = (firstIndex until firstIndex + 18).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      val v18 = c18(row, i18)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17) ||
          hasEmptyValue(row, i18, v18)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18))
    }
  }

  final private def cast19(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19) = (firstIndex until firstIndex + 19).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      val v18 = c18(row, i18)
      val v19 = c19(row, i19)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17) ||
          hasEmptyValue(row, i18, v18) ||
          hasEmptyValue(row, i19, v19)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19))
    }
  }

  final private def cast20(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20) = (firstIndex until firstIndex + 20).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      val v18 = c18(row, i18)
      val v19 = c19(row, i19)
      val v20 = c20(row, i20)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17) ||
          hasEmptyValue(row, i18, v18) ||
          hasEmptyValue(row, i19, v19) ||
          hasEmptyValue(row, i20, v20)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20))
    }
  }

  final private def cast21(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21) = (firstIndex until firstIndex + 21).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      val v18 = c18(row, i18)
      val v19 = c19(row, i19)
      val v20 = c20(row, i20)
      val v21 = c21(row, i21)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17) ||
          hasEmptyValue(row, i18, v18) ||
          hasEmptyValue(row, i19, v19) ||
          hasEmptyValue(row, i20, v20) ||
          hasEmptyValue(row, i21, v21)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21))
    }
  }

  final private def cast22(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casts
    val List(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22) = (firstIndex until firstIndex + 22).toList
    (row: RS) => {
      val v1  = c1(row, i1)
      val v2  = c2(row, i2)
      val v3  = c3(row, i3)
      val v4  = c4(row, i4)
      val v5  = c5(row, i5)
      val v6  = c6(row, i6)
      val v7  = c7(row, i7)
      val v8  = c8(row, i8)
      val v9  = c9(row, i9)
      val v10 = c10(row, i10)
      val v11 = c11(row, i11)
      val v12 = c12(row, i12)
      val v13 = c13(row, i13)
      val v14 = c14(row, i14)
      val v15 = c15(row, i15)
      val v16 = c16(row, i16)
      val v17 = c17(row, i17)
      val v18 = c18(row, i18)
      val v19 = c19(row, i19)
      val v20 = c20(row, i20)
      val v21 = c21(row, i21)
      val v22 = c22(row, i22)
      if (
        hasEmptyValue(row, i1, v1) ||
          hasEmptyValue(row, i2, v2) ||
          hasEmptyValue(row, i3, v3) ||
          hasEmptyValue(row, i4, v4) ||
          hasEmptyValue(row, i5, v5) ||
          hasEmptyValue(row, i6, v6) ||
          hasEmptyValue(row, i7, v7) ||
          hasEmptyValue(row, i8, v8) ||
          hasEmptyValue(row, i9, v9) ||
          hasEmptyValue(row, i10, v10) ||
          hasEmptyValue(row, i11, v11) ||
          hasEmptyValue(row, i12, v12) ||
          hasEmptyValue(row, i13, v13) ||
          hasEmptyValue(row, i14, v14) ||
          hasEmptyValue(row, i15, v15) ||
          hasEmptyValue(row, i16, v16) ||
          hasEmptyValue(row, i17, v17) ||
          hasEmptyValue(row, i18, v18) ||
          hasEmptyValue(row, i19, v19) ||
          hasEmptyValue(row, i20, v20) ||
          hasEmptyValue(row, i21, v21) ||
          hasEmptyValue(row, i22, v22)
      ) Option.empty[Any] else
        Some((v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22))
    }
  }
}