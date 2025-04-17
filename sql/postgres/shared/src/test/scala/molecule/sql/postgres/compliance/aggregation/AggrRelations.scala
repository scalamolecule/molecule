package molecule.sql.postgres.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation._
import molecule.sql.postgres.setup.Api_postgres_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_postgres_async)
}
