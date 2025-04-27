package molecule.db.sql.mariadb.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_mariadb_async)
}
