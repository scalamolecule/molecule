// GENERATED CODE ********************************
package molecule.db.datalog.core.query.casting

import java.util.{Collections, Comparator, ArrayList as jArrayList, Iterator as jIterator, List as jList, Map as jMap}
import molecule.db.datalog.core.query.DatomicQueryBase
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


trait CastOptNestedLeaf_ { self: DatomicQueryBase =>

  private val rowList = new ListBuffer[Any]

  final protected def pullOptNestedLeaf(
    pullCasts: List[jIterator[?] => Any],
    pullSorts: List[Int => (Row, Row) => Int]
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

    pullCasts.length match {
      case 1  => pullLeaf1(pullCasts, optComparator)
      case 2  => pullLeaf2(pullCasts, optComparator)
      case 3  => pullLeaf3(pullCasts, optComparator)
      case 4  => pullLeaf4(pullCasts, optComparator)
      case 5  => pullLeaf5(pullCasts, optComparator)
      case 6  => pullLeaf6(pullCasts, optComparator)
      case 7  => pullLeaf7(pullCasts, optComparator)
      case 8  => pullLeaf8(pullCasts, optComparator)
      case 9  => pullLeaf9(pullCasts, optComparator)
      case 10 => pullLeaf10(pullCasts, optComparator)
      case 11 => pullLeaf11(pullCasts, optComparator)
      case 12 => pullLeaf12(pullCasts, optComparator)
      case 13 => pullLeaf13(pullCasts, optComparator)
      case 14 => pullLeaf14(pullCasts, optComparator)
      case 15 => pullLeaf15(pullCasts, optComparator)
      case 16 => pullLeaf16(pullCasts, optComparator)
      case 17 => pullLeaf17(pullCasts, optComparator)
      case 18 => pullLeaf18(pullCasts, optComparator)
      case 19 => pullLeaf19(pullCasts, optComparator)
      case 20 => pullLeaf20(pullCasts, optComparator)
      case 21 => pullLeaf21(pullCasts, optComparator)
      case 22 => pullLeaf22(pullCasts, optComparator)
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
        resolve(n, optComparator, cast)
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[?, ?]
  ): jArrayList[Any] = {
    map.values.asScala.foreach {
      case map: jMap[_, _] => flatten(list, map)
      case v               => list.add(v)
    }
    list
  }

  final private def resolve(
    arity: Int,
    optComparator: Option[Comparator[Row]],
    cast: java.util.Iterator[?] => Any,
  ): jIterator[?] => List[Any] = {
    val handleMaps = optComparator.fold(
      handleRows(arity, cast)
    )(comparator =>
      handleRowsSorted(arity, cast, comparator)
    )
    resolveNested(handleMaps)
  }

  private def handleRows(
    arity: Int,
    cast: java.util.Iterator[?] => Any
  ): jList[?] => List[Any] = {
    val list = new jArrayList[Any](arity)
    (rows: jList[?]) =>
      rowList.clear()
      rows.asScala.toList.foreach {
        case row: jMap[_, _] =>
          list.clear()
          try {
            rowList += cast(flatten(list, row).iterator)
          } catch {
            case _: NullValueException => ()
          }
      }
      rowList.toList
  }

  private def handleRowsSorted(
    arity: Int,
    cast: java.util.Iterator[?] => Any,
    comparator: Comparator[Row]
  ): jList[?] => List[Any] = {
    (rows: jList[?]) =>
      val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
      rows.asScala.foreach {
        case row: jMap[_, _] =>
          val list = new jArrayList[Any](arity)
          sortedRows.add(flatten(list, row).asInstanceOf[Row])
      }
      Collections.sort(sortedRows, comparator)
      val tupleList = sortedRows.asScala.flatMap { row =>
        try {
          Some(cast(row.iterator))
        } catch {
          case _: NullValueException => None
          case NonFatal(e)           => throw e
        }
      }.toList
      tupleList
  }

  final private def resolveNested(
    handleMaps: jList[?] => List[Any]
  ): jIterator[?] => List[Any] = {
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


  final private def pullLeaf1(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1) = pullCasts
    val cast     = (it: java.util.Iterator[?]) => c1(it)
    resolveNested(
      optComparator.fold {
        val list = new jArrayList[Any](1)
        (rows: jList[?]) =>
          val isSets = optNestedLeafIsSet.getOrElse {
            val rowsIt = rows.iterator()
            var isSet  = false
            var search = true
            while (search && rowsIt.hasNext) {
              val rowIt = rowsIt.next.asInstanceOf[jMap[?, ?]].values().iterator()
              while (search && rowIt.hasNext) {
                rowIt.next match {
                  case "__none__" => ()

                  case value: jMap[_, _] if value.values().iterator().next.isInstanceOf[jList[?]] =>
                    isSet = true
                    search = false
                    optNestedLeafIsSet = Some(true)

                  case _ =>
                    search = false
                    optNestedLeafIsSet = Some(false)
                }
              }
            }
            isSet
          }

          rowList.clear()
          if (isSets) {
            rows.asScala.toList.foreach {
              case row: jMap[_, _] =>
                list.clear()
                try {
                  rowList += cast(flatten(list, row).iterator)
                } catch {
                  case _: NullValueException => ()
                }
            }
            // Coalesce Sets
            var coalescedSet = Set.empty[Any]
            rowList.foreach {
              case set: Set[_] =>
                coalescedSet = coalescedSet ++ set
            }
            if (coalescedSet.isEmpty) Nil else List(coalescedSet)

          } else {
            rows.asScala.toList.foreach {
              case row: jMap[_, _] =>
                list.clear()
                try {
                  rowList += cast(flatten(list, row).iterator)
                } catch {
                  case _: NullValueException => ()
                }
            }
            rowList.toList
          }

      } { comparator =>
        (rows: jList[?]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](1)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf2(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2) = pullCasts
    val cast         = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it)
      )
    resolve(2, optComparator, cast)
  }

  final private def pullLeaf3(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3) = pullCasts
    val cast             = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    resolve(3, optComparator, cast)
  }

