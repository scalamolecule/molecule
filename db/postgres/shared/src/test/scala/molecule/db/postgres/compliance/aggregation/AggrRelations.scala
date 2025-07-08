package molecule.db.postgres.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.postgres.setup.Api_postgres_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_postgres_async)
}
