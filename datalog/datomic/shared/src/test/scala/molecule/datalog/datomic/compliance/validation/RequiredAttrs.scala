package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.datalog.datomic.setup.Api_datomic_async

class RequiredAttrs extends Test {
  RequiredAttrs(this, Api_datomic_async)
}
