package molecule.sql.core.query

import molecule.base.ast.Card
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import molecule.sql.core.javaSql.{PrepStmt, ResultSetInterface}
import molecule.sql.core.query.casting.{CastStrategy, CastTuple}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


trait SqlQueryBase extends BaseHelpers with JavaConversions {

  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any

  // Lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  // Main query
  final protected val select      = new ListBuffer[String]
  final protected var select2     = Map.empty[Int, (List[(String, String, String, List[String])], Set[String]) => String]
  final protected var distinct    = true
  final protected var from        = ""
  final protected val joins       = new ListBuffer[(String, String, String, List[String])]
  final protected val tempTables  = ListBuffer.empty[String]
  final protected val where       = new ListBuffer[(String, String)]
  final protected val groupBy     = new mutable.LinkedHashSet[String]
  final protected val having      = new mutable.LinkedHashSet[String]
  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final protected var aggregate   = false
  final protected val groupByCols = new mutable.LinkedHashSet[String]
  final protected var hardLimit   = 0
  final           val inputs      = new ListBuffer[PrepStmt => Unit]

  final var casts: CastStrategy = CastTuple()

  final protected val nestedIds = new ArrayBuffer[String]
  final protected var level     = 0

  final protected var whereSplit = 0

  final protected var prevRefNss = Set.empty[String]
  final protected val preExts    = mutable.Map.empty[List[String], Option[String]]
  final protected val exts       = mutable.Map.empty[List[String], Option[String]]

  //  override final var path = List.empty[String]
  final var path = List.empty[String]


  // Query variables
  final protected var filterAttrVars = Map.empty[List[String], String]

  def setNotNull(col: String) = {
    where += ((col, "IS NOT NULL"))
  }
  def setNull(col: String) = {
    where += ((col, "IS NULL"))
  }
  def unsetNotNull(col: String) = {
    where -= ((col, "IS NOT NULL"))
  }

  def addPredicatesToLastLeftJoin(): Unit = {
    val (leftJoin, table, as, colsMatch) = joins.last
    val leftJoinPredicates               = where.drop(whereSplit).map { case (col, pred) => s"$col $pred" }
    val updatedLeftJoin                  = (leftJoin, table, as, colsMatch ++ leftJoinPredicates)
    joins.update(joins.length - 1, updatedLeftJoin)
    where.takeInPlace(whereSplit)
  }

  private var index = 0
  def getIndex = {
    index += 1
    index
  }

  def getRowCount(resultSet: RS): Int = {
    resultSet.last()
    val size = resultSet.getRow
    resultSet.beforeFirst()
    size
  }

  final protected def getCol(attr: Attr, path: List[String] = path): String = {
    val ext = getOptExt(path).getOrElse("")
    s"${attr.ns}$ext.${attr.attr}"
  }

  protected def getOptExt(path: List[String] = path): Option[String] = {
    exts.getOrElse(path, Option.empty[String])
  }

  protected def handleRef(refAttr: String, refNs: String): Unit = {
    path = path ++ List(refAttr, refNs)
    if (prevRefNss.contains(refNs)) {
      preExts(path) = preExts.getOrElse(path, Some("_" + refAttr))
    } else {
      preExts(path) = preExts.getOrElse(path, None)
    }
    prevRefNss += refNs
  }

  protected def handleBackRef(): Unit = {
    path = path.dropRight(2)
  }
}