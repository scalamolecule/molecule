package molecule.db.sql.sqlite.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_sqlite_async)
}
