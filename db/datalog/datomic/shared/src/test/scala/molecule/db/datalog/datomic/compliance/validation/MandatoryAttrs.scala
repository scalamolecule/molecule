package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.datalog.datomic.setup.Api_datomic_async

class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_datomic_async)
}
