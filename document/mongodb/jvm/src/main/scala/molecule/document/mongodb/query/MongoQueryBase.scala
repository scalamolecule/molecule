package molecule.document.mongodb.query

import java.util
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

  class Branch {
    var idField        = false
    var nested         = false
    var path           = List.empty[String]
    var prevPathDot    = ""
    var pathDot        = ""
    var pathUnderscore = ""

    private val pathFields = ListBuffer.empty[String]
    var uniqueIndex = 0

    val matches          = new util.ArrayList[Bson]
    var mandatoryLookup = Option.empty[Bson]

    val preGroupFields = ListBuffer.empty[(String, String)]
    val groupIdFields  = ListBuffer.empty[(String, String, String)]
    val groupExprs     = ListBuffer.empty[(String, BsonValue)]

    val addFields = mutable.Map.empty[List[String], List[(String, BsonValue)]]
    addFields(Nil) = Nil

    val refOwnerships = mutable.Map.empty[List[String], Boolean]
    refOwnerships(Nil) = true

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
    def embedded = refOwnerships(path)

    def groupExpr(uniqueField: String, bson: BsonValue): Unit = {
      groupExprs += ((pathUnderscore + uniqueField, bson))
    }
    def groupSets(uniqueField: String, field: String): Unit = {
      //    println("--- groupSets --- " + field)
      groupExpr(uniqueField,
        new BsonDocument().append("$addToSet", new BsonString(field))
      )
    }
    def addField(field: String, value: BsonValue): Unit = {
      addFields(path) = addFields.getOrElse(path, Nil) :+ field -> value
    }
  }

  final protected var b  = new Branch
  final protected val bb = ListBuffer.empty[(List[String], Branch)]
  bb += Nil -> b // top document

  final protected val unwinds = ListBuffer.empty[String]

  final protected var topPath     = List.empty[String]
  final protected val projections = mutable.Map.empty[List[String], BsonDocument]
  projections(Nil) = new BsonDocument()

  final protected var sampleSize  = 0
  final protected var uniqueIndex = 0

  final protected val limit = new util.ArrayList[Bson]
  final protected val sorts = new util.ArrayList[Bson]

  final protected var allCasts = ListBuffer( // nested levels
    ListBuffer( // nss
      (
        Option.empty[String], // nested ref attr
        ListBuffer.empty[String], // ref attr path
        ListBuffer.empty[(String, BsonDocument => Any)] // field, cast
      )
    )
  )

  def projectField(field: String): Unit = {
    projections(topPath) = projections(topPath).append(field, new BsonInt32(1))
  }
  def removeField(pathField: String): Unit = {
    projections(topPath) = projections(topPath).remove(pathField).asInstanceOf[BsonDocument]
  }

  protected def aggrFn(fn: String, input: BsonValue, n: Int): BsonDocument = {
    new BsonDocument().append(fn,
      new BsonDocument()
        .append("input", input)
        .append("n", new BsonInt32(n))
    )
  }

  def immutableCastss = allCasts.map(
    _.toList.map {
      case (nestedRefAttr, path, casts) => (nestedRefAttr, path.toList, casts.toList)
    }
  ).toList

  final protected def addCast(field: String, cast: BsonDocument => Any): Unit = {
    allCasts.last.last._3 += ((field, cast))
  }

  final protected def removeLastCast(): Unit = {
    val last = allCasts.last.last._3
    last.remove(last.size - 1)
  }

  final protected def replaceCast(field: String, cast: BsonDocument => Any): Unit = {
    removeLastCast()
    addCast(field, cast)
  }

  // Used to lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  //  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
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

  //  def getRowCount(resultSet: Row): Int = {
  //    //    resultSet.last()
  //    //    val size = resultSet.getRow
  //    //    resultSet.beforeFirst()
  //    //    size
  //    ???
  //  }
}
