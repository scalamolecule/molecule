package molecule.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor._
import molecule.sql.postgres.setup.Api_postgres_async

class SharedSemantics extends Test {
  SharedSemantics(this, Api_postgres_async)
}
