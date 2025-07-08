package molecule.db.sqlite.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sqlite.setup.Api_sqlite_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_sqlite_async)
}
