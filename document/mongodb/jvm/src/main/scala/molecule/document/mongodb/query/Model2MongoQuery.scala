package molecule.document.mongodb.query

import java.util
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2QueryBase
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.document.mongodb.query.mongoModel.FlatEmbed
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import org.bson.conversions.Bson
import scala.annotation.tailrec

class Model2MongoQuery[Tpl](elements0: List[Element])
  extends Model2QueryBase
    with ModelUtils
    with ModelTransformations_
    with ResolveExprOne
    with ResolveExprOneID
    with ResolveExprSet
    with ResolveExprSetRefAttr
    with ResolveRef
    with MongoQueryBase
    with JavaConversions
    with BsonUtils
    with MoleculeLogging {


  final def getBsonQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
  ): (String, util.ArrayList[Bson]) = {
    val elements1                              = if (altElements.isEmpty) elements0 else altElements
    val (elements2, initialNs, hasFilterAttr0) = validateQueryModel(elements1)
    hasFilterAttr = hasFilterAttr0

    topBranch = new FlatEmbed(ns = initialNs, cardMany = true)
    b = topBranch

    // Recursively resolve molecule elements
    resolve(elements2)

    // Top Mongo pipeline of aggregation stages
    val topStages = new util.ArrayList[Bson]()

    if (sampleSize > 0) {
      topStages.add(new BsonDocument("$sample", new BsonDocument("size", new BsonInt32(sampleSize))))
    }

    // Recursively add aggregation pipeline stages for all branches
    topStages.addAll(topBranch.getStages)

    pagination(topStages, optOffset, optLimit)

    // Return elements with possible filter attribute resolutions
    (initialNs, topStages)
  }

  def pagination(
    stages: util.ArrayList[Bson],
    optOffset: Option[Int],
    optLimit: Option[Int],
  ): Unit = {
    lazy val skipLimit = new BsonArray()
    optOffset.foreach {
      case 0    => () // Start from beginning
      case skip => skipLimit.add(new BsonDocument("$skip", new BsonInt32(skip.abs)))
    }
    optLimit.foreach {
      case 0 =>
        // Get no results, so no need to use stages from query
        stages.clear()
        stages.add(new BsonDocument("$match",
          new BsonDocument("_id",
            new BsonDocument("$exists", new BsonBoolean(false))
          )))

      case limit => skipLimit.add(new BsonDocument("$limit", new BsonInt32(limit.abs)))
    }

    if (optOffset.isDefined || optLimit.isDefined) {
      val metaDataArray = new BsonArray()
      metaDataArray.add(new BsonDocument("$count", new BsonString("totalCount")))
      stages.add(new BsonDocument("$facet", new BsonDocument()
        .append("metaData", metaDataArray)
        .append("rows", skipLimit)
      ))
    }
  }


  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                     =>
        if (a.attr == "id" && a.filterAttr.nonEmpty) {
          throw ModelError(noIdFiltering)
        }
        if (a.attr == "id") {
          a match {
            case a: AttrOneMan => resolveAttrOneManID(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTacID(a); resolve(tail)
            case _             => throw new Exception("Unexpected optional id")
          }
        } else {
          if (a.owner)
            throw ModelError("Can't query for non-existing ids of embedded documents in MongoDB.")
          a match {
            case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
            case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
          }
        }
      case a: AttrSet                     =>
        if (a.owner)
          throw ModelError("Can't query for non-existing set of ids of embedded documents in MongoDB.")
        a match {
          case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
          case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
          case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
        }
      case ref: Ref                       =>
        resolveRef(ref);
        resolve(tail)
      case backRef: BackRef               => resolveBackRef(backRef, tail.head); resolve(tail)
      case Nested(ref, nestedElements)    => resolveNested(ref, nestedElements, tail)
      case NestedOpt(ref, nestedElements) => resolveNestedOpt(ref, nestedElements, tail)
    }
    case Nil             => ()
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    resolveNestedRef(ref, nestedElements)
    resolve(nestedElements)
    resolve(tail)
  }

  final private def resolveNestedOpt(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    resolveNestedOptRef(ref, nestedElements)
    resolve(nestedElements)
    resolve(tail)
  }
}