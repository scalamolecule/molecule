package molecule.sql.h2.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.coreTests.util.Array2List
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import scala.util.Random
import scala.util.control.NonFatal


trait TestSuite_h2_array extends CoreTestSuiteBase with Array2List with BaseHelpers {

  override val platform              = "jvm"
  override val database              = "H2"
  override val isJsPlatform: Boolean = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url   = "jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_h2,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_h2
    )
    var conn  = JdbcConn_JVM(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = JdbcHandler_JVM.recreateDb(proxy)
      test(conn)
    } catch {
      case NonFatal(exc) =>
        exc.printStackTrace()
        throw exc
    } finally {
      if (conn != null && conn.sqlConn != null) {
        conn.sqlConn.close()
      }
    }
  }
}
