package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class NestedEmbed(
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
  projection: BsonDocument = new BsonDocument("_id", new BsonInt32(0)),
) extends Branch(
  level,
  parent,
  true,
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
    //    println(s"----- 4 -----  $refAttr  $sorts")

    addMatches()
    subBranches.foreach(ref => stages.addAll(ref.getStages))

    if (parent.isEmpty) {
      addStage("$project", projection)
    }
    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
    }

    stages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""NestedEmbed($level, $parent1, $cardMany,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}
