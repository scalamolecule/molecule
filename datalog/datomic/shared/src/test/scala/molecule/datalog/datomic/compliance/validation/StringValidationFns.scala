package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.StringValidationFns
import molecule.datalog.datomic.setup.Api_datomic_async

class StringValidationFns extends MUnitSuite {
  StringValidationFns(this, Api_datomic_async)
}
