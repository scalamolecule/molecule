package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.datalog.datomic.setup.Api_datomic_async

class AggrRelations extends MUnitSuite {
  AggrRelations(this, Api_datomic_async)
}
