// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import scala.annotation.tailrec


trait CastOptRefLeaf_ {

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[jIterator[_] => Any],
    acc: List[jIterator[_] => Any],
  ): List[jIterator[_] => Any] = {
    arities match {
      case 0 :: as =>
        resolveArities(as, casts.tail, acc :+ casts.head)

      // Nested
      case -1 :: Nil =>
        resolveArities(Nil, casts.tail, acc :+ casts.head)

      case _ => acc
    }
  }

  final protected def pullOptRefLeaf(
    arities: List[Int],
    pullCasts0: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val pullCasts = resolveArities(arities, pullCasts0, Nil)
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

  final private def resolve(
    cast: java.util.Iterator[_] => Any
  ): jIterator[_] => Option[Any] = {
    val handleMap = (optionalData: jMap[_, _]) =>
      Some(cast(optionalData.values().iterator()))
    (it: jIterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
      (
        c1(it)
      )
    )
  }

  final private def pullLeaf2(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it)
      )
    )
  }

  final private def pullLeaf3(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it)
      )
    )
  }

  final private def pullLeaf4(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
      (
        c1(it),
        c2(it),
        c3(it),
        c4(it)
      )
    )
  }

  final private def pullLeaf5(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
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