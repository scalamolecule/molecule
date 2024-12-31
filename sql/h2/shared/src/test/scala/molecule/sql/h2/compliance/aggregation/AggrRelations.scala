package molecule.sql.h2.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.sql.h2.setup.Api_h2_async

class AggrRelations extends MUnitSuite {
  AggrRelations(this, Api_h2_async)
}
