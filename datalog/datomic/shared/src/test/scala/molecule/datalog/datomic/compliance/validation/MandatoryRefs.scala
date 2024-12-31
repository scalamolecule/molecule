package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation._
import molecule.datalog.datomic.setup.Api_datomic_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_datomic_async)
}
