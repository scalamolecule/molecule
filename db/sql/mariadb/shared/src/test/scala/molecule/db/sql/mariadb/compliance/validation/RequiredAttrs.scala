package molecule.db.sql.mariadb.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_mariadb_async)
}
