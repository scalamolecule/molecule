package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.TestZio_postgres

object ZioApi extends ZioApi with TestZio_postgres
