package molecule.sql.h2.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.sql.h2.setup.Api_h2_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_h2_async)
}
