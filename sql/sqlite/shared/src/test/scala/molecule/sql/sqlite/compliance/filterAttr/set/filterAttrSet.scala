package molecule.sql.sqlite.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Adjacent extends Adjacent with TestAsync_sqlite
object CrossNs extends CrossNs with TestAsync_sqlite
object CrossNsOwned extends CrossNsOwned with TestAsync_sqlite
object Types extends Types with TestAsync_sqlite
