// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastNestedOptLeafFlatten_[Tpl] {
  self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def pullLeafFlatten(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => List[Any] = {
    pullCasts.length match {
      case 1 => pullLeafFlatten1(pullCasts)
      case 2 => pullLeafFlatten2(pullCasts)
      case 3 => pullLeafFlatten3(pullCasts)
      case 4 => pullLeafFlatten4(pullCasts)
      case 5 => pullLeafFlatten5(pullCasts)
      case 6 => pullLeafFlatten6(pullCasts)
      case 7 => pullLeafFlatten7(pullCasts)
      case 8 => pullLeafFlatten8(pullCasts)
      case 9 => pullLeafFlatten9(pullCasts)
      case 10 => pullLeafFlatten10(pullCasts)
      case 11 => pullLeafFlatten11(pullCasts)
      case 12 => pullLeafFlatten12(pullCasts)
      case 13 => pullLeafFlatten13(pullCasts)
      case 14 => pullLeafFlatten14(pullCasts)
      case 15 => pullLeafFlatten15(pullCasts)
      case 16 => pullLeafFlatten16(pullCasts)
      case 17 => pullLeafFlatten17(pullCasts)
      case 18 => pullLeafFlatten18(pullCasts)
      case 19 => pullLeafFlatten19(pullCasts)
      case 20 => pullLeafFlatten20(pullCasts)
      case 21 => pullLeafFlatten21(pullCasts)
      case 22 => pullLeafFlatten22(pullCasts)
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[_, _]
  ): jArrayList[Any] = {
    map.values.forEach {
      case map: jMap[_, _] =>
        flatten(list, map)
      case v               =>
        list.add(v)
    }
    list
  }

  final private def pullLeafFlatten1(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val list = new jArrayList[Any](1)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten2(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val list = new jArrayList[Any](2)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten3(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val list = new jArrayList[Any](3)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten4(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val list = new jArrayList[Any](4)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten5(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val list = new jArrayList[Any](5)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten6(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val list = new jArrayList[Any](6)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten7(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val list = new jArrayList[Any](7)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten8(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val list = new jArrayList[Any](8)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten9(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val list = new jArrayList[Any](9)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten10(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](10)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten11(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](11)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten12(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](12)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten13(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](13)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten14(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](14)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten15(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](15)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten16(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](16)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten17(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](17)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten18(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](18)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten19(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](19)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten20(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](20)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten21(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](21)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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

  final private def pullLeafFlatten22(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](22)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map).iterator
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