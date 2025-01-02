package molecule.sql.core.facade

import java.sql.{Connection, DriverManager}
import molecule.core.marshalling.JdbcProxy
import scala.concurrent.blocking
import scala.util.Using.Manager


object JdbcHandler_JVM {

  //    def recreateDb(proxy: JdbcProxy): JdbcConn_JVM = blocking {
  //      val sqlConn = DriverManager.getConnection(proxy.url)
  //      val conn    = JdbcConn_JVM(proxy, sqlConn)
  //      val stmt    = conn.sqlConn.createStatement
  //      stmt.executeUpdate(proxy.schemaStr)
  //      stmt.close()
  //      conn
  //    }

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
        proxy.schema.schemaData.head
      stmt.executeUpdate(sql)
      sqlConn.commit()
      conn
    }.get
  }
}