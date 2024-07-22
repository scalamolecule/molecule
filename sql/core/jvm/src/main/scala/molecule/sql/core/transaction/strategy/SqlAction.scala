package molecule.sql.core.transaction.strategy

import java.sql.{Connection, Statement}
import scala.collection.mutable.ListBuffer

abstract class SqlAction(
  parent: SqlAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlBase {

  // Strategy execution
  def executeRoot: List[Long] = ???
  def execute(): Unit = ???


  // todo, use ArrayBuilders instead of ListBuffer
//    private[transaction] val children1     = mutable.ArrayBuilder.make[SqlAction]

  private[transaction] val children     = ListBuffer.empty[SqlAction]
  private[transaction] val cols         = ListBuffer.empty[String]
  private[transaction] val placeHolders = ListBuffer.empty[String]
  private[transaction] val postSetters  = ListBuffer.empty[List[Long] => Unit]

  private[transaction] var ids = List.empty[Long]

  private[transaction] val rowSetters: ListBuffer[ListBuffer[PS => Unit]] =
    ListBuffer.empty[ListBuffer[PS => Unit]]

  // For inspection
  private[transaction] val postStmts = ListBuffer.empty[String]


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

  def paramIndex(attr: String): Int = paramIndex(attr, "")
  def paramIndex(attr: String, typeCast: String): Int = {
    cols += attr
    placeHolders += "?" + typeCast
    cols.length
  }

  def addColSetter(colSetter: PS => Unit): Unit = {
    rowSetters.last += colSetter
  }

  def addPostSetter(postSetter: List[Long] => Unit): Unit = {
    postSetters += postSetter
  }
  def getPostSetters: ListBuffer[List[Long] => Unit] = postSetters


  def prepare(stmt: String): PS = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }


  // Helpers -------------------------------------

  def insertJoins(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    addPostSetter(
      (parentIds: List[Long]) => {
        val leftId = parentIds.head
        val ps     = prepare(sqlOps.insertJoinStmt(ns, refAttr, refNs))
        val it     = refIds.iterator
        while (it.hasNext) {
          ps.setLong(1, leftId)
          ps.setLong(2, it.next())
          ps.addBatch()
        }
        ps.executeBatch()
        ps.close()
      }
    )
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


  // Render --------------------------------------

  def render(indent: Int): String = ???

  def curStmt: String = ???

  def insert(): Unit = {
    val ps = prepare(curStmt)
    //    println(s"++++++++  $ns  " + rowSetters.length + "  " + curStmt.linesIterator.next())
    //    rowSetters.foreach(r => println(r.length))
    // Populate prepared statement
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