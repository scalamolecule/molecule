package molecule.sql.jdbc.facade

import java.sql.Statement
import java.util.UUID.randomUUID
import datomic.Peer
import molecule.core.marshalling.{DatomicPeerProxy, SqlProxy}
import scala.concurrent.{ExecutionContext, Future, blocking}
import scala.util.control.NonFatal


trait JdbcHandler {

  //  def createDatabase(
  //    protocol: String = "mem",
  //    dbIdentifier: String = ""
  //  ): Boolean = blocking {
  //    Peer.createDatabase(s"datomic:$protocol://$dbIdentifier")
  //  }
  //
  //  def deleteDatabase(
  //    protocol: String = "mem",
  //    dbIdentifier: String = "localhost:4334/"
  //  ): Boolean = blocking {
  //    Peer.deleteDatabase(s"datomic:$protocol://$dbIdentifier")
  //  }

  def connect(proxy: SqlProxy, url: String): JdbcConn_JVM = blocking {
    val sqlConn = java.sql.DriverManager.getConnection(url)
    JdbcConn_JVM(proxy, sqlConn)
  }

  def recreateDb(proxy: SqlProxy, url: String): JdbcConn_JVM = {
    //    val stmts = proxy.schema.head
    val stmts                    =
      """create table Ns(
        |    id long auto_increment primary key,
        |    s LONGVARCHAR,
        |    int INT
        |);
        |""".stripMargin
//    var conn: SqlConn_JVM        = SqlConn_JVM(proxy, null)
//    var stmt: java.sql.Statement =
//    try {
      val conn = connect(proxy, url)
      val stmt = conn.sqlConn.createStatement
      val res = stmt.executeUpdate(stmts)

      stmt.close()
      //    conn.sqlConn.createStatement.executeQuery(stmts)
      //    conn.sqlConn.createStatement.execute(stmts)
      conn
//    } catch {
//      case NonFatal(exc) => throw exc
//    } finally {
//      stmt.close()
//      conn.sqlConn.close()
//    }
  }

//    def recreateDbFromEdn(
//      proxy: SqlProxy,
//      protocol: String = "mem",
//      dbIdentifier: String = "",
//      isFreeVersion: Boolean = false
//    )(implicit ec: ExecutionContext): Future[SqlConn_JVM] = blocking {
//      val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
////      deleteDatabase(protocol, id)
////      createDatabase(protocol, id)
//      val conn = connect(proxy, protocol, id, isFreeVersion)
//      // Ensure each transaction finishes before the next
//      for {
//        // partitions
//        _ <- if (proxy.schema.head.nonEmpty)
//          conn.transactEdn(proxy.schema.head) else Future.unit
//        // attributes
//        _ <- if (proxy.schema(1).nonEmpty)
//          conn.transactEdn(proxy.schema(1)) else Future.unit
//        // aliases
//        _ <- if (proxy.schema(2).nonEmpty)
//          conn.transactEdn(proxy.schema(2)) else Future.unit
//      } yield conn
//    }
}

object JdbcHandler extends JdbcHandler

