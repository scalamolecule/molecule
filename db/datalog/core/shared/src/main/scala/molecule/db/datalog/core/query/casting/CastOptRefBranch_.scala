// GENERATED CODE ********************************
package molecule.db.datalog.core.query.casting

import java.util.{ArrayList as jArrayList, Iterator as jIterator, Map as jMap}
import molecule.core.util.JavaConversions


trait CastOptRefBranch_ extends JavaConversions {

  final protected def pullOptRefBranch(
    pullCasts0: List[jIterator[?] => Any],
    pullNested: jIterator[?] => Option[Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val pullCasts = pullCasts0 :+ pullNested
    pullCasts.length match {
      case 1  => pullBranch1(pullCasts, refDepth)
      case 2  => pullBranch2(pullCasts, refDepth)
      case 3  => pullBranch3(pullCasts, refDepth)
      case 4  => pullBranch4(pullCasts, refDepth)
      case 5  => pullBranch5(pullCasts, refDepth)
      case 6  => pullBranch6(pullCasts, refDepth)
      case 7  => pullBranch7(pullCasts, refDepth)
      case 8  => pullBranch8(pullCasts, refDepth)
      case 9  => pullBranch9(pullCasts, refDepth)
      case 10 => pullBranch10(pullCasts, refDepth)
      case 11 => pullBranch11(pullCasts, refDepth)
      case 12 => pullBranch12(pullCasts, refDepth)
      case 13 => pullBranch13(pullCasts, refDepth)
      case 14 => pullBranch14(pullCasts, refDepth)
      case 15 => pullBranch15(pullCasts, refDepth)
      case 16 => pullBranch16(pullCasts, refDepth)
      case 17 => pullBranch17(pullCasts, refDepth)
      case 18 => pullBranch18(pullCasts, refDepth)
      case 19 => pullBranch19(pullCasts, refDepth)
      case 20 => pullBranch20(pullCasts, refDepth)
      case 21 => pullBranch21(pullCasts, refDepth)
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
    arity: Int,
    refDepth: Int,
    cast: java.util.Iterator[?] => Any
  ): jIterator[?] => Option[Any] = {
    val list      = new jArrayList[Any](arity)
    val handleMap = (optionalData: jMap[?, ?]) => {
      list.clear()
      Some(cast(flatten(list, optionalData, refDepth, 0).iterator()))
    }
    (it: jIterator[?]) =>
      try {
        it.next match {
          case maps: jMap[_, _] => handleMap(maps)
          case _                => None
        }
      } catch {
        case _: NullValueException => None
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch1(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1) = pullCasts
    resolve(1, refDepth, (it: java.util.Iterator[?]) =>
      (
        c1(it)
      )
    )
  }

  final private def pullBranch2(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2) = pullCasts
    resolve(2, refDepth, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it)
      )
    )
  }

  final private def pullBranch3(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3) = pullCasts
    resolve(3, refDepth, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    )
  }

  final private def pullBranch4(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    resolve(4, refDepth, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    )
  }

  final private def pullBranch5(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    resolve(5, refDepth, (it: java.util.Iterator[?]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it),
        c5(it)
      )
    )
  }

  final private def pullBranch6(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    resolve(6, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch7(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    resolve(7, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch8(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    resolve(8, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch9(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    resolve(9, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch10(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    resolve(10, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch11(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    resolve(11, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch12(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    resolve(12, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch13(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    resolve(13, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch14(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    resolve(14, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch15(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    resolve(15, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch16(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    resolve(16, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch17(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    resolve(17, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch18(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    resolve(18, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch19(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    resolve(19, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch20(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    resolve(20, refDepth, (it: java.util.Iterator[?]) =>
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

  final private def pullBranch21(
    pullCasts: List[jIterator[?] => Any],
    refDepth: Int
  ): jIterator[?] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    resolve(21, refDepth, (it: java.util.Iterator[?]) =>
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
}