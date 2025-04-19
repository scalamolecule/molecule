package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api.*
import molecule.sql.mysql.setup.Api_mysql_io

class IOApiTest extends Test_io {
  IOApi(this, Api_mysql_io)
}