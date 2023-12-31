package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class FlatRef(
  parent: Option[Branch] = None,
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
    refs.foreach(ref => stages.addAll(ref.getStages))


    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
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

    // ref array non empty
    parentStages.add(
      new BsonDocument().append("$match",
        new BsonDocument().append(dot1 + refAttr,
          new BsonDocument().append("$ne", new BsonArray)
        )
      )
    )

    // Get head document of ref array
    parentStages.add(
      new BsonDocument().append("$addFields",
        new BsonDocument().append(dot1 + refAttr,
          new BsonDocument().append("$first", new BsonString("$" + dot1 + refAttr))
        )
      )
    )

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
    val children = if (refs.isEmpty) "" else
      s"\n$p  " + refs.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatRef($parent1,
       |${p}  $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection$children
       |${p})""".stripMargin
  }
}
