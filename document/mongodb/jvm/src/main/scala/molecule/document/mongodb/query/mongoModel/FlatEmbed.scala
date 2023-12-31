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

  override def getStages: util.ArrayList[BsonDocument] = {
    addMatches()

    //    println(s"----- 1 -----  $dot  $refAttr  ${parent.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    // Recursively resolve embedded/looked-up documents
    refs.foreach(ref => stages.addAll(ref.getStages))

    if (cardMany) {
      stages.add(new BsonDocument()
        .append("$unwind", new BsonString("$" + dot.init)))
    }

    if (parent.isEmpty) {
      //      println("--- TOP embed -------------------")
      //      println(this)
      //      println(s"groupExprs   : $groupExprs")
      //      println(s"groupIdFields: $groupIdFields")
      //      println(s"addFields    : $addFields")
      //      println(s"addFields2   : $addFields2")
      //      println(s"sorts   : $sorts")
      //      group(stages)
      addAggregationStages()
    }

    if (parent.nonEmpty && projection.isEmpty) {
      // Remove empty projections with only tacit attributes (or no attributes)
      parent.get.projection.remove(refAttr)
    }
    if (parent.isEmpty) {
      addStage("$project", projection)
    }

    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
    }
    stages
  }


  private def addAggregationStages(): Unit = {
    // Pre-group
    val (preGroup, prefix) = if (preGroupFields.nonEmpty) {
      val preGroupFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (fieldPath, fieldAlias) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + fieldPath))
      }
      preGroupFields.foreach { case (fieldPath, fieldAlias) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + fieldPath))
      }
      stages.add(new BsonDocument().append("$group", new BsonDocument().append("_id", preGroupFieldsDoc)))
      (true, "$_id.")
    } else {
      (false, "$")
    }

    // Group
    if (groupExprs.nonEmpty) {
      val groupIdFieldsDoc = new BsonDocument()
      if (preGroup) {
        groupIdFields.foreach { case (_, fieldAlias) =>
          groupIdFieldsDoc.put(fieldAlias, new BsonString(prefix + fieldAlias))
        }
      } else {
        groupIdFields.foreach { case (fieldPath, fieldAlias) =>
          groupIdFieldsDoc.put(fieldAlias, new BsonString(prefix + fieldPath))
        }
      }

      val groupDoc = new BsonDocument()
      groupDoc.append("_id", groupIdFieldsDoc)
      groupExprs.foreach { case (field, bson) =>
        groupDoc.put(field, bson)
      }
      stages.add(new BsonDocument().append("$group", groupDoc))

      // $addFields - "format" fields to expected structure
      val addFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (fieldPath, fieldAlias) =>
        addFieldsDoc.put(fieldPath, new BsonString("$_id." + fieldAlias))
      }
      addFields.foreach { case (fieldPath, fieldAlias) =>
        addFieldsDoc.put(fieldPath, new BsonString("$" + fieldAlias))
      }
      stages.add(new BsonDocument().append("$addFields", addFieldsDoc))
    }
  }


  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.refAttr})")
    val children = if (refs.isEmpty) "" else
      s"\n$p  " + refs.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatEmbed($parent1, $cardMany
       |${p}  $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection$children
       |${p})""".stripMargin
  }
}
