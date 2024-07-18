package molecule.sql.core.transaction.strategy

import java.sql.{Connection, Statement}
import molecule.sql.core.transaction.strategy.insert.InsertNested
import scala.collection.mutable.ListBuffer

abstract class SqlAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  table: String
) extends SqlBase {

  protected val refs = ListBuffer.empty[SqlAction]
  val cols = ListBuffer.empty[String]
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

  def execute: List[Long]


  def nextRow(): Unit = {
    rowSetters += ListBuffer.empty[PS => Unit]
    //    println(s"$table nextRow")
    //    refs.foreach(_.nextRow())
    refs.foreach {
      case _: InsertNested => () //
      case other           => other.nextRow()
    }
  }
  def nextNestedRow(): Unit = {
    rowSetters += ListBuffer.empty[PS => Unit]
    refs.foreach(_.nextRow())
    //    refs.foreach {
    //      case nested: InsertNested => nested.nextRow()
    //      case other                => ()
    //      //          case other           => other.nextRow()
    //    }
  }

  def paramIndex = cols.length + 1

  def paramIndex(
    attr: String, placeHolder: String = "?", typeCast: String = ""
  ): Int = {
    cols += attr
    placeHolders += placeHolder + typeCast
    cols.length
  }

  def add(colSetter: PS => Unit): Unit = {
    rowSetters.last += colSetter

    //    rowSetters.foreach { rowSetter =>
    //      println(rowSetter.mkString(s"$table add\n  ", "\n  ", "\n  --"))
    //    }
  }

  def addPostSetter(postSetter: List[Long] => Unit): Unit = {
    postSetters += postSetter
  }
  def getPostSetters: ListBuffer[List[Long] => Unit] = postSetters


  def prepStmt(stmt: String): PS = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }

  def render(indent: Int): String = ???

  def recurseRender(indent: Int, strategy: String): String = {
    val p1             = "  " * indent
    val p2             = p1 + "  "
    val strategies     = refs.map(_.render(indent + 1)) ++
      (sqlOps.insertStmt(table, cols, placeHolders) +: postStmts)
    val executionGraph = strategies.map(stmt =>
      stmt.linesIterator.mkString("\n  " + p1)
    ).mkString(s"\n$p2---------------------------\n$p2")
    s"""$p1$strategy(
       |$p1  $executionGraph
       |$p1)""".stripMargin
  }
}