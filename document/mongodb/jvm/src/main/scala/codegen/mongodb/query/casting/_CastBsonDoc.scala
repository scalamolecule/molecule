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
       |  private type NsCasts = (List[String], List[(String, BsonDocument => Any)])
       |  private type NssCasts = List[NsCasts]
       |  private type Casts = List[NssCasts]
       |
       |  private val curLevelDocs = mutable.Map.empty[List[String], BsonDocument]
       |
       |  @tailrec
       |  private def castLevel(
       |    acc: List[BsonDocument => Any],
       |    levelCasts: NssCasts,
       |    nestedCasts: Casts,
       |    isNested: Boolean
       |  ): List[BsonDocument => Any] = {
       |    levelCasts match {
       |      case (_ :: Nil, casts) :: refs if isNested =>
       |        castLevel(acc ++ casts.map(_._2), refs, nestedCasts, isNested)
       |
       |      case (Nil, casts) :: refs =>
       |        castLevel(acc ++ casts.map(_._2), refs, nestedCasts, isNested)
       |
       |      case (path, casts) :: refs if nestedCasts.isEmpty =>
       |        val curPath = if(isNested) path.tail else path
       |        castLevel(acc ++ castRefNs(curPath, casts), refs, nestedCasts, isNested)
       |
       |      case Nil if nestedCasts.nonEmpty =>
       |        curLevelDocs.clear()
       |        acc :+ nestedCast(nestedCasts)
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  private def castRefNs(path: List[String], casts: List[(String, BsonDocument => Any)]): List[BsonDocument => Any] = {
       |    casts.map { case (attr, cast) =>
       |      (outerDoc: BsonDocument) => {
       |        // Maybe this could be done in a smarter way with recursion only..
       |        val doc = curLevelDocs.getOrElse(path,
       |          path.foldLeft(outerDoc) {
       |            case (acc, ns) => acc.get(ns).asDocument()
       |          }
       |        )
       |        curLevelDocs(path) = doc
       |        cast(doc)
       |      }
       |    }
       |  }
       |
       |  private def nestedCast(nested: Casts): BsonDocument => Any = {
       |    val refAttr    = nested.head.head._1.head
       |    val innerCasts = documentCaster(nested, true)
       |    (doc: BsonDocument) => {
       |      val inner = ListBuffer.empty[Any]
       |      doc.get(refAttr).asArray().forEach(nestedRow =>
       |        inner += innerCasts(
       |          nestedRow.asDocument()
       |        )
       |      )
       |      inner.toList
       |    }
       |  }
       |
       |  final def documentCaster(casts: Casts, isNested: Boolean = false): BsonDocument => Any = {
       |    val casters = castLevel(Nil, casts.head, casts.tail, isNested)
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
