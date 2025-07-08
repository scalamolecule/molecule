package molecule.db.mariadb.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.mariadb
import molecule.db.mariadb.setup.Api_mariadb_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_mariadb_async)
}
