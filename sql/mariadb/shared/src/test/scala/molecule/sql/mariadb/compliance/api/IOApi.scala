package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_io

class IOApi extends MUnitSuite {
  IOApi(this, Api_mariadb_io)
}