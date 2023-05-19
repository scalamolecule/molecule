package molecule.sql.jdbc.transaction

import java.lang.{Boolean => jBoolean}
import java.sql.PreparedStatement
import java.util
import java.util.{UUID, ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, DatomicPeerProxy}
import molecule.core.util.Executor._
import molecule.core.util.{ModelUtils, fns}
import molecule.sql.jdbc.facade.{JdbcConn_JVM, JdbcHandler}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait JdbcTxBase_JVM extends JdbcDataType_JVM with ModelUtils {

  // Override on instantiation
  protected val sqlConn: java.sql.Connection

  // todo: replace with the ones underneath
  var colSetters  = List.empty[Setter]
  var insertStmts = List.empty[SqlStmt]
  protected var table   = ""
  protected val columns = ListBuffer.empty[String]

  protected var tables         = Array("")
  protected var columnLists    = Array(Array.empty[String])
  protected var colSetterLists = Array(Array.empty[Setter])
  protected var batchSetters   = List.empty[Setter]
  protected var joinInserts    = List.empty[String]
  protected var joinSetters    = List.empty[Setter]
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
  protected val connectionPool = mutable.HashMap.empty[UUID, Future[JdbcConn_JVM]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
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

  protected def getFreshConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    proxy match {
      case proxy@DatomicPeerProxy(protocol, dbIdentifier, _, _, _, isFreeVersion) =>
        //        protocol match {
        //          case "mem" =>
        //            JdbcHandler.recreateDbFromEdn(proxy, protocol, dbIdentifier, isFreeVersion)
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
