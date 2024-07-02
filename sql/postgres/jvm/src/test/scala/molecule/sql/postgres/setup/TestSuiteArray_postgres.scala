package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.sql.core.facade.JdbcHandler_JVM
import molecule.sql.postgres.marshalling.{Connection_postgres, Connection_postgres => c}


trait TestSuiteArray_postgres extends CoreTestSuiteBase with BaseHelpers {

  override val platform     = "jvm"
  override val database     = "Postgres"
  override val isJsPlatform = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(Connection_postgres.getConnection(schema))
  }
}
