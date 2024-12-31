package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Api_h2_io

class IOApi extends MUnitSuite {
  IOApi(this, Api_h2_io)
}
