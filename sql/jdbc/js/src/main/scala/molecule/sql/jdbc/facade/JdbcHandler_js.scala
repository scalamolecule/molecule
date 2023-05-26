package molecule.sql.jdbc.facade

import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import scala.concurrent.blocking


trait JdbcHandler_js {

  def connect(proxy: JdbcProxy, url: String): JdbcConn_js = blocking {
    JdbcConn_js(proxy, RpcRequest.request)
  }

  def recreateDb(proxy: JdbcProxy, url: String): JdbcConn_js = blocking {
    //    val conn = connect(proxy, url)
    //    val stmt = conn.sqlConn.createStatement
    //    stmt.executeUpdate(proxy.createSchema)
    //    stmt.close()
    //    conn
    ???
  }
}

object JdbcHandler_js extends JdbcHandler_js

