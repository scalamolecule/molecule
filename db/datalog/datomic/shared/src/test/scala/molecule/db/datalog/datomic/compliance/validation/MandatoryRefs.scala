package molecule.db.datalog.datomic.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_datomic_async)
}
