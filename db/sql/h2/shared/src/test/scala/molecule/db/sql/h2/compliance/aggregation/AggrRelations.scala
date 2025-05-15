package molecule.db.sql.h2.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql.h2.setup.Api_h2_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_h2_async)
}
