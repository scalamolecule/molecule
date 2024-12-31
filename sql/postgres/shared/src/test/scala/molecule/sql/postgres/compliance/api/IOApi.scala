package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Api_postgres_io

class IOApi extends Test {
  IOApi(this, Api_postgres_io)
}