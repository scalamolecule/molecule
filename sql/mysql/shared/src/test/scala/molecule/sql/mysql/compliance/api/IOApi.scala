package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Api_mysql_io

class IOApi extends Test_io {
  IOApi(this, Api_mysql_io)
}