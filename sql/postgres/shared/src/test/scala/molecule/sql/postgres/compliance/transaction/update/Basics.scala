package molecule.sql.postgres.compliance.transaction.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update._
import molecule.sql.postgres.setup.Api_postgres_async

class Basics extends MUnitSuite {
  Basics(this, Api_postgres_async)
}
