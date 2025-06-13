package molecule.rpc.openapi.setup

import molecule.core.dataModel.Schema
import molecule.base.util.BaseHelpers
import molecule.db.core.spi.Conn
import molecule.db.compliance.domains.schema._
import molecule.db.compliance.setup.CoreTestSuite
import molecule.rpc.openapi.schema.StarwarsSchema


trait TestSuite_openapi extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case StarwarsSchema => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
    }
    test(conn)
  }
}
