package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.{Test_mariadb_async, Test_mariadb_io}

object Test_IOApi extends IOApi with Test_mariadb_io
