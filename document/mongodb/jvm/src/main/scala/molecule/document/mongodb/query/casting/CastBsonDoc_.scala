// GENERATED CODE ********************************
package molecule.document.mongodb.query.casting

import org.bson._
import scala.annotation.tailrec


trait CastBsonDoc_ {

  @tailrec
  final private def resolveCastss(
    acc: List[BsonDocument => Any],
    castss: List[Cast],
  ): List[BsonDocument => Any] = {
    castss match {
      case CastAttr(_, cast) :: more => resolveCastss(acc :+ cast, more)

      // Ref
      case CastNs(refAttr, casts, false) :: more =>
        resolveCastss(acc ++ refCasts(refAttr, casts), more)

      // Nested
      case CastNs(refAttr, casts, true) :: more =>
        val refCasts = documentCaster(casts)
        val ref      = (doc: BsonDocument) => {
          refCasts(doc.get(refAttr).asDocument())
        }
        resolveCastss(acc :+ ref, more)

      case _ => acc
    }
  }

  private def refCasts(refAttr: String, casts: List[Cast]): List[BsonDocument => Any] = {
    resolveCastss(Nil, casts).map { refCast =>
      (curDoc: BsonDocument) => {
        refCast(curDoc.get(refAttr).asDocument())
      }
    }
  }


  final def documentCaster(castss: List[Cast]): BsonDocument => Any = {
    val casters = resolveCastss(Nil, castss)
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