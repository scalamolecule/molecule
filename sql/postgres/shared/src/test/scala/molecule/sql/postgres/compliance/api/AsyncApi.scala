package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_AsyncApi extends AsyncApi with TestAsync_postgres
