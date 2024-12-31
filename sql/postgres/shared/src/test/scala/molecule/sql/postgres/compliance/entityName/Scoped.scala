package molecule.sql.postgres.compliance.entityName

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.entityName.Scoped
import molecule.sql.postgres.setup.Api_postgres_async

class Scoped extends MUnitSuite {
  Scoped(this, Api_postgres_async)
}
