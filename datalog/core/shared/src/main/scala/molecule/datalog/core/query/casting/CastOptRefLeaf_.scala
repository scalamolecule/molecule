// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{ArrayList => jArrayList, Iterator => jIterator, Map => jMap}
import molecule.core.util.JavaConversions


trait CastOptRefLeaf_ extends JavaConversions {

  final protected def pullOptRefLeaf(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    pullCasts.length match {
      case 1  => pullLeaf1(pullCasts)
      case 2  => pullLeaf2(pullCasts)
      case 3  => pullLeaf3(pullCasts)
      case 4  => pullLeaf4(pullCasts)
      case 5  => pullLeaf5(pullCasts)
      case 6  => pullLeaf6(pullCasts)
      case 7  => pullLeaf7(pullCasts)
      case 8  => pullLeaf8(pullCasts)
      case 9  => pullLeaf9(pullCasts)
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
    cast: java.util.Iterator[?] => Any
  ): jIterator[?] => Option[Any] = {
    val list = new jArrayList[Any](arity)
    val handleMap = (optionalData: jMap[?, ?]) => {
      list.clear()
      Some(cast(flatten(list, optionalData).iterator()))
    }
    (it: jIterator[?]) =>
      try {
        it.next match {
          case map: jMap[_, _] => handleMap(map)
          case _               => None
        }
      } catch {
        case _: NullValueException => None
        case e: Throwable          => throw e
      }
  }

  final private def pullLeaf1(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1) = pullCasts
    resolve(1, (it: java.util.Iterator[?]) =>
      (
        c1(it)
      )
    )
  }

  final private def pullLeaf2(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2) = pullCasts
    resolve(2, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it)
      )
    )
  }

  final private def pullLeaf3(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3) = pullCasts
    resolve(3, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    )
  }

  final private def pullLeaf4(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    resolve(4, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    )
  }

  final private def pullLeaf5(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    resolve(5, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it)
      )
    )
  }

  final private def pullLeaf6(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    resolve(6, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it)
      )
    )
  }

  final private def pullLeaf7(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    resolve(7, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it),
        c6(it),
        c7(it)
      )
    )
  }

  final private def pullLeaf8(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    resolve(8, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf9(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    resolve(9, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf10(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    resolve(10, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf11(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    resolve(11, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf12(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    resolve(12, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf13(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    resolve(13, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf14(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    resolve(14, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf15(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    resolve(15, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf16(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    resolve(16, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf17(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    resolve(17, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf18(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    resolve(18, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf19(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    resolve(19, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf20(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    resolve(20, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf21(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    resolve(21, (it: java.util.Iterator[?]) =>
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
    )
  }

  final private def pullLeaf22(
    pullCasts: List[jIterator[?] => Any]
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = pullCasts
    resolve(22, (it: java.util.Iterator[?]) =>
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
    )
  }
}