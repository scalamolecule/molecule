package molecule.db.datalog.datomic.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_datomic_async)
}
