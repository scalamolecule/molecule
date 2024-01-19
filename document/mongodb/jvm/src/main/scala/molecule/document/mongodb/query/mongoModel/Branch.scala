package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

abstract class Branch(
  val level: Int,
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
  val preMatches    = new util.ArrayList[Bson]
  val matches       = new util.ArrayList[Bson]
  val postMatches   = new util.ArrayList[Bson]
  val filterMatches = new util.ArrayList[Bson]

  val preGroupFields   = ListBuffer.empty[(String, String)]
  val groupIdFields    = ListBuffer.empty[(Int, String, String)]
  val optSetSeparators = ListBuffer.empty[(String, BsonValue)]
  val groupExprs       = ListBuffer.empty[(String, BsonValue)]
  var addFields        = Set.empty[(String, BsonValue)]

  val sorts       = ListBuffer.empty[(Int, Bson)]
  val subBranches = ListBuffer.empty[Branch]

  // Base branch holds matches of embedded documents
  var base: Branch = this

  val preStages  = new util.ArrayList[BsonDocument]
  val postStages = new util.ArrayList[BsonDocument]
  val pipeline   = new BsonArray()


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
    groupExpr(keyField, new BsonDocument("$addToSet", new BsonString(setField)))
  }

  def unique(field: String): String = {
    val uniqueField = if (!pathFields.contains(dot + field)) {
      field
    } else {
      // append suffix to distinguish multiple uses of the same field
      uniqueIndex += 1
      val uniqueField = field + "_" + uniqueIndex
      uniqueField
    }
    pathFields += dot + uniqueField

    //    println(s"--- $dot   $uniqueField")
    uniqueField
  }

  def addStage(name: String, doc: Bson): Unit = {
    stages.add(
      new BsonDocument(name,
        // Add codec for MQL expressions (filters)
        doc.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      )
    )
  }
  def addStage(name: String, docs: util.ArrayList[Bson]): Unit = {
    docs.size match {
      case 0 => ()
      case 1 => addStage(name, docs.get(0))
      case _ => stages.add(
        new BsonDocument(name,
          Filters.and(docs)
            .toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
        )
      )
    }
  }

  def getStages: util.ArrayList[BsonDocument]
  def render(tabs: Int): String
}