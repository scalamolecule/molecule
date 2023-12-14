// GENERATED CODE ********************************
package molecule.document.mongodb.query.casting

import org.bson._
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


trait CastBsonDoc_ {

  private type Casts = (List[String], List[(String, Boolean, BsonDocument => Any)])

  // Let backrefs go back to same previously worked on namespace doc
  protected val curLevelDocs = mutable.Map.empty[List[String], BsonDocument]

  var level = 0

  def indent: String = "  " * level

  @tailrec
  private def castLevel(
    acc: List[BsonDocument => Any],
    levelCasts: List[Casts],
    nestedCasts: List[List[Casts]],
    isNested: Boolean
  ): List[BsonDocument => Any] = {
    levelCasts match {
      case Nil if nestedCasts.nonEmpty =>
        println(indent + s"A  ${acc.length}  ${levelCasts.length}  $isNested")
        acc :+ nestedCast(nestedCasts)

      case Nil =>
        println(indent + "END -------")
        level = -1
        acc

      case (Nil, nsCasts) :: refCasts =>
        // Top level
        println(indent + s"B  $isNested")
        castLevel(acc ++ nsCasts.map(_._3), refCasts, nestedCasts, isNested)

      case (_, nsCasts) :: refCasts if isNested && nestedCasts.isEmpty =>
        // Nested leaf
        println(indent + s"Z  $isNested")
        castLevel(acc ++ nsCasts.map(_._3), refCasts, nestedCasts, isNested)


      case (refAttrPath, nsCasts) :: refCasts =>
        println(indent + s"C  $isNested  $refAttrPath  ${nestedCasts.isEmpty}")
        val curPath = if (isNested) refAttrPath.tail else refAttrPath
        castLevel(acc ++ castRefAttrs(curPath, nsCasts), refCasts, nestedCasts, isNested)
    }
  }

  //  @tailrec
  //  private def castLevel(
  //    acc: List[BsonDocument => Any],
  //    levelCasts: List[Casts],
  //    nestedCasts: List[List[Casts]],
  //    isNested: Boolean
  //  ): List[BsonDocument => Any] = {
  //    levelCasts match {
  //      case (_ :: Nil, casts) :: refs if isNested =>
  //        castLevel(acc ++ casts.map(_._3), refs, nestedCasts, isNested)
  //
  //      case (Nil, casts) :: refs =>
  //        castLevel(acc ++ casts.map(_._3), refs, nestedCasts, isNested)
  //
  //      case (path, casts) :: refs if nestedCasts.isEmpty =>
  //        val curPath = if (isNested) path.tail else path
  //        castLevel(acc ++ castRefNs(curPath, casts), refs, nestedCasts, isNested)
  //
  //      case Nil if nestedCasts.nonEmpty =>
  //        curLevelDocs.clear()
  //        acc :+ nestedCast(nestedCasts)
  //
  //      case _ =>
  //        acc
  //    }
  //  }

  private def castRefAttrs(
    refAttrPath: List[String],
    nsCasts: List[(String, Boolean, BsonDocument => Any)]
  ): List[BsonDocument => Any] = {
    nsCasts.map { case (field, nested, fieldCast) =>
      (outerDoc: BsonDocument) => {
        println(indent + s"  C1  $refAttrPath")
        println(indent + s"  C2  $outerDoc   $field   ============ ${curLevelDocs.contains(refAttrPath)}")

        val doc = curLevelDocs.getOrElse(refAttrPath,
          // Traverse to leaf document
          refAttrPath.foldLeft(outerDoc) {
            case (curDoc, refAttr) =>
              println(indent + s"  C3  $curDoc   $field  ${curDoc.get(refAttr)}")
              curDoc.get(refAttr).asDocument()
          }
        )
        println(indent + s"  C4  $doc   $field")
        curLevelDocs(refAttrPath) = doc
        fieldCast(doc)
      }
    }
  }

  private def nestedCast(allCasts: List[List[Casts]]): BsonDocument => Any = {
    level += 1
    val refAttr     = allCasts.head.head._1.last
    val nestedCasts = documentCaster(allCasts, true)
    (outerDoc: BsonDocument) => {
      level += 1
      val nestedRows = ListBuffer.empty[Any]
      println(indent + s"A  $refAttr")
      println(indent + s"A  $outerDoc")

      curLevelDocs.clear()
      outerDoc.get(refAttr).asArray().forEach { nestedRow =>
        println(indent + s"A  nestedRow: $nestedRow")
        nestedRows += nestedCasts(nestedRow.asDocument())
      }
      nestedRows.toList
    }
  }

  final def documentCaster(
    allCasts: List[List[Casts]],
    isNested: Boolean = false
  ): BsonDocument => Any = {
    val casters = castLevel(Nil, allCasts.head, allCasts.tail, isNested)
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