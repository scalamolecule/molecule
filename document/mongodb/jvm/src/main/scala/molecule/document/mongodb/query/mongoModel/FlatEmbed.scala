package molecule.document.mongodb.query.mongoModel

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.{Filters, Sorts}
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class FlatEmbed(
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
  true,
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
    //    println(s"----- 1 -----  $dot  $refAttr  ${parent.map(_.isEmbedded)}")
    //    matches.forEach(m => println(m))

    addMatches()

    // Recursively resolve embedded/looked-up documents
    subBranches.foreach(ref => stages.addAll(ref.getStages))

    if (cardMany) {
      stages.add(new BsonDocument()
        .append("$unwind", new BsonString("$" + dot.init)))
    }

    addStage("$match", preMatches)

    stages.addAll(preStages)

    if (parent.isEmpty) {
      addAggregationStages()
    }

    if (parent.nonEmpty && projection.isEmpty) {
      // Remove empty projections with only tacit attributes (or no attributes)
      parent.get.projection.remove(refAttr)
    }

    addStage("$match", postMatches)

    stages.addAll(postStages)

    if (parent.isEmpty) {
      addStage("$project", projection)
    }

    if (sorts.nonEmpty) {
      addStage("$sort", Sorts.orderBy(getSorts))
    }
    stages
  }


  private def addAggregationStages(): Unit = {
    // Pre-group
    val (preGroup, prefix) = if (preGroupFields.nonEmpty) {
      val preGroupFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (_, fieldPath, fieldAlias) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + fieldPath))
      }
      preGroupFields.foreach { case (fieldPath, fieldAlias) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + fieldPath))
      }
      stages.add(new BsonDocument("$group", new BsonDocument("_id", preGroupFieldsDoc)))
      (true, "$_id.")
    } else {
      (false, "$")
    }

    if (groupExprs.nonEmpty) {
      // $group
      val groupIdFieldsDoc = new BsonDocument()
      if (preGroup) {
        groupIdFields.foreach { case (_, _, fieldAlias) =>
          groupIdFieldsDoc.put(fieldAlias, new BsonString(prefix + fieldAlias))
        }
      } else {
        groupIdFields.foreach { case (_, fieldPath, fieldAlias) =>
          groupIdFieldsDoc.put(fieldAlias, new BsonString(prefix + fieldPath))
        }
      }
      optSetSeparators.foreach { case (dummyField, cond) =>
        // Separate nulls from arrays/sets of values when grouping
        groupIdFieldsDoc.put(dummyField, cond)
      }
      val groupDoc = new BsonDocument()
      groupDoc.append("_id", groupIdFieldsDoc)
      groupExprs.foreach { case (field, bson) =>
        groupDoc.put(field, bson)
      }
      stages.add(new BsonDocument("$group", groupDoc))

      // $addFields - "format" fields to expected structure
      val addFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (_, fieldPath, fieldAlias) =>
        addFieldsDoc.put(fieldPath, new BsonString("$_id." + fieldAlias))
      }
      addFields.foreach { case (fieldPath, bson) =>
        addFieldsDoc.put(fieldPath, bson)
      }
      if (!addFieldsDoc.isEmpty) {
        stages.add(new BsonDocument("$addFields", addFieldsDoc))
      }
    }
  }


  override def toString = render(0)
  override def render(tabs: Int): String = {
    val p        = "  " * tabs
    val parent1  = parent.fold("None")(parent => s"Some(${parent.ns})")
    val children = if (subBranches.isEmpty) "" else
      s"\n$p  " + subBranches.map(ref => ref.render(tabs + 1)).mkString(s",\n$p  ")
    s"""FlatEmbed($level, $parent1, $cardMany,
       |${p}  $ns, $refAttr, $refNs, $pathFields, $dot, $und, $path, $alias,
       |${p}  $projection
       |${p})$children""".stripMargin
  }
}
