package molecule.sql.sqlite.compliance.time

import molecule.coreTests.spi.time.GetAsOf
import molecule.sql.sqlite.setup.TestAsync_sqlite

object GetAsOf extends GetAsOf with TestAsync_sqlite
