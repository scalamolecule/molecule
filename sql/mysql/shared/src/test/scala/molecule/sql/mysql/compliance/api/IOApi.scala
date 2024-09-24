package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Test_mysql_io

object Test_IOApi extends IOApi with Test_mysql_io
