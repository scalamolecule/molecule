package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class NestedRef(
  parent: Option[Branch] = None,
  refAttr: String = "",
  refNs: String = "",
  pathFields: ListBuffer[String] = ListBuffer.empty[String],
  dot: String = "",
  und: String = "",
  path: String = "",
  alias: String = "",
  mandatory: Boolean = true,
  projection: BsonDocument = new BsonDocument().append("_id", new BsonInt32(0)),
) extends Branch(
  parent,
  refAttr,
  refNs,
  pathFields,
  dot,
  und,
  path,
  alias,
  projection,
) {
  isEmbedded = false

  private val outerStages = new util.ArrayList[BsonDocument]
  private val pipeline    = new BsonArray()

  override def getStages: util.ArrayList[BsonDocument] = {
    addMatches()
    refs.foreach(ref => stages.addAll(ref.getStages))
    addStage("$project", projection)

    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
    }

    // Process lookup pipeline stages
    stages.forEach(stage => pipeline.add(stage))
    val lookup = new BsonDocument()
      .append("from", new BsonString(refNs))
      .append("localField", new BsonString(refAttr))
      .append("foreignField", new BsonString("_id"))
      .append("as", new BsonString(refAttr))

    if (!pipeline.isEmpty) {
      lookup.append("pipeline", pipeline)
    }
    outerStages.add(new BsonDocument("$lookup", lookup))

    if (mandatory) {
      outerStages.add(
        new BsonDocument().append("$match",
          new BsonDocument().append(refAttr,
            new BsonDocument().append("$ne", new BsonArray())
          )
        )
      )
    }
    outerStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p = "  " * tabs
    val parent1 = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if(refs.isEmpty)"" else
      s"\n$p  " + refs.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""NestedRef(
       |${p}  $parent1,
       |${p}  $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection$children
       |${p})""".stripMargin
  }
}