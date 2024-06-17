package molecule.sql.sqlite.setup

import java.sql.DriverManager
import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.sqlite.facade.JdbcConnSQlite_JVM
import scala.concurrent.blocking
import scala.util.control.NonFatal

trait TestSuite_sqlite extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "SQlite"

  val url = "jdbc:sqlite::memory:"

  def recreateDb(proxy: JdbcProxy): JdbcConnSQlite_JVM = blocking {
    val sqlConn = DriverManager.getConnection(url)
    val conn    = new JdbcConnSQlite_JVM(proxy, sqlConn)
    val stmt    = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }


  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_sqlite,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_sqlite
    )
    var conn  = new JdbcConnSQlite_JVM(proxy, null)
    try {
      conn = recreateDb(proxy)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      if (conn.sqlConn != null) {
        conn.sqlConn.close()
      }
    }
  }
}
