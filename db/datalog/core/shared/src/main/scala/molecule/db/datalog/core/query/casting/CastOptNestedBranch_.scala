// GENERATED CODE ********************************
package molecule.db.datalog.core.query.casting

import java.util.{Collections, Comparator, ArrayList as jArrayList, Iterator as jIterator, List as jList, Map as jMap}
import molecule.db.datalog.core.query.DatomicQueryBase


trait CastOptNestedBranch_ { self: DatomicQueryBase =>

  final protected def pullOptNestedBranch(
    pullCasts0: List[jIterator[?] => Any],
    pullSorts: List[Int => (Row, Row) => Int],
    pullNested: jIterator[?] => List[Any],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val optComparator = {
      if (pullSorts.nonEmpty) {
        val n = pullSorts.length
        Some(
          new Comparator[Row] {
            override def compare(a: Row, b: Row): Int = {
              var i      = 0
              var result = 0
              result = pullSorts(i)(0)(a, b)
              i += 1
              while (result == 0 && i != n) {
                result = pullSorts(i)(0)(a, b)
                i += 1
              }
              result
            }
          }
        )
      } else None
    }

    val pullCasts = pullCasts0 :+ pullNested

    pullCasts.length match {
      case 1  => pullBranch1(pullCasts, optComparator, refDepth)
      case 2  => pullBranch2(pullCasts, optComparator, refDepth)
      case 3  => pullBranch3(pullCasts, optComparator, refDepth)
      case 4  => pullBranch4(pullCasts, optComparator, refDepth)
      case 5  => pullBranch5(pullCasts, optComparator, refDepth)
      case 6  => pullBranch6(pullCasts, optComparator, refDepth)
      case 7  => pullBranch7(pullCasts, optComparator, refDepth)
      case 8  => pullBranch8(pullCasts, optComparator, refDepth)
      case 9  => pullBranch9(pullCasts, optComparator, refDepth)
      case 10 => pullBranch10(pullCasts, optComparator, refDepth)
      case 11 => pullBranch11(pullCasts, optComparator, refDepth)
      case 12 => pullBranch12(pullCasts, optComparator, refDepth)
      case 13 => pullBranch13(pullCasts, optComparator, refDepth)
      case 14 => pullBranch14(pullCasts, optComparator, refDepth)
      case 15 => pullBranch15(pullCasts, optComparator, refDepth)
      case 16 => pullBranch16(pullCasts, optComparator, refDepth)
      case 17 => pullBranch17(pullCasts, optComparator, refDepth)
      case 18 => pullBranch18(pullCasts, optComparator, refDepth)
      case 19 => pullBranch19(pullCasts, optComparator, refDepth)
      case 20 => pullBranch20(pullCasts, optComparator, refDepth)
      case 21 => pullBranch21(pullCasts, optComparator, refDepth)
      case n  =>
        val cast = (it: jIterator[?]) => {
          var castIndex  = 0
          var tpl: Tuple = EmptyTuple
          while (castIndex < n) {
            tpl = tpl :* pullCasts(castIndex)(it)
            castIndex += 1
          }
          tpl
        }
        resolve(optComparator, refDepth, cast, n)
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[?, ?],
    max: Int,
    cur: Int
  ): jArrayList[Any] = {
    map.values.asScala.foreach {
      case map: jMap[_, _] if cur == max => list.add(map)
      case map: jMap[_, _]               => flatten(list, map, max, cur + 1)
      case v                             => list.add(v)
    }
    list
  }

  final private def resolve(
    optComparator: Option[Comparator[Row]],
    refDepth: Int,
    cast: jIterator[?] => Any,
    arity: Int
  ): jIterator[?] => List[Any] = {
    val handleMaps = {
      optComparator.fold {
        val list = new jArrayList[Any](arity)
        (rows: jList[?]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[?]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](arity)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    }

    (it: jIterator[?]) =>
      try {
        it.next match {
          case maps: jList[_] => handleMaps(maps)
          case _              => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch1(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1) = pullCasts
    val cast     = (it: jIterator[?]) =>
      (
        c1(it)
        )
    resolve(optComparator, refDepth, cast, 1)
  }

  final private def pullBranch2(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2) = pullCasts
    val cast         = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it)
      )
    resolve(optComparator, refDepth, cast, 2)
  }

  final private def pullBranch3(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3) = pullCasts
    val cast             = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    resolve(optComparator, refDepth, cast, 3)
  }

  final private def pullBranch4(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    val cast                 = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    resolve(optComparator, refDepth, cast, 4)
  }

  final private def pullBranch5(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    val cast                     = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it)
      )
    resolve(optComparator, refDepth, cast, 5)
  }

  final private def pullBranch6(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    val cast                         = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it)
      )
    resolve(optComparator, refDepth, cast, 6)
  }

  final private def pullBranch7(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    val cast                             = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it)
      )
    resolve(optComparator, refDepth, cast, 7)
  }

  final private def pullBranch8(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    val cast                                 = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it)
      )
    resolve(optComparator, refDepth, cast, 8)
  }

  final private def pullBranch9(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    val cast                                     = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it)
      )
    resolve(optComparator, refDepth, cast, 9)
  }

  final private def pullBranch10(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    val cast                                          = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it)
      )
    resolve(optComparator, refDepth, cast, 10)
  }

  final private def pullBranch11(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    val cast                                               = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it)
      )
    resolve(optComparator, refDepth, cast, 11)
  }

  final private def pullBranch12(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    val cast                                                    = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it)
      )
    resolve(optComparator, refDepth, cast, 12)
  }

  final private def pullBranch13(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    val cast                                                         = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it)
      )
    resolve(optComparator, refDepth, cast, 13)
  }

  final private def pullBranch14(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    val cast                                                              = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it)
      )
    resolve(optComparator, refDepth, cast, 14)
  }

  final private def pullBranch15(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    val cast                                                                   = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it)
      )
    resolve(optComparator, refDepth, cast, 15)
  }

  final private def pullBranch16(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    val cast                                                                        = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it)
      )
    resolve(optComparator, refDepth, cast, 16)
  }

  final private def pullBranch17(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    val cast                                                                             = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it),
        c17(it)
      )
    resolve(optComparator, refDepth, cast, 17)
  }

  final private def pullBranch18(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    val cast                                                                                  = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it),
        c17(it),
        c18(it)
      )
    resolve(optComparator, refDepth, cast, 18)
  }

  final private def pullBranch19(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    val cast                                                                                       = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it),
        c17(it),
        c18(it),
        c19(it)
      )
    resolve(optComparator, refDepth, cast, 19)
  }

  final private def pullBranch20(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    val cast                                                                                            = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it),
        c17(it),
        c18(it),
        c19(it),
        c20(it)
      )
    resolve(optComparator, refDepth, cast, 20)
  }

  final private def pullBranch21(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    val cast                                                                                                 = (it: jIterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it),
        c8(it),
        c9(it),
        c10(it),
        c11(it),
        c12(it),
        c13(it),
        c14(it),
        c15(it),
        c16(it),
        c17(it),
        c18(it),
        c19(it),
        c20(it),
        c21(it)
      )
    resolve(optComparator, refDepth, cast, 21)
  }
}