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
  parent: Option[Branch] = None,
  ns: String = "",
  refAttr: String = "",
  refNs: String = "",
  pathFields: ListBuffer[String] = ListBuffer.empty[String],
  dot: String = "",
  und: String = "",
  path: String = "",
  alias: String = "",
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

  private val parentStages = new util.ArrayList[BsonDocument]
  private val pipeline     = new BsonArray()

  override def getStages: util.ArrayList[BsonDocument] = {
    //    println(s"----- 2 -----  $dot  $refAttr  ${parent.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    // Recursively resolve embedded/looked-up documents
    subBranches.foreach(ref => stages.addAll(ref.getStages))

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
    parentStages.add(new BsonDocument("$lookup", lookup))


    val refNotEmpty  = new BsonDocument(dot1 + refAttr, new BsonDocument("$ne", new BsonArray))
    val outerMatches = if (parent.get.filterMatches.isEmpty) {
      refNotEmpty
    } else {
      val all = new BsonArray()
      all.add(refNotEmpty)
      parent.get.filterMatches.forEach(m => all.add(m.toBsonDocument))
      new BsonDocument("$and", all)
    }
    parentStages.add(new BsonDocument("$match", outerMatches))

    // Get head document of ref array
    parentStages.add(
      new BsonDocument().append("$addFields",
        new BsonDocument().append(dot1 + refAttr,
          new BsonDocument().append("$first", new BsonString("$" + dot1 + refAttr))
        )
      )
    )

    // Match filter attribute with flattened ref fields
    if (!parentMatches.isEmpty) {
      parentStages.add(
        new BsonDocument().append("$match",
          Filters.and(parentMatches)
            .toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
        )
      )
    }

    if (parent.nonEmpty && projection.isEmpty) {
      // Remove empty projections with only tacit attributes (or no attributes)
      parent.get.projection.remove(refAttr)
    }

    parentStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatRef($parent1,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}
