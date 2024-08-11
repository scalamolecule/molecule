package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.TestZio_mysql

object Test_ZioApi extends ZioApi with TestZio_mysql
