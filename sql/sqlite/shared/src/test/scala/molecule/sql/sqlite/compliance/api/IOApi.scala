package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.IOApi
import molecule.sql.sqlite.setup.Api_sqlite_io

class IOApi extends MUnitSuite {
  IOApi(this, Api_sqlite_io)
}
