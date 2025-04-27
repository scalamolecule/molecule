package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.datalog.datomic.setup.Api_datomic_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_datomic_async)
}
