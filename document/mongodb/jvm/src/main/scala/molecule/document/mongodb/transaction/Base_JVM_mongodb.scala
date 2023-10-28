package molecule.document.mongodb.transaction

import java.sql.{PreparedStatement => PS}
import java.util
import java.util.UUID
import molecule.base.ast._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, JdbcProxy, MongoProxy}
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.{MongoConn_JVM, MongoHandler_JVM}
import org.bson.BsonDocument
import scala.collection.mutable
import scala.concurrent.Future

trait Base_JVM_mongodb extends DataType_JVM_mongodb with ModelUtils with BaseHelpers {

  var level = 0
  override def indent(level: Int) = "  " * level
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var doPrint              = false
  protected var initialNs            = ""
  protected var ids                  = Seq.empty[Long]
  protected var uniqueFilterElements = List.empty[Element]
  protected var filterElements       = List.empty[Element]




//  protected val filters = new util.ArrayList[BsonDocument]()
  protected val filters = new BsonDocument()







  // "Connection pool" ---------------------------------------------

  // todo: offer real solution
  private val connectionPool = mutable.HashMap.empty[UUID, Future[MongoConn_JVM]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[MongoConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    // logger.debug("connectionPool.size: " + connectionPool.size)
    futConnTimeAdjusted
  }

  private def getFreshConn(proxy: ConnProxy): Future[MongoConn_JVM] = {
    Future(MongoHandler_JVM.recreateDb(proxy.asInstanceOf[MongoProxy]))
  }
}
