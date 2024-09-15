package molecule.rpc.openapi.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.rpc.openapi.schema.StarwarsSchema


trait TestSuite_openapi extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "Postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case StarwarsSchema      => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
    }
    test(conn)
  }
}
