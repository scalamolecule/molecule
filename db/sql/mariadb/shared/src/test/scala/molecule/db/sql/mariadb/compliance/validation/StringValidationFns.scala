package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_mariadb_async)
}
