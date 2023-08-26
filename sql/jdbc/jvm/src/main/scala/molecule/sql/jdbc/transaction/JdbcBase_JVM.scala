package molecule.sql.jdbc.transaction

import java.lang.{Boolean => jBoolean}
import java.sql.{PreparedStatement => PS}
import java.util.{UUID, List => jList}
import clojure.lang.Keyword
import molecule.base.ast.SchemaAST._
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, DatomicProxy}
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait JdbcBase_JVM extends JdbcDataType_JVM with ModelUtils {

  // Override on instantiation
  protected val sqlConn: java.sql.Connection

  //  protected var level    = 0
  //  protected var firstRow = true

  var level = 0
  def indent(level: Int) = "  " * level

  protected var doPrint = false
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var compositeGroup = 0
  protected var initialId      = 0L
  protected var initialNs      = ""

  // Dynamic ref path of current level and branch (each backref initiates a new branch)
  protected var curRefPath = List("0")

  // Ordered tables to have data inserted
  // refPath, ns, selfRef, cols
  protected var inserts = List.empty[(List[String], List[String])]
  protected var joins   = List.empty[(List[String], String, String, List[String], List[String])]

  protected var ids            = Seq.empty[Long]
  protected val updateCols     = ListBuffer.empty[String]
  protected var filterElements = List.empty[Element]


  // PreparedStatement param indexes for each (table, col) coordinate
  protected val paramIndexes   = mutable.Map.empty[(List[String], String), Int]
  protected val colSettersMap  = mutable.Map.empty[List[String], List[Setter]]
  protected val rowSettersMap  = mutable.Map.empty[List[String], List[Setter]]
  protected val tableDatas     = mutable.Map.empty[List[String], Table]
  protected var joinTableDatas = List.empty[JoinTable]
  protected val insertIndexes  = mutable.Map.empty[List[String], Int]
  protected val rightCountsMap = mutable.Map.empty[List[String], List[Int]]

  //  final protected var aritiess  = List(List.empty[List[Int]])


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


  protected var nsFull       : String              = ""
  protected var part         : String              = ""
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e            : AnyRef              = "" // Long or String (#db/id[db.part/user -1])
  protected var e0           : AnyRef              = ""
  protected var stmt         : jList[AnyRef]       = null
  protected var backRefs     : Map[String, AnyRef] = Map.empty[String, AnyRef]
  protected val prevRefs     : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected var hasComposites: Boolean             = false

  protected lazy val add           = kw("db", "add")
  protected lazy val retract       = kw("db", "retract")
  protected lazy val retractEntity = kw("db", "retractEntity")
  protected lazy val dbId          = kw("db", "id")
  protected lazy val datomicTx     = "datomic.tx"

  protected lazy val bigInt2java  = (v: Any) => v.asInstanceOf[BigInt].bigInteger
  protected lazy val bigDec2java  = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal
  protected lazy val char2java    = (v: Any) => v.toString
  protected lazy val byte2java    = (v: Any) => v.asInstanceOf[Byte].toInt
  protected lazy val short2java   = (v: Any) => v.asInstanceOf[Short].toInt
  protected lazy val boolean2java = (v: Any) => v.asInstanceOf[Boolean].asInstanceOf[jBoolean]

  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)

  protected def appendStmt(
    op: Keyword,
    e: AnyRef,
    a: Keyword,
    v: AnyRef,
  ): Unit = {
    //    val addStmt = stmtList
    //    addStmt.add(op)
    //    addStmt.add(e)
    //    addStmt.add(a)
    //    addStmt.add(v)
    //    stmts.add(addStmt)
  }
  protected def addRetractEntityStmt(id: AnyRef): Unit = {
    //    val stmt = new jArrayList[AnyRef](2)
    //    stmt.add(retractEntity)
    //    stmt.add(id)
    //    stmts.add(stmt)
  }


  // Connection pool ---------------------------------------------

  // todo: real solution
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

  protected def getCompositeJoinResolver[T](compositeNs: String, initialRefPath: List[String]): T => Unit = {
    val curPath = curRefPath

    //    println("curRefPath 0: " + curPath)

    lazy val compositeJoinPath = curPath :+ "CompositeJoin"

    // Start composite join table with single row (treated as normal insert since there's only 1 join per row)
    // When insertion order is reversed, this join table will be set after left and right has been inserted
    inserts = (compositeJoinPath, List("a", "b", "a_id", "b_id")) +: inserts

    //    println("joinPath: " + compositeJoinPath)

    // Start new composite table
    val compositePath = curPath ++ List("CompositeJoin", compositeNs)
    curRefPath = compositePath

    //    println("curRefPath 1: " + curPath)
    //    println("-----")
    //    inserts.foreach(println)
    //    println("-----")

    (_: T) => {
      // Composite join table setter
      val compositeJoinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        val initialId   = idsMap(initialRefPath)(rowIndex)
        val compositeId = idsMap(compositePath)(rowIndex)
        //        println("----- idsMap: ----------")
        //        println(idsMap.mkString("\n"))
        //        println("----------- " + compositePath)
        //        println("id1: " + initialId)
        //        println("id2: " + compositeId)
        ps.setString(1, initialNs) // All composite groups relate to initial namespace
        ps.setString(2, compositeNs)
        ps.setLong(3, initialId) // All composite groups relate to initial namespace id
        ps.setLong(4, compositeId)
        ps.addBatch()

      }
      addColSetter(compositeJoinPath, compositeJoinSetter)
    }
  }
}
