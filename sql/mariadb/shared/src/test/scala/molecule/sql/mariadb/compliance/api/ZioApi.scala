package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.TestZio_mariadb

object ZioApi extends ZioApi with TestZio_mariadb
