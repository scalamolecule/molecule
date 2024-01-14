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
  ns: String = "",
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
  ns,
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

  override def getStages: util.ArrayList[BsonDocument] = {
    addMatches()
    subBranches.foreach(ref => stages.addAll(ref.getStages))
    addStage("$project", projection)

    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
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
    postStages.add(new BsonDocument("$lookup", lookup))

    if (mandatory) {
      postStages.add(
        new BsonDocument().append("$match",
          new BsonDocument().append(refAttr,
            new BsonDocument().append("$ne", new BsonArray())
          )
        )
      )
    }
    postStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p = "  " * tabs
    val parent1 = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if(subBranches.isEmpty)"" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""NestedRef(
       |${p}  $parent1,
       |${p}  $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}