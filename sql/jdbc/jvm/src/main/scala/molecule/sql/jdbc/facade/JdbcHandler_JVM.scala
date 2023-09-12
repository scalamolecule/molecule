package molecule.sql.jdbc.facade

import molecule.core.marshalling.JdbcProxy
import scala.concurrent.blocking


trait JdbcHandler_JVM {

  private def connect(proxy: JdbcProxy): JdbcConn_JVM = blocking {
    val sqlConn = java.sql.DriverManager.getConnection(proxy.url)
    JdbcConn_JVM(proxy, sqlConn)
  }

  def recreateDb(proxy: JdbcProxy): JdbcConn_JVM = blocking {
    val conn = connect(proxy)
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }
}

object JdbcHandler_JVM extends JdbcHandler_JVM

