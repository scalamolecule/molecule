package molecule.sql.core.transaction.strategy

import java.sql.{Connection, Statement}
import scala.collection.mutable.ListBuffer

abstract class SqlAction(
  parent: SqlAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlBase {

  // Strategy execution -----------------------------------------

  def executeRoot: List[Long] = ???

  private[transaction] def execute(): Unit = ???

  // update
  private[transaction] def buildIdsQuery(): Unit = ???
  private[transaction] def getIds(): Unit = ???
  private[transaction] def distributeIds(): Unit = ???


  // Housekeeping ----------------------------------------------------

  private[transaction] val children      = ListBuffer.empty[SqlAction]
  private[transaction] val cols          = ListBuffer.empty[String]
  private[transaction] val mandatoryCols = ListBuffer.empty[String]
  private[transaction] val placeHolders  = ListBuffer.empty[String]
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

  def setCol(attr: String): Int = setCol(attr, "")
  def setCol(attr: String, typeCast: String): Int = {
    cols += attr
    placeHolders += "?" + typeCast
    cols.length
  }

  def addColSetter(colSetter: PS => Unit): Unit = {
    rowSetters.last += colSetter
  }


  def prepare(stmt: String): PS = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }

  def insertJoins(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    //    addPostSetter(
    //      (parentIds: List[Long]) => {
    //        val leftId = parentIds.head
    //        val ps     = prepare(sqlOps.insertJoinStmt(ns, refAttr, refNs))
    //        val it     = refIds.iterator
    //        while (it.hasNext) {
    //          ps.setLong(1, leftId)
    //          ps.setLong(2, it.next())
    //          ps.addBatch()
    //        }
    //        ps.executeBatch()
    //        ps.close()
    //      }
    //    )
    ???
  }

  def deleteJoins(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    if (refIds.nonEmpty) {
      //      val deleteStmt = sqlOps.deleteStmt(ns, )
      //      addPostSetter(
      //        (parentIds: List[Long]) => {
      //          val leftId = parentIds.head
      //          val ps     = prepare(sqlOps.deleteStmt(ns, refAttr, refNs))
      //          val it     = refIds.iterator
      //          while (it.hasNext) {
      //            ps.setLong(1, leftId)
      //            ps.setLong(2, it.next())
      //            ps.addBatch()
      //          }
      //          ps.executeBatch()
      //          ps.close()
      //        }
      //      )
      ???
    }
  }


  // Save/Insert execution --------------------------------------

  def insert(): Unit = {
    val ps = prepare(curStmt)
    rowSetters.foreach {
      case rowSetter if rowSetter.nonEmpty =>
        rowSetter.foreach { colSetter =>
          colSetter(ps)
        }
        ps.addBatch()

      case _ => ps.addBatch() // Add empty row (for joins)
    }
    // Cache generated ids (various db implementations)
    // Closes prepared statement
    ids = sqlOps.getIds(sqlConn, ns, ps)
  }


  //  def distributeIds(refIds: Array[List[Long]]): Unit = ???


  def distributeIds(refIds: Array[List[Long]]): Unit = {
    children.foreach(_.distributeIds(refIds.tail))
    ids = refIds.head
  }

  // Render --------------------------------------

  def render(indent: Int): String = ???

  def curStmt: String = ???

  // Render graph of action executions
  def recurseRender(indent: Int, action: String): String = {
    val cur   = if (indent == -1) Nil else List(curStmt)
    val stmts = children.map(_.render(indent + 1)) ++ cur
    val graph = stmts.map(stmt => stmt.linesIterator
      .mkString("\n   ")
    ).mkString(s"\n   ---------------------------\n   ")
    s"""$action(
       |   $graph
       |)""".stripMargin
  }
}