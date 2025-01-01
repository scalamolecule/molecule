package molecule.sql.sqlite.setup

import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.{JdbcProxy_sqlite, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS

trait DbConnection_sqlite extends DbConnection {

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    val proxy = JdbcProxy_sqlite("jdbc:sqlite::memory:", schema)
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
