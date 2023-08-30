package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}
import java.util.UUID
import molecule.base.ast.SchemaAST._
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, DatomicProxy}
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.mutable
import scala.concurrent.Future

trait JdbcBase_JVM extends JdbcDataType_JVM with ModelUtils {

  // Override on instantiation
  protected val sqlConn: java.sql.Connection

  var level = 0
  def indent(level: Int) = "  " * level
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var doPrint              = false
  protected var initialNs            = ""
  protected var curRefPath           = List("0")
  protected var inserts              = List.empty[(List[String], List[String])]
  protected var updates              = List.empty[(List[String], List[String])]
  protected var placeHolders         = List.empty[String]
  protected var joins                = List.empty[(List[String], String, String, List[String], List[String])]
  protected var ids                  = Seq.empty[Long]
  protected val updateCols           = mutable.Map.empty[List[String], List[String]]
  protected var uniqueFilterElements = List.empty[Element]
  protected var filterElements       = List.empty[Element]
  protected val paramIndexes         = mutable.Map.empty[(List[String], String), Int]
  protected val colSettersMap        = mutable.Map.empty[List[String], List[Setter]]
  protected val rowSettersMap        = mutable.Map.empty[List[String], List[Setter]]
  protected val tableDatas           = mutable.Map.empty[List[String], Table]
  protected var manualTableDatas     = List.empty[Table]
  protected var joinTableDatas       = List.empty[JoinTable]
  protected val rightCountsMap       = mutable.Map.empty[List[String], List[Int]]


  protected def addColSetter(refPath: List[String], colSetter: Setter) = {
    // Cache colSetter for this table
    colSettersMap.get(refPath).fold[Unit](
      colSettersMap += refPath -> List(colSetter)
    )(colSetters =>
      colSettersMap(refPath) = colSetters :+ colSetter
    )
  }

  protected def printValue(
    level: Int,
    ns: String,
    attr: String,
    tplIndex0: Int,
    paramIndex: Int,
    value: Any
  ): Unit = {
    if (doPrint) {
      val fullAttr = s"$ns.$attr"
      val pad      = padS(14, fullAttr)
      val tplIndex = if (tplIndex0 == -1) "-" else tplIndex0
      println(s"${indent(level)}$fullAttr$pad tplIndex: $tplIndex   paramIndex: $paramIndex   value: " + value)
    }
  }


  // "Connection pool" ---------------------------------------------

  // todo: offer real solution
  protected val connectionPool = mutable.HashMap.empty[UUID, Future[JdbcConn_jvm]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[JdbcConn_jvm] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      //      conn.updateAdhocDbView(proxy.adhocDbView)
      //      conn.updateTestDbView(proxy.testDbView, proxy.testDbStatus)
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    // logger.debug("connectionPool.size: " + connectionPool.size)
    futConnTimeAdjusted
  }

  protected def getFreshConn(proxy: ConnProxy): Future[JdbcConn_jvm] = {
    proxy match {
      case proxy@DatomicProxy(protocol, dbIdentifier, _, _, _, _, _, _, _, _, _) =>
        //        protocol match {
        //          case "mem" =>
        //            JdbcHandler.recreateDbFromEdn(proxy, protocol, dbIdentifier)
        //              .recover {
        //                case exc: Throwable => throw ExecutionError(exc.getMessage)
        //              }
        //
        //          case "free" | "dev" | "pro" =>
        //            Future(JdbcHandler.connect(proxy, protocol, dbIdentifier))
        //              .recover {
        //                case exc: Throwable => throw ExecutionError(exc.getMessage)
        //              }
        ???

      case other => Future.failed(
        ExecutionError(s"\nCan't serve Peer protocol `$other`.")
      )
    }
  }

  protected def getRefResolver[T](ns: String, refAttr: String, refNs: String, card: Card): T => Unit = {
    val joinTable = s"${ns}_${refAttr}_$refNs"
    val curPath   = curRefPath
    //    println("curRefPath 0: " + curPath)

    if (inserts.exists(_._1 == curPath)) {
      // Add ref attribute to current namespace
      inserts = inserts.map {
        case (path, cols) if card == CardOne && path == curPath =>
          paramIndexes += (curPath, refAttr) -> (cols.length + 1)
          (curPath, cols :+ refAttr)

        case other => other
      }

    } else if (card == CardOne) {
      // Make card-one ref from current empty namespace
      paramIndexes += (curPath, refAttr) -> 1
      inserts = inserts :+ (curPath, List(refAttr))

    } else if (card == CardSet) {
      // ref to join table
      // Make card-many ref from current empty namespace
      inserts = inserts :+ (curPath, Nil)
    }

    lazy val joinPath = curPath :+ joinTable

    //    println("joinPath: " + joinPath)


    if (card == CardSet) {
      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List(id1, id2)) +: inserts
    }

    // Start new ref table
    val refPath = curPath ++ List(refAttr, refNs)
    curRefPath = refPath
    inserts = inserts :+ (refPath, Nil)

    //    println("curRefPath 1: " + curRefPath)
    //    println("-----")
    //    inserts.foreach(println)
    //    println("-----")

    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes(curPath, refAttr)
      (_: T) => {
        val colSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId = idsMap(refPath)(rowIndex)
          //          printValue(level, ns, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
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
