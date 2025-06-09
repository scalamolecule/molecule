package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_sqlite_async)
}
