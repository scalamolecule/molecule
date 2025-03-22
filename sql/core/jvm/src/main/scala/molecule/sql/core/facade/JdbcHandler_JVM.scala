package molecule.sql.core.facade

import java.sql.Connection
import molecule.core.marshalling.JdbcProxy
import scala.concurrent.blocking
import scala.util.Using.Manager


object JdbcHandler_JVM {

  // For docker test containers
  def recreateDb(conn: JdbcConn_JVM): JdbcConn_JVM = blocking {
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
      val sql  = if (proxy.schemaStr.nonEmpty)
        proxy.schemaStr
      else
        proxy.schemaData.head
      stmt.executeUpdate(sql)
      sqlConn.commit()
      conn
    }.get
  }
}