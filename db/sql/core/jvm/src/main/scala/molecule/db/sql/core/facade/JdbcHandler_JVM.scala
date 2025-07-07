package molecule.db.sql.core.facade

import java.sql.Connection
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.util.SchemaLoader
import scala.concurrent.blocking
import scala.util.Using.Manager

object JdbcHandler_JVM extends SchemaLoader {

  // For docker test containers
  def updateDb(conn: JdbcConn_JVM): JdbcConn_JVM = blocking {
    val stmt   = conn.sqlConn.createStatement
    stmt.executeUpdate(getSqlInit(conn.proxy))
    stmt.close()
    conn
  }


  def recreateDb(proxy: JdbcProxy, sqlConn: Connection): JdbcConn_JVM = {
    Manager { use =>
      sqlConn.setAutoCommit(false)
      val conn = JdbcConn_JVM(proxy, sqlConn)
      val stmt = use(conn.sqlConn.createStatement)
      stmt.executeUpdate(getSqlInit(proxy))
      sqlConn.commit()
      conn
    }.get
  }
}