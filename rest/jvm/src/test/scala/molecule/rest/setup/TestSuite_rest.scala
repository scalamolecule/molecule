package molecule.rest.setup

import molecule.db.base.api.Schema
import molecule.db.base.util.BaseHelpers
import molecule.db.core.spi.Conn
import molecule.db.compliance.domains.schema._
import molecule.db.compliance.setup.CoreTestSuite
import molecule.rest.schema.StarwarsSchema


trait TestSuite_rest extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case StarwarsSchema      => ??? //JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
    }
    test(conn)
  }
}
