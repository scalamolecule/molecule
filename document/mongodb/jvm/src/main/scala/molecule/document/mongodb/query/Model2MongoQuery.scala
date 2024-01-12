package molecule.document.mongodb.query

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.query.Model2QueryBase
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import org.bson.conversions.Bson
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

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
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    validateQueryModel(elements1)
    //    elements1.foreach(println)

    // Set attrMap if available (used to get original type of aggregate attributes)
    if (hasFilterAttr) {
      resolveFilterAttrs(elements1)
    }

    // Recursively resolve molecule elements
    resolve(elements1)

    // Top Mongo aggregation stages
    val topStages = new util.ArrayList[Bson]()

    if (sampleSize > 0) {
      topStages.add(new BsonDocument().append("$sample",
        new BsonDocument().append("size", new BsonInt32(sampleSize)))
      )
    }

    // Recursively add aggregation pipeline stages for all branches
    topStages.addAll(topBranch.getStages)

    // Return elements with possible filter attribute resolutions
    (getInitialNonGenericNs(elements1), topStages)
  }

  def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    val limit_ = if (isNestedMan || isNestedOpt) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + (if (isBackwards) -limit else limit))
    }

    val offset_ = if (isNestedMan || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + (if (isBackwards) -offset else offset))
    }

    s"$limit_$offset_"
  }


  private def resolveFilterAttrs(elements: List[Element]): Unit = {
    @tailrec
    def recurse(elements: List[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => checkAttr(a); recurse(tail)
            case n: Nested    => recurse(n.elements ++ tail)
            case n: NestedOpt => recurse(n.elements ++ tail)
            case _            => recurse(tail)
          }
        case Nil             => ()
      }
    }
    def checkAttr(a: Attr): Unit = {
      val attrName = a.cleanName
      availableAttrs += attrName
      if (a.filterAttr.nonEmpty) {
        val fa = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Filter attributes inside filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterAttrName = fa.cleanName
        filterAttrVars.get(filterAttrName).fold {
          // Create datomic variable for this expression attribute
          filterAttrVars = filterAttrVars + (filterAttrName -> attrName)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttrName"))

        if (fa.ns == a.ns) {
          // Add adjacent filter attribute is already lifted in boilerplate code...
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterAttrName pointing to other namespace should be tacit.")
        } else if (fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed.")
        } else {
          // Expect expression attribute in other namespace
          expectedFilterAttrs += filterAttrName
        }
      }
    }
    recurse(elements)
    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attribute(s). Found:\n  " + expectedFilterAttrs.mkString("\n  "))
    }
  }

  private lazy val noIdFiltering = "Filter attributes not allowed to involve entity ids."

  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                     =>
        if (a.attr == "id" && a.filterAttr.nonEmpty || a.attr != "id" && a.filterAttr.exists(_.attr == "id")) {
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
      case ref: Ref                       => resolveRef(ref); resolve(tail)
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