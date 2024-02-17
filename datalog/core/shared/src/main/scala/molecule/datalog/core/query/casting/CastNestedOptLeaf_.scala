// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2QueryBase
import molecule.datalog.core.query.DatomicQueryBase
import scala.annotation.tailrec


trait CastNestedOptLeaf_
  extends CastRow2Tpl_ with CastIt2Tpl_ { self: Model2QueryBase with DatomicQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[List[Int]],
    casts: List[jIterator[_] => Any],
    acc: List[jIterator[_] => Any],
  ): List[jIterator[_] => Any] = {
    arities match {
      case List(1) :: as =>
        resolveArities(as, casts.tail, acc :+ casts.head)

      // Nested
      case List(-1) :: Nil =>
        resolveArities(Nil, casts.tail, acc :+ casts.head)

      case _ => acc
    }
  }


  final protected def pullLeaf(
    arities: List[List[Int]],
    pullCasts0: List[jIterator[_] => Any],
    pullSorts: List[Int => (Row, Row) => Int]
  ): jIterator[_] => List[Any] = {
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
    val pullCasts     = resolveArities(arities, pullCasts0, Nil)
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
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[_, _]
  ): jArrayList[Any] = {
    map.values.asScala.foreach {
      case map: jMap[_, _] => flatten(list, map)
      case v               => list.add(v)
    }
    list
  }

  final private def resolve(
    handleMaps: jList[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    (it: jIterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {

    val List(c1) = pullCasts
    val cast     = (it: java.util.Iterator[_]) => c1(it)
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](1)
        (rows: jList[_]) =>
          val isSets = flatten(list, rows.get(0).asInstanceOf[jMap[_, _]])
            .get(0).isInstanceOf[jList[_]]

          if (isSets) {
            val res          = rows.asScala.toList.map {
              case row: jMap[_, _] =>
                list.clear()
                cast(flatten(list, row).iterator)
            }
            // Coalesce Sets
            var coalescedSet = Set.empty[Any]
            res.foreach {
              case set: Set[_] => coalescedSet = coalescedSet ++ set
            }
            List(coalescedSet)

          } else {
            rows.asScala.toList.map {
              case row: jMap[_, _] =>
                list.clear()
                cast(flatten(list, row).iterator)
            }
          }

      } { comparator =>
        (rows: jList[_]) =>
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
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2) = pullCasts
    val cast         = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](2)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](2)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf3(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3) = pullCasts
    val cast             = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](3)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](3)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf4(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    val cast                 = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](4)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](4)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf5(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    val cast                     = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](5)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](5)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf6(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    val cast                         = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](6)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](6)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf7(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    val cast                             = (it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it)
      )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](7)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](7)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf8(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    val cast                                 = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](8)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](8)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf9(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    val cast                                     = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](9)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](9)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf10(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    val cast                                          = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](10)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](10)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf11(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    val cast                                               = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](11)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](11)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf12(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    val cast                                                    = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](12)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](12)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf13(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    val cast                                                         = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](13)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](13)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf14(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    val cast                                                              = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](14)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](14)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf15(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    val cast                                                                   = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](15)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](15)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf16(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    val cast                                                                        = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](16)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](16)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf17(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    val cast                                                                             = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](17)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](17)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf18(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    val cast                                                                                  = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](18)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](18)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf19(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    val cast                                                                                       = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](19)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](19)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf20(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    val cast                                                                                            = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](20)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](20)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf21(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    val cast                                                                                                 = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](21)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](21)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullLeaf22(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = pullCasts
    val cast                                                                                                      = (it: java.util.Iterator[_]) =>
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
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](22)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](22)
              sortedRows.add(flatten(list, row).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }
}