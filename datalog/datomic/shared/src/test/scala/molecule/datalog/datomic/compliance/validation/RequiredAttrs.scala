package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.datalog.datomic.setup.Api_datomic_async

class RequiredAttrs extends MUnitSuite {
  RequiredAttrs(this, Api_datomic_async)
}
