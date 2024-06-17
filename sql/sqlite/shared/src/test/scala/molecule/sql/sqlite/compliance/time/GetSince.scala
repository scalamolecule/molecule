package molecule.sql.sqlite.compliance.time

import molecule.coreTests.spi.time.GetSince
import molecule.sql.sqlite.setup.TestAsync_sqlite

object GetSince extends GetSince with TestAsync_sqlite
