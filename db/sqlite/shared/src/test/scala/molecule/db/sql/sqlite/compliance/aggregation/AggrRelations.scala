package molecule.db.sql.sqlite.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_sqlite_async)
}
