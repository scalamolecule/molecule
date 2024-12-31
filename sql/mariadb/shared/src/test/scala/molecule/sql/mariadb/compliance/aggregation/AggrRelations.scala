package molecule.sql.mariadb.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.sql.mariadb.setup.Api_mariadb_async

class AggrRelations extends MUnitSuite {
  AggrRelations(this, Api_mariadb_async)
}
