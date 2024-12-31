package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_zio

class ZioApi extends Test {
  ZioApi(this, Api_mariadb_zio)
}
