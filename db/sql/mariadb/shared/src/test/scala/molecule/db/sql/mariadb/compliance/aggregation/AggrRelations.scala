package molecule.db.sql.mariadb.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_mariadb_async)
}
