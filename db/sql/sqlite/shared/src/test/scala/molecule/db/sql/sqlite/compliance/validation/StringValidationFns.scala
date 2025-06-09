package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_sqlite_async)
}
