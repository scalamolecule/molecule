package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Api_h2_io

class IOApiTest extends Test_io {
  IOApi(this, Api_h2_io)
}
