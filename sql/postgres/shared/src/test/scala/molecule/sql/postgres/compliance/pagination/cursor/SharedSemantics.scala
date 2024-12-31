package molecule.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.postgres.setup.Api_postgres_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_postgres_async)
}
