// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastNestedOptBranch_[Tpl] {
  self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def pullBranch(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    pullCasts.length match {
      case 1 => pullBranch1(pullCasts, pullLeaf)
      case 2 => pullBranch2(pullCasts, pullLeaf)
      case 3 => pullBranch3(pullCasts, pullLeaf)
      case 4 => pullBranch4(pullCasts, pullLeaf)
      case 5 => pullBranch5(pullCasts, pullLeaf)
      case 6 => pullBranch6(pullCasts, pullLeaf)
      case 7 => pullBranch7(pullCasts, pullLeaf)
      case 8 => pullBranch8(pullCasts, pullLeaf)
      case 9 => pullBranch9(pullCasts, pullLeaf)
      case 10 => pullBranch10(pullCasts, pullLeaf)
      case 11 => pullBranch11(pullCasts, pullLeaf)
      case 12 => pullBranch12(pullCasts, pullLeaf)
      case 13 => pullBranch13(pullCasts, pullLeaf)
      case 14 => pullBranch14(pullCasts, pullLeaf)
      case 15 => pullBranch15(pullCasts, pullLeaf)
      case 16 => pullBranch16(pullCasts, pullLeaf)
      case 17 => pullBranch17(pullCasts, pullLeaf)
      case 18 => pullBranch18(pullCasts, pullLeaf)
      case 19 => pullBranch19(pullCasts, pullLeaf)
      case 20 => pullBranch20(pullCasts, pullLeaf)
      case 21 => pullBranch21(pullCasts, pullLeaf)
    }
  }

  final private def pullBranch1(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch2(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch3(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch4(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch5(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch6(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch7(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it),
                c7(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch8(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it),
                c7(it),
                c8(it),
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch9(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch10(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch11(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch12(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch13(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch14(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch15(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch16(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch17(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch18(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch19(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch20(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch21(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any]
  ): jIterator[_] => List[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
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
                pullLeaf(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }
}