  final private def pullLeaf4(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    val cast                 = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    resolve(4, optComparator, cast)
  }

  final private def pullLeaf5(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    val cast                     = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it)
      )
    resolve(5, optComparator, cast)
  }

  final private def pullLeaf6(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    val cast                         = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it)
      )
    resolve(6, optComparator, cast)
  }

  final private def pullLeaf7(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    val cast                             = (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it)
      )
    resolve(7, optComparator, cast)
  }

  final private def pullLeaf8(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    val cast                                 = (it: java.util.Iterator[?]) =>
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
    resolve(8, optComparator, cast)
  }

  final private def pullLeaf9(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    val cast                                     = (it: java.util.Iterator[?]) =>
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
    resolve(9, optComparator, cast)
  }

  final private def pullLeaf10(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    val cast                                          = (it: java.util.Iterator[?]) =>
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
    resolve(10, optComparator, cast)
  }

  final private def pullLeaf11(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    val cast                                               = (it: java.util.Iterator[?]) =>
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
    resolve(11, optComparator, cast)
  }

  final private def pullLeaf12(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    val cast                                                    = (it: java.util.Iterator[?]) =>
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
    resolve(12, optComparator, cast)
  }

  final private def pullLeaf13(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    val cast                                                         = (it: java.util.Iterator[?]) =>
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
    resolve(13, optComparator, cast)
  }

  final private def pullLeaf14(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    val cast                                                              = (it: java.util.Iterator[?]) =>
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
    resolve(14, optComparator, cast)
  }

  final private def pullLeaf15(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    val cast                                                                   = (it: java.util.Iterator[?]) =>
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
    resolve(15, optComparator, cast)
  }

  final private def pullLeaf16(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    val cast                                                                        = (it: java.util.Iterator[?]) =>
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
    resolve(16, optComparator, cast)
  }

  final private def pullLeaf17(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    val cast                                                                             = (it: java.util.Iterator[?]) =>
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
    resolve(17, optComparator, cast)
  }

  final private def pullLeaf18(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    val cast                                                                                  = (it: java.util.Iterator[?]) =>
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
    resolve(18, optComparator, cast)
  }

  final private def pullLeaf19(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    val cast                                                                                       = (it: java.util.Iterator[?]) =>
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
    resolve(19, optComparator, cast)
  }

  final private def pullLeaf20(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    val cast                                                                                            = (it: java.util.Iterator[?]) =>
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
    resolve(20, optComparator, cast)
  }

  final private def pullLeaf21(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    val cast                                                                                                 = (it: java.util.Iterator[?]) =>
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
    resolve(21, optComparator, cast)
  }

  final private def pullLeaf22(
    pullCasts: List[jIterator[?] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[?] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = pullCasts
    val cast                                                                                                      = (it: java.util.Iterator[?]) =>
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
        c21(it),
        c22(it)
      )
    resolve(22, optComparator, cast)
  }
}