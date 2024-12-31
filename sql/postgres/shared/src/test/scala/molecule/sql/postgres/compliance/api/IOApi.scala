package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Api_postgres_io

class IOApi extends MUnitSuite {
  IOApi(this, Api_postgres_io)
}