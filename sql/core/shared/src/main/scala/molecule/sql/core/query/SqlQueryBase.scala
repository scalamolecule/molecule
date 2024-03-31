package molecule.sql.core.query

import molecule.base.ast.Card
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.query.Model2QueryBase
import molecule.core.util.JavaConversions
import molecule.sql.core.javaSql.{PrepStmt, ResultSetInterface}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


trait SqlQueryBase extends Model2QueryBase with BaseHelpers with JavaConversions {

  type RS = ResultSetInterface
  type ParamIndex = Int
  type NestedTpls = List[Any]

  def getRowCount(resultSet: RS): Int = {
    resultSet.last()
    val size = resultSet.getRow
    resultSet.beforeFirst()
    size
  }

  // Lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Card, String, Seq[String])]

  private var index = 0
  def getIndex = {
    index += 1
    index
  }

  // Main query
  final protected val select      = new ListBuffer[String]
  final protected var distinct    = true
  final protected var from        = ""
  final protected val joins       = new ListBuffer[(String, String, String, String, String)]
  final protected val tempTables  = ListBuffer.empty[String]
  final protected val notNull     = new ListBuffer[String]
  final protected val where       = new ListBuffer[(String, String)]
  final protected val groupBy     = new mutable.LinkedHashSet[String]
  final protected val having      = new mutable.LinkedHashSet[String]
  final protected var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final protected var aggregate   = false
  final protected val groupByCols = new mutable.LinkedHashSet[String]
  final protected var hardLimit   = 0
  final           val inputs      = new ListBuffer[PrepStmt => Unit]

  // Input args and cast lambdas
  final           var castss      = List(List.empty[(RS, Int) => Any])
  final           var aritiess    = List(List.empty[List[Int]])
  final           var isNestedMan = false
  final           var isNestedOpt = false
  final protected val nestedIds   = new ArrayBuffer[String]
  final protected var level       = 0
  final protected val args        = new ArrayBuffer[AnyRef]


  final protected var prevRefNss = Set.empty[String]
  final protected val preExts    = mutable.Map.empty[List[String], Option[String]]
  final protected val exts       = mutable.Map.empty[List[String], Option[String]]

  // Current path: List(Ns, refAttrNs2, Ns2, refAttrNs3, Ns3 ...)
  final protected var path = List.empty[String]

  // Query variables
  final protected var filterAttrVars = Map.empty[List[String], String]


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

  final protected def addCast(cast: (RS, Int) => Any): Unit = {
    castss = castss.init :+ (castss.last :+ cast)
  }

  final protected def removeLastCast(): Unit = {
    castss = castss.init :+ castss.last.init
  }

  final protected def replaceCast(cast: (RS, Int) => Any): Unit = {
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

  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )

  def noCollectionMatching(attr: Attr) = {
    val a = attr.cleanName
    throw ModelError(s"Matching collections ($a) not supported in queries.")
  }

  def noApplyNothing(attr: Attr): Unit = {
    val a = attr.cleanName
    throw ModelError(
      s"Applying nothing to mandatory attribute ($a) is reserved for updates to retract."
    )
  }
}