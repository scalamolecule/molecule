package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_sqlite_async)
}
