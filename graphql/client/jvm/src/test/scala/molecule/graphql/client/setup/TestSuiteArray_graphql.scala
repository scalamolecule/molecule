package molecule.graphql.client.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.graphql.client.schema.StarwarsSchema


trait TestSuiteArray_graphql extends CoreTestSuiteBase with BaseHelpers {

  override val platform              = "jvm"
  override val database              = "postgres"
  override val isJsPlatform: Boolean = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case StarwarsSchema => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
      //      case _              => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
    }
    test(conn)
  }
}
