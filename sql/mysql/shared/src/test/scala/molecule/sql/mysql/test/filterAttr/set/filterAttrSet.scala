package molecule.sql.mysql.test.filterAttr.set

import molecule.coreTests.test.filterAttr.set._
import molecule.sql.mysql.setup.TestAsync_mysql

object Adjacent extends Adjacent with TestAsync_mysql
object CrossNs extends CrossNs with TestAsync_mysql
object Semantics extends Semantics with TestAsync_mysql
object Types extends Types with TestAsync_mysql
