package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.datalog.datomic.setup.Api_datomic_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_datomic_async)
}
