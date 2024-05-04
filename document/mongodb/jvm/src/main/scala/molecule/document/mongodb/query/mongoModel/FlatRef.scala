package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class FlatRef(
  level: Int = 0,
  parent: Option[Branch] = None,
  cardMany: Boolean = false,
  ns: String = "",
  refAttr: String = "",
  refNs: String = "",
  pathFields: ListBuffer[String] = ListBuffer.empty[String],
  dot: String = "",
  und: String = "",
  path: String = "",
  alias: String = "",
  projection: BsonDocument = new BsonDocument("_id", new BsonInt32(0)),
) extends Branch(
  level,
  parent,
  false,
  cardMany,
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
    println(s"----- 2 -----  $dot  $refAttr  ${parent.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    // Recursively resolve embedded/looked-up documents
    subBranches.foreach(branch => stages.addAll(branch.getStages))

    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
    }

    // Process lookup pipeline stages
    val dot1 = if (parent.get.isEmbedded) dot else ""
    addMatches()

    stages.forEach(stage => pipeline.add(stage))

    val lookup = new BsonDocument()
      .append("from", new BsonString(refNs))
      .append("localField", new BsonString(dot1 + refAttr))
      .append("foreignField", new BsonString("_id"))
      .append("as", new BsonString(dot1 + refAttr))

    if (!pipeline.isEmpty) {
      lookup.append("pipeline", pipeline)
    }
    postStages.add(new BsonDocument("$lookup", lookup))

    val refNotEmpty  = new BsonDocument(dot1 + refAttr, new BsonDocument("$ne", new BsonArray))
    val outerMatches = if (parent.get.filterMatches.isEmpty) {
      refNotEmpty
    } else {
      val all = new BsonArray()
      all.add(refNotEmpty)
      parent.get.filterMatches.forEach(m => all.add(m.toBsonDocument))
      new BsonDocument("$and", all)
    }
    postStages.add(new BsonDocument("$match", outerMatches))

    if (cardMany) {
      // Flatten card-many docs
      postStages.add(
        new BsonDocument("$unwind",
          new BsonDocument("path", new BsonString("$" + dot1 + refAttr))
        )
      )
    } else {
      // Get head document of ref array
      postStages.add(
        new BsonDocument("$addFields",
          new BsonDocument(dot1 + refAttr,
            new BsonDocument("$first", new BsonString("$" + dot1 + refAttr))
          )
        )
      )
    }

    // Match filter attribute with flattened ref fields
    addStage("$match", postMatches)

    if (parent.nonEmpty && projection.isEmpty) {
      // Remove empty projections with only tacit attributes (or no attributes)
      parent.get.projection.remove(refAttr)
    }

    postStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.ns})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatRef($level, $parent1, $cardMany,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}
