// GENERATED CODE ********************************
package molecule.document.mongodb.query.casting

import org.bson._
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


trait CastBsonDoc_ {

  private type NsCasts = (List[String], List[(String, BsonDocument => Any)])
  private type NssCasts = List[NsCasts]
  private type Casts = List[NssCasts]

  private val curLevelDocs = mutable.Map.empty[List[String], BsonDocument]

  @tailrec
  private def castLevel(
    acc: List[BsonDocument => Any],
    levelCasts: NssCasts,
    nestedCasts: Casts,
    isNested: Boolean
  ): List[BsonDocument => Any] = {
    levelCasts match {
      case (_ :: Nil, casts) :: refs if isNested =>
        castLevel(acc ++ casts.map(_._2), refs, nestedCasts, isNested)

      case (Nil, casts) :: refs =>
        castLevel(acc ++ casts.map(_._2), refs, nestedCasts, isNested)

      case (path, casts) :: refs if nestedCasts.isEmpty =>
        val curPath = if(isNested) path.tail else path
        castLevel(acc ++ castRefNs(curPath, casts), refs, nestedCasts, isNested)

      case Nil if nestedCasts.nonEmpty =>
        curLevelDocs.clear()
        acc :+ nestedCast(nestedCasts)

      case Nil => acc
    }
  }

  private def castRefNs(path: List[String], casts: List[(String, BsonDocument => Any)]): List[BsonDocument => Any] = {
    casts.map { case (attr, cast) =>
      (outerDoc: BsonDocument) => {

        println(outerDoc)

        // Maybe this could be done in a smarter way with recursion only..
        val doc = curLevelDocs.getOrElse(path,
          path.foldLeft(outerDoc) {
            case (acc, ns) =>
              println(s"------ $ns ")

              acc.get(ns).asDocument()
          }
        )
        curLevelDocs(path) = doc
        cast(doc)
      }
    }
  }

  private def nestedCast(nested: Casts): BsonDocument => Any = {
    val refAttr    = nested.head.head._1.head
    val innerCasts = documentCaster(nested, true)
    (doc: BsonDocument) => {
      val inner = ListBuffer.empty[Any]
      doc.get(refAttr).asArray().forEach(nestedRow =>
        inner += innerCasts(
          nestedRow.asDocument()
        )
      )
      inner.toList
    }
  }

  final def documentCaster(casts: Casts, isNested: Boolean = false): BsonDocument => Any = {
    val casters = castLevel(Nil, casts.head, casts.tail, isNested)
    casters.length match {
      case 1  => cast1(casters)
      case 2  => cast2(casters)
      case 3  => cast3(casters)
      case 4  => cast4(casters)
      case 5  => cast5(casters)
      case 6  => cast6(casters)
      case 7  => cast7(casters)
      case 8  => cast8(casters)
      case 9  => cast9(casters)
      case 10 => cast10(casters)
      case 11 => cast11(casters)
      case 12 => cast12(casters)
      case 13 => cast13(casters)
      case 14 => cast14(casters)
      case 15 => cast15(casters)
      case 16 => cast16(casters)
      case 17 => cast17(casters)
      case 18 => cast18(casters)
      case 19 => cast19(casters)
      case 20 => cast20(casters)
      case 21 => cast21(casters)
      case 22 => cast22(casters)
    }
  }


  final private def cast1(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1) = casters
    (doc: BsonDocument) =>
      (
        c1(doc)
        )
  }

  final private def cast2(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc)
      )
  }

  final private def cast3(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc)
      )
  }

  final private def cast4(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc)
      )
  }

  final private def cast5(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc)
      )
  }

  final private def cast6(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc)
      )
  }

  final private def cast7(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc)
      )
  }

  final private def cast8(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc)
      )
  }

  final private def cast9(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc)
      )
  }

  final private def cast10(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc)
      )
  }

  final private def cast11(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc)
      )
  }

  final private def cast12(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc)
      )
  }

  final private def cast13(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc)
      )
  }

  final private def cast14(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc)
      )
  }

  final private def cast15(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc)
      )
  }

  final private def cast16(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc)
      )
  }

  final private def cast17(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc)
      )
  }

  final private def cast18(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc),
        c18(doc)
      )
  }

  final private def cast19(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc),
        c18(doc),
        c19(doc)
      )
  }

  final private def cast20(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc),
        c18(doc),
        c19(doc),
        c20(doc)
      )
  }

  final private def cast21(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc),
        c18(doc),
        c19(doc),
        c20(doc),
        c21(doc)
      )
  }

  final private def cast22(casters: List[BsonDocument => Any]): BsonDocument => Any = {
    val List(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22) = casters
    (doc: BsonDocument) =>
      (
        c1(doc),
        c2(doc),
        c3(doc),
        c4(doc),
        c5(doc),
        c6(doc),
        c7(doc),
        c8(doc),
        c9(doc),
        c10(doc),
        c11(doc),
        c12(doc),
        c13(doc),
        c14(doc),
        c15(doc),
        c16(doc),
        c17(doc),
        c18(doc),
        c19(doc),
        c20(doc),
        c21(doc),
        c22(doc)
      )
  }
}