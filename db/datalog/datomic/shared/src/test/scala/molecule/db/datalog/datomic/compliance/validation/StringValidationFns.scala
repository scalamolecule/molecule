package molecule.db.datalog.datomic.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_datomic_async)
}
