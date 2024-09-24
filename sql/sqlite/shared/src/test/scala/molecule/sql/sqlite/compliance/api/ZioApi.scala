package molecule.sql.sqlite.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.Test_sqlite_zio

object Test_ZioApi extends ZioApi with Test_sqlite_zio
