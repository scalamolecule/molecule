package molecule.sql.mariadb.compliance.entityName

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.entityName.Scoped
import molecule.sql.mariadb.setup.Api_mariadb_async

class Scoped extends MUnitSuite {
  Scoped(this, Api_mariadb_async)
}
