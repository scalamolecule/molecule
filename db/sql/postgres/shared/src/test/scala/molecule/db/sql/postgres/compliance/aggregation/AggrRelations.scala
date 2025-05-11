package molecule.db.sql.postgres.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_postgres_async)
}
