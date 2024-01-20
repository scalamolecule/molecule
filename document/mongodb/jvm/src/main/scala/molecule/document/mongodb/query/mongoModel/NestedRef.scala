package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class NestedRef(
  level: Int = 0,
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
  projection: BsonDocument = new BsonDocument("_id", new BsonInt32(0)),
) extends Branch(
  level,
  parent,
  false,
  true,
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

  override def getStages: util.ArrayList[BsonDocument] = {
    //    println(s"----- 3 -----  $refAttr  $sorts")

    // Nested -------------------
    addMatches()
    subBranches.foreach(ref => stages.addAll(ref.getStages))
    addStage("$project", projection)

    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
    }

    // Parent -------------------

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
        new BsonDocument("$match",
          new BsonDocument(refAttr,
            new BsonDocument("$ne", new BsonArray())
          )
        )
      )
    }

    postStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""NestedRef($level, $parent1, $cardMany,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $mandatory, $projection
       |${p})$children""".stripMargin
  }
}