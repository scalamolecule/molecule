package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.client.model.Sorts
import molecule.base.error.ModelError
import org.bson._
import scala.collection.mutable.ListBuffer

class FlatRefNested(
  level: Int = 0,
  parent0: Option[Branch] = None,
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
  parent0,
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
    println(s"----- 2a -----  $dot  $refAttr  ${parent0.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    // Recursively resolve embedded/looked-up documents
    subBranches.foreach(branch => stages.addAll(branch.getStages))

    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
    }

    // Process lookup pipeline stages
    addMatches()

    stages.forEach(stage => pipeline.add(stage))

    val lookup = parent0 match {
      case Some(parent: NestedEmbed) =>
        val projection = new BsonDocument()
        parent.projection.forEach {
          case (k, v: BsonDocument) =>
            val ref = new BsonDocument()
            v.forEach {
              case (k, _) => ref.append(k, new BsonString("$" + k))
            }
            projection.append(k, ref)
          case (k, _)               => projection.append(k, new BsonString("$nested." + k))
        }

        new BsonDocument()
          .append("from", new BsonString(refNs))
          .append("let", new BsonDocument(parent.refAttr, new BsonString("$" + parent.refAttr)))
          .append("pipeline", {
            val a = new BsonArray()
            a.add(new BsonDocument("$addFields",
              new BsonDocument("nested",
                new BsonDocument("$filter",
                  new BsonDocument()
                    .append("input", new BsonString("$$" + parent.refAttr))
                    .append("as", new BsonString(parent.refAttr))
                    .append("cond", new BsonDocument("$eq", {
                      val a = new BsonArray()
                      a.add(new BsonString("$_id"))
                      a.add(new BsonString("$$" + parent.refAttr + "." + refAttr))
                      a
                    })))))
            )
            a.add(new BsonDocument("$unwind", new BsonString("$nested")))
            a.add(new BsonDocument("$project", projection))
            a
          })
          .append("as", new BsonString(parent.refAttr))

      case other => throw ModelError("Unexpected MongoDB branch: " + other)
    }


    postStages.add(new BsonDocument("$lookup", lookup))
    postStages
  }

  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent0.fold("None")(parent => s"Some(${parent.ns})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatRefNested($level, $parent1, $cardMany,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}
