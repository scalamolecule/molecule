package molecule.db.sql.core.facade

import java.sql.Connection
import molecule.db.core.marshalling.JdbcProxy
import scala.concurrent.blocking
import scala.util.Using.Manager


object JdbcHandler_JVM {

  // For docker test containers
  def updateDb(conn: JdbcConn_JVM): JdbcConn_JVM = blocking {
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(conn.proxy.schemaStr)
    stmt.close()
    conn
  }


  def recreateDb(proxy: JdbcProxy, sqlConn: Connection): JdbcConn_JVM = {
    Manager { use =>
      sqlConn.setAutoCommit(false)
      val conn = JdbcConn_JVM(proxy, sqlConn)
      val stmt = use(conn.sqlConn.createStatement)
      stmt.executeUpdate(proxy.schemaStr)
      sqlConn.commit()
      conn
    }.get
  }
}