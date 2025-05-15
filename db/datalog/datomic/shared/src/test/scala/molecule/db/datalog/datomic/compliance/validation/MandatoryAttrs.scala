package molecule.db.datalog.datomic.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.datalog.datomic.setup.Api_datomic_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_datomic_async)
}
