package molecule.db.datalog.datomic.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_datomic_async)
}
