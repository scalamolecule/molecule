package molecule.sql.mysql.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mysql.setup.TestAsync_mysql

object Adjacent extends Adjacent with TestAsync_mysql
object CrossNs extends CrossNs with TestAsync_mysql
object CrossNsOwned extends CrossNsOwned with TestAsync_mysql
object Types extends Types with TestAsync_mysql
