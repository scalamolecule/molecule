package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.Api_sqlite_zio

class ZioApi extends MUnitSuite {
  ZioApi(this, Api_sqlite_zio)
}
