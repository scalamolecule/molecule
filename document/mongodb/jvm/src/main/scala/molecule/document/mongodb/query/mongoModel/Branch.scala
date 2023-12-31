package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

abstract class Branch(
  val parent: Option[Branch],
  val refAttr: String,
  val refNs: String,
  val pathFields: ListBuffer[String] = ListBuffer.empty[String],
  val dot: String,
  val und: String,
  val path: String,
  val alias: String,
  val projection: BsonDocument,
) {
  var isEmbedded  = true
  var uniqueIndex = 0
  val stages      = new util.ArrayList[BsonDocument]
  val matches     = new util.ArrayList[Bson]

  val preGroupFields = ListBuffer.empty[(String, String)]
  val groupIdFields  = ListBuffer.empty[(String, String)]
  val groupExprs     = ListBuffer.empty[(String, BsonValue)]
  var addFields      = Set.empty[(String, String)]

  val sorts = new util.ArrayList[Bson]
  val refs  = ListBuffer.empty[Branch]


  def addMatches(): Unit = {
    matches.size match {
      case 0 => () // do nothing
      case 1 => addStage("$match", matches.iterator.next.toBsonDocument)
      case _ => addStage("$match", Filters.and(matches))
    }
  }

  def groupExpr(uniqueField: String, bson: BsonValue): Unit = {
    groupExprs += ((und + uniqueField, bson))
  }
  def groupSets(uniqueField: String, field: String): Unit = {
    groupExpr(uniqueField,
      new BsonDocument().append("$addToSet", new BsonString(field))
    )
  }

  def unique(field: String): String = {
    val uniqueField = if (!pathFields.contains(dot + field)) {
      field
    } else {
      // append suffix to distinguish multiple uses of the same field
      uniqueIndex += 1
      field + "_" + uniqueIndex
    }
    pathFields += dot + uniqueField

    //    println(s"--- $dot   $uniqueField")
    uniqueField
  }

  def addStage(name: String, params: Bson): Boolean = {
    stages.add(
      new BsonDocument().append(name,
        // Add codec for MQL expressions (filters)
        params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      )
    )
  }
  def getStages: util.ArrayList[BsonDocument]
  def render(tabs: Int): String

}