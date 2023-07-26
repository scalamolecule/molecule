package molecule.datalog.datomic.transaction

import java.lang.{Boolean => jBoolean}
import java.util.{UUID, ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, DatomicProxy}
import molecule.core.util.Executor._
import molecule.core.util.{ModelUtils, fns}
import molecule.datalog.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait DatomicBase_JVM extends DatomicDataType_JVM with ModelUtils {

  protected def initTxBase(elements: List[Element], idIndex: Int = 0): Unit = {
    nsFull = getInitialNs(elements)
    part = fns.partNs(nsFull).head
    lowest = idIndex
  }

  // Accumulate java insertion data
  final protected val stmts: jArrayList[jList[AnyRef]] = new jArrayList[jList[AnyRef]]()

  protected var nsFull       : String              = ""
  protected var part         : String              = ""
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e            : AnyRef              = "" // Long or String (#db/id[db.part/user -1])
  protected var e0           : AnyRef              = ""
  protected var unusedRefId  : Boolean             = false
  protected var stmt         : jList[AnyRef]       = null
  protected var backRefs     : Map[String, AnyRef] = Map.empty[String, AnyRef]
  protected val prevRefs     : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected val usedRefIds   : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected val unusedRefIds : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
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


  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }
  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)

  protected def stmtList = new jArrayList[AnyRef](4)
  protected def appendStmt(
    op: Keyword,
    e: AnyRef,
    a: Keyword,
    v: AnyRef,
  ): Unit = {
    val addStmt = stmtList
    addStmt.add(op)
    addStmt.add(e)
    addStmt.add(a)
    addStmt.add(v)
    stmts.add(addStmt)
  }
  protected def addRetractEntityStmt(id: AnyRef) = {
    val stmt = new jArrayList[AnyRef](2)
    stmt.add(retractEntity)
    stmt.add(id)
    stmts.add(stmt)
  }


  // Connection pool ---------------------------------------------

  // todo: real solution
  protected val connectionPool = mutable.HashMap.empty[UUID, Future[DatomicConn_JVM]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[DatomicConn_JVM] = {
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

  protected def getFreshConn(proxy: ConnProxy): Future[DatomicConn_JVM] = {
    proxy match {
      case proxy@DatomicProxy(protocol, dbIdentifier, _, _, _, _, _, _, _, _, _) =>
        protocol match {
          case "mem" =>
            DatomicPeer.recreateDb(proxy, protocol, dbIdentifier)
              .recover {
                case exc: Throwable => throw ExecutionError(exc.getMessage)
              }

          case "free" | "dev" | "pro" =>
            Future(DatomicPeer.connect(proxy, protocol, dbIdentifier))
              .recover {
                case exc: Throwable => throw ExecutionError(exc.getMessage)
              }

          case other => Future.failed(
            ExecutionError(s"\nCan't serve Peer protocol `$other`.")
          )
        }

      case _ => ???
    }
  }
}
