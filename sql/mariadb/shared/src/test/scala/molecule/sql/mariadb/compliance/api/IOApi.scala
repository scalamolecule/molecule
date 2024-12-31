package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_io

class IOApi extends Test {
  IOApi(this, Api_mariadb_io)
}