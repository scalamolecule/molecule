package molecule.document.mongodb.query

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import molecule.base.ast.Card
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import molecule.document.mongodb.query.mongoModel.{FlatEmbed, Branch}
import org.bson.conversions.Bson
import org.bson.{BsonArray, BsonDocument, BsonInt32, BsonString, BsonValue}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait MongoQueryBase extends BaseHelpers with JavaConversions {

  // A Branch holds data about attributes in a molecule namespace that we need
  // to translate into Mongo aggregation pipeline stages.
  // Each molecule relationship to another namespace initiates its own Branch.
  // With Mongo's two relationship models, a Branch models 4 relationship combinations:
  // - Embedded/flat
  // - Embedded/nested
  // - Referenced/flat
  // - Referenced/nested
  class BranchOLD {
    var idField = false

    var owner  = false
    var nested = false

    var path           = List.empty[String]
    var prevPathDot    = ""
    var pathDot        = ""
    var pathUnderscore = ""

    private val pathFields = ListBuffer.empty[String]
    var uniqueIndex = 0

    val matches         = new util.ArrayList[Bson]
    var mandatoryLookup = Option.empty[Bson]

    val preGroupFields = ListBuffer.empty[(String, String)]
    val groupIdFields  = ListBuffer.empty[(String, String, String)]
    val groupExprs     = ListBuffer.empty[(String, BsonValue)]

    val addFields = mutable.Map.empty[List[String], List[(String, BsonValue)]]
    addFields(Nil) = Nil

    val refOwnerships = mutable.Map.empty[List[String], Boolean]
    refOwnerships(Nil) = true

    val nestedSorts = ListBuffer.empty[(String, Int)]

    // Helper functions for each branch

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
      groupExpr(uniqueField,
        new BsonDocument().append("$addToSet", new BsonString(field))
      )
    }
    def addField(field: String, value: BsonValue): Unit = {
      addFields(path) = addFields.getOrElse(path, Nil) :+ field -> value
    }
  }


  // Current Branch and all branch instances
  final protected var bx = new BranchOLD

  //  println(s"0 ----- ${System.identityHashCode(b)}  ${b.nested}   ${b.embedded}   ${b.path}")


//  final protected val bb = ListBuffer.empty[(List[String], BranchOLD)]
//  bb += Nil -> bx // top document

  // General data for top level pipeline stages

  // Top branch holds aggregation and projection of all attributes
  final protected val topBranch = new FlatEmbed()

  // Base branch holds matches of embedded documents
  // Changes on moving to ref document
  final protected var baseBranch: Branch = topBranch

  //  topBranch

  //  println("0 " + System.identityHashCode(topBranch))
  //  println("0 " + System.identityHashCode(baseBranch))


  // Current branch changes for each namespace
  final protected var b: Branch = topBranch
  //  println("1 " + System.identityHashCode(b))

  //  val topProjection = b.projection
  //    new BsonDocument().append("_id", new BsonInt32(0))


  def projectField(field: String): Unit = {
//    projections(topPath) = projections(topPath).append(field, new BsonInt32(1))

    //    println("a " + System.identityHashCode(baseBranch))
    b.projection.append(field, new BsonInt32(1))
  }
  def removeField(pathField: String): Unit = {
//    projections(topPath) = projections(topPath).remove(pathField).asInstanceOf[BsonDocument]
    b.projection.remove(pathField)
    //    projections2(topPath2) = projections2(topPath2).remove(pathField).asInstanceOf[BsonDocument]
  }

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

  //  def addMatch(stages: util.ArrayList[Bson], matches: util.ArrayList[Bson]): Unit = {
  //    matches.size match {
  //      case 0 => () // do nothing
  //      case 1 => addStage(stages, "$match", matches.iterator.next.toBsonDocument)
  //      case _ => addStage(stages, "$match", Filters.and(matches))
  //    }
  //  }
  //
  //  def addStage(stages: util.ArrayList[Bson], name: String, params: Bson): Boolean = {
  //    // Add codec for MQL expressions
  //    stages.add(
  //      new BsonDocument().append(name,
  //        params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
  //      )
  //    )
  //  }

  // Used to lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  final protected var hardLimit = 0

  // Input args and cast lambdas
  final           var isNested    = false
  final           var isNestedMan = false
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
