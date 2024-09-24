package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_AsyncApi extends AsyncApi with Test_mariadb_async
