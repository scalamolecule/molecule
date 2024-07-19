package molecule.sql.core.transaction.strategy

import java.sql.{Connection, Statement}
import molecule.sql.core.transaction.strategy.insert.InsertNested
import scala.collection.mutable.ListBuffer

abstract class SqlAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlBase {

  // Strategy execution
  def execute: List[Long]


  protected val children     = ListBuffer.empty[SqlAction]
  protected val cols         = ListBuffer.empty[String]
  protected val placeHolders = ListBuffer.empty[String]
  protected val postSetters  = ListBuffer.empty[List[Long] => Unit]



  private[strategy] var rowSetters: ListBuffer[ListBuffer[PS => Unit]] =
    ListBuffer.empty[ListBuffer[PS => Unit]]

  // For inspection
  private[strategy] val postStmts = ListBuffer.empty[String]

  // Keep track of nested counts for joins
  private var nestedCounts = List.empty[Int]
  def addNestedCount(n: Int): Unit = {
    nestedCounts = nestedCounts :+ n
  }
  def getNestedCounts = nestedCounts

  def nextRow(): Unit = {
    rowSetters += ListBuffer.empty[PS => Unit]
    //    println(s"$table nextRow")
    children.foreach {
      case _: InsertNested => () // use nextNestedRow in addNested
      case other           => other.nextRow()
    }
  }
  def nextNestedRow(): Unit = {
    rowSetters += ListBuffer.empty[PS => Unit]
    children.foreach(_.nextRow())
  }

  def addChild[T <: SqlAction](child: T, addRowSetter: Boolean = false): T = {
    children += child
    if (addRowSetter)
      child.rowSetters += ListBuffer.empty[PS => Unit]
    child
  }

  def paramIndex = cols.length + 1

  def paramIndex(attr: String): Int = paramIndex(attr, "")
  def paramIndex(attr: String, typeCast: String): Int = {
    cols += attr
    placeHolders += "?" + typeCast
    cols.length
  }

  def addColSetter(colSetter: PS => Unit): Unit = {
    rowSetters.last += colSetter
    //    rowSetters.foreach { rowSetter =>
    //      println(rowSetter.mkString(s"$table add\n  ", "\n  ", "\n  --"))
    //    }
  }

  def addPostSetter(postSetter: List[Long] => Unit): Unit = {
    postSetters += postSetter
  }
  def getPostSetters: ListBuffer[List[Long] => Unit] = postSetters


  def prepare(stmt: String): PS = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }

  def render(indent: Int): String = ???

  def curStmt: String

  def recurseRender(indent: Int, strategy: String): String = {
    val p1        = "  " * indent
    val p2        = p1 + "  "
    val stmts     = children.map(_.render(indent + 1)) ++ (curStmt +: postStmts)
    val stmtGraph = stmts.map(stmt => stmt.linesIterator.mkString("\n  " + p1)
    ).mkString(s"\n$p2---------------------------\n$p2")
    s"""$p1$strategy(
       |$p1  $stmtGraph
       |$p1)""".stripMargin
  }
}