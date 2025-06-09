package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.datalog.datomic.setup.Api_datomic_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_datomic_async)
}
