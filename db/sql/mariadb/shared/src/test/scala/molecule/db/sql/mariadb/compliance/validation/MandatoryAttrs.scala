package molecule.db.sql.mariadb.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_mariadb_async)
}
