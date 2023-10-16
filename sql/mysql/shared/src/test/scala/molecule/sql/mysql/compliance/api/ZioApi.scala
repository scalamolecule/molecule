package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.TestZio_mysql

object ZioApi extends ZioApi with TestZio_mysql
