package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api.*
import molecule.sql.sqlite.setup.Api_sqlite_io

class IOApiTest extends Test_io {
  IOApi(this, Api_sqlite_io)
}
