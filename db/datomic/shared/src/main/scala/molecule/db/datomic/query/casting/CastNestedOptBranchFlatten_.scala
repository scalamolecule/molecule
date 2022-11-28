// GENERATED CODE ********************************
package molecule.db.datomic.query.casting

import java.util.{ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
import molecule.core.query.Model2Query
import molecule.db.datomic.query.Base


trait CastNestedOptBranchFlatten_[Tpl] {
  self: Model2Query[Tpl] with Base[Tpl] =>

  final protected def pullBranchFlatten(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    pullCasts.length match {
      case 1 => pullBranch1(pullCasts, pullLeaf, levelIndex)
      case 2 => pullBranch2(pullCasts, pullLeaf, levelIndex)
      case 3 => pullBranch3(pullCasts, pullLeaf, levelIndex)
      case 4 => pullBranch4(pullCasts, pullLeaf, levelIndex)
      case 5 => pullBranch5(pullCasts, pullLeaf, levelIndex)
      case 6 => pullBranch6(pullCasts, pullLeaf, levelIndex)
      case 7 => pullBranch7(pullCasts, pullLeaf, levelIndex)
      case 8 => pullBranch8(pullCasts, pullLeaf, levelIndex)
      case 9 => pullBranch9(pullCasts, pullLeaf, levelIndex)
      case 10 => pullBranch10(pullCasts, pullLeaf, levelIndex)
      case 11 => pullBranch11(pullCasts, pullLeaf, levelIndex)
      case 12 => pullBranch12(pullCasts, pullLeaf, levelIndex)
      case 13 => pullBranch13(pullCasts, pullLeaf, levelIndex)
      case 14 => pullBranch14(pullCasts, pullLeaf, levelIndex)
      case 15 => pullBranch15(pullCasts, pullLeaf, levelIndex)
      case 16 => pullBranch16(pullCasts, pullLeaf, levelIndex)
      case 17 => pullBranch17(pullCasts, pullLeaf, levelIndex)
      case 18 => pullBranch18(pullCasts, pullLeaf, levelIndex)
      case 19 => pullBranch19(pullCasts, pullLeaf, levelIndex)
      case 20 => pullBranch20(pullCasts, pullLeaf, levelIndex)
      case 21 => pullBranch21(pullCasts, pullLeaf, levelIndex)
    }
  }

  final private def flatten(
    list: jArrayList[Any],
    map: jMap[_, _],
    max: Int,
    cur: Int
  ): jArrayList[Any] = {
    map.values.forEach {
      case map: jMap[_, _] if cur == max => list.add(map)
      case map: jMap[_, _]               => flatten(list, map, max, cur + 1)
      case v                             => list.add(v)
    }
    list
  }

  final private def pullBranch1(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val list = new jArrayList[Any](2)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val list = new jArrayList[Any](3)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch3(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val list = new jArrayList[Any](4)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch4(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val list = new jArrayList[Any](5)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch5(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val list = new jArrayList[Any](6)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch6(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val list = new jArrayList[Any](7)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch7(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val list = new jArrayList[Any](8)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch8(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val list = new jArrayList[Any](9)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
              (
                c0(it),
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

  final private def pullBranch9(
    pullCasts: List[jIterator[_] => Any],
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
    val c0 = pullCasts(0)
    val c1 = pullCasts(1)
    val c2 = pullCasts(2)
    val c3 = pullCasts(3)
    val c4 = pullCasts(4)
    val c5 = pullCasts(5)
    val c6 = pullCasts(6)
    val c7 = pullCasts(7)
    val c8 = pullCasts(8)
    val list = new jArrayList[Any](10)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](11)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](12)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](13)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](14)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](15)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](16)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](17)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](18)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](19)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](20)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](21)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
    pullLeaf: jIterator[_] => List[Any],
    levelIndex: Int
  ): jIterator[_] => List[Any] = {
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
    val list = new jArrayList[Any](22)
    (it: jIterator[_]) =>
      try {
        it.next match {
          case vs: jList[_] => vs.asScala.toList.map {
            case map: jMap[_, _] =>
              list.clear()
              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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