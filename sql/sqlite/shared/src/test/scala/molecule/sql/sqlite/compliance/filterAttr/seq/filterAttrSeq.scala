package molecule.sql.sqlite.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Adjacent extends Adjacent with TestAsync_sqlite
object CrossNs extends CrossNs with TestAsync_sqlite
object CrossNsOwned extends CrossNsOwned with TestAsync_sqlite
object Types extends Types with TestAsync_sqlite
