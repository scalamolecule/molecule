package molecule.document.mongodb.query

import java.util
import com.mongodb.client.model.Projections
import molecule.base.ast.Card
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import org.bson.conversions.Bson
import org.bson.{BsonDocument, BsonString, BsonValue}
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

  trait Fields
  case class Field(field: String) extends Fields
  case class Refs(field: String) extends Fields

  var idField = false

  var path           = List.empty[String]
  var pathDot        = ""
  var pathUnderscore = ""

  val matches     = new util.ArrayList[Bson]

  var projections      = new util.ArrayList[Bson]
  var projections2     = ListBuffer.empty[String]
  var levelProjections = List(List(projections))

  final protected val pathFields     = ListBuffer.empty[String]
  final protected val preGroupFields = ListBuffer.empty[(String, String)]
  final protected val groupIdFields  = ListBuffer.empty[(String, String, String)]
  final protected val groupFields    = ListBuffer.empty[(String, String)]
  final protected val groupExprs     = ListBuffer.empty[(String, BsonValue)]
  final protected val addFields      = mutable.Map.empty[List[String], List[String]]

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

  def path2 = pathDot.replace('.', '_')

  def unique(field: String) = {
    //    println(s"------ $fieldSuffix  $pathDot  $field  ")
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

  def printCasts(label: String = ""): Unit = {
    println("------------ " + label)
    immutableCastss.map(nss =>
      nss.map {
        case (path, casts) => s"$path -> ${casts.map(_._1)}"
      }.mkString("List(\n  ", "\n  ", ")")
    ).foreach(println)
  }

  final protected def addCast(field: String, cast: BsonDocument => Any): Unit = {
    casts.last.last._2 += (field -> cast)
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

  //  final protected def addCast(field: String, cast: BsonDocument => Any): Unit = {
  //    castssOLD = castssOLD.init :+ (castssOLD.last :+ cast)
  //    curCasts = curCasts :+ CastAttr(field, cast)
  //
  //    //    println("  CAST ")
  //    curCasts.foreach(c => println("  --- " + c))
  //  }

  final protected def removeLastCast(): Unit = {
    val x: ListBuffer[(String, BsonDocument => Any)] = casts.last.last._2
    x.remove(x.size - 1)
  }

  final protected def replaceCast(field: String, cast: BsonDocument => Any): Unit = {
    removeLastCast()
    addCast(field, cast)
  }

  // Used to lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  // Main query
  //  final protected val select     = new ListBuffer[String]
  //  final protected var distinct   = true
  //  final protected var from       = ""
  //  final protected val joins      = new ListBuffer[(String, String, String, String)]
  //  final protected var tempTables = List.empty[String]
  final protected val notNull     = new ListBuffer[String]
  //  final protected val where      = new ListBuffer[(String, String)]
  //  final protected val groupBy    = new mutable.LinkedHashSet[String]
  //  final protected val having     = new mutable.LinkedHashSet[String]
  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  //  final protected var aggregate  = false
  //  final protected val in         = new ArrayBuffer[String]
  final protected var hardLimit   = 0
  //
  //  // Input args and cast lambdas
  //  final           var aritiess    = List(List.empty[List[Int]])
  final           var isNested    = false
  final           var isNestedOpt = false
  //  final protected val nestedIds   = new ArrayBuffer[String]
  final protected var level       = 0
  //
  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr  = false

  // Query variables
  final protected var filterAttrVars      = Map.empty[String, String]
  final protected val expectedFilterAttrs = mutable.Set.empty[String]
  final protected val availableAttrs      = mutable.Set.empty[String]


  //  def fullField(field: String) = {
  ////    if (path.isEmpty) field else path.mkString(".") + "." + field
  //    if (path.isEmpty) field else path.mkString(".") + "." + field
  //  }


  //  final protected def aritiesNested(): Unit = {
  //    val newLevel           = Nil
  //    val curLevel           = aritiess.last
  //    val curLevelWithNested = curLevel :+ List(-1)
  //    aritiess = (aritiess.init :+ curLevelWithNested) :+ newLevel
  //  }
  //
  //  final protected def aritiesAttr(): Unit = {
  //    // Add new arity of 1
  //    aritiess = aritiess.init :+ (aritiess.last :+ List(1))
  //  }

  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
}
