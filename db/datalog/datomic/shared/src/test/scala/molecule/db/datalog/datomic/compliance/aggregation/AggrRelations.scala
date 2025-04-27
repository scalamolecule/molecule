package molecule.db.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_datomic_async)
}
