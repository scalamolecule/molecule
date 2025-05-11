package molecule.db.datalog.datomic.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_datomic_async)
}
