package molecule.db.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_postgres_async)
}
