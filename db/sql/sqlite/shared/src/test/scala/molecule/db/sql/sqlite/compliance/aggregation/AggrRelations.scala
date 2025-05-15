package molecule.db.sql.sqlite.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_sqlite_async)
}
