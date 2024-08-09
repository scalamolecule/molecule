// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2Query
import molecule.datalog.core.query.DatomicQueryBase
import scala.annotation.tailrec


trait CastOptNestedBranch_
  extends CastIt2Tpl_ { self: Model2Query with DatomicQueryBase =>

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[jIterator[_] => Any],
    pullNested: jIterator[_] => List[Any],
    acc: List[jIterator[_] => Any],
  ): List[jIterator[_] => Any] = {
    arities match {
      case 0 :: as =>
        resolveArities(as, casts.tail, pullNested, acc :+ casts.head)

      // Nested
      case -1 :: Nil =>
        resolveArities(Nil, Nil, pullNested, acc :+ pullNested)

      case _ => acc
    }
  }

  final protected def pullOptNestedBranch(
    arities: List[Int],
    pullCasts0: List[jIterator[_] => Any],
    pullSorts: List[Int => (Row, Row) => Int],
    pullNested: jIterator[_] => List[Any],
    refDepth: Int
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
    val pullCasts     = resolveArities(arities, pullCasts0, pullNested, Nil)
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
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[_, _],
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

  final private def pullBranch1(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1) = pullCasts
    val cast     = (it: jIterator[_]) =>
      (
        c1(it)
        )
    resolve(
      optComparator.fold {
        val list = new jArrayList[Any](1)
        (rows: jList[_]) =>
          rows.asScala.toList.map {
            case row: jMap[_, _] =>
              list.clear()
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](1)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch2(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2) = pullCasts
    val cast         = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](2)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch3(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3) = pullCasts
    val cast             = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](3)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch4(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    val cast                 = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](4)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch5(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    val cast                     = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](5)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch6(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    val cast                         = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](6)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch7(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    val cast                             = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](7)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch8(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    val cast                                 = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](8)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch9(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    val cast                                     = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](9)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch10(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    val cast                                          = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](10)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch11(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    val cast                                               = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](11)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch12(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    val cast                                                    = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](12)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch13(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    val cast                                                         = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](13)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch14(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    val cast                                                              = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](14)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch15(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    val cast                                                                   = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](15)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch16(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    val cast                                                                        = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](16)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch17(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    val cast                                                                             = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](17)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch18(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    val cast                                                                                  = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](18)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch19(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    val cast                                                                                       = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](19)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch20(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    val cast                                                                                            = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](20)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }

  final private def pullBranch21(
    pullCasts: List[jIterator[_] => Any],
    optComparator: Option[Comparator[Row]],
    refDepth: Int
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    val cast                                                                                                 = (it: jIterator[_]) =>
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
              cast(flatten(list, row, refDepth, 0).iterator)
          }
      } { comparator =>
        (rows: jList[_]) =>
          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
          rows.asScala.foreach {
            case row: jMap[_, _] =>
              val list = new jArrayList[Any](21)
              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
          }
          Collections.sort(sortedRows, comparator)
          sortedRows.asScala.map { row => cast(row.iterator) }.toList
      }
    )
  }
}