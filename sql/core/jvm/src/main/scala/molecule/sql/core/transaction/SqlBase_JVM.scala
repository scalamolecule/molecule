package molecule.sql.core.transaction

import java.sql.Statement
import java.util.UUID
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Delete, Update}
import molecule.core.marshalling.ConnProxy
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase}
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait SqlBase_JVM extends SqlDataType_JVM with ModelUtils with BaseHelpers {

  // Implement/set for each database ----------------------------

  protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = ???

  lazy val sqlConn: java.sql.Connection = ???

  implicit lazy val sqlOps: SqlOps = new SqlOps

  def getModel2SqlQuery(
    elements: List[Element]
  ): Model2SqlQuery with SqlQueryBase = ???

  def update_getData(conn: JdbcConn_JVM, update: Update): Data = ???

  def delete_getExecutioner(
    conn: JdbcConn_JVM, delete: Delete
  ): Option[() => List[Long]] = ???


  // Prepared statement buildup -------------------------------

  lazy protected val defaultValues = "(id) VALUES (DEFAULT)"

  var level = 0
  override def indent(level: Int) = "  " * level
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var doPrint          = false
  protected var initialNs        = ""
  protected var curRefPath       = List("0")
  protected var placeHolders     = List.empty[String]
  protected var joins            = List.empty[(List[String], String, String, List[String], List[String])]
  protected var ids              = Seq.empty[Long]
  protected var cols             = ListBuffer.empty[String]
  protected val updateCols       = mutable.Map.empty[List[String], List[String]]
  protected var filterElements   = List.empty[Element]
  protected val colSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val rowSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val tableDatas       = mutable.Map.empty[List[String], Table]
  protected var manualTableDatas = List.empty[Table]

  def transactionStmt(stmt: String) = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }

  protected def addColSetter(refPath: List[String], colSetter: Setter) = {
    // Cache colSetter for this table
    colSettersMap.get(refPath).fold[Unit](
      colSettersMap += refPath -> List(colSetter)
    )(colSetters =>
      colSettersMap(refPath) = colSetters :+ colSetter
    )
  }


  // "Connection pool" ---------------------------------------------

  // todo: use Hikari or other real connection pool solution
  private val connectionPool = mutable.HashMap.empty[UUID, Future[JdbcConn_JVM]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getJdbcConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    // logger.debug("connectionPool.size: " + connectionPool.size)
    futConnTimeAdjusted
  }
}
