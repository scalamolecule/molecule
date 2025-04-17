package molecule.sql.sqlite.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation._
import molecule.sql.sqlite.setup.Api_sqlite_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_sqlite_async)
}
