package molecule.db.common.facade

import java.sql.Connection
import scala.concurrent.blocking
import scala.util.Using.Manager
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.util.SchemaLoader

object JdbcHandler_JVM extends SchemaLoader {

  // For docker test containers
  def updateDb(conn: JdbcConn_JVM): JdbcConn_JVM = blocking {
    val stmt = conn.sqlConn.createStatement
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