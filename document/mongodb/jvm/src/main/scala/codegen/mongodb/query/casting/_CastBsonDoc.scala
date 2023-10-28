package codegen.mongodb.query.casting

import codegen.mongodb.MongoGenBase

object _CastBsonDoc extends MongoGenBase("CastBsonDoc", "/query/casting") {

  override val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.document.mongodb.query.casting
       |
       |import org.bson._
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_ {
       |
       |  @tailrec
       |  final private def resolveCastss(
       |    castss: List[List[BsonDocument => Any]],
       |    acc: List[BsonDocument => Any]
       |  ): List[BsonDocument => Any] = {
       |    castss match {
       |      case (cast :: casts) :: Nil => resolveCastss(casts :: Nil, acc :+ cast)
       |
       |      case (cast :: Nil) :: nested =>
       |        val nestedDocCaster: BsonDocument => Any = documentCaster(nested)
       |        val resolveNested  : BsonDocument => Any = (outerDoc: BsonDocument) => {
       |          cast(outerDoc).asInstanceOf[BsonArray].toArray.toList.map {
       |            case nestedDoc: BsonDocument => nestedDocCaster(nestedDoc)
       |            case other                   => throw new Exception("Unexpected nested Bson type: " + other)
       |          }
       |        }
       |        acc :+ resolveNested
       |
       |      case (cast :: casts) :: nested => resolveCastss(casts :: nested, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final def documentCaster(castss: List[List[BsonDocument => Any]]): BsonDocument => Any = {
       |    val casters = resolveCastss(castss, Nil)
       |    castss.length match {
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
