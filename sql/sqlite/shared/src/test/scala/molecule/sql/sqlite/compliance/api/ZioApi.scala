package molecule.sql.sqlite.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.TestZio_sqlite

object Test_ZioApi extends ZioApi with TestZio_sqlite
