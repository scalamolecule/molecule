package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Api_h2_zio

class ZioApi extends MUnitSuite {
  ZioApi(this, Api_h2_zio)
}
