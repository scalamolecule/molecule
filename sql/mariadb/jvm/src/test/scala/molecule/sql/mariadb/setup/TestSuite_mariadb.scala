package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcHandler_JVM
import molecule.sql.mariadb.marshalling.{Connection_mariadb, Connection_mariadb => c}


trait TestSuite_mariadb extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "MariaDB"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(Connection_mariadb.getConnection(schema))
  }
}
