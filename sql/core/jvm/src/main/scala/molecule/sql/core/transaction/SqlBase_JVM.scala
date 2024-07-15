package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.UUID
import molecule.base.ast._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Delete, Update}
import molecule.core.marshalling.ConnProxy
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase}
import molecule.sql.core.transaction.op.DbOps
import molecule.sql.core.transaction.strategy.TxStrategy
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait SqlBase_JVM extends SqlDataType_JVM with ModelUtils with BaseHelpers {

  // Implement/set for each database ----------------------------

  protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = ???

  lazy val sqlConn: java.sql.Connection = ???

  final var save: TxStrategy = null // set in resolvers

  implicit lazy val dbOps: DbOps = new DbOps

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
  protected var inserts          = List.empty[(List[String], List[(String, String)])]
  protected var placeHolders     = List.empty[String]
  protected var joins            = List.empty[(List[String], String, String, List[String], List[String])]
  protected var ids              = Seq.empty[Long]
  protected var cols             = ListBuffer.empty[String]
  protected val updateCols       = mutable.Map.empty[List[String], List[String]]
  protected var filterElements   = List.empty[Element]
  protected val paramIndexes     = mutable.Map.empty[(List[String], String), Int]
  protected val colSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val rowSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val tableDatas       = mutable.Map.empty[List[String], Table]
  protected var manualTableDatas = List.empty[Table]
  protected var joinTableDatas   = List.empty[JoinTable]
  protected val rightCountsMap   = mutable.Map.empty[List[String], List[Int]]

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

  protected def getRefResolver[T](
    ns: String, refAttr: String, refNs: String, card: Card
  ): T => Unit = {
    val joinTablePath = ss(ns, refAttr, refNs)
    val curPath       = curRefPath

    if (inserts.exists(_._1 == curPath)) {
      // Add ref attribute to current namespace
      inserts = inserts.map {
        case (path, cols) if card == CardOne && path == curPath =>
          paramIndexes += (curPath, refAttr) -> (cols.length + 1)
          (curPath, cols :+ (refAttr -> ""))

        case other => other
      }

    } else if (card == CardOne) {
      // Make card-one ref from current empty namespace
      paramIndexes += (curPath, refAttr) -> 1
      inserts = inserts :+ (curPath -> List(refAttr -> ""))

    } else if (card == CardSet) {
      // ref to join table
      // Make card-many ref from current empty namespace
      inserts = inserts :+ (curPath -> Nil)
    }

    lazy val joinPath = curPath :+ joinTablePath

    if (card == CardSet) {
      // join table with single row (treated as normal insert
      // since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs)
        (ss(ns, "1_id"), ss(refNs, "2_id"))
      else
        (ss(ns, "id"), ss(refNs, "id"))
      // When insertion order is reversed, this join table will be set
      // after left and right has been inserted
      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts
    }

    // Start new ref table
    val refPath = curPath ++ List(refAttr, refNs)
    curRefPath = refPath
    inserts = inserts :+ (refPath -> Nil)


    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes(curPath -> refAttr)
      var rowIndex   = 0
      (_: T) => {
        val colSetter: Setter = {
          (ps: PS, idsMap: IdsMap, _: RowIndex) => {
            val refId = idsMap(refPath)(rowIndex)
            rowIndex += 1
            ps.setLong(paramIndex, refId)
          }
        }
        addColSetter(curPath, colSetter)
      }

    } else {
      (_: T) => {
        // Empty row if no attributes in namespace in order to have an id for join table
        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.addBatch()
          }
          addColSetter(curPath, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          val refId2 = idsMap(refPath)(rowIndex)
          //          println("-----------")
          //          println("id1: " + refId1)
          //          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }
}
