package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.Api_sqlite_io

class IOApi extends Test_io {
  IOApi(this, Api_sqlite_io)
}
