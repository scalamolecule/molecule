package molecule.db.sql.mariadb.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_mariadb_async)
}
