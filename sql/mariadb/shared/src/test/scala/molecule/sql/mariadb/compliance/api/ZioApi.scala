package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Test_mariadb_zio

object Test_ZioApi extends ZioApi with Test_mariadb_zio
