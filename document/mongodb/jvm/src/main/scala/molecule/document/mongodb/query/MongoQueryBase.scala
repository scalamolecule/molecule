package molecule.document.mongodb.query

import java.util
import com.mongodb.client.model.Projections
import molecule.base.ast.Card
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import org.bson.conversions.Bson
import org.bson.{BsonDocument, BsonInt32, BsonString, BsonValue}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait MongoQueryBase extends BaseHelpers with JavaConversions {

  type Row = List[Any]
  type ParamIndex = Int
  type NestedTpls = List[Any]

  def getRowCount(resultSet: Row): Int = {
    //    resultSet.last()
    //    val size = resultSet.getRow
    //    resultSet.beforeFirst()
    //    size
    ???
  }

  var idField = false

  final protected var refPath        = List.empty[String]
  final protected var pathDot        = ""
  final protected var pathUnderscore = ""
  final protected val pathFields     = ListBuffer.empty[String]

  final protected val matches          = new util.ArrayList[Bson]
  final protected val preGroupFields   = ListBuffer.empty[(String, String)]
  final protected val groupIdFields    = ListBuffer.empty[(String, String, String)]
  final protected val groupExprs       = ListBuffer.empty[(String, BsonValue)]

  // Initiate top level
  final protected val addFields        = mutable.Map.empty[List[String], List[(String, BsonValue)]]
  addFields(Nil) = Nil

  final protected var projections      = new util.ArrayList[Bson]
  final protected var levelProjections = List(List(projections))

  var sampleSize  = 0
  var fieldIndex  = 0
  var uniqueIndex = 0

  val limit = new util.ArrayList[Bson]
  val sorts = new util.ArrayList[Bson]

  var casts = ListBuffer( // nested levels
    ListBuffer( // nss
      (ListBuffer.empty[String], ListBuffer.empty[(String, BsonDocument => Any)]) // ns path -> (attribute, cast)
    )
  )

  protected def groupExpr(uniqueField: String, bson: BsonValue): Unit = {
    groupExprs += ((pathUnderscore + uniqueField, bson))
  }
  protected def groupSets(uniqueField: String, field: String): Unit = {
    println("--- " + field)
    groupExpr(uniqueField,
      new BsonDocument().append("$addToSet", new BsonString(field))
    )
  }
  protected def addField(field: String, value: BsonValue): Unit = {
    addFields(refPath) = addFields.getOrElse(refPath, Nil) :+ field -> value
  }

  protected def aggrFn(fn: String, input: BsonValue, n: Int): BsonDocument = {
    new BsonDocument().append(fn,
      new BsonDocument()
        .append("input", input)
        .append("n", new BsonInt32(n))
    )
  }

  def unique(field: String) = {
    val uniqueField = if (!pathFields.contains(pathDot + field)) {
      field
    } else {
      // append suffix to distinguish multiple uses of the same field
      uniqueIndex += 1
      field + "_" + uniqueIndex
    }
    pathFields += pathDot + uniqueField
    uniqueField
  }

  final protected def projectField(field: String): Unit = {
    projections.add(Projections.include(pathDot + field))
  }
  final protected def removeField(pathField: String): Unit = {
    projections.remove(Projections.include(pathField))
  }

  def immutableCastss = casts.map(
    _.toList.map {
      case (path, casts) => (path.toList, casts.toList)
    }
  ).toList

  final protected def addCast(field: String, cast: BsonDocument => Any): Unit = {
    casts.last.last._2 += (field -> cast)
  }

  final protected def removeLastCast(): Unit = {
    val last = casts.last.last._2
    last.remove(last.size - 1)
  }

  final protected def replaceCast(field: String, cast: BsonDocument => Any): Unit = {
    removeLastCast()
    addCast(field, cast)
  }

  // Used to lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final protected var hardLimit   = 0
  //
  //  // Input args and cast lambdas
  final           var isNested    = false
  final           var isNestedOpt = false
  final protected var level       = 0
  //
  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr  = false

  // Query variables
  final protected var filterAttrVars      = Map.empty[String, String]
  final protected val expectedFilterAttrs = mutable.Set.empty[String]
  final protected val availableAttrs      = mutable.Set.empty[String]


  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
}
