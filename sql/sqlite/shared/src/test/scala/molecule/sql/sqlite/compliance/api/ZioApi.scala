package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.Api_sqlite_zio

class ZioApi extends Test {
  ZioApi(this, Api_sqlite_zio)
}
