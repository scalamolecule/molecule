package molecule.sql.postgres.test.api

import molecule.coreTests.test.api._
import molecule.sql.postgres.setup.TestZio_postgres

object ZioApi extends ZioApi with TestZio_postgres
