package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.mariadb.setup.Api_mariadb_async

class StringValidationFns extends MUnitSuite {
  StringValidationFns(this, Api_mariadb_async)
}
