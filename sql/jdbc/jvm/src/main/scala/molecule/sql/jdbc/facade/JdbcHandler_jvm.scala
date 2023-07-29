package molecule.sql.jdbc.facade

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import scala.concurrent.blocking


trait JdbcHandler_jvm {

  def connect(proxy: JdbcProxy, url: String): JdbcConn_jvm = blocking {
    val sqlConn = java.sql.DriverManager.getConnection(url)
    JdbcConn_jvm(proxy, sqlConn)
  }

  def recreateDb(proxy: JdbcProxy, url: String): JdbcConn_jvm = blocking {
    val conn = connect(proxy, url)
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }
}

object JdbcHandler_jvm extends JdbcHandler_jvm

