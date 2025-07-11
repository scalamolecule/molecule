package molecule.db.postgresql.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.postgresql.setup.Api_postgresql_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_postgresql_async)
}
