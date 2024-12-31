package molecule.sql.postgres.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.sql.postgres.setup.Api_postgres_async

class AggrRelations extends MUnitSuite {
  AggrRelations(this, Api_postgres_async)
}
