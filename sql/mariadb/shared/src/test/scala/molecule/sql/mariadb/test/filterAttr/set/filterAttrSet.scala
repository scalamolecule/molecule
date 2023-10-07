package molecule.sql.mariadb.test.filterAttr.set

import molecule.coreTests.test.filterAttr.set._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Adjacent extends Adjacent with TestAsync_mariadb
object CrossNs extends CrossNs with TestAsync_mariadb
object Semantics extends Semantics with TestAsync_mariadb
object Types extends Types with TestAsync_mariadb
