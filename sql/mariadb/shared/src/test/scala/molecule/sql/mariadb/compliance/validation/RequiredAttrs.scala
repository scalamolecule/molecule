package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.mariadb.setup.Api_mariadb_async

class RequiredAttrs extends MUnitSuite {
  RequiredAttrs(this, Api_mariadb_async)
}
