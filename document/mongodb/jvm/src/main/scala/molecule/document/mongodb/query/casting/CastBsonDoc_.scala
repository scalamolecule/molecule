// GENERATED CODE ********************************
package molecule.document.mongodb.query.casting

import molecule.base.util.BaseHelpers
import org.bson._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


trait CastBsonDoc_ extends BaseHelpers {

  private type Casts = (Option[String], List[String], List[(String, BsonDocument => Any)])

  // Let backrefs go back to same previously worked on namespace doc
  protected val curLevelDocs = mutable.Map.empty[List[String], BsonDocument]

  var level = 0
  def indent: String = "  " * level

  private def castLevel(
    levelCasts: List[Casts],
    nestedCasts: List[List[Casts]]
  ): List[BsonDocument => Any] = {
    val flatCasts = levelCasts.flatMap {
      case (_, Nil, attrCasts)         => attrCasts.map(_._2) // outer doc used
      case (_, refAttrPath, attrCasts) => attrCasts.map { case (field, fieldCast) =>
        (outerDoc: BsonDocument) => {
          // Traverse to sub document
          val subDoc = refAttrPath.foldLeft(outerDoc) {
            case (curDoc, refAttr) => curDoc.getDocument(refAttr)
          }
          fieldCast(subDoc)
        }
      }
    }
    if (nestedCasts.isEmpty)
      flatCasts
    else
      flatCasts :+ castNested(levelCasts.last._2, nestedCasts)
  }

  private def castNested(
    lastAttrPath: List[String],
    nestedCasts: List[List[Casts]]
  ): BsonDocument => Any = {
    level += 1
    val nestedRefAttr      = nestedCasts.head.head._1.get
    val castNestedDocument = levelCaster(nestedCasts)
    val singleNestedOpt    = nestedCasts.last.last._3.size == 1

    (outerDoc: BsonDocument) => {
      level += 1
      val nestedRows = ListBuffer.empty[Any]
      val doc        = lastAttrPath match {
        case Nil => outerDoc
        case _   => lastAttrPath.foldLeft(outerDoc) {
          case (curDoc, refAttr) => curDoc.getDocument(refAttr)
        }
      }
      curLevelDocs.clear()
      doc.get(nestedRefAttr) match {
        case a: BsonArray => a.forEach { nestedRow =>
          nestedRows += castNestedDocument(nestedRow.asDocument())
        }
        case _            => () // Dummy document for empty optional nested data
      }
      //      doc.getArray(nestedRefAttr).forEach { nestedRow =>
      //        nestedRows += castNestedDocument(nestedRow.asDocument())
      //      }
      if (singleNestedOpt && nestedRows.nonEmpty && nestedRows.head.isInstanceOf[Set[_]]) {
        // (can't flatten like this with Scala 2.12.18)
        //        List(nestedRows.asInstanceOf[ListBuffer[Set[_]]].flatten.toSet)
        val set = mutable.Set.empty[Any]
        nestedRows.asInstanceOf[ListBuffer[Set[Any]]].foreach(s => set ++= s)
        List(set)
      } else {
        nestedRows.toList
      }
    }
  }

  private def debugCasts(allCasts: List[List[Casts]]): Unit = {
    println(s"######################### $level ##########################################################")
    println(
      allCasts.head.map { case (nestedRefAttr, refPath, castData) =>
          val refs  = refPath
          val casts = if (castData.isEmpty) "-" else castData.map {
            case (field, _) =>
              val p1 = padS(20, field)
              s"$field $p1 <cast>"
          }.mkString("\n  ")
          s"""$nestedRefAttr  $refs
             |  $casts""".stripMargin
        }
        .mkString("\n")
    )
    println("----------------------------------------")
  }

  final def levelCaster(allCasts: List[List[Casts]]): BsonDocument => Any = {
    //    debugCasts(allCasts)
    val casters = castLevel(allCasts.head, allCasts.tail)
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