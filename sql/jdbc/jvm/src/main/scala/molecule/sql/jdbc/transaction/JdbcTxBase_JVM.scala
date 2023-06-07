package molecule.sql.jdbc.transaction

import java.lang.{Boolean => jBoolean}
import java.sql.PreparedStatement
import java.util
import java.util.{UUID, ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.ast.SchemaAST.padS
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, DatomicProxy}
import molecule.core.util.Executor._
import molecule.core.util.{ModelUtils, fns}
import molecule.sql.jdbc.facade.{JdbcConn_jvm, JdbcHandler_jvm}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait JdbcTxBase_JVM extends JdbcDataType_JVM with ModelUtils {

  // Override on instantiation
  protected val sqlConn: java.sql.Connection

  //  protected var level    = 0
  protected var firstRow = true

//  // Dynamic ref path of current level and branch (each backref initiates a new branch)
//  protected var curRefPath = List("0")
//
//  // Ordered tables to have data inserted
//  // refPath, ns, selfRef, cols
//  //  protected var inserts = List.empty[(List[String], String, List[String], List[TableInsert])]
//  //  protected var inserts2 = List.empty[(Int, List[String], String, List[String])]
//  protected var inserts = List.empty[(List[String], List[String])]
//  protected var joins   = List.empty[(String, String, String, Int, Int)]
//
//
//  // PreparedStatement param indexes for each (table, col) coordinate
//  protected val paramIndexes     = mutable.Map.empty[(List[String], String), Int]
//  protected val colSettersMap    = mutable.Map.empty[List[String], List[Setter]]
//  protected val rowSettersMap    = mutable.Map.empty[List[String], List[Setter]]
//  protected val tableInserts     = mutable.Map.empty[List[String], TableInsert]
//  protected var joinTableInserts = List.empty[JoinTableInsert]
//  protected val insertIndexes    = mutable.Map.empty[List[String], Int]
//
//  protected def addColSetter(refPath: List[String], colSetter: Setter) = {
//    // Cache colSetter for this table
//    //    colSettersMap.get((refPath, ns)).fold[Unit](
//    colSettersMap.get(refPath).fold[Unit](
//      colSettersMap.addOne(refPath -> List(colSetter))
//    )(colSetters =>
//      colSettersMap(refPath) = colSetters :+ colSetter
//    )
//  }

  var level = 0
  //  def indent = "  " * level
  def indent(level: Int) = "  " * level
  def indent(refPath: List[String]) = "  " * refPath.head.toInt


  //  var arities = Array.empty[Array[Int]]
  // Dynamic ref path of current level and branch (each backref initiates a new branch)
  protected var curRefPath = List("0")

  // Ordered tables to have data inserted
  // refPath, ns, selfRef, cols
  //  protected var inserts = List.empty[(List[String], String, List[String], List[TableInsert])]
  //  protected var inserts2 = List.empty[(Int, List[String], String, List[String])]
  protected var inserts = List.empty[(List[String], List[String])]
  //  protected var joins   = List.empty[(List[String], String, String, String, Int, Int)]
  protected var joins   = List.empty[(List[String], String, String, List[String], List[String])]


  // PreparedStatement param indexes for each (table, col) coordinate
  protected val paramIndexes     = mutable.Map.empty[(List[String], String), Int]
  protected val colSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val rowSettersMap    = mutable.Map.empty[List[String], List[Setter]]
  protected val tableInserts     = mutable.Map.empty[List[String], TableInsert]
  protected var joinTableInserts = List.empty[JoinTableInsert]
  //  protected var insertIndex      = -1
  protected val insertIndexes    = mutable.Map.empty[List[String], Int]
  //  protected val joinArityMap  = mutable.Map.empty[List[String], Array[Int]]
  protected val rightCountsMap   = mutable.Map.empty[List[String], List[Int]]


  protected def addColSetter(refPath: List[String], colSetter: Setter) = {
    // Cache colSetter for this table
    colSettersMap.get(refPath).fold[Unit](
      colSettersMap.addOne(refPath -> List(colSetter))
    )(colSetters =>
      colSettersMap(refPath) = colSetters :+ colSetter
    )
  }


  def arr[T](a: Array[T]) = {
    a.map {
      case a: Array[_] => a.mkString("Array(", ", ", ")")
      case other       => other
    }.mkString("Array(", ", ", ")")
  }

  protected def printValue(
    level: Int,
    ns: String,
    attr: String,
    tplIndex0: Int,
    paramIndex: Int,
    value: Any
  ): Unit = {
    val fullAttr = s"$ns.$attr"
    val pad      = padS(14, fullAttr)
    val tplIndex = if (tplIndex0 == -1) "-" else tplIndex0
    println(s"${indent(level)}$fullAttr$pad tplIndex: $tplIndex   paramIndex: $paramIndex   value: " + value)
  }


  // todo: replace with the ones underneath
  //  var colSetters  = List.empty[Setter]
  //  var insertStmts = List.empty[String]
  //  protected var table   = ""
  //  protected val columns = ListBuffer.empty[String]
  //
  //  protected var tables         = Array("")
  //  protected var columnLists    = Array(Array.empty[String])
  //  protected var colSetterLists = Array(Array.empty[Setter])
  //  protected var batchSetters   = List.empty[Setter]
  //  protected var joinInserts    = List.empty[String]
  //  protected var joinSetters    = List.empty[Setter]
  //  protected var joinColumnLists = List(List.empty[String])


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
  protected def addRetractEntityStmt(eid: AnyRef): Unit = {
    //    val stmt = new jArrayList[AnyRef](2)
    //    stmt.add(retractEntity)
    //    stmt.add(eid)
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
}
