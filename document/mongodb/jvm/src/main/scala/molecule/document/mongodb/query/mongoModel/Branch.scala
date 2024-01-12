package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

abstract class Branch(
  val parent: Option[Branch], // parent branch can be both embedded or ref
  var ns: String,
  val refAttr: String,
  val refNs: String,
  val pathFields: ListBuffer[String] = ListBuffer.empty[String],
  val dot: String,
  val und: String,
  val path: String,
  val alias: String,
  val projection: BsonDocument,
) {
  var isEmbedded    = true
  var uniqueIndex   = 0
  val stages        = new util.ArrayList[BsonDocument]
  val matches       = new util.ArrayList[Bson]
  val parentMatches = new util.ArrayList[Bson]
  val filterMatches = new util.ArrayList[Bson]

  val preGroupFields   = ListBuffer.empty[(String, String)]
  val groupIdFields    = ListBuffer.empty[(String, String)]
  val optSetSeparators = ListBuffer.empty[(String, BsonValue)]
  val groupExprs       = ListBuffer.empty[(String, BsonValue)]
  var addFields        = Set.empty[(String, BsonValue)]

  val sorts       = ListBuffer.empty[(Int, Bson)]
  val subBranches = ListBuffer.empty[Branch]

  // Base branch holds matches of embedded documents
  var base: Branch = this

  def addMatches(): Unit = {
    matches.size match {
      case 0 => () // do nothing
      case 1 => addStage("$match", matches.iterator.next.toBsonDocument)
      case _ => addStage("$match", Filters.and(matches))
    }
  }

  def getSorts: util.ArrayList[Bson] = {
    val sorts1 = new util.ArrayList[Bson]
    sorts.sortBy(_._1).foreach { case (_, sort) => sorts1.add(sort) }
    sorts1
  }

  def groupExpr(field: String, bson: BsonValue): Unit = {
    groupExprs += ((field, bson))
  }
  def groupAddToSet(keyField: String, setField: String): Unit = {
    groupExpr(keyField, new BsonDocument().append("$addToSet", new BsonString(setField)))
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