package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.facade.{JdbcConn_jvm, JdbcHandler_jvm}
import scala.util.Random
import scala.util.control.NonFatal


trait JdbcTestSuite extends CoreTestSuite {

  override val platform = "Jdbc jvm"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    var conn  = JdbcConn_jvm(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = JdbcHandler_jvm.recreateDb(proxy, url)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      conn.sqlConn.close()
    }
  }
}
