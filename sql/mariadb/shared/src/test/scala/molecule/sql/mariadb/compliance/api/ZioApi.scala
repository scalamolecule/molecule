package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_zio

class ZioApi extends MUnitSuite {
  ZioApi(this, Api_mariadb_zio)
}
