package codegen.mongodb.query.casting

import codegen.mongodb.MongoGenBase

object _CastBsonDoc extends MongoGenBase("CastBsonDoc", "/query/casting") {

  override val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.document.mongodb.query.casting
       |
       |import molecule.base.util.BaseHelpers
       |import org.bson._
       |import scala.collection.mutable
       |import scala.collection.mutable.ListBuffer
       |
       |
       |trait CastBsonDoc_ extends BaseHelpers {
       |
       |  private type Casts = (Option[String], List[String], List[(String, BsonDocument => Any)])
       |
       |  // Let backrefs go back to same previously worked on namespace doc
       |  protected val curLevelDocs = mutable.Map.empty[List[String], BsonDocument]
       |
       |  var level = 0
       |  def indent: String = "  " * level
       |
       |  private def castLevel(
       |    levelCasts: List[Casts],
       |    nestedCasts: List[List[Casts]]
       |  ): List[BsonDocument => Any] = {
       |    val flatCasts = levelCasts.flatMap {
       |      case (_, Nil, attrCasts)         => attrCasts.map(_._2) // outer doc used
       |      case (_, refAttrPath, attrCasts) => attrCasts.map { case (field, fieldCast) =>
       |        (outerDoc: BsonDocument) => {
       |          // Traverse to sub document
       |          val subDoc = refAttrPath.foldLeft(outerDoc) {
       |            case (curDoc, refAttr) => curDoc.getDocument(refAttr)
       |          }
       |          fieldCast(subDoc)
       |        }
       |      }
       |    }
       |    if (nestedCasts.isEmpty)
       |      flatCasts
       |    else
       |      flatCasts :+ castNested(levelCasts.last._2, nestedCasts)
       |  }
       |
       |  private def castNested(
       |    lastAttrPath: List[String],
       |    nestedCasts: List[List[Casts]]
       |  ): BsonDocument => Any = {
       |    level += 1
       |    val nestedRefAttr      = nestedCasts.head.head._1.get
       |    val castNestedDocument = levelCaster(nestedCasts)
       |    val singleNestedOpt    = nestedCasts.last.last._3.size == 1
       |
       |    (outerDoc: BsonDocument) => {
       |      level += 1
       |      val nestedRows = ListBuffer.empty[Any]
       |      val doc        = lastAttrPath match {
       |        case Nil => outerDoc
       |        case _   => lastAttrPath.foldLeft(outerDoc) {
       |          case (curDoc, refAttr) => curDoc.getDocument(refAttr)
       |        }
       |      }
       |      curLevelDocs.clear()
       |      doc.getArray(nestedRefAttr).forEach { nestedRow =>
       |        nestedRows += castNestedDocument(nestedRow.asDocument())
       |      }
       |      if (singleNestedOpt && nestedRows.nonEmpty && nestedRows.head.isInstanceOf[Set[_]]) {
       |        List(nestedRows.asInstanceOf[ListBuffer[Set[_]]].flatten.toSet)
       |      } else {
       |        nestedRows.toList
       |      }
       |    }
       |  }
       |
       |  private def debugCasts(allCasts: List[List[Casts]]): Unit = {
       |    println(s"######################### $$level ##########################################################")
       |    println(
       |      allCasts.head.map { case (nestedRefAttr, refPath, castData) =>
       |          val refs  = refPath
       |          val casts = if (castData.isEmpty) "-" else castData.map {
       |            case (field, _) =>
       |              val p1 = padS(20, field)
       |              s"$$field $$p1 <cast>"
       |          }.mkString("\n  ")
       |          s\"\"\"$$nestedRefAttr  $$refs
       |             |  $$casts\"\"\".stripMargin
       |        }
       |        .mkString("\n")
       |    )
       |    println("----------------------------------------")
       |  }
       |
       |  final def levelCaster(allCasts: List[List[Casts]]): BsonDocument => Any = {
       |    //    debugCasts(allCasts)
       |    val casters = castLevel(allCasts.head, allCasts.tail)
       |    casters.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(doc)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[BsonDocument => Any]): BsonDocument => Any = {
         |    val List($casters) = casters
         |    (doc: BsonDocument) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
