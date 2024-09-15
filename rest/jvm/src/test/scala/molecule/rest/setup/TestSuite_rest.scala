package molecule.rest.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.rest.schema.StarwarsSchema


trait TestSuite_rest extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "Postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case StarwarsSchema      => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
    }
    test(conn)
  }
}
