package molecule.document.mongodb.query

import com.mongodb.client.model.Projections
import molecule.base.ast.Card
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import molecule.document.mongodb.query.casting._
import org.bson.{BsonDocument, BsonInt32}
import org.bson.conversions.Bson
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

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

  var path    = ""
  val matches = new java.util.ArrayList[Bson]

  var projections      = new java.util.ArrayList[Bson]
  var levelProjections = List(List(projections))

  val group = new java.util.ArrayList[Bson]
  val limit = new java.util.ArrayList[Bson]
  val sorts = new java.util.ArrayList[Bson]

  var casts = ListBuffer( // nested levels
    ListBuffer( // nss
      (ListBuffer.empty[String], ListBuffer.empty[(String, BsonDocument => Any)]) // ns path -> (attribute, cast)
    )
  )

  def immutableCastss = casts.map(
    _.toList.map {
      case (path, casts) => (path.toList, casts.toList)
    }
  ).toList

  def printCasts(label: String = ""): Unit = {
    println("------------ " + label)
    immutableCastss.map(nss =>
      nss.map {
        case (path, casts) => s"$path -> ${casts.map(_._1)}"
      }.mkString("List(\n  ", "\n  ", ")")
    ).foreach(println)
  }

  final protected def addCast(field: String, cast: BsonDocument => Any): Unit = {
    val curNs = casts.last.last
    curNs._2 += (field -> cast)
    //    printCasts("ATTR " + field)
  }


  def updatedCasts = {
    //    println(castss.mkString(s"\n================= CASTSS\n", "\n---\n", ""))
    //    println(curCasts.mkString(s"\n================= curCasts\n", "\n---\n", ""))

    //    val res = root(castss.init :+ castss.last.copy(casts = curCasts))
    //
    //    //    println(res.casts.mkString(s"\n================= RESULT\n", "\n---\n", ""))
    //    res

    ???
  }

  // Used to lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  // Main query
  final protected val select      = new ListBuffer[String]
  final protected var distinct    = true
  final protected var from        = ""
  final protected val joins       = new ListBuffer[(String, String, String, String)]
  final protected var tempTables  = List.empty[String]
  final protected val notNull     = new ListBuffer[String]
  final protected val where       = new ListBuffer[(String, String)]
  final protected val groupBy     = new mutable.LinkedHashSet[String]
  final protected val having      = new mutable.LinkedHashSet[String]
  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final protected var aggregate   = false
  final protected val groupByCols = new mutable.LinkedHashSet[String]
  final protected val in          = new ArrayBuffer[String]
  final protected var hardLimit   = 0

  // Input args and cast lambdas
  final           var aritiess    = List(List.empty[List[Int]])
  final           var isNested    = false
  final           var isNestedOpt = false
  final protected val nestedIds   = new ArrayBuffer[String]
  final protected var level       = 0

  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr = false

  // Query variables
  final protected var filterAttrVars      = Map.empty[String, String]
  final protected val expectedFilterAttrs = mutable.Set.empty[String]
  final protected val availableAttrs      = mutable.Set.empty[String]


  def fullField(field: String) = {
    if (path.isEmpty) field else path.mkString(".") + "." + field
  }

  final protected def addField(field: String): Unit = {
    projections.add(Projections.include(path + field))
  }


  final protected def aritiesNested(): Unit = {
    val newLevel           = Nil
    val curLevel           = aritiess.last
    val curLevelWithNested = curLevel :+ List(-1)
    aritiess = (aritiess.init :+ curLevelWithNested) :+ newLevel
  }

  final protected def aritiesAttr(): Unit = {
    // Add new arity of 1
    aritiess = aritiess.init :+ (aritiess.last :+ List(1))
  }

  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
}
