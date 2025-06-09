package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.datalog.datomic.setup.Api_datomic_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_datomic_async)
}
