package molecule.sql.mariadb.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Adjacent extends Adjacent with TestAsync_mariadb
object CrossNs extends CrossNs with TestAsync_mariadb
object CrossNsOwned extends CrossNsOwned with TestAsync_mariadb
object Types extends Types with TestAsync_mariadb
