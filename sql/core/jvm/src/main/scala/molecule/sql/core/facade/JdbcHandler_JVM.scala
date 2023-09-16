package molecule.sql.core.facade

import java.sql.DriverManager
import molecule.core.marshalling.JdbcProxy
import scala.concurrent.blocking


object JdbcHandler_JVM {

  // For in-memory dbs
  def recreateDb(proxy: JdbcProxy): JdbcConn_JVM = blocking {
    val sqlConn = DriverManager.getConnection(proxy.url)
    val conn    = JdbcConn_JVM(proxy, sqlConn)
    val stmt    = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }

  // For docker test containers
  def recreateDb(conn: JdbcConn_JVM, recreationStmt: String): JdbcConn_JVM = blocking {
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(recreationStmt)
    stmt.close()
    conn
  }
}