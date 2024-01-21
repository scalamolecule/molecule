package molecule.sql.mysql.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mysql.setup.TestAsync_mysql

object Adjacent extends Adjacent with TestAsync_mysql
object CrossNs extends CrossNs with TestAsync_mysql
object CrossNsOwned extends CrossNsOwned with TestAsync_mysql
object Semantics extends Semantics with TestAsync_mysql
object Sorting extends Sorting with TestAsync_mysql
object Types extends Types with TestAsync_mysql
