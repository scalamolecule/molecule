package molecule.db.sql.sqlite.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_sqlite_async)
}
