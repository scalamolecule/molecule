package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class FlatEmbed(
  parent: Option[Branch] = None,
  cardMany: Boolean = false,
  refAttr: String = "",
  refNs: String = "",
  pathFields: ListBuffer[String] = ListBuffer.empty[String],
  dot: String = "",
  und: String = "",
  projection: BsonDocument = new BsonDocument().append("_id", new BsonInt32(0)),
) extends Branch(
  parent,
  refAttr,
  refNs,
  pathFields,
  dot,
  und,
  projection,
) {

  override def getStages: util.ArrayList[BsonDocument] = {
    addMatches()

    //    println(s"----- 1 -----  $dot  $refAttr  ${parent.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    refs.foreach(ref => stages.addAll(ref.getStages))

    if (cardMany) {
      stages.add(new BsonDocument()
        .append("$unwind", new BsonString("$" + dot.init)))
    }
    if (parent.isEmpty) {
      addStage("$project", projection)
    }
    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
    }
    stages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if (refs.isEmpty) "" else
      s"\n$p  " + refs.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatEmbed($parent1, $cardMany
       |${p}  $refAttr, $refNs, $pathFields, $dot, $und,
       |${p}  $projection$children
       |${p})""".stripMargin
  }
}
