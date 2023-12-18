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
  val projection: BsonDocument,
) {
  var isEmbedded = true

  var uniqueIndex = 0

  val addFields = mutable.Map.empty[List[String], List[(String, BsonValue)]]
  addFields(Nil) = Nil

  val matches = new util.ArrayList[Bson]

  val sorts = new util.ArrayList[Bson]

  val refs = ListBuffer.empty[Branch]

  protected val stages = new util.ArrayList[BsonDocument]

  def getStages: util.ArrayList[BsonDocument]



  def addMatches(): Unit = {
    matches.size match {
      case 0 => () // do nothing
      case 1 => addStage("$match", matches.iterator.next.toBsonDocument)
      case _ => addStage("$match", Filters.and(matches))
    }
  }

  def addStage(
    name: String,
    params: Bson
  ): Boolean = {
    stages.add(
      new BsonDocument().append(name,
        // Add codec for MQL expressions (filters)
        params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      )
    )
  }


  def addMatches2(
    matches: util.ArrayList[Bson] = matches,
    stages: util.ArrayList[BsonDocument] = stages
  ): Unit = {
    matches.size match {
      case 0 => () // do nothing
      case 1 => addStage2("$match", matches.iterator.next.toBsonDocument, stages)
      case _ => addStage2("$match", Filters.and(matches), stages)
    }
  }
  def addStage2(
    name: String,
    params: Bson,
    stages: util.ArrayList[BsonDocument] = stages
  ): Boolean = {
    stages.add(
      new BsonDocument().append(name,
        // Add codec for MQL expressions (filters)
        params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      )
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
    uniqueField
  }

  def render(tabs: Int): String = "XXX"
}