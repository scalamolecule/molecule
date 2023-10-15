package molecule.sql.mariadb.compliance.api

import molecule.coreTests.compliance.api._
import molecule.sql.mariadb.setup.TestZio_mariadb

object ZioApi extends ZioApi with TestZio_mariadb
