package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.datalog.datomic.setup.Api_datomic_async

class MandatoryAttrs extends MUnitSuite {
  MandatoryAttrs(this, Api_datomic_async)
}
