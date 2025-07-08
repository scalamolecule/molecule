package molecule.db.h2.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.h2.setup.Api_h2_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_h2_async)
}
