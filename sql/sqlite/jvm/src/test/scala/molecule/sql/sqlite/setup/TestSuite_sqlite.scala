package molecule.sql.sqlite.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuite_io}
import molecule.sql.sqlite.facade.{JdbcConnSQlite_JVM, JdbcHandlerSQlite_JVM}
import scala.util.control.NonFatal

trait TestSuite_sqlite extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "SQlite"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy                    = JdbcProxy(
      "jdbc:sqlite::memory:",
      schema.sqlSchema_sqlite,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_sqlite
    )
    var conn: JdbcConnSQlite_JVM = null
    try {
      conn = JdbcHandlerSQlite_JVM.recreateDb(proxy)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      if (conn.sqlConn != null) {
        // Closing connection after each test
        conn.sqlConn.close()
      }
    }
  }
}
