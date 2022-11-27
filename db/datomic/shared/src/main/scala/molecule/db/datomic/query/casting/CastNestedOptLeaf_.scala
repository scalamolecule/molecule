// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastNestedOptLeaf_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def pullLeaf(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    pullCasts.length match {
      case 1 => pullLeaf1(pullCasts)
      case 2 => pullLeaf2(pullCasts)
      case 3 => pullLeaf3(pullCasts)
      case 4 => pullLeaf4(pullCasts)
      case 5 => pullLeaf5(pullCasts)
      case 6 => pullLeaf6(pullCasts)
      case 7 => pullLeaf7(pullCasts)
      case 8 => pullLeaf8(pullCasts)
      case 9 => pullLeaf9(pullCasts)
      case 10 => pullLeaf10(pullCasts)
      case 11 => pullLeaf11(pullCasts)
      case 12 => pullLeaf12(pullCasts)
      case 13 => pullLeaf13(pullCasts)
      case 14 => pullLeaf14(pullCasts)
      case 15 => pullLeaf15(pullCasts)
      case 16 => pullLeaf16(pullCasts)
      case 17 => pullLeaf17(pullCasts)
      case 18 => pullLeaf18(pullCasts)
      case 19 => pullLeaf19(pullCasts)
      case 20 => pullLeaf20(pullCasts)
      case 21 => pullLeaf21(pullCasts)
      case 22 => pullLeaf22(pullCasts)
    }
  }

  final private def pullLeaf1(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf2(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf3(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf4(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf5(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it),
                c4(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf6(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf7(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf8(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it),
                c7(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf9(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
                c1(it),
                c2(it),
                c3(it),
                c4(it),
                c5(it),
                c6(it),
                c7(it),
                c8(it)
              )
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf10(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf11(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf12(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf13(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf14(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf15(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf16(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf17(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf18(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    val c17 = pullCasts(17)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf19(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    val c17 = pullCasts(17)
    val c18 = pullCasts(18)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf20(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    val c17 = pullCasts(17)
    val c18 = pullCasts(18)
    val c19 = pullCasts(19)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf21(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    val c17 = pullCasts(17)
    val c18 = pullCasts(18)
    val c19 = pullCasts(19)
    val c20 = pullCasts(20)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf22(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val c9 = pullCasts(9)
    val c10 = pullCasts(10)
    val c11 = pullCasts(11)
    val c12 = pullCasts(12)
    val c13 = pullCasts(13)
    val c14 = pullCasts(14)
    val c15 = pullCasts(15)
    val c16 = pullCasts(16)
    val c17 = pullCasts(17)
    val c18 = pullCasts(18)
    val c19 = pullCasts(19)
    val c20 = pullCasts(20)
    val c21 = pullCasts(21)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              val it = map.values.iterator
              (
                c0(it),
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
          }
          case _            => Nil
        }
      } catch {
        case _: NullValueException => Nil
        case e: Throwable          => throw e
      }
  }
}