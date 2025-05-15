package molecule.db.datalog.datomic.compliance.validation

import molecule.db.compliance.setup.MUnitSuiteWithArrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.datalog.datomic.setup.Api_datomic_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_datomic_async)
}
