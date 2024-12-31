package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.mariadb.setup.Api_mariadb_async

class MandatoryAttrs extends MUnitSuite {
  MandatoryAttrs(this, Api_mariadb_async)
}
