package molecule.sql.sqlite.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite_io
import molecule.sql.sqlite.facade.{JdbcConnSQlite_JVM, JdbcHandlerSQlite_JVM}
import scala.util.control.NonFatal

trait TestSuite_sqlite_io extends CoreTestSuite_io with BaseHelpers {

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
      case NonFatal(exc) =>
        exc.printStackTrace()
        throw exc
    } finally {
      if (conn != null && conn.sqlConn != null) {
        // Closing connection after each test
        // Doesn't work with munit tests - maybe not necessary with memory db?
        //        conn.sqlConn.close()
      }
    }
  }
}
