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

  // Top branch holds aggregation and projection of all attributes
  final protected var topBranch: FlatEmbed = new FlatEmbed()

  // Current branch. Changes for each new namespace
  final protected var b: Branch = topBranch

  final protected val branchesByPath     = mutable.Map.empty[List[String], Branch]
  final protected var nestedLevel        = 0
  final protected var hasFilterAttr      = false
  final protected val postFilters        = mutable.Map.empty[List[String], Branch => Boolean]
  final protected var path               = List.empty[String]
  final protected var pathLevels         = mutable.Map.empty[List[String], Int]
  final protected var nestedBaseBranches = mutable.Map.empty[Int, (String, Branch)]
  final protected var prefixedFieldPair  = (0, "", "")


  def addField(uniqueField: String) = {
    if (b.parent.nonEmpty) {
      topBranch.addFields += (b.path + uniqueField) -> new BsonString("$" + b.alias + uniqueField)
    }
  }

  def projectField(field: String): Unit = {
    b.projection.append(field, new BsonInt32(1))
  }

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
    new BsonDocument(fn,
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

  final protected var hardLimit = 0

  // Input args and cast lambdas
  final var isNested    = false
  final var isNestedMan = false
  final var isNestedOpt = false

  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr = false


  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
}
