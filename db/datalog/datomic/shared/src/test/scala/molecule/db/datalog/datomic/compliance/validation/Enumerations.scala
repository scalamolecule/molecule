package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.datalog.datomic.setup.Api_datomic_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_datomic_async)
}
