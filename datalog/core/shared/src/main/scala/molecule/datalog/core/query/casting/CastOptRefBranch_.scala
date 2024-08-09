// GENERATED CODE ********************************
package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import scala.annotation.tailrec


trait CastOptRefBranch_ {

  @tailrec
  final private def resolveArities(
    arities: List[Int],
    casts: List[jIterator[_] => Any],
    pullNested: jIterator[_] => Option[Any],
    acc: List[jIterator[_] => Any],
  ): List[jIterator[_] => Any] = {
    arities match {
      case 1 :: as =>
        resolveArities(as, casts.tail, pullNested, acc :+ casts.head)

      // Nested
      case -1 :: Nil =>
        resolveArities(Nil, Nil, pullNested, acc :+ pullNested)

      case _ => acc
    }
  }

  final protected def pullOptRefBranch(
    arities: List[Int],
    pullCasts0: List[jIterator[_] => Any],
    pullNested: jIterator[_] => Option[Any]
  ): jIterator[_] => Option[Any] = {
    val pullCasts = resolveArities(arities, pullCasts0, pullNested, Nil)
    pullCasts.length match {
      case 1  => pullBranch1(pullCasts)
      case 2  => pullBranch2(pullCasts)
      case 3  => pullBranch3(pullCasts)
      case 4  => pullBranch4(pullCasts)
      case 5  => pullBranch5(pullCasts)
      case 6  => pullBranch6(pullCasts)
      case 7  => pullBranch7(pullCasts)
      case 8  => pullBranch8(pullCasts)
      case 9  => pullBranch9(pullCasts)
      case 10 => pullBranch10(pullCasts)
      case 11 => pullBranch11(pullCasts)
      case 12 => pullBranch12(pullCasts)
      case 13 => pullBranch13(pullCasts)
      case 14 => pullBranch14(pullCasts)
      case 15 => pullBranch15(pullCasts)
      case 16 => pullBranch16(pullCasts)
      case 17 => pullBranch17(pullCasts)
      case 18 => pullBranch18(pullCasts)
      case 19 => pullBranch19(pullCasts)
      case 20 => pullBranch20(pullCasts)
      case 21 => pullBranch21(pullCasts)
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
          case maps: jMap[_, _] => handleMap(maps)
          case _                => None
        }
      } catch {
        case _: NullValueException => None
        case e: Throwable          => throw e
      }
  }

  final private def pullBranch1(
    pullCasts: List[jIterator[_] => Any]
  ): jIterator[_] => Option[Any] = {
    val List(c1) = pullCasts
    resolve((it: java.util.Iterator[_]) =>
      (
        c1(it)
      )
    )
  }

  final private def pullBranch2(
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

  final private def pullBranch3(
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

  final private def pullBranch4(
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

  final private def pullBranch5(
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

  final private def pullBranch6(
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

  final private def pullBranch7(
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

  final private def pullBranch8(
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

  final private def pullBranch9(
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

  final private def pullBranch10(
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

  final private def pullBranch11(
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

  final private def pullBranch12(
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

  final private def pullBranch13(
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

  final private def pullBranch14(
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

  final private def pullBranch15(
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

  final private def pullBranch16(
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

  final private def pullBranch17(
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

  final private def pullBranch18(
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

  final private def pullBranch19(
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

  final private def pullBranch20(
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

  final private def pullBranch21(
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
}