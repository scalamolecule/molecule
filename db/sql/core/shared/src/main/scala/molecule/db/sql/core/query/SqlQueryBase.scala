package molecule.db.sql.core.query

import molecule.base.metaModel.Cardinality
import molecule.base.util.BaseHelpers
import molecule.core.dataModel.*
import molecule.core.util.JavaConversions
import molecule.db.sql.core.javaSql.{PrepStmt, ResultSetInterface}
import molecule.db.sql.core.query.casting.strategy.{CastStrategy, CastTuple}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

trait SqlQueryBase extends BaseHelpers with JavaConversions {

  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any

  // Lookup original type of aggregate attributes
  final protected var attrMap = Map.empty[String, (Cardinality, String, Seq[String])]

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

  final val binders    = new ListBuffer[PrepStmt => Unit]
  final var bindIndex  = -1
  final val bindValues = new ListBuffer[Any]

  final var castStrategy: CastStrategy = CastTuple()

  final protected val nestedIds = new ArrayBuffer[String]
  final protected var level     = 0

  final protected var whereSplit = 0

  final protected var prevRefs = Set.empty[String]
  final protected val preExts  = mutable.Map.empty[List[String], Option[String]]
  final protected val exts     = mutable.Map.empty[List[String], Option[String]]

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

    val firstPredicates = where.dropRight(where.length - whereSplit)
    where.clear()
    where ++= firstPredicates
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
    s"${attr.ent}$ext.${attr.attr}"
  }

  protected def getOptExt(path: List[String] = path): Option[String] = {
    exts.getOrElse(path, Option.empty[String])
  }

  protected def handleRef(refAttr: String, ref: String): Unit = {
    path = path ++ List(refAttr, ref)
    if (prevRefs.contains(ref)) {
      preExts(path) = preExts.getOrElse(path, Some("_" + refAttr))
    } else {
      preExts(path) = preExts.getOrElse(path, None)
    }
    prevRefs += ref
  }

  protected def handleBackRef(): Unit = {
    path = path.dropRight(2)
  }


  protected def hasEmptyValue(row: RS, i: Int, v: Any): Boolean = {
    v != None && (row.getObject(i) match {
      case null                          => true
      case set: Set[_] if set.isEmpty    => true
      case seq: Seq[_] if seq.isEmpty    => true
      case map: Map[_, _] if map.isEmpty => true

      case sqlArray: java.sql.Array
        if sqlArray.getArray.asInstanceOf[Array[?]].head == null =>
        true

      case "[null]" => true // json array with empty Seq

      case x =>
        //          println(s"-------- $x  " + x.getClass.getSimpleName)
        false
    })
  }

  protected def hasEmpty(row: RS, firstIndex: Int, vs: List[Any]): Boolean = {
    var i = firstIndex - 1
    vs.exists { v =>
      i += 1
      (row.getObject(i) match {
        case null                          => true
        case set: Set[_] if set.isEmpty    => true
        case seq: Seq[_] if seq.isEmpty    => true
        case map: Map[_, _] if map.isEmpty => true

        case sqlArray: java.sql.Array
          if sqlArray.getArray.asInstanceOf[Array[?]].head == null =>
          true

        case "[null]" => true // json array with empty Seq

        case x =>
          //          println(s"-------- $x  " + x.getClass.getSimpleName)
          false
      }) && v != None
    }
  }

  def addBinding(
    col: String,
    bind: (PrepStmt, Int, Int, Any) => Unit,
    expr: String,
  ): Unit = {
    where += ((col, expr))
    val paramIndex = binders.length + 1
    bindIndex = bindIndex + 1
    val bindIndexStable = bindIndex
    binders += ((ps: PrepStmt) => bind(ps, paramIndex, bindIndexStable, bindValues(bindIndexStable)))
  }
}