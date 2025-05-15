package molecule.db.datalog.datomic.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_datomic_async)
}
