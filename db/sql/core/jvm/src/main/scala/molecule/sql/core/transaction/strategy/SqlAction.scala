package molecule.sql.core.transaction.strategy

import java.sql.{Statement, PreparedStatement as PS}
import scala.collection.mutable.ListBuffer

abstract class SqlAction(parent: SqlAction, sqlOps: SqlOps, ent: String) {

  // Strategy execution -----------------------------------------

  def execute: List[Long] = ???

  private[transaction] def process(): Unit = ???


  // Housekeeping ----------------------------------------------------

  private[transaction] val children      = ListBuffer.empty[SqlAction]
  private[transaction] val cols          = ListBuffer.empty[String]
  private[transaction] val mandatoryCols = ListBuffer.empty[String]
  private[transaction] val placeHolders  = ListBuffer.empty[String]
  private[transaction] val joins         = ListBuffer.empty[String]
  private[transaction] val clauses       = ListBuffer.empty[String]
  private[transaction] var ids           = List.empty[Long]
  private[transaction] val rowSetters    = ListBuffer.empty[ListBuffer[PS => Unit]]


  // Resolve helpers ----------------------------------------------------

  def addChild[T <: SqlAction](child: T, addRowSetter: Boolean = false): T = {
    children += child
    if (addRowSetter)
      child.rowSetters += ListBuffer.empty[PS => Unit]
    child
  }

  def addSibling[T <: SqlAction](sibling: T, addRowSetter: Boolean = false): T = {
    parent.children += sibling
    if (addRowSetter) {
      sibling.rowSetters += ListBuffer.empty[PS => Unit]
    }
    sibling
  }

  def replaceSibling[T <: SqlAction](sibling: T, addRowSetter: Boolean = false): T = {
    parent.children.clear()
    parent.children += sibling
    if (addRowSetter) {
      sibling.rowSetters += ListBuffer.empty[PS => Unit]
    }
    sibling
  }

  def setCol(attr: String): Int = setCol(attr, "")
  def setCol(attr: String, typeCast: String): Int = {
    cols += attr
    placeHolders += "?" + typeCast
    cols.length
  }

  def addColSetter(colSetter: PS => Unit): Unit = {
    rowSetters.last += colSetter
  }

  def colCount: Int = cols.length

  def prepare(stmt: String): PS = {
    sqlOps.sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }


  // Execution --------------------------------------

  def insert(enforce: Boolean = true): Unit = {
    //    println(s"\n=========================== ENFORCE: $enforce   rowSetters.size: " + rowSetters.size)
    val stmt = curStmt
    //    println(stmt)
    val ps   = prepare(stmt)

    rowSetters.foreach {
      case rowSetter if rowSetter.nonEmpty =>
        //        println("ROWSETTER SIZE: " + rowSetter.size)
        rowSetter.foreach { colSetter =>
          colSetter(ps)
        }
        ps.addBatch()

      case _ if enforce =>
        //        println("ADD EMPTY")
        ps.addBatch() // Add empty row for joins

      case _ =>
        //        println("NONE")
        () // optional ref is None - add no ref
    }

    // Cache generated ids (various db implementations)
    // Closes prepared statement
    ids = sqlOps.getIds(ps, ent)
    //    println(s"--------------------------- ids: $ids\n")
  }


  // Populate update graph -------------------------

  def completeIds(refIds: Array[List[Long]]): Unit = ???

  def buildExecutionGraph(): Unit = ???


  // Render --------------------------------------

  def curStmt: String = ???

  def render(indent: Int): String = ???

  // Render graph of action executions
  def recurseRender(indent: Int, action: String): String = {
    val cur   = if (indent == -1) Nil else List(curStmt)
    val stmts = children.map(_.render(indent + 1)) ++ cur
    val graph = stmts.map(stmt => stmt.linesIterator
      .mkString("\n  ")
    ).mkString(s"\n  ---------------------------\n  ")
    s"""$action(
       |  $graph
       |)""".stripMargin
  }
}