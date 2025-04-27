package molecule.db.datalog.datomic.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_datomic_async)
}
