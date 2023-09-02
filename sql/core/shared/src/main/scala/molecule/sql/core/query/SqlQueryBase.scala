package molecule.sql.core.query

import java.sql.ResultSet
import java.util.{Iterator => jIterator, List => jList}
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.query.Model2Query
import molecule.core.util.JavaConversions
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


trait SqlQueryBase extends BaseHelpers with JavaConversions { self: Model2Query =>

  // This type represents both all rows and the individual row where the
  // internal cursor is positioned
  type Row = java.sql.ResultSet
  type AttrIndex = Int
  type NestedTpls = List[Any]

  type RowOLD = jList[AnyRef]

  def getRowCount(rows: ResultSet): Int = {
    rows.last()
    val size = rows.getRow
    rows.beforeFirst()
    size
  }

  // Main query
  final protected var isNested    = false
  final protected var isNestedOpt = false

  final protected val nestedIds = new ArrayBuffer[String]

  final protected var level = 0


  final protected val select      = new ListBuffer[String]
  final protected var distinct    = true
  final protected var from        = ""
  final protected val joins       = new ListBuffer[(String, String, String, String)]
  final protected val notNull     = new ListBuffer[String]
  final protected val where       = new ListBuffer[(String, String)]
  final protected val groupBy     = new mutable.LinkedHashSet[String]
  final protected val having      = new mutable.LinkedHashSet[String]
  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final protected var fetch       = new ListBuffer[String]
  final protected var limitClause = ""

  final protected var aggregate   = false
  final protected val groupByCols = new mutable.LinkedHashSet[String]

  final protected val exts = mutable.Map.empty[String, Option[String]]


  final protected val in = new ArrayBuffer[String]

  // Input args and cast lambdas
  final protected val args     = new ArrayBuffer[AnyRef]
  final protected var castss   = List(List.empty[(Row, Int) => Any])
  final protected var aritiess = List(List.empty[List[Int]])

  // Sorting
  final protected var sortss = List(List.empty[(Int, Int => (RowOLD, RowOLD) => Int)])


  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr = false

  // Query variables
  final protected var filterAttrVars: Map[String, String] = Map.empty[String, String]
  final protected val expectedFilterAttrs                 = mutable.Set.empty[String]
  final protected val availableAttrs                      = mutable.Set.empty[String]

  final protected def getCol(attr: Attr): String = {
    exts(attr.ns).fold(attr.name)(ext => attr.ns + ext + "." + attr.attr)
  }

  final protected def addCast(cast: (Row, Int) => Any): Unit = {
    castss = castss.init :+ (castss.last :+ cast)
  }

  final protected def removeLastCast(): Unit = {
    castss = castss.init :+ castss.last.init
  }

  final protected def replaceCast(cast: (Row, Int) => Any): Unit = {
    removeLastCast()
    addCast(cast)
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

  final protected def getFlatSorters(
    sortss: List[List[(Int, Int => (RowOLD, RowOLD) => Int)]]
  ): List[Int => (RowOLD, RowOLD) => Int] = {
    sortss.flatMap(_.sortBy(_._1).map(_._2))
  }

  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested data structures."
  )
}