package molecule.sql.jdbc.facade

import java.sql.Statement
import java.util.UUID.randomUUID
import datomic.Peer
import molecule.core.marshalling.{DatomicPeerProxy, SqlProxy}
import scala.concurrent.{ExecutionContext, Future, blocking}
import scala.util.control.NonFatal


trait JdbcHandler {

  def connect(proxy: SqlProxy, url: String): JdbcConn_JVM = blocking {
    val sqlConn = java.sql.DriverManager.getConnection(url)
    JdbcConn_JVM(proxy, sqlConn)
  }

  def recreateDb(proxy: SqlProxy, url: String): JdbcConn_JVM = blocking {
    val conn = connect(proxy, url)
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.schema.sqlSchema("h2"))
    stmt.close()
    conn
  }
}

object JdbcHandler extends JdbcHandler

