package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.datalog.datomic.setup.Api_datomic_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_datomic_async)
}